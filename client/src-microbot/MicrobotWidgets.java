/**
 * 634 widget / inventory / bank group ids discovered for Void.
 * Do not copy OSRS Microbot globval — revise as ifaces are confirmed in-game.
 */
final class MicrobotWidgets {

    /**
     * Inventory backpack root — common RS2-era id; overridden by scan when
     * {@link #findInventoryRoot()} finds a panel with item children.
     */
    static int INVENTORY_GROUP = 149;

    /** Bank main interface (placeholder — scan prefers open bank by item grid). */
    static int BANK_GROUP = 762;

    private MicrobotWidgets() {
    }

    static Class46 get(int packedId) {
        try {
            return BitmapFont.method2570(1512932720, packedId);
        } catch (Throwable t) {
            return null;
        }
    }

    static Class46 getChild(int packedParent, int child) {
        try {
            return Class348_Sub22.method2957(child, (byte) -54, packedParent);
        } catch (Throwable t) {
            return null;
        }
    }

    /** Absolute scene tile X for local player. */
    static int localAbsX() {
        Player p = Class132.localPlayer;
        if (p == null) {
            return -1;
        }
        return p.anIntArray10320[0] + za_Sub2.regionTileX;
    }

    static int localAbsY() {
        Player p = Class132.localPlayer;
        if (p == null) {
            return -1;
        }
        return p.anIntArray10317[0] + Class90.regionTileY;
    }

    static int localPlane() {
        Player p = Class132.localPlayer;
        return p != null ? p.plane : 0;
    }
}
