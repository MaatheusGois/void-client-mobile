# Microbot (void-client)

Bot runtime + `Rs2*` API for the RS634 client.

**Why this folder, no `package microbot`?**  
The 634 sources live in the Java *unnamed* (default) package. A named package cannot
reference those types. So these files stay default-package but sit in a separate
Gradle source root (`src-microbot`) for clarity.

Wired from:
- `client/build.gradle.kts` → `java.srcDirs("src", "src-microbot")`
- Android / iOS `prepareClientSources` merges both trees into `generated/client`
