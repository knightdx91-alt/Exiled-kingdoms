package com.badlogic.gdx.graphics.g3d.environment;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.m;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class SphericalHarmonics {
    private static final float[] coeff = {0.282095f, 0.488603f, 0.488603f, 0.488603f, 1.092548f, 1.092548f, 1.092548f, 0.315392f, 0.546274f};
    public final float[] data;

    public SphericalHarmonics() {
        this.data = new float[27];
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

    public SphericalHarmonics set(float[] fArr) {
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

    public SphericalHarmonics(float[] fArr) {
        if (fArr.length == 27) {
            this.data = (float[]) fArr.clone();
            return;
        }
        throw new m("Incorrect array size");
    }

    public SphericalHarmonics set(AmbientCubemap ambientCubemap) {
        return set(ambientCubemap.data);
    }

    public SphericalHarmonics set(Color color) {
        return set(color.f1680r, color.f1679g, color.f1678b);
    }

    public SphericalHarmonics set(float f2, float f3, float f4) {
        int i2 = 0;
        while (true) {
            float[] fArr = this.data;
            if (i2 >= fArr.length) {
                return this;
            }
            fArr[i2] = f2;
            int i3 = i2 + 2;
            fArr[i2 + 1] = f3;
            i2 += 3;
            fArr[i3] = f4;
        }
    }
}
