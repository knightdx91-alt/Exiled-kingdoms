package com.badlogic.gdx.graphics.g2d;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.utils.a;
import com.badlogic.gdx.utils.d0;
import com.badlogic.gdx.utils.j;
import com.badlogic.gdx.utils.l;
import com.badlogic.gdx.utils.o;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class BitmapFontCache {
    private static final Color tempColor = new Color(1.0f, 1.0f, 1.0f, 1.0f);
    private final Color color;
    private float currentTint;
    private final BitmapFont font;
    private int glyphCount;
    private int[] idx;
    private boolean integer;
    private final a<GlyphLayout> layouts;
    private o[] pageGlyphIndices;
    private float[][] pageVertices;
    private final a<GlyphLayout> pooledLayouts;
    private int[] tempGlyphCount;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    private float f1683x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    private float f1684y;

    public BitmapFontCache(BitmapFont bitmapFont) {
        this(bitmapFont, bitmapFont.usesIntegerPositions());
    }

    private void addGlyph(BitmapFont.Glyph glyph, float f2, float f3, float f4) {
        BitmapFont.BitmapFontData bitmapFontData = this.font.data;
        float f5 = bitmapFontData.scaleX;
        float f6 = bitmapFontData.scaleY;
        float fRound = (glyph.xoffset * f5) + f2;
        float fRound2 = (glyph.yoffset * f6) + f3;
        float fRound3 = glyph.width * f5;
        float fRound4 = glyph.height * f6;
        float f7 = glyph.f1681u;
        float f8 = glyph.u2;
        float f9 = glyph.f1682v;
        float f10 = glyph.v2;
        if (this.integer) {
            fRound = Math.round(fRound);
            fRound2 = Math.round(fRound2);
            fRound3 = Math.round(fRound3);
            fRound4 = Math.round(fRound4);
        }
        float f11 = fRound3 + fRound;
        float f12 = fRound4 + fRound2;
        int i2 = glyph.page;
        int[] iArr = this.idx;
        int i3 = iArr[i2];
        iArr[i2] = i3 + 20;
        o[] oVarArr = this.pageGlyphIndices;
        if (oVarArr != null) {
            o oVar = oVarArr[i2];
            int i4 = this.glyphCount;
            this.glyphCount = i4 + 1;
            oVar.a(i4);
        }
        float[] fArr = this.pageVertices[i2];
        fArr[i3] = fRound;
        fArr[i3 + 1] = fRound2;
        fArr[i3 + 2] = f4;
        fArr[i3 + 3] = f7;
        fArr[i3 + 4] = f9;
        fArr[i3 + 5] = fRound;
        fArr[i3 + 6] = f12;
        fArr[i3 + 7] = f4;
        fArr[i3 + 8] = f7;
        fArr[i3 + 9] = f10;
        fArr[i3 + 10] = f11;
        fArr[i3 + 11] = f12;
        fArr[i3 + 12] = f4;
        fArr[i3 + 13] = f8;
        fArr[i3 + 14] = f10;
        fArr[i3 + 15] = f11;
        fArr[i3 + 16] = fRound2;
        fArr[i3 + 17] = f4;
        fArr[i3 + 18] = f8;
        fArr[i3 + 19] = f9;
    }

    private void addToCache(GlyphLayout glyphLayout, float f2, float f3) {
        int length;
        int i2 = this.font.regions.f1750b;
        float[][] fArr = this.pageVertices;
        if (fArr.length < i2) {
            float[][] fArr2 = new float[i2][];
            System.arraycopy(fArr, 0, fArr2, 0, fArr.length);
            this.pageVertices = fArr2;
            int[] iArr = new int[i2];
            int[] iArr2 = this.idx;
            System.arraycopy(iArr2, 0, iArr, 0, iArr2.length);
            this.idx = iArr;
            o[] oVarArr = new o[i2];
            o[] oVarArr2 = this.pageGlyphIndices;
            if (oVarArr2 != null) {
                length = oVarArr2.length;
                System.arraycopy(oVarArr2, 0, oVarArr, 0, oVarArr2.length);
            } else {
                length = 0;
            }
            while (length < i2) {
                oVarArr[length] = new o();
                length++;
            }
            this.pageGlyphIndices = oVarArr;
            this.tempGlyphCount = new int[i2];
        }
        this.layouts.a(glyphLayout);
        requireGlyphs(glyphLayout);
        int i3 = glyphLayout.runs.f1750b;
        for (int i4 = 0; i4 < i3; i4++) {
            GlyphLayout.GlyphRun glyphRun = glyphLayout.runs.get(i4);
            a<BitmapFont.Glyph> aVar = glyphRun.glyphs;
            j jVar = glyphRun.xAdvances;
            float floatBits = glyphRun.color.toFloatBits();
            float fE = glyphRun.f1685x + f2;
            float f4 = glyphRun.f1686y + f3;
            int i5 = aVar.f1750b;
            for (int i6 = 0; i6 < i5; i6++) {
                BitmapFont.Glyph glyph = aVar.get(i6);
                fE += jVar.e(i6);
                addGlyph(glyph, fE, f4, floatBits);
            }
        }
        this.currentTint = Color.WHITE_FLOAT_BITS;
    }

    private void requireGlyphs(GlyphLayout glyphLayout) {
        if (this.pageVertices.length == 1) {
            int i2 = glyphLayout.runs.f1750b;
            int i3 = 0;
            for (int i4 = 0; i4 < i2; i4++) {
                i3 += glyphLayout.runs.get(i4).glyphs.f1750b;
            }
            requirePageGlyphs(0, i3);
            return;
        }
        int[] iArr = this.tempGlyphCount;
        int length = iArr.length;
        for (int i5 = 0; i5 < length; i5++) {
            iArr[i5] = 0;
        }
        int i6 = glyphLayout.runs.f1750b;
        for (int i7 = 0; i7 < i6; i7++) {
            a<BitmapFont.Glyph> aVar = glyphLayout.runs.get(i7).glyphs;
            int i8 = aVar.f1750b;
            for (int i9 = 0; i9 < i8; i9++) {
                int i10 = aVar.get(i9).page;
                iArr[i10] = iArr[i10] + 1;
            }
        }
        int length2 = iArr.length;
        for (int i11 = 0; i11 < length2; i11++) {
            requirePageGlyphs(i11, iArr[i11]);
        }
    }

    private void requirePageGlyphs(int i2, int i3) {
        o[] oVarArr = this.pageGlyphIndices;
        if (oVarArr != null) {
            o oVar = oVarArr[i2];
            if (i3 > oVar.f1849a.length) {
                oVar.c(i3 - oVar.f1850b);
            }
        }
        int i4 = this.idx[i2];
        int i5 = (i3 * 20) + i4;
        float[][] fArr = this.pageVertices;
        float[] fArr2 = fArr[i2];
        if (fArr2 == null) {
            fArr[i2] = new float[i5];
        } else if (fArr2.length < i5) {
            float[] fArr3 = new float[i5];
            System.arraycopy(fArr2, 0, fArr3, 0, i4);
            this.pageVertices[i2] = fArr3;
        }
    }

    public GlyphLayout addText(CharSequence charSequence, float f2, float f3) {
        return addText(charSequence, f2, f3, 0, charSequence.length(), 0.0f, 8, false, null);
    }

    public void clear() {
        this.f1683x = 0.0f;
        this.f1684y = 0.0f;
        d0.b(this.pooledLayouts);
        this.pooledLayouts.clear();
        this.layouts.clear();
        int length = this.idx.length;
        for (int i2 = 0; i2 < length; i2++) {
            o[] oVarArr = this.pageGlyphIndices;
            if (oVarArr != null) {
                oVarArr[i2].f1850b = 0;
            }
            this.idx[i2] = 0;
        }
    }

    public void draw(Batch batch) {
        a<TextureRegion> regions = this.font.getRegions();
        int length = this.pageVertices.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (this.idx[i2] > 0) {
                batch.draw(regions.get(i2).getTexture(), this.pageVertices[i2], 0, this.idx[i2]);
            }
        }
    }

    public Color getColor() {
        return this.color;
    }

    public BitmapFont getFont() {
        return this.font;
    }

    public a<GlyphLayout> getLayouts() {
        return this.layouts;
    }

    public int getVertexCount(int i2) {
        return this.idx[i2];
    }

    public float[] getVertices() {
        return getVertices(0);
    }

    public float getX() {
        return this.f1683x;
    }

    public float getY() {
        return this.f1684y;
    }

    public void setAlphas(float f2) {
        int i2 = ((int) (f2 * 254.0f)) << 24;
        int length = this.pageVertices.length;
        float f3 = 0.0f;
        float fH = 0.0f;
        for (int i3 = 0; i3 < length; i3++) {
            float[] fArr = this.pageVertices[i3];
            int i4 = this.idx[i3];
            for (int i5 = 2; i5 < i4; i5 += 5) {
                float f4 = fArr[i5];
                if (f4 != f3 || i5 == 2) {
                    fH = l.h((l.g(f4) & 16777215) | i2);
                    fArr[i5] = fH;
                    f3 = f4;
                } else {
                    fArr[i5] = fH;
                }
            }
        }
    }

    public void setColor(Color color) {
        this.color.set(color);
    }

    public void setColors(float f2) {
        int length = this.pageVertices.length;
        for (int i2 = 0; i2 < length; i2++) {
            float[] fArr = this.pageVertices[i2];
            int i3 = this.idx[i2];
            for (int i4 = 2; i4 < i3; i4 += 5) {
                fArr[i4] = f2;
            }
        }
    }

    public void setPosition(float f2, float f3) {
        translate(f2 - this.f1683x, f3 - this.f1684y);
    }

    public GlyphLayout setText(CharSequence charSequence, float f2, float f3) {
        clear();
        return addText(charSequence, f2, f3, 0, charSequence.length(), 0.0f, 8, false);
    }

    public void setUseIntegerPositions(boolean z2) {
        this.integer = z2;
    }

    public void tint(Color color) {
        float floatBits = color.toFloatBits();
        if (this.currentTint == floatBits) {
            return;
        }
        this.currentTint = floatBits;
        int[] iArr = this.tempGlyphCount;
        int length = iArr.length;
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3++) {
            iArr[i3] = 0;
        }
        int i4 = this.layouts.f1750b;
        int i5 = 0;
        while (i5 < i4) {
            GlyphLayout glyphLayout = this.layouts.get(i5);
            int i6 = glyphLayout.runs.f1750b;
            int i7 = i2;
            while (i7 < i6) {
                GlyphLayout.GlyphRun glyphRun = glyphLayout.runs.get(i7);
                a<BitmapFont.Glyph> aVar = glyphRun.glyphs;
                float floatBits2 = tempColor.set(glyphRun.color).mul(color).toFloatBits();
                int i8 = aVar.f1750b;
                int i9 = i2;
                while (i9 < i8) {
                    int i10 = aVar.get(i9).page;
                    int i11 = iArr[i10];
                    int i12 = (i11 * 20) + 2;
                    iArr[i10] = i11 + 1;
                    float[] fArr = this.pageVertices[i10];
                    for (int i13 = i2; i13 < 20; i13 += 5) {
                        fArr[i12 + i13] = floatBits2;
                    }
                    i9++;
                    i2 = 0;
                }
                i7++;
                i2 = 0;
            }
            i5++;
            i2 = 0;
        }
    }

    public void translate(float f2, float f3) {
        if (f2 == 0.0f && f3 == 0.0f) {
            return;
        }
        if (this.integer) {
            f2 = Math.round(f2);
            f3 = Math.round(f3);
        }
        this.f1683x += f2;
        this.f1684y += f3;
        float[][] fArr = this.pageVertices;
        int length = fArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            float[] fArr2 = fArr[i2];
            int i3 = this.idx[i2];
            for (int i4 = 0; i4 < i3; i4 += 5) {
                fArr2[i4] = fArr2[i4] + f2;
                int i5 = i4 + 1;
                fArr2[i5] = fArr2[i5] + f3;
            }
        }
    }

    public boolean usesIntegerPositions() {
        return this.integer;
    }

    public BitmapFontCache(BitmapFont bitmapFont, boolean z2) {
        this.layouts = new a<>();
        this.pooledLayouts = new a<>();
        this.color = new Color(1.0f, 1.0f, 1.0f, 1.0f);
        this.font = bitmapFont;
        this.integer = z2;
        int i2 = bitmapFont.regions.f1750b;
        if (i2 == 0) {
            throw new IllegalArgumentException("The specified font must contain at least one texture page.");
        }
        this.pageVertices = new float[i2][];
        this.idx = new int[i2];
        if (i2 > 1) {
            o[] oVarArr = new o[i2];
            this.pageGlyphIndices = oVarArr;
            int length = oVarArr.length;
            for (int i3 = 0; i3 < length; i3++) {
                this.pageGlyphIndices[i3] = new o();
            }
        }
        this.tempGlyphCount = new int[i2];
    }

    public GlyphLayout addText(CharSequence charSequence, float f2, float f3, float f4, int i2, boolean z2) {
        return addText(charSequence, f2, f3, 0, charSequence.length(), f4, i2, z2, null);
    }

    public float[] getVertices(int i2) {
        return this.pageVertices[i2];
    }

    public void setColor(float f2, float f3, float f4, float f5) {
        this.color.set(f2, f3, f4, f5);
    }

    public GlyphLayout addText(CharSequence charSequence, float f2, float f3, int i2, int i3, float f4, int i4, boolean z2) {
        return addText(charSequence, f2, f3, i2, i3, f4, i4, z2, null);
    }

    public GlyphLayout setText(CharSequence charSequence, float f2, float f3, float f4, int i2, boolean z2) {
        clear();
        return addText(charSequence, f2, f3, 0, charSequence.length(), f4, i2, z2);
    }

    public GlyphLayout addText(CharSequence charSequence, float f2, float f3, int i2, int i3, float f4, int i4, boolean z2, String str) {
        GlyphLayout glyphLayout = (GlyphLayout) d0.d(GlyphLayout.class);
        this.pooledLayouts.a(glyphLayout);
        glyphLayout.setText(this.font, charSequence, i2, i3, this.color, f4, i4, z2, str);
        addText(glyphLayout, f2, f3);
        return glyphLayout;
    }

    public void setColors(Color color) {
        setColors(color.toFloatBits());
    }

    public GlyphLayout setText(CharSequence charSequence, float f2, float f3, int i2, int i3, float f4, int i4, boolean z2) {
        clear();
        return addText(charSequence, f2, f3, i2, i3, f4, i4, z2);
    }

    public void draw(Batch batch, int i2, int i3) {
        if (this.pageVertices.length == 1) {
            batch.draw(this.font.getRegion().getTexture(), this.pageVertices[0], i2 * 20, (i3 - i2) * 20);
            return;
        }
        a<TextureRegion> regions = this.font.getRegions();
        int length = this.pageVertices.length;
        for (int i4 = 0; i4 < length; i4++) {
            o oVar = this.pageGlyphIndices[i4];
            int i5 = oVar.f1850b;
            int i6 = 0;
            int i7 = -1;
            for (int i8 = 0; i8 < i5; i8++) {
                int iD = oVar.d(i8);
                if (iD >= i3) {
                    break;
                }
                if (i7 == -1 && iD >= i2) {
                    i7 = i8;
                }
                if (iD >= i2) {
                    i6++;
                }
            }
            if (i7 != -1 && i6 != 0) {
                batch.draw(regions.get(i4).getTexture(), this.pageVertices[i4], i7 * 20, i6 * 20);
            }
        }
    }

    public void setColors(float f2, float f3, float f4, float f5) {
        int i2 = ((int) (f3 * 255.0f)) << 8;
        int i3 = (int) (f2 * 255.0f);
        setColors(l.h(i3 | i2 | (((int) (f4 * 255.0f)) << 16) | (((int) (f5 * 255.0f)) << 24)));
    }

    public void setColors(Color color, int i2, int i3) {
        setColors(color.toFloatBits(), i2, i3);
    }

    public GlyphLayout setText(CharSequence charSequence, float f2, float f3, int i2, int i3, float f4, int i4, boolean z2, String str) {
        clear();
        return addText(charSequence, f2, f3, i2, i3, f4, i4, z2, str);
    }

    public void addText(GlyphLayout glyphLayout, float f2, float f3) {
        addToCache(glyphLayout, f2, f3 + this.font.data.ascent);
    }

    public void setColors(float f2, int i2, int i3) {
        float[][] fArr = this.pageVertices;
        if (fArr.length == 1) {
            float[] fArr2 = fArr[0];
            int iMin = Math.min(i3 * 20, this.idx[0]);
            for (int i4 = (i2 * 20) + 2; i4 < iMin; i4 += 5) {
                fArr2[i4] = f2;
            }
            return;
        }
        int length = fArr.length;
        for (int i5 = 0; i5 < length; i5++) {
            float[] fArr3 = this.pageVertices[i5];
            o oVar = this.pageGlyphIndices[i5];
            int i6 = oVar.f1850b;
            for (int i7 = 0; i7 < i6; i7++) {
                int i8 = oVar.f1849a[i7];
                if (i8 >= i3) {
                    break;
                }
                if (i8 >= i2) {
                    for (int i9 = 0; i9 < 20; i9 += 5) {
                        fArr3[(i7 * 20) + 2 + i9] = f2;
                    }
                }
            }
        }
    }

    public void setText(GlyphLayout glyphLayout, float f2, float f3) {
        clear();
        addText(glyphLayout, f2, f3);
    }

    public void draw(Batch batch, float f2) {
        if (f2 == 1.0f) {
            draw(batch);
            return;
        }
        Color color = getColor();
        float f3 = color.f1677a;
        color.f1677a = f2 * f3;
        setColors(color);
        draw(batch);
        color.f1677a = f3;
        setColors(color);
    }
}
