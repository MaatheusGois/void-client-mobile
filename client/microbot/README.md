# Microbot (void-client)

Bot runtime + HUD / mouse backends for the RS634 client.

**Why this folder, no `package microbot`?**  
The 634 sources live in the Java *unnamed* (default) package. A named package cannot
reference those types. So these files stay default-package but sit in a separate
Gradle source root for clarity.

`Rs2*` scripting helpers live next door in `rs2/` (same rule).

## Client source roots

Domain folders next to the 634 core — same relationships as in the original client
(fonts / input / menu subsystems), plus Void host extras and the automation layer:

| Dir | Contents |
|-----|----------|
| `src` | RS634 deob core + toolkit short names (`ha`, …) |
| `fonts` | `BitmapFont` + software / OpenGL / jaclib backends |
| `input` | `MouseHandler`, `AwtMouseHandler`, `BasicMouseHandler` |
| `menu` | `MenuEntry`, `DefaultClickSwapper` |
| `void` | `LoginPrefs`, `MobileKeyboard` |
| `microbot` | bot runtime / panel / mouse backends |
| `rs2` | `Rs2*` scripting API (used by microbot) |

Wired from:
- `client/build.gradle.kts` → `java.srcDirs(...)` lists every root above
- Android / iOS `prepareClientSources` merges all trees into `generated/client`
