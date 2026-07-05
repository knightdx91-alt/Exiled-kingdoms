package com.badlogic.gdx.graphics.g3d.particles;

import a0.n;
import b0.a;
import com.badlogic.gdx.graphics.g3d.particles.ResourceData;
import com.badlogic.gdx.graphics.g3d.particles.batches.ParticleBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.a;
import com.badlogic.gdx.utils.i;
import r.d;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class ParticleEffect implements i, ResourceData.Configurable {
    private a bounds;
    private com.badlogic.gdx.utils.a<ParticleController> controllers;

    public ParticleEffect() {
        this.controllers = new com.badlogic.gdx.utils.a<>(true, 3, ParticleController.class);
    }

    public ParticleEffect copy() {
        return new ParticleEffect(this);
    }

    @Override // com.badlogic.gdx.utils.i
    public void dispose() {
        int i2 = this.controllers.f1750b;
        for (int i3 = 0; i3 < i2; i3++) {
            this.controllers.get(i3).dispose();
        }
    }

    public void draw() {
        int i2 = this.controllers.f1750b;
        for (int i3 = 0; i3 < i2; i3++) {
            this.controllers.get(i3).draw();
        }
    }

    public void end() {
        int i2 = this.controllers.f1750b;
        for (int i3 = 0; i3 < i2; i3++) {
            this.controllers.get(i3).end();
        }
    }

    public ParticleController findController(String str) {
        int i2 = this.controllers.f1750b;
        for (int i3 = 0; i3 < i2; i3++) {
            ParticleController particleController = this.controllers.get(i3);
            if (particleController.name.equals(str)) {
                return particleController;
            }
        }
        return null;
    }

    public a getBoundingBox() {
        if (this.bounds == null) {
            this.bounds = new a();
        }
        a aVar = this.bounds;
        aVar.f();
        a.b<ParticleController> it = this.controllers.iterator();
        while (it.hasNext()) {
            aVar.b(it.next().getBoundingBox());
        }
        return aVar;
    }

    public com.badlogic.gdx.utils.a<ParticleController> getControllers() {
        return this.controllers;
    }

    public void init() {
        int i2 = this.controllers.f1750b;
        for (int i3 = 0; i3 < i2; i3++) {
            this.controllers.get(i3).init();
        }
    }

    public boolean isComplete() {
        int i2 = this.controllers.f1750b;
        for (int i3 = 0; i3 < i2; i3++) {
            if (!this.controllers.get(i3).isComplete()) {
                return false;
            }
        }
        return true;
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ResourceData.Configurable
    public void load(d dVar, ResourceData resourceData) {
        a.b<ParticleController> it = this.controllers.iterator();
        while (it.hasNext()) {
            it.next().load(dVar, resourceData);
        }
    }

    public void reset() {
        int i2 = this.controllers.f1750b;
        for (int i3 = 0; i3 < i2; i3++) {
            this.controllers.get(i3).reset();
        }
    }

    public void rotate(n nVar) {
        int i2 = this.controllers.f1750b;
        for (int i3 = 0; i3 < i2; i3++) {
            this.controllers.get(i3).rotate(nVar);
        }
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ResourceData.Configurable
    public void save(d dVar, ResourceData resourceData) {
        a.b<ParticleController> it = this.controllers.iterator();
        while (it.hasNext()) {
            it.next().save(dVar, resourceData);
        }
    }

    public void scale(float f2, float f3, float f4) {
        int i2 = this.controllers.f1750b;
        for (int i3 = 0; i3 < i2; i3++) {
            this.controllers.get(i3).scale(f2, f3, f4);
        }
    }

    public void setBatch(com.badlogic.gdx.utils.a<ParticleBatch<?>> aVar) {
        a.b<ParticleController> it = this.controllers.iterator();
        while (it.hasNext()) {
            ParticleController next = it.next();
            a.b<ParticleBatch<?>> it2 = aVar.iterator();
            while (it2.hasNext()) {
                if (next.renderer.setBatch(it2.next())) {
                    break;
                }
            }
        }
    }

    public void setTransform(Matrix4 matrix4) {
        int i2 = this.controllers.f1750b;
        for (int i3 = 0; i3 < i2; i3++) {
            this.controllers.get(i3).setTransform(matrix4);
        }
    }

    public void start() {
        int i2 = this.controllers.f1750b;
        for (int i3 = 0; i3 < i2; i3++) {
            this.controllers.get(i3).start();
        }
    }

    public void translate(com.badlogic.gdx.math.a aVar) {
        int i2 = this.controllers.f1750b;
        for (int i3 = 0; i3 < i2; i3++) {
            this.controllers.get(i3).translate(aVar);
        }
    }

    public void update() {
        int i2 = this.controllers.f1750b;
        for (int i3 = 0; i3 < i2; i3++) {
            this.controllers.get(i3).update();
        }
    }

    public ParticleEffect(ParticleEffect particleEffect) {
        this.controllers = new com.badlogic.gdx.utils.a<>(true, particleEffect.controllers.f1750b);
        int i2 = particleEffect.controllers.f1750b;
        for (int i3 = 0; i3 < i2; i3++) {
            this.controllers.a(particleEffect.controllers.get(i3).copy());
        }
    }

    public void rotate(com.badlogic.gdx.math.a aVar, float f2) {
        int i2 = this.controllers.f1750b;
        for (int i3 = 0; i3 < i2; i3++) {
            this.controllers.get(i3).rotate(aVar, f2);
        }
    }

    public void scale(com.badlogic.gdx.math.a aVar) {
        int i2 = this.controllers.f1750b;
        for (int i3 = 0; i3 < i2; i3++) {
            this.controllers.get(i3).scale(aVar.f1729a, aVar.f1730b, aVar.f1731c);
        }
    }

    public void update(float f2) {
        int i2 = this.controllers.f1750b;
        for (int i3 = 0; i3 < i2; i3++) {
            this.controllers.get(i3).update(f2);
        }
    }

    public ParticleEffect(ParticleController... particleControllerArr) {
        this.controllers = new com.badlogic.gdx.utils.a<>(particleControllerArr);
    }
}
