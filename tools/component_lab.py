#!/usr/bin/env python3
"""Small local component browser and image-card generator.

This intentionally uses only the Python standard library. It does not execute
client code: opaque classes are not safe to instantiate without the game
bootstrap and cache. Instead it gives an investigator one isolated source
unit at a time, with a deterministic image that can be attached to an AI
analysis request.
"""

from __future__ import annotations

import argparse
import html
import re
import socketserver
from dataclasses import dataclass
from pathlib import Path
from string import Template
from http.server import BaseHTTPRequestHandler
from urllib.parse import unquote, urlparse


ROOT = Path(__file__).resolve().parents[1]
SOURCE_ROOTS = (ROOT / "client" / "components", ROOT / "client" / "misc")
METHOD_RE = re.compile(r"(?m)^\s*(?:(?:public|private|protected|static|final|synchronized)\s+)+[\w<>\[\], ?]+\s+(\w+)\s*\(")


@dataclass(frozen=True)
class Component:
    name: str
    path: Path
    source: str

    @property
    def lines(self) -> int:
        return self.source.count("\n") + 1

    @property
    def methods(self) -> tuple[str, ...]:
        return tuple(dict.fromkeys(METHOD_RE.findall(self.source)))

    @property
    def fields(self) -> int:
        return sum(1 for line in self.source.splitlines() if ";" in line and "(" not in line)


def components() -> dict[str, Component]:
    found: dict[str, Component] = {}
    for root in SOURCE_ROOTS:
        if not root.is_dir():
            continue
        for path in root.glob("*.java"):
            if not (path.name.startswith("Component") or path.name.startswith("DisplayModeManagerContainer")):
                continue
            found[path.stem] = Component(path.stem, path, path.read_text(encoding="utf-8", errors="replace"))
    return dict(sorted(found.items(), key=lambda item: item[0]))


def image_svg(component: Component) -> str:
    """Return a readable SVG source card; SVG is supported by browsers and vision tools."""
    lines = component.source.splitlines()
    width, line_height, top = 1500, 18, 118
    height = max(260, top + line_height * min(len(lines), 180) + 34)
    visible = lines[:180]
    text = "\n".join(
        f'<text x="32" y="{top + i * line_height}" class="code">'
        f'<tspan class="number">{i + 1:4d}</tspan> {html.escape(line[:170])}</text>'
        for i, line in enumerate(visible)
    )
    methods = ", ".join(component.methods[:8]) or "none detected"
    return f"""<svg xmlns="http://www.w3.org/2000/svg" width="{width}" height="{height}" viewBox="0 0 {width} {height}">
<rect width="100%" height="100%" fill="#101827"/>
<rect width="100%" height="84" fill="#17243a"/>
<text x="32" y="36" class="title">{html.escape(component.name)}</text>
<text x="32" y="62" class="meta">{component.lines} lines · {component.fields} fields · methods: {html.escape(methods)}</text>
<text x="32" y="98" class="hint">Void Component Lab · source card · first 180 lines</text>
{text}
<style>
.title {{ font: 700 28px sans-serif; fill: #f8fafc }}
.meta,.hint {{ font: 15px sans-serif; fill: #9fb2cc }}
.code {{ font: 14px monospace; fill: #d8e2f0; white-space: pre }}
.number {{ fill: #7186a5 }}
</style></svg>"""


PAGE = Template("""<!doctype html>
<meta charset="utf-8">
<title>Void Component Lab</title>
<style>
body{font:15px system-ui;background:#0b1220;color:#e5edf8;margin:0}
header{padding:22px 28px;background:#17243a} h1{margin:0 0 6px}
main{display:grid;grid-template-columns:280px 1fr;min-height:calc(100vh - 105px)}
nav{padding:16px;border-right:1px solid #293952;overflow:auto}
nav a{display:block;color:#a9c8f5;text-decoration:none;padding:5px 8px;border-radius:4px}
nav a:hover,nav a.selected{background:#263b5d;color:white}
section{padding:24px;overflow:auto}.stats{color:#9fb2cc}
img{max-width:100%;background:#101827;border:1px solid #293952}
pre{padding:16px;overflow:auto;background:#101827;border:1px solid #293952;font:13px monospace}
.button{color:#b8d7ff}
</style>
<header><h1>Void Component Lab</h1>
<div>Inspect one opaque class at a time and export a vision-friendly image card.</div></header>
<main><nav>$NAV</nav><section>$CONTENT</section></main>
""")


