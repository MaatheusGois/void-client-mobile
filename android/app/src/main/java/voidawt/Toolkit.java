package voidawt;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import voidawt.datatransfer.Clipboard;
import voidawt.image.BufferedImage;
import voidawt.image.ImageProducer;

public class Toolkit {
    private static final Toolkit INSTANCE = new Toolkit();
    private final Clipboard clipboard = new Clipboard();
    private final EventQueue eventQueue = new EventQueue();

    public static Toolkit getDefaultToolkit() {
        return INSTANCE;
    }

    public EventQueue getSystemEventQueue() {
        return eventQueue;
    }

    public Clipboard getSystemClipboard() {
        return clipboard;
    }

    public Dimension getScreenSize() {
        return new Dimension(AwtHost.GAME_WIDTH, AwtHost.GAME_HEIGHT);
    }

    public void sync() {
    }

    public Image createImage(byte[] data) {
        if (data == null) {
            return new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        }
        Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
        if (bitmap == null) {
            return new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        }
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        bitmap.getPixels(image.peekArgb(), 0, w, 0, 0, w, h);
        return image;
    }

    public Image createImage(ImageProducer producer) {
        if (producer == null) {
            return new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        }
        ProducerImage image = new ProducerImage();
        producer.startProduction(image);
        return image.image != null ? image.image : new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
    }

    public Cursor createCustomCursor(Image cursor, Point hotSpot, String name) {
        return new Cursor();
    }

    public void beep() {
    }

    private static final class ProducerImage implements voidawt.image.ImageConsumer {
        BufferedImage image;

        public void setDimensions(int width, int height) {
            image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        }

        public void setProperties(java.util.Hashtable<?, ?> props) {
        }

        public void setColorModel(voidawt.image.ColorModel model) {
        }

        public void setHints(int hintflags) {
        }

        public void setPixels(int x, int y, int w, int h, voidawt.image.ColorModel model, byte[] pixels, int off, int scansize) {
        }

        public void setPixels(int x, int y, int w, int h, voidawt.image.ColorModel model, int[] pixels, int off, int scansize) {
            if (image == null) {
                setDimensions(x + w, y + h);
            }
            image.setRGB(x, y, w, h, pixels, off, scansize);
        }

        public void imageComplete(int status) {
        }
    }
}
