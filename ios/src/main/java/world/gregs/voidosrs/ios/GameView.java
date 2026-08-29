package world.gregs.voidosrs.ios;

import org.robovm.apple.coregraphics.CGPoint;
import org.robovm.apple.coregraphics.CGRect;
import org.robovm.apple.dispatch.DispatchQueue;
import org.robovm.apple.foundation.NSArray;
import org.robovm.apple.foundation.NSObject;
import org.robovm.apple.foundation.NSSet;
import org.robovm.apple.gamecontroller.GCController;
import org.robovm.apple.gamecontroller.GCControllerButtonInput;
import org.robovm.apple.gamecontroller.GCControllerDirectionPad;
import org.robovm.apple.gamecontroller.GCExtendedGamepad;
import org.robovm.apple.uikit.UIColor;
import org.robovm.apple.uikit.UIEvent;
import org.robovm.apple.uikit.UIImage;
import org.robovm.apple.uikit.UIImageView;
import org.robovm.apple.uikit.UITouch;
import org.robovm.apple.uikit.UIView;
import org.robovm.apple.uikit.UIViewContentMode;
import org.robovm.objc.block.VoidBlock1;
import org.robovm.objc.block.VoidBlock3;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import voidawt.AwtHost;
import voidawt.event.MouseEvent;

/**
 * iOS game surface: blits each {@link AwtHost} frame into an {@link UIImageView}
 * and maps multitouch to AWT mouse / wheel (same gestures as Android).
 *
 * <p>Tap → left click, long-press (hold still) → right click,
 * one-finger drag → left-button mouse drag (map, items, …),
 * two-finger pan → camera orbit, pinch → zoom,
 * 4-finger tap → developer console (no auto IME; tap later toggles keyboard).
 * Soft keyboard open for chat/login is owned by {@link GameController} /
 * {@code MobileKeyboard}; this view dismisses IME on tap-outside while
 * {@link AwtHost#SOFT_KEYBOARD_OPEN} is true.
 *
 * <p>DualShock / Xbox / MFi (via {@link GCController}): left stick moves a drawn
 * cursor, ✕ left-click, ○ right-click, L1/L2 zoom, right stick camera orbit —
 * same mapping as Android {@code MainActivity.GameView} pad path.
 *
 * <p>Window resize (Stage Manager / split): the image view stretches immediately;
 * {@link AwtHost#setDisplaySize} (client layout redraw) runs only after the size
 * has been quiet for {@value #RESIZE_SETTLE_MS} ms.
 */
public class GameView extends UIView implements AwtHost.Presenter {
    private static final float PINCH_PX_PER_NOTCH = 28f;
    private static final float CURSOR_SIZE = 32f;
    private static final float CURSOR_HOT_X = 6f;
    private static final float CURSOR_HOT_Y = 6f;
    /**
     * Hold still within this → long-press right-click.
     * Move past this before timeout → left-button mouse drag (map, items, …).
     * Two-finger pan → camera orbit; pinch → zoom; pad right stick also orbits.
     */
    private static final float TOUCH_SLOP = 28f;
    /** DualShock / gamepad → virtual mouse (mirrors Android constants). */
    private static final float PAD_DEADZONE = 0.15f;
    private static final float PAD_CURSOR_SPEED = 14f;
    private static final float PAD_ORBIT_SCALE = 10f;
    private static final long PAD_ZOOM_INTERVAL_MS = 90L;
    private static final float PAD_TRIGGER_THRESHOLD = 0.35f;
    private static final long PAD_TICK_MS = 16L;

