/* DisplayModeManagerContainer67 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

final class DisplayModeManagerContainer67
/**
 * RENAMED from `Class112` (JODE-obfuscated).
 * Evidence: root class; no distinctive extends/strings
 */ implements Runnable {
    static int anInt1726;
    static Component170 aClass207_1727;
    static int anInt1728;
    static int anInt1729;
    private final HashTable aClass107_1730 = new HashTable();
    static int anInt1731;
    static int anInt1732;
    private Thread aThread1733;
    int anInt1734;
    static int anInt1735;
    static int anInt1736;
    static int anInt1737;
    private boolean aBoolean1738 = false;

    final HashNodeSub16Sub2 method1049(byte[] is, CacheIndexReader class137, byte i, int i_0_) {
        try {
            if (i != 10) aBoolean1738 = false;
            anInt1732++;
            HashNodeSub16Sub2 class348_sub42_sub16_sub2 = new HashNodeSub16Sub2();
            class348_sub42_sub16_sub2.aByteArray10461 = is;
            class348_sub42_sub16_sub2.aBoolean9663 = false;
            class348_sub42_sub16_sub2.aClass137_10458 = class137;
            class348_sub42_sub16_sub2.accessAge = i_0_;
            class348_sub42_sub16_sub2.anInt10457 = 2;
            method1050(class348_sub42_sub16_sub2, i + -123);
            return class348_sub42_sub16_sub2;
        } catch (RuntimeException runtimeexception) {
            throw NpcDefinition.method2929(runtimeexception, ("aca.G(" + (is != null ? "{...}" : "null") + ',' + (class137 != null ? "{...}" : "null") + ',' + i + ',' + i_0_ + ')'));
        }
    }

    private final void method1050(HashNodeSub16Sub2 class348_sub42_sub16_sub2, int i) {
        anInt1735++;
        synchronized (aClass107_1730) {
            aClass107_1730.add(true, class348_sub42_sub16_sub2);
            this.anInt1734++;
            if (i > -100) aClass207_1727 = null;
            aClass107_1730.notifyAll();
        }
    }

    final void method1051(boolean bool) {
        aBoolean1738 = bool;
        anInt1731++;
        synchronized (aClass107_1730) {
            aClass107_1730.notifyAll();
        }
        try {
            aThread1733.join();
        } catch (InterruptedException interruptedexception) {
            /* empty */
        }
        aThread1733 = null;
    }

    public static void method1052(byte i) {
        aClass207_1727 = null;
        int i_1_ = 107 % ((i - -20) / 50);
    }

    static final int method1053(int i) {
        anInt1728++;
        if (i != 3112) method1052((byte) 121);
        if (Component156.aClass46_3701 == null) {
            if (!Component364.aBoolean8335 && Component192.menuTip != null) return (Component192.menuTip.priority);
            int i_2_ = AbstractGlTextureSub4.mouseHandler.getCursorX(true);
            int i_3_ = AbstractGlTextureSub4.mouseHandler.getCursorY((byte) 81);
            if (PauseHandler.aBoolean9535) {
                if (i_2_ > DisplayModeManagerContainer136.anInt4717 && i_2_ < Component227.anInt1117 + DisplayModeManagerContainer136.anInt4717) {
                    int i_9_ = -1;
                    for (int i_10_ = 0; DisplayModeManagerContainer345.anInt166 > i_10_; i_10_++) {
                        if (DisplayModeManagerContainer5.aBoolean1211) {
                            int i_11_ = (DefinitionGroup.anInt9532 - -33 - -(i_10_ * 16));
                            if (i_3_ > -13 + i_11_ && i_3_ <= i_11_ + 3) i_9_ = i_10_;
                        } else {
                            int i_12_ = (i_10_ * 16 + 31 + DefinitionGroup.anInt9532);
                            if (i_3_ > -13 + i_12_ && i_3_ <= i_12_ + 3) i_9_ = i_10_;
                        }
                    }
                    if (i_9_ != -1) {
                        int i_13_ = 0;
                        ClientErrorReporter class156 = new ClientErrorReporter(Component237.aClass107_3022);
                        for (HashNodeSub13 class348_sub42_sub13 = ((HashNodeSub13) class156.method1240(110)); class348_sub42_sub13 != null; class348_sub42_sub13 = ((HashNodeSub13) class156.method1243((byte) 77))) {
                            if (i_9_ == i_13_++) return ((MenuEntry) class348_sub42_sub13.aClass107_9621.sentinel.next).priority;
                        }
                    }
                } else if (Component359.aClass348_Sub42_Sub13_3152 != null && DisplayModeManagerContainer368.anInt5252 < i_2_ && (NodeSub1Sub1.anInt8806 + DisplayModeManagerContainer368.anInt5252) > i_2_) {
                    int i_4_ = -1;
                    for (int i_5_ = 0; ((Component359.aClass348_Sub42_Sub13_3152.anInt9615) > i_5_); i_5_++) {
                        if (DisplayModeManagerContainer5.aBoolean1211) {
                            int i_7_ = MouseHandler.menuOriginY + (33 + 16 * i_5_);
                            if (-13 + i_7_ < i_3_ && 3 + i_7_ >= i_3_) i_4_ = i_5_;
                        } else {
                            int i_6_ = i_5_ * 16 + 31 + MouseHandler.menuOriginY;
                            if (-13 + i_6_ < i_3_ && i_6_ - -3 >= i_3_) i_4_ = i_5_;
                        }
                    }
                    if (i_4_ != -1) {
                        int i_8_ = 0;
                        ClientErrorReporter class156 = new ClientErrorReporter(Component359.aClass348_Sub42_Sub13_3152.aClass107_9621);
                        for (MenuEntry class348_sub42_sub12 = ((MenuEntry) class156.method1240(9)); class348_sub42_sub12 != null; class348_sub42_sub12 = ((MenuEntry) class156.method1243((byte) 90))) {
                            if (i_8_++ == i_4_) return (class348_sub42_sub12.priority);
                        }
                    }
                }
            } else if (i_2_ > DisplayModeManagerContainer136.anInt4717 && (i_2_ < DisplayModeManagerContainer136.anInt4717 - -Component227.anInt1117)) {
                int i_14_ = -1;
                for (int i_15_ = 0; DisplayModeManagerContainer306.menuEntryCount > i_15_; i_15_++) {
                    if (DisplayModeManagerContainer5.aBoolean1211) {
                        int i_17_ = ((-i_15_ + (-1 + DisplayModeManagerContainer306.menuEntryCount)) * 16 + DefinitionGroup.anInt9532 + 33);
                        if (i_3_ > i_17_ - 13 && i_17_ + 3 >= i_3_) i_14_ = i_15_;
                    } else {
                        int i_16_ = 31 + (DefinitionGroup.anInt9532 + (-i_15_ + (DisplayModeManagerContainer306.menuEntryCount - 1)) * 16);
                        if (-13 + i_16_ < i_3_ && i_16_ + 3 >= i_3_) i_14_ = i_15_;
                    }
                }
                if (i_14_ != -1) {
                    int i_18_ = 0;
                    Component37 class312 = new Component37(DefinitionSub4.menuEntries);
                    for (MenuEntry class348_sub42_sub12 = ((MenuEntry) class312.method2327((byte) -53)); class348_sub42_sub12 != null; class348_sub42_sub12 = ((MenuEntry) class312.method2329(i + -3102))) {
                        if (i_14_ == i_18_++) return (class348_sub42_sub12.priority);
                    }
                }
            }
        }
        return -1;
    }

    final HashNodeSub16Sub2 method1054(CacheIndexReader class137, int i, byte i_19_) {
        anInt1729++;
        HashNodeSub16Sub2 class348_sub42_sub16_sub2 = new HashNodeSub16Sub2();
        if (i_19_ != -112) return null;
        class348_sub42_sub16_sub2.aBoolean9663 = false;
        class348_sub42_sub16_sub2.anInt10457 = 3;
        class348_sub42_sub16_sub2.accessAge = i;
        class348_sub42_sub16_sub2.aClass137_10458 = class137;
        method1050(class348_sub42_sub16_sub2, -101);
        return class348_sub42_sub16_sub2;
    }

    public final void run() {
        while (!aBoolean1738) {
            HashNodeSub16Sub2 class348_sub42_sub16_sub2;
            synchronized (aClass107_1730) {
                class348_sub42_sub16_sub2 = ((HashNodeSub16Sub2) aClass107_1730.removeHead(20));
                if (class348_sub42_sub16_sub2 == null) {
                    try {
                        aClass107_1730.wait();
                    } catch (InterruptedException interruptedexception) {
                        /* empty */
                    }
                    continue;
                } else this.anInt1734--;
            }
            try {
                if ((class348_sub42_sub16_sub2.anInt10457) != 2) {
                    if (class348_sub42_sub16_sub2.anInt10457 == 3) class348_sub42_sub16_sub2.aByteArray10461 = (class348_sub42_sub16_sub2.aClass137_10458.readEntry((byte) -4, (int) class348_sub42_sub16_sub2.accessAge));
                } else class348_sub42_sub16_sub2.aClass137_10458.writeEntry(class348_sub42_sub16_sub2.aByteArray10461.length, (int) (class348_sub42_sub16_sub2.accessAge), class348_sub42_sub16_sub2.aByteArray10461, -7305);
            } catch (Exception exception) {
                ClientErrorReporter.method1242(null, exception, 15004);
            }
            class348_sub42_sub16_sub2.aBoolean9664 = false;
        }
        anInt1736++;
    }

    final HashNodeSub16Sub2 method1055(CacheIndexReader class137, int i, byte i_20_) {
        if (i_20_ >= -98) method1052((byte) 110);
        anInt1737++;
        HashNodeSub16Sub2 class348_sub42_sub16_sub2 = new HashNodeSub16Sub2();
        class348_sub42_sub16_sub2.anInt10457 = 1;
        synchronized (aClass107_1730) {
            for (HashNodeSub16Sub2 class348_sub42_sub16_sub2_21_ = ((HashNodeSub16Sub2) aClass107_1730.first(-95)); class348_sub42_sub16_sub2_21_ != null; class348_sub42_sub16_sub2_21_ = ((HashNodeSub16Sub2) aClass107_1730.next((byte) 73))) {
                if ((class348_sub42_sub16_sub2_21_.accessAge == (long) i) && (class348_sub42_sub16_sub2_21_.aClass137_10458 == class137) && class348_sub42_sub16_sub2_21_.anInt10457 == 2) {
                    class348_sub42_sub16_sub2.aByteArray10461 = class348_sub42_sub16_sub2_21_.aByteArray10461;
                    class348_sub42_sub16_sub2.aBoolean9664 = false;
                    return class348_sub42_sub16_sub2;
                }
            }
        }
        class348_sub42_sub16_sub2.aByteArray10461 = class137.readEntry((byte) -4, i);
        class348_sub42_sub16_sub2.aBoolean9663 = true;
        class348_sub42_sub16_sub2.aBoolean9664 = false;
        return class348_sub42_sub16_sub2;
    }

    DisplayModeManagerContainer67(ReflectionInvoker class297) {
        this.anInt1734 = 0;
        Task class144 = class297.method2236(this, -10240, 5);
        while (class144.anInt1997 == 0) SpriteAtlasShader.method2161((byte) 43, 10L);
        if (class144.anInt1997 == 2) throw new RuntimeException();
        aThread1733 = (Thread) class144.result;
    }
}
