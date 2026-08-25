package voidawt;

import org.robovm.apple.coretext.CTLine;
import org.robovm.apple.uikit.UIColor;
import org.robovm.apple.uikit.UIFont;

public class FontMetrics {
    private final Font font;
    private final UIFont uiFont;

    FontMetrics(Font font) {
        this.font = font == null ? new Font("Helvetica", Font.PLAIN, 12) : font;
        this.uiFont = this.font.uiFont();
    }

    public int charWidth(char c) {
        return stringWidth(String.valueOf(c));
    }

    public int stringWidth(String s) {
        if (s == null || s.length() == 0) {
            return 1;
        }
        CTLine line = CTLine.create(font.attributed(s, UIColor.white()));
        return Math.max(1, (int) Math.ceil(line.getWidth()));
    }

    public int getAscent() {
        return Math.max(1, (int) Math.ceil(uiFont.getAscender()));
    }

    public int getMaxAscent() {
        return getAscent();
    }

    public int getDescent() {
        // UIKit descender is negative.
        return Math.max(1, (int) Math.ceil(-uiFont.getDescender()));
    }

    public int getMaxDescent() {
        return getDescent();
    }

    public int getHeight() {
        return Math.max(getAscent() + getDescent(), (int) Math.ceil(uiFont.getLineHeight()));
    }
}
