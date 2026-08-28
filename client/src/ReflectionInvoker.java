/* ReflectionInvoker - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

/**
 * RENAMED from `Class297` (JODE-obfuscated).
 * Reflection-based method/constructor invoker (invokeByOpcode dispatches by opcode). Used to call into restricted JDK APIs (e.g. system Clipboard get/setContents) and load classes; enforces a classloader null-check SecurityException. Implements Runnable.
 */

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.Transferable;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;

final class ReflectionInvoker implements Runnable {
    private Callback_Sub1 aCallback_Sub1_3776;
    boolean aBoolean3777;
    static String aString3778;
    RandomAccessFileReader aClass234_3779 = null;
    static String aString3780;
    private static volatile long aLong3781 = 0L;
    static String aString3782;
    static Method aMethod3783;
    private static String aString3784;
    RandomAccessFileReader aClass234_3785 = null;
    static Method aMethod3786;
    private Object anObject3787;
    RandomAccessFileReader aClass234_3788;
    private static String aString3789;
    private final Thread aThread3790;
    private Object anObject3791;
    private static int anInt3792;
    private Object anObject3793;
    boolean aBoolean3794;
    RandomAccessFileReader[] aClass234Array3795;
    static String aString3796;
    private Task aClass144_3797;
    private Task aClass144_3798;
    EventQueue anEventQueue3799;
    private static String aString3800;
    private boolean aBoolean3801;
    private Component138 aClass134_3802;
    static String aString3803;
    /*synthetic*/ static Class aClass3804;
    /*synthetic*/ static Class aClass3805;
    /*synthetic*/ static Class aClass3806;
    /*synthetic*/ static Class aClass3807;

    final Task method2229(int i, int i_0_, int i_1_, int i_2_, byte i_3_) {
        if (i_3_ != -11) aLong3781 = 34L;
        return invokeByOpcode(8, (i_1_ << 16) - -i, 6, i_0_ + (i_2_ << 16), null);
    }

    final Task method2230(Class[] var_classes, int i, Class var_class, String string) {
        if (i < 49) return null;
        return invokeByOpcode(8, 0, 8, 0, new Object[]{var_class, string, var_classes});
    }

    static final RandomAccessFileReader method2231(String string, int i) {
        if (i != -1141472112) return null;
        return method2241(string, 12606, aString3789, anInt3792);
    }

    final Task method2232(int i, int i_4_) {
        if (i <= 20) method2236(null, -123, -128);
        return invokeByOpcode(8, 0, 3, i_4_, null);
    }

    final Task method2233(byte i, String string, boolean bool) {
        if (i != -46) return null;
        if (!bool) return invokeByOpcode(8, 0, 13, 0, string);
        return invokeByOpcode(8, 0, 12, 0, string);
    }

