package com.badlogic.gdx.graphics.g3d.utils;

import com.badlogic.gdx.graphics.GLTexture;
import com.badlogic.gdx.graphics.Texture;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class TextureDescriptor<T extends GLTexture> implements Comparable<TextureDescriptor<T>> {
    public Texture.TextureFilter magFilter;
    public Texture.TextureFilter minFilter;
    public T texture;
    public Texture.TextureWrap uWrap;
    public Texture.TextureWrap vWrap;

    public TextureDescriptor(T t2, Texture.TextureFilter textureFilter, Texture.TextureFilter textureFilter2, Texture.TextureWrap textureWrap, Texture.TextureWrap textureWrap2) {
        this.texture = null;
        set(t2, textureFilter, textureFilter2, textureWrap, textureWrap2);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TextureDescriptor)) {
            return false;
        }
        TextureDescriptor textureDescriptor = (TextureDescriptor) obj;
        return textureDescriptor.texture == this.texture && textureDescriptor.minFilter == this.minFilter && textureDescriptor.magFilter == this.magFilter && textureDescriptor.uWrap == this.uWrap && textureDescriptor.vWrap == this.vWrap;
    }

    public int hashCode() {
        T t2 = this.texture;
        long textureObjectHandle = ((((long) (t2 == null ? 0 : t2.glTarget)) * 811) + ((long) (t2 == null ? 0 : t2.getTextureObjectHandle()))) * 811;
        Texture.TextureFilter textureFilter = this.minFilter;
        long gLEnum = (textureObjectHandle + ((long) (textureFilter == null ? 0 : textureFilter.getGLEnum()))) * 811;
        Texture.TextureFilter textureFilter2 = this.magFilter;
        long gLEnum2 = (gLEnum + ((long) (textureFilter2 == null ? 0 : textureFilter2.getGLEnum()))) * 811;
        Texture.TextureWrap textureWrap = this.uWrap;
        long gLEnum3 = (gLEnum2 + ((long) (textureWrap == null ? 0 : textureWrap.getGLEnum()))) * 811;
        Texture.TextureWrap textureWrap2 = this.vWrap;
        long gLEnum4 = gLEnum3 + ((long) (textureWrap2 != null ? textureWrap2.getGLEnum() : 0));
        return (int) ((gLEnum4 >> 32) ^ gLEnum4);
    }

    public void set(T t2, Texture.TextureFilter textureFilter, Texture.TextureFilter textureFilter2, Texture.TextureWrap textureWrap, Texture.TextureWrap textureWrap2) {
        this.texture = t2;
        this.minFilter = textureFilter;
        this.magFilter = textureFilter2;
        this.uWrap = textureWrap;
        this.vWrap = textureWrap2;
    }

    @Override // java.lang.Comparable
    public int compareTo(TextureDescriptor<T> textureDescriptor) {
        if (textureDescriptor == this) {
            return 0;
        }
        T t2 = this.texture;
        int i2 = t2 == null ? 0 : t2.glTarget;
        T t3 = textureDescriptor.texture;
        int i3 = t3 == null ? 0 : t3.glTarget;
        if (i2 != i3) {
            return i2 - i3;
        }
        int textureObjectHandle = t2 == null ? 0 : t2.getTextureObjectHandle();
        T t4 = textureDescriptor.texture;
        int textureObjectHandle2 = t4 == null ? 0 : t4.getTextureObjectHandle();
        if (textureObjectHandle != textureObjectHandle2) {
            return textureObjectHandle - textureObjectHandle2;
        }
        Texture.TextureFilter textureFilter = this.minFilter;
        if (textureFilter != textureDescriptor.minFilter) {
            int gLEnum = textureFilter == null ? 0 : textureFilter.getGLEnum();
            Texture.TextureFilter textureFilter2 = textureDescriptor.minFilter;
            return gLEnum - (textureFilter2 != null ? textureFilter2.getGLEnum() : 0);
        }
        Texture.TextureFilter textureFilter3 = this.magFilter;
        if (textureFilter3 != textureDescriptor.magFilter) {
            int gLEnum2 = textureFilter3 == null ? 0 : textureFilter3.getGLEnum();
            Texture.TextureFilter textureFilter4 = textureDescriptor.magFilter;
            return gLEnum2 - (textureFilter4 != null ? textureFilter4.getGLEnum() : 0);
        }
        Texture.TextureWrap textureWrap = this.uWrap;
        if (textureWrap != textureDescriptor.uWrap) {
            int gLEnum3 = textureWrap == null ? 0 : textureWrap.getGLEnum();
            Texture.TextureWrap textureWrap2 = textureDescriptor.uWrap;
            return gLEnum3 - (textureWrap2 != null ? textureWrap2.getGLEnum() : 0);
        }
        Texture.TextureWrap textureWrap3 = this.vWrap;
        if (textureWrap3 == textureDescriptor.vWrap) {
            return 0;
        }
        int gLEnum4 = textureWrap3 == null ? 0 : textureWrap3.getGLEnum();
        Texture.TextureWrap textureWrap4 = textureDescriptor.vWrap;
        return gLEnum4 - (textureWrap4 != null ? textureWrap4.getGLEnum() : 0);
    }

    public TextureDescriptor(T t2) {
        this(t2, null, null, null, null);
    }

    public TextureDescriptor() {
        this.texture = null;
    }

    public <V extends T> void set(TextureDescriptor<V> textureDescriptor) {
        this.texture = textureDescriptor.texture;
        this.minFilter = textureDescriptor.minFilter;
        this.magFilter = textureDescriptor.magFilter;
        this.uWrap = textureDescriptor.uWrap;
        this.vWrap = textureDescriptor.vWrap;
    }
}
