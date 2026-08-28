# Decode — `ios/src/main/java/world/gregs/voidosrs/ios` (4 classes)

> **Subsistema:** Plataforma — bootstrap Android/iOS

Total: 4

---

## ArgbBridge.java — Classe ArgbBridge

- **Arquivo:** `ios/src/main/java/world/gregs/voidosrs/ios/ArgbBridge.java`
- **Declaração:** `class ArgbBridge`
- **Package:** `world.gregs.voidosrs.ios`
- **Subsistema:** Plataforma — bootstrap Android/iOS
- **Tamanho:** 71 linhas

### Campos

| Nome | Tipo | Nota |
|---|---|---|
| `rgba` | `byte[]` |  |
| `data` | `NSData` |  |
| `provider` | `CGDataProvider` |  |
| `space` | `CGColorSpace` |  |
| `info` | `CGBitmapInfo` |  |
| `cg` | `CGImage` |  |
| `cg` | `CGImage` |  |
| `rgba` | `byte[]` |  |
| `space` | `CGColorSpace` |  |
| `ctx` | `CGBitmapContext` |  |

### Métodos

- **ArgbBridge()**
- **toImage(int[] argb, int w, int h)**
- **copy(UIImage image, int[] dest, int w, int h)**

### Relações

`voidawt.Toolkit`

---

## GameController.java — Classe GameController

- **Arquivo:** `ios/src/main/java/world/gregs/voidosrs/ios/GameController.java`
- **Declaração:** `class GameController extends UIViewController`
- **Package:** `world.gregs.voidosrs.ios`
- **Subsistema:** Plataforma — bootstrap Android/iOS
- **Tamanho:** 758 linhas

### Campos

| Nome | Tipo | Nota |
|---|---|---|
| `game` | `GameView` |  |
| `ime` | `UITextField` |  |
| `keyboardBall` | `UIButton` |  |
| `typedBuffer` | `String` |  |
| `serverOverlay` | `UIView` |  |
| `serverCard` | `UIView` |  |
| `serverTitle` | `UILabel` |  |
| `serverDetail` | `UILabel` |  |
| `serverField` | `UITextField` |  |
| `serverConnect` | `UIButton` |  |
| `serverCancel` | `UIButton` |  |
| `changeServerBtn` | `UIButton` |  |

### Métodos

- **loadView()**
- **setUserHome()**
- **run()**
- **shouldReturn(UITextField textField)**
- **injectEnter()**
- **hideKeyboard()**
- **shouldChangeCharacters(UITextField textField, NSRange range, String string)**
- **injectBackspace()**
- **onTouchUpInside(UIControl control, UIEvent event)**
- **hideKeyboard()**
- **showKeyboard()**
- **onTouchUpInside(UIControl control, UIEvent event)**
- **showServerOverlay(false)**
- **buildServerOverlay(root)**
- **setView(root)**
- **showServerOverlay(false)**
- **scheduleLoginPoll()**
- **showSoftKeyboard(final String reason)**
- **run()**
- **showKeyboard()**
- **hideSoftKeyboard(final String reason)**
- **run()**
- **hideKeyboard()**
- **toggleSoftKeyboard(final String reason)**
- **run()**
- **hideKeyboard()**
- **showKeyboard()**
- **syncSoftKeyboardToDevConsole()**
- **invoke(UIKeyboardAnimation animation)**
- **panGameForKeyboard(animation, false)**
- **invoke(UIKeyboardAnimation animation)**
- **panGameForKeyboard(animation, false)**
- **invoke(UIKeyboardAnimation animation)**
- **panGameForKeyboard(animation, true)**
- **viewDidLayoutSubviews()**

### Relações

`voidawt.AwtHost`, `voidawt.Component`, `voidawt.event.KeyEvent`

---

## GameView.java — Classe GameView

- **Arquivo:** `ios/src/main/java/world/gregs/voidosrs/ios/GameView.java`
- **Declaração:** `class GameView extends UIView implements AwtHost.Presenter`
- **Package:** `world.gregs.voidosrs.ios`
- **Subsistema:** Plataforma — bootstrap Android/iOS
- **Tamanho:** 845 linhas

### Campos

| Nome | Tipo | Nota |
|---|---|---|
| `imageView` | `UIImageView` |  |
| `cursorView` | `UIImageView` |  |
| `lastTouchPoint` | `CGPoint` |  |
| `sizeListener` | `Runnable` |  |
| `padController` | `GCController` |  |
| `padConnectObserver` | `NSObject` |  |
| `padDisconnectObserver` | `NSObject` |  |
| `cursor` | `UIImage` |  |
| `already` | `NSArray<GCController>` |  |
| `list` | `NSArray<GCController>` |  |
| `c` | `GCController` |  |
| `pad` | `GCExtendedGamepad` |  |

### Métodos

- **GameView(CGRect frame)**
- **setMultipleTouchEnabled(true)**
- **setUserInteractionEnabled(true)**
- **addSubview(imageView)**
- **addSubview(cursorView)**
- **startPadListening()**
- **startPadListening()**
- **invoke(GCController controller)**
- **attachPad(controller)**
- **invoke(GCController controller)**
- **deactivatePad()**
- **deactivatePad()**
- **run()**
- **anyPadConnected()**
- **attachPad(GCController controller)**
- **clearPadHandlers(padController)**
- **bindPadHandlers(pad)**
- **activatePad()**
- **clearPadHandlers(GCController controller)**
- **bindPadHandlers(final GCExtendedGamepad pad)**
- **invoke(GCControllerDirectionPad dpad, Float x, Float y)**
- **startPadTick()**
- **invoke(GCControllerDirectionPad dpad, Float x, Float y)**
- **startPadTick()**
- **invoke(GCControllerButtonInput button, Float value, Boolean pressed)**
- **invoke(GCControllerButtonInput button, Float value, Boolean pressed)**
- **invoke(GCControllerButtonInput button, Float value, Boolean pressed)**
- **ensureCursor()**
- **startPadTick()**
- **invoke(GCControllerButtonInput button, Float value, Boolean pressed)**
- **startPadTick()**
- **invoke(GCControllerButtonInput button, Float value, Boolean pressed)**
- **ensureCursor()**
- **startPadTick()**
- **onPadClick(boolean left, boolean pressed)**

### Relações

`voidawt.AwtHost`, `voidawt.event.MouseEvent`

---

## Main.java — Classe Main

- **Arquivo:** `ios/src/main/java/world/gregs/voidosrs/ios/Main.java`
- **Declaração:** `class Main extends UIApplicationDelegateAdapter`
- **Package:** `world.gregs.voidosrs.ios`
- **Subsistema:** Plataforma — bootstrap Android/iOS
- **Tamanho:** 44 linhas

### Campos

| Nome | Tipo | Nota |
|---|---|---|
| `window` | `UIWindow` |  |
| `session` | `AVAudioSession` |  |
| `pool` | `NSAutoreleasePool` |  |

### Métodos

- **didFinishLaunching(UIApplication application, UIApplicationLaunchOptions launchOptions)**
- **main(String[] args)**

### Relações

`voidsound`

---