    private final UIImageView imageView;
    private final UIImageView cursorView;
    private final Map<Long, CGPoint> active = new LinkedHashMap<Long, CGPoint>();
    private boolean down;
    private boolean multiTouch;
    private boolean dragging;
    private boolean longPressFired;
    /** Fired once while 4+ fingers are down; reset when all fingers lift. */
    private boolean fourFingerConsoleFired;
    private int multiTouchMaxCount;
    private boolean ignoreSingleFinger;
    private float downVx;
    private float downVy;
    private int downX;
    private int downY;
    private float lastPinchDist = -1f;
    private float pinchAccum;
    /** Midpoint of the last two-finger sample (camera pan). */
    private float lastPanX;
    private float lastPanY;
    private boolean panTracked;
    private int frameW = AwtHost.GAME_WIDTH;
    private int frameH = AwtHost.GAME_HEIGHT;
    private CGPoint lastTouchPoint = new CGPoint(0, 0);
    private Runnable sizeListener;
    /** Bumped to cancel a pending long-press callback. */
    private int pressGeneration;
    /**
     * Last size pushed into {@link AwtHost#setDisplaySize}. While the user is
     * still dragging a Stage Manager / split window, we only stretch the
     * existing framebuffer ({@link UIViewContentMode#ScaleToFill}) and defer
     * the client viewport relayout until the size has been stable for
     * {@link #RESIZE_SETTLE_MS}.
     */
    private int appliedW;
    private int appliedH;
    private int pendingW;
    private int pendingH;
    /** Bumped to cancel a pending settled-resize callback. */
    private int resizeGeneration;
    /** Quiet period after the last layout change before adapting the game layout. */
    private static final long RESIZE_SETTLE_MS = 3000;

    // DualShock / gamepad → virtual mouse
    private float cursorVx = -1f;
    private float cursorVy = -1f;
    private boolean padActive;
    private GCController padController;
    private float stickLX;
    private float stickLY;
    private float stickRX;
    private float stickRY;
    private float triggerL2;
    private boolean l1Held;
    private boolean padLeftDown;
    private boolean padRightDown;
    private boolean padTickRunning;
    private long lastPadZoomAt;
    /** Bumped to cancel a pending pad-tick callback. */
    private int padTickGeneration;
    private NSObject padConnectObserver;
    private NSObject padDisconnectObserver;

    public GameView(CGRect frame) {
        super(frame);
        setMultipleTouchEnabled(true);
        setUserInteractionEnabled(true);
        setBackgroundColor(UIColor.black());
        imageView = new UIImageView(getBounds());
        imageView.setContentMode(UIViewContentMode.ScaleToFill);
        imageView.setUserInteractionEnabled(false);
        addSubview(imageView);
        cursorView = new UIImageView(new CGRect(0, 0, CURSOR_SIZE, CURSOR_SIZE));
        UIImage cursor = UIImage.getImage("cursor_normal_select");
        if (cursor == null) {
            cursor = UIImage.getImage("cursor_normal_select.png");
        }
        cursorView.setImage(cursor);
        cursorView.setContentMode(UIViewContentMode.ScaleToFill);
        cursorView.setUserInteractionEnabled(false);
        cursorView.setHidden(true);
        addSubview(cursorView);
        startPadListening();
    }

    /**
     * Subscribe to {@link GCController} connect/disconnect and pick up any pad
     * already paired. Call once from the constructor.
     */
    private void startPadListening() {
        padConnectObserver = GCController.Notifications.observeDidConnect(
                new VoidBlock1<GCController>() {
                    public void invoke(GCController controller) {
                        System.out.println("void-osrs pad connect vendor="
                                + (controller != null ? controller.getVendorName() : null));
                        attachPad(controller);
                    }
                });
        padDisconnectObserver = GCController.Notifications.observeDidDisconnect(
                new VoidBlock1<GCController>() {
                    public void invoke(GCController controller) {
                        System.out.println("void-osrs pad disconnect vendor="
                                + (controller != null ? controller.getVendorName() : null));
                        if (padController != null && padController.equals(controller)) {
                            deactivatePad();
                        } else if (!anyPadConnected()) {
                            deactivatePad();
                        }
                    }
                });
        // Bluetooth DualShock discovery (no-op if already connected).
        GCController.startWirelessControllerDiscovery(new Runnable() {
            public void run() {
                System.out.println("void-osrs pad wireless discovery finished");
            }
        });
        NSArray<GCController> already = GCController.getControllers();
        if (already != null) {
            long n = already.size();
            for (int i = 0; i < n; i++) {
                attachPad(already.get(i));
                if (padActive) {
                    break;
                }
            }
        }
    }

    private boolean anyPadConnected() {
        NSArray<GCController> list = GCController.getControllers();
        if (list == null) {
            return false;
        }
        long n = list.size();
        for (int i = 0; i < n; i++) {
            GCController c = list.get(i);
            if (c != null && c.getExtendedGamepad() != null) {
                return true;
            }
        }
        return false;
    }

