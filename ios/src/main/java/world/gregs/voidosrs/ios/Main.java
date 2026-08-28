package world.gregs.voidosrs.ios;

import org.robovm.apple.avfoundation.AVAudioSession;
import org.robovm.apple.avfoundation.AVAudioSessionCategory;
import org.robovm.apple.avfoundation.AVAudioSessionCategoryOptions;
import org.robovm.apple.foundation.NSAutoreleasePool;
import org.robovm.apple.uikit.UIApplication;
import org.robovm.apple.uikit.UIApplicationDelegateAdapter;
import org.robovm.apple.uikit.UIApplicationLaunchOptions;
import org.robovm.apple.uikit.UIColor;
import org.robovm.apple.uikit.UIScreen;
import org.robovm.apple.uikit.UIWindow;

public class Main extends UIApplicationDelegateAdapter {
    private UIWindow window;

    @Override
    public boolean didFinishLaunching(UIApplication application, UIApplicationLaunchOptions launchOptions) {
        System.setProperty("java.net.preferIPv4Stack", "true");
        // Activate Playback early so AudioQueue (voidsound) is not muted / silent
        // until the first line opens on the mixer thread.
        // MixWithOthers: exclusive Playback interrupts PiP / other apps even when
        // in-game volume is 0 — we only need a session for our mixer, not audio focus.
        try {
            AVAudioSession session = AVAudioSession.getSharedInstance();
            session.setCategory(AVAudioSessionCategory.Playback,
                    AVAudioSessionCategoryOptions.MixWithOthers);
            session.setActive(true);
        } catch (Throwable t) {
            t.printStackTrace();
        }
        window = new UIWindow(UIScreen.getMainScreen().getBounds());
        window.setBackgroundColor(UIColor.black());
        // Keep screen from dimming / sleeping while the game is open
        window.setIdleTimerDisabled(true);
        window.setRootViewController(new GameController());
        window.makeKeyAndVisible();
        return true;
    }

    public static void main(String[] args) {
        NSAutoreleasePool pool = new NSAutoreleasePool();
        UIApplication.main(args, null, Main.class);
        pool.close();
    }
}
