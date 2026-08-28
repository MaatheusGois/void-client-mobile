/* RenderableSub9Sub1 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

final class RenderableSub9Sub1
/**
 * RENAMED from `Class318_Sub9_Sub1` (JODE-obfuscated).
 * Evidence: subclass of RenderableSub9 (hierarchy)
 */ extends RenderableSub9 {
    static int anInt8782;
    String aString8783;
    static float aFloat8784;
    static int[] anIntArray8785;
    short aShort8786;
    int anInt8787 = (int) (Component240.method599(-92) / 1000L);
    static int anInt8788;

    static final DisplayModeManagerContainer115[] method2515(int i) {
        if (i != 1494) method2515(-18);
        anInt8782++;
        if (NativeLibraryLoader.aClass57Array2974 == null) {
            DisplayModeManagerContainer115[] class57s = Component248.method286((byte) -107, OggUrlStream.aClass297_8992);
            DisplayModeManagerContainer115[] class57s_0_ = new DisplayModeManagerContainer115[class57s.length];
            int i_1_ = 0;
            int i_2_ = Component192.aClass348_Sub51_3959.aClass239_Sub23_7231.method1818(-32350);
            while_108_:
            for (int i_3_ = 0; class57s.length > i_3_; i_3_++) {
                DisplayModeManagerContainer115 class57 = class57s[i_3_];
                if ((class57.anInt1046 <= 0 || class57.anInt1046 >= 24) && class57.anInt1047 >= 800 && class57.anInt1054 >= 600 && (i_2_ != 2 || (class57.anInt1047 <= 800) && class57.anInt1054 <= 600) && (i_2_ != 1 || ((class57.anInt1047 <= 1024) && (class57.anInt1054 <= 768)))) {
                    for (int i_4_ = 0; i_4_ < i_1_; i_4_++) {
                        DisplayModeManagerContainer115 class57_5_ = class57s_0_[i_4_];
                        if ((class57_5_.anInt1047 == class57.anInt1047) && (class57_5_.anInt1054 == class57.anInt1054)) {
                            if (class57.anInt1046 > class57_5_.anInt1046) class57s_0_[i_4_] = class57;
                            continue while_108_;
                        }
                    }
                    class57s_0_[i_1_] = class57;
                    i_1_++;
                }
            }
            NativeLibraryLoader.aClass57Array2974 = new DisplayModeManagerContainer115[i_1_];
            Component313.method1575(class57s_0_, 0, NativeLibraryLoader.aClass57Array2974, 0, i_1_);
            int[] is = new int[NativeLibraryLoader.aClass57Array2974.length];
            for (int i_6_ = 0; (NativeLibraryLoader.aClass57Array2974.length > i_6_); i_6_++) {
                DisplayModeManagerContainer115 class57 = NativeLibraryLoader.aClass57Array2974[i_6_];
                is[i_6_] = (class57.anInt1047 * class57.anInt1054);
            }
            DisplayModeManagerContainer271.method366(NativeLibraryLoader.aClass57Array2974, (byte) -123, is);
        }
        return NativeLibraryLoader.aClass57Array2974;
    }

    static final RSARequest method2516(int i, byte i_7_, int i_8_) {
        anInt8788++;
        RSARequest class348_sub42_sub15 = ((RSARequest) Component265.aClass356_1585.method3480(((long) i_8_ << 32 | (long) i), i_7_ ^ ~0x171e));
        if (i_7_ != 105) aFloat8784 = 0.99212307F;
        if (class348_sub42_sub15 == null) {
            class348_sub42_sub15 = new RSARequest(i_8_, i);
            Component265.aClass356_1585.method3483((byte) 91, (class348_sub42_sub15.aLong4291), class348_sub42_sub15);
        }
        return class348_sub42_sub15;
    }

    public static void method2517(byte i) {
        if (i != 4) anIntArray8785 = null;
        anIntArray8785 = null;
    }

    RenderableSub9Sub1(String string, int i) {
        this.aString8783 = string;
        this.aShort8786 = (short) i;
    }
}
