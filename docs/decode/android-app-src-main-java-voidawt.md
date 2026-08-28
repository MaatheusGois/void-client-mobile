# Decode — `android/app/src/main/java/voidawt` (29 classes)

> **Subsistema:** UI — AWT reimplementado (voidawt)

Total: 29

---

## AwtHost.java — Classe AwtHost

- **Arquivo:** `android/app/src/main/java/voidawt/AwtHost.java`
- **Declaração:** `class AwtHost`
- **Package:** `voidawt`
- **Subsistema:** UI — AWT reimplementado (voidawt)
- **Tamanho:** 396 linhas

### Campos

| Nome | Tipo | Nota |
|---|---|---|
| `presenter` | `Presenter` |  |
| `root` | `Component` |  |
| `gameCanvas` | `Canvas` |  |
| `MAIN` | `Handler` |  |
| `locked` | `int[]` |  |
| `logical` | `int[]` |  |
| `r` | `Component` |  |
| `c` | `Canvas` |  |
| `frame` | `Object` |  |
| `f` | `java.lang.reflect.Field` |  |
| `f` | `java.lang.reflect.Field` |  |
| `p` | `Presenter` |  |

### Métodos

- **present(Bitmap frame)**
- **isExclusiveFullscreen()**
- **setDisplaySize(int width, int height)**
- **setDisplaySize(width, height, false)**
- **setDisplaySize(int width, int height, boolean pinExclusive)**
- **syncClientViewport(width, height)**
- **fullscreenLogicalSize()**
- **syncClientViewport(int width, int height)**
- **setStaticInt("Class92", "anInt1524", width)**
- **setStaticInt("ha_Sub2", "anInt7666", height)**
- **setStaticInt("Class321", "anInt4017", width)**
- **setStaticInt("Class348_Sub42_Sub8_Sub2", "anInt10432", height)**
- **setStaticInt("Class272", "anInt3473", width)**
- **setStaticInt("Class348_Sub22", "anInt6857", height)**
- **setStaticInt("Class348_Sub48", "anInt7129", 0)**
- **setStaticInt("Class335", "anInt4167", 0)**
- **setStaticBoolean("Class50_Sub1", "aBoolean5219", true)**
- **setStaticInt(String className, String field, int value)**
- **setStaticBoolean(String className, String field, boolean value)**
- **logicalSize(int surfaceW, int surfaceH)**
- **setRoot(Component component)**
- **onGameCanvas(Canvas canvas)**
- **present(int[] pixels, int width, int height)**
- **run()**
- **injectRightClick(int x, int y)**
- **injectMouse(MouseEvent.MOUSE_MOVED, x, y, 0, 0)**
- **injectMouse(MouseEvent.MOUSE_PRESSED, x, y, MouseEvent.BUTTON3, 1)**
- **injectMouse(MouseEvent.MOUSE_RELEASED, x, y, MouseEvent.BUTTON3, 1)**
- **injectMouse(MouseEvent.MOUSE_CLICKED, x, y, MouseEvent.BUTTON3, 1)**
- **injectLeftClick(int x, int y)**
- **injectMouse(MouseEvent.MOUSE_MOVED, x, y, 0, 0)**
- **injectMouse(MouseEvent.MOUSE_PRESSED, x, y, MouseEvent.BUTTON1, 1)**
- **injectMouse(MouseEvent.MOUSE_RELEASED, x, y, MouseEvent.BUTTON1, 1)**
- **injectMouse(MouseEvent.MOUSE_CLICKED, x, y, MouseEvent.BUTTON1, 1)**
- **injectMouse(int id, int x, int y, int button, int clickCount)**

### Relações

`Class239_Sub2`, `Class239_Sub8`, `Class272`, `Class314`, `Class314.aFloat3938`, `Class316`, `Class316.method2363`, `Class321`

---

## BorderLayout.java — Classe BorderLayout

- **Arquivo:** `android/app/src/main/java/voidawt/BorderLayout.java`
- **Declaração:** `class BorderLayout implements LayoutManager`
- **Package:** `voidawt`
- **Subsistema:** UI — AWT reimplementado (voidawt)
- **Tamanho:** 29 linhas

### Campos

