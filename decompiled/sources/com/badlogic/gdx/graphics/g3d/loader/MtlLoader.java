package com.badlogic.gdx.graphics.g3d.loader;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.model.data.ModelMaterial;
import com.badlogic.gdx.graphics.g3d.model.data.ModelTexture;
import com.badlogic.gdx.utils.a;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* JADX INFO: compiled from: ObjLoader.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
class MtlLoader {
    public a<ModelMaterial> materials = new a<>();

    MtlLoader() {
    }

    public ModelMaterial getMaterial(String str) {
        a.b<ModelMaterial> it = this.materials.iterator();
        while (it.hasNext()) {
            ModelMaterial next = it.next();
            if (next.id.equals(str)) {
                return next;
            }
        }
        ModelMaterial modelMaterial = new ModelMaterial();
        modelMaterial.id = str;
        modelMaterial.diffuse = new Color(Color.WHITE);
        this.materials.a(modelMaterial);
        return modelMaterial;
    }

    public void load(com.badlogic.gdx.files.a aVar) {
        Color color = Color.WHITE;
        if (aVar == null || !aVar.exists()) {
            return;
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(aVar.read()), 4096);
        String strReplace = "default";
        String strPath = null;
        float f2 = 1.0f;
        float f3 = 0.0f;
        Color color2 = color;
        while (true) {
            try {
                String line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                if (line.length() > 0 && line.charAt(0) == '\t') {
                    line = line.substring(1).trim();
                }
                String[] strArrSplit = line.split("\\s+");
                if (strArrSplit[0].length() != 0 && strArrSplit[0].charAt(0) != '#') {
                    String lowerCase = strArrSplit[0].toLowerCase();
                    if (lowerCase.equals("newmtl")) {
                        ModelMaterial modelMaterial = new ModelMaterial();
                        modelMaterial.id = strReplace;
                        modelMaterial.diffuse = new Color(color);
                        modelMaterial.specular = new Color(color2);
                        modelMaterial.opacity = f2;
                        modelMaterial.shininess = f3;
                        if (strPath != null) {
                            ModelTexture modelTexture = new ModelTexture();
                            modelTexture.usage = 2;
                            modelTexture.fileName = new String(strPath);
                            if (modelMaterial.textures == null) {
                                modelMaterial.textures = new a<>(true, 1);
                            }
                            modelMaterial.textures.a(modelTexture);
                        }
                        this.materials.a(modelMaterial);
                        strReplace = strArrSplit.length > 1 ? strArrSplit[1].replace('.', '_') : "default";
                        color = Color.WHITE;
                        color2 = color;
                        f2 = 1.0f;
                        f3 = 0.0f;
                    } else if (lowerCase.equals("kd") || lowerCase.equals("ks")) {
                        float f4 = Float.parseFloat(strArrSplit[1]);
                        float f5 = Float.parseFloat(strArrSplit[2]);
                        float f6 = Float.parseFloat(strArrSplit[3]);
                        float f7 = strArrSplit.length > 4 ? Float.parseFloat(strArrSplit[4]) : 1.0f;
                        if (strArrSplit[0].toLowerCase().equals("kd")) {
                            color = new Color();
                            color.set(f4, f5, f6, f7);
                        } else {
                            color2 = new Color();
                            color2.set(f4, f5, f6, f7);
                        }
                    } else if (lowerCase.equals("tr") || lowerCase.equals("d")) {
                        f2 = Float.parseFloat(strArrSplit[1]);
                    } else if (lowerCase.equals("ns")) {
                        f3 = Float.parseFloat(strArrSplit[1]);
                    } else if (lowerCase.equals("map_kd")) {
                        strPath = aVar.parent().child(strArrSplit[1]).path();
                    }
                }
            } catch (IOException unused) {
                return;
            }
        }
        bufferedReader.close();
        ModelMaterial modelMaterial2 = new ModelMaterial();
        modelMaterial2.id = strReplace;
        modelMaterial2.diffuse = new Color(color);
        modelMaterial2.specular = new Color(color2);
        modelMaterial2.opacity = f2;
        modelMaterial2.shininess = f3;
        if (strPath != null) {
            ModelTexture modelTexture2 = new ModelTexture();
            modelTexture2.usage = 2;
            modelTexture2.fileName = new String(strPath);
            if (modelMaterial2.textures == null) {
                modelMaterial2.textures = new a<>(true, 1);
            }
            modelMaterial2.textures.a(modelTexture2);
        }
        this.materials.a(modelMaterial2);
    }
}
