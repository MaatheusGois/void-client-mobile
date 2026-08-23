package voidawt;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;

import voidawt.event.FocusEvent;
import voidawt.event.KeyEvent;
import voidawt.event.MouseEvent;
import voidawt.event.MouseWheelEvent;

public final class AwtHost {
    public static final int GAME_WIDTH = 765;
    public static final int GAME_HEIGHT = 503;

    public static volatile Presenter presenter;
    public static volatile Component root;
    public static volatile Canvas gameCanvas;
    private static final Handler MAIN = new Handler(Looper.getMainLooper());

    public interface Presenter {
        void present(Bitmap frame);
    }

    public static void setRoot(Component component) {
        root = component;
        if (component != null) {
            component.setSize(GAME_WIDTH, GAME_HEIGHT);
        }
    }

    public static void onGameCanvas(Canvas canvas) {
        gameCanvas = canvas;
    }

    public static void present(int[] pixels, int width, int height) {
        Presenter p = presenter;
        if (p == null || pixels == null || width <= 0 || height <= 0) {
            return;
        }
        final Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        int[] argb = new int[width * height];
        int n = Math.min(argb.length, pixels.length);
        for (int i = 0; i < n; i++) {
            int rgb = pixels[i];
            argb[i] = (rgb & 0xff000000) == 0 ? rgb | 0xff000000 : rgb;
        }
        bitmap.setPixels(argb, 0, width, 0, 0, width, height);
        MAIN.post(new Runnable() {
            public void run() {
                p.present(bitmap);
            }
        });
    }

    public static void injectMouse(int id, int x, int y, int button, int clickCount) {
        Component target = gameCanvas != null ? gameCanvas : root;
        if (target == null) {
            return;
        }
        MouseEvent e = new MouseEvent(target, id, System.currentTimeMillis(), 0, x, y, clickCount, button == 3, button);
        if (id == MouseEvent.MOUSE_WHEEL) {
            return;
        }
        target.dispatchMouse(e);
    }

    public static void injectWheel(int x, int y, int rotation) {
        Component target = gameCanvas != null ? gameCanvas : root;
        if (target == null) {
            return;
        }
        MouseWheelEvent e = new MouseWheelEvent(target, MouseEvent.MOUSE_WHEEL, System.currentTimeMillis(), 0, x, y, 0, false, rotation);
        target.dispatchWheel(e);
    }

    public static void injectKey(int id, int keyCode, char keyChar) {
        Component target = gameCanvas != null ? gameCanvas : root;
        if (target == null) {
            return;
        }
        KeyEvent e = new KeyEvent(target, id, System.currentTimeMillis(), 0, keyCode, keyChar);
        target.dispatchKey(e);
    }

    public static void injectFocus(boolean gained) {
        Component target = gameCanvas != null ? gameCanvas : root;
        if (target == null) {
            return;
        }
        FocusEvent e = new FocusEvent(target, gained ? FocusEvent.FOCUS_GAINED : FocusEvent.FOCUS_LOST);
        target.dispatchFocus(e);
    }
}
