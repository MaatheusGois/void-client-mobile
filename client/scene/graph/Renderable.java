/* Renderable - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

/**
 * RENAMED from `Class318` (JODE-obfuscated).
 * Base class for renderable scene objects (10 subclasses incl. RenderableObject). Holds shared lifecycle; method2375 touches the NodeCache of renderables.
 */

class Renderable {
    Renderable aClass318_3970;
    static int anInt3971;
    static int anInt3972;
    static int anInt3973;
    static Component315[] aClass243Array3974 = new Component315[5];
    static int anInt3975;
    Renderable aClass318_3976;
    static DisplayModeManagerContainer42 aClass304_3977;

    final void method2373(boolean bool) {
        anInt3975++;
        if (this.aClass318_3976 != null) {
            this.aClass318_3976.aClass318_3970 = this.aClass318_3970;
            this.aClass318_3970.aClass318_3976 = this.aClass318_3976;
            this.aClass318_3970 = null;
            if (bool == false) this.aClass318_3976 = null;
        }
    }

    public static void method2374(byte i) {
        aClass304_3977 = null;
        int i_0_ = 108 / ((i - -83) / 41);
        aClass243Array3974 = null;
    }

    static final void method2375(int i) {
        anInt3972++;
        Connection.aClass60_2671.method590(0);
        if (i != 16127) anInt3971 = -113;
    }

    public Renderable() {
        /* empty */
    }

    static {
        for (int i = 0; aClass243Array3974.length > i; i++)
            aClass243Array3974[i] = new Component315();
        aClass304_3977 = new DisplayModeManagerContainer42(1);
    }
}
