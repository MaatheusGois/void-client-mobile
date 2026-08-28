# Decode — `android/app/src/main/java/jaclib/memory` (4 classes)

> **Subsistema:** Nativo — jaclib

Total: 4

---

## Buffer.java — Classe Buffer

- **Arquivo:** `android/app/src/main/java/jaclib/memory/Buffer.java`
- **Declaração:** `interface Buffer`
- **Package:** `jaclib.memory`
- **Subsistema:** Nativo — jaclib
- **Tamanho:** 11 linhas

### Campos

_—_

### Métodos

- **getAddress()**
- **getSize()**
- **a(byte[] arg0, int arg1, int arg2, int arg3)**

### Relações

`jaclib.memory`

---

## NativeBuffer.java — Classe NativeBuffer

- **Arquivo:** `android/app/src/main/java/jaclib/memory/NativeBuffer.java`
- **Declaração:** `class NativeBuffer implements Buffer, Source`
- **Package:** `jaclib.memory`
- **Subsistema:** Nativo — jaclib
- **Tamanho:** 36 linhas

### Campos

_—_

### Métodos

- **a(byte[] arg0, int arg1, int arg2, int arg3)**
- **a(long arg0, int arg1)**
- **getAddress()**
- **put(long arg0, byte[] arg1, int arg2, int arg3, int arg4)**
- **get(long arg0, byte[] arg1, int arg2, int arg3, int arg4)**
- **getSize()**

### Relações

`jaclib.memory`

---

## Source.java — Classe Source

- **Arquivo:** `android/app/src/main/java/jaclib/memory/Source.java`
- **Declaração:** `interface Source`
- **Package:** `jaclib.memory`
- **Subsistema:** Nativo — jaclib
- **Tamanho:** 9 linhas

### Campos

_—_

### Métodos

- **getSize()**
- **getAddress()**

### Relações

`jaclib.memory`

---

## Stream.java — Classe Stream

- **Arquivo:** `android/app/src/main/java/jaclib/memory/Stream.java`
- **Declaração:** `class Stream`
- **Package:** `jaclib.memory`
- **Subsistema:** Nativo — jaclib
- **Tamanho:** 161 linhas

### Campos

| Nome | Tipo | Nota |
|---|---|---|
| `a` | `Buffer` |  |
| `c` | `byte[]` |  |

### Métodos

- **getLSB(int arg0)**
- **floatToRawIntBits(float arg0)**
- **b()**
- **Stream(Buffer arg0)**
- **Stream(Buffer arg0, int arg1, int arg2)**
- **Stream()**
- **Stream(int arg0)**
- **a(float arg0)**
- **a()**
- **f(int arg0)**
- **b(int arg0)**
- **c(int arg0)**
- **a(Buffer arg0)**
- **a(int arg0, int arg1, int arg2, int arg3)**
- **a(int arg0)**
- **a(Buffer arg0, int arg1, int arg2)**
- **e(int arg0)**
- **d(int arg0)**
- **b(int arg0, int arg1, int arg2, int arg3)**
- **b(float arg0)**
- **flush()**
- **c()**

### Relações

`jaclib.memory`

---

