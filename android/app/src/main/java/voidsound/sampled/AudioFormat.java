package voidsound.sampled;

public class AudioFormat {
    private final float sampleRate;
    private final int sampleSizeInBits;
    private final int channels;
    private final boolean signed;
    private final boolean bigEndian;

    public AudioFormat(float sampleRate, int sampleSizeInBits, int channels, boolean signed, boolean bigEndian) {
        this.sampleRate = sampleRate;
        this.sampleSizeInBits = sampleSizeInBits;
        this.channels = channels;
        this.signed = signed;
        this.bigEndian = bigEndian;
    }

    public float getSampleRate() {
        return sampleRate;
    }

    public int getSampleSizeInBits() {
        return sampleSizeInBits;
    }

    public int getChannels() {
        return channels;
    }

    public boolean isSigned() {
        return signed;
    }

    public boolean isBigEndian() {
        return bigEndian;
    }

    public int getFrameSize() {
        int bits = sampleSizeInBits <= 0 ? 16 : sampleSizeInBits;
        int ch = channels <= 0 ? 1 : channels;
        return ((bits + 7) / 8) * ch;
    }
}
