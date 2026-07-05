package com.badlogic.gdx.graphics.g3d.particles.batches;

import com.badlogic.gdx.graphics.g3d.RenderableProvider;
import com.badlogic.gdx.graphics.g3d.particles.ResourceData;
import com.badlogic.gdx.graphics.g3d.particles.renderers.ParticleControllerRenderData;
import r.d;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public interface ParticleBatch<T extends ParticleControllerRenderData> extends RenderableProvider, ResourceData.Configurable {
    void begin();

    void draw(T t2);

    void end();

    void load(d dVar, ResourceData resourceData);

    void save(d dVar, ResourceData resourceData);
}
