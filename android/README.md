# Void Client — Android

Software-rendered 634 client on ART. Architecture, rewrite pipeline, input, networking, JS5/display pitfalls: **[ARCHITECTURE.md](../ARCHITECTURE.md)**.

Root runbook (server + desktop + mobile): **[README.md](../README.md)**.

## Run

1. Start Void on the Mac (`./gradlew :game:run`) so **TCP 43594** is open.
2. Install and launch:

```bash
cd void-client/android
./gradlew :app:installDebug
./scripts/adb-reverse.sh          # physical USB device — maps phone :43594 → host
adb shell am start -n world.gregs.voidosrs.android/.MainActivity
adb logcat -s void-osrs:I
```

Emulator reaches the host as `10.0.2.2` (reverse optional for that path).

Healthy boot: `probe OK` / `boot server=…` / `Connect OK: …:43594`.

## Controls

| Gesture | Action |
|---------|--------|
| Tap | Left click |
| Long-press | Right click |
| Pinch | Zoom (wheel) |
| One-finger drag | Camera orbit |
| Tap login / chat field | Soft keyboard |

## Overrides

```bash
adb shell setprop debug.void.server 192.168.x.x
# or -Dvoid.server=… when launching the JVM host
```
