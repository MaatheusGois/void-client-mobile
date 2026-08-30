/* Component46 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

final class Component46
/**
 * RENAMED from `Class217` (JODE-obfuscated).
 * Evidence: root class; no distinctive extends/strings
 */ {
    static int anInt2840;
    static int anInt2841;
    private final CacheStore aClass45_2842;
    static int anInt2843;
    static NodeCache aClass60_2844 = new NodeCache(64);
    int anInt2845;
    static int anInt2846;
    static int anInt2847;
    private NodeCache aClass60_2848 = new NodeCache(64);

    public static void decodedOperation1585(int i) {
        if (i != -1) decodedOperation1585(-34);
        aClass60_2844 = null;
    }

    static final void decodedOperation1586(boolean bool, byte i, String string) {
        if (i >= -51) aClass60_2844 = null;
        anInt2840++;
        Component110.decodedOperation259(-1, 113, bool, string, -1);
    }

    final void decodedOperation1587(int i, int i_0_) {
        if (i_0_ != 3758) aClass60_2848 = null;
        synchronized (aClass60_2848) {
            aClass60_2848.processSoftEntries(2, i);
        }
        anInt2841++;
    }

    final Component146 decodedOperation1588(int i, int i_1_) {
        anInt2847++;
        Component146 class159;
        synchronized (aClass60_2848) {
            class159 = (Component146) aClass60_2848.get(i_1_, -126);
        }
        if (class159 != null) return class159;
        byte[] is;
        synchronized (aClass45_2842) {
            is = aClass45_2842.getFile(-1860, 16, i_1_);
            if (i >= -17) aClass60_2848 = null;
        }
        class159 = new Component146();
        if (is != null) class159.decodedOperation1253(new Buffer(is), true);
        synchronized (aClass60_2848) {
            aClass60_2848.putOne(class159, i_1_, (byte) -124);
        }
        return class159;
    }

    final void decodedOperation1589(byte i) {
        anInt2843++;
        synchronized (aClass60_2848) {
            aClass60_2848.clear(0);
        }
        if (i < 126) decodedOperation1588(-98, -35);
    }

    final void decodedOperation1590(int i) {
        anInt2846++;
        if (i != 0) this.anInt2845 = -97;
        synchronized (aClass60_2848) {
            aClass60_2848.purgeSoftReferences(i ^ ~0x6b);
        }
    }

    Component46(GameType class230, int i, CacheStore class45) {
        try {
            aClass45_2842 = class45;
            if (aClass45_2842 != null) this.anInt2845 = aClass45_2842.getFileCount(0, 16);
            else this.anInt2845 = 0;
        } catch (RuntimeException runtimeexception) {
            throw NpcDefinition.wrapThrowable(runtimeexception, ("rv.<init>(" + (class230 != null ? "{...}" : "null") + ',' + i + ',' + (class45 != null ? "{...}" : "null") + ')'));
        }
    }
}
