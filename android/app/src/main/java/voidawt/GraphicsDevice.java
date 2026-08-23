package voidawt;

public class GraphicsDevice {
    private boolean valid = true;
    private DisplayMode mode = new DisplayMode(AwtHost.GAME_WIDTH, AwtHost.GAME_HEIGHT, 32, 60);

    public boolean isFullScreenSupported() {
        return true;
    }

    public DisplayMode getDisplayMode() {
        return mode;
    }

    public void setDisplayMode(DisplayMode dm) {
        if (dm != null) {
            mode = dm;
        }
    }

    public DisplayMode[] getDisplayModes() {
        return new DisplayMode[]{mode};
    }

    public void setFullScreenWindow(Window w) {
    }

    public Window getFullScreenWindow() {
        return null;
    }
}
