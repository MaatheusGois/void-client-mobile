# Decode — `android/app/src/main/java/jagtheora/vorbis` (4 classes)

> **Subsistema:** Mídia — Theora/Vorbis/Ogg

Total: 4

---

## DSPState.java — Classe DSPState

- **Arquivo:** `android/app/src/main/java/jagtheora/vorbis/DSPState.java`
- **Declaração:** `class DSPState extends SimplePeer`
- **Package:** `jagtheora.vorbis`
- **Subsistema:** Mídia — Theora/Vorbis/Ogg
- **Tamanho:** 25 linhas

### Campos

_—_

### Métodos

- **DSPState(VorbisInfo arg0)**
- **pcmOut(int arg0)**
- **init(VorbisInfo arg0)**
- **granuleTime()**
- **clear()**
- **blockIn(VorbisBlock arg0)**
- **read(int arg0)**

### Relações

—

---

## VorbisBlock.java — Classe VorbisBlock

- **Arquivo:** `android/app/src/main/java/jagtheora/vorbis/VorbisBlock.java`
- **Declaração:** `class VorbisBlock extends SimplePeer`
- **Package:** `jagtheora.vorbis`
- **Subsistema:** Mídia — Theora/Vorbis/Ogg
- **Tamanho:** 20 linhas

### Campos

_—_

### Métodos

- **VorbisBlock(DSPState arg0)**
- **synthesis(OggPacket arg0)**
- **init(DSPState arg0)**
- **clear()**

### Relações

—

---

## VorbisComment.java — Classe VorbisComment

- **Arquivo:** `android/app/src/main/java/jagtheora/vorbis/VorbisComment.java`
- **Declaração:** `class VorbisComment extends SimplePeer`
- **Package:** `jagtheora.vorbis`
- **Subsistema:** Mídia — Theora/Vorbis/Ogg
- **Tamanho:** 17 linhas

### Campos

_—_

### Métodos

- **VorbisComment()**
- **init()**
- **clear()**

### Relações

—

---

## VorbisInfo.java — Classe VorbisInfo

- **Arquivo:** `android/app/src/main/java/jagtheora/vorbis/VorbisInfo.java`
- **Declaração:** `class VorbisInfo extends SimplePeer`
- **Package:** `jagtheora.vorbis`
- **Subsistema:** Mídia — Theora/Vorbis/Ogg
- **Tamanho:** 30 linhas

### Campos

_—_

### Métodos

- **initFields()**
- **initFields()**
- **VorbisInfo()**
- **clear()**
- **headerIn(VorbisComment arg0, OggPacket arg1)**
- **init()**

### Relações

—

---

