package com.badlogic.gdx.graphics.g3d.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.model.MeshPart;
import com.badlogic.gdx.graphics.g3d.model.Node;
import com.badlogic.gdx.graphics.g3d.model.NodePart;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.a;
import com.badlogic.gdx.utils.i;
import com.badlogic.gdx.utils.m;
import java.util.Iterator;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class ModelBuilder {
    private Model model;
    private Node node;
    private a<MeshBuilder> builders = new a<>();
    private Matrix4 tmpTransform = new Matrix4();

    private void endnode() {
        if (this.node != null) {
            this.node = null;
        }
    }

    private MeshBuilder getBuilder(VertexAttributes vertexAttributes) {
        a.b<MeshBuilder> it = this.builders.iterator();
        while (it.hasNext()) {
            MeshBuilder next = it.next();
            if (next.getAttributes().equals(vertexAttributes) && next.lastIndex() < 16383) {
                return next;
            }
        }
        MeshBuilder meshBuilder = new MeshBuilder();
        meshBuilder.begin(vertexAttributes);
        this.builders.a(meshBuilder);
        return meshBuilder;
    }

    public static void rebuildReferences(Model model) {
        model.materials.clear();
        model.meshes.clear();
        model.meshParts.clear();
        a.b<Node> it = model.nodes.iterator();
        while (it.hasNext()) {
            rebuildReferences(model, it.next());
        }
    }

    public void begin() {
        if (this.model != null) {
            throw new m("Call end() first");
        }
        this.node = null;
        this.model = new Model();
        this.builders.clear();
    }

    public Model createArrow(float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, int i2, int i3, Material material, long j2) {
        begin();
        part("arrow", i3, j2, material).arrow(f2, f3, f4, f5, f6, f7, f8, f9, i2);
        return end();
    }

    public Model createBox(float f2, float f3, float f4, Material material, long j2) {
        return createBox(f2, f3, f4, 4, material, j2);
    }

    public Model createCapsule(float f2, float f3, int i2, Material material, long j2) {
        return createCapsule(f2, f3, i2, 4, material, j2);
    }

    public Model createCone(float f2, float f3, float f4, int i2, Material material, long j2) {
        return createCone(f2, f3, f4, i2, 4, material, j2);
    }

    public Model createCylinder(float f2, float f3, float f4, int i2, Material material, long j2) {
        return createCylinder(f2, f3, f4, i2, 4, material, j2);
    }

    public Model createLineGrid(int i2, int i3, float f2, float f3, Material material, long j2) {
        begin();
        MeshPartBuilder meshPartBuilderPart = part("lines", 1, j2, material);
        float f4 = (i2 * f2) / 2.0f;
        float f5 = (i3 * f3) / 2.0f;
        float f6 = -f4;
        float f7 = -f5;
        float f8 = f6;
        float f9 = f8;
        for (int i4 = 0; i4 <= i2; i4++) {
            meshPartBuilderPart.line(f8, 0.0f, f5, f9, 0.0f, f7);
            f8 += f2;
            f9 += f2;
        }
        float f10 = f7;
        for (int i5 = 0; i5 <= i3; i5++) {
            meshPartBuilderPart.line(f6, 0.0f, f7, f4, 0.0f, f10);
            f7 += f3;
            f10 += f3;
        }
        return end();
    }

    public Model createRect(float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13, float f14, float f15, float f16, Material material, long j2) {
        return createRect(f2, f3, f4, f5, f6, f7, f8, f9, f10, f11, f12, f13, f14, f15, f16, 4, material, j2);
    }

    public Model createSphere(float f2, float f3, float f4, int i2, int i3, Material material, long j2) {
        return createSphere(f2, f3, f4, i2, i3, 4, material, j2);
    }

    public Model createXYZCoordinates(float f2, float f3, float f4, int i2, int i3, Material material, long j2) {
        begin();
        node();
        MeshPartBuilder meshPartBuilderPart = part("xyz", i3, j2, material);
        meshPartBuilderPart.setColor(Color.RED);
        meshPartBuilderPart.arrow(0.0f, 0.0f, 0.0f, f2, 0.0f, 0.0f, f3, f4, i2);
        meshPartBuilderPart.setColor(Color.GREEN);
        meshPartBuilderPart.arrow(0.0f, 0.0f, 0.0f, 0.0f, f2, 0.0f, f3, f4, i2);
        meshPartBuilderPart.setColor(Color.BLUE);
        meshPartBuilderPart.arrow(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, f2, f3, f4, i2);
        return end();
    }

    public Model end() {
        Model model = this.model;
        if (model == null) {
            throw new m("Call begin() first");
        }
        endnode();
        this.model = null;
        a.b<MeshBuilder> it = this.builders.iterator();
        while (it.hasNext()) {
            it.next().end();
        }
        this.builders.clear();
        rebuildReferences(model);
        return model;
    }

    public void manage(i iVar) {
        Model model = this.model;
        if (model == null) {
            throw new m("Call begin() first");
        }
        model.manageDisposable(iVar);
    }

    protected Node node(Node node) {
        if (this.model == null) {
            throw new m("Call begin() first");
        }
        endnode();
        this.model.nodes.a(node);
        this.node = node;
        return node;
    }

    public void part(MeshPart meshPart, Material material) {
        if (this.node == null) {
            node();
        }
        this.node.parts.a(new NodePart(meshPart, material));
    }

    public Model createBox(float f2, float f3, float f4, int i2, Material material, long j2) {
        begin();
        part("box", i2, j2, material).box(f2, f3, f4);
        return end();
    }

    public Model createCapsule(float f2, float f3, int i2, int i3, Material material, long j2) {
        begin();
        part("capsule", i3, j2, material).capsule(f2, f3, i2);
        return end();
    }

    public Model createCone(float f2, float f3, float f4, int i2, int i3, Material material, long j2) {
        return createCone(f2, f3, f4, i2, i3, material, j2, 0.0f, 360.0f);
    }

    public Model createCylinder(float f2, float f3, float f4, int i2, int i3, Material material, long j2) {
        return createCylinder(f2, f3, f4, i2, i3, material, j2, 0.0f, 360.0f);
    }

    public Model createRect(float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13, float f14, float f15, float f16, int i2, Material material, long j2) {
        begin();
        part("rect", i2, j2, material).rect(f2, f3, f4, f5, f6, f7, f8, f9, f10, f11, f12, f13, f14, f15, f16);
        return end();
    }

    public Model createSphere(float f2, float f3, float f4, int i2, int i3, int i4, Material material, long j2) {
        return createSphere(f2, f3, f4, i2, i3, i4, material, j2, 0.0f, 360.0f, 0.0f, 180.0f);
    }

    public Model createCone(float f2, float f3, float f4, int i2, Material material, long j2, float f5, float f6) {
        return createCone(f2, f3, f4, i2, 4, material, j2, f5, f6);
    }

    public Model createCylinder(float f2, float f3, float f4, int i2, Material material, long j2, float f5, float f6) {
        return createCylinder(f2, f3, f4, i2, 4, material, j2, f5, f6);
    }

    public Model createSphere(float f2, float f3, float f4, int i2, int i3, Material material, long j2, float f5, float f6, float f7, float f8) {
        return createSphere(f2, f3, f4, i2, i3, 4, material, j2, f5, f6, f7, f8);
    }

    public MeshPart part(String str, Mesh mesh, int i2, int i3, int i4, Material material) {
        MeshPart meshPart = new MeshPart();
        meshPart.id = str;
        meshPart.primitiveType = i2;
        meshPart.mesh = mesh;
        meshPart.offset = i3;
        meshPart.size = i4;
        part(meshPart, material);
        return meshPart;
    }

    public Model createArrow(com.badlogic.gdx.math.a aVar, com.badlogic.gdx.math.a aVar2, Material material, long j2) {
        return createArrow(aVar.f1729a, aVar.f1730b, aVar.f1731c, aVar2.f1729a, aVar2.f1730b, aVar2.f1731c, 0.1f, 0.1f, 5, 4, material, j2);
    }

    public Model createCone(float f2, float f3, float f4, int i2, int i3, Material material, long j2, float f5, float f6) {
        begin();
        part("cone", i3, j2, material).cone(f2, f3, f4, i2, f5, f6);
        return end();
    }

    public Model createCylinder(float f2, float f3, float f4, int i2, int i3, Material material, long j2, float f5, float f6) {
        begin();
        part("cylinder", i3, j2, material).cylinder(f2, f3, f4, i2, f5, f6);
        return end();
    }

    public Model createSphere(float f2, float f3, float f4, int i2, int i3, int i4, Material material, long j2, float f5, float f6, float f7, float f8) {
        begin();
        part("sphere", i4, j2, material).sphere(f2, f3, f4, i2, i3, f5, f6, f7, f8);
        return end();
    }

    private static void rebuildReferences(Model model, Node node) {
        a.b<NodePart> it = node.parts.iterator();
        while (it.hasNext()) {
            NodePart next = it.next();
            if (!model.materials.e(next.material, true)) {
                model.materials.a(next.material);
            }
            if (!model.meshParts.e(next.meshPart, true)) {
                model.meshParts.a(next.meshPart);
                if (!model.meshes.e(next.meshPart.mesh, true)) {
                    model.meshes.a(next.meshPart.mesh);
                }
                model.manageDisposable(next.meshPart.mesh);
            }
        }
        Iterator<Node> it2 = node.getChildren().iterator();
        while (it2.hasNext()) {
            rebuildReferences(model, it2.next());
        }
    }

    public Node node() {
        Node node = new Node();
        node(node);
        node.id = "node" + this.model.nodes.f1750b;
        return node;
    }

    public MeshPart part(String str, Mesh mesh, int i2, Material material) {
        return part(str, mesh, i2, 0, mesh.getNumIndices(), material);
    }

    public Model createXYZCoordinates(float f2, Material material, long j2) {
        return createXYZCoordinates(f2, 0.1f, 0.1f, 5, 4, material, j2);
    }

    public Node node(String str, Model model) {
        Node node = new Node();
        node.id = str;
        node.addChildren(model.nodes);
        node(node);
        Iterator<i> it = model.getManagedDisposables().iterator();
        while (it.hasNext()) {
            manage(it.next());
        }
        return node;
    }

    public MeshPartBuilder part(String str, int i2, VertexAttributes vertexAttributes, Material material) {
        MeshBuilder builder = getBuilder(vertexAttributes);
        part(builder.part(str, i2), material);
        return builder;
    }

    public MeshPartBuilder part(String str, int i2, long j2, Material material) {
        return part(str, i2, MeshBuilder.createAttributes(j2), material);
    }
}
