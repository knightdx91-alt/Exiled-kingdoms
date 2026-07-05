package com.badlogic.gdx.graphics.g3d;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g3d.utils.RenderContext;
import com.badlogic.gdx.utils.i;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public interface Shader extends i {
    void begin(Camera camera, RenderContext renderContext);

    boolean canRender(Renderable renderable);

    int compareTo(Shader shader);

    @Override // com.badlogic.gdx.utils.i
    /* synthetic */ void dispose();

    void end();

    void init();

    void render(Renderable renderable);
}
