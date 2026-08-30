# Lote plan

A **lote** is a batch of 15–40 evidenced renames (plus the reflection
sync + comments) that ships green. This file sequences the next ones in
ROI order.

Workflow per lote (from skill):
1. Pick the island.
2. For each token: find defining class, pin a name from a call-site
   clue, run `check_defs.py` for uniqueness.
3. Rename whole-word outside string literals.
4. Sync reflection strings (both `AwtHost.java` + `ServerPrefs.java`) in
   the same change.
5. Comment class header + non-obvious members.
6. Run the verification gate (below).
7. Record to `lean-ctx` (`knowledge remember` / `session decision`).

Concrete evidence for each island is in `findings.md`.

---

## lote 50 — `ModelProvider` interface island  *(~80 method refs freed)*

**Scope:**
- `client/toolkit/base/d.java` → rename file to `ModelProvider.java`,
  rename class to `ModelProvider`.
- `client/components/Component283.java` → rename file to `ModelStore.java`
  (or `ModelProviderImpl.java`), rename class.
- `client/components/Component319.java` → rename file to `Model.java`,
  rename class (and the boolean fields inside).
- Rename all `aD####` fields of type `d` (interface) across the codebase
  to `modelProvider`. Hot spots:
  - `DisplayModeManagerContainer164.aD4579`
  - `DisplayModeManagerContainer190.aD4579`
  - `DisplayModeManagerContainer282.aD4579`
  - `DisplayModeManagerContainer50.MatrixSub1.aD5684`
- Rename all 6 methods on the interface:
  - `method1` → `getVertices` (2 refs)
  - `method2` → `getModelCount` (2 refs)
  - `method3` → `getModel` (67 refs)
  - `method4` → `isModelLoaded` (13 refs)
  - `method5` → `getTriangles` (8 refs)
  - `method6` → `getIndices` (6 refs)

**Reflection check:** none. No `AwtHost` / `ServerPrefs` string update
needed.

**Expected output:** ~80 method refs resolved, 1 class rename, 1 file
rename. Model/provider abstraction becomes readable.

---

## lote 51 — CS2 interpreter stacks  *(~1 450 refs freed, the biggest single win)*

**Scope** — all in `client/script/ClientScriptExecutor.java`:

| Field | New name | Refs |
|---|---|---:|
| `anInt1173` | `intStackPointer` | 1 238 |
| `anInt1170` | `stringStackPointer` | 208 |
| `anInt1154` | `callFramePointer` | (low, load-bearing) |
| `anIntArray1149` | `intStack` | many |
| `aStringArray1152` | `stringStack` | many |
| `aClass184Array1168` | `callFrames` | many |

**Reflection check:** none.

**Caveat:** this is **all in one file** (~5 200 lines). Keep the rename
mechanical — use a Python pass, don't try to be clever about magic
literals.

**Expected output:** the entire `execute()` interpreter becomes readable.
The `intStackPointer++ / intStack[ptr] = X` push/pop pattern will be
obvious at a glance.

---

## lote 52 — `SpriteSub3` rasterizer family  *(~1 200 refs freed)*

**Scope** — `client/sprites/SpriteSub3.java` and its subclasses
(`SpriteSub3Sub2`, `SpriteSub3Sub3`, `SpriteCapture`).

| Field | New name | Refs |
|---|---|---:|
| `anInt8477` | `spriteAlpha` | 319 |
| `anInt8471` | `spriteWidth` | 235 |
| `anInt8450` | `scanlineStartX` | 306 |
| `anInt8481` | `scanlineStartY` | 282 |
| `anInt8451` | `scanlineAdvanceX` | 137 |
| `anInt8453` | `scanlineAdvanceY` | (likely) |
| `anInt8480` | (per-frame color init) | 128 |
| `anInt8469`, `anInt8463`, `anInt8465` | (sprite counters) | ~110 each |
| `aHa_Sub1_8460` | `toolkit` | (sprite toolkit ref) |
| **all 84xx** `anInt####` | (SpriteSub3 fields) | 100s more |

This is the **whole SpriteSub3 rasterizer**, one well-known role
(scanline rasterizer with alpha blend). The "84xx" range is
essentially a single island.

**Reflection check:** none.

**Expected output:** the `SpriteCapture` (which is the inline rasterizer
loop) becomes understandable: alpha blend, scanline stepping, etc.

---

## lote 53 — Display-mode island  *(3 hot files, ~660 refs freed)*

**Files:**
- `client/misc/DisplayModeManagerContainer164.java`  (187 refs)
- `client/misc/DisplayModeManagerContainer190.java`  (263 refs)
- `client/misc/DisplayModeManagerContainer282.java`  (214 refs)

**Known reflective members** (do not break):
- `fullscreenAvailable`
- `getWindowMode`
- `GlToolkitSub2.anInt7666` (rename to `canvasHeight` here)

**Steps:**
1. Read all three files. They're in the same FSM family.
2. Find the `anInt` ranges that cluster in these files.
3. Rename per-file, do **not** cross-file rename unless `check_defs.py`
   confirms single definition.
