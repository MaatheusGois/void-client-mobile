package voidawt;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;

import voidawt.event.FocusEvent;
import voidawt.event.KeyEvent;
import voidawt.event.MouseEvent;
import voidawt.event.MouseWheelEvent;

public final class AwtHost {
    /** Default matches fullscreen 800x600 (client boot / display prefs). */
    public static volatile int GAME_WIDTH = 800;
    public static volatile int GAME_HEIGHT = 600;

    /**
     * Cap longest logical edge, or {@code 0} to use the native SurfaceView size.
     * Native size fills the phone; a lower cap makes HUD sprites larger when stretched.
     * Ignored while the client holds an exclusive FS frame ({@code Class34.aFrame476}).
     */
    public static volatile int LOGICAL_MAX_EDGE = 0;

    public static volatile Presenter presenter;
    public static volatile Component root;
    public static volatile Canvas gameCanvas;
    private static final Handler MAIN = new Handler(Looper.getMainLooper());

    public interface Presenter {
        void present(Bitmap frame);
    }

    /**
     * Logical game size (aspect-matched to the surface). Android stretches the
     * presented frame to fill the SurfaceView when a logical cap is set.
     */
    public static void setDisplaySize(int width, int height) {
        int[] locked = fullscreenLogicalSize();
        if (locked != null) {
            width = locked[0];
            height = locked[1];
        } else {
            width = Math.max(1, width);
            height = Math.max(1, height);
            int[] logical = logicalSize(width, height);
            width = logical[0];
            height = logical[1];
        }
        GAME_WIDTH = width;
        GAME_HEIGHT = height;
        Component r = root;
        if (r != null) {
            r.setSize(width, height);
        }
        Canvas c = gameCanvas;
        if (c != null) {
            c.setSize(width, height);
        }
        GraphicsEnvironment.getLocalGraphicsEnvironment()
                .getDefaultScreenDevice()
                .setDisplayMode(new DisplayMode(width, height, 32, 60));
        // Keep client viewport ints in sync so splash/HUD fill the logical frame.
        syncClientViewport(width, height);
    }

    /** Keep surface resize from expanding past exclusive FS (e.g. 800x600). */
    private static int[] fullscreenLogicalSize() {
        try {
            Object frame = Class.forName("Class34").getDeclaredField("aFrame476").get(null);
            if (frame == null) {
                return null;
            }
            int fw = Class.forName("Class346").getDeclaredField("anInt4276").getInt(null);
            int fh = Class.forName("Class239_Sub8").getDeclaredField("anInt5911").getInt(null);
            if (fw <= 0 || fh <= 0) {
                return null;
            }
            return new int[]{fw, fh};
        } catch (Throwable ignored) {
            return null;
        }
    }

    private static void syncClientViewport(int width, int height) {
        try {
            setStaticInt("Class92", "anInt1524", width);
            setStaticInt("ha_Sub2", "anInt7666", height);
            setStaticInt("Class321", "anInt4017", width);
            setStaticInt("Class348_Sub42_Sub8_Sub2", "anInt10432", height);
            setStaticInt("Class272", "anInt3473", width);
            setStaticInt("Class348_Sub22", "anInt6857", height);
            setStaticInt("Class348_Sub48", "anInt7129", 0);
            setStaticInt("Class335", "anInt4167", 0);
            // FS / resizable semantics (method3229 uses Class34.aFrame476 for mode 3).
            setStaticBoolean("Class50_Sub1", "aBoolean5219", true);
        } catch (Throwable ignored) {
        }
    }

    private static void setStaticInt(String className, String field, int value) throws Exception {
        java.lang.reflect.Field f = Class.forName(className).getDeclaredField(field);
        f.setAccessible(true);
        f.setInt(null, value);
    }

    private static void setStaticBoolean(String className, String field, boolean value) throws Exception {
        java.lang.reflect.Field f = Class.forName(className).getDeclaredField(field);
        f.setAccessible(true);
        f.setBoolean(null, value);
    }

