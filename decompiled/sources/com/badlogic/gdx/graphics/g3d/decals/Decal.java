package com.badlogic.gdx.graphics.g3d.decals;

import a0.n;
import a0.q;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.a;
import com.badlogic.gdx.utils.l;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class Decal {
    public static final int C1 = 3;
    public static final int C2 = 9;
    public static final int C3 = 15;
    public static final int C4 = 21;
    public static final int SIZE = 24;
    public static final int U1 = 4;
    public static final int U2 = 10;
    public static final int U3 = 16;
    public static final int U4 = 22;
    public static final int V1 = 5;
    public static final int V2 = 11;
    public static final int V3 = 17;
    public static final int V4 = 23;
    private static final int VERTEX_SIZE = 6;
    public static final int X1 = 0;
    public static final int X2 = 6;
    public static final int X3 = 12;
    public static final int X4 = 18;
    public static final int Y1 = 1;
    public static final int Y2 = 7;
    public static final int Y3 = 13;
    public static final int Y4 = 19;
    public static final int Z1 = 2;
    public static final int Z2 = 8;
    public static final int Z3 = 14;
    public static final int Z4 = 20;
    protected Color color;
    protected q dimensions;
    protected DecalMaterial material;
    protected a position;
    protected n rotation;
    protected q scale;
    public q transformationOffset;
    protected boolean updated;
    public int value;
    protected float[] vertices;
    private static a tmp = new a();
    private static a tmp2 = new a();
    static final a dir = new a();
    protected static n rotator = new n(0.0f, 0.0f, 0.0f, 0.0f);

    public Decal() {
        this.vertices = new float[24];
        this.position = new a();
        this.rotation = new n();
        this.scale = new q(1.0f, 1.0f);
        this.color = new Color();
        this.transformationOffset = null;
        this.dimensions = new q();
        this.updated = false;
        this.material = new DecalMaterial();
    }

    public static Decal newDecal(TextureRegion textureRegion) {
        return newDecal(textureRegion.getRegionWidth(), textureRegion.getRegionHeight(), textureRegion, -1, -1);
    }

    public Color getColor() {
        return this.color;
    }

    public float getHeight() {
        return this.dimensions.f92b;
    }

    public DecalMaterial getMaterial() {
        return this.material;
    }

    public a getPosition() {
        return this.position;
    }

    public n getRotation() {
        return this.rotation;
    }

    public float getScaleX() {
        return this.scale.f91a;
    }

    public float getScaleY() {
        return this.scale.f92b;
    }

    public TextureRegion getTextureRegion() {
        return this.material.textureRegion;
    }

    public float[] getVertices() {
        update();
        return this.vertices;
    }

    public float getWidth() {
        return this.dimensions.f91a;
    }

    public float getX() {
        return this.position.f1729a;
    }

    public float getY() {
        return this.position.f1730b;
    }

    public float getZ() {
        return this.position.f1731c;
    }

    public void lookAt(a aVar, a aVar2) {
        a aVar3 = dir;
        aVar3.u(aVar);
        aVar3.w(this.position);
        aVar3.n();
        setRotation(aVar3, aVar2);
    }

    protected void resetVertices() {
        q qVar = this.dimensions;
        float f2 = qVar.f91a;
        float f3 = (-f2) / 2.0f;
        float f4 = f2 + f3;
        float f5 = qVar.f92b;
        float f6 = f5 / 2.0f;
        float f7 = f6 - f5;
        float[] fArr = this.vertices;
        fArr[0] = f3;
        fArr[1] = f6;
        fArr[2] = 0.0f;
        fArr[6] = f4;
        fArr[7] = f6;
        fArr[8] = 0.0f;
        fArr[12] = f3;
        fArr[13] = f7;
        fArr[14] = 0.0f;
        fArr[18] = f4;
        fArr[19] = f7;
        fArr[20] = 0.0f;
        this.updated = false;
    }

    public void rotateX(float f2) {
        rotator.g(a.f1725d, f2);
        this.rotation.b(rotator);
        this.updated = false;
    }

    public void rotateY(float f2) {
        rotator.g(a.f1726e, f2);
        this.rotation.b(rotator);
        this.updated = false;
    }

    public void rotateZ(float f2) {
        rotator.g(a.f1727f, f2);
        this.rotation.b(rotator);
        this.updated = false;
    }

    public void setBlending(int i2, int i3) {
        DecalMaterial decalMaterial = this.material;
        decalMaterial.srcBlendFactor = i2;
        decalMaterial.dstBlendFactor = i3;
    }

    public void setColor(float f2, float f3, float f4, float f5) {
        this.color.set(f2, f3, f4, f5);
        int i2 = ((int) (f3 * 255.0f)) << 8;
        int i3 = (int) (f2 * 255.0f);
        float fH = l.h(i3 | i2 | (((int) (f4 * 255.0f)) << 16) | (((int) (f5 * 255.0f)) << 24));
        float[] fArr = this.vertices;
        fArr[3] = fH;
        fArr[9] = fH;
        fArr[15] = fH;
        fArr[21] = fH;
    }

    public void setDimensions(float f2, float f3) {
        q qVar = this.dimensions;
        qVar.f91a = f2;
        qVar.f92b = f3;
        this.updated = false;
    }

    public void setHeight(float f2) {
        this.dimensions.f92b = f2;
        this.updated = false;
    }

    public void setMaterial(DecalMaterial decalMaterial) {
        this.material = decalMaterial;
    }

    public void setPackedColor(float f2) {
        Color.abgr8888ToColor(this.color, f2);
        float[] fArr = this.vertices;
        fArr[3] = f2;
        fArr[9] = f2;
        fArr[15] = f2;
        fArr[21] = f2;
    }

    public void setPosition(float f2, float f3, float f4) {
        this.position.t(f2, f3, f4);
        this.updated = false;
    }

    public void setRotation(float f2, float f3, float f4) {
        n nVar = this.rotation;
        nVar.getClass();
        double d2 = f4 * 0.017453292f * 0.5f;
        float fSin = (float) Math.sin(d2);
        float fCos = (float) Math.cos(d2);
        double d3 = f3 * 0.017453292f * 0.5f;
        float fSin2 = (float) Math.sin(d3);
        float fCos2 = (float) Math.cos(d3);
        double d4 = f2 * 0.017453292f * 0.5f;
        float fSin3 = (float) Math.sin(d4);
        float fCos3 = (float) Math.cos(d4);
        float f5 = fCos3 * fSin2;
        float f6 = fSin3 * fCos2;
        float f7 = fCos3 * fCos2;
        float f8 = fSin3 * fSin2;
        nVar.f83a = (f6 * fSin) + (f5 * fCos);
        nVar.f84b = (f6 * fCos) - (f5 * fSin);
        nVar.f85c = (f7 * fSin) - (f8 * fCos);
        nVar.f86d = (f8 * fSin) + (f7 * fCos);
        this.updated = false;
    }

    public void setRotationX(float f2) {
        this.rotation.g(a.f1725d, f2);
        this.updated = false;
    }

    public void setRotationY(float f2) {
        this.rotation.g(a.f1726e, f2);
        this.updated = false;
    }

    public void setRotationZ(float f2) {
        this.rotation.g(a.f1727f, f2);
        this.updated = false;
    }

    public void setScale(float f2, float f3) {
        q qVar = this.scale;
        qVar.f91a = f2;
        qVar.f92b = f3;
        this.updated = false;
    }

    public void setScaleX(float f2) {
        this.scale.f91a = f2;
        this.updated = false;
    }

    public void setScaleY(float f2) {
        this.scale.f92b = f2;
        this.updated = false;
    }

    public void setTextureRegion(TextureRegion textureRegion) {
        this.material.textureRegion = textureRegion;
        updateUVs();
    }

    public void setWidth(float f2) {
        this.dimensions.f91a = f2;
        this.updated = false;
    }

    public void setX(float f2) {
        this.position.f1729a = f2;
        this.updated = false;
    }

    public void setY(float f2) {
        this.position.f1730b = f2;
        this.updated = false;
    }

    public void setZ(float f2) {
        this.position.f1731c = f2;
        this.updated = false;
    }

    protected void transformVertices() {
        float f2;
        float f3;
        q qVar = this.transformationOffset;
        if (qVar != null) {
            f2 = -qVar.f91a;
            f3 = -qVar.f92b;
        } else {
            f2 = 0.0f;
            f3 = 0.0f;
        }
        float[] fArr = this.vertices;
        float f4 = fArr[0] + f2;
        q qVar2 = this.scale;
        float f5 = f4 * qVar2.f91a;
        float f6 = (fArr[1] + f3) * qVar2.f92b;
        float f7 = fArr[2];
        n nVar = this.rotation;
        float f8 = nVar.f86d;
        float f9 = nVar.f84b;
        float f10 = nVar.f85c;
        fArr[0] = ((f9 * f7) + (f8 * f5)) - (f10 * f6);
        float f11 = nVar.f83a;
        fArr[1] = ((f10 * f5) + (f8 * f6)) - (f11 * f7);
        fArr[2] = ((f11 * f6) + (f8 * f7)) - (f9 * f5);
        float f12 = (((-f11) * f5) - (f9 * f6)) - (f10 * f7);
        nVar.a();
        float[] fArr2 = this.vertices;
        float f13 = fArr2[0];
        float f14 = fArr2[1];
        float f15 = fArr2[2];
        n nVar2 = this.rotation;
        float f16 = nVar2.f83a;
        float f17 = nVar2.f86d;
        float f18 = nVar2.f85c;
        float f19 = nVar2.f84b;
        fArr2[0] = ((f14 * f18) + ((f13 * f17) + (f12 * f16))) - (f15 * f19);
        fArr2[1] = ((f15 * f16) + ((f14 * f17) + (f12 * f19))) - (f13 * f18);
        fArr2[2] = ((f13 * f19) + ((f15 * f17) + (f12 * f18))) - (f14 * f16);
        nVar2.a();
        float[] fArr3 = this.vertices;
        float f20 = fArr3[0];
        a aVar = this.position;
        fArr3[0] = (aVar.f1729a - f2) + f20;
        fArr3[1] = (aVar.f1730b - f3) + fArr3[1];
        fArr3[2] = fArr3[2] + aVar.f1731c;
        float f21 = fArr3[6] + f2;
        q qVar3 = this.scale;
        float f22 = f21 * qVar3.f91a;
        float f23 = (fArr3[7] + f3) * qVar3.f92b;
        float f24 = fArr3[8];
        n nVar3 = this.rotation;
        float f25 = nVar3.f86d;
        float f26 = nVar3.f84b;
        float f27 = nVar3.f85c;
        fArr3[6] = ((f26 * f24) + (f25 * f22)) - (f27 * f23);
        float f28 = nVar3.f83a;
        fArr3[7] = ((f27 * f22) + (f25 * f23)) - (f28 * f24);
        fArr3[8] = ((f28 * f23) + (f25 * f24)) - (f26 * f22);
        float f29 = (((-f28) * f22) - (f26 * f23)) - (f27 * f24);
        nVar3.a();
        float[] fArr4 = this.vertices;
        float f30 = fArr4[6];
        float f31 = fArr4[7];
        float f32 = fArr4[8];
        n nVar4 = this.rotation;
        float f33 = nVar4.f83a;
        float f34 = nVar4.f86d;
        float f35 = nVar4.f85c;
        float f36 = nVar4.f84b;
        fArr4[6] = ((f31 * f35) + ((f30 * f34) + (f29 * f33))) - (f32 * f36);
        fArr4[7] = ((f32 * f33) + ((f31 * f34) + (f29 * f36))) - (f30 * f35);
        fArr4[8] = ((f30 * f36) + ((f32 * f34) + (f29 * f35))) - (f31 * f33);
        nVar4.a();
        float[] fArr5 = this.vertices;
        float f37 = fArr5[6];
        a aVar2 = this.position;
        fArr5[6] = (aVar2.f1729a - f2) + f37;
        fArr5[7] = (aVar2.f1730b - f3) + fArr5[7];
        fArr5[8] = fArr5[8] + aVar2.f1731c;
        float f38 = fArr5[12] + f2;
        q qVar4 = this.scale;
        float f39 = f38 * qVar4.f91a;
        float f40 = (fArr5[13] + f3) * qVar4.f92b;
        float f41 = fArr5[14];
        n nVar5 = this.rotation;
        float f42 = nVar5.f86d;
        float f43 = nVar5.f84b;
        float f44 = nVar5.f85c;
        fArr5[12] = ((f43 * f41) + (f42 * f39)) - (f44 * f40);
        float f45 = nVar5.f83a;
        fArr5[13] = ((f44 * f39) + (f42 * f40)) - (f45 * f41);
        fArr5[14] = ((f45 * f40) + (f42 * f41)) - (f43 * f39);
        float f46 = (((-f45) * f39) - (f43 * f40)) - (f44 * f41);
        nVar5.a();
        float[] fArr6 = this.vertices;
        float f47 = fArr6[12];
        float f48 = fArr6[13];
        float f49 = fArr6[14];
        n nVar6 = this.rotation;
        float f50 = nVar6.f83a;
        float f51 = nVar6.f86d;
        float f52 = nVar6.f85c;
        float f53 = nVar6.f84b;
        fArr6[12] = ((f48 * f52) + ((f47 * f51) + (f46 * f50))) - (f49 * f53);
        fArr6[13] = ((f49 * f50) + ((f48 * f51) + (f46 * f53))) - (f47 * f52);
        fArr6[14] = ((f47 * f53) + ((f49 * f51) + (f46 * f52))) - (f48 * f50);
        nVar6.a();
        float[] fArr7 = this.vertices;
        float f54 = fArr7[12];
        a aVar3 = this.position;
        fArr7[12] = (aVar3.f1729a - f2) + f54;
        fArr7[13] = (aVar3.f1730b - f3) + fArr7[13];
        fArr7[14] = fArr7[14] + aVar3.f1731c;
        float f55 = fArr7[18] + f2;
        q qVar5 = this.scale;
        float f56 = f55 * qVar5.f91a;
        float f57 = (fArr7[19] + f3) * qVar5.f92b;
        float f58 = fArr7[20];
        n nVar7 = this.rotation;
        float f59 = nVar7.f86d;
        float f60 = nVar7.f84b;
        float f61 = nVar7.f85c;
        fArr7[18] = ((f60 * f58) + (f59 * f56)) - (f61 * f57);
        float f62 = nVar7.f83a;
        fArr7[19] = ((f61 * f56) + (f59 * f57)) - (f62 * f58);
        fArr7[20] = ((f62 * f57) + (f59 * f58)) - (f60 * f56);
        float f63 = (((-f62) * f56) - (f60 * f57)) - (f61 * f58);
        nVar7.a();
        float[] fArr8 = this.vertices;
        float f64 = fArr8[18];
        float f65 = fArr8[19];
        float f66 = fArr8[20];
        n nVar8 = this.rotation;
        float f67 = nVar8.f83a;
        float f68 = nVar8.f86d;
        float f69 = nVar8.f85c;
        float f70 = nVar8.f84b;
        fArr8[18] = ((f65 * f69) + ((f64 * f68) + (f63 * f67))) - (f66 * f70);
        fArr8[19] = ((f66 * f67) + ((f65 * f68) + (f63 * f70))) - (f64 * f69);
        fArr8[20] = ((f64 * f70) + ((f66 * f68) + (f63 * f69))) - (f65 * f67);
        nVar8.a();
        float[] fArr9 = this.vertices;
        float f71 = fArr9[18];
        a aVar4 = this.position;
        fArr9[18] = (aVar4.f1729a - f2) + f71;
        fArr9[19] = (aVar4.f1730b - f3) + fArr9[19];
        fArr9[20] = fArr9[20] + aVar4.f1731c;
        this.updated = true;
    }

    public void translate(float f2, float f3, float f4) {
        this.position.a(f2, f3, f4);
        this.updated = false;
    }

    public void translateX(float f2) {
        this.position.f1729a += f2;
        this.updated = false;
    }

    public void translateY(float f2) {
        this.position.f1730b += f2;
        this.updated = false;
    }

    public void translateZ(float f2) {
        this.position.f1731c += f2;
        this.updated = false;
    }

    protected void update() {
        if (this.updated) {
            return;
        }
        resetVertices();
        transformVertices();
    }

    protected void updateUVs() {
        TextureRegion textureRegion = this.material.textureRegion;
        this.vertices[4] = textureRegion.getU();
        this.vertices[5] = textureRegion.getV();
        this.vertices[10] = textureRegion.getU2();
        this.vertices[11] = textureRegion.getV();
        this.vertices[16] = textureRegion.getU();
        this.vertices[17] = textureRegion.getV2();
        this.vertices[22] = textureRegion.getU2();
        this.vertices[23] = textureRegion.getV2();
    }

    public static Decal newDecal(TextureRegion textureRegion, boolean z2) {
        return newDecal(textureRegion.getRegionWidth(), textureRegion.getRegionHeight(), textureRegion, z2 ? GL20.GL_SRC_ALPHA : -1, z2 ? GL20.GL_ONE_MINUS_SRC_ALPHA : -1);
    }

    public static Decal newDecal(float f2, float f3, TextureRegion textureRegion) {
        return newDecal(f2, f3, textureRegion, -1, -1);
    }

    public void setPosition(a aVar) {
        this.position.u(aVar);
        this.updated = false;
    }

    public void translate(a aVar) {
        this.position.b(aVar);
        this.updated = false;
    }

    public static Decal newDecal(float f2, float f3, TextureRegion textureRegion, boolean z2) {
        return newDecal(f2, f3, textureRegion, z2 ? GL20.GL_SRC_ALPHA : -1, z2 ? GL20.GL_ONE_MINUS_SRC_ALPHA : -1);
    }

    public static Decal newDecal(float f2, float f3, TextureRegion textureRegion, int i2, int i3) {
        Decal decal = new Decal();
        decal.setTextureRegion(textureRegion);
        decal.setBlending(i2, i3);
        q qVar = decal.dimensions;
        qVar.f91a = f2;
        qVar.f92b = f3;
        decal.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        return decal;
    }

    public void setScale(float f2) {
        q qVar = this.scale;
        qVar.f91a = f2;
        qVar.f92b = f2;
        this.updated = false;
    }

    public void setColor(Color color) {
        this.color.set(color);
        float floatBits = color.toFloatBits();
        float[] fArr = this.vertices;
        fArr[3] = floatBits;
        fArr[9] = floatBits;
        fArr[15] = floatBits;
        fArr[21] = floatBits;
    }

    public Decal(DecalMaterial decalMaterial) {
        this.vertices = new float[24];
        this.position = new a();
        this.rotation = new n();
        this.scale = new q(1.0f, 1.0f);
        this.color = new Color();
        this.transformationOffset = null;
        this.dimensions = new q();
        this.updated = false;
        this.material = decalMaterial;
    }

    public static Decal newDecal(float f2, float f3, TextureRegion textureRegion, int i2, int i3, DecalMaterial decalMaterial) {
        Decal decal = new Decal(decalMaterial);
        decal.setTextureRegion(textureRegion);
        decal.setBlending(i2, i3);
        q qVar = decal.dimensions;
        qVar.f91a = f2;
        qVar.f92b = f3;
        decal.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        return decal;
    }

    public void setRotation(a aVar, a aVar2) {
        a aVar3 = tmp;
        aVar3.u(aVar2);
        aVar3.d(aVar);
        aVar3.n();
        a aVar4 = tmp2;
        aVar4.getClass();
        aVar4.t(aVar.f1729a, aVar.f1730b, aVar.f1731c);
        aVar4.d(tmp);
        aVar4.n();
        n nVar = this.rotation;
        a aVar5 = tmp;
        float f2 = aVar5.f1729a;
        a aVar6 = tmp2;
        nVar.h(false, f2, aVar6.f1729a, aVar.f1729a, aVar5.f1730b, aVar6.f1730b, aVar.f1730b, aVar5.f1731c, aVar6.f1731c, aVar.f1731c);
        this.updated = false;
    }

    public void setRotation(n nVar) {
        this.rotation.f(nVar);
        this.updated = false;
    }
}
