# Decode — `client/src-microbot` (14 classes)

> **Subsistema:** Automação — Microbot

Total: 14

---

## DesktopAwtMouse.java — Classe DesktopAwtMouse

- **Arquivo:** `client/src-microbot/DesktopAwtMouse.java`
- **Declaração:** `class DesktopAwtMouse implements MicrobotMouseBackend`
- **Package:** `(default)`
- **Subsistema:** Automação — Microbot
- **Tamanho:** 74 linhas

### Campos

| Nome | Tipo | Nota |
|---|---|---|
| `c` | `Canvas` |  |
| `c` | `Canvas` |  |
| `canvas` | `Canvas` |  |
| `event` | `MouseEvent` |  |

### Métodos

- **DesktopAwtMouse()**
- **canvas()**
- **mouseMoved(int x, int y)**
- **dispatch(MouseEvent.MOUSE_MOVED, x, y, 0, 0)**
- **mousePressed(int x, int y, int button)**
- **dispatch(MouseEvent.MOUSE_PRESSED, x, y, button, 1)**
- **mouseReleased(int x, int y, int button)**
- **dispatch(MouseEvent.MOUSE_RELEASED, x, y, button, 1)**
- **mouseClicked(int x, int y, int button)**
- **dispatch(MouseEvent.MOUSE_CLICKED, x, y, button, 1)**
- **canvasWidth()**
- **canvasHeight()**
- **dispatch(int id, int x, int y, int button, int clickCount)**
- **dispatchWithoutFocusGrab(canvas, event)**
- **dispatchWithoutFocusGrab(Canvas canvas, MouseEvent event)**

### Relações

`Class305.gameCanvas`

---

## ExampleCombatScript.java — Classe ExampleCombatScript

- **Arquivo:** `client/src-microbot/ExampleCombatScript.java`
- **Declaração:** `class ExampleCombatScript`
- **Package:** `(default)`
- **Subsistema:** Automação — Microbot
- **Tamanho:** 104 linhas

### Campos

| Nome | Tipo | Nota |
|---|---|---|
| `target` | `Npc` |  |
| `name` | `String` |  |
| `all` | `Npc[]` |  |
| `n` | `Npc` |  |
| `def` | `Class79` |  |
| `t` | `Class79` |  |

### Métodos

- **ExampleCombatScript()**
- **start()**
- **shutdown()**
- **isRunning()**
- **pulse()**
- **dumpNpcActions()**
- **dumpNpcActions()**

### Relações

`Class132.localPlayer`, `Class318_Sub1_Sub3_Sub3.aClass170_10209`, `Class79`, `Class79_10505`, `Class79_10505.aString1372`

---

## Microbot.java — Classe Microbot

- **Arquivo:** `client/src-microbot/Microbot.java`
- **Declaração:** `class Microbot`
- **Package:** `(default)`
- **Subsistema:** Automação — Microbot
- **Tamanho:** 103 linhas

### Campos

| Nome | Tipo | Nota |
|---|---|---|
| `targetMenu` | `NewMenuEntry` |  |
| `pendingDispatch` | `NewMenuEntry` |  |
| `mouse` | `VirtualMouse` |  |
| `clientThread` | `Thread` |  |
| `exampleCombat` | `ExampleCombatScript` |  |

### Métodos

- **Microbot()**
- **markClientThread()**
- **isClientThread()**
- **isLoggedIn()**
- **doInvoke(NewMenuEntry entry, int x, int y)**
- **doInvoke(NewMenuEntry entry)**
- **doInvoke(entry, -1, -1)**
- **log(String msg)**
- **getExampleCombat()**
- **isStarted()**
- **setStarted(boolean v)**

### Relações

`Class132.localPlayer`, `Class325`

---

## MicrobotBlockingEvents.java — Classe MicrobotBlockingEvents

- **Arquivo:** `client/src-microbot/MicrobotBlockingEvents.java`
- **Declaração:** `class MicrobotBlockingEvents`
- **Package:** `(default)`
- **Subsistema:** Automação — Microbot
- **Tamanho:** 12 linhas

### Campos

_—_

### Métodos

- **MicrobotBlockingEvents()**
- **isBlocking()**

### Relações

`Class132.localPlayer`

---

## MicrobotGlobal.java — Classe MicrobotGlobal

- **Arquivo:** `client/src-microbot/MicrobotGlobal.java`
- **Declaração:** `class MicrobotGlobal`
- **Package:** `(default)`
- **Subsistema:** Automação — Microbot
- **Tamanho:** 65 linhas

### Campos

| Nome | Tipo | Nota |
|---|---|---|
| `v` | `Object` |  |

### Métodos

