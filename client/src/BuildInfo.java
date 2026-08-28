/* BuildInfo - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

/**
 * RENAMED from `Class6` (JODE-obfuscated).
 * Build/version info. Holds the client build number ('Build: 634') and the AwtHost reference; reports version to the server and debug overlays.
 */

final class BuildInfo {
    short aShort143;
    int anInt144;
    boolean aBoolean145;
    short aShort146;
    static int anInt147;
    byte aByte148;
    static int anInt149;
    short aShort150;
    static Component150 aClass227_151 = new Component150(2);
    static int anInt152;
    static int anInt153;
    int anInt154;
    static int anInt155;
    byte aByte156;

    static final void method203(int i, int i_0_, int i_1_, int i_2_, int i_3_) {
        anInt149++;
        int i_4_ = 0;
        if (i > -92) aClass227_151 = null;
        int i_5_ = i_3_;
        int i_6_ = -i_3_;
        int i_7_ = -1;
        int i_8_ = LogicError.method831(Component22.anInt1745, i_3_ + i_0_, Component27.anInt4960, -108);
        int i_9_ = LogicError.method831(Component22.anInt1745, -i_3_ + i_0_, Component27.anInt4960, 81);
        MenuOpener.method1156(-27, i_8_, DisplayModeManagerContainer167.anIntArrayArray255[i_2_], i_9_, i_1_);
        while (i_5_ > i_4_) {
            i_7_ += 2;
            i_6_ += i_7_;
            if (i_6_ > 0) {
                i_5_--;
                i_6_ -= i_5_ << 1;
                int i_10_ = -i_5_ + i_2_;
                int i_11_ = i_5_ + i_2_;
                if (i_11_ >= Component72.anInt1910 && PauseTimer.anInt513 >= i_10_) {
                    int i_12_ = LogicError.method831(Component22.anInt1745, i_4_ + i_0_, Component27.anInt4960, 74);
                    int i_13_ = LogicError.method831(Component22.anInt1745, i_0_ + -i_4_, Component27.anInt4960, 98);
                    if (i_11_ <= PauseTimer.anInt513) MenuOpener.method1156(-27, i_12_, (DisplayModeManagerContainer167.anIntArrayArray255[i_11_]), i_13_, i_1_);
                    if (Component72.anInt1910 <= i_10_) MenuOpener.method1156(-27, i_12_, (DisplayModeManagerContainer167.anIntArrayArray255[i_10_]), i_13_, i_1_);
                }
            }
            int i_14_ = -++i_4_ + i_2_;
            int i_15_ = i_2_ - -i_4_;
            if (Component72.anInt1910 <= i_15_ && PauseTimer.anInt513 >= i_14_) {
                int i_16_ = LogicError.method831(Component22.anInt1745, i_0_ + i_5_, Component27.anInt4960, 52);
                int i_17_ = LogicError.method831(Component22.anInt1745, i_0_ + -i_5_, Component27.anInt4960, -106);
                if (i_15_ <= PauseTimer.anInt513) MenuOpener.method1156(-27, i_16_, DisplayModeManagerContainer167.anIntArrayArray255[i_15_], i_17_, i_1_);
                if (i_14_ >= Component72.anInt1910) MenuOpener.method1156(-27, i_16_, DisplayModeManagerContainer167.anIntArrayArray255[i_14_], i_17_, i_1_);
            }
        }
    }

