/**
 * One right-click / left-click menu row (option + target + opcode + params).
 * <p>
 * Built by {@link Class50_Sub3#addMenuEntry} while hovering NPCs / objects /
 * widgets; sorted into {@link Class348_Sub40_Sub4#menuEntries}; the tip row
 * lives in {@link Class316#menuTip}. Left-click or menu pick runs
 * {@link Class325#processMenuAction}, which turns the opcode into a game packet.
 * <p>
 * Custom Void opcodes in the 1900-range (DefaultClickSwapper, Microbot toggle)
 * are intercepted client-side and never sent.
 * <p>
 * Microbot plants synthetic rows with real game opcodes, then calls
 * {@link Class325#processMenuAction} on the client thread.
 */
final class MenuEntry extends Class348_Sub42 {

    /**
     * Left-side option text shown in the menu, e.g. {@code "Attack"},
     * {@code "Walk here"}, {@code "Wear"}. May include {@code <col=…>} tags.
     */
    String option;

    /** Unused / leftover deob field (kept for binary layout parity). */
    static int anInt9594;
    String aString9595;
    static int anInt9596;

    /** Construction flag from {@link Class50_Sub3#addMenuEntry} (bool arg). */
    boolean aBoolean9597;
    static int anInt9598;

    /**
     * Item id on interface / inventory rows ({@link Class46#anInt812}), or
     * {@code -1} for world entities.
     */
    int itemId;

    /**
     * Submenu / target grouping key (NPC index, object hash, …) used when
     * collapsing rows that share a target string.
     */
    long groupKey;

    /**
     * Right-side target text, e.g. {@code "<col=ffff00>Goblin"} or widget name.
     */
    String target;

    /**
     * First action param — local tile X for walk / object, component child for
     * interface ops ({@link Class46#anInt704}).
     */
    int param0;

    static Class356 aClass356_9603;
    static int anInt9604 = 0;

    /**
     * Entity / action identifier consumed by {@link Class325#processMenuAction}:
     * NPC index, packed object id (high bits), walk flag, etc.
     */
    long identifier;

    static int anInt9606;

    /**
     * Second action param — local tile Y for walk / object, packed interface id
     * for CC_OP ({@link Class46#anInt830}).
     */
    int param1;

    /**
     * Menu opcode. Stock game values (Attack=25/20/…, Walk=19, object 3/4/9/…).
     * Values {@code >= 2000} are shift-variants ({@code opcode - 2000} before send).
     * Void client-only ops live in 1900–1907.
     */
    int opcode;

    /**
     * Sort / tip priority — higher wins left-click tip. Microbot / DefaultClick
     * force near {@code Integer.MAX_VALUE}.
     */
    int priority;

    boolean aBoolean9610;
    boolean aBoolean9611;
    static int[] anIntArray9612;

    public static void method3228(int i) {
        anIntArray9612 = null;
        int i_0_ = 29 / ((31 - i) / 43);
        aClass356_9603 = null;
    }

    static final int method3229(int i) {
        anInt9596++;
        if (Class34.aFrame476 != null) return 3;
        if (i >= -59) anInt9604 = 79;
        if (!Class50_Sub1.aBoolean5219) return 1;
        return 2;
    }

    static final void method3230(int[] is, int[] is_1_, int i) {
        try {
            anInt9606++;
            if (is == null || is_1_ == null) {
                Class348_Sub40_Sub6.aByteArrayArrayArray9134 = null;
                Class190.anIntArray2552 = null;
                Class59_Sub2_Sub2.anIntArray8684 = null;
            } else {
                Class59_Sub2_Sub2.anIntArray8684 = is;
                Class190.anIntArray2552 = new int[is.length];
                Class348_Sub40_Sub6.aByteArrayArrayArray9134 = new byte[is.length][][];
                for (int i_2_ = i; i_2_ < Class59_Sub2_Sub2.anIntArray8684.length; i_2_++)
                    Class348_Sub40_Sub6.aByteArrayArrayArray9134[i_2_] = new byte[is_1_[i_2_]][];
            }
        } catch (RuntimeException runtimeexception) {
            throw Class348_Sub17.method2929(runtimeexception, ("db.D(" + (is != null ? "{...}" : "null") + ',' + (is_1_ != null ? "{...}" : "null") + ',' + i + ')'));
        }
    }

    static final void method3231(int i, int i_3_, int i_4_, int i_5_, int i_6_, int i_7_) {
        if (i_7_ > -123) method3229(14);
        Class239_Sub12.anInt5973 = i_5_;
        Class121.anInt1797 = i_4_;
        Class352.anInt4336 = i_3_;
        Class367_Sub11.anInt7403 = i_6_;
        anInt9598++;
        Class281.anInt3647 = i;
        if (Class239_Sub12.anInt5973 >= 100) {
            int i_8_ = Class352.anInt4336 * 512 - -256;
            int i_9_ = Class281.anInt3647 * 512 - -256;
            int i_10_ = (Class275.method2064(i_8_, Class355.anInt4372, 11219, i_9_) + -Class121.anInt1797);
            int i_11_ = i_8_ - Class286_Sub4.anInt6246;
            int i_12_ = -Class305.anInt3855 + i_10_;
            int i_13_ = i_9_ - Class59_Sub2_Sub2.anInt8685;
            int i_14_ = (int) Math.sqrt(i_11_ * i_11_ + i_13_ * i_13_);
            Class348_Sub42_Sub19.anInt9701 = (int) (Math.atan2(i_12_, i_14_) * 2607.5945876176133) & 0x3fff;
            Class5.anInt4638 = (int) (-2607.5945876176133 * Math.atan2(i_11_, i_13_)) & 0x3fff;
            if (Class348_Sub42_Sub19.anInt9701 < 1024) Class348_Sub42_Sub19.anInt9701 = 1024;
            Class338.anInt4186 = 0;
            if (Class348_Sub42_Sub19.anInt9701 > 3072) Class348_Sub42_Sub19.anInt9701 = 3072;
        }
        Class348_Sub40_Sub21.anInt9282 = 2;
        Class9.anInt167 = Class318_Sub1_Sub5_Sub2.anInt10163 = -1;
    }

    /**
     * @param option   menu option label
     * @param target   coloured target name
     * @param priority tip sort weight
     * @param opcode   menu opcode (see field docs)
     * @param itemId   item id or -1
     * @param identifier entity/action id (see field docs)
     * @param param0   first param (tile X / child)
     * @param param1   second param (tile Y / iface)
     */
    MenuEntry(String option, String target, int priority, int opcode, int itemId, long identifier, int param0, int param1, boolean bool, boolean bool_20_, long groupKey, boolean bool_22_) {
        try {
            this.param1 = param1;
            this.opcode = opcode;
            this.itemId = itemId;
            this.identifier = identifier;
            this.aBoolean9610 = bool;
            this.aBoolean9597 = bool_20_;
            this.target = target;
            this.aBoolean9611 = bool_22_;
            this.option = option;
            this.priority = priority;
            this.groupKey = groupKey;
            this.param0 = param0;
        } catch (RuntimeException runtimeexception) {
            throw Class348_Sub17.method2929(runtimeexception, ("db.<init>(" + (option != null ? "{...}" : "null") + ',' + (target != null ? "{...}" : "null") + ',' + priority + ',' + opcode + ',' + itemId + ',' + identifier + ',' + param0 + ',' + param1 + ',' + bool + ',' + bool_20_ + ',' + groupKey + ',' + bool_22_ + ')'));
        }
    }

    static {
        aClass356_9603 = new Class356(16);
        anIntArray9612 = new int[8];
    }
}