- **MicrobotGlobal()**
- **sleep(long ms)**
- **sleepUntil(BooleanSupplier condition, long timeoutMs)**
- **sleepUntil(condition, timeoutMs, 100)**
- **sleepUntil(BooleanSupplier condition, long timeoutMs, long pollMs)**
- **sleep(pollMs)**
- **sleepUntilTrue(Callable condition, long timeoutMs)**
- **sleepUntil(new BooleanSupplier()**
- **getAsBoolean()**

### Relações

—

---

## MicrobotMenu.java — Classe MicrobotMenu

- **Arquivo:** `client/src-microbot/MicrobotMenu.java`
- **Declaração:** `class MicrobotMenu`
- **Package:** `(default)`
- **Subsistema:** Automação — Microbot
- **Tamanho:** 164 linhas

### Campos

| Nome | Tipo | Nota |
|---|---|---|
| `target` | `NewMenuEntry` |  |
| `match` | `MenuEntry` |  |
| `t` | `NewMenuEntry` |  |
| `entry` | `MenuEntry` |  |
| `b` | `StringBuilder` |  |

### Métodos

- **MicrobotMenu()**
- **applyTargetMenu()**
- **injectPending()**
- **applyTargetMenu()**
- **dispatchPending()**
- **buildEntry(NewMenuEntry t)**
- **onMenuOptionClicked()**
- **findMatching(NewMenuEntry target)**
- **plant(NewMenuEntry t)**
- **findMatching(t)**
- **stripCol(String s)**

### Relações

`Class135_Sub2.menuTipSecondary`, `Class316.menuTip`, `Class325`, `Class325.processMenuAction`, `Class348_Sub40_Sub4.menuEntries.method1990`, `Class348_Sub40_Sub4.menuEntries.method1995`, `Class348_Sub40_Sub4.menuEntries.method1996`, `Class50_Sub3`

---

## MicrobotMouseBackend.java — Classe MicrobotMouseBackend

- **Arquivo:** `client/src-microbot/MicrobotMouseBackend.java`
- **Declaração:** `interface MicrobotMouseBackend`
- **Package:** `(default)`
- **Subsistema:** Automação — Microbot
- **Tamanho:** 20 linhas

### Campos

_—_

### Métodos

- **mouseMoved(int x, int y)**
- **mousePressed(int x, int y, int button)**
- **mouseReleased(int x, int y, int button)**
- **mouseClicked(int x, int y, int button)**
- **canvasWidth()**
- **canvasHeight()**

### Relações

`Class305`, `voidawt`

---

## MicrobotPanel.java — Classe MicrobotPanel

- **Arquivo:** `client/src-microbot/MicrobotPanel.java`
- **Declaração:** `class MicrobotPanel`
- **Package:** `(default)`
- **Subsistema:** Automação — Microbot
- **Tamanho:** 188 linhas

### Campos

| Nome | Tipo | Nota |
|---|---|---|
| `font` | `BitmapFont` |  |
| `header` | `String` |  |
| `node` | `Class348` |  |
| `next` | `Class348` |  |
| `click` | `Class348_Sub45` |  |
| `script` | `ExampleCombatScript` |  |

### Métodos

- **MicrobotPanel()**
- **isVisible()**
- **height()**
- **contains(int x, int y)**
- **isMouseOver()**
- **draw(ha renderer)**
- **pollInput()**
- **onClick(cx, cy)**
- **onClick(int x, int y)**
- **toggleCombat()**
- **toggleCombat()**

### Relações

`Class240.aClass324_4684`, `Class258_Sub4.mouseHandler`, `Class258_Sub4.mouseHandler.getCursorX`, `Class258_Sub4.mouseHandler.getCursorY`, `Class261`, `Class262_8744`, `Class318_Sub1_Sub3`, `Class318_Sub1_Sub3.aClass262_8744.method1990`

---

## MicrobotRuntime.java — Classe MicrobotRuntime

- **Arquivo:** `client/src-microbot/MicrobotRuntime.java`
- **Declaração:** `class MicrobotRuntime`
- **Package:** `(default)`
- **Subsistema:** Automação — Microbot
- **Tamanho:** 125 linhas

### Campos

| Nome | Tipo | Nota |
|---|---|---|
| `npcCache` | `Rs2NpcCache` |  |
| `playerCache` | `Rs2PlayerCache` |  |
| `tileObjectCache` | `Rs2TileObjectCache` |  |
| `tileItemCache` | `Rs2TileItemCache` |  |
| `combat` | `ExampleCombatScript` |  |
| `attack` | `String` |  |
| `actions` | `String[]` |  |
| `label` | `String` |  |

### Métodos

- **MicrobotRuntime()**
- **ensureStarted()**
- **tick()**
- **ensureStarted()**
- **queueWalkAssist(int localX, int localY)**
- **injectNpcMenu(Npc npc, Class79 composition)**
- **handleMenuAction(MenuEntry entry)**

### Relações

`Class274.aClass274_3506.method2063`, `Class298.method2252`, `Class348_Sub33.anInt6967`, `Class50_Sub3.addMenuEntry`, `Class79`

---

## MicrobotScript.java — Classe MicrobotScript

- **Arquivo:** `client/src-microbot/MicrobotScript.java`
- **Declaração:** `class MicrobotScript extends MicrobotGlobal`
- **Package:** `(default)`
- **Subsistema:** Automação — Microbot
- **Tamanho:** 94 linhas

### Campos

| Nome | Tipo | Nota |
|---|---|---|
| `executor` | `ScheduledExecutorService` |  |
| `t` | `Thread` |  |
| `running` | `AtomicBoolean` |  |
| `mainFuture` | `ScheduledFuture` |  |

### Métodos

- **run()**
- **schedule(Runnable task, long delayMs)**
- **shutdownScheduleOnly()**
- **run()**
- **shutdown()**
- **shutdownScheduleOnly()**
- **dispose()**
- **shutdown()**
- **shutdownScheduleOnly()**
- **isRunning()**

### Relações

—

---

## MicrobotWidgets.java — Classe MicrobotWidgets

- **Arquivo:** `client/src-microbot/MicrobotWidgets.java`
- **Declaração:** `class MicrobotWidgets`
- **Package:** `(default)`
- **Subsistema:** Automação — Microbot
- **Tamanho:** 56 linhas

### Campos

| Nome | Tipo | Nota |
|---|---|---|
| `p` | `Player` |  |
| `p` | `Player` |  |
| `p` | `Player` |  |

### Métodos

- **MicrobotWidgets()**
- **get(int packedId)**
- **getChild(int packedParent, int child)**
- **localAbsX()**
- **localAbsY()**
- **localPlane()**

### Relações

`Class132.localPlayer`, `Class348_Sub22.method2957`, `Class46`, `Class90.regionTileY`

---

## NewMenuEntry.java — Classe NewMenuEntry

- **Arquivo:** `client/src-microbot/NewMenuEntry.java`
- **Declaração:** `class NewMenuEntry`
- **Package:** `(default)`
- **Subsistema:** Automação — Microbot
- **Tamanho:** 110 linhas

### Campos

| Nome | Tipo | Nota |
|---|---|---|
| `option` | `String` |  |
| `target` | `String` |  |

### Métodos

- **NewMenuEntry()**
- **NewMenuEntry(String option, String target, int opcode, long identifier, int param0, int param1, int itemId)**
- **getOption()**
- **setOption(String option)**
- **getTarget()**
- **setTarget(String target)**
- **getOpcode()**
- **setOpcode(int opcode)**
- **getIdentifier()**
- **setIdentifier(long identifier)**
- **getParam0()**
- **setParam0(int param0)**
- **getParam1()**
- **setParam1(int param1)**
- **getItemId()**
- **setItemId(int itemId)**
- **toString()**

### Relações

`Class325`

---

## VirtualMouse.java — Classe VirtualMouse

- **Arquivo:** `client/src-microbot/VirtualMouse.java`
- **Declaração:** `class VirtualMouse`
- **Package:** `(default)`
- **Subsistema:** Automação — Microbot
- **Tamanho:** 101 linhas

### Campos

| Nome | Tipo | Nota |
|---|---|---|
| `scheduler` | `ScheduledExecutorService` |  |
| `t` | `Thread` |  |
| `backend` | `MicrobotMouseBackend` |  |
| `mobile` | `VoidAwtHostMouse` |  |
| `action` | `Runnable` |  |

### Métodos

- **VirtualMouse()**
- **ensureBackend()**
- **getBackend()**
- **ensureBackend()**
- **click(int x, int y, NewMenuEntry entry)**
- **ensureBackend()**
- **run()**
- **clickCenter(NewMenuEntry entry)**
- **ensureBackend()**
- **click(x, y, entry)**
- **move(int x, int y)**
- **ensureBackend()**
- **getLastX()**
- **getLastY()**

### Relações

—

---

## VoidAwtHostMouse.java — Classe VoidAwtHostMouse

- **Arquivo:** `client/src-microbot/VoidAwtHostMouse.java`
- **Declaração:** `class VoidAwtHostMouse implements MicrobotMouseBackend`
- **Package:** `(default)`
- **Subsistema:** Automação — Microbot
- **Tamanho:** 54 linhas

### Campos

| Nome | Tipo | Nota |
|---|---|---|
| `injectMouse` | `java.lang.reflect.Method` |  |
| `c` | `Class` |  |

### Métodos

- **VoidAwtHostMouse(Class hostClass)**
- **tryCreate()**
- **mouseMoved(int x, int y)**
- **invoke(503, x, y, 0, 0)**
- **mousePressed(int x, int y, int button)**
- **invoke(501, x, y, button, 1)**
- **mouseReleased(int x, int y, int button)**
- **invoke(502, x, y, button, 1)**
- **mouseClicked(int x, int y, int button)**
- **invoke(500, x, y, button, 1)**
- **canvasWidth()**
- **canvasHeight()**
- **invoke(int id, int x, int y, int button, int clickCount)**

### Relações

`Class321.anInt4017`, `Class348_Sub42_Sub8_Sub2.anInt10432`, `voidawt`, `voidawt.AwtHost`

---

