/* CursorManager - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

/**
 * RENAMED from `Class165` (JODE-obfuscated).
 * Custom cursor manager. Builds BufferedImage-based cursors (setBlankCursor/1282) and installs them on a Component via createCustomCursor; also a blank 1x1 cursor.
 */

import java.awt.*;
import java.awt.image.BufferedImage;

final class CursorManager {
    private final Robot aRobot2176;
    private Component aComponent2177;

    final void setCursorPos(int i, int i_0_) {
        aRobot2176.mouseMove(i, i_0_);
    }

    final void setBlankCursor(Component component, boolean bool) {
        if (bool) component = null;
        else if (component == null) throw new NullPointerException();
        if (component != aComponent2177) {
            if (null != aComponent2177) {
                aComponent2177.setCursor(null);
                aComponent2177 = null;
            }
            if (null != component) {
                component.setCursor(component.getToolkit().createCustomCursor(new BufferedImage(1, 1, 2), new Point(0, 0), null));
                aComponent2177 = component;
            }
        }
    }

    final void setCustomCursor(Component component, int[] is, int i, int i_1_, Point point) {
        if (is != null) {
            BufferedImage bufferedimage = new BufferedImage(i, i_1_, 2);
            bufferedimage.setRGB(0, 0, i, i_1_, is, 0, i);
            component.setCursor(component.getToolkit().createCustomCursor(bufferedimage, point, null));
        } else component.setCursor(null);
    }

    CursorManager() throws Exception {
        aRobot2176 = new Robot();
    }
}
