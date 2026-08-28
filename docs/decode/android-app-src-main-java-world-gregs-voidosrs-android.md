# Decode — `android/app/src/main/java/world/gregs/voidosrs/android` (1 classes)

> **Subsistema:** Plataforma — bootstrap Android/iOS

Total: 1

---

## MainActivity.java — Classe MainActivity

- **Arquivo:** `android/app/src/main/java/world/gregs/voidosrs/android/MainActivity.java`
- **Declaração:** `class MainActivity extends Activity`
- **Package:** `world.gregs.voidosrs.android`
- **Subsistema:** Plataforma — bootstrap Android/iOS
- **Tamanho:** 1832 linhas

### Campos

| Nome | Tipo | Nota |
|---|---|---|
| `debugHud` | `TextView` |  |
| `instance` | `MainActivity` |  |
| `imeInput` | `EditText` |  |
| `keyboardBall` | `TextView` |  |
| `game` | `GameView` |  |
| `rootLayout` | `FrameLayout` |  |
| `serverOverlay` | `View` |  |
| `serverField` | `EditText` |  |
| `changeServerBtn` | `TextView` |  |
| `serverTitle` | `TextView` |  |
| `serverSubtitle` | `TextView` |  |
| `serverCancel` | `View` |  |

### Métodos

- **run()**
- **applySurfaceSize(w, h, "deferred-shrink", true)**
- **run()**
- **hideSystemUi()**
- **applySurfaceSize(w, h, "resume-restore", true)**
- **onCreate(Bundle savedInstanceState)**
- **buildServerOverlay(root)**
- **setContentView(root)**
- **installKeyboardPan(root)**
- **hideSystemUi()**
- **requestAudioFocus()**
- **installLogBridge()**
- **showSoftKeyboard(String reason)**
- **hideSoftKeyboard(String reason)**
- **toggleSoftKeyboard(String reason)**
- **hideKeyboard()**
- **showKeyboard()**
- **syncSoftKeyboardToDevConsole()**
- **showServerOverlay(false)**
- **pollLoginButton()**
- **dispatchKeyEvent(KeyEvent event)**
- **onGenericMotionEvent(MotionEvent event)**
- **onPause()**
- **onResume()**
- **requestAudioFocus()**
- **hideSystemUi()**
- **windowSizePx()**
- **applySurfaceSize(int width, int height, String reason, boolean force)**
- **startClientIfReady(width, height)**
- **defaultHostHint()**
- **resolveBootHost()**
- **buildChangeServerButton()**
- **buildServerOverlay(FrameLayout root)**
- **applyServerFromOverlay()**
- **roundRect(int fill, int stroke, int radiusDp)**

### Relações

`voidawt.AwtHost`, `voidawt.Component`, `voidawt.event.KeyEvent.KEY_PRESSED`, `voidawt.event.KeyEvent.KEY_RELEASED`, `voidawt.event.KeyEvent.KEY_TYPED`, `voidawt.event.KeyEvent.VK_BACK_SPACE`, `voidawt.event.KeyEvent.VK_ENTER`, `voidawt.event.MouseEvent`

---

