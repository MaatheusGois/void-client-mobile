package world.gregs.voidosrs.android;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import java.io.PrintStream;
import java.io.OutputStream;
import android.util.Log;
import android.os.Handler;
import android.os.Looper;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.InputDevice;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.media.AudioAttributes;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import voidawt.AwtHost;
import voidawt.event.MouseEvent;

public class MainActivity extends Activity {
    private volatile boolean clientStarted;
    private TextView debugHud;
    private static volatile MainActivity instance;
    private EditText imeInput;
    private TextView keyboardBall;
    private GameView game;
    private boolean keyboardOpen;
    private String typedBuffer = "";
    private boolean syncingText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.setProperty("user.home", getFilesDir().getAbsolutePath());
        // adb reverse listens on IPv4 127.0.0.1 only — avoid IPv6 dual-stack ECONNREFUSED.
        System.setProperty("java.net.preferIPv4Stack", "true");

        game = new GameView(this);
        imeInput = buildImeField();
        keyboardBall = buildKeyboardBall();

        FrameLayout root = new FrameLayout(this);
        root.setBackgroundColor(Color.BLACK);
        root.addView(game, new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        FrameLayout.LayoutParams imeLp = new FrameLayout.LayoutParams(1, 1);
        imeLp.gravity = Gravity.BOTTOM | Gravity.START;
        root.addView(imeInput, imeLp);

        int ballSize = dp(40);
        FrameLayout.LayoutParams ballLp = new FrameLayout.LayoutParams(ballSize, ballSize);
        ballLp.gravity = Gravity.TOP | Gravity.START;
        ballLp.setMargins(20, 20, 0, 0);
        root.addView(keyboardBall, ballLp);

        setContentView(root);
        hideSystemUi();
        requestAudioFocus();
        instance = this;
        installLogBridge();
        // On-screen debug HUD off — still mirrors to logcat via installLogBridge.
        // debugHud = buildDebugHud();
        // root.addView(debugHud);
        AwtHost.presenter = game;
        game.requestFocus();
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (game != null && game.handlePadKey(event)) {
            return true;
        }
        return super.dispatchKeyEvent(event);
    }

    @Override
    public boolean onGenericMotionEvent(MotionEvent event) {
        if (game != null && game.handlePadMotion(event)) {
            return true;
        }
        return super.onGenericMotionEvent(event);
    }

    @Override
    protected void onPause() {
        if (game != null) {
            game.stopPadTick();
        }
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        requestAudioFocus();
        hideSystemUi();
        if (game != null) {
            game.requestFocus();
            game.kickPadTick();
        }
    }

    private void requestAudioFocus() {
        AudioManager am = (AudioManager) getSystemService(AUDIO_SERVICE);
        if (am == null) {
            return;
        }
        try {
            AudioAttributes attrs = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build();
            AudioFocusRequest req = new AudioFocusRequest.Builder(AudioManager.AUDIOFOCUS_GAIN)
                    .setAudioAttributes(attrs)
                    .setOnAudioFocusChangeListener(new AudioManager.OnAudioFocusChangeListener() {
                        public void onAudioFocusChange(int focusChange) {
                        }
                    })
                    .build();
            am.requestAudioFocus(req);
        } catch (Throwable t) {
            Log.w("void-osrs", "audio focus", t);
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            hideSystemUi();
        }
    }

