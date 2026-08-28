/* ImageCacheStore - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */
/**
 * RENAMED from `Class322` (JODE-obfuscated).
 * Full image cache store. Holds int[][][] RGB pixel buffers; method2553 throws 'Can only retrieve a full image cache' if the cache is not fully populated.
 */

final class ImageCacheStore {
    static int anInt4018;
    static int anInt4019;
    private final int anInt4020;
    private NodeList aClass262_4021;
    private int anInt4022;
    static int anInt4023;
    private final int anInt4024;
    private int anInt4025 = 0;
    static int anInt4026;
    static Component183 aClass114_4027;
    static int anInt4028;
    private int[][][] anIntArrayArrayArray4029;
    static int anInt4030;
    static int[] anIntArray4031;
    static int anInt4032 = -1;
    private ClientSystemInfo[] aClass348_Sub24Array4033;
    static int anInt4034;
    boolean aBoolean4035;
    static Component17 aClass308_4036;

    static final int method2552(boolean bool, int i, int i_0_, int i_1_) {
        if (i_1_ != -24667) method2554((byte) 95);
        anInt4023++;
        NodeSub13 class348_sub13 = AbstractGlTextureSub4.method1974((byte) 4, i, bool);
        if (class348_sub13 == null) return -1;
        if (i_0_ < 0 || i_0_ >= class348_sub13.anIntArray6757.length) return -1;
        return class348_sub13.anIntArray6757[i_0_];
    }

    final int[][][] method2553(int i) {
        anInt4018++;
        if (anInt4024 != anInt4020) throw new RuntimeException("Can only retrieve a full image cache");
        for (int i_2_ = i; i_2_ < anInt4020; i_2_++)
            aClass348_Sub24Array4033[i_2_] = DisplayModeManagerContainer196.aClass348_Sub24_4226;
        return anIntArrayArrayArray4029;
    }

    static final void method2554(byte i) {
        if (i != -45) anInt4032 = 61;
        anInt4030++;
        if (Component37.anInt3931 == 1 || Component37.anInt3931 == 3 || (Component37.anInt3931 != Component178.anInt1447 && (Component37.anInt3931 == 0 || Component178.anInt1447 == 0))) {
            NodeSub32.anInt6930 = 0;
            Component324.anInt2057 = 0;
            Component21.aClass356_3654.method3481(0);
        }
        Component178.anInt1447 = Component37.anInt3931;
    }

    public static void method2555(byte i) {
        if (i != 28) method2554((byte) 21);
        anIntArray4031 = null;
        aClass114_4027 = null;
        aClass308_4036 = null;
    }

    static final void method2556(boolean bool, int i, DisplayModeManagerContainer58 class318_sub1_sub3_sub3) {
        anInt4028++;
        int i_3_ = -1;
        int i_4_ = 0;
        if (OpenGlShader.clientCycle < class318_sub1_sub3_sub3.anInt10239) RadixText.method1834(class318_sub1_sub3_sub3, (byte) -16);
        else if (OpenGlShader.clientCycle <= (class318_sub1_sub3_sub3.anInt10300)) Component362.method1041(-1, class318_sub1_sub3_sub3);
        else {
            Component386.method1600(false, 0, class318_sub1_sub3_sub3);
            i_3_ = Component366.anInt3062;
            i_4_ = Component162.anInt8387;
        }
        if (bool != false) aClass114_4027 = null;
        if ((class318_sub1_sub3_sub3.x < 512) || class318_sub1_sub3_sub3.y < 512 || (-512 + AbstractShaderSub4.anInt7319 * 512 <= class318_sub1_sub3_sub3.x) || (-512 + ParametricDefinition.anInt9109 * 512 <= class318_sub1_sub3_sub3.y)) {
            class318_sub1_sub3_sub3.anInt10291 = -1;
            class318_sub1_sub3_sub3.anInt10300 = 0;
            i_4_ = 0;
            i_3_ = -1;
            class318_sub1_sub3_sub3.anIntArray10236 = null;
            class318_sub1_sub3_sub3.anInt10269 = -1;
            class318_sub1_sub3_sub3.anInt10286 = -1;
            class318_sub1_sub3_sub3.anInt10239 = 0;
            class318_sub1_sub3_sub3.x = (512 * (class318_sub1_sub3_sub3.anIntArray10320[0]) + 256 * class318_sub1_sub3_sub3.method2436((byte) 120));
            class318_sub1_sub3_sub3.y = (512 * (class318_sub1_sub3_sub3.anIntArray10317[0]) + class318_sub1_sub3_sub3.method2436((byte) 88) * 256);
            class318_sub1_sub3_sub3.method2427(70);
        }
        if ((Component72.localPlayer == class318_sub1_sub3_sub3) && (class318_sub1_sub3_sub3.x < 6144 || class318_sub1_sub3_sub3.y < 6144 || (class318_sub1_sub3_sub3.x >= 512 * (AbstractShaderSub4.anInt7319 + -12)) || (512 * (ParametricDefinition.anInt9109 - 12) <= class318_sub1_sub3_sub3.y))) {
            class318_sub1_sub3_sub3.anInt10291 = -1;
            i_3_ = -1;
            class318_sub1_sub3_sub3.anInt10300 = 0;
            class318_sub1_sub3_sub3.anInt10269 = -1;
            class318_sub1_sub3_sub3.anInt10239 = 0;
            i_4_ = 0;
            class318_sub1_sub3_sub3.anIntArray10236 = null;
            class318_sub1_sub3_sub3.anInt10286 = -1;
            class318_sub1_sub3_sub3.x = (512 * (class318_sub1_sub3_sub3.anIntArray10320[0]) + 256 * class318_sub1_sub3_sub3.method2436((byte) 115));
            class318_sub1_sub3_sub3.y = (512 * (class318_sub1_sub3_sub3.anIntArray10317[0]) + 256 * class318_sub1_sub3_sub3.method2436((byte) 115));
            class318_sub1_sub3_sub3.method2427(54);
        }
        int i_5_ = NodeSub8.method2774((byte) 108, class318_sub1_sub3_sub3);
        za_Sub2.method3443(true, class318_sub1_sub3_sub3);
        DisplayModeManagerContainer5.method729(i_3_, i_5_, (byte) 67, class318_sub1_sub3_sub3, i_4_);
        Component140.method3208(class318_sub1_sub3_sub3, i_3_, -98);
        DisplayModeManagerContainer124.method1635(-69, class318_sub1_sub3_sub3);
    }

