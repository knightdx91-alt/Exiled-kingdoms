package com.badlogic.gdx.graphics.g3d.model.data;

import com.badlogic.gdx.utils.a;
import com.badlogic.gdx.utils.m;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class ModelData {
    public String id;
    public final short[] version = new short[2];
    public final a<ModelMesh> meshes = new a<>();
    public final a<ModelMaterial> materials = new a<>();
    public final a<ModelNode> nodes = new a<>();
    public final a<ModelAnimation> animations = new a<>();

    public void addMesh(ModelMesh modelMesh) {
        a.b<ModelMesh> it = this.meshes.iterator();
        while (it.hasNext()) {
            ModelMesh next = it.next();
            if (next.id.equals(modelMesh.id)) {
                throw new m(a.a.m(next.id, "' already in model", new StringBuilder("Mesh with id '")));
            }
        }
        this.meshes.a(modelMesh);
    }
}
