/* Component266 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

final class Component266
/**
 * RENAMED from `Class239_Sub13` (JODE-obfuscated).
 * Evidence: subclass of Component339 (hierarchy)
 */ extends Component339 {
    static int anInt5974;
    static int anInt5975;
    static int anInt5976;
    static int anInt5977;
    static int anInt5978;
    static int anInt5979;
    static int anInt5980 = 0;
    static int anInt5981;

    final int method1776(int i) {
        if (i != -32350) anInt5980 = 42;
        anInt5978++;
        return this.anInt3138;
    }

    final void method1716(boolean bool) {
        if (this.aClass348_Sub51_3136.method3422(674) == WorldNameText.aClass230_8638) this.anInt3138 = 2;
        if (bool == false) {
            anInt5974++;
            if (this.anInt3138 < 0 || this.anInt3138 > 2) this.anInt3138 = method1710(20014);
        }
    }

    static final void method1777(int i, DisplayModeManagerContainer58 class318_sub1_sub3_sub3) {
        if (i == -3) {
            if (class318_sub1_sub3_sub3 instanceof Npc) {
                Npc npc = (Npc) class318_sub1_sub3_sub3;
                if (npc.aClass79_10505 != null) ParticleShader.method2150(((Component72.localPlayer.plane) != (npc.plane)), false, npc);
            } else if (class318_sub1_sub3_sub3 instanceof Player) {
                Player player = ((Player) class318_sub1_sub3_sub3);
                PlayerState.method3298((byte) 105, ((player.plane) != (Component72.localPlayer.plane)), player);
            }
            anInt5981++;
        }
    }

    final int method1714(int i, int i_0_) {
        if (i != 3) return 3;
        anInt5979++;
        return 1;
    }

    Component266(int i, NodeSub51 class348_sub51) {
        super(i, class348_sub51);
    }

    final void method1712(int i, int i_1_) {
        anInt5977++;
        this.anInt3138 = i_1_;
        int i_2_ = -54 / ((82 - i) / 35);
    }

    final int method1710(int i) {
        anInt5975++;
        if (i != 20014) return 70;
        return 1;
    }

    Component266(NodeSub51 class348_sub51) {
        super(class348_sub51);
    }
}
