package com.badlogic.gdx.graphics.g3d.particles.values;

import a0.j;
import com.badlogic.gdx.math.a;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class RectangleSpawnShapeValue extends PrimitiveSpawnShapeValue {
    public RectangleSpawnShapeValue(RectangleSpawnShapeValue rectangleSpawnShapeValue) {
        super(rectangleSpawnShapeValue);
        load(rectangleSpawnShapeValue);
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.values.SpawnShapeValue
    public SpawnShapeValue copy() {
        return new RectangleSpawnShapeValue(this);
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x004d A[PHI: r0 r1
      0x004d: PHI (r0v20 float) = (r0v10 float), (r0v22 float) binds: [B:43:0x00a5, B:15:0x004b] A[DONT_GENERATE, DONT_INLINE]
      0x004d: PHI (r1v15 float) = (r1v5 float), (r1v19 float) binds: [B:43:0x00a5, B:15:0x004b] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // com.badlogic.gdx.graphics.g3d.particles.values.SpawnShapeValue
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void spawnAux(a aVar, float f2) {
        float fG;
        float fG2;
        float fG3;
        float f3;
        float fB = a.a.b(this.spawnWidthValue, f2, this.spawnWidthDiff, this.spawnWidth);
        float fB2 = a.a.b(this.spawnHeightValue, f2, this.spawnHeightDiff, this.spawnHeight);
        float fB3 = a.a.b(this.spawnDepthValue, f2, this.spawnDepthDiff, this.spawnDepth);
        if (!this.edges) {
            aVar.f1729a = j.g(fB) - (fB / 2.0f);
            aVar.f1730b = j.g(fB2) - (fB2 / 2.0f);
            aVar.f1731c = j.g(fB3) - (fB3 / 2.0f);
            return;
        }
        int iNextLong = ((int) j.f69a.nextLong(3)) - 1;
        if (iNextLong == -1) {
            if (j.i(1) == 0) {
                fB = -fB;
            }
            fG2 = fB / 2.0f;
            if (fG2 == 0.0f) {
                if (j.i(1) == 0) {
                    fB2 = -fB2;
                }
                fG = fB2 / 2.0f;
                if (j.i(1) == 0) {
                    fB3 = -fB3;
                }
                f3 = fB3 / 2.0f;
            } else {
                fG = j.g(fB2) - (fB2 / 2.0f);
                fG3 = j.g(fB3);
                f3 = fG3 - (fB3 / 2.0f);
            }
        } else if (iNextLong == 0) {
            if (j.i(1) == 0) {
                fB3 = -fB3;
            }
            f3 = fB3 / 2.0f;
            if (f3 == 0.0f) {
                if (j.i(1) == 0) {
                    fB2 = -fB2;
                }
                fG = fB2 / 2.0f;
                if (j.i(1) == 0) {
                    fB = -fB;
                }
                fG2 = fB / 2.0f;
            } else {
                fG = j.g(fB2) - (fB2 / 2.0f);
                fG2 = j.g(fB) - (fB / 2.0f);
            }
        } else {
            if (j.i(1) == 0) {
                fB2 = -fB2;
            }
            fG = fB2 / 2.0f;
            if (fG == 0.0f) {
                if (j.i(1) == 0) {
                    fB = -fB;
                }
                fG2 = fB / 2.0f;
                if (j.i(1) == 0) {
                }
                f3 = fB3 / 2.0f;
            } else {
                fG2 = j.g(fB) - (fB / 2.0f);
                fG3 = j.g(fB3);
                f3 = fG3 - (fB3 / 2.0f);
            }
        }
        aVar.f1729a = fG2;
        aVar.f1730b = fG;
        aVar.f1731c = f3;
    }

    public RectangleSpawnShapeValue() {
    }
}
