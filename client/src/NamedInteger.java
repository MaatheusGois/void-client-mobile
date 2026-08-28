/* NamedInteger - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

/**
 * RENAMED from `Class364` (JODE-obfuscated).
 * Node holding a named integer (String name + value). Used inside hashtables of named integer constants.
 */

final class NamedInteger {
    int value;
    static int anInt4467;
    static int anInt4468;
    static int anInt4469;

    public final String toString() {
        anInt4467++;
        throw new IllegalStateException();
    }

    static final void method3517(int i) {
        if (i <= 35) method3517(98);
        anInt4468++;
        Component121.aClass60_4543.clear(0);
    }

    NamedInteger(String string, int i) {
        this.value = i;
    }
}