| Nome | Tipo | Nota |
|---|---|---|
| `CENTER` | `String` |  |
| `NORTH` | `String` |  |
| `SOUTH` | `String` |  |
| `EAST` | `String` |  |
| `WEST` | `String` |  |

### Métodos

- **BorderLayout()**
- **addLayoutComponent(String name, Component comp)**
- **removeLayoutComponent(Component comp)**
- **preferredLayoutSize(Container parent)**
- **minimumLayoutSize(Container parent)**
- **layoutContainer(Container parent)**

### Relações

`voidawt`

---

## Canvas.java — Classe Canvas

- **Arquivo:** `android/app/src/main/java/voidawt/Canvas.java`
- **Declaração:** `class Canvas extends Component`
- **Package:** `voidawt`
- **Subsistema:** UI — AWT reimplementado (voidawt)
- **Tamanho:** 34 linhas

### Campos

| Nome | Tipo | Nota |
|---|---|---|
| `buffer` | `BufferedImage` |  |

### Métodos

- **getGraphics()**
- **ensureBuffer()**
- **createImage(int w, int h)**
- **ensureBuffer()**
- **drawBackbuffer(Image img, int x, int y, ImageObserver observer)**
- **ensureBuffer()**

### Relações

`voidawt`, `voidawt.image.BufferedImage`, `voidawt.image.ImageObserver`

---

## Color.java — Classe Color

- **Arquivo:** `android/app/src/main/java/voidawt/Color.java`
- **Declaração:** `class Color`
- **Package:** `voidawt`
- **Subsistema:** UI — AWT reimplementado (voidawt)
- **Tamanho:** 37 linhas

### Campos

| Nome | Tipo | Nota |
|---|---|---|
| `black` | `Color` |  |
| `white` | `Color` |  |
| `red` | `Color` |  |

### Métodos

- **Color(int r, int g, int b)**
- **Color(int r, int g, int b, int a)**
- **Color(int rgb)**
- **getRGB()**
- **getRed()**
- **getGreen()**
- **getBlue()**

### Relações

`voidawt`

---

## Component.java — Classe Component

- **Arquivo:** `android/app/src/main/java/voidawt/Component.java`
- **Declaração:** `class Component implements ImageObserver`
- **Package:** `voidawt`
- **Subsistema:** UI — AWT reimplementado (voidawt)
- **Tamanho:** 316 linhas

### Campos

| Nome | Tipo | Nota |
|---|---|---|
| `parent` | `Container` |  |
| `background` | `Color` |  |
| `font` | `Font` |  |
| `cursor` | `Cursor` |  |
| `mouseListeners` | `List<MouseListener>` |  |
| `mouseMotionListeners` | `List<MouseMotionListener>` |  |
| `mouseWheelListeners` | `List<MouseWheelListener>` |  |
| `keyListeners` | `List<KeyListener>` |  |
| `focusListeners` | `List<FocusListener>` |  |

### Métodos

- **setSize(int width, int height)**
- **setSize(Dimension d)**
- **setSize(d.width, d.height)**
- **getSize()**
- **getWidth()**
- **getHeight()**
- **setLocation(int x, int y)**
- **getX()**
- **getY()**
- **setVisible(boolean visible)**
- **setPreferredSize(Dimension d)**
- **setSize(d)**
- **getPreferredSize()**
- **getSize()**
- **isVisible()**
- **setBackground(Color color)**
- **getBackground()**
- **setFont(Font font)**
- **getFont()**
- **getFontMetrics(Font font)**
- **getParent()**
- **getToolkit()**
- **setCursor(Cursor cursor)**
- **getCursor()**
- **setIgnoreRepaint(boolean ignore)**
- **setBounds(int x, int y, int width, int height)**
- **setLocation(x, y)**
- **setSize(width, height)**
- **isShowing()**
- **repaint()**
- **getTreeLock()**
- **getPeer()**
- **prepareImage(Image image, ImageObserver observer)**
- **prepareImage(Image image, Component component)**
- **setFocusTraversalKeysEnabled(boolean enabled)**

### Relações

`voidawt`, `voidawt.event.FocusEvent`, `voidawt.event.FocusListener`, `voidawt.event.KeyEvent`, `voidawt.event.KeyListener`, `voidawt.event.MouseEvent`, `voidawt.event.MouseListener`, `voidawt.event.MouseMotionListener`

