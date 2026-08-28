# Decode — `android/app/src/main/java/voidawt/image` (12 classes)

> **Subsistema:** UI — AWT reimplementado (voidawt)

Total: 12

---

## BufferedImage.java — Classe BufferedImage

- **Arquivo:** `android/app/src/main/java/voidawt/image/BufferedImage.java`
- **Declaração:** `class BufferedImage extends Image`
- **Package:** `voidawt.image`
- **Subsistema:** UI — AWT reimplementado (voidawt)
- **Tamanho:** 70 linhas

### Campos

| Nome | Tipo | Nota |
|---|---|---|
| `data` | `int[]` |  |
| `graphics` | `Graphics` |  |
| `buffer` | `DataBuffer` |  |

### Métodos

- **BufferedImage(int width, int height, int type)**
- **BufferedImage(ColorModel cm, WritableRaster raster, boolean isRasterPremultiplied, Hashtable<?, ?> properties)**
- **getWidth()**
- **getHeight()**
- **getWidth(ImageObserver observer)**
- **getHeight(ImageObserver observer)**
- **getGraphics()**
- **setRGB(int startX, int startY, int w, int h, int[] rgbArray, int offset, int scansize)**
- **peekArgb()**

### Relações

`voidawt.Graphics`, `voidawt.Image`, `voidawt.image`

---

## ColorModel.java — Classe ColorModel

- **Arquivo:** `android/app/src/main/java/voidawt/image/ColorModel.java`
- **Declaração:** `class ColorModel`
- **Package:** `voidawt.image`
- **Subsistema:** UI — AWT reimplementado (voidawt)
- **Tamanho:** 15 linhas

### Campos

_—_

### Métodos

- **ColorModel(int bits)**
- **createCompatibleSampleModel(int w, int h)**
- **getRGB(int pixel)**

### Relações

`voidawt.image`

---

## DataBuffer.java — Classe DataBuffer

- **Arquivo:** `android/app/src/main/java/voidawt/image/DataBuffer.java`
- **Declaração:** `class DataBuffer`
- **Package:** `voidawt.image`
- **Subsistema:** UI — AWT reimplementado (voidawt)
- **Tamanho:** 10 linhas

### Campos

_—_

### Métodos

- **getSize()**

### Relações

`voidawt.image`

---

## DataBufferInt.java — Classe DataBufferInt

- **Arquivo:** `android/app/src/main/java/voidawt/image/DataBufferInt.java`
- **Declaração:** `class DataBufferInt extends DataBuffer`
- **Package:** `voidawt.image`
- **Subsistema:** UI — AWT reimplementado (voidawt)
- **Tamanho:** 19 linhas

### Campos

| Nome | Tipo | Nota |
|---|---|---|
| `data` | `int[]` |  |

### Métodos

- **DataBufferInt(int[] data, int size)**
- **DataBufferInt(int size)**
- **getData()**

### Relações

`voidawt.image`

---

## DirectColorModel.java — Classe DirectColorModel

- **Arquivo:** `android/app/src/main/java/voidawt/image/DirectColorModel.java`
- **Declaração:** `class DirectColorModel extends ColorModel`
- **Package:** `voidawt.image`
- **Subsistema:** UI — AWT reimplementado (voidawt)
- **Tamanho:** 64 linhas

### Campos

_—_

### Métodos

- **DirectColorModel(int bits, int rmask, int gmask, int bmask)**
- **DirectColorModel(int bits, int rmask, int gmask, int bmask, int amask)**
- **shift(int mask)**
- **scale(int value, int mask, int shift)**
- **getRGB(int pixel)**
- **createCompatibleSampleModel(int w, int h)**

### Relações

`voidawt.image`

---

## ImageConsumer.java — Classe ImageConsumer

- **Arquivo:** `android/app/src/main/java/voidawt/image/ImageConsumer.java`
- **Declaração:** `interface ImageConsumer`
- **Package:** `voidawt.image`
- **Subsistema:** UI — AWT reimplementado (voidawt)
- **Tamanho:** 27 linhas

### Campos

_—_

### Métodos

