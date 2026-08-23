package world.gregs.voidosrs.android;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.EditText;
import android.widget.LinearLayout;

import voidawt.AwtHost;
import voidawt.event.KeyEvent;
import voidawt.event.MouseEvent;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.setProperty("user.home", getFilesDir().getAbsolutePath());

        GameView game = new GameView(this);
        EditText input = new EditText(this);
        input.setHint("type here (login / chat)");
        input.setSingleLine(true);
        input.setOnEditorActionListener((v, actionId, event) -> {
            AwtHost.injectKey(KeyEvent.KEY_PRESSED, KeyEvent.VK_ENTER, '\n');
            AwtHost.injectKey(KeyEvent.KEY_TYPED, 0, '\n');
            AwtHost.injectKey(KeyEvent.KEY_RELEASED, KeyEvent.VK_ENTER, '\n');
            v.setText("");
            return true;
        });
        input.addTextChangedListener(new android.text.TextWatcher() {
            private String last = "";

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String now = s == null ? "" : s.toString();
                if (now.length() > last.length()) {
                    char c = now.charAt(now.length() - 1);
                    int code = Character.toUpperCase(c);
                    AwtHost.injectKey(KeyEvent.KEY_PRESSED, code, c);
                    AwtHost.injectKey(KeyEvent.KEY_TYPED, 0, c);
                    AwtHost.injectKey(KeyEvent.KEY_RELEASED, code, c);
                } else if (now.length() < last.length()) {
                    AwtHost.injectKey(KeyEvent.KEY_PRESSED, KeyEvent.VK_BACK_SPACE, '\b');
                    AwtHost.injectKey(KeyEvent.KEY_TYPED, 0, '\b');
                    AwtHost.injectKey(KeyEvent.KEY_RELEASED, KeyEvent.VK_BACK_SPACE, '\b');
                }
                last = now;
            }

            public void afterTextChanged(android.text.Editable s) {
            }
        });

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.addView(game, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 1f));
        root.addView(input, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        setContentView(root);

        AwtHost.presenter = game;
        new Thread(() -> {
            try {
                Class<?> loaderCl = Class.forName("Loader");
                loaderCl.getField("address").set(null, "10.0.2.2");
                loaderCl.getField("debug").set(null, true);
                Object loader = loaderCl.getDeclaredConstructor().newInstance();
                loaderCl.getMethod("setSize", int.class, int.class).invoke(loader, AwtHost.GAME_WIDTH, AwtHost.GAME_HEIGHT);
                AwtHost.setRoot((voidawt.Component) loader);
                loaderCl.getMethod("init").invoke(loader);
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }, "void-client").start();
    }

    static final class GameView extends SurfaceView implements SurfaceHolder.Callback, AwtHost.Presenter {
        private Bitmap frame;
        private final Paint paint = new Paint(Paint.FILTER_BITMAP_FLAG);
        private final Rect src = new Rect();
        private final Rect dst = new Rect();
        private boolean down;

        GameView(MainActivity activity) {
            super(activity);
            getHolder().addCallback(this);
            setFocusable(true);
        }

        public boolean onTouchEvent(MotionEvent event) {
            int[] xy = map(event.getX(), event.getY());
            int x = xy[0];
            int y = xy[1];
            int action = event.getActionMasked();
            if (action == MotionEvent.ACTION_DOWN) {
                down = true;
                AwtHost.injectMouse(MouseEvent.MOUSE_PRESSED, x, y, MouseEvent.BUTTON1, 1);
            } else if (action == MotionEvent.ACTION_MOVE && down) {
                AwtHost.injectMouse(MouseEvent.MOUSE_DRAGGED, x, y, MouseEvent.BUTTON1, 0);
            } else if (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_CANCEL) {
                AwtHost.injectMouse(MouseEvent.MOUSE_RELEASED, x, y, MouseEvent.BUTTON1, 1);
                AwtHost.injectMouse(MouseEvent.MOUSE_CLICKED, x, y, MouseEvent.BUTTON1, 1);
                down = false;
            }
            return true;
        }

        private int[] map(float vx, float vy) {
            int vw = Math.max(1, getWidth());
            int vh = Math.max(1, getHeight());
            float scale = Math.min(vw / (float) AwtHost.GAME_WIDTH, vh / (float) AwtHost.GAME_HEIGHT);
            float dw = AwtHost.GAME_WIDTH * scale;
            float dh = AwtHost.GAME_HEIGHT * scale;
            float ox = (vw - dw) / 2f;
            float oy = (vh - dh) / 2f;
            int x = (int) ((vx - ox) / scale);
            int y = (int) ((vy - oy) / scale);
            x = Math.max(0, Math.min(AwtHost.GAME_WIDTH - 1, x));
            y = Math.max(0, Math.min(AwtHost.GAME_HEIGHT - 1, y));
            return new int[]{x, y};
        }

        public void present(Bitmap bitmap) {
            Bitmap old = frame;
            frame = bitmap;
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
            if (bmp == null) {
                return;
            }
            src.set(0, 0, bmp.getWidth(), bmp.getHeight());
            int vw = canvas.getWidth();
            int vh = canvas.getHeight();
            float scale = Math.min(vw / (float) bmp.getWidth(), vh / (float) bmp.getHeight());
            int dw = Math.round(bmp.getWidth() * scale);
            int dh = Math.round(bmp.getHeight() * scale);
            int ox = (vw - dw) / 2;
            int oy = (vh - dh) / 2;
            dst.set(ox, oy, ox + dw, oy + dh);
            canvas.drawBitmap(bmp, src, dst, paint);
        }

        public void surfaceCreated(SurfaceHolder holder) {
        }

        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        }

        public void surfaceDestroyed(SurfaceHolder holder) {
        }
    }
}
