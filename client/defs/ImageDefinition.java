/* ImageDefinition - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

/**
 * RENAMED from `Class348_Sub40_Sub7` (JODE-obfuscated).
 * Image/sprite definition (extends renamed Definition). method3047() builds RGB int[][] pixel buffers (R/G/B channels) from a config cache (ImageCacheStore) and applies horizontal flip; produces decoded image data for the cache.
 */

final class ImageDefinition extends Definition {
    private boolean aBoolean9140 = true;
    static int anInt9141;
    static int anInt9142;
    static int anInt9143;
    static MenuEntry aMenuEntry_9144;
    static int anInt9145;
    static int anInt9146;
    private boolean aBoolean9147 = true;

    final int[][] method3047(int i, int i_0_) {
        anInt9141++;
        int[][] is = this.aClass322_7033.method2557(-119, i);
        if (this.aClass322_7033.aBoolean4035) {
            int[][] is_1_ = this.method3039((byte) 120, (aBoolean9147 ? -i + DisplayModeManagerContainer356.anInt6325 : i), 0);
            int[] is_2_ = is_1_[0];
            int[] is_3_ = is_1_[1];
            int[] is_4_ = is_1_[2];
            int[] is_5_ = is[0];
            int[] is_6_ = is[1];
            int[] is_7_ = is[2];
            if (aBoolean9140) {
                for (int i_8_ = 0; i_8_ < DefinitionSub6.anInt9139; i_8_++) {
                    is_5_[i_8_] = is_2_[Component287.anInt6076 + -i_8_];
                    is_6_[i_8_] = is_3_[Component287.anInt6076 - i_8_];
                    is_7_[i_8_] = is_4_[-i_8_ + Component287.anInt6076];
                }
            } else {
                for (int i_9_ = 0; (i_9_ < DefinitionSub6.anInt9139); i_9_++) {
                    is_5_[i_9_] = is_2_[i_9_];
                    is_6_[i_9_] = is_3_[i_9_];
                    is_7_[i_9_] = is_4_[i_9_];
                }
            }
        }
        if (i_0_ != -1564599039) method3065(-40, true, 93);
        return is;
    }

