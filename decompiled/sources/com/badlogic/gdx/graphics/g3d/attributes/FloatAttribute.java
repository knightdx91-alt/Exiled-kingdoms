package com.badlogic.gdx.graphics.g3d.attributes;

import a0.j;
import com.badlogic.gdx.graphics.g3d.Attribute;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class FloatAttribute extends Attribute {
    public float value;
    public static final String ShininessAlias = "shininess";
    public static final long Shininess = Attribute.register(ShininessAlias);
    public static final String AlphaTestAlias = "alphaTest";
    public static final long AlphaTest = Attribute.register(AlphaTestAlias);

    public FloatAttribute(long j2) {
        super(j2);
    }

    public static FloatAttribute createAlphaTest(float f2) {
        return new FloatAttribute(AlphaTest, f2);
    }

    public static FloatAttribute createShininess(float f2) {
        return new FloatAttribute(Shininess, f2);
    }

    @Override // com.badlogic.gdx.graphics.g3d.Attribute
    public Attribute copy() {
        return new FloatAttribute(this.type, this.value);
    }

    @Override // com.badlogic.gdx.graphics.g3d.Attribute
    public int hashCode() {
        return Float.floatToRawIntBits(this.value) + (super.hashCode() * 977);
    }

    public FloatAttribute(long j2, float f2) {
        super(j2);
        this.value = f2;
    }

    @Override // java.lang.Comparable
    public int compareTo(Attribute attribute) {
        long j2 = this.type;
        long j3 = attribute.type;
        if (j2 != j3) {
            return (int) (j2 - j3);
        }
        float f2 = ((FloatAttribute) attribute).value;
        if (j.d(this.value, f2)) {
            return 0;
        }
        return this.value < f2 ? -1 : 1;
    }
}
