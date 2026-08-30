# Concrete findings (session discovery)

This file is the live **research log** — concrete evidence discovered during
the deobfuscation discovery pass. Use it as the input for lote planning.

Last updated: 2026-08-30 (lote 59 executed; lote 51/54 marked done).

---

## 1. lote 50 — `method3` island (CONFIRMED)

**Interface:** `client/toolkit/base/d.java` (single-letter JODE name — deep obfuscation)
**Implementer:** `client/components/Component283.java` (formerly `Class244`)
**Field type on the toolkit:** `aD####` (e.g. `aD4579`, `aD5684`)

**Method map (the interface has 6 methods, all in low-numbered `method1`–`method6`):**

| Interface method | Signature | Returns | Inferred name | Evidence |
|---|---|---|---|---|
| `method1` | `(int, float, boolean, int, int, int)` | `float[]` | `getVertices` | pattern `0.7F` = scale, returns vertex array |
| `method2` | `(boolean)` | `int` | `getModelCount` | returns `anInt4625` (constructor-loaded count) |
| `method3` | `(int id, int magic)` | `Component319` | `getModel` | 67 call-sites, all `id & 0xffff`, magic arg always `-6662` |
| `method4` | `(int, int)` | `boolean` | `isModelLoaded` | gate check before fetching model |
| `method5` | `(boolean, int, float, int, int, int)` | `int[]` | `getTriangles` | texture/material lookup |
| `method6` | `(int, int, float, int, boolean, int)` | `int[]` | `getIndices` | vertex index list |

**Component319 (formerly `Class12`)** is the **Model / Renderable** struct.
Fields `aBoolean207` (loaded), `aBoolean209` (visible?), etc.

**Reflection:** zero — `method3` family is not touched by `AwtHost` or `ServerPrefs`. Safe rename without host sync.

**Suggested full rename (one lote):**
- `d` → `ModelProvider`
- `Component283` → `ModelStore` (or keep `ModelProviderImpl`)
- `Component319` → `Model`
- `aD####` → `modelProvider`
- `method1`–`method6` as above

**Lote size:** ~80 method refs + ~15 field refs + 1 class rename.

---

## 2. lote 51 — CS2 interpreter stacks (CONFIRMED)

`ClientScriptExecutor.java` (formerly `Class66`) is the **CS2 (clientscript)
interpreter** with three stacks. The `anInt1170`/`anInt1173`/`anInt1154`
cluster is the **stack-machine state** of the interpreter.

| Field | Stack it points into | Inferred name | Refs |
|---|---|---|---:|
| `anIntArray1149` (int[1000]) | int stack (data) | `intStack` | — |
| `aStringArray1152` (String[1000]) | string stack (data) | `stringStack` | — |
| `aClass184Array1168` (Component357[]) | call frames | `callFrames` | — |
| **`anInt1173`** | `intStack` | **`intStackPointer`** | **1 238** |
| **`anInt1170`** | `stringStack` | **`stringStackPointer`** | **208** |
| **`anInt1154`** | `callFrames` | **`callFramePointer`** | (low refs, but load-bearing) |

**Evidence:** line 4920 `anInt1173 = 0;` at the start of `execute()` (reset
all three). Lines 4907, 4909, 4933, 4943: textbook `anIntArray1149[anInt1173++]`
(push) and `anInt1173 -= 2` (pop 2) patterns.

**Reflection:** zero. Safe to rename.

**Lote size:** ~1 240 + 200 + small = ~1 450 ref renames. Single file.

**Bonus:** in the same lote, also rename the stack arrays:
`anIntArray1149` → `intStack`, `aStringArray1152` → `stringStack`,
`aClass184Array1168` → `callFrames`, and the dispatcher fields
(`aStringArray1155`, `aStringArray1176`, `anIntArray1175`).

---

## 3. Top method#### resolved (5 of top-6) — DONE 2026-08-29

| Token | Defining class | Renamed to | Evidence |
|---|---|---|---|
| `method3429` (149→0) | `NodeSub51` (Preferences) | `applyPreference` | calls `method1718` + `method3426` commit |
| `method3771` (104→0) | `GlToolkitSub2` | `bindTexture` | `glEnable/glDisable/glBindTexture` |
| `method2436` (104→0) | `DisplayModeManagerContainer58` + `Player` | `getSize` | returns tile footprint; Player overrides via appearance |
| `method3243` (103→0) | `HashNodeSub14` | `enqueueOutboundPacket` | queues write buffer; **not** particles |
| `method2148` (100→0) | `ParticleShader` | `createOutboundPacket` | factory + `"Encode packet"` log |