    public final void run() {
        for (; ; ) {
            Task class144;
            synchronized (this) {
                for (; ; ) {
                    if (aBoolean3801) return;
                    if (aClass144_3797 != null) {
                        class144 = aClass144_3797;
                        aClass144_3797 = aClass144_3797.aClass144_1995;
                        if (aClass144_3797 == null) aClass144_3798 = null;
                        break;
                    }
                    try {
                        this.wait();
                    } catch (InterruptedException interruptedexception) {
                        /* empty */
                    }
                }
            }
            try {
                int i = class144.anInt1994;
                if (i == 1) {
                    if (aLong3781 > Component240.currentTimeMillis(-53)) throw new IOException();
                    if (Loader.debug) {
                        System.out.println("Connect: " + class144.anObject1996 + " " + class144.anInt2000);
                    }
                    {
                        String host = (String) class144.anObject1996;
                        InetAddress addr;
                        if (host != null && host.matches("\\d+\\.\\d+\\.\\d+\\.\\d+")) {
                            String[] p = host.split("\\.");
                            addr = InetAddress.getByAddress(new byte[]{
                                    (byte) Integer.parseInt(p[0]),
                                    (byte) Integer.parseInt(p[1]),
                                    (byte) Integer.parseInt(p[2]),
                                    (byte) Integer.parseInt(p[3])
                            });
                        } else {
                            addr = InetAddress.getByName(host);
                        }
                        Socket socket = new Socket();
                        socket.connect(new java.net.InetSocketAddress(addr, class144.anInt2000), 5000);
                        class144.result = socket;
                    }
                    if (Loader.debug) {
                        System.out.println("Connect OK: " + class144.anObject1996 + ":" + class144.anInt2000);
                    }
                } else if (i == 22) {
                    if (aLong3781 > Component240.currentTimeMillis(-92)) throw new IOException();
                    try {
                        class144.result = Component128.method593(class144.anInt2000, (byte) -90, ((String) class144.anObject1996)).connect(-112);
                    } catch (IOException_Sub1 ioexception_sub1) {
                        class144.result = ioexception_sub1.getMessage();
                        throw ioexception_sub1;
                    }
                } else if (i == 2) {
                    Thread thread = new Thread((Runnable) (class144.anObject1996));
                    thread.setDaemon(true);
                    thread.start();
                    thread.setPriority(class144.anInt2000);
                    class144.result = thread;
                } else if (i == 4) {
                    if (Component240.currentTimeMillis(-73) < aLong3781) throw new IOException();
                    class144.result = new DataInputStream(((URL) (class144.anObject1996)).openStream());
                } else if (i == 8) {
                    Object[] objects = ((Object[]) class144.anObject1996);
                    if (this.aBoolean3777 && (((Class) objects[0]).getClassLoader() == null)) throw new SecurityException();
                    class144.result = (((Class) objects[0]).getDeclaredMethod((String) objects[1], (Class[]) objects[2]));
                } else if (i == 9) {
                    Object[] objects = ((Object[]) class144.anObject1996);
                    if (this.aBoolean3777 && (((Class) objects[0]).getClassLoader() == null)) throw new SecurityException();
                    class144.result = (((Class) objects[0]).getDeclaredField((String) objects[1]));
                } else if (i == 18) {
                    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                    class144.result = clipboard.getContents(null);
                } else if (i == 19) {
                    Transferable transferable = ((Transferable) (class144.anObject1996));
                    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                    clipboard.setContents(transferable, null);
                } else if (this.aBoolean3777) {
                    if (i == 3) {
                        if (aLong3781 > Component240.currentTimeMillis(-123)) throw new IOException();
                        String string = (((0xff & (class144.anInt2000 >> 24))) + "." + (0xff & (class144.anInt2000 >> 16)) + "." + ((class144.anInt2000 >> 8) & 0xff) + "." + (0xff & class144.anInt2000));
                        class144.result = InetAddress.getByName(string).getHostName();
                    } else if (i == 21) {
                        if (Component240.currentTimeMillis(-82) < aLong3781) throw new IOException();
                        class144.result = InetAddress.getByName((String) (class144.anObject1996)).getAddress();
                    } else if (i != 5) {
                        if (i == 6) {
                            Frame frame = (new Frame("Jagex Full Screen"));
                            class144.result = frame;
                            frame.setResizable(false);
                            if (this.aBoolean3794) aClass134_3802.method1146((class144.anInt2000 & 0xffff), (class144.anInt1999 >> 16), (class144.anInt2000 >>> 16), -43, 0xffff & (class144.anInt1999), frame);
                            else
                                ((DisplayModeManager) anObject3793).setDisplayMode(frame, (new Integer((class144.anInt2000) >>> 16)), (new Integer(0xffff & (class144.anInt2000))), (new Integer((class144.anInt1999) >> 16)), (new Integer((class144.anInt1999) & 0xffff)));
                        } else if (i == 7) {
                            if (this.aBoolean3794) aClass134_3802.method1147(((Frame) (class144.anObject1996)), 8);
                            else ((DisplayModeManager) anObject3793).restoreDisplayMode();
                        } else if (i == 12) {
                            RandomAccessFileReader class234 = (method2241(((String) (class144.anObject1996)), 12606, aString3789, anInt3792));
                            class144.result = class234;
                        } else if (i == 13) {
                            RandomAccessFileReader class234 = (method2241(((String) (class144.anObject1996)), 12606, "", anInt3792));
                            class144.result = class234;
                        } else if ((this.aBoolean3777) && i == 14) {
                            int i_5_ = (class144.anInt2000);
                            int i_6_ = (class144.anInt1999);
                            if (this.aBoolean3794) aCallback_Sub1_3776.method3621(i_5_, (byte) 115, i_6_);
                            else ((CursorManager) anObject3791).setCursorPos((new Integer(i_5_)), (new Integer(i_6_)));
                        } else if ((this.aBoolean3777) && (i == 15)) {
                            boolean bool = ((class144.anInt2000) != 0);
                            Component component = ((Component) (class144.anObject1996));
                            if (this.aBoolean3794) aCallback_Sub1_3776.method3622(bool, 13259, component);
                            else ((CursorManager) anObject3791).setBlankCursor(component, (new Boolean(bool)));
                        } else if (!(this.aBoolean3794) && (i == 17)) {
                            Object[] objects = ((Object[]) (class144.anObject1996));
                            ((CursorManager) anObject3791).setCustomCursor((Component) objects[0], (int[]) objects[1], (new Integer(class144.anInt2000)), (new Integer(class144.anInt1999)), (Point) objects[2]);
                        } else if (i == 16) {
                            try {
                                if (!aString3803.startsWith("win")) throw new Exception();
                                String string = ((String) (class144.anObject1996));
                                if (!(string.startsWith("http://")) && !(string.startsWith("https://"))) throw new Exception();
                                String string_7_ = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789?&=,.%+-_#:/*";
                                for (int i_8_ = 0; (i_8_ < (string.length())); i_8_++) {
                                    if ((string_7_.indexOf(string.charAt(i_8_))) == -1) throw new Exception();
                                }
                                Runtime.getRuntime().exec("cmd /c start \"j\" \"" + string + "\"");
                                class144.result = null;
                            } catch (Exception exception) {
                                class144.result = exception;
                                throw exception;
                            }
                        } else throw new Exception("");
                    } else if (!this.aBoolean3794) class144.result = ((DisplayModeManager) anObject3793).getDisplayModesPacked();
                    else class144.result = aClass134_3802.method1145(true);
                } else throw new Exception("");
                class144.anInt1997 = 1;
            } catch (Throwable throwable) {
                if (Loader.debug && class144.anInt1994 == 1) {
                    System.out.println("Connect FAIL: " + class144.anObject1996 + ":" + class144.anInt2000
                            + " " + throwable.getClass().getSimpleName() + ": " + throwable.getMessage());
                }
                if (Loader.trace) {
                    throwable.printStackTrace();
                }
                class144.anInt1997 = 2;
            }
            synchronized (class144) {
                class144.notify();
            }
        }
    }