---

## Container.java — Classe Container

- **Arquivo:** `android/app/src/main/java/voidawt/Container.java`
- **Declaração:** `class Container extends Component`
- **Package:** `voidawt`
- **Subsistema:** UI — AWT reimplementado (voidawt)
- **Tamanho:** 55 linhas

### Campos

| Nome | Tipo | Nota |
|---|---|---|
| `children` | `List<Component>` |  |
| `layout` | `LayoutManager` |  |

### Métodos

- **setLayout(LayoutManager layout)**
- **getLayout()**
- **add(Component comp)**
- **add(Component comp, Object constraints)**
- **add(comp)**
- **remove(Component comp)**
- **getComponentCount()**
- **getComponents()**

### Relações

`voidawt`

---

## Cursor.java — Classe Cursor

- **Arquivo:** `android/app/src/main/java/voidawt/Cursor.java`
- **Declaração:** `class Cursor`
- **Package:** `voidawt`
- **Subsistema:** UI — AWT reimplementado (voidawt)
- **Tamanho:** 12 linhas

### Campos

_—_

### Métodos

- **Cursor()**
- **Cursor(int type)**

### Relações

`voidawt`

---

## Dialog.java — Classe Dialog

- **Arquivo:** `android/app/src/main/java/voidawt/Dialog.java`
- **Declaração:** `class Dialog extends Window`
- **Package:** `voidawt`
- **Subsistema:** UI — AWT reimplementado (voidawt)
- **Tamanho:** 6 linhas

### Campos

_—_

### Métodos

- **Dialog(Frame owner)**

### Relações

`voidawt`

---

## Dimension.java — Classe Dimension

- **Arquivo:** `android/app/src/main/java/voidawt/Dimension.java`
- **Declaração:** `class Dimension`
- **Package:** `voidawt`
- **Subsistema:** UI — AWT reimplementado (voidawt)
- **Tamanho:** 14 linhas

### Campos

_—_

### Métodos

- **Dimension()**
- **Dimension(int width, int height)**

### Relações

`voidawt`

---

## DisplayMode.java — Classe DisplayMode

- **Arquivo:** `android/app/src/main/java/voidawt/DisplayMode.java`
- **Declaração:** `class DisplayMode`
- **Package:** `voidawt`
- **Subsistema:** UI — AWT reimplementado (voidawt)
- **Tamanho:** 42 linhas

### Campos

| Nome | Tipo | Nota |
|---|---|---|
| `other` | `DisplayMode` |  |

### Métodos

- **DisplayMode(int width, int height, int bitDepth, int refreshRate)**
- **getWidth()**
- **getHeight()**
- **getBitDepth()**
- **getRefreshRate()**
- **equals(Object obj)**

### Relações

`voidawt`

---

## EventQueue.java — Classe EventQueue

- **Arquivo:** `android/app/src/main/java/voidawt/EventQueue.java`
- **Declaração:** `class EventQueue`
- **Package:** `voidawt`
- **Subsistema:** UI — AWT reimplementado (voidawt)
- **Tamanho:** 25 linhas

### Campos

| Nome | Tipo | Nota |
|---|---|---|
| `events` | `LinkedList<AWTEvent>` |  |

### Métodos

- **peekEvent()**
- **getNextEvent()**
- **wait()**
- **postEvent(AWTEvent event)**
- **notifyAll()**

### Relações

`voidawt`, `voidawt.event.AWTEvent`

---

## Font.java — Classe Font

- **Arquivo:** `android/app/src/main/java/voidawt/Font.java`
- **Declaração:** `class Font`
- **Package:** `voidawt`
- **Subsistema:** UI — AWT reimplementado (voidawt)
- **Tamanho:** 48 linhas

### Campos

| Nome | Tipo | Nota |
|---|---|---|
| `name` | `String` |  |
| `paint` | `Paint` |  |

### Métodos

- **Font(String name, int style, int size)**
- **getSize()**
- **getStyle()**

### Relações

`Class199`, `Class323`, `voidawt`

---

## FontMetrics.java — Classe FontMetrics

