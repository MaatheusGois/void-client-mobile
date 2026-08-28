/* Component302 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

import java.io.IOException;

final class Component302
/**
 * RENAMED from `Class239_Sub18` (JODE-obfuscated).
 * Evidence: subclass of Component339 (hierarchy)
 */ extends Component339 {
    static int anInt6029;
    static Component111 aClass251_6030 = new Component111();
    static int anInt6031;
    static int anInt6032;
    static int anInt6033;
    static int anInt6034;
    static int[] anIntArray6035;
    static int anInt6036;
    static int anInt6037;
    static int anInt6038;

    Component302(int i, NodeSub51 class348_sub51) {
        super(i, class348_sub51);
    }

    public static void method1799(int i) {
        int i_0_ = 51 / ((i - 33) / 40);
        anIntArray6035 = null;
        aClass251_6030 = null;
    }

    final int method1714(int i, int i_1_) {
        anInt6034++;
        if (i != 3) return -46;
        if (this.aClass348_Sub51_3136.method3425(-62)) return 3;
        if (i_1_ == 0 || this.aClass348_Sub51_3136.aClass239_Sub9_7256.method1759(-32350) == 1) return 1;
        return 2;
    }

    final int method1710(int i) {
        if (i != 20014) anIntArray6035 = null;
        anInt6029++;
        return 1;
    }

    final int method1800(int i) {
        anInt6031++;
        if (i != -32350) anIntArray6035 = null;
        return this.anInt3138;
    }

    final void method1716(boolean bool) {
        anInt6036++;
        if (bool == false) {
            if (this.aClass348_Sub51_3136.method3425(-94)) this.anInt3138 = 0;
            if (this.anInt3138 < 0 && this.anInt3138 > 2) this.anInt3138 = method1710(20014);
        }
    }

    final boolean method1801(int i) {
        if (i <= 85) method1800(90);
        anInt6037++;
        return !this.aClass348_Sub51_3136.method3425(-70);
    }

    static final void method1802(int i) throws IOException {
        if (DefinitionSub8.aClass238_9165 != null && NodeSub34.anInt6969 > 0) {
            int i_2_ = 0;
            for (; ; ) {
                ParticleSystem class348_sub47 = (ParticleSystem) DefinitionSub13.aClass262_9201.first(i ^ 0x4);
                if (class348_sub47 == null) break;
                DefinitionSub8.aClass238_9165.writeBytes(0, i ^ 0x77, class348_sub47.anInt7119, (class348_sub47.aClass348_Sub49_Sub2_7116.payload));
                i_2_ += class348_sub47.anInt7119;
                NodeSub34.anInt6969 -= class348_sub47.anInt7119;
                class348_sub47.unlink((byte) 74);
                class348_sub47.aClass348_Sub49_Sub2_7116.release((byte) -69);
                class348_sub47.method3326((byte) -45);
            }
            Component53.anInt193 = 0;
            Component268.anInt1433 += i_2_;
        }
        if (i != 0) method1799(58);
        anInt6038++;
    }

    final void method1712(int i, int i_3_) {
        this.anInt3138 = i_3_;
        anInt6032++;
        int i_4_ = 65 / ((82 - i) / 35);
    }

    Component302(NodeSub51 class348_sub51) {
        super(class348_sub51);
    }
}