    final void method2234(byte i) {
        synchronized (this) {
            aBoolean3801 = true;
            this.notifyAll();
        }
        try {
            aThread3790.join();
            if (i != 103) this.aClass234_3788 = null;
        } catch (InterruptedException interruptedexception) {
            /* empty */
        }
        if (this.aClass234_3785 != null) {
            try {
                this.aClass234_3785.close(false);
            } catch (IOException ioexception) {
                /* empty */
            }
        }
        if (this.aClass234_3779 != null) {
            try {
                this.aClass234_3779.close(false);
            } catch (IOException ioexception) {
                /* empty */
            }
        }
        if (this.aClass234Array3795 != null) {
            for (int i_9_ = 0; i_9_ < this.aClass234Array3795.length; i_9_++) {
                if (this.aClass234Array3795[i_9_] != null) {
                    try {
                        this.aClass234Array3795[i_9_].close(false);
                    } catch (IOException ioexception) {
                        /* empty */
                    }
                }
            }
        }
        do {
            if (this.aClass234_3788 != null) {
                try {
                    this.aClass234_3788.close(false);
                } catch (IOException ioexception) {
                    break;
                }
                break;
            }
        } while (false);
    }

    final Task method2235(boolean bool, int i, String string, int i_10_) {
        if (i_10_ != 28225) return null;
        return invokeByOpcode(i_10_ ^ 0x6e49, 0, bool ? 22 : 1, i, string);
    }