Also renamed `anInt10280` → `size` on the entity base.

**Correction vs earlier draft:** `method2148`/`method3243` are outbound **packet** helpers (type still named `ParticleSystem`). Do not call them particle FX.

---

## 3b. CS2 stacks — DONE 2026-08-29 (lote 51)

| Old | New |
|---|---|
| `anIntArray1149` | `intStack` |
| `aStringArray1152` | `stringStack` |
| `aClass184Array1168` | `callFrames` |
| `anInt1173` | `intStackPointer` |
| `anInt1170` | `stringStackPointer` |
| `anInt1154` | `callFramePointer` |

Reflection: none. Desktop + iOS compile green after lote.

---

## 4. `SpriteSub3` family — rasterizer (NEW ISLAND)

`client/sprites/SpriteSub3.java` (formerly `Class105_Sub3`) extends
`Component24` (Sprite base). **This is the CPU rasterizer for sprites** —
the biggest hot path in 2D rendering.

| Field | Inferred name | Evidence |
|---|---|---|
| `anInt8477` (319 refs) | `spriteAlpha` | `i_35_ >>> 24` (extract alpha from ARGB), `== 255` (opaque gate) |
| `anInt8471` (235 refs) | `spriteWidth` | instance field set from `i` arg; used in stride calc |
| `anInt8450`, `anInt8481` | `scanlineStart*` | `(f - f) * 4096F * anInt8470 / f` (fixed-point) |
| `anInt8451`, `anInt8453` | `scanlineAdvance*` | `+= anInt8453` in pixel-stepping loops |
| `aHa_Sub1_8460` | `toolkit` | `GlToolkitSub1` reference |

The whole **84xx range** is `SpriteSub3`'s fields. The **sub-files**
(`SpriteSub3Sub2`, `SpriteSub3Sub3`, `SpriteCapture`) inherit them all.
~200+ anInt tokens can be renamed in a single targeted lote, with high
confidence because the role is well-known (rasterizer scanline state).

