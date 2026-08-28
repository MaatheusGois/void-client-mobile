# Decode — `android/app/src/main/java/jaclib/peer` (8 classes)

> **Subsistema:** Nativo — jaclib

Total: 8

---

## IUnknown.java — Classe IUnknown

- **Arquivo:** `android/app/src/main/java/jaclib/peer/IUnknown.java`
- **Declaração:** `class IUnknown extends Peer`
- **Package:** `jaclib.peer`
- **Subsistema:** Nativo — jaclib
- **Tamanho:** 15 linhas

### Campos

_—_

### Métodos

- **IUnknown(ti arg0)**
- **AddRef()**
- **a(int arg0)**

### Relações

`jaclib.peer`

---

## IUnknownReference.java — Classe IUnknownReference

- **Arquivo:** `android/app/src/main/java/jaclib/peer/IUnknownReference.java`
- **Declaração:** `class IUnknownReference extends PeerReference`
- **Package:** `jaclib.peer`
- **Subsistema:** Nativo — jaclib
- **Tamanho:** 11 linhas

### Campos

_—_

### Métodos

- **IUnknownReference(IUnknown arg0, ti arg1)**
- **releasePeer(long arg0)**

### Relações

`jaclib.peer`

---

## NativeHeapPeerReference.java — Classe NativeHeapPeerReference

- **Arquivo:** `android/app/src/main/java/jaclib/peer/NativeHeapPeerReference.java`
- **Declaração:** `class NativeHeapPeerReference extends PeerReference`
- **Package:** `jaclib.peer`
- **Subsistema:** Nativo — jaclib
- **Tamanho:** 11 linhas

### Campos

_—_

### Métodos

- **NativeHeapPeerReference(os arg0, ti arg1)**
- **releasePeer(long arg0)**

### Relações

`jaclib.peer`

---

## Peer.java — Classe Peer

- **Arquivo:** `android/app/src/main/java/jaclib/peer/Peer.java`
- **Declaração:** `class Peer`
- **Package:** `jaclib.peer`
- **Subsistema:** Nativo — jaclib
- **Tamanho:** 73 linhas

### Campos

| Nome | Tipo | Nota |
|---|---|---|
| `a` | `Class` |  |
| `reference` | `PeerReference` |  |
| `instance` | `Class` |  |
| `local1` | `char[]` |  |
| `local0` | `char[]` |  |

### Métodos

- **getClass(String name)**
- **init(Class arg0)**
- **z(String arg0)**
- **z(char[] arg0)**
- **Peer()**
- **a()**
- **a(byte arg0)**

### Relações

`jaclib.peer`, `jaclib.peer.PeerReference`

---

## PeerReference.java — Classe PeerReference

- **Arquivo:** `android/app/src/main/java/jaclib/peer/PeerReference.java`
- **Declaração:** `class PeerReference extends WeakReference`
- **Package:** `jaclib.peer`
- **Subsistema:** Nativo — jaclib
- **Tamanho:** 40 linhas

### Campos

| Nome | Tipo | Nota |
|---|---|---|
| `b` | `PeerReference` |  |
| `a` | `PeerReference` |  |

### Métodos

- **PeerReference(Peer arg0, ti arg1)**
- **setPeer(long arg0)**
- **a(int arg0)**
- **b(int arg0)**
- **releasePeer(long arg0)**

### Relações

`jaclib.peer`

---

## hb.java — Classe ofuscada do núcleo

- **Arquivo:** `android/app/src/main/java/jaclib/peer/hb.java`
- **Declaração:** `class hb extends ti`
- **Package:** `jaclib.peer`
- **Subsistema:** Nativo — jaclib
- **Tamanho:** 4 linhas

### Campos

_—_

### Métodos

_—_

### Relações

`jaclib.peer`

---

## os.java — Classe ofuscada do núcleo

- **Arquivo:** `android/app/src/main/java/jaclib/peer/os.java`
- **Declaração:** `class os extends Peer`
- **Package:** `jaclib.peer`
- **Subsistema:** Nativo — jaclib
- **Tamanho:** 9 linhas

### Campos

_—_

### Métodos

- **os(ti arg0)**

### Relações

`jaclib.peer`

---

## ti.java — Classe ofuscada do núcleo

- **Arquivo:** `android/app/src/main/java/jaclib/peer/ti.java`
- **Declaração:** `class ti`
- **Package:** `jaclib.peer`
- **Subsistema:** Nativo — jaclib
- **Tamanho:** 64 linhas

### Campos

| Nome | Tipo | Nota |
|---|---|---|
| `a` | `PeerReference` |  |
| `c` | `PeerReference` |  |
| `b` | `ReferenceQueue` |  |
| `local3` | `Reference` |  |
| `local9` | `PeerReference` |  |

### Métodos

- **a(int arg0)**
- **a(PeerReference arg0, byte arg1)**
- **a(PeerReference arg0, int arg1)**
- **b(int arg0)**
- **c(int arg0)**

### Relações

`jaclib.peer`

---

