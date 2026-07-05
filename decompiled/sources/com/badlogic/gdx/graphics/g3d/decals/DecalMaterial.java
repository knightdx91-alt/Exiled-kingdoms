package com.badlogic.gdx.graphics.g3d.decals;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class DecalMaterial {
    public static final int NO_BLEND = -1;
    protected int dstBlendFactor;
    protected int srcBlendFactor;
    protected TextureRegion textureRegion;

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        DecalMaterial decalMaterial = (DecalMaterial) obj;
        return this.dstBlendFactor == decalMaterial.dstBlendFactor && this.srcBlendFactor == decalMaterial.srcBlendFactor && this.textureRegion.getTexture() == decalMaterial.textureRegion.getTexture();
    }

    public int getDstBlendFactor() {
        return this.dstBlendFactor;
    }

    public int getSrcBlendFactor() {
        return this.srcBlendFactor;
    }

    public int hashCode() {
        return ((((this.textureRegion.getTexture() != null ? this.textureRegion.getTexture().hashCode() : 0) * 31) + this.srcBlendFactor) * 31) + this.dstBlendFactor;
    }

    public boolean isOpaque() {
        return this.srcBlendFactor == -1;
    }

    public void set() {
        this.textureRegion.getTexture().bind(0);
        if (isOpaque()) {
            return;
        }
        Gdx.gl.glBlendFunc(this.srcBlendFactor, this.dstBlendFactor);
    }
}
