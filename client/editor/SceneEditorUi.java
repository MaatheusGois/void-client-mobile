import java.util.Collection;

/**
 * In-world scene editor HUD matching the local-object mockup:
 * asset palette, click-to-place, select, drag-to-move, add/remove action strip.
 * <p>
 * Tile under cursor is read from the existing Walk-here menu tip (opcode 19)
 * built each frame by {@link ColoredText#method1823} — no custom raycast.
 */
final class SceneEditorUi {
    /** Curated LocType palette (RS634-ish). Ids are adjustable via console. */
    static final Asset[] ASSETS = {
            new Asset("Tree", 1276),
            new Asset("Oak", 1281),
            new Asset("Dead tree", 1274),
            new Asset("Bush", 1251),
            new Asset("Bench", 10378),
            new Asset("Lamp", 10827),
            new Asset("Fountain", 879),
            new Asset("Crate", 356),
            new Asset("Barrel", 365),
            new Asset("Cart", 794)
    };

    private static final int PANEL_W = 132;
    private static final int PANEL_PAD = 6;
    private static final int ROW_H = 18;
    private static final int ASSET_COLS = 2;
    private static final int ASSET_CELL = 58;
    private static final int ASSET_GAP = 4;
    private static final int BG = 0xC0121218;
    private static final int ACCENT = 0xFF00E5FF;
    private static final int SELECT = 0xFFFF00AA;
    private static final int ADD_COL = 0xFF66FF66;
    private static final int REM_COL = 0xFFFF6666;
    private static final int SHADOW = 0xFF000000;
    private static final int GHOST = 0x88AAAAAA;
    private static final int DRAG_LINE = 0xFFFF8800;

    private static int selectedAsset;
    private static long selectedId = -1L;
    private static boolean dragging;
    private static int dragHoverAbsX = -1;
    private static int dragHoverAbsY = -1;
    private static int hoverAbsX = -1;
    private static int hoverAbsY = -1;
    private static boolean mouseOverUi;
    private static boolean announced;

    private SceneEditorUi() {
    }

    static boolean isMouseOver() {
        return mouseOverUi && SceneEditorHost.isEditorMode();
    }

    static Asset currentAsset() {
        if (selectedAsset < 0 || selectedAsset >= ASSETS.length) {
            selectedAsset = 0;
        }
        return ASSETS[selectedAsset];
    }

    static void onEditorEnabled() {
        if (!announced) {
            announced = true;
            try {
                ShaderProgramSub2.addChatMessage("", 5, (byte) -100, 0,
                        "[System] Scene editor active. Pick an asset, click to place, drag to move.",
                        "", "");
            } catch (Throwable ignored) {
                /* chat may be unavailable mid-login */
            }
        }
    }

    static void onEditorDisabled() {
        selectedId = -1L;
        dragging = false;
        announced = false;
    }

    /** Called from the draw loop after Microbot HUD. */
    static void draw(GraphicsToolkit toolkit) {
        if (!SceneEditorHost.isEditorMode() || toolkit == null || !Microbot.isLoggedIn()) {
            return;
        }
        onEditorEnabled();
        try {
            BitmapFont font = Applet_Sub1.aClass324_20;
            if (font == null) {
                font = Component49.aClass324_4684;
            }
            if (font == null) {
                return;
            }
            refreshHoverTile();
            drawPalette(toolkit, font);
            drawActionMenu(toolkit, font);
            drawWorldOverlay(toolkit, font);
            drawToolBanner(toolkit, font);
        } catch (Throwable t) {
            System.out.println("scene-editor ui draw: " + t.getMessage());
        }
    }

