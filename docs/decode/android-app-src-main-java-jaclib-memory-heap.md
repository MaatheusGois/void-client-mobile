# Decode — `android/app/src/main/java/jaclib/memory/heap` (2 classes)

> **Subsistema:** Nativo — jaclib

Total: 2

---

## NativeHeap.java — Classe NativeHeap

- **Arquivo:** `android/app/src/main/java/jaclib/memory/heap/NativeHeap.java`
- **Declaração:** `class NativeHeap`
- **Package:** `jaclib.memory.heap`
- **Subsistema:** Nativo — jaclib
- **Tamanho:** 55 linhas

### Campos

_—_

### Métodos

- **NativeHeap(int arg0)**
- **allocateHeap(int arg0)**
- **finalize()**
- **deallocateBuffer(int arg0)**
- **allocateBuffer(int arg0, boolean arg1)**
- **deallocateHeap()**
- **a(int arg0, boolean arg1)**
- **put(int arg0, byte[] arg1, int arg2, int arg3, int arg4)**
- **get(int arg0, byte[] arg1, int arg2, int arg3, int arg4)**
- **a()**
- **getBufferAddress(int arg0)**
- **b()**

### Relações

`jaclib.memory.heap`

---

## NativeHeapBuffer.java — Classe NativeHeapBuffer

- **Arquivo:** `android/app/src/main/java/jaclib/memory/heap/NativeHeapBuffer.java`
- **Declaração:** `class NativeHeapBuffer implements Buffer, Source`
- **Package:** `jaclib.memory.heap`
- **Subsistema:** Nativo — jaclib
- **Tamanho:** 56 linhas

### Campos

| Nome | Tipo | Nota |
|---|---|---|
| `a` | `NativeHeap` |  |

### Métodos

- **NativeHeapBuffer(NativeHeap arg0, int arg1, int arg2)**
- **getSize()**
- **a(byte[] arg0, int arg1, int arg2, int arg3)**
- **getAddress()**
- **a()**
- **b()**
- **finalize()**

### Relações

`jaclib.memory.Buffer`, `jaclib.memory.Source`, `jaclib.memory.heap`

---

