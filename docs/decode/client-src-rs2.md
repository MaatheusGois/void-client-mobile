# Decode — `client/src-rs2` (13 classes)

> **Subsistema:** Compat — RS2

Total: 13

---

## Rs2Bank.java — Classe Rs2Bank

- **Arquivo:** `client/src-rs2/Rs2Bank.java`
- **Declaração:** `class Rs2Bank`
- **Package:** `(default)`
- **Subsistema:** Compat — RS2
- **Tamanho:** 128 linhas

### Campos

| Nome | Tipo | Nota |
|---|---|---|
| `title` | `Class46` |  |
| `bankSlots` | `Class46[]` |  |
| `close` | `Class46` |  |
| `slot` | `Class46` |  |
| `slots` | `Class46[]` |  |
| `entry` | `NewMenuEntry` |  |
| `banker` | `Npc` |  |
| `list` | `java.util.ArrayList` |  |
| `roots` | `Class46[][]` |  |
| `bestList` | `java.util.ArrayList` |  |
| `tmp` | `java.util.ArrayList` |  |
| `all` | `Class46[]` |  |

### Métodos

- **Rs2Bank()**
- **isOpen()**
- **close()**
- **depositAll(int itemId)**
- **withdraw(int itemId, String action)**
- **withdrawOne(int itemId)**
- **open()**
- **bankSlots()**
- **collect(MicrobotWidgets.BANK_GROUP, list)**
- **collect(g, tmp)**
- **collect(int group, java.util.ArrayList list)**
- **walk(all[i], list)**
- **walk(Class46 c, java.util.ArrayList list)**
- **walk(c.aClass46Array798[i], list)**

### Relações

`Class348_Sub40_Sub33.aClass46ArrayArray9427`, `Class348_Sub40_Sub33.aClass46ArrayArray9427.length`, `Class46`, `Class46Array798`, `Class46Array798.length`

---

## Rs2Combat.java — Classe Rs2Combat

- **Arquivo:** `client/src-rs2/Rs2Combat.java`
- **Declaração:** `class Rs2Combat`
- **Package:** `(default)`
- **Subsistema:** Compat — RS2
- **Tamanho:** 28 linhas

### Campos

| Nome | Tipo | Nota |
|---|---|---|
| `n` | `Npc` |  |

### Métodos

- **Rs2Combat()**
- **inCombat()**
- **attackNearest(int maxDist)**

### Relações

—

---

## Rs2GameObject.java — Classe Rs2GameObject

- **Arquivo:** `client/src-rs2/Rs2GameObject.java`
- **Declaração:** `class Rs2GameObject`
- **Package:** `(default)`
- **Subsistema:** Compat — RS2
- **Tamanho:** 193 linhas

### Campos

| Nome | Tipo | Nota |
|---|---|---|
| `OBJECT_OPCODES` | `int[]` |  |
| `hit` | `SceneObjectHit` |  |
| `act` | `String` |  |
| `target` | `String` |  |
| `entry` | `NewMenuEntry` |  |
| `layer` | `Class357[][]` |  |
| `best` | `SceneObjectHit` |  |
| `col` | `Class357[]` |  |
| `tile` | `Class357` |  |
| `h` | `SceneObjectHit` |  |
| `iface` | `Interface10` |  |
| `def` | `Class51` |  |

### Métodos

- **Rs2GameObject()**
- **interactNearest(String objectName, String action)**
- **interact(SceneObjectHit hit, String action)**
- **findNearest(String name)**
- **closer(SceneObjectHit best, int bestDist, SceneObjectHit h, int px, int py)**
- **hitFromNode(Object node, String name, int x, int y, int plane)**
- **opcodeFor(Class51 def, String action)**
- **SceneObjectHit(Class51 def, Interface10 iface, int localX, int localY, int plane)**

### Relações

`Class132.localPlayer`, `Class132.localPlayer.anIntArray10317`, `Class132.localPlayer.anIntArray10320`, `Class132.localPlayer.plane`, `Class147.aClass357ArrayArrayArray2029`, `Class147.aClass357ArrayArrayArray2029.length`, `Class148`, `Class148_2038`

---

## Rs2GroundItem.java — Classe Rs2GroundItem

- **Arquivo:** `client/src-rs2/Rs2GroundItem.java`
- **Declaração:** `class Rs2GroundItem`
- **Package:** `(default)`
- **Subsistema:** Compat — RS2
- **Tamanho:** 30 linhas

### Campos

| Nome | Tipo | Nota |
|---|---|---|
| `label` | `Class46` |  |

### Métodos

- **Rs2GroundItem()**
- **takeNearest(String name)**
- **interactNearest(name, "Take")**
- **interactNearest(String name, String action)**

### Relações

`Class239_Sub24`, `Class348_Sub34`, `Class348_Sub37.`, `Class46`

---

## Rs2Inventory.java — Classe Rs2Inventory

