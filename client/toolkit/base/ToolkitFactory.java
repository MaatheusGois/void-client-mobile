/* ToolkitFactory - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

/**
 * RENAMED from `Class93` (JODE-obfuscated).
 * GraphicsToolkit factory. method862(Canvas, d, CacheStore, int, int) builds and returns the global GraphicsToolkit (GraphicsToolkit); method861 tears it down. Called at client startup.
 */

import jaggl.OpenGL;

import java.applet.Applet;
import java.awt.*;

final class ToolkitFactory {
    static int anInt1529;
    static Applet anApplet1530;
    static String[] aStringArray1531 = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
    static int anInt1532;
    static int anInt1533;
    static int anInt1534 = 0;

    public static void method861(int i) {
        aStringArray1531 = null;
        int i_0_ = 2 / ((i - 33) / 38);
        anApplet1530 = null;
    }

    static final GraphicsToolkit method862(Canvas canvas, d var_d, CacheStore class45, int i, int i_1_) {
        try {
            anInt1532++;
            if (!JaclibLoader.method215(27165)) throw new RuntimeException("");
            if (!DefinitionSub19.method3098(i ^ ~0x158f, "jaggl")) throw new RuntimeException("");
            OpenGL opengl = new OpenGL();
            long l = opengl.init(canvas, 8, 8, 8, 24, 0, i_1_);
            if (l == 0L) throw new RuntimeException("");
            if (i != 25542) return null;
            GlExtensionManager class377 = new GlExtensionManager(opengl, canvas, l, var_d, class45, i_1_);
            class377.method3930((byte) 26);
            return class377;
        } catch (RuntimeException runtimeexception) {
            throw NpcDefinition.method2929(runtimeexception, ("lt.D(" + (canvas != null ? "{...}" : "null") + ',' + (var_d != null ? "{...}" : "null") + ',' + (class45 != null ? "{...}" : "null") + ',' + i + ',' + i_1_ + ')'));
        }
    }

    static final byte[] method863(int i, byte i_2_, byte[] is) {
        if (i_2_ < 64) return null;
        anInt1529++;
        byte[] is_3_ = new byte[i];
        Component313.method1577(is, 0, is_3_, 0, i);
        return is_3_;
    }

    static final byte[] method864(int i, byte[] is, int i_4_, int i_5_) {
        anInt1533++;
        if (i_5_ >= -30) aStringArray1531 = null;
        byte[] is_6_ = new byte[i_4_];
        Component313.method1577(is, i, is_6_, 0, i_4_);
        return is_6_;
    }
}
