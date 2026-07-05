package com.badlogic.gdx.graphics.g3d.model;

import b0.a;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class MeshPart {
    private static final a bounds = new a();
    public String id;
    public Mesh mesh;
    public int offset;
    public int primitiveType;
    public int size;
    public final com.badlogic.gdx.math.a center = new com.badlogic.gdx.math.a();
    public final com.badlogic.gdx.math.a halfExtents = new com.badlogic.gdx.math.a();
    public float radius = -1.0f;

    public MeshPart() {
    }

    public boolean equals(MeshPart meshPart) {
        return meshPart == this || (meshPart != null && meshPart.mesh == this.mesh && meshPart.primitiveType == this.primitiveType && meshPart.offset == this.offset && meshPart.size == this.size);
    }

    public void render(ShaderProgram shaderProgram, boolean z2) {
        this.mesh.render(shaderProgram, this.primitiveType, this.offset, this.size, z2);
    }

    public MeshPart set(MeshPart meshPart) {
        this.id = meshPart.id;
        this.mesh = meshPart.mesh;
        this.offset = meshPart.offset;
        this.size = meshPart.size;
        this.primitiveType = meshPart.primitiveType;
        this.center.u(meshPart.center);
        this.halfExtents.u(meshPart.halfExtents);
        this.radius = meshPart.radius;
        return this;
    }

    public void update() {
        Mesh mesh = this.mesh;
        a aVar = bounds;
        mesh.calculateBoundingBox(aVar, this.offset, this.size);
        aVar.d(this.center);
        com.badlogic.gdx.math.a aVar2 = this.halfExtents;
        aVar.e(aVar2);
        aVar2.s(0.5f);
        this.radius = this.halfExtents.h();
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj instanceof MeshPart) {
            return equals((MeshPart) obj);
        }
        return false;
    }

    public void render(ShaderProgram shaderProgram) {
        this.mesh.render(shaderProgram, this.primitiveType, this.offset, this.size);
    }

    public MeshPart(String str, Mesh mesh, int i2, int i3, int i4) {
        set(str, mesh, i2, i3, i4);
    }

    public MeshPart set(String str, Mesh mesh, int i2, int i3, int i4) {
        this.id = str;
        this.mesh = mesh;
        this.offset = i2;
        this.size = i3;
        this.primitiveType = i4;
        this.center.t(0.0f, 0.0f, 0.0f);
        this.halfExtents.t(0.0f, 0.0f, 0.0f);
        this.radius = -1.0f;
        return this;
    }

    public MeshPart(MeshPart meshPart) {
        set(meshPart);
    }
}
