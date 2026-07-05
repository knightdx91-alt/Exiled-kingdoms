package com.badlogic.gdx.graphics.g3d.particles.values;

import com.badlogic.gdx.math.a;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class PointSpawnShapeValue extends PrimitiveSpawnShapeValue {
    public PointSpawnShapeValue(PointSpawnShapeValue pointSpawnShapeValue) {
        super(pointSpawnShapeValue);
        load(pointSpawnShapeValue);
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.values.SpawnShapeValue
    public SpawnShapeValue copy() {
        return new PointSpawnShapeValue(this);
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.values.SpawnShapeValue
    public void spawnAux(a aVar, float f2) {
        float f3 = this.spawnWidth;
        aVar.f1729a = a.a.b(this.spawnWidthValue, f2, this.spawnWidthDiff, f3);
        float f4 = this.spawnHeight;
        aVar.f1730b = a.a.b(this.spawnHeightValue, f2, this.spawnHeightDiff, f4);
        float f5 = this.spawnDepth;
        aVar.f1731c = a.a.b(this.spawnDepthValue, f2, this.spawnDepthDiff, f5);
    }

    public PointSpawnShapeValue() {
    }
}
