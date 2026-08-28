/**
 * Inventory backpack facade — scans loaded interface trees for item-bearing
 * components ({@link DisplayModeManagerContainer57#anInt812} &gt; 0). Interact via {@link Rs2Widget}.
 */
final class Rs2Inventory {

    private Rs2Inventory() {
    }

    static int count(int itemId) {
        int total = 0;
        DisplayModeManagerContainer57[] slots = slots();
        for (int i = 0; i < slots.length; i++) {
            if (slots[i] != null && slots[i].anInt812 == itemId) {
                total++;
            }
        }
        return total;
    }

    static boolean contains(int itemId) {
        return count(itemId) > 0;
    }

    static boolean isFull() {
        DisplayModeManagerContainer57[] slots = slots();
        int filled = 0;
        int capacity = 0;
        for (int i = 0; i < slots.length; i++) {
            if (slots[i] == null) {
                continue;
            }
            capacity++;
            if (slots[i].anInt812 > 0) {
                filled++;
            }
        }
        return capacity > 0 && filled >= capacity;
    }

    static boolean interact(int itemId, String action) {
        DisplayModeManagerContainer57 slot = findSlot(itemId);
        if (slot == null) {
            return false;
        }
        NewMenuEntry entry = new NewMenuEntry(action,
                slot.aString752 != null ? slot.aString752 : "",
                Rs2Widget.OPCODE_CC_OP,
                1L,
                slot.anInt704,
                slot.anInt830,
                itemId);
        Microbot.doInvoke(entry);
        return true;
    }

    static boolean drop(int itemId) {
        return interact(itemId, "Drop");
    }

    static DisplayModeManagerContainer57 findSlot(int itemId) {
        DisplayModeManagerContainer57[] slots = slots();
        for (int i = 0; i < slots.length; i++) {
            if (slots[i] != null && slots[i].anInt812 == itemId) {
                return slots[i];
            }
        }
        return null;
    }

    /**
     * Collect item components under inventory-ish groups. Prefers group
     * {@link MicrobotWidgets#INVENTORY_GROUP}, else any visible item grid.
     */
    static DisplayModeManagerContainer57[] slots() {
        java.util.ArrayList list = new java.util.ArrayList();
        collectGroup(MicrobotWidgets.INVENTORY_GROUP, list);
        if (list.isEmpty()) {
            // Fallback: first group with many item children
            if (DefinitionSub33.aClass46ArrayArray9427 != null) {
                DisplayModeManagerContainer57[][] roots = DefinitionSub33.aClass46ArrayArray9427;
                for (int g = 0; g < roots.length; g++) {
                    int before = list.size();
                    collectGroup(g, list);
                    if (list.size() - before >= 20) {
                        break;
                    }
                    // discard sparse groups
                    while (list.size() > before) {
                        list.remove(list.size() - 1);
                    }
                }
            }
        }
        return (DisplayModeManagerContainer57[]) list.toArray(new DisplayModeManagerContainer57[list.size()]);
    }

    private static void collectGroup(int group, java.util.ArrayList list) {
        if (DefinitionSub33.aClass46ArrayArray9427 == null) {
            return;
        }
        if (group < 0 || group >= DefinitionSub33.aClass46ArrayArray9427.length) {
            return;
        }
        DisplayModeManagerContainer57[] all = DefinitionSub33.aClass46ArrayArray9427[group];
        if (all == null) {
            return;
        }
        for (int i = 0; i < all.length; i++) {
            collectItems(all[i], list);
        }
    }

    private static void collectItems(DisplayModeManagerContainer57 c, java.util.ArrayList list) {
        if (c == null) {
            return;
        }
        if (c.anInt812 > 0) {
            list.add(c);
        }
        if (c.aClass46Array798 != null) {
            for (int i = 0; i < c.aClass46Array798.length; i++) {
                collectItems(c.aClass46Array798[i], list);
            }
        }
    }
}
