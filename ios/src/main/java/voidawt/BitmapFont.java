package voidawt;

final class BitmapFont {
    private BitmapFont() {
    }

    static void blit(int[] dst, int tw, int th, int x, int y, char ch, int rgb, int glyphW, int glyphH) {
        int bits = glyph(ch);
        for (int row = 0; row < 7; row++) {
            for (int col = 0; col < 5; col++) {
                if ((bits & (1 << (row * 5 + col))) == 0) {
                    continue;
                }
                int x0 = x + col * glyphW / 5;
                int y0 = y + row * glyphH / 7;
                int x1 = x + (col + 1) * glyphW / 5;
                int y1 = y + (row + 1) * glyphH / 7;
                for (int yy = y0; yy < y1; yy++) {
                    if (yy < 0 || yy >= th) {
                        continue;
                    }
                    int off = yy * tw;
                    for (int xx = x0; xx < x1; xx++) {
                        if (xx >= 0 && xx < tw) {
                            dst[off + xx] = rgb;
                        }
                    }
                }
            }
        }
    }

    private static int glyph(char ch) {
        if (ch < 32 || ch > 126) {
            ch = '?';
        }
        return GLYPHS[ch - 32];
    }

    /** 5x7 packed bits (bit 0 = top-left, row-major). Printable ASCII 32-126. */
    private static final int[] GLYPHS = {
            0x0000000, 0x0841041, 0x00A5000, 0x15F7C5F, 0x1D7D5F1, 0x1999999, 0x0C9A5C9, 0x0041000,
            0x0441044, 0x0110411, 0x0157115, 0x0047C04, 0x0000411, 0x0007C00, 0x0000041, 0x0111111,
            0x1F1111F, 0x0041047, 0x1D1111D, 0x0F1111F, 0x0107C11, 0x0F1111F, 0x1F15151, 0x011111F,
            0x1F1515F, 0x0F15151, 0x0040040, 0x0040044, 0x0441044, 0x00E00E0, 0x0110411, 0x0D1111D,
            0x1D1515D, 0x1F15151, 0x1F1515F, 0x1F11111, 0x1F14141, 0x1F1111D, 0x1117C11, 0x0411041,
            0x1F0101F, 0x1115411, 0x111111F, 0x1155551, 0x1133551, 0x1F1111F, 0x1D15151, 0x1F1115F,
            0x1D15551, 0x0D1515B, 0x0411041, 0x111111F, 0x111111E, 0x111555E, 0x1154551, 0x0511051,
            0x1911119, 0x0441044, 0x1F1111F, 0x0E0100E, 0x1041041, 0x0E0100E, 0x00A5000, 0x000001F,
            0x0041000, 0x0E1515E, 0x1F15151, 0x0E11110, 0x1F15151, 0x0E15150, 0x047D044, 0x0F1515E,
            0x1F14141, 0x0410041, 0x1E0101E, 0x1115411, 0x0411041, 0x1555551, 0x1D14141, 0x0E1111E,
            0x1F15150, 0x0F1515E, 0x0E14140, 0x0C15152, 0x047D044, 0x111111E, 0x111111C, 0x111555C,
            0x1154551, 0x111151E, 0x1911119, 0x0447C44, 0x0411041, 0x0117C11, 0x00A5000
    };
}
