package world.gregs.voidosrs.ios;

import org.robovm.apple.coregraphics.CGPoint;
import org.robovm.apple.coregraphics.CGRect;
import org.robovm.apple.foundation.NSArray;
import org.robovm.apple.foundation.NSSet;
import org.robovm.apple.uikit.UIColor;
import org.robovm.apple.uikit.UIEvent;
import org.robovm.apple.uikit.UIGestureRecognizer;
import org.robovm.apple.uikit.UIGestureRecognizerState;
import org.robovm.apple.uikit.UIImageView;
import org.robovm.apple.uikit.UILongPressGestureRecognizer;
import org.robovm.apple.uikit.UITouch;
import org.robovm.apple.uikit.UIView;
import org.robovm.apple.uikit.UIViewContentMode;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import voidawt.AwtHost;
import voidawt.event.MouseEvent;

public class GameView extends UIView implements AwtHost.Presenter {
    private static final float PINCH_PX_PER_NOTCH = 28f;
    private static final float TOUCH_SLOP = 24f;
    private static final double LONG_PRESS_SECONDS = 0.4;
    private static final double LONG_PRESS_SLOP = 24.0;

    private final UIImageView imageView;
    private final Map<Long, CGPoint> active = new LinkedHashMap<Long, CGPoint>();
    private boolean down;
    private boolean multiTouch;
    private boolean dragging;
    private boolean longPressFired;
    private boolean ignoreSingleFinger;
    private float downVx;
    private float downVy;
    private int downX;
    private int downY;
    private float lastPinchDist = -1f;
    private float pinchAccum;
    private float lastMidX;
    private float lastMidY;
    private int frameW = AwtHost.GAME_WIDTH;
    private int frameH = AwtHost.GAME_HEIGHT;
    private CGPoint lastTouchPoint = new CGPoint(0, 0);
    private Runnable sizeListener;

    public GameView(CGRect frame) {
        super(frame);
        setMultipleTouchEnabled(true);
        setUserInteractionEnabled(true);
        setBackgroundColor(UIColor.black());
        imageView = new UIImageView(getBounds());
        imageView.setContentMode(UIViewContentMode.ScaleToFill);
        imageView.setUserInteractionEnabled(false);
        addSubview(imageView);
        UILongPressGestureRecognizer longPress = new UILongPressGestureRecognizer(
                new UIGestureRecognizer.OnGestureListener() {
                    public void onGesture(UIGestureRecognizer recognizer) {
                        if (recognizer.getState() != UIGestureRecognizerState.Began) {
                            return;
                        }
                        if (!down || multiTouch || dragging || longPressFired || ignoreSingleFinger) {
                            return;
                        }
                        longPressFired = true;
                        CGPoint p = recognizer.getLocationInView(GameView.this);
                        int[] xy = map(p);
                        downX = xy[0];
                        downY = xy[1];
                        AwtHost.injectMouse(MouseEvent.MOUSE_PRESSED, downX, downY, MouseEvent.BUTTON3, 1);
                        AwtHost.injectMouse(MouseEvent.MOUSE_RELEASED, downX, downY, MouseEvent.BUTTON3, 1);
                        AwtHost.injectMouse(MouseEvent.MOUSE_CLICKED, downX, downY, MouseEvent.BUTTON3, 1);
                    }
                });
        longPress.setMinimumPressDuration(LONG_PRESS_SECONDS);
        longPress.setAllowableMovement(LONG_PRESS_SLOP);
        longPress.setCancelsTouchesInView(false);
        addGestureRecognizer(longPress);
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

    @Override
    public void touchesBegan(NSSet<UITouch> touches, UIEvent event) {
        updateActive(touches, false);
        int count = active.size();
        if (count >= 2) {
            if (down) {
                if (dragging && !longPressFired) {
                    int[] xy = map(lastTouchPoint);
                    AwtHost.injectMouse(MouseEvent.MOUSE_RELEASED, xy[0], xy[1], MouseEvent.BUTTON1, 1);
                }
                down = false;
                dragging = false;
                longPressFired = false;
            }
            ignoreSingleFinger = true;
            multiTouch = true;
            lastPinchDist = spacing();
            pinchAccum = 0f;
            CGPoint mid = midpoint();
            lastMidX = (float) mid.getX();
            lastMidY = (float) mid.getY();
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
        downX = xy[0];
        downY = xy[1];
    }

    @Override
    public void touchesMoved(NSSet<UITouch> touches, UIEvent event) {
        updateActive(touches, false);
        if (multiTouch) {
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
                CGPoint mid = midpoint();
                float dx = (float) mid.getX() - lastMidX;
                float dy = (float) mid.getY() - lastMidY;
                if (dx != 0f || dy != 0f) {
                    AwtHost.injectCameraOrbit(dx, dy);
                }
                lastMidX = (float) mid.getX();
                lastMidY = (float) mid.getY();
            }
            return;
        }
        if (ignoreSingleFinger || !down || longPressFired) {
            return;
        }
        CGPoint p = firstPoint();
        int[] xy = map(p);
        float moved = Math.max(Math.abs((float) p.getX() - downVx), Math.abs((float) p.getY() - downVy));
        if (!dragging && moved > TOUCH_SLOP) {
            dragging = true;
            AwtHost.injectMouse(MouseEvent.MOUSE_PRESSED, downX, downY, MouseEvent.BUTTON1, 1);
        }
        if (dragging) {
            AwtHost.injectMouse(MouseEvent.MOUSE_DRAGGED, xy[0], xy[1], MouseEvent.BUTTON1, 0);
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
            } else {
                lastPinchDist = spacing();
                CGPoint mid = midpoint();
                lastMidX = (float) mid.getX();
                lastMidY = (float) mid.getY();
            }
            return;
        }
        if (ignoreSingleFinger) {
            updateActive(touches, true);
            ignoreSingleFinger = false;
            down = false;
            dragging = false;
            longPressFired = false;
            return;
        }
        CGPoint p = lastTouchPoint;
        updateActive(touches, true);
        int[] xy = map(p);
        if (!down) {
            // leftover
        } else if (longPressFired) {
            // right-click already injected
        } else if (dragging) {
            AwtHost.injectMouse(MouseEvent.MOUSE_RELEASED, xy[0], xy[1], MouseEvent.BUTTON1, 1);
            AwtHost.injectMouse(MouseEvent.MOUSE_CLICKED, xy[0], xy[1], MouseEvent.BUTTON1, 1);
        } else if (!cancelled) {
            AwtHost.injectMouse(MouseEvent.MOUSE_PRESSED, downX, downY, MouseEvent.BUTTON1, 1);
            AwtHost.injectMouse(MouseEvent.MOUSE_RELEASED, downX, downY, MouseEvent.BUTTON1, 1);
            AwtHost.injectMouse(MouseEvent.MOUSE_CLICKED, downX, downY, MouseEvent.BUTTON1, 1);
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
