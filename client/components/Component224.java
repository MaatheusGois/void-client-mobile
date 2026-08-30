/* Component224 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

final class Component224
/**
 * RENAMED from `Class273` (JODE-obfuscated).
 * Evidence: root class; no distinctive extends/strings
 */ implements Interface21 {
    static int[] anIntArray5165 = {-1, -1, 1, 1};
    static int anInt5166;
    /** Packed type/opcode id for this constant. */
    int id;
    static int anInt5168;
    static Component75 aClass173_5169 = new Component75();

    public static void decodedOperation2055(int i) {
        anIntArray5165 = null;
        aClass173_5169 = null;
        if (i != 1) anIntArray5165 = null;
    }

    static final boolean decodedOperation2056(int i, int i_0_, int i_1_) {
        if (i_0_ < 80) decodedOperation2055(17);
        anInt5168++;
        if (!Component334.decodedOperation1193(i_1_, i, true)) return false;
        if (DisplayModeManagerContainer145.decodedOperation1087(12644, i_1_, i) | (i & 0xb000) != 0 | DisplayModeManagerContainer347.decodedOperation2075(i, -128, i_1_)) return true;
        return ((ClientErrorReporter.hasCollisionBlockFlags(i_1_, i, -128) | Component175.decodedOperation1722(i, i_1_, (byte) 92)) & (0x37 & i_1_) == 0);
    }

    /** {@code i | i_2_}. */
    static int bitwiseOr(int i, int i_2_) {
        return i | i_2_;
    }

    public final String toString() {
        anInt5166++;
        throw new IllegalStateException();
    }

    Component224(String string, int i) {
        this.id = i;
    }
}
