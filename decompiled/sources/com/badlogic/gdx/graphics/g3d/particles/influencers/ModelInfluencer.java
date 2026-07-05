package com.badlogic.gdx.graphics.g3d.particles.influencers;

import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.particles.ParallelArray;
import com.badlogic.gdx.graphics.g3d.particles.ParticleChannels;
import com.badlogic.gdx.graphics.g3d.particles.ResourceData;
import com.badlogic.gdx.utils.a;
import com.badlogic.gdx.utils.c0;
import r.d;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public abstract class ModelInfluencer extends Influencer {
    ParallelArray.ObjectChannel<ModelInstance> modelChannel;
    public a<Model> models;

    public static class Random extends ModelInfluencer {
        ModelInstancePool pool;

        private class ModelInstancePool extends c0<ModelInstance> {
            public ModelInstancePool() {
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.badlogic.gdx.utils.c0
            public ModelInstance newObject() {
                return new ModelInstance(Random.this.models.m());
            }
        }

        public Random() {
            this.pool = new ModelInstancePool();
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public void activateParticles(int i2, int i3) {
            int i4 = i3 + i2;
            while (i2 < i4) {
                this.modelChannel.data[i2] = this.pool.obtain();
                i2++;
            }
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public void init() {
            this.pool.clear();
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public void killParticles(int i2, int i3) {
            int i4 = i3 + i2;
            while (i2 < i4) {
                this.pool.free(this.modelChannel.data[i2]);
                this.modelChannel.data[i2] = null;
                i2++;
            }
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public Random copy() {
            return new Random(this);
        }

        public Random(Random random) {
            super(random);
            this.pool = new ModelInstancePool();
        }

        public Random(Model... modelArr) {
            super(modelArr);
            this.pool = new ModelInstancePool();
        }
    }

    public static class Single extends ModelInfluencer {
        public Single() {
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public void init() {
            Model modelG = this.models.g();
            int i2 = this.controller.emitter.maxParticleCount;
            for (int i3 = 0; i3 < i2; i3++) {
                this.modelChannel.data[i3] = new ModelInstance(modelG);
            }
        }

        public Single(Single single) {
            super(single);
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public Single copy() {
            return new Single(this);
        }

        public Single(Model... modelArr) {
            super(modelArr);
        }
    }

    public ModelInfluencer() {
        this.models = new a<>(true, 1, Model.class);
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void allocateChannels() {
        this.modelChannel = (ParallelArray.ObjectChannel) this.controller.particles.addChannel(ParticleChannels.ModelInstance);
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent, com.badlogic.gdx.graphics.g3d.particles.ResourceData.Configurable
    public void load(d dVar, ResourceData resourceData) {
        ResourceData.SaveData saveData = resourceData.getSaveData();
        while (true) {
            r.a aVarLoadAsset = saveData.loadAsset();
            if (aVarLoadAsset == null) {
                return;
            }
            this.models.a((Model) dVar.f(aVarLoadAsset));
        }
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent, com.badlogic.gdx.graphics.g3d.particles.ResourceData.Configurable
    public void save(d dVar, ResourceData resourceData) {
        ResourceData.SaveData saveDataCreateSaveData = resourceData.createSaveData();
        a.b<Model> it = this.models.iterator();
        while (it.hasNext()) {
            saveDataCreateSaveData.saveAsset(dVar.h(it.next()), Model.class);
        }
    }

    public ModelInfluencer(Model... modelArr) {
        this.models = new a<>(modelArr);
    }

    public ModelInfluencer(ModelInfluencer modelInfluencer) {
        this((Model[]) modelInfluencer.models.u(Model.class));
    }
}
