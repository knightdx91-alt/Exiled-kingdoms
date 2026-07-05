package com.badlogic.gdx.graphics.g2d;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.a;
import com.badlogic.gdx.utils.i;
import com.badlogic.gdx.utils.m;
import com.badlogic.gdx.utils.n0;
import com.badlogic.gdx.utils.y;
import com.badlogic.gdx.utils.z;
import com.google.android.gms.common.api.Api;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class TextureAtlas implements i {
    private final a<AtlasRegion> regions;
    private final z<Texture> textures;
    static final String[] tuple = new String[4];
    static final Comparator<TextureAtlasData.Region> indexComparator = new Comparator<TextureAtlasData.Region>() { // from class: com.badlogic.gdx.graphics.g2d.TextureAtlas.1
        @Override // java.util.Comparator
        public int compare(TextureAtlasData.Region region, TextureAtlasData.Region region2) {
            int i2 = region.index;
            int i3 = Api.BaseClientBuilder.API_PRIORITY_OTHER;
            if (i2 == -1) {
                i2 = Integer.MAX_VALUE;
            }
            int i4 = region2.index;
            if (i4 != -1) {
                i3 = i4;
            }
            return i2 - i3;
        }
    };

    public static class TextureAtlasData {
        final a<Page> pages = new a<>();
        final a<Region> regions = new a<>();

        public static class Page {
            public final Pixmap.Format format;
            public final float height;
            public final Texture.TextureFilter magFilter;
            public final Texture.TextureFilter minFilter;
            public Texture texture;
            public final com.badlogic.gdx.files.a textureFile;
            public final Texture.TextureWrap uWrap;
            public final boolean useMipMaps;
            public final Texture.TextureWrap vWrap;
            public final float width;

            public Page(com.badlogic.gdx.files.a aVar, float f2, float f3, boolean z2, Pixmap.Format format, Texture.TextureFilter textureFilter, Texture.TextureFilter textureFilter2, Texture.TextureWrap textureWrap, Texture.TextureWrap textureWrap2) {
                this.width = f2;
                this.height = f3;
                this.textureFile = aVar;
                this.useMipMaps = z2;
                this.format = format;
                this.minFilter = textureFilter;
                this.magFilter = textureFilter2;
                this.uWrap = textureWrap;
                this.vWrap = textureWrap2;
            }
        }

        public static class Region {
            public int degrees;
            public boolean flip;
            public int height;
            public int index;
            public int left;
            public String name;
            public float offsetX;
            public float offsetY;
            public int originalHeight;
            public int originalWidth;
            public int[] pads;
            public Page page;
            public boolean rotate;
            public int[] splits;
            public int top;
            public int width;
        }

        public TextureAtlasData(com.badlogic.gdx.files.a aVar, com.badlogic.gdx.files.a aVar2, boolean z2) {
            float f2;
            float f3;
            Texture.TextureWrap textureWrap;
            Texture.TextureWrap textureWrap2;
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(aVar.read()), 64);
            while (true) {
                Page page = null;
                while (true) {
                    try {
                        try {
                            String line = bufferedReader.readLine();
                            if (line == null) {
                                n0.a(bufferedReader);
                                this.regions.sort(TextureAtlas.indexComparator);
                                return;
                            }
                            if (line.trim().length() == 0) {
                                break;
                            }
                            if (page == null) {
                                com.badlogic.gdx.files.a aVarChild = aVar2.child(line);
                                if (TextureAtlas.readTuple(bufferedReader) == 2) {
                                    String[] strArr = TextureAtlas.tuple;
                                    float f4 = Integer.parseInt(strArr[0]);
                                    float f5 = Integer.parseInt(strArr[1]);
                                    TextureAtlas.readTuple(bufferedReader);
                                    f3 = f5;
                                    f2 = f4;
                                } else {
                                    f2 = 0.0f;
                                    f3 = 0.0f;
                                }
                                String[] strArr2 = TextureAtlas.tuple;
                                Pixmap.Format formatValueOf = Pixmap.Format.valueOf(strArr2[0]);
                                TextureAtlas.readTuple(bufferedReader);
                                Texture.TextureFilter textureFilterValueOf = Texture.TextureFilter.valueOf(strArr2[0]);
                                Texture.TextureFilter textureFilterValueOf2 = Texture.TextureFilter.valueOf(strArr2[1]);
                                String value = TextureAtlas.readValue(bufferedReader);
                                Texture.TextureWrap textureWrap3 = Texture.TextureWrap.ClampToEdge;
                                if (value.equals("x")) {
                                    textureWrap = Texture.TextureWrap.Repeat;
                                    textureWrap2 = textureWrap3;
                                } else if (value.equals("y")) {
                                    textureWrap2 = Texture.TextureWrap.Repeat;
                                    textureWrap = textureWrap3;
                                } else {
                                    textureWrap = value.equals("xy") ? Texture.TextureWrap.Repeat : textureWrap3;
                                    textureWrap2 = textureWrap;
                                }
                                page = new Page(aVarChild, f2, f3, textureFilterValueOf.isMipMap(), formatValueOf, textureFilterValueOf, textureFilterValueOf2, textureWrap, textureWrap2);
                                this.pages.a(page);
                            } else {
                                String value2 = TextureAtlas.readValue(bufferedReader);
                                int iIntValue = value2.equalsIgnoreCase("true") ? 90 : value2.equalsIgnoreCase("false") ? 0 : Integer.valueOf(value2).intValue();
                                TextureAtlas.readTuple(bufferedReader);
                                String[] strArr3 = TextureAtlas.tuple;
                                int i2 = Integer.parseInt(strArr3[0]);
                                int i3 = Integer.parseInt(strArr3[1]);
                                TextureAtlas.readTuple(bufferedReader);
                                int i4 = Integer.parseInt(strArr3[0]);
                                int i5 = Integer.parseInt(strArr3[1]);
                                Region region = new Region();
                                region.page = page;
                                region.left = i2;
                                region.top = i3;
                                region.width = i4;
                                region.height = i5;
                                region.name = line;
                                region.rotate = iIntValue == 90;
                                region.degrees = iIntValue;
                                if (TextureAtlas.readTuple(bufferedReader) == 4) {
                                    region.splits = new int[]{Integer.parseInt(strArr3[0]), Integer.parseInt(strArr3[1]), Integer.parseInt(strArr3[2]), Integer.parseInt(strArr3[3])};
                                    if (TextureAtlas.readTuple(bufferedReader) == 4) {
                                        region.pads = new int[]{Integer.parseInt(strArr3[0]), Integer.parseInt(strArr3[1]), Integer.parseInt(strArr3[2]), Integer.parseInt(strArr3[3])};
                                        TextureAtlas.readTuple(bufferedReader);
                                    }
                                }
                                region.originalWidth = Integer.parseInt(strArr3[0]);
                                region.originalHeight = Integer.parseInt(strArr3[1]);
                                TextureAtlas.readTuple(bufferedReader);
                                region.offsetX = Integer.parseInt(strArr3[0]);
                                region.offsetY = Integer.parseInt(strArr3[1]);
                                region.index = Integer.parseInt(TextureAtlas.readValue(bufferedReader));
                                if (z2) {
                                    region.flip = true;
                                }
                                this.regions.a(region);
                            }
                        } catch (Exception e2) {
                            throw new m("Error reading pack file: " + aVar, (Throwable) e2);
                        }
                    } finally {
                        n0.a(bufferedReader);
                    }
                }
            }
        }

        public a<Page> getPages() {
            return this.pages;
        }

        public a<Region> getRegions() {
            return this.regions;
        }
    }

    public TextureAtlas() {
        this.textures = new z<>(0);
        this.regions = new a<>();
    }

    private void load(TextureAtlasData textureAtlasData) {
        y yVar = new y();
        a.b<TextureAtlasData.Page> it = textureAtlasData.pages.iterator();
        while (it.hasNext()) {
            TextureAtlasData.Page next = it.next();
            Texture texture = next.texture;
            if (texture == null) {
                texture = new Texture(next.textureFile, next.format, next.useMipMaps);
                texture.setFilter(next.minFilter, next.magFilter);
                texture.setWrap(next.uWrap, next.vWrap);
            } else {
                texture.setFilter(next.minFilter, next.magFilter);
                texture.setWrap(next.uWrap, next.vWrap);
            }
            this.textures.add(texture);
            yVar.j(next, texture);
        }
        a.b<TextureAtlasData.Region> it2 = textureAtlasData.regions.iterator();
        while (it2.hasNext()) {
            TextureAtlasData.Region next2 = it2.next();
            int i2 = next2.width;
            int i3 = next2.height;
            Texture texture2 = (Texture) yVar.d(next2.page);
            int i4 = next2.left;
            int i5 = next2.top;
            boolean z2 = next2.rotate;
            AtlasRegion atlasRegion = new AtlasRegion(texture2, i4, i5, z2 ? i3 : i2, z2 ? i2 : i3);
            atlasRegion.index = next2.index;
            atlasRegion.name = next2.name;
            atlasRegion.offsetX = next2.offsetX;
            atlasRegion.offsetY = next2.offsetY;
            atlasRegion.originalHeight = next2.originalHeight;
            atlasRegion.originalWidth = next2.originalWidth;
            atlasRegion.rotate = next2.rotate;
            atlasRegion.degrees = next2.degrees;
            atlasRegion.splits = next2.splits;
            atlasRegion.pads = next2.pads;
            if (next2.flip) {
                atlasRegion.flip(false, true);
            }
            this.regions.a(atlasRegion);
        }
    }

    private Sprite newSprite(AtlasRegion atlasRegion) {
        if (atlasRegion.packedWidth != atlasRegion.originalWidth || atlasRegion.packedHeight != atlasRegion.originalHeight) {
            return new AtlasSprite(atlasRegion);
        }
        if (!atlasRegion.rotate) {
            return new Sprite(atlasRegion);
        }
        Sprite sprite = new Sprite(atlasRegion);
        sprite.setBounds(0.0f, 0.0f, atlasRegion.getRegionHeight(), atlasRegion.getRegionWidth());
        sprite.rotate90(true);
        return sprite;
    }

    static int readTuple(BufferedReader bufferedReader) throws IOException {
        int iIndexOf;
        String line = bufferedReader.readLine();
        int iIndexOf2 = line.indexOf(58);
        if (iIndexOf2 == -1) {
            throw new m("Invalid line: ".concat(line));
        }
        int i2 = iIndexOf2 + 1;
        int i3 = 0;
        while (i3 < 3 && (iIndexOf = line.indexOf(44, i2)) != -1) {
            tuple[i3] = line.substring(i2, iIndexOf).trim();
            i2 = iIndexOf + 1;
            i3++;
        }
        tuple[i3] = line.substring(i2).trim();
        return i3 + 1;
    }

    static String readValue(BufferedReader bufferedReader) throws IOException {
        String line = bufferedReader.readLine();
        int iIndexOf = line.indexOf(58);
        if (iIndexOf != -1) {
            return line.substring(iIndexOf + 1).trim();
        }
        throw new m("Invalid line: ".concat(line));
    }

    public AtlasRegion addRegion(String str, Texture texture, int i2, int i3, int i4, int i5) {
        this.textures.add(texture);
        AtlasRegion atlasRegion = new AtlasRegion(texture, i2, i3, i4, i5);
        atlasRegion.name = str;
        atlasRegion.index = -1;
        this.regions.a(atlasRegion);
        return atlasRegion;
    }

    public NinePatch createPatch(String str) {
        int i2 = this.regions.f1750b;
        for (int i3 = 0; i3 < i2; i3++) {
            AtlasRegion atlasRegion = this.regions.get(i3);
            if (atlasRegion.name.equals(str)) {
                int[] iArr = atlasRegion.splits;
                if (iArr == null) {
                    throw new IllegalArgumentException(a.a.A("Region does not have ninepatch splits: ", str));
                }
                NinePatch ninePatch = new NinePatch(atlasRegion, iArr[0], iArr[1], iArr[2], iArr[3]);
                if (atlasRegion.pads != null) {
                    ninePatch.setPadding(r0[0], r0[1], r0[2], r0[3]);
                }
                return ninePatch;
            }
        }
        return null;
    }

    public Sprite createSprite(String str) {
        int i2 = this.regions.f1750b;
        for (int i3 = 0; i3 < i2; i3++) {
            if (this.regions.get(i3).name.equals(str)) {
                return newSprite(this.regions.get(i3));
            }
        }
        return null;
    }

    public a<Sprite> createSprites() {
        a<Sprite> aVar = new a<>(true, this.regions.f1750b, Sprite.class);
        int i2 = this.regions.f1750b;
        for (int i3 = 0; i3 < i2; i3++) {
            aVar.a(newSprite(this.regions.get(i3)));
        }
        return aVar;
    }

    @Override // com.badlogic.gdx.utils.i
    public void dispose() {
        z.a<Texture> it = this.textures.iterator();
        while (it.hasNext()) {
            it.next().dispose();
        }
        this.textures.a(0);
    }

    public AtlasRegion findRegion(String str) {
        int i2 = this.regions.f1750b;
        for (int i3 = 0; i3 < i2; i3++) {
            if (this.regions.get(i3).name.equals(str)) {
                return this.regions.get(i3);
            }
        }
        return null;
    }

    public a<AtlasRegion> findRegions(String str) {
        a<AtlasRegion> aVar = new a<>(AtlasRegion.class);
        int i2 = this.regions.f1750b;
        for (int i3 = 0; i3 < i2; i3++) {
            AtlasRegion atlasRegion = this.regions.get(i3);
            if (atlasRegion.name.equals(str)) {
                aVar.a(new AtlasRegion(atlasRegion));
            }
        }
        return aVar;
    }

    public a<AtlasRegion> getRegions() {
        return this.regions;
    }

    public z<Texture> getTextures() {
        return this.textures;
    }

    public Sprite createSprite(String str, int i2) {
        int i3 = this.regions.f1750b;
        for (int i4 = 0; i4 < i3; i4++) {
            AtlasRegion atlasRegion = this.regions.get(i4);
            if (atlasRegion.name.equals(str) && atlasRegion.index == i2) {
                return newSprite(this.regions.get(i4));
            }
        }
        return null;
    }

    public AtlasRegion findRegion(String str, int i2) {
        int i3 = this.regions.f1750b;
        for (int i4 = 0; i4 < i3; i4++) {
            AtlasRegion atlasRegion = this.regions.get(i4);
            if (atlasRegion.name.equals(str) && atlasRegion.index == i2) {
                return atlasRegion;
            }
        }
        return null;
    }

    public TextureAtlas(String str) {
        this(Gdx.files.internal(str));
    }

    public a<Sprite> createSprites(String str) {
        a<Sprite> aVar = new a<>(Sprite.class);
        int i2 = this.regions.f1750b;
        for (int i3 = 0; i3 < i2; i3++) {
            AtlasRegion atlasRegion = this.regions.get(i3);
            if (atlasRegion.name.equals(str)) {
                aVar.a(newSprite(atlasRegion));
            }
        }
        return aVar;
    }

    public static class AtlasRegion extends TextureRegion {
        public int degrees;
        public int index;
        public String name;
        public float offsetX;
        public float offsetY;
        public int originalHeight;
        public int originalWidth;
        public int packedHeight;
        public int packedWidth;
        public int[] pads;
        public boolean rotate;
        public int[] splits;

        public AtlasRegion(Texture texture, int i2, int i3, int i4, int i5) {
            super(texture, i2, i3, i4, i5);
            this.originalWidth = i4;
            this.originalHeight = i5;
            this.packedWidth = i4;
            this.packedHeight = i5;
        }

        @Override // com.badlogic.gdx.graphics.g2d.TextureRegion
        public void flip(boolean z2, boolean z3) {
            super.flip(z2, z3);
            if (z2) {
                this.offsetX = (this.originalWidth - this.offsetX) - getRotatedPackedWidth();
            }
            if (z3) {
                this.offsetY = (this.originalHeight - this.offsetY) - getRotatedPackedHeight();
            }
        }

        public float getRotatedPackedHeight() {
            return this.rotate ? this.packedWidth : this.packedHeight;
        }

        public float getRotatedPackedWidth() {
            return this.rotate ? this.packedHeight : this.packedWidth;
        }

        public String toString() {
            return this.name;
        }

        public AtlasRegion(AtlasRegion atlasRegion) {
            setRegion(atlasRegion);
            this.index = atlasRegion.index;
            this.name = atlasRegion.name;
            this.offsetX = atlasRegion.offsetX;
            this.offsetY = atlasRegion.offsetY;
            this.packedWidth = atlasRegion.packedWidth;
            this.packedHeight = atlasRegion.packedHeight;
            this.originalWidth = atlasRegion.originalWidth;
            this.originalHeight = atlasRegion.originalHeight;
            this.rotate = atlasRegion.rotate;
            this.degrees = atlasRegion.degrees;
            this.splits = atlasRegion.splits;
            this.pads = atlasRegion.pads;
        }

        public AtlasRegion(TextureRegion textureRegion) {
            setRegion(textureRegion);
            this.packedWidth = textureRegion.getRegionWidth();
            int regionHeight = textureRegion.getRegionHeight();
            this.packedHeight = regionHeight;
            this.originalWidth = this.packedWidth;
            this.originalHeight = regionHeight;
        }
    }

    public TextureAtlas(com.badlogic.gdx.files.a aVar) {
        this(aVar, aVar.parent());
    }

    public TextureAtlas(com.badlogic.gdx.files.a aVar, boolean z2) {
        this(aVar, aVar.parent(), z2);
    }

    public AtlasRegion addRegion(String str, TextureRegion textureRegion) {
        this.textures.add(textureRegion.texture);
        AtlasRegion atlasRegion = new AtlasRegion(textureRegion);
        atlasRegion.name = str;
        atlasRegion.index = -1;
        this.regions.a(atlasRegion);
        return atlasRegion;
    }

    public TextureAtlas(com.badlogic.gdx.files.a aVar, com.badlogic.gdx.files.a aVar2) {
        this(aVar, aVar2, false);
    }

    public TextureAtlas(com.badlogic.gdx.files.a aVar, com.badlogic.gdx.files.a aVar2, boolean z2) {
        this(new TextureAtlasData(aVar, aVar2, z2));
    }

    public TextureAtlas(TextureAtlasData textureAtlasData) {
        this.textures = new z<>(0);
        this.regions = new a<>();
        if (textureAtlasData != null) {
            load(textureAtlasData);
        }
    }

    public static class AtlasSprite extends Sprite {
        float originalOffsetX;
        float originalOffsetY;
        final AtlasRegion region;

        public AtlasSprite(AtlasRegion atlasRegion) {
            this.region = new AtlasRegion(atlasRegion);
            this.originalOffsetX = atlasRegion.offsetX;
            this.originalOffsetY = atlasRegion.offsetY;
            setRegion(atlasRegion);
            setOrigin(atlasRegion.originalWidth / 2.0f, atlasRegion.originalHeight / 2.0f);
            int regionWidth = atlasRegion.getRegionWidth();
            int regionHeight = atlasRegion.getRegionHeight();
            if (atlasRegion.rotate) {
                super.rotate90(true);
                super.setBounds(atlasRegion.offsetX, atlasRegion.offsetY, regionHeight, regionWidth);
            } else {
                super.setBounds(atlasRegion.offsetX, atlasRegion.offsetY, regionWidth, regionHeight);
            }
            setColor(1.0f, 1.0f, 1.0f, 1.0f);
        }

        @Override // com.badlogic.gdx.graphics.g2d.Sprite, com.badlogic.gdx.graphics.g2d.TextureRegion
        public void flip(boolean z2, boolean z3) {
            if (this.region.rotate) {
                super.flip(z3, z2);
            } else {
                super.flip(z2, z3);
            }
            float originX = getOriginX();
            float originY = getOriginY();
            AtlasRegion atlasRegion = this.region;
            float f2 = atlasRegion.offsetX;
            float f3 = atlasRegion.offsetY;
            float widthRatio = getWidthRatio();
            float heightRatio = getHeightRatio();
            AtlasRegion atlasRegion2 = this.region;
            atlasRegion2.offsetX = this.originalOffsetX;
            atlasRegion2.offsetY = this.originalOffsetY;
            atlasRegion2.flip(z2, z3);
            AtlasRegion atlasRegion3 = this.region;
            float f4 = atlasRegion3.offsetX;
            this.originalOffsetX = f4;
            float f5 = atlasRegion3.offsetY;
            this.originalOffsetY = f5;
            float f6 = f4 * widthRatio;
            atlasRegion3.offsetX = f6;
            float f7 = f5 * heightRatio;
            atlasRegion3.offsetY = f7;
            translate(f6 - f2, f7 - f3);
            setOrigin(originX, originY);
        }

        public AtlasRegion getAtlasRegion() {
            return this.region;
        }

        @Override // com.badlogic.gdx.graphics.g2d.Sprite
        public float getHeight() {
            return (super.getHeight() / this.region.getRotatedPackedHeight()) * this.region.originalHeight;
        }

        public float getHeightRatio() {
            return super.getHeight() / this.region.getRotatedPackedHeight();
        }

        @Override // com.badlogic.gdx.graphics.g2d.Sprite
        public float getOriginX() {
            return super.getOriginX() + this.region.offsetX;
        }

        @Override // com.badlogic.gdx.graphics.g2d.Sprite
        public float getOriginY() {
            return super.getOriginY() + this.region.offsetY;
        }

        @Override // com.badlogic.gdx.graphics.g2d.Sprite
        public float getWidth() {
            return (super.getWidth() / this.region.getRotatedPackedWidth()) * this.region.originalWidth;
        }

        public float getWidthRatio() {
            return super.getWidth() / this.region.getRotatedPackedWidth();
        }

        @Override // com.badlogic.gdx.graphics.g2d.Sprite
        public float getX() {
            return super.getX() - this.region.offsetX;
        }

        @Override // com.badlogic.gdx.graphics.g2d.Sprite
        public float getY() {
            return super.getY() - this.region.offsetY;
        }

        @Override // com.badlogic.gdx.graphics.g2d.Sprite
        public void rotate90(boolean z2) {
            super.rotate90(z2);
            float originX = getOriginX();
            float originY = getOriginY();
            AtlasRegion atlasRegion = this.region;
            float f2 = atlasRegion.offsetX;
            float f3 = atlasRegion.offsetY;
            float widthRatio = getWidthRatio();
            float heightRatio = getHeightRatio();
            if (z2) {
                AtlasRegion atlasRegion2 = this.region;
                atlasRegion2.offsetX = f3;
                atlasRegion2.offsetY = ((atlasRegion2.originalHeight * heightRatio) - f2) - (atlasRegion2.packedWidth * widthRatio);
            } else {
                AtlasRegion atlasRegion3 = this.region;
                atlasRegion3.offsetX = ((atlasRegion3.originalWidth * widthRatio) - f3) - (atlasRegion3.packedHeight * heightRatio);
                atlasRegion3.offsetY = f2;
            }
            AtlasRegion atlasRegion4 = this.region;
            translate(atlasRegion4.offsetX - f2, atlasRegion4.offsetY - f3);
            setOrigin(originX, originY);
        }

        @Override // com.badlogic.gdx.graphics.g2d.Sprite
        public void setBounds(float f2, float f3, float f4, float f5) {
            AtlasRegion atlasRegion = this.region;
            float f6 = f4 / atlasRegion.originalWidth;
            float f7 = f5 / atlasRegion.originalHeight;
            float f8 = this.originalOffsetX * f6;
            atlasRegion.offsetX = f8;
            float f9 = this.originalOffsetY * f7;
            atlasRegion.offsetY = f9;
            boolean z2 = atlasRegion.rotate;
            super.setBounds(f2 + f8, f3 + f9, (z2 ? atlasRegion.packedHeight : atlasRegion.packedWidth) * f6, (z2 ? atlasRegion.packedWidth : atlasRegion.packedHeight) * f7);
        }

        @Override // com.badlogic.gdx.graphics.g2d.Sprite
        public void setOrigin(float f2, float f3) {
            AtlasRegion atlasRegion = this.region;
            super.setOrigin(f2 - atlasRegion.offsetX, f3 - atlasRegion.offsetY);
        }

        @Override // com.badlogic.gdx.graphics.g2d.Sprite
        public void setOriginCenter() {
            float f2 = this.width / 2.0f;
            AtlasRegion atlasRegion = this.region;
            super.setOrigin(f2 - atlasRegion.offsetX, (this.height / 2.0f) - atlasRegion.offsetY);
        }

        @Override // com.badlogic.gdx.graphics.g2d.Sprite
        public void setPosition(float f2, float f3) {
            AtlasRegion atlasRegion = this.region;
            super.setPosition(f2 + atlasRegion.offsetX, f3 + atlasRegion.offsetY);
        }

        @Override // com.badlogic.gdx.graphics.g2d.Sprite
        public void setSize(float f2, float f3) {
            setBounds(getX(), getY(), f2, f3);
        }

        @Override // com.badlogic.gdx.graphics.g2d.Sprite
        public void setX(float f2) {
            super.setX(f2 + this.region.offsetX);
        }

        @Override // com.badlogic.gdx.graphics.g2d.Sprite
        public void setY(float f2) {
            super.setY(f2 + this.region.offsetY);
        }

        public String toString() {
            return this.region.toString();
        }

        public AtlasSprite(AtlasSprite atlasSprite) {
            this.region = atlasSprite.region;
            this.originalOffsetX = atlasSprite.originalOffsetX;
            this.originalOffsetY = atlasSprite.originalOffsetY;
            set(atlasSprite);
        }
    }
}