    /** Sticky immersive: hide status (clock) + nav (back/home). */
    private void hideSystemUi() {
        Window window = getWindow();
        if (window == null) {
            return;
        }
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        View decor = window.getDecorView();
        // Must run after setContentView — DecorView is null before that on some OEMs.
        if (decor == null) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 30) {
            window.setDecorFitsSystemWindows(false);
            WindowInsetsController c = decor.getWindowInsetsController();
            if (c != null) {
                c.hide(WindowInsets.Type.statusBars() | WindowInsets.Type.navigationBars());
                c.setSystemBarsBehavior(WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE);
            }
        } else {
            decor.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN);
        }
    }

    private EditText buildImeField() {
        EditText input = new EditText(this);
        input.setBackgroundColor(Color.TRANSPARENT);
        input.setTextColor(Color.TRANSPARENT);
        input.setHintTextColor(Color.TRANSPARENT);
        input.setCursorVisible(false);
        input.setSingleLine(true);
        input.setImeOptions(EditorInfo.IME_ACTION_DONE | EditorInfo.IME_FLAG_NO_FULLSCREEN);
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
        input.setImportantForAutofill(View.IMPORTANT_FOR_AUTOFILL_NO);
        input.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE
                    || actionId == EditorInfo.IME_ACTION_GO
                    || actionId == EditorInfo.IME_ACTION_SEND
                    || (event != null && event.getKeyCode() == android.view.KeyEvent.KEYCODE_ENTER)) {
                injectEnter();
                hideKeyboard();
                return true;
            }
            return false;
        });
        input.addTextChangedListener(new android.text.TextWatcher() {
            private String last = "";

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (syncingText) {
                    last = s == null ? "" : s.toString();
                    return;
                }
                String now = s == null ? "" : s.toString();
                if (now.length() > last.length()) {
                    for (int i = last.length(); i < now.length(); i++) {
                        injectChar(now.charAt(i));
                    }
                } else if (now.length() < last.length()) {
                    int removed = last.length() - now.length();
                    for (int i = 0; i < removed; i++) {
                        injectBackspace();
                    }
                }
                last = now;
                typedBuffer = now;
            }

            public void afterTextChanged(android.text.Editable s) {
            }
        });
        input.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus && keyboardOpen) {
                hideKeyboard();
            }
        });
        return input;
    }

    private TextView buildKeyboardBall() {
        TextView ball = new TextView(this);
        ball.setText("⌨");
        ball.setTextSize(16f);
        ball.setGravity(Gravity.CENTER);
        ball.setTextColor(Color.WHITE);
        ball.setContentDescription("Open keyboard");
        GradientDrawable bg = new GradientDrawable();
        bg.setShape(GradientDrawable.OVAL);
        bg.setColor(0xF2222222);
        bg.setStroke(dp(1), 0xFFE0B040);
        ball.setBackground(bg);
        ball.setElevation(dp(4));
        ball.setOnClickListener(v -> {
            if (keyboardOpen) {
                hideKeyboard();
            } else {
                showKeyboard();
            }
        });
        return ball;
    }

    private void showKeyboard() {
        keyboardOpen = true;
        keyboardBall.setAlpha(0.35f);
        syncingText = true;
        imeInput.setText(typedBuffer);
        imeInput.setSelection(typedBuffer.length());
        syncingText = false;
        imeInput.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.showSoftInput(imeInput, InputMethodManager.SHOW_IMPLICIT);
        }
    }

    private void hideKeyboard() {
        keyboardOpen = false;
        keyboardBall.setAlpha(1f);
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(imeInput.getWindowToken(), 0);
        }
        imeInput.clearFocus();
        game.requestFocus();
    }

    private void injectChar(char c) {
        int code = Character.toUpperCase(c);
        AwtHost.injectKey(voidawt.event.KeyEvent.KEY_PRESSED, code, c);
        AwtHost.injectKey(voidawt.event.KeyEvent.KEY_TYPED, 0, c);
        AwtHost.injectKey(voidawt.event.KeyEvent.KEY_RELEASED, code, c);
    }

    private void injectBackspace() {
        AwtHost.injectKey(voidawt.event.KeyEvent.KEY_PRESSED, voidawt.event.KeyEvent.VK_BACK_SPACE, '\b');
        AwtHost.injectKey(voidawt.event.KeyEvent.KEY_TYPED, 0, '\b');
        AwtHost.injectKey(voidawt.event.KeyEvent.KEY_RELEASED, voidawt.event.KeyEvent.VK_BACK_SPACE, '\b');
    }

    private void injectEnter() {
        AwtHost.injectKey(voidawt.event.KeyEvent.KEY_PRESSED, voidawt.event.KeyEvent.VK_ENTER, '\n');
        AwtHost.injectKey(voidawt.event.KeyEvent.KEY_TYPED, 0, '\n');
        AwtHost.injectKey(voidawt.event.KeyEvent.KEY_RELEASED, voidawt.event.KeyEvent.VK_ENTER, '\n');
        typedBuffer = "";
        syncingText = true;
        imeInput.setText("");
        syncingText = false;
    }

    private int dp(int value) {
        return Math.round(value * getResources().getDisplayMetrics().density);
    }

    private TextView buildDebugHud() {
        TextView tv = new TextView(this);
        tv.setTextSize(11f);
        tv.setTextColor(0xFFE8E8E8);
        tv.setBackgroundColor(0x99000000);
        tv.setPadding(dp(8), dp(4), dp(8), dp(4));
        tv.setText("void debug…");
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.WRAP_CONTENT);
        lp.gravity = Gravity.BOTTOM;
        tv.setLayoutParams(lp);
        return tv;
    }

    static void hud(final String msg) {
        Log.i("void-osrs", msg);
        final MainActivity a = instance;
        if (a == null || a.debugHud == null) return;
        a.runOnUiThread(() -> a.debugHud.setText(msg));
    }

    private static void installLogBridge() {
        PrintStream ps = new PrintStream(new OutputStream() {
            private final StringBuilder buf = new StringBuilder();

            @Override
            public synchronized void write(int b) {
                if (b == '\n') flushLine();
                else buf.append((char) b);
            }

            @Override
            public synchronized void write(byte[] b, int off, int len) {
                for (int i = 0; i < len; i++) write(b[off + i] & 0xff);
            }

            private void flushLine() {
                String line = buf.toString();
                buf.setLength(0);
                if (line.isEmpty()) return;
                Log.i("void-osrs", line);
                if (line.startsWith("Connect:")
                        || line.startsWith("Connect ")
                        || line.startsWith("js5")
                        || line.startsWith("load ")
                        || line.startsWith("error_game_")
                        || line.startsWith("boot ")) {
                    hud(line);
                }
            }
        }, true);
        System.setOut(ps);
        System.setErr(ps);
    }

    /** First host that accepts a TCP connect within a short timeout. */
    private static String pickReachableServer(int port, String... hosts) {
        for (String host : hosts) {
            long t0 = System.currentTimeMillis();
            try {
                java.net.Socket s = new java.net.Socket();
                s.connect(new java.net.InetSocketAddress(host, port), 800);
                s.close();
                Log.i("void-osrs", "probe OK " + host + ":" + port
                        + " in " + (System.currentTimeMillis() - t0) + "ms");
                return host;
            } catch (Throwable e) {
                Log.w("void-osrs", "probe FAIL " + host + ":" + port
                        + " (" + e.getClass().getSimpleName() + ": " + e.getMessage() + ")"
                        + " in " + (System.currentTimeMillis() - t0) + "ms");
            }
        }
        String fallback = hosts.length > 0 ? hosts[0] : "127.0.0.1";
        Log.e("void-osrs", "no reachable JS5 host — falling back to " + fallback
                + " (run: adb reverse tcp:" + port + " tcp:" + port + ")");
        return fallback;
    }

    /** Reads debug.void.server (setprop) or -Dvoid.server. */
    private static String readServerOverride() {
        String fromProp = System.getProperty("void.server");
        if (fromProp != null && !fromProp.isEmpty()) {
            return fromProp.trim();
        }
        try {
            Class<?> sp = Class.forName("android.os.SystemProperties");
            String v = (String) sp.getMethod("get", String.class, String.class)
                    .invoke(null, "debug.void.server", "");
            if (v != null && !v.isEmpty()) {
                return v.trim();
            }
        } catch (Throwable ignored) {
        }
        return null;
    }

    private static boolean isEmulator() {
        String fingerprint = Build.FINGERPRINT;
        String model = Build.MODEL;
        String product = Build.PRODUCT;
        return fingerprint.startsWith("generic")
                || fingerprint.contains("emulator")
                || model.contains("Emulator")
                || model.contains("sdk_gphone")
                || product.contains("sdk")
                || product.contains("emulator");
    }

    private void startClientIfReady(int width, int height) {
        if (clientStarted || width <= 0 || height <= 0) {
            return;
        }
        clientStarted = true;
        AwtHost.setDisplaySize(width, height);
        new Thread(() -> {
            try {
                Class<?> loaderCl = Class.forName("Loader");
                // Prefer adb reverse (127.0.0.1). Fall back to LAN if reverse is down.
                // Override: adb shell setprop debug.void.server <ip>
                String server = readServerOverride();
                if (server == null || server.isEmpty()) {
                    if (isEmulator()) {
                        server = "10.0.2.2";
                    } else {
                        server = pickReachableServer(43594, "127.0.0.1", "192.168.18.214");
                    }
                }
                Log.i("void-osrs", "boot server=" + server + ":43594"
                        + " emu=" + isEmulator()
                        + " override=" + readServerOverride());
                hud("boot " + server + ":43594");
                loaderCl.getField("address").set(null, server);
                loaderCl.getField("port").setInt(null, 43594);
                loaderCl.getField("debug").set(null, true);
                loaderCl.getField("trace").set(null, true);
                Object loader = loaderCl.getDeclaredConstructor().newInstance();
                loaderCl.getMethod("setSize", int.class, int.class).invoke(loader, AwtHost.GAME_WIDTH, AwtHost.GAME_HEIGHT);
                AwtHost.setRoot((voidawt.Component) loader);
                loaderCl.getMethod("init").invoke(loader);
            } catch (Throwable t) {
                Log.e("void-osrs", "client boot failed", t);
                hud("boot FAIL: " + t.getMessage());
            }
        }, "void-client").start();
    }

    final class GameView extends SurfaceView implements SurfaceHolder.Callback, AwtHost.Presenter {
        private Bitmap frame;
        private final Paint paint = new Paint(Paint.FILTER_BITMAP_FLAG);
        private final Paint cursorPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        private final Rect src = new Rect();
        private final Rect dst = new Rect();
        private boolean down;
        private boolean multiTouch;
        private boolean dragging;
        private boolean longPressFired;
        /** After a 2-finger gesture, ignore leftover single-finger UP/MOVE until a fresh DOWN. */
        private boolean ignoreSingleFinger;
        private float downVx;
        private float downVy;
        private int downX;
        private int downY;
        private float lastPinchDist = -1f;
        private float pinchAccum;
        private float lastOrbitX;
        private float lastOrbitY;
        private int frameW = AwtHost.GAME_WIDTH;
        private int frameH = AwtHost.GAME_HEIGHT;
        private static final float PINCH_PX_PER_NOTCH = 28f;
        /**
         * Hold still within this → long-press right-click.
         * Move past this before timeout → one-finger camera orbit.
         */
        private static final float LONG_PRESS_CANCEL_SLOP = 100f;
        private final Handler touchHandler = new Handler(Looper.getMainLooper());
        // DualShock / gamepad → virtual mouse
        private static final float PAD_DEADZONE = 0.15f;
        private static final float PAD_CURSOR_SPEED = 14f;
        private static final float PAD_ORBIT_SCALE = 10f;
        private static final long PAD_ZOOM_INTERVAL_MS = 90L;
        private static final float PAD_TRIGGER_THRESHOLD = 0.35f;
        private float cursorVx = -1f;
        private float cursorVy = -1f;
        private boolean padActive;
        private float stickLX;
        private float stickLY;
        private float stickRX;
        private float stickRY;
        private float triggerL2;
        private boolean l1Held;
        private boolean l2DigitalHeld;
        private boolean padLeftDown;
        private boolean padRightDown;
        private boolean padTickRunning;
        private long lastPadZoomAt;
        private final Runnable padTick = new Runnable() {
            public void run() {
                padTickRunning = false;
                if (!padActive) {
                    return;
                }
                boolean keep = tickPad();
                if (keep) {
                    padTickRunning = true;
                    touchHandler.postDelayed(this, 16);
                }
            }
        };
        private final Runnable longPressRunnable = new Runnable() {
            public void run() {
                if (!down || multiTouch || dragging || longPressFired || ignoreSingleFinger) {
                    Log.i("void-osrs", "longPress SKIP down=" + down + " drag=" + dragging
                            + " multi=" + multiTouch + " fired=" + longPressFired);
                    return;
                }
                longPressFired = true;
                Log.i("void-osrs", "longPress right-click @ " + downX + "," + downY);
                AwtHost.injectRightClick(downX, downY);
            }
        };

        GameView(MainActivity activity) {
            super(activity);
            getHolder().addCallback(this);
            setFocusable(true);
            setFocusableInTouchMode(true);
            cursorPaint.setStyle(Paint.Style.STROKE);
            cursorPaint.setStrokeWidth(2.5f);
            cursorPaint.setColor(0xFFE8F0FF);
            addOnLayoutChangeListener((v, left, top, right, bottom, oldLeft, oldTop, oldRight, oldBottom) -> {
                if (keyboardOpen) {
                    return;
                }
                int w = Math.max(0, right - left);
                int h = Math.max(0, bottom - top);
                if (w > 0 && h > 0) {
                    AwtHost.setDisplaySize(w, h);
                    startClientIfReady(w, h);
                    if (cursorVx < 0f) {
                        cursorVx = w * 0.5f;
                        cursorVy = h * 0.5f;
                    } else {
                        cursorVx = Math.min(cursorVx, w - 1);
                        cursorVy = Math.min(cursorVy, h - 1);
                    }
                }
            });
        }

        void stopPadTick() {
            touchHandler.removeCallbacks(padTick);
            padTickRunning = false;
        }

        void kickPadTick() {
            if (padActive && needsPadTick()) {
                startPadTick();
            }
        }

        private void activatePad() {
            padActive = true;
            ensureCursor();
            startPadTick();
            redraw();
        }

        private void ensureCursor() {
            int w = Math.max(1, getWidth());
            int h = Math.max(1, getHeight());
            if (cursorVx < 0f || cursorVy < 0f) {
                cursorVx = w * 0.5f;
                cursorVy = h * 0.5f;
            }
        }

        private void startPadTick() {
            if (padTickRunning) {
                return;
            }
            padTickRunning = true;
            touchHandler.post(padTick);
        }

        private boolean needsPadTick() {
            return Math.abs(stickLX) > PAD_DEADZONE
                    || Math.abs(stickLY) > PAD_DEADZONE
                    || Math.abs(stickRX) > PAD_DEADZONE
                    || Math.abs(stickRY) > PAD_DEADZONE
                    || triggerL2 > PAD_TRIGGER_THRESHOLD
                    || l2DigitalHeld
                    || l1Held;
        }

        private float dead(float v) {
            return Math.abs(v) < PAD_DEADZONE ? 0f : v;
        }

        private boolean tickPad() {
            ensureCursor();
            float lx = dead(stickLX);
            float ly = dead(stickLY);
            float rx = dead(stickRX);
            float ry = dead(stickRY);
            boolean moved = false;

            if (lx != 0f || ly != 0f) {
                float w = Math.max(1, getWidth());
                float h = Math.max(1, getHeight());
                cursorVx = Math.max(0f, Math.min(w - 1f, cursorVx + lx * PAD_CURSOR_SPEED));
                cursorVy = Math.max(0f, Math.min(h - 1f, cursorVy + ly * PAD_CURSOR_SPEED));
                int[] xy = map(cursorVx, cursorVy);
                if (padLeftDown) {
                    AwtHost.injectMouse(MouseEvent.MOUSE_DRAGGED, xy[0], xy[1], MouseEvent.BUTTON1, 0);
                } else if (padRightDown) {
                    AwtHost.injectMouse(MouseEvent.MOUSE_DRAGGED, xy[0], xy[1], MouseEvent.BUTTON3, 0);
                } else {
                    AwtHost.injectMouse(MouseEvent.MOUSE_MOVED, xy[0], xy[1], 0, 0);
                }
                moved = true;
            }

            if (rx != 0f || ry != 0f) {
                AwtHost.injectCameraOrbit(rx * PAD_ORBIT_SCALE, ry * PAD_ORBIT_SCALE);
                moved = true;
            }

            boolean zoomIn = triggerL2 > PAD_TRIGGER_THRESHOLD || l2DigitalHeld;
            boolean zoomOut = l1Held;
            long now = System.currentTimeMillis();
            if ((zoomIn || zoomOut) && now - lastPadZoomAt >= PAD_ZOOM_INTERVAL_MS) {
                int[] xy = map(cursorVx, cursorVy);
                if (zoomIn) {
                    AwtHost.injectWheel(xy[0], xy[1], -1);
                }
                if (zoomOut) {
                    AwtHost.injectWheel(xy[0], xy[1], 1);
                }
                lastPadZoomAt = now;
                moved = true;
            }

            if (moved) {
                redraw();
            }
            return needsPadTick();
        }

        private boolean isGamepad(KeyEvent event) {
            int src = event.getSource();
            if ((src & InputDevice.SOURCE_GAMEPAD) == InputDevice.SOURCE_GAMEPAD
                    || (src & InputDevice.SOURCE_JOYSTICK) == InputDevice.SOURCE_JOYSTICK) {
                return true;
            }
            int code = event.getKeyCode();
            return code >= KeyEvent.KEYCODE_BUTTON_A && code <= KeyEvent.KEYCODE_BUTTON_MODE;
        }

        boolean handlePadKey(KeyEvent event) {
            if (!isGamepad(event) && event.getKeyCode() < KeyEvent.KEYCODE_BUTTON_A) {
                return false;
            }
            int code = event.getKeyCode();
            // Ignore repeats for clicks; L1 held is tracked via down/up.
            if (event.getAction() == KeyEvent.ACTION_DOWN && event.getRepeatCount() > 0) {
                if (code == KeyEvent.KEYCODE_BUTTON_L1) {
                    return true;
                }
                if (code == KeyEvent.KEYCODE_BUTTON_L2) {
                    return true;
                }
                return code >= KeyEvent.KEYCODE_BUTTON_A && code <= KeyEvent.KEYCODE_BUTTON_MODE;
            }
            if (code < KeyEvent.KEYCODE_BUTTON_A || code > KeyEvent.KEYCODE_BUTTON_MODE) {
                // Still accept if source is gamepad for unknown codes
                if (!isGamepad(event)) {
                    return false;
                }
            }

            activatePad();
            int[] xy = map(cursorVx, cursorVy);
            boolean down = event.getAction() == KeyEvent.ACTION_DOWN;

            switch (code) {
                case KeyEvent.KEYCODE_BUTTON_A: // ✕ Cross → left click
                    if (down) {
                        padLeftDown = true;
                        AwtHost.injectMouse(MouseEvent.MOUSE_PRESSED, xy[0], xy[1], MouseEvent.BUTTON1, 1);
                    } else if (event.getAction() == KeyEvent.ACTION_UP) {
                        padLeftDown = false;
                        AwtHost.injectMouse(MouseEvent.MOUSE_RELEASED, xy[0], xy[1], MouseEvent.BUTTON1, 1);
                        AwtHost.injectMouse(MouseEvent.MOUSE_CLICKED, xy[0], xy[1], MouseEvent.BUTTON1, 1);
                    }
                    return true;
                case KeyEvent.KEYCODE_BUTTON_B: // ○ Circle → right click
                    if (down) {
                        padRightDown = true;
                        Log.i("void-osrs", "pad ○ right-press @ " + xy[0] + "," + xy[1]);
                        AwtHost.injectMouse(MouseEvent.MOUSE_MOVED, xy[0], xy[1], 0, 0);
                        AwtHost.injectMouse(MouseEvent.MOUSE_PRESSED, xy[0], xy[1], MouseEvent.BUTTON3, 1);
                    } else if (event.getAction() == KeyEvent.ACTION_UP) {
                        padRightDown = false;
                        AwtHost.injectMouse(MouseEvent.MOUSE_RELEASED, xy[0], xy[1], MouseEvent.BUTTON3, 1);
                        AwtHost.injectMouse(MouseEvent.MOUSE_CLICKED, xy[0], xy[1], MouseEvent.BUTTON3, 1);
                    }
                    return true;
                case KeyEvent.KEYCODE_BUTTON_L1: // zoom out
                    l1Held = down;
                    if (down) {
                        AwtHost.injectWheel(xy[0], xy[1], 1);
                        lastPadZoomAt = System.currentTimeMillis();
                        startPadTick();
                    }
                    return true;
                case KeyEvent.KEYCODE_BUTTON_L2: // zoom in (digital)
                    l2DigitalHeld = down;
                    if (down) {
                        AwtHost.injectWheel(xy[0], xy[1], -1);
                        lastPadZoomAt = System.currentTimeMillis();
                        startPadTick();
                    }
                    return true;
                default:
                    return code >= KeyEvent.KEYCODE_BUTTON_A && code <= KeyEvent.KEYCODE_BUTTON_MODE;
            }
        }

        boolean handlePadMotion(MotionEvent event) {
            int src = event.getSource();
            boolean joy = (src & InputDevice.SOURCE_CLASS_JOYSTICK) != 0
                    || (src & InputDevice.SOURCE_GAMEPAD) != 0
                    || (src & InputDevice.SOURCE_JOYSTICK) != 0;
            if (!joy) {
                return false;
            }
            if (event.getActionMasked() != MotionEvent.ACTION_MOVE) {
                return false;
            }
            activatePad();
            stickLX = axis(event, MotionEvent.AXIS_X);
            stickLY = axis(event, MotionEvent.AXIS_Y);

            float rz = axis(event, MotionEvent.AXIS_RZ);
            float z = axis(event, MotionEvent.AXIS_Z);
            float rx = axis(event, MotionEvent.AXIS_RX);
            float ry = axis(event, MotionEvent.AXIS_RY);
            // Prefer Z/RZ (common DS4 HID); fall back to RX/RY.
            if (Math.abs(z) > PAD_DEADZONE || Math.abs(rz) > PAD_DEADZONE) {
                stickRX = z;
                stickRY = rz;
            } else {
                stickRX = rx;
                stickRY = ry;
            }

            float lTrigger = axis(event, MotionEvent.AXIS_LTRIGGER);
            float brake = axis(event, MotionEvent.AXIS_BRAKE);
            triggerL2 = Math.max(lTrigger, brake);

            startPadTick();
            return true;
        }

        private float axis(MotionEvent e, int axis) {
            float v = e.getAxisValue(axis);
            return Float.isNaN(v) ? 0f : v;
        }

        public boolean onTouchEvent(MotionEvent event) {
            int action = event.getActionMasked();
            int count = event.getPointerCount();

            if (action == MotionEvent.ACTION_POINTER_DOWN && count >= 2) {
                cancelLongPressWatch();
                down = false;
                dragging = false;
                longPressFired = false;
                ignoreSingleFinger = true;
                multiTouch = true;
                lastPinchDist = spacing(event);
                pinchAccum = 0f;
                return true;
            }

            if (multiTouch) {
                if (action == MotionEvent.ACTION_MOVE && count >= 2) {
                    float dist = spacing(event);
                    if (lastPinchDist > 0f) {
                        pinchAccum += dist - lastPinchDist;
                        while (pinchAccum >= PINCH_PX_PER_NOTCH) {
                            int[] midGame = mapMid(event);
                            AwtHost.injectWheel(midGame[0], midGame[1], -1);
                            pinchAccum -= PINCH_PX_PER_NOTCH;
                        }
                        while (pinchAccum <= -PINCH_PX_PER_NOTCH) {
                            int[] midGame = mapMid(event);
                            AwtHost.injectWheel(midGame[0], midGame[1], 1);
                            pinchAccum += PINCH_PX_PER_NOTCH;
                        }
                    }
                    lastPinchDist = dist;
                    return true;
                }
                if (action == MotionEvent.ACTION_POINTER_UP) {
                    if (count - 1 < 2) {
                        multiTouch = false;
                        ignoreSingleFinger = true;
                        down = false;
                        dragging = false;
                        longPressFired = false;
                        lastPinchDist = -1f;
                        pinchAccum = 0f;
                    } else {
                        lastPinchDist = spacingAfterPointerUp(event);
                    }
                    return true;
                }
                if (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_CANCEL) {
                    multiTouch = false;
                    ignoreSingleFinger = false;
                    lastPinchDist = -1f;
                    pinchAccum = 0f;
                    down = false;
                    dragging = false;
                    longPressFired = false;
                    return true;
                }
                return true;
            }

            if (ignoreSingleFinger) {
                if (action == MotionEvent.ACTION_DOWN) {
                    ignoreSingleFinger = false;
                } else if (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_CANCEL) {
                    ignoreSingleFinger = false;
                    down = false;
                    dragging = false;
                    longPressFired = false;
                    cancelLongPressWatch();
                    return true;
                } else {
                    return true;
                }
            }

            int[] xy = map(event.getX(), event.getY());
            int x = xy[0];
            int y = xy[1];
            if (action == MotionEvent.ACTION_DOWN) {
                down = true;
                dragging = false;
                longPressFired = false;
                downVx = event.getX();
                downVy = event.getY();
                lastOrbitX = downVx;
                lastOrbitY = downVy;
                downX = x;
                downY = y;
                cancelLongPressWatch();
                long timeout = Math.max(350, ViewConfiguration.getLongPressTimeout());
                Log.i("void-osrs", "longPress SCHEDULE " + timeout + "ms @ " + downX + "," + downY);
                touchHandler.postDelayed(longPressRunnable, timeout);
            } else if (action == MotionEvent.ACTION_MOVE && down && !longPressFired) {
                float moved = Math.max(Math.abs(event.getX() - downVx), Math.abs(event.getY() - downVy));
                if (!dragging && moved > LONG_PRESS_CANCEL_SLOP) {
                    dragging = true;
                    cancelLongPressWatch();
                    lastOrbitX = event.getX();
                    lastOrbitY = event.getY();
                    Log.i("void-osrs", "orbit START moved=" + moved);
                }
                if (dragging) {
                    // One-finger drag → camera orbit (was two-finger pan).
                    float dx = event.getX() - lastOrbitX;
                    float dy = event.getY() - lastOrbitY;
                    AwtHost.injectCameraOrbit(dx, dy);
                    lastOrbitX = event.getX();
                    lastOrbitY = event.getY();
                }
            } else if (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_CANCEL) {
                cancelLongPressWatch();
                if (!down) {
                    // leftover
                } else if (longPressFired) {
                    Log.i("void-osrs", "longPress UP after right-click");
                } else if (dragging) {
                    // camera orbit — no mouse click
                    Log.i("void-osrs", "orbit END");
                } else if (action == MotionEvent.ACTION_UP) {
                    Log.i("void-osrs", "tap left-click @ " + downX + "," + downY);
                    AwtHost.injectLeftClick(downX, downY);
                }
                down = false;
                dragging = false;
                longPressFired = false;
            }
            return true;
        }

        private void cancelLongPressWatch() {
            touchHandler.removeCallbacks(longPressRunnable);
        }

        private float spacing(MotionEvent e) {
            float dx = e.getX(0) - e.getX(1);
            float dy = e.getY(0) - e.getY(1);
            return (float) Math.hypot(dx, dy);
        }

        private float spacingAfterPointerUp(MotionEvent e) {
            int up = e.getActionIndex();
            int a = -1;
            int b = -1;
            for (int i = 0; i < e.getPointerCount(); i++) {
                if (i == up) {
                    continue;
                }
                if (a < 0) {
                    a = i;
                } else {
                    b = i;
                    break;
                }
            }
            if (a < 0 || b < 0) {
                return -1f;
            }
            float dx = e.getX(a) - e.getX(b);
            float dy = e.getY(a) - e.getY(b);
            return (float) Math.hypot(dx, dy);
        }

        private float[] midpoint(MotionEvent e) {
            return new float[]{(e.getX(0) + e.getX(1)) * 0.5f, (e.getY(0) + e.getY(1)) * 0.5f};
        }

        private float[] midpointAfterPointerUp(MotionEvent e) {
            int up = e.getActionIndex();
            float sx = 0f;
            float sy = 0f;
            int n = 0;
            for (int i = 0; i < e.getPointerCount(); i++) {
                if (i == up) {
                    continue;
                }
                sx += e.getX(i);
                sy += e.getY(i);
                n++;
            }
            if (n == 0) {
                return new float[]{0f, 0f};
            }
            return new float[]{sx / n, sy / n};
        }

        private int[] mapMid(MotionEvent e) {
            float[] mid = midpoint(e);
            return map(mid[0], mid[1]);
        }

        private int[] map(float vx, float vy) {
            int vw = Math.max(1, getWidth());
            int vh = Math.max(1, getHeight());
            int gw = Math.max(1, frameW);
            int gh = Math.max(1, frameH);
            int x = (int) (vx * gw / (float) vw);
            int y = (int) (vy * gh / (float) vh);
            x = Math.max(0, Math.min(gw - 1, x));
            y = Math.max(0, Math.min(gh - 1, y));
            return new int[]{x, y};
        }

        public void present(Bitmap bitmap) {
            Bitmap old = frame;
            frame = bitmap;
            frameW = bitmap.getWidth();
            frameH = bitmap.getHeight();
            if (old != null && old != bitmap) {
                old.recycle();
            }
            if (getHolder().getSurface().isValid()) {
                Canvas canvas = getHolder().lockCanvas();
                if (canvas != null) {
                    drawFrame(canvas);
                    getHolder().unlockCanvasAndPost(canvas);
                }
            }
        }

        private void drawFrame(Canvas canvas) {
            canvas.drawColor(0xff000000);
            Bitmap bmp = frame;
            if (bmp != null) {
                src.set(0, 0, bmp.getWidth(), bmp.getHeight());
                dst.set(0, 0, canvas.getWidth(), canvas.getHeight());
                canvas.drawBitmap(bmp, src, dst, paint);
            }
            if (padActive && cursorVx >= 0f && cursorVy >= 0f) {
                float x = cursorVx;
                float y = cursorVy;
                float r = 12f;
                cursorPaint.setStyle(Paint.Style.STROKE);
                cursorPaint.setColor(0xEE101010);
                cursorPaint.setStrokeWidth(4f);
                canvas.drawCircle(x, y, r, cursorPaint);
                canvas.drawLine(x - r - 4f, y, x + r + 4f, y, cursorPaint);
                canvas.drawLine(x, y - r - 4f, x, y + r + 4f, cursorPaint);
                cursorPaint.setColor(0xFFE8F0FF);
                cursorPaint.setStrokeWidth(2f);
                canvas.drawCircle(x, y, r, cursorPaint);
                canvas.drawLine(x - r - 4f, y, x + r + 4f, y, cursorPaint);
                canvas.drawLine(x, y - r - 4f, x, y + r + 4f, cursorPaint);
            }
        }

        public void surfaceCreated(SurfaceHolder holder) {
        }

        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            if (width <= 0 || height <= 0) {
                return;
            }
            // IME used to use adjustResize and shrink the surface → client viewport
            // stuck at a tiny size with black bars. Ignore shrinks while the keyboard
            // is up (manifest is adjustNothing; this is belt-and-suspenders).
            if (keyboardOpen) {
                redraw();
                return;
            }
            AwtHost.setDisplaySize(width, height);
            startClientIfReady(width, height);
            redraw();
        }

        private void redraw() {
            if (getHolder().getSurface().isValid()) {
                Canvas canvas = getHolder().lockCanvas();
                if (canvas != null) {
                    drawFrame(canvas);
                    getHolder().unlockCanvasAndPost(canvas);
                }
            }
        }

        public void surfaceDestroyed(SurfaceHolder holder) {
        }
    }
}
