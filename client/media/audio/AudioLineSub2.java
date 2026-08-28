/* AudioLineSub2 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

import java.awt.*;

final class AudioLineSub2
/**
 * RENAMED from `Class279_Sub2` (JODE-obfuscated).
 * Evidence: subclass of AudioLine (hierarchy)
 */ extends AudioLine {
    private final int anInt6181;
    private static Interface20 anInterface20_6182;

    final void flush() {
        anInterface20_6182.method77((byte) 98, anInt6181);
    }

    final int getBufferedSamples() {
        return anInterface20_6182.method75((byte) -93, anInt6181);
    }

    public static void method2097() {
        anInterface20_6182 = null;
    }

    final void close() {
        anInterface20_6182.method74(anInt6181, (byte) 122);
    }

    final void initOnComponent(Component component) throws Exception {
        anInterface20_6182.method78(Component231.anInt339, Component21.aBoolean3652, component, 27929);
    }

    final void method2094() {
        anInterface20_6182.method76(anInt6181, this.anIntArray3603);
    }

    AudioLineSub2(ReflectionInvoker class297, int i) {
        anInterface20_6182 = (Interface20) class297.method2244(21);
        anInt6181 = i;
    }

    final void open(int i) throws Exception {
        if (i > 32768) throw new IllegalArgumentException();
        anInterface20_6182.method79(i, anInt6181, (byte) 112);
    }
}
