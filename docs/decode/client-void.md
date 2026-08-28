# Decode — `client/void` (2 classes)

> **Subsistema:** Void — customizações

Total: 2

---

## LoginPrefs.java — Classe LoginPrefs

- **Arquivo:** `client/void/LoginPrefs.java`
- **Declaração:** `class LoginPrefs`
- **Package:** `(default)`
- **Subsistema:** Void — customizações
- **Tamanho:** 324 linhas

### Campos

| Nome | Tipo | Nota |
|---|---|---|
| `home` | `String` |  |
| `f` | `File` |  |
| `in` | `BufferedReader` |  |
| `user` | `String` |  |
| `pass` | `String` |  |
| `w` | `FileWriter` |  |
| `f` | `File` |  |
| `parent` | `File` |  |
| `creds` | `String[]` |  |
| `target` | `Class348_Sub41` |  |
| `all` | `Class46[]` |  |
| `first` | `Class46` |  |

### Métodos

- **LoginPrefs()**
- **file()**
- **load()**
- **save(String username, String password)**
- **remember(String username, String password)**
- **save(username, password)**
- **onLoginScreen()**
- **fillLoginFields(creds[0], creds[1])**
- **tick()**
- **runGraphicsAutoSetup()**
- **closeModalOverlays()**
- **needsGraphicsAutoSetup()**
- **runGraphicsAutoSetup()**
- **hasModalOverlay()**
- **closeModalOverlays()**
- **onWorldEntered()**
- **fillLoginFields(String user, String pass)**

### Relações

`Class125.aClass356_4915.method3482`, `Class125.aClass356_4915.method3484`, `Class127_Sub1.method1118`, `Class139.aBoolean1952`, `Class14_Sub2.method243`, `Class186.aString2496`, `Class186.aString2496.length`, `Class225.anInt2955`

---

## MobileKeyboard.java — Classe MobileKeyboard

- **Arquivo:** `client/void/MobileKeyboard.java`
- **Declaração:** `class MobileKeyboard`
- **Package:** `(default)`
- **Subsistema:** Void — customizações
- **Tamanho:** 245 linhas

### Campos

| Nome | Tipo | Nota |
|---|---|---|
| `text` | `String` |  |
| `parent` | `Class46` |  |

### Métodos

- **MobileKeyboard()**
- **setInset(int px, int viewHeight)**
- **onInterfacePress(Class46 component, int screenX, int screenY)**
- **requestShow("keyListener id=" + component.anInt830)**
- **requestShow("loginText id=" + component.anInt830 + " state=" + gameState)**
- **isTextInput(Class46 c)**
- **liftPx(Class46 c, int screenX, int screenY)**
- **loginLayerShift()**
- **loginHitShift(Class46 c)**
- **loginLayerShift()**
- **inChatBand(Class46 c, int screenX, int screenY)**
- **isFullscreen(Class46 c)**
- **isLoginState(int gameState)**
- **isIosHost()**
- **shiftY()**
- **requestShow(String reason)**
- **requestHide(String reason)**

### Relações

`Class240.clientState`, `Class46`, `Class46_782`, `Class92.anInt1524`, `voidawt.AwtHost`

---

