import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;

/** Deterministic command facade for UI, gamepad, and future AI adapters. */
final class SceneEditor {
    enum Mode { GAME, EDITOR, PREVIEW }
    private static final int HISTORY_LIMIT = 100;
    private final SceneStore store;
    private Scene scene = new Scene("default");
    private final Deque<Scene> undo = new ArrayDeque<Scene>();
    private final Deque<Scene> redo = new ArrayDeque<Scene>();
    private long nextId = 1;
    private Mode mode = Mode.GAME;
    private boolean dirty;

    SceneEditor(SceneStore store) { this.store = store; }
    Scene scene() { return scene; }
    Mode mode() { return mode; }
    boolean isDirty() { return dirty; }
    void setMode(Mode mode) { this.mode = mode == null ? Mode.GAME : mode; }

    SceneObject add(int objectId, int x, int y, int z, int plane) {
        final long id = nextId;
        final SceneObject object = new SceneObject(id, objectId, x, y, z, plane);
        change(new Runnable() { public void run() { scene.add(object); }});
        nextId = id + 1;
        return object;
    }
    void remove(final long id) {
        change(new Runnable() { public void run() {
            if (scene.remove(id) == null) throw new IllegalArgumentException("object not found: " + id);
        }});
    }
    void move(final long id, final int x, final int y, final int z) {
        change(new Runnable() { public void run() {
            SceneObject o = required(id); o.x = x; o.y = y; o.z = z; o.validate();
        }});
    }
    void rotate(final long id, final int rotation) {
        change(new Runnable() { public void run() { SceneObject o = required(id); o.rotation = rotation; o.validate(); }});
    }
    void scale(final long id, final float scale) {
        change(new Runnable() { public void run() { SceneObject o = required(id); o.scale = scale; o.validate(); }});
    }
    void undo() {
        if (!undo.isEmpty()) {
            redo.push(scene);
            while (redo.size() > HISTORY_LIMIT) redo.removeLast();
            scene = undo.pop();
            dirty = true;
        }
    }
    void redo() {
        if (!redo.isEmpty()) {
            undo.push(scene);
            while (undo.size() > HISTORY_LIMIT) undo.removeLast();
            scene = redo.pop();
            dirty = true;
        }
    }
    void save(String name) throws IOException { store.save(name, scene); dirty = false; }
    void load(String name) throws IOException { scene = store.load(name); undo.clear(); redo.clear(); dirty = false; }
    void autosave() throws IOException { store.autosave(scene); }

    /** Parses only local, deterministic commands; no arbitrary code or file paths are accepted. */
    String command(String input) throws IOException {
        if (input == null) throw new IllegalArgumentException("empty command");
        String[] p = input.trim().split("\\s+");
        if (p.length == 0) throw new IllegalArgumentException("empty command");
        if ("add".equals(p[0]) && p.length == 6) return "added " + add(Integer.parseInt(p[1]), Integer.parseInt(p[2]), Integer.parseInt(p[3]), Integer.parseInt(p[4]), Integer.parseInt(p[5])).id;
        if ("move".equals(p[0]) && p.length == 5) { move(Long.parseLong(p[1]), Integer.parseInt(p[2]), Integer.parseInt(p[3]), Integer.parseInt(p[4])); return "moved"; }
        if ("rotate".equals(p[0]) && p.length == 3) { rotate(Long.parseLong(p[1]), Integer.parseInt(p[2])); return "rotated"; }
        if ("scale".equals(p[0]) && p.length == 3) { scale(Long.parseLong(p[1]), Float.parseFloat(p[2])); return "scaled"; }
        if ("remove".equals(p[0]) && p.length == 2) { remove(Long.parseLong(p[1])); return "removed"; }
        if ("undo".equals(p[0]) && p.length == 1) { undo(); return "undone"; }
        if ("redo".equals(p[0]) && p.length == 1) { redo(); return "redone"; }
        if ("save".equals(p[0]) && p.length == 2) { save(p[1]); return "saved"; }
        throw new IllegalArgumentException("unsupported editor command");
    }

    private SceneObject required(long id) {
        SceneObject o = scene.get(id);
        if (o == null) throw new IllegalArgumentException("object not found: " + id);
        return o;
    }
    private void change(Runnable operation) {
        Scene before = scene.copy();
        operation.run();
        undo.push(before);
        while (undo.size() > HISTORY_LIMIT) undo.removeLast();
        redo.clear();
        dirty = true;
    }
}