    /** Aspect-fit surface into {@link #LOGICAL_MAX_EDGE} (no-op when edge is 0). */
    public static int[] logicalSize(int surfaceW, int surfaceH) {
        surfaceW = Math.max(1, surfaceW);
        surfaceH = Math.max(1, surfaceH);
        int maxEdge = LOGICAL_MAX_EDGE;
        if (maxEdge <= 0) {
            return new int[]{surfaceW, surfaceH};
        }
        int longEdge = Math.max(surfaceW, surfaceH);
        if (longEdge <= maxEdge) {
            return new int[]{surfaceW, surfaceH};
        }
        float s = maxEdge / (float) longEdge;
        return new int[]{
                Math.max(1, Math.round(surfaceW * s)),
                Math.max(1, Math.round(surfaceH * s))
        };
    }

    public static void setRoot(Component component) {
        root = component;
        if (component != null) {
            component.setSize(GAME_WIDTH, GAME_HEIGHT);
        }
    }

    public static void onGameCanvas(Canvas canvas) {
        gameCanvas = canvas;
        if (canvas != null) {
            canvas.setSize(GAME_WIDTH, GAME_HEIGHT);
        }
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

    public static void injectRightClick(int x, int y) {
        injectMouse(MouseEvent.MOUSE_MOVED, x, y, 0, 0);
        injectMouse(MouseEvent.MOUSE_PRESSED, x, y, MouseEvent.BUTTON3, 1);
        injectMouse(MouseEvent.MOUSE_RELEASED, x, y, MouseEvent.BUTTON3, 1);
        injectMouse(MouseEvent.MOUSE_CLICKED, x, y, MouseEvent.BUTTON3, 1);
    }

    public static void injectLeftClick(int x, int y) {
        injectMouse(MouseEvent.MOUSE_MOVED, x, y, 0, 0);
        injectMouse(MouseEvent.MOUSE_PRESSED, x, y, MouseEvent.BUTTON1, 1);
        injectMouse(MouseEvent.MOUSE_RELEASED, x, y, MouseEvent.BUTTON1, 1);
        injectMouse(MouseEvent.MOUSE_CLICKED, x, y, MouseEvent.BUTTON1, 1);
    }

    public static void injectMouse(int id, int x, int y, int button, int clickCount) {
        Component target = gameCanvas != null ? gameCanvas : root;
        if (target == null) {
            return;
        }
        int modifiers = 0;
        if (button == 1) {
            modifiers = 16; // BUTTON1_MASK
        } else if (button == 2) {
            modifiers = 8; // BUTTON2_MASK
        } else if (button == 3) {
            modifiers = 4; // BUTTON3_MASK / META_MASK
        }
        MouseEvent e = new MouseEvent(target, id, System.currentTimeMillis(), modifiers, x, y, clickCount, button == 3, button);
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

    /** Orbit camera by view-pixel deltas (two-finger pan). Positive dx = look right. */
    public static void injectCameraOrbit(float dx, float dy) {
        if (dx == 0f && dy == 0f) {
            return;
        }
        try {
            // yaw (Class314.aFloat3938), pitch (Class76.aFloat1287) — default-package client fields
            float yawScale = 8f;
            float pitchScale = 4f;
            Class<?> yawCl = Class.forName("Class314");
            java.lang.reflect.Field yawF = yawCl.getDeclaredField("aFloat3938");
            yawF.setAccessible(true);
            yawF.setFloat(null, yawF.getFloat(null) + dx * yawScale);

            Class<?> pitchCl = Class.forName("Class76");
            java.lang.reflect.Field pitchF = pitchCl.getDeclaredField("aFloat1287");
            pitchF.setAccessible(true);
            pitchF.setFloat(null, pitchF.getFloat(null) + dy * pitchScale);

            Class.forName("Class239_Sub2")
                    .getDeclaredMethod("method1725", int.class)
                    .invoke(null, 262144);
        } catch (Throwable ignored) {
        }
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
