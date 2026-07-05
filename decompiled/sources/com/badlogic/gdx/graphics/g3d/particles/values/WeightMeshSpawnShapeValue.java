package com.badlogic.gdx.graphics.g3d.particles.values;

import a.a;
import a0.c;
import a0.j;
import a0.o;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.particles.values.MeshSpawnShapeValue;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class WeightMeshSpawnShapeValue extends MeshSpawnShapeValue {
    private c<MeshSpawnShapeValue.Triangle> distribution;

    public WeightMeshSpawnShapeValue(WeightMeshSpawnShapeValue weightMeshSpawnShapeValue) {
        super(weightMeshSpawnShapeValue);
        this.distribution = new c<>();
        load(weightMeshSpawnShapeValue);
    }

    public void calculateWeights() {
        this.distribution.b();
        VertexAttributes vertexAttributes = this.mesh.getVertexAttributes();
        int numIndices = this.mesh.getNumIndices();
        int numVertices = this.mesh.getNumVertices();
        short s = (short) (vertexAttributes.vertexSize / 4);
        short s2 = (short) (vertexAttributes.findByUsage(1).offset / 4);
        float[] fArr = new float[numVertices * s];
        this.mesh.getVertices(fArr);
        int i2 = 0;
        if (numIndices > 0) {
            short[] sArr = new short[numIndices];
            this.mesh.getIndices(sArr);
            while (i2 < numIndices) {
                int i3 = (sArr[i2] * s) + s2;
                int i4 = (sArr[i2 + 1] * s) + s2;
                int i5 = (sArr[i2 + 2] * s) + s2;
                float f2 = fArr[i3];
                float f3 = fArr[i3 + 1];
                float f4 = fArr[i3 + 2];
                float f5 = fArr[i4];
                float f6 = fArr[i4 + 1];
                float f7 = fArr[i4 + 2];
                float f8 = fArr[i5];
                float f9 = fArr[i5 + 1];
                float f10 = fArr[i5 + 2];
                this.distribution.a(new MeshSpawnShapeValue.Triangle(f2, f3, f4, f5, f6, f7, f8, f9, f10), Math.abs((((f3 - f6) * f8) + a.C(f9, f3, f5, (f6 - f9) * f2)) / 2.0f));
                i2 += 3;
            }
        } else {
            while (i2 < numVertices) {
                int i6 = i2 + s2;
                int i7 = i6 + s;
                int i8 = i7 + s;
                float f11 = fArr[i6];
                float f12 = fArr[i6 + 1];
                float f13 = fArr[i6 + 2];
                float f14 = fArr[i7];
                float f15 = fArr[i7 + 1];
                float f16 = fArr[i7 + 2];
                float f17 = fArr[i8];
                float f18 = fArr[i8 + 1];
                float f19 = fArr[i8 + 2];
                this.distribution.a(new MeshSpawnShapeValue.Triangle(f11, f12, f13, f14, f15, f16, f17, f18, f19), Math.abs((((f12 - f15) * f17) + a.C(f18, f12, f14, (f15 - f18) * f11)) / 2.0f));
                i2 += s;
            }
        }
        this.distribution.c();
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.values.SpawnShapeValue
    public SpawnShapeValue copy() {
        return new WeightMeshSpawnShapeValue(this);
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.values.SpawnShapeValue
    public void init() {
        calculateWeights();
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.values.SpawnShapeValue
    public void spawnAux(com.badlogic.gdx.math.a aVar, float f2) {
        MeshSpawnShapeValue.Triangle triangleD = this.distribution.d();
        o oVar = j.f69a;
        float fNextFloat = oVar.nextFloat();
        float fNextFloat2 = oVar.nextFloat();
        float f3 = triangleD.x1;
        float fC = a.C(triangleD.x3, f3, fNextFloat2, a.C(triangleD.x2, f3, fNextFloat, f3));
        float f4 = triangleD.y1;
        float fC2 = a.C(triangleD.y3, f4, fNextFloat2, a.C(triangleD.y2, f4, fNextFloat, f4));
        float f5 = triangleD.z1;
        aVar.t(fC, fC2, a.C(triangleD.z3, f5, fNextFloat2, a.C(triangleD.z2, f5, fNextFloat, f5)));
    }

    public WeightMeshSpawnShapeValue() {
        this.distribution = new c<>();
    }
}