    static final void method204(int i) {
        anInt155++;
        while (Component80.aClass348_Sub49_Sub2_3813.method3415(-62, DefinitionSub25.anInt9341) >= 15) {
            int i_18_ = Component80.aClass348_Sub49_Sub2_3813.readBits((byte) -24, 15);
            if (i_18_ == 32767) break;
            boolean bool = false;
            NodeSub22 class348_sub22 = ((NodeSub22) Component21.aClass356_3654.method3480(i_18_, -6008));
            if (class348_sub22 == null) {
                Npc npc = new Npc();
                npc.anInt10290 = i_18_;
                class348_sub22 = new NodeSub22(npc);
                Component21.aClass356_3654.method3483((byte) 57, i_18_, class348_sub22);
                bool = true;
                DefinitionSub23.aClass348_Sub22Array9319[NodeSub32.anInt6930++] = class348_sub22;
            }
            Npc npc = (class348_sub22.aNpc_6859);
            DisplayModeManagerContainer238.anIntArray1233[Component324.anInt2057++] = i_18_;
            npc.anInt10306 = OggStreamReader.anInt9041;
            if ((npc.aClass79_10505) != null && npc.aClass79_10505.method793(0)) Component298.method181(true, npc);
            int i_19_ = Component80.aClass348_Sub49_Sub2_3813.readBits((byte) -24, 2);
            int i_20_ = Component80.aClass348_Sub49_Sub2_3813.readBits((byte) -24, 1);
            int i_21_ = Component80.aClass348_Sub49_Sub2_3813.readBits((byte) -24, 5);
            if (i_21_ > 15) i_21_ -= 32;
            int i_22_ = Component80.aClass348_Sub49_Sub2_3813.readBits((byte) -24, 5);
            if (i_22_ > 15) i_22_ -= 32;
            int i_23_ = 0x3d01 & 4 + Component80.aClass348_Sub49_Sub2_3813.readBits((byte) -24, 3) << 11;
            int i_24_ = Component80.aClass348_Sub49_Sub2_3813.readBits((byte) -24, 1);
            if (i_24_ == 1) Component354.anIntArray224[DisplayModeManagerContainer204.anInt1597++] = i_18_;
            npc.method2448((Component291.aClass278_2529.method2079(Component80.aClass348_Sub49_Sub2_3813.readBits((byte) -24, 14), -1)), i ^ 0x2b297815);
            npc.method2434((byte) 111, npc.aClass79_10505.anInt1399);
            npc.anInt10310 = (npc.aClass79_10505.anInt1329) << 3;
            if (bool) npc.method2435((byte) -108, i_23_, true);
            npc.method2444((Component72.localPlayer.anIntArray10317[0]) - -i_21_, i_20_ == 1, (Component72.localPlayer.anIntArray10320[0]) + i_22_, i + 724138125, npc.method2436((byte) 50), i_19_);
            if (npc.aClass79_10505.method793(0)) DisplayModeManagerContainer369.method1614(979190089, npc, npc.plane, (npc.anIntArray10317[0]), (npc.anIntArray10320[0]), null, null, 0);
        }
        if (i == -724138005) Component80.aClass348_Sub49_Sub2_3813.stopBitAccess(false);
    }

    static final void method205(int i, int i_25_, int i_26_, String string, int i_27_, int i_28_, int i_29_, int i_30_) {
        try {
            anInt147++;
            RenderableSub5 class318_sub5 = new RenderableSub5();
            class318_sub5.anInt6419 = i_25_;
            class318_sub5.anInt6418 = i_30_;
            class318_sub5.anInt6422 = i_29_;
            class318_sub5.anInt6421 = i + OpenGlShader.clientCycle;
            if (i_28_ >= -48) method206(-90, -126, -8);
            class318_sub5.aString6416 = string;
            class318_sub5.anInt6415 = i_26_;
            class318_sub5.anInt6420 = i_27_;
            Component241.aClass243_2957.method1869(-103, class318_sub5);
        } catch (RuntimeException runtimeexception) {
            throw NpcDefinition.method2929(runtimeexception, ("go.F(" + i + ',' + i_25_ + ',' + i_26_ + ',' + (string != null ? "{...}" : "null") + ',' + i_27_ + ',' + i_28_ + ',' + i_29_ + ',' + i_30_ + ')'));
        }
    }

    static final int method206(int i, int i_31_, int i_32_) {
        anInt152++;
        int i_33_ = i_31_ >>> 24;
        int i_34_ = -i_33_ + i_32_;
        i_31_ = (0xff0000 & (i_31_ & 0xff00) * i_33_ | (0xff00ff & i_31_) * i_33_ & ~0xff00ff) >>> 8;
        return i_31_ + (((i & 0xff00) * i_34_ & 0xff0000 | ~0xff00ff & (0xff00ff & i) * i_34_) >>> 8);
    }

