package com.badlogic.gdx.graphics.g3d.attributes;

import a0.j;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g3d.Attribute;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class BlendingAttribute extends Attribute {
    public static final String Alias = "blended";
    public static final long Type = Attribute.register(Alias);
    public boolean blended;
    public int destFunction;
    public float opacity;
    public int sourceFunction;

    public BlendingAttribute() {
        this((BlendingAttribute) null);
    }

    public static final boolean is(long j2) {
        return (Type & j2) == j2;
    }

    @Override // com.badlogic.gdx.graphics.g3d.Attribute
    public int hashCode() {
        return Float.floatToRawIntBits(this.opacity) + (((((((super.hashCode() * 947) + (this.blended ? 1 : 0)) * 947) + this.sourceFunction) * 947) + this.destFunction) * 947);
    }

    public BlendingAttribute(boolean z2, int i2, int i3, float f2) {
        super(Type);
        this.blended = z2;
        this.sourceFunction = i2;
        this.destFunction = i3;
        this.opacity = f2;
    }

    @Override // java.lang.Comparable
    public int compareTo(Attribute attribute) {
        long j2 = this.type;
        long j3 = attribute.type;
        if (j2 != j3) {
            return (int) (j2 - j3);
        }
        BlendingAttribute blendingAttribute = (BlendingAttribute) attribute;
        boolean z2 = this.blended;
        if (z2 != blendingAttribute.blended) {
            return z2 ? 1 : -1;
        }
        int i2 = this.sourceFunction;
        int i3 = blendingAttribute.sourceFunction;
        if (i2 != i3) {
            return i2 - i3;
        }
        int i4 = this.destFunction;
        int i5 = blendingAttribute.destFunction;
        if (i4 != i5) {
            return i4 - i5;
        }
        if (j.d(this.opacity, blendingAttribute.opacity)) {
            return 0;
        }
        return this.opacity < blendingAttribute.opacity ? 1 : -1;
    }

    @Override // com.badlogic.gdx.graphics.g3d.Attribute
    public BlendingAttribute copy() {
        return new BlendingAttribute(this);
    }

    public BlendingAttribute(int i2, int i3, float f2) {
        this(true, i2, i3, f2);
    }

    public BlendingAttribute(int i2, int i3) {
        this(i2, i3, 1.0f);
    }

    public BlendingAttribute(boolean z2, float f2) {
        this(z2, GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA, f2);
    }

    public BlendingAttribute(float f2) {
        this(true, f2);
    }

    public BlendingAttribute(BlendingAttribute blendingAttribute) {
        this(blendingAttribute == null || blendingAttribute.blended, blendingAttribute == null ? GL20.GL_SRC_ALPHA : blendingAttribute.sourceFunction, blendingAttribute == null ? GL20.GL_ONE_MINUS_SRC_ALPHA : blendingAttribute.destFunction, blendingAttribute == null ? 1.0f : blendingAttribute.opacity);
    }
}
