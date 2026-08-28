/* HashNode - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

/**
 * RENAMED from `Class348_Sub42` (JODE-obfuscated).
 * Node used inside HashTable chains. Holds a long hash key (aLong7057) and chain prev/next (aClass348_Sub42_7060/7063). Base for HashNodeSub19 and other hash-keyed nodes.
 */

class HashNode extends Node {
    long aLong7057;
    static Component245 aClass2_7058;
    static int anInt7059 = 0;
    HashNode aClass348_Sub42_7060;
    static int anInt7061;
    static int anInt7062;
    HashNode aClass348_Sub42_7063;
    static int anInt7064;

    public static void method3161(int i) {
        if (i != 0) method3161(-27);
        aClass2_7058 = null;
    }

    final void method3162(boolean bool) {
        anInt7064++;
        if (bool != true) method3163((byte) 50);
        if (this.aClass348_Sub42_7060 != null) {
            this.aClass348_Sub42_7060.aClass348_Sub42_7063 = this.aClass348_Sub42_7063;
            this.aClass348_Sub42_7063.aClass348_Sub42_7060 = this.aClass348_Sub42_7060;
            this.aClass348_Sub42_7060 = null;
            this.aClass348_Sub42_7063 = null;
        }
    }

    static final void method3163(byte i) {
        ShaderCompilerSub1.anInt6513 = 0;
        anInt7062++;
        if (i == -114) {
            for (int i_0_ = 0; i_0_ < 2048; i_0_++) {
                Component101.aClass348_Sub49Array2105[i_0_] = null;
                Component293.aByteArray3300[i_0_] = (byte) 1;
                NpcDefinition.aClass359Array6802[i_0_] = null;
            }
        }
    }

    final boolean method3164(byte i) {
        anInt7061++;
        if (this.aClass348_Sub42_7060 == null) return false;
        if (i != 1) method3162(false);
        return true;
    }

    public HashNode() {
        /* empty */
    }

    static {
        aClass2_7058 = new Component245();
    }
}