    static final void method3064(int i, int i_10_, boolean bool, int i_11_, int i_12_, int i_13_, int i_14_, boolean bool_15_, DisplayModeManagerContainer57[] class46s, int i_16_, int i_17_) {
        try {
            NodeSub8.aHa6654.KA(i, i_12_, i_14_, i_17_);
            if (bool_15_ == false) {
                anInt9145++;
                int i_18_ = 0;
                for (/**/; class46s.length > i_18_; i_18_++) {
                    DisplayModeManagerContainer57 class46 = class46s[i_18_];
                    if (class46 != null && (class46.anInt834 == i_16_ || (i_16_ == -1412584499 && Component156.aClass46_3701 == class46))) {
                        int i_19_ = i_11_ + class46.anInt800;
                        int i_20_ = class46.anInt750 + i_10_;
                        i_20_ -= MobileKeyboard.liftPx(class46, i_19_, i_20_);
                        int i_21_ = 1 + (class46.anInt709 + i_19_);
                        int i_22_ = class46.anInt789 + i_20_ - -1;
                        int i_23_;
                        if (i_13_ == -1) {
                            Component166.aRectangleArray2371[NodeSub38.anInt7008].setBounds(i_19_, i_20_, class46.anInt709, class46.anInt789);
                            i_23_ = NodeSub38.anInt7008++;
                        } else i_23_ = i_13_;
                        class46.anInt794 = OpenGlShader.clientCycle;
                        class46.anInt760 = i_23_;
                        if (!client.method111(class46)) {
                            if (class46.anInt765 != 0) NodeSub18.method2942(class46, (byte) -36);
                            int i_24_ = i_19_;
                            int i_25_ = i_20_;
                            int i_26_ = 0;
                            int i_27_ = 0;
                            if (Component210.aBoolean5300) {
                                i_26_ = s_Sub3.method4008((byte) -124);
                                i_27_ = Component110.method260(bool_15_);
                            }
                            int i_28_ = class46.anInt696;
                            if (DisplayModeManagerContainer356.aBoolean6327 && (client.method105(class46).anInt7098 != 0 || class46.anInt774 == 0) && i_28_ > 127) i_28_ = 127;
                            if (class46 == Component156.aClass46_3701) {
                                if (i_16_ != -1412584499 && ((Component37.anInt3930 == class46.anInt797) || (InputStream_Sub1.anInt78 == (class46.anInt797)))) {
                                    Component257.anInt4792 = i_10_;
                                    NodeSub1.anInt6555 = i_11_;
                                    Component161.aClass46Array1942 = class46s;
                                    continue;
                                }
                                if (Component189.aBoolean3819 && Component162.aBoolean8386) {
                                    int i_29_ = (AbstractGlTextureSub4.mouseHandler.getCursorX(true) - -i_26_);
                                    int i_30_ = (AbstractGlTextureSub4.mouseHandler.getCursorY((byte) 101) + i_27_);
                                    i_29_ -= RenderableSub4.anInt6411;
                                    i_30_ -= Component386.anInt2872;
                                    if (DefinitionSub37.anInt9461 > i_29_) i_29_ = DefinitionSub37.anInt9461;
                                    if (i_30_ < DisplayModeManagerContainer172.anInt558) i_30_ = DisplayModeManagerContainer172.anInt558;
                                    if (class46.anInt709 + i_29_ > ((Component374.aClass46_4130.anInt709) + DefinitionSub37.anInt9461)) i_29_ = (-class46.anInt709 + ((DefinitionSub37.anInt9461) + (Component374.aClass46_4130.anInt709)));
                                    if (i_30_ + class46.anInt789 > (Component374.aClass46_4130.anInt789) + DisplayModeManagerContainer172.anInt558) i_30_ = (-class46.anInt789 + (DisplayModeManagerContainer172.anInt558 - -(Component374.aClass46_4130.anInt789)));
                                    i_24_ = i_29_;
                                    i_25_ = i_30_;
                                }
                                if (class46.anInt797 == InputStream_Sub1.anInt78) i_28_ = 128;
                            }
                            int i_31_;
                            int i_32_;
                            int i_33_;
                            int i_34_;
                            if (class46.anInt774 == 2) {
                                i_31_ = i;
                                i_32_ = i_12_;
                                i_33_ = i_17_;
                                i_34_ = i_14_;
                            } else {
                                int i_35_ = i_24_ - -class46.anInt709;
                                int i_36_ = i_25_ + class46.anInt789;
                                if (class46.anInt774 == 9) {
                                    i_36_++;
                                    i_35_++;
                                }
                                i_31_ = Math.max(i, i_24_);
                                i_32_ = (Math.max(i_12_, i_25_));
                                i_34_ = Math.min(i_14_, i_35_);
                                i_33_ = (Math.min(i_17_, i_36_));
                            }
                            if (i_31_ < i_34_ && i_32_ < i_33_) {
                                if (class46.anInt765 != 0) {
                                    if ((Component98.anInt5943 == class46.anInt765) || (class46.anInt765 == Component37.anInt3932)) {
                                        Component2.method198(class46, false, i_25_, i_24_);
                                        if (!Component210.aBoolean5300) {
                                            OutputStream_Sub1.method132(124, (Component37.anInt3932 == (class46.anInt765)), class46.anInt709, i_24_, class46.anInt789, i_25_);
                                            NodeSub8.aHa6654.KA(i, i_12_, i_14_, i_17_);
                                        }
                                        InflaterDecompressor.aBooleanArray2076[i_23_] = true;
                                        continue;
                                    }
                                    if (class46.anInt765 == Component200.anInt3717) {
                                        if (class46.method425((NodeSub8.aHa6654), (byte) 8) != null) {
                                            Component381.method3570(false);
                                            HashTable.method1007(class46, (NodeSub8.aHa6654), i_24_, i_25_, 22960);
                                            DisplayModeManagerContainer259.aBooleanArray3438[i_23_] = true;
                                            NodeSub8.aHa6654.KA(i, i_12_, i_14_, i_17_);
                                            if (Component210.aBoolean5300) {
                                                if (bool) Component103.method2663(-5590, i_19_, i_21_, i_20_, i_22_);
                                                else Component285.method503(i_22_, i_19_, (byte) -74, i_21_, i_20_);
                                            }
                                        }
                                        continue;
                                    }
                                    if (class46.anInt765 == LibraryCreditsText.anInt5951) {
                                        if (class46.method425((NodeSub8.aHa6654), (byte) 26) != null) {
                                            DisplayModeManagerContainer220.method271(i_25_, class46, (byte) -98, i_24_);
                                            DisplayModeManagerContainer259.aBooleanArray3438[i_23_] = true;
                                            NodeSub8.aHa6654.KA(i, i_12_, i_14_, i_17_);
                                            if (Component210.aBoolean5300) {
                                                if (!bool) Component285.method503(i_22_, i_19_, (byte) -74, i_21_, i_20_);
                                                else Component103.method2663(-5590, i_19_, i_21_, i_20_, i_22_);
                                            }
                                        }
                                        continue;
                                    }
                                    if (class46.anInt765 == NodeSub45.anInt7102) {
                                        DisplayModeManagerContainer104.method2464((byte) -7, i_24_, class46.anInt709, class46.anInt789, NodeSub8.aHa6654, i_25_, DefinitionSub4.aD9113);
                                        InflaterDecompressor.aBooleanArray2076[i_23_] = true;
                                        NodeSub8.aHa6654.KA(i, i_12_, i_14_, i_17_);
                                        continue;
                                    }
                                    if (SceneManager.anInt2861 == class46.anInt765) {
                                        TeleportHandler.method3498(class46.anInt789, NodeSub8.aHa6654, i_24_, class46.anInt709, true, i_25_);
                                        InflaterDecompressor.aBooleanArray2076[i_23_] = true;
                                        NodeSub8.aHa6654.KA(i, i_12_, i_14_, i_17_);
                                        continue;
                                    }
                                    if (Component257.anInt4793 == class46.anInt765) {
                                        if (Component10.aBoolean3811 || ReliefShader.aBoolean2514) {
                                            int i_37_ = (class46.anInt709 + i_24_);
                                            int i_38_ = 15 + i_25_;
                                            if (Component210.aBoolean5300) {
                                                if (!bool) Component285.method503(i_22_, i_19_, (byte) -74, i_21_, i_20_);
                                                else Component103.method2663(-5590, i_19_, i_21_, i_20_, i_22_);
                                            }
                                            if (Component10.aBoolean3811) {
                                                int i_39_ = -256;
                                                if (DisplayModeManagerContainer348.anInt5891 < 20) i_39_ = -65536;
                                                Applet_Sub1.aClass324_20.drawTextRightAligned("Fps:" + (DisplayModeManagerContainer348.anInt5891), i_38_, i_39_, i_37_, -128, -1);
                                                i_38_ += 15;
                                                Runtime runtime = Runtime.getRuntime();
                                                int i_40_ = (int) (((runtime.totalMemory()) + -(runtime.freeMemory())) / 1024L);
                                                int i_41_ = -256;
                                                if (i_40_ > 98304) {
                                                    i_41_ = -65536;
                                                    if (DisplayModeManagerContainer254.aBoolean2151) {
                                                        DisplayModeManagerContainer57.method427(-35);
                                                        for (int i_42_ = 0; i_42_ < 10; i_42_++)
                                                            System.gc();
                                                        i_40_ = (int) (((runtime.totalMemory()) - (runtime.freeMemory())) / 1024L);
                                                        if (i_40_ > 65536) DisplayModeManagerContainer213.method544("WARNING: Memory usage over 64MB! Please inform whoever is responsible for the content/area you are using/in.", false, 4);
                                                    }
                                                }
                                                Applet_Sub1.aClass324_20.drawTextRightAligned("Mem:" + i_40_ + "k", i_38_, i_41_, i_37_, -124, -1);
                                                i_38_ += 15;
                                                Applet_Sub1.aClass324_20.drawTextRightAligned(("In:" + Component235.anInt3372 + "B/s Out:" + (NodeSub48.anInt7134) + "B/s"), i_38_, -256, i_37_, -127, -1);
                                                i_38_ += 15;
                                                int i_43_ = (NodeSub8.aHa6654.E() / 1024);
                                                Applet_Sub1.aClass324_20.drawTextRightAligned("Offheap:" + i_43_ + "k", i_38_, (i_43_ <= 65536 ? -256 : -65536), i_37_, -125, -1);
                                                i_38_ += 15;
                                                int i_44_ = 0;
                                                int i_45_ = 0;
                                                int i_46_ = 0;
                                                for (int i_47_ = 0; (i_47_ < 37); i_47_++) {
                                                    if ((Component354.aClass314_Sub1Array223[i_47_]) != null) {
                                                        i_44_ += (Component354.aClass314_Sub1Array223[i_47_].method2345(0));
                                                        i_45_ += (Component354.aClass314_Sub1Array223[i_47_].method2351(0));
                                                        i_46_ += (Component354.aClass314_Sub1Array223[i_47_].method2341(24940));
                                                    }
                                                }
                                                int i_48_ = i_46_ * 100 / i_44_;
                                                int i_49_ = 10000 * i_45_ / i_44_;
                                                String string = ("Cache:" + (Component186.method3486(2, true, 0, i_49_, 16980)) + "% (" + i_48_ + "%)");
                                                Component49.aClass324_4684.drawTextRightAligned(string, i_38_, -256, i_37_, -127, -1);
                                                i_38_ += 12;
                                                if (Loader.showCoordinates) {
                                                    int playerX = (Component72.localPlayer.x >> 9) + za_Sub2.regionTileX;
                                                    int playerY = (Component72.localPlayer.y >> 9) + Component330.regionTileY;
                                                    Component49.aClass324_4684.drawTextRightAligned("Coordinates: " + playerX + ", " + playerY + ", " + Component72.localPlayer.plane, i_38_, -256, i_37_, -127, -1);
                                                    i_38_ += 12;
                                                    Component49.aClass324_4684.drawTextRightAligned("Region id: " + (((playerX >> 6) << 8) + (playerY >> 6)) + " (" + (playerX >> 6) + ", " + (playerY >> 6) + ")", i_38_, -256, i_37_, -127, -1);
                                                    i_38_ += 12;
                                                    Component49.aClass324_4684.drawTextRightAligned("Chunk: " + (playerX >> 3) + ", " + (playerY >> 3), i_38_, -256, i_37_, -127, -1);
                                                    i_38_ += 12;
                                                }
                                            }
                                            if (Component55.anInt3936 > 0) Component49.aClass324_4684.drawTextRightAligned(("Particles: " + (Component203.anInt8780) + " / " + Component55.anInt3936), i_38_, -256, i_37_, -121, -1);
                                            i_38_ += 12;
                                            if (ReliefShader.aBoolean2514) {
                                                Component49.aClass324_4684.drawTextRightAligned(("Polys: " + NodeSub8.aHa6654.I() + " Models: " + NodeSub8.aHa6654.M()), i_38_, -256, i_37_, -127, -1);
                                                i_38_ += 12;
                                                Component49.aClass324_4684.drawTextRightAligned(("Ls: " + DisplayModeManagerContainer249.anInt4666 + " La: " + Component328.anInt1482 + " NPC: " + Component150.anInt2968 + " Pl: " + za_Sub1.anInt9776), i_38_, -256, i_37_, -122, -1);
                                                Component362.method1042((byte) -123);
                                                i_38_ += 12;
                                            }
                                            InflaterDecompressor.aBooleanArray2076[i_23_] = true;
                                        }
                                        continue;
                                    }
                                }
                                if (class46.anInt774 == 0) {
                                    if ((class46.anInt765 == Component86.anInt4532) && NodeSub8.aHa6654.method3666()) NodeSub8.aHa6654.method3658(i_24_, i_25_, class46.anInt709, class46.anInt789);
                                    method3064(i_31_, -class46.anInt755 + i_25_, bool, i_24_ - class46.anInt747, i_32_, i_23_, i_34_, false, class46s, class46.anInt830, i_33_);
                                    if (class46.aClass46Array798 != null) method3064(i_31_, -(class46.anInt755) + i_25_, bool, i_24_ - (class46.anInt747), i_32_, i_23_, i_34_, false, (class46.aClass46Array798), (class46.anInt830), i_33_);
                                    NodeSub41 class348_sub41 = ((NodeSub41) (Component15.aClass356_4915.method3480(class46.anInt830, -6008)));
                                    if (class348_sub41 != null) Component146.method1252(i_25_, (class348_sub41.anInt7050), i_32_, i_23_, i_24_, i_34_, i_33_, (byte) 60, i_31_);
                                    if ((class46.anInt765 == Component86.anInt4532) && NodeSub8.aHa6654.method3666()) NodeSub8.aHa6654.method3698();
                                    NodeSub8.aHa6654.KA(i, i_12_, i_14_, i_17_);
                                }
                                if (Component143.aBooleanArray2326[i_23_] || ha_Sub3.anInt8045 > 1) {
                                    if (class46.anInt774 == 3) {
                                        if (i_28_ == 0) {
                                            if (class46.aBoolean810) NodeSub8.aHa6654.fillRect(i_24_, i_25_, (class46.anInt709), (class46.anInt789), (class46.anInt749), 0);
                                            else NodeSub8.aHa6654.method3628(i_24_, i_25_, (class46.anInt709), (class46.anInt789), (class46.anInt749), 0);
                                        } else if (!class46.aBoolean810) NodeSub8.aHa6654.method3628(i_24_, i_25_, class46.anInt709, class46.anInt789, (0xffffff & class46.anInt749 | (-(i_28_ & 0xff) + 255 << 24)), 1);
                                        else NodeSub8.aHa6654.fillRect(i_24_, i_25_, class46.anInt709, class46.anInt789, (0xffffff & class46.anInt749 | (-(0xff & i_28_) + 255 << 24)), 1);
                                        if (Component210.aBoolean5300) {
                                            if (bool) Component103.method2663(-5590, i_19_, i_21_, i_20_, i_22_);
                                            else Component285.method503(i_22_, i_19_, (byte) -74, i_21_, i_20_);
                                        }
                                    } else if (class46.anInt774 == 4) {
                                        BitmapFont class324 = class46.method426((NodeSub8.aHa6654), (byte) 68);
                                        if (class324 == null) {
                                            if (HashNodeSub13.aBoolean9616) Component111.method1916(-9343, class46);
                                        } else {
                                            int i_50_ = class46.anInt749;
                                            String string = (class46.aString792);
                                            if (class46.anInt812 != -1) {
                                                NumberFormatter class213 = (Exception_Sub1.aClass255_112.method1940(-67, (class46.anInt812)));
                                                string = (class213.aString2795);
                                                if (string == null) string = "null";
                                                if (((class213.anInt2820) == 1 || (class46.anInt781) != 1) && (class46.anInt781) != -1) string = ("<col=ff9040>" + string + "</col> x" + (NameFormatter.method356(-127, (class46.anInt781))));
                                            }
                                            if (class46.anInt806 != -1) {
                                                string = (WaterSurfaceShader.method2157((class46.anInt806), -1431655765));
                                                if (string == null) string = "";
                                            }
                                            if (Component297.aClass46_4730 == class46) {
                                                string = (FriendsIgnoreList.aClass274_3514.method2063((ObjectDeserializer.anInt6967), 544));
                                                i_50_ = (class46.anInt749);
                                            }
                                            if (Component29.aBoolean10046) NodeSub8.aHa6654.T(i_24_, i_25_, (class46.anInt709) + i_24_, (class46.anInt789) + i_25_);
                                            class324.method2568(null, class46.anInt789, (byte) -77, null, 0, Component22.aClass105Array1744, (-(i_28_ & 0xff) + 255 << 24) | i_50_, i_25_, class46.anInt709, class46.anInt700, string, 0, i_24_, class46.anInt762, (!(class46.aBoolean769) ? -1 : (-(0xff & i_28_) + 255 << 24)), class46.anInt773, class46.anInt673);
                                            if (Component29.aBoolean10046) NodeSub8.aHa6654.KA(i, i_12_, i_14_, i_17_);
                                            if (string.trim().length() > 0) {
                                                if (Component29.aBoolean10046) {
                                                    if (Component210.aBoolean5300) {
                                                        if (!bool) Component285.method503(i_22_, i_19_, (byte) -74, i_21_, i_20_);
                                                        else Component103.method2663(-5590, i_19_, i_21_, i_20_, i_22_);
                                                    }
                                                } else {
                                                    Component184 class143 = (Component137.method1151(-25411, (NodeSub8.aHa6654), (class46.anInt702)));
                                                    int i_51_ = (class143.method1187(string, bool_15_, class46.anInt709, (Component22.aClass105Array1744)));
                                                    int i_52_ = (class143.method1185((Component22.aClass105Array1744), 0, class46.anInt673, class46.anInt709, string));
                                                    if (Component210.aBoolean5300) {
                                                        if (!bool) Component285.method503(i_25_ + i_52_, i_24_, (byte) -74, (i_24_ - -i_51_), i_25_);
                                                        else Component103.method2663(-5590, i_24_, i_51_ + i_24_, i_25_, (i_25_ + i_52_));
                                                    }
                                                }
                                            }
                                        }
                                    } else if (class46.anInt774 == 5) {
                                        if (class46.anInt705 >= 0)
                                            class46.method444(bool_15_, Component132.aClass25_1813, ComponentDownloader.aClass84_413).method2293(0, NodeSub8.aHa6654, (byte) -35, i_24_, class46.anInt709, i_25_, (class46.anInt835 << 3), (class46.anInt759 << 3), 0, class46.anInt789);
                                        else {
                                            Component24 class105;
                                            if (class46.anInt812 != -1) {
                                                Component101 class154 = ((class46.aBoolean720) ? (Component72.localPlayer.aClass154_10536) : null);
                                                class105 = (Exception_Sub1.aClass255_112.method1941((class46.anInt672), (byte) -74, (class46.anInt812), (class46.anInt781), (~0xffffff | (class46.anInt809)), (class46.anInt678), NodeSub8.aHa6654, class154));
                                            } else if ((class46.anInt806) == -1) class105 = (class46.method443(NodeSub8.aHa6654, (byte) -57));
                                            else class105 = (Component264.method2263(NodeSub8.aHa6654, 0, (class46.anInt806)));
                                            if (class105 == null) {
                                                if (HashNodeSub13.aBoolean9616) Component111.method1916(-9343, class46);
                                            } else {
                                                int i_53_ = class105.method966();
                                                int i_54_ = class105.method980();
                                                int i_55_ = ((-(i_28_ & 0xff) + 255 << 24) | ((class46.anInt749) != 0 ? (class46.anInt749 & 0xffffff) : 16777215));
                                                if (class46.aBoolean697) {
                                                    NodeSub8.aHa6654.T(i_24_, i_25_, i_24_ - -(class46.anInt709), (class46.anInt789) + i_25_);
                                                    if ((class46.anInt828) != 0) {
                                                        int i_56_ = ((i_53_ - 1 + (class46.anInt709)) / i_53_);
                                                        int i_57_ = ((i_54_ - 1 + (class46.anInt789)) / i_54_);
                                                        for (int i_58_ = 0; i_58_ < i_56_; i_58_++) {
                                                            for (int i_59_ = 0; i_59_ < i_57_; i_59_++) {
                                                                if ((class46.anInt749) == 0) class105.method981((((float) i_53_ / 2.0F) + (float) (i_24_ - -(i_53_ * i_58_))), (((float) i_54_ / 2.0F) + (float) (i_59_ * i_54_ + i_25_)), 4096, (class46.anInt828));
                                                                else class105.method977((((float) i_53_ / 2.0F) + (float) (i_53_ * i_58_ + i_24_)), (((float) i_54_ / 2.0F) + (float) (i_25_ + i_59_ * i_54_)), 4096, (class46.anInt828), 0, i_55_, 1);
                                                            }
                                                        }
                                                    } else if ((class46.anInt749) != 0 || (i_28_ != 0)) class105.method965(i_24_, i_25_, (class46.anInt709), (class46.anInt789), 0, i_55_, 1);
                                                    else class105.method972(i_24_, i_25_, (class46.anInt709), (class46.anInt789));
                                                    NodeSub8.aHa6654.KA(i, i_12_, i_14_, i_17_);
                                                } else if ((class46.anInt749) == 0 && i_28_ == 0) {
                                                    if ((class46.anInt828) != 0) class105.method981(((float) i_24_ + ((float) (class46.anInt709) / 2.0F)), (((float) (class46.anInt789) / 2.0F) + (float) i_25_), (4096 * (class46.anInt709) / i_53_), (class46.anInt828));
                                                    else if ((i_53_ == (class46.anInt709)) && ((class46.anInt789) == i_54_)) class105.method974(i_24_, i_25_);
                                                    else class105.method973(i_24_, i_25_, (class46.anInt709), (class46.anInt789));
                                                } else if (class46.anInt828 != 0) class105.method977(((float) i_24_ + ((float) (class46.anInt709) / 2.0F)), ((float) i_25_ + ((float) (class46.anInt789) / 2.0F)), (4096 * class46.anInt709 / i_53_), (class46.anInt828), 0, i_55_, 1);
                                                else if (((class46.anInt709) == i_53_) && (class46.anInt789 == i_54_)) class105.method964(i_24_, i_25_, 0, i_55_, 1);
                                                else class105.method970(i_24_, i_25_, (class46.anInt709), (class46.anInt789), 0, i_55_, 1);
                                            }
                                        }
                                        if (Component210.aBoolean5300) {
                                            if (!bool) Component285.method503(i_22_, i_19_, (byte) -74, i_21_, i_20_);
                                            else Component103.method2663(-5590, i_19_, i_21_, i_20_, i_22_);
                                        }
                                    } else if (class46.anInt774 == 6) {
                                        Component142.method3489(115);
                                        DisplayModeManagerContainer370 class64 = null;
                                        int i_60_ = 0;
                                        if (class46.anInt812 != -1) {
                                            NumberFormatter class213 = (Exception_Sub1.aClass255_112.method1940(103, (class46.anInt812)));
                                            if (class213 != null) {
                                                class213 = (class213.method1560((class46.anInt781), (byte) 97));
                                                DisplayModeManagerContainer167 class17 = ((class46.anInt699) == -1 ? null : (RunescapeInfo.aClass87_191.method835((class46.anInt699), 7)));
                                                Component101 class154 = (!(class46.aBoolean720) ? null : (Component72.localPlayer.aClass154_10536));
                                                class64 = (class213.method1559(class154, class17, NodeSub8.aHa6654, 2048, (class46.anInt841), 1, (class46.anInt795), (byte) 88, (class46.anInt730)));
                                                if (class64 != null) i_60_ = (-class64.fa() >> 1);
                                                else Component111.method1916(-9343, class46);
                                            }
                                        } else if ((class46.anInt770) == 5) {
                                            int i_61_ = class46.anInt753;
                                            if (i_61_ >= 0 && (i_61_ < 2048)) {
                                                Player player = (InterfaceRenderer.aPlayerArray5058[i_61_]);
                                                DisplayModeManagerContainer167 class17 = ((class46.anInt699) == -1 ? null : (RunescapeInfo.aClass87_191.method835((class46.anInt699), 7)));
                                                if ((player != null) && ((i_61_ == (StringDefinition.anInt9591)) || ((class46.anInt779) == (Component349.method2418((player.aString10544), (byte) -50)))))
                                                    class64 = (player.aClass154_10536.method1226((DisplayModeManagerContainer58.aClass170_10209), null, null, true, (Exception_Sub1.aClass255_112), 0, class17, class46.anInt730, true, -1, null, 2048, (DefinitionSub25.aClass150_9342), class46.anInt841, (NodeSub8.aHa6654), (Component291.aClass278_2529), (RunescapeInfo.aClass87_191), 0, class46.anInt795, 0, (DisplayModeManagerContainer282.aClass261_5558)));
                                            }
                                        } else if ((class46.anInt770) == 8 || (class46.anInt770) == 9) {
                                            NodeSub13 class348_sub13 = (AbstractGlTextureSub4.method1974((byte) -123, (class46.anInt753), false));
                                            DisplayModeManagerContainer167 class17 = ((class46.anInt699) == -1 ? null : (RunescapeInfo.aClass87_191.method835((class46.anInt699), 7)));
                                            if (class348_sub13 != null) {
                                                Component101 class154 = ((class46.aBoolean720) ? (Component72.localPlayer.aClass154_10536) : null);
                                                class64 = (class348_sub13.method2803(class17, class154, (class46.anInt779), (class46.anInt795), (class46.anInt841), (class46.anInt730), 2048, NodeSub8.aHa6654, (class46.anInt770) == 9, -1));
                                            }
                                        } else if (class46.anInt699 == -1) {
                                            class64 = (class46.method430((DefinitionSub25.aClass150_9342), RunescapeInfo.aClass87_191, 2048, NodeSub8.aHa6654, -1, 0, (Exception_Sub1.aClass255_112), (DisplayModeManagerContainer58.aClass170_10209), 255, (Component72.localPlayer.aClass154_10536), null, (DisplayModeManagerContainer282.aClass261_5558), (Component291.aClass278_2529), -1));
                                            if (class64 == null && (HashNodeSub13.aBoolean9616)) Component111.method1916(-9343, class46);
                                        } else {
                                            DisplayModeManagerContainer167 class17 = (RunescapeInfo.aClass87_191.method835((class46.anInt699), 7));
                                            class64 = (class46.method430((DefinitionSub25.aClass150_9342), RunescapeInfo.aClass87_191, 2048, NodeSub8.aHa6654, (class46.anInt795), (class46.anInt841), (Exception_Sub1.aClass255_112), (DisplayModeManagerContainer58.aClass170_10209), 255, (Component72.localPlayer.aClass154_10536), class17, (DisplayModeManagerContainer282.aClass261_5558), (Component291.aClass278_2529), (class46.anInt730)));
                                            if (class64 == null && (HashNodeSub13.aBoolean9616)) Component111.method1916(-9343, class46);
                                        }
                                        if (class64 != null) {
                                            int i_62_;
                                            if (class46.anInt796 > 0) i_62_ = (((class46.anInt709) << 9) / (class46.anInt796));
                                            else i_62_ = 512;
                                            int i_63_;
                                            if (class46.anInt826 <= 0) i_63_ = 512;
                                            else i_63_ = (((class46.anInt789) << 9) / (class46.anInt826));
                                            int i_64_ = (i_24_ - -((class46.anInt709) / 2));
                                            int i_65_ = i_25_ + (class46.anInt789) / 2;
                                            if (!class46.aBoolean784) {
                                                i_64_ += (i_62_ * (class46.anInt688) >> 9);
                                                i_65_ += (i_63_ * (class46.anInt799) >> 9);
                                            }
                                            Component270.aClass101_2123.method910();
                                            NodeSub8.aHa6654.method3638(Component270.aClass101_2123);
                                            NodeSub8.aHa6654.DA(i_64_, i_65_, i_62_, i_63_);
                                            NodeSub8.aHa6654.ya();
                                            if (class46.aBoolean754) NodeSub8.aHa6654.C(false);
                                            if (class46.aBoolean784) {
                                                Cp1252Decoder.aClass101_5209.method899(class46.anInt757);
                                                Cp1252Decoder.aClass101_5209.method896(class46.anInt675);
                                                Cp1252Decoder.aClass101_5209.method908(class46.anInt717);
                                                Cp1252Decoder.aClass101_5209.method891((class46.anInt688), (class46.anInt799), (class46.anInt787));
                                            } else {
                                                int i_66_ = ((((class46.anInt716) << 2) * (DisplayModeManagerContainer88.anIntArray1207[(class46.anInt757 << 3)])) >> 14);
                                                int i_67_ = (((DisplayModeManagerContainer88.anIntArray1204[((class46.anInt757) << 3)]) * ((class46.anInt716) << 2)) >> 14);
                                                Cp1252Decoder.aClass101_5209.method902(-(class46.anInt717) << 3);
                                                Cp1252Decoder.aClass101_5209.method896((class46.anInt675) << 3);
                                                Cp1252Decoder.aClass101_5209.method891(((class46.anInt808) << 2), (((class46.anInt786) << 2) + (i_66_ + i_60_)), ((class46.anInt786) << 2) + i_67_);
                                                Cp1252Decoder.aClass101_5209.method900((class46.anInt757) << 3);
                                            }
                                            class46.method437(-20154, class64, NodeSub8.aHa6654, OpenGlShader.clientCycle, Cp1252Decoder.aClass101_5209);
                                            if (Component29.aBoolean10046) NodeSub8.aHa6654.T(i_24_, i_25_, (class46.anInt709) + i_24_, (i_25_ + (class46.anInt789)));
                                            if (!class46.aBoolean784) {
                                                if (!class46.aBoolean689) {
                                                    class64.method615((Cp1252Decoder.aClass101_5209), null, 1);
                                                    if ((class46.aClass318_Sub10_740) != null) NodeSub8.aHa6654.method3684(class46.aClass318_Sub10_740.method2539());
                                                } else class64.method608((Cp1252Decoder.aClass101_5209), null, ((class46.anInt716) << 2), 1);
                                            } else if (class46.aBoolean689) class64.method608((Cp1252Decoder.aClass101_5209), null, (class46.anInt716), 1);
                                            else {
                                                class64.method615((Cp1252Decoder.aClass101_5209), null, 1);
                                                if ((class46.aClass318_Sub10_740) != null) NodeSub8.aHa6654.method3684(class46.aClass318_Sub10_740.method2539());
                                            }
                                            if (Component29.aBoolean10046) NodeSub8.aHa6654.KA(i, i_12_, i_14_, i_17_);
                                            if (class46.aBoolean754) NodeSub8.aHa6654.C(true);
                                        }
                                        if (Component210.aBoolean5300) {
                                            if (bool) Component103.method2663(-5590, i_19_, i_21_, i_20_, i_22_);
                                            else Component285.method503(i_22_, i_19_, (byte) -74, i_21_, i_20_);
                                        }
                                    } else if (class46.anInt774 == 9) {
                                        int i_68_;
                                        int i_69_;
                                        int i_70_;
                                        int i_71_;
                                        if (class46.aBoolean744) {
                                            i_68_ = (class46.anInt789 + i_25_);
                                            i_69_ = i_24_;
                                            i_70_ = (class46.anInt709 + i_24_);
                                            i_71_ = i_25_;
                                        } else {
                                            i_71_ = i_25_ + (class46.anInt789);
                                            i_70_ = (class46.anInt709 + i_24_);
                                            i_68_ = i_25_;
                                            i_69_ = i_24_;
                                        }
                                        if (class46.anInt690 != 1) NodeSub8.aHa6654.method3688(i_69_, i_68_, i_70_, i_71_, class46.anInt749, class46.anInt690, 0);
                                        else NodeSub8.aHa6654.method3709(i_69_, i_68_, i_70_, i_71_, class46.anInt749, 0);
                                        if (Component210.aBoolean5300) {
                                            if (bool) Component103.method2663(-5590, i_19_, i_21_, i_20_, i_22_);
                                            else Component285.method503(i_22_, i_19_, (byte) -74, i_21_, i_20_);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (RuntimeException runtimeexception) {
            throw NpcDefinition.method2929(runtimeexception, ("jia.B(" + i + ',' + i_10_ + ',' + bool + ',' + i_11_ + ',' + i_12_ + ',' + i_13_ + ',' + i_14_ + ',' + bool_15_ + ',' + (class46s != null ? "{...}" : "null") + ',' + i_16_ + ',' + i_17_ + ')'));
        }
    }

    final void method3049(Buffer class348_sub49, int i, int i_72_) {
        while_146_:
        do {
            try {
                anInt9146++;
                if (i_72_ == 31015) {
                    int i_73_ = i;
                    do {
                        if (i_73_ == 0) {
                            aBoolean9140 = class348_sub49.readUnsignedByte(255) == 1;
                            return;
                        } else if (i_73_ != 1) {
                            if (i_73_ == 2) break;
                            break while_146_;
                        }
                        aBoolean9147 = class348_sub49.readUnsignedByte(255) == 1;
                        return;
                    } while (false);
                    this.aBoolean7045 = class348_sub49.readUnsignedByte(255) == 1;
                    break;
                }
                break;
            } catch (RuntimeException runtimeexception) {
                throw NpcDefinition.method2929(runtimeexception, ("jia.F(" + (class348_sub49 != null ? "{...}" : "null") + ',' + i + ',' + i_72_ + ')'));
            }
        } while (false);
    }

    public ImageDefinition() {
        super(1, false);
    }

    static final void method3065(int i, boolean bool, int i_74_) {
        Component269.anInt8765++;
        anInt9142++;
        if (bool != false) method3065(-42, true, 43);
        ParticleSystem class348_sub47 = ParticleShader.method2148(PrimitiveTypeDefinition.aClass351_9089, DisplayModeManagerContainer64.aClass77_9029, -97);
        class348_sub47.aClass348_Sub49_Sub2_7116.writeShortAdd(52, i_74_);
        class348_sub47.aClass348_Sub49_Sub2_7116.writeIntMiddle(i, (byte) 44);
        HashNodeSub14.method3243(25, class348_sub47);
    }

    final int[] method3042(int i, int i_75_) {
        anInt9143++;
        if (i_75_ != 255) aMenuEntry_9144 = null;
        int[] is = this.aClass191_7032.method1433(0, i);
        if (this.aClass191_7032.aBoolean2570) {
            int[] is_76_ = this.method3048((aBoolean9147 ? DisplayModeManagerContainer356.anInt6325 - i : i), i_75_ + 633706082, 0);
            if (aBoolean9140) {
                for (int i_77_ = 0; (i_77_ < DefinitionSub6.anInt9139); i_77_++)
                    is[i_77_] = is_76_[Component287.anInt6076 + -i_77_];
            } else Component313.method1578(is_76_, 0, is, 0, DefinitionSub6.anInt9139);
        }
        return is;
    }

    public static void method3066(int i) {
        if (i != -1) aMenuEntry_9144 = null;
        aMenuEntry_9144 = null;
    }
}
