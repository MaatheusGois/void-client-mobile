package voidawt;

public class Font {
    public static final int PLAIN = 0;
    public static final int BOLD = 1;
    public static final int ITALIC = 2;

    final String name;
    final int style;
    final int size;

    public Font(String name, int style, int size) {
        this.name = name;
        this.style = style;
        this.size = Math.max(1, size);
    }

    public int getSize() {
        return size;
    }

    public int getStyle() {
        return style;
    }
}
