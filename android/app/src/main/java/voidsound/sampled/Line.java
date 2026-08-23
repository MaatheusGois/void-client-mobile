package voidsound.sampled;

public interface Line {
    public static class Info {
        public Info(Class<?> lineClass, AudioFormat format, int bufferSize) {
        }

        public Info(Class<?> lineClass) {
        }
    }

    void open() throws LineUnavailableException;

    void close();

    void start();

    void flush();
}
