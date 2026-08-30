# Local scene editor

Device-local object scene editor: model + JSON persistence + **live placer** +
**in-game HUD** + **right-click edit**. Never writes JS5 and never sends packets.

Placed objects **stay in the world** when you turn the editor off. Only
`ed clear` / Remove clears them. Edits autosave to `~/void-scenes/autosave.json`
and restore on the next login.

## In-game

1. Microbot → **Editor: ON** (or `ed mode editor`).
2. Right panel **City Assets** — pick Tree / Bench / …
3. **Click** empty ground → place current asset.
4. **Right-click** any scenery → **Move** / **Remove** / **Rotate**.
5. **Ctrl+click** scenery → claim + drag (including stock world objects).
6. After **Move**: click a destination tile.
7. Turn **Editor: OFF** — objects remain. Named save: `ed save demo`.

## Persistence

| When | What happens |
|---|---|
| Place / move / rotate / remove | model updates + autosave |
| Editor OFF | objects stay live locally; autosave |
| `ed save <name>` (player) | local JSON only |
| `ed save <name>` (**admin**) | local JSON + `scene_place` each object + `scene_flush` → server GameObjects, `data/area/scene/editor.obj-spawns.toml`, JS5 `lX_Y` |
| Walk to new region | auto re-apply from local model |
| Next login | restore `autosave.json` and apply |

Chop / interact only work on **server** objects → turn Editor OFF after an admin save.
## Console

```text
ed mode editor|game
ed spawn 1276
ed apply
ed save demo
ed load demo
ed status
```

## Layers

| Piece | Role |
|---|---|
| `Scene` / `SceneObject` | Versioned model |
| `SceneStore` | `~/void-scenes/*.json` (+ `.bak`) |
| `SceneEditor` | Commands + undo/redo |
| `SceneObjectAdapter` / `LiveSceneBridge` | `SceneManager.method1591` sync |
| `SceneEditorHost` | Console / claim / tick (region + restore) |
| `SceneEditorUi` | Palette + click/drag HUD |
| `SceneEditorMenu` | Right-click Move/Remove/Rotate |

## Limits

- Scenery type 10; free `z` / scale stored only
- Stock objects you **Remover** without owning come back on region reload
- Palette LocType ids: `SceneEditorUi.ASSETS`

## Build

```bash
./gradlew :client:compileJava
make desktop-run SERVER_IP=…
```
