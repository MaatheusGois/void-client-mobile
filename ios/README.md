# iOS

Same client as Android, AOT via MobiVM/RoboVM. Root runbook: **[README.md](../README.md)**.

Needs **arm64** JDK 17 (`make` checks `JAVA_17`, default `~/.jdks/jdk-17.0.20.1+1`).

```bash
make ios
make ios-relaunch
```

Simulator → `127.0.0.1`. Device → set `void.server` before boot or edit the default in `GameController`.
