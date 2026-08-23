# Void Client

Deobfuscated 634 (2010-12-14) client — desktop JVM, plus Android and iOS ports of the same software renderer.

Mobile architecture / maintenance: **[ARCHITECTURE.md](ARCHITECTURE.md)**.

https://github.com/user-attachments/assets/10381f43-aba1-4b22-b725-282112065ff2

## Prerequisites

| Target | Needs |
|--------|--------|
| **Server** | [Void](https://github.com/GregHib/void) game process listening on **TCP 43594** (JS5 + login) |
| **Desktop** | JDK 21+ (32-bit JRE helps with old OpenGL/DirectX) |
| **Android** | Android SDK / `adb`, device or emulator, JDK 17+ for Gradle |
| **iOS** | Xcode, **arm64** JDK 17 (e.g. Temurin at `~/.jdks/jdk-17.0.20.1+1`), Simulator |

Start the server before any client:

```bash
# from the Void repo
./gradlew :game:run
# healthy: "[Main] - Void loaded in …ms" and port 43594 open
```

If you use the monorepo Makefile (`void-osrs/`):

```bash
make run          # server
make client       # desktop
make android      # install + adb reverse + launch
make ios          # Simulator (arm64 JDK 17)
```

---

## Desktop

```bash
cd void-client
./gradlew shadowJar
# jar → client/build/libs/void-client-1.2.0.jar

java -jar client/build/libs/void-client-1.2.0.jar
# OpenGL/DirectX issues → try a 32-bit JRE where available:
java -jar client/build/libs/void-client-1.2.0.jar -d32
```

Or run without packaging:

```bash
./gradlew :client:run
```

---

## Android

Package: `world.gregs.voidosrs.android`.

### Device (USB)

Mac Wi‑Fi usually blocks inbound `:43594`. Use **adb reverse** so the phone hits `127.0.0.1:43594` → host:

```bash
cd void-client/android
./gradlew :app:installDebug
./scripts/adb-reverse.sh          # or: adb reverse tcp:43594 tcp:43594
adb shell am start -n world.gregs.voidosrs.android/.MainActivity
adb logcat -s void-osrs:I
```

Re-run `adb-reverse.sh` after unplug/replug or `adb kill-server`.

### Emulator

Emulator reaches the host as `10.0.2.2` (no reverse needed for that path). Install/launch the same way; server must still be on the Mac.

### Controls

| Gesture | Action |
|---------|--------|
| Tap | Left click |
| Long-press | Right click |
| Pinch | Zoom (mouse wheel) |
| One-finger drag | Camera orbit |
| Tap login / chat text field | Soft keyboard (system IME) |

---

## iOS

Bundle: `world.gregs.voidosrs.ios`. MobiVM/RoboVM AOT — **JDK must be arm64** (`os.arch=aarch64`). An x86_64 Homebrew JDK makes RoboVM look for `x86_64-simulator` and fail on modern runtimes.

Simulator uses `127.0.0.1` (server on the Mac). Physical device uses LAN IP unless you set `void.server`.

```bash
export JAVA_HOME="$HOME/.jdks/jdk-17.0.20.1+1/Contents/Home"   # arm64 Temurin 17
export PATH="$JAVA_HOME/bin:$PATH"

cd void-client/ios
./gradlew --no-daemon launchIPhoneSimulator \
  -Probovm.arch=arm64 \
  -Probovm.device.name='iPhone 17 Pro'
```

`gradle.properties` already sets `robovm.arch=arm64` and the device name. First AOT is slow; later builds reuse `~/.robovm/cache`. Give Gradle **8GB** (`org.gradle.jvmargs=-Xmx8g`).

If the `.app` installs but SpringBoard crashes:

```bash
xcrun simctl install booted build/robovm.tmp/Void.app
xcrun simctl launch booted world.gregs.voidosrs.ios
```

### Controls

Same as Android: tap / long-press / pinch / drag. Soft keyboard opens when you tap an in-game text field (login, chat, etc.).

---

## Networking notes

- Port **43594** for JS5 + login (`Loader.modewhere=0` LIVE).
- Override server: `-Dvoid.server=<ip>` or `System.setProperty("void.server", …)`.
- Android device: prefer `127.0.0.1` via reverse, then LAN fallback.
- After `error_game_js5connect`, fix network/server and **force-stop + relaunch** the app (that error is terminal for the process).

Details and pitfalls: **[ARCHITECTURE.md](ARCHITECTURE.md)** · Android notes: [android/README.md](android/README.md) · iOS notes: [ios/README.md](ios/README.md).
