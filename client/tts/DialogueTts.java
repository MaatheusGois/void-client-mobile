/** Stable façade; Piper/Sherpa can replace the engine without changing the hook. */
public final class DialogueTts {
    private static volatile DialogueTtsEngine engine = new NativeDialogueTtsEngine();
    private static String lastText;

    public static synchronized void speak(String text, VoiceGender gender) {
        String cleaned = strip(text);
        if (cleaned.length() == 0 || cleaned.equals(lastText)) return;
        lastText = cleaned;
        System.out.println("void-tts: " + gender + " " + cleaned);
        engine.speak(cleaned, gender);
    }

    public static synchronized void stop() {
        lastText = null;
        engine.stop();
    }

    /** Replace the backend without changing the dialogue hook (Piper can use this later). */
    public static synchronized void setEngine(DialogueTtsEngine replacement) {
        if (replacement == null) throw new IllegalArgumentException("replacement");
        engine.stop();
        engine = replacement;
        lastText = null;
    }

    /** Called once per client logic tick. Future selection: System property void.tts=piper. */
    public static void pulse() {
        DialogueChatboxScanner.pulse();
    }

    static String strip(String text) {
        if (text == null) return "";
        return text.replaceAll("<[^>]*>", " ").replaceAll("\\s+", " ").trim();
    }
}
