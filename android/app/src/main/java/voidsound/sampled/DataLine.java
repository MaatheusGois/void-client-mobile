package voidsound.sampled;

public interface DataLine extends Line {
    public static class Info extends Line.Info {
        private final AudioFormat format;
        private final int bufferSize;

        public Info(Class<?> lineClass, AudioFormat format, int bufferSize) {
            super(lineClass, format, bufferSize);
            this.format = format;
            this.bufferSize = bufferSize;
        }

        public AudioFormat getFormat() {
            return format;
        }

        public int getBufferSize() {
            return bufferSize;
        }
    }

    int available();

    int write(byte[] b, int off, int len);
}
