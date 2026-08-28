/* HashNodeSub16Sub2 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

final class HashNodeSub16Sub2
/**
 * RENAMED from `Class348_Sub42_Sub16_Sub2` (JODE-obfuscated).
 * Evidence: subclass of HashNodeSub16 (hierarchy)
 */ extends HashNodeSub16 {
    int anInt10457;
    CacheIndexReader aClass137_10458;
    static int anInt10459;
    static int anInt10460;
    byte[] aByteArray10461;
    static int anInt10462;
    static int anInt10463 = 0;
    static int anInt10464;
    static LruCache aClass356_10465 = new LruCache(16);

    static final boolean method3263(boolean bool) {
        if (bool != true) return false;
        anInt10459++;
        return StringCache.aBoolean4328;
    }

    public static void method3264(byte i) {
        aClass356_10465 = null;
        if (i != -24) method3265(-70, null, null);
    }

    final int method3257(int i) {
        anInt10460++;
        if (i != 16) this.aClass137_10458 = null;
        if (this.aBoolean9664) return 0;
        return 100;
    }

    static final void method3265(int i, Node class348, Node class348_0_) {
        try {
            if (class348.aClass348_4295 != null) class348.method2715((byte) 27);
            int i_1_ = -109 % ((6 - i) / 43);
            anInt10462++;
            class348.aClass348_4295 = class348_0_.aClass348_4295;
            class348.aClass348_4294 = class348_0_;
            class348.aClass348_4295.aClass348_4294 = class348;
            class348.aClass348_4294.aClass348_4295 = class348;
        } catch (RuntimeException runtimeexception) {
            throw NpcDefinition.method2929(runtimeexception, ("qh.K(" + i + ',' + (class348 != null ? "{...}" : "null") + ',' + (class348_0_ != null ? "{...}" : "null") + ')'));
        }
    }

    final byte[] method3259(int i) {
        anInt10464++;
        if (i != 16) method3259(8);
        if (this.aBoolean9664) throw new RuntimeException();
        return this.aByteArray10461;
    }

    public HashNodeSub16Sub2() {
        /* empty */
    }
}
