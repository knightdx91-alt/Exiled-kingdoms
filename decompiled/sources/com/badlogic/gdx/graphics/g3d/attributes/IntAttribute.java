package com.badlogic.gdx.graphics.g3d.attributes;

import com.badlogic.gdx.graphics.g3d.Attribute;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class IntAttribute extends Attribute {
    public int value;
    public static final String CullFaceAlias = "cullface";
    public static final long CullFace = Attribute.register(CullFaceAlias);

    public IntAttribute(long j2) {
        super(j2);
    }

    public static IntAttribute createCullFace(int i2) {
        return new IntAttribute(CullFace, i2);
    }

    @Override // com.badlogic.gdx.graphics.g3d.Attribute
    public Attribute copy() {
        return new IntAttribute(this.type, this.value);
    }

    @Override // com.badlogic.gdx.graphics.g3d.Attribute
    public int hashCode() {
        return (super.hashCode() * 983) + this.value;
    }

    public IntAttribute(long j2, int i2) {
        super(j2);
        this.value = i2;
    }

    @Override // java.lang.Comparable
    public int compareTo(Attribute attribute) {
        long j2 = this.type;
        long j3 = attribute.type;
        return j2 != j3 ? (int) (j2 - j3) : this.value - ((IntAttribute) attribute).value;
    }
}
