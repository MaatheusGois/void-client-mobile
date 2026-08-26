/**
 * Widget tree helpers — find by text / packed id, click via menu-inject.
 * Interface item ops: 18 / 1011 (CC_OP), 13 (Use) — see {@link Class239_Sub17}.
 */
final class Rs2Widget {

    static final int OPCODE_CC_OP = 18;
    static final int OPCODE_CC_OP_HIGH = 1011;
    static final int OPCODE_USE = 13;

    private Rs2Widget() {
    }

    static Class46 get(int packedId) {
        return MicrobotWidgets.get(packedId);
    }

    static boolean isVisible(Class46 w) {
        return w != null && !w.aBoolean813;
    }

    static Class46 findByText(String text) {
        if (text == null || Class348_Sub40_Sub33.aClass46ArrayArray9427 == null) {
            return null;
        }
        Class46[][] roots = Class348_Sub40_Sub33.aClass46ArrayArray9427;
        for (int g = 0; g < roots.length; g++) {
            Class46[] all = roots[g];
            if (all == null) {
                continue;
            }
            for (int i = 0; i < all.length; i++) {
                Class46 c = all[i];
                if (c == null) {
                    continue;
                }
                if (c.aString752 != null && c.aString752.toLowerCase().indexOf(text.toLowerCase()) >= 0) {
                    return c;
                }
                if (c.aClass46Array798 != null) {
                    Class46 nested = findInChildren(c.aClass46Array798, text);
                    if (nested != null) {
                        return nested;
                    }
                }
            }
        }
        return null;
    }

    private static Class46 findInChildren(Class46[] kids, String text) {
        if (kids == null) {
            return null;
        }
        for (int i = 0; i < kids.length; i++) {
            Class46 c = kids[i];
            if (c == null) {
                continue;
            }
            if (c.aString752 != null && c.aString752.toLowerCase().indexOf(text.toLowerCase()) >= 0) {
                return c;
            }
            if (c.aClass46Array798 != null) {
                Class46 nested = findInChildren(c.aClass46Array798, text);
                if (nested != null) {
                    return nested;
                }
            }
        }
        return null;
    }

    /**
     * Left-click a component option (Wear / Withdraw-1 / …) via menu inject.
     */
    static boolean click(Class46 component, String option) {
        if (component == null || option == null) {
            return false;
        }
        int packed = (component.anInt704 << 0) | component.anInt830;
        NewMenuEntry entry = new NewMenuEntry(option, component.aString752 != null ? component.aString752 : "",
                OPCODE_CC_OP, 1L, component.anInt704, component.anInt830, component.anInt812);
        // identifier for item rows is often 1+slot — anInt9605 from method466 uses 1+i
        entry.setIdentifier(1L);
        entry.setParam0(component.anInt704);
        entry.setParam1(component.anInt830);
        Microbot.doInvoke(entry);
        return true;
    }

    static boolean click(String text) {
        Class46 w = findByText(text);
        return w != null && click(w, text);
    }
}
