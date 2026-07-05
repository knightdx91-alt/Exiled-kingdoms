package com.badlogic.gdx.graphics.profiling;

import a.a;
import a0.e;
import com.badlogic.gdx.graphics.GL20;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public abstract class GLInterceptor implements GL20 {
    protected int calls;
    protected int drawCalls;
    protected GLProfiler glProfiler;
    protected int shaderSwitches;
    protected int textureBindings;
    protected final e vertexCount;

    protected GLInterceptor(GLProfiler gLProfiler) {
        e eVar = new e();
        eVar.b();
        this.vertexCount = eVar;
        this.glProfiler = gLProfiler;
    }

    public static String resolveErrorNumber(int i2) {
        switch (i2) {
            case GL20.GL_INVALID_ENUM /* 1280 */:
                return "GL_INVALID_ENUM";
            case GL20.GL_INVALID_VALUE /* 1281 */:
                return "GL_INVALID_VALUE";
            case GL20.GL_INVALID_OPERATION /* 1282 */:
                return "GL_INVALID_OPERATION";
            case 1283:
            case 1284:
            default:
                return a.h(i2, "number ");
            case GL20.GL_OUT_OF_MEMORY /* 1285 */:
                return "GL_OUT_OF_MEMORY";
            case GL20.GL_INVALID_FRAMEBUFFER_OPERATION /* 1286 */:
                return "GL_INVALID_FRAMEBUFFER_OPERATION";
        }
    }

    public int getCalls() {
        return this.calls;
    }

    public int getDrawCalls() {
        return this.drawCalls;
    }

    public int getShaderSwitches() {
        return this.shaderSwitches;
    }

    public int getTextureBindings() {
        return this.textureBindings;
    }

    public e getVertexCount() {
        return this.vertexCount;
    }

    public void reset() {
        this.calls = 0;
        this.textureBindings = 0;
        this.drawCalls = 0;
        this.shaderSwitches = 0;
        this.vertexCount.b();
    }
}
