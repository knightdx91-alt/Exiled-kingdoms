package com.badlogic.gdx.graphics.g3d.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import r.d;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public interface TextureProvider {

    public static class AssetTextureProvider implements TextureProvider {
        public final d assetManager;

        public AssetTextureProvider(d dVar) {
            this.assetManager = dVar;
        }

        @Override // com.badlogic.gdx.graphics.g3d.utils.TextureProvider
        public Texture load(String str) {
            return (Texture) this.assetManager.e(str, Texture.class);
        }
    }

    Texture load(String str);

    public static class FileTextureProvider implements TextureProvider {
        private Texture.TextureFilter magFilter;
        private Texture.TextureFilter minFilter;
        private Texture.TextureWrap uWrap;
        private boolean useMipMaps;
        private Texture.TextureWrap vWrap;

        public FileTextureProvider() {
            Texture.TextureFilter textureFilter = Texture.TextureFilter.Linear;
            this.magFilter = textureFilter;
            this.minFilter = textureFilter;
            Texture.TextureWrap textureWrap = Texture.TextureWrap.Repeat;
            this.vWrap = textureWrap;
            this.uWrap = textureWrap;
            this.useMipMaps = false;
        }

        @Override // com.badlogic.gdx.graphics.g3d.utils.TextureProvider
        public Texture load(String str) {
            Texture texture = new Texture(Gdx.files.internal(str), this.useMipMaps);
            texture.setFilter(this.minFilter, this.magFilter);
            texture.setWrap(this.uWrap, this.vWrap);
            return texture;
        }

        public FileTextureProvider(Texture.TextureFilter textureFilter, Texture.TextureFilter textureFilter2, Texture.TextureWrap textureWrap, Texture.TextureWrap textureWrap2, boolean z2) {
            this.minFilter = textureFilter;
            this.magFilter = textureFilter2;
            this.uWrap = textureWrap;
            this.vWrap = textureWrap2;
            this.useMipMaps = z2;
        }
    }
}