- **Arquivo:** `client/src-rs2/Rs2Inventory.java`
- **Declaração:** `class Rs2Inventory`
- **Package:** `(default)`
- **Subsistema:** Compat — RS2
- **Tamanho:** 127 linhas

### Campos

| Nome | Tipo | Nota |
|---|---|---|
| `slots` | `Class46[]` |  |
| `slots` | `Class46[]` |  |
| `slot` | `Class46` |  |
| `entry` | `NewMenuEntry` |  |
| `slots` | `Class46[]` |  |
| `list` | `java.util.ArrayList` |  |
| `roots` | `Class46[][]` |  |
| `all` | `Class46[]` |  |

### Métodos

- **Rs2Inventory()**
- **count(int itemId)**
- **contains(int itemId)**
- **isFull()**
- **interact(int itemId, String action)**
- **drop(int itemId)**
- **interact(itemId, "Drop")**
- **findSlot(int itemId)**
- **slots()**
- **collectGroup(MicrobotWidgets.INVENTORY_GROUP, list)**
- **collectGroup(g, list)**
- **collectGroup(int group, java.util.ArrayList list)**
- **collectItems(all[i], list)**
- **collectItems(Class46 c, java.util.ArrayList list)**
- **collectItems(c.aClass46Array798[i], list)**

### Relações

`Class348_Sub40_Sub33.aClass46ArrayArray9427`, `Class348_Sub40_Sub33.aClass46ArrayArray9427.length`, `Class46`, `Class46Array798`, `Class46Array798.length`

---

## Rs2Keyboard.java — Classe Rs2Keyboard

- **Arquivo:** `client/src-rs2/Rs2Keyboard.java`
- **Declaração:** `class Rs2Keyboard`
- **Package:** `(default)`
- **Subsistema:** Compat — RS2
- **Tamanho:** 58 linhas

### Campos

| Nome | Tipo | Nota |
|---|---|---|
| `canvas` | `Canvas` |  |
| `e` | `KeyEvent` |  |
| `c` | `Class` |  |
| `m` | `Method` |  |

### Métodos

- **Rs2Keyboard()**
- **typeString(String text)**
- **keyPress(int keyCode)**
- **dispatch(KeyEvent.KEY_PRESSED, keyCode, KeyEvent.CHAR_UNDEFINED)**
- **dispatch(KeyEvent.KEY_RELEASED, keyCode, KeyEvent.CHAR_UNDEFINED)**
- **keyType(char ch)**
- **dispatch(KeyEvent.KEY_PRESSED, code, ch)**
- **dispatch(KeyEvent.KEY_TYPED, code, ch)**
- **dispatch(KeyEvent.KEY_RELEASED, code, ch)**
- **dispatch(int id, int keyCode, char ch)**
- **tryMobileInject(int id, int keyCode, char ch)**

### Relações

`Class305.gameCanvas`, `voidawt.AwtHost`, `voidawt.AwtHost.injectKey`

---

## Rs2Npc.java — Classe Rs2Npc

- **Arquivo:** `client/src-rs2/Rs2Npc.java`
- **Declaração:** `class Rs2Npc`
- **Package:** `(default)`
- **Subsistema:** Compat — RS2
- **Tamanho:** 138 linhas

### Campos

| Nome | Tipo | Nota |
|---|---|---|
| `best` | `Npc` |  |
| `all` | `Npc[]` |  |
| `n` | `Npc` |  |
| `nname` | `String` |  |
| `attack` | `String` |  |
| `best` | `Npc` |  |
| `all` | `Npc[]` |  |
| `n` | `Npc` |  |
| `def` | `Class79` |  |
| `def` | `Class79` |  |
| `target` | `String` |  |
| `entry` | `NewMenuEntry` |  |

### Métodos

- **Rs2Npc()**
- **getAll()**
- **getNearest(String name)**
- **getNearestAttackable()**
- **interact(Npc npc, String action)**
- **attack(Npc npc)**
- **attackLabelPublic()**
- **attackLabel()**
- **attackLabel()**
- **hasAction(Class79 def, String action)**
- **opcodeForAction(Class79 def, String action)**

### Relações

`Class274.aClass274_3506.method2063`, `Class286_Sub3`, `Class318_Sub1_Sub3_Sub3.aClass170_10209`, `Class348_Sub33.anInt6967`, `Class79`, `Class79_10505`, `Class79_10505.aString1372`

---

## Rs2NpcCache.java — Classe Rs2NpcCache

- **Arquivo:** `client/src-rs2/Rs2NpcCache.java`
- **Declaração:** `class Rs2NpcCache`
- **Package:** `(default)`
- **Subsistema:** Compat — RS2
- **Tamanho:** 59 linhas

### Campos

| Nome | Tipo | Nota |
|---|---|---|
| `npcs` | `Npc[]` |  |
| `node` | `Class348_Sub22` |  |
| `out` | `Npc[]` |  |

### Métodos