    final Task method2236(Runnable runnable, int i, int i_11_) {
        if (i != -10240) method2242(null, (byte) 57, null);
        return invokeByOpcode(8, 0, 2, i_11_, runnable);
    }

    final Task method2237(URL url, int i) {
        if (i != 8362) return null;
        return invokeByOpcode(8, 0, 4, 0, url);
    }

    final Task method2238(int i, int[] is, int i_12_, Point point, Component component, int i_13_) {
        if (i_12_ != 17) method2240(-75);
        return invokeByOpcode(i_12_ + -9, i, 17, i_13_, new Object[]{component, is, point});
    }

    final void method2239(int i) {
        if (i > -90) this.aClass234_3779 = null;
        aLong3781 = 5000L + Component240.currentTimeMillis(-107);
    }

    final Task method2240(int i) {
        if (i != 972476528) return null;
        return invokeByOpcode(8, 0, 5, 0, null);
    }

    private static final RandomAccessFileReader method2241(String string, int i, String string_14_, int i_15_) {
        String string_16_;
        if (i_15_ == 33) string_16_ = "jagex_" + string_14_ + "_preferences" + string + "_rc.dat";
        else if (i_15_ != 34) string_16_ = "jagex_" + string_14_ + "_preferences" + string + ".dat";
        else string_16_ = "jagex_" + string_14_ + "_preferences" + string + "_wip.dat";
        if (i != 12606) return null;
        String[] strings = {"c:/rscache/", "/rscache/", aString3800, "c:/windows/", "c:/winnt/", "c:/", "/tmp/", ""};
        for (int i_17_ = 0; strings.length > i_17_; i_17_++) {
            String string_18_ = strings[i_17_];
            if (string_18_.length() <= 0 || new File(string_18_).exists()) {
                try {
                    RandomAccessFileReader class234 = new RandomAccessFileReader(new File(string_18_, string_16_), "rw", 10000L);
                    return class234;
                } catch (Exception exception) {
                    /* empty */
                }
            }
        }
        return null;
    }

    final boolean method2242(byte[] is, byte i, File file) {
        try {
            FileOutputStream fileoutputstream = new FileOutputStream(file);
            fileoutputstream.write(is, 0, is.length);
            if (i != -104) return false;
            fileoutputstream.close();
            return true;
        } catch (IOException ioexception) {
            throw new RuntimeException();
        }
    }

    final Task method2243(int i, String string, Class var_class) {
        if (i != 0) method2239(-13);
        return invokeByOpcode(8, 0, 9, 0, new Object[]{var_class, string});
    }

    final Object method2244(int i) {
        if (i != 21) method2237(null, 23);
        return anObject3787;
    }

    final Task method2245(String string, int i) {
        if (i != 16) return null;
        return invokeByOpcode(i + -8, 0, 16, 0, string);
    }

    private final Task invokeByOpcode(int i, int i_19_, int i_20_, int i_21_, Object object) {
        Task class144 = new Task();
        class144.anObject1996 = object;
        class144.anInt1999 = i_19_;
        class144.anInt1994 = i_20_;
        class144.anInt2000 = i_21_;
        synchronized (this) {
            if (aClass144_3798 == null) aClass144_3798 = aClass144_3797 = class144;
            else {
                aClass144_3798.aClass144_1995 = class144;
                aClass144_3798 = class144;
            }
            this.notify();
            if (i != 8) method2235(false, 76, null, 37);
        }
        return class144;
    }

    final boolean method2247(int i) {
        if (i != -4) method2245(null, 50);
        if (!this.aBoolean3777) return false;
        if (this.aBoolean3794) {
            return aClass134_3802 != null;
        }
        return anObject3793 != null;
    }

    final Task method2248(byte i, Frame frame) {
        if (i != 89) return null;
        return invokeByOpcode(i ^ 0x51, 0, 7, 0, frame);
    }

