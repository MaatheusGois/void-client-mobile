/* PauseHandler - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

/**
 * RENAMED from `Class348_Sub42_Sub6` (JODE-obfuscated).
 * Pause handler node. Extends HashNode; implements the 'Pausing for N seconds...' delay used to throttle or pace timed client operations (e.g. loading pauses).
 */

final class PauseHandler extends HashNode {
    static boolean aBoolean9535 = false;
    static Task aClass144_9536;
    static int anInt9537;
    DisplayModeManagerContainer104 aClass318_Sub1_Sub3_Sub4_9538;
    static int anInt9539;

    static final void method3188(byte i, Buffer class348_sub49) {
        anInt9539++;
        for (int i_0_ = 0; i_0_ < Component241.anInt2956; i_0_++) {
            int i_1_ = class348_sub49.readSmart(-127);
            int i_2_ = class348_sub49.readUnsignedShort(842397944);
            if (i_2_ == 65535) i_2_ = -1;
            if (OutputStream_Sub1.aClass110_Sub1Array97[i_1_] != null) OutputStream_Sub1.aClass110_Sub1Array97[i_1_].anInt1704 = i_2_;
        }
        int i_3_ = -75 / ((53 - i) / 48);
    }

    static final void method3189(int i, String[] strings) {
        if (i == 0) {
            anInt9537++;
            if (strings.length > 1) {
                for (int i_4_ = 0; i_4_ < strings.length; i_4_++) {
                    if (strings[i_4_].startsWith("pause")) {
                        int i_5_ = 5;
                        try {
                            i_5_ = Integer.parseInt(strings[i_4_].substring(6));
                        } catch (Exception exception) {
                            /* empty */
                        }
                        Applet_Sub1.method94(("Pausing for " + i_5_ + " seconds..."), -109);
                        Cp1252Decoder.aStringArray5223 = strings;
                        Component221.anInt1794 = i_4_ - -1;
                        Component100.aLong8694 = (long) (i_5_ * 1000) + Component240.currentTimeMillis(-104);
                        break;
                    }
                    Component126.aString4461 = strings[i_4_];
                    Component210.method555(false, 0);
                }
            } else {
                Component126.aString4461 += strings[0];
                NodeSub38.anInt7006 += strings[0].length();
            }
        }
    }

    PauseHandler(DisplayModeManagerContainer104 class318_sub1_sub3_sub4) {
        this.aClass318_Sub1_Sub3_Sub4_9538 = class318_sub1_sub3_sub4;
    }

    public static void method3190(boolean bool) {
        if (bool != false) aBoolean9535 = true;
        aClass144_9536 = null;
    }
}