    private void attachPad(GCController controller) {
        if (controller == null) {
            return;
        }
        GCExtendedGamepad pad = controller.getExtendedGamepad();
        if (pad == null) {
            return;
        }
        // Prefer the newly connected pad; rebind handlers every time.
        if (padController != null && padController != controller) {
            clearPadHandlers(padController);
        }
        padController = controller;
        controller.setHandlerQueue(DispatchQueue.getMainQueue());
        bindPadHandlers(pad);
        activatePad();
    }

    private void clearPadHandlers(GCController controller) {
        if (controller == null) {
            return;
        }
        GCExtendedGamepad pad = controller.getExtendedGamepad();
        if (pad == null) {
            return;
        }
        pad.getLeftThumbstick().setValueChangedHandler(null);
        pad.getRightThumbstick().setValueChangedHandler(null);
        pad.getButtonA().setPressedChangedHandler(null);
        pad.getButtonB().setPressedChangedHandler(null);
        pad.getLeftShoulder().setPressedChangedHandler(null);
        pad.getLeftTrigger().setValueChangedHandler(null);
        pad.getLeftTrigger().setPressedChangedHandler(null);
    }

    private void bindPadHandlers(final GCExtendedGamepad pad) {
        // GCController Y: +up; screen Y: +down — negate both sticks.
        pad.getLeftThumbstick().setValueChangedHandler(
                new VoidBlock3<GCControllerDirectionPad, Float, Float>() {
                    public void invoke(GCControllerDirectionPad dpad, Float x, Float y) {
                        stickLX = x != null ? x.floatValue() : 0f;
                        stickLY = y != null ? -y.floatValue() : 0f;
                        startPadTick();
                    }
                });
        pad.getRightThumbstick().setValueChangedHandler(
                new VoidBlock3<GCControllerDirectionPad, Float, Float>() {
                    public void invoke(GCControllerDirectionPad dpad, Float x, Float y) {
                        stickRX = x != null ? x.floatValue() : 0f;
                        stickRY = y != null ? -y.floatValue() : 0f;
                        startPadTick();
                    }
                });
        // ✕ Cross → left click (Apple maps Cross to buttonA on DualShock)
        pad.getButtonA().setPressedChangedHandler(
                new VoidBlock3<GCControllerButtonInput, Float, Boolean>() {
                    public void invoke(GCControllerButtonInput button, Float value, Boolean pressed) {
                        onPadClick(true, pressed != null && pressed.booleanValue());
                    }
                });
        // ○ Circle → right click
        pad.getButtonB().setPressedChangedHandler(
                new VoidBlock3<GCControllerButtonInput, Float, Boolean>() {
                    public void invoke(GCControllerButtonInput button, Float value, Boolean pressed) {
                        onPadClick(false, pressed != null && pressed.booleanValue());
                    }
                });
        // L1 → zoom out while held
        pad.getLeftShoulder().setPressedChangedHandler(
                new VoidBlock3<GCControllerButtonInput, Float, Boolean>() {
                    public void invoke(GCControllerButtonInput button, Float value, Boolean pressed) {
                        l1Held = pressed != null && pressed.booleanValue();
                        if (l1Held) {
                            ensureCursor();
                            int[] xy = mapCursor();
                            AwtHost.injectWheel(xy[0], xy[1], 1);
                            lastPadZoomAt = System.currentTimeMillis();
                            startPadTick();
                        }
                    }
                });
        // L2 analog → zoom in (also treat digital press)
        pad.getLeftTrigger().setValueChangedHandler(
                new VoidBlock3<GCControllerButtonInput, Float, Boolean>() {
                    public void invoke(GCControllerButtonInput button, Float value, Boolean pressed) {
                        triggerL2 = value != null ? value.floatValue() : 0f;
                        startPadTick();
                    }
                });
        pad.getLeftTrigger().setPressedChangedHandler(
                new VoidBlock3<GCControllerButtonInput, Float, Boolean>() {
                    public void invoke(GCControllerButtonInput button, Float value, Boolean pressed) {
                        if (pressed != null && pressed.booleanValue()) {
                            ensureCursor();
                            int[] xy = mapCursor();
                            AwtHost.injectWheel(xy[0], xy[1], -1);
                            lastPadZoomAt = System.currentTimeMillis();
                            startPadTick();
                        }
                    }
                });
    }

