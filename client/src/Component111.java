/* Component111 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

final class Component111
/**
 * RENAMED from `Class251` (JODE-obfuscated).
 * Evidence: root class; no distinctive extends/strings
 */ {
    static int anInt3229;
    static int anInt3230;
    static int anInt3231;
    static StringCache aClass351_3232 = new StringCache(51, 3);
    static int anInt3233;
    static int anInt3234 = 0;
    static int anInt3235;
    static int anInt3236;

    public static void method1912(int i) {
        if (i == 8549) aClass351_3232 = null;
    }

    static final void method1913(boolean bool, int i, DisplayModeManagerContainer57 class46) {
        anInt3235++;
        int i_0_ = -40 % ((-35 - i) / 51);
        int i_1_ = (class46.anInt698 == 0 ? class46.anInt709 : class46.anInt698);
        int i_2_ = (class46.anInt791 != 0 ? class46.anInt791 : class46.anInt789);
        AbstractShaderSub1.method3534(false, class46.anInt830, i_1_, bool, i_2_, (DefinitionSub33.aClass46ArrayArray9427[(class46.anInt830 >> 16)]));
        if (class46.aClass46Array798 != null) AbstractShaderSub1.method3534(false, class46.anInt830, i_1_, bool, i_2_, class46.aClass46Array798);
        NodeSub41 class348_sub41 = ((NodeSub41) Component15.aClass356_4915.method3480(class46.anInt830, -6008));
        if (class348_sub41 != null) Component272.method1728(i_2_, -1, (class348_sub41.anInt7050), bool, i_1_);
    }

    static final int method1914(int i, int i_3_) {
        anInt3231++;
        if (i != -23590) method1913(false, -115, null);
        return i_3_ & 0xff;
    }

    public final String toString() {
        anInt3230++;
        throw new IllegalStateException();
    }

    static final boolean method1915(byte i, int i_4_) {
        if (i != 4) return false;
        anInt3233++;
        return i_4_ != 1 && i_4_ != 7;
    }

    static final void method1916(int i, DisplayModeManagerContainer57 class46) {
        anInt3229++;
        if (class46.anInt794 == Component255.anInt1064) InflaterDecompressor.aBooleanArray2076[class46.anInt760] = true;
        if (i != -9343) method1914(-107, 120);
    }

    public Component111() {
        /* empty */
    }
}