- **Arquivo:** `android/app/src/main/java/voidawt/FontMetrics.java`
- **Declaração:** `class FontMetrics`
- **Package:** `voidawt`
- **Subsistema:** UI — AWT reimplementado (voidawt)
- **Tamanho:** 44 linhas

### Campos

| Nome | Tipo | Nota |
|---|---|---|
| `paint` | `Paint` |  |

### Métodos

- **FontMetrics(Font font)**
- **charWidth(char c)**
- **stringWidth(String s)**
- **getAscent()**
- **getMaxAscent()**
- **getAscent()**
- **getDescent()**
- **getMaxDescent()**
- **getDescent()**
- **getHeight()**

### Relações

`Class323`, `voidawt`

---

## Frame.java — Classe Frame

- **Arquivo:** `android/app/src/main/java/voidawt/Frame.java`
- **Declaração:** `class Frame extends Window`
- **Package:** `voidawt`
- **Subsistema:** UI — AWT reimplementado (voidawt)
- **Tamanho:** 34 linhas

### Campos

| Nome | Tipo | Nota |
|---|---|---|
| `title` | `String` |  |

### Métodos

- **Frame()**
- **Frame(String title)**
- **setTitle(String title)**
- **getTitle()**
- **setResizable(boolean resizable)**
- **setUndecorated(boolean undecorated)**
- **setIconImages(java.util.List<? extends Image> icons)**

### Relações

`Class34.aFrame476`, `voidawt`

---

## Graphics.java — Classe Graphics

- **Arquivo:** `android/app/src/main/java/voidawt/Graphics.java`
- **Declaração:** `class Graphics`
- **Package:** `voidawt`
- **Subsistema:** UI — AWT reimplementado (voidawt)
- **Tamanho:** 182 linhas

### Campos

| Nome | Tipo | Nota |
|---|---|---|
| `target` | `BufferedImage` |  |
| `color` | `Color` |  |
| `font` | `Font` |  |
| `clip` | `Shape` |  |
| `px` | `int[]` |  |
| `paint` | `Paint` |  |
| `fm` | `Paint.FontMetrics` |  |
| `bitmap` | `Bitmap` |  |
| `canvas` | `android.graphics.Canvas` |  |
| `src` | `int[]` |  |
| `dst` | `int[]` |  |
| `src` | `int[]` |  |

### Métodos

- **Graphics(BufferedImage target)**
- **Graphics(BufferedImage target, boolean presentOnDraw)**
- **getColor()**
- **setColor(Color color)**
- **setFont(Font font)**
- **getFont()**
- **getClip()**
- **getClipBounds()**
- **setClip(Shape clip)**
- **setClip(int x, int y, int width, int height)**
- **fillRect(int x, int y, int w, int h)**
- **drawRect(int x, int y, int w, int h)**
- **fillRect(x, y, w, 1)**
- **fillRect(x, y + h, w, 1)**
- **fillRect(x, y, 1, h)**
- **fillRect(x + w, y, 1, h)**
- **drawString(String str, int x, int y)**
- **drawImage(Image img, int x, int y, ImageObserver observer)**
- **drawImage(Image img, int x, int y, int width, int height, ImageObserver observer)**
- **drawImage(img, x, y, observer)**
- **clipRect(int x, int y, int width, int height)**
- **setClip(x, y, width, height)**
- **clearRect(int x, int y, int w, int h)**
- **fillRect(x, y, w, h)**

### Relações

`Class199`, `Class323`, `voidawt`, `voidawt.image.BufferedImage`, `voidawt.image.ImageObserver`

---

## GraphicsConfiguration.java — Classe GraphicsConfiguration

- **Arquivo:** `android/app/src/main/java/voidawt/GraphicsConfiguration.java`
- **Declaração:** `class GraphicsConfiguration`
- **Package:** `voidawt`
- **Subsistema:** UI — AWT reimplementado (voidawt)
- **Tamanho:** 4 linhas

### Campos

_—_

### Métodos

_—_

### Relações

`voidawt`

---

## GraphicsDevice.java — Classe GraphicsDevice

- **Arquivo:** `android/app/src/main/java/voidawt/GraphicsDevice.java`
- **Declaração:** `class GraphicsDevice`
- **Package:** `voidawt`
- **Subsistema:** UI — AWT reimplementado (voidawt)
- **Tamanho:** 52 linhas

