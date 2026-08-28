/* SoftReferenceCacheNode - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

/**
 * RENAMED from `Class348_Sub42_Sub8_Sub1` (JODE-obfuscated).
 * Soft-reference cache entry. extends CacheNode; wraps a SoftReference (aSoftReference10428) and returns its referent via method3193.
 */

import java.lang.ref.SoftReference;

final class SoftReferenceCacheNode extends CacheNode {
    private final SoftReference aSoftReference10428;

    final boolean method3195(int i) {
        if (i != -4) method3193(-41);
        return true;
    }

    final Object method3193(int i) {
        if (i <= 75) return null;
        return aSoftReference10428.get();
    }

    SoftReferenceCacheNode(Object object, int i) {
        super(i);
        aSoftReference10428 = new SoftReference(object);
    }
}
