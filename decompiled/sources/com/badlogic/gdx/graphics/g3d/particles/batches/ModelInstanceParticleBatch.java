package com.badlogic.gdx.graphics.g3d.particles.batches;

import com.badlogic.gdx.graphics.g3d.Renderable;
import com.badlogic.gdx.graphics.g3d.particles.ResourceData;
import com.badlogic.gdx.graphics.g3d.particles.renderers.ModelInstanceControllerRenderData;
import com.badlogic.gdx.utils.a;
import com.badlogic.gdx.utils.c0;
import r.d;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class ModelInstanceParticleBatch implements ParticleBatch<ModelInstanceControllerRenderData> {
    int bufferedParticlesCount;
    a<ModelInstanceControllerRenderData> controllersRenderData = new a<>(false, 5);

    @Override // com.badlogic.gdx.graphics.g3d.particles.batches.ParticleBatch
    public void begin() {
        this.controllersRenderData.clear();
        this.bufferedParticlesCount = 0;
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.batches.ParticleBatch
    public void end() {
    }

    public int getBufferedCount() {
        return this.bufferedParticlesCount;
    }

    @Override // com.badlogic.gdx.graphics.g3d.RenderableProvider
    public void getRenderables(a<Renderable> aVar, c0<Renderable> c0Var) {
        a.b<ModelInstanceControllerRenderData> it = this.controllersRenderData.iterator();
        while (it.hasNext()) {
            ModelInstanceControllerRenderData next = it.next();
            int i2 = next.controller.particles.size;
            for (int i3 = 0; i3 < i2; i3++) {
                next.modelInstanceChannel.data[i3].getRenderables(aVar, c0Var);
            }
        }
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.batches.ParticleBatch, com.badlogic.gdx.graphics.g3d.particles.ResourceData.Configurable
    public void load(d dVar, ResourceData resourceData) {
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.batches.ParticleBatch, com.badlogic.gdx.graphics.g3d.particles.ResourceData.Configurable
    public void save(d dVar, ResourceData resourceData) {
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.batches.ParticleBatch
    public void draw(ModelInstanceControllerRenderData modelInstanceControllerRenderData) {
        this.controllersRenderData.a(modelInstanceControllerRenderData);
        this.bufferedParticlesCount += modelInstanceControllerRenderData.controller.particles.size;
    }
}