- **setDimensions(int width, int height)**
- **setProperties(java.util.Hashtable<?, ?> props)**
- **setColorModel(ColorModel model)**
- **setHints(int hintflags)**
- **setPixels(int x, int y, int w, int h, ColorModel model, byte[] pixels, int off, int scansize)**
- **setPixels(int x, int y, int w, int h, ColorModel model, int[] pixels, int off, int scansize)**
- **imageComplete(int status)**

### Relações

`voidawt.image`

---

## ImageObserver.java — Classe ImageObserver

- **Arquivo:** `android/app/src/main/java/voidawt/image/ImageObserver.java`
- **Declaração:** `interface ImageObserver`
- **Package:** `voidawt.image`
- **Subsistema:** UI — AWT reimplementado (voidawt)
- **Tamanho:** 14 linhas

### Campos

_—_

### Métodos

- **imageUpdate(voidawt.Image img, int infoflags, int x, int y, int width, int height)**

### Relações

`voidawt.Image`, `voidawt.image`

---

## ImageProducer.java — Classe ImageProducer

- **Arquivo:** `android/app/src/main/java/voidawt/image/ImageProducer.java`
- **Declaração:** `interface ImageProducer`
- **Package:** `voidawt.image`
- **Subsistema:** UI — AWT reimplementado (voidawt)
- **Tamanho:** 13 linhas

### Campos

_—_

### Métodos

- **addConsumer(ImageConsumer ic)**
- **isConsumer(ImageConsumer ic)**
- **removeConsumer(ImageConsumer ic)**
- **startProduction(ImageConsumer ic)**
- **requestTopDownLeftRightResend(ImageConsumer ic)**

### Relações

`voidawt.image`

---

## PixelGrabber.java — Classe PixelGrabber

- **Arquivo:** `android/app/src/main/java/voidawt/image/PixelGrabber.java`
- **Declaração:** `class PixelGrabber`
- **Package:** `voidawt.image`
- **Subsistema:** UI — AWT reimplementado (voidawt)
- **Tamanho:** 48 linhas

### Campos

| Nome | Tipo | Nota |
|---|---|---|
| `image` | `Image` |  |
| `pix` | `int[]` |  |
| `src` | `int[]` |  |

### Métodos

- **PixelGrabber(Image image, int x, int y, int w, int h, int[] pix, int off, int scansize)**
- **grabPixels()**

### Relações

`voidawt.Image`, `voidawt.image`

---

## Raster.java — Classe Raster

- **Arquivo:** `android/app/src/main/java/voidawt/image/Raster.java`
- **Declaração:** `class Raster`
- **Package:** `voidawt.image`
- **Subsistema:** UI — AWT reimplementado (voidawt)
- **Tamanho:** 31 linhas

### Campos

| Nome | Tipo | Nota |
|---|---|---|
| `sampleModel` | `SampleModel` |  |
| `dataBuffer` | `DataBuffer` |  |
| `raster` | `WritableRaster` |  |

### Métodos

- **createWritableRaster(SampleModel sm, DataBuffer db, Point location)**
- **getDataBuffer()**
- **getWidth()**
- **getHeight()**

### Relações

`voidawt.Point`, `voidawt.image`

---

## SampleModel.java — Classe SampleModel

- **Arquivo:** `android/app/src/main/java/voidawt/image/SampleModel.java`
- **Declaração:** `class SampleModel`
- **Package:** `voidawt.image`
- **Subsistema:** UI — AWT reimplementado (voidawt)
- **Tamanho:** 11 linhas

### Campos

_—_

### Métodos

- **SampleModel(int width, int height)**

### Relações

`voidawt.image`

---

## WritableRaster.java — Classe WritableRaster

- **Arquivo:** `android/app/src/main/java/voidawt/image/WritableRaster.java`
- **Declaração:** `class WritableRaster extends Raster`
- **Package:** `voidawt.image`
- **Subsistema:** UI — AWT reimplementado (voidawt)
- **Tamanho:** 4 linhas

### Campos

_—_

### Métodos

_—_

### Relações

`voidawt.image`

---

