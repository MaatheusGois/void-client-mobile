# Decode — `android/app/src/main/java/jagtheora/theora` (6 classes)

> **Subsistema:** Mídia — Theora/Vorbis/Ogg

Total: 6

---

## DecoderContext.java — Classe DecoderContext

- **Arquivo:** `android/app/src/main/java/jagtheora/theora/DecoderContext.java`
- **Declaração:** `class DecoderContext extends SimplePeer`
- **Package:** `jagtheora.theora`
- **Subsistema:** Mídia — Theora/Vorbis/Ogg
- **Tamanho:** 32 linhas

### Campos

_—_

### Métodos

- **DecoderContext(TheoraInfo arg0, SetupInfo arg1)**
- **granuleTime(GranulePos arg0)**
- **decodePacketIn(OggPacket arg0, GranulePos arg1)**
- **getMaxPostProcessingLevel()**
- **granuleFrame(GranulePos arg0)**
- **setPostProcessingLevel(int arg0)**
- **setGranulePosition(long arg0)**
- **decodeFrame(Frame arg0)**
- **init(TheoraInfo arg0, SetupInfo arg1)**
- **clear()**

### Relações

—

---

## Frame.java — Classe Frame

- **Arquivo:** `android/app/src/main/java/jagtheora/theora/Frame.java`
- **Declaração:** `class Frame extends SimplePeer`
- **Package:** `jagtheora.theora`
- **Subsistema:** Mídia — Theora/Vorbis/Ogg
- **Tamanho:** 22 linhas

### Campos

| Nome | Tipo | Nota |
|---|---|---|
| `pixels` | `int[]` |  |

### Métodos

- **init()**
- **Frame(int arg0, int arg1)**
- **clear()**

### Relações

—

---

## GranulePos.java — Classe GranulePos

- **Arquivo:** `android/app/src/main/java/jagtheora/theora/GranulePos.java`
- **Declaração:** `class GranulePos extends SimplePeer`
- **Package:** `jagtheora.theora`
- **Subsistema:** Mídia — Theora/Vorbis/Ogg
- **Tamanho:** 12 linhas

### Campos

_—_

### Métodos

- **init()**
- **clear()**

### Relações

—

---

## SetupInfo.java — Classe SetupInfo

- **Arquivo:** `android/app/src/main/java/jagtheora/theora/SetupInfo.java`
- **Declaração:** `class SetupInfo extends SimplePeer`
- **Package:** `jagtheora.theora`
- **Subsistema:** Mídia — Theora/Vorbis/Ogg
- **Tamanho:** 11 linhas

### Campos

_—_

### Métodos

- **clear()**
- **decodeHeader(TheoraInfo arg0, TheoraComment arg1, OggPacket arg2)**

### Relações

—

---

## TheoraComment.java — Classe TheoraComment

- **Arquivo:** `android/app/src/main/java/jagtheora/theora/TheoraComment.java`
- **Declaração:** `class TheoraComment extends SimplePeer`
- **Package:** `jagtheora.theora`
- **Subsistema:** Mídia — Theora/Vorbis/Ogg
- **Tamanho:** 17 linhas

### Campos

_—_

### Métodos

- **TheoraComment()**
- **init()**
- **clear()**

### Relações

—

---

## TheoraInfo.java — Classe TheoraInfo

- **Arquivo:** `android/app/src/main/java/jagtheora/theora/TheoraInfo.java`
- **Declaração:** `class TheoraInfo extends SimplePeer`
- **Package:** `jagtheora.theora`
- **Subsistema:** Mídia — Theora/Vorbis/Ogg
- **Tamanho:** 53 linhas

### Campos

_—_

### Métodos

- **initFields()**
- **initFields()**
- **TheoraInfo()**
- **init()**
- **clear()**

### Relações

—

---

