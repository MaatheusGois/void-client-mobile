/* StringCache - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

/**
 * RENAMED from `Class351` (JODE-obfuscated).
 * String-keyed cache. Backed by a NodeCache (aClass60_4327 = new NodeCache(3000000,200)); method3455(String,int) looks up by name. Used by CacheStore and many systems.
 */

import java.awt.*;

final class StringCache {
    static int anInt4322;
    int anInt4323;
    static int anInt4324;
    static int anInt4325;
    private int anInt4326;
    static NodeCache aClass60_4327 = new NodeCache(3000000, 200);
    static boolean aBoolean4328 = false;
    static Font aFont4329;

    static final boolean method3455(String string, int i) {
        anInt4324++;
        if (string == null) return false;
        for (int i_0_ = 0; (i_0_ < MenuEntry.ignoreCount); i_0_++) {
            if (string.equalsIgnoreCase(DisplayModeManagerContainer145.ignoreDisplayNames[i_0_])) return true;
            if (string.equalsIgnoreCase(ShaderSub2.ignoreLastDisplayNames[i_0_])) return true;
        }
        if (i != 28280) aBoolean4328 = false;
        return false;
    }

    public final String toString() {
        anInt4325++;
        throw new IllegalStateException();
    }

    final int method3456(int i) {
        anInt4322++;
        if (i != 200) anInt4326 = -78;
        return anInt4326;
    }

    public static void method3457(boolean bool) {
        aFont4329 = null;
        aClass60_4327 = null;
        if (bool != true) method3455(null, -16);
    }

    StringCache(int i, int i_1_) {
        anInt4326 = i;
        this.anInt4323 = i_1_;
    }
}
