/**
 * Local scene object edited by the optional scene editor.
 *
 * <p>This model deliberately does not depend on the obfuscated renderer classes.
 * It can therefore be persisted and tested without starting the client.</p>
 */
final class SceneObject {
    static final int MAX_ID = 0x7fffffff;

    final long id;
    int objectId;
    int x;
    int y;
    int z;
    int plane;
    int rotation;
    float scale = 1.0f;
    boolean visible = true;
    boolean collision = true;
    String name;

    SceneObject(long id, int objectId, int x, int y, int z, int plane) {
        this.id = id;
        this.objectId = objectId;
        this.x = x;
        this.y = y;
        this.z = z;
        this.plane = plane;
        validate();
    }

    SceneObject copy() {
        SceneObject copy = new SceneObject(id, objectId, x, y, z, plane);
        copy.rotation = rotation;
        copy.scale = scale;
        copy.visible = visible;
        copy.collision = collision;
        copy.name = name;
        return copy;
    }

    void validate() {
        if (id < 0 || id > MAX_ID || objectId < 0 || objectId > MAX_ID) {
            throw new IllegalArgumentException("invalid scene object id");
        }
        if (plane < 0 || plane > 3) {
            throw new IllegalArgumentException("plane must be between 0 and 3");
        }
        if (scale <= 0.0f || scale > 100.0f || Float.isNaN(scale)
                || Float.isInfinite(scale)) {
            throw new IllegalArgumentException("scale must be between 0 and 100");
        }
        rotation &= 2047;
    }
}
