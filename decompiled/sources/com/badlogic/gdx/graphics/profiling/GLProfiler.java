package com.badlogic.gdx.graphics.profiling;

import a0.e;
import com.badlogic.gdx.f;
import com.badlogic.gdx.graphics.GL30;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class GLProfiler {
    private boolean enabled = false;
    private GLInterceptor glInterceptor;
    private f graphics;
    private GLErrorListener listener;

    public GLProfiler(f fVar) {
        this.graphics = fVar;
        if (fVar.getGL30() != null) {
            this.glInterceptor = new GL30Interceptor(this, fVar.getGL30());
        } else {
            this.glInterceptor = new GL20Interceptor(this, fVar.getGL20());
        }
        this.listener = GLErrorListener.LOGGING_LISTENER;
    }

    public void disable() {
        if (this.enabled) {
            if (this.graphics.getGL30() != null) {
                f fVar = this.graphics;
                fVar.setGL30(((GL30Interceptor) fVar.getGL30()).gl30);
            } else {
                f fVar2 = this.graphics;
                fVar2.setGL20(((GL20Interceptor) fVar2.getGL20()).gl20);
            }
            this.enabled = false;
        }
    }

    public void enable() {
        if (this.enabled) {
            return;
        }
        if (this.graphics.getGL30() != null) {
            this.graphics.setGL30((GL30) this.glInterceptor);
        } else {
            this.graphics.setGL20(this.glInterceptor);
        }
        this.enabled = true;
    }

    public int getCalls() {
        return this.glInterceptor.getCalls();
    }

    public int getDrawCalls() {
        return this.glInterceptor.getDrawCalls();
    }

    public GLErrorListener getListener() {
        return this.listener;
    }

    public int getShaderSwitches() {
        return this.glInterceptor.getShaderSwitches();
    }

    public int getTextureBindings() {
        return this.glInterceptor.getTextureBindings();
    }

    public e getVertexCount() {
        return this.glInterceptor.getVertexCount();
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public void reset() {
        this.glInterceptor.reset();
    }

    public void setListener(GLErrorListener gLErrorListener) {
        this.listener = gLErrorListener;
    }
}
