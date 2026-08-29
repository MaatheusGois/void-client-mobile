/**
 * Mobile soft-keyboard bridge. Desktop no-ops; Android/iOS AwtHost picks this up via reflection.
 */
final class MobileKeyboard {
    private static int insetPx;
    private static int viewH = 1;
    private static int lastLoggedShift = -1;
    /** Unlifted screen Y/H of the text field that opened the IME. */
    private static int focusY;
    private static int focusH;
    /** Cached: client jar is shared; iOS host class is only on the RoboVM classpath. */
    private static boolean iosHost;
    private static boolean iosHostKnown;

    private MobileKeyboard() {
    }

    /** Called from the native host when the IME overlap changes (view pixels). */
    public static void setInset(int px, int viewHeight) {
        insetPx = px < 0 ? 0 : px;
        viewH = viewHeight < 1 ? 1 : viewHeight;
        if (insetPx == 0) {
            lastLoggedShift = -1;
        }
        System.out.println("void-osrs keyboard inset=" + insetPx + " viewH=" + viewH
                + " shift=" + shiftY());
    }

    /** Called when the player presses a UI component (mouse-down). {@code screenY} is unlifted. */
    static void onInterfacePress(DisplayModeManagerContainer57 component, int screenX, int screenY) {
        if (component == null) {
            return;
        }
        boolean keyListener = component.anObjectArray822 != null;
        boolean mouseDown = component.anObjectArray763 != null;
        int type = component.type;
        String text = component.textContent;
        if (text != null && text.length() > 48) {
            text = text.substring(0, 48);
        }
        int gameState = Component49.clientState;
        System.out.println("void-osrs ifPress id=" + component.packedId
                + " type=" + type
                + " keyListener=" + keyListener
                + " mouseDown=" + mouseDown
                + " state=" + gameState
                + " size=" + component.width + "x" + component.height
                + " xy=" + screenX + "," + screenY
                + " text=[" + text + "]");

        // Login root (type 0, 800x600) has a keyListener — must NOT open IME.
        // Real inputs are type-4 single-line text (login ~27px, chat ~17px).
        if (!isTextInput(component)) {
            return;
        }
        focusY = screenY;
        focusH = component.height;
        if (keyListener) {
            requestShow("keyListener id=" + component.packedId);
            return;
        }
        if (mouseDown && isLoginState(gameState)) {
            requestShow("loginText id=" + component.packedId + " state=" + gameState);
        }
    }

    /** Single-line text widget suitable for soft-keyboard focus. */
    private static boolean isTextInput(DisplayModeManagerContainer57 c) {
        if (c.type != 4 || isFullscreen(c)) {
            return false;
        }
        int h = c.height;
        return h > 0 && h <= 48;
    }

    /**
     * Pixels to subtract from this widget's screen Y (chatbox layer only).
     * Applied in draw + input so children follow via parent offset.
     * Only the gameframe slot (parent fullscreen) is lifted — lifting a nested
     * 137 inside an unmoved clip makes the chat draw off-clip and vanish.
     */
    static int liftPx(DisplayModeManagerContainer57 c, int screenX, int screenY) {
        // Dev console sits at the top — never shove chat up while it's open.
        if (StringCache.devConsoleOpen) {
            return 0;
        }
        int shift = shiftY();
        if (shift <= 0 || c == null || isLoginState(Component49.clientState)) {
            return 0;
        }
        DisplayModeManagerContainer57 parent = c.parent;
        if (parent != null && !isFullscreen(parent)) {
            return 0;
        }
        if (!inChatBand(c, screenX, screenY)) {
            return 0;
        }
        int maxLift = Math.max(0, screenY - 4);
        int lift = shift < maxLift ? shift : maxLift;
        if (lift != lastLoggedShift) {
            lastLoggedShift = lift;
            System.out.println("void-osrs chatLift shift=" + lift
                    + " kb=" + shift
                    + " id=" + c.packedId
                    + " xy=" + screenX + "," + screenY
                    + " size=" + c.width + "x" + c.height
                    + " state=" + Component49.clientState);
        }
        return lift;
    }

    /**
     * Login interface 744 — lift just enough that the focused field sits a bit
     * above the IME, not the full keyboard height.
     */
    static int loginLayerShift() {
        if (!isLoginState(Component49.clientState)) {
            return 0;
        }
        int kb = shiftY();
        if (kb <= 0 || focusH <= 0) {
            return 0;
        }
        int gh = GlToolkitSub2.anInt7666;
        if (gh <= 0) {
            gh = 503;
        }
        int kbTop = gh - kb;
        // Android: 12px gap above IME. iOS keyboard frame is short vs the
        // predictive bar / home indicator, so keep at least +30 extra.
        int pad = 12;
        if (isIosHost()) {
            pad += 30;
        }
        int need = focusY + focusH + pad - kbTop;
        if (need <= 0) {
            return 0;
        }
        if (need > kb) {
            need = kb;
        }
        if (need > focusY) {
            need = Math.max(0, focusY);
        }
        return need;
    }

