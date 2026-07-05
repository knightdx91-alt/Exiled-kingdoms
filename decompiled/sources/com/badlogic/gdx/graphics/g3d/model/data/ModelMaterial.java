package com.badlogic.gdx.graphics.g3d.model.data;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.a;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class ModelMaterial {
    public Color ambient;
    public Color diffuse;
    public Color emissive;
    public String id;
    public float opacity = 1.0f;
    public Color reflection;
    public float shininess;
    public Color specular;
    public a<ModelTexture> textures;
    public MaterialType type;

    public enum MaterialType {
        Lambert,
        Phong
    }
}
