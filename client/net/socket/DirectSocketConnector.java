/* DirectSocketConnector - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

/**
 * RENAMED from `Class272_Sub1` (JODE-obfuscated).
 * Plain (direct) socket connector. extends SocketConnector; method2050(int) returns a raw `new Socket` with no proxy.
 */

import java.io.IOException;
import java.net.Socket;

final class DirectSocketConnector extends SocketConnector {
    final Socket method2050(int i) throws IOException {
        if (i > -100) return null;
        return this.method2047((byte) 87);
    }

    public DirectSocketConnector() {
        /* empty */
    }
}
