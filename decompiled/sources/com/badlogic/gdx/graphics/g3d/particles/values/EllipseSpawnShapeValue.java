package com.badlogic.gdx.graphics.g3d.particles.values;

import a0.j;
import com.badlogic.gdx.graphics.g3d.particles.values.PrimitiveSpawnShapeValue;
import com.badlogic.gdx.math.a;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.t;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class EllipseSpawnShapeValue extends PrimitiveSpawnShapeValue {
    PrimitiveSpawnShapeValue.SpawnSide side;

    public EllipseSpawnShapeValue(EllipseSpawnShapeValue ellipseSpawnShapeValue) {
        super(ellipseSpawnShapeValue);
        this.side = PrimitiveSpawnShapeValue.SpawnSide.both;
        load(ellipseSpawnShapeValue);
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.values.SpawnShapeValue
    public SpawnShapeValue copy() {
        return new EllipseSpawnShapeValue(this);
    }

    public PrimitiveSpawnShapeValue.SpawnSide getSide() {
        return this.side;
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.values.PrimitiveSpawnShapeValue, com.badlogic.gdx.graphics.g3d.particles.values.SpawnShapeValue, com.badlogic.gdx.graphics.g3d.particles.values.ParticleValue
    public void load(ParticleValue particleValue) {
        super.load(particleValue);
        this.side = ((EllipseSpawnShapeValue) particleValue).side;
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.values.PrimitiveSpawnShapeValue, com.badlogic.gdx.graphics.g3d.particles.values.SpawnShapeValue, com.badlogic.gdx.graphics.g3d.particles.values.ParticleValue, com.badlogic.gdx.utils.Json.b
    public void read(Json json, t tVar) {
        super.read(json, tVar);
        this.side = (PrimitiveSpawnShapeValue.SpawnSide) json.readValue("side", PrimitiveSpawnShapeValue.SpawnSide.class, tVar);
    }

    public void setSide(PrimitiveSpawnShapeValue.SpawnSide spawnSide) {
        this.side = spawnSide;
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.values.SpawnShapeValue
    public void spawnAux(a aVar, float f2) {
        float fG;
        float fG2;
        float fG3;
        float fB = a.a.b(this.spawnWidthValue, f2, this.spawnWidthDiff, this.spawnWidth);
        float fB2 = a.a.b(this.spawnHeightValue, f2, this.spawnHeightDiff, this.spawnHeight);
        float fB3 = a.a.b(this.spawnDepthValue, f2, this.spawnDepthDiff, this.spawnDepth);
        PrimitiveSpawnShapeValue.SpawnSide spawnSide = this.side;
        float fH = j.h(0.0f, spawnSide == PrimitiveSpawnShapeValue.SpawnSide.top ? 3.1415927f : spawnSide == PrimitiveSpawnShapeValue.SpawnSide.bottom ? -3.1415927f : 6.2831855f);
        if (!this.edges) {
            fG = j.g(fB / 2.0f);
            fG2 = j.g(fB2 / 2.0f);
            fG3 = j.g(fB3 / 2.0f);
        } else {
            if (fB == 0.0f) {
                aVar.t(0.0f, j.j(fH) * (fB2 / 2.0f), j.b(fH) * (fB3 / 2.0f));
                return;
            }
            if (fB2 == 0.0f) {
                aVar.t(j.b(fH) * (fB / 2.0f), 0.0f, j.j(fH) * (fB3 / 2.0f));
                return;
            } else if (fB3 == 0.0f) {
                aVar.t(j.b(fH) * (fB / 2.0f), j.j(fH) * (fB2 / 2.0f), 0.0f);
                return;
            } else {
                fG = fB / 2.0f;
                fG2 = fB2 / 2.0f;
                fG3 = fB3 / 2.0f;
            }
        }
        float fH2 = j.h(-1.0f, 1.0f);
        float fSqrt = (float) Math.sqrt(1.0f - (fH2 * fH2));
        aVar.t(j.b(fH) * fG * fSqrt, j.j(fH) * fG2 * fSqrt, fG3 * fH2);
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.values.PrimitiveSpawnShapeValue, com.badlogic.gdx.graphics.g3d.particles.values.SpawnShapeValue, com.badlogic.gdx.graphics.g3d.particles.values.ParticleValue, com.badlogic.gdx.utils.Json.b
    public void write(Json json) {
        super.write(json);
        json.writeValue("side", this.side);
    }

    public EllipseSpawnShapeValue() {
        this.side = PrimitiveSpawnShapeValue.SpawnSide.both;
    }
}
