/**
 * Mobile soft-keyboard bridge. Desktop no-ops; Android/iOS AwtHost picks this up via reflection.
 */
final class MobileKeyboard {
    private MobileKeyboard() {
    }

    /** Called when the player presses a UI component (mouse-down). */
    static void onInterfacePress(Class46 component) {
        if (component == null) {
            return;
        }
        boolean keyListener = component.anObjectArray822 != null;
        boolean mouseDown = component.anObjectArray763 != null;
        int type = component.anInt774;
        String text = component.aString792;
        if (text != null && text.length() > 48) {
            text = text.substring(0, 48);
        }
        int gameState = Class240.anInt4674;
        System.out.println("void-osrs ifPress id=" + component.anInt830
                + " type=" + type
                + " keyListener=" + keyListener
                + " mouseDown=" + mouseDown
                + " state=" + gameState
                + " size=" + component.anInt709 + "x" + component.anInt789
                + " text=[" + text + "]");

        // Text field with key script, or any key-listening widget the user pressed.
        if (keyListener) {
            requestShow("keyListener id=" + component.anInt830);
            return;
        }
        // Login / lobby text widgets (type 4) that accept clicks.
        if (type == 4 && mouseDown && (gameState == 3 || gameState == 7 || gameState == 0)) {
            requestShow("loginText id=" + component.anInt830 + " state=" + gameState);
        }
    }

    static void requestShow(String reason) {
        System.out.println("void-osrs keyboard REQUEST show (" + reason + ")");
        try {
            Class.forName("voidawt.AwtHost")
                    .getMethod("requestSoftKeyboard", String.class)
                    .invoke(null, reason);
        } catch (Throwable ignored) {
            // Desktop / missing host — ignore.
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