    /**
     * Eat world / palette clicks while editor mode is on.
     * Must run with {@link MicrobotPanel#pollInput()} before walk/menu consume.
     */
    static void pollInput() {
        mouseOverUi = false;
        if (!SceneEditorHost.isEditorMode() || AbstractGlTextureSub4.mouseHandler == null) {
            return;
        }
        if (!Microbot.isLoggedIn()) {
            return;
        }
        try {
            int mx = AbstractGlTextureSub4.mouseHandler.getCursorX(true);
            int my = AbstractGlTextureSub4.mouseHandler.getCursorY((byte) 100);
            mouseOverUi = hitPalette(mx, my) || hitActionMenu(mx, my);

            refreshHoverTile();

            Node node = Component327.aClass262_8744.first(4);
            while (node != null) {
                Node next = Component327.aClass262_8744.next((byte) 79);
                if (node instanceof NodeSub45) {
                    NodeSub45 click = (NodeSub45) node;
                    int type = click.getEventType(86);
                    int cx = click.getX((byte) -128);
                    int cy = click.getY(33);
                    if (type == 0) {
                        if (hitPalette(cx, cy)) {
                            onPaletteClick(cx, cy);
                            click.unlink((byte) 97);
                        } else if (hitActionMenu(cx, cy)) {
                            onActionClick(cx, cy);
                            click.unlink((byte) 97);
                        } else if (!BuildInfo.isMouseOverConsole() && !MicrobotPanel.contains(cx, cy)) {
                            onWorldPress(cx, cy);
                            click.unlink((byte) 97);
                        }
                    } else if (type == 3) {
                        // left mouse release — finish drag
                        if (dragging) {
                            onWorldRelease();
                            click.unlink((byte) 97);
                        }
                    }
                }
                node = next;
            }

            if (dragging && hoverAbsX >= 0) {
                dragHoverAbsX = hoverAbsX;
                dragHoverAbsY = hoverAbsY;
            }
        } catch (Throwable t) {
            System.out.println("scene-editor ui input: " + t.getMessage());
        }
    }

    private static void onWorldPress(int cx, int cy) {
        int[] tile = tipTileAbs();
        if (tile == null) {
            return;
        }
        SceneObject hit = findAt(tile[0], tile[1], tile[2]);
        if (hit != null) {
            selectedId = hit.id;
            dragging = true;
            dragHoverAbsX = tile[0];
            dragHoverAbsY = tile[1];
            return;
        }
        // Empty tile → place current asset
        placeAt(tile[0], tile[1], tile[2]);
    }

    private static void onWorldRelease() {
        if (!dragging || selectedId < 0) {
            dragging = false;
            return;
        }
        if (dragHoverAbsX >= 0 && dragHoverAbsY >= 0) {
            try {
                SceneObject o = SceneEditorHost.editor().scene().get(selectedId);
                if (o != null && (o.x != dragHoverAbsX || o.y != dragHoverAbsY)) {
                    SceneEditorHost.editor().move(selectedId, dragHoverAbsX, dragHoverAbsY, o.z);
                    SceneEditorHost.resync();
                }
            } catch (Throwable t) {
                System.out.println("scene-editor move: " + t.getMessage());
            }
        }
        dragging = false;
    }

    private static void placeAt(int absX, int absY, int plane) {
        try {
            Asset asset = currentAsset();
            SceneObject added = SceneEditorHost.editor().add(asset.objectId, absX, absY, 0, plane);
            selectedId = added.id;
            SceneEditorHost.resync();
        } catch (Throwable t) {
            System.out.println("scene-editor place: " + t.getMessage());
        }
    }

    private static void removeSelected() {
        if (selectedId < 0) {
            return;
        }
        try {
            SceneEditorHost.editor().remove(selectedId);
            selectedId = -1L;
            dragging = false;
            SceneEditorHost.resync();
        } catch (Throwable t) {
            System.out.println("scene-editor remove: " + t.getMessage());
        }
    }

    private static SceneObject findAt(int absX, int absY, int plane) {
        SceneObject best = null;
        int bestDist = 2;
        for (SceneObject o : SceneEditorHost.editor().scene().objects()) {
            if (o.plane != plane) {
                continue;
            }
            int d = Math.max(Math.abs(o.x - absX), Math.abs(o.y - absY));
            if (d < bestDist) {
                bestDist = d;
                best = o;
            }
        }
        return best;
    }

    /** Absolute tile under cursor from Walk-here tip, or null. */
    private static int[] tipTileAbs() {
        MenuEntry walk = findWalkTip();
        if (walk == null) {
            return null;
        }
        int localX = walk.param0;
        int localY = walk.param1;
        if (!SceneObjectAdapter.inSceneBounds(localX, localY)) {
            return null;
        }
        int plane = MicrobotWidgets.localPlane();
        return new int[]{
                localX + NodeBaseSub2.regionTileX,
                localY + Component330.regionTileY,
                plane
        };
    }

    private static MenuEntry findWalkTip() {
        MenuEntry tip = Component192.menuTip;
        if (tip != null) {
            int op = tip.opcode >= 2000 ? tip.opcode - 2000 : tip.opcode;
            if (op == 19) {
                return tip;
            }
        }
        try {
            for (MenuEntry e = (MenuEntry) DefinitionSub4.menuEntries.sentinel.previous;
                 e != null && e != DefinitionSub4.menuEntries.sentinel;
                 e = (MenuEntry) e.previous) {
                int op = e.opcode >= 2000 ? e.opcode - 2000 : e.opcode;
                if (op == 19) {
                    return e;
                }
            }
        } catch (Throwable ignored) {
            /* empty */
        }
        return null;
    }

