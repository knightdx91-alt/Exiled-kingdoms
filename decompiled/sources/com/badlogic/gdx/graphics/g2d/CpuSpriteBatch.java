package com.badlogic.gdx.graphics.g2d;

import a0.a;
import a0.j;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.m;
import com.google.android.gms.auth.api.credentials.CredentialsApi;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class CpuSpriteBatch extends SpriteBatch {
    private final a adjustAffine;
    private boolean adjustNeeded;
    private boolean haveIdentityRealMatrix;
    private final a tmpAffine;
    private final Matrix4 virtualMatrix;

    public CpuSpriteBatch() {
        this(CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT);
    }

    private static boolean checkEqual(Matrix4 matrix4, a aVar) {
        float[] fArr = matrix4.f1724a;
        return fArr[0] == aVar.f25a && fArr[1] == aVar.f28d && fArr[4] == aVar.f26b && fArr[5] == aVar.f29e && fArr[12] == aVar.f27c && fArr[13] == aVar.f30f;
    }

    private static boolean checkIdt(Matrix4 matrix4) {
        float[] fArr = matrix4.f1724a;
        return fArr[0] == 1.0f && fArr[1] == 0.0f && fArr[4] == 0.0f && fArr[5] == 1.0f && fArr[12] == 0.0f && fArr[13] == 0.0f;
    }

    private void drawAdjusted(TextureRegion textureRegion, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10) {
        drawAdjustedUV(textureRegion.texture, f2, f3, f4, f5, f6, f7, f8, f9, f10, textureRegion.f1698u, textureRegion.v2, textureRegion.u2, textureRegion.f1699v, false, false);
    }

    private void drawAdjustedUV(Texture texture, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13, float f14, boolean z2, boolean z3) {
        float f15;
        float f16;
        float f17;
        float f18;
        float f19;
        float f20;
        float f21;
        float f22;
        float f23;
        float f24;
        float f25;
        if (!this.drawing) {
            throw new IllegalStateException("CpuSpriteBatch.begin must be called before draw.");
        }
        if (texture != this.lastTexture) {
            switchTexture(texture);
        } else if (this.idx == this.vertices.length) {
            super.flush();
        }
        float f26 = f2 + f4;
        float f27 = f3 + f5;
        float f28 = -f4;
        float f29 = -f5;
        float f30 = f6 - f4;
        float f31 = f7 - f5;
        if (f8 != 1.0f || f9 != 1.0f) {
            f28 *= f8;
            f29 *= f9;
            f30 *= f8;
            f31 *= f9;
        }
        if (f10 != 0.0f) {
            float fC = j.c(f10);
            float fK = j.k(f10);
            float f32 = fC * f28;
            f16 = f32 - (fK * f29);
            float f33 = f28 * fK;
            float f34 = (f29 * fC) + f33;
            float f35 = fK * f31;
            f15 = f32 - f35;
            float f36 = f31 * fC;
            f19 = f33 + f36;
            float f37 = (fC * f30) - f35;
            float f38 = f36 + (fK * f30);
            f18 = f38 - (f19 - f34);
            f21 = (f37 - f15) + f16;
            f30 = f37;
            f17 = f34;
            f20 = f38;
        } else {
            f15 = f28;
            f16 = f15;
            f17 = f29;
            f18 = f17;
            f19 = f31;
            f20 = f19;
            f21 = f30;
        }
        float f39 = f16 + f26;
        float f40 = f17 + f27;
        float f41 = f15 + f26;
        float f42 = f19 + f27;
        float f43 = f30 + f26;
        float f44 = f20 + f27;
        float f45 = f21 + f26;
        float f46 = f18 + f27;
        if (z2) {
            f23 = f11;
            f22 = f13;
        } else {
            f22 = f11;
            f23 = f13;
        }
        if (z3) {
            f25 = f12;
            f24 = f14;
        } else {
            f24 = f12;
            f25 = f14;
        }
        a aVar = this.adjustAffine;
        float[] fArr = this.vertices;
        int i2 = this.idx;
        float f47 = aVar.f25a;
        float f48 = aVar.f26b;
        float f49 = f23;
        float f50 = aVar.f27c;
        fArr[i2] = (f48 * f40) + (f47 * f39) + f50;
        float f51 = aVar.f28d;
        float f52 = aVar.f29e;
        float f53 = (f40 * f52) + (f39 * f51);
        float f54 = aVar.f30f;
        fArr[i2 + 1] = f53 + f54;
        float f55 = this.colorPacked;
        fArr[i2 + 2] = f55;
        fArr[i2 + 3] = f22;
        fArr[i2 + 4] = f24;
        fArr[i2 + 5] = a.a.B(f48, f42, f47 * f41, f50);
        fArr[i2 + 6] = a.a.B(f52, f42, f41 * f51, f54);
        fArr[i2 + 7] = f55;
        fArr[i2 + 8] = f22;
        fArr[i2 + 9] = f25;
        fArr[i2 + 10] = a.a.B(f48, f44, f47 * f43, f50);
        fArr[i2 + 11] = a.a.B(f52, f44, f51 * f43, f54);
        fArr[i2 + 12] = f55;
        fArr[i2 + 13] = f49;
        fArr[i2 + 14] = f25;
        fArr[i2 + 15] = a.a.B(f48, f46, f47 * f45, f50);
        fArr[i2 + 16] = a.a.B(f52, f46, f51 * f45, f54);
        fArr[i2 + 17] = f55;
        fArr[i2 + 18] = f49;
        fArr[i2 + 19] = f24;
        this.idx = i2 + 20;
    }

    @Override // com.badlogic.gdx.graphics.g2d.SpriteBatch, com.badlogic.gdx.graphics.g2d.Batch
    public void draw(Texture texture, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, int i2, int i3, int i4, int i5, boolean z2, boolean z3) {
        if (this.adjustNeeded) {
            drawAdjusted(texture, f2, f3, f4, f5, f6, f7, f8, f9, f10, i2, i3, i4, i5, z2, z3);
        } else {
            super.draw(texture, f2, f3, f4, f5, f6, f7, f8, f9, f10, i2, i3, i4, i5, z2, z3);
        }
    }

    public void flushAndSyncTransformMatrix() {
        flush();
        if (this.adjustNeeded) {
            boolean zCheckIdt = checkIdt(this.virtualMatrix);
            this.haveIdentityRealMatrix = zCheckIdt;
            if (!zCheckIdt) {
                float[] fArr = this.virtualMatrix.f1724a;
                float f2 = fArr[3];
                float f3 = fArr[6];
                float f4 = f2 * f3;
                float f5 = fArr[9];
                float f6 = fArr[12];
                float f7 = fArr[2];
                float f8 = fArr[7];
                float f9 = f7 * f8;
                float fZ = a.a.z(f9, f5, f6, f4 * f5 * f6);
                float f10 = fArr[5];
                float f11 = f2 * f10;
                float f12 = fArr[10];
                float fZ2 = a.a.z(f11, f12, f6, fZ);
                float f13 = fArr[1];
                float f14 = f13 * f8;
                float fA = a.a.a(f14, f12, f6, fZ2);
                float f15 = f7 * f10;
                float f16 = fArr[11];
                float fA2 = a.a.a(f15, f16, f6, fA);
                float f17 = f13 * f3;
                float fZ3 = a.a.z(f17, f16, f6, fA2);
                float f18 = fArr[8];
                float f19 = fArr[13];
                float fA3 = a.a.a(f9, f18, f19, fZ3 - ((f4 * f18) * f19));
                float f20 = fArr[4];
                float f21 = f2 * f20;
                float fA4 = a.a.a(f21, f12, f19, fA3);
                float f22 = fArr[0];
                float f23 = f8 * f22;
                float f24 = f7 * f20;
                float f25 = f3 * f22;
                float fA5 = a.a.a(f25, f16, f19, a.a.z(f24, f16, f19, a.a.z(f23, f12, f19, fA4)));
                float f26 = fArr[14];
                float f27 = f13 * f20;
                float fA6 = a.a.a(f27, f16, f26, a.a.a(f23, f5, f26, a.a.z(f21, f5, f26, a.a.z(f14, f18, f26, (f11 * f18 * f26) + fA5))));
                float f28 = f22 * f10;
                float fZ4 = a.a.z(f28, f16, f26, fA6);
                float f29 = fArr[15];
                if (a.a.a(f28, f12, f29, a.a.z(f27, f12, f29, a.a.z(f25, f5, f29, a.a.a(f24, f5, f29, a.a.a(f17, f18, f29, fZ4 - ((f15 * f18) * f29)))))) == 0.0f) {
                    throw new m("Transform matrix is singular, can't sync");
                }
            }
            this.adjustNeeded = false;
            super.setTransformMatrix(this.virtualMatrix);
        }
    }

    @Override // com.badlogic.gdx.graphics.g2d.SpriteBatch, com.badlogic.gdx.graphics.g2d.Batch
    public Matrix4 getTransformMatrix() {
        return this.adjustNeeded ? this.virtualMatrix : super.getTransformMatrix();
    }

    @Override // com.badlogic.gdx.graphics.g2d.SpriteBatch, com.badlogic.gdx.graphics.g2d.Batch
    public void setTransformMatrix(Matrix4 matrix4) {
        Matrix4 transformMatrix = super.getTransformMatrix();
        if (checkEqual(transformMatrix, matrix4)) {
            this.adjustNeeded = false;
            return;
        }
        if (!isDrawing()) {
            transformMatrix.q(matrix4);
            this.haveIdentityRealMatrix = checkIdt(transformMatrix);
            return;
        }
        this.virtualMatrix.q(matrix4);
        this.adjustNeeded = true;
        if (this.haveIdentityRealMatrix) {
            this.adjustAffine.c(matrix4);
            return;
        }
        this.tmpAffine.c(matrix4);
        a aVar = this.adjustAffine;
        aVar.c(transformMatrix);
        aVar.a();
        aVar.b(this.tmpAffine);
    }

    public CpuSpriteBatch(int i2) {
        this(i2, null);
    }

    private void drawAdjusted(Texture texture, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, int i2, int i3, int i4, int i5, boolean z2, boolean z3) {
        float width = 1.0f / texture.getWidth();
        float height = 1.0f / texture.getHeight();
        drawAdjustedUV(texture, f2, f3, f4, f5, f6, f7, f8, f9, f10, i2 * width, (i3 + i5) * height, width * (i2 + i4), height * i3, z2, z3);
    }

    public CpuSpriteBatch(int i2, ShaderProgram shaderProgram) {
        super(i2, shaderProgram);
        this.virtualMatrix = new Matrix4();
        this.adjustAffine = new a();
        this.haveIdentityRealMatrix = true;
        this.tmpAffine = new a();
    }

    private static boolean checkEqual(Matrix4 matrix4, Matrix4 matrix42) {
        if (matrix4 == matrix42) {
            return true;
        }
        float[] fArr = matrix4.f1724a;
        float f2 = fArr[0];
        float[] fArr2 = matrix42.f1724a;
        return f2 == fArr2[0] && fArr[1] == fArr2[1] && fArr[4] == fArr2[4] && fArr[5] == fArr2[5] && fArr[12] == fArr2[12] && fArr[13] == fArr2[13];
    }

    @Override // com.badlogic.gdx.graphics.g2d.SpriteBatch, com.badlogic.gdx.graphics.g2d.Batch
    public void draw(Texture texture, float f2, float f3, float f4, float f5, int i2, int i3, int i4, int i5, boolean z2, boolean z3) {
        if (!this.adjustNeeded) {
            super.draw(texture, f2, f3, f4, f5, i2, i3, i4, i5, z2, z3);
        } else {
            drawAdjusted(texture, f2, f3, 0.0f, 0.0f, f4, f5, 1.0f, 1.0f, 0.0f, i2, i3, i4, i5, z2, z3);
        }
    }

    private void drawAdjusted(TextureRegion textureRegion, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, boolean z2) {
        float f11;
        float f12;
        float f13;
        float f14;
        float f15;
        float f16;
        float f17;
        float f18;
        float f19;
        float f20;
        float f21;
        if (this.drawing) {
            Texture texture = textureRegion.texture;
            if (texture != this.lastTexture) {
                switchTexture(texture);
            } else if (this.idx == this.vertices.length) {
                super.flush();
            }
            float f22 = f2 + f4;
            float f23 = f3 + f5;
            float f24 = -f4;
            float f25 = -f5;
            float f26 = f6 - f4;
            float f27 = f7 - f5;
            if (f8 != 1.0f || f9 != 1.0f) {
                f24 *= f8;
                f25 *= f9;
                f26 *= f8;
                f27 *= f9;
            }
            if (f10 != 0.0f) {
                float fC = j.c(f10);
                float fK = j.k(f10);
                float f28 = fC * f24;
                f12 = f28 - (fK * f25);
                float f29 = f24 * fK;
                float f30 = (f25 * fC) + f29;
                float f31 = fK * f27;
                f11 = f28 - f31;
                float f32 = f27 * fC;
                f15 = f29 + f32;
                float f33 = (fC * f26) - f31;
                float f34 = f32 + (fK * f26);
                f14 = f34 - (f15 - f30);
                f17 = (f33 - f11) + f12;
                f26 = f33;
                f13 = f30;
                f16 = f34;
            } else {
                f11 = f24;
                f12 = f11;
                f13 = f25;
                f14 = f13;
                f15 = f27;
                f16 = f15;
                f17 = f26;
            }
            float f35 = f12 + f22;
            float f36 = f13 + f23;
            float f37 = f11 + f22;
            float f38 = f15 + f23;
            float f39 = f26 + f22;
            float f40 = f16 + f23;
            float f41 = f17 + f22;
            float f42 = f14 + f23;
            if (z2) {
                f18 = textureRegion.u2;
                f19 = textureRegion.v2;
                f20 = textureRegion.f1698u;
                f21 = textureRegion.f1699v;
            } else {
                f18 = textureRegion.f1698u;
                f19 = textureRegion.f1699v;
                f20 = textureRegion.u2;
                f21 = textureRegion.v2;
            }
            float f43 = f21;
            float f44 = f19;
            float f45 = f20;
            float f46 = f18;
            a aVar = this.adjustAffine;
            float[] fArr = this.vertices;
            int i2 = this.idx;
            float f47 = aVar.f25a;
            float f48 = f21;
            float f49 = aVar.f26b;
            float f50 = aVar.f27c;
            fArr[i2] = (f49 * f36) + (f47 * f35) + f50;
            float f51 = aVar.f28d;
            float f52 = aVar.f29e;
            float f53 = aVar.f30f;
            fArr[i2 + 1] = (f36 * f52) + (f35 * f51) + f53;
            float f54 = this.colorPacked;
            fArr[i2 + 2] = f54;
            fArr[i2 + 3] = f46;
            fArr[i2 + 4] = f44;
            fArr[i2 + 5] = a.a.B(f49, f38, f47 * f37, f50);
            fArr[i2 + 6] = a.a.B(f52, f38, f37 * f51, f53);
            fArr[i2 + 7] = f54;
            fArr[i2 + 8] = f45;
            fArr[i2 + 9] = f44;
            fArr[i2 + 10] = a.a.B(f49, f40, f47 * f39, f50);
            fArr[i2 + 11] = a.a.B(f52, f40, f51 * f39, f53);
            fArr[i2 + 12] = f54;
            fArr[i2 + 13] = f45;
            fArr[i2 + 14] = f48;
            fArr[i2 + 15] = a.a.B(f49, f42, f47 * f41, f50);
            fArr[i2 + 16] = a.a.B(f52, f42, f51 * f41, f53);
            fArr[i2 + 17] = f54;
            fArr[i2 + 18] = f46;
            fArr[i2 + 19] = f43;
            this.idx = i2 + 20;
            return;
        }
        throw new IllegalStateException("CpuSpriteBatch.begin must be called before draw.");
    }

    @Override // com.badlogic.gdx.graphics.g2d.SpriteBatch, com.badlogic.gdx.graphics.g2d.Batch
    public void draw(Texture texture, float f2, float f3, int i2, int i3, int i4, int i5) {
        if (!this.adjustNeeded) {
            super.draw(texture, f2, f3, i2, i3, i4, i5);
        } else {
            drawAdjusted(texture, f2, f3, 0.0f, 0.0f, i4, i5, 1.0f, 1.0f, 0.0f, i2, i3, i4, i5, false, false);
        }
    }

    @Override // com.badlogic.gdx.graphics.g2d.SpriteBatch, com.badlogic.gdx.graphics.g2d.Batch
    public void draw(Texture texture, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9) {
        if (!this.adjustNeeded) {
            super.draw(texture, f2, f3, f4, f5, f6, f7, f8, f9);
        } else {
            drawAdjustedUV(texture, f2, f3, 0.0f, 0.0f, f4, f5, 1.0f, 1.0f, 0.0f, f6, f7, f8, f9, false, false);
        }
    }

    @Override // com.badlogic.gdx.graphics.g2d.SpriteBatch, com.badlogic.gdx.graphics.g2d.Batch
    public void draw(Texture texture, float f2, float f3) {
        if (!this.adjustNeeded) {
            super.draw(texture, f2, f3);
        } else {
            drawAdjusted(texture, f2, f3, 0.0f, 0.0f, texture.getWidth(), texture.getHeight(), 1.0f, 1.0f, 0.0f, 0, 1, 1, 0, false, false);
        }
    }

    public void setTransformMatrix(a aVar) {
        Matrix4 transformMatrix = super.getTransformMatrix();
        if (checkEqual(transformMatrix, aVar)) {
            this.adjustNeeded = false;
            return;
        }
        this.virtualMatrix.p(aVar);
        if (isDrawing()) {
            this.adjustNeeded = true;
            if (this.haveIdentityRealMatrix) {
                a aVar2 = this.adjustAffine;
                aVar2.getClass();
                aVar2.f25a = aVar.f25a;
                aVar2.f26b = aVar.f26b;
                aVar2.f27c = aVar.f27c;
                aVar2.f28d = aVar.f28d;
                aVar2.f29e = aVar.f29e;
                aVar2.f30f = aVar.f30f;
                return;
            }
            a aVar3 = this.adjustAffine;
            aVar3.c(transformMatrix);
            aVar3.a();
            aVar3.b(aVar);
            return;
        }
        transformMatrix.p(aVar);
        this.haveIdentityRealMatrix = checkIdt(transformMatrix);
    }

    @Override // com.badlogic.gdx.graphics.g2d.SpriteBatch, com.badlogic.gdx.graphics.g2d.Batch
    public void draw(Texture texture, float f2, float f3, float f4, float f5) {
        if (!this.adjustNeeded) {
            super.draw(texture, f2, f3, f4, f5);
        } else {
            drawAdjusted(texture, f2, f3, 0.0f, 0.0f, f4, f5, 1.0f, 1.0f, 0.0f, 0, 1, 1, 0, false, false);
        }
    }

    @Override // com.badlogic.gdx.graphics.g2d.SpriteBatch, com.badlogic.gdx.graphics.g2d.Batch
    public void draw(TextureRegion textureRegion, float f2, float f3) {
        if (!this.adjustNeeded) {
            super.draw(textureRegion, f2, f3);
        } else {
            drawAdjusted(textureRegion, f2, f3, 0.0f, 0.0f, textureRegion.getRegionWidth(), textureRegion.getRegionHeight(), 1.0f, 1.0f, 0.0f);
        }
    }

    @Override // com.badlogic.gdx.graphics.g2d.SpriteBatch, com.badlogic.gdx.graphics.g2d.Batch
    public void draw(TextureRegion textureRegion, float f2, float f3, float f4, float f5) {
        if (!this.adjustNeeded) {
            super.draw(textureRegion, f2, f3, f4, f5);
        } else {
            drawAdjusted(textureRegion, f2, f3, 0.0f, 0.0f, f4, f5, 1.0f, 1.0f, 0.0f);
        }
    }

    @Override // com.badlogic.gdx.graphics.g2d.SpriteBatch, com.badlogic.gdx.graphics.g2d.Batch
    public void draw(TextureRegion textureRegion, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10) {
        if (!this.adjustNeeded) {
            super.draw(textureRegion, f2, f3, f4, f5, f6, f7, f8, f9, f10);
        } else {
            drawAdjusted(textureRegion, f2, f3, f4, f5, f6, f7, f8, f9, f10);
        }
    }

    @Override // com.badlogic.gdx.graphics.g2d.SpriteBatch, com.badlogic.gdx.graphics.g2d.Batch
    public void draw(TextureRegion textureRegion, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, boolean z2) {
        if (!this.adjustNeeded) {
            super.draw(textureRegion, f2, f3, f4, f5, f6, f7, f8, f9, f10, z2);
        } else {
            drawAdjusted(textureRegion, f2, f3, f4, f5, f6, f7, f8, f9, f10, z2);
        }
    }

    @Override // com.badlogic.gdx.graphics.g2d.SpriteBatch, com.badlogic.gdx.graphics.g2d.Batch
    public void draw(Texture texture, float[] fArr, int i2, int i3) {
        if (i3 % 20 == 0) {
            if (!this.adjustNeeded) {
                super.draw(texture, fArr, i2, i3);
                return;
            } else {
                drawAdjusted(texture, fArr, i2, i3);
                return;
            }
        }
        throw new m("invalid vertex count");
    }

    @Override // com.badlogic.gdx.graphics.g2d.SpriteBatch, com.badlogic.gdx.graphics.g2d.Batch
    public void draw(TextureRegion textureRegion, float f2, float f3, a aVar) {
        if (!this.adjustNeeded) {
            super.draw(textureRegion, f2, f3, aVar);
        } else {
            drawAdjusted(textureRegion, f2, f3, aVar);
        }
    }

    private void drawAdjusted(TextureRegion textureRegion, float f2, float f3, a aVar) {
        if (this.drawing) {
            Texture texture = textureRegion.texture;
            if (texture != this.lastTexture) {
                switchTexture(texture);
            } else if (this.idx == this.vertices.length) {
                super.flush();
            }
            float f4 = aVar.f27c;
            float f5 = aVar.f30f;
            float f6 = aVar.f26b;
            float f7 = (f6 * f3) + f4;
            float f8 = aVar.f29e;
            float f9 = (f8 * f3) + f5;
            float f10 = aVar.f25a;
            float fB = a.a.B(f6, f3, f10 * f2, f4);
            float f11 = aVar.f28d;
            float fB2 = a.a.B(f8, f3, f11 * f2, f5);
            float f12 = (f10 * f2) + f4;
            float f13 = (f11 * f2) + f5;
            float f14 = textureRegion.f1698u;
            float f15 = textureRegion.v2;
            float f16 = textureRegion.u2;
            float f17 = textureRegion.f1699v;
            a aVar2 = this.adjustAffine;
            float[] fArr = this.vertices;
            int i2 = this.idx;
            float f18 = aVar2.f25a;
            float f19 = aVar2.f26b;
            float f20 = aVar2.f27c;
            fArr[i2] = (f19 * f5) + (f18 * f4) + f20;
            float f21 = aVar2.f28d;
            float f22 = aVar2.f29e;
            float f23 = (f5 * f22) + (f4 * f21);
            float f24 = aVar2.f30f;
            fArr[i2 + 1] = f23 + f24;
            float f25 = this.colorPacked;
            fArr[i2 + 2] = f25;
            fArr[i2 + 3] = f14;
            fArr[i2 + 4] = f15;
            fArr[i2 + 5] = a.a.B(f19, f9, f18 * f7, f20);
            fArr[i2 + 6] = a.a.B(f22, f9, f7 * f21, f24);
            fArr[i2 + 7] = f25;
            fArr[i2 + 8] = f14;
            fArr[i2 + 9] = f17;
            fArr[i2 + 10] = a.a.B(f19, fB2, f18 * fB, f20);
            fArr[i2 + 11] = a.a.B(f22, fB2, f21 * fB, f24);
            fArr[i2 + 12] = f25;
            fArr[i2 + 13] = f16;
            fArr[i2 + 14] = f17;
            fArr[i2 + 15] = a.a.B(f19, f13, f18 * f12, f20);
            fArr[i2 + 16] = a.a.B(f22, f13, f21 * f12, f24);
            fArr[i2 + 17] = f25;
            fArr[i2 + 18] = f16;
            fArr[i2 + 19] = f15;
            this.idx = i2 + 20;
            return;
        }
        throw new IllegalStateException("CpuSpriteBatch.begin must be called before draw.");
    }

    private void drawAdjusted(Texture texture, float[] fArr, int i2, int i3) {
        if (this.drawing) {
            if (texture != this.lastTexture) {
                switchTexture(texture);
            }
            a aVar = this.adjustAffine;
            int iMin = Math.min(this.vertices.length - this.idx, i3);
            do {
                i3 -= iMin;
                while (iMin > 0) {
                    float f2 = fArr[i2];
                    float f3 = fArr[i2 + 1];
                    float[] fArr2 = this.vertices;
                    int i4 = this.idx;
                    fArr2[i4] = (aVar.f26b * f3) + (aVar.f25a * f2) + aVar.f27c;
                    fArr2[i4 + 1] = (aVar.f29e * f3) + (aVar.f28d * f2) + aVar.f30f;
                    fArr2[i4 + 2] = fArr[i2 + 2];
                    fArr2[i4 + 3] = fArr[i2 + 3];
                    fArr2[i4 + 4] = fArr[i2 + 4];
                    this.idx = i4 + 5;
                    i2 += 5;
                    iMin -= 5;
                }
                if (i3 > 0) {
                    super.flush();
                    iMin = Math.min(this.vertices.length, i3);
                }
            } while (i3 > 0);
            return;
        }
        throw new IllegalStateException("CpuSpriteBatch.begin must be called before draw.");
    }
}
