package com.badlogic.gdx.graphics.g3d;

import com.badlogic.gdx.utils.a;
import java.util.Iterator;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class Material extends Attributes {
    private static int counter;
    public String id;

    public Material() {
        StringBuilder sb = new StringBuilder("mtl");
        int i2 = counter + 1;
        counter = i2;
        sb.append(i2);
        this(sb.toString());
    }

    public Material copy() {
        return new Material(this);
    }

    @Override // com.badlogic.gdx.graphics.g3d.Attributes, java.util.Comparator
    public boolean equals(Object obj) {
        return (obj instanceof Material) && (obj == this || (((Material) obj).id.equals(this.id) && super.equals(obj)));
    }

    @Override // com.badlogic.gdx.graphics.g3d.Attributes
    public int hashCode() {
        return (this.id.hashCode() * 3) + super.hashCode();
    }

    public Material(String str) {
        this.id = str;
    }

    public Material(Attribute... attributeArr) {
        this();
        set(attributeArr);
    }

    public Material(String str, Attribute... attributeArr) {
        this(str);
        set(attributeArr);
    }

    public Material(a<Attribute> aVar) {
        this();
        set(aVar);
    }

    public Material(String str, a<Attribute> aVar) {
        this(str);
        set(aVar);
    }

    public Material(Material material) {
        this(material.id, material);
    }

    public Material(String str, Material material) {
        this(str);
        Iterator<Attribute> it = material.iterator();
        while (it.hasNext()) {
            set(it.next().copy());
        }
    }
}
