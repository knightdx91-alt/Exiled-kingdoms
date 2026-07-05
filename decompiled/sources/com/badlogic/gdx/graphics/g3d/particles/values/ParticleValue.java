package com.badlogic.gdx.graphics.g3d.particles.values;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.t;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class ParticleValue implements Json.b {
    public boolean active;

    public ParticleValue() {
    }

    public boolean isActive() {
        return this.active;
    }

    public void load(ParticleValue particleValue) {
        this.active = particleValue.active;
    }

    @Override // com.badlogic.gdx.utils.Json.b
    public void read(Json json, t tVar) {
        this.active = ((Boolean) json.readValue("active", Boolean.class, tVar)).booleanValue();
    }

    public void setActive(boolean z2) {
        this.active = z2;
    }

    @Override // com.badlogic.gdx.utils.Json.b
    public void write(Json json) {
        json.writeValue("active", Boolean.valueOf(this.active));
    }

    public ParticleValue(ParticleValue particleValue) {
        this.active = particleValue.active;
    }
}
