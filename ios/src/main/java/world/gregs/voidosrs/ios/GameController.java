package world.gregs.voidosrs.ios;

import org.robovm.apple.coregraphics.CGAffineTransform;
import org.robovm.apple.coregraphics.CGRect;
import org.robovm.apple.dispatch.DispatchQueue;
import org.robovm.apple.foundation.NSArray;
import org.robovm.apple.foundation.NSObject;
import org.robovm.apple.foundation.NSRange;
import org.robovm.apple.foundation.NSURL;
import org.robovm.apple.uikit.NSLineBreakMode;
import org.robovm.apple.uikit.NSTextAlignment;
import org.robovm.apple.uikit.UIAlertAction;
import org.robovm.apple.uikit.UIAlertActionStyle;
import org.robovm.apple.uikit.UIAlertController;
import org.robovm.apple.uikit.UIAlertControllerStyle;
import org.robovm.apple.uikit.UIButton;
import org.robovm.apple.uikit.UIButtonType;
import org.robovm.apple.uikit.UIColor;
import org.robovm.apple.uikit.UIControl;
import org.robovm.apple.uikit.UIControlState;
import org.robovm.apple.uikit.UIEvent;
import org.robovm.apple.uikit.UIFont;
import org.robovm.apple.uikit.UIInterfaceOrientationMask;
import org.robovm.apple.uikit.UIKeyboardAnimation;
import org.robovm.apple.uikit.UIKeyboardAppearance;
import org.robovm.apple.uikit.UIKeyboardType;
import org.robovm.apple.uikit.UILabel;
import org.robovm.apple.uikit.UIReturnKeyType;
import org.robovm.apple.uikit.UITextAutocapitalizationType;
import org.robovm.apple.uikit.UITextAutocorrectionType;
import org.robovm.apple.uikit.UIControlContentHorizontalAlignment;
import org.robovm.apple.uikit.UITextBorderStyle;
import org.robovm.apple.uikit.UITextFieldViewMode;
import org.robovm.apple.uikit.UITextField;
import org.robovm.apple.uikit.UITextFieldDelegateAdapter;
import org.robovm.apple.uikit.UITextSpellCheckingType;
import org.robovm.apple.uikit.UITextView;
import org.robovm.apple.uikit.UIView;
import org.robovm.apple.uikit.UIViewController;
import org.robovm.apple.uikit.UIWindow;
import org.robovm.objc.block.VoidBlock1;
import org.robovm.apple.foundation.NSFileManager;
import org.robovm.apple.foundation.NSSearchPathDirectory;
import org.robovm.apple.foundation.NSSearchPathDomainMask;

import java.util.concurrent.TimeUnit;

import voidawt.AwtHost;
import voidawt.event.KeyEvent;
import world.gregs.voidosrs.AffiliationDisclaimer;
import world.gregs.voidosrs.ServerPrefs;

public class GameController extends UIViewController {
    private GameView game;
    private UITextField ime;
    private UIButton keyboardBall;
    private boolean keyboardOpen;
    private boolean clientStarted;
    private boolean syncingText;
    private String typedBuffer = "";
    private UIView serverOverlay;
    private UIView serverCard;
    private UILabel serverTitle;
    private UILabel serverDetail;
    private UITextField serverField;
    private UIButton serverConnect;
    private UIButton serverCancel;
    private UIButton changeServerBtn;
    private UIButton[] historyBtns;
    private UILabel serverRecent;
    private boolean loginPollScheduled;
    private UIView disclaimerOverlay;
    private UIView disclaimerCard;
    private UILabel disclaimerTitle;
    private UITextView disclaimerBody;
    private UIButton disclaimerAccept;
    private NSObject keyboardFrameObserver;
    private NSObject keyboardDidShowObserver;
    private NSObject keyboardWillHideObserver;

