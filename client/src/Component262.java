/* Component262 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

import java.io.IOException;

final class Component262
/**
 * RENAMED from `Class116` (JODE-obfuscated).
 * Evidence: root class; no distinctive extends/strings
 */ {
    static int anInt1758;
    static boolean shiftClick = true;
    static int anInt1760;
    static String aString1761;
    static Component183 aClass114_1762;

    public static void method1062(byte i) {
        if (i == 35) {
            aClass114_1762 = null;
            aString1761 = null;
        }
    }

    static final void method1063(int i) {
        if (i != 1) aString1761 = null;
        anInt1760++;
        RandomAccessFileReader class234 = null;
        try {
            Task class144 = OggUrlStream.aClass297_8992.method2233((byte) -46, "2", true);
            while (class144.anInt1997 == 0) SpriteAtlasShader.method2161((byte) 63, 1L);
            if (class144.anInt1997 == 1) {
                class234 = (RandomAccessFileReader) class144.anObject1998;
                byte[] is = new byte[(int) class234.method1662((byte) -46)];
                int i_0_;
                for (int i_1_ = 0; is.length > i_1_; i_1_ += i_0_) {
                    i_0_ = class234.method1656(is, i_1_, (byte) -12, is.length + -i_1_);
                    if (i_0_ == -1) throw new IOException("EOF");
                }
                Sprite.method3014(new Buffer(is), (byte) -40);
            }
        } catch (Exception exception) {
            /* empty */
        }
        do {
            try {
                if (class234 == null) break;
                class234.method1657(false);
            } catch (Exception exception) {
                break;
            }
            break;
        } while (false);
    }

    static {
        anInt1758 = 0;
        aClass114_1762 = new Component183(50, -1);
    }
}
