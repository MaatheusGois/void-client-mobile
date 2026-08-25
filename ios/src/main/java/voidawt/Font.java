package voidawt;

import org.robovm.apple.foundation.NSAttributedString;
import org.robovm.apple.uikit.NSAttributedStringAttributes;
import org.robovm.apple.uikit.UIColor;
import org.robovm.apple.uikit.UIFont;

public class Font {
    public static final int PLAIN = 0;
    public static final int BOLD = 1;
    public static final int ITALIC = 2;

    final String name;
    final int style;
    final int size;
    private UIFont uiFont;

    public Font(String name, int style, int size) {
        this.name = name == null ? "Helvetica" : name;
        this.style = style;
        this.size = Math.max(1, size);
    }

    public int getSize() {
        return size;
    }

    public int getStyle() {
        return style;
    }

    UIFont uiFont() {
        if (uiFont != null) {
            return uiFont;
        }
        double pt = size;
        UIFont resolved = null;
        boolean bold = (style & BOLD) != 0;
        boolean italic = (style & ITALIC) != 0;
        if (bold && italic) {
            resolved = UIFont.getFont(name + "-BoldOblique", pt);
            if (resolved == null) {
                resolved = UIFont.getFont(name + "-BoldItalic", pt);
            }
        } else if (bold) {
            resolved = UIFont.getFont(name + "-Bold", pt);
            if (resolved == null) {
                resolved = UIFont.getBoldSystemFont(pt);
            }
        } else if (italic) {
            resolved = UIFont.getFont(name + "-Oblique", pt);
            if (resolved == null) {
                resolved = UIFont.getFont(name + "-Italic", pt);
            }
            if (resolved == null) {
                resolved = UIFont.getItalicSystemFont(pt);
            }
        } else {
            resolved = UIFont.getFont(name, pt);
        }
        if (resolved == null) {
            resolved = bold ? UIFont.getBoldSystemFont(pt) : UIFont.getSystemFont(pt);
        }
        uiFont = resolved;
        return uiFont;
    }

    NSAttributedString attributed(String text, UIColor color) {
        NSAttributedStringAttributes attrs = new NSAttributedStringAttributes();
        attrs.setFont(uiFont());
        attrs.setForegroundColor(color == null ? UIColor.white() : color);
        return new NSAttributedString(text == null ? "" : text, attrs);
    }
}
