/* CacheIndexReader - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

/**
 * RENAMED from `Class137` (JODE-obfuscated).
 * Cache index-file reader. Holds two SeekableFile handles (dataFile/indexFile); readEntry(byte,int) seeks to a 6-byte index entry (6*i) and reads the header + data block; writeEntry/method1162 write/read cache sectors.
 */

final class CacheIndexReader {
    static int anInt1931;
    private int anInt1932;
    private SeekableFile dataFile = null;
    static int anInt1934;
    static int anInt1935;
    static int anInt1936;
    static int anInt1937;
    private SeekableFile indexFile = null;
    private int anInt1939 = 65000;

    final boolean writeEntry(int i, int i_0_, byte[] is, int i_1_) {
        if (i_1_ != -7305) return true;
        anInt1935++;
        synchronized (dataFile) {
            if (i < 0 || i > anInt1939) throw new IllegalArgumentException();
            boolean bool = method1162(is, -15096, i_0_, i, true);
            if (!bool) bool = method1162(is, -15096, i_0_, i, false);
            return bool;
        }
    }

    final byte[] readEntry(byte i, int i_2_) {
        anInt1937++;
        synchronized (dataFile) {
            try {
                if ((long) (6 * i_2_ - -6) > indexFile.length(0)) return null;
                indexFile.seek(6 * i_2_, (byte) -106);
                indexFile.read(0, Component339.aByteArray3144, 6, i + -16713);
                int i_3_ = ((0xff & Component339.aByteArray3144[2]) + ((0xff00 & Component339.aByteArray3144[1] << 8) + (0xff0000 & Component339.aByteArray3144[0] << 16)));
                int i_4_ = ((0xff & Component339.aByteArray3144[5]) + ((0xff & Component339.aByteArray3144[3]) << 16) - -((Component339.aByteArray3144[4] & 0xff) << 8));
                if (i_3_ < 0 || anInt1939 < i_3_) return null;
                if (i_4_ <= 0 || (long) i_4_ > dataFile.length(0) / 520L) return null;
                byte[] is = new byte[i_3_];
                int i_5_ = 0;
                if (i != -4) return null;
                int i_6_ = 0;
                while (i_3_ > i_5_) {
                    if (i_4_ == 0) return null;
                    dataFile.seek(i_4_ * 520, (byte) -103);
                    int i_7_ = -i_5_ + i_3_;
                    if (i_7_ > 512) i_7_ = 512;
                    dataFile.read(0, Component339.aByteArray3144, 8 + i_7_, i ^ 0x414f);
                    int i_8_ = ((0xff00 & Component339.aByteArray3144[0] << 8) + (Component339.aByteArray3144[1] & 0xff));
                    int i_9_ = ((Component339.aByteArray3144[2] << 8 & 0xff00) + (Component339.aByteArray3144[3] & 0xff));
                    int i_10_ = (((Component339.aByteArray3144[5] & 0xff) << 8) + (0xff0000 & Component339.aByteArray3144[4] << 16) + (Component339.aByteArray3144[6] & 0xff));
                    int i_11_ = 0xff & Component339.aByteArray3144[7];
                    if (i_2_ != i_8_ || i_9_ != i_6_ || i_11_ != anInt1932) return null;
                    if (i_10_ < 0 || ((long) i_10_ > dataFile.length(i + 4) / 520L)) return null;
                    i_4_ = i_10_;
                    i_6_++;
                    for (int i_12_ = 0; i_12_ < i_7_; i_12_++)
                        is[i_5_++] = Component339.aByteArray3144[8 + i_12_];
                }
                return is;
            } catch (java.io.IOException ioexception) {
                return null;
            }
        }
    }

