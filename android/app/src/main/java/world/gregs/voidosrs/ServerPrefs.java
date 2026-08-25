package world.gregs.voidosrs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

/**
 * Persisted JS5/login hosts ({@code user.home/void-server.txt}), newest first, max 5.
 * Shared Android + iOS (copied into the iOS source set).
 */
public final class ServerPrefs {
    public static final int MAX_HISTORY = 5;

    private ServerPrefs() {
    }

    public static File file() {
        String home = System.getProperty("user.home", ".");
        return new File(home, "void-server.txt");
    }

    public static String load() {
        String[] all = loadAll();
        return all.length > 0 ? all[0] : null;
    }

    public static String[] loadAll() {
        File f = file();
        if (!f.isFile()) {
            return new String[0];
        }
        ArrayList<String> out = new ArrayList<String>();
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(f));
            String line;
            while ((line = in.readLine()) != null && out.size() < MAX_HISTORY) {
                String n = normalize(line);
                if (n != null && !out.contains(n)) {
                    out.add(n);
                }
            }
        } catch (Throwable ignored) {
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Throwable ignored) {
                }
            }
        }
        return out.toArray(new String[out.size()]);
    }

    public static void save(String host) {
        String n = normalize(host);
        if (n == null) {
            return;
        }
        ArrayList<String> out = new ArrayList<String>();
        out.add(n);
        String[] old = loadAll();
        for (int i = 0; i < old.length && out.size() < MAX_HISTORY; i++) {
            if (!n.equals(old[i])) {
                out.add(old[i]);
            }
        }
        FileWriter w = null;
        try {
            File f = file();
            File parent = f.getParentFile();
            if (parent != null) {
                parent.mkdirs();
            }
            w = new FileWriter(f);
            for (int i = 0; i < out.size(); i++) {
                w.write(out.get(i));
                w.write('\n');
            }
        } catch (Throwable t) {
            t.printStackTrace();
        } finally {
            if (w != null) {
                try {
                    w.close();
                } catch (Throwable ignored) {
                }
            }
        }
    }

    /** {@code http://192.168.1.10:43594/} → {@code 192.168.1.10} */
    public static String normalize(String raw) {
        if (raw == null) {
            return null;
        }
        String s = raw.trim();
        if (s.length() == 0) {
            return null;
        }
        int scheme = s.indexOf("://");
        if (scheme >= 0) {
            s = s.substring(scheme + 3);
        }
        int slash = s.indexOf('/');
        if (slash >= 0) {
            s = s.substring(0, slash);
        }
        int at = s.indexOf('@');
        if (at >= 0) {
            s = s.substring(at + 1);
        }
        if (s.startsWith("[")) {
            int end = s.indexOf(']');
            if (end > 0) {
                s = s.substring(1, end);
            }
        } else {
            int colon = s.lastIndexOf(':');
            if (colon > 0) {
                boolean digits = true;
                for (int i = colon + 1; i < s.length(); i++) {
                    if (s.charAt(i) < '0' || s.charAt(i) > '9') {
                        digits = false;
                        break;
                    }
                }
                if (digits) {
                    s = s.substring(0, colon);
                }
            }
        }
        s = s.trim();
        return s.length() == 0 ? null : s;
    }

    /** Title / lobby / account screens — not splash (0–2) and not in-world (10). */
    public static int gameState() {
        try {
            java.lang.reflect.Field f = Class.forName("Class240").getDeclaredField("anInt4674");
            f.setAccessible(true);
            return f.getInt(null);
        } catch (Throwable ignored) {
            return -1;
        }
    }

    public static boolean isLoginScreen() {
        int state = gameState();
        return state == 3 || state == 4 || state == 5 || state == 6
                || state == 7 || state == 8 || state == 9 || state == 12;
    }

    /** Fatal JS5 / connect error page ({@code error_game_js5connect}). */
    public static boolean isConnectFailScreen() {
        return gameState() == 14;
    }

    /** JS5 retry counter — increments on each Connect/handshake fail. */
    public static int js5FailCount() {
        try {
            java.lang.reflect.Field inst = Class.forName("Class348_Sub4").getDeclaredField("aClass248_6601");
            inst.setAccessible(true);
            Object js5 = inst.get(null);
            if (js5 == null) {
                return 0;
            }
            java.lang.reflect.Field c = js5.getClass().getDeclaredField("anInt3213");
            c.setAccessible(true);
            return c.getInt(js5);
        } catch (Throwable ignored) {
            return 0;
        }
    }

    /** Splash still running and JS5 already failed at least once — don't wait for state 14. */
    public static boolean isConnectFailing() {
        if (isConnectFailScreen()) {
            return true;
        }
        int state = gameState();
        return state >= 0 && state <= 2 && js5FailCount() >= 1;
    }

    public static boolean showsServerPicker() {
        return isLoginScreen() || isConnectFailing();
    }
}
