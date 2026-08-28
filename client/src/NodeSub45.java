/* NodeSub45 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

abstract class NodeSub45
/**
 * RENAMED from `Class348_Sub45` (JODE-obfuscated).
 * Evidence: subclass of Node (hierarchy)
 */ extends Node {
    static int anInt7102 = 1400;
    static DisplayModeManagerContainer42 aClass304_7103 = new DisplayModeManagerContainer42(3);
    static int anInt7104;
    static Component183 aClass114_7105 = new Component183(101, 20);
    static Component183 aClass114_7106 = new Component183(36, -2);
    static Component24[] aClass105Array7107;
    static TeleportHandler[] aClass361Array7108 = new TeleportHandler[4];

    abstract int method3308(byte i);

    static final void method3309(int i) {
        Component160.anIntArrayArrayArray4356 = (new int[Component291.anInt2524][1 + StaticElementRenderer.anInt6451]
                [1 + NodeSub41.anInt7054]);
        anInt7104++;
        NodeSub44.anInt7101 = 0;
        Component53.anInt194 = Component148.anInt3465;
        DisplayModeManagerContainer104.aClass338Array10330 = new Component103[2000];
        ha_Sub2.anInt7714 = Component148.anInt3465;
        Cp1252Decoder.aBoolean5226 = false;
        Component335.aClass338Array2034 = new Component103[500];
        Component325.anInt1200 = 0;
        RadixText.anInt6115 = 0;
        InterfaceRenderer.aClass338Array5060 = new Component103[1000];
        if (i > -113) aClass304_7103 = null;
        HashNodeSub10.anInt9577 = 0;
        HashNodeSub19.aClass338Array9700 = new Component103[(int) (500 * Loader.RENDER_DISTANCE_MULTIPLIER)];
        DefinitionSub23.aBoolean9307 = !(JaclibLoader.aHa171 instanceof oa);
    }

    public NodeSub45() {
        /* empty */
    }

    abstract int method3310(int i);

    abstract int method3311(int i);

    abstract long method3312(byte i);

    static final void method3313(int i, s var_s) {
        aa_Sub1.aSArray5191[i] = var_s;
    }

    public static void method3314(int i) {
        aClass114_7106 = null;
        if (i != 5) method3313(-103, null);
        aClass114_7105 = null;
        aClass361Array7108 = null;
        aClass105Array7107 = null;
        aClass304_7103 = null;
    }

    abstract int method3315(int i);
}
