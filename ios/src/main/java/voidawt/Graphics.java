package voidawt;

import org.robovm.apple.coregraphics.CGAffineTransform;
import org.robovm.apple.coregraphics.CGBitmapContext;
import org.robovm.apple.coregraphics.CGColorSpace;
import org.robovm.apple.coregraphics.CGImageAlphaInfo;
import org.robovm.apple.coregraphics.CGRect;
import org.robovm.apple.coretext.CTLine;
import org.robovm.apple.foundation.NSAttributedString;
import org.robovm.apple.uikit.UIColor;
import org.robovm.apple.uikit.UIFont;

import voidawt.image.BufferedImage;
import voidawt.image.ImageObserver;

/**
 * Software AWT {@code Graphics} for the iOS host.
 * <p>
 * Draws into a {@link BufferedImage} {@code int[]} ARGB buffer. The canvas path
 * sets {@code presentOnDraw} so {@link #drawImage} pushes frames via
 * {@link AwtHost#present}. Splash loading UI ({@code HelveticaFont}) and AWT glyph
 * baking ({@code FontGlyphCache}) both go through {@link #drawString}.
 */
public class Graphics {
    private final BufferedImage target;
    private Color color = Color.black;
    private Font font = new Font("Helvetica", Font.PLAIN, 12);
    private Shape clip;
    /** When true, {@link #drawImage} presents the buffer (game canvas backbuffer). */
    private final boolean presentOnDraw;

    public Graphics(BufferedImage target) {
        this(target, false);
    }

    public Graphics(BufferedImage target, boolean presentOnDraw) {
        this.target = target;
        this.presentOnDraw = presentOnDraw;
        this.clip = new Rectangle(0, 0, target.getWidth(), target.getHeight());
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color == null ? Color.black : color;
    }

    public void setFont(Font font) {
        if (font != null) {
            this.font = font;
        }
    }

    public Font getFont() {
        return font;
    }

    public Shape getClip() {
        return clip;
    }

    public Rectangle getClipBounds() {
        return clip == null ? null : clip.getBounds();
    }

    public void setClip(Shape clip) {
        this.clip = clip;
    }

    public void setClip(int x, int y, int width, int height) {
        this.clip = new Rectangle(x, y, width, height);
    }

    public void fillRect(int x, int y, int w, int h) {
        int[] px = target.peekArgb();
        int tw = target.getWidth();
        int th = target.getHeight();
        int rgb = color.getRGB();
        int x1 = Math.max(0, x);
        int y1 = Math.max(0, y);
        int x2 = Math.min(tw, x + w);
        int y2 = Math.min(th, y + h);
        for (int yy = y1; yy < y2; yy++) {
            int row = yy * tw;
            for (int xx = x1; xx < x2; xx++) {
                px[row + xx] = rgb;
            }
        }
    }

    public void drawRect(int x, int y, int w, int h) {
        fillRect(x, y, w, 1);
        fillRect(x, y + h, w, 1);
        fillRect(x, y, 1, h);
        fillRect(x + w, y, 1, h);
    }

