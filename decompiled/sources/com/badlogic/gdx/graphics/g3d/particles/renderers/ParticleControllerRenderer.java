package com.badlogic.gdx.graphics.g3d.particles.renderers;

import com.badlogic.gdx.graphics.g3d.particles.ParticleController;
import com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent;
import com.badlogic.gdx.graphics.g3d.particles.batches.ParticleBatch;
import com.badlogic.gdx.graphics.g3d.particles.renderers.ParticleControllerRenderData;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public abstract class ParticleControllerRenderer<D extends ParticleControllerRenderData, T extends ParticleBatch<D>> extends ParticleControllerComponent {
    protected T batch;
    protected D renderData;

    protected ParticleControllerRenderer() {
    }

    public abstract boolean isCompatible(ParticleBatch<?> particleBatch);

    @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void set(ParticleController particleController) {
        super.set(particleController);
        D d2 = this.renderData;
        if (d2 != null) {
            d2.controller = this.controller;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean setBatch(ParticleBatch<?> particleBatch) {
        if (!isCompatible(particleBatch)) {
            return false;
        }
        this.batch = particleBatch;
        return true;
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void update() {
        this.batch.draw(this.renderData);
    }

    protected ParticleControllerRenderer(D d2) {
        this.renderData = d2;
    }
}