    private void onPadClick(boolean left, boolean pressed) {
        ensureCursor();
        int[] xy = mapCursor();
        if (left) {
            if (pressed) {
                padLeftDown = true;
                AwtHost.injectMouse(MouseEvent.MOUSE_PRESSED, xy[0], xy[1], MouseEvent.BUTTON1, 1);
            } else if (padLeftDown) {
                padLeftDown = false;
                AwtHost.injectMouse(MouseEvent.MOUSE_RELEASED, xy[0], xy[1], MouseEvent.BUTTON1, 1);
                AwtHost.injectMouse(MouseEvent.MOUSE_CLICKED, xy[0], xy[1], MouseEvent.BUTTON1, 1);
            }
        } else {
            if (pressed) {
                padRightDown = true;
                System.out.println("void-osrs pad ○ right-press @ " + xy[0] + "," + xy[1]);
                AwtHost.injectMouse(MouseEvent.MOUSE_MOVED, xy[0], xy[1], 0, 0);
                AwtHost.injectMouse(MouseEvent.MOUSE_PRESSED, xy[0], xy[1], MouseEvent.BUTTON3, 1);
            } else if (padRightDown) {
                padRightDown = false;
                AwtHost.injectMouse(MouseEvent.MOUSE_RELEASED, xy[0], xy[1], MouseEvent.BUTTON3, 1);
                AwtHost.injectMouse(MouseEvent.MOUSE_CLICKED, xy[0], xy[1], MouseEvent.BUTTON3, 1);
            }
        }
        redrawCursor();
    }

    private void activatePad() {
        padActive = true;
        ensureCursor();
        startPadTick();
        redrawCursor();
    }

    private void deactivatePad() {
        if (!padActive && padController == null) {
            return;
        }
        if (padLeftDown || padRightDown) {
            int[] xy = mapCursor();
            if (padLeftDown) {
                AwtHost.injectMouse(MouseEvent.MOUSE_RELEASED, xy[0], xy[1], MouseEvent.BUTTON1, 1);
            }
            if (padRightDown) {
                AwtHost.injectMouse(MouseEvent.MOUSE_RELEASED, xy[0], xy[1], MouseEvent.BUTTON3, 1);
            }
        }
        clearPadHandlers(padController);
        padActive = false;
        padController = null;
        stickLX = 0f;
        stickLY = 0f;
        stickRX = 0f;
        stickRY = 0f;
        triggerL2 = 0f;
        l1Held = false;
        padLeftDown = false;
        padRightDown = false;
        stopPadTick();
        redrawCursor();
    }

    private void ensureCursor() {
        float w = Math.max(1f, (float) getBounds().getWidth());
        float h = Math.max(1f, (float) getBounds().getHeight());
        if (cursorVx < 0f || cursorVy < 0f) {
            cursorVx = w * 0.5f;
            cursorVy = h * 0.5f;
        } else {
            cursorVx = Math.max(0f, Math.min(w - 1f, cursorVx));
            cursorVy = Math.max(0f, Math.min(h - 1f, cursorVy));
        }
    }

    private int[] mapCursor() {
        return map(new CGPoint(cursorVx, cursorVy));
    }

    private void redrawCursor() {
        setPadCursor(cursorVx, cursorVy, padActive);
    }

    private void stopPadTick() {
        padTickGeneration++;
        padTickRunning = false;
    }

    private void startPadTick() {
        if (!padActive) {
            return;
        }
        if (padTickRunning) {
            return;
        }
        padTickRunning = true;
        final int gen = ++padTickGeneration;
        // First tick ASAP (mirrors Android Handler.post); then every PAD_TICK_MS.
        DispatchQueue.getMainQueue().async(new Runnable() {
            public void run() {
                runPadTick(gen);
            }
        });
    }

