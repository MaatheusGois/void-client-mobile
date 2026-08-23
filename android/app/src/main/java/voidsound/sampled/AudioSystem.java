package voidsound.sampled;

public class AudioSystem {
    public static Mixer.Info[] getMixerInfo() {
        return new Mixer.Info[0];
    }

    public static Line getLine(Line.Info info) throws LineUnavailableException {
        throw new LineUnavailableException("android");
    }
}
