# Local scene editor core

The `editor` source root contains the first, renderer-independent part of the
local object scene editor. `Scene` and `SceneObject` are the persisted model;
`SceneStore` writes versioned JSON below `user.home/void-scenes` using a
temporary file and a `.bak` predecessor; `SceneEditor` exposes validated,
deterministic commands and bounded undo/redo.

Supported commands are:

```text
add <objectId> <x> <y> <z> <plane>
move <localId> <x> <y> <z>
rotate <localId> <rotation>
scale <localId> <scale>
remove <localId>
undo
redo
save <name>
```

This layer is intentionally local-only and does not alter JS5 cache files or
send changes to the game server. The next integration step is an explicit
adapter that maps `SceneObject` values to renderable scene nodes; keeping that
adapter separate prevents malformed scene files or editor commands from
mutating the normal game mode.
