package com.badlogic.gdx.graphics.g2d;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Colors;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.a;
import com.badlogic.gdx.utils.c0;
import com.badlogic.gdx.utils.d0;
import com.badlogic.gdx.utils.j;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class GlyphLayout implements c0.a {
    public float height;
    public final a<GlyphRun> runs = new a<>(true, 1);
    public float width;
    private static final c0<GlyphRun> glyphRunPool = d0.c(GlyphRun.class);
    private static final c0<Color> colorPool = d0.c(Color.class);
    private static final a<Color> colorStack = new a<>(true, 4);

    public static class GlyphRun implements c0.a {
        public float width;

        /* JADX INFO: renamed from: x, reason: collision with root package name */
        public float f1685x;

        /* JADX INFO: renamed from: y, reason: collision with root package name */
        public float f1686y;
        public a<BitmapFont.Glyph> glyphs = new a<>();
        public j xAdvances = new j();
        public final Color color = new Color();

        @Override // com.badlogic.gdx.utils.c0.a
        public void reset() {
            this.glyphs.clear();
            this.xAdvances.f1824b = 0;
            this.width = 0.0f;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(this.glyphs.f1750b + 32);
            a<BitmapFont.Glyph> aVar = this.glyphs;
            int i2 = aVar.f1750b;
            for (int i3 = 0; i3 < i2; i3++) {
                sb.append((char) aVar.get(i3).id);
            }
            sb.append(", #");
            sb.append(this.color);
            sb.append(", ");
            sb.append(this.f1685x);
            sb.append(", ");
            sb.append(this.f1686y);
            sb.append(", ");
            sb.append(this.width);
            return sb.toString();
        }
    }

    public GlyphLayout() {
    }

    private void adjustLastGlyph(BitmapFont.BitmapFontData bitmapFontData, GlyphRun glyphRun) {
        if (glyphRun.glyphs.k().fixedWidth) {
            return;
        }
        glyphRun.xAdvances.f1823a[r3.f1824b - 1] = ((r0.width + r0.xoffset) * bitmapFontData.scaleX) - bitmapFontData.padRight;
    }

    private int parseColorMarkup(CharSequence charSequence, int i2, int i3, c0<Color> c0Var) {
        int i4;
        int i5;
        if (i2 == i3) {
            return -1;
        }
        char cCharAt = charSequence.charAt(i2);
        if (cCharAt != '#') {
            if (cCharAt == '[') {
                return -2;
            }
            if (cCharAt == ']') {
                a<Color> aVar = colorStack;
                if (aVar.f1750b > 1) {
                    c0Var.free(aVar.l());
                }
                return 0;
            }
            for (int i6 = i2 + 1; i6 < i3; i6++) {
                if (charSequence.charAt(i6) == ']') {
                    Color color = Colors.get(charSequence.subSequence(i2, i6).toString());
                    if (color == null) {
                        return -1;
                    }
                    Color colorObtain = c0Var.obtain();
                    colorStack.a(colorObtain);
                    colorObtain.set(color);
                    return i6 - i2;
                }
            }
            return -1;
        }
        int i7 = i2 + 1;
        int i8 = 0;
        while (true) {
            if (i7 >= i3) {
                break;
            }
            char cCharAt2 = charSequence.charAt(i7);
            if (cCharAt2 != ']') {
                if (cCharAt2 >= '0' && cCharAt2 <= '9') {
                    i4 = i8 * 16;
                    i5 = cCharAt2 - '0';
                } else if (cCharAt2 >= 'a' && cCharAt2 <= 'f') {
                    i4 = i8 * 16;
                    i5 = cCharAt2 - 'W';
                } else {
                    if (cCharAt2 < 'A' || cCharAt2 > 'F') {
                        break;
                    }
                    i4 = i8 * 16;
                    i5 = cCharAt2 - '7';
                }
                i8 = i5 + i4;
                i7++;
            } else if (i7 >= i2 + 2 && i7 <= i2 + 9) {
                int i9 = i7 - i2;
                if (i9 <= 7) {
                    for (int i10 = 0; i10 < 9 - i9; i10++) {
                        i8 <<= 4;
                    }
                    i8 |= 255;
                }
                Color colorObtain2 = c0Var.obtain();
                colorStack.a(colorObtain2);
                Color.rgba8888ToColor(colorObtain2, i8);
                return i9;
            }
        }
        return -1;
    }

    private void truncate(BitmapFont.BitmapFontData bitmapFontData, GlyphRun glyphRun, float f2, String str, int i2, c0<GlyphRun> c0Var) {
        GlyphRun glyphRunObtain = c0Var.obtain();
        bitmapFontData.getGlyphs(glyphRunObtain, str, 0, str.length(), null);
        float f3 = 0.0f;
        if (glyphRunObtain.xAdvances.f1824b > 0) {
            adjustLastGlyph(bitmapFontData, glyphRunObtain);
            j jVar = glyphRunObtain.xAdvances;
            float[] fArr = jVar.f1823a;
            int i3 = jVar.f1824b;
            for (int i4 = 1; i4 < i3; i4++) {
                f3 += fArr[i4];
            }
        }
        float f4 = f2 - f3;
        float f5 = glyphRun.f1685x;
        float[] fArr2 = glyphRun.xAdvances.f1823a;
        int i5 = 0;
        while (i5 < glyphRun.xAdvances.f1824b) {
            f5 += fArr2[i5];
            if (f5 > f4) {
                break;
            } else {
                i5++;
            }
        }
        if (i5 > 1) {
            glyphRun.glyphs.v(i5 - 1);
            j jVar2 = glyphRun.xAdvances;
            if (jVar2.f1824b > i5) {
                jVar2.f1824b = i5;
            }
            adjustLastGlyph(bitmapFontData, glyphRun);
            j jVar3 = glyphRunObtain.xAdvances;
            int i6 = jVar3.f1824b;
            if (i6 > 0) {
                glyphRun.xAdvances.b(jVar3, 1, i6 - 1);
            }
        } else {
            glyphRun.glyphs.clear();
            j jVar4 = glyphRun.xAdvances;
            jVar4.f1824b = 0;
            j jVar5 = glyphRunObtain.xAdvances;
            jVar4.c(jVar5.f1823a, 0, jVar5.f1824b);
        }
        glyphRun.glyphs.b(glyphRunObtain.glyphs);
        c0Var.free(glyphRunObtain);
    }

    private GlyphRun wrap(BitmapFont.BitmapFontData bitmapFontData, GlyphRun glyphRun, int i2, int i3) {
        GlyphRun glyphRunObtain;
        a<BitmapFont.Glyph> aVar = glyphRun.glyphs;
        int i4 = aVar.f1750b;
        j jVar = glyphRun.xAdvances;
        int i5 = i2;
        while (i5 > 0 && bitmapFontData.isWhitespace((char) aVar.get(i5 - 1).id)) {
            i5--;
        }
        while (i2 < i4 && bitmapFontData.isWhitespace((char) aVar.get(i2).id)) {
            i2++;
        }
        if (i2 < i4) {
            glyphRunObtain = glyphRunPool.obtain();
            glyphRunObtain.color.set(glyphRun.color);
            a<BitmapFont.Glyph> aVar2 = glyphRunObtain.glyphs;
            aVar2.c(aVar, 0, i5);
            aVar.p(i2 - 1);
            glyphRun.glyphs = aVar2;
            glyphRunObtain.glyphs = aVar;
            j jVar2 = glyphRunObtain.xAdvances;
            jVar2.b(jVar, 0, i5 + 1);
            jVar.f(i2);
            jVar.f1823a[0] = ((-aVar.g().xoffset) * bitmapFontData.scaleX) - bitmapFontData.padLeft;
            glyphRun.xAdvances = jVar2;
            glyphRunObtain.xAdvances = jVar;
        } else {
            aVar.v(i5);
            int i6 = i5 + 1;
            if (jVar.f1824b > i6) {
                jVar.f1824b = i6;
            }
            glyphRunObtain = null;
        }
        if (i5 == 0) {
            glyphRunPool.free(glyphRun);
            this.runs.l();
        } else {
            adjustLastGlyph(bitmapFontData, glyphRun);
        }
        return glyphRunObtain;
    }

    @Override // com.badlogic.gdx.utils.c0.a
    public void reset() {
        d0.c(GlyphRun.class).freeAll(this.runs);
        this.runs.clear();
        this.width = 0.0f;
        this.height = 0.0f;
    }

    public void setText(BitmapFont bitmapFont, CharSequence charSequence) {
        setText(bitmapFont, charSequence, 0, charSequence.length(), bitmapFont.getColor(), 0.0f, 8, false, null);
    }

    public String toString() {
        if (this.runs.f1750b == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(VertexAttributes.Usage.Tangent);
        sb.append(this.width);
        sb.append('x');
        sb.append(this.height);
        sb.append('\n');
        int i2 = this.runs.f1750b;
        for (int i3 = 0; i3 < i2; i3++) {
            sb.append(this.runs.get(i3).toString());
            sb.append('\n');
        }
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }

    public void setText(BitmapFont bitmapFont, CharSequence charSequence, Color color, float f2, int i2, boolean z2) {
        setText(bitmapFont, charSequence, 0, charSequence.length(), color, f2, i2, z2, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:146:0x0303  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x0319  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00b2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void setText(BitmapFont bitmapFont, CharSequence charSequence, int i2, int i3, Color color, float f2, int i4, boolean z2, String str) {
        Color color2;
        int i5;
        Color colorK;
        int i6;
        int i7;
        int i8;
        boolean z3;
        int i9;
        float f3;
        int i10;
        int i11;
        float f4;
        int i12;
        int i13;
        CharSequence charSequence2 = charSequence;
        a<GlyphRun> aVar = this.runs;
        glyphRunPool.freeAll(aVar);
        aVar.clear();
        BitmapFont.BitmapFontData bitmapFontData = bitmapFont.data;
        float f5 = 0.0f;
        int i14 = i2;
        if (i14 == i3) {
            this.width = 0.0f;
            this.height = bitmapFontData.capHeight;
            return;
        }
        boolean z4 = str != null ? true : f2 <= bitmapFontData.spaceXadvance * 3.0f ? false : z2;
        boolean z5 = bitmapFontData.markupEnabled;
        if (z5) {
            int i15 = colorStack.f1750b;
            for (int i16 = 1; i16 < i15; i16++) {
                colorPool.free(colorStack.get(i16));
            }
            a<Color> aVar2 = colorStack;
            aVar2.clear();
            color2 = color;
            aVar2.a(color2);
        } else {
            color2 = color;
        }
        float f6 = bitmapFontData.down;
        int i17 = i14;
        Color color3 = color2;
        float f7 = 0.0f;
        BitmapFont.Glyph glyphK = null;
        loop1: while (true) {
            if (i14 != i3) {
                i5 = i14 + 1;
                char cCharAt = charSequence2.charAt(i14);
                Color color4 = color3;
                if (cCharAt != '\n') {
                    if (cCharAt == '[' && z5) {
                        int colorMarkup = parseColorMarkup(charSequence2, i5, i3, colorPool);
                        if (colorMarkup >= 0) {
                            i5 = colorMarkup + 1 + i5;
                            colorK = colorStack.k();
                            z3 = false;
                            i6 = i14;
                            i7 = -1;
                        } else if (colorMarkup == -2) {
                            i14 += 2;
                            color3 = color4;
                        }
                    }
                    colorK = color4;
                    i7 = -1;
                    i6 = -1;
                } else {
                    colorK = color4;
                    i6 = i14;
                    i7 = -1;
                    z3 = true;
                }
                if (i6 == i7) {
                    if (i6 != i17) {
                        c0<GlyphRun> c0Var = glyphRunPool;
                        GlyphRun glyphRunObtain = c0Var.obtain();
                        glyphRunObtain.color.set(color2);
                        i10 = i5;
                        GlyphRun glyphRunWrap = glyphRunObtain;
                        BitmapFont.Glyph glyph = glyphK;
                        i11 = i17;
                        f3 = f6;
                        bitmapFontData.getGlyphs(glyphRunObtain, charSequence, i17, i6, glyph);
                        a<BitmapFont.Glyph> aVar3 = glyphRunWrap.glyphs;
                        if (aVar3.f1750b == 0) {
                            c0Var.free(glyphRunWrap);
                            glyphK = glyph;
                        } else {
                            if (glyph != null) {
                                f7 -= glyph.fixedWidth ? glyph.xadvance * bitmapFontData.scaleX : ((glyph.width + glyph.xoffset) * bitmapFontData.scaleX) - bitmapFontData.padRight;
                            }
                            float f8 = f7;
                            glyphK = aVar3.k();
                            glyphRunWrap.f1685x = f8;
                            glyphRunWrap.f1686y = f5;
                            if (z3 || i6 == i3) {
                                adjustLastGlyph(bitmapFontData, glyphRunWrap);
                            }
                            aVar.a(glyphRunWrap);
                            j jVar = glyphRunWrap.xAdvances;
                            int i18 = jVar.f1824b;
                            float[] fArr = jVar.f1823a;
                            if (z4 && i18 != 0) {
                                float f9 = fArr[0] + fArr[1] + f8;
                                int i19 = 2;
                                f7 = f9;
                                while (true) {
                                    int i20 = i19;
                                    if (i20 >= i18) {
                                        break;
                                    }
                                    int i21 = i20 - 1;
                                    BitmapFont.Glyph glyph2 = glyphRunWrap.glyphs.get(i21);
                                    if ((((glyph2.width + glyph2.xoffset) * bitmapFontData.scaleX) - bitmapFontData.padRight) + f7 <= f2) {
                                        f7 += fArr[i20];
                                        i13 = i20;
                                        i12 = 1;
                                    } else {
                                        if (str != null) {
                                            truncate(bitmapFontData, glyphRunWrap, f2, str, i20, glyphRunPool);
                                            break loop1;
                                        }
                                        f5 += f3;
                                        int wrapIndex = bitmapFontData.getWrapIndex(glyphRunWrap.glyphs, i20);
                                        if ((wrapIndex != 0 || glyphRunWrap.f1685x != 0.0f) && wrapIndex < glyphRunWrap.glyphs.f1750b) {
                                            i21 = wrapIndex;
                                        }
                                        if (i21 == 0) {
                                            int i22 = glyphRunWrap.glyphs.f1750b;
                                            while (i21 < i22 && bitmapFontData.isWhitespace((char) glyphRunWrap.glyphs.get(i21).id)) {
                                                i21++;
                                            }
                                            if (i21 > 0) {
                                                glyphRunWrap.glyphs.p(i21 - 1);
                                                glyphRunWrap.xAdvances.f(i21);
                                            }
                                            fArr[0] = ((-glyphRunWrap.glyphs.g().xoffset) * bitmapFontData.scaleX) - bitmapFontData.padLeft;
                                            int i23 = aVar.f1750b;
                                            if (i23 > 1) {
                                                GlyphRun glyphRun = aVar.get(i23 - 2);
                                                int i24 = glyphRun.glyphs.f1750b - 1;
                                                while (i24 > 0 && bitmapFontData.isWhitespace((char) glyphRun.glyphs.get(i24).id)) {
                                                    i24--;
                                                }
                                                glyphRun.glyphs.v(i24 + 1);
                                                j jVar2 = glyphRun.xAdvances;
                                                int i25 = i24 + 2;
                                                if (jVar2.f1824b > i25) {
                                                    jVar2.f1824b = i25;
                                                }
                                                adjustLastGlyph(bitmapFontData, glyphRun);
                                            }
                                        } else {
                                            glyphRunWrap = wrap(bitmapFontData, glyphRunWrap, i21, i20);
                                            if (glyphRunWrap == null) {
                                                glyphK = null;
                                                f4 = 0.0f;
                                                f7 = 0.0f;
                                                break;
                                            }
                                            aVar.a(glyphRunWrap);
                                        }
                                        j jVar3 = glyphRunWrap.xAdvances;
                                        int i26 = jVar3.f1824b;
                                        fArr = jVar3.f1823a;
                                        float f10 = fArr[0];
                                        i12 = 1;
                                        if (i26 > 1) {
                                            f10 += fArr[1];
                                        }
                                        glyphRunWrap.f1685x = 0.0f;
                                        glyphRunWrap.f1686y = f5;
                                        f7 = f10;
                                        glyphK = null;
                                        i18 = i26;
                                        i13 = 1;
                                    }
                                    i19 = i13 + i12;
                                }
                            } else {
                                f4 = 0.0f;
                                f7 = f8;
                                if (z5) {
                                    for (int i27 = 0; i27 < i18; i27++) {
                                        f7 += fArr[i27];
                                    }
                                }
                            }
                            if (z3) {
                                f5 = i6 == i11 ? (bitmapFontData.blankLineScale * f3) + f5 : f5 + f3;
                                f7 = f4;
                                glyphK = null;
                            }
                            i9 = i10;
                            color2 = colorK;
                        }
                    } else {
                        i11 = i17;
                        f3 = f6;
                        i10 = i5;
                    }
                    f4 = 0.0f;
                    if (z3) {
                    }
                    i9 = i10;
                    color2 = colorK;
                } else {
                    i9 = i17;
                    f3 = f6;
                    i10 = i5;
                }
                charSequence2 = charSequence;
                i17 = i9;
                color3 = colorK;
                f6 = f3;
                i14 = i10;
            } else {
                if (i17 == i3) {
                    break;
                }
                i5 = i14;
                colorK = color3;
                i6 = i3;
                i7 = -1;
            }
            z3 = false;
            if (i6 == i7) {
            }
            charSequence2 = charSequence;
            i17 = i9;
            color3 = colorK;
            f6 = f3;
            i14 = i10;
        }
        this.height = Math.abs(f5) + bitmapFontData.capHeight;
        GlyphRun[] glyphRunArr = aVar.f1749a;
        int i28 = aVar.f1750b;
        float fMax = 0.0f;
        for (int i29 = 0; i29 < i28; i29++) {
            GlyphRun glyphRun2 = glyphRunArr[i29];
            float[] fArr2 = glyphRun2.xAdvances.f1823a;
            float f11 = fArr2[0];
            a<BitmapFont.Glyph> aVar4 = glyphRun2.glyphs;
            BitmapFont.Glyph[] glyphArr = aVar4.f1749a;
            int i30 = aVar4.f1750b;
            float f12 = f11;
            float fMax2 = 0.0f;
            int i31 = 0;
            while (i31 < i30) {
                BitmapFont.Glyph glyph3 = glyphArr[i31];
                fMax2 = Math.max(fMax2, (((glyph3.width + glyph3.xoffset) * bitmapFontData.scaleX) - bitmapFontData.padRight) + f12);
                i31++;
                f12 += fArr2[i31];
            }
            float fMax3 = Math.max(f12, fMax2);
            glyphRun2.width = fMax3;
            fMax = Math.max(fMax, glyphRun2.f1685x + fMax3);
        }
        this.width = fMax;
        if ((i4 & 8) != 0) {
            return;
        }
        boolean z6 = (i4 & 1) != 0;
        float f13 = -2.1474836E9f;
        float fMax4 = 0.0f;
        int i32 = 0;
        for (int i33 = 0; i33 < i28; i33++) {
            GlyphRun glyphRun3 = glyphRunArr[i33];
            float f14 = glyphRun3.f1686y;
            if (f14 != f13) {
                float f15 = f2 - fMax4;
                if (z6) {
                    f15 /= 2.0f;
                }
                while (true) {
                    i8 = i32;
                    if (i8 >= i33) {
                        break;
                    }
                    i32 = i8 + 1;
                    glyphRunArr[i8].f1685x += f15;
                }
                fMax4 = glyphRun3.f1685x + glyphRun3.width;
                i32 = i8;
                f13 = f14;
            } else {
                fMax4 = Math.max(fMax4, glyphRun3.f1685x + glyphRun3.width);
            }
        }
        float f16 = f2 - fMax4;
        if (z6) {
            f16 /= 2.0f;
        }
        while (true) {
            int i34 = i32;
            if (i34 >= i28) {
                return;
            }
            i32 = i34 + 1;
            glyphRunArr[i34].f1685x += f16;
        }
    }

    public GlyphLayout(BitmapFont bitmapFont, CharSequence charSequence) {
        setText(bitmapFont, charSequence);
    }

    public GlyphLayout(BitmapFont bitmapFont, CharSequence charSequence, Color color, float f2, int i2, boolean z2) {
        setText(bitmapFont, charSequence, color, f2, i2, z2);
    }

    public GlyphLayout(BitmapFont bitmapFont, CharSequence charSequence, int i2, int i3, Color color, float f2, int i4, boolean z2, String str) {
        setText(bitmapFont, charSequence, i2, i3, color, f2, i4, z2, str);
    }
}
