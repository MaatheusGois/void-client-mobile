/* KeyFocusHandler - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

/**
 * RENAMED from `Class346_Sub1` (JODE-obfuscated).
 * Keyboard + focus input handler. Extends InputHandler and implements KeyListener, FocusListener; queues Interface6 events via NodeLists.
 */

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.reflect.Method;

final class KeyFocusHandler extends InputHandler implements KeyListener, FocusListener {
    static int anInt6525;
    static int anInt6526;
    static int anInt6527;
    static Component183 aClass114_6528 = new Component183(14, 2);
    static int anInt6529;
    static int anInt6530;
    static int anInt6531;
    static int anInt6532;
    static int anInt6533;
    static int anInt6534;
    static int anInt6535;
    static int anInt6536;
    static int anInt6537;
    static int anInt6538;
    static int anInt6539;
    static int anInt6540;
    private final NodeList aClass262_6541 = new NodeList();
    private NodeList aClass262_6542 = new NodeList();
    private boolean[] aBooleanArray6543 = new boolean[112];
    private Component aComponent6544;

    final Interface6 popKeyEvent(int i) {
        if (i != 0) aBooleanArray6543 = null;
        anInt6525++;
        return (Interface6) aClass262_6541.peekFirst(8);
    }

    final boolean isKeyDown(int i, int i_0_) {
        if (i_0_ >= -120) method2700((byte) 119);
        anInt6539++;
        if (i < 0 || i >= 112) return false;
        return aBooleanArray6543[i];
    }

    private final void method2700(byte i) {
        if (i == -104) {
            anInt6533++;
            if (aComponent6544 != null) {
                aComponent6544.removeKeyListener(this);
                aComponent6544.removeFocusListener(this);
                aComponent6544 = null;
                for (int i_1_ = 0; i_1_ < 112; i_1_++)
                    aBooleanArray6543[i_1_] = false;
                aClass262_6541.clear(116);
                aClass262_6542.clear(i ^ ~0x1b);
            }
        }
    }

    public final synchronized void keyPressed(KeyEvent keyevent) {
        anInt6526++;
        method2701(keyevent, 0, 0);
    }

    private final void method2701(KeyEvent keyevent, int i, int i_2_) {
        anInt6538++;
        int i_3_ = keyevent.getKeyCode();
        if (i_3_ != 0) {
            if (i_3_ >= 0 && DebugPanicSub2.anIntArray8507.length > i_3_) {
                i_3_ = DebugPanicSub2.anIntArray8507[i_3_];
                if (i_2_ == 0 && (i_3_ & 0x80) != 0) i_3_ = 0;
                else i_3_ &= ~0x80;
            } else i_3_ = 0;
        } else i_3_ = 0;
        if (i != i_3_) {
            method2702(i_3_, 128, '\0', i_2_);
            keyevent.consume();
        }
    }

    final void reset(int i) {
        anInt6530++;
        method2700((byte) -104);
        if (i != 14174) aClass262_6542 = null;
    }

    private final void method2702(int i, int i_4_, char c, int i_5_) {
        anInt6532++;
        NodeSub11 class348_sub11 = new NodeSub11();
        class348_sub11.anInt4771 = i_5_;
        class348_sub11.anInt4767 = i;
        class348_sub11.aChar4761 = c;
        class348_sub11.aLong4764 = Component240.currentTimeMillis(-102);
        aClass262_6542.addTail(class348_sub11, -20180);
        if (i_4_ != 128) aComponent6544 = null;
    }

    public final synchronized void keyReleased(KeyEvent keyevent) {
        anInt6529++;
        method2701(keyevent, 0, 1);
    }

    public final synchronized void keyTyped(KeyEvent keyevent) {
        anInt6527++;
        char c = keyevent.getKeyChar();
        if (c != 0 && DisplayModeManagerContainer145.method1089(-125, c)) {
            method2702(-1, 128, c, 3);
            keyevent.consume();
        }
    }

    public final void focusGained(FocusEvent focusevent) {
        anInt6540++;
    }

