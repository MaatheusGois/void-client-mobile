package voidsound.sampled;

/** JavaSound-style PCM ring: {@link #free()} is SourceDataLine.available(). */
final class ByteRing {
    private final byte[] buf;
    private int head;
    private int size;

    ByteRing(int capacity) {
        buf = new byte[Math.max(2048, capacity)];
    }

    synchronized int capacity() {
        return buf.length;
    }

    synchronized int free() {
        return buf.length - size;
    }

    synchronized int used() {
        return size;
    }

    synchronized void clear() {
        head = 0;
        size = 0;
        notifyAll();
    }

    synchronized int write(byte[] src, int off, int len) {
        int done = 0;
        while (done < len) {
            while (size == buf.length) {
                try {
                    wait(5L);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return done;
                }
            }
            int space = buf.length - size;
            int chunk = Math.min(len - done, space);
            int tail = (head + size) % buf.length;
            int first = Math.min(chunk, buf.length - tail);
            System.arraycopy(src, off + done, buf, tail, first);
            if (chunk > first) {
                System.arraycopy(src, off + done + first, buf, 0, chunk - first);
            }
            size += chunk;
            done += chunk;
            notifyAll();
        }
        return done;
    }

    synchronized int read(byte[] dst, int off, int len) {
        if (size == 0 || len <= 0) {
            return 0;
        }
        int chunk = Math.min(len, size);
        int first = Math.min(chunk, buf.length - head);
        System.arraycopy(buf, head, dst, off, first);
        if (chunk > first) {
            System.arraycopy(buf, 0, dst, off + first, chunk - first);
        }
        head = (head + chunk) % buf.length;
        size -= chunk;
        notifyAll();
        return chunk;
    }
}
