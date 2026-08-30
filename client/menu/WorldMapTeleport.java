/**
 * Client-only Teleport action for world-map activity icons.
 *
 * MapElement category 3 is the cache's Activities category. Add future
 * allowed categories to {@link #ALLOWED_CATEGORIES} without changing the hook.
 */
final class WorldMapTeleport {

    /** Private client-only opcode; it must never reach the game protocol. */
    static final int OPCODE_TELEPORT = 4900;

    /** Cache category ids allowed to receive the Teleport action. */
    private static final int[] ALLOWED_CATEGORIES = {3};

    private WorldMapTeleport() {
    }

    static void inject(NodeSub12 hit, Component274 type) {
        if (Component353.anInt2581 <= 0 || hit == null || type == null
                || !isAllowedCategory(type.anInt596) || hit.aClass348_Sub21_6751 == null) {
            return;
        }
        NodeSub21 element = hit.aClass348_Sub21_6751;
        int x = element.anInt6852 + DisplayModeManagerContainer229.anInt1266;
        int y = element.anInt6851 + DisplayModeManagerContainer229.anInt1263;
        int plane = element.anInt6850;
        if (plane < 0 || plane > 3) {
            plane = Component72.localPlayer != null ? Component72.localPlayer.plane : 0;
        }
        DisplayModeManagerContainer368.addMenuEntry(
                false, type.aString565, y, (byte) -93, false, x, -1, true,
                OPCODE_TELEPORT, plane, "Teleport", 0L, -1);
    }

    static boolean handle(MenuEntry entry) {
        if (entry == null) {
            return false;
        }
        int opcode = entry.opcode >= 2000 ? entry.opcode - 2000 : entry.opcode;
        if (opcode != OPCODE_TELEPORT) {
            return false;
        }
        CommandHandler.handleCommand(
                "tele " + entry.param0 + " " + entry.param1 + " " + (int) entry.identifier,
                true, false, (byte) -79);
        return true;
    }

    private static boolean isAllowedCategory(int category) {
        for (int allowed : ALLOWED_CATEGORIES) {
            if (allowed == category) {
                return true;
            }
        }
        return false;
    }
}
