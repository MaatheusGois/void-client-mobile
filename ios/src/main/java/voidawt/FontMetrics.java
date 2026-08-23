package voidawt;

public class FontMetrics {
    private final Font font;

    FontMetrics(Font font) {
        this.font = font == null ? new Font("Helvetica", Font.PLAIN, 12) : font;
    }

    public int charWidth(char c) {
        int size = Math.max(8, font.getSize());
        int glyphW = Math.max(5, size * 3 / 5);
        return glyphW + 1;
    }

    public int stringWidth(String s) {
        if (s == null || s.length() == 0) {
            return 1;
        }
        return Math.max(1, s.length() * charWidth('M'));
    }

    public int getAscent() {
        return Math.max(7, font.getSize());
    }

    public int getMaxAscent() {
        return getAscent();
    }

    public int getDescent() {
        return Math.max(1, font.getSize() / 4);
    }

    public int getMaxDescent() {
        return getDescent();
    }

    public int getHeight() {
        return getAscent() + getDescent() + 1;
    }
}
