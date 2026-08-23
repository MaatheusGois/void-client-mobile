package world.gregs.voidosrs.ios;

import org.robovm.apple.coregraphics.CGRect;
import org.robovm.apple.dispatch.DispatchQueue;
import org.robovm.apple.foundation.NSArray;
import org.robovm.apple.foundation.NSRange;
import org.robovm.apple.foundation.NSURL;
import org.robovm.apple.uikit.NSTextAlignment;
import org.robovm.apple.uikit.UIButton;
import org.robovm.apple.uikit.UIButtonType;
import org.robovm.apple.uikit.UIColor;
import org.robovm.apple.uikit.UIControl;
import org.robovm.apple.uikit.UIControlState;
import org.robovm.apple.uikit.UIEvent;
import org.robovm.apple.uikit.UIInterfaceOrientationMask;
import org.robovm.apple.uikit.UIKeyboardAppearance;
import org.robovm.apple.uikit.UIReturnKeyType;
import org.robovm.apple.uikit.UITextAutocapitalizationType;
import org.robovm.apple.uikit.UITextAutocorrectionType;
import org.robovm.apple.uikit.UITextField;
import org.robovm.apple.uikit.UITextFieldDelegateAdapter;
import org.robovm.apple.uikit.UITextSpellCheckingType;
import org.robovm.apple.uikit.UIView;
import org.robovm.apple.uikit.UIViewController;
import org.robovm.apple.foundation.NSFileManager;
import org.robovm.apple.foundation.NSSearchPathDirectory;
import org.robovm.apple.foundation.NSSearchPathDomainMask;

import voidawt.AwtHost;
import voidawt.event.KeyEvent;

public class GameController extends UIViewController {
    private GameView game;
    private UITextField ime;
    private UIButton keyboardBall;
    private boolean keyboardOpen;
    private boolean clientStarted;
    private boolean syncingText;
    private String typedBuffer = "";

    @Override
    public void loadView() {
        setUserHome();
        UIView root = new UIView();
        root.setBackgroundColor(UIColor.black());
        game = new GameView(new CGRect(0, 0, 1, 1));
        game.setSizeListener(new Runnable() {
            public void run() {
                startClientIfReady(game.viewWidth(), game.viewHeight());
            }
        });
        root.addSubview(game);

        ime = new UITextField(new CGRect(0, 0, 1, 1));
        ime.setHidden(true);
        ime.setAlpha(0);
        ime.setAutocapitalizationType(UITextAutocapitalizationType.None);
        ime.setAutocorrectionType(UITextAutocorrectionType.No);
        ime.setSpellCheckingType(UITextSpellCheckingType.No);
        ime.setKeyboardAppearance(UIKeyboardAppearance.Dark);
        ime.setReturnKeyType(UIReturnKeyType.Go);
        ime.setDelegate(new UITextFieldDelegateAdapter() {
            @Override
            public boolean shouldReturn(UITextField textField) {
                injectEnter();
                hideKeyboard();
                return true;
            }

            @Override
            public boolean shouldChangeCharacters(UITextField textField, NSRange range, String string) {
                if (syncingText) {
                    return true;
                }
                if (string == null) {
                    string = "";
                }
                if (string.length() == 0) {
                    long removed = range.getLength();
                    for (int i = 0; i < removed; i++) {
                        injectBackspace();
                    }
                } else {
                    for (int i = 0; i < string.length(); i++) {
                        injectChar(string.charAt(i));
                    }
                }
                return true;
            }
        });
        root.addSubview(ime);

        keyboardBall = new UIButton(UIButtonType.System);
        keyboardBall.setTitle("⌨", UIControlState.Normal);
        keyboardBall.setTitleColor(UIColor.white(), UIControlState.Normal);
        keyboardBall.setBackgroundColor(new UIColor(0.13, 0.13, 0.13, 0.95));
        keyboardBall.getLayer().setCornerRadius(20);
        keyboardBall.getLayer().setBorderWidth(1);
        keyboardBall.getLayer().setBorderColor(new UIColor(0.88, 0.69, 0.25, 1).getCGColor());
        keyboardBall.getTitleLabel().setTextAlignment(NSTextAlignment.Center);
        keyboardBall.addOnTouchUpInsideListener(new UIControl.OnTouchUpInsideListener() {
            public void onTouchUpInside(UIControl control, UIEvent event) {
                if (keyboardOpen) {
                    hideKeyboard();
                } else {
                    showKeyboard();
                }
            }
        });
        // Keyboard ball hidden — soft keyboard opens on in-game text-field taps.
        keyboardBall.setHidden(true);
        setView(root);
        AwtHost.softKeyboardListener = new AwtHost.SoftKeyboardListener() {
            public void showSoftKeyboard(final String reason) {
                System.out.println("void-osrs softKeyboard show: " + reason);
                DispatchQueue.getMainQueue().async(new Runnable() {
                    public void run() {
                        showKeyboard();
                    }
                });
            }

            public void hideSoftKeyboard(final String reason) {
                System.out.println("void-osrs softKeyboard hide: " + reason);
                DispatchQueue.getMainQueue().async(new Runnable() {
                    public void run() {
                        hideKeyboard();
                    }
                });
            }
        };
    }

