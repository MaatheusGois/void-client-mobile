/**
 * Minimal blocking-event gate — pauses scripts when there is no local player.
 */
final class MicrobotBlockingEvents {

    private MicrobotBlockingEvents() {
    }

    static boolean isBlocking() {
        return Class132.localPlayer == null;
    }
}
