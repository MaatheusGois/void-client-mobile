import java.io.IOException;

/**
 * Process-wide scene editor host: model ({@link SceneEditor}) + live adapter.
 * <p>
 * Console / Microbot call this — never talk to {@link SceneManager} directly.
 * All mutations that should appear in-world call {@link #resync()} afterward.
 */
final class SceneEditorHost {
    private static SceneEditor editor;

    private SceneEditorHost() {
    }

    static SceneEditor editor() {
        if (editor == null) {
            editor = new SceneEditor(new SceneStore());
        }
        return editor;
    }

    static boolean isEditorMode() {
        return editor().mode() == SceneEditor.Mode.EDITOR;
    }

    static void setEditorMode(boolean on) {
        editor().setMode(on ? SceneEditor.Mode.EDITOR : SceneEditor.Mode.GAME);
        if (!on) {
            LiveSceneBridge.clearLive();
            SceneEditorUi.onEditorDisabled();
        } else {
            resync();
            SceneEditorUi.onEditorEnabled();
        }
    }

    /** Re-place every object from the current model into the live scene. */
    static int resync() {
        return LiveSceneBridge.resync(editor().scene());
    }

    /**
     * Spawn LocType {@code objectId} at the local player's absolute tile.
     * {@code z}/{@code scale} are stored but ignored by the stock placer.
     */
    static String spawnAtPlayer(int objectId) throws IOException {
        if (Component72.localPlayer == null) {
            throw new IllegalStateException("not logged in");
        }
        int x = MicrobotWidgets.localAbsX();
        int y = MicrobotWidgets.localAbsY();
        int plane = MicrobotWidgets.localPlane();
        SceneObject added = editor().add(objectId, x, y, 0, plane);
        int placed = resync();
        String name = SceneObjectAdapter.nameOf(objectId);
        return "added " + added.id + (name != null ? " (" + name + ")" : "")
                + " @ " + x + "," + y + "," + plane
                + " live=" + placed;
    }

    /**
     * Run a model command then resync the live scene.
     * Extra host commands: {@code spawn <objectId>}, {@code apply}, {@code clear},
     * {@code load <name>}, {@code mode [editor|game]}, {@code status}.
     */
    static String command(String input) throws IOException {
        if (input == null) {
            throw new IllegalArgumentException("empty command");
        }
        String trimmed = input.trim();
        if (trimmed.length() == 0) {
            throw new IllegalArgumentException("empty command");
        }
        String[] p = trimmed.split("\\s+");
        String op = p[0].toLowerCase();

        if ("status".equals(op) && p.length == 1) {
            Scene s = editor().scene();
            return "mode=" + editor().mode()
                    + " objects=" + s.objects().size()
                    + " live=" + LiveSceneBridge.liveCount()
                    + " dirty=" + editor().isDirty()
                    + " name=" + s.name;
        }
        if ("mode".equals(op)) {
            if (p.length == 1) {
                return "mode=" + editor().mode();
            }
            if ("editor".equalsIgnoreCase(p[1]) || "on".equalsIgnoreCase(p[1])) {
                setEditorMode(true);
                return "mode=EDITOR live=" + LiveSceneBridge.liveCount();
            }
            if ("game".equalsIgnoreCase(p[1]) || "off".equalsIgnoreCase(p[1])) {
                setEditorMode(false);
                return "mode=GAME";
            }
            throw new IllegalArgumentException("mode editor|game");
        }
        if ("spawn".equals(op) && p.length == 2) {
            if (!isEditorMode()) {
                setEditorMode(true);
            }
            return spawnAtPlayer(Integer.parseInt(p[1]));
        }
        if ("apply".equals(op) && p.length == 1) {
            return "applied live=" + resync();
        }
        if ("clear".equals(op) && p.length == 1) {
            editor().clearObjects();
            LiveSceneBridge.clearLive();
            return "cleared";
        }
        if ("load".equals(op) && p.length == 2) {
            editor().load(p[1]);
            if (!isEditorMode()) {
                setEditorMode(true);
            }
            return "loaded " + p[1] + " live=" + resync();
        }

        // Delegate to model commands; resync after mutations.
        String result = editor().command(trimmed);
        if ("save".equals(op)) {
            return result;
        }
        int live = resync();
        return result + " live=" + live;
    }

    static void printHelp() {
        Applet_Sub1.printConsole("ed mode [editor|game] — toggle local scene editor", 80);
        Applet_Sub1.printConsole("ed spawn <objectId> — place LocType at your tile", 80);
        Applet_Sub1.printConsole("ed add <objectId> <x> <y> <z> <plane>", 80);
        Applet_Sub1.printConsole("ed move <id> <x> <y> <z> | rotate <id> <0-3> | scale <id> <f>", 80);
        Applet_Sub1.printConsole("ed remove <id> | undo | redo | clear | apply", 80);
        Applet_Sub1.printConsole("ed save <name> | load <name> | status", 80);
        Applet_Sub1.printConsole("(z/scale stored only; placer uses terrain height + rot 0-3)", 80);
    }
}