    private final boolean method1162(byte[] is, int i, int i_13_, int i_14_, boolean bool) {
        anInt1936++;
        synchronized (dataFile) {
            try {
                int i_15_;
                if (bool) {
                    if (indexFile.length(i + 15096) < (long) (i_13_ * 6 - -6)) return false;
                    indexFile.seek(i_13_ * 6, (byte) 70);
                    indexFile.read(0, Component339.aByteArray3144, 6, -16717);
                    i_15_ = ((0xff & Component339.aByteArray3144[5]) + ((0xff & Component339.aByteArray3144[3]) << 16) + ((Component339.aByteArray3144[4] & 0xff) << 8));
                    if (i_15_ <= 0 || (dataFile.length(i ^ ~0x3af7) / 520L < (long) i_15_)) return false;
                } else {
                    i_15_ = (int) ((519L + dataFile.length(0)) / 520L);
                    if (i_15_ == 0) i_15_ = 1;
                }
                Component339.aByteArray3144[0] = (byte) (i_14_ >> 16);
                Component339.aByteArray3144[4] = (byte) (i_15_ >> 8);
                Component339.aByteArray3144[2] = (byte) i_14_;
                Component339.aByteArray3144[1] = (byte) (i_14_ >> 8);
                Component339.aByteArray3144[3] = (byte) (i_15_ >> 16);
                Component339.aByteArray3144[5] = (byte) i_15_;
                indexFile.seek(i_13_ * 6, (byte) 111);
                indexFile.write(0, 6, true, Component339.aByteArray3144);
                int i_16_ = 0;
                if (i != -15096) anInt1932 = 122;
                int i_17_ = 0;
                while (i_16_ < i_14_) {
                    int i_18_ = 0;
                    if (bool) {
                        dataFile.seek(520 * i_15_, (byte) -116);
                        try {
                            dataFile.read(0, Component339.aByteArray3144, 8, -16717);
                        } catch (java.io.EOFException eofexception) {
                            break;
                        }
                        int i_19_ = ((0xff & Component339.aByteArray3144[1]) + (0xff00 & (Component339.aByteArray3144[0] << 8)));
                        i_18_ = ((0xff00 & Component339.aByteArray3144[5] << 8) + (((Component339.aByteArray3144[4] & 0xff) << 16) - -(0xff & Component339.aByteArray3144[6])));
                        int i_20_ = ((Component339.aByteArray3144[2] << 8 & 0xff00) + (Component339.aByteArray3144[3] & 0xff));
                        int i_21_ = 0xff & Component339.aByteArray3144[7];
                        if (i_19_ != i_13_ || i_20_ != i_17_ || i_21_ != anInt1932) return false;
                        if (i_18_ < 0 || (dataFile.length(0) / 520L < (long) i_18_)) return false;
                    }
                    if (i_18_ == 0) {
                        bool = false;
                        i_18_ = (int) ((dataFile.length(0) - -519L) / 520L);
                        if (i_18_ == 0) i_18_++;
                        if (i_15_ == i_18_) i_18_++;
                    }
                    if (i_14_ + -i_16_ <= 512) i_18_ = 0;
                    Component339.aByteArray3144[3] = (byte) i_17_;
                    Component339.aByteArray3144[2] = (byte) (i_17_ >> 8);
                    Component339.aByteArray3144[0] = (byte) (i_13_ >> 8);
                    Component339.aByteArray3144[1] = (byte) i_13_;
                    Component339.aByteArray3144[5] = (byte) (i_18_ >> 8);
                    Component339.aByteArray3144[6] = (byte) i_18_;
                    Component339.aByteArray3144[7] = (byte) anInt1932;
                    Component339.aByteArray3144[4] = (byte) (i_18_ >> 16);
                    dataFile.seek(520 * i_15_, (byte) -121);
                    dataFile.write(0, 8, true, Component339.aByteArray3144);
                    int i_22_ = -i_16_ + i_14_;
                    if (i_22_ > 512) i_22_ = 512;
                    dataFile.write(i_16_, i_22_, true, is);
                    i_17_++;
                    i_16_ += i_22_;
                    i_15_ = i_18_;
                }
                return true;
            } catch (java.io.IOException ioexception) {
                return false;
            }
        }
    }

    public final String toString() {
        anInt1934++;
        return "Cache:" + anInt1932;
    }

    static final boolean method1163(int i, byte i_23_, int i_24_) {
        int i_25_ = -65 / ((i_23_ - 2) / 55);
        anInt1931++;
        return (i_24_ & 0x800) != 0;
    }

    CacheIndexReader(int i, SeekableFile class78, SeekableFile class78_26_, int i_27_) {
        try {
            anInt1932 = i;
            dataFile = class78;
            indexFile = class78_26_;
            anInt1939 = i_27_;
        } catch (RuntimeException runtimeexception) {
            throw NpcDefinition.method2929(runtimeexception, ("nw.<init>(" + i + ',' + (class78 != null ? "{...}" : "null") + ',' + (class78_26_ != null ? "{...}" : "null") + ',' + i_27_ + ')'));
        }
    }
}
