/**
 * Mobile VirtualMouse backend — reflects into {@code voidawt.AwtHost}.
 * Event ids match AWT/voidawt: CLICKED=500, PRESSED=501, RELEASED=502, MOVED=503.
 */
final class VoidAwtHostMouse implements MicrobotMouseBackend {

    private final java.lang.reflect.Method injectMouse;

    VoidAwtHostMouse(Class hostClass) throws Exception {
        injectMouse = hostClass.getMethod("injectMouse", int.class, int.class, int.class, int.class, int.class);
    }

    static VoidAwtHostMouse tryCreate() {
        try {
            Class c = Class.forName("voidawt.AwtHost");
            return new VoidAwtHostMouse(c);
        } catch (Throwable t) {
            return null;
        }
    }

    public void mouseMoved(int x, int y) {
        invoke(503, x, y, 0, 0);
    }

    public void mousePressed(int x, int y, int button) {
        invoke(501, x, y, button, 1);
    }

    public void mouseReleased(int x, int y, int button) {
        invoke(502, x, y, button, 1);
    }

    public void mouseClicked(int x, int y, int button) {
        invoke(500, x, y, button, 1);
    }

    public int canvasWidth() {
        return Class321.anInt4017 > 0 ? Class321.anInt4017 : 765;
    }

    public int canvasHeight() {
        return Class348_Sub42_Sub8_Sub2.anInt10432 > 0 ? Class348_Sub42_Sub8_Sub2.anInt10432 : 503;
    }

    private void invoke(int id, int x, int y, int button, int clickCount) {
        try {
            injectMouse.invoke(null, Integer.valueOf(id), Integer.valueOf(x), Integer.valueOf(y),
                    Integer.valueOf(button), Integer.valueOf(clickCount));
        } catch (Throwable t) {
            System.out.println("microbot VoidAwtHostMouse inject failed: " + t.getMessage());
        }
    }
}
