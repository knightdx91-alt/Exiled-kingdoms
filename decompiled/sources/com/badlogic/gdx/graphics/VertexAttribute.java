package com.badlogic.gdx.graphics;

import a.a;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class VertexAttribute {
    public String alias;
    public final boolean normalized;
    public final int numComponents;
    public int offset;
    public final int type;
    public int unit;
    public final int usage;
    private final int usageIndex;

    public VertexAttribute(int i2, int i3, String str) {
        this(i2, i3, str, 0);
    }

    public static VertexAttribute Binormal() {
        return new VertexAttribute(256, 3, ShaderProgram.BINORMAL_ATTRIBUTE);
    }

    public static VertexAttribute BoneWeight(int i2) {
        return new VertexAttribute(64, 2, a.h(i2, ShaderProgram.BONEWEIGHT_ATTRIBUTE), i2);
    }

    public static VertexAttribute ColorPacked() {
        return new VertexAttribute(4, 4, GL20.GL_UNSIGNED_BYTE, true, ShaderProgram.COLOR_ATTRIBUTE);
    }

    public static VertexAttribute ColorUnpacked() {
        return new VertexAttribute(2, 4, GL20.GL_FLOAT, false, ShaderProgram.COLOR_ATTRIBUTE);
    }

    public static VertexAttribute Normal() {
        return new VertexAttribute(8, 3, ShaderProgram.NORMAL_ATTRIBUTE);
    }

    public static VertexAttribute Position() {
        return new VertexAttribute(1, 3, ShaderProgram.POSITION_ATTRIBUTE);
    }

    public static VertexAttribute Tangent() {
        return new VertexAttribute(VertexAttributes.Usage.Tangent, 3, ShaderProgram.TANGENT_ATTRIBUTE);
    }

    public static VertexAttribute TexCoords(int i2) {
        return new VertexAttribute(16, 2, a.h(i2, ShaderProgram.TEXCOORD_ATTRIBUTE), i2);
    }

    public VertexAttribute copy() {
        return new VertexAttribute(this.usage, this.numComponents, this.type, this.normalized, this.alias, this.unit);
    }

    public boolean equals(Object obj) {
        if (obj instanceof VertexAttribute) {
            return equals((VertexAttribute) obj);
        }
        return false;
    }

    public int getKey() {
        return (this.usageIndex << 8) + (this.unit & 255);
    }

    public int getSizeInBytes() {
        int i2 = this.type;
        if (i2 == 5126 || i2 == 5132) {
            return this.numComponents * 4;
        }
        switch (i2) {
            case GL20.GL_BYTE /* 5120 */:
            case GL20.GL_UNSIGNED_BYTE /* 5121 */:
                return this.numComponents;
            case GL20.GL_SHORT /* 5122 */:
            case GL20.GL_UNSIGNED_SHORT /* 5123 */:
                return this.numComponents * 2;
            default:
                return 0;
        }
    }

    public int hashCode() {
        return this.alias.hashCode() + (((getKey() * 541) + this.numComponents) * 541);
    }

    public VertexAttribute(int i2, int i3, String str, int i4) {
        this(i2, i3, i2 == 4 ? GL20.GL_UNSIGNED_BYTE : GL20.GL_FLOAT, i2 == 4, str, i4);
    }

    public VertexAttribute(int i2, int i3, int i4, boolean z2, String str) {
        this(i2, i3, i4, z2, str, 0);
    }

    public boolean equals(VertexAttribute vertexAttribute) {
        return vertexAttribute != null && this.usage == vertexAttribute.usage && this.numComponents == vertexAttribute.numComponents && this.type == vertexAttribute.type && this.normalized == vertexAttribute.normalized && this.alias.equals(vertexAttribute.alias) && this.unit == vertexAttribute.unit;
    }

    public VertexAttribute(int i2, int i3, int i4, boolean z2, String str, int i5) {
        this.usage = i2;
        this.numComponents = i3;
        this.type = i4;
        this.normalized = z2;
        this.alias = str;
        this.unit = i5;
        this.usageIndex = Integer.numberOfTrailingZeros(i2);
    }
}
