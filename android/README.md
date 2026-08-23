# Void Client — Android

Software-rendered 634 client on ART. Architecture, rewrite pipeline, input, networking, JS5/display pitfalls: **[ARCHITECTURE.md](../ARCHITECTURE.md)**.

Physical device needs the game server on the Mac **and** USB reverse (LAN is usually firewalled):

```bash
cd void-client/android
./gradlew :app:installDebug
./scripts/adb-reverse.sh
adb logcat -s void-osrs:I
```

Emulator talks to the host as `10.0.2.2`. Touch: tap = left click, long-press = right click, pinch = wheel, two-finger drag = camera. Keyboard FAB (top-left) for login/chat.