    /**
     * Rasterize {@code str} with CoreText into a tight glyph bitmap, then blit
     * non-empty pixels into the ARGB target.
     * <p>
     * AWT {@code drawString(x,y)} treats {@code y} as the <em>baseline</em>.
     * We flip the CGBitmapContext CTM so CoreText's +y-up matches the buffer's
     * top-left layout — without that flip the splash text renders upside-down.
     * Android does the equivalent with {@code Paint}/{@code Canvas.drawText}.
     * <p>
     * Callers: splash progress ({@code HelveticaFont}), and {@code FontGlyphCache} glyph bake
     * (black fill + white string + {@code PixelGrabber}).
     */
    public void drawString(String str, int x, int y) {
        if (str == null || str.length() == 0) {
            return;
        }
        UIFont uiFont = font.uiFont();
        UIColor uiColor = UIColor.fromRGBA(
                color.getRed() / 255.0,
                color.getGreen() / 255.0,
                color.getBlue() / 255.0,
                1.0);
        NSAttributedString attributed = font.attributed(str, uiColor);
        CTLine line = CTLine.create(attributed);
        double ascent = Math.max(uiFont.getAscender(), line.getAscent());
        double descent = Math.max(-uiFont.getDescender(), line.getDescent());
        double width = Math.max(1.0, line.getWidth());
        int pad = 2;
        int bw = Math.max(1, (int) Math.ceil(width) + pad * 2);
        int bh = Math.max(1, (int) Math.ceil(ascent + descent) + pad * 2);
        byte[] rgba = new byte[bw * bh * 4];
        CGColorSpace space = CGColorSpace.createDeviceRGB();
        CGBitmapContext ctx = CGBitmapContext.create(
                rgba, bw, bh, 8, bw * 4L, space, CGImageAlphaInfo.PremultipliedLast);
        if (ctx == null) {
            return;
        }
        ctx.clearRect(new CGRect(0, 0, bw, bh));
        // Top-left bitmap ↔ CoreText +y-up: flip CTM, identity text matrix, baseline at pad+descent.
        ctx.translateCTM(0, bh);
        ctx.scaleCTM(1, -1);
        ctx.setTextMatrix(CGAffineTransform.Identity());
        ctx.setTextPosition(pad, pad + descent);
        line.draw(ctx);

        int[] dst = target.peekArgb();
        int tw = target.getWidth();
        int th = target.getHeight();
        int dx0 = x - pad;
        // AWT baseline at y → top of glyph box is roughly y - ascent.
        int dy0 = y - (int) Math.ceil(ascent) - pad;
        for (int row = 0; row < bh; row++) {
            int dy = dy0 + row;
            if (dy < 0 || dy >= th) {
                continue;
            }
            int dyOff = dy * tw;
            int srcRow = row * bw * 4;
            for (int col = 0; col < bw; col++) {
                int o = srcRow + col * 4;
                int a = rgba[o + 3] & 0xff;
                if (a == 0) {
                    continue;
                }
                int dx = dx0 + col;
                if (dx < 0 || dx >= tw) {
                    continue;
                }
                int r = rgba[o] & 0xff;
                int g = rgba[o + 1] & 0xff;
                int b = rgba[o + 2] & 0xff;
                // PremultipliedLast RGBA → ARGB; skip fully transparent (keeps black under glyphs).
                dst[dyOff + dx] = (a << 24) | (r << 16) | (g << 8) | b;
            }
        }
    }

    public boolean drawImage(Image img, int x, int y, ImageObserver observer) {
        if (img == null) {
            return false;
        }
        int[] src = img.peekArgb();
        int sw = img.getWidth(observer);
        int sh = img.getHeight(observer);
        if (src == null || sw <= 0 || sh <= 0) {
            return false;
        }
        int[] dst = target.peekArgb();
        int tw = target.getWidth();
        int th = target.getHeight();
        for (int row = 0; row < sh; row++) {
            int dy = y + row;
            if (dy < 0 || dy >= th) {
                continue;
            }
            int sy = row * sw;
            int dyOff = dy * tw;
            for (int col = 0; col < sw; col++) {
                int dx = x + col;
                if (dx < 0 || dx >= tw) {
                    continue;
                }
                dst[dyOff + dx] = src[sy + col];
            }
        }
        if (presentOnDraw) {
            AwtHost.present(dst, tw, th);
        }
        return true;
    }

    public boolean drawImage(Image img, int x, int y, int width, int height, ImageObserver observer) {
        return drawImage(img, x, y, observer);
    }

    public void clipRect(int x, int y, int width, int height) {
        setClip(x, y, width, height);
    }

    public void clearRect(int x, int y, int w, int h) {
        Color old = color;
        color = Color.black;
        fillRect(x, y, w, h);
        color = old;
    }
}
