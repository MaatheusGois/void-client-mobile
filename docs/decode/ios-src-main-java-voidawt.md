# Decode — `ios/src/main/java/voidawt` (5 classes)

> **Subsistema:** UI — AWT reimplementado (voidawt)

Total: 5

---

## AwtHost.java — Classe AwtHost

- **Arquivo:** `ios/src/main/java/voidawt/AwtHost.java`
- **Declaração:** `class AwtHost`
- **Package:** `voidawt`
- **Subsistema:** UI — AWT reimplementado (voidawt)
- **Tamanho:** 329 linhas

### Campos

| Nome | Tipo | Nota |
|---|---|---|
| `presenter` | `Presenter` |  |
| `root` | `Component` |  |
| `gameCanvas` | `Canvas` |  |
| `locked` | `int[]` |  |
| `logical` | `int[]` |  |
| `r` | `Component` |  |
| `c` | `Canvas` |  |
| `frame` | `Object` |  |
| `f` | `java.lang.reflect.Field` |  |
| `f` | `java.lang.reflect.Field` |  |
| `p` | `Presenter` |  |
| `copy` | `int[]` |  |

### Métodos

- **present(int[] argb, int width, int height)**
- **setDisplaySize(int width, int height)**
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
- **injectWheel(int x, int y, int rotation)**
- **injectCameraOrbit(float dx, float dy)**
- **injectKey(int id, int keyCode, char keyChar)**

### Relações

`Class239_Sub2`, `Class239_Sub8`, `Class272`, `Class314`, `Class316`, `Class316.method2363`, `Class321`, `Class335`

---

## Font.java — Classe Font

- **Arquivo:** `ios/src/main/java/voidawt/Font.java`
- **Declaração:** `class Font`
- **Package:** `voidawt`
- **Subsistema:** UI — AWT reimplementado (voidawt)
- **Tamanho:** 89 linhas

### Campos

| Nome | Tipo | Nota |
|---|---|---|
| `name` | `String` |  |
| `uiFont` | `UIFont` |  |
| `resolved` | `UIFont` |  |
| `attrs` | `NSAttributedStringAttributes` |  |

### Métodos

- **Font(String name, int style, int size)**
- **getSize()**
- **getStyle()**
- **uiFont()**
- **attributed(String text, UIColor color)**

### Relações

`Class199`, `Class323`, `voidawt`

---

## FontMetrics.java — Classe FontMetrics

- **Arquivo:** `ios/src/main/java/voidawt/FontMetrics.java`
- **Declaração:** `class FontMetrics`
- **Package:** `voidawt`
- **Subsistema:** UI — AWT reimplementado (voidawt)
- **Tamanho:** 57 linhas

### Campos

| Nome | Tipo | Nota |
|---|---|---|
| `font` | `Font` |  |
| `uiFont` | `UIFont` |  |
| `line` | `CTLine` |  |

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

## Graphics.java — Classe Graphics

- **Arquivo:** `ios/src/main/java/voidawt/Graphics.java`
- **Declaração:** `class Graphics`
- **Package:** `voidawt`
- **Subsistema:** UI — AWT reimplementado (voidawt)
- **Tamanho:** 225 linhas

### Campos

| Nome | Tipo | Nota |
|---|---|---|
| `target` | `BufferedImage` |  |
| `color` | `Color` |  |
| `font` | `Font` |  |
| `clip` | `Shape` |  |
| `px` | `int[]` |  |
| `uiFont` | `UIFont` |  |
| `uiColor` | `UIColor` |  |
| `attributed` | `NSAttributedString` |  |
| `line` | `CTLine` |  |
| `rgba` | `byte[]` |  |
| `space` | `CGColorSpace` |  |
| `ctx` | `CGBitmapContext` |  |

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

## Toolkit.java — Classe Toolkit

- **Arquivo:** `ios/src/main/java/voidawt/Toolkit.java`
- **Declaração:** `class Toolkit`
- **Package:** `voidawt`
- **Subsistema:** UI — AWT reimplementado (voidawt)
- **Tamanho:** 113 linhas

### Campos

| Nome | Tipo | Nota |
|---|---|---|
| `INSTANCE` | `Toolkit` |  |
| `clipboard` | `Clipboard` |  |
| `eventQueue` | `EventQueue` |  |
| `ui` | `UIImage` |  |
| `cg` | `CGImage` |  |
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