    private static void refreshHoverTile() {
        int[] tile = tipTileAbs();
        if (tile == null) {
            hoverAbsX = hoverAbsY = -1;
            return;
        }
        hoverAbsX = tile[0];
        hoverAbsY = tile[1];
    }

    private static int paletteX() {
        return Math.max(8, SocketConnector.canvasWidth - PANEL_W - 8);
    }

    private static int paletteY() {
        return 48;
    }

    private static int paletteHeight() {
        int rows = (ASSETS.length + ASSET_COLS - 1) / ASSET_COLS;
        return PANEL_PAD * 2 + 20 + rows * (ASSET_CELL + ASSET_GAP) + ROW_H;
    }

    private static boolean hitPalette(int x, int y) {
        int px = paletteX();
        int py = paletteY();
        return x >= px && x < px + PANEL_W && y >= py && y < py + paletteHeight();
    }

    private static void onPaletteClick(int x, int y) {
        int relX = x - paletteX() - PANEL_PAD;
        int relY = y - paletteY() - PANEL_PAD - 20;
        if (relY < 0) {
            return;
        }
        int col = relX / (ASSET_CELL + ASSET_GAP);
        int row = relY / (ASSET_CELL + ASSET_GAP);
        if (col < 0 || col >= ASSET_COLS) {
            return;
        }
        int idx = row * ASSET_COLS + col;
        if (idx >= 0 && idx < ASSETS.length) {
            selectedAsset = idx;
        }
    }

    private static void drawPalette(GraphicsToolkit toolkit, BitmapFont font) {
        int px = paletteX();
        int py = paletteY();
        int ph = paletteHeight();
        toolkit.fillRect2D(px, py, PANEL_W, ph, BG, 1);
        toolkit.fillRect2D(px, py, PANEL_W, 1, ACCENT, 1);
        font.drawText("City Assets", ACCENT, py + PANEL_PAD + 12, px + PANEL_PAD, SHADOW, -110);
        int cellY = py + PANEL_PAD + 20;
        for (int i = 0; i < ASSETS.length; i++) {
            int col = i % ASSET_COLS;
            int row = i / ASSET_COLS;
            int cx = px + PANEL_PAD + col * (ASSET_CELL + ASSET_GAP);
            int cy = cellY + row * (ASSET_CELL + ASSET_GAP);
            int fill = i == selectedAsset ? 0xC0332255 : 0xC0222228;
            toolkit.fillRect2D(cx, cy, ASSET_CELL, ASSET_CELL, fill, 1);
            if (i == selectedAsset) {
                toolkit.fillRect3D(cx, cy, ASSET_CELL, ASSET_CELL, SELECT, 0);
            } else {
                toolkit.fillRect3D(cx, cy, ASSET_CELL, ASSET_CELL, 0xFF444444, 0);
            }
            String label = ASSETS[i].label;
            font.drawText(label.length() > 8 ? label.substring(0, 8) : label,
                    0xFFFFFFFF, cy + ASSET_CELL / 2 + 4, cx + 4, SHADOW, -110);
        }
    }

    private static int actionMenuX() {
        return Math.max(8, SocketConnector.canvasWidth / 2 - 70);
    }

    private static int actionMenuY() {
        return Math.max(8, NpcNode.canvasHeight - 90);
    }

    private static boolean hitActionMenu(int x, int y) {
        int ax = actionMenuX();
        int ay = actionMenuY();
        return x >= ax && x < ax + 140 && y >= ay && y < ay + 54;
    }

    private static void onActionClick(int x, int y) {
        int relY = y - actionMenuY();
        if (relY < 28) {
            int[] tile = tipTileAbs();
            if (tile != null) {
                placeAt(tile[0], tile[1], tile[2]);
            } else {
                try {
                    SceneEditorHost.spawnAtPlayer(currentAsset().objectId);
                    SceneObject last = null;
                    for (SceneObject o : SceneEditorHost.editor().scene().objects()) {
                        last = o;
                    }
                    if (last != null) {
                        selectedId = last.id;
                    }
                } catch (Throwable t) {
                    System.out.println("scene-editor add: " + t.getMessage());
                }
            }
        } else {
            removeSelected();
        }
    }