    @Override
    public void viewDidLayoutSubviews() {
        super.viewDidLayoutSubviews();
        CGRect bounds = getView().getBounds();
        game.setFrame(bounds);
        ime.setFrame(new CGRect(0, bounds.getHeight() - 1, 1, 1));
        keyboardBall.setFrame(new CGRect(20, 20, 40, 40));
    }

    @Override
    public UIInterfaceOrientationMask getSupportedInterfaceOrientations() {
        return UIInterfaceOrientationMask.Landscape;
    }

    @Override
    public boolean prefersStatusBarHidden() {
        return true;
    }

    private void showKeyboard() {
        keyboardOpen = true;
        if (keyboardBall != null) {
            keyboardBall.setAlpha(0.35);
        }
        syncingText = true;
        ime.setText(typedBuffer);
        syncingText = false;
        ime.becomeFirstResponder();
    }

    private void hideKeyboard() {
        keyboardOpen = false;
        if (keyboardBall != null) {
            keyboardBall.setAlpha(1);
        }
        ime.resignFirstResponder();
    }

    private void injectChar(char c) {
        typedBuffer = typedBuffer + c;
        int code = Character.toUpperCase(c);
        AwtHost.injectKey(KeyEvent.KEY_PRESSED, code, c);
        AwtHost.injectKey(KeyEvent.KEY_TYPED, 0, c);
        AwtHost.injectKey(KeyEvent.KEY_RELEASED, code, c);
    }

    private void injectBackspace() {
        if (typedBuffer.length() > 0) {
            typedBuffer = typedBuffer.substring(0, typedBuffer.length() - 1);
        }
        AwtHost.injectKey(KeyEvent.KEY_PRESSED, KeyEvent.VK_BACK_SPACE, '\b');
        AwtHost.injectKey(KeyEvent.KEY_TYPED, 0, '\b');
        AwtHost.injectKey(KeyEvent.KEY_RELEASED, KeyEvent.VK_BACK_SPACE, '\b');
    }

    private void injectEnter() {
        AwtHost.injectKey(KeyEvent.KEY_PRESSED, KeyEvent.VK_ENTER, '\n');
        AwtHost.injectKey(KeyEvent.KEY_TYPED, 0, '\n');
        AwtHost.injectKey(KeyEvent.KEY_RELEASED, KeyEvent.VK_ENTER, '\n');
        typedBuffer = "";
        syncingText = true;
        ime.setText("");
        syncingText = false;
    }

    private static boolean isSimulator() {
        return System.getenv("SIMULATOR_DEVICE_NAME") != null
                || System.getenv("SIMULATOR_UDID") != null;
    }

    private static void setUserHome() {
        try {
            NSArray<NSURL> urls = NSFileManager.getDefaultManager().getURLsForDirectory(
                    NSSearchPathDirectory.DocumentDirectory, NSSearchPathDomainMask.UserDomainMask);
            if (urls != null && urls.size() > 0) {
                System.setProperty("user.home", urls.get(0).getPath());
            }
        } catch (Throwable ignored) {
        }
    }

    private void startClientIfReady(int width, int height) {
        if (clientStarted || width <= 0 || height <= 0) {
            return;
        }
        clientStarted = true;
        AwtHost.setDisplaySize(width, height);
        AwtHost.presenter = game;
        new Thread(new Runnable() {
            public void run() {
                try {
                    Class<?> loaderCl = Class.forName("Loader");
                    String server = System.getProperty("void.server");
                    if (server == null || server.isEmpty()) {
                        server = isSimulator() ? "127.0.0.1" : "192.168.18.214";
                    }
                    loaderCl.getField("address").set(null, server);
                    loaderCl.getField("debug").set(null, true);
                    Object loader = loaderCl.getDeclaredConstructor().newInstance();
                    loaderCl.getMethod("setSize", int.class, int.class).invoke(loader, AwtHost.GAME_WIDTH, AwtHost.GAME_HEIGHT);
                    AwtHost.setRoot((voidawt.Component) loader);
                    loaderCl.getMethod("init").invoke(loader);
                } catch (Throwable t) {
                    t.printStackTrace();
                }
            }
        }, "void-client").start();
    }
}
