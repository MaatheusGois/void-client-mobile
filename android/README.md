# Android

Software-rendered 634 client on ART, with the 667 migration profile available
for gated testing. Root runbook: **[README.md](../README.md)**.

```bash
make android
make android-server SERVER_IP=192.168.1.10
make android-log
```

Defaults: USB reverse → `127.0.0.1`; emulator → `10.0.2.2`; else probe / LAN fallback via `debug.void.server` / `void.server`. The host uses `void.port` and `void.protocol` for the same endpoint selection as the generated client (`43594` legacy, `443` for the opt-in 667 profile).

Generated client sources are copied by `prepareClientSources`; do not edit
`android/app/build/generated`. The 667 cache must remain in the
`runescape-667` namespace and requires a compatible server/cache pair. For a
device-side migration probe, set `debug.void.protocol` (and optionally
`debug.void.port`) with `adb shell setprop` before restarting the app.
