# Void Client — iOS

Same software-rendered Java client as Android, AOT-compiled with MobiVM (RoboVM). Architecture, RoboVM patches, JDK, maintenance: **[ARCHITECTURE.md](../ARCHITECTURE.md)**.

Root runbook: **[README.md](../README.md)**.

## Run (Simulator)

1. Start Void on the Mac (`./gradlew :game:run`) — Simulator uses `127.0.0.1:43594`.
2. Use an **arm64** JDK 17 (x86_64 Homebrew JDKs break modern Simulator):

```bash
export JAVA_HOME="$HOME/.jdks/jdk-17.0.20.1+1/Contents/Home"
export PATH="$JAVA_HOME/bin:$PATH"
# confirm: java -XshowSettings:properties -version 2>&1 | grep os.arch  → aarch64

cd void-client/ios
./gradlew --no-daemon launchIPhoneSimulator \
  -Probovm.arch=arm64 \
  -Probovm.device.name='iPhone 17 Pro'
```

Needs Xcode. First AOT is slow; later runs reuse `~/.robovm/cache`.

Physical device uses the LAN IP unless you set `void.server`.

### SpringBoard crash after install

```bash
xcrun simctl install booted build/robovm.tmp/Void.app
xcrun simctl launch booted world.gregs.voidosrs.ios
```


## Controls

| Gesture | Action |
|---------|--------|
| Tap | Left click |
| Long-press | Right click |
| Pinch | Zoom |
| Two-finger / one-finger drag | Camera |
| Tap login / chat field | Soft keyboard |
