package com.badlogic.gdx.graphics.g3d.particles.values;

import a0.j;
import a0.o;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.particles.ResourceData;
import com.badlogic.gdx.math.a;
import com.badlogic.gdx.utils.m;
import r.d;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public abstract class MeshSpawnShapeValue extends SpawnShapeValue {
    protected Mesh mesh;
    protected Model model;

    public MeshSpawnShapeValue(MeshSpawnShapeValue meshSpawnShapeValue) {
        super(meshSpawnShapeValue);
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.values.SpawnShapeValue, com.badlogic.gdx.graphics.g3d.particles.values.ParticleValue
    public void load(ParticleValue particleValue) {
        super.load(particleValue);
        MeshSpawnShapeValue meshSpawnShapeValue = (MeshSpawnShapeValue) particleValue;
        setMesh(meshSpawnShapeValue.mesh, meshSpawnShapeValue.model);
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.values.SpawnShapeValue, com.badlogic.gdx.graphics.g3d.particles.ResourceData.Configurable
    public void save(d dVar, ResourceData resourceData) {
        if (this.model != null) {
            ResourceData.SaveData saveDataCreateSaveData = resourceData.createSaveData();
            saveDataCreateSaveData.saveAsset(dVar.h(this.model), Model.class);
            saveDataCreateSaveData.save("index", Integer.valueOf(this.model.meshes.h(this.mesh, true)));
        }
    }

    public void setMesh(Mesh mesh, Model model) {
        if (mesh.getVertexAttribute(1) == null) {
            throw new m("Mesh vertices must have Usage.Position");
        }
        this.model = model;
        this.mesh = mesh;
    }

    public static class Triangle {
        float x1;
        float x2;
        float x3;
        float y1;
        float y2;
        float y3;
        float z1;
        float z2;
        float z3;

        public Triangle(float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10) {
            this.x1 = f2;
            this.y1 = f3;
            this.z1 = f4;
            this.x2 = f5;
            this.y2 = f6;
            this.z2 = f7;
            this.x3 = f8;
            this.y3 = f9;
            this.z3 = f10;
        }

        public static a pick(float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, a aVar) {
            o oVar = j.f69a;
            float fNextFloat = oVar.nextFloat();
            float fNextFloat2 = oVar.nextFloat();
            aVar.t(a.a.C(f8, f2, fNextFloat2, a.a.C(f5, f2, fNextFloat, f2)), a.a.C(f9, f3, fNextFloat2, a.a.C(f6, f3, fNextFloat, f3)), a.a.C(f10, f4, fNextFloat2, a.a.C(f7, f4, fNextFloat, f4)));
            return aVar;
        }

        public a pick(a aVar) {
            o oVar = j.f69a;
            float fNextFloat = oVar.nextFloat();
            float fNextFloat2 = oVar.nextFloat();
            float f2 = this.x1;
            float fC = a.a.C(this.x3, f2, fNextFloat2, a.a.C(this.x2, f2, fNextFloat, f2));
            float f3 = this.y1;
            float fC2 = a.a.C(this.y3, f3, fNextFloat2, a.a.C(this.y2, f3, fNextFloat, f3));
            float f4 = this.z1;
            aVar.t(fC, fC2, a.a.C(this.z3, f4, fNextFloat2, a.a.C(this.z2, f4, fNextFloat, f4)));
            return aVar;
        }
    }

    public MeshSpawnShapeValue() {
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.values.SpawnShapeValue, com.badlogic.gdx.graphics.g3d.particles.ResourceData.Configurable
    public void load(d dVar, ResourceData resourceData) {
        ResourceData.SaveData saveData = resourceData.getSaveData();
        r.a aVarLoadAsset = saveData.loadAsset();
        if (aVarLoadAsset != null) {
            Model model = (Model) dVar.f(aVarLoadAsset);
            setMesh(model.meshes.get(((Integer) saveData.load("index")).intValue()), model);
        }
    }

    public void setMesh(Mesh mesh) {
        setMesh(mesh, null);
    }
}
