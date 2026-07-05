package com.badlogic.gdx.graphics.g3d.model;

import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Renderable;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.b;
import java.util.Arrays;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class NodePart {
    public Matrix4[] bones;
    public boolean enabled = true;
    public b<Node, Matrix4> invBoneBindTransforms;
    public Material material;
    public MeshPart meshPart;

    public NodePart() {
    }

    public NodePart copy() {
        return new NodePart().set(this);
    }

    protected NodePart set(NodePart nodePart) {
        this.meshPart = new MeshPart(nodePart.meshPart);
        this.material = nodePart.material;
        this.enabled = nodePart.enabled;
        b<Node, Matrix4> bVar = nodePart.invBoneBindTransforms;
        if (bVar != null) {
            b<Node, Matrix4> bVar2 = this.invBoneBindTransforms;
            int i2 = 0;
            if (bVar2 == null) {
                this.invBoneBindTransforms = new b<>(true, bVar.f1767c, Node.class);
            } else {
                Arrays.fill(bVar2.f1765a, 0, bVar2.f1767c, (Object) null);
                Arrays.fill(bVar2.f1766b, 0, bVar2.f1767c, (Object) null);
                bVar2.f1767c = 0;
            }
            this.invBoneBindTransforms.d(nodePart.invBoneBindTransforms);
            Matrix4[] matrix4Arr = this.bones;
            if (matrix4Arr == null || matrix4Arr.length != this.invBoneBindTransforms.f1767c) {
                this.bones = new Matrix4[this.invBoneBindTransforms.f1767c];
            }
            while (true) {
                Matrix4[] matrix4Arr2 = this.bones;
                if (i2 >= matrix4Arr2.length) {
                    break;
                }
                if (matrix4Arr2[i2] == null) {
                    matrix4Arr2[i2] = new Matrix4();
                }
                i2++;
            }
        } else {
            this.invBoneBindTransforms = null;
            this.bones = null;
        }
        return this;
    }

    public Renderable setRenderable(Renderable renderable) {
        renderable.material = this.material;
        renderable.meshPart.set(this.meshPart);
        renderable.bones = this.bones;
        return renderable;
    }

    public NodePart(MeshPart meshPart, Material material) {
        this.meshPart = meshPart;
        this.material = material;
    }
}
