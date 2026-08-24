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
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.InputType;
import android.hardware.input.InputManager;
import android.view.Gravity;
import android.view.InputDevice;
import android.view.KeyEvent;
import android.view.MotionEvent;
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
    /** Largest surface size we have accepted (ignores transient resume/IME shrinks). */
    private int stableSurfaceW;
    private int stableSurfaceH;
    /** Ignore / don't commit shrinks until this uptime (resume inset flash). */
    private long resumeGraceUntilMs;
    private final Handler uiHandler = new Handler(Looper.getMainLooper());
    private final Runnable deferredShrinkApply = new Runnable() {
        public void run() {
            if (game == null || keyboardOpen) {
                return;
            }
            if (android.os.SystemClock.uptimeMillis() < resumeGraceUntilMs) {
                uiHandler.postDelayed(this, 200);
                return;
            }
            int w = game.getWidth();
            int h = game.getHeight();
            if (w > 0 && h > 0) {
                applySurfaceSize(w, h, "deferred-shrink", true);
            }
        }
    };
    private final Runnable resumeSizeRestore = new Runnable() {
        public void run() {
            hideSystemUi();
            if (game == null) {
                return;
            }
            int[] win = windowSizePx();
            int vw = game.getWidth();
            int vh = game.getHeight();
            int w = vw > 0 ? vw : win[0];
            int h = vh > 0 ? vh : win[1];
            // If the view is still transiently shrunk, pin client to the larger of
            // last stable size and real window metrics (not the shrunk view).
            long viewArea = (long) Math.max(0, vw) * Math.max(0, vh);
            long stableArea = (long) stableSurfaceW * stableSurfaceH;
            long winArea = (long) win[0] * win[1];
            if (stableArea > 0 && viewArea > 0 && viewArea * 100 < stableArea * 85) {
                w = Math.max(stableSurfaceW, win[0]);
                h = Math.max(stableSurfaceH, win[1]);
            } else if (winArea > viewArea && win[0] > 0 && win[1] > 0) {
                w = win[0];
                h = win[1];
            }
            if (w > 0 && h > 0) {
                applySurfaceSize(w, h, "resume-restore", true);
            }
            game.requestLayout();
            game.invalidate();
        }
    };

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

        // Keyboard ball hidden — soft keyboard opens on in-game text-field taps.
        // Keep instance for emergency toggle via long-press on empty corner if needed.
        keyboardBall.setVisibility(View.GONE);

        setContentView(root);
        hideSystemUi();
        requestAudioFocus();
        instance = this;
        installLogBridge();
        // On-screen debug HUD off — still mirrors to logcat via installLogBridge.
        // debugHud = buildDebugHud();
        // root.addView(debugHud);
        AwtHost.presenter = game;
        AwtHost.softKeyboardListener = new AwtHost.SoftKeyboardListener() {
            public void showSoftKeyboard(String reason) {
                Log.i("void-osrs", "softKeyboard show: " + reason);
                runOnUiThread(() -> showKeyboard());
            }

            public void hideSoftKeyboard(String reason) {
                Log.i("void-osrs", "softKeyboard hide: " + reason);
                runOnUiThread(() -> hideKeyboard());
            }

            public void toggleSoftKeyboard(String reason) {
                Log.i("void-osrs", "softKeyboard toggle: " + reason + " open=" + keyboardOpen);
                runOnUiThread(() -> {
                    if (keyboardOpen) {
                        hideKeyboard();
                    } else {
                        showKeyboard();
                    }
                });
            }

            public void syncSoftKeyboardToDevConsole() {
                // No-op: delayed sync raced the game thread and re-opened the IME on close.
            }
        };
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
        uiHandler.removeCallbacks(resumeSizeRestore);
        uiHandler.removeCallbacks(deferredShrinkApply);
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
        resumeGraceUntilMs = android.os.SystemClock.uptimeMillis() + 750;
        if (game != null) {
            game.requestFocus();
            game.kickPadTick();
            // System bars flash on resume and briefly shrink the SurfaceView; that used
            // to stick the client viewport at ~half size. Re-hide + re-apply after settle.
            uiHandler.removeCallbacks(resumeSizeRestore);
            uiHandler.removeCallbacks(deferredShrinkApply);
            uiHandler.post(resumeSizeRestore);
            uiHandler.postDelayed(resumeSizeRestore, 120);
            uiHandler.postDelayed(resumeSizeRestore, 400);
        }
    }

    /** Real window pixel size (includes cutout area when immersive). */
    private int[] windowSizePx() {
        if (Build.VERSION.SDK_INT >= 30) {
            android.graphics.Rect b = getWindowManager().getCurrentWindowMetrics().getBounds();
            return new int[]{Math.max(1, b.width()), Math.max(1, b.height())};
        }
        android.util.DisplayMetrics dm = new android.util.DisplayMetrics();
        getWindowManager().getDefaultDisplay().getRealMetrics(dm);
        return new int[]{Math.max(1, dm.widthPixels), Math.max(1, dm.heightPixels)};
    }

    /**
     * Push surface size into the AWT client. Ignores transient shrinks (keyboard /
     * immersive bar flash) so the viewport does not stick at a tiny size.
     */
    private void applySurfaceSize(int width, int height, String reason, boolean force) {
        if (width <= 0 || height <= 0) {
            return;
        }
        if (keyboardOpen && !force) {
            return;
        }
        long area = (long) width * height;
        long stableArea = (long) stableSurfaceW * stableSurfaceH;
        boolean significantShrink = stableArea > 0 && area * 100 < stableArea * 85;
        boolean inResumeGrace = android.os.SystemClock.uptimeMillis() < resumeGraceUntilMs;
        // Transient inset flash on resume / IME — do not teach the client a tiny viewport.
        if (significantShrink && (inResumeGrace || !force || !"deferred-shrink".equals(reason))) {
            Log.w("void-osrs", "ignore shrink " + width + "x" + height
                    + " vs stable " + stableSurfaceW + "x" + stableSurfaceH
                    + " (" + reason + (inResumeGrace ? " grace" : "") + ")");
            if (!inResumeGrace && !force) {
                uiHandler.removeCallbacks(deferredShrinkApply);
                uiHandler.postDelayed(deferredShrinkApply, 400);
            }
            if (stableSurfaceW > 0 && stableSurfaceH > 0
                    && (force || "surfaceChanged".equals(reason) || "layout".equals(reason))) {
                // Keep client at last good size while the view catches up.
                AwtHost.setDisplaySize(stableSurfaceW, stableSurfaceH);
            }
            return;
        }
        uiHandler.removeCallbacks(deferredShrinkApply);
        if (area >= stableArea || "deferred-shrink".equals(reason)) {
            stableSurfaceW = width;
            stableSurfaceH = height;
        }
        Log.i("void-osrs", "surface size " + width + "x" + height
                + " stable=" + stableSurfaceW + "x" + stableSurfaceH
                + " (" + reason + (force ? " force" : "") + ")");
        AwtHost.setDisplaySize(width, height);
        startClientIfReady(width, height);
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
            resumeGraceUntilMs = Math.max(resumeGraceUntilMs,
                    android.os.SystemClock.uptimeMillis() + 400);
            uiHandler.removeCallbacks(resumeSizeRestore);
            uiHandler.post(resumeSizeRestore);
            uiHandler.postDelayed(resumeSizeRestore, 200);
        }
    }

    /** Sticky immersive: hide status (clock) + nav (back/home). */
    private void hideSystemUi() {
        Window window = getWindow();
        if (window == null) {
            return;
        }
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        if (Build.VERSION.SDK_INT >= 28) {
            WindowManager.LayoutParams lp = window.getAttributes();
            lp.layoutInDisplayCutoutMode =
                    WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
            window.setAttributes(lp);
        }
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
        Log.i("void-osrs", "showKeyboard");
        keyboardOpen = true;
        uiHandler.removeCallbacks(forceHideImeRunnable);
        if (keyboardBall != null) {
            keyboardBall.setAlpha(0.35f);
        }
        if (imeInput == null) {
            return;
        }
        syncingText = true;
        imeInput.setText(typedBuffer);
        imeInput.setSelection(typedBuffer.length());
        syncingText = false;
        imeInput.setFocusable(true);
        imeInput.setFocusableInTouchMode(true);
        imeInput.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.restartInput(imeInput);
            imm.showSoftInput(imeInput, InputMethodManager.SHOW_FORCED);
        }
        if (Build.VERSION.SDK_INT >= 30) {
            View decor = getWindow().getDecorView();
            WindowInsetsController c = decor.getWindowInsetsController();
            if (c != null) {
                c.show(WindowInsets.Type.ime());
            }
        }
    }

    private final Runnable forceHideImeRunnable = new Runnable() {
        public void run() {
            if (keyboardOpen) {
                return;
            }
            forceHideIme();
        }
    };

    private void forceHideIme() {
        View decor = getWindow() != null ? getWindow().getDecorView() : null;
        if (Build.VERSION.SDK_INT >= 30 && decor != null) {
            WindowInsetsController c = decor.getWindowInsetsController();
            if (c != null) {
                c.hide(WindowInsets.Type.ime());
            }
        }
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            if (imeInput != null && imeInput.getWindowToken() != null) {
                imm.hideSoftInputFromWindow(imeInput.getWindowToken(), 0);
            }
            if (game != null && game.getWindowToken() != null) {
                imm.hideSoftInputFromWindow(game.getWindowToken(), 0);
            }
            if (decor != null && decor.getWindowToken() != null) {
                imm.hideSoftInputFromWindow(decor.getWindowToken(), 0);
            }
        }
    }

    private void hideKeyboard() {
        Log.i("void-osrs", "hideKeyboard");
        keyboardOpen = false;
        if (keyboardBall != null) {
            keyboardBall.setAlpha(1f);
        }
        if (imeInput != null) {
            imeInput.clearFocus();
            // Drop focusability so the IME cannot linger on this field.
            imeInput.setFocusable(false);
            imeInput.setFocusableInTouchMode(false);
        }
        if (game != null) {
            game.requestFocus();
            game.requestFocusFromTouch();
        }
        forceHideIme();
        // IME often ignores the first hide while still animating in.
        uiHandler.removeCallbacks(forceHideImeRunnable);
        uiHandler.postDelayed(forceHideImeRunnable, 50);
        uiHandler.postDelayed(forceHideImeRunnable, 200);
        uiHandler.postDelayed(forceHideImeRunnable, 450);
    }

    private boolean isImeVisible() {
        if (Build.VERSION.SDK_INT >= 30) {
            View decor = getWindow() != null ? getWindow().getDecorView() : null;
            if (decor != null) {
                android.view.WindowInsets insets = decor.getRootWindowInsets();
                if (insets != null) {
                    return insets.isVisible(WindowInsets.Type.ime());
                }
            }
        }
        return keyboardOpen;
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

    final class GameView extends View implements AwtHost.Presenter {
        private Bitmap frame;
        private final Paint paint = new Paint(Paint.FILTER_BITMAP_FLAG);
        private final Paint cursorPaint = new Paint(Paint.FILTER_BITMAP_FLAG | Paint.ANTI_ALIAS_FLAG);
        private final Rect src = new Rect();
        private final Rect dst = new Rect();
        private final RectF cursorDst = new RectF();
        private Bitmap cursorBmp;
        private float cursorHotX;
        private float cursorHotY;
        private float cursorDrawW;
        private float cursorDrawH;
        private boolean down;
        private boolean multiTouch;
        private boolean dragging;
        private boolean longPressFired;
        /** Fired once while 4+ fingers are down; reset when all fingers lift. */
        private boolean fourFingerConsoleFired;
        /** Show soft keyboard after the open gesture fully ends (fingers up). */
        private boolean pendingConsoleKeyboard;
        private int multiTouchMaxCount;
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
        private int padDeviceId = -1;
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
        private InputManager inputManager;
        private final InputManager.InputDeviceListener padDeviceListener =
                new InputManager.InputDeviceListener() {
                    public void onInputDeviceAdded(int deviceId) {
                    }

                    public void onInputDeviceRemoved(int deviceId) {
                        onPadDeviceGone(deviceId);
                    }

                    public void onInputDeviceChanged(int deviceId) {
                        if (InputDevice.getDevice(deviceId) == null) {
                            onPadDeviceGone(deviceId);
                        }
                    }
                };
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
            setWillNotDraw(false);
            setFocusable(true);
            setFocusableInTouchMode(true);
            cursorBmp = BitmapFactory.decodeResource(getResources(), R.drawable.cursor_normal_select);
            float density = getResources().getDisplayMetrics().density;
            cursorDrawW = 32f * density;
            cursorDrawH = 32f * density;
            cursorHotX = 6f / 32f * cursorDrawW;
            cursorHotY = 6f / 32f * cursorDrawH;
            addOnLayoutChangeListener((v, left, top, right, bottom, oldLeft, oldTop, oldRight, oldBottom) -> {
                if (keyboardOpen) {
                    return;
                }
                int w = Math.max(0, right - left);
                int h = Math.max(0, bottom - top);
                if (w > 0 && h > 0) {
                    applySurfaceSize(w, h, "layout", false);
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

        @Override
        protected void onAttachedToWindow() {
            super.onAttachedToWindow();
            inputManager = (InputManager) getContext().getSystemService(Context.INPUT_SERVICE);
            if (inputManager != null) {
                inputManager.registerInputDeviceListener(padDeviceListener, touchHandler);
            }
        }

        @Override
        protected void onDetachedFromWindow() {
            if (inputManager != null) {
                inputManager.unregisterInputDeviceListener(padDeviceListener);
                inputManager = null;
            }
            super.onDetachedFromWindow();
        }

        void stopPadTick() {
            touchHandler.removeCallbacks(padTick);
            padTickRunning = false;
        }

        void kickPadTick() {
            if (padActive && !anyPadConnected()) {
                deactivatePad();
                return;
            }
            if (padActive && needsPadTick()) {
                startPadTick();
            }
        }

        private void activatePad(int deviceId) {
            padActive = true;
            if (deviceId >= 0) {
                padDeviceId = deviceId;
            }
            ensureCursor();
            startPadTick();
            redraw();
        }

        private void deactivatePad() {
            if (!padActive) {
                return;
            }
            if (padLeftDown || padRightDown) {
                int[] xy = map(cursorVx, cursorVy);
                if (padLeftDown) {
                    AwtHost.injectMouse(MouseEvent.MOUSE_RELEASED, xy[0], xy[1], MouseEvent.BUTTON1, 1);
                }
                if (padRightDown) {
                    AwtHost.injectMouse(MouseEvent.MOUSE_RELEASED, xy[0], xy[1], MouseEvent.BUTTON3, 1);
                }
            }
            padActive = false;
            padDeviceId = -1;
            stickLX = 0f;
            stickLY = 0f;
            stickRX = 0f;
            stickRY = 0f;
            triggerL2 = 0f;
            l1Held = false;
            l2DigitalHeld = false;
            padLeftDown = false;
            padRightDown = false;
            stopPadTick();
            redraw();
        }

        private void onPadDeviceGone(int deviceId) {
            if (!padActive) {
                return;
            }
            if (padDeviceId >= 0 && deviceId != padDeviceId && anyPadConnected()) {
                return;
            }
            deactivatePad();
        }

        private boolean isPadDevice(InputDevice device) {
            if (device == null || device.isVirtual()) {
                return false;
            }
            int src = device.getSources();
            return (src & InputDevice.SOURCE_GAMEPAD) == InputDevice.SOURCE_GAMEPAD
                    || (src & InputDevice.SOURCE_JOYSTICK) == InputDevice.SOURCE_JOYSTICK;
        }

        private boolean anyPadConnected() {
            for (int id : InputDevice.getDeviceIds()) {
                if (isPadDevice(InputDevice.getDevice(id))) {
                    return true;
                }
            }
            return false;
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

            activatePad(event.getDeviceId());
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
            int action = event.getActionMasked();
            if (action == MotionEvent.ACTION_CANCEL) {
                onPadDeviceGone(event.getDeviceId());
                return true;
            }
            if (action != MotionEvent.ACTION_MOVE) {
                return false;
            }
            activatePad(event.getDeviceId());
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
                if (!multiTouch) {
                    multiTouchMaxCount = 0;
                }
                multiTouch = true;
                multiTouchMaxCount = Math.max(multiTouchMaxCount, count);
                lastPinchDist = spacing(event);
                pinchAccum = 0f;
                // IME covers the view — dismiss so a 4-finger close can finish.
                if (keyboardOpen || isImeVisible()) {
                    hideKeyboard();
                }
                maybeFourFingerConsole();
                return true;
            }

            if (multiTouch) {
                if (action == MotionEvent.ACTION_MOVE && count >= 2) {
                    multiTouchMaxCount = Math.max(multiTouchMaxCount, count);
                    maybeFourFingerConsole();
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
                        finishFourFingerGesture();
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
                    finishFourFingerGesture();
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
                    Log.i("void-osrs", "tap left-click @ " + downX + "," + downY
                            + " frac=" + String.format(java.util.Locale.US, "%.3f,%.3f",
                                downX / (float) Math.max(1, frameW),
                                downY / (float) Math.max(1, frameH))
                            + " frame=" + frameW + "x" + frameH);
                    AwtHost.injectLeftClick(downX, downY);
                    if (AwtHost.isDevConsoleOpen()) {
                        if (isImeVisible() || keyboardOpen) {
                            hideKeyboard();
                        } else {
                            showKeyboard();
                        }
                    } else if (isImeVisible() || keyboardOpen) {
                        hideKeyboard();
                    }
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

        private void maybeFourFingerConsole() {
            if (fourFingerConsoleFired || multiTouchMaxCount < 4) {
                return;
            }
            fourFingerConsoleFired = true;
            boolean wasOpen = AwtHost.isDevConsoleOpen();
            Log.i("void-osrs", "4-finger tap → developer console (wasOpen=" + wasOpen + ")");
            if (wasOpen) {
                AwtHost.setDevConsoleOpen(false);
                pendingConsoleKeyboard = false;
                hideKeyboard();
            } else {
                AwtHost.setDevConsoleOpen(true);
                // Don't show IME until fingers lift — otherwise the gesture aborts.
                pendingConsoleKeyboard = true;
            }
        }

        private void finishFourFingerGesture() {
            fourFingerConsoleFired = false;
            multiTouchMaxCount = 0;
            if (pendingConsoleKeyboard) {
                pendingConsoleKeyboard = false;
                if (AwtHost.isDevConsoleOpen()) {
                    showKeyboard();
                }
            }
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
            // View.onDraw always uses the layout size — avoids SurfaceView buffer
            // sticking at ~half width after resume on some OEM devices.
            postInvalidateOnAnimation();
        }

        @Override
        protected void onDraw(Canvas canvas) {
            drawFrame(canvas);
        }

        @Override
        protected void onSizeChanged(int w, int h, int oldw, int oldh) {
            super.onSizeChanged(w, h, oldw, oldh);
            if (w > 0 && h > 0 && !keyboardOpen) {
                applySurfaceSize(w, h, "sizeChanged", false);
            }
        }

        private void drawFrame(Canvas canvas) {
            canvas.drawColor(0xff000000);
            Bitmap bmp = frame;
            if (bmp != null) {
                src.set(0, 0, bmp.getWidth(), bmp.getHeight());
                int dw = Math.max(canvas.getWidth(), getWidth());
                int dh = Math.max(canvas.getHeight(), getHeight());
                dst.set(0, 0, dw, dh);
                canvas.drawBitmap(bmp, src, dst, paint);
            }
            if (padActive && cursorVx >= 0f && cursorVy >= 0f && cursorBmp != null) {
                float left = cursorVx - cursorHotX;
                float top = cursorVy - cursorHotY;
                cursorDst.set(left, top, left + cursorDrawW, top + cursorDrawH);
                canvas.drawBitmap(cursorBmp, null, cursorDst, cursorPaint);
            }
        }

        void redraw() {
            invalidate();
        }
    }
}
