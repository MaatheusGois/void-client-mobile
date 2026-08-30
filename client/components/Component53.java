/* Component53 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

final class Component53
/**
 * RENAMED from `Class11` (JODE-obfuscated).
 * Evidence: root class; no distinctive extends/strings
 */ {
    static int anInt193 = 0;
    static int anInt194;
    static int anInt195;
    /** Active {@link BuildType} for this session. */
    static BuildType currentBuildType;
    static int anInt197 = 0;

    public static void decodedOperation221(int i) {
        if (i != 0) anInt197 = -100;
        currentBuildType = null;
    }

    static final void decodedOperation222(Player player, int i, byte i_0_, int i_1_) {
        anInt195++;
        if (i_0_ != 4) anInt194 = -100;
        int[] is = new int[4];
        Component313.decodedOperation1579(is, 0, is.length, i);
        Component25.decodedOperation1791(23946, is, i_1_, player);
    }
}
