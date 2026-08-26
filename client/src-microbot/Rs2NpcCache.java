/**
 * Per-tick NPC snapshot (Queryable-lite). Refresh when {@link Class367_Sub11#clientCycle}
 * advances. Source: {@link Class282#aClass356_3654} + {@link Class74#anIntArray1233}.
 */
final class Rs2NpcCache {

    private int lastTick = -1;
    private Npc[] npcs = new Npc[0];
    private int count;

    Rs2NpcCache() {
    }

    void refreshIfNeeded() {
        int tick = Class367_Sub11.clientCycle;
        if (tick == lastTick) {
            return;
        }
        lastTick = tick;
        int n = Class150.anInt2057;
        if (n < 0) {
            n = 0;
        }
        if (npcs.length < n) {
            npcs = new Npc[Math.max(n, 64)];
        }
        count = 0;
        for (int i = 0; i < Class150.anInt2057; i++) {
            try {
                Class348_Sub22 node = (Class348_Sub22) Class282.aClass356_3654.method3480(Class74.anIntArray1233[i], -6008);
                if (node == null || node.aNpc_6859 == null) {
                    continue;
                }
                npcs[count++] = node.aNpc_6859;
            } catch (Throwable ignored) {
            }
        }
    }

    int size() {
        refreshIfNeeded();
        return count;
    }

    Npc get(int i) {
        refreshIfNeeded();
        if (i < 0 || i >= count) {
            return null;
        }
        return npcs[i];
    }

    Npc[] snapshot() {
        refreshIfNeeded();
        Npc[] out = new Npc[count];
        System.arraycopy(npcs, 0, out, 0, count);
        return out;
    }
}