    private static void drawActionMenu(GraphicsToolkit toolkit, BitmapFont font) {
        int ax = actionMenuX();
        int ay = actionMenuY();
        toolkit.fillRect2D(ax, ay, 140, 54, BG, 1);
        toolkit.fillRect2D(ax, ay, 140, 1, ACCENT, 1);
        font.drawText("action menu", ACCENT, ay + 12, ax + 8, SHADOW, -110);
        font.drawText("[+] Add " + shortLabel(currentAsset().label), ADD_COL, ay + 28, ax + 8, SHADOW, -110);
        font.drawText("[-] Remove", REM_COL, ay + 44, ax + 8, SHADOW, -110);
    }

    private static void drawToolBanner(GraphicsToolkit toolkit, BitmapFont font) {
        Asset a = currentAsset();
        String text = a.label + " Tool Active";
        font.drawText(text, SELECT, 18, 160, SHADOW, -110);
    }

    private static void drawWorldOverlay(GraphicsToolkit toolkit, BitmapFont font) {
        SceneObject selected = selectedId >= 0 ? SceneEditorHost.editor().scene().get(selectedId) : null;
        if (selected != null) {
            projectAndBox(toolkit, selected.x, selected.y, selected.plane, SELECT, 24);
        }
        if (dragging && dragHoverAbsX >= 0 && selected != null) {
            projectAndBox(toolkit, dragHoverAbsX, dragHoverAbsY, selected.plane, GHOST, 20);
            drawDragLine(toolkit, selected.x, selected.y, selected.plane, dragHoverAbsX, dragHoverAbsY);
        } else if (hoverAbsX >= 0 && selectedId < 0) {
            projectAndBox(toolkit, hoverAbsX, hoverAbsY, MicrobotWidgets.localPlane(), 0x6600E5FF, 16);
        }
    }

    private static void projectAndBox(GraphicsToolkit toolkit, int absX, int absY, int plane, int argb, int half) {
        int lx = SceneObjectAdapter.toLocalX(absX);
        int ly = SceneObjectAdapter.toLocalY(absY);
        int fineX = (lx << 9) + 256;
        int fineY = (ly << 9) + 256;
        try {
            ShaderSub2.method165(plane, 0, 0, 0, fineX, 0, fineY, (byte) 61, 0, 0);
            int sx = Component71.anIntArray6062[0];
            int sy = Component71.anIntArray6062[1];
            if (sx < 0 || sy < 0) {
                return;
            }
            toolkit.fillRect3D(sx - half, sy - half, half * 2, half * 2, argb, 0);
        } catch (Throwable ignored) {
            /* projection can fail off-screen */
        }
    }

    private static void drawDragLine(GraphicsToolkit toolkit, int ax, int ay, int plane, int bx, int by) {
        int lx0 = SceneObjectAdapter.toLocalX(ax);
        int ly0 = SceneObjectAdapter.toLocalY(ay);
        int lx1 = SceneObjectAdapter.toLocalX(bx);
        int ly1 = SceneObjectAdapter.toLocalY(by);
        try {
            ShaderSub2.method165(plane, 0, 0, 0, (lx0 << 9) + 256, 0, (ly0 << 9) + 256, (byte) 61, 0, 0);
            int sx0 = Component71.anIntArray6062[0];
            int sy0 = Component71.anIntArray6062[1];
            ShaderSub2.method165(plane, 0, 0, 0, (lx1 << 9) + 256, 0, (ly1 << 9) + 256, (byte) 61, 0, 0);
            int sx1 = Component71.anIntArray6062[0];
            int sy1 = Component71.anIntArray6062[1];
            if (sx0 < 0 || sy0 < 0 || sx1 < 0 || sy1 < 0) {
                return;
            }
            // Approximate line with small rects along the segment.
            int steps = Math.max(Math.abs(sx1 - sx0), Math.abs(sy1 - sy0)) / 4;
            if (steps < 1) {
                steps = 1;
            }
            for (int i = 0; i <= steps; i++) {
                int sx = sx0 + (sx1 - sx0) * i / steps;
                int sy = sy0 + (sy1 - sy0) * i / steps;
                toolkit.fillRect2D(sx - 1, sy - 1, 3, 3, DRAG_LINE, 1);
            }
        } catch (Throwable ignored) {
            /* empty */
        }
    }

    private static String shortLabel(String s) {
        return s.length() > 8 ? s.substring(0, 8) : s;
    }

    static final class Asset {
        final String label;
        final int objectId;

        Asset(String label, int objectId) {
            this.label = label;
            this.objectId = objectId;
        }
    }
}