    private void runPadTick(final int gen) {
        if (gen != padTickGeneration) {
            return;
        }
        boolean keep = tickPad();
        if (!keep) {
            padTickRunning = false;
            return;
        }
        DispatchQueue.getMainQueue().after(PAD_TICK_MS, TimeUnit.MILLISECONDS, new Runnable() {
            public void run() {
                runPadTick(gen);
            }
        });
    }

    private boolean needsPadTick() {
        return Math.abs(stickLX) > PAD_DEADZONE
                || Math.abs(stickLY) > PAD_DEADZONE
                || Math.abs(stickRX) > PAD_DEADZONE
                || Math.abs(stickRY) > PAD_DEADZONE
                || triggerL2 > PAD_TRIGGER_THRESHOLD
                || l1Held;
    }

    private float dead(float v) {
        return Math.abs(v) < PAD_DEADZONE ? 0f : v;
    }

    private boolean tickPad() {
        if (!padActive) {
            return false;
        }
        ensureCursor();
        float lx = dead(stickLX);
        float ly = dead(stickLY);
        float rx = dead(stickRX);
        float ry = dead(stickRY);
        boolean moved = false;

        if (lx != 0f || ly != 0f) {
            float w = Math.max(1f, (float) getBounds().getWidth());
            float h = Math.max(1f, (float) getBounds().getHeight());
            cursorVx = Math.max(0f, Math.min(w - 1f, cursorVx + lx * PAD_CURSOR_SPEED));
            cursorVy = Math.max(0f, Math.min(h - 1f, cursorVy + ly * PAD_CURSOR_SPEED));
            int[] xy = mapCursor();
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

        boolean zoomIn = triggerL2 > PAD_TRIGGER_THRESHOLD;
        boolean zoomOut = l1Held;
        long now = System.currentTimeMillis();
        if ((zoomIn || zoomOut) && now - lastPadZoomAt >= PAD_ZOOM_INTERVAL_MS) {
            int[] xy = mapCursor();
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
            redrawCursor();
        }
        return needsPadTick();
    }

    public void setPadCursor(float x, float y, boolean visible) {
        cursorView.setHidden(!visible);
        if (!visible) {
            return;
        }
        cursorView.setFrame(new CGRect(x - CURSOR_HOT_X, y - CURSOR_HOT_Y, CURSOR_SIZE, CURSOR_SIZE));
        bringSubviewToFront(cursorView);
    }

    public void setSizeListener(Runnable listener) {
        this.sizeListener = listener;
    }

    @Override
    public void layoutSubviews() {
        super.layoutSubviews();
        CGRect bounds = getBounds();
        imageView.setFrame(bounds);
        bringSubviewToFront(cursorView);
        int w = Math.max(0, (int) Math.round(bounds.getWidth()));
        int h = Math.max(0, (int) Math.round(bounds.getHeight()));
        if (w <= 0 || h <= 0) {
            return;
        }
        if (cursorVx >= 0f) {
            cursorVx = Math.min(cursorVx, w - 1);
            cursorVy = Math.min(cursorVy, h - 1);
            if (padActive) {
                redrawCursor();
            }
        }
        pendingW = w;
        pendingH = h;
        // Boot path: need a real size immediately so Loader can start.
        if (appliedW == 0 || appliedH == 0) {
            applyDisplaySize(w, h);
            return;
        }
        if (w == appliedW && h == appliedH) {
            return;
        }
        // Still resizing — stretch pixels only; settle before client redraw.
        scheduleSettledResize();
    }

    /**
     * After {@link #RESIZE_SETTLE_MS} with no further size changes, push the
     * pending view size into the AWT / Jagex viewport so HUD / gameframe
     * reflow. Cancelled if the user keeps dragging.
     */
    private void scheduleSettledResize() {
        final int gen = ++resizeGeneration;
        DispatchQueue.getMainQueue().after(RESIZE_SETTLE_MS, TimeUnit.MILLISECONDS, new Runnable() {
            public void run() {
                if (gen != resizeGeneration) {
                    return;
                }
                if (pendingW <= 0 || pendingH <= 0) {
                    return;
                }
                if (pendingW == appliedW && pendingH == appliedH) {
                    return;
                }
                applyDisplaySize(pendingW, pendingH);
            }
        });
    }

    private void applyDisplaySize(int w, int h) {
        appliedW = w;
        appliedH = h;
        AwtHost.setDisplaySize(w, h);
        if (sizeListener != null) {
            sizeListener.run();
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
        DispatchQueue.getMainQueue().after(225, TimeUnit.MILLISECONDS, new Runnable() {
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
            AwtHost.requestHideSoftKeyboard("dev-console-close");
        } else {
            // Open console only — do not raise IME/chat. Tap later toggles keyboard if needed.
            AwtHost.setDevConsoleOpen(true);
        }
    }

    private void finishFourFingerGesture() {
        fourFingerConsoleFired = false;
        multiTouchMaxCount = 0;
    }

    @Override
    public void touchesBegan(NSSet<UITouch> touches, UIEvent event) {
        updateActive(touches, false);
        int count = active.size();
        if (count >= 2) {
            cancelLongPress();
            // Second finger: abort any in-progress left drag so BUTTON1 isn't stuck.
            if (dragging) {
                AwtHost.injectMouse(MouseEvent.MOUSE_RELEASED, downX, downY, MouseEvent.BUTTON1, 1);
            }
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
            CGPoint mid = midpoint();
            lastPanX = (float) mid.getX();
            lastPanY = (float) mid.getY();
            panTracked = true;
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
                CGPoint mid = midpoint();
                float mx = (float) mid.getX();
                float my = (float) mid.getY();
                if (panTracked) {
                    AwtHost.injectCameraOrbit(mx - lastPanX, my - lastPanY);
                }
                lastPanX = mx;
                lastPanY = my;
                panTracked = true;
                float dist = spacing();
                if (lastPinchDist > 0f) {
                    pinchAccum += dist - lastPinchDist;
                    while (pinchAccum >= PINCH_PX_PER_NOTCH) {
                        int[] midGame = map(mid);
                        AwtHost.injectWheel(midGame[0], midGame[1], -1);
                        pinchAccum -= PINCH_PX_PER_NOTCH;
                    }
                    while (pinchAccum <= -PINCH_PX_PER_NOTCH) {
                        int[] midGame = map(mid);
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
        int[] xy = map(p);
        float moved = Math.max(Math.abs((float) p.getX() - downVx), Math.abs((float) p.getY() - downVy));
        if (!dragging && moved > TOUCH_SLOP) {
            // Finger slid → left-button drag (same path as pad ✕ held + stick).
            dragging = true;
            cancelLongPress();
            AwtHost.injectMouse(MouseEvent.MOUSE_MOVED, downX, downY, 0, 0);
            AwtHost.injectMouse(MouseEvent.MOUSE_PRESSED, downX, downY, MouseEvent.BUTTON1, 1);
            AwtHost.injectMouse(MouseEvent.MOUSE_DRAGGED, xy[0], xy[1], MouseEvent.BUTTON1, 0);
        } else if (dragging) {
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
                panTracked = false;
                cancelLongPress();
                finishFourFingerGesture();
            } else {
                lastPinchDist = spacing();
                CGPoint mid = midpoint();
                lastPanX = (float) mid.getX();
                lastPanY = (float) mid.getY();
                panTracked = true;
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
            CGPoint p = firstPoint();
            int[] xy = map(p);
            AwtHost.injectMouse(MouseEvent.MOUSE_RELEASED, xy[0], xy[1], MouseEvent.BUTTON1, 1);
        } else if (!cancelled) {
            System.out.println("void-osrs tap left-click @ " + downX + "," + downY
                    + " frac=" + String.format(java.util.Locale.US, "%.3f,%.3f",
                        downX / (float) Math.max(1, frameW),
                        downY / (float) Math.max(1, frameH))
                    + " frame=" + frameW + "x" + frameH);
            AwtHost.injectLeftClick(downX, downY);
            // IME only from the --> write strip. History taps re-run in the client
            // and must not raise/hide the keyboard.
            if (AwtHost.isDevConsoleOpen()) {
                if (AwtHost.isConsolePromptTap(downX, downY)) {
                    AwtHost.requestToggleSoftKeyboard("dev-console-prompt");
                }
            } else if (AwtHost.SOFT_KEYBOARD_OPEN) {
                AwtHost.requestHideSoftKeyboard("tap-dismiss");
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
