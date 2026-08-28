/* ToolkitLoader - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

/**
 * RENAMED from `Class96` (JODE-obfuscated).
 * Toolkit bootstrap/loader. Uses reflection (D3DToolkit.class.getDeclaredMethod("createToolkit", Canvas, d, CacheStore, Integer)) to locate and invoke the active toolkit's createToolkit factory method at client startup; selects the GL/D3D implementation.
 */

import java.awt.*;
import java.lang.reflect.Method;

final class ToolkitLoader {
    static final ha method870(int i, int i_0_, d var_d, CacheStore class45, Canvas canvas) {
        if (i_0_ != 18993) return null;
        ha var_ha;
        try {
            if (!JaclibLoader.method215(27165)) throw new RuntimeException("");
            if (!DefinitionSub19.method3098(-30282, "jagdx")) throw new RuntimeException("");
            Method method = (D3DToolkit.class.getDeclaredMethod("createToolkit", Canvas.class, d.class, CacheStore.class, Integer.class));
            var_ha = (ha) method.invoke(null, new Object[]{canvas, var_d, class45, new Integer(i)});
        } catch (Throwable throwable) {
            throw new RuntimeException("");
        }
        return var_ha;
    }
}
