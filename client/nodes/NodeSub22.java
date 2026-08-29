/* NodeSub22 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

import java.awt.*;

final class NodeSub22
/**
 * NPC list node ({@link #npc}) plus static interface helpers.
 * RENAMED from {@code Class348_Sub22} (JODE-obfuscated).
 */ extends Node {
    static int anInt6857;
    static int anInt6858;
    Npc npc;
    static int anInt6860;
    static int anInt6861;
    static int anInt6862;

    /**
     * Resolve a component: packed parent id + optional child index.
     * {@code childIndex == -1} → the parent itself; otherwise {@code parent.children[childIndex]}.
     * Opaque middle arg must be {@code (byte) -54}.
     * Used by prayer alias fire ({@link JoystickAlias}) and Microbot widget lookup.
     */
    static final DisplayModeManagerContainer57 getChildComponent(int i, byte i_0_, int i_1_) {
        anInt6858++;
        DisplayModeManagerContainer57 class46 = BitmapFont.getComponent(i_0_ + 1512932774, i_1_);
        if (i_0_ != -54) method2958(-23, null);
        if (i == -1) return class46;
        if (class46 == null || class46.children == null || (i >= class46.children.length)) return null;
        return class46.children[i];
    }

    static final int method2958(int i, CacheStore class45) {
        anInt6861++;
        int i_2_ = 0;
        if (class45.isSingletonFileReady(false, anInt6862)) i_2_++;
        if (class45.isSingletonFileReady(false, KeyStoreLoader.anInt1639)) i_2_++;
        if (class45.isSingletonFileReady(false, BasicMouseHandler.anInt7429)) i_2_++;
        if (class45.isSingletonFileReady(false, GraphicsToolkit.anInt4562)) i_2_++;
        if (class45.isSingletonFileReady(false, CommandHandler.anInt1435)) i_2_++;
        if (class45.isSingletonFileReady(false, Component95.anInt1756)) i_2_++;
        if (class45.isSingletonFileReady(false, ReferenceTable.anInt3739)) i_2_++;
        if (class45.isSingletonFileReady(false, Component328.anInt1481)) i_2_++;
        if (class45.isSingletonFileReady(false, Component98.anInt5948)) i_2_++;
        if (class45.isSingletonFileReady(false, Component22.anInt1742)) i_2_++;
        if (class45.isSingletonFileReady(false, NamedInteger.anInt4469)) i_2_++;
        if (i != 22388) return 8;
        if (class45.isSingletonFileReady(false, DefinitionSub38.anInt9473)) i_2_++;
        if (class45.isSingletonFileReady(false, Component38.anInt2510)) i_2_++;
        if (class45.isSingletonFileReady(false, RSACipher.anInt4895)) i_2_++;
        if (class45.isSingletonFileReady(false, Component134.anInt5814)) i_2_++;
        if (class45.isSingletonFileReady(false, DisplayModeManagerContainer89.anInt8370)) i_2_++;
        return i_2_;
    }

    static final void method2959(int i) {
        Component280.aClass346_2449.reset(14174);
        anInt6860++;
        AbstractGlTextureSub4.mouseHandler.destroy(0);
        NpcComposition.aClient1367.recreateGameCanvas((byte) -49);
        DisplayModeManagerContainer50.gameCanvas.setBackground(Color.black);
        Component244.anInt4179 = i;
        Component280.aClass346_2449 = NodeSub3.method2743(DisplayModeManagerContainer50.gameCanvas, (byte) 84);
        AbstractGlTextureSub4.mouseHandler = NodeSub18.createMouseHandler(DisplayModeManagerContainer50.gameCanvas, 0, true);
    }

    NodeSub22(Npc npc) {
        this.npc = npc;
    }
}
