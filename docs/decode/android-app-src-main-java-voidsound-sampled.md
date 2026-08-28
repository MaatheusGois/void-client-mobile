# Decode — `android/app/src/main/java/voidsound/sampled` (9 classes)

> **Subsistema:** Áudio — voidsound/sampled

Total: 9

---

## AudioFormat.java — Classe AudioFormat

- **Arquivo:** `android/app/src/main/java/voidsound/sampled/AudioFormat.java`
- **Declaração:** `class AudioFormat`
- **Package:** `voidsound.sampled`
- **Subsistema:** Áudio — voidsound/sampled
- **Tamanho:** 50 linhas

### Campos

_—_

### Métodos

- **AudioFormat(float sampleRate, int sampleSizeInBits, int channels, boolean signed, boolean bigEndian)**
- **getSampleRate()**
- **getSampleSizeInBits()**
- **getChannels()**
- **isSigned()**
- **isBigEndian()**
- **getFrameSize()**

### Relações

`Class22.anInt339`, `Class282.aBoolean3652`, `voidsound.sampled`

---

## AudioSystem.java — Classe AudioSystem

- **Arquivo:** `android/app/src/main/java/voidsound/sampled/AudioSystem.java`
- **Declaração:** `class AudioSystem`
- **Package:** `voidsound.sampled`
- **Subsistema:** Áudio — voidsound/sampled
- **Tamanho:** 37 linhas

### Campos

| Nome | Tipo | Nota |
|---|---|---|
| `MIXER` | `Mixer.Info` |  |
| `format` | `AudioFormat` |  |
| `di` | `DataLine.Info` |  |

### Métodos

- **getMixerInfo()**
- **getLine(Line.Info info)**

### Relações

`Class279_Sub1`, `voidsound.sampled`

---

## ByteRing.java — Classe ByteRing

- **Arquivo:** `android/app/src/main/java/voidsound/sampled/ByteRing.java`
- **Declaração:** `class ByteRing`
- **Package:** `voidsound.sampled`
- **Subsistema:** Áudio — voidsound/sampled
- **Tamanho:** 83 linhas

### Campos

| Nome | Tipo | Nota |
|---|---|---|
| `buf` | `byte[]` |  |

### Métodos

- **ByteRing(int capacity)**
- **capacity()**
- **free()**
- **used()**
- **clear()**
- **notifyAll()**
- **write(byte[] src, int off, int len)**
- **wait(5L)**
- **notifyAll()**
- **read(byte[] dst, int off, int len)**
- **notifyAll()**

### Relações

`Class279_Sub1.method2081`, `voidsound.sampled`

---

## DataLine.java — Classe DataLine

- **Arquivo:** `android/app/src/main/java/voidsound/sampled/DataLine.java`
- **Declaração:** `interface DataLine extends Line`
- **Package:** `voidsound.sampled`
- **Subsistema:** Áudio — voidsound/sampled
- **Tamanho:** 39 linhas

### Campos

| Nome | Tipo | Nota |
|---|---|---|
| `format` | `AudioFormat` |  |

### Métodos

- **Info(Class<?> lineClass, AudioFormat format, int bufferSize)**
- **getFormat()**
- **getBufferSize()**
- **available()**
- **write(byte[] b, int off, int len)**

### Relações

`Class279_Sub1`, `Class279_Sub1.method2081`, `Class279_Sub1.method2082`, `voidsound.sampled`

---

## Line.java — Classe Line

- **Arquivo:** `android/app/src/main/java/voidsound/sampled/Line.java`
- **Declaração:** `interface Line`
- **Package:** `voidsound.sampled`
- **Subsistema:** Áudio — voidsound/sampled
- **Tamanho:** 20 linhas

### Campos

_—_

### Métodos

- **Info(Class<?> lineClass, AudioFormat format, int bufferSize)**
- **Info(Class<?> lineClass)**
- **open()**
- **close()**
- **start()**
- **flush()**

### Relações

`voidsound.sampled`

---

## LineUnavailableException.java — Classe LineUnavailableException

- **Arquivo:** `android/app/src/main/java/voidsound/sampled/LineUnavailableException.java`
- **Declaração:** `class LineUnavailableException extends Exception`
- **Package:** `voidsound.sampled`
- **Subsistema:** Áudio — voidsound/sampled
- **Tamanho:** 11 linhas

### Campos

_—_

### Métodos

- **LineUnavailableException()**
- **LineUnavailableException(String message)**

### Relações

`voidsound.sampled`

---

## Mixer.java — Classe Mixer

- **Arquivo:** `android/app/src/main/java/voidsound/sampled/Mixer.java`
- **Declaração:** `class Mixer`
- **Package:** `voidsound.sampled`
- **Subsistema:** Áudio — voidsound/sampled
- **Tamanho:** 13 linhas

### Campos

_—_

### Métodos

- **getName()**

### Relações

`Class279_Sub1`, `voidsound.sampled`

---

## PcmSourceDataLine.java — Classe PcmSourceDataLine

- **Arquivo:** `android/app/src/main/java/voidsound/sampled/PcmSourceDataLine.java`
- **Declaração:** `class PcmSourceDataLine implements SourceDataLine`
- **Package:** `voidsound.sampled`
- **Subsistema:** Áudio — voidsound/sampled
- **Tamanho:** 236 linhas

### Campos

| Nome | Tipo | Nota |
|---|---|---|
| `TAG` | `String` |  |
| `format` | `voidsound.sampled.AudioFormat` |  |
| `ring` | `ByteRing` |  |
| `track` | `AudioTrack` |  |
| `pump` | `Thread` |  |
| `r` | `ByteRing` |  |
| `r` | `ByteRing` |  |
| `src` | `byte[]` |  |
| `stretched` | `byte[]` |  |
| `r` | `ByteRing` |  |
| `t` | `AudioTrack` |  |
| `out` | `byte[]` |  |

### Métodos

- **PcmSourceDataLine(voidsound.sampled.AudioFormat format, int bufferBytes)**
- **open()**
- **run()**
- **pumpLoop()**
- **close()**
- **start()**
- **flush()**
- **available()**
- **write(byte[] b, int off, int len)**
- **pumpLoop()**
- **stretch2x(byte[] src, int len, byte[] dst)**
- **peak(byte[] b, int off, int len)**

### Relações

`Class279_Sub1`, `voidsound.sampled`, `voidsound.sampled.AudioFormat`

---

## SourceDataLine.java — Classe SourceDataLine

- **Arquivo:** `android/app/src/main/java/voidsound/sampled/SourceDataLine.java`
- **Declaração:** `interface SourceDataLine extends DataLine`
- **Package:** `voidsound.sampled`
- **Subsistema:** Áudio — voidsound/sampled
- **Tamanho:** 9 linhas

### Campos

_—_

### Métodos

_—_

### Relações

`Class279_Sub1`, `voidsound.sampled`

---

