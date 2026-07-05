package com.badlogic.gdx.graphics.g3d.model;

import a0.n;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.a;
import com.badlogic.gdx.utils.a;
import com.badlogic.gdx.utils.b;
import com.badlogic.gdx.utils.m;
import java.util.Iterator;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class Node {
    public String id;
    public boolean isAnimated;
    protected Node parent;
    public boolean inheritTransform = true;
    public final a translation = new a();
    public final n rotation = new n(0.0f, 0.0f, 0.0f, 1.0f);
    public final a scale = new a(1.0f, 1.0f, 1.0f);
    public final Matrix4 localTransform = new Matrix4();
    public final Matrix4 globalTransform = new Matrix4();
    public com.badlogic.gdx.utils.a<NodePart> parts = new com.badlogic.gdx.utils.a<>(true, 2);
    private final com.badlogic.gdx.utils.a<Node> children = new com.badlogic.gdx.utils.a<>(true, 2);

    public static Node getNode(com.badlogic.gdx.utils.a<Node> aVar, String str, boolean z2, boolean z3) {
        int i2 = aVar.f1750b;
        if (z3) {
            for (int i3 = 0; i3 < i2; i3++) {
                Node node = aVar.get(i3);
                if (node.id.equalsIgnoreCase(str)) {
                    return node;
                }
            }
        } else {
            for (int i4 = 0; i4 < i2; i4++) {
                Node node2 = aVar.get(i4);
                if (node2.id.equals(str)) {
                    return node2;
                }
            }
        }
        if (!z2) {
            return null;
        }
        for (int i5 = 0; i5 < i2; i5++) {
            Node node3 = getNode(aVar.get(i5).children, str, true, z3);
            if (node3 != null) {
                return node3;
            }
        }
        return null;
    }

    public <T extends Node> int addChild(T t2) {
        return insertChild(-1, t2);
    }

    public <T extends Node> int addChildren(Iterable<T> iterable) {
        return insertChildren(-1, iterable);
    }

    public <T extends Node> void attachTo(T t2) {
        t2.addChild(this);
    }

    public void calculateBoneTransforms(boolean z2) {
        Matrix4[] matrix4Arr;
        int i2;
        a.b<NodePart> it = this.parts.iterator();
        while (it.hasNext()) {
            NodePart next = it.next();
            b<Node, Matrix4> bVar = next.invBoneBindTransforms;
            if (bVar != null && (matrix4Arr = next.bones) != null && (i2 = bVar.f1767c) == matrix4Arr.length) {
                for (int i3 = 0; i3 < i2; i3++) {
                    Matrix4 matrix4 = next.bones[i3];
                    matrix4.o(next.invBoneBindTransforms.f1765a[i3].globalTransform);
                    matrix4.g(next.invBoneBindTransforms.f1766b[i3]);
                }
            }
        }
        if (z2) {
            a.b<Node> it2 = this.children.iterator();
            while (it2.hasNext()) {
                it2.next().calculateBoneTransforms(true);
            }
        }
    }

    public b0.a calculateBoundingBox(b0.a aVar) {
        aVar.f();
        return extendBoundingBox(aVar);
    }

    public Matrix4 calculateLocalTransform() {
        if (!this.isAnimated) {
            Matrix4 matrix4 = this.localTransform;
            com.badlogic.gdx.math.a aVar = this.translation;
            n nVar = this.rotation;
            com.badlogic.gdx.math.a aVar2 = this.scale;
            matrix4.getClass();
            matrix4.m(aVar.f1729a, aVar.f1730b, aVar.f1731c, nVar.f83a, nVar.f84b, nVar.f85c, nVar.f86d, aVar2.f1729a, aVar2.f1730b, aVar2.f1731c);
        }
        return this.localTransform;
    }

    public void calculateTransforms(boolean z2) {
        calculateLocalTransform();
        calculateWorldTransform();
        if (z2) {
            a.b<Node> it = this.children.iterator();
            while (it.hasNext()) {
                it.next().calculateTransforms(true);
            }
        }
    }

    public Matrix4 calculateWorldTransform() {
        Node node;
        if (!this.inheritTransform || (node = this.parent) == null) {
            this.globalTransform.o(this.localTransform);
        } else {
            Matrix4 matrix4 = this.globalTransform;
            matrix4.o(node.globalTransform);
            matrix4.g(this.localTransform);
        }
        return this.globalTransform;
    }

    public Node copy() {
        return new Node().set(this);
    }

    public void detach() {
        Node node = this.parent;
        if (node != null) {
            node.removeChild(this);
            this.parent = null;
        }
    }

    public b0.a extendBoundingBox(b0.a aVar) {
        return extendBoundingBox(aVar, true);
    }

    public Node getChild(int i2) {
        return this.children.get(i2);
    }

    public int getChildCount() {
        return this.children.f1750b;
    }

    public Iterable<Node> getChildren() {
        return this.children;
    }

    public Node getParent() {
        return this.parent;
    }

    public boolean hasChildren() {
        com.badlogic.gdx.utils.a<Node> aVar = this.children;
        return aVar != null && aVar.f1750b > 0;
    }

    public boolean hasParent() {
        return this.parent != null;
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0034  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public <T extends Node> int insertChild(int i2, T t2) {
        for (Node parent = this; parent != null; parent = parent.getParent()) {
            if (parent == t2) {
                throw new m("Cannot add a parent as a child");
            }
        }
        Node parent2 = t2.getParent();
        if (parent2 != null && !parent2.removeChild(t2)) {
            throw new m("Could not remove child from its current parent");
        }
        if (i2 >= 0) {
            com.badlogic.gdx.utils.a<Node> aVar = this.children;
            if (i2 >= aVar.f1750b) {
                com.badlogic.gdx.utils.a<Node> aVar2 = this.children;
                int i3 = aVar2.f1750b;
                aVar2.a(t2);
                i2 = i3;
            } else {
                aVar.i(i2, t2);
            }
        }
        t2.parent = this;
        return i2;
    }

    public <T extends Node> int insertChildren(int i2, Iterable<T> iterable) {
        if (i2 < 0 || i2 > this.children.f1750b) {
            i2 = this.children.f1750b;
        }
        Iterator<T> it = iterable.iterator();
        int i3 = i2;
        while (it.hasNext()) {
            insertChild(i3, it.next());
            i3++;
        }
        return i2;
    }

    public <T extends Node> boolean removeChild(T t2) {
        if (!this.children.q(t2, true)) {
            return false;
        }
        t2.parent = null;
        return true;
    }

    protected Node set(Node node) {
        detach();
        this.id = node.id;
        this.isAnimated = node.isAnimated;
        this.inheritTransform = node.inheritTransform;
        this.translation.u(node.translation);
        this.rotation.f(node.rotation);
        this.scale.u(node.scale);
        this.localTransform.o(node.localTransform);
        this.globalTransform.o(node.globalTransform);
        this.parts.clear();
        a.b<NodePart> it = node.parts.iterator();
        while (it.hasNext()) {
            this.parts.a(it.next().copy());
        }
        this.children.clear();
        Iterator<Node> it2 = node.getChildren().iterator();
        while (it2.hasNext()) {
            addChild(it2.next().copy());
        }
        return this;
    }

    public b0.a extendBoundingBox(b0.a aVar, boolean z2) {
        int i2 = this.parts.f1750b;
        for (int i3 = 0; i3 < i2; i3++) {
            NodePart nodePart = this.parts.get(i3);
            if (nodePart.enabled) {
                MeshPart meshPart = nodePart.meshPart;
                if (z2) {
                    meshPart.mesh.extendBoundingBox(aVar, meshPart.offset, meshPart.size, this.globalTransform);
                } else {
                    meshPart.mesh.extendBoundingBox(aVar, meshPart.offset, meshPart.size);
                }
            }
        }
        int i4 = this.children.f1750b;
        for (int i5 = 0; i5 < i4; i5++) {
            this.children.get(i5).extendBoundingBox(aVar);
        }
        return aVar;
    }

    public Node getChild(String str, boolean z2, boolean z3) {
        return getNode(this.children, str, z2, z3);
    }

    public b0.a calculateBoundingBox(b0.a aVar, boolean z2) {
        aVar.f();
        return extendBoundingBox(aVar, z2);
    }
}