class Handler(BaseHTTPRequestHandler):
    def __init__(self, request, client_address, server, catalog: dict[str, Component]):
        self.catalog = catalog
        super().__init__(request, client_address, server)

    def reply(self, body: str, content_type: str = "text/html; charset=utf-8", status: int = 200) -> None:
        data = body.encode("utf-8")
        self.send_response(status)
        self.send_header("Content-Type", content_type)
        self.send_header("Content-Length", str(len(data)))
        self.end_headers()
        self.wfile.write(data)

    def do_GET(self) -> None:  # noqa: N802 - BaseHTTPRequestHandler API
        parsed = urlparse(self.path)
        path = unquote(parsed.path)
        if path == "/":
            selected = next(iter(self.catalog), None)
            self.show(selected)
        elif path.startswith("/component/") and path.endswith(".svg"):
            self.svg(path[len("/component/"):-4])
        elif path.startswith("/component/"):
            self.show(path[len("/component/"):])
        else:
            self.reply("not found", "text/plain; charset=utf-8", 404)

    def show(self, name: str | None) -> None:
        nav = "".join(
            f'<a class="{"selected" if item == name else ""}" href="/component/{html.escape(item)}">'
            f'{html.escape(item)}</a>' for item in self.catalog
        )
        component = self.catalog.get(name or "")
        if component is None:
            content = "<h2>No component selected</h2>"
        else:
            methods = ", ".join(component.methods) or "none detected"
            content = (f"<h2>{html.escape(component.name)}</h2>"
                       f'<p class="stats">{component.lines} lines · {component.fields} field-like declarations</p>'
                       f'<p>Methods: {html.escape(methods)}</p>'
                       f'<p><a class="button" href="/component/{html.escape(component.name)}.svg">'
                       "Open image card</a> · right-click it to save for AI analysis</p>"
                       f"<pre>{html.escape(component.source)}</pre>")
        self.reply(PAGE.substitute(NAV=nav, CONTENT=content))

    def svg(self, name: str) -> None:
        component = self.catalog.get(name)
        if component is None:
            self.reply("not found", "text/plain; charset=utf-8", 404)
        else:
            self.reply(image_svg(component), "image/svg+xml; charset=utf-8")

    def log_message(self, *_: object) -> None:
        pass


def export_cards(catalog: dict[str, Component], directory: Path) -> None:
    directory.mkdir(parents=True, exist_ok=True)
    for component in catalog.values():
        (directory / f"{component.name}.svg").write_text(image_svg(component), encoding="utf-8")
    print(f"exported {len(catalog)} image cards to {directory}")


def main() -> None:
    parser = argparse.ArgumentParser(description="Browse opaque client components and make AI-ready SVG cards.")
    parser.add_argument("--port", type=int, default=8765)
    parser.add_argument("--export-dir", type=Path, help="write one SVG card per component and exit")
    args = parser.parse_args()
    catalog = components()
    if args.export_dir:
        export_cards(catalog, args.export_dir)
        return
    handler = lambda request, address, server: Handler(request, address, server, catalog)
    with socketserver.ThreadingTCPServer(("127.0.0.1", args.port), handler) as server:
        print(f"Component Lab: http://127.0.0.1:{args.port}/ ({len(catalog)} components)")
        try:
            server.serve_forever()
        except KeyboardInterrupt:
            pass


if __name__ == "__main__":
    main()
