/* Class373 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

/**
 * Mouse input abstraction over the game canvas.
 * Desktop: {@link Class373_Sub1} (AWT listeners). Mobile: voidawt-backed subclass.
 * <p>
 * Live cursor: {@link #method3597} = X, {@link #method3594} = Y (canvas pixels).
 * Button held: {@link #method3595} left, {@link #method3588} middle, {@link #method3590} right.
 * Queued events: {@link #method3596} yields {@link Class348_Sub45}
 * ({@code method3310}: 0=left press, 1=middle, 2=right, 3/4/5=releases, 6=wheel).
 * Singleton used by the client: {@link Class258_Sub4#aClass373_8552}.
 */
abstract class Class373 {
    static int anInt4533;
    /** Y origin used when laying out the right-click menu. */
    static int anInt4534;
    static int anInt4535;

    /** Middle mouse button currently held. */
    abstract boolean method3588(int i);

    /** Sync pending AWT events into the click / motion queues (called once per frame). */
    abstract void method3589(int i);

    public Class373() {
        /* empty */
    }

    /** Right mouse button currently held. */
    abstract boolean method3590(byte i);

    static final Class181 method3591(int i, int i_0_) {
        anInt4535++;
        Class181 class181 = (Class181) Class5.aClass60_4636.method583(i, i_0_ + -128);
        if (class181 != null) return class181;
        byte[] is = Class239_Sub12.aClass45_5964.method410(-1860, i_0_, i);
        class181 = new Class181();
        if (is != null) class181.method1370(24, new Class348_Sub49(is));
        class181.method1371(4);
        Class5.aClass60_4636.method582(class181, i, (byte) -103);
        return class181;
    }

    abstract void method3592(int i);

    /** Any mouse button currently held. */
    final boolean method3593(int i) {
        anInt4533++;
        if (i <= 91) method3593(53);
        return method3595(-83) || method3588(-121) || method3590((byte) 125);
    }

    /** Current cursor Y in canvas pixels. */
    abstract int method3594(byte i);

    /** Left mouse button currently held. */
    abstract boolean method3595(int i);

    /** Pop next queued mouse event, or {@code null} when empty. */
    abstract Class348_Sub45 method3596(int i);

    /** Current cursor X in canvas pixels. */
    abstract int method3597(boolean bool);
}
