/* AbstractBuffer - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

/**
 * RENAMED from `Class344` (JODE-obfuscated).
 * Abstract base for byte buffers. Declares abstract read/write (method2689/2691/2692). Subclass ByteBufferReader wraps a java.nio.ByteBuffer.
 */

abstract class AbstractBuffer {
    static StringCache aClass351_4262 = new StringCache(34, 15);
    static int anInt4263 = 2;
    static StringCache aClass351_4264 = new StringCache(40, 12);
    static int anInt4265 = 0;
    static StringCache aClass351_4266 = new StringCache(72, 16);
    static int anInt4267;

    abstract byte[] method2689(int i, int i_0_, int i_1_);

    public static void method2690(int i) {
        aClass351_4266 = null;
        aClass351_4264 = null;
        aClass351_4262 = null;
        int i_2_ = -29 / ((i - -54) / 45);
    }

    abstract void method2691(byte i, byte[] is);

    abstract byte[] method2692(int i);
}
