---
name: void-client-component-lab
description: Inspect an opaque client component and safely render a local binary buffer for deobfuscation evidence.
---

# Component Lab workflow

Use the dependency-free lab before renaming an opaque component:

1. Start the browser with `make component-lab`.
2. Select the candidate `ComponentNNN` and inspect its source and method list.
3. If a captured binary buffer is available, run:
   `python3 tools/component_lab.py --buffer /absolute/path/data.bin`
4. Open `/buffer.svg` from the lab page (or export it with
   `--export-buffer /absolute/path/buffer.svg`). The card shows a bounded
   hex/ASCII view and the first four bytes interpreted in both float byte
   orders.
5. Compare the buffer read/write shape with call sites and related classes.
6. Rename only when the role is supported by evidence; record the old token,
   proposed name, and evidence in `docs/deobfuscation/islands/findings.md`.

The lab never executes client Java or assumes a cache format. It only reads the
explicit local file supplied with `--buffer`, limits the rendered view to 4096
bytes, and escapes data before placing it in SVG.
