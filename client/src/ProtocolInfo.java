/**
 * Versioned protocol and cache settings shared by the desktop and rewritten mobile
 * client sources.
 *
 * <p>The 667 profile is intentionally opt-in until the 667 packet, cache, and RSA
 * implementations are imported. Keeping the 634 profile as the default preserves a
 * working rollback while allowing each migration gate to be exercised independently.</p>
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
     * Returns the selected client revision. Use {@code -Dvoid.protocol=667} for the
     * migration profile or {@code -Dvoid.protocol=634} for the rollback profile.
     */
    public static int revision() {
        int selected = selectedRevision;
        if (selected == REVISION_634 || selected == REVISION_667) {
            return selected;
        }
        synchronized (ProtocolInfo.class) {
            selected = selectedRevision;
            if (selected == REVISION_634 || selected == REVISION_667) {
                return selected;
            }
            String property = readProperty(REVISION_PROPERTY);
            if (property != null) {
                try {
                    int parsed = Integer.parseInt(property);
                    if (parsed == REVISION_634 || parsed == REVISION_667) {
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

    /** Selects a revision from a launcher argument before the applet is created. */
    public static synchronized void selectRevision(int revision) {
        if (revision != REVISION_634 && revision != REVISION_667) {
            throw new IllegalArgumentException("Unsupported protocol revision: " + revision);
        }
        selectedRevision = revision;
    }

    public static boolean is667() {
        return revision() == REVISION_667;
    }

    /**
     * Returns the configured game port. The audited 667 source uses 443 as its
     * primary live endpoint and 43594 as its secondary endpoint; Void's legacy
     * profile continues to use 43594 as its primary endpoint.
     */
    public static int port() {
        int fallback = is667() ? 443 : 43594;
        return configuredPort(PORT_PROPERTY, fallback);
    }

    /** Returns the configured failover port, defaulting to the 667 secondary endpoint. */
    public static int secondaryPort() {
        return configuredPort(SECONDARY_PORT_PROPERTY, 43594);
    }

    /**
     * Adds the revision to the cache namespace. This is deliberately idempotent so
     * callers can pass either the applet game name or an already-versioned name.
     */
    public static String cacheNamespace(String gameName) {
        String base = gameName;
        if (base == null || base.length() == 0) {
            base = "runescape";
        }
        int suffix = base.lastIndexOf('-');
        if (suffix >= 0 && (base.endsWith("-" + REVISION_634)
                || base.endsWith("-" + REVISION_667))) {
            base = base.substring(0, suffix);
        }
        return base + "-" + revision();
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
}
