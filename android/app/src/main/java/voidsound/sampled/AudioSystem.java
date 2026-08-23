package voidsound.sampled;

public class AudioSystem {
    private static final Mixer.Info MIXER = new Mixer.Info();

    public static Mixer.Info[] getMixerInfo() {
        return new Mixer.Info[] { MIXER };
    }

    public static Line getLine(Line.Info info) throws LineUnavailableException {
        AudioFormat format = null;
        int bufferSize = 0;
        if (info instanceof DataLine.Info) {
            DataLine.Info di = (DataLine.Info) info;
            format = di.getFormat();
            bufferSize = di.getBufferSize();
        }
        if (format == null) {
            throw new LineUnavailableException("no format");
        }
        return new PcmSourceDataLine(format, bufferSize);
    }
}
