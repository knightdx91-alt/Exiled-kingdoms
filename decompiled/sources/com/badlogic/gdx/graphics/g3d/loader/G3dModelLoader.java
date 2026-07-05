package com.badlogic.gdx.graphics.g3d.loader;

import a0.n;
import a0.q;
import com.badlogic.gdx.assets.loaders.e;
import com.badlogic.gdx.assets.loaders.g;
import com.badlogic.gdx.files.a;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.FloatAttribute;
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
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.a;
import com.badlogic.gdx.utils.b;
import com.badlogic.gdx.utils.d;
import com.badlogic.gdx.utils.m;
import com.badlogic.gdx.utils.t;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class G3dModelLoader extends g<g.a> {
    public static final short VERSION_HI = 0;
    public static final short VERSION_LO = 1;
    protected final d reader;
    protected final n tempQ;

    public G3dModelLoader(d dVar) {
        this(dVar, null);
    }

    @Override // com.badlogic.gdx.assets.loaders.g
    public ModelData loadModelData(a aVar, g.a aVar2) {
        return parseModel(aVar);
    }

    /* JADX WARN: Type inference failed for: r10v18, types: [T, a0.n] */
    /* JADX WARN: Type inference failed for: r10v2, types: [T, com.badlogic.gdx.math.a] */
    /* JADX WARN: Type inference failed for: r7v21, types: [T, com.badlogic.gdx.math.a] */
    /* JADX WARN: Type inference failed for: r7v26, types: [T, com.badlogic.gdx.math.a] */
    /* JADX WARN: Type inference failed for: r8v8, types: [T, a0.n] */
    /* JADX WARN: Type inference failed for: r9v10, types: [T, com.badlogic.gdx.math.a] */
    protected void parseAnimations(ModelData modelData, t tVar) {
        ModelAnimation modelAnimation;
        ModelAnimation modelAnimation2;
        ModelData modelData2 = modelData;
        t tVarK = tVar.k("animations");
        if (tVarK == null) {
            return;
        }
        modelData2.animations.f(tVarK.f1962j);
        t tVar2 = tVarK.f1958f;
        while (tVar2 != null) {
            t tVarK2 = tVar2.k("bones");
            if (tVarK2 != null) {
                ModelAnimation modelAnimation3 = new ModelAnimation();
                modelData2.animations.a(modelAnimation3);
                modelAnimation3.nodeAnimations.f(tVarK2.f1962j);
                t tVarK3 = tVar2.k("id");
                if (tVarK3 == null) {
                    throw new IllegalArgumentException("Named value not found: ".concat("id"));
                }
                modelAnimation3.id = tVarK3.j();
                t tVar3 = tVarK2.f1958f;
                while (tVar3 != null) {
                    ModelNodeAnimation modelNodeAnimation = new ModelNodeAnimation();
                    modelAnimation3.nodeAnimations.a(modelNodeAnimation);
                    t tVarK4 = tVar3.k("boneId");
                    if (tVarK4 == null) {
                        throw new IllegalArgumentException("Named value not found: ".concat("boneId"));
                    }
                    modelNodeAnimation.nodeId = tVarK4.j();
                    t tVarK5 = tVar3.k("keyframes");
                    float f2 = 1000.0f;
                    float f3 = 0.0f;
                    int i2 = 1;
                    int i3 = 0;
                    int i4 = 3;
                    if (tVarK5 == null || !tVarK5.q()) {
                        modelAnimation = modelAnimation3;
                        t tVarK6 = tVar3.k("translation");
                        if (tVarK6 != null && tVarK6.q()) {
                            com.badlogic.gdx.utils.a<ModelNodeKeyframe<com.badlogic.gdx.math.a>> aVar = new com.badlogic.gdx.utils.a<>();
                            modelNodeAnimation.translation = aVar;
                            aVar.f(tVarK6.f1962j);
                            for (t tVar4 = tVarK6.f1958f; tVar4 != null; tVar4 = tVar4.f1960h) {
                                ModelNodeKeyframe<com.badlogic.gdx.math.a> modelNodeKeyframe = new ModelNodeKeyframe<>();
                                modelNodeAnimation.translation.a(modelNodeKeyframe);
                                modelNodeKeyframe.keytime = tVar4.m("keytime", 0.0f) / 1000.0f;
                                t tVarK7 = tVar4.k("value");
                                if (tVarK7 != null && tVarK7.f1962j >= 3) {
                                    modelNodeKeyframe.value = new com.badlogic.gdx.math.a(tVarK7.l(0), tVarK7.l(1), tVarK7.l(2));
                                }
                            }
                        }
                        t tVarK8 = tVar3.k("rotation");
                        if (tVarK8 != null && tVarK8.q()) {
                            com.badlogic.gdx.utils.a<ModelNodeKeyframe<n>> aVar2 = new com.badlogic.gdx.utils.a<>();
                            modelNodeAnimation.rotation = aVar2;
                            aVar2.f(tVarK8.f1962j);
                            for (t tVar5 = tVarK8.f1958f; tVar5 != null; tVar5 = tVar5.f1960h) {
                                ModelNodeKeyframe<n> modelNodeKeyframe2 = new ModelNodeKeyframe<>();
                                modelNodeAnimation.rotation.a(modelNodeKeyframe2);
                                modelNodeKeyframe2.keytime = tVar5.m("keytime", 0.0f) / 1000.0f;
                                t tVarK9 = tVar5.k("value");
                                if (tVarK9 != null && tVarK9.f1962j >= 4) {
                                    modelNodeKeyframe2.value = new n(tVarK9.l(0), tVarK9.l(1), tVarK9.l(2), tVarK9.l(3));
                                }
                            }
                        }
                        t tVarK10 = tVar3.k("scaling");
                        if (tVarK10 != null && tVarK10.q()) {
                            com.badlogic.gdx.utils.a<ModelNodeKeyframe<com.badlogic.gdx.math.a>> aVar3 = new com.badlogic.gdx.utils.a<>();
                            modelNodeAnimation.scaling = aVar3;
                            aVar3.f(tVarK10.f1962j);
                            for (t tVar6 = tVarK10.f1958f; tVar6 != null; tVar6 = tVar6.f1960h) {
                                ModelNodeKeyframe<com.badlogic.gdx.math.a> modelNodeKeyframe3 = new ModelNodeKeyframe<>();
                                modelNodeAnimation.scaling.a(modelNodeKeyframe3);
                                modelNodeKeyframe3.keytime = tVar6.m("keytime", 0.0f) / 1000.0f;
                                t tVarK11 = tVar6.k("value");
                                if (tVarK11 != null && tVarK11.f1962j >= 3) {
                                    modelNodeKeyframe3.value = new com.badlogic.gdx.math.a(tVarK11.l(0), tVarK11.l(1), tVarK11.l(2));
                                }
                            }
                        }
                    } else {
                        t tVar7 = tVarK5.f1958f;
                        while (tVar7 != null) {
                            float fM = tVar7.m("keytime", f3) / f2;
                            t tVarK12 = tVar7.k("translation");
                            if (tVarK12 != null && tVarK12.f1962j == i4) {
                                if (modelNodeAnimation.translation == null) {
                                    modelNodeAnimation.translation = new com.badlogic.gdx.utils.a<>();
                                }
                                ModelNodeKeyframe<com.badlogic.gdx.math.a> modelNodeKeyframe4 = new ModelNodeKeyframe<>();
                                modelNodeKeyframe4.keytime = fM;
                                modelNodeKeyframe4.value = new com.badlogic.gdx.math.a(tVarK12.l(i3), tVarK12.l(i2), tVarK12.l(2));
                                modelNodeAnimation.translation.a(modelNodeKeyframe4);
                            }
                            t tVarK13 = tVar7.k("rotation");
                            if (tVarK13 == null || tVarK13.f1962j != 4) {
                                modelAnimation2 = modelAnimation3;
                            } else {
                                if (modelNodeAnimation.rotation == null) {
                                    modelNodeAnimation.rotation = new com.badlogic.gdx.utils.a<>();
                                }
                                ModelNodeKeyframe<n> modelNodeKeyframe5 = new ModelNodeKeyframe<>();
                                modelNodeKeyframe5.keytime = fM;
                                modelAnimation2 = modelAnimation3;
                                modelNodeKeyframe5.value = new n(tVarK13.l(0), tVarK13.l(1), tVarK13.l(2), tVarK13.l(3));
                                modelNodeAnimation.rotation.a(modelNodeKeyframe5);
                            }
                            t tVarK14 = tVar7.k("scale");
                            if (tVarK14 != null && tVarK14.f1962j == 3) {
                                if (modelNodeAnimation.scaling == null) {
                                    modelNodeAnimation.scaling = new com.badlogic.gdx.utils.a<>();
                                }
                                ModelNodeKeyframe<com.badlogic.gdx.math.a> modelNodeKeyframe6 = new ModelNodeKeyframe<>();
                                modelNodeKeyframe6.keytime = fM;
                                modelNodeKeyframe6.value = new com.badlogic.gdx.math.a(tVarK14.l(0), tVarK14.l(1), tVarK14.l(2));
                                modelNodeAnimation.scaling.a(modelNodeKeyframe6);
                            }
                            tVar7 = tVar7.f1960h;
                            modelAnimation3 = modelAnimation2;
                            i4 = 3;
                            f2 = 1000.0f;
                            f3 = 0.0f;
                            i2 = 1;
                            i3 = 0;
                        }
                        modelAnimation = modelAnimation3;
                    }
                    tVar3 = tVar3.f1960h;
                    modelAnimation3 = modelAnimation;
                }
            }
            tVar2 = tVar2.f1960h;
            modelData2 = modelData;
        }
    }

    protected VertexAttribute[] parseAttributes(t tVar) {
        com.badlogic.gdx.utils.a aVar = new com.badlogic.gdx.utils.a();
        int i2 = 0;
        int i3 = 0;
        for (t tVar2 = tVar.f1958f; tVar2 != null; tVar2 = tVar2.f1960h) {
            String strJ = tVar2.j();
            if (strJ.equals("POSITION")) {
                aVar.a(VertexAttribute.Position());
            } else if (strJ.equals("NORMAL")) {
                aVar.a(VertexAttribute.Normal());
            } else if (strJ.equals("COLOR")) {
                aVar.a(VertexAttribute.ColorUnpacked());
            } else if (strJ.equals("COLORPACKED")) {
                aVar.a(VertexAttribute.ColorPacked());
            } else if (strJ.equals("TANGENT")) {
                aVar.a(VertexAttribute.Tangent());
            } else if (strJ.equals("BINORMAL")) {
                aVar.a(VertexAttribute.Binormal());
            } else if (strJ.startsWith("TEXCOORD")) {
                aVar.a(VertexAttribute.TexCoords(i2));
                i2++;
            } else {
                if (!strJ.startsWith("BLENDWEIGHT")) {
                    throw new m(a.a.l("Unknown vertex attribute '", strJ, "', should be one of position, normal, uv, tangent or binormal"));
                }
                aVar.a(VertexAttribute.BoneWeight(i3));
                i3++;
            }
        }
        return (VertexAttribute[]) aVar.u(VertexAttribute.class);
    }

    protected Color parseColor(t tVar) {
        if (tVar.f1962j >= 3) {
            return new Color(tVar.l(0), tVar.l(1), tVar.l(2), 1.0f);
        }
        throw new m("Expected Color values <> than three.");
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x00b1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected void parseMaterials(ModelData modelData, t tVar, String str) {
        String str2;
        t tVarK = tVar.k("materials");
        if (tVarK == null) {
            return;
        }
        modelData.materials.f(tVarK.f1962j);
        for (t tVar2 = tVarK.f1958f; tVar2 != null; tVar2 = tVar2.f1960h) {
            ModelMaterial modelMaterial = new ModelMaterial();
            String strO = tVar2.o("id", null);
            if (strO == null) {
                throw new m("Material needs an id.");
            }
            modelMaterial.id = strO;
            t tVarK2 = tVar2.k("diffuse");
            if (tVarK2 != null) {
                modelMaterial.diffuse = parseColor(tVarK2);
            }
            t tVarK3 = tVar2.k("ambient");
            if (tVarK3 != null) {
                modelMaterial.ambient = parseColor(tVarK3);
            }
            t tVarK4 = tVar2.k("emissive");
            if (tVarK4 != null) {
                modelMaterial.emissive = parseColor(tVarK4);
            }
            t tVarK5 = tVar2.k("specular");
            if (tVarK5 != null) {
                modelMaterial.specular = parseColor(tVarK5);
            }
            t tVarK6 = tVar2.k("reflection");
            if (tVarK6 != null) {
                modelMaterial.reflection = parseColor(tVarK6);
            }
            modelMaterial.shininess = tVar2.m(FloatAttribute.ShininessAlias, 0.0f);
            modelMaterial.opacity = tVar2.m("opacity", 1.0f);
            t tVarK7 = tVar2.k("textures");
            if (tVarK7 != null) {
                for (t tVar3 = tVarK7.f1958f; tVar3 != null; tVar3 = tVar3.f1960h) {
                    ModelTexture modelTexture = new ModelTexture();
                    String strO2 = tVar3.o("id", null);
                    if (strO2 == null) {
                        throw new m("Texture has no id.");
                    }
                    modelTexture.id = strO2;
                    String strO3 = tVar3.o("filename", null);
                    if (strO3 == null) {
                        throw new m("Texture needs filename.");
                    }
                    StringBuilder sbS = a.a.s(str);
                    if (str.length() != 0) {
                        str2 = str.endsWith("/") ? "" : "/";
                    }
                    modelTexture.fileName = a.a.m(str2, strO3, sbS);
                    modelTexture.uvTranslation = readVector2(tVar3.k("uvTranslation"), 0.0f, 0.0f);
                    modelTexture.uvScaling = readVector2(tVar3.k("uvScaling"), 1.0f, 1.0f);
                    String strO4 = tVar3.o("type", null);
                    if (strO4 == null) {
                        throw new m("Texture needs type.");
                    }
                    modelTexture.usage = parseTextureUsage(strO4);
                    if (modelMaterial.textures == null) {
                        modelMaterial.textures = new com.badlogic.gdx.utils.a<>();
                    }
                    modelMaterial.textures.a(modelTexture);
                }
            }
            modelData.materials.a(modelMaterial);
        }
    }

    protected void parseMeshes(ModelData modelData, t tVar) {
        t tVarK = tVar.k("meshes");
        if (tVarK != null) {
            modelData.meshes.f(tVarK.f1962j);
            for (t tVar2 = tVarK.f1958f; tVar2 != null; tVar2 = tVar2.f1960h) {
                ModelMesh modelMesh = new ModelMesh();
                modelMesh.id = tVar2.o("id", "");
                modelMesh.attributes = parseAttributes(tVar2.y("attributes"));
                modelMesh.vertices = tVar2.y("vertices").e();
                t tVarY = tVar2.y("parts");
                com.badlogic.gdx.utils.a aVar = new com.badlogic.gdx.utils.a();
                for (t tVar3 = tVarY.f1958f; tVar3 != null; tVar3 = tVar3.f1960h) {
                    ModelMeshPart modelMeshPart = new ModelMeshPart();
                    String strO = tVar3.o("id", null);
                    if (strO == null) {
                        throw new m("Not id given for mesh part");
                    }
                    a.b it = aVar.iterator();
                    while (it.hasNext()) {
                        if (((ModelMeshPart) it.next()).id.equals(strO)) {
                            throw new m(a.a.l("Mesh part with id '", strO, "' already in defined"));
                        }
                    }
                    modelMeshPart.id = strO;
                    String strO2 = tVar3.o("type", null);
                    if (strO2 == null) {
                        throw new m(a.a.l("No primitive type given for mesh part '", strO, "'"));
                    }
                    modelMeshPart.primitiveType = parseType(strO2);
                    modelMeshPart.indices = tVar3.y("indices").i();
                    aVar.a(modelMeshPart);
                }
                modelMesh.parts = (ModelMeshPart[]) aVar.u(ModelMeshPart.class);
                modelData.meshes.a(modelMesh);
            }
        }
    }

    public ModelData parseModel(com.badlogic.gdx.files.a aVar) {
        t tVarA = this.reader.a(aVar);
        ModelData modelData = new ModelData();
        t tVarY = tVarA.y("version");
        modelData.version[0] = tVarY.n(0);
        modelData.version[1] = tVarY.n(1);
        short[] sArr = modelData.version;
        if (sArr[0] != 0 || sArr[1] != 1) {
            throw new m("Model version not supported");
        }
        modelData.id = tVarA.o("id", "");
        parseMeshes(modelData, tVarA);
        parseMaterials(modelData, tVarA, aVar.parent().path());
        parseNodes(modelData, tVarA);
        parseAnimations(modelData, tVarA);
        return modelData;
    }

    protected com.badlogic.gdx.utils.a<ModelNode> parseNodes(ModelData modelData, t tVar) {
        t tVarK = tVar.k("nodes");
        if (tVarK != null) {
            modelData.nodes.f(tVarK.f1962j);
            for (t tVar2 = tVarK.f1958f; tVar2 != null; tVar2 = tVar2.f1960h) {
                modelData.nodes.a(parseNodesRecursively(tVar2));
            }
        }
        return modelData.nodes;
    }

    protected ModelNode parseNodesRecursively(t tVar) {
        String str;
        String str2;
        char c2;
        int i2;
        char c3;
        String str3;
        String str4;
        int i3;
        G3dModelLoader g3dModelLoader = this;
        ModelNode modelNode = new ModelNode();
        String str5 = null;
        String strO = tVar.o("id", null);
        if (strO == null) {
            throw new m("Node id missing.");
        }
        modelNode.id = strO;
        String str6 = "translation";
        t tVarK = tVar.k("translation");
        char c4 = 3;
        if (tVarK != null && tVarK.f1962j != 3) {
            throw new m("Node translation incomplete");
        }
        int i4 = 0;
        boolean z2 = true;
        modelNode.translation = tVarK == null ? null : new com.badlogic.gdx.math.a(tVarK.l(0), tVarK.l(1), tVarK.l(2));
        String str7 = "rotation";
        t tVarK2 = tVar.k("rotation");
        if (tVarK2 != null && tVarK2.f1962j != 4) {
            throw new m("Node rotation incomplete");
        }
        modelNode.rotation = tVarK2 == null ? null : new n(tVarK2.l(0), tVarK2.l(1), tVarK2.l(2), tVarK2.l(3));
        t tVarK3 = tVar.k("scale");
        if (tVarK3 != null && tVarK3.f1962j != 3) {
            throw new m("Node scale incomplete");
        }
        modelNode.scale = tVarK3 == null ? null : new com.badlogic.gdx.math.a(tVarK3.l(0), tVarK3.l(1), tVarK3.l(2));
        String strO2 = tVar.o("mesh", null);
        if (strO2 != null) {
            modelNode.meshId = strO2;
        }
        t tVarK4 = tVar.k("parts");
        if (tVarK4 != null) {
            modelNode.parts = new ModelNodePart[tVarK4.f1962j];
            t tVar2 = tVarK4.f1958f;
            int i5 = 0;
            while (tVar2 != null) {
                ModelNodePart modelNodePart = new ModelNodePart();
                String strO3 = tVar2.o("meshpartid", str5);
                String strO4 = tVar2.o("materialid", str5);
                if (strO3 == null || strO4 == null) {
                    throw new m(a.a.l("Node ", strO, " part is missing meshPartId or materialId"));
                }
                modelNodePart.materialId = strO4;
                modelNodePart.meshPartId = strO3;
                t tVarK5 = tVar2.k("bones");
                if (tVarK5 != null) {
                    modelNodePart.bones = new b<>(z2, tVarK5.f1962j, String.class);
                    t tVar3 = tVarK5.f1958f;
                    while (tVar3 != null) {
                        String strO5 = tVar3.o("node", str5);
                        if (strO5 == null) {
                            throw new m("Bone node ID missing");
                        }
                        Matrix4 matrix4 = new Matrix4();
                        t tVarK6 = tVar3.k(str6);
                        if (tVarK6 == null || tVarK6.f1962j < 3) {
                            str3 = str6;
                        } else {
                            str3 = str6;
                            matrix4.v(tVarK6.l(0), tVarK6.l(1), tVarK6.l(2));
                        }
                        t tVarK7 = tVar3.k(str7);
                        if (tVarK7 == null || tVarK7.f1962j < 4) {
                            str4 = str7;
                            i3 = 3;
                        } else {
                            n nVar = g3dModelLoader.tempQ;
                            str4 = str7;
                            i3 = 3;
                            nVar.e(tVarK7.l(0), tVarK7.l(1), tVarK7.l(2), tVarK7.l(3));
                            matrix4.j(nVar);
                        }
                        t tVarK8 = tVar3.k("scale");
                        if (tVarK8 != null && tVarK8.f1962j >= i3) {
                            matrix4.l(tVarK8.l(0), tVarK8.l(1), tVarK8.l(2));
                        }
                        modelNodePart.bones.c(strO5, matrix4);
                        tVar3 = tVar3.f1960h;
                        g3dModelLoader = this;
                        str6 = str3;
                        str7 = str4;
                        str5 = null;
                    }
                    str = str6;
                    str2 = str7;
                    i2 = 0;
                    c3 = 2;
                    c2 = 3;
                } else {
                    str = str6;
                    str2 = str7;
                    c2 = c4;
                    i2 = i4;
                    c3 = 2;
                }
                modelNode.parts[i5] = modelNodePart;
                tVar2 = tVar2.f1960h;
                i5++;
                z2 = true;
                i4 = i2;
                c4 = c2;
                str6 = str;
                str7 = str2;
                str5 = null;
                g3dModelLoader = this;
            }
        }
        int i6 = i4;
        t tVarK9 = tVar.k("children");
        if (tVarK9 != null) {
            modelNode.children = new ModelNode[tVarK9.f1962j];
            t tVar4 = tVarK9.f1958f;
            int i7 = i6;
            while (tVar4 != null) {
                modelNode.children[i7] = parseNodesRecursively(tVar4);
                tVar4 = tVar4.f1960h;
                i7++;
            }
        }
        return modelNode;
    }

    protected int parseTextureUsage(String str) {
        if (str.equalsIgnoreCase("AMBIENT")) {
            return 4;
        }
        if (str.equalsIgnoreCase("BUMP")) {
            return 8;
        }
        if (str.equalsIgnoreCase("DIFFUSE")) {
            return 2;
        }
        if (str.equalsIgnoreCase("EMISSIVE")) {
            return 3;
        }
        if (str.equalsIgnoreCase("NONE")) {
            return 1;
        }
        if (str.equalsIgnoreCase("NORMAL")) {
            return 7;
        }
        if (str.equalsIgnoreCase("REFLECTION")) {
            return 10;
        }
        if (str.equalsIgnoreCase("SHININESS")) {
            return 6;
        }
        if (str.equalsIgnoreCase("SPECULAR")) {
            return 5;
        }
        return str.equalsIgnoreCase("TRANSPARENCY") ? 9 : 0;
    }

    protected int parseType(String str) {
        if (str.equals("TRIANGLES")) {
            return 4;
        }
        if (str.equals("LINES")) {
            return 1;
        }
        if (str.equals("POINTS")) {
            return 0;
        }
        if (str.equals("TRIANGLE_STRIP")) {
            return 5;
        }
        if (str.equals("LINE_STRIP")) {
            return 3;
        }
        throw new m(a.a.l("Unknown primitive type '", str, "', should be one of triangle, trianglestrip, line, linestrip, lineloop or point"));
    }

    protected q readVector2(t tVar, float f2, float f3) {
        if (tVar == null) {
            return new q(f2, f3);
        }
        if (tVar.f1962j == 2) {
            return new q(tVar.l(0), tVar.l(1));
        }
        throw new m("Expected Vector2 values <> than two.");
    }

    public G3dModelLoader(d dVar, e eVar) {
        super(eVar);
        this.tempQ = new n();
        this.reader = dVar;
    }
}
