/* NodeSub16 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

abstract class NodeSub16
/**
 * RENAMED from `Class348_Sub16` (JODE-obfuscated).
 * Evidence: subclass of Node (hierarchy)
 */ extends Node {
    volatile boolean aBoolean6784 = true;
    NodeSub16 aClass348_Sub16_6785;
    int anInt6786;
    NodeSub19 aClass348_Sub19_6787;

    final void method2815(int[] is, int i, int i_0_) {
        if (this.aBoolean6784) method2817(is, i, i_0_);
        else method2819(i_0_);
    }

    abstract NodeSub16 method2816();

    abstract void method2817(int[] is, int i, int i_1_);

    abstract NodeSub16 method2818();

    abstract void method2819(int i);

    int method2820() {
        return 255;
    }

    public NodeSub16() {
        /* empty */
    }

    abstract int method2821();
}
