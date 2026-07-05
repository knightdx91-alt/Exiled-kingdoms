package com.badlogic.gdx.graphics.g2d;

import com.badlogic.gdx.utils.a;
import com.badlogic.gdx.utils.c0;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class ParticleEffectPool extends c0<PooledEffect> {
    private final ParticleEffect effect;

    public class PooledEffect extends ParticleEffect {
        PooledEffect(ParticleEffect particleEffect) {
            super(particleEffect);
        }

        public void free() {
            ParticleEffectPool.this.free(this);
        }
    }

    public ParticleEffectPool(ParticleEffect particleEffect, int i2, int i3) {
        super(i2, i3);
        this.effect = particleEffect;
    }

    @Override // com.badlogic.gdx.utils.c0
    public void free(PooledEffect pooledEffect) {
        super.free(pooledEffect);
        pooledEffect.reset(false);
        float f2 = pooledEffect.xSizeScale;
        ParticleEffect particleEffect = this.effect;
        if (f2 == particleEffect.xSizeScale && pooledEffect.ySizeScale == particleEffect.ySizeScale && pooledEffect.motionScale == particleEffect.motionScale) {
            return;
        }
        a<ParticleEmitter> emitters = pooledEffect.getEmitters();
        a<ParticleEmitter> emitters2 = this.effect.getEmitters();
        for (int i2 = 0; i2 < emitters.f1750b; i2++) {
            ParticleEmitter particleEmitter = emitters.get(i2);
            ParticleEmitter particleEmitter2 = emitters2.get(i2);
            particleEmitter.matchSize(particleEmitter2);
            particleEmitter.matchMotion(particleEmitter2);
        }
        ParticleEffect particleEffect2 = this.effect;
        pooledEffect.xSizeScale = particleEffect2.xSizeScale;
        pooledEffect.ySizeScale = particleEffect2.ySizeScale;
        pooledEffect.motionScale = particleEffect2.motionScale;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.badlogic.gdx.utils.c0
    public PooledEffect newObject() {
        PooledEffect pooledEffect = new PooledEffect(this.effect);
        pooledEffect.start();
        return pooledEffect;
    }
}
