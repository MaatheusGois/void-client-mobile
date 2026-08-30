import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/** Versioned, local-only collection of objects for the scene editor. */
final class Scene {
    static final int FORMAT_VERSION = 1;
    static final int MAX_OBJECTS = 10000;

    String name;
    int region;
    private final LinkedHashMap<Long, SceneObject> objects = new LinkedHashMap<Long, SceneObject>();

    Scene(String name) {
        this.name = name == null || name.length() == 0 ? "default" : name;
    }

    Scene copy() {
        Scene copy = new Scene(name);
        copy.region = region;
        for (SceneObject object : objects.values()) {
            copy.objects.put(object.id, object.copy());
        }
        return copy;
    }

    Collection<SceneObject> objects() {
        return new ArrayList<SceneObject>(objects.values());
    }

    SceneObject get(long id) {
        return objects.get(id);
    }

    void add(SceneObject object) {
        if (object == null) throw new IllegalArgumentException("object is null");
        object.validate();
        if (objects.size() >= MAX_OBJECTS && !objects.containsKey(object.id)) {
            throw new IllegalArgumentException("scene object limit exceeded");
        }
        if (objects.containsKey(object.id)) {
            throw new IllegalArgumentException("duplicate scene object id: " + object.id);
        }
        objects.put(object.id, object);
    }

    SceneObject remove(long id) {
        return objects.remove(id);
    }

    void validate() {
        if (name == null || name.length() == 0 || name.length() > 128) {
            throw new IllegalArgumentException("invalid scene name");
        }
        if (region < 0) throw new IllegalArgumentException("invalid region");
        for (Map.Entry<Long, SceneObject> entry : objects.entrySet()) {
            if (entry.getKey().longValue() != entry.getValue().id) {
                throw new IllegalArgumentException("invalid scene object index");
            }
            entry.getValue().validate();
        }
    }
}
