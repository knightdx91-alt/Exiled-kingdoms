package com.badlogic.gdx.graphics.g3d.particles.values;

import a0.j;
import com.badlogic.gdx.math.a;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class LineSpawnShapeValue extends PrimitiveSpawnShapeValue {
    public LineSpawnShapeValue(LineSpawnShapeValue lineSpawnShapeValue) {
        super(lineSpawnShapeValue);
        load(lineSpawnShapeValue);
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.values.SpawnShapeValue
    public SpawnShapeValue copy() {
        return new LineSpawnShapeValue(this);
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.values.SpawnShapeValue
    public void spawnAux(a aVar, float f2) {
        float f3 = this.spawnWidth;
        float fB = a.a.b(this.spawnWidthValue, f2, this.spawnWidthDiff, f3);
        float f4 = this.spawnHeight;
        float fB2 = a.a.b(this.spawnHeightValue, f2, this.spawnHeightDiff, f4);
        float f5 = this.spawnDepth;
        float fB3 = a.a.b(this.spawnDepthValue, f2, this.spawnDepthDiff, f5);
        float fNextFloat = j.f69a.nextFloat();
        aVar.f1729a = fB * fNextFloat;
        aVar.f1730b = fB2 * fNextFloat;
        aVar.f1731c = fNextFloat * fB3;
    }

    public LineSpawnShapeValue() {
    }
}
