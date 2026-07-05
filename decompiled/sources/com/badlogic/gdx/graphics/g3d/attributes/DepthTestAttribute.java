package com.badlogic.gdx.graphics.g3d.attributes;

import a0.j;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g3d.Attribute;
import com.badlogic.gdx.utils.m;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class DepthTestAttribute extends Attribute {
    public static final String Alias = "depthStencil";
    protected static long Mask;
    public static final long Type;
    public int depthFunc;
    public boolean depthMask;
    public float depthRangeFar;
    public float depthRangeNear;

    static {
        long jRegister = Attribute.register(Alias);
        Type = jRegister;
        Mask = jRegister;
    }

    public DepthTestAttribute() {
        this(GL20.GL_LEQUAL);
    }

    public static final boolean is(long j2) {
        return (j2 & Mask) != 0;
    }

    @Override // com.badlogic.gdx.graphics.g3d.Attribute
    public Attribute copy() {
        return new DepthTestAttribute(this);
    }

    @Override // com.badlogic.gdx.graphics.g3d.Attribute
    public int hashCode() {
        return ((Float.floatToRawIntBits(this.depthRangeFar) + ((Float.floatToRawIntBits(this.depthRangeNear) + (((super.hashCode() * 971) + this.depthFunc) * 971)) * 971)) * 971) + (this.depthMask ? 1 : 0);
    }

    public DepthTestAttribute(boolean z2) {
        this(GL20.GL_LEQUAL, z2);
    }

    @Override // java.lang.Comparable
    public int compareTo(Attribute attribute) {
        long j2 = this.type;
        long j3 = attribute.type;
        if (j2 != j3) {
            return (int) (j2 - j3);
        }
        DepthTestAttribute depthTestAttribute = (DepthTestAttribute) attribute;
        int i2 = this.depthFunc;
        int i3 = depthTestAttribute.depthFunc;
        if (i2 != i3) {
            return i2 - i3;
        }
        boolean z2 = this.depthMask;
        if (z2 != depthTestAttribute.depthMask) {
            return z2 ? -1 : 1;
        }
        if (!j.d(this.depthRangeNear, depthTestAttribute.depthRangeNear)) {
            return this.depthRangeNear < depthTestAttribute.depthRangeNear ? -1 : 1;
        }
        if (j.d(this.depthRangeFar, depthTestAttribute.depthRangeFar)) {
            return 0;
        }
        return this.depthRangeFar < depthTestAttribute.depthRangeFar ? -1 : 1;
    }

    public DepthTestAttribute(int i2) {
        this(i2, true);
    }

    public DepthTestAttribute(int i2, boolean z2) {
        this(i2, 0.0f, 1.0f, z2);
    }

    public DepthTestAttribute(int i2, float f2, float f3) {
        this(i2, f2, f3, true);
    }

    public DepthTestAttribute(int i2, float f2, float f3, boolean z2) {
        this(Type, i2, f2, f3, z2);
    }

    public DepthTestAttribute(long j2, int i2, float f2, float f3, boolean z2) {
        super(j2);
        if (is(j2)) {
            this.depthFunc = i2;
            this.depthRangeNear = f2;
            this.depthRangeFar = f3;
            this.depthMask = z2;
            return;
        }
        throw new m("Invalid type specified");
    }

    public DepthTestAttribute(DepthTestAttribute depthTestAttribute) {
        this(depthTestAttribute.type, depthTestAttribute.depthFunc, depthTestAttribute.depthRangeNear, depthTestAttribute.depthRangeFar, depthTestAttribute.depthMask);
    }
}
