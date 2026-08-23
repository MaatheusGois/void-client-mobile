# Void Client

Deobfuscated 634 (2010-12-14) client — desktop JVM, plus Android and iOS ports of the same software renderer.

Architecture / maintenance: **[ARCHITECTURE.md](ARCHITECTURE.md)**.

https://github.com/user-attachments/assets/10381f43-aba1-4b22-b725-282112065ff2

## Server

Clients expect a [Void](https://github.com/GregHib/void) (or compatible) game server on **TCP 43594** (JS5 + login). Run that separately, then point each client at the host IP (defaults below).

```bash
make help
```

---

## Desktop

```bash
make desktop              # ./gradlew :client:run → 127.0.0.1:43594
make desktop-jar          # shadow jar
make desktop-run SERVER_IP=192.168.1.10
# or: java -jar client/build/libs/void-client-1.2.0.jar --address 192.168.1.10
```

---

## Android

Needs JDK 17+ and Android SDK / `adb`.

```bash
make android              # installDebug + adb reverse + launch
make android-log
make android-server SERVER_IP=192.168.1.10   # setprop + relaunch
```

### Server IP

| Setup | Default | Override |
|-------|---------|----------|
| USB + `adb reverse` | `127.0.0.1` | usually none |
| Emulator | `10.0.2.2` | — |
| Device on LAN (no reverse) | probe then LAN fallback | `make android-server SERVER_IP=…` |

Also: `adb shell setprop debug.void.server <ip>` then force-stop / relaunch.

### Controls

Tap = left click · long-press = right click · pinch = zoom · drag = camera · tap login/chat field = soft keyboard.

More: [android/README.md](android/README.md).

---

## iOS

Needs Xcode and an **arm64** JDK 17 (`os.arch=aarch64`). Default path: `~/.jdks/jdk-17.0.20.1+1` (override with `JAVA_17=…`).

```bash
make ios                  # Simulator → 127.0.0.1:43594
make ios-relaunch         # after SpringBoard crash
```

### Server IP

| Setup | Default |
|-------|---------|
| Simulator | `127.0.0.1` |
| Physical device | LAN fallback — set `System.setProperty("void.server", "<ip>")` before boot, or change the default in `GameController` |

### Controls

Same as Android. Soft keyboard opens on text-field taps.

More: [ios/README.md](ios/README.md).
