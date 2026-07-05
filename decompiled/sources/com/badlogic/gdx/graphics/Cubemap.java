package com.badlogic.gdx.graphics;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.d;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.glutils.FacedCubemapData;
import com.badlogic.gdx.graphics.glutils.PixmapTextureData;
import com.badlogic.gdx.utils.a;
import com.badlogic.gdx.utils.m;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import r.b;
import r.d;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class Cubemap extends GLTexture {
    private static d assetManager;
    static final Map<Application, a<Cubemap>> managedCubemaps = new HashMap();
    protected CubemapData data;

    public enum CubemapSide {
        PositiveX(0, GL20.GL_TEXTURE_CUBE_MAP_POSITIVE_X, 0.0f, -1.0f, 0.0f, 1.0f, 0.0f, 0.0f),
        NegativeX(1, GL20.GL_TEXTURE_CUBE_MAP_NEGATIVE_X, 0.0f, -1.0f, 0.0f, -1.0f, 0.0f, 0.0f),
        PositiveY(2, GL20.GL_TEXTURE_CUBE_MAP_POSITIVE_Y, 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 0.0f),
        NegativeY(3, GL20.GL_TEXTURE_CUBE_MAP_NEGATIVE_Y, 0.0f, 0.0f, -1.0f, 0.0f, -1.0f, 0.0f),
        PositiveZ(4, GL20.GL_TEXTURE_CUBE_MAP_POSITIVE_Z, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 1.0f),
        NegativeZ(5, GL20.GL_TEXTURE_CUBE_MAP_NEGATIVE_Z, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, -1.0f);

        public final com.badlogic.gdx.math.a direction;
        public final int glEnum;
        public final int index;
        public final com.badlogic.gdx.math.a up;

        CubemapSide(int i2, int i3, float f2, float f3, float f4, float f5, float f6, float f7) {
            this.index = i2;
            this.glEnum = i3;
            this.up = new com.badlogic.gdx.math.a(f2, f3, f4);
            this.direction = new com.badlogic.gdx.math.a(f5, f6, f7);
        }

        public com.badlogic.gdx.math.a getDirection(com.badlogic.gdx.math.a aVar) {
            aVar.u(this.direction);
            return aVar;
        }

        public int getGLEnum() {
            return this.glEnum;
        }

        public com.badlogic.gdx.math.a getUp(com.badlogic.gdx.math.a aVar) {
            aVar.u(this.up);
            return aVar;
        }
    }

    public Cubemap(CubemapData cubemapData) {
        super(GL20.GL_TEXTURE_CUBE_MAP);
        this.data = cubemapData;
        load(cubemapData);
    }

    private static void addManagedCubemap(Application application, Cubemap cubemap) {
        Map<Application, a<Cubemap>> map = managedCubemaps;
        a<Cubemap> aVar = map.get(application);
        if (aVar == null) {
            aVar = new a<>();
        }
        aVar.a(cubemap);
        map.put(application, aVar);
    }

    public static void clearAllCubemaps(Application application) {
        managedCubemaps.remove(application);
    }

    public static String getManagedStatus() {
        StringBuilder sb = new StringBuilder("Managed cubemap/app: { ");
        Iterator<Application> it = managedCubemaps.keySet().iterator();
        while (it.hasNext()) {
            sb.append(managedCubemaps.get(it.next()).f1750b);
            sb.append(" ");
        }
        sb.append("}");
        return sb.toString();
    }

    public static int getNumManagedCubemaps() {
        return managedCubemaps.get(Gdx.app).f1750b;
    }

    public static void invalidateAllCubemaps(Application application) {
        a aVar = managedCubemaps.get(application);
        if (aVar == null) {
            return;
        }
        d dVar = assetManager;
        if (dVar == null) {
            for (int i2 = 0; i2 < aVar.f1750b; i2++) {
                ((Cubemap) aVar.get(i2)).reload();
            }
            return;
        }
        dVar.c();
        a aVar2 = new a(aVar);
        a.b it = aVar2.iterator();
        while (it.hasNext()) {
            Cubemap cubemap = (Cubemap) it.next();
            String strH = assetManager.h(cubemap);
            if (strH == null) {
                cubemap.reload();
            } else {
                final int iL = assetManager.l(strH);
                assetManager.x(0, strH);
                cubemap.glHandle = 0;
                d.b bVar = new d.b();
                bVar.f1577b = cubemap.getCubemapData();
                bVar.f1578c = cubemap.getMinFilter();
                bVar.f1579d = cubemap.getMagFilter();
                bVar.f1580e = cubemap.getUWrap();
                bVar.f1581f = cubemap.getVWrap();
                bVar.f1576a = cubemap;
                bVar.loadedCallback = new b.a() { // from class: com.badlogic.gdx.graphics.Cubemap.1
                    @Override // r.b.a
                    public void finishedLoading(r.d dVar2, String str, Class cls) {
                        dVar2.x(iL, str);
                    }
                };
                assetManager.y(strH);
                cubemap.glHandle = Gdx.gl.glGenTexture();
                assetManager.s(strH, Cubemap.class, bVar);
            }
        }
        aVar.clear();
        aVar.d(aVar2.f1749a, 0, aVar2.f1750b);
    }

    public static void setAssetManager(r.d dVar) {
        assetManager = dVar;
    }

    @Override // com.badlogic.gdx.graphics.GLTexture, com.badlogic.gdx.utils.i
    public void dispose() {
        if (this.glHandle == 0) {
            return;
        }
        delete();
        if (this.data.isManaged()) {
            Map<Application, a<Cubemap>> map = managedCubemaps;
            if (map.get(Gdx.app) != null) {
                map.get(Gdx.app).q(this, true);
            }
        }
    }

    public CubemapData getCubemapData() {
        return this.data;
    }

    @Override // com.badlogic.gdx.graphics.GLTexture
    public int getDepth() {
        return 0;
    }

    @Override // com.badlogic.gdx.graphics.GLTexture
    public int getHeight() {
        return this.data.getHeight();
    }

    @Override // com.badlogic.gdx.graphics.GLTexture
    public int getWidth() {
        return this.data.getWidth();
    }

    @Override // com.badlogic.gdx.graphics.GLTexture
    public boolean isManaged() {
        return this.data.isManaged();
    }

    public void load(CubemapData cubemapData) {
        if (!cubemapData.isPrepared()) {
            cubemapData.prepare();
        }
        bind();
        unsafeSetFilter(this.minFilter, this.magFilter, true);
        unsafeSetWrap(this.uWrap, this.vWrap, true);
        unsafeSetAnisotropicFilter(this.anisotropicFilterLevel, true);
        cubemapData.consumeCubemapData();
        Gdx.gl.glBindTexture(this.glTarget, 0);
    }

    @Override // com.badlogic.gdx.graphics.GLTexture
    protected void reload() {
        if (!isManaged()) {
            throw new m("Tried to reload an unmanaged Cubemap");
        }
        this.glHandle = Gdx.gl.glGenTexture();
        load(this.data);
    }

    public Cubemap(com.badlogic.gdx.files.a aVar, com.badlogic.gdx.files.a aVar2, com.badlogic.gdx.files.a aVar3, com.badlogic.gdx.files.a aVar4, com.badlogic.gdx.files.a aVar5, com.badlogic.gdx.files.a aVar6) {
        this(aVar, aVar2, aVar3, aVar4, aVar5, aVar6, false);
    }

    public Cubemap(com.badlogic.gdx.files.a aVar, com.badlogic.gdx.files.a aVar2, com.badlogic.gdx.files.a aVar3, com.badlogic.gdx.files.a aVar4, com.badlogic.gdx.files.a aVar5, com.badlogic.gdx.files.a aVar6, boolean z2) {
        this(TextureData.Factory.loadFromFile(aVar, z2), TextureData.Factory.loadFromFile(aVar2, z2), TextureData.Factory.loadFromFile(aVar3, z2), TextureData.Factory.loadFromFile(aVar4, z2), TextureData.Factory.loadFromFile(aVar5, z2), TextureData.Factory.loadFromFile(aVar6, z2));
    }

    public Cubemap(Pixmap pixmap, Pixmap pixmap2, Pixmap pixmap3, Pixmap pixmap4, Pixmap pixmap5, Pixmap pixmap6) {
        this(pixmap, pixmap2, pixmap3, pixmap4, pixmap5, pixmap6, false);
    }

    public Cubemap(Pixmap pixmap, Pixmap pixmap2, Pixmap pixmap3, Pixmap pixmap4, Pixmap pixmap5, Pixmap pixmap6, boolean z2) {
        this(pixmap == null ? null : new PixmapTextureData(pixmap, null, z2, false), pixmap2 == null ? null : new PixmapTextureData(pixmap2, null, z2, false), pixmap3 == null ? null : new PixmapTextureData(pixmap3, null, z2, false), pixmap4 == null ? null : new PixmapTextureData(pixmap4, null, z2, false), pixmap5 == null ? null : new PixmapTextureData(pixmap5, null, z2, false), pixmap6 == null ? null : new PixmapTextureData(pixmap6, null, z2, false));
    }

    public Cubemap(int i2, int i3, int i4, Pixmap.Format format) {
        this(new PixmapTextureData(new Pixmap(i4, i3, format), null, false, true), new PixmapTextureData(new Pixmap(i4, i3, format), null, false, true), new PixmapTextureData(new Pixmap(i2, i4, format), null, false, true), new PixmapTextureData(new Pixmap(i2, i4, format), null, false, true), new PixmapTextureData(new Pixmap(i2, i3, format), null, false, true), new PixmapTextureData(new Pixmap(i2, i3, format), null, false, true));
    }

    public Cubemap(TextureData textureData, TextureData textureData2, TextureData textureData3, TextureData textureData4, TextureData textureData5, TextureData textureData6) {
        super(GL20.GL_TEXTURE_CUBE_MAP);
        Texture.TextureFilter textureFilter = Texture.TextureFilter.Nearest;
        this.minFilter = textureFilter;
        this.magFilter = textureFilter;
        Texture.TextureWrap textureWrap = Texture.TextureWrap.ClampToEdge;
        this.uWrap = textureWrap;
        this.vWrap = textureWrap;
        FacedCubemapData facedCubemapData = new FacedCubemapData(textureData, textureData2, textureData3, textureData4, textureData5, textureData6);
        this.data = facedCubemapData;
        load(facedCubemapData);
    }
}
