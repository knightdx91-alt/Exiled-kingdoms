package com.badlogic.gdx.graphics.g3d.particles;

import com.badlogic.gdx.graphics.g3d.Renderable;
import com.badlogic.gdx.graphics.g3d.RenderableProvider;
import com.badlogic.gdx.graphics.g3d.particles.batches.ParticleBatch;
import com.badlogic.gdx.utils.a;
import com.badlogic.gdx.utils.c0;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class ParticleSystem implements RenderableProvider {
    private static ParticleSystem instance;
    private a<ParticleBatch<?>> batches = new a<>();
    private a<ParticleEffect> effects = new a<>();

    @Deprecated
    public static ParticleSystem get() {
        if (instance == null) {
            instance = new ParticleSystem();
        }
        return instance;
    }

    public void add(ParticleBatch<?> particleBatch) {
        this.batches.a(particleBatch);
    }

    public void begin() {
        a.b<ParticleBatch<?>> it = this.batches.iterator();
        while (it.hasNext()) {
            it.next().begin();
        }
    }

    public void draw() {
        a.b<ParticleEffect> it = this.effects.iterator();
        while (it.hasNext()) {
            it.next().draw();
        }
    }

    public void end() {
        a.b<ParticleBatch<?>> it = this.batches.iterator();
        while (it.hasNext()) {
            it.next().end();
        }
    }

    public a<ParticleBatch<?>> getBatches() {
        return this.batches;
    }

    @Override // com.badlogic.gdx.graphics.g3d.RenderableProvider
    public void getRenderables(a<Renderable> aVar, c0<Renderable> c0Var) {
        a.b<ParticleBatch<?>> it = this.batches.iterator();
        while (it.hasNext()) {
            it.next().getRenderables(aVar, c0Var);
        }
    }

    public void remove(ParticleEffect particleEffect) {
        this.effects.q(particleEffect, true);
    }

    public void removeAll() {
        this.effects.clear();
    }

    public void update() {
        a.b<ParticleEffect> it = this.effects.iterator();
        while (it.hasNext()) {
            it.next().update();
        }
    }

    public void updateAndDraw() {
        a.b<ParticleEffect> it = this.effects.iterator();
        while (it.hasNext()) {
            ParticleEffect next = it.next();
            next.update();
            next.draw();
        }
    }

    public void add(ParticleEffect particleEffect) {
        this.effects.a(particleEffect);
    }

    public void update(float f2) {
        a.b<ParticleEffect> it = this.effects.iterator();
        while (it.hasNext()) {
            it.next().update(f2);
        }
    }

    public void updateAndDraw(float f2) {
        a.b<ParticleEffect> it = this.effects.iterator();
        while (it.hasNext()) {
            ParticleEffect next = it.next();
            next.update(f2);
            next.draw();
        }
    }
}
