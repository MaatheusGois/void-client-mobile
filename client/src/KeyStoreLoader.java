/* KeyStoreLoader - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

/**
 * RENAMED from `Class106` (JODE-obfuscated).
 * Keystore/certificate loader. Loads PKCS#12 keystore aliases (p12_full, p11_full, b12_full) used for the client's signed-resource verification.
 */

final class KeyStoreLoader {
    static int anInt1631;
    static int anInt1632;
    private NewsFetcher aClass339_1633;
    static int anInt1634;
    static int[] anIntArray1635 = new int[16];
    static int[] anIntArray1636 = null;
    private final CacheStore aClass45_1637;
    static int[][] anIntArrayArray1638 = {{0, 1, 2, 3}, {1, -1, -1, 0}, {-1, 2, -1, 0}, {-1, 0, -1, 2}, {0, 1, -1, 2}, {1, 2, -1, 0}, {-1, 4, -1, 1}, {-1, 3, 4, -1}, {-1, 0, 2, -1}, {-1, -1, 2, 0}, {0, 2, 5, 3}, {0, -1, 6, -1}, {0, 1, 2, 3}};
    static int anInt1639;
    static int anInt1640;
    private final CacheStore aClass45_1641;

    private final NewsFetcher method998(int i) {
        anInt1632++;
        if (aClass339_1633 == null) aClass339_1633 = new NewsFetcher();
        if (i > -71) method1001(null, -15);
        return aClass339_1633;
    }

    public static void method999(int i) {
        anIntArray1636 = null;
        anIntArray1635 = null;
        anIntArrayArray1638 = null;
        if (i != 21745) method1001(null, -51);
    }

    final Interface1 method1000(boolean bool, Interface12 interface12) {
        anInt1640++;
        if (interface12 == null) return null;
        DisplayModeManagerContainer369 class223 = interface12.method51((byte) 120);
        if (Component386.aClass223_2868 == class223) return new DisplayModeManagerContainer249((RSACipher) interface12);
        if (MatrixSub1.aClass223_5689 == class223) return new Component49(method998(-107), (Component15) interface12);
        if (class223 == DefinitionSub21.aClass223_9274) return new Component296(aClass45_1637, (Component118) interface12);
        if (class223 == DisplayModeManagerContainer332.aClass223_4997) return new Component162(aClass45_1637, (Component383) interface12);
        if (class223 == Buffer.aClass223_7175) return new Component2(aClass45_1637, aClass45_1641, (Component14) interface12);
        if (class223 == Component55.aClass223_3934) return new DisplayModeManagerContainer89(aClass45_1637, aClass45_1641, (Component381) interface12);
        if (class223 == Component316.aClass223_2489) return new Component364(aClass45_1637, aClass45_1641, (Component76) interface12);
        if (class223 == RadixParser.aClass223_2307) return new VideoAdPlayer(aClass45_1637, aClass45_1641, (HeapDumpHelper) interface12);
        if (bool != true) anIntArray1636 = null;
        if (Component90.aClass223_2045 == class223) return new DisplayModeManagerContainer232(aClass45_1637, (Component247) interface12);
        if (Component38.aClass223_2507 == class223) return new Component305(aClass45_1637, aClass45_1641, (DummyClass) interface12);
        return null;
    }

    static final void method1001(CacheStore class45, int i) {
        anInt1634++;
        LoadingState.anInt1044 = class45.method417("p11_full", i);
        DisplayModeManagerContainer167.anInt235 = class45.method417("p12_full", i);
        ReferenceTable.anInt3736 = class45.method417("b12_full", 0);
    }

    KeyStoreLoader(CacheStore class45, CacheStore class45_0_) {
        try {
            aClass45_1637 = class45;
            aClass45_1641 = class45_0_;
        } catch (RuntimeException runtimeexception) {
            throw NpcDefinition.method2929(runtimeexception, ("mia.<init>(" + (class45 != null ? "{...}" : "null") + ',' + (class45_0_ != null ? "{...}" : "null") + ')'));
        }
    }

    static {
        anInt1631 = 0;
    }
}