    static final void method207(ha var_ha, byte i) {
        do {
            try {
                anInt153++;
                int i_35_ = 0;
                int i_36_ = 0;
                if (i < 113) aClass227_151 = null;
                if (Component210.aBoolean5300) {
                    i_35_ = s_Sub3.method4008((byte) -127);
                    i_36_ = Component110.method260(false);
                }
                // Purple developer console band: half height on mobile only.
                final int consoleH = BuildInfo.isMobile() ? 175 : 350;
                final int scrollTrack = consoleH - 8;
                var_ha.KA(i_35_, i_36_, Component236.anInt4017 + i_35_, i_36_ + consoleH);
                var_ha.fillRect(i_35_, i_36_, Component236.anInt4017, consoleH, 0x332277 | Component39.anInt2254 << 24, 1);
                Component103.method2663(-5590, i_35_, Component236.anInt4017 + i_35_, i_36_, i_36_ + consoleH);
                int i_37_ = consoleH / Component342.anInt1188;
                if (Component14.anInt8587 > 0) {
                    int i_38_ = scrollTrack + -Component342.anInt1188;
                    int i_39_ = (i_37_ * i_38_ / (-1 + (i_37_ - -Component14.anInt8587)));
                    int i_40_ = 4;
                    if (Component14.anInt8587 > 1) i_40_ += ((Component14.anInt8587 + (-1 + -Component94.anInt3676)) * (i_38_ - i_39_) / (Component14.anInt8587 + -1));
                    var_ha.fillRect(-16 + (Component236.anInt4017 + i_35_), i_36_ + i_40_, 12, i_39_, 0x332277 | Component39.anInt2254 << 24, 2);
                    for (int i_41_ = Component94.anInt3676; ((i_41_ < i_37_ + Component94.anInt3676) && Component14.anInt8587 > i_41_); i_41_++) {
                        String[] strings = (DefinitionSub23.method3113('\010', true, ArbShaderProgram.aStringArray6200[i_41_]));
                        int i_42_ = (-16 + Component236.anInt4017 + -8) / strings.length;
                        for (int i_43_ = 0; i_43_ < strings.length; i_43_++) {
                            int i_44_ = i_42_ * i_43_ + 8;
                            var_ha.KA(i_35_ + i_44_, i_36_, i_42_ + i_35_ - (-i_44_ - -8), i_36_ + consoleH);
                            Applet_Sub1.aClass324_20.drawText(AudioMixer.method1909((byte) 31, strings[i_43_]), -1, (-((-Component94.anInt3676 + i_41_) * Component342.anInt1188) + (-ImageProducerSprite.anInt9077 + i_36_ - (-consoleH - (-2 + -(Component163.aClass143_3179.anInt1993))))), i_35_ + i_44_, -16777216, -110);
                        }
                    }
                }
                Component49.aClass324_4684.drawTextRightAligned("Build: 634", consoleH + (i_36_ + -20), -1, (Component236.anInt4017 + i_35_ + -25), -121, -16777216);
                var_ha.KA(i_35_, i_36_, i_35_ - -Component236.anInt4017, i_36_ - -consoleH);
                var_ha.method3649((byte) -80, Component236.anInt4017, -ImageProducerSprite.anInt9077 + (consoleH + i_36_), -1, i_35_);
                NodeList.aClass324_3326.drawText("--> " + AudioMixer.method1909((byte) 31, Component126.aString4461), -1, (i_36_ - (-consoleH + Component27.aClass143_4962.anInt1993) - 1), 10 + i_35_, -16777216, -127);
                if (!Component143.aBoolean2329) break;
                int i_45_ = -1;
                if (OpenGlShader.clientCycle % 30 > 15) i_45_ = 16777215;
                var_ha.method3660(10 + (i_35_ - -(Component27.aClass143_4962.method1183(true, "--> " + (AudioMixer.method1909((byte) 31, Component126.aString4461).substring(0, NodeSub38.anInt7006))))), i_45_, 12, consoleH + (i_36_ + -Component27.aClass143_4962.anInt1993 - 11), true);
            } catch (RuntimeException runtimeexception) {
                throw NpcDefinition.method2929(runtimeexception, ("go.B(" + (var_ha != null ? "{...}" : "null") + ',' + i + ')'));
            }
            break;
        } while (false);
    }

    public static void method208(byte i) {
        if (i != 0) method204(29);
        aClass227_151 = null;
    }

    /** Android/iOS rewrite to {@code voidawt}; desktop keeps {@code java.awt}. */
    static boolean isMobile() {
        try {
            Class.forName("voidawt.AwtHost");
            return true;
        } catch (Throwable ignored) {
            return false;
        }
    }

    /** Explicit GC stalls the game thread for seconds on mobile ART — skip there. */
    static void maybeGc() {
        if (!isMobile()) {
            System.gc();
        }
    }

    BuildInfo(int i, int i_46_, int i_47_, int i_48_, int i_49_, int i_50_, int i_51_, int i_52_, int i_53_, boolean bool, int i_54_) {
        this.aBoolean145 = bool;
        this.aByte156 = (byte) i_53_;
        this.anInt154 = i_54_;
        this.aShort143 = (short) i_50_;
        this.aByte148 = (byte) i_52_;
        this.aShort146 = (short) i_51_;
        this.aShort150 = (short) i_49_;
        this.anInt144 = i;
    }
}
