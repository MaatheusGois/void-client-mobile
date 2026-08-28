/* JaclibLoader - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

/**
 * RENAMED from `Class9` (JODE-obfuscated).
 * Jaclib native-memory loader. Loads the jaclib (JNI memory/GL binding) native library used by the toolkit.
 */

final class JaclibLoader {
    static int anInt167;
    static int[] anIntArray168 = {28, 35, 40, 44};
    static int anInt169 = -1;
    static int anInt170;
    static GraphicsToolkit aHa171;

    static final boolean method215(int i) {
        anInt170++;
        if (i != 27165) return false;
        if (!DefinitionSub19.method3098(-30282, "jaclib")) return false;
        return DefinitionSub19.method3098(-30282, "hw3d");
    }

    public static void method216(boolean bool) {
        anIntArray168 = null;
        if (bool != false) method216(true);
        aHa171 = null;
    }
}
