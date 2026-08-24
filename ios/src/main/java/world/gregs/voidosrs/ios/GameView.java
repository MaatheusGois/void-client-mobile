package world.gregs.voidosrs.ios;

import org.robovm.apple.coregraphics.CGPoint;
import org.robovm.apple.coregraphics.CGRect;
import org.robovm.apple.dispatch.DispatchQueue;
import org.robovm.apple.foundation.NSArray;
import org.robovm.apple.foundation.NSSet;
import org.robovm.apple.uikit.UIColor;
import org.robovm.apple.uikit.UIEvent;
import org.robovm.apple.uikit.UIImageView;
import org.robovm.apple.uikit.UITouch;
import org.robovm.apple.uikit.UIView;
import org.robovm.apple.uikit.UIViewContentMode;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import voidawt.AwtHost;

public class GameView extends UIView implements AwtHost.Presenter {
    private static final float PINCH_PX_PER_NOTCH = 28f;
    /**
     * Hold still within this → long-press right-click.
     * Move past this before timeout → one-finger camera orbit.
     */
    private static final float TOUCH_SLOP = 100f;

    private final UIImageView imageView;
    private final Map<Long, CGPoint> active = new LinkedHashMap<Long, CGPoint>();
    private boolean down;
    private boolean multiTouch;
    private boolean dragging;
    private boolean longPressFired;
    /** Fired once while 4+ fingers are down; reset when all fingers lift. */
    private boolean fourFingerConsoleFired;
    private boolean pendingConsoleKeyboard;
    private int multiTouchMaxCount;
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
    private CGPoint lastTouchPoint = new CGPoint(0, 0);
    private Runnable sizeListener;
    /** Bumped to cancel a pending long-press callback. */
    private int pressGeneration;

    public GameView(CGRect frame) {
        super(frame);
        setMultipleTouchEnabled(true);
        setUserInteractionEnabled(true);
        setBackgroundColor(UIColor.black());
        imageView = new UIImageView(getBounds());
        imageView.setContentMode(UIViewContentMode.ScaleToFill);
        imageView.setUserInteractionEnabled(false);
        addSubview(imageView);
    }

    public void setSizeListener(Runnable listener) {
        this.sizeListener = listener;
    }

    @Override
    public void layoutSubviews() {
        super.layoutSubviews();
        CGRect bounds = getBounds();
        imageView.setFrame(bounds);
        int w = Math.max(0, (int) Math.round(bounds.getWidth()));
        int h = Math.max(0, (int) Math.round(bounds.getHeight()));
        if (w > 0 && h > 0) {
            AwtHost.setDisplaySize(w, h);
            if (sizeListener != null) {
                sizeListener.run();
            }
        }
    }

    public int viewWidth() {
        return Math.max(1, (int) Math.round(getBounds().getWidth()));
    }

    public int viewHeight() {
        return Math.max(1, (int) Math.round(getBounds().getHeight()));
    }

    public void present(int[] argb, int width, int height) {
        frameW = width;
        frameH = height;
        imageView.setImage(ArgbBridge.toImage(argb, width, height));
    }

    private void scheduleLongPress() {
        final int gen = ++pressGeneration;
        DispatchQueue.getMainQueue().after(450, TimeUnit.MILLISECONDS, new Runnable() {
            public void run() {
                if (gen != pressGeneration) {
                    return;
                }
                if (!down || multiTouch || dragging || longPressFired || ignoreSingleFinger) {
                    return;
                }
                longPressFired = true;
                System.out.println("longPress right-click @ " + downX + "," + downY);
                AwtHost.injectRightClick(downX, downY);
            }
        });
    }

    private void cancelLongPress() {
        pressGeneration++;
    }

    private void maybeFourFingerConsole() {
        if (fourFingerConsoleFired || multiTouchMaxCount < 4) {
            return;
        }
        fourFingerConsoleFired = true;
        boolean wasOpen = AwtHost.isDevConsoleOpen();
        System.out.println("void-osrs 4-finger tap → developer console (wasOpen=" + wasOpen + ")");
        if (wasOpen) {
            AwtHost.setDevConsoleOpen(false);
            pendingConsoleKeyboard = false;
            AwtHost.requestHideSoftKeyboard("dev-console-close");
        } else {
            AwtHost.setDevConsoleOpen(true);
            pendingConsoleKeyboard = true;
        }
    }

    private void finishFourFingerGesture() {
        fourFingerConsoleFired = false;
        multiTouchMaxCount = 0;
        if (pendingConsoleKeyboard) {
            pendingConsoleKeyboard = false;
            if (AwtHost.isDevConsoleOpen()) {
                AwtHost.requestSoftKeyboard("dev-console-open");
            }
        }
    }

    @Override
    public void touchesBegan(NSSet<UITouch> touches, UIEvent event) {
        updateActive(touches, false);
        int count = active.size();
        if (count >= 2) {
            cancelLongPress();
            down = false;
            dragging = false;
            longPressFired = false;
            ignoreSingleFinger = true;
            if (!multiTouch) {
                multiTouchMaxCount = 0;
            }
            multiTouch = true;
            multiTouchMaxCount = Math.max(multiTouchMaxCount, count);
            lastPinchDist = spacing();
            pinchAccum = 0f;
            AwtHost.requestHideSoftKeyboard("multi-touch");
            maybeFourFingerConsole();
            return;
        }
        if (ignoreSingleFinger) {
            ignoreSingleFinger = false;
        }
        CGPoint p = firstPoint();
        int[] xy = map(p);
        down = true;
        dragging = false;
        longPressFired = false;
        downVx = (float) p.getX();
        downVy = (float) p.getY();
        lastOrbitX = downVx;
        lastOrbitY = downVy;
        downX = xy[0];
        downY = xy[1];
        scheduleLongPress();
    }