### Campos

| Nome | Tipo | Nota |
|---|---|---|
| `mode` | `DisplayMode` |  |
| `current` | `DisplayMode` |  |
| `sizes` | `int[][]` |  |
| `modes` | `DisplayMode[]` |  |

### Métodos

- **isFullScreenSupported()**
- **getDisplayMode()**
- **setDisplayMode(DisplayMode dm)**
- **getDisplayModes()**
- **setFullScreenWindow(Window w)**
- **getFullScreenWindow()**

### Relações

`Class7`, `voidawt`

---

## GraphicsEnvironment.java — Classe GraphicsEnvironment

- **Arquivo:** `android/app/src/main/java/voidawt/GraphicsEnvironment.java`
- **Declaração:** `class GraphicsEnvironment`
- **Package:** `voidawt`
- **Subsistema:** UI — AWT reimplementado (voidawt)
- **Tamanho:** 27 linhas

### Campos

| Nome | Tipo | Nota |
|---|---|---|
| `INSTANCE` | `GraphicsEnvironment` |  |
| `device` | `GraphicsDevice` |  |

### Métodos

- **getLocalGraphicsEnvironment()**
- **getDefaultScreenDevice()**
- **getScreenDevices()**
- **isHeadlessInstance()**
- **isHeadless()**

### Relações

`voidawt`

---

## Image.java — Classe Image

- **Arquivo:** `android/app/src/main/java/voidawt/Image.java`
- **Declaração:** `class Image`
- **Package:** `voidawt`
- **Subsistema:** UI — AWT reimplementado (voidawt)
- **Tamanho:** 23 linhas

### Campos

_—_

### Métodos

- **getWidth(ImageObserver observer)**
- **getHeight(ImageObserver observer)**
- **getGraphics()**
- **flush()**
- **getSource()**
- **peekArgb()**

### Relações

`voidawt`, `voidawt.image.ImageObserver`, `voidawt.image.ImageProducer`

---

## Insets.java — Classe Insets

- **Arquivo:** `android/app/src/main/java/voidawt/Insets.java`
- **Declaração:** `class Insets`
- **Package:** `voidawt`
- **Subsistema:** UI — AWT reimplementado (voidawt)
- **Tamanho:** 15 linhas

### Campos

_—_

### Métodos

- **Insets(int top, int left, int bottom, int right)**

### Relações

`voidawt`

---

## LayoutManager.java — Classe LayoutManager

- **Arquivo:** `android/app/src/main/java/voidawt/LayoutManager.java`
- **Declaração:** `interface LayoutManager`
- **Package:** `voidawt`
- **Subsistema:** UI — AWT reimplementado (voidawt)
- **Tamanho:** 13 linhas

### Campos

_—_

### Métodos

- **addLayoutComponent(String name, Component comp)**
- **removeLayoutComponent(Component comp)**
- **preferredLayoutSize(Container parent)**
- **minimumLayoutSize(Container parent)**
- **layoutContainer(Container parent)**

### Relações

`voidawt`

---

## MediaTracker.java — Classe MediaTracker

- **Arquivo:** `android/app/src/main/java/voidawt/MediaTracker.java`
- **Declaração:** `class MediaTracker`
- **Package:** `voidawt`
- **Subsistema:** UI — AWT reimplementado (voidawt)
- **Tamanho:** 18 linhas

### Campos

_—_

### Métodos

- **MediaTracker(Component component)**
- **addImage(Image image, int id)**
- **waitForAll()**
- **isErrorAny()**

### Relações

`voidawt`

---

## Panel.java — Classe Panel

- **Arquivo:** `android/app/src/main/java/voidawt/Panel.java`
- **Declaração:** `class Panel extends Container`
- **Package:** `voidawt`
- **Subsistema:** UI — AWT reimplementado (voidawt)
- **Tamanho:** 4 linhas

### Campos

_—_

### Métodos

_—_

### Relações

`voidawt`

---

## Point.java — Classe Point

- **Arquivo:** `android/app/src/main/java/voidawt/Point.java`
- **Declaração:** `class Point`
- **Package:** `voidawt`
- **Subsistema:** UI — AWT reimplementado (voidawt)
- **Tamanho:** 14 linhas

