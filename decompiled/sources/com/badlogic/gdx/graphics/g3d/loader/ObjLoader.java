package com.badlogic.gdx.graphics.g3d.loader;

import a0.n;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.e;
import com.badlogic.gdx.assets.loaders.g;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.model.data.ModelData;
import com.badlogic.gdx.graphics.g3d.model.data.ModelMesh;
import com.badlogic.gdx.graphics.g3d.model.data.ModelMeshPart;
import com.badlogic.gdx.graphics.g3d.model.data.ModelNode;
import com.badlogic.gdx.graphics.g3d.model.data.ModelNodePart;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.utils.a;
import com.badlogic.gdx.utils.j;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class ObjLoader extends g<ObjLoaderParameters> {
    public static boolean logWarning;
    final a<Group> groups;
    final j norms;
    final j uvs;
    final j verts;

    private class Group {
        boolean hasNorms;
        boolean hasUVs;
        final String name;
        a<Integer> faces = new a<>(true, 200);
        int numFaces = 0;
        Material mat = new Material("");
        String materialName = "default";

        Group(String str) {
            this.name = str;
        }
    }

    public static class ObjLoaderParameters extends g.a {
        public boolean flipV;

        public ObjLoaderParameters() {
        }

        public ObjLoaderParameters(boolean z2) {
            this.flipV = z2;
        }
    }

    public ObjLoader() {
        this(null);
    }

    private int getIndex(String str, int i2) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        int i3 = Integer.parseInt(str);
        return i3 < 0 ? i2 + i3 : i3 - 1;
    }

    private Group setActiveGroup(String str) {
        a.b<Group> it = this.groups.iterator();
        while (it.hasNext()) {
            Group next = it.next();
            if (next.name.equals(str)) {
                return next;
            }
        }
        Group group = new Group(str);
        this.groups.a(group);
        return group;
    }

    public Model loadModel(com.badlogic.gdx.files.a aVar, boolean z2) {
        return loadModel(aVar, new ObjLoaderParameters(z2));
    }

    public ObjLoader(e eVar) {
        super(eVar);
        this.verts = new j(300);
        this.norms = new j(300);
        this.uvs = new j(200);
        this.groups = new a<>(true, 10);
    }

    @Override // com.badlogic.gdx.assets.loaders.g
    public ModelData loadModelData(com.badlogic.gdx.files.a aVar, ObjLoaderParameters objLoaderParameters) {
        return loadModelData(aVar, objLoaderParameters != null && objLoaderParameters.flipV);
    }

    protected ModelData loadModelData(com.badlogic.gdx.files.a aVar, boolean z2) {
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        String strA;
        String strA2;
        String strA3;
        char cCharAt;
        if (logWarning) {
            Gdx.app.error("ObjLoader", "Wavefront (OBJ) is not fully supported, consult the documentation for more information");
        }
        MtlLoader mtlLoader = new MtlLoader();
        Group group = new Group("default");
        this.groups.a(group);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(aVar.read()), 4096);
        while (true) {
            try {
                String line = bufferedReader.readLine();
                i2 = 3;
                i3 = 2;
                if (line == null) {
                    break;
                }
                String[] strArrSplit = line.split("\\s+");
                if (strArrSplit.length < 1) {
                    break;
                }
                if (strArrSplit[0].length() != 0 && (cCharAt = strArrSplit[0].toLowerCase().charAt(0)) != '#') {
                    if (cCharAt == 'v') {
                        if (strArrSplit[0].length() == 1) {
                            this.verts.a(Float.parseFloat(strArrSplit[1]));
                            this.verts.a(Float.parseFloat(strArrSplit[2]));
                            this.verts.a(Float.parseFloat(strArrSplit[3]));
                        } else if (strArrSplit[0].charAt(1) == 'n') {
                            this.norms.a(Float.parseFloat(strArrSplit[1]));
                            this.norms.a(Float.parseFloat(strArrSplit[2]));
                            this.norms.a(Float.parseFloat(strArrSplit[3]));
                        } else if (strArrSplit[0].charAt(1) == 't') {
                            this.uvs.a(Float.parseFloat(strArrSplit[1]));
                            this.uvs.a(z2 ? 1.0f - Float.parseFloat(strArrSplit[2]) : Float.parseFloat(strArrSplit[2]));
                        }
                    } else if (cCharAt == 'f') {
                        a<Integer> aVar2 = group.faces;
                        for (int i7 = 1; i7 < strArrSplit.length - 2; i7++) {
                            String[] strArrSplit2 = strArrSplit[1].split("/");
                            aVar2.a(Integer.valueOf(getIndex(strArrSplit2[0], this.verts.f1824b)));
                            if (strArrSplit2.length > 2) {
                                if (i7 == 1) {
                                    group.hasNorms = true;
                                }
                                aVar2.a(Integer.valueOf(getIndex(strArrSplit2[2], this.norms.f1824b)));
                            }
                            if (strArrSplit2.length > 1 && strArrSplit2[1].length() > 0) {
                                if (i7 == 1) {
                                    group.hasUVs = true;
                                }
                                aVar2.a(Integer.valueOf(getIndex(strArrSplit2[1], this.uvs.f1824b)));
                            }
                            String[] strArrSplit3 = strArrSplit[i7 + 1].split("/");
                            aVar2.a(Integer.valueOf(getIndex(strArrSplit3[0], this.verts.f1824b)));
                            if (strArrSplit3.length > 2) {
                                aVar2.a(Integer.valueOf(getIndex(strArrSplit3[2], this.norms.f1824b)));
                            }
                            if (strArrSplit3.length > 1 && strArrSplit3[1].length() > 0) {
                                aVar2.a(Integer.valueOf(getIndex(strArrSplit3[1], this.uvs.f1824b)));
                            }
                            String[] strArrSplit4 = strArrSplit[i7 + 2].split("/");
                            aVar2.a(Integer.valueOf(getIndex(strArrSplit4[0], this.verts.f1824b)));
                            if (strArrSplit4.length > 2) {
                                aVar2.a(Integer.valueOf(getIndex(strArrSplit4[2], this.norms.f1824b)));
                            }
                            if (strArrSplit4.length > 1 && strArrSplit4[1].length() > 0) {
                                aVar2.a(Integer.valueOf(getIndex(strArrSplit4[1], this.uvs.f1824b)));
                            }
                            group.numFaces++;
                        }
                    } else if (cCharAt != 'o' && cCharAt != 'g') {
                        if (strArrSplit[0].equals("mtllib")) {
                            mtlLoader.load(aVar.parent().child(strArrSplit[1]));
                        } else if (strArrSplit[0].equals("usemtl")) {
                            if (strArrSplit.length == 1) {
                                group.materialName = "default";
                            } else {
                                group.materialName = strArrSplit[1].replace('.', '_');
                            }
                        }
                    } else if (strArrSplit.length > 1) {
                        group = setActiveGroup(strArrSplit[1]);
                    } else {
                        group = setActiveGroup("default");
                    }
                }
            } catch (IOException unused) {
                return null;
            }
        }
        bufferedReader.close();
        int i8 = 0;
        while (true) {
            a<Group> aVar3 = this.groups;
            i4 = aVar3.f1750b;
            if (i8 >= i4) {
                break;
            }
            if (aVar3.get(i8).numFaces < 1) {
                this.groups.o(i8);
                i8--;
            }
            i8++;
        }
        if (i4 < 1) {
            return null;
        }
        ModelData modelData = new ModelData();
        int i9 = 0;
        int i10 = 0;
        while (i9 < i4) {
            Group group2 = this.groups.get(i9);
            a<Integer> aVar4 = group2.faces;
            int i11 = aVar4.f1750b;
            int i12 = group2.numFaces;
            boolean z3 = group2.hasNorms;
            boolean z4 = group2.hasUVs;
            int i13 = i12 * i2;
            float[] fArr = new float[((z3 ? i2 : 0) + 3 + (z4 ? i3 : 0)) * i13];
            int i14 = 0;
            int i15 = 0;
            while (i14 < i11) {
                int i16 = i14 + 1;
                int i17 = i4;
                int iIntValue = aVar4.get(i14).intValue() * 3;
                int i18 = i11;
                int i19 = i9;
                fArr[i15] = this.verts.e(iIntValue);
                MtlLoader mtlLoader2 = mtlLoader;
                fArr[i15 + 1] = this.verts.e(iIntValue + 1);
                int i20 = i15 + 3;
                fArr[i15 + 2] = this.verts.e(iIntValue + 2);
                if (z3) {
                    int iIntValue2 = aVar4.get(i16).intValue() * 3;
                    fArr[i20] = this.norms.e(iIntValue2);
                    fArr[i15 + 4] = this.norms.e(iIntValue2 + 1);
                    fArr[i15 + 5] = this.norms.e(iIntValue2 + 2);
                    i20 = i15 + 6;
                    i16 = i14 + 2;
                }
                if (z4) {
                    int iIntValue3 = aVar4.get(i16).intValue() * 2;
                    fArr[i20] = this.uvs.e(iIntValue3);
                    fArr[i20 + 1] = this.uvs.e(iIntValue3 + 1);
                    i15 = i20 + 2;
                    i14 = i16 + 1;
                } else {
                    i15 = i20;
                    i14 = i16;
                }
                i4 = i17;
                i11 = i18;
                i9 = i19;
                mtlLoader = mtlLoader2;
            }
            MtlLoader mtlLoader3 = mtlLoader;
            int i21 = i9;
            int i22 = i4;
            if (i13 >= 32767) {
                i13 = 0;
            }
            short[] sArr = new short[i13];
            if (i13 > 0) {
                for (int i23 = 0; i23 < i13; i23++) {
                    sArr[i23] = (short) i23;
                }
            }
            a aVar5 = new a();
            aVar5.a(new VertexAttribute(1, 3, ShaderProgram.POSITION_ATTRIBUTE));
            if (z3) {
                aVar5.a(new VertexAttribute(8, 3, ShaderProgram.NORMAL_ATTRIBUTE));
            }
            if (z4) {
                i6 = 2;
                aVar5.a(new VertexAttribute(16, 2, "a_texCoord0"));
            } else {
                i6 = 2;
            }
            i10++;
            String string = Integer.toString(i10);
            if ("default".equals(group2.name)) {
                strA = a.a.A("node", string);
            } else {
                strA = group2.name;
            }
            if ("default".equals(group2.name)) {
                strA2 = a.a.A("mesh", string);
            } else {
                strA2 = group2.name;
            }
            if ("default".equals(group2.name)) {
                strA3 = a.a.A("part", string);
            } else {
                strA3 = group2.name;
            }
            ModelNode modelNode = new ModelNode();
            modelNode.id = strA;
            modelNode.meshId = strA2;
            modelNode.scale = new com.badlogic.gdx.math.a(1.0f, 1.0f, 1.0f);
            modelNode.translation = new com.badlogic.gdx.math.a();
            modelNode.rotation = new n();
            ModelNodePart modelNodePart = new ModelNodePart();
            modelNodePart.meshPartId = strA3;
            modelNodePart.materialId = group2.materialName;
            modelNode.parts = new ModelNodePart[]{modelNodePart};
            ModelMeshPart modelMeshPart = new ModelMeshPart();
            modelMeshPart.id = strA3;
            modelMeshPart.indices = sArr;
            modelMeshPart.primitiveType = 4;
            ModelMesh modelMesh = new ModelMesh();
            modelMesh.id = strA2;
            modelMesh.attributes = (VertexAttribute[]) aVar5.u(VertexAttribute.class);
            modelMesh.vertices = fArr;
            modelMesh.parts = new ModelMeshPart[]{modelMeshPart};
            modelData.nodes.a(modelNode);
            modelData.meshes.a(modelMesh);
            modelData.materials.a(mtlLoader3.getMaterial(group2.materialName));
            i4 = i22;
            i2 = 3;
            i3 = i6;
            i9 = i21 + 1;
            mtlLoader = mtlLoader3;
        }
        j jVar = this.verts;
        if (jVar.f1824b > 0) {
            i5 = 0;
            jVar.f1824b = 0;
        } else {
            i5 = 0;
        }
        j jVar2 = this.norms;
        if (jVar2.f1824b > 0) {
            jVar2.f1824b = i5;
        }
        j jVar3 = this.uvs;
        if (jVar3.f1824b > 0) {
            jVar3.f1824b = i5;
        }
        a<Group> aVar6 = this.groups;
        if (aVar6.f1750b > 0) {
            aVar6.clear();
        }
        return modelData;
    }
}
