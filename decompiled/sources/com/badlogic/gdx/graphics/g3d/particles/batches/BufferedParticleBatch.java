package com.badlogic.gdx.graphics.g3d.particles.batches;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g3d.particles.ParticleSorter;
import com.badlogic.gdx.graphics.g3d.particles.renderers.ParticleControllerRenderData;
import com.badlogic.gdx.utils.a;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public abstract class BufferedParticleBatch<T extends ParticleControllerRenderData> implements ParticleBatch<T> {
    protected int bufferedParticlesCount;
    protected Camera camera;
    protected a<T> renderData;
    protected int currentCapacity = 0;
    protected ParticleSorter sorter = new ParticleSorter.Distance();

    protected BufferedParticleBatch(Class<T> cls) {
        this.renderData = new a<>(false, 10, cls);
    }

    protected abstract void allocParticlesData(int i2);

    @Override // com.badlogic.gdx.graphics.g3d.particles.batches.ParticleBatch
    public void begin() {
        this.renderData.clear();
        this.bufferedParticlesCount = 0;
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.batches.ParticleBatch
    public void draw(T t2) {
        if (t2.controller.particles.size > 0) {
            this.renderData.a(t2);
            this.bufferedParticlesCount += t2.controller.particles.size;
        }
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.batches.ParticleBatch
    public void end() {
        int i2 = this.bufferedParticlesCount;
        if (i2 > 0) {
            ensureCapacity(i2);
            flush(this.sorter.sort(this.renderData));
        }
    }

    public void ensureCapacity(int i2) {
        if (this.currentCapacity >= i2) {
            return;
        }
        this.sorter.ensureCapacity(i2);
        allocParticlesData(i2);
        this.currentCapacity = i2;
    }

    protected abstract void flush(int[] iArr);

    public int getBufferedCount() {
        return this.bufferedParticlesCount;
    }

    public ParticleSorter getSorter() {
        return this.sorter;
    }

    public void resetCapacity() {
        this.bufferedParticlesCount = 0;
        this.currentCapacity = 0;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
        this.sorter.setCamera(camera);
    }

    public void setSorter(ParticleSorter particleSorter) {
        this.sorter = particleSorter;
        particleSorter.setCamera(this.camera);
        particleSorter.ensureCapacity(this.currentCapacity);
    }
}
