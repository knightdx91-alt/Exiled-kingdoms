package com.badlogic.gdx.graphics.g3d.particles.values;

import a0.j;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.particles.values.MeshSpawnShapeValue;
import com.badlogic.gdx.math.a;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class UnweightedMeshSpawnShapeValue extends MeshSpawnShapeValue {
    private short[] indices;
    private int positionOffset;
    private int triangleCount;
    private int vertexCount;
    private int vertexSize;
    private float[] vertices;

    public UnweightedMeshSpawnShapeValue(UnweightedMeshSpawnShapeValue unweightedMeshSpawnShapeValue) {
        super(unweightedMeshSpawnShapeValue);
        load(unweightedMeshSpawnShapeValue);
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.values.SpawnShapeValue
    public SpawnShapeValue copy() {
        return new UnweightedMeshSpawnShapeValue(this);
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.values.MeshSpawnShapeValue
    public void setMesh(Mesh mesh, Model model) {
        super.setMesh(mesh, model);
        this.vertexSize = mesh.getVertexSize() / 4;
        this.positionOffset = mesh.getVertexAttribute(1).offset / 4;
        int numIndices = mesh.getNumIndices();
        if (numIndices > 0) {
            short[] sArr = new short[numIndices];
            this.indices = sArr;
            mesh.getIndices(sArr);
            this.triangleCount = this.indices.length / 3;
        } else {
            this.indices = null;
        }
        int numVertices = mesh.getNumVertices();
        this.vertexCount = numVertices;
        float[] fArr = new float[numVertices * this.vertexSize];
        this.vertices = fArr;
        mesh.getVertices(fArr);
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.values.SpawnShapeValue
    public void spawnAux(a aVar, float f2) {
        if (this.indices == null) {
            int i2 = j.i(this.vertexCount - 3);
            int i3 = this.vertexSize;
            int i4 = (i2 * i3) + this.positionOffset;
            int i5 = i4 + i3;
            int i6 = i3 + i5;
            float[] fArr = this.vertices;
            MeshSpawnShapeValue.Triangle.pick(fArr[i4], fArr[i4 + 1], fArr[i4 + 2], fArr[i5], fArr[i5 + 1], fArr[i5 + 2], fArr[i6], fArr[i6 + 1], fArr[i6 + 2], aVar);
            return;
        }
        int i7 = j.i(this.triangleCount - 1) * 3;
        short[] sArr = this.indices;
        short s = sArr[i7];
        int i8 = this.vertexSize;
        int i9 = this.positionOffset;
        int i10 = (s * i8) + i9;
        int i11 = (sArr[i7 + 1] * i8) + i9;
        int i12 = (sArr[i7 + 2] * i8) + i9;
        float[] fArr2 = this.vertices;
        MeshSpawnShapeValue.Triangle.pick(fArr2[i10], fArr2[i10 + 1], fArr2[i10 + 2], fArr2[i11], fArr2[i11 + 1], fArr2[i11 + 2], fArr2[i12], fArr2[i12 + 1], fArr2[i12 + 2], aVar);
    }

    public UnweightedMeshSpawnShapeValue() {
    }
}
