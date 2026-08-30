/** Stable façade; Piper/Sherpa can replace the engine without changing the hook. */
public final class DialogueTts {
    private static final DialogueTtsEngine ENGINE = new NativeDialogueTtsEngine();
    private static String lastText;

    public static synchronized void speak(String text, VoiceGender gender) {
        String cleaned = strip(text);
        if (cleaned.length() == 0 || cleaned.equals(lastText)) return;
        lastText = cleaned;
        System.out.println("void-tts: " + gender + " " + cleaned);
        ENGINE.speak(cleaned, gender);
    }

    public static synchronized void stop() {
        lastText = null;
        ENGINE.stop();
    }

    /** Called once per client logic tick. Future swap: System property void.tts=piper. */
    public static void pulse() {
        DialogueChatboxScanner.pulse();
    }

    static String strip(String text) {
        if (text == null) return "";
        return text.replaceAll("<[^>]*>", " ").replaceAll("\\s+", " ").trim();
    }
}
