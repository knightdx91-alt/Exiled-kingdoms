package com.badlogic.gdx.graphics.g3d.attributes;

import a0.j;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.Attribute;
import com.badlogic.gdx.graphics.g3d.utils.TextureDescriptor;
import com.badlogic.gdx.utils.m;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class TextureAttribute extends Attribute {
    public static final long Ambient;
    public static final String AmbientAlias = "ambientTexture";
    public static final long Bump;
    public static final String BumpAlias = "bumpTexture";
    public static final long Diffuse;
    public static final String DiffuseAlias = "diffuseTexture";
    public static final long Emissive;
    public static final String EmissiveAlias = "emissiveTexture";
    protected static long Mask = 0;
    public static final long Normal;
    public static final String NormalAlias = "normalTexture";
    public static final long Reflection;
    public static final String ReflectionAlias = "reflectionTexture";
    public static final long Specular;
    public static final String SpecularAlias = "specularTexture";
    public float offsetU;
    public float offsetV;
    public float scaleU;
    public float scaleV;
    public final TextureDescriptor<Texture> textureDescription;
    public int uvIndex;

    static {
        long jRegister = Attribute.register(DiffuseAlias);
        Diffuse = jRegister;
        long jRegister2 = Attribute.register(SpecularAlias);
        Specular = jRegister2;
        long jRegister3 = Attribute.register(BumpAlias);
        Bump = jRegister3;
        long jRegister4 = Attribute.register(NormalAlias);
        Normal = jRegister4;
        long jRegister5 = Attribute.register(AmbientAlias);
        Ambient = jRegister5;
        long jRegister6 = Attribute.register(EmissiveAlias);
        Emissive = jRegister6;
        long jRegister7 = Attribute.register(ReflectionAlias);
        Reflection = jRegister7;
        Mask = jRegister | jRegister2 | jRegister3 | jRegister4 | jRegister5 | jRegister6 | jRegister7;
    }

    public TextureAttribute(long j2) {
        super(j2);
        this.offsetU = 0.0f;
        this.offsetV = 0.0f;
        this.scaleU = 1.0f;
        this.scaleV = 1.0f;
        this.uvIndex = 0;
        if (!is(j2)) {
            throw new m("Invalid type specified");
        }
        this.textureDescription = new TextureDescriptor<>();
    }

    public static TextureAttribute createAmbient(Texture texture) {
        return new TextureAttribute(Ambient, texture);
    }

    public static TextureAttribute createBump(Texture texture) {
        return new TextureAttribute(Bump, texture);
    }

    public static TextureAttribute createDiffuse(Texture texture) {
        return new TextureAttribute(Diffuse, texture);
    }

    public static TextureAttribute createEmissive(Texture texture) {
        return new TextureAttribute(Emissive, texture);
    }

    public static TextureAttribute createNormal(Texture texture) {
        return new TextureAttribute(Normal, texture);
    }

    public static TextureAttribute createReflection(Texture texture) {
        return new TextureAttribute(Reflection, texture);
    }

    public static TextureAttribute createSpecular(Texture texture) {
        return new TextureAttribute(Specular, texture);
    }

    public static final boolean is(long j2) {
        return (j2 & Mask) != 0;
    }

    @Override // com.badlogic.gdx.graphics.g3d.Attribute
    public Attribute copy() {
        return new TextureAttribute(this);
    }

    @Override // com.badlogic.gdx.graphics.g3d.Attribute
    public int hashCode() {
        return ((Float.floatToRawIntBits(this.scaleV) + ((Float.floatToRawIntBits(this.scaleU) + ((Float.floatToRawIntBits(this.offsetV) + ((Float.floatToRawIntBits(this.offsetU) + ((this.textureDescription.hashCode() + (super.hashCode() * 991)) * 991)) * 991)) * 991)) * 991)) * 991) + this.uvIndex;
    }

    public void set(TextureRegion textureRegion) {
        this.textureDescription.texture = textureRegion.getTexture();
        this.offsetU = textureRegion.getU();
        this.offsetV = textureRegion.getV();
        this.scaleU = textureRegion.getU2() - this.offsetU;
        this.scaleV = textureRegion.getV2() - this.offsetV;
    }

    public static TextureAttribute createAmbient(TextureRegion textureRegion) {
        return new TextureAttribute(Ambient, textureRegion);
    }

    public static TextureAttribute createBump(TextureRegion textureRegion) {
        return new TextureAttribute(Bump, textureRegion);
    }

    public static TextureAttribute createDiffuse(TextureRegion textureRegion) {
        return new TextureAttribute(Diffuse, textureRegion);
    }

    public static TextureAttribute createEmissive(TextureRegion textureRegion) {
        return new TextureAttribute(Emissive, textureRegion);
    }

    public static TextureAttribute createNormal(TextureRegion textureRegion) {
        return new TextureAttribute(Normal, textureRegion);
    }

    public static TextureAttribute createReflection(TextureRegion textureRegion) {
        return new TextureAttribute(Reflection, textureRegion);
    }

    public static TextureAttribute createSpecular(TextureRegion textureRegion) {
        return new TextureAttribute(Specular, textureRegion);
    }

    @Override // java.lang.Comparable
    public int compareTo(Attribute attribute) {
        long j2 = this.type;
        long j3 = attribute.type;
        if (j2 != j3) {
            return j2 < j3 ? -1 : 1;
        }
        TextureAttribute textureAttribute = (TextureAttribute) attribute;
        int iCompareTo = this.textureDescription.compareTo((TextureDescriptor) textureAttribute.textureDescription);
        if (iCompareTo != 0) {
            return iCompareTo;
        }
        int i2 = this.uvIndex;
        int i3 = textureAttribute.uvIndex;
        if (i2 != i3) {
            return i2 - i3;
        }
        if (!j.d(this.scaleU, textureAttribute.scaleU)) {
            return this.scaleU > textureAttribute.scaleU ? 1 : -1;
        }
        if (!j.d(this.scaleV, textureAttribute.scaleV)) {
            return this.scaleV > textureAttribute.scaleV ? 1 : -1;
        }
        if (!j.d(this.offsetU, textureAttribute.offsetU)) {
            return this.offsetU > textureAttribute.offsetU ? 1 : -1;
        }
        if (j.d(this.offsetV, textureAttribute.offsetV)) {
            return 0;
        }
        return this.offsetV > textureAttribute.offsetV ? 1 : -1;
    }

    public <T extends Texture> TextureAttribute(long j2, TextureDescriptor<T> textureDescriptor) {
        this(j2);
        this.textureDescription.set(textureDescriptor);
    }

    public <T extends Texture> TextureAttribute(long j2, TextureDescriptor<T> textureDescriptor, float f2, float f3, float f4, float f5, int i2) {
        this(j2, textureDescriptor);
        this.offsetU = f2;
        this.offsetV = f3;
        this.scaleU = f4;
        this.scaleV = f5;
        this.uvIndex = i2;
    }

    public <T extends Texture> TextureAttribute(long j2, TextureDescriptor<T> textureDescriptor, float f2, float f3, float f4, float f5) {
        this(j2, textureDescriptor, f2, f3, f4, f5, 0);
    }

    public TextureAttribute(long j2, Texture texture) {
        this(j2);
        this.textureDescription.texture = texture;
    }

    public TextureAttribute(long j2, TextureRegion textureRegion) {
        this(j2);
        set(textureRegion);
    }

    public TextureAttribute(TextureAttribute textureAttribute) {
        this(textureAttribute.type, textureAttribute.textureDescription, textureAttribute.offsetU, textureAttribute.offsetV, textureAttribute.scaleU, textureAttribute.scaleV, textureAttribute.uvIndex);
    }
}
