package world.gregs.voidosrs.ios;

import org.robovm.apple.coregraphics.CGBitmapContext;
import org.robovm.apple.coregraphics.CGBitmapInfo;
import org.robovm.apple.coregraphics.CGColorRenderingIntent;
import org.robovm.apple.coregraphics.CGColorSpace;
import org.robovm.apple.coregraphics.CGDataProvider;
import org.robovm.apple.coregraphics.CGImage;
import org.robovm.apple.coregraphics.CGImageAlphaInfo;
import org.robovm.apple.coregraphics.CGRect;
import org.robovm.apple.foundation.NSData;
import org.robovm.apple.uikit.UIImage;

public final class ArgbBridge {
    private ArgbBridge() {
    }

    public static UIImage toImage(int[] argb, int w, int h) {
        byte[] rgba = new byte[w * h * 4];
        int n = Math.min(argb.length, w * h);
        for (int i = 0; i < n; i++) {
            int p = argb[i];
            int a = (p >>> 24) & 0xff;
            if (a == 0) {
                a = 0xff;
            }
            rgba[i * 4] = (byte) ((p >> 16) & 0xff);
            rgba[i * 4 + 1] = (byte) ((p >> 8) & 0xff);
            rgba[i * 4 + 2] = (byte) (p & 0xff);
            rgba[i * 4 + 3] = (byte) a;
        }
        NSData data = new NSData(rgba);
        CGDataProvider provider = CGDataProvider.create(data);
        CGColorSpace space = CGColorSpace.createDeviceRGB();
        CGBitmapInfo info = new CGBitmapInfo(CGImageAlphaInfo.Last.value() | CGBitmapInfo.ByteOrder32Big.value());
        CGImage cg = CGImage.create(w, h, 8, 32, w * 4L, space, info, provider, null, false, CGColorRenderingIntent.Default);
        return new UIImage(cg);
    }

    public static void copy(UIImage image, int[] dest, int w, int h) {
        CGImage cg = image.getCGImage();
        if (cg == null || dest == null) {
            return;
        }
        byte[] rgba = new byte[w * h * 4];
        CGColorSpace space = CGColorSpace.createDeviceRGB();
        CGBitmapContext ctx = CGBitmapContext.create(rgba, w, h, 8, w * 4L, space, CGImageAlphaInfo.PremultipliedLast);
        ctx.clearRect(new CGRect(0, 0, w, h));
        ctx.drawImage(new CGRect(0, 0, w, h), cg);
        int n = Math.min(dest.length, w * h);
        for (int i = 0; i < n; i++) {
            int o = i * 4;
            int r = rgba[o] & 0xff;
            int g = rgba[o + 1] & 0xff;
            int b = rgba[o + 2] & 0xff;
            dest[i] = 0xff000000 | (r << 16) | (g << 8) | b;
        }
    }
}
