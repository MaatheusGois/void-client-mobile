/* NodeSub35 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

final class NodeSub35
/**
 * RENAMED from `Class348_Sub35` (JODE-obfuscated).
 * Evidence: subclass of Node (hierarchy)
 */ extends Node {
    static int anInt6975;
    /** Payload int — enum value, GL list length, etc. depending on owner. */
    int intValue;
    static DisplayModeManagerContainer238 aClass74_6977 = new DisplayModeManagerContainer238(8, 3);
    static NodeList aClass262_6978;
    static int anInt6979 = 0;
    static CacheStore aClass45_6980;
    static int anInt6981;

    static final void decodedOperation3027(byte i) {
        anInt6975++;
        if (Component192.preferences.aClass239_Sub27_7261.decodedOperation1840(-32350) == 0 && Component117.anInt4372 != Component385.anInt2204) NodeSub41.decodedOperation3157(DisplayModeManagerContainer363.anInt4095, (byte) 123, GraphicsToolkit.anInt4581, 11, false);
        else {
            NodeSub46.decodedOperation3319(NodeSub8.toolkit, (byte) -121);
            if (i != 33) decodedOperation3028(-79);
            if (DisplayModeManagerContainer174.anInt10395 != Component117.anInt4372) DefinitionSub9.decodedOperation3072((byte) -96);
        }
    }

    public static void decodedOperation3028(int i) {
        if (i != -11677) decodedOperation3027((byte) -80);
        aClass74_6977 = null;
        aClass45_6980 = null;
        aClass262_6978 = null;
    }

    public NodeSub35() {
        /* empty */
    }

    NodeSub35(int i) {
        this.intValue = i;
    }

    static {
        aClass262_6978 = new NodeList();
    }
}
