# Local scene editor

Device-local object scene editor: model + JSON persistence + **live placer** +
**in-game HUD** (asset palette, click/drag, action menu). Never writes JS5 and
never sends packets.

## In-game (what you asked for)

1. Microbot → **Editor: ON** (or console `ed mode editor`).
2. Right panel **City Assets** — pick Tree / Bench / …
3. **Click** empty ground → place current asset.
4. **Click** your object → select (magenta box); **drag** → move (orange trail).
5. Bottom **action menu**: `[+] Add` / `[-] Remove`.
6. Chat: `[System] Scene editor active…`

Also still works from console: `ed spawn 1276`, `ed save demo`, etc.

## Layers

| Piece | Role |
|---|---|
| `Scene` / `SceneObject` | Versioned model |
| `SceneStore` | `~/void-scenes/*.json` (+ `.bak`) |
| `SceneEditor` | Commands + undo/redo |
| `SceneObjectAdapter` / `LiveSceneBridge` | `SceneManager.method1591` sync |
| `SceneEditorHost` | Console / Microbot entry |
| `SceneEditorUi` | Palette + click/drag HUD |

Tile under cursor comes from the existing Walk-here tip (opcode 19).

## Limits

- Scenery type 10; free `z` / scale stored only
- Region reload → `ed apply` or re-enable editor
- Palette LocType ids are a starting set (edit `SceneEditorUi.ASSETS`)
- Selection box is a 2D projection overlay, not a true 3D highlight

## Build

```bash
./gradlew :client:compileJava
# or
make desktop-run SERVER_IP=…
```
