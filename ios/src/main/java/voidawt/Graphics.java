package voidawt;

import voidawt.image.BufferedImage;
import voidawt.image.ImageObserver;

public class Graphics {
    private final BufferedImage target;
    private Color color = Color.black;
    private Font font = new Font("Helvetica", Font.PLAIN, 12);
    private Shape clip;
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

    public void drawString(String str, int x, int y) {
        if (str == null || str.length() == 0) {
            return;
        }
        int[] px = target.peekArgb();
        int tw = target.getWidth();
        int th = target.getHeight();
        int rgb = color.getRGB();
        int size = Math.max(8, font.getSize());
        int glyphH = Math.max(7, size);
        int glyphW = Math.max(5, size * 3 / 5);
        int baseline = y;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            BitmapFont.blit(px, tw, th, x + i * (glyphW + 1), baseline - glyphH + 1, ch, rgb, glyphW, glyphH);
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
