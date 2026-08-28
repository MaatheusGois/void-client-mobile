# Decode — `ios/src/main/java/voidsound/sampled` (1 classes)

> **Subsistema:** Áudio — voidsound/sampled

Total: 1

---

## PcmSourceDataLine.java — Classe PcmSourceDataLine

- **Arquivo:** `ios/src/main/java/voidsound/sampled/PcmSourceDataLine.java`
- **Declaração:** `class PcmSourceDataLine implements SourceDataLine`
- **Package:** `voidsound.sampled`
- **Subsistema:** Áudio — voidsound/sampled
- **Tamanho:** 315 linhas

### Campos

| Nome | Tipo | Nota |
|---|---|---|
| `format` | `AudioFormat` |  |
| `callback` | `AudioQueue.OutputCallback` |  |
| `ring` | `ByteRing` |  |
| `queue` | `AudioQueue` |  |
| `dst` | `ByteBuffer` |  |
| `session` | `AVAudioSession` |  |
| `flags` | `AudioFormatFlags` |  |
| `asbd` | `AudioStreamBasicDescription` |  |
| `priming` | `byte[]` |  |
| `buf` | `AudioQueueBuffer` |  |
| `q` | `AudioQueue` |  |
| `q` | `AudioQueue` |  |

### Métodos

- **onOutput(AudioQueue q, long bufferPtr)**
- **NativeBuf(long handle)**
- **putPcm(byte[] src)**
- **setAudioDataByteSize(n)**
- **PcmSourceDataLine(AudioFormat format, int bufferBytes)**
- **open()**
- **runOnMain(new Runnable()**
- **run()**
- **openOnMain()**
- **close()**
- **openOnMain()**
- **fill(priming)**
- **close()**
- **runOnMain(new Runnable()**
- **run()**
- **start()**
- **flush()**
- **runOnMain(new Runnable()**
- **run()**
- **available()**
- **write(byte[] b, int off, int len)**
- **onOutput(AudioQueue q, long bufferPtr)**
- **fill(data)**
- **fill(byte[] dst)**
- **runOnMain(final Runnable task)**
- **run()**
- **peak(byte[] b, int off, int len)**

### Relações

`Class279_Sub1`, `voidsound.sampled`

---

