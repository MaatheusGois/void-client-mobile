# Decode — `android/app/src/main/java/voidswing` (3 classes)

> **Subsistema:** Compat — Swing/Sun

Total: 3

---

## ImageIcon.java — Classe ImageIcon

- **Arquivo:** `android/app/src/main/java/voidswing/ImageIcon.java`
- **Declaração:** `class ImageIcon`
- **Package:** `voidswing`
- **Subsistema:** Compat — Swing/Sun
- **Tamanho:** 36 linhas

### Campos

| Nome | Tipo | Nota |
|---|---|---|
| `image` | `Image` |  |
| `in` | `java.io.InputStream` |  |
| `data` | `byte[]` |  |
| `out` | `java.io.ByteArrayOutputStream` |  |
| `buf` | `byte[]` |  |

### Métodos

- **ImageIcon(URL url)**
- **getImage()**
- **readAll(java.io.InputStream in)**

### Relações

`voidawt.Image`, `voidawt.Toolkit`

---

## JFrame.java — Classe JFrame

- **Arquivo:** `android/app/src/main/java/voidswing/JFrame.java`
- **Declaração:** `class JFrame extends Frame`
- **Package:** `voidswing`
- **Subsistema:** Compat — Swing/Sun
- **Tamanho:** 28 linhas

### Campos

| Nome | Tipo | Nota |
|---|---|---|
| `contentPane` | `Container` |  |

### Métodos

- **JFrame()**
- **JFrame(String title)**
- **getContentPane()**
- **setDefaultCloseOperation(int operation)**

### Relações

`voidawt.BorderLayout`, `voidawt.Container`, `voidawt.Frame`, `voidawt.Image`, `voidawt.Panel`

---

## JPanel.java — Classe JPanel

- **Arquivo:** `android/app/src/main/java/voidswing/JPanel.java`
- **Declaração:** `class JPanel extends Panel`
- **Package:** `voidswing`
- **Subsistema:** Compat — Swing/Sun
- **Tamanho:** 6 linhas

### Campos

_—_

### Métodos

_—_

### Relações

`voidawt.Panel`

---

