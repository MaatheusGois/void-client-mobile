# Decode — `client/menu` (2 classes)

> **Subsistema:** UI — Menus

Total: 2

---

## DefaultClickSwapper.java — Classe DefaultClickSwapper

- **Arquivo:** `client/menu/DefaultClickSwapper.java`
- **Declaração:** `class DefaultClickSwapper`
- **Package:** `(default)`
- **Subsistema:** UI — Menus
- **Tamanho:** 489 linhas

### Campos

| Nome | Tipo | Nota |
|---|---|---|
| `NPC_OPCODES` | `int[]` |  |
| `OBJECT_OPCODES` | `int[]` |  |
| `ITEM_OPCODES` | `int[]` |  |
| `FILE_NAME` | `String` |  |
| `COL_ACCENT` | `String` |  |
| `COL_END` | `String` |  |
| `actions` | `String[]` |  |
| `current` | `String` |  |
| `actions` | `String[]` |  |
| `current` | `String` |  |
| `current` | `String` |  |
| `unique` | `java.util.LinkedHashSet` |  |

### Métodos

- **DefaultClickSwapper()**
- **getPreferredNpcAction(int compositionId)**
- **ensureLoaded()**
- **getPreferredAction(int compositionId)**
- **getPreferredNpcAction(compositionId)**
- **getPreferredObjectAction(int objectId)**
- **ensureLoaded()**
- **getPreferredItemAction(int itemId)**
- **ensureLoaded()**
- **injectNpcMenu(Npc npc, Class79 composition)**
- **ensureLoaded()**
- **injectActionRows(actions, current, OPCODE_SET_NPC, OPCODE_RESET_NPC, compositionId)**
- **injectObjectMenu(Class51 object)**
- **ensureLoaded()**
- **injectActionRows(actions, current, OPCODE_SET_OBJECT, OPCODE_RESET_OBJECT, objectId)**
- **injectItemMenu(Class46 component)**
- **ensureLoaded()**
- **isSkippedItemOption(String action)**
- **injectActionRows(String[] actions, String current, int setOp, int resetOp, int id)**
- **applySwaps()**
- **ensureLoaded()**
- **demoteAttackNear(preferred)**
- **applyNpcSwaps()**
- **applySwaps()**
- **handleMenuAction(MenuEntry entry)**
- **setDefault(npcDefaults, "npc", id, label)**
- **clearDefault(npcDefaults, "npc", id)**
- **setDefault(objectDefaults, "object", id, label)**
- **clearDefault(objectDefaults, "object", id)**
- **setDefault(itemDefaults, "item", id, label)**
- **clearDefault(itemDefaults, "item", id)**
- **stripDefaultLabel(String label)**
- **setDefault(Map<Integer, String> map, String kind, int id, String actionName)**
- **ensureLoaded()**
- **save()**

### Relações

`Class239_Sub8.method1753`, `Class274.aClass274_3506.method2063`, `Class282.aClass356_3654.method3480`, `Class286_Sub2.method2144`, `Class318_Sub1_Sub3_Sub3.aClass170_10209`, `Class318_Sub1_Sub5.method2485`, `Class348_Sub22`, `Class348_Sub33.anInt6967`

---

## MenuEntry.java — Classe MenuEntry

- **Arquivo:** `client/menu/MenuEntry.java`
- **Declaração:** `class MenuEntry extends Class348_Sub42`
- **Package:** `(default)`
- **Subsistema:** UI — Menus
- **Tamanho:** 181 linhas

### Campos

| Nome | Tipo | Nota |
|---|---|---|
| `option` | `String` |  |
| `aString9595` | `String` |  |
| `target` | `String` |  |
| `aClass356_9603` | `Class356` |  |
| `anIntArray9612` | `int[]` |  |

### Métodos

- **method3228(int i)**
- **method3229(int i)**
- **method3230(int[] is, int[] is_1_, int i)**
- **method3231(int i, int i_3_, int i_4_, int i_5_, int i_6_, int i_7_)**
- **MenuEntry(String option, String target, int priority, int opcode, int itemId, long identifier, int param0, int param1, boolean bool, boolean bool_20_, long groupKey, boolean bool_22_)**

### Relações

`Class121.anInt1797`, `Class190.anIntArray2552`, `Class239_Sub12.anInt5973`, `Class275.method2064`, `Class281.anInt3647`, `Class286_Sub4.anInt6246`, `Class305.anInt3855`, `Class316`

---

