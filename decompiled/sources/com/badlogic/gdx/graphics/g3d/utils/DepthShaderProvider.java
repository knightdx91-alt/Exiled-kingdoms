package com.badlogic.gdx.graphics.g3d.utils;

import com.badlogic.gdx.files.a;
import com.badlogic.gdx.graphics.g3d.Renderable;
import com.badlogic.gdx.graphics.g3d.Shader;
import com.badlogic.gdx.graphics.g3d.shaders.DepthShader;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class DepthShaderProvider extends BaseShaderProvider {
    public final DepthShader.Config config;

    public DepthShaderProvider(DepthShader.Config config) {
        this.config = config == null ? new DepthShader.Config() : config;
    }

    @Override // com.badlogic.gdx.graphics.g3d.utils.BaseShaderProvider
    protected Shader createShader(Renderable renderable) {
        return new DepthShader(renderable, this.config);
    }

    public DepthShaderProvider(String str, String str2) {
        this(new DepthShader.Config(str, str2));
    }

    public DepthShaderProvider(a aVar, a aVar2) {
        this(aVar.readString(), aVar2.readString());
    }

    public DepthShaderProvider() {
        this(null);
    }
}
