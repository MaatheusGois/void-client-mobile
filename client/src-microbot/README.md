# Microbot (void-client)

Bot runtime + HUD / mouse backends for the RS634 client.

**Why this folder, no `package microbot`?**  
The 634 sources live in the Java *unnamed* (default) package. A named package cannot
reference those types. So these files stay default-package but sit in a separate
Gradle source root for clarity.

`Rs2*` scripting helpers live next door in `src-rs2/` (same rule).

## Client source roots

| Dir | Contents |
|-----|----------|
| `src` | RS634 deob + toolkit short names (`ha`, `Class*`, …) |
| `src-input` | `MouseHandler`, `AwtMouseHandler`, `BasicMouseHandler` |
| `src-font` | `BitmapFont` + toolkit backends |
| `src-menu` | `MenuEntry`, `DefaultClickSwapper` |
| `src-void` | `LoginPrefs`, `MobileKeyboard` |
| `src-microbot` | bot runtime / panel / mouse backends |
| `src-rs2` | `Rs2*` scripting API |

Wired from:
- `client/build.gradle.kts` → `java.srcDirs(...)` lists every root above
- Android / iOS `prepareClientSources` merges all trees into `generated/client`
