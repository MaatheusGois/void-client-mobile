# RuneScape 634 → 667 compatibility matrix

This is the pinned audit and migration checklist for the 667 work. It is not a
claim that the two clients are wire-compatible. The current source remains the
working 634 implementation; the incomplete 667 target is rejected before
networking until the core and protocol ports below are complete.

## Source decision

| Item | Decision |
|---|---|
| Source | [`StrongHold/runescape-667`](https://github.com/StrongHold/runescape-667) |
| Pinned commit | `f45c5e8a9e3369a7aeb11b68510fd00d4445d683` |
| Contents found | Java client, loader, JS5/cache artifacts, protocol documentation |
| Contents not found | Server module |
| License | No root `LICENSE` was identified; obtain a redistribution/legal decision before importing code or binary cache data |
| Compatibility consequence | Supply or upgrade a separately licensed 667-compatible server |

The machine-readable form of this decision is
[`667-source-manifest.json`](667-source-manifest.json). The 634 branch/commit
remains the rollback reference; do not replace it with a mixed 634/667 source
set.

## Baseline and profile controls

- Canonical client sources: `client/`, Java 8, unnamed package.
- Android source preparation: `android/app/build.gradle.kts`.
- iOS/tvOS source preparation: `ios/build.gradle`.
- Mobile hosts: `android/app/src/main/java` and `ios/src/main/java`.
- Native stubs: `android/scripts/gen_stubs.py`.
- Current software renderer: `toolkit/software` presented by `voidawt.AwtHost`.
- Current legacy endpoint: TCP `43594` for JS5 and login.
- Version selection is centralized in `client/src/ProtocolInfo.java`.
- `634` is the only runnable profile while the migration is incomplete.
  `-Dvoid.protocol=667` (or desktop `--protocol 667`) fails closed before
  networking.
- `-Dvoid.port=<1..65535>` overrides the compatible 634 server endpoint. The
  audited source's live port is not a server contract for this client.
- The working 634 cache keeps the historical `runescape` namespace. A
  completed 667 port must use `runescape-667` under the existing
  `.jagex_cache_<id>` / `.file_store_<id>` roots.

## Difference inventory

| Area | Local 634 responsibility | Pinned 667 responsibility/difference | Risk | Required action | Status |
|---|---|---|---|---|---|
| Bootstrap | `client/src/Loader.java`, `client/src/client.java`; applet parameters and unnamed-package bootstrap | `loader/` and packaged `com.jagex` client; different startup/build model | High: duplicate classes cannot share a source set | Port the core behind the existing Void hosts, or isolate the packaged core in a separate source set; do not globally rename `634` | Profile/configuration added; core pending |
| Revision/build | JS5/startup/login values in `client.java`, `TheoraVideoPlayer.java`, and console `BuildInfo` | Revision 667 and 667 build metadata | High: a revision literal does not port packet layouts | Keep the runnable profile at 634 until every revision-bearing field is ported with the server | 667 selection rejected; core pending |
| JS5 handshake | `components/Component253.java` and loading state machine | 667 handshake/endpoint behavior and JS5 artifacts in `runescape/` / `lib/` | Critical: wrong handshake can look like a network failure | Port handshake bytes, retry states, XOR and response codes from the compatible server | Pending |
| JS5 indexes/groups | `LoadingManager`, `CacheFileStore`, `Component219` load indexes 13/33/34 | 667 index/group IDs and named-file layout | Critical: stale 634 groups can decode as valid data | Import 667 index map and validate cold/warm cache plus named files | Cache namespace isolated; map pending |
| Reference table | `client/cache/ReferenceTable.java`, protocols 5–6, CRC/Whirlpool/version checks | 667 reference-table protocol and index revisions | Critical: parser can accept the wrong metadata | Compare parser byte-for-byte and add fixtures from the pinned cache | Pending |
| Compression/checksums | Cache inflate, CRC, Whirlpool, master-index RSA | 667 compression and master-index rules | High: corrupted or incompatible assets | Port formats and keep rejection/error telemetry | Existing checks; 667 validation pending |
| Login crypto | `net/crypto`, `Buffer`, ISAAC/XTEA and login block in `client`/`TheoraVideoPlayer` | 667 RSA/block sizes/order and server keys | Critical: old RSA keys or lengths cannot authenticate | Obtain documented 667 server keys; port block layout and packet sizes together | Pending; current keys retained |
| Gameplay packets | `net/packet`, `PacketReader`, component opcode handlers | 667 inbound/outbound opcodes and entity/update masks | Critical: login success does not imply gameplay compatibility | Port packet definitions before `rs2/` or microbot | Pending |
| Definitions/assets | `defs/`, sprites, maps, models, animations and current widget IDs | 667 cache definitions and CS2 scripts | High: IDs and binary decoders may shift | Generate a new cache/widget inventory and port decoders | Pending |
| Interfaces/widgets | `components/`, `widget-map/`, login/inventory/bank/chat/minimap IDs | 667 interface trees and script IDs | High: stale IDs can produce silent UI corruption | Remap only after 667 cache boot is stable | Pending |
| Renderer | `toolkit/*`, software `ha_*`, fonts, sprites, textures and scene graph | 667 toolkit contracts and asset formats | High: software path must remain available on mobile | Compare toolkit contracts; preserve `AwtHost` framebuffer and native fallback | Pending |
| Native/JNI | `native/` plus generated `jaggl`, `jagdx`, `jaclib`, `jagtheora`, `jagex3` stubs | New/changed native names and methods in the 667 client | High on RoboVM/ART | Regenerate stubs, update force-link/reflection tables, fail closed to software | Pending |
| Android | `prepareClientSources`, `MainActivity`, `voidawt`, `voidsound` | Packaged Java 11 core and possible API changes | High: flattening cannot preserve named-package assumptions | Decide core strategy first; keep generated output out of source edits | Port gate pending |
| iOS/tvOS | `prepareSharedSources`, RoboVM force links, UIKit/CoreText/AudioQueue hosts | New classes/reflection/invokedynamic requirements | High: AOT/linker failures can be platform-only | Update source exclusions and `forceLinkClasses`; run clean builds after renames | Port gate pending |
| Audio/video | `media/audio`, `media/ogg`, `TheoraVideoPlayer`, mobile `voidsound` | 667 codec/native contracts and login revision fields | Medium/High: missing codecs can block boot or mute game | Keep software/audio fallback; validate title music and effects separately | Pending |
| Server integration | Void-compatible TCP 43594 server assumption | External repository has no server | Critical: no server can be inferred from client code | Pin a compatible server, RSA keys, revision, ports, and legal terms | Blocked on server choice |
| Microbot/Rs2 | `microbot/` and `rs2/` call 634 IDs/opcodes | 667 IDs/opcodes unknown | High: automation may perform wrong actions | Disable dependent features until packet/widget maps are verified | Pending |

## Cache and rollback contract

The cache directory is selected at initialization, not by a global string
replacement. The working 634 client keeps `runescape`; a completed 667 port
must select `runescape-667`. `CacheDirectory` also clears its per-process file
memoization when the namespace changes. A 667 cache must never be pointed at a
634 directory.

Rollback is:

```text
java -Dvoid.protocol=634 -Dvoid.port=43594 -jar client/build/libs/void-client-1.2.0.jar
```

Restore the 634 source branch/commit if a source-level rollback is required.
Do not delete the 667 cache while diagnosing it; preserving both namespaces
makes cold-cache comparisons reproducible.

## Migration gates

- [x] Pin source commit and record that it is client-only.
- [x] Centralize the implemented revision, endpoint, and future cache namespace policy.
- [x] Keep 634 as the default rollback-safe profile.
- [ ] Compile a clean 667 core without duplicate 634 classes.
- [ ] Connect to JS5 and load the 667 master index from a cold cache.
- [ ] Validate CRC/Whirlpool/reference-table versions and named files.
- [ ] Complete RSA/login against the pinned compatible server.
- [ ] Render title, UI, sprites, fonts, scene, maps, and animations on desktop.
- [ ] Regenerate/verify Android JNI stubs and software framebuffer.
- [ ] Compile and run iOS/tvOS with updated RoboVM force links.
- [ ] Generate the 667 widget map and re-enable verified microbot features.
- [ ] Run the full acceptance matrix in the migration plan.

## Validation snapshot

Recorded on 2026-08-30 from the migration branch:

| Gate | Result | Notes |
|---|---|---|
| `./gradlew :client:compileJava` | PASS | Java 8 desktop source set compiles; existing internal-API/deprecation warnings remain |
| `python3 .cursor/skills/void-client-deobfuscate/scripts/check_reflection.py` | PASS | No missing or stale host reflection targets |
| `cd ios && ./gradlew prepareClientSources prepareSharedSources` | PASS | Generated sources include the versioned client profile |
| `cd ios && ./gradlew compileJava` | PASS | Java host/shared source set compiles |
| `cd android && ./gradlew :app:prepareClientSources` | BLOCKED | Android Gradle Plugin `8.7.3` was unavailable from configured repositories in this environment |
| Android APK / device, RoboVM package, tvOS device, JS5, login, gameplay | NOT RUN | Requires platform SDKs, compatible 667 server, RSA keys, and 667 cache fixtures |

The Android result is an environment dependency failure, not evidence of
protocol compatibility. Re-run `assembleDebug` after the Android plugin is
available; then run the cold-cache, login, renderer, input, audio, resume, and
device gates only after a real 667 core and compatible server have been
integrated.
