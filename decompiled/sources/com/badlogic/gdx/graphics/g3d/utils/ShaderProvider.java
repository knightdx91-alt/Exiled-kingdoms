package com.badlogic.gdx.graphics.g3d.utils;

import com.badlogic.gdx.graphics.g3d.Renderable;
import com.badlogic.gdx.graphics.g3d.Shader;
import com.badlogic.gdx.utils.i;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public interface ShaderProvider extends i {
    @Override // com.badlogic.gdx.utils.i
    /* synthetic */ void dispose();

    Shader getShader(Renderable renderable);
}
