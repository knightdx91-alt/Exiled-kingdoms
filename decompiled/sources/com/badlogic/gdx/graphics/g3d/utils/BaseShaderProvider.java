package com.badlogic.gdx.graphics.g3d.utils;

import com.badlogic.gdx.graphics.g3d.Renderable;
import com.badlogic.gdx.graphics.g3d.Shader;
import com.badlogic.gdx.utils.a;
import com.badlogic.gdx.utils.m;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public abstract class BaseShaderProvider implements ShaderProvider {
    protected a<Shader> shaders = new a<>();

    protected abstract Shader createShader(Renderable renderable);

    @Override // com.badlogic.gdx.graphics.g3d.utils.ShaderProvider, com.badlogic.gdx.utils.i
    public void dispose() {
        a.b<Shader> it = this.shaders.iterator();
        while (it.hasNext()) {
            it.next().dispose();
        }
        this.shaders.clear();
    }

    @Override // com.badlogic.gdx.graphics.g3d.utils.ShaderProvider
    public Shader getShader(Renderable renderable) {
        Shader shader = renderable.shader;
        if (shader != null && shader.canRender(renderable)) {
            return shader;
        }
        a.b<Shader> it = this.shaders.iterator();
        while (it.hasNext()) {
            Shader next = it.next();
            if (next.canRender(renderable)) {
                return next;
            }
        }
        Shader shaderCreateShader = createShader(renderable);
        if (!shaderCreateShader.canRender(renderable)) {
            throw new m("unable to provide a shader for this renderable");
        }
        shaderCreateShader.init();
        this.shaders.a(shaderCreateShader);
        return shaderCreateShader;
    }
}
