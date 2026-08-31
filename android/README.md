# Android

Software-rendered 634 client on ART. The 667 source is audited but not yet a
runnable profile. Root runbook: **[README.md](../README.md)**.

```bash
make android
make android-server SERVER_IP=192.168.1.10
make android-log
```

Defaults: USB reverse → `127.0.0.1`; emulator → `10.0.2.2`; else probe / LAN fallback via `debug.void.server` / `void.server`. The working client uses TCP `43594` and accepts `debug.void.port`.

Generated client sources are copied by `prepareClientSources`; do not edit
`android/app/build/generated`. The 667 cache must remain in the
`runescape-667` namespace and requires a compatible server/cache pair. Setting
`debug.void.protocol=667` is intentionally rejected before the generated client
starts; do not use it as evidence of compatibility.
