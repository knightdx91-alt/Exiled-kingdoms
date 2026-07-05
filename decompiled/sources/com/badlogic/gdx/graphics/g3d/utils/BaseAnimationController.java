package com.badlogic.gdx.graphics.g3d.utils;

import a0.n;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.model.Animation;
import com.badlogic.gdx.graphics.g3d.model.Node;
import com.badlogic.gdx.graphics.g3d.model.NodeAnimation;
import com.badlogic.gdx.graphics.g3d.model.NodeKeyframe;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.a;
import com.badlogic.gdx.utils.a;
import com.badlogic.gdx.utils.c0;
import com.badlogic.gdx.utils.m;
import com.badlogic.gdx.utils.y;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class BaseAnimationController {
    public final ModelInstance target;
    private static final y<Node, Transform> transforms = new y<>();
    private static final Transform tmpT = new Transform();
    private final c0<Transform> transformPool = new c0<Transform>() { // from class: com.badlogic.gdx.graphics.g3d.utils.BaseAnimationController.1
        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.badlogic.gdx.utils.c0
        public Transform newObject() {
            return new Transform();
        }
    };
    private boolean applying = false;

    public static final class Transform implements c0.a {
        public final a translation = new a();
        public final n rotation = new n();
        public final a scale = new a(1.0f, 1.0f, 1.0f);

        public Transform idt() {
            this.translation.t(0.0f, 0.0f, 0.0f);
            this.rotation.e(0.0f, 0.0f, 0.0f, 1.0f);
            this.scale.t(1.0f, 1.0f, 1.0f);
            return this;
        }

        public Transform lerp(Transform transform, float f2) {
            return lerp(transform.translation, transform.rotation, transform.scale, f2);
        }

        @Override // com.badlogic.gdx.utils.c0.a
        public void reset() {
            idt();
        }

        public Transform set(a aVar, n nVar, a aVar2) {
            this.translation.u(aVar);
            this.rotation.f(nVar);
            this.scale.u(aVar2);
            return this;
        }

        public Matrix4 toMatrix4(Matrix4 matrix4) {
            a aVar = this.translation;
            n nVar = this.rotation;
            a aVar2 = this.scale;
            matrix4.getClass();
            matrix4.m(aVar.f1729a, aVar.f1730b, aVar.f1731c, nVar.f83a, nVar.f84b, nVar.f85c, nVar.f86d, aVar2.f1729a, aVar2.f1730b, aVar2.f1731c);
            return matrix4;
        }

        public String toString() {
            return this.translation.toString() + " - " + this.rotation.toString() + " - " + this.scale.toString();
        }

        public Transform lerp(a aVar, n nVar, a aVar2, float f2) {
            this.translation.k(aVar, f2);
            this.rotation.j(nVar, f2);
            this.scale.k(aVar2, f2);
            return this;
        }

        public Transform set(Transform transform) {
            return set(transform.translation, transform.rotation, transform.scale);
        }
    }

    public BaseAnimationController(ModelInstance modelInstance) {
        this.target = modelInstance;
    }

    private static final void applyNodeAnimationBlending(NodeAnimation nodeAnimation, y<Node, Transform> yVar, c0<Transform> c0Var, float f2, float f3) {
        Node node = nodeAnimation.node;
        node.isAnimated = true;
        Transform nodeAnimationTransform = getNodeAnimationTransform(nodeAnimation, f3);
        Transform transformE = yVar.e(node, null);
        if (transformE != null) {
            if (f2 > 0.999999f) {
                transformE.set(nodeAnimationTransform);
                return;
            } else {
                transformE.lerp(nodeAnimationTransform, f2);
                return;
            }
        }
        if (f2 > 0.999999f) {
            yVar.j(node, c0Var.obtain().set(nodeAnimationTransform));
        } else {
            yVar.j(node, c0Var.obtain().set(node.translation, node.rotation, node.scale).lerp(nodeAnimationTransform, f2));
        }
    }

    private static final void applyNodeAnimationDirectly(NodeAnimation nodeAnimation, float f2) {
        Node node = nodeAnimation.node;
        node.isAnimated = true;
        getNodeAnimationTransform(nodeAnimation, f2).toMatrix4(node.localTransform);
    }

    static final <T> int getFirstKeyframeIndexAtTime(com.badlogic.gdx.utils.a<NodeKeyframe<T>> aVar, float f2) {
        int i2 = aVar.f1750b - 1;
        int i3 = 0;
        if (i2 > 0 && f2 >= aVar.get(0).keytime && f2 <= aVar.get(i2).keytime) {
            while (i3 < i2) {
                int i4 = (i3 + i2) / 2;
                int i5 = i4 + 1;
                if (f2 > aVar.get(i5).keytime) {
                    i3 = i5;
                } else {
                    if (f2 >= aVar.get(i4).keytime) {
                        return i4;
                    }
                    i2 = i4 - 1;
                }
            }
        }
        return i3;
    }

    private static final Transform getNodeAnimationTransform(NodeAnimation nodeAnimation, float f2) {
        Transform transform = tmpT;
        getTranslationAtTime(nodeAnimation, f2, transform.translation);
        getRotationAtTime(nodeAnimation, f2, transform.rotation);
        getScalingAtTime(nodeAnimation, f2, transform.scale);
        return transform;
    }

    private static final n getRotationAtTime(NodeAnimation nodeAnimation, float f2, n nVar) {
        com.badlogic.gdx.utils.a<NodeKeyframe<n>> aVar = nodeAnimation.rotation;
        if (aVar == null) {
            nVar.f(nodeAnimation.node.rotation);
            return nVar;
        }
        if (aVar.f1750b == 1) {
            nVar.f(aVar.get(0).value);
            return nVar;
        }
        int firstKeyframeIndexAtTime = getFirstKeyframeIndexAtTime(aVar, f2);
        NodeKeyframe<n> nodeKeyframe = nodeAnimation.rotation.get(firstKeyframeIndexAtTime);
        nVar.f(nodeKeyframe.value);
        int i2 = firstKeyframeIndexAtTime + 1;
        com.badlogic.gdx.utils.a<NodeKeyframe<n>> aVar2 = nodeAnimation.rotation;
        if (i2 < aVar2.f1750b) {
            NodeKeyframe<n> nodeKeyframe2 = aVar2.get(i2);
            float f3 = nodeKeyframe.keytime;
            nVar.j(nodeKeyframe2.value, (f2 - f3) / (nodeKeyframe2.keytime - f3));
        }
        return nVar;
    }

    private static final a getScalingAtTime(NodeAnimation nodeAnimation, float f2, a aVar) {
        com.badlogic.gdx.utils.a<NodeKeyframe<a>> aVar2 = nodeAnimation.scaling;
        if (aVar2 == null) {
            aVar.u(nodeAnimation.node.scale);
            return aVar;
        }
        if (aVar2.f1750b == 1) {
            aVar.u(aVar2.get(0).value);
            return aVar;
        }
        int firstKeyframeIndexAtTime = getFirstKeyframeIndexAtTime(aVar2, f2);
        NodeKeyframe<a> nodeKeyframe = nodeAnimation.scaling.get(firstKeyframeIndexAtTime);
        aVar.u(nodeKeyframe.value);
        int i2 = firstKeyframeIndexAtTime + 1;
        com.badlogic.gdx.utils.a<NodeKeyframe<a>> aVar3 = nodeAnimation.scaling;
        if (i2 < aVar3.f1750b) {
            NodeKeyframe<a> nodeKeyframe2 = aVar3.get(i2);
            float f3 = nodeKeyframe.keytime;
            aVar.k(nodeKeyframe2.value, (f2 - f3) / (nodeKeyframe2.keytime - f3));
        }
        return aVar;
    }

    private static final a getTranslationAtTime(NodeAnimation nodeAnimation, float f2, a aVar) {
        com.badlogic.gdx.utils.a<NodeKeyframe<a>> aVar2 = nodeAnimation.translation;
        if (aVar2 == null) {
            aVar.u(nodeAnimation.node.translation);
            return aVar;
        }
        if (aVar2.f1750b == 1) {
            aVar.u(aVar2.get(0).value);
            return aVar;
        }
        int firstKeyframeIndexAtTime = getFirstKeyframeIndexAtTime(aVar2, f2);
        NodeKeyframe<a> nodeKeyframe = nodeAnimation.translation.get(firstKeyframeIndexAtTime);
        aVar.u(nodeKeyframe.value);
        int i2 = firstKeyframeIndexAtTime + 1;
        com.badlogic.gdx.utils.a<NodeKeyframe<a>> aVar3 = nodeAnimation.translation;
        if (i2 < aVar3.f1750b) {
            NodeKeyframe<a> nodeKeyframe2 = aVar3.get(i2);
            float f3 = nodeKeyframe.keytime;
            aVar.k(nodeKeyframe2.value, (f2 - f3) / (nodeKeyframe2.keytime - f3));
        }
        return aVar;
    }

    protected void apply(Animation animation, float f2, float f3) {
        if (!this.applying) {
            throw new m("You must call begin() before adding an animation");
        }
        applyAnimation(transforms, this.transformPool, f3, animation, f2);
    }

    protected void applyAnimation(Animation animation, float f2) {
        if (this.applying) {
            throw new m("Call end() first");
        }
        applyAnimation(null, null, 1.0f, animation, f2);
        this.target.calculateTransforms();
    }

    protected void applyAnimations(Animation animation, float f2, Animation animation2, float f3, float f4) {
        if (animation2 == null || f4 == 0.0f) {
            applyAnimation(animation, f2);
            return;
        }
        if (animation == null || f4 == 1.0f) {
            applyAnimation(animation2, f3);
        } else {
            if (this.applying) {
                throw new m("Call end() first");
            }
            begin();
            apply(animation, f2, 1.0f);
            apply(animation2, f3, f4);
            end();
        }
    }

    protected void begin() {
        if (this.applying) {
            throw new m("You must call end() after each call to being()");
        }
        this.applying = true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    protected void end() {
        if (!this.applying) {
            throw new m("You must call begin() first");
        }
        y.a<Node, Transform> aVarB = transforms.b();
        aVarB.getClass();
        while (aVarB.hasNext()) {
            y.b next = aVarB.next();
            ((Transform) next.f2058b).toMatrix4(((Node) next.f2057a).localTransform);
            this.transformPool.free(next.f2058b);
        }
        transforms.clear();
        this.target.calculateTransforms();
        this.applying = false;
    }

    protected void removeAnimation(Animation animation) {
        a.b<NodeAnimation> it = animation.nodeAnimations.iterator();
        while (it.hasNext()) {
            it.next().node.isAnimated = false;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected static void applyAnimation(y<Node, Transform> yVar, c0<Transform> c0Var, float f2, Animation animation, float f3) {
        if (yVar == null) {
            a.b<NodeAnimation> it = animation.nodeAnimations.iterator();
            while (it.hasNext()) {
                applyNodeAnimationDirectly(it.next(), f3);
            }
            return;
        }
        y.c<Node> cVarG = yVar.g();
        cVarG.getClass();
        while (cVarG.hasNext()) {
            cVarG.next().isAnimated = false;
        }
        a.b<NodeAnimation> it2 = animation.nodeAnimations.iterator();
        while (it2.hasNext()) {
            applyNodeAnimationBlending(it2.next(), yVar, c0Var, f2, f3);
        }
        y.a<Node, Transform> aVarB = yVar.b();
        aVarB.getClass();
        while (aVarB.hasNext()) {
            y.b next = aVarB.next();
            Node node = (Node) next.f2057a;
            if (!node.isAnimated) {
                node.isAnimated = true;
                ((Transform) next.f2058b).lerp(node.translation, node.rotation, node.scale, f2);
            }
        }
    }
}
