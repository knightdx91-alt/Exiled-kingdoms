package com.badlogic.gdx.graphics.g3d;

import a0.n;
import a0.q;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.attributes.BlendingAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.FloatAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.model.Animation;
import com.badlogic.gdx.graphics.g3d.model.MeshPart;
import com.badlogic.gdx.graphics.g3d.model.Node;
import com.badlogic.gdx.graphics.g3d.model.NodeAnimation;
import com.badlogic.gdx.graphics.g3d.model.NodeKeyframe;
import com.badlogic.gdx.graphics.g3d.model.NodePart;
import com.badlogic.gdx.graphics.g3d.model.data.ModelAnimation;
import com.badlogic.gdx.graphics.g3d.model.data.ModelData;
import com.badlogic.gdx.graphics.g3d.model.data.ModelMaterial;
import com.badlogic.gdx.graphics.g3d.model.data.ModelMesh;
import com.badlogic.gdx.graphics.g3d.model.data.ModelMeshPart;
import com.badlogic.gdx.graphics.g3d.model.data.ModelNode;
import com.badlogic.gdx.graphics.g3d.model.data.ModelNodeAnimation;
import com.badlogic.gdx.graphics.g3d.model.data.ModelNodeKeyframe;
import com.badlogic.gdx.graphics.g3d.model.data.ModelNodePart;
import com.badlogic.gdx.graphics.g3d.model.data.ModelTexture;
import com.badlogic.gdx.graphics.g3d.utils.TextureDescriptor;
import com.badlogic.gdx.graphics.g3d.utils.TextureProvider;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.BufferUtils;
import com.badlogic.gdx.utils.a;
import com.badlogic.gdx.utils.b;
import com.badlogic.gdx.utils.i;
import com.badlogic.gdx.utils.m;
import com.badlogic.gdx.utils.y;
import java.util.Arrays;
import java.util.Iterator;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class Model implements i {
    public final a<Animation> animations;
    protected final a<i> disposables;
    public final a<Material> materials;
    public final a<MeshPart> meshParts;
    public final a<Mesh> meshes;
    private y<NodePart, b<String, Matrix4>> nodePartBones;
    public final a<Node> nodes;

    public Model() {
        this.materials = new a<>();
        this.nodes = new a<>();
        this.animations = new a<>();
        this.meshes = new a<>();
        this.meshParts = new a<>();
        this.disposables = new a<>();
        this.nodePartBones = new y<>();
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

    protected Material convertMaterial(ModelMaterial modelMaterial, TextureProvider textureProvider) {
        Texture textureLoad;
        Material material = new Material();
        material.id = modelMaterial.id;
        if (modelMaterial.ambient != null) {
            material.set(new ColorAttribute(ColorAttribute.Ambient, modelMaterial.ambient));
        }
        if (modelMaterial.diffuse != null) {
            material.set(new ColorAttribute(ColorAttribute.Diffuse, modelMaterial.diffuse));
        }
        if (modelMaterial.specular != null) {
            material.set(new ColorAttribute(ColorAttribute.Specular, modelMaterial.specular));
        }
        if (modelMaterial.emissive != null) {
            material.set(new ColorAttribute(ColorAttribute.Emissive, modelMaterial.emissive));
        }
        if (modelMaterial.reflection != null) {
            material.set(new ColorAttribute(ColorAttribute.Reflection, modelMaterial.reflection));
        }
        if (modelMaterial.shininess > 0.0f) {
            material.set(new FloatAttribute(FloatAttribute.Shininess, modelMaterial.shininess));
        }
        if (modelMaterial.opacity != 1.0f) {
            material.set(new BlendingAttribute(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA, modelMaterial.opacity));
        }
        y yVar = new y();
        a<ModelTexture> aVar = modelMaterial.textures;
        if (aVar != null) {
            a.b<ModelTexture> it = aVar.iterator();
            while (it.hasNext()) {
                ModelTexture next = it.next();
                if (yVar.a(next.fileName)) {
                    textureLoad = (Texture) yVar.d(next.fileName);
                } else {
                    textureLoad = textureProvider.load(next.fileName);
                    yVar.j(next.fileName, textureLoad);
                    this.disposables.a(textureLoad);
                }
                TextureDescriptor textureDescriptor = new TextureDescriptor(textureLoad);
                textureDescriptor.minFilter = textureLoad.getMinFilter();
                textureDescriptor.magFilter = textureLoad.getMagFilter();
                textureDescriptor.uWrap = textureLoad.getUWrap();
                textureDescriptor.vWrap = textureLoad.getVWrap();
                q qVar = next.uvTranslation;
                float f2 = qVar == null ? 0.0f : qVar.f91a;
                float f3 = qVar == null ? 0.0f : qVar.f92b;
                q qVar2 = next.uvScaling;
                float f4 = qVar2 == null ? 1.0f : qVar2.f91a;
                float f5 = qVar2 == null ? 1.0f : qVar2.f92b;
                int i2 = next.usage;
                if (i2 == 2) {
                    material.set(new TextureAttribute(TextureAttribute.Diffuse, textureDescriptor, f2, f3, f4, f5));
                } else if (i2 == 3) {
                    material.set(new TextureAttribute(TextureAttribute.Emissive, textureDescriptor, f2, f3, f4, f5));
                } else if (i2 == 4) {
                    material.set(new TextureAttribute(TextureAttribute.Ambient, textureDescriptor, f2, f3, f4, f5));
                } else if (i2 == 5) {
                    material.set(new TextureAttribute(TextureAttribute.Specular, textureDescriptor, f2, f3, f4, f5));
                } else if (i2 == 7) {
                    material.set(new TextureAttribute(TextureAttribute.Normal, textureDescriptor, f2, f3, f4, f5));
                } else if (i2 == 8) {
                    material.set(new TextureAttribute(TextureAttribute.Bump, textureDescriptor, f2, f3, f4, f5));
                } else if (i2 == 10) {
                    material.set(new TextureAttribute(TextureAttribute.Reflection, textureDescriptor, f2, f3, f4, f5));
                }
            }
        }
        return material;
    }

    protected void convertMesh(ModelMesh modelMesh) {
        int length = 0;
        for (ModelMeshPart modelMeshPart : modelMesh.parts) {
            length += modelMeshPart.indices.length;
        }
        boolean z2 = length > 0;
        VertexAttributes vertexAttributes = new VertexAttributes(modelMesh.attributes);
        int length2 = modelMesh.vertices.length / (vertexAttributes.vertexSize / 4);
        Mesh mesh = new Mesh(true, length2, length, vertexAttributes);
        this.meshes.a(mesh);
        this.disposables.a(mesh);
        BufferUtils.d(modelMesh.vertices, mesh.getVerticesBuffer(), modelMesh.vertices.length, 0);
        mesh.getIndicesBuffer().clear();
        int i2 = 0;
        for (ModelMeshPart modelMeshPart2 : modelMesh.parts) {
            MeshPart meshPart = new MeshPart();
            meshPart.id = modelMeshPart2.id;
            meshPart.primitiveType = modelMeshPart2.primitiveType;
            meshPart.offset = i2;
            meshPart.size = z2 ? modelMeshPart2.indices.length : length2;
            meshPart.mesh = mesh;
            if (z2) {
                mesh.getIndicesBuffer().put(modelMeshPart2.indices);
            }
            i2 += meshPart.size;
            this.meshParts.a(meshPart);
        }
        mesh.getIndicesBuffer().position(0);
        a.b<MeshPart> it = this.meshParts.iterator();
        while (it.hasNext()) {
            it.next().update();
        }
    }

    @Override // com.badlogic.gdx.utils.i
    public void dispose() {
        a.b<i> it = this.disposables.iterator();
        while (it.hasNext()) {
            it.next().dispose();
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
        return getAnimation(str, true);
    }

    public Iterable<i> getManagedDisposables() {
        return this.disposables;
    }

    public Material getMaterial(String str) {
        return getMaterial(str, true);
    }

    public Node getNode(String str) {
        return getNode(str, true);
    }

    protected void load(ModelData modelData, TextureProvider textureProvider) {
        loadMeshes(modelData.meshes);
        loadMaterials(modelData.materials, textureProvider);
        loadNodes(modelData.nodes);
        loadAnimations(modelData.animations);
        calculateTransforms();
    }

    protected void loadAnimations(Iterable<ModelAnimation> iterable) {
        a<NodeKeyframe<n>> aVar;
        a<NodeKeyframe<com.badlogic.gdx.math.a>> aVar2;
        for (ModelAnimation modelAnimation : iterable) {
            Animation animation = new Animation();
            animation.id = modelAnimation.id;
            a.b<ModelNodeAnimation> it = modelAnimation.nodeAnimations.iterator();
            while (it.hasNext()) {
                ModelNodeAnimation next = it.next();
                Node node = getNode(next.nodeId);
                if (node != null) {
                    NodeAnimation nodeAnimation = new NodeAnimation();
                    nodeAnimation.node = node;
                    if (next.translation != null) {
                        a<NodeKeyframe<com.badlogic.gdx.math.a>> aVar3 = new a<>();
                        nodeAnimation.translation = aVar3;
                        aVar3.f(next.translation.f1750b);
                        a.b<ModelNodeKeyframe<com.badlogic.gdx.math.a>> it2 = next.translation.iterator();
                        while (it2.hasNext()) {
                            ModelNodeKeyframe<com.badlogic.gdx.math.a> next2 = it2.next();
                            float f2 = next2.keytime;
                            if (f2 > animation.duration) {
                                animation.duration = f2;
                            }
                            a<NodeKeyframe<com.badlogic.gdx.math.a>> aVar4 = nodeAnimation.translation;
                            com.badlogic.gdx.math.a aVar5 = next2.value;
                            aVar4.a(new NodeKeyframe<>(f2, new com.badlogic.gdx.math.a(aVar5 == null ? node.translation : aVar5)));
                        }
                    }
                    if (next.rotation != null) {
                        a<NodeKeyframe<n>> aVar6 = new a<>();
                        nodeAnimation.rotation = aVar6;
                        aVar6.f(next.rotation.f1750b);
                        a.b<ModelNodeKeyframe<n>> it3 = next.rotation.iterator();
                        while (it3.hasNext()) {
                            ModelNodeKeyframe<n> next3 = it3.next();
                            float f3 = next3.keytime;
                            if (f3 > animation.duration) {
                                animation.duration = f3;
                            }
                            a<NodeKeyframe<n>> aVar7 = nodeAnimation.rotation;
                            n nVar = next3.value;
                            n nVar2 = nVar == null ? node.rotation : nVar;
                            n nVar3 = new n();
                            nVar3.f(nVar2);
                            aVar7.a(new NodeKeyframe<>(f3, nVar3));
                        }
                    }
                    if (next.scaling != null) {
                        a<NodeKeyframe<com.badlogic.gdx.math.a>> aVar8 = new a<>();
                        nodeAnimation.scaling = aVar8;
                        aVar8.f(next.scaling.f1750b);
                        a.b<ModelNodeKeyframe<com.badlogic.gdx.math.a>> it4 = next.scaling.iterator();
                        while (it4.hasNext()) {
                            ModelNodeKeyframe<com.badlogic.gdx.math.a> next4 = it4.next();
                            float f4 = next4.keytime;
                            if (f4 > animation.duration) {
                                animation.duration = f4;
                            }
                            a<NodeKeyframe<com.badlogic.gdx.math.a>> aVar9 = nodeAnimation.scaling;
                            com.badlogic.gdx.math.a aVar10 = next4.value;
                            aVar9.a(new NodeKeyframe<>(f4, new com.badlogic.gdx.math.a(aVar10 == null ? node.scale : aVar10)));
                        }
                    }
                    a<NodeKeyframe<com.badlogic.gdx.math.a>> aVar11 = nodeAnimation.translation;
                    if ((aVar11 != null && aVar11.f1750b > 0) || (((aVar = nodeAnimation.rotation) != null && aVar.f1750b > 0) || ((aVar2 = nodeAnimation.scaling) != null && aVar2.f1750b > 0))) {
                        animation.nodeAnimations.a(nodeAnimation);
                    }
                }
            }
            if (animation.nodeAnimations.f1750b > 0) {
                this.animations.a(animation);
            }
        }
    }

    protected void loadMaterials(Iterable<ModelMaterial> iterable, TextureProvider textureProvider) {
        Iterator<ModelMaterial> it = iterable.iterator();
        while (it.hasNext()) {
            this.materials.a(convertMaterial(it.next(), textureProvider));
        }
    }

    protected void loadMeshes(Iterable<ModelMesh> iterable) {
        Iterator<ModelMesh> it = iterable.iterator();
        while (it.hasNext()) {
            convertMesh(it.next());
        }
    }

    protected Node loadNode(ModelNode modelNode) {
        MeshPart next;
        Node node = new Node();
        node.id = modelNode.id;
        com.badlogic.gdx.math.a aVar = modelNode.translation;
        if (aVar != null) {
            node.translation.u(aVar);
        }
        n nVar = modelNode.rotation;
        if (nVar != null) {
            node.rotation.f(nVar);
        }
        com.badlogic.gdx.math.a aVar2 = modelNode.scale;
        if (aVar2 != null) {
            node.scale.u(aVar2);
        }
        ModelNodePart[] modelNodePartArr = modelNode.parts;
        if (modelNodePartArr != null) {
            for (ModelNodePart modelNodePart : modelNodePartArr) {
                Material material = null;
                if (modelNodePart.meshPartId != null) {
                    a.b<MeshPart> it = this.meshParts.iterator();
                    while (it.hasNext()) {
                        next = it.next();
                        if (modelNodePart.meshPartId.equals(next.id)) {
                            break;
                        }
                    }
                    next = null;
                } else {
                    next = null;
                }
                if (modelNodePart.materialId != null) {
                    a.b<Material> it2 = this.materials.iterator();
                    while (true) {
                        if (!it2.hasNext()) {
                            break;
                        }
                        Material next2 = it2.next();
                        if (modelNodePart.materialId.equals(next2.id)) {
                            material = next2;
                            break;
                        }
                    }
                }
                if (next == null || material == null) {
                    throw new m("Invalid node: " + node.id);
                }
                NodePart nodePart = new NodePart();
                nodePart.meshPart = next;
                nodePart.material = material;
                node.parts.a(nodePart);
                b<String, Matrix4> bVar = modelNodePart.bones;
                if (bVar != null) {
                    this.nodePartBones.j(nodePart, bVar);
                }
            }
        }
        ModelNode[] modelNodeArr = modelNode.children;
        if (modelNodeArr != null) {
            for (ModelNode modelNode2 : modelNodeArr) {
                node.addChild(loadNode(modelNode2));
            }
        }
        return node;
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected void loadNodes(Iterable<ModelNode> iterable) {
        this.nodePartBones.clear();
        Iterator<ModelNode> it = iterable.iterator();
        while (it.hasNext()) {
            this.nodes.a(loadNode(it.next()));
        }
        y.a<NodePart, b<String, Matrix4>> aVarB = this.nodePartBones.b();
        aVarB.getClass();
        while (aVarB.hasNext()) {
            y.b next = aVarB.next();
            NodePart nodePart = (NodePart) next.f2057a;
            if (nodePart.invBoneBindTransforms == null) {
                nodePart.invBoneBindTransforms = new b<>(false, 16, Node.class);
            }
            b<Node, Matrix4> bVar = ((NodePart) next.f2057a).invBoneBindTransforms;
            Arrays.fill(bVar.f1765a, 0, bVar.f1767c, (Object) null);
            Arrays.fill(bVar.f1766b, 0, bVar.f1767c, (Object) null);
            bVar.f1767c = 0;
            b.a aVarA = ((b) next.f2058b).a();
            while (aVarA.hasNext()) {
                y.b bVar2 = (y.b) aVarA.next();
                b<Node, Matrix4> bVar3 = ((NodePart) next.f2057a).invBoneBindTransforms;
                Node node = getNode((String) bVar2.f2057a);
                Matrix4 matrix4 = new Matrix4((Matrix4) bVar2.f2058b);
                matrix4.e();
                bVar3.c(node, matrix4);
            }
        }
    }

    public void manageDisposable(i iVar) {
        if (this.disposables.e(iVar, true)) {
            return;
        }
        this.disposables.a(iVar);
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

    public Node getNode(String str, boolean z2, boolean z3) {
        return Node.getNode(this.nodes, str, z2, z3);
    }

    public Model(ModelData modelData) {
        this(modelData, new TextureProvider.FileTextureProvider());
    }

    public Model(ModelData modelData, TextureProvider textureProvider) {
        this.materials = new a<>();
        this.nodes = new a<>();
        this.animations = new a<>();
        this.meshes = new a<>();
        this.meshParts = new a<>();
        this.disposables = new a<>();
        this.nodePartBones = new y<>();
        load(modelData, textureProvider);
    }
}
