package com.badlogic.gdx.graphics.g2d;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.a;
import com.badlogic.gdx.utils.i;
import com.badlogic.gdx.utils.j;
import com.badlogic.gdx.utils.m;
import com.badlogic.gdx.utils.n0;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class BitmapFont implements i {
    private static final int LOG2_PAGE_SIZE = 9;
    private static final int PAGES = 128;
    private static final int PAGE_SIZE = 512;
    private final BitmapFontCache cache;
    final BitmapFontData data;
    private boolean flipped;
    boolean integer;
    private boolean ownsTexture;
    a<TextureRegion> regions;

    public static class Glyph {
        public boolean fixedWidth;
        public int height;
        public int id;
        public byte[][] kerning;
        public int page = 0;
        public int srcX;
        public int srcY;

        /* JADX INFO: renamed from: u, reason: collision with root package name */
        public float f1681u;
        public float u2;

        /* JADX INFO: renamed from: v, reason: collision with root package name */
        public float f1682v;
        public float v2;
        public int width;
        public int xadvance;
        public int xoffset;
        public int yoffset;

        public int getKerning(char c2) {
            byte[] bArr;
            byte[][] bArr2 = this.kerning;
            if (bArr2 == null || (bArr = bArr2[c2 >>> '\t']) == null) {
                return 0;
            }
            return bArr[c2 & 511];
        }

        public void setKerning(int i2, int i3) {
            if (this.kerning == null) {
                this.kerning = new byte[128][];
            }
            byte[][] bArr = this.kerning;
            int i4 = i2 >>> 9;
            byte[] bArr2 = bArr[i4];
            if (bArr2 == null) {
                bArr2 = new byte[512];
                bArr[i4] = bArr2;
            }
            bArr2[i2 & 511] = (byte) i3;
        }

        public String toString() {
            return Character.toString((char) this.id);
        }
    }

    public BitmapFont() {
        this(Gdx.files.classpath("com/badlogic/gdx/utils/arial-15.fnt"), Gdx.files.classpath("com/badlogic/gdx/utils/arial-15.png"), false, true);
    }

    static int indexOf(CharSequence charSequence, char c2, int i2) {
        int length = charSequence.length();
        while (i2 < length) {
            if (charSequence.charAt(i2) == c2) {
                return i2;
            }
            i2++;
        }
        return length;
    }

    @Override // com.badlogic.gdx.utils.i
    public void dispose() {
        if (!this.ownsTexture) {
            return;
        }
        int i2 = 0;
        while (true) {
            a<TextureRegion> aVar = this.regions;
            if (i2 >= aVar.f1750b) {
                return;
            }
            aVar.get(i2).getTexture().dispose();
            i2++;
        }
    }

    public GlyphLayout draw(Batch batch, CharSequence charSequence, float f2, float f3) {
        this.cache.clear();
        GlyphLayout glyphLayoutAddText = this.cache.addText(charSequence, f2, f3);
        this.cache.draw(batch);
        return glyphLayoutAddText;
    }

    public float getAscent() {
        return this.data.ascent;
    }

    public BitmapFontCache getCache() {
        return this.cache;
    }

    public float getCapHeight() {
        return this.data.capHeight;
    }

    public Color getColor() {
        return this.cache.getColor();
    }

    public BitmapFontData getData() {
        return this.data;
    }

    public float getDescent() {
        return this.data.descent;
    }

    public float getLineHeight() {
        return this.data.lineHeight;
    }

    public TextureRegion getRegion() {
        return this.regions.g();
    }

    public a<TextureRegion> getRegions() {
        return this.regions;
    }

    public float getScaleX() {
        return this.data.scaleX;
    }

    public float getScaleY() {
        return this.data.scaleY;
    }

    public float getSpaceXadvance() {
        return this.data.spaceXadvance;
    }

    public float getXHeight() {
        return this.data.xHeight;
    }

    public boolean isFlipped() {
        return this.flipped;
    }

    protected void load(BitmapFontData bitmapFontData) {
        for (Glyph[] glyphArr : bitmapFontData.glyphs) {
            if (glyphArr != null) {
                for (Glyph glyph : glyphArr) {
                    if (glyph != null) {
                        bitmapFontData.setGlyphRegion(glyph, this.regions.get(glyph.page));
                    }
                }
            }
        }
        Glyph glyph2 = bitmapFontData.missingGlyph;
        if (glyph2 != null) {
            bitmapFontData.setGlyphRegion(glyph2, this.regions.get(glyph2.page));
        }
    }

    public BitmapFontCache newFontCache() {
        return new BitmapFontCache(this, this.integer);
    }

    public boolean ownsTexture() {
        return this.ownsTexture;
    }

    public void setColor(Color color) {
        this.cache.getColor().set(color);
    }

    public void setFixedWidthGlyphs(CharSequence charSequence) {
        int i2;
        BitmapFontData bitmapFontData = this.data;
        int length = charSequence.length();
        int i3 = 0;
        for (int i4 = 0; i4 < length; i4++) {
            Glyph glyph = bitmapFontData.getGlyph(charSequence.charAt(i4));
            if (glyph != null && (i2 = glyph.xadvance) > i3) {
                i3 = i2;
            }
        }
        int length2 = charSequence.length();
        for (int i5 = 0; i5 < length2; i5++) {
            Glyph glyph2 = bitmapFontData.getGlyph(charSequence.charAt(i5));
            if (glyph2 != null) {
                glyph2.xoffset = ((i3 - glyph2.xadvance) / 2) + glyph2.xoffset;
                glyph2.xadvance = i3;
                glyph2.kerning = null;
                glyph2.fixedWidth = true;
            }
        }
    }

    public void setOwnsTexture(boolean z2) {
        this.ownsTexture = z2;
    }

    public void setUseIntegerPositions(boolean z2) {
        this.integer = z2;
        this.cache.setUseIntegerPositions(z2);
    }

    public String toString() {
        String str = this.data.name;
        return str != null ? str : super.toString();
    }

    public boolean usesIntegerPositions() {
        return this.integer;
    }

    public BitmapFont(boolean z2) {
        this(Gdx.files.classpath("com/badlogic/gdx/utils/arial-15.fnt"), Gdx.files.classpath("com/badlogic/gdx/utils/arial-15.png"), z2, true);
    }

    public TextureRegion getRegion(int i2) {
        return this.regions.get(i2);
    }

    public void setColor(float f2, float f3, float f4, float f5) {
        this.cache.getColor().set(f2, f3, f4, f5);
    }

    public BitmapFont(com.badlogic.gdx.files.a aVar, TextureRegion textureRegion) {
        this(aVar, textureRegion, false);
    }

    public BitmapFont(com.badlogic.gdx.files.a aVar, TextureRegion textureRegion, boolean z2) {
        this(new BitmapFontData(aVar, z2), textureRegion, true);
    }

    public GlyphLayout draw(Batch batch, CharSequence charSequence, float f2, float f3, float f4, int i2, boolean z2) {
        this.cache.clear();
        GlyphLayout glyphLayoutAddText = this.cache.addText(charSequence, f2, f3, f4, i2, z2);
        this.cache.draw(batch);
        return glyphLayoutAddText;
    }

    public BitmapFont(com.badlogic.gdx.files.a aVar) {
        this(aVar, false);
    }

    public BitmapFont(com.badlogic.gdx.files.a aVar, boolean z2) {
        this(new BitmapFontData(aVar, z2), (TextureRegion) null, true);
    }

    public BitmapFont(com.badlogic.gdx.files.a aVar, com.badlogic.gdx.files.a aVar2, boolean z2) {
        this(aVar, aVar2, z2, true);
    }

    public GlyphLayout draw(Batch batch, CharSequence charSequence, float f2, float f3, int i2, int i3, float f4, int i4, boolean z2) {
        this.cache.clear();
        GlyphLayout glyphLayoutAddText = this.cache.addText(charSequence, f2, f3, i2, i3, f4, i4, z2);
        this.cache.draw(batch);
        return glyphLayoutAddText;
    }

    public static class BitmapFontData {
        public float ascent;
        public float blankLineScale;
        public char[] breakChars;
        public char[] capChars;
        public float capHeight;
        public float cursorX;
        public float descent;
        public float down;
        public boolean flipped;
        public com.badlogic.gdx.files.a fontFile;
        public final Glyph[][] glyphs;
        public String[] imagePaths;
        public float lineHeight;
        public boolean markupEnabled;
        public Glyph missingGlyph;
        public String name;
        public float padBottom;
        public float padLeft;
        public float padRight;
        public float padTop;
        public float scaleX;
        public float scaleY;
        public float spaceXadvance;
        public char[] xChars;
        public float xHeight;

        public BitmapFontData() {
            this.capHeight = 1.0f;
            this.blankLineScale = 1.0f;
            this.scaleX = 1.0f;
            this.scaleY = 1.0f;
            this.glyphs = new Glyph[128][];
            this.xHeight = 1.0f;
            this.xChars = new char[]{'x', 'e', 'a', 'o', 'n', 's', 'r', 'c', 'u', 'm', 'v', 'w', 'z'};
            this.capChars = new char[]{'M', 'N', 'B', 'D', 'C', 'E', 'F', 'K', 'A', 'G', 'H', 'I', 'J', 'L', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        }

        public Glyph getFirstGlyph() {
            for (Glyph[] glyphArr : this.glyphs) {
                if (glyphArr != null) {
                    for (Glyph glyph : glyphArr) {
                        if (glyph != null && glyph.height != 0 && glyph.width != 0) {
                            return glyph;
                        }
                    }
                }
            }
            throw new m("No glyphs found.");
        }

        public com.badlogic.gdx.files.a getFontFile() {
            return this.fontFile;
        }

        public Glyph getGlyph(char c2) {
            Glyph[] glyphArr = this.glyphs[c2 / 512];
            if (glyphArr != null) {
                return glyphArr[c2 & 511];
            }
            return null;
        }

        public void getGlyphs(GlyphLayout.GlyphRun glyphRun, CharSequence charSequence, int i2, int i3, Glyph glyph) {
            Glyph glyph2;
            int i4 = i3 - i2;
            if (i4 == 0) {
                return;
            }
            boolean z2 = this.markupEnabled;
            float f2 = this.scaleX;
            a<Glyph> aVar = glyphRun.glyphs;
            j jVar = glyphRun.xAdvances;
            aVar.f(i4);
            glyphRun.xAdvances.d(i4 + 1);
            do {
                int i5 = i2 + 1;
                char cCharAt = charSequence.charAt(i2);
                if (cCharAt == '\r' || ((glyph2 = getGlyph(cCharAt)) == null && (glyph2 = this.missingGlyph) == null)) {
                    i2 = i5;
                } else {
                    aVar.a(glyph2);
                    jVar.a(glyph == null ? glyph2.fixedWidth ? 0.0f : ((-glyph2.xoffset) * f2) - this.padLeft : (glyph.getKerning(cCharAt) + glyph.xadvance) * f2);
                    i2 = (z2 && cCharAt == '[' && i5 < i3 && charSequence.charAt(i5) == '[') ? i2 + 2 : i5;
                    glyph = glyph2;
                }
            } while (i2 < i3);
            if (glyph != null) {
                jVar.a(glyph.fixedWidth ? glyph.xadvance * f2 : ((glyph.width + glyph.xoffset) * f2) - this.padRight);
            }
        }

        public String getImagePath(int i2) {
            return this.imagePaths[i2];
        }

        public String[] getImagePaths() {
            return this.imagePaths;
        }

        public int getWrapIndex(a<Glyph> aVar, int i2) {
            int i3 = i2 - 1;
            Glyph[] glyphArr = aVar.f1749a;
            char c2 = (char) glyphArr[i3].id;
            if (isWhitespace(c2)) {
                return i3;
            }
            if (isBreakChar(c2)) {
                i3 = i2 - 2;
            }
            while (i3 > 0) {
                char c3 = (char) glyphArr[i3].id;
                if (isWhitespace(c3) || isBreakChar(c3)) {
                    return i3 + 1;
                }
                i3--;
            }
            return 0;
        }

        public boolean hasGlyph(char c2) {
            return (this.missingGlyph == null && getGlyph(c2) == null) ? false : true;
        }

        public boolean isBreakChar(char c2) {
            char[] cArr = this.breakChars;
            if (cArr == null) {
                return false;
            }
            for (char c3 : cArr) {
                if (c2 == c3) {
                    return true;
                }
            }
            return false;
        }

        public boolean isWhitespace(char c2) {
            return c2 == '\t' || c2 == '\n' || c2 == '\r' || c2 == ' ';
        }

        public void load(com.badlogic.gdx.files.a aVar, boolean z2) throws Throwable {
            int iMax;
            String line;
            float f2;
            float f3;
            float f4;
            float f5;
            float f6;
            float f7;
            BufferedReader bufferedReader;
            Glyph[][] glyphArr;
            int i2;
            Glyph[] glyphArr2;
            String str;
            if (this.imagePaths != null) {
                throw new IllegalStateException("Already loaded.");
            }
            this.name = aVar.nameWithoutExtension();
            BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(aVar.read()), 512);
            try {
                try {
                    String line2 = bufferedReader2.readLine();
                    try {
                        if (line2 == null) {
                            throw new m("File is empty.");
                        }
                        String strSubstring = line2.substring(line2.indexOf("padding=") + 8);
                        boolean z3 = false;
                        if (strSubstring.substring(0, strSubstring.indexOf(32)).split(",", 4).length != 4) {
                            throw new m("Invalid padding.");
                        }
                        this.padTop = Integer.parseInt(r3[0]);
                        boolean z4 = true;
                        this.padRight = Integer.parseInt(r3[1]);
                        this.padBottom = Integer.parseInt(r3[2]);
                        this.padLeft = Integer.parseInt(r3[3]);
                        float f8 = this.padTop + this.padBottom;
                        String line3 = bufferedReader2.readLine();
                        if (line3 == null) {
                            throw new m("Missing common header.");
                        }
                        String[] strArrSplit = line3.split(" ", 9);
                        if (strArrSplit.length < 3) {
                            throw new m("Invalid common header.");
                        }
                        if (!strArrSplit[1].startsWith("lineHeight=")) {
                            throw new m("Missing: lineHeight");
                        }
                        this.lineHeight = Integer.parseInt(strArrSplit[1].substring(11));
                        if (!strArrSplit[2].startsWith("base=")) {
                            throw new m("Missing: base");
                        }
                        float f9 = Integer.parseInt(strArrSplit[2].substring(5));
                        if (strArrSplit.length < 6 || (str = strArrSplit[5]) == null || !str.startsWith("pages=")) {
                            iMax = 1;
                        } else {
                            try {
                                iMax = Math.max(1, Integer.parseInt(strArrSplit[5].substring(6)));
                            } catch (NumberFormatException unused) {
                                iMax = 1;
                            }
                        }
                        this.imagePaths = new String[iMax];
                        for (int i3 = 0; i3 < iMax; i3++) {
                            String line4 = bufferedReader2.readLine();
                            if (line4 == null) {
                                throw new m("Missing additional page definitions.");
                            }
                            Matcher matcher = Pattern.compile(".*id=(\\d+)").matcher(line4);
                            if (matcher.find()) {
                                String strGroup = matcher.group(1);
                                try {
                                    if (Integer.parseInt(strGroup) != i3) {
                                        throw new m("Page IDs must be indices starting at 0: " + strGroup);
                                    }
                                } catch (NumberFormatException e2) {
                                    throw new m("Invalid page id: " + strGroup, e2);
                                }
                            }
                            Matcher matcher2 = Pattern.compile(".*file=\"?([^\"]+)\"?").matcher(line4);
                            if (!matcher2.find()) {
                                throw new m("Missing: file");
                            }
                            this.imagePaths[i3] = aVar.parent().child(matcher2.group(1)).path().replaceAll("\\\\", "/");
                        }
                        float f10 = 0.0f;
                        this.descent = 0.0f;
                        while (true) {
                            String line5 = bufferedReader2.readLine();
                            if (line5 == null || line5.startsWith("kernings ") || line5.startsWith("metrics ")) {
                                break;
                            }
                            BufferedReader bufferedReader3 = bufferedReader2;
                            if (line5.startsWith("char ")) {
                                Glyph glyph = new Glyph();
                                StringTokenizer stringTokenizer = new StringTokenizer(line5, " =");
                                stringTokenizer.nextToken();
                                stringTokenizer.nextToken();
                                int i4 = Integer.parseInt(stringTokenizer.nextToken());
                                if (i4 <= 0) {
                                    this.missingGlyph = glyph;
                                } else if (i4 <= 65535) {
                                    setGlyph(i4, glyph);
                                }
                                glyph.id = i4;
                                stringTokenizer.nextToken();
                                glyph.srcX = Integer.parseInt(stringTokenizer.nextToken());
                                stringTokenizer.nextToken();
                                glyph.srcY = Integer.parseInt(stringTokenizer.nextToken());
                                stringTokenizer.nextToken();
                                glyph.width = Integer.parseInt(stringTokenizer.nextToken());
                                stringTokenizer.nextToken();
                                glyph.height = Integer.parseInt(stringTokenizer.nextToken());
                                stringTokenizer.nextToken();
                                glyph.xoffset = Integer.parseInt(stringTokenizer.nextToken());
                                stringTokenizer.nextToken();
                                if (z2) {
                                    glyph.yoffset = Integer.parseInt(stringTokenizer.nextToken());
                                } else {
                                    glyph.yoffset = -(glyph.height + Integer.parseInt(stringTokenizer.nextToken()));
                                }
                                stringTokenizer.nextToken();
                                glyph.xadvance = Integer.parseInt(stringTokenizer.nextToken());
                                if (stringTokenizer.hasMoreTokens()) {
                                    stringTokenizer.nextToken();
                                }
                                if (stringTokenizer.hasMoreTokens()) {
                                    try {
                                        glyph.page = Integer.parseInt(stringTokenizer.nextToken());
                                    } catch (NumberFormatException unused2) {
                                    }
                                }
                                if (glyph.width > 0 && glyph.height > 0) {
                                    this.descent = Math.min(glyph.yoffset + f9, this.descent);
                                }
                            }
                            bufferedReader2 = bufferedReader3;
                            z3 = false;
                        }
                        this.descent += this.padBottom;
                        while (true) {
                            line = bufferedReader2.readLine();
                            if (line == null || !line.startsWith("kerning ")) {
                                break;
                            }
                            BufferedReader bufferedReader4 = bufferedReader2;
                            StringTokenizer stringTokenizer2 = new StringTokenizer(line, " =");
                            stringTokenizer2.nextToken();
                            stringTokenizer2.nextToken();
                            int i5 = Integer.parseInt(stringTokenizer2.nextToken());
                            stringTokenizer2.nextToken();
                            int i6 = Integer.parseInt(stringTokenizer2.nextToken());
                            if (i5 >= 0 && i5 <= 65535 && i6 >= 0 && i6 <= 65535) {
                                Glyph glyph2 = getGlyph((char) i5);
                                stringTokenizer2.nextToken();
                                int i7 = Integer.parseInt(stringTokenizer2.nextToken());
                                if (glyph2 != null) {
                                    glyph2.setKerning(i6, i7);
                                }
                            }
                            bufferedReader2 = bufferedReader4;
                            z3 = false;
                        }
                        if (line == null || !line.startsWith("metrics ")) {
                            z4 = z3;
                            f2 = 0.0f;
                            f3 = 0.0f;
                            f4 = 0.0f;
                            f5 = 0.0f;
                            f6 = 0.0f;
                            f7 = 0.0f;
                        } else {
                            StringTokenizer stringTokenizer3 = new StringTokenizer(line, " =");
                            stringTokenizer3.nextToken();
                            stringTokenizer3.nextToken();
                            float f11 = Float.parseFloat(stringTokenizer3.nextToken());
                            stringTokenizer3.nextToken();
                            f3 = Float.parseFloat(stringTokenizer3.nextToken());
                            stringTokenizer3.nextToken();
                            f4 = Float.parseFloat(stringTokenizer3.nextToken());
                            stringTokenizer3.nextToken();
                            f5 = Float.parseFloat(stringTokenizer3.nextToken());
                            stringTokenizer3.nextToken();
                            f6 = Float.parseFloat(stringTokenizer3.nextToken());
                            stringTokenizer3.nextToken();
                            f7 = Float.parseFloat(stringTokenizer3.nextToken());
                            stringTokenizer3.nextToken();
                            f2 = Float.parseFloat(stringTokenizer3.nextToken());
                            f10 = f11;
                        }
                        Glyph glyph3 = getGlyph(' ');
                        if (glyph3 == null) {
                            glyph3 = new Glyph();
                            glyph3.id = 32;
                            Glyph glyph4 = getGlyph('l');
                            if (glyph4 == null) {
                                glyph4 = getFirstGlyph();
                            }
                            glyph3.xadvance = glyph4.xadvance;
                            setGlyph(32, glyph3);
                        }
                        if (glyph3.width == 0) {
                            float f12 = this.padLeft;
                            bufferedReader = bufferedReader2;
                            glyph3.width = (int) (glyph3.xadvance + f12 + this.padRight);
                            glyph3.xoffset = (int) (-f12);
                        } else {
                            bufferedReader = bufferedReader2;
                        }
                        this.spaceXadvance = glyph3.xadvance;
                        Glyph firstGlyph = null;
                        for (char c2 : this.xChars) {
                            firstGlyph = getGlyph(c2);
                            if (firstGlyph != null) {
                                break;
                            }
                        }
                        if (firstGlyph == null) {
                            firstGlyph = getFirstGlyph();
                        }
                        this.xHeight = firstGlyph.height - f8;
                        Glyph glyph5 = null;
                        for (char c3 : this.capChars) {
                            glyph5 = getGlyph(c3);
                            if (glyph5 != null) {
                                break;
                            }
                        }
                        if (glyph5 == null) {
                            Glyph[][] glyphArr3 = this.glyphs;
                            int length = glyphArr3.length;
                            int i8 = 0;
                            while (i8 < length) {
                                Glyph[] glyphArr4 = glyphArr3[i8];
                                if (glyphArr4 == null) {
                                    glyphArr = glyphArr3;
                                    i2 = length;
                                } else {
                                    glyphArr = glyphArr3;
                                    int length2 = glyphArr4.length;
                                    i2 = length;
                                    int i9 = 0;
                                    while (i9 < length2) {
                                        int i10 = length2;
                                        Glyph glyph6 = glyphArr4[i9];
                                        if (glyph6 != null) {
                                            glyphArr2 = glyphArr4;
                                            int i11 = glyph6.height;
                                            if (i11 != 0 && glyph6.width != 0) {
                                                this.capHeight = Math.max(this.capHeight, i11);
                                            }
                                        } else {
                                            glyphArr2 = glyphArr4;
                                        }
                                        i9++;
                                        length2 = i10;
                                        glyphArr4 = glyphArr2;
                                    }
                                }
                                i8++;
                                glyphArr3 = glyphArr;
                                length = i2;
                            }
                        } else {
                            this.capHeight = glyph5.height;
                        }
                        float f13 = this.capHeight - f8;
                        this.capHeight = f13;
                        float f14 = f9 - f13;
                        this.ascent = f14;
                        float f15 = -this.lineHeight;
                        this.down = f15;
                        if (z2) {
                            this.ascent = -f14;
                            this.down = -f15;
                        }
                        if (z4) {
                            this.ascent = f10;
                            this.descent = f3;
                            this.down = f4;
                            this.capHeight = f5;
                            this.lineHeight = f6;
                            this.spaceXadvance = f7;
                            this.xHeight = f2;
                        }
                        n0.a(bufferedReader);
                    } catch (Exception e3) {
                        e = e3;
                        throw new m("Error loading font file: " + aVar, e);
                    }
                } catch (Throwable th) {
                    th = th;
                    Throwable th2 = th;
                    n0.a(bufferedReader2);
                    throw th2;
                }
            } catch (Exception e4) {
                e = e4;
            } catch (Throwable th3) {
                th = th3;
                Throwable th22 = th;
                n0.a(bufferedReader2);
                throw th22;
            }
        }

        public void scale(float f2) {
            setScale(this.scaleX + f2, this.scaleY + f2);
        }

        public void setGlyph(int i2, Glyph glyph) {
            Glyph[][] glyphArr = this.glyphs;
            int i3 = i2 / 512;
            Glyph[] glyphArr2 = glyphArr[i3];
            if (glyphArr2 == null) {
                glyphArr2 = new Glyph[512];
                glyphArr[i3] = glyphArr2;
            }
            glyphArr2[i2 & 511] = glyph;
        }

        /* JADX WARN: Removed duplicated region for block: B:14:0x006c A[PHI: r1 r11
          0x006c: PHI (r1v4 float) = (r1v3 float), (r1v21 float) binds: [B:7:0x004a, B:12:0x0061] A[DONT_GENERATE, DONT_INLINE]
          0x006c: PHI (r11v1 float) = (r11v0 float), (r11v6 float) binds: [B:7:0x004a, B:12:0x0061] A[DONT_GENERATE, DONT_INLINE]] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public void setGlyphRegion(Glyph glyph, TextureRegion textureRegion) {
            float f2;
            float f3;
            Texture texture = textureRegion.getTexture();
            float width = 1.0f / texture.getWidth();
            float height = 1.0f / texture.getHeight();
            float f4 = textureRegion.f1698u;
            float f5 = textureRegion.f1699v;
            float regionWidth = textureRegion.getRegionWidth();
            float regionHeight = textureRegion.getRegionHeight();
            float f6 = 0.0f;
            if (textureRegion instanceof TextureAtlas.AtlasRegion) {
                TextureAtlas.AtlasRegion atlasRegion = (TextureAtlas.AtlasRegion) textureRegion;
                f2 = atlasRegion.offsetX;
                f3 = (atlasRegion.originalHeight - atlasRegion.packedHeight) - atlasRegion.offsetY;
            } else {
                f2 = 0.0f;
                f3 = 0.0f;
            }
            int i2 = glyph.srcX;
            float f7 = i2;
            int i3 = glyph.width;
            float f8 = i2 + i3;
            int i4 = glyph.srcY;
            float f9 = i4;
            int i5 = glyph.height;
            float f10 = i4 + i5;
            if (f2 > 0.0f) {
                f7 -= f2;
                if (f7 < 0.0f) {
                    glyph.width = (int) (i3 + f7);
                    glyph.xoffset = (int) (glyph.xoffset - f7);
                    f7 = 0.0f;
                }
                f8 -= f2;
                if (f8 > regionWidth) {
                    glyph.width = (int) (glyph.width - (f8 - regionWidth));
                } else {
                    regionWidth = f8;
                }
            }
            if (f3 > 0.0f) {
                float f11 = f9 - f3;
                if (f11 < 0.0f) {
                    int i6 = (int) (i5 + f11);
                    glyph.height = i6;
                    if (i6 < 0) {
                        glyph.height = 0;
                    }
                } else {
                    f6 = f11;
                }
                f10 -= f3;
                if (f10 > regionHeight) {
                    float f12 = f10 - regionHeight;
                    glyph.height = (int) (glyph.height - f12);
                    glyph.yoffset = (int) (glyph.yoffset + f12);
                    f9 = f6;
                } else {
                    f9 = f6;
                    regionHeight = f10;
                }
            } else {
                regionHeight = f10;
            }
            glyph.f1681u = (f7 * width) + f4;
            glyph.u2 = (regionWidth * width) + f4;
            if (this.flipped) {
                glyph.f1682v = (f9 * height) + f5;
                glyph.v2 = (regionHeight * height) + f5;
            } else {
                glyph.v2 = (f9 * height) + f5;
                glyph.f1682v = (regionHeight * height) + f5;
            }
        }

        public void setLineHeight(float f2) {
            float f3 = f2 * this.scaleY;
            this.lineHeight = f3;
            if (!this.flipped) {
                f3 = -f3;
            }
            this.down = f3;
        }

        public void setScale(float f2, float f3) {
            if (f2 == 0.0f) {
                throw new IllegalArgumentException("scaleX cannot be 0.");
            }
            if (f3 == 0.0f) {
                throw new IllegalArgumentException("scaleY cannot be 0.");
            }
            float f4 = f2 / this.scaleX;
            float f5 = f3 / this.scaleY;
            this.lineHeight *= f5;
            this.spaceXadvance *= f4;
            this.xHeight *= f5;
            this.capHeight *= f5;
            this.ascent *= f5;
            this.descent *= f5;
            this.down *= f5;
            this.padLeft *= f4;
            this.padRight *= f4;
            this.padTop *= f5;
            this.padBottom *= f5;
            this.scaleX = f2;
            this.scaleY = f3;
        }

        public String toString() {
            String str = this.name;
            return str != null ? str : super.toString();
        }

        public BitmapFontData(com.badlogic.gdx.files.a aVar, boolean z2) throws Throwable {
            this.capHeight = 1.0f;
            this.blankLineScale = 1.0f;
            this.scaleX = 1.0f;
            this.scaleY = 1.0f;
            this.glyphs = new Glyph[128][];
            this.xHeight = 1.0f;
            this.xChars = new char[]{'x', 'e', 'a', 'o', 'n', 's', 'r', 'c', 'u', 'm', 'v', 'w', 'z'};
            this.capChars = new char[]{'M', 'N', 'B', 'D', 'C', 'E', 'F', 'K', 'A', 'G', 'H', 'I', 'J', 'L', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
            this.fontFile = aVar;
            this.flipped = z2;
            load(aVar, z2);
        }

        public void setScale(float f2) {
            setScale(f2, f2);
        }
    }

    public BitmapFont(com.badlogic.gdx.files.a aVar, com.badlogic.gdx.files.a aVar2, boolean z2, boolean z3) {
        this(new BitmapFontData(aVar, z2), new TextureRegion(new Texture(aVar2, false)), z3);
        this.ownsTexture = true;
    }

    public BitmapFont(BitmapFontData bitmapFontData, TextureRegion textureRegion, boolean z2) {
        this(bitmapFontData, (a<TextureRegion>) (textureRegion != null ? new a(new TextureRegion[]{textureRegion}) : null), z2);
    }

    public GlyphLayout draw(Batch batch, CharSequence charSequence, float f2, float f3, int i2, int i3, float f4, int i4, boolean z2, String str) {
        this.cache.clear();
        GlyphLayout glyphLayoutAddText = this.cache.addText(charSequence, f2, f3, i2, i3, f4, i4, z2, str);
        this.cache.draw(batch);
        return glyphLayoutAddText;
    }

    public BitmapFont(BitmapFontData bitmapFontData, a<TextureRegion> aVar, boolean z2) {
        com.badlogic.gdx.files.a fileHandle;
        this.flipped = bitmapFontData.flipped;
        this.data = bitmapFontData;
        this.integer = z2;
        if (aVar != null && aVar.f1750b != 0) {
            this.regions = aVar;
            this.ownsTexture = false;
        } else {
            String[] strArr = bitmapFontData.imagePaths;
            if (strArr != null) {
                int length = strArr.length;
                this.regions = new a<>(true, length);
                for (int i2 = 0; i2 < length; i2++) {
                    com.badlogic.gdx.files.a aVar2 = bitmapFontData.fontFile;
                    if (aVar2 == null) {
                        fileHandle = Gdx.files.internal(bitmapFontData.imagePaths[i2]);
                    } else {
                        fileHandle = Gdx.files.getFileHandle(bitmapFontData.imagePaths[i2], aVar2.type());
                    }
                    this.regions.a(new TextureRegion(new Texture(fileHandle, false)));
                }
                this.ownsTexture = true;
            } else {
                throw new IllegalArgumentException("If no regions are specified, the font data must have an images path.");
            }
        }
        this.cache = newFontCache();
        load(bitmapFontData);
    }

    public void draw(Batch batch, GlyphLayout glyphLayout, float f2, float f3) {
        this.cache.clear();
        this.cache.addText(glyphLayout, f2, f3);
        this.cache.draw(batch);
    }
}
