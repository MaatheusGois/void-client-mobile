import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** Atomic JSON persistence for local editor scenes; it never touches the JS5 cache. */
final class SceneStore {
    private static final String NUMBER = "\"%s\"\\s*:\\s*(-?\\d+(?:\\.\\d+)?)";
    private static final String STRING = "\"%s\"\\s*:\\s*\"((?:\\\\.|[^\"\\\\])*)\"";
    private static final Pattern OBJECT = Pattern.compile("\\{((?:\"(?:\\\\.|[^\"\\\\])*\"|[^{}])*)\\}");
    private static final Map<String, Pattern> NUMBER_PATTERNS = patterns(NUMBER,
            "version", "region", "id", "objectId", "x", "y", "z", "plane", "rotation", "scale");
    private static final Map<String, Pattern> STRING_PATTERNS = patterns(STRING, "name", "label");
    private final File directory;

    SceneStore() {
        this(new File(System.getProperty("user.home", "."), "void-scenes"));
    }

    SceneStore(File directory) {
        this.directory = directory;
    }

    File file(String name) {
        if (name == null || !name.matches("[A-Za-z0-9._-]{1,64}")) {
            throw new IllegalArgumentException("invalid scene filename");
        }
        return new File(directory, name + ".json");
    }

    void save(String name, Scene scene) throws IOException {
        scene.validate();
        File target = file(name);
        if (!directory.exists() && !directory.mkdirs() && !directory.isDirectory()) {
            throw new IOException("cannot create scene directory");
        }
        File temp = new File(directory, target.getName() + ".tmp");
        File backup = new File(directory, target.getName() + ".bak");
        BufferedWriter out = new BufferedWriter(new FileWriter(temp));
        try {
            out.write(toJson(scene));
        } finally {
            out.close();
        }
        if (target.exists() && !target.renameTo(backup)) {
            temp.delete();
            throw new IOException("cannot create scene backup");
        }
        if (!temp.renameTo(target)) {
            if (backup.exists()) backup.renameTo(target);
            throw new IOException("cannot replace scene file");
        }
    }

    void autosave(Scene scene) throws IOException {
        save("autosave", scene);
    }

    Scene load(String name) throws IOException {
        File target = file(name);
        if (!target.isFile()) throw new IOException("scene does not exist: " + name);
        return parse(read(target));
    }

    private static String read(File file) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(file));
        StringBuilder text = new StringBuilder();
        try {
            String line;
            while ((line = in.readLine()) != null) text.append(line);
        } finally {
            in.close();
        }
        return text.toString();
    }

    static String toJson(Scene scene) {
        StringBuilder out = new StringBuilder();
        out.append("{\"version\":1,\"name\":\"").append(escape(scene.name))
                .append("\",\"region\":").append(scene.region).append(",\"objects\":[");
        boolean first = true;
        for (SceneObject o : scene.objects()) {
            if (!first) out.append(',');
            first = false;
            out.append("{\"id\":").append(o.id).append(",\"objectId\":").append(o.objectId)
                    .append(",\"x\":").append(o.x).append(",\"y\":").append(o.y)
                    .append(",\"z\":").append(o.z).append(",\"plane\":").append(o.plane)
                    .append(",\"rotation\":").append(o.rotation).append(",\"scale\":")
                    .append(o.scale).append(",\"visible\":").append(o.visible)
                    .append(",\"collision\":").append(o.collision);
            if (o.name != null) out.append(",\"label\":\"").append(escape(o.name)).append("\"");
            out.append('}');
        }
        return out.append("]}").toString();
    }

    private static Scene parse(String json) throws IOException {
        try {
            int version = integer(json, "version");
            if (version != Scene.FORMAT_VERSION) throw new IOException("unsupported scene version");
            Scene scene = new Scene(string(json, "name"));
            scene.region = integer(json, "region");
            int start = json.indexOf("\"objects\":[");
            int end = json.lastIndexOf("]}");
            if (start < 0 || end < start) throw new IOException("invalid objects array");
            String body = json.substring(start + 11, end);
            Matcher matcher = OBJECT.matcher(body);
            while (matcher.find()) {
                String item = matcher.group(1);
                SceneObject object = new SceneObject(longValue(item, "id"), integer(item, "objectId"),
                        integer(item, "x"), integer(item, "y"), integer(item, "z"), integer(item, "plane"));
                object.rotation = integer(item, "rotation");
                object.scale = (float) decimal(item, "scale");
                object.visible = bool(item, "visible");
                object.collision = bool(item, "collision");
                object.name = optionalString(item, "label");
                scene.add(object);
            }
            scene.validate();
            return scene;
        } catch (IllegalArgumentException e) {
            throw new IOException("invalid scene: " + e.getMessage());
        }
    }

    private static int integer(String s, String key) { return (int) decimal(s, key); }
    private static long longValue(String s, String key) { return (long) decimal(s, key); }
    private static double decimal(String s, String key) {
        Matcher m = NUMBER_PATTERNS.get(key).matcher(s);
        if (!m.find()) throw new IllegalArgumentException("missing " + key);
        return Double.parseDouble(m.group(1));
    }
    private static boolean bool(String s, String key) {
        return s.matches("(?s).*\"" + key + "\"\\s*:\\s*true.*");
    }
    private static String string(String s, String key) {
        String value = optionalString(s, key);
        if (value == null) throw new IllegalArgumentException("missing " + key);
        return value;
    }
    private static String optionalString(String s, String key) {
        Pattern pattern = STRING_PATTERNS.get(key);
        if (pattern == null) throw new IllegalArgumentException("invalid string field");
        Matcher m = pattern.matcher(s);
        return m.find() ? unescape(m.group(1)) : null;
    }
    private static Map<String, Pattern> patterns(String template, String... keys) {
        Map<String, Pattern> out = new HashMap<String, Pattern>();
        for (String key : keys) out.put(key, Pattern.compile(String.format(template, key)));
        return out;
    }
    private static String escape(String s) { return s.replace("\\", "\\\\").replace("\"", "\\\"").replace("\n", "\\n"); }
    private static String unescape(String s) {
        StringBuilder out = new StringBuilder(s.length());
        boolean escaped = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (escaped) {
                out.append(c == 'n' ? '\n' : c);
                escaped = false;
            } else if (c == '\\') {
                escaped = true;
            } else {
                out.append(c);
            }
        }
        if (escaped) out.append('\\');
        return out.toString();
    }
}
