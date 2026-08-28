/* BrowserDetector - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

/**
 * RENAMED from `Class179` (JODE-obfuscated).
 * Browser capability detector. Probes for 'havefirefox' / 'haveie6' (and similar) to choose the embedded-browser path.
 */

final class BrowserDetector {
    static int anInt2355;
    static int anInt2356;
    static int anInt2357;
    static int anInt2358;
    static int anInt2359;
    private boolean aBoolean2360 = false;
    static int anInt2361 = 1;
    static int anInt2362;
    static int anInt2363;
    private int anInt2364 = -1;
    private final int anInt2365;
    static int anInt2366;
    private String[] aStringArray2367 = new String[0];
    static int anInt2368;
    static int anInt2369;

    static final Object method1357(byte[] is, boolean bool, byte i) {
        if (i < 73) anInt2361 = -51;
        anInt2363++;
        if (is == null) return null;
        if (is.length > 136 && !DisplayModeManagerContainer167.aBoolean247) {
            try {
                AbstractBuffer class344 = new ByteBufferReader();
                class344.setBytes((byte) 62, is);
                return class344;
            } catch (Throwable throwable) {
                DisplayModeManagerContainer167.aBoolean247 = true;
            }
        }
        if (bool) return GlToolkitSub3.method3873(is, 0);
        return is;
    }

    private final int method1358(int i, int i_0_) {
        int i_1_ = 71 % ((i_0_ - -4) / 53);
        anInt2362++;
        int i_2_ = aStringArray2367.length;
        while (i_2_ <= i) {
            if (!aBoolean2360) i_2_ += anInt2365;
            else if (i_2_ != 0) i_2_ *= anInt2365;
            else i_2_ = 1;
        }
        return i_2_;
    }

    static final int method1359(boolean bool, int i) {
        anInt2368++;
        if (bool != true) anInt2361 = -32;
        return i >>> 10;
    }

    static final void method1360(String string, ReflectionInvoker class297, boolean bool, boolean bool_3_, int i) {
        try {
            anInt2369++;
            if (bool_3_) {
                if (ReflectionInvoker.aString3803.startsWith("win") && class297.aBoolean3777) {
                    String string_4_ = null;
                    if (ToolkitFactory.anApplet1530 != null) string_4_ = ToolkitFactory.anApplet1530.getParameter("haveie6");
                    if (string_4_ == null || !string_4_.equals("1")) {
                        Task class144 = BrowserUrlOpener.method2862(class297, string, -117, 0);
                        Component203.aClass144_8766 = class144;
                        Component195.aClass297_5017 = class297;
                        CacheNode.aString9554 = string;
                        return;
                    }
                }
                if (ReflectionInvoker.aString3803.startsWith("mac")) {
                    String string_5_ = null;
                    if (ToolkitFactory.anApplet1530 != null) string_5_ = ToolkitFactory.anApplet1530.getParameter("havefirefox");
                    if (string_5_ != null && string_5_.equals("1") && bool) {
                        BrowserUrlOpener.method2862(class297, string, 42, 1);
                        return;
                    }
                }
                BrowserUrlOpener.method2862(class297, string, 96, 2);
            } else BrowserUrlOpener.method2862(class297, string, -96, 3);
            int i_6_ = -6 / ((i - 20) / 44);
        } catch (RuntimeException runtimeexception) {
            throw NpcDefinition.method2929(runtimeexception, ("bo.C(" + (string != null ? "{...}" : "null") + ',' + (class297 != null ? "{...}" : "null") + ',' + bool + ',' + bool_3_ + ',' + i + ')'));
        }
    }

    final String[] method1361(int i) {
        anInt2359++;
        int i_7_ = 95 % ((15 - i) / 32);
        String[] strings = new String[1 + anInt2364];
        Component313.method1575(aStringArray2367, 0, strings, 0, anInt2364 - -1);
        return strings;
    }

    private final void method1362(String string, int i, int i_8_) {
        if (i >= -56) aBoolean2360 = true;
        anInt2366++;
        if (i_8_ > anInt2364) anInt2364 = i_8_;
        if (aStringArray2367.length <= i_8_) method1363(i_8_, -107);
        aStringArray2367[i_8_] = string;
    }

    private final void method1363(int i, int i_9_) {
        int i_10_ = 50 % ((3 - i_9_) / 63);
        anInt2358++;
        String[] strings = new String[method1358(i, 108)];
        Component313.method1575(aStringArray2367, 0, strings, 0, aStringArray2367.length);
        aStringArray2367 = strings;
    }

    final void method1364(int i, String string) {
        method1362(string, -99, 1 + anInt2364);
        anInt2356++;
        if (i != -1) aBoolean2360 = true;
    }

    static final void method1365(int i, byte i_11_, Buffer class348_sub49) {
        if (i_11_ > -113) anInt2361 = -64;
        if (DisplayModeManagerContainer152.aClass78_4538 != null) {
            try {
                DisplayModeManagerContainer152.aClass78_4538.seek(0L, (byte) 59);
                DisplayModeManagerContainer152.aClass78_4538.write(i, 24, true, (class348_sub49.payload));
            } catch (Exception exception) {
                /* empty */
            }
        }
        anInt2355++;
    }

    BrowserDetector(int i, boolean bool) {
        anInt2365 = i;
        aBoolean2360 = bool;
    }

    public final String toString() {
        anInt2357++;
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append("[");
        for (int i = 0; anInt2364 > i; i++) {
            if (i != 0) stringbuffer.append(", ");
            stringbuffer.append(aStringArray2367[i]);
        }
        stringbuffer.append("]");
        return stringbuffer.toString();
    }
}
