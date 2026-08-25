---
name: run-mobile-device
description: >-
  Build, install, and launch Void OSRS on a physical iPad or Android phone.
  Use when the user says restart, deploy, run on device, iPad, iOS device,
  make android, install, or relaunch the mobile client.
---

# Run Void on device

Game server must already be up on **TCP 43594**.

This skill lives in the **void-client** repo. Run scripts from `void-client/` (this directory).

**Always execute the scripts** (do not paste gradle/signing flags by hand):

| Device | Command | `block_until_ms` |
|--------|---------|------------------|
| iPad | `bash .cursor/skills/run-mobile-device/scripts/ios-device.sh` | `240000` |
| Android USB | `bash .cursor/skills/run-mobile-device/scripts/android-device.sh` | `180000` |

When the user says **restart** / **deploy** without a platform:

1. Just edited `ios/` or `GameController.java` → iPad script.
2. Just edited `android/` or `MainActivity.java` → Android script.
3. Else: iPad if `xcrun devicectl list devices` shows a physical iPad `available`; else Android if `adb devices` has `device`. If both, iPad.

Do **not** use `make ios` for a physical iPad (Simulator only).

## iOS iPad

The script: checks arm64 JDK 17 → `launchIOSDevice` with signing → **`devicectl install` + `launch`**.

RoboVM always prints `AppLauncher failed ... NumberFormatException: 18446744071562067970` after **BUILD SUCCESSFUL**. That is success. The script still installs `ios/build/robovm.tmp/Void.app`.

Defaults (override with env):

| Env | Default |
|-----|---------|
| `JAVA_17` | `$HOME/.jdks/jdk-17.0.20.1+1/Contents/Home` |
| `IOS_UDID` | `00008120-000E7D8830214932` (hardware UDID, **not** the `Identifier` from `devicectl list`) |
| `IOS_SIGN_IDENTITY` | `Apple Development: Matheus Gois (885DD2XQL9)` |
| `IOS_PROVISIONING_PROFILE` | `bdd4f76c-78f2-493c-b2e0-66f72e688f77` |

If install fails, re-query UDID:

```bash
xcrun devicectl list devices
xcrun devicectl device info details --device iPad   # Hardware.UDID
```

Signing rediscovery: `security find-identity -v -p codesigning`. Team `CTAFH43K36`.

Bundle id: `world.gregs.voidosrs.ios`.

Simulator only if asked: `make ios` / `make ios-relaunch`.

## Android USB

The script refuses to hang if `adb devices` is empty. Then `make android` (installDebug + reverse `:43594` + launch).

- Package `world.gregs.voidosrs.android` / `.MainActivity`
- USB reverse → phone uses host `127.0.0.1:43594`
- LAN: `make android-server SERVER_IP=<lan-ip>`
- Logs: `make android-log`
- Already installed: `make android-run`

## Server host

Saved in `{user.home}/void-server.txt` (in-app picker). Do not change unless asked.

Overrides: `-Dvoid.server=<host>` (host only, no `http://` / port). Android also `adb shell setprop debug.void.server <host>` then relaunch.
