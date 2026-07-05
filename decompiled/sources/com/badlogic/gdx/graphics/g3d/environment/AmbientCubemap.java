package com.badlogic.gdx.graphics.g3d.environment;

import a.a;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.m;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class AmbientCubemap {
    private static final int NUM_VALUES = 18;
    public final float[] data;

    public AmbientCubemap() {
        this.data = new float[18];
    }

    private static final float clamp(float f2) {
        float f3 = 0.0f;
        if (f2 >= 0.0f) {
            f3 = 1.0f;
            if (f2 <= 1.0f) {
                return f2;
            }
        }
        return f3;
    }

    public AmbientCubemap add(float f2, float f3, float f4) {
        int i2 = 0;
        while (true) {
            float[] fArr = this.data;
            if (i2 >= fArr.length) {
                return this;
            }
            int i3 = i2 + 1;
            fArr[i2] = fArr[i2] + f2;
            int i4 = i2 + 2;
            fArr[i3] = fArr[i3] + f3;
            i2 += 3;
            fArr[i4] = fArr[i4] + f4;
        }
    }

    public AmbientCubemap clear() {
        int i2 = 0;
        while (true) {
            float[] fArr = this.data;
            if (i2 >= fArr.length) {
                return this;
            }
            fArr[i2] = 0.0f;
            i2++;
        }
    }

    public Color getColor(Color color, int i2) {
        int i3 = i2 * 3;
        float[] fArr = this.data;
        return color.set(fArr[i3], fArr[i3 + 1], fArr[i3 + 2], 1.0f);
    }

    public AmbientCubemap set(float[] fArr) {
        int i2 = 0;
        while (true) {
            float[] fArr2 = this.data;
            if (i2 >= fArr2.length) {
                return this;
            }
            fArr2[i2] = fArr[i2];
            i2++;
        }
    }

    public String toString() {
        String string = "";
        for (int i2 = 0; i2 < this.data.length; i2 += 3) {
            StringBuilder sbS = a.s(string);
            sbS.append(Float.toString(this.data[i2]));
            sbS.append(", ");
            sbS.append(Float.toString(this.data[i2 + 1]));
            sbS.append(", ");
            sbS.append(Float.toString(this.data[i2 + 2]));
            sbS.append("\n");
            string = sbS.toString();
        }
        return string;
    }

    public AmbientCubemap clamp() {
        int i2 = 0;
        while (true) {
            float[] fArr = this.data;
            if (i2 >= fArr.length) {
                return this;
            }
            fArr[i2] = clamp(fArr[i2]);
            i2++;
        }
    }

    public AmbientCubemap(float[] fArr) {
        if (fArr.length == 18) {
            float[] fArr2 = new float[fArr.length];
            this.data = fArr2;
            System.arraycopy(fArr, 0, fArr2, 0, fArr2.length);
            return;
        }
        throw new m("Incorrect array size");
    }

    public AmbientCubemap set(AmbientCubemap ambientCubemap) {
        return set(ambientCubemap.data);
    }

    public AmbientCubemap set(Color color) {
        return set(color.f1680r, color.f1679g, color.f1678b);
    }

    public AmbientCubemap add(Color color) {
        return add(color.f1680r, color.f1679g, color.f1678b);
    }

    public AmbientCubemap set(float f2, float f3, float f4) {
        for (int i2 = 0; i2 < 18; i2 += 3) {
            float[] fArr = this.data;
            fArr[i2] = f2;
            fArr[i2 + 1] = f3;
            fArr[i2 + 2] = f4;
        }
        return this;
    }

    public AmbientCubemap add(float f2, float f3, float f4, float f5, float f6, float f7) {
        float f8 = f5 * f5;
        float f9 = f6 * f6;
        float f10 = f7 * f7;
        float f11 = f8 + f9 + f10;
        if (f11 == 0.0f) {
            return this;
        }
        float f12 = (f11 + 1.0f) * (1.0f / f11);
        float f13 = f2 * f12;
        float f14 = f3 * f12;
        float f15 = f4 * f12;
        int i2 = f5 > 0.0f ? 0 : 3;
        float[] fArr = this.data;
        fArr[i2] = (f8 * f13) + fArr[i2];
        int i3 = i2 + 1;
        fArr[i3] = (f8 * f14) + fArr[i3];
        int i4 = i2 + 2;
        fArr[i4] = (f8 * f15) + fArr[i4];
        int i5 = f6 > 0.0f ? 6 : 9;
        fArr[i5] = (f9 * f13) + fArr[i5];
        int i6 = i5 + 1;
        fArr[i6] = (f9 * f14) + fArr[i6];
        int i7 = i5 + 2;
        fArr[i7] = (f9 * f15) + fArr[i7];
        int i8 = f7 > 0.0f ? 12 : 15;
        fArr[i8] = (f13 * f10) + fArr[i8];
        int i9 = i8 + 1;
        fArr[i9] = (f14 * f10) + fArr[i9];
        int i10 = i8 + 2;
        fArr[i10] = (f10 * f15) + fArr[i10];
        return this;
    }

    public AmbientCubemap(AmbientCubemap ambientCubemap) {
        this(ambientCubemap.data);
    }

    public AmbientCubemap add(Color color, com.badlogic.gdx.math.a aVar) {
        return add(color.f1680r, color.f1679g, color.f1678b, aVar.f1729a, aVar.f1730b, aVar.f1731c);
    }

    public AmbientCubemap add(float f2, float f3, float f4, com.badlogic.gdx.math.a aVar) {
        return add(f2, f3, f4, aVar.f1729a, aVar.f1730b, aVar.f1731c);
    }

    public AmbientCubemap add(Color color, float f2, float f3, float f4) {
        return add(color.f1680r, color.f1679g, color.f1678b, f2, f3, f4);
    }

    public AmbientCubemap add(Color color, com.badlogic.gdx.math.a aVar, com.badlogic.gdx.math.a aVar2) {
        return add(color.f1680r, color.f1679g, color.f1678b, aVar2.f1729a - aVar.f1729a, aVar2.f1730b - aVar.f1730b, aVar2.f1731c - aVar.f1731c);
    }

    public AmbientCubemap add(Color color, com.badlogic.gdx.math.a aVar, com.badlogic.gdx.math.a aVar2, float f2) {
        float fE = f2 / (aVar2.e(aVar) + 1.0f);
        return add(color.f1680r * fE, color.f1679g * fE, color.f1678b * fE, aVar2.f1729a - aVar.f1729a, aVar2.f1730b - aVar.f1730b, aVar2.f1731c - aVar.f1731c);
    }
}
