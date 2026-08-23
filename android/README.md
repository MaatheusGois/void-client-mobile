# Void Client — Android spike

Open this folder (`void-client/android`) in Android Studio.

Emulator talks to the host machine as `10.0.2.2` (already the default in `MainActivity`). Run the game server on the Mac first.

```bash
cd void-client/android
./gradlew :app:installDebug
```

Software renderer only. Touch = left click. Type in the bar at the bottom for login/chat. Two-finger / right-click is not wired yet.
