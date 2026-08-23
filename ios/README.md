# Void Client — iOS

Same software-rendered Java client as Android, AOT-compiled with MobiVM (RoboVM). Full architecture, RoboVM patches, JDK, and maintenance: **[ARCHITECTURE.md](../ARCHITECTURE.md)**.

Simulator talks to the host as `127.0.0.1`. Run the game server on the Mac first. Physical device uses the LAN IP unless you set `void.server`.

```bash
export JAVA_HOME="$HOME/.jdks/jdk-17.0.20.1+1/Contents/Home"   # must be arm64
cd void-client/ios
./gradlew --no-daemon launchIPhoneSimulator
```

Needs Xcode. Touch: tap = left click, long-press = right click, pinch = zoom, two-finger drag = orbit. Keyboard ball (top-left) for login/chat.
