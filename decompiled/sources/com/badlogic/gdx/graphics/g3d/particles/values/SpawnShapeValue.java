package com.badlogic.gdx.graphics.g3d.particles.values;

import com.badlogic.gdx.graphics.g3d.particles.ResourceData;
import com.badlogic.gdx.math.a;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.t;
import r.d;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public abstract class SpawnShapeValue extends ParticleValue implements ResourceData.Configurable {
    public RangedNumericValue xOffsetValue;
    public RangedNumericValue yOffsetValue;
    public RangedNumericValue zOffsetValue;

    public SpawnShapeValue() {
        this.xOffsetValue = new RangedNumericValue();
        this.yOffsetValue = new RangedNumericValue();
        this.zOffsetValue = new RangedNumericValue();
    }

    public abstract SpawnShapeValue copy();

    public void init() {
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.values.ParticleValue
    public void load(ParticleValue particleValue) {
        super.load(particleValue);
        SpawnShapeValue spawnShapeValue = (SpawnShapeValue) particleValue;
        this.xOffsetValue.load(spawnShapeValue.xOffsetValue);
        this.yOffsetValue.load(spawnShapeValue.yOffsetValue);
        this.zOffsetValue.load(spawnShapeValue.zOffsetValue);
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.values.ParticleValue, com.badlogic.gdx.utils.Json.b
    public void read(Json json, t tVar) {
        super.read(json, tVar);
        this.xOffsetValue = (RangedNumericValue) json.readValue("xOffsetValue", RangedNumericValue.class, tVar);
        this.yOffsetValue = (RangedNumericValue) json.readValue("yOffsetValue", RangedNumericValue.class, tVar);
        this.zOffsetValue = (RangedNumericValue) json.readValue("zOffsetValue", RangedNumericValue.class, tVar);
    }

    public void save(d dVar, ResourceData resourceData) {
    }

    public final a spawn(a aVar, float f2) {
        spawnAux(aVar, f2);
        RangedNumericValue rangedNumericValue = this.xOffsetValue;
        if (rangedNumericValue.active) {
            aVar.f1729a = rangedNumericValue.newLowValue() + aVar.f1729a;
        }
        RangedNumericValue rangedNumericValue2 = this.yOffsetValue;
        if (rangedNumericValue2.active) {
            aVar.f1730b = rangedNumericValue2.newLowValue() + aVar.f1730b;
        }
        RangedNumericValue rangedNumericValue3 = this.zOffsetValue;
        if (rangedNumericValue3.active) {
            aVar.f1731c = rangedNumericValue3.newLowValue() + aVar.f1731c;
        }
        return aVar;
    }

    public abstract void spawnAux(a aVar, float f2);

    public void start() {
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.values.ParticleValue, com.badlogic.gdx.utils.Json.b
    public void write(Json json) {
        super.write(json);
        json.writeValue("xOffsetValue", this.xOffsetValue);
        json.writeValue("yOffsetValue", this.yOffsetValue);
        json.writeValue("zOffsetValue", this.zOffsetValue);
    }

    public SpawnShapeValue(SpawnShapeValue spawnShapeValue) {
        this();
    }

    public void load(d dVar, ResourceData resourceData) {
    }
}
