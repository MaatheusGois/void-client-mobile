/* SoftReferenceHolder - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

/**
 * RENAMED from `Class348_Sub42_Sub9_Sub2` (JODE-obfuscated).
 * Concrete soft-reference holder. extends ReferenceHolder; stores a SoftReference (aSoftReference10446) and returns it via method3205.
 */

import java.lang.ref.SoftReference;

final class SoftReferenceHolder extends ReferenceHolder {
    private SoftReference aSoftReference10446;

    final Object method3205(int i) {
        if (i != 65536) aSoftReference10446 = null;
        return aSoftReference10446.get();
    }

    final boolean method3206(byte i) {
        int i_0_ = -88 % ((-63 - i) / 61);
        return true;
    }

    SoftReferenceHolder(Interface14 interface14, Object object, int i) {
        super(interface14, i);
        aSoftReference10446 = new SoftReference(object);
    }
}
