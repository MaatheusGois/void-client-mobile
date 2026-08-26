/**
 * NPC query + interact (menu-inject Attack / action).
 * Attack opcode slot 0 = 25 (see {@link Class286_Sub3#method2150}).
 */
final class Rs2Npc {

    static final int OPCODE_ATTACK_0 = 25;
    static final int OPCODE_ACTION_1 = 20;
    static final int OPCODE_ACTION_2 = 44;
    static final int OPCODE_ACTION_3 = 46;
    static final int OPCODE_ACTION_4 = 60;

    private Rs2Npc() {
    }

    static Npc[] getAll() {
        return MicrobotRuntime.npcCache.snapshot();
    }

    static Npc getNearest(String name) {
        Npc best = null;
        int bestDist = Integer.MAX_VALUE;
        Npc[] all = getAll();
        for (int i = 0; i < all.length; i++) {
            Npc n = all[i];
            if (n == null || n.aClass79_10505 == null) {
                continue;
            }
            String nname = n.aClass79_10505.aString1372;
            if (name != null && (nname == null || !nname.equalsIgnoreCase(name))) {
                continue;
            }
            int d = Rs2Player.distanceTo(n);
            if (d < bestDist) {
                bestDist = d;
                best = n;
            }
        }
        return best;
    }

    static Npc getNearestAttackable() {
        String attack = attackLabel();
        Npc best = null;
        int bestDist = Integer.MAX_VALUE;
        Npc[] all = getAll();
        for (int i = 0; i < all.length; i++) {
            Npc n = all[i];
            if (n == null || n.aClass79_10505 == null) {
                continue;
            }
            Class79 def = n.aClass79_10505;
            if (def.anIntArray1377 != null) {
                def = def.method794(Class318_Sub1_Sub3_Sub3.aClass170_10209, -1);
                if (def == null) {
                    continue;
                }
            }
            if (!hasAction(def, attack)) {
                continue;
            }
            int d = Rs2Player.distanceTo(n);
            if (d < bestDist) {
                bestDist = d;
                best = n;
            }
        }
        return best;
    }

    static boolean interact(Npc npc, String action) {
        if (npc == null || action == null) {
            return false;
        }
        Class79 def = npc.aClass79_10505;
        if (def == null) {
            return false;
        }
        if (def.anIntArray1377 != null) {
            def = def.method794(Class318_Sub1_Sub3_Sub3.aClass170_10209, -1);
            if (def == null) {
                return false;
            }
        }
        int opcode = opcodeForAction(def, action);
        if (opcode <= 0) {
            return false;
        }
        String target = "<col=ffff00>" + def.aString1372;
        NewMenuEntry entry = new NewMenuEntry(action, target, opcode, npc.anInt10290, 0, 0, -1);
        Microbot.doInvoke(entry);
        return true;
    }

    static boolean attack(Npc npc) {
        return interact(npc, attackLabel());
    }

    /** Exposed for diagnostics / ExampleCombat. */
    static String attackLabelPublic() {
        return attackLabel();
    }

    private static String attackLabel() {
        try {
            return Class274.aClass274_3506.method2063(Class348_Sub33.anInt6967, 544);
        } catch (Throwable t) {
            return "Attack";
        }
    }

    private static boolean hasAction(Class79 def, String action) {
        String[] actions = def.aStringArray1349;
        if (actions == null) {
            return false;
        }
        for (int i = 0; i < actions.length && i < 5; i++) {
            if (actions[i] != null && actions[i].equalsIgnoreCase(action)) {
                return true;
            }
        }
        return false;
    }

    private static int opcodeForAction(Class79 def, String action) {
        String[] actions = def.aStringArray1349;
        if (actions == null) {
            return -1;
        }
        int[] ops = {OPCODE_ATTACK_0, OPCODE_ACTION_1, OPCODE_ACTION_2, OPCODE_ACTION_3, OPCODE_ACTION_4};
        for (int i = 0; i < 5 && i < actions.length; i++) {
            if (actions[i] != null && actions[i].equalsIgnoreCase(action)) {
                return ops[i];
            }
        }
        return -1;
    }
}
