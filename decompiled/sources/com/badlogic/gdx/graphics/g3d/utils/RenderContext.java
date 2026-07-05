package com.badlogic.gdx.graphics.g3d.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class RenderContext {
    private int blendDFactor;
    private int blendSFactor;
    private boolean blending;
    private int cullFace;
    private int depthFunc;
    private boolean depthMask;
    private float depthRangeFar;
    private float depthRangeNear;
    public final TextureBinder textureBinder;

    public RenderContext(TextureBinder textureBinder) {
        this.textureBinder = textureBinder;
    }

    public void begin() {
        Gdx.gl.glDisable(GL20.GL_DEPTH_TEST);
        this.depthFunc = 0;
        Gdx.gl.glDepthMask(true);
        this.depthMask = true;
        Gdx.gl.glDisable(GL20.GL_BLEND);
        this.blending = false;
        Gdx.gl.glDisable(GL20.GL_CULL_FACE);
        this.blendDFactor = 0;
        this.blendSFactor = 0;
        this.cullFace = 0;
        this.textureBinder.begin();
    }

    public void end() {
        if (this.depthFunc != 0) {
            Gdx.gl.glDisable(GL20.GL_DEPTH_TEST);
        }
        if (!this.depthMask) {
            Gdx.gl.glDepthMask(true);
        }
        if (this.blending) {
            Gdx.gl.glDisable(GL20.GL_BLEND);
        }
        if (this.cullFace > 0) {
            Gdx.gl.glDisable(GL20.GL_CULL_FACE);
        }
        this.textureBinder.end();
    }

    public void setBlending(boolean z2, int i2, int i3) {
        if (z2 != this.blending) {
            this.blending = z2;
            if (z2) {
                Gdx.gl.glEnable(GL20.GL_BLEND);
            } else {
                Gdx.gl.glDisable(GL20.GL_BLEND);
            }
        }
        if (z2) {
            if (this.blendSFactor == i2 && this.blendDFactor == i3) {
                return;
            }
            Gdx.gl.glBlendFunc(i2, i3);
            this.blendSFactor = i2;
            this.blendDFactor = i3;
        }
    }

    public void setCullFace(int i2) {
        if (i2 != this.cullFace) {
            this.cullFace = i2;
            if (i2 != 1028 && i2 != 1029 && i2 != 1032) {
                Gdx.gl.glDisable(GL20.GL_CULL_FACE);
            } else {
                Gdx.gl.glEnable(GL20.GL_CULL_FACE);
                Gdx.gl.glCullFace(i2);
            }
        }
    }

    public void setDepthMask(boolean z2) {
        if (this.depthMask != z2) {
            GL20 gl20 = Gdx.gl;
            this.depthMask = z2;
            gl20.glDepthMask(z2);
        }
    }

    public void setDepthTest(int i2) {
        setDepthTest(i2, 0.0f, 1.0f);
    }

    public void setDepthTest(int i2, float f2, float f3) {
        int i3 = this.depthFunc;
        boolean z2 = i3 != 0;
        boolean z3 = i2 != 0;
        if (i3 != i2) {
            this.depthFunc = i2;
            if (z3) {
                Gdx.gl.glEnable(GL20.GL_DEPTH_TEST);
                Gdx.gl.glDepthFunc(i2);
            } else {
                Gdx.gl.glDisable(GL20.GL_DEPTH_TEST);
            }
        }
        if (z3) {
            if (!z2 || this.depthFunc != i2) {
                GL20 gl20 = Gdx.gl;
                this.depthFunc = i2;
                gl20.glDepthFunc(i2);
            }
            if (z2 && this.depthRangeNear == f2 && this.depthRangeFar == f3) {
                return;
            }
            GL20 gl202 = Gdx.gl;
            this.depthRangeNear = f2;
            this.depthRangeFar = f3;
            gl202.glDepthRangef(f2, f3);
        }
    }
}