    @Override
    public void touchesMoved(NSSet<UITouch> touches, UIEvent event) {
        updateActive(touches, false);
        if (multiTouch) {
            multiTouchMaxCount = Math.max(multiTouchMaxCount, active.size());
            maybeFourFingerConsole();
            if (active.size() >= 2) {
                float dist = spacing();
                if (lastPinchDist > 0f) {
                    pinchAccum += dist - lastPinchDist;
                    while (pinchAccum >= PINCH_PX_PER_NOTCH) {
                        int[] midGame = map(midpoint());
                        AwtHost.injectWheel(midGame[0], midGame[1], -1);
                        pinchAccum -= PINCH_PX_PER_NOTCH;
                    }
                    while (pinchAccum <= -PINCH_PX_PER_NOTCH) {
                        int[] midGame = map(midpoint());
                        AwtHost.injectWheel(midGame[0], midGame[1], 1);
                        pinchAccum += PINCH_PX_PER_NOTCH;
                    }
                }
                lastPinchDist = dist;
            }
            return;
        }
        if (ignoreSingleFinger || !down || longPressFired) {
            return;
        }
        CGPoint p = firstPoint();
        float moved = Math.max(Math.abs((float) p.getX() - downVx), Math.abs((float) p.getY() - downVy));
        if (!dragging && moved > TOUCH_SLOP) {
            dragging = true;
            cancelLongPress();
            lastOrbitX = (float) p.getX();
            lastOrbitY = (float) p.getY();
        }
        if (dragging) {
            float dx = (float) p.getX() - lastOrbitX;
            float dy = (float) p.getY() - lastOrbitY;
            AwtHost.injectCameraOrbit(dx, dy);
            lastOrbitX = (float) p.getX();
            lastOrbitY = (float) p.getY();
        }
    }

    @Override
    public void touchesEnded(NSSet<UITouch> touches, UIEvent event) {
        finishTouches(touches, false);
    }

    @Override
    public void touchesCancelled(NSSet<UITouch> touches, UIEvent event) {
        finishTouches(touches, true);
    }

    private void finishTouches(NSSet<UITouch> touches, boolean cancelled) {
        if (multiTouch) {
            updateActive(touches, true);
            if (active.size() < 2) {
                multiTouch = false;
                ignoreSingleFinger = active.size() == 1;
                down = false;
                dragging = false;
                longPressFired = false;
                lastPinchDist = -1f;
                pinchAccum = 0f;
                cancelLongPress();
                finishFourFingerGesture();
            } else {
                lastPinchDist = spacing();
            }
            return;
        }
        if (ignoreSingleFinger) {
            updateActive(touches, true);
            ignoreSingleFinger = false;
            down = false;
            dragging = false;
            longPressFired = false;
            cancelLongPress();
            return;
        }
        updateActive(touches, true);
        cancelLongPress();
        if (!down) {
            // leftover
        } else if (longPressFired) {
            // right-click already injected
        } else if (dragging) {
            // one-finger camera orbit — no mouse click
        } else if (!cancelled) {
            System.out.println("void-osrs tap left-click @ " + downX + "," + downY
                    + " frac=" + String.format(java.util.Locale.US, "%.3f,%.3f",
                        downX / (float) Math.max(1, frameW),
                        downY / (float) Math.max(1, frameH))
                    + " frame=" + frameW + "x" + frameH);
            AwtHost.injectLeftClick(downX, downY);
            if (AwtHost.isDevConsoleOpen()) {
                AwtHost.requestToggleSoftKeyboard("dev-console-tap");
            } else {
                AwtHost.requestHideSoftKeyboard("dev-console-stuck");
            }
        }
        down = false;
        dragging = false;
        longPressFired = false;
    }

    private void updateActive(NSSet<UITouch> touches, boolean remove) {
        NSArray<UITouch> arr = touches.getValues();
        int n = (int) arr.size();
        for (int i = 0; i < n; i++) {
            UITouch t = arr.get(i);
            CGPoint loc = t.getLocationInView(this);
            lastTouchPoint = loc;
            if (remove) {
                active.remove(t.getHandle());
            } else {
                active.put(t.getHandle(), loc);
            }
        }
    }

    private CGPoint firstPoint() {
        if (active.isEmpty()) {
            return lastTouchPoint;
        }
        return active.values().iterator().next();
    }

    private float spacing() {
        List<CGPoint> pts = points();
        if (pts.size() < 2) {
            return -1f;
        }
        CGPoint a = pts.get(0);
        CGPoint b = pts.get(1);
        double dx = a.getX() - b.getX();
        double dy = a.getY() - b.getY();
        return (float) Math.hypot(dx, dy);
    }

    private CGPoint midpoint() {
        List<CGPoint> pts = points();
        if (pts.size() < 2) {
            return firstPoint();
        }
        CGPoint a = pts.get(0);
        CGPoint b = pts.get(1);
        return new CGPoint((a.getX() + b.getX()) * 0.5, (a.getY() + b.getY()) * 0.5);
    }

    private List<CGPoint> points() {
        return new ArrayList<CGPoint>(active.values());
    }

    private int[] map(CGPoint p) {
        int vw = viewWidth();
        int vh = viewHeight();
        int gw = Math.max(1, frameW);
        int gh = Math.max(1, frameH);
        int x = (int) (p.getX() * gw / (double) vw);
        int y = (int) (p.getY() * gh / (double) vh);
        x = Math.max(0, Math.min(gw - 1, x));
        y = Math.max(0, Math.min(gh - 1, y));
        return new int[]{x, y};
    }
}