- **Rs2NpcCache()**
- **refreshIfNeeded()**
- **size()**
- **refreshIfNeeded()**
- **get(int i)**
- **refreshIfNeeded()**
- **snapshot()**
- **refreshIfNeeded()**

### Relações

`Class150.anInt2057`, `Class282`, `Class282.aClass356_3654.method3480`, `Class348_Sub22`, `Class356_3654`, `Class367_Sub11`, `Class367_Sub11.clientCycle`, `Class74`

---

## Rs2Player.java — Classe Rs2Player

- **Arquivo:** `client/src-rs2/Rs2Player.java`
- **Declaração:** `class Rs2Player`
- **Package:** `(default)`
- **Subsistema:** Compat — RS2
- **Tamanho:** 79 linhas

### Campos

| Nome | Tipo | Nota |
|---|---|---|
| `p` | `Player` |  |
| `p` | `Player` |  |
| `p` | `Player` |  |
| `p` | `Player` |  |
| `p` | `Player` |  |

### Métodos

- **Rs2Player()**
- **getLocal()**
- **isMoving()**
- **inCombat()**
- **getInteractingIndex()**
- **getAnimation()**
- **getCombatLevel()**
- **getWorldX()**
- **getWorldY()**
- **getPlane()**
- **distanceTo(int absX, int absY)**
- **distanceTo(Npc npc)**
- **distanceTo(nx, ny)**

### Relações

`Class90.regionTileY`

---

## Rs2PlayerCache.java — Classe Rs2PlayerCache

- **Arquivo:** `client/src-rs2/Rs2PlayerCache.java`
- **Declaração:** `class Rs2PlayerCache`
- **Package:** `(default)`
- **Subsistema:** Compat — RS2
- **Tamanho:** 58 linhas

### Campos

| Nome | Tipo | Nota |
|---|---|---|
| `local` | `Player` |  |
| `players` | `Player[]` |  |
| `indices` | `int[]` |  |
| `p` | `Player` |  |

### Métodos

- **Rs2PlayerCache()**
- **refreshIfNeeded()**
- **getLocal()**
- **refreshIfNeeded()**
- **size()**
- **refreshIfNeeded()**
- **get(int i)**
- **refreshIfNeeded()**

### Relações

`Class132.localPlayer`, `Class286_Sub7.anIntArray6290`, `Class294.aPlayerArray5058`, `Class328_Sub1.anInt6513`, `Class367_Sub11.clientCycle`

---

## Rs2TileCaches.java — Classe Rs2TileObjectCache

- **Arquivo:** `client/src-rs2/Rs2TileCaches.java`
- **Declaração:** `class Rs2TileObjectCache`
- **Package:** `(default)`
- **Subsistema:** Compat — RS2
- **Tamanho:** 37 linhas

### Campos

_—_

### Métodos

- **Rs2TileObjectCache()**
- **refreshIfNeeded()**
- **Rs2TileItemCache()**
- **refreshIfNeeded()**

### Relações

`Class367_Sub11.clientCycle`

---

## Rs2Walker.java — Classe Rs2Walker

- **Arquivo:** `client/src-rs2/Rs2Walker.java`
- **Declaração:** `class Rs2Walker`
- **Package:** `(default)`
- **Subsistema:** Compat — RS2
- **Tamanho:** 48 linhas

### Campos

| Nome | Tipo | Nota |
|---|---|---|
| `entry` | `NewMenuEntry` |  |

### Métodos

- **Rs2Walker()**
- **isNear(int absX, int absY, int dist)**
- **walkTo(int absX, int absY)**
- **walkTo(int absX, int absY, int plane)**
- **walkToLocal(int localX, int localY)**
- **walkTo(localX + za_Sub2.regionTileX, localY + Class90.regionTileY)**

### Relações

`Class298`, `Class325`, `Class348_Sub40_Sub3.anInt9109`, `Class367_Sub4.anInt7319`, `Class90.regionTileY`

---

## Rs2Widget.java — Classe Rs2Widget

- **Arquivo:** `client/src-rs2/Rs2Widget.java`
- **Declaração:** `class Rs2Widget`
- **Package:** `(default)`
- **Subsistema:** Compat — RS2
- **Tamanho:** 95 linhas

### Campos

| Nome | Tipo | Nota |
|---|---|---|
| `roots` | `Class46[][]` |  |
| `all` | `Class46[]` |  |
| `c` | `Class46` |  |
| `nested` | `Class46` |  |
| `c` | `Class46` |  |
| `nested` | `Class46` |  |
| `entry` | `NewMenuEntry` |  |
| `w` | `Class46` |  |

### Métodos

- **Rs2Widget()**
- **get(int packedId)**
- **isVisible(Class46 w)**
- **findByText(String text)**
- **findInChildren(Class46[] kids, String text)**
- **click(Class46 component, String option)**
- **click(String text)**

### Relações

`Class239_Sub17`, `Class348_Sub40_Sub33.aClass46ArrayArray9427`, `Class46`, `Class46Array798`

---

