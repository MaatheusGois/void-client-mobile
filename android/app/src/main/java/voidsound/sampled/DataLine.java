package voidsound.sampled;

public interface DataLine extends Line {
    public static class Info extends Line.Info {
        public Info(Class<?> lineClass, AudioFormat format, int bufferSize) {
            super(lineClass, format, bufferSize);
        }
    }

    int available();

    int write(byte[] b, int off, int len);
}