**Reflection:** none directly. (Note: `GlToolkitSub2.anInt7666` is
reflective and is the toolkit's canvasHeight, not a SpriteSub3 field.)

---

## 5. Reflection bridge — 10 fields still obfuscated

The reflection gate currently tracks 25 members. **10 are still in
`anInt####` form** — these are the display-state mirrors that the mobile
host writes when the canvas resizes (via `ScreenModeManager`).

| Field | Role | Suggested name | Refs |
|---|---|---|---:|
| `SocketConnector.anInt3473` | **source** — canvas width | `canvasWidth` | low (just a holder) |
| `NpcNode.anInt6857` | **source** — canvas height | `canvasHeight` | low |
| `Component236.anInt4017` | mirror → `SocketConnector.anInt3473` | `canvasWidth` | low |
| `DisplayModeManagerContainer147.anInt4167` | reset to 0 between draw calls | `spriteBatchReset` (?) | low |
| `DisplayModeManagerContainer23.anInt1524` | init `= 765` → `= canvasWidth` | `defaultDrawDistance` (or `canvasWidth`) | low |
| `DisplayModeManagerContainer295.anInt5911` | mirror → `canvasWidth` | `canvasWidth` | low |
| `GlToolkitSub2.anInt7666` | mirror → `canvasHeight` | `canvasHeight` | low |
| `InputHandler.anInt4276` | mirror → `canvasWidth` | `canvasWidth` | low |
| `NodeSub48.anInt7129` | reset to 0 between frames | (per-frame reset) | low |
| `PacketReader.anInt10432` | mirror → `canvasHeight` | `canvasHeight` | low |

**Pattern:** `ScreenModeManager.applyMode()` writes `container.getSize().width`
→ `SocketConnector.anInt3473`, and that cascades into all the other holders.
`NpcNode.anInt6857` similarly from `height`. Then `OpenGlShader` /
`SpriteAtlasShader` copy the values into per-frame fields that get reset.

**Important:** when these get renamed, the **mobile host** (which currently
sends the obfuscated string literals) must move to the new names. The
reflection gate (`check_reflection.py`) will catch any miss.

The `EXPECTED` table in the script will need updating as the renames land:
replace `(field, "SocketConnector", "anInt3473")` with
`(field, "SocketConnector", "canvasWidth")`, etc.

---

## 5b. lote 52 — `SpriteSub3` rasterizer (CONFIRMED, executed)

**File:** `client/sprites/SpriteSub3.java` + subclasses `SpriteSub3Sub2`,
`SpriteSub3Sub3`, `SpriteCapture`. Also `client/components/Component297.java`
(constructor reads `spriteWidth`/`spriteHeight`).

This is the **CPU sprite rasterizer** — a scanline alpha-blend loop. Proof
(grep on the 84xx fields in `SpriteSub3.java`):

| Token | New name | Evidence |
|---|---|---|
| `anInt8477` | `spriteAlpha` | `int i = argb >>> 24;` then `if (i == 255) { opaque fast path }` — high byte = alpha |
| `anInt8471` | `spriteWidth` | bound to the drawn width (`i` arg), used in stride `i_3_ * anInt8471` |
| `anInt8470` | `spriteHeight` | `anInt8464 + anInt8470 + anInt8456` (top+height+bottom) |
| `anInt8450` | `scanlineX` | `(int) ((f_61_ - f_59_) * 4096.0F * (float) anInt8470 / f_84_)` fixed-point scanline coord |
| `anInt8481` | `scanlineY` | same shape as `scanlineX`, paired in the rasterizer step |
| `anInt8451` | `scanlineStepX` | `anInt8451 += anInt8453` per-pixel advance |
| `anInt8453` | `scanlineStepY` | the step added into `scanlineStepX` each pixel |
| `aHa_Sub1_8460` | `toolkit` | declared `GlToolkitSub1 aHa_Sub1_8460;` — the toolkit used for canvas-width math |

**Reflection:** none. **Gate result:** `check_reflection.py` PASS,
`:client:compileJava` SUCCESS (2026-08-30). `Component297.java` was the
only external reader and was updated in the same change.

**Deferred (lote 52b):** `anInt8480`, `anInt8469`, `anInt8463`, `anInt8465`
and the `anInt8452`/`anInt8454`–`anInt8459`/`anInt8461`/`anInt8466`–`anInt8468`
range — role not yet proven strongly enough to rename blindly.

---

## 6. `Component192` — already half-named utility (RECORDED)

`Component192.java` (formerly `Class316`) is the **menu/console/bezier
utility** class. Header comment from prior lote already names:

- `menuTip` (left-click tip)
- `openDevConsole`
- `drawBezier`
- `formatMenuEntry`
- `lookup` (open-addressed hash)
- `clearStatics`, `clearSoftCache`

The field `aClass348_Sub51_3959` is a `NodeSub51` (Preferences) reference —
rename to `preferences`. `NodeSub51` itself (formerly `Class348_Sub51`)
holds the 30+ `aClass239_SubN_NNNN` sub-preference fields.

---

## 7. Lote 59 — `Component182` float writers (CONFIRMED, executed)

`Component182` is a `Buffer` subclass. Its paired methods serialize an IEEE-754
float into the buffer using opposite byte orders:

| Old token | New name | Evidence |
|---|---|---|
| `method3399` | `writeFloatLE` | `floatToRawIntBits`, then bytes are emitted least-significant first |
| `method3400` | `writeFloatBE` | `floatToRawIntBits`, then bytes are emitted most-significant first |

Both methods have one definition and their call sites are limited to
`Component182` users. No reflection strings reference them. This resolves
160 call-site tokens without changing wire behavior.

## 8. Unresolved tokens (need more work)

These remain TBD from the top-tokens list:

- `method3494`, `method3493`, `method3738`, `method3850`, `method3849`
  (high call-site counts, not yet investigated)
- `method835` (69 refs; defining class and role still need confirmation)
- `anInt3138` (174 refs, 3xxx range — probably particle count or scan counter)
- `anInt4592`, `anInt4588` (4xxx range, possibly NPC list / entity array)
- `anInt9139`, `anInt8983` (9xxx range — usually widgets / interfaces)

**Next session** can pick any of these. Recommended order based on fan-out:
`anInt3138` → `anInt4592` → `method3493/3494` (likely paired).

---

## 9. lote 50 executed — interface name `d` DEFERRED (IMPORTANT)

lote 50 renamed `method1-6`→`getVertices/getModelCount/getModel/isModelLoaded/
getTriangles/getIndices`, `aD####`→`modelProvider`, `Component319`→`Model`,
`Component283`→`ModelStore`. The interface itself (`client/toolkit/base/d.java`)
keeps the name `d`.

**Do NOT rename `d`→`ModelProvider` by regex.** A single-letter identifier
collides with hundreds of local `d` variables and with string literals
(`FriendsIgnoreList` FR/ES text, `ReflectionInvoker` IP regex `"\\d+..."`,
`Loader` `-d` flag). A blanket or "type-scoped" regex rewrite breaks the
build. Rename `d` only with a real IDE refactoring tool in a dedicated pass.
