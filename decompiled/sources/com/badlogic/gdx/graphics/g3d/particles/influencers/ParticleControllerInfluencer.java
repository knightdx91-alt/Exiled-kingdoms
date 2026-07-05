package com.badlogic.gdx.graphics.g3d.particles.influencers;

import com.badlogic.gdx.graphics.g3d.particles.ParallelArray;
import com.badlogic.gdx.graphics.g3d.particles.ParticleChannels;
import com.badlogic.gdx.graphics.g3d.particles.ParticleController;
import com.badlogic.gdx.graphics.g3d.particles.ParticleEffect;
import com.badlogic.gdx.graphics.g3d.particles.ResourceData;
import com.badlogic.gdx.utils.a;
import com.badlogic.gdx.utils.c0;
import com.badlogic.gdx.utils.o;
import r.d;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public abstract class ParticleControllerInfluencer extends Influencer {
    ParallelArray.ObjectChannel<ParticleController> particleControllerChannel;
    public a<ParticleController> templates;

    public static class Random extends ParticleControllerInfluencer {
        ParticleControllerPool pool;

        private class ParticleControllerPool extends c0<ParticleController> {
            public ParticleControllerPool() {
            }

            @Override // com.badlogic.gdx.utils.c0
            public void clear() {
                int free = Random.this.pool.getFree();
                for (int i2 = 0; i2 < free; i2++) {
                    Random.this.pool.obtain().dispose();
                }
                super.clear();
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.badlogic.gdx.utils.c0
            public ParticleController newObject() {
                ParticleController particleControllerCopy = Random.this.templates.m().copy();
                particleControllerCopy.init();
                return particleControllerCopy;
            }
        }

        public Random() {
            this.pool = new ParticleControllerPool();
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public void activateParticles(int i2, int i3) {
            int i4 = i3 + i2;
            while (i2 < i4) {
                ParticleController particleControllerObtain = this.pool.obtain();
                particleControllerObtain.start();
                this.particleControllerChannel.data[i2] = particleControllerObtain;
                i2++;
            }
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.influencers.ParticleControllerInfluencer, com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent, com.badlogic.gdx.utils.i
        public void dispose() {
            this.pool.clear();
            super.dispose();
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public void init() {
            this.pool.clear();
            for (int i2 = 0; i2 < this.controller.emitter.maxParticleCount; i2++) {
                ParticleControllerPool particleControllerPool = this.pool;
                particleControllerPool.free(particleControllerPool.newObject());
            }
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public void killParticles(int i2, int i3) {
            int i4 = i3 + i2;
            while (i2 < i4) {
                ParticleController particleController = this.particleControllerChannel.data[i2];
                particleController.end();
                this.pool.free(particleController);
                this.particleControllerChannel.data[i2] = null;
                i2++;
            }
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public Random copy() {
            return new Random(this);
        }

        public Random(ParticleController... particleControllerArr) {
            super(particleControllerArr);
            this.pool = new ParticleControllerPool();
        }

        public Random(Random random) {
            super(random);
            this.pool = new ParticleControllerPool();
        }
    }

    public static class Single extends ParticleControllerInfluencer {
        public Single(ParticleController... particleControllerArr) {
            super(particleControllerArr);
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public void activateParticles(int i2, int i3) {
            int i4 = i3 + i2;
            while (i2 < i4) {
                this.particleControllerChannel.data[i2].start();
                i2++;
            }
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public void init() {
            ParticleController particleControllerG = this.templates.g();
            int i2 = this.controller.particles.capacity;
            for (int i3 = 0; i3 < i2; i3++) {
                ParticleController particleControllerCopy = particleControllerG.copy();
                particleControllerCopy.init();
                this.particleControllerChannel.data[i3] = particleControllerCopy;
            }
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public void killParticles(int i2, int i3) {
            int i4 = i3 + i2;
            while (i2 < i4) {
                this.particleControllerChannel.data[i2].end();
                i2++;
            }
        }

        public Single() {
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public Single copy() {
            return new Single(this);
        }

        public Single(Single single) {
            super(single);
        }
    }

    public ParticleControllerInfluencer() {
        this.templates = new a<>(true, 1, ParticleController.class);
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void allocateChannels() {
        this.particleControllerChannel = (ParallelArray.ObjectChannel) this.controller.particles.addChannel(ParticleChannels.ParticleController);
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent, com.badlogic.gdx.utils.i
    public void dispose() {
        if (this.controller != null) {
            for (int i2 = 0; i2 < this.controller.particles.size; i2++) {
                ParticleController particleController = this.particleControllerChannel.data[i2];
                if (particleController != null) {
                    particleController.dispose();
                    this.particleControllerChannel.data[i2] = null;
                }
            }
        }
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void end() {
        for (int i2 = 0; i2 < this.controller.particles.size; i2++) {
            this.particleControllerChannel.data[i2].end();
        }
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent, com.badlogic.gdx.graphics.g3d.particles.ResourceData.Configurable
    public void load(d dVar, ResourceData resourceData) {
        ResourceData.SaveData saveData = resourceData.getSaveData();
        a.b it = ((a) saveData.load("indices")).iterator();
        while (true) {
            r.a aVarLoadAsset = saveData.loadAsset();
            if (aVarLoadAsset == null) {
                return;
            }
            a<ParticleController> controllers = ((ParticleEffect) dVar.f(aVarLoadAsset)).getControllers();
            o oVar = (o) it.next();
            int i2 = oVar.f1850b;
            for (int i3 = 0; i3 < i2; i3++) {
                this.templates.a(controllers.get(oVar.d(i3)));
            }
        }
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent, com.badlogic.gdx.graphics.g3d.particles.ResourceData.Configurable
    public void save(d dVar, ResourceData resourceData) {
        ResourceData.SaveData saveDataCreateSaveData = resourceData.createSaveData();
        a aVar = new a();
        dVar.g(aVar);
        a aVar2 = new a(this.templates);
        a aVar3 = new a();
        for (int i2 = 0; i2 < aVar.f1750b && aVar2.f1750b > 0; i2++) {
            ParticleEffect particleEffect = (ParticleEffect) aVar.get(i2);
            a<ParticleController> controllers = particleEffect.getControllers();
            a.b it = aVar2.iterator();
            o oVar = null;
            while (it.hasNext()) {
                int iH = controllers.h((ParticleController) it.next(), true);
                if (iH > -1) {
                    if (oVar == null) {
                        oVar = new o();
                    }
                    it.remove();
                    oVar.a(iH);
                }
            }
            if (oVar != null) {
                saveDataCreateSaveData.saveAsset(dVar.h(particleEffect), ParticleEffect.class);
                aVar3.a(oVar);
            }
        }
        saveDataCreateSaveData.save("indices", aVar3);
    }

    public ParticleControllerInfluencer(ParticleController... particleControllerArr) {
        this.templates = new a<>(particleControllerArr);
    }

    public ParticleControllerInfluencer(ParticleControllerInfluencer particleControllerInfluencer) {
        this(particleControllerInfluencer.templates.f1749a);
    }
}
