package voidawt;

public class Frame extends Window {
    private String title = "";

    public Frame() {
    }

    public Frame(String title) {
        this.title = title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setResizable(boolean resizable) {
    }

    public void setUndecorated(boolean undecorated) {
    }

    public void setIconImages(java.util.List<? extends Image> icons) {
    }
}