    @Override
    public void loadView() {
        setUserHome();
        UIView root = new UIView();
        root.setBackgroundColor(UIColor.black());
        root.setClipsToBounds(true);
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

        changeServerBtn = pillButton("Server");
        changeServerBtn.setHidden(true);
        changeServerBtn.setUserInteractionEnabled(true);
        changeServerBtn.addOnTouchUpInsideListener(new UIControl.OnTouchUpInsideListener() {
            public void onTouchUpInside(UIControl control, UIEvent event) {
                showServerOverlay(false);
            }
        });
        root.addSubview(changeServerBtn);
        buildServerOverlay(root);
        buildDisclaimerOverlay(root);

        setView(root);
        maybeShowDisclaimerThenContinue();
        scheduleLoginPoll();
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

            public void toggleSoftKeyboard(final String reason) {
                System.out.println("void-osrs softKeyboard toggle: " + reason + " open=" + keyboardOpen);
                DispatchQueue.getMainQueue().async(new Runnable() {
                    public void run() {
                        if (keyboardOpen) {
                            hideKeyboard();
                        } else {
                            showKeyboard();
                        }
                    }
                });
            }

            public void syncSoftKeyboardToDevConsole() {
                // No-op: delayed sync raced and could re-show the keyboard on close.
            }
        };
        keyboardFrameObserver = UIWindow.Notifications.observeKeyboardWillChangeFrame(
                new VoidBlock1<UIKeyboardAnimation>() {
                    public void invoke(UIKeyboardAnimation animation) {
                        panGameForKeyboard(animation, false);
                    }
                });
        keyboardDidShowObserver = UIWindow.Notifications.observeKeyboardDidShow(
                new VoidBlock1<UIKeyboardAnimation>() {
                    public void invoke(UIKeyboardAnimation animation) {
                        panGameForKeyboard(animation, false);
                    }
                });
        keyboardWillHideObserver = UIWindow.Notifications.observeKeyboardWillHide(
                new VoidBlock1<UIKeyboardAnimation>() {
                    public void invoke(UIKeyboardAnimation animation) {
                        panGameForKeyboard(animation, true);
                    }
                });
    }

    @Override
    public void viewDidLayoutSubviews() {
        super.viewDidLayoutSubviews();
        CGRect bounds = getView().getBounds();
        game.setFrame(bounds);
        ime.setFrame(new CGRect(0, bounds.getHeight() - 1, 1, 1));
        keyboardBall.setFrame(new CGRect(20, 20, 40, 40));
        layoutServerUi(bounds);
        layoutDisclaimerUi(bounds);
    }

    /**
     * Keep the game landscape-only. Info.plist still lists all iPad orientations so
     * the system allows multitasking / window resize (TN3192); this intersects that set.
     */
    @Override
    public UIInterfaceOrientationMask getSupportedInterfaceOrientations() {
        return UIInterfaceOrientationMask.Landscape;
    }

    @Override
    public boolean prefersStatusBarHidden() {
        return true;
    }

    private void showKeyboard() {
        if (serverOverlay != null && !serverOverlay.isHidden()) {
            return;
        }
        keyboardOpen = true;
        AwtHost.SOFT_KEYBOARD_OPEN = true;
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
        AwtHost.SOFT_KEYBOARD_OPEN = false;
        if (keyboardBall != null) {
            keyboardBall.setAlpha(1);
        }
        ime.resignFirstResponder();
        clearKeyboardInset();
    }

    private void clearKeyboardInset() {
        if (game == null) {
            AwtHost.setKeyboardInset(0, 1);
            return;
        }
        game.setTransform(CGAffineTransform.Identity());
        AwtHost.setKeyboardInset(0, Math.max(1, game.viewHeight()));
    }

    /** Report IME overlap so the client can lift only the chatbox. */
    private void panGameForKeyboard(UIKeyboardAnimation animation, boolean hiding) {
        if (game == null || getView() == null) {
            return;
        }
        game.setTransform(CGAffineTransform.Identity());
        if (hiding || !keyboardOpen) {
            clearKeyboardInset();
            System.out.println("void-osrs keyboard pan inset=0 open=" + keyboardOpen + " hide=" + hiding);
            return;
        }
        boolean overlay = serverOverlay != null && !serverOverlay.isHidden();
        int inset = 0;
        int viewHpx = Math.max(1, game.viewHeight());
        if (!overlay && animation != null && animation.getEndFrame() != null) {
            try {
                CGRect local = getView().convertRectFromView(animation.getEndFrame(), null);
                double viewH = getView().getBounds().getHeight();
                if (viewH < 32) {
                    viewH = viewHpx;
                }
                double kbTop = local.getOrigin().getY();
                double kbH = local.getSize().getHeight();
                double overlap = viewH - kbTop;
                if (overlap < 0) {
                    overlap = 0;
                }
                if (kbH > 0 && overlap > kbH) {
                    overlap = kbH;
                }
                double cap = viewH * 0.45;
                if (overlap > cap) {
                    overlap = cap;
                }
                if (overlap > 1) {
                    inset = (int) Math.round(overlap) + 15;
                }
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
        System.out.println("void-osrs keyboard pan inset=" + inset + " open=" + keyboardOpen);
        AwtHost.setKeyboardInset(inset, viewHpx);
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

    private static String defaultHostHint() {
        return isSimulator() ? "127.0.0.1" : "192.168.18.188";
    }

    private static String propertyOverride() {
        String v = System.getProperty("void.server");
        return ServerPrefs.normalize(v);
    }

    private static String resolveBootHost() {
        String override = propertyOverride();
        if (override != null) {
            return override;
        }
        return ServerPrefs.load();
    }

    private static UIColor gold() {
        return new UIColor(0.83, 0.66, 0.28, 1);
    }

    private UIButton pillButton(String title) {
        return styleButton(title, false);
    }

    private UIButton styleButton(String title, boolean primary) {
        UIButton b = new UIButton(UIButtonType.Custom);
        b.setTitle(title, UIControlState.Normal);
        b.getTitleLabel().setFont(UIFont.getBoldSystemFont(15));
        b.getLayer().setCornerRadius(10);
        b.getLayer().setMasksToBounds(true);
        if (primary) {
            b.setTitleColor(new UIColor(0.10, 0.08, 0.04, 1), UIControlState.Normal);
            b.setBackgroundColor(gold());
            b.getLayer().setBorderWidth(0);
        } else {
            b.setTitleColor(new UIColor(0.92, 0.90, 0.84, 1), UIControlState.Normal);
            b.setBackgroundColor(new UIColor(0.14, 0.13, 0.11, 1));
            b.getLayer().setBorderWidth(1);
            b.getLayer().setBorderColor(new UIColor(0.83, 0.66, 0.28, 0.45).getCGColor());
        }
        return b;
    }

    private UIButton historyRowButton() {
        UIButton b = new UIButton(UIButtonType.Custom);
        b.setTitleColor(new UIColor(0.86, 0.84, 0.78, 1), UIControlState.Normal);
        b.getTitleLabel().setFont(UIFont.getSystemFont(14));
        b.setContentHorizontalAlignment(UIControlContentHorizontalAlignment.Left);
        b.getLayer().setCornerRadius(10);
        b.getLayer().setMasksToBounds(true);
        b.setBackgroundColor(new UIColor(0.13, 0.12, 0.10, 1));
        b.getLayer().setBorderWidth(1);
        b.getLayer().setBorderColor(new UIColor(1, 1, 1, 0.06).getCGColor());
        return b;
    }

    private void styleHistoryRow(UIButton b, String host, String selected) {
        boolean on = host != null && host.equals(selected);
        b.setTitle("  " + host, UIControlState.Normal);
        if (on) {
            b.setTitleColor(gold(), UIControlState.Normal);
            b.setBackgroundColor(new UIColor(0.83, 0.66, 0.28, 0.14));
            b.getLayer().setBorderColor(gold().getCGColor());
            b.getLayer().setBorderWidth(1);
        } else {
            b.setTitleColor(new UIColor(0.86, 0.84, 0.78, 1), UIControlState.Normal);
            b.setBackgroundColor(new UIColor(0.13, 0.12, 0.10, 1));
            b.getLayer().setBorderColor(new UIColor(1, 1, 1, 0.06).getCGColor());
            b.getLayer().setBorderWidth(1);
        }
    }

    /**
     * First launch only: scrollable non-affiliation disclaimer before server picker / client boot.
     * Acceptance is persisted in {@code user.home/void-disclaimer.txt}.
     */
    private void maybeShowDisclaimerThenContinue() {
        if (AffiliationDisclaimer.isAccepted()) {
            continueAfterDisclaimer();
            return;
        }
        if (disclaimerOverlay != null) {
            disclaimerOverlay.setHidden(false);
            getView().bringSubviewToFront(disclaimerOverlay);
            layoutDisclaimerUi(getView().getBounds());
        }
    }

    private void continueAfterDisclaimer() {
        if (disclaimerOverlay != null) {
            disclaimerOverlay.setHidden(true);
        }
        if (resolveBootHost() == null) {
            showServerOverlay(false);
        }
        startClientIfReady(game.viewWidth(), game.viewHeight());
    }

    private void buildDisclaimerOverlay(UIView root) {
        disclaimerOverlay = new UIView();
        disclaimerOverlay.setBackgroundColor(new UIColor(0.02, 0.01, 0.01, 0.78));
        disclaimerOverlay.setHidden(true);
        disclaimerOverlay.setUserInteractionEnabled(true);

        disclaimerCard = new UIView();
        disclaimerCard.setBackgroundColor(new UIColor(0.07, 0.06, 0.05, 0.97));
        disclaimerCard.getLayer().setCornerRadius(16);
        disclaimerCard.getLayer().setBorderWidth(1);
        disclaimerCard.getLayer().setBorderColor(new UIColor(0.83, 0.66, 0.28, 0.7).getCGColor());
        disclaimerCard.getLayer().setMasksToBounds(true);

        disclaimerTitle = new UILabel();
        disclaimerTitle.setText(AffiliationDisclaimer.TITLE);
        disclaimerTitle.setTextColor(new UIColor(0.97, 0.95, 0.88, 1));
        disclaimerTitle.setFont(UIFont.getBoldSystemFont(20));
        disclaimerTitle.setTextAlignment(NSTextAlignment.Left);
        disclaimerTitle.setNumberOfLines(2);
        disclaimerTitle.setLineBreakMode(NSLineBreakMode.WordWrapping);

        disclaimerBody = new UITextView(new CGRect(0, 0, 1, 1));
        disclaimerBody.setText(AffiliationDisclaimer.BODY);
        disclaimerBody.setEditable(false);
        disclaimerBody.setSelectable(false);
        disclaimerBody.setTextColor(new UIColor(0.90, 0.88, 0.82, 1));
        disclaimerBody.setBackgroundColor(UIColor.clear());
        disclaimerBody.setFont(UIFont.getSystemFont(13));
        disclaimerBody.setTextAlignment(NSTextAlignment.Left);

        disclaimerAccept = pillButton(AffiliationDisclaimer.ACCEPT_LABEL);
        disclaimerAccept.setBackgroundColor(gold());
        disclaimerAccept.setTitleColor(new UIColor(0.10, 0.08, 0.04, 1), UIControlState.Normal);
        disclaimerAccept.addOnTouchUpInsideListener(new UIControl.OnTouchUpInsideListener() {
            public void onTouchUpInside(UIControl control, UIEvent event) {
                AffiliationDisclaimer.markAccepted();
                continueAfterDisclaimer();
            }
        });

        disclaimerCard.addSubview(disclaimerTitle);
        disclaimerCard.addSubview(disclaimerBody);
        disclaimerCard.addSubview(disclaimerAccept);
        disclaimerOverlay.addSubview(disclaimerCard);
        root.addSubview(disclaimerOverlay);
    }

    private void layoutDisclaimerUi(CGRect bounds) {
        if (disclaimerOverlay == null) {
            return;
        }
        double w = bounds.getWidth();
        double h = bounds.getHeight();
        disclaimerOverlay.setFrame(bounds);
        double pad = 18;
        double cardW = Math.min(520, w - 40);
        double cardH = Math.min(h - 40, Math.max(280, h * 0.86));
        double cardX = (w - cardW) / 2;
        double cardY = Math.max(12, (h - cardH) / 2);
        disclaimerCard.setFrame(new CGRect(cardX, cardY, cardW, cardH));
        disclaimerTitle.setFrame(new CGRect(pad, 16, cardW - pad * 2, 44));
        double btnH = 44;
        double bodyTop = 68;
        double bodyH = cardH - bodyTop - btnH - 28;
        disclaimerBody.setFrame(new CGRect(pad - 4, bodyTop, cardW - pad * 2 + 8, bodyH));
        disclaimerAccept.setFrame(new CGRect(pad, cardH - btnH - 16, cardW - pad * 2, btnH));
    }

    private void buildServerOverlay(UIView root) {
        serverOverlay = new UIView();
        serverOverlay.setBackgroundColor(new UIColor(0.02, 0.01, 0.01, 0.62));
        serverOverlay.setHidden(true);

        serverCard = new UIView();
        serverCard.setBackgroundColor(new UIColor(0.07, 0.06, 0.05, 0.97));
        serverCard.getLayer().setCornerRadius(16);
        serverCard.getLayer().setBorderWidth(1);
        serverCard.getLayer().setBorderColor(new UIColor(0.83, 0.66, 0.28, 0.7).getCGColor());
        serverCard.getLayer().setMasksToBounds(true);

        serverTitle = new UILabel();
        serverTitle.setText("Server");
        serverTitle.setTextColor(new UIColor(0.97, 0.95, 0.88, 1));
        serverTitle.setFont(UIFont.getBoldSystemFont(22));
        serverTitle.setTextAlignment(NSTextAlignment.Left);

        serverDetail = new UILabel();
        serverDetail.setTextColor(new UIColor(0.93, 0.62, 0.48, 1));
        serverDetail.setFont(UIFont.getSystemFont(13));
        serverDetail.setTextAlignment(NSTextAlignment.Left);
        serverDetail.setNumberOfLines(3);
        serverDetail.setLineBreakMode(NSLineBreakMode.WordWrapping);
        serverDetail.setHidden(true);

        serverField = new UITextField();
        serverField.setPlaceholder(defaultHostHint());
        serverField.setTextColor(new UIColor(0.97, 0.95, 0.88, 1));
        serverField.setBackgroundColor(new UIColor(0.12, 0.11, 0.09, 1));
        serverField.setBorderStyle(UITextBorderStyle.None);
        serverField.getLayer().setCornerRadius(10);
        serverField.getLayer().setMasksToBounds(true);
        serverField.getLayer().setBorderWidth(1);
        serverField.getLayer().setBorderColor(new UIColor(0.83, 0.66, 0.28, 0.35).getCGColor());
        UIView pad = new UIView(new CGRect(0, 0, 14, 44));
        serverField.setLeftView(pad);
        serverField.setLeftViewMode(UITextFieldViewMode.Always);
        serverField.setRightView(new UIView(new CGRect(0, 0, 10, 44)));
        serverField.setRightViewMode(UITextFieldViewMode.Always);
        serverField.setAutocapitalizationType(UITextAutocapitalizationType.None);
        serverField.setAutocorrectionType(UITextAutocorrectionType.No);
        serverField.setSpellCheckingType(UITextSpellCheckingType.No);
        serverField.setKeyboardAppearance(UIKeyboardAppearance.Dark);
        serverField.setKeyboardType(UIKeyboardType.URL);
        serverField.setReturnKeyType(UIReturnKeyType.Go);
        serverField.setFont(UIFont.getSystemFont(16));
        serverField.setDelegate(new UITextFieldDelegateAdapter() {
            @Override
            public boolean shouldReturn(UITextField textField) {
                applyServerFromOverlay();
                return true;
            }
        });

        serverRecent = new UILabel();
        serverRecent.setText("RECENT");
        serverRecent.setTextColor(new UIColor(0.83, 0.66, 0.28, 0.75));
        serverRecent.setFont(UIFont.getBoldSystemFont(11));
        serverRecent.setHidden(true);

        serverConnect = styleButton("Connect", true);
        serverConnect.addOnTouchUpInsideListener(new UIControl.OnTouchUpInsideListener() {
            public void onTouchUpInside(UIControl control, UIEvent event) {
                applyServerFromOverlay();
            }
        });

        serverCancel = styleButton("Cancel", false);
        serverCancel.addOnTouchUpInsideListener(new UIControl.OnTouchUpInsideListener() {
            public void onTouchUpInside(UIControl control, UIEvent event) {
                hideServerOverlay();
            }
        });

        serverCard.addSubview(serverTitle);
        serverCard.addSubview(serverDetail);
        serverCard.addSubview(serverField);
        serverCard.addSubview(serverRecent);
        historyBtns = new UIButton[ServerPrefs.MAX_HISTORY];
        for (int i = 0; i < historyBtns.length; i++) {
            final int idx = i;
            UIButton chip = historyRowButton();
            chip.setHidden(true);
            chip.addOnTouchUpInsideListener(new UIControl.OnTouchUpInsideListener() {
                public void onTouchUpInside(UIControl control, UIEvent event) {
                    String t = historyBtns[idx].getTitle(UIControlState.Normal);
                    if (t != null) {
                        t = t.trim();
                    }
                    if (t != null && t.length() > 0) {
                        serverField.setText(t);
                        layoutServerUi(getView().getBounds());
                    }
                }
            });
            historyBtns[i] = chip;
            serverCard.addSubview(chip);
        }
        serverCard.addSubview(serverConnect);
        serverCard.addSubview(serverCancel);
        serverOverlay.addSubview(serverCard);
        root.addSubview(serverOverlay);
    }

    private void layoutServerUi(CGRect bounds) {
        double w = bounds.getWidth();
        double h = bounds.getHeight();
        if (changeServerBtn != null) {
            changeServerBtn.setFrame(new CGRect(16, 16, 92, 36));
        }
        if (serverOverlay == null) {
            return;
        }
        serverOverlay.setFrame(bounds);
        String[] history = ServerPrefs.loadAll();
        int shown = 0;
        if (historyBtns != null) {
            for (int i = 0; i < historyBtns.length; i++) {
                if (i < history.length) {
                    historyBtns[i].setTitle(history[i], UIControlState.Normal);
                    historyBtns[i].setHidden(false);
                    shown++;
                } else {
                    historyBtns[i].setHidden(true);
                }
            }
        }
        double pad = 22;
        double cardW = Math.min(440, w - 48);
        double rowH = 40;
        double rowGap = 8;
        double innerW = cardW - pad * 2;
        double y = 20;
        serverTitle.setFrame(new CGRect(pad, y, innerW, 28));
        y += 28;
        if (serverDetail != null && !serverDetail.isHidden()) {
            y += 6;
            serverDetail.setFrame(new CGRect(pad, y, innerW, 48));
            y += 48;
        }
        y += 12;
        serverField.setFrame(new CGRect(pad, y, innerW, 44));
        y += 44;
        if (shown > 0 && historyBtns != null) {
            y += 14;
            if (serverRecent != null) {
                serverRecent.setHidden(false);
                serverRecent.setFrame(new CGRect(pad, y, innerW, 14));
            }
            y += 18;
            String selected = ServerPrefs.normalize(serverField.getText());
            for (int i = 0; i < shown; i++) {
                styleHistoryRow(historyBtns[i], history[i], selected);
                historyBtns[i].setFrame(new CGRect(pad, y, innerW, rowH));
                y += rowH + rowGap;
            }
        } else if (serverRecent != null) {
            serverRecent.setHidden(true);
        }
        y += 10;
        double btnW = (innerW - 12) / 2;
        if (clientStarted) {
            serverCancel.setHidden(false);
            serverCancel.setFrame(new CGRect(pad, y, btnW, 44));
            serverConnect.setFrame(new CGRect(pad + btnW + 12, y, btnW, 44));
        } else {
            serverCancel.setHidden(true);
            serverConnect.setFrame(new CGRect(pad, y, innerW, 44));
        }
        y += 44 + 22;
        double cardH = y;
        double cardX = (w - cardW) / 2;
        double cardY = Math.max(12, (h - cardH) / 2 - 12);
        serverCard.setFrame(new CGRect(cardX, cardY, cardW, cardH));
    }

    private void showServerOverlay(boolean connectionFailed) {
        String current = resolveBootHost();
        if (current == null) {
            current = defaultHostHint();
        }
        serverField.setText(current);
        if (connectionFailed) {
            serverTitle.setText("Can't reach server");
            serverDetail.setText(current + " isn't working. Try another server, then reopen the app.");
            serverDetail.setHidden(false);
        } else {
            serverTitle.setText("Server");
            serverDetail.setHidden(true);
        }
        serverOverlay.setHidden(false);
        changeServerBtn.setHidden(true);
        hideKeyboard();
        serverField.becomeFirstResponder();
        layoutServerUi(getView().getBounds());
        getView().bringSubviewToFront(serverOverlay);
    }

    private void hideServerOverlay() {
        serverField.resignFirstResponder();
        serverOverlay.setHidden(true);
        getView().sendSubviewToBack(serverOverlay);
    }

    private void applyServerFromOverlay() {
        String host = ServerPrefs.normalize(serverField.getText());
        if (host == null) {
            host = ServerPrefs.normalize(defaultHostHint());
        }
        if (host == null) {
            return;
        }
        if (!clientStarted) {
            ServerPrefs.save(host);
            hideServerOverlay();
            startClientIfReady(game.viewWidth(), game.viewHeight());
            return;
        }
        String previous = ServerPrefs.load();
        if (host.equals(previous)) {
            hideServerOverlay();
            return;
        }
        final String target = host;
        serverField.resignFirstResponder();
        DispatchQueue.getMainQueue().async(new Runnable() {
            public void run() {
                UIAlertController alert = new UIAlertController(
                        "Switch server?",
                        "The app will close. Open it again to connect to the new server.",
                        UIAlertControllerStyle.Alert);
                alert.addAction(new UIAlertAction("Cancel", UIAlertActionStyle.Cancel, null));
                alert.addAction(new UIAlertAction("OK", UIAlertActionStyle.Default, new VoidBlock1<UIAlertAction>() {
                    public void invoke(UIAlertAction action) {
                        ServerPrefs.save(target);
                        hideServerOverlay();
                        System.exit(0);
                    }
                }));
                presentViewController(alert, true, new Runnable() {
                    public void run() {
                    }
                });
            }
        });
    }

    private void scheduleLoginPoll() {
        if (loginPollScheduled) {
            return;
        }
        loginPollScheduled = true;
        pollLoginButton();
    }

    private int lastPolledState = Integer.MIN_VALUE;
    private boolean connectFailOverlayShown;

    private void pollLoginButton() {
        DispatchQueue.getMainQueue().after(200, TimeUnit.MILLISECONDS, new Runnable() {
            public void run() {
                boolean overlayUp = serverOverlay != null && !serverOverlay.isHidden();
                int state = ServerPrefs.gameState();
                boolean failing = ServerPrefs.isConnectFailing();
                boolean show = clientStarted && !overlayUp && ServerPrefs.showsServerPicker();
                if (ServerPrefs.isLoginScreen()) {
                    connectFailOverlayShown = false;
                }
                if (state != lastPolledState) {
                    lastPolledState = state;
                    System.out.println("void-osrs loginBtn state=" + state
                            + " js5fails=" + ServerPrefs.js5FailCount()
                            + " show=" + show + " started=" + clientStarted
                            + " overlay=" + overlayUp);
                }
                if (clientStarted && !overlayUp && failing && !connectFailOverlayShown) {
                    connectFailOverlayShown = true;
                    showServerOverlay(true);
                    pollLoginButton();
                    return;
                }
                if (changeServerBtn != null) {
                    changeServerBtn.setHidden(!show);
                    if (show) {
                        getView().bringSubviewToFront(changeServerBtn);
                    }
                }
                pollLoginButton();
            }
        });
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
        if (!AffiliationDisclaimer.isAccepted()) {
            return;
        }
        final String server = resolveBootHost();
        if (server == null) {
            return;
        }
        clientStarted = true;
        AwtHost.setDisplaySize(width, height);
        AwtHost.presenter = game;
        new Thread(new Runnable() {
            public void run() {
                try {
                    Class<?> loaderCl = Class.forName("Loader");
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
