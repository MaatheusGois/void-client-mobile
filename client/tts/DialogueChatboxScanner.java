import java.util.ArrayList;
import java.util.List;

/** Content-based scanner for modal dialogue, intentionally independent of interface ids. */
final class DialogueChatboxScanner {
    private static final List<DisplayModeManagerContainer57> labels = new ArrayList<DisplayModeManagerContainer57>();

    static void pulse() {
        labels.clear();
        boolean modal = false;
        DisplayModeManagerContainer57[][] roots = DefinitionSub33.openInterfaces;
        if (roots == null) {
            DialogueTts.stop();
            return;
        }
        for (DisplayModeManagerContainer57[] group : roots) {
            if (group != null) {
                for (DisplayModeManagerContainer57 widget : group) {
                    modal |= scan(widget);
                }
            }
        }
        if (!modal) {
            DialogueTts.stop();
            return;
        }
        String body = null;
        int bodyLength = 0;
        for (DisplayModeManagerContainer57 widget : labels) {
            String text = DialogueTts.strip(widget.textContent);
            if (text.length() > bodyLength && !isControl(text) && text.length() > 2) {
                body = text;
                bodyLength = text.length();
            }
        }
        if (body == null) {
            DialogueTts.stop();
            return;
        }
        VoiceGender gender = VoiceGender.MALE;
        Player player = Component72.localPlayer;
        if (player != null && player.displayName != null) {
            for (DisplayModeManagerContainer57 widget : labels) {
                String title = DialogueTts.strip(widget.textContent);
                if (title.equalsIgnoreCase(player.displayName)) {
                    gender = player.isFemaleVoice() ? VoiceGender.FEMALE : VoiceGender.MALE;
                    break;
                }
            }
        }
        DialogueTts.speak(body, gender);
    }

    private static boolean scan(DisplayModeManagerContainer57 widget) {
        if (widget == null || widget.hidden) return false;
        boolean modal = false;
        String option = DialogueTts.strip(widget.continueOption);
        String text = DialogueTts.strip(widget.textContent);
        if (isControl(option) || isControl(text)) modal = true;
        if (widget.textContent != null && text.length() > 0) labels.add(widget);
        if (widget.children != null) {
            for (DisplayModeManagerContainer57 child : widget.children) modal |= scan(child);
        }
        return modal;
    }

    private static boolean isControl(String text) {
        if (text == null || text.length() == 0) return false;
        String normalized = text.toLowerCase();
        return normalized.equals("continue") || normalized.equals("continuar")
                || normalized.startsWith("choose ") || normalized.startsWith("select ");
    }
}