4. Sync reflection for any field that `AwtHost` touches — the most
   important is `GlToolkitSub2.anInt7666` (already reflective).
5. Gate.

**Expected output:** fullscreen / window-mode readability for both
desktop and mobile.

---

## lote 54 — `GlToolkit*` renderer island  *(~1 200 refs freed)*

**Files:**
- `client/toolkit/base/GlToolkitSub3.java`  (602 refs)
- `client/toolkit/gl/GlToolkitSub2.java`    (573 refs)

**Risk:** high — these are the renderer, and `AwtHost` reads a few of
their fields for camera / viewport sync.

**Steps:**
1. **First** — read both `AwtHost.java` files end to end. Note every
   string the host reads from the GlToolkit family.
2. Find the OpenGL binding surface. Many methods are 1:1 with GL calls
   (`glBindBuffer`, `glDrawArrays`, etc.). Those are easy evidence.
3. Rename the GL-binding methods first (low risk, high count). Start
   with the **already-resolved** ones:
   - `method3771` → `setTextureUnit` (104 refs)
4. Then tackle the state fields (`aBoolean` / `anInt` / `aClass` of GL
   handles). Cross-check reflection for each.
5. Gate.

**Expected output:** the entire renderer becomes readable; the camera
and viewport fix in `AwtHost` becomes maintainable.

---

## lote 55 — `PacketReader`  *(202 refs, server protocol)*

**File:** `client/net/packet/PacketReader.java` (+ `Buffer.java`)

**Why this is safe:** server-driven, not reflective, and opcode
dispatcher pattern is well-known (opcodes in `Buffer.read*` shapes).

**Steps:**
1. Read `Buffer.java` first to lock the wire format.
2. Walk `PacketReader.method####` in source order. Many will be obvious
   from opcode (region rebuild = `if (opcode == SOME_CONST) {…}`).
3. Rename per-method. No reflection sync needed.
4. Gate.

**Expected output:** server protocol becomes traceable; useful for any
future protocol work.

---

## lote 56 — Display-state mirror cleanup  *(reflection sync required)*

**Scope:** rename the 10 reflective `anInt####` fields listed in
`findings.md §5` to their English names. Critical: this lote **must
update the mobile hosts**.

| Field | New name | Host string to update |
|---|---|---|
| `SocketConnector.anInt3473` | `canvasWidth` | both `AwtHost.java` |
| `NpcNode.anInt6857` | `canvasHeight` | both `AwtHost.java` |
| `Component236.anInt4017` | `canvasWidth` | both `AwtHost.java` |
| `DisplayModeManagerContainer295.anInt5911` | `canvasWidth` | both `AwtHost.java` |
| `InputHandler.anInt4276` | `canvasWidth` | both `AwtHost.java` |
| `GlToolkitSub2.anInt7666` | `canvasHeight` | both `AwtHost.java` |
| `PacketReader.anInt10432` | `canvasHeight` | both `AwtHost.java` |
| `DisplayModeManagerContainer147.anInt4167` | (per-draw reset) | both `AwtHost.java` |
| `NodeSub48.anInt7129` | (per-frame reset) | both `AwtHost.java` |
| `DisplayModeManagerContainer23.anInt1524` | `canvasWidth` (or `defaultDrawDistance`) | both `AwtHost.java` |

**Critical:** also update the `EXPECTED` table in
`.cursor/skills/void-client-deobfuscate/scripts/check_reflection.py` to
match the new names. Otherwise the gate will FAIL.

**Expected output:** the canvas-resize reflective bridge becomes
self-documenting. Future renames in the same area are easy.

---

## lote 57 — `ClientScriptExecutor` opcode handlers  *(~490 refs freed)*

**File:** `client/script/ClientScriptExecutor.java` (continued from lote 51)

**Risk:** CS2 touches every subsystem, so renames cascade. Keep this
lote small (15 renames max) and re-run the gate after each batch.

**Steps:**
1. The first ~50 lines are the opcode dispatcher. Read them first.
2. Opcode handlers are usually named after the script's job. Look for
   string literals (`"give"`, `"teleport"`, etc.) or bit-flag patterns.
3. Rename per-handler. Do **not** rename the dispatcher itself.
4. Gate.

---

## lote 58+ — Long tail

After the above, the high-fan-out tokens are gone. The remaining
`method####` are mostly unique-per-class and safe to rename file-by-file
without a global pass.

**Cadence:** 1 lote per session, run the gate at the end of every lote,
record progress in `lean-ctx` for continuity.

---

## Verification gate (every lote)

```bash
python3 .cursor/skills/void-client-deobfuscate/scripts/check_reflection.py
./gradlew :client:compileJava
(cd ios && ./gradlew compileJava)              # use `clean compileJava` after class renames
python3 .cursor/skills/void-client-deobfuscate/scripts/count_methods.py
```

**Done definition:** `count_methods.py` reports < 100 unique
`method####`. Current: **3 331**. Target after lote 50: < 3 250.
After lote 51: < 2 100. After lote 52: < 900.
