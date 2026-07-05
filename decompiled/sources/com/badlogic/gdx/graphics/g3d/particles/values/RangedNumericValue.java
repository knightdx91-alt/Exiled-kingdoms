package com.badlogic.gdx.graphics.g3d.particles.values;

import a0.j;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.t;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class RangedNumericValue extends ParticleValue {
    private float lowMax;
    private float lowMin;

    public float getLowMax() {
        return this.lowMax;
    }

    public float getLowMin() {
        return this.lowMin;
    }

    public void load(RangedNumericValue rangedNumericValue) {
        super.load((ParticleValue) rangedNumericValue);
        this.lowMax = rangedNumericValue.lowMax;
        this.lowMin = rangedNumericValue.lowMin;
    }

    public float newLowValue() {
        float f2 = this.lowMin;
        return (j.f69a.nextFloat() * (this.lowMax - f2)) + f2;
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.values.ParticleValue, com.badlogic.gdx.utils.Json.b
    public void read(Json json, t tVar) {
        super.read(json, tVar);
        Class cls = Float.TYPE;
        this.lowMin = ((Float) json.readValue("lowMin", cls, tVar)).floatValue();
        this.lowMax = ((Float) json.readValue("lowMax", cls, tVar)).floatValue();
    }

    public void setLow(float f2) {
        this.lowMin = f2;
        this.lowMax = f2;
    }

    public void setLowMax(float f2) {
        this.lowMax = f2;
    }

    public void setLowMin(float f2) {
        this.lowMin = f2;
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.values.ParticleValue, com.badlogic.gdx.utils.Json.b
    public void write(Json json) {
        super.write(json);
        json.writeValue("lowMin", Float.valueOf(this.lowMin));
        json.writeValue("lowMax", Float.valueOf(this.lowMax));
    }

    public void setLow(float f2, float f3) {
        this.lowMin = f2;
        this.lowMax = f3;
    }
}
