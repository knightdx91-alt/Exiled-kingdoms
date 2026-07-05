package com.badlogic.gdx.graphics.g3d;

import a0.n;
import com.badlogic.gdx.graphics.g3d.model.Animation;
import com.badlogic.gdx.graphics.g3d.model.Node;
import com.badlogic.gdx.graphics.g3d.model.NodeAnimation;
import com.badlogic.gdx.graphics.g3d.model.NodeKeyframe;
import com.badlogic.gdx.graphics.g3d.model.NodePart;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.a;
import com.badlogic.gdx.utils.b;
import com.badlogic.gdx.utils.c0;
import java.util.Iterator;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class ModelInstance implements RenderableProvider {
    public static boolean defaultShareKeyframes = true;
    public final a<Animation> animations;
    public final a<Material> materials;
    public final Model model;
    public final a<Node> nodes;
    public Matrix4 transform;
    public Object userData;

    public ModelInstance(Model model) {
        this(model, (String[]) null);
    }

    private void copyNodes(a<Node> aVar) {
        int i2 = aVar.f1750b;
        for (int i3 = 0; i3 < i2; i3++) {
            this.nodes.a(aVar.get(i3).copy());
        }
        invalidate();
    }

    private void invalidate(Node node) {
        int i2 = node.parts.f1750b;
        for (int i3 = 0; i3 < i2; i3++) {
            NodePart nodePart = node.parts.get(i3);
            b<Node, Matrix4> bVar = nodePart.invBoneBindTransforms;
            if (bVar != null) {
                for (int i4 = 0; i4 < bVar.f1767c; i4++) {
                    Node[] nodeArr = bVar.f1765a;
                    nodeArr[i4] = getNode(nodeArr[i4].id);
                }
            }
            if (!this.materials.e(nodePart.material, true)) {
                int iH = this.materials.h(nodePart.material, false);
                if (iH < 0) {
                    a<Material> aVar = this.materials;
                    Material materialCopy = nodePart.material.copy();
                    nodePart.material = materialCopy;
                    aVar.a(materialCopy);
                } else {
                    nodePart.material = this.materials.get(iH);
                }
            }
        }
        int childCount = node.getChildCount();
        for (int i5 = 0; i5 < childCount; i5++) {
            invalidate(node.getChild(i5));
        }
    }

    public b0.a calculateBoundingBox(b0.a aVar) {
        aVar.f();
        return extendBoundingBox(aVar);
    }

    public void calculateTransforms() {
        int i2 = this.nodes.f1750b;
        for (int i3 = 0; i3 < i2; i3++) {
            this.nodes.get(i3).calculateTransforms(true);
        }
        for (int i4 = 0; i4 < i2; i4++) {
            this.nodes.get(i4).calculateBoneTransforms(true);
        }
    }

    public ModelInstance copy() {
        return new ModelInstance(this);
    }

    public void copyAnimation(Animation animation) {
        copyAnimation(animation, defaultShareKeyframes);
    }

    public void copyAnimations(Iterable<Animation> iterable) {
        Iterator<Animation> it = iterable.iterator();
        while (it.hasNext()) {
            copyAnimation(it.next(), defaultShareKeyframes);
        }
    }

    public b0.a extendBoundingBox(b0.a aVar) {
        int i2 = this.nodes.f1750b;
        for (int i3 = 0; i3 < i2; i3++) {
            this.nodes.get(i3).extendBoundingBox(aVar);
        }
        return aVar;
    }

    public Animation getAnimation(String str) {
        return getAnimation(str, false);
    }

    public Material getMaterial(String str) {
        return getMaterial(str, true);
    }

    public Node getNode(String str) {
        return getNode(str, true);
    }

    public Renderable getRenderable(Renderable renderable) {
        return getRenderable(renderable, this.nodes.get(0));
    }

    @Override // com.badlogic.gdx.graphics.g3d.RenderableProvider
    public void getRenderables(a<Renderable> aVar, c0<Renderable> c0Var) {
        a.b<Node> it = this.nodes.iterator();
        while (it.hasNext()) {
            getRenderables(it.next(), aVar, c0Var);
        }
    }

    public ModelInstance(Model model, String str, boolean z2) {
        this(model, null, str, false, false, z2);
    }

    public void copyAnimation(Animation animation, boolean z2) {
        Animation animation2 = new Animation();
        animation2.id = animation.id;
        animation2.duration = animation.duration;
        a.b<NodeAnimation> it = animation.nodeAnimations.iterator();
        while (it.hasNext()) {
            NodeAnimation next = it.next();
            Node node = getNode(next.node.id);
            if (node != null) {
                NodeAnimation nodeAnimation = new NodeAnimation();
                nodeAnimation.node = node;
                if (z2) {
                    nodeAnimation.translation = next.translation;
                    nodeAnimation.rotation = next.rotation;
                    nodeAnimation.scaling = next.scaling;
                } else {
                    if (next.translation != null) {
                        nodeAnimation.translation = new a<>();
                        a.b<NodeKeyframe<com.badlogic.gdx.math.a>> it2 = next.translation.iterator();
                        while (it2.hasNext()) {
                            NodeKeyframe<com.badlogic.gdx.math.a> next2 = it2.next();
                            nodeAnimation.translation.a(new NodeKeyframe<>(next2.keytime, next2.value));
                        }
                    }
                    if (next.rotation != null) {
                        nodeAnimation.rotation = new a<>();
                        a.b<NodeKeyframe<n>> it3 = next.rotation.iterator();
                        while (it3.hasNext()) {
                            NodeKeyframe<n> next3 = it3.next();
                            nodeAnimation.rotation.a(new NodeKeyframe<>(next3.keytime, next3.value));
                        }
                    }
                    if (next.scaling != null) {
                        nodeAnimation.scaling = new a<>();
                        a.b<NodeKeyframe<com.badlogic.gdx.math.a>> it4 = next.scaling.iterator();
                        while (it4.hasNext()) {
                            NodeKeyframe<com.badlogic.gdx.math.a> next4 = it4.next();
                            nodeAnimation.scaling.a(new NodeKeyframe<>(next4.keytime, next4.value));
                        }
                    }
                }
                if (nodeAnimation.translation != null || nodeAnimation.rotation != null || nodeAnimation.scaling != null) {
                    animation2.nodeAnimations.a(nodeAnimation);
                }
            }
        }
        if (animation2.nodeAnimations.f1750b > 0) {
            this.animations.a(animation2);
        }
    }

    public Animation getAnimation(String str, boolean z2) {
        int i2 = this.animations.f1750b;
        int i3 = 0;
        if (z2) {
            while (i3 < i2) {
                Animation animation = this.animations.get(i3);
                if (animation.id.equalsIgnoreCase(str)) {
                    return animation;
                }
                i3++;
            }
            return null;
        }
        while (i3 < i2) {
            Animation animation2 = this.animations.get(i3);
            if (animation2.id.equals(str)) {
                return animation2;
            }
            i3++;
        }
        return null;
    }

    public Material getMaterial(String str, boolean z2) {
        int i2 = this.materials.f1750b;
        int i3 = 0;
        if (z2) {
            while (i3 < i2) {
                Material material = this.materials.get(i3);
                if (material.id.equalsIgnoreCase(str)) {
                    return material;
                }
                i3++;
            }
            return null;
        }
        while (i3 < i2) {
            Material material2 = this.materials.get(i3);
            if (material2.id.equals(str)) {
                return material2;
            }
            i3++;
        }
        return null;
    }

    public Node getNode(String str, boolean z2) {
        return getNode(str, z2, false);
    }

    public Renderable getRenderable(Renderable renderable, Node node) {
        return getRenderable(renderable, node, node.parts.get(0));
    }

    public ModelInstance(Model model, Matrix4 matrix4, String str, boolean z2) {
        this(model, matrix4, str, false, false, z2);
    }

    public void copyAnimations(Iterable<Animation> iterable, boolean z2) {
        Iterator<Animation> it = iterable.iterator();
        while (it.hasNext()) {
            copyAnimation(it.next(), z2);
        }
    }

    public Node getNode(String str, boolean z2, boolean z3) {
        return Node.getNode(this.nodes, str, z2, z3);
    }

    public Renderable getRenderable(Renderable renderable, Node node, NodePart nodePart) {
        Matrix4 matrix4;
        nodePart.setRenderable(renderable);
        if (nodePart.bones == null && (matrix4 = this.transform) != null) {
            Matrix4 matrix42 = renderable.worldTransform;
            matrix42.o(matrix4);
            matrix42.g(node.globalTransform);
        } else {
            Matrix4 matrix43 = this.transform;
            if (matrix43 != null) {
                renderable.worldTransform.o(matrix43);
            } else {
                renderable.worldTransform.d();
            }
        }
        renderable.userData = this.userData;
        return renderable;
    }

    protected void getRenderables(Node node, a<Renderable> aVar, c0<Renderable> c0Var) {
        a<NodePart> aVar2 = node.parts;
        if (aVar2.f1750b > 0) {
            a.b<NodePart> it = aVar2.iterator();
            while (it.hasNext()) {
                NodePart next = it.next();
                if (next.enabled) {
                    aVar.a(getRenderable(c0Var.obtain(), node, next));
                }
            }
        }
        Iterator<Node> it2 = node.getChildren().iterator();
        while (it2.hasNext()) {
            getRenderables(it2.next(), aVar, c0Var);
        }
    }

    public ModelInstance(Model model, String str, boolean z2, boolean z3) {
        this(model, null, str, true, z2, z3);
    }

    public ModelInstance(Model model, Matrix4 matrix4, String str, boolean z2, boolean z3) {
        this(model, matrix4, str, true, z2, z3);
    }

    private void copyNodes(a<Node> aVar, String... strArr) {
        int i2 = aVar.f1750b;
        for (int i3 = 0; i3 < i2; i3++) {
            Node node = aVar.get(i3);
            int length = strArr.length;
            int i4 = 0;
            while (true) {
                if (i4 >= length) {
                    break;
                }
                if (strArr[i4].equals(node.id)) {
                    this.nodes.a(node.copy());
                    break;
                }
                i4++;
            }
        }
        invalidate();
    }

    public ModelInstance(Model model, String str, boolean z2, boolean z3, boolean z4) {
        this(model, null, str, z2, z3, z4);
    }

    public ModelInstance(Model model, Matrix4 matrix4, String str, boolean z2, boolean z3, boolean z4) {
        this(model, matrix4, str, z2, z3, z4, defaultShareKeyframes);
    }

    public ModelInstance(Model model, Matrix4 matrix4, String str, boolean z2, boolean z3, boolean z4, boolean z5) {
        this.materials = new a<>();
        a<Node> aVar = new a<>();
        this.nodes = aVar;
        this.animations = new a<>();
        this.model = model;
        this.transform = matrix4 == null ? new Matrix4() : matrix4;
        Node node = model.getNode(str, z2);
        Node nodeCopy = node.copy();
        aVar.a(nodeCopy);
        if (z4) {
            this.transform.g(z3 ? node.globalTransform : node.localTransform);
            nodeCopy.translation.t(0.0f, 0.0f, 0.0f);
            nodeCopy.rotation.e(0.0f, 0.0f, 0.0f, 1.0f);
            nodeCopy.scale.t(1.0f, 1.0f, 1.0f);
        } else if (z3 && nodeCopy.hasParent()) {
            this.transform.g(node.getParent().globalTransform);
        }
        invalidate();
        copyAnimations(model.animations, z5);
        calculateTransforms();
    }

    private void copyNodes(a<Node> aVar, a<String> aVar2) {
        int i2 = aVar.f1750b;
        for (int i3 = 0; i3 < i2; i3++) {
            Node node = aVar.get(i3);
            a.b<String> it = aVar2.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (it.next().equals(node.id)) {
                        this.nodes.a(node.copy());
                        break;
                    }
                } else {
                    break;
                }
            }
        }
        invalidate();
    }

    private void invalidate() {
        int i2 = this.nodes.f1750b;
        for (int i3 = 0; i3 < i2; i3++) {
            invalidate(this.nodes.get(i3));
        }
    }

    public ModelInstance(Model model, String... strArr) {
        this(model, (Matrix4) null, strArr);
    }

    public ModelInstance(Model model, Matrix4 matrix4, String... strArr) {
        this.materials = new a<>();
        this.nodes = new a<>();
        this.animations = new a<>();
        this.model = model;
        this.transform = matrix4 == null ? new Matrix4() : matrix4;
        if (strArr == null) {
            copyNodes(model.nodes);
        } else {
            copyNodes(model.nodes, strArr);
        }
        copyAnimations(model.animations, defaultShareKeyframes);
        calculateTransforms();
    }

    public ModelInstance(Model model, a<String> aVar) {
        this(model, (Matrix4) null, aVar);
    }

    public ModelInstance(Model model, Matrix4 matrix4, a<String> aVar) {
        this(model, matrix4, aVar, defaultShareKeyframes);
    }

    public ModelInstance(Model model, Matrix4 matrix4, a<String> aVar, boolean z2) {
        this.materials = new a<>();
        this.nodes = new a<>();
        this.animations = new a<>();
        this.model = model;
        this.transform = matrix4 == null ? new Matrix4() : matrix4;
        copyNodes(model.nodes, aVar);
        copyAnimations(model.animations, z2);
        calculateTransforms();
    }

    public ModelInstance(Model model, com.badlogic.gdx.math.a aVar) {
        this(model);
        Matrix4 matrix4 = this.transform;
        matrix4.d();
        float f2 = aVar.f1729a;
        float[] fArr = matrix4.f1724a;
        fArr[12] = f2;
        fArr[13] = aVar.f1730b;
        fArr[14] = aVar.f1731c;
    }

    public ModelInstance(Model model, float f2, float f3, float f4) {
        this(model);
        this.transform.t(f2, f3, f4);
    }

    public ModelInstance(Model model, Matrix4 matrix4) {
        this(model, matrix4, (String[]) null);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public ModelInstance(ModelInstance modelInstance) {
        Matrix4 matrix4 = modelInstance.transform;
        matrix4.getClass();
        this(modelInstance, new Matrix4(matrix4));
    }

    public ModelInstance(ModelInstance modelInstance, Matrix4 matrix4) {
        this(modelInstance, matrix4, defaultShareKeyframes);
    }

    public ModelInstance(ModelInstance modelInstance, Matrix4 matrix4, boolean z2) {
        this.materials = new a<>();
        this.nodes = new a<>();
        this.animations = new a<>();
        this.model = modelInstance.model;
        this.transform = matrix4 == null ? new Matrix4() : matrix4;
        copyNodes(modelInstance.nodes);
        copyAnimations(modelInstance.animations, z2);
        calculateTransforms();
    }
}
