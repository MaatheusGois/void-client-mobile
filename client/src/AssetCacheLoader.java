/* AssetCacheLoader - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

/**
 * AssetCacheLoader — gerenciador de cache com LRU dual (antigo `AssetCacheLoader` / `hs`).
 * <p>
 * Mantém dois caches {@code LruCache(256)}:
 * <ul>
 *   <li>{@code aClass356_381} — cache de {@code NodeSub19Sub1} já decodificados (sprites/models)</li>
 *   <li>{@code aClass356_374} — cache intermediário de {@code NodeSub10} (estruturas cruas)</li>
 * </ul>
 * Usa dois {@code CacheStore} (fontes de dados: índice do cache) para resolver
 * recursos sob demanda via {@code Component277.method2372} e {@code NodeSub10.method2795}.
 * A chave composta é um {@code long} derivado de dois ints com bit-mixing
 * (ex.: {@code i ^ (i1 >>>12 | i1<<4)}). Renomeado com base em uso de
 * {@code LruCache} + {@code CacheStore} + padrão de cache hit/miss.
 */
/**
 * RENAMED from `Class26` (JODE-obfuscated).
 * Evidence: root class; no distinctive extends/strings
 */
final class AssetCacheLoader {
    static int anInt373;
    /** Cache de decodificados (NodeSub19Sub1) — 256 entradas LRU. */
    private final LruCache aClass356_374 = new LruCache(256);
    static int anInt375;
    static int anInt376;
    /** Fonte secundária (índice 2) — ex.: dados de modelo/textura. */
    private final CacheStore aClass45_377;
    static int anInt378;
    /** Fonte primária (índice 1) — ex.: definições base. */
    private final CacheStore aClass45_379;
    static int anInt380;
    /** Cache principal de resultados (NodeSub19Sub1) — 256 entradas. */
    private final LruCache aClass356_381 = new LruCache(256);
    static NamedInteger aClass364_382 = new NamedInteger("WTQA", 2);
    static int anInt383;
    static boolean aBoolean384 = false;
    static int anInt385 = 0;
    static int anInt386;

    public static void method306(boolean bool) {
        aClass364_382 = null;
        if (bool != true) method311((byte) -5, '\002');
    }

    private final NodeSub19Sub1 method307(int i, int[] is, int i_0_, int i_1_) {
        anInt378++;
        int i_2_ = i ^ (i_1_ >>> 12 | i_1_ << 4 & 0xfff1);
        i_2_ |= i_1_ << 16;
        long l = i_2_;
        NodeSub19Sub1 class348_sub19_sub1 = (NodeSub19Sub1) aClass356_381.method3480(l, -6008);
        if (class348_sub19_sub1 != null) return class348_sub19_sub1;
        if (is != null && is[0] <= 0) return null;
        Component277 class317 = Component277.method2372(aClass45_379, i_1_, i);
        if (i_0_ < 50) method311((byte) -60, 'n');
        if (class317 == null) return null;
        class348_sub19_sub1 = class317.method2369();
        aClass356_381.method3483((byte) 32, l, class348_sub19_sub1);
        if (is != null) is[0] -= (class348_sub19_sub1.aByteArray8984).length;
        return class348_sub19_sub1;
    }

    final NodeSub19Sub1 method308(int i, int[] is, int i_3_) {
        anInt373++;
        if ((~aClass45_379.method414(-1)) == i_3_) return method307(i, is, 122, 0);
        if (aClass45_379.method407(0, i) == 1) return method307(0, is, 69, i);
        throw new RuntimeException();
    }

    final NodeSub19Sub1 method309(int[] is, int i, int i_4_) {
        anInt376++;
        if (aClass45_377.method414(i_4_) == 1) return method310(i, 0, (byte) 92, is);
        if (aClass45_377.method407(0, i) == 1) return method310(0, i, (byte) 78, is);
        if (i_4_ != -1) method307(44, null, -124, -47);
        throw new RuntimeException();
    }

    private final NodeSub19Sub1 method310(int i, int i_5_, byte i_6_, int[] is) {
        anInt375++;
        int i_7_ = i ^ (0xfff0 & i_5_ << 4 | i_5_ >>> 12);
        i_7_ |= i_5_ << 16;
        int i_8_ = -113 / ((i_6_ - 16) / 34);
        long l = (long) i_7_ ^ 0x100000000L;
        NodeSub19Sub1 class348_sub19_sub1 = (NodeSub19Sub1) aClass356_381.method3480(l, -6008);
        if (class348_sub19_sub1 != null) return class348_sub19_sub1;
        if (is != null && is[0] <= 0) return null;
        NodeSub10 class348_sub10 = (NodeSub10) aClass356_374.method3480(l, -6008);
        if (class348_sub10 == null) {
            class348_sub10 = NodeSub10.method2795(aClass45_377, i_5_, i);
            if (class348_sub10 == null) return null;
            aClass356_374.method3483((byte) 108, l, class348_sub10);
        }
        class348_sub19_sub1 = class348_sub10.method2791(is);
        if (class348_sub19_sub1 == null) return null;
        class348_sub10.method2715((byte) 27);
        aClass356_381.method3483((byte) 117, l, class348_sub19_sub1);
        return class348_sub19_sub1;
    }

    static final boolean method311(byte i, char c) {
        anInt380++;
        if (i <= 85) method311((byte) -75, '\014');
        return c == 160 || c == 32 || c == 95 || c == 45;
    }

    AssetCacheLoader(CacheStore class45, CacheStore class45_9_) {
        try {
            aClass45_377 = class45_9_;
            aClass45_379 = class45;
        } catch (RuntimeException runtimeexception) {
            throw NpcDefinition.method2929(runtimeexception, ("AssetCacheLoader.<init>(" + (class45 != null ? "{...}" : "null") + ',' + (class45_9_ != null ? "{...}" : "null") + ')'));
        }
    }

    static {
        anInt383 = 0;
    }
}