    static final void method2703(int i, int i_6_, int i_7_, byte i_8_) {
        anInt6536++;
        DisplayModeManagerContainer56 class190 = NodeList.aClass190ArrayArray3335[i_6_][i_7_];
        if (i_8_ == -13) DisplayModeManagerContainer254.method1260(false, i, (class190 == null ? FriendsIgnoreList.aClass190_3547 : class190));
    }

    private final int method2704(byte i) {
        anInt6531++;
        int i_9_ = 0;
        if (aBooleanArray6543[81]) i_9_ |= 0x1;
        if (i != 127) return 25;
        if (aBooleanArray6543[82]) i_9_ |= 0x4;
        if (aBooleanArray6543[86]) i_9_ |= 0x2;
        return i_9_;
    }

    final synchronized void sync(int i) {
        anInt6534++;
        aClass262_6541.clear(110);
        if (i >= 51) {
            for (NodeSub11 class348_sub11 = (NodeSub11) aClass262_6542.peekFirst(8); class348_sub11 != null; class348_sub11 = (NodeSub11) aClass262_6542.peekFirst(8)) {
                class348_sub11.anInt4766 = method2704((byte) 127);
                if (class348_sub11.anInt4771 == 0) {
                    if (!aBooleanArray6543[class348_sub11.anInt4767]) {
                        NodeSub11 class348_sub11_10_ = new NodeSub11();
                        class348_sub11_10_.anInt4767 = class348_sub11.anInt4767;
                        class348_sub11_10_.anInt4771 = 0;
                        class348_sub11_10_.aChar4761 = '\0';
                        class348_sub11_10_.anInt4766 = class348_sub11.anInt4766;
                        class348_sub11_10_.aLong4764 = class348_sub11.aLong4764;
                        aClass262_6541.addTail(class348_sub11_10_, -20180);
                        aBooleanArray6543[(class348_sub11.anInt4767)] = true;
                    }
                    class348_sub11.anInt4771 = 2;
                    aClass262_6541.addTail(class348_sub11, -20180);
                } else if (class348_sub11.anInt4771 != 1) {
                    if (class348_sub11.anInt4771 == -1) {
                        for (int i_11_ = 0; i_11_ < 112; i_11_++) {
                            if (aBooleanArray6543[i_11_]) {
                                NodeSub11 class348_sub11_12_ = new NodeSub11();
                                class348_sub11_12_.aChar4761 = '\0';
                                class348_sub11_12_.anInt4767 = i_11_;
                                class348_sub11_12_.anInt4766 = (class348_sub11.anInt4766);
                                class348_sub11_12_.anInt4771 = 1;
                                class348_sub11_12_.aLong4764 = (class348_sub11.aLong4764);
                                aClass262_6541.addTail(class348_sub11_12_, -20180);
                                aBooleanArray6543[i_11_] = false;
                            }
                        }
                    } else if (class348_sub11.anInt4771 == 3) aClass262_6541.addTail(class348_sub11, -20180);
                } else if (aBooleanArray6543[class348_sub11.anInt4767]) {
                    aClass262_6541.addTail(class348_sub11, -20180);
                    aBooleanArray6543[(class348_sub11.anInt4767)] = false;
                }
            }
        }
    }

    public final synchronized void focusLost(FocusEvent focusevent) {
        anInt6537++;
        method2702(0, 128, '\0', -1);
    }

    public static void method2705(int i) {
        aClass114_6528 = null;
        if (i != 14645) method2705(1);
    }

    private final void method2706(Component component, int i) {
        method2700((byte) -104);
        if (i != 2) method2705(-19);
        anInt6535++;
        aComponent6544 = component;
        Method method = ReflectionInvoker.aMethod3783;
        if (method != null) {
            try {
                method.invoke(aComponent6544, Boolean.FALSE);
            } catch (Throwable throwable) {
                /* empty */
            }
        }
        aComponent6544.addKeyListener(this);
        aComponent6544.addFocusListener(this);
    }

    KeyFocusHandler(Component component) {
        Component182.method3402((byte) -82);
        method2706(component, 2);
    }
}
