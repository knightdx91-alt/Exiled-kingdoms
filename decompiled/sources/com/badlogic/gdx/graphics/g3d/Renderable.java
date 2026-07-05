package com.badlogic.gdx.graphics.g3d;

import com.badlogic.gdx.graphics.g3d.model.MeshPart;
import com.badlogic.gdx.math.Matrix4;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class Renderable {
    public Matrix4[] bones;
    public Environment environment;
    public Material material;
    public Shader shader;
    public Object userData;
    public final Matrix4 worldTransform = new Matrix4();
    public final MeshPart meshPart = new MeshPart();

    public Renderable set(Renderable renderable) {
        this.worldTransform.o(renderable.worldTransform);
        this.material = renderable.material;
        this.meshPart.set(renderable.meshPart);
        this.bones = renderable.bones;
        this.environment = renderable.environment;
        this.shader = renderable.shader;
        this.userData = renderable.userData;
        return this;
    }
}
