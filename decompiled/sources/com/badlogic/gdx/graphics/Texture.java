package com.badlogic.gdx.graphics;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.p;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.glutils.FileTextureData;
import com.badlogic.gdx.graphics.glutils.PixmapTextureData;
import com.badlogic.gdx.utils.a;
import com.badlogic.gdx.utils.m;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import r.b;
import r.d;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class Texture extends GLTexture {
    private static d assetManager;
    static final Map<Application, a<Texture>> managedTextures = new HashMap();
    TextureData data;

    public enum TextureFilter {
        Nearest(GL20.GL_NEAREST),
        Linear(GL20.GL_LINEAR),
        MipMap(GL20.GL_LINEAR_MIPMAP_LINEAR),
        MipMapNearestNearest(GL20.GL_NEAREST_MIPMAP_NEAREST),
        MipMapLinearNearest(GL20.GL_LINEAR_MIPMAP_NEAREST),
        MipMapNearestLinear(GL20.GL_NEAREST_MIPMAP_LINEAR),
        MipMapLinearLinear(GL20.GL_LINEAR_MIPMAP_LINEAR);

        final int glEnum;

        TextureFilter(int i2) {
            this.glEnum = i2;
        }

        public int getGLEnum() {
            return this.glEnum;
        }

        public boolean isMipMap() {
            int i2 = this.glEnum;
            return (i2 == 9728 || i2 == 9729) ? false : true;
        }
    }

    public enum TextureWrap {
        MirroredRepeat(GL20.GL_MIRRORED_REPEAT),
        ClampToEdge(GL20.GL_CLAMP_TO_EDGE),
        Repeat(GL20.GL_REPEAT);

        final int glEnum;

        TextureWrap(int i2) {
            this.glEnum = i2;
        }

        public int getGLEnum() {
            return this.glEnum;
        }
    }

    public Texture(String str) {
        this(Gdx.files.internal(str));
    }

    private static void addManagedTexture(Application application, Texture texture) {
        Map<Application, a<Texture>> map = managedTextures;
        a<Texture> aVar = map.get(application);
        if (aVar == null) {
            aVar = new a<>();
        }
        aVar.a(texture);
        map.put(application, aVar);
    }

    public static void clearAllTextures(Application application) {
        managedTextures.remove(application);
    }

    public static String getManagedStatus() {
        StringBuilder sb = new StringBuilder("Managed textures/app: { ");
        Iterator<Application> it = managedTextures.keySet().iterator();
        while (it.hasNext()) {
            sb.append(managedTextures.get(it.next()).f1750b);
            sb.append(" ");
        }
        sb.append("}");
        return sb.toString();
    }

    public static int getNumManagedTextures() {
        return managedTextures.get(Gdx.app).f1750b;
    }

    public static void invalidateAllTextures(Application application) {
        a aVar = managedTextures.get(application);
        if (aVar == null) {
            return;
        }
        d dVar = assetManager;
        if (dVar == null) {
            for (int i2 = 0; i2 < aVar.f1750b; i2++) {
                ((Texture) aVar.get(i2)).reload();
            }
            return;
        }
        dVar.c();
        a aVar2 = new a(aVar);
        a.b bVarJ = aVar2.iterator();
        while (bVarJ.hasNext()) {
            Texture texture = (Texture) bVarJ.next();
            String strH = assetManager.h(texture);
            if (strH == null) {
                texture.reload();
            } else {
                final int iL = assetManager.l(strH);
                assetManager.x(0, strH);
                texture.glHandle = 0;
                p.b bVar = new p.b();
                bVar.f1596d = texture.getTextureData();
                bVar.f1597e = texture.getMinFilter();
                bVar.f1598f = texture.getMagFilter();
                bVar.f1599g = texture.getUWrap();
                bVar.f1600h = texture.getVWrap();
                bVar.f1594b = texture.data.useMipMaps();
                bVar.f1595c = texture;
                bVar.loadedCallback = new b.a() { // from class: com.badlogic.gdx.graphics.Texture.1
                    @Override // r.b.a
                    public void finishedLoading(d dVar2, String str, Class cls) {
                        dVar2.x(iL, str);
                    }
                };
                assetManager.y(strH);
                texture.glHandle = Gdx.gl.glGenTexture();
                assetManager.s(strH, Texture.class, bVar);
            }
        }
        aVar.clear();
        aVar.d(aVar2.f1749a, 0, aVar2.f1750b);
    }

    public static void setAssetManager(d dVar) {
        assetManager = dVar;
    }

    @Override // com.badlogic.gdx.graphics.GLTexture, com.badlogic.gdx.utils.i
    public void dispose() {
        if (this.glHandle == 0) {
            return;
        }
        delete();
        if (this.data.isManaged()) {
            Map<Application, a<Texture>> map = managedTextures;
            if (map.get(Gdx.app) != null) {
                map.get(Gdx.app).q(this, true);
            }
        }
    }

    public void draw(Pixmap pixmap, int i2, int i3) {
        if (this.data.isManaged()) {
            throw new m("can't draw to a managed texture");
        }
        bind();
        Gdx.gl.glTexSubImage2D(this.glTarget, 0, i2, i3, pixmap.getWidth(), pixmap.getHeight(), pixmap.getGLFormat(), pixmap.getGLType(), pixmap.getPixels());
    }

    @Override // com.badlogic.gdx.graphics.GLTexture
    public int getDepth() {
        return 0;
    }

    @Override // com.badlogic.gdx.graphics.GLTexture
    public int getHeight() {
        return this.data.getHeight();
    }

    public TextureData getTextureData() {
        return this.data;
    }

    @Override // com.badlogic.gdx.graphics.GLTexture
    public int getWidth() {
        return this.data.getWidth();
    }

    @Override // com.badlogic.gdx.graphics.GLTexture
    public boolean isManaged() {
        return this.data.isManaged();
    }

    public void load(TextureData textureData) {
        if (this.data != null && textureData.isManaged() != this.data.isManaged()) {
            throw new m("New data must have the same managed status as the old data");
        }
        this.data = textureData;
        if (!textureData.isPrepared()) {
            textureData.prepare();
        }
        bind();
        GLTexture.uploadImageData(GL20.GL_TEXTURE_2D, textureData);
        unsafeSetFilter(this.minFilter, this.magFilter, true);
        unsafeSetWrap(this.uWrap, this.vWrap, true);
        unsafeSetAnisotropicFilter(this.anisotropicFilterLevel, true);
        Gdx.gl.glBindTexture(this.glTarget, 0);
    }

    @Override // com.badlogic.gdx.graphics.GLTexture
    protected void reload() {
        if (!isManaged()) {
            throw new m("Tried to reload unmanaged Texture");
        }
        this.glHandle = Gdx.gl.glGenTexture();
        load(this.data);
    }

    public String toString() {
        TextureData textureData = this.data;
        return textureData instanceof FileTextureData ? textureData.toString() : super.toString();
    }

    public Texture(com.badlogic.gdx.files.a aVar) {
        this(aVar, (Pixmap.Format) null, false);
    }

    public Texture(com.badlogic.gdx.files.a aVar, boolean z2) {
        this(aVar, (Pixmap.Format) null, z2);
    }

    public Texture(com.badlogic.gdx.files.a aVar, Pixmap.Format format, boolean z2) {
        this(TextureData.Factory.loadFromFile(aVar, format, z2));
    }

    public Texture(Pixmap pixmap) {
        this(new PixmapTextureData(pixmap, null, false, false));
    }

    public Texture(Pixmap pixmap, boolean z2) {
        this(new PixmapTextureData(pixmap, null, z2, false));
    }

    public Texture(Pixmap pixmap, Pixmap.Format format, boolean z2) {
        this(new PixmapTextureData(pixmap, format, z2, false));
    }

    public Texture(int i2, int i3, Pixmap.Format format) {
        this(new PixmapTextureData(new Pixmap(i2, i3, format), null, false, true));
    }

    public Texture(TextureData textureData) {
        this(GL20.GL_TEXTURE_2D, Gdx.gl.glGenTexture(), textureData);
    }

    protected Texture(int i2, int i3, TextureData textureData) {
        super(i2, i3);
        load(textureData);
        if (textureData.isManaged()) {
            addManagedTexture(Gdx.app, this);
        }
    }
}
