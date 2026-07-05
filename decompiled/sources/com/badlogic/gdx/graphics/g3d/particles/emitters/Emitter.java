package com.badlogic.gdx.graphics.g3d.particles.emitters;

import com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.t;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public abstract class Emitter extends ParticleControllerComponent {
    public int maxParticleCount = 4;
    public int minParticleCount;
    public float percent;

    public Emitter(Emitter emitter) {
        set(emitter);
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void end() {
        this.controller.particles.size = 0;
    }

    public int getMaxParticleCount() {
        return this.maxParticleCount;
    }

    public int getMinParticleCount() {
        return this.minParticleCount;
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void init() {
        this.controller.particles.size = 0;
    }

    public boolean isComplete() {
        return this.percent >= 1.0f;
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent, com.badlogic.gdx.utils.Json.b
    public void read(Json json, t tVar) {
        Class cls = Integer.TYPE;
        this.minParticleCount = ((Integer) json.readValue("minParticleCount", cls, tVar)).intValue();
        this.maxParticleCount = ((Integer) json.readValue("maxParticleCount", cls, tVar)).intValue();
    }

    public void set(Emitter emitter) {
        this.minParticleCount = emitter.minParticleCount;
        this.maxParticleCount = emitter.maxParticleCount;
    }

    public void setMaxParticleCount(int i2) {
        this.maxParticleCount = i2;
    }

    public void setMinParticleCount(int i2) {
        this.minParticleCount = i2;
    }

    public void setParticleCount(int i2, int i3) {
        setMinParticleCount(i2);
        setMaxParticleCount(i3);
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent, com.badlogic.gdx.utils.Json.b
    public void write(Json json) {
        json.writeValue("minParticleCount", Integer.valueOf(this.minParticleCount));
        json.writeValue("maxParticleCount", Integer.valueOf(this.maxParticleCount));
    }

    public Emitter() {
    }
}
