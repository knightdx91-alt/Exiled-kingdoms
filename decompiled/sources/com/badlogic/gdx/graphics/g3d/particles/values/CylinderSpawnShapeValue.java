package com.badlogic.gdx.graphics.g3d.particles.values;

import a0.j;
import com.badlogic.gdx.math.a;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class CylinderSpawnShapeValue extends PrimitiveSpawnShapeValue {
    public CylinderSpawnShapeValue(CylinderSpawnShapeValue cylinderSpawnShapeValue) {
        super(cylinderSpawnShapeValue);
        load(cylinderSpawnShapeValue);
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.values.SpawnShapeValue
    public SpawnShapeValue copy() {
        return new CylinderSpawnShapeValue(this);
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.values.SpawnShapeValue
    public void spawnAux(a aVar, float f2) {
        float fG;
        float fB = a.a.b(this.spawnWidthValue, f2, this.spawnWidthDiff, this.spawnWidth);
        float fB2 = a.a.b(this.spawnHeightValue, f2, this.spawnHeightDiff, this.spawnHeight);
        float fB3 = a.a.b(this.spawnDepthValue, f2, this.spawnDepthDiff, this.spawnDepth);
        float fG2 = j.g(fB2) - (fB2 / 2.0f);
        if (this.edges) {
            fG = fB / 2.0f;
        } else {
            fG = j.g(fB) / 2.0f;
            fB3 = j.g(fB3);
        }
        float f3 = fB3 / 2.0f;
        float fG3 = 0.0f;
        boolean z2 = fG == 0.0f;
        boolean z3 = f3 == 0.0f;
        if (!z2 && !z3) {
            fG3 = j.g(360.0f);
        } else if (z2) {
            fG3 = j.i(1) == 0 ? -90.0f : 90.0f;
        } else if (z3 && j.i(1) != 0) {
            fG3 = 180.0f;
        }
        aVar.t(j.c(fG3) * fG, fG2, j.k(fG3) * f3);
    }

    public CylinderSpawnShapeValue() {
    }
}
