# Local scene editor

Renderer-independent model + live placer for a **device-local** object scene
editor. Scenes never modify the JS5 cache and never send packets to the server.

## Layers

| Piece | Role |
|---|---|
| `Scene` / `SceneObject` | Versioned model (JSON) |
| `SceneStore` | Atomic save under `user.home/void-scenes` (+ `.bak`) |
| `SceneEditor` | Validated commands + undo/redo |
| `SceneObjectAdapter` | Places/removes LocTypes via `SceneManager.method1591` |
| `LiveSceneBridge` | Resyncs model ↔ live tile graph |
| `SceneEditorHost` | Console / Microbot entry point |

## In-game usage

1. Log in, open the developer console (`` ` ``).
2. `ed` — print help + status.
3. `ed mode editor` **or** Microbot panel → **Editor: ON**.
4. `spawn 1276` / `ed spawn 1276` — place LocType at your tile.
5. `ed save demo` / `ed load demo` / `ed undo` / `ed clear`.

Supported console surface:

```text
ed mode [editor|game]
ed spawn <objectId>
ed add <objectId> <x> <y> <z> <plane>
ed move <localId> <x> <y> <z>
ed rotate <localId> <0-3>
ed scale <localId> <scale>   # stored only
ed remove <localId>
ed undo | redo | clear | apply | status
ed save <name> | load <name>
```

## Limits (this PR)

- Default place type is scenery (`type=10`). Walls/decor need follow-up.
- Terrain height is used; free `z` is stored but not applied.
- Per-instance `scale` is stored but not applied (LocType default scale).
- Objects vanish on region reload until `ed apply` / `ed load`.
- No mouse pick / drag yet — console + Microbot toggle only.

## Offline demo

```bash
export JAVA_HOME="$HOME/.jdks/jdk-17.0.20.1+1/Contents/Home"
./gradlew :client:compileJava
java -cp client/build/classes/java/main SceneEditorDemo
```

## Build

```bash
./gradlew :client:compileJava
```
