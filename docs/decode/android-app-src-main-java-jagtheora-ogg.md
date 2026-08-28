# Decode — `android/app/src/main/java/jagtheora/ogg` (4 classes)

> **Subsistema:** Mídia — Theora/Vorbis/Ogg

Total: 4

---

## OggPacket.java — Classe OggPacket

- **Arquivo:** `android/app/src/main/java/jagtheora/ogg/OggPacket.java`
- **Declaração:** `class OggPacket extends SimplePeer`
- **Package:** `jagtheora.ogg`
- **Subsistema:** Mídia — Theora/Vorbis/Ogg
- **Tamanho:** 18 linhas

### Campos

_—_

### Métodos

- **isTheora()**
- **isHeader()**
- **clear()**
- **getData()**
- **isKeyFrame()**
- **isVorbis()**

### Relações

—

---

## OggPage.java — Classe OggPage

- **Arquivo:** `android/app/src/main/java/jagtheora/ogg/OggPage.java`
- **Declaração:** `class OggPage extends SimplePeer`
- **Package:** `jagtheora.ogg`
- **Subsistema:** Mídia — Theora/Vorbis/Ogg
- **Tamanho:** 24 linhas

### Campos

_—_

### Métodos

- **getGranulePos()**
- **isBOS()**
- **getVersion()**
- **getCompletedPackets()**
- **getSerialNumber()**
- **isEOS()**
- **clear()**
- **isContinued()**
- **getPageNumber()**

### Relações

—

---

## OggStreamState.java — Classe OggStreamState

- **Arquivo:** `android/app/src/main/java/jagtheora/ogg/OggStreamState.java`
- **Declaração:** `class OggStreamState extends SimplePeer`
- **Package:** `jagtheora.ogg`
- **Subsistema:** Mídia — Theora/Vorbis/Ogg
- **Tamanho:** 32 linhas

### Campos

_—_

### Métodos

- **OggStreamState(int arg0)**
- **init(int arg0)**
- **packetOut()**
- **isEOS()**
- **packetPeek(OggPacket arg0)**
- **clear()**
- **reset()**
- **packetOut(OggPacket arg0)**
- **packetPeek()**
- **resetSerialNo(int arg0)**
- **pageIn(OggPage arg0)**

### Relações

—

---

## OggSyncState.java — Classe OggSyncState

- **Arquivo:** `android/app/src/main/java/jagtheora/ogg/OggSyncState.java`
- **Declaração:** `class OggSyncState extends SimplePeer`
- **Package:** `jagtheora.ogg`
- **Subsistema:** Mídia — Theora/Vorbis/Ogg
- **Tamanho:** 25 linhas

### Campos

_—_

### Métodos

- **OggSyncState()**
- **clear()**
- **init()**
- **pageSeek(OggPage arg0)**
- **pageOut(OggPage arg0)**
- **write(byte[] arg0, int arg1)**
- **reset()**

### Relações

—

---

