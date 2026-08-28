/* Applet_Sub1 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

import jagex3.jagmisc.jagmisc;

import java.applet.Applet;
import java.applet.AppletContext;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Date;

public abstract class Applet_Sub1 extends Applet implements Runnable, FocusListener, WindowListener {
    static int anInt1;
    static int anInt2;
    static int anInt3;
    static int anInt4;
    static int anInt5;
    static int anInt6;
    static int anInt7;
    static int anInt8;
    static int anInt9;
    static int anInt10;
    static int anInt11;
    static int anInt12;
    static int anInt13;
    static int anInt14;
    static int anInt15;
    static int anInt16;
    private boolean aBoolean17 = false;
    static int anInt18;
    static int anInt19;
    static BitmapFont aClass324_20;
    static int anInt21;
    static int anInt22;
    static int anInt23;
    static int anInt24;
    static int anInt25;
    static int anInt26;
    private boolean aBoolean27 = false;
    static int anInt28;
    static int anInt29;
    static int anInt30;
    static int anInt31;
    static int anInt32;
    static int anInt33;
    static int anInt34;
    static int anInt35;
    static int anInt36;
    static int anInt37;
    static int[] anIntArray38;
    static int anInt39;
    static int anInt40;
    public static boolean aBoolean41;
    public static int anInt42;
    public static int anInt43;
    public static int anInt44;
    public static int anInt45;
    public static boolean aBoolean46;
    public static boolean aBoolean47;
    public static boolean aBoolean48;
    public static boolean aBoolean49;
    public static boolean aBoolean50;
    public static boolean aBoolean51;
    public static boolean aBoolean52;
    public static int anInt53;
    public static boolean aBoolean54;
    public static int anInt55;
    public static boolean aBoolean56;
    public static int anInt57;
    public static boolean aBoolean58;
    public static int anInt59;

    abstract void method80(int i);

    public final void stop() {
        anInt24++;
        if (this == DefinitionSub9.anApplet_Sub1_9169 && !AssetCacheLoader.aBoolean384) Component22.aLong1739 = Component240.method599(-73) - -4000L;
    }

    public final String getParameter(String string) {
        anInt3++;
        if (RSACipher.aFrame4904 != null) return null;
        if (ToolkitFactory.anApplet1530 != null && ToolkitFactory.anApplet1530 != this) return ToolkitFactory.anApplet1530.getParameter(string);
        return super.getParameter(string);
    }

    String method81(byte i) {
        anInt1++;
        if (i <= 40) run();
        return null;
    }

    final void method82(int i, String string) {
        int i_0_ = 88 / ((i - -5) / 54);
        anInt22++;
        if (!aBoolean27) {
            aBoolean27 = true;
            System.out.println("error_game_" + string);
            try {
                AppletInvoker.method1617((byte) 125, ToolkitFactory.anApplet1530, "loggedout");
            } catch (Throwable throwable) {
                /* empty */
            }
            try {
                getAppletContext().showDocument(new URL(getCodeBase(), ("error_game_" + string + ".ws")), "_top");
            } catch (Exception exception) {
                /* empty */
            }
        }
    }

    public final void windowActivated(WindowEvent windowevent) {
        anInt16++;
    }

    final boolean method83(boolean bool) {
        if (bool != true) getDocumentBase();
        anInt5++;
        return DefinitionSub19.method3098(-30282, "jagmisc");
    }

    public final void focusLost(FocusEvent focusevent) {
        anInt9++;
        ToolbarRefreshDefinition.aBoolean9229 = false;
    }

    private final void method84(int i) {
        anInt8++;
        long l = Component240.method599(i + -88);
        long l_1_ = ShaderCompilerSub2Sub1.aLongArray8800[Component283.anInt4613];
        ShaderCompilerSub2Sub1.aLongArray8800[Component283.anInt4613] = l;
        Component283.anInt4613 = 0x1f & 1 + Component283.anInt4613;
        if (l_1_ != 0L && l > l_1_) {
            /* empty */
        }
        synchronized (this) {
            Component143.aBoolean2329 = ToolbarRefreshDefinition.aBoolean9229;
        }
        method99((byte) 93);
        if (i != -1) aBoolean27 = true;
    }

    public final void update(Graphics graphics) {
        anInt34++;
        paint(graphics);
    }

    static final void method85(int i, CacheStore class45) {
        anInt32++;
        Component76.aClass45_8601 = class45;
        if (i != 0) anInt37 = 101;
    }

    public final void windowClosing(WindowEvent windowevent) {
        anInt15++;
        destroy();
    }

    public final URL getDocumentBase() {
        anInt30++;
        if (RSACipher.aFrame4904 != null) return null;
        if (ToolkitFactory.anApplet1530 != null && this != ToolkitFactory.anApplet1530) return ToolkitFactory.anApplet1530.getDocumentBase();
        return super.getDocumentBase();
    }

    static final boolean method86(String string, int i) {
        anInt13++;
        if (i != 0) return true;
        return Component300.aHashtable3548.containsKey(string);
    }

    public final void windowDeactivated(WindowEvent windowevent) {
        anInt12++;
    }

    synchronized void method87(byte i) {
        if (i > -11) paint(null);
        if (DisplayModeManagerContainer50.gameCanvas != null) {
            DisplayModeManagerContainer50.gameCanvas.removeFocusListener(this);
            DisplayModeManagerContainer50.gameCanvas.getParent().setBackground(Color.black);
            DisplayModeManagerContainer50.gameCanvas.getParent().remove(DisplayModeManagerContainer50.gameCanvas);
        }
        anInt7++;
        Container container;
        if (Component225.aFrame476 == null) {
            if (RSACipher.aFrame4904 == null) {
                if (ToolkitFactory.anApplet1530 == null) container = DefinitionSub9.anApplet_Sub1_9169;
                else container = ToolkitFactory.anApplet1530;
            } else container = RSACipher.aFrame4904;
        } else container = Component225.aFrame476;
        container.setLayout(null);
        DisplayModeManagerContainer50.gameCanvas = new Canvas_Sub1(this);
        container.add(DisplayModeManagerContainer50.gameCanvas);
        DisplayModeManagerContainer50.gameCanvas.setSize(Component236.anInt4017, PacketReader.anInt10432);
        DisplayModeManagerContainer50.gameCanvas.setVisible(true);
        if (container == RSACipher.aFrame4904) {
            Insets insets = RSACipher.aFrame4904.getInsets();
            DisplayModeManagerContainer50.gameCanvas.setLocation((insets.left + NodeSub48.anInt7129), insets.top - -DisplayModeManagerContainer147.anInt4167);
        } else DisplayModeManagerContainer50.gameCanvas.setLocation(NodeSub48.anInt7129, DisplayModeManagerContainer147.anInt4167);
        DisplayModeManagerContainer50.gameCanvas.addFocusListener(this);
        DisplayModeManagerContainer50.gameCanvas.requestFocus();
        ToolbarRefreshDefinition.aBoolean9229 = true;
        Component143.aBoolean2329 = true;
        Component297.aBoolean4726 = true;
        DisplayModeManagerContainer351.aBoolean2674 = false;
        NodeSub12.aLong6748 = Component240.method599(-106);
    }

    public final void windowOpened(WindowEvent windowevent) {
        anInt39++;
    }

    public final synchronized void paint(Graphics graphics) {
        anInt18++;
        if (this == DefinitionSub9.anApplet_Sub1_9169 && !AssetCacheLoader.aBoolean384) {
            Component297.aBoolean4726 = true;
            if (AbstractShaderSub4.aBoolean7320 && -NodeSub12.aLong6748 + Component240.method599(-57) > 1000) {
                Rectangle rectangle = graphics.getClipBounds();
                if (rectangle == null || (rectangle.width >= SocketConnector.anInt3473 && (NodeSub22.anInt6857 <= rectangle.height))) DisplayModeManagerContainer351.aBoolean2674 = true;
            }
        }
    }

    private final void method88(int i) {
        anInt2++;
        long l = Component240.method599(-119);
        long l_2_ = Buffer.aLongArray7206[InflaterDecompressor.anInt2071];
        Buffer.aLongArray7206[InflaterDecompressor.anInt2071] = l;
        if (l_2_ != 0L && l_2_ < l) {
            int i_3_ = (int) (l - l_2_);
            DisplayModeManagerContainer348.anInt5891 = (32000 + (i_3_ >> 1)) / i_3_;
        }
        InflaterDecompressor.anInt2071 = InflaterDecompressor.anInt2071 - -1 & 0x1f;
        if (Component146.anInt2127++ > 50) {
            Component146.anInt2127 -= 50;
            Component297.aBoolean4726 = true;
            DisplayModeManagerContainer50.gameCanvas.setSize(Component236.anInt4017, PacketReader.anInt10432);
            DisplayModeManagerContainer50.gameCanvas.setVisible(true);
            if (RSACipher.aFrame4904 != null && Component225.aFrame476 == null) {
                Insets insets = RSACipher.aFrame4904.getInsets();
                DisplayModeManagerContainer50.gameCanvas.setLocation((insets.left - -NodeSub48.anInt7129), (insets.top + DisplayModeManagerContainer147.anInt4167));
            } else DisplayModeManagerContainer50.gameCanvas.setLocation(NodeSub48.anInt7129, DisplayModeManagerContainer147.anInt4167);
        }
        method93(-11018);
        if (i > -107) method90(true, true);
    }

    public final void windowDeiconified(WindowEvent windowevent) {
        anInt35++;
    }

    public final void focusGained(FocusEvent focusevent) {
        anInt23++;
        ToolbarRefreshDefinition.aBoolean9229 = true;
        Component297.aBoolean4726 = true;
    }

    public final void windowClosed(WindowEvent windowevent) {
        anInt33++;
    }

    final boolean method89(int i) {
        anInt40++;
        return true;
        /*if (i <= 19) return true;
        String string = getDocumentBase().getHost().toLowerCase();
        if (string.equals("jagex.com") || string.endsWith(".jagex.com")) return true;
        if (string.equals("runescape.com") || string.endsWith(".runescape.com")) return true;
        if (string.equals("stellardawn.com") || string.endsWith(".stellardawn.com")) return true;
        if (string.endsWith("127.0.0.1")) return true;
        for (*//**//*; string.length() > 0 && string.charAt(-1 + string.length()) >= 48; string = string.substring(0, string.length() - 1)) {
            if (string.charAt(string.length() - 1) > 57) {
                break;
            }
        }
        if (string.endsWith("192.168.1.")) return true;
        method82(53, "invalidhost");
        return false;*/
    }

    private final void method90(boolean bool, boolean bool_4_) {
        anInt26++;
        synchronized (this) {
            if (AssetCacheLoader.aBoolean384) return;
            AssetCacheLoader.aBoolean384 = true;
        }
        System.out.println("Shutdown start - clean:" + bool);
        if (ToolkitFactory.anApplet1530 != null) ToolkitFactory.anApplet1530.destroy();
        if (bool_4_ != false) aBoolean17 = false;
        try {
            method80(0);
        } catch (Exception exception) {
            /* empty */
        }
        if (aBoolean17) {
            try {
                jagmisc.quit();
            } catch (Throwable throwable) {
                /* empty */
            }
            aBoolean17 = false;
        }
        Component36.method1945((byte) -128, true);
        NativeLibraryLoader.method1629(!bool_4_);
        if (DisplayModeManagerContainer50.gameCanvas != null) {
            try {
                DisplayModeManagerContainer50.gameCanvas.removeFocusListener(this);
                DisplayModeManagerContainer50.gameCanvas.getParent().remove(DisplayModeManagerContainer50.gameCanvas);
            } catch (Exception exception) {
                /* empty */
            }
        }
        if (OggUrlStream.aClass297_8992 != null) {
            try {
                OggUrlStream.aClass297_8992.method2234((byte) 103);
            } catch (Exception exception) {
                /* empty */
            }
        }
        method91((byte) 108);
        if (RSACipher.aFrame4904 != null) {
            RSACipher.aFrame4904.setVisible(false);
            RSACipher.aFrame4904.dispose();
            RSACipher.aFrame4904 = null;
        }
        System.out.println("Shutdown complete - clean:" + bool);
    }

    public static final void provideLoaderApplet(Applet applet) {
        anInt11++;
        ToolkitFactory.anApplet1530 = applet;
    }

    abstract void method91(byte i);

    public final AppletContext getAppletContext() {
        anInt19++;
        if (RSACipher.aFrame4904 != null) return null;
        if (ToolkitFactory.anApplet1530 != null && this != ToolkitFactory.anApplet1530) return ToolkitFactory.anApplet1530.getAppletContext();
        return super.getAppletContext();
    }

    public final URL getCodeBase() {
        anInt29++;
        if (RSACipher.aFrame4904 != null) return null;
        if (ToolkitFactory.anApplet1530 != null && this != ToolkitFactory.anApplet1530) return ToolkitFactory.anApplet1530.getCodeBase();
        return super.getCodeBase();
    }

    public abstract void init();

    public final void destroy() {
        anInt21++;
        if (DefinitionSub9.anApplet_Sub1_9169 == this && !AssetCacheLoader.aBoolean384) {
            Component22.aLong1739 = Component240.method599(-108);
            SpriteAtlasShader.method2161((byte) 77, 5000L);
            DisplayModeManagerContainer155.aClass297_2993 = null;
            method90(false, false);
        }
    }

    public final void run() {
        anInt28++;
        do {
            try {
                if (ReflectionInvoker.aString3782 != null) {
                    String string = ReflectionInvoker.aString3782.toLowerCase();
                    if (string.indexOf("sun") != -1 || string.indexOf("apple") != -1) {
                        String string_5_ = ReflectionInvoker.aString3796;
                        if (string_5_.equals("1.1") || string_5_.startsWith("1.1.") || string_5_.equals("1.2") || string_5_.startsWith("1.2.")) {
                            method82(-119, "wrongjava");
                            break;
                        }
                    } else if (string.indexOf("ibm") != -1 && (ReflectionInvoker.aString3796 == null || ReflectionInvoker.aString3796.equals("1.4.2"))) {
                        method82(81, "wrongjava");
                        break;
                    }
                }
                if (ReflectionInvoker.aString3796 != null && ReflectionInvoker.aString3796.startsWith("1.")) {
                    int i = 2;
                    int i_6_ = 0;
                    while (ReflectionInvoker.aString3796.length() > i) {
                        int i_7_ = ReflectionInvoker.aString3796.charAt(i);
                        if (i_7_ < 48 || i_7_ > 57) break;
                        i++;
                        i_6_ = 10 * i_6_ - (-i_7_ + 48);
                    }
                    if (i_6_ >= 5) AbstractShaderSub4.aBoolean7320 = true;
                }
                Applet applet = DefinitionSub9.anApplet_Sub1_9169;
                if (ToolkitFactory.anApplet1530 != null) applet = ToolkitFactory.anApplet1530;
                Method method = ReflectionInvoker.aMethod3786;
                if (method != null) {
                    try {
                        method.invoke(applet, Boolean.TRUE);
                    } catch (Throwable throwable) {
                        /* empty */
                    }
                }
                aa_Sub3.method168((byte) 103);
                Component162.method1119(false);
                method87((byte) -97);
                method92(28740);
                NodeSub8.aClass241_6660 = Component267.method1631(false);
                while (Component22.aLong1739 == 0L || (Component240.method599(-124) < Component22.aLong1739)) {
                    MatrixSub2.anInt5744 = NodeSub8.aClass241_6660.method1861(0, DisplayModeManagerContainer306.aLong4783);
                    for (int i = 0; MatrixSub2.anInt5744 > i; i++)
                        method84(-1);
                    method88(-119);
                    DummyClass.method3578((byte) -42, DisplayModeManagerContainer50.gameCanvas, (OggUrlStream.aClass297_8992));
                }
            } catch (Throwable throwable) {
                ClientErrorReporter.method1242(method81((byte) 109), throwable, 15004);
                method82(123, "crash");
            } finally {
                method90(true, false);
            }
        } while (false);
    }

    abstract void method92(int i);

    abstract void method93(int i);

    static final void set(String string) {
        Component126.aString4461 = string;
        NodeSub38.anInt7006 = string.length();
    }

    static final void method94(String string, int i) {
        anInt6++;
        if (ArbShaderProgram.aStringArray6200 == null) DisplayModeManagerContainer288.method249(2);
        ParticleShader.aCalendar6221.setTime(new Date(Component240.method599(-102)));
        int i_8_ = ParticleShader.aCalendar6221.get(11);
        int i_9_ = ParticleShader.aCalendar6221.get(12);
        int i_10_ = ParticleShader.aCalendar6221.get(13);
        String string_11_ = (Integer.toString(i_8_ / 10) + i_8_ % 10 + ":" + i_9_ / 10 + i_9_ % 10 + ":" + i_10_ / 10 + i_10_ % 10);
        String[] strings = DefinitionSub23.method3113('\n', true, string);
        for (int i_12_ = 0; i_12_ < strings.length; i_12_++) {
            for (int i_13_ = Component14.anInt8587; i_13_ > 0; i_13_--)
                ArbShaderProgram.aStringArray6200[i_13_] = ArbShaderProgram.aStringArray6200[-1 + i_13_];
            ArbShaderProgram.aStringArray6200[0] = string_11_ + ": " + strings[i_12_];
            if (Component40.aFileOutputStream6323 != null) {
                try {
                    Component40.aFileOutputStream6323.write(ClientSystemInfo.method2992(((ArbShaderProgram.aStringArray6200[0]) + "\n"), (byte) -20));
                } catch (java.io.IOException ioexception) {
                    /* empty */
                }
            }
            if (-1 + ArbShaderProgram.aStringArray6200.length > Component14.anInt8587) {
                Component14.anInt8587++;
                if (Component94.anInt3676 > 0) Component94.anInt3676++;
            }
        }
        int i_14_ = 85 / ((i - -1) / 52);
    }

    final void method95(int i, int i_15_, int i_16_, int i_17_, int i_18_, String string, int i_19_) {
        anInt25++;
        try {
            if (DefinitionSub9.anApplet_Sub1_9169 == null) {
                NodeSub48.anInt7129 = 0;
                NodeSub22.anInt6857 = PacketReader.anInt10432 = i_16_;
                SocketConnector.anInt3473 = Component236.anInt4017 = i;
                DisplayModeManagerContainer147.anInt4167 = 0;
                NodeSub1Sub3.anInt8818 = i_15_;
                DefinitionSub9.anApplet_Sub1_9169 = this;
                NodeSub8.anApplet6662 = ToolkitFactory.anApplet1530;
                DisplayModeManagerContainer155.aClass297_2993 = OggUrlStream.aClass297_8992 = new ReflectionInvoker(i_17_, string, i_18_, ToolkitFactory.anApplet1530 != null);
                Task class144 = OggUrlStream.aClass297_8992.method2236(this, -10240, 1);
                if (i_19_ != 50) anInt37 = -13;
                while (class144.anInt1997 == 0) SpriteAtlasShader.method2161((byte) -126, 10L);
            } else {
                NodeSub51.anInt7252++;
                if (NodeSub51.anInt7252 >= 3) method82(112, "alreadyloaded");
                else getAppletContext().showDocument(getDocumentBase(), "_self");
            }
        } catch (Throwable throwable) {
            ClientErrorReporter.method1242(null, throwable, 15004);
            method82(52, "crash");
        }
    }

    final void method96(int i, int i_20_, boolean bool, int i_21_, int i_22_, String string, int i_23_, int i_24_) {
        try {
            if (i_23_ != 23499) return;
            SocketConnector.anInt3473 = Component236.anInt4017 = i_20_;
            NodeSub22.anInt6857 = PacketReader.anInt10432 = i_24_;
            DefinitionSub9.anApplet_Sub1_9169 = this;
            DisplayModeManagerContainer147.anInt4167 = 0;
            NodeSub48.anInt7129 = 0;
            NodeSub1Sub3.anInt8818 = i_21_;
            NodeSub8.anApplet6662 = null;
            RSACipher.aFrame4904 = new Frame();
            RSACipher.aFrame4904.setTitle("Jagex");
            RSACipher.aFrame4904.setResizable(true);
            RSACipher.aFrame4904.addWindowListener(this);
            RSACipher.aFrame4904.setVisible(true);
            RSACipher.aFrame4904.toFront();
            Insets insets = RSACipher.aFrame4904.getInsets();
            RSACipher.aFrame4904.setSize(insets.right + (insets.left + SocketConnector.anInt3473), (insets.bottom + (NodeSub22.anInt6857 + insets.top)));
            DisplayModeManagerContainer155.aClass297_2993 = OggUrlStream.aClass297_8992 = new ReflectionInvoker(i, string, i_22_, true);
            Task class144 = OggUrlStream.aClass297_8992.method2236(this, i_23_ + -33739, 1);
            while (class144.anInt1997 == 0) SpriteAtlasShader.method2161((byte) 21, 10L);
        } catch (Exception exception) {
            ClientErrorReporter.method1242(null, exception, i_23_ + -8495);
        }
        anInt31++;
    }

    public final void windowIconified(WindowEvent windowevent) {
        anInt14++;
    }

    final boolean method97(int i) {
        if (i != -1) method88(-104);
        anInt4++;
        return DefinitionSub19.method3098(-30282, "jaclib");
    }

    public static void method98(int i) {
        anIntArray38 = null;
        aClass324_20 = null;
        if (i != 32717) method86(null, 65);
    }

    public final void start() {
        anInt36++;
        if (this == DefinitionSub9.anApplet_Sub1_9169 && !AssetCacheLoader.aBoolean384) Component22.aLong1739 = 0L;
    }

    abstract void method99(byte i);

    final boolean method100(int i) {
        anInt10++;
        if (i != 10) return true;
        return DefinitionSub19.method3098(-30282, "jagtheora");
    }
}
