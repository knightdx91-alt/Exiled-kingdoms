package com.badlogic.gdx.graphics.g3d.attributes;

import com.badlogic.gdx.graphics.Cubemap;
import com.badlogic.gdx.graphics.g3d.Attribute;
import com.badlogic.gdx.graphics.g3d.utils.TextureDescriptor;
import com.badlogic.gdx.utils.m;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class CubemapAttribute extends Attribute {
    public static final long EnvironmentMap;
    public static final String EnvironmentMapAlias = "environmentCubemap";
    protected static long Mask;
    public final TextureDescriptor<Cubemap> textureDescription;

    static {
        long jRegister = Attribute.register(EnvironmentMapAlias);
        EnvironmentMap = jRegister;
        Mask = jRegister;
    }

    public CubemapAttribute(long j2) {
        super(j2);
        if (!is(j2)) {
            throw new m("Invalid type specified");
        }
        this.textureDescription = new TextureDescriptor<>();
    }

    public static final boolean is(long j2) {
        return (j2 & Mask) != 0;
    }

    @Override // com.badlogic.gdx.graphics.g3d.Attribute
    public Attribute copy() {
        return new CubemapAttribute(this);
    }

    @Override // com.badlogic.gdx.graphics.g3d.Attribute
    public int hashCode() {
        return this.textureDescription.hashCode() + (super.hashCode() * 967);
    }

    @Override // java.lang.Comparable
    public int compareTo(Attribute attribute) {
        long j2 = this.type;
        long j3 = attribute.type;
        return j2 != j3 ? (int) (j2 - j3) : this.textureDescription.compareTo((TextureDescriptor) ((CubemapAttribute) attribute).textureDescription);
    }

    public <T extends Cubemap> CubemapAttribute(long j2, TextureDescriptor<T> textureDescriptor) {
        this(j2);
        this.textureDescription.set(textureDescriptor);
    }

    public CubemapAttribute(long j2, Cubemap cubemap) {
        this(j2);
        this.textureDescription.texture = cubemap;
    }

    public CubemapAttribute(CubemapAttribute cubemapAttribute) {
        this(cubemapAttribute.type, cubemapAttribute.textureDescription);
    }
}
