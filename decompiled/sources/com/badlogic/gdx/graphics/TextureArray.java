package com.badlogic.gdx.graphics;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.TextureArrayData;
import com.badlogic.gdx.utils.a;
import com.badlogic.gdx.utils.m;
import java.nio.Buffer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class TextureArray extends GLTexture {
    static final Map<Application, a<TextureArray>> managedTextureArrays = new HashMap();
    private TextureArrayData data;

    public TextureArray(String... strArr) {
        this(getInternalHandles(strArr));
    }

    private static void addManagedTexture(Application application, TextureArray textureArray) {
        Map<Application, a<TextureArray>> map = managedTextureArrays;
        a<TextureArray> aVar = map.get(application);
        if (aVar == null) {
            aVar = new a<>();
        }
        aVar.a(textureArray);
        map.put(application, aVar);
    }

    public static void clearAllTextureArrays(Application application) {
        managedTextureArrays.remove(application);
    }

    private static com.badlogic.gdx.files.a[] getInternalHandles(String... strArr) {
        com.badlogic.gdx.files.a[] aVarArr = new com.badlogic.gdx.files.a[strArr.length];
        for (int i2 = 0; i2 < strArr.length; i2++) {
            aVarArr[i2] = Gdx.files.internal(strArr[i2]);
        }
        return aVarArr;
    }

    public static String getManagedStatus() {
        StringBuilder sb = new StringBuilder("Managed TextureArrays/app: { ");
        Iterator<Application> it = managedTextureArrays.keySet().iterator();
        while (it.hasNext()) {
            sb.append(managedTextureArrays.get(it.next()).f1750b);
            sb.append(" ");
        }
        sb.append("}");
        return sb.toString();
    }

    public static int getNumManagedTextureArrays() {
        return managedTextureArrays.get(Gdx.app).f1750b;
    }

    public static void invalidateAllTextureArrays(Application application) {
        a<TextureArray> aVar = managedTextureArrays.get(application);
        if (aVar == null) {
            return;
        }
        for (int i2 = 0; i2 < aVar.f1750b; i2++) {
            aVar.get(i2).reload();
        }
    }

    private void load(TextureArrayData textureArrayData) {
        if (this.data != null && textureArrayData.isManaged() != this.data.isManaged()) {
            throw new m("New data must have the same managed status as the old data");
        }
        this.data = textureArrayData;
        bind();
        Gdx.gl30.glTexImage3D(GL30.GL_TEXTURE_2D_ARRAY, 0, textureArrayData.getInternalFormat(), textureArrayData.getWidth(), textureArrayData.getHeight(), textureArrayData.getDepth(), 0, textureArrayData.getInternalFormat(), textureArrayData.getGLType(), (Buffer) null);
        if (!textureArrayData.isPrepared()) {
            textureArrayData.prepare();
        }
        textureArrayData.consumeTextureArrayData();
        setFilter(this.minFilter, this.magFilter);
        setWrap(this.uWrap, this.vWrap);
        Gdx.gl.glBindTexture(this.glTarget, 0);
    }

    @Override // com.badlogic.gdx.graphics.GLTexture
    public int getDepth() {
        return this.data.getDepth();
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

    @Override // com.badlogic.gdx.graphics.GLTexture
    protected void reload() {
        if (!isManaged()) {
            throw new m("Tried to reload an unmanaged TextureArray");
        }
        this.glHandle = Gdx.gl.glGenTexture();
        load(this.data);
    }

    public TextureArray(com.badlogic.gdx.files.a... aVarArr) {
        this(false, aVarArr);
    }

    public TextureArray(boolean z2, com.badlogic.gdx.files.a... aVarArr) {
        this(z2, Pixmap.Format.RGBA8888, aVarArr);
    }

    public TextureArray(boolean z2, Pixmap.Format format, com.badlogic.gdx.files.a... aVarArr) {
        this(TextureArrayData.Factory.loadFromFiles(format, z2, aVarArr));
    }

    public TextureArray(TextureArrayData textureArrayData) {
        super(GL30.GL_TEXTURE_2D_ARRAY, Gdx.gl.glGenTexture());
        if (Gdx.gl30 != null) {
            load(textureArrayData);
            if (textureArrayData.isManaged()) {
                addManagedTexture(Gdx.app, this);
                return;
            }
            return;
        }
        throw new m("TextureArray requires a device running with GLES 3.0 compatibilty");
    }
}
