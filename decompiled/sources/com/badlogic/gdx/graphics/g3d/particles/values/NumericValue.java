package com.badlogic.gdx.graphics.g3d.particles.values;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.t;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class NumericValue extends ParticleValue {
    private float value;

    public float getValue() {
        return this.value;
    }

    public void load(NumericValue numericValue) {
        super.load((ParticleValue) numericValue);
        this.value = numericValue.value;
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.values.ParticleValue, com.badlogic.gdx.utils.Json.b
    public void read(Json json, t tVar) {
        super.read(json, tVar);
        this.value = ((Float) json.readValue("value", Float.TYPE, tVar)).floatValue();
    }

    public void setValue(float f2) {
        this.value = f2;
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.values.ParticleValue, com.badlogic.gdx.utils.Json.b
    public void write(Json json) {
        super.write(json);
        json.writeValue("value", Float.valueOf(this.value));
    }
}