    /** Keep 744 hit-testing aligned with {@link #loginLayerShift()} draw offset. */
    static int loginHitShift(DisplayModeManagerContainer57 c) {
        if (c == null || (c.packedId >>> 16) != 744) {
            return 0;
        }
        return loginLayerShift();
    }

    private static boolean inChatBand(DisplayModeManagerContainer57 c, int screenX, int screenY) {
        int sw = Math.max(1, DisplayModeManagerContainer23.anInt1524);
        int sh = Math.max(1, GlToolkitSub2.anInt7666);
        if (isFullscreen(c)) {
            return false;
        }
        int h = c.height;
        int w = c.width;
        if (h > sh * 45 / 100 || h < 40) {
            return false;
        }
        if (screenX > sw / 2) {
            return false;
        }
        if (screenY + h < sh * 70 / 100) {
            return false;
        }
        int iface = c.packedId >>> 16;
        if (iface == 137) {
            return true;
        }
        return w >= 120;
    }

    private static boolean isFullscreen(DisplayModeManagerContainer57 c) {
        int sw = Math.max(1, DisplayModeManagerContainer23.anInt1524);
        int sh = Math.max(1, GlToolkitSub2.anInt7666);
        return c.width >= sw - 8 && c.height >= sh - 8;
    }

    /** Title / login / lobby — not the in-game world (state 10). */
    private static boolean isLoginState(int gameState) {
        return gameState == 0 || gameState == 3 || gameState == 7;
    }

    private static boolean isIosHost() {
        if (!iosHostKnown) {
            iosHostKnown = true;
            try {
                Class.forName("world.gregs.voidosrs.ios.GameController");
                iosHost = true;
            } catch (Throwable ignored) {
            }
        }
        return iosHost;
    }

    /**
     * How many canvas pixels the soft keyboard covers from the bottom.
     * Unlike {@link #shiftY()}, this is <b>not</b> capped — used to size the
     * purple developer console so its bottom sits just above the IME.
     */
    static int imeCoverCanvasPx() {
        int px = insetPx;
        int vh = viewH;
        try {
            Class<?> host = Class.forName("voidawt.AwtHost");
            px = host.getField("KEYBOARD_INSET_PX").getInt(null);
            int hostH = host.getField("VIEW_HEIGHT_PX").getInt(null);
            if (hostH > 1) {
                vh = hostH;
            }
        } catch (Throwable ignored) {
        }
        if (px <= 0 || vh < 32) {
            return 0;
        }
        int canvasH = PacketReader.anInt10432;
        if (canvasH <= 0) {
            canvasH = GlToolkitSub2.anInt7666;
        }
        if (canvasH <= 0) {
            return 0;
        }
        int cover = px * canvasH / vh;
        if (cover > canvasH - 8) {
            cover = canvasH - 8;
        }
        return cover;
    }

    private static int shiftY() {
        int px = insetPx;
        int vh = viewH;
        try {
            Class<?> host = Class.forName("voidawt.AwtHost");
            px = host.getField("KEYBOARD_INSET_PX").getInt(null);
            int hostH = host.getField("VIEW_HEIGHT_PX").getInt(null);
            if (hostH > 1) {
                vh = hostH;
            }
        } catch (Throwable ignored) {
        }
        if (px <= 0 || vh < 32) {
            return 0;
        }
        int gh = GlToolkitSub2.anInt7666;
        if (gh <= 0) {
            gh = 503;
        }
        int shift = px * gh / vh;
        int cap = gh * 50 / 100;
        if (shift > cap) {
            shift = cap;
        }
        return shift;
    }

    static void requestShow(String reason) {
        System.out.println("void-osrs keyboard REQUEST show (" + reason + ")");
        try {
            Class.forName("voidawt.AwtHost")
                    .getMethod("requestSoftKeyboard", String.class)
                    .invoke(null, reason);
        } catch (Throwable ignored) {
        }
    }

    static void requestHide(String reason) {
        System.out.println("void-osrs keyboard REQUEST hide (" + reason + ")");
        try {
            Class.forName("voidawt.AwtHost")
                    .getMethod("requestHideSoftKeyboard", String.class)
                    .invoke(null, reason);
        } catch (Throwable ignored) {
        }
    }
}
