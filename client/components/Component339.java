/* Component339 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

abstract class Component339
/**
 * RENAMED from `Class239` (JODE-obfuscated).
 * Evidence: root class; no distinctive extends/strings
 */ {
    static int anInt3134;
    static Component183 aClass114_3135 = new Component183(108, 5);
    NodeSub51 aClass348_Sub51_3136;
    static int anInt3137;
    int anInt3138;
    static int anInt3139;
    static int anInt3140;
    static int anInt3141;
    static int anInt3142;
    static Component183 aClass114_3143;
    /** Shared 520+ byte sector scratch for {@link CacheIndexReader}. */
    static byte[] sectorBuffer = new byte[520];
    static Component183 aClass114_3145;
    static CacheStore aClass45_3146;
    static Component290 aClass166_3147;

    /** All {@link BuildType}s: LIVE, RC, WIP. */
    static final BuildType[] values(int i) {
        if (i > -110) method1715(97);
        anInt3141++;
        return (new BuildType[]{Component342.LIVE, DefinitionSub20.RC, Component118.WIP});
    }

    abstract int method1710(int i);

    static final void method1711(int i, CacheStore class45, int i_0_, CacheStore class45_1_) {
        try {
            MenuOpener.aClass45_4843 = class45_1_;
            anInt3140++;
            if (i != 7) aClass45_3146 = null;
            Component161.aClass45_1940 = class45;
        } catch (RuntimeException runtimeexception) {
            throw NpcDefinition.wrapThrowable(runtimeexception, ("su.Q(" + i + ',' + (class45 != null ? "{...}" : "null") + ',' + i_0_ + ',' + (class45_1_ != null ? "{...}" : "null") + ')'));
        }
    }

    abstract void method1712(int i, int i_2_);

    static final void method1713(boolean bool, int i) {
        Component272.method1728(PacketReader.anInt10432, -1, r.anInt9721, bool, Component236.anInt4017);
        if (i == 520) anInt3137++;
    }

    abstract int method1714(int i, int i_3_);

    public static void method1715(int i) {
        aClass114_3145 = null;
        aClass114_3143 = null;
        aClass45_3146 = null;
        aClass114_3135 = null;
        sectorBuffer = null;
        aClass166_3147 = null;
        if (i < 13) values(-99);
    }

    abstract void method1716(boolean bool);

    Component339(NodeSub51 class348_sub51) {
        this.aClass348_Sub51_3136 = class348_sub51;
        this.anInt3138 = method1710(20014);
    }

    static final void method1717(int i, int i_4_, int i_5_, int i_6_) {
        ObjectDeserializer.aByteArrayArrayArray6962 = new byte[i_6_][i_5_][i_4_];
        if (i != 19278) method1717(35, 126, -83, 85);
        anInt3134++;
    }

    Component339(int i, NodeSub51 class348_sub51) {
        this.aClass348_Sub51_3136 = class348_sub51;
        this.anInt3138 = i;
    }

    final void method1718(int i, int i_7_) {
        if (i_7_ < 3) method1712(12, 42);
        anInt3139++;
        if (method1714(3, i) != 3) method1712(124, i);
    }

    static {
        aClass114_3143 = new Component183(7, 3);
    }
}
