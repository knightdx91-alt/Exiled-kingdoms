package com.badlogic.gdx.graphics.g2d;

import a0.d;
import a0.i;
import a0.m;
import a0.p;
import a0.q;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.a;
import com.badlogic.gdx.utils.j0;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class RepeatablePolygonSprite {
    private int cols;
    private float density;
    private float gridHeight;
    private float gridWidth;
    private TextureRegion region;
    private int rows;
    private boolean dirty = true;
    private a<float[]> parts = new a<>();
    private a<float[]> vertices = new a<>();
    private a<short[]> indices = new a<>();

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public float f1694x = 0.0f;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public float f1695y = 0.0f;
    private Color color = Color.WHITE;
    private q offset = new q();

    private void buildVertices() {
        this.vertices.clear();
        int i2 = 0;
        while (true) {
            a<float[]> aVar = this.parts;
            if (i2 >= aVar.f1750b) {
                this.dirty = false;
                return;
            }
            float[] fArr = aVar.get(i2);
            if (fArr != null) {
                float[] fArr2 = new float[(fArr.length * 5) / 2];
                int i3 = this.rows;
                int i4 = i2 / i3;
                int i5 = i2 % i3;
                int i6 = 0;
                for (int i7 = 0; i7 < fArr.length; i7 += 2) {
                    float f2 = fArr[i7];
                    q qVar = this.offset;
                    fArr2[i6] = f2 + qVar.f91a + this.f1694x;
                    int i8 = i7 + 1;
                    fArr2[i6 + 1] = fArr[i8] + qVar.f92b + this.f1695y;
                    int i9 = i6 + 3;
                    fArr2[i6 + 2] = this.color.toFloatBits();
                    float f3 = fArr[i7];
                    float f4 = this.gridWidth;
                    float f5 = (f3 % f4) / f4;
                    float f6 = fArr[i8];
                    float f7 = this.gridHeight;
                    float f8 = (f6 % f7) / f7;
                    if (f3 == i4 * f4) {
                        f5 = 0.0f;
                    }
                    float f9 = 1.0f;
                    if (f3 == (i4 + 1) * f4) {
                        f5 = 1.0f;
                    }
                    if (f6 == i5 * f7) {
                        f8 = 0.0f;
                    }
                    if (f6 != (i5 + 1) * f7) {
                        f9 = f8;
                    }
                    float u2 = ((this.region.getU2() - this.region.getU()) * f5) + this.region.getU();
                    float v2 = ((this.region.getV2() - this.region.getV()) * f9) + this.region.getV();
                    int i10 = i6 + 4;
                    fArr2[i9] = u2;
                    i6 += 5;
                    fArr2[i10] = v2;
                }
                this.vertices.a(fArr2);
            }
            i2++;
        }
    }

    private float[] offset(float[] fArr) {
        q qVar = this.offset;
        float f2 = fArr[0];
        float f3 = fArr[1];
        qVar.f91a = f2;
        qVar.f92b = f3;
        for (int i2 = 0; i2 < fArr.length - 1; i2 += 2) {
            q qVar2 = this.offset;
            float f4 = qVar2.f91a;
            float f5 = fArr[i2];
            if (f4 > f5) {
                qVar2.f91a = f5;
            }
            float f6 = qVar2.f92b;
            float f7 = fArr[i2 + 1];
            if (f6 > f7) {
                qVar2.f92b = f7;
            }
        }
        for (int i3 = 0; i3 < fArr.length; i3 += 2) {
            float f8 = fArr[i3];
            q qVar3 = this.offset;
            fArr[i3] = f8 - qVar3.f91a;
            int i4 = i3 + 1;
            fArr[i4] = fArr[i4] - qVar3.f92b;
        }
        return fArr;
    }

    private float[] snapToGrid(float[] fArr) {
        for (int i2 = 0; i2 < fArr.length; i2 += 2) {
            float f2 = fArr[i2];
            float f3 = this.gridWidth;
            float f4 = (f2 / f3) % 1.0f;
            int i3 = i2 + 1;
            float f5 = (fArr[i3] / this.gridHeight) % 1.0f;
            if (f4 > 0.99f || f4 < 0.01f) {
                fArr[i2] = f3 * Math.round(f2 / f3);
            }
            if (f5 > 0.99f || f5 < 0.01f) {
                fArr[i3] = this.gridHeight * Math.round(fArr[i3] / r1);
            }
        }
        return fArr;
    }

    public void draw(PolygonSpriteBatch polygonSpriteBatch) {
        if (this.dirty) {
            buildVertices();
        }
        for (int i2 = 0; i2 < this.vertices.f1750b; i2++) {
            polygonSpriteBatch.draw(this.region.getTexture(), this.vertices.get(i2), 0, this.vertices.get(i2).length, this.indices.get(i2), 0, this.indices.get(i2).length);
        }
    }

    public void setColor(Color color) {
        this.color = color;
        this.dirty = true;
    }

    public void setPolygon(TextureRegion textureRegion, float[] fArr) {
        setPolygon(textureRegion, fArr, -1.0f);
    }

    public void setPosition(float f2, float f3) {
        this.f1694x = f2;
        this.f1695y = f3;
        this.dirty = true;
    }

    public void setPolygon(TextureRegion textureRegion, float[] fArr, float f2) {
        this.region = textureRegion;
        m mVar = new m(offset(fArr));
        m mVar2 = new m();
        m mVar3 = new m();
        d dVar = new d();
        p pVarA = mVar.a();
        if (f2 == -1.0f) {
            f2 = pVarA.getWidth() / textureRegion.getRegionWidth();
        }
        float regionHeight = textureRegion.getRegionHeight() / textureRegion.getRegionWidth();
        this.cols = (int) Math.ceil(f2);
        float width = pVarA.getWidth() / f2;
        this.gridWidth = width;
        this.gridHeight = regionHeight * width;
        this.rows = (int) Math.ceil(pVarA.getHeight() / this.gridHeight);
        for (int i2 = 0; i2 < this.cols; i2++) {
            int i3 = 0;
            while (i3 < this.rows) {
                float f3 = this.gridWidth;
                float f4 = i2 * f3;
                float f5 = this.gridHeight;
                float f6 = i3 * f5;
                i3++;
                float f7 = i3 * f5;
                float f8 = (i2 + 1) * f3;
                mVar2.e(new float[]{f4, f6, f4, f7, f8, f7, f8, f6});
                i.b(mVar, mVar2, mVar3);
                float[] fArrC = mVar3.c();
                if (fArrC.length > 0) {
                    this.parts.a(snapToGrid(fArrC));
                    j0 j0VarC = dVar.c(fArrC);
                    a<short[]> aVar = this.indices;
                    int i4 = j0VarC.f1827b;
                    short[] sArr = new short[i4];
                    System.arraycopy(j0VarC.f1826a, 0, sArr, 0, i4);
                    aVar.a(sArr);
                } else {
                    this.parts.a(null);
                }
            }
        }
        buildVertices();
    }
}