### Campos

_—_

### Métodos

- **Point()**
- **Point(int x, int y)**

### Relações

`voidawt`

---

## Rectangle.java — Classe Rectangle

- **Arquivo:** `android/app/src/main/java/voidawt/Rectangle.java`
- **Declaração:** `class Rectangle implements Shape`
- **Package:** `voidawt`
- **Subsistema:** UI — AWT reimplementado (voidawt)
- **Tamanho:** 34 linhas

### Campos

_—_

### Métodos

- **Rectangle()**
- **Rectangle(int x, int y, int width, int height)**
- **Rectangle(int width, int height)**
- **getBounds()**
- **setBounds(int x, int y, int width, int height)**

### Relações

`voidawt`

---

## Robot.java — Classe Robot

- **Arquivo:** `android/app/src/main/java/voidawt/Robot.java`
- **Declaração:** `class Robot`
- **Package:** `voidawt`
- **Subsistema:** UI — AWT reimplementado (voidawt)
- **Tamanho:** 9 linhas

### Campos

_—_

### Métodos

- **Robot()**
- **mouseMove(int x, int y)**

### Relações

`voidawt`

---

## Shape.java — Classe Shape

- **Arquivo:** `android/app/src/main/java/voidawt/Shape.java`
- **Declaração:** `interface Shape`
- **Package:** `voidawt`
- **Subsistema:** UI — AWT reimplementado (voidawt)
- **Tamanho:** 5 linhas

### Campos

_—_

### Métodos

- **getBounds()**

### Relações

`voidawt`

---

## Toolkit.java — Classe Toolkit

- **Arquivo:** `android/app/src/main/java/voidawt/Toolkit.java`
- **Declaração:** `class Toolkit`
- **Package:** `voidawt`
- **Subsistema:** UI — AWT reimplementado (voidawt)
- **Tamanho:** 125 linhas

### Campos

| Nome | Tipo | Nota |
|---|---|---|
| `INSTANCE` | `Toolkit` |  |
| `clipboard` | `Clipboard` |  |
| `eventQueue` | `EventQueue` |  |
| `opts` | `BitmapFactory.Options` |  |
| `bitmap` | `Bitmap` |  |
| `converted` | `Bitmap` |  |
| `image` | `BufferedImage` |  |
| `pixels` | `int[]` |  |
| `image` | `ProducerImage` |  |
| `image` | `BufferedImage` |  |
| `argb` | `int[]` |  |

### Métodos

- **getDefaultToolkit()**
- **getSystemEventQueue()**
- **getSystemClipboard()**
- **getScreenSize()**
- **sync()**
- **createImage(byte[] data)**
- **createImage(ImageProducer producer)**
- **createCustomCursor(Image cursor, Point hotSpot, String name)**
- **beep()**
- **setDimensions(int width, int height)**
- **setProperties(java.util.Hashtable<?, ?> props)**
- **setColorModel(voidawt.image.ColorModel model)**
- **setHints(int hintflags)**
- **setPixels(int x, int y, int w, int h, voidawt.image.ColorModel model, byte[] pixels, int off, int scansize)**
- **setPixels(int x, int y, int w, int h, voidawt.image.ColorModel model, int[] pixels, int off, int scansize)**
- **setDimensions(x + w, y + h)**
- **imageComplete(int status)**

### Relações

`voidawt`, `voidawt.datatransfer.Clipboard`, `voidawt.image.BufferedImage`, `voidawt.image.ColorModel`, `voidawt.image.ImageConsumer`, `voidawt.image.ImageProducer`

---

## Window.java — Classe Window

- **Arquivo:** `android/app/src/main/java/voidawt/Window.java`
- **Declaração:** `class Window extends Container`
- **Package:** `voidawt`
- **Subsistema:** UI — AWT reimplementado (voidawt)
- **Tamanho:** 23 linhas

### Campos

_—_

### Métodos

- **pack()**
- **toFront()**
- **dispose()**
- **addWindowListener(voidawt.event.WindowListener l)**
- **removeWindowListener(voidawt.event.WindowListener l)**
- **getInsets()**

### Relações

`voidawt`, `voidawt.event.WindowListener`

---