    ReflectionInvoker(int i, String string, int i_22_, boolean bool) throws Exception {
        this.aBoolean3777 = false;
        aClass144_3798 = null;
        aBoolean3801 = false;
        this.aClass234_3788 = null;
        this.aBoolean3794 = false;
        aClass144_3797 = null;
        aString3789 = string;
        this.aBoolean3777 = bool;
        aString3796 = "1.1";
        aString3782 = "Unknown";
        anInt3792 = i;
        try {
            aString3782 = System.getProperty("java.vendor");
            aString3796 = System.getProperty("java.version");
        } catch (Exception exception) {
            /* empty */
        }
        if (aString3782.toLowerCase().indexOf("microsoft") != -1) this.aBoolean3794 = true;
        try {
            aString3784 = System.getProperty("os.name");
        } catch (Exception exception) {
            aString3784 = "Unknown";
        }
        aString3803 = aString3784.toLowerCase();
        try {
            aString3780 = System.getProperty("os.arch").toLowerCase();
        } catch (Exception exception) {
            aString3780 = "";
        }
        try {
            aString3778 = System.getProperty("os.version").toLowerCase();
        } catch (Exception exception) {
            aString3778 = "";
        }
        try {
            aString3800 = System.getProperty("user.home");
            if (aString3800 != null) aString3800 += "/";
        } catch (Exception exception) {
            /* empty */
        }
        if (aString3800 == null) aString3800 = "~/";
        try {
            this.anEventQueue3799 = Toolkit.getDefaultToolkit().getSystemEventQueue();
        } catch (Throwable throwable) {
            /* empty */
        }
        if (!this.aBoolean3794) {
            try {
                aMethod3783 = Component.class.getDeclaredMethod("setFocusTraversalKeysEnabled", Boolean.TYPE);
            } catch (Exception exception) {
                /* empty */
            }
            try {
                aMethod3786 = (Container.class.getDeclaredMethod("setFocusCycleRoot", Boolean.TYPE));
            } catch (Exception exception) {
                /* empty */
            }
        }
        CacheDirectory.method1465((byte) -121, aString3789, anInt3792);
        if (this.aBoolean3777) {
            this.aClass234_3788 = new RandomAccessFileReader(CacheDirectory.method1464(0, anInt3792, "random.dat", null), "rw", 25L);
            this.aClass234_3785 = new RandomAccessFileReader(CacheDirectory.method1466(-2, "main_file_cache.dat2"), "rw", 209715200L);
            this.aClass234_3779 = new RandomAccessFileReader(CacheDirectory.method1466(-2, "main_file_cache.idx255"), "rw", 1048576L);
            this.aClass234Array3795 = new RandomAccessFileReader[i_22_];
            for (int i_23_ = 0; i_22_ > i_23_; i_23_++)
                this.aClass234Array3795[i_23_] = new RandomAccessFileReader(CacheDirectory.method1466(-2, ("main_file_cache.idx" + i_23_)), "rw", 1048576L);
            if (this.aBoolean3794) {
                try {
                    anObject3787 = new Component131();
                } catch (Throwable throwable) {
                    /* empty */
                }
            }
            try {
                if (!this.aBoolean3794) anObject3793 = new DisplayModeManager();
                else aClass134_3802 = new Component138();
            } catch (Throwable throwable) {
                /* empty */
            }
            try {
                if (!this.aBoolean3794) anObject3791 = new CursorManager();
                else aCallback_Sub1_3776 = new Callback_Sub1();
            } catch (Throwable throwable) {
                /* empty */
            }
        }
        if (this.aBoolean3777 && !this.aBoolean3794) {
            ThreadGroup threadgroup = Thread.currentThread().getThreadGroup();
            for (ThreadGroup threadgroup_24_ = threadgroup.getParent(); threadgroup_24_ != null; threadgroup_24_ = threadgroup.getParent())
                threadgroup = threadgroup_24_;
            Thread[] threads = new Thread[1000];
            threadgroup.enumerate(threads);
            for (int i_25_ = 0; threads.length > i_25_; i_25_++) {
                if (threads[i_25_] != null && threads[i_25_].getName().startsWith("AWT")) threads[i_25_].setPriority(1);
            }
        }
        aBoolean3801 = false;
        aThread3790 = new Thread(this);
        aThread3790.setPriority(10);
        aThread3790.setDaemon(true);
        aThread3790.start();
    }

}
