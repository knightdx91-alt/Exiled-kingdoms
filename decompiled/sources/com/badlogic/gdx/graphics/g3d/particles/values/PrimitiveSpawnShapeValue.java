package com.badlogic.gdx.graphics.g3d.particles.values;

import com.badlogic.gdx.math.a;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.t;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public abstract class PrimitiveSpawnShapeValue extends SpawnShapeValue {
    protected static final a TMP_V1 = new a();
    boolean edges;
    protected float spawnDepth;
    protected float spawnDepthDiff;
    public ScaledNumericValue spawnDepthValue;
    protected float spawnHeight;
    protected float spawnHeightDiff;
    public ScaledNumericValue spawnHeightValue;
    protected float spawnWidth;
    protected float spawnWidthDiff;
    public ScaledNumericValue spawnWidthValue;

    public enum SpawnSide {
        both,
        top,
        bottom
    }

    public PrimitiveSpawnShapeValue() {
        this.edges = false;
        this.spawnWidthValue = new ScaledNumericValue();
        this.spawnHeightValue = new ScaledNumericValue();
        this.spawnDepthValue = new ScaledNumericValue();
    }

    public ScaledNumericValue getSpawnDepth() {
        return this.spawnDepthValue;
    }

    public ScaledNumericValue getSpawnHeight() {
        return this.spawnHeightValue;
    }

    public ScaledNumericValue getSpawnWidth() {
        return this.spawnWidthValue;
    }

    public boolean isEdges() {
        return this.edges;
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.values.SpawnShapeValue, com.badlogic.gdx.graphics.g3d.particles.values.ParticleValue
    public void load(ParticleValue particleValue) {
        super.load(particleValue);
        PrimitiveSpawnShapeValue primitiveSpawnShapeValue = (PrimitiveSpawnShapeValue) particleValue;
        this.edges = primitiveSpawnShapeValue.edges;
        this.spawnWidthValue.load(primitiveSpawnShapeValue.spawnWidthValue);
        this.spawnHeightValue.load(primitiveSpawnShapeValue.spawnHeightValue);
        this.spawnDepthValue.load(primitiveSpawnShapeValue.spawnDepthValue);
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.values.SpawnShapeValue, com.badlogic.gdx.graphics.g3d.particles.values.ParticleValue, com.badlogic.gdx.utils.Json.b
    public void read(Json json, t tVar) {
        super.read(json, tVar);
        this.spawnWidthValue = (ScaledNumericValue) json.readValue("spawnWidthValue", ScaledNumericValue.class, tVar);
        this.spawnHeightValue = (ScaledNumericValue) json.readValue("spawnHeightValue", ScaledNumericValue.class, tVar);
        this.spawnDepthValue = (ScaledNumericValue) json.readValue("spawnDepthValue", ScaledNumericValue.class, tVar);
        this.edges = ((Boolean) json.readValue("edges", Boolean.TYPE, tVar)).booleanValue();
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.values.ParticleValue
    public void setActive(boolean z2) {
        super.setActive(z2);
        this.spawnWidthValue.setActive(true);
        this.spawnHeightValue.setActive(true);
        this.spawnDepthValue.setActive(true);
    }

    public void setDimensions(float f2, float f3, float f4) {
        this.spawnWidthValue.setHigh(f2);
        this.spawnHeightValue.setHigh(f3);
        this.spawnDepthValue.setHigh(f4);
    }

    public void setEdges(boolean z2) {
        this.edges = z2;
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.values.SpawnShapeValue
    public void start() {
        this.spawnWidth = this.spawnWidthValue.newLowValue();
        this.spawnWidthDiff = this.spawnWidthValue.newHighValue();
        if (!this.spawnWidthValue.isRelative()) {
            this.spawnWidthDiff -= this.spawnWidth;
        }
        this.spawnHeight = this.spawnHeightValue.newLowValue();
        this.spawnHeightDiff = this.spawnHeightValue.newHighValue();
        if (!this.spawnHeightValue.isRelative()) {
            this.spawnHeightDiff -= this.spawnHeight;
        }
        this.spawnDepth = this.spawnDepthValue.newLowValue();
        this.spawnDepthDiff = this.spawnDepthValue.newHighValue();
        if (this.spawnDepthValue.isRelative()) {
            return;
        }
        this.spawnDepthDiff -= this.spawnDepth;
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.values.SpawnShapeValue, com.badlogic.gdx.graphics.g3d.particles.values.ParticleValue, com.badlogic.gdx.utils.Json.b
    public void write(Json json) {
        super.write(json);
        json.writeValue("spawnWidthValue", this.spawnWidthValue);
        json.writeValue("spawnHeightValue", this.spawnHeightValue);
        json.writeValue("spawnDepthValue", this.spawnDepthValue);
        json.writeValue("edges", Boolean.valueOf(this.edges));
    }

    public PrimitiveSpawnShapeValue(PrimitiveSpawnShapeValue primitiveSpawnShapeValue) {
        super(primitiveSpawnShapeValue);
        this.edges = false;
        this.spawnWidthValue = new ScaledNumericValue();
        this.spawnHeightValue = new ScaledNumericValue();
        this.spawnDepthValue = new ScaledNumericValue();
    }
}
