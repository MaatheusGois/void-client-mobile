/* ClientErrorReporter - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */
/**
 * RENAMED from `Class156` (JODE-obfuscated).
 * Client error reporter. Posts exceptions/messages to the clienterror.ws?c= endpoint (TheoraVideoPlayer.method847) for server-side error logging.
 */

import java.io.DataInputStream;
import java.net.URL;

final class ClientErrorReporter {
    static float aFloat2111;
    static int anInt2112;
    static String[] aStringArray2113 = new String[100];
    static int anInt2114;
    private HashNode aClass348_Sub42_2115;
    static int anInt2116;
    static int[] anIntArray2117 = new int[2];
    private HashTable aClass107_2118;
    static int anInt2119;
    static int anInt2120;

    static final boolean method1238(int i, int i_0_, int i_1_) {
        anInt2116++;
        if (i_1_ > -4) aFloat2111 = -0.9273654F;
        return (0x800 & i) != 0;
    }

    public static void method1239(int i) {
        aStringArray2113 = null;
        anIntArray2117 = null;
        if (i != 30114) anIntArray2117 = null;
    }

    final HashNode method1240(int i) {
        anInt2114++;
        if (i <= 7) method1240(79);
        HashNode class348_sub42 = (aClass107_2118.aClass348_Sub42_1647.aClass348_Sub42_7063);
        if (class348_sub42 == aClass107_2118.aClass348_Sub42_1647) {
            aClass348_Sub42_2115 = null;
            return null;
        }
        aClass348_Sub42_2115 = class348_sub42.aClass348_Sub42_7063;
        return class348_sub42;
    }

    static final boolean method1241(int i, int i_2_, int i_3_) {
        if (i_3_ > -111) method1239(-81);
        anInt2119++;
        return ((i_2_ & 0x18) != 0 | (0x220 & i_2_) == 544);
    }

    public ClientErrorReporter() {
        /* empty */
    }

    static final void method1242(String string, Throwable throwable, int i) {
        anInt2112++;
        do {
            try {
                String string_4_ = "";
                if (throwable != null) string_4_ = GlWaterShader.method2766(false, throwable);
                if (string != null) {
                    if (throwable != null) string_4_ += " | ";
                    string_4_ += string;
                }
                TheoraVideoPlayer.method847(string_4_, -5192);
                string_4_ = DisplayModeManagerContainer196.method2680("%3a", true, ":", string_4_);
                string_4_ = DisplayModeManagerContainer196.method2680("%40", true, "@", string_4_);
                string_4_ = DisplayModeManagerContainer196.method2680("%26", true, "&", string_4_);
                string_4_ = DisplayModeManagerContainer196.method2680("%23", true, "#", string_4_);
                if (NodeSub8.anApplet6662 != null) {
                    if (i != 15004) method1238(-69, 63, 124);
                    Task class144 = (DisplayModeManagerContainer155.aClass297_2993.method2237(new URL(NodeSub8.anApplet6662.getCodeBase(), ("clienterror.ws?c=" + NodeSub1Sub3.anInt8818 + "&u=" + (BufferCacheSub2.aString8265 != null ? BufferCacheSub2.aString8265 : String.valueOf(Component84.aLong1507)) + "&v1=" + ReflectionInvoker.aString3782 + "&v2=" + ReflectionInvoker.aString3796 + "&e=" + string_4_)), 8362));
                    while (class144.anInt1997 == 0) SpriteAtlasShader.method2161((byte) -123, 1L);
                    if (class144.anInt1997 != 1) break;
                    DataInputStream datainputstream = (DataInputStream) class144.anObject1998;
                    datainputstream.read();
                    datainputstream.close();
                }
            } catch (Exception exception) {
                break;
            }
            break;
        } while (false);
    }

    final HashNode method1243(byte i) {
        anInt2120++;
        if (i < 44) aStringArray2113 = null;
        HashNode class348_sub42 = aClass348_Sub42_2115;
        if (class348_sub42 == aClass107_2118.aClass348_Sub42_1647) {
            aClass348_Sub42_2115 = null;
            return null;
        }
        aClass348_Sub42_2115 = class348_sub42.aClass348_Sub42_7063;
        return class348_sub42;
    }

    ClientErrorReporter(HashTable class107) {
        aClass107_2118 = class107;
    }
}
