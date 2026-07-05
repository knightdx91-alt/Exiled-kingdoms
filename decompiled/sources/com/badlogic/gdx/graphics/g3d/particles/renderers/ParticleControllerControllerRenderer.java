package com.badlogic.gdx.graphics.g3d.particles.renderers;

import com.badlogic.gdx.graphics.g3d.particles.ParallelArray;
import com.badlogic.gdx.graphics.g3d.particles.ParticleChannels;
import com.badlogic.gdx.graphics.g3d.particles.ParticleController;
import com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent;
import com.badlogic.gdx.graphics.g3d.particles.batches.ParticleBatch;
import com.badlogic.gdx.utils.m;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class ParticleControllerControllerRenderer extends ParticleControllerRenderer {
    ParallelArray.ObjectChannel<ParticleController> controllerChannel;

    @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public ParticleControllerComponent copy() {
        return new ParticleControllerControllerRenderer();
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void init() {
        ParallelArray.ObjectChannel<ParticleController> objectChannel = (ParallelArray.ObjectChannel) this.controller.particles.getChannel(ParticleChannels.ParticleController);
        this.controllerChannel = objectChannel;
        if (objectChannel == null) {
            throw new m("ParticleController channel not found, specify an influencer which will allocate it please.");
        }
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.renderers.ParticleControllerRenderer
    public boolean isCompatible(ParticleBatch particleBatch) {
        return false;
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.renderers.ParticleControllerRenderer, com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void update() {
        int i2 = this.controller.particles.size;
        for (int i3 = 0; i3 < i2; i3++) {
            this.controllerChannel.data[i3].draw();
        }
    }
}
