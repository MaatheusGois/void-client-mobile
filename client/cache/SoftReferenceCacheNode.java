/* SoftReferenceCacheNode - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

/**
 * RENAMED from `Class348_Sub42_Sub8_Sub1` (JODE-obfuscated).
 * Soft-reference cache entry. extends CacheNode; wraps a SoftReference (aSoftReference10428) and returns its referent via getValue.
 */

import java.lang.ref.SoftReference;

final class SoftReferenceCacheNode extends CacheNode {
    private final SoftReference aSoftReference10428;

    final boolean isSoft(int i) {
        if (i != -4) getValue(-41);
        return true;
    }

    final Object getValue(int i) {
        if (i <= 75) return null;
        return aSoftReference10428.get();
    }

    SoftReferenceCacheNode(Object object, int i) {
        super(i);
        aSoftReference10428 = new SoftReference(object);
    }
}
