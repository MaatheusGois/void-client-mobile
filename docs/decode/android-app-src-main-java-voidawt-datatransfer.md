# Decode — `android/app/src/main/java/voidawt/datatransfer` (5 classes)

> **Subsistema:** UI — AWT reimplementado (voidawt)

Total: 5

---

## Clipboard.java — Classe Clipboard

- **Arquivo:** `android/app/src/main/java/voidawt/datatransfer/Clipboard.java`
- **Declaração:** `class Clipboard`
- **Package:** `voidawt.datatransfer`
- **Subsistema:** UI — AWT reimplementado (voidawt)
- **Tamanho:** 13 linhas

### Campos

| Nome | Tipo | Nota |
|---|---|---|
| `contents` | `Transferable` |  |

### Métodos

- **getContents(Object requestor)**
- **setContents(Transferable contents, ClipboardOwner owner)**

### Relações

`voidawt.datatransfer`

---

## ClipboardOwner.java — Classe ClipboardOwner

- **Arquivo:** `android/app/src/main/java/voidawt/datatransfer/ClipboardOwner.java`
- **Declaração:** `interface ClipboardOwner`
- **Package:** `voidawt.datatransfer`
- **Subsistema:** UI — AWT reimplementado (voidawt)
- **Tamanho:** 5 linhas

### Campos

_—_

### Métodos

- **lostOwnership(Clipboard clipboard, Transferable contents)**

### Relações

`voidawt.datatransfer`

---

## DataFlavor.java — Classe DataFlavor

- **Arquivo:** `android/app/src/main/java/voidawt/datatransfer/DataFlavor.java`
- **Declaração:** `class DataFlavor`
- **Package:** `voidawt.datatransfer`
- **Subsistema:** UI — AWT reimplementado (voidawt)
- **Tamanho:** 8 linhas

### Campos

| Nome | Tipo | Nota |
|---|---|---|
| `stringFlavor` | `DataFlavor` |  |

### Métodos

- **DataFlavor()**

### Relações

`voidawt.datatransfer`

---

## StringSelection.java — Classe StringSelection

- **Arquivo:** `android/app/src/main/java/voidawt/datatransfer/StringSelection.java`
- **Declaração:** `class StringSelection implements Transferable`
- **Package:** `voidawt.datatransfer`
- **Subsistema:** UI — AWT reimplementado (voidawt)
- **Tamanho:** 23 linhas

### Campos

| Nome | Tipo | Nota |
|---|---|---|
| `data` | `String` |  |

### Métodos

- **StringSelection(String data)**
- **getTransferDataFlavors()**
- **isDataFlavorSupported(DataFlavor flavor)**
- **getTransferData(DataFlavor flavor)**

### Relações

`voidawt.datatransfer`

---

## Transferable.java — Classe Transferable

- **Arquivo:** `android/app/src/main/java/voidawt/datatransfer/Transferable.java`
- **Declaração:** `interface Transferable`
- **Package:** `voidawt.datatransfer`
- **Subsistema:** UI — AWT reimplementado (voidawt)
- **Tamanho:** 11 linhas

### Campos

_—_

### Métodos

- **getTransferDataFlavors()**
- **isDataFlavorSupported(DataFlavor flavor)**
- **getTransferData(DataFlavor flavor)**

### Relações

`voidawt.datatransfer`

---

