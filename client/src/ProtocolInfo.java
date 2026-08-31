/**
 * Versioned protocol and cache settings shared by the desktop and rewritten mobile
 * client sources.
 *
 * <p>The 667 source has been audited and pinned, but its packet, cache, and RSA
 * implementations have not been imported. The current client therefore exposes
 * only the working 634 profile and rejects attempts to run the incomplete target
 * profile instead of sending 634 packets with a 667 revision.</p>
 */
public final class ProtocolInfo {
    public static final int REVISION_634 = 634;
    public static final int REVISION_667 = 667;
    public static final int DEFAULT_REVISION = REVISION_634;

    /** Source audited for the 667 migration. */
    public static final String SOURCE_REPOSITORY = "https://github.com/StrongHold/runescape-667";
    public static final String SOURCE_COMMIT = "f45c5e8a9e3369a7aeb11b68510fd00d4445d683";

    private static final String REVISION_PROPERTY = "void.protocol";
    private static final String PORT_PROPERTY = "void.port";
    private static final String SECONDARY_PORT_PROPERTY = "void.secondary-port";
    private static volatile int selectedRevision = -1;

    private ProtocolInfo() {
    }

    /**
     * Returns the implemented client revision. The target 667 profile is rejected
     * by {@link #ensureSupported()} until its core and protocol ports are complete.
     */
    public static int revision() {
        int selected = selectedRevision;
        if (selected == REVISION_634) {
            return selected;
        }
        synchronized (ProtocolInfo.class) {
            selected = selectedRevision;
            if (selected == REVISION_634) {
                return selected;
            }
            String property = readProperty(REVISION_PROPERTY);
            if (property != null) {
                try {
                    int parsed = Integer.parseInt(property);
                    if (parsed == REVISION_667) {
                        throw unsupportedTarget();
                    }
                    if (parsed == REVISION_634) {
                        selectedRevision = parsed;
                        return parsed;
                    }
                } catch (NumberFormatException ignored) {
                    // Fall through to the safe legacy profile.
                }
                warn("unsupported " + REVISION_PROPERTY + "='" + property
                        + "'; using " + DEFAULT_REVISION);
            }
            selectedRevision = DEFAULT_REVISION;
            return DEFAULT_REVISION;
        }
    }

    /**
     * Selects a revision from a launcher argument before the applet is created.
     * The only runnable revision is currently 634. This method deliberately
     * rejects 667 so a launcher cannot put the 634 packet implementation on a
     * 667 connection by changing one integer.
     */
    public static synchronized void selectRevision(int revision) {
        if (revision == REVISION_667) {
            throw unsupportedTarget();
        }
        if (revision != REVISION_634) {
            throw new IllegalArgumentException("Unsupported protocol revision: " + revision);
        }
        selectedRevision = revision;
    }

    /**
     * Fails before applet/client startup when the target profile was requested
     * through a system property. Mobile hosts call this through {@link Loader}
     * before creating the generated client.
     */
    public static void ensureSupported() {
        String property = readProperty(REVISION_PROPERTY);
        if (property == null) {
            return;
        }
        try {
            int requested = Integer.parseInt(property);
            if (requested == REVISION_667) {
                throw unsupportedTarget();
            }
            if (requested != REVISION_634) {
                throw new IllegalArgumentException("Unsupported protocol revision: " + requested);
            }
        } catch (NumberFormatException ignored) {
            throw new IllegalArgumentException("Invalid " + REVISION_PROPERTY + "='" + property + "'");
        }
    }

    public static boolean is667() {
        return revision() == REVISION_667;
    }

    /**
     * Returns the configured game port. The port is server-specific; keep the
     * Void-compatible 43594 default until a compatible 667 server is selected.
     */
    public static int port() {
        return configuredPort(PORT_PROPERTY, 43594);
    }

    /** Returns the configured failover port for the selected compatible server. */
    public static int secondaryPort() {
        return configuredPort(SECONDARY_PORT_PROPERTY, 43594);
    }

    /**
     * Adds a suffix only for the future 667 profile. The implemented 634 profile
     * keeps the historical cache directory, while a completed 667 port will use a
     * separate namespace and cannot consume those files.
     */
    public static String cacheNamespace(String gameName) {
        String base = gameName;
        if (base == null || base.isEmpty()) {
            base = "runescape";
        }
        int suffix = base.lastIndexOf('-');
        if (suffix >= 0 && (base.endsWith("-" + REVISION_634)
                || base.endsWith("-" + REVISION_667))) {
            base = base.substring(0, suffix);
        }
        return revision() == REVISION_667 ? base + "-" + REVISION_667 : base;
    }

    private static int configuredPort(String propertyName, int fallback) {
        String property = readProperty(propertyName);
        if (property == null) {
            return fallback;
        }
        try {
            int parsed = Integer.parseInt(property);
            if (parsed > 0 && parsed <= 65535) {
                return parsed;
            }
        } catch (NumberFormatException ignored) {
            // Fall through to the safe endpoint.
        }
        warn("unsupported " + propertyName + "='" + property + "'; using " + fallback);
        return fallback;
    }

    private static String readProperty(String name) {
        try {
            String value = System.getProperty(name);
            if (value != null) {
                value = value.trim();
            }
            return value == null || value.length() == 0 ? null : value;
        } catch (SecurityException ignored) {
            return null;
        }
    }

    private static void warn(String message) {
        try {
            System.err.println("void-osrs: " + message);
        } catch (Throwable ignored) {
            // Mobile hosts may not expose a writable stderr during bootstrap.
        }
    }

    private static UnsupportedOperationException unsupportedTarget() {
        return new UnsupportedOperationException(
                "667 migration is not runnable: port the pinned client, cache, packets, and RSA first");
    }
}