    final int[][] method2557(int i, int i_6_) {
        anInt4034++;
        if (i >= -75) method2554((byte) -61);
        if (anInt4020 != anInt4024) {
            if (anInt4020 == 1) {
                this.aBoolean4035 = i_6_ != anInt4022;
                anInt4022 = i_6_;
                return anIntArrayArrayArray4029[0];
            }
            ClientSystemInfo class348_sub24 = aClass348_Sub24Array4033[i_6_];
            if (class348_sub24 == null) {
                this.aBoolean4035 = true;
                if (anInt4020 <= anInt4025) {
                    ClientSystemInfo class348_sub24_7_ = (ClientSystemInfo) aClass262_4021.method1993(-126);
                    class348_sub24 = new ClientSystemInfo(i_6_, class348_sub24_7_.anInt6875);
                    aClass348_Sub24Array4033[class348_sub24_7_.anInt6872] = null;
                    class348_sub24_7_.method2715((byte) 56);
                } else {
                    class348_sub24 = new ClientSystemInfo(i_6_, anInt4025);
                    anInt4025++;
                }
                aClass348_Sub24Array4033[i_6_] = class348_sub24;
            } else this.aBoolean4035 = false;
            aClass262_4021.method2001(class348_sub24, -110);
            return (anIntArrayArrayArray4029[class348_sub24.anInt6875]);
        }
        this.aBoolean4035 = aClass348_Sub24Array4033[i_6_] == null;
        aClass348_Sub24Array4033[i_6_] = DisplayModeManagerContainer196.aClass348_Sub24_4226;
        return anIntArrayArrayArray4029[i_6_];
    }

    final void method2558(int i) {
        anInt4019++;
        if (i != 6144) anIntArrayArrayArray4029 = null;
        for (int i_8_ = 0; anInt4020 > i_8_; i_8_++) {
            anIntArrayArrayArray4029[i_8_][0] = null;
            anIntArrayArrayArray4029[i_8_][1] = null;
            anIntArrayArrayArray4029[i_8_][2] = null;
            anIntArrayArrayArray4029[i_8_] = null;
        }
        aClass348_Sub24Array4033 = null;
        anIntArrayArrayArray4029 = null;
        aClass262_4021.method1996(99);
        aClass262_4021 = null;
    }

    ImageCacheStore(int i, int i_9_, int i_10_) {
        anInt4022 = -1;
        aClass262_4021 = new NodeList();
        this.aBoolean4035 = false;
        anInt4020 = i;
        anInt4024 = i_9_;
        aClass348_Sub24Array4033 = new ClientSystemInfo[anInt4024];
        anIntArrayArrayArray4029 = new int[anInt4020][3][i_10_];
    }

    static {
        aClass114_4027 = new Component183(90, 10);
        aClass308_4036 = new Component17(128);
    }
}
