package com.badlogic.gdx.graphics.g3d.utils;

import com.badlogic.gdx.files.a;
import com.badlogic.gdx.graphics.g3d.Renderable;
import com.badlogic.gdx.graphics.g3d.Shader;
import com.badlogic.gdx.graphics.g3d.shaders.DefaultShader;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class DefaultShaderProvider extends BaseShaderProvider {
    public final DefaultShader.Config config;

    public DefaultShaderProvider(DefaultShader.Config config) {
        this.config = config == null ? new DefaultShader.Config() : config;
    }

    @Override // com.badlogic.gdx.graphics.g3d.utils.BaseShaderProvider
    protected Shader createShader(Renderable renderable) {
        return new DefaultShader(renderable, this.config);
    }

    public DefaultShaderProvider(String str, String str2) {
        this(new DefaultShader.Config(str, str2));
    }

    public DefaultShaderProvider(a aVar, a aVar2) {
        this(aVar.readString(), aVar2.readString());
    }

    public DefaultShaderProvider() {
        this(null);
    }
}
