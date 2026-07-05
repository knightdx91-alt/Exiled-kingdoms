package com.badlogic.gdx.graphics.g3d.particles.values;

import a.a;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.t;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class GradientColorValue extends ParticleValue {
    private static float[] temp = new float[3];
    private float[] colors = {1.0f, 1.0f, 1.0f};
    public float[] timeline = {0.0f};

    public float[] getColor(float f2) {
        getColor(f2, temp, 0);
        return temp;
    }

    public float[] getColors() {
        return this.colors;
    }

    public float[] getTimeline() {
        return this.timeline;
    }

    public void load(GradientColorValue gradientColorValue) {
        super.load((ParticleValue) gradientColorValue);
        float[] fArr = new float[gradientColorValue.colors.length];
        this.colors = fArr;
        System.arraycopy(gradientColorValue.colors, 0, fArr, 0, fArr.length);
        float[] fArr2 = new float[gradientColorValue.timeline.length];
        this.timeline = fArr2;
        System.arraycopy(gradientColorValue.timeline, 0, fArr2, 0, fArr2.length);
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.values.ParticleValue, com.badlogic.gdx.utils.Json.b
    public void read(Json json, t tVar) {
        super.read(json, tVar);
        this.colors = (float[]) json.readValue("colors", float[].class, tVar);
        this.timeline = (float[]) json.readValue("timeline", float[].class, tVar);
    }

    public void setColors(float[] fArr) {
        this.colors = fArr;
    }

    public void setTimeline(float[] fArr) {
        this.timeline = fArr;
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.values.ParticleValue, com.badlogic.gdx.utils.Json.b
    public void write(Json json) {
        super.write(json);
        json.writeValue("colors", this.colors);
        json.writeValue("timeline", this.timeline);
    }

    public void getColor(float f2, float[] fArr, int i2) {
        int i3;
        float[] fArr2 = this.timeline;
        int length = fArr2.length;
        int i4 = 0;
        int i5 = 1;
        while (true) {
            int i6 = i5;
            i3 = i4;
            i4 = i6;
            if (i4 >= length) {
                i4 = -1;
                break;
            } else if (fArr2[i4] > f2) {
                break;
            } else {
                i5 = i4 + 1;
            }
        }
        float f3 = fArr2[i3];
        int i7 = i3 * 3;
        float[] fArr3 = this.colors;
        float f4 = fArr3[i7];
        float f5 = fArr3[i7 + 1];
        float f6 = fArr3[i7 + 2];
        if (i4 == -1) {
            fArr[i2] = f4;
            fArr[i2 + 1] = f5;
            fArr[i2 + 2] = f6;
        } else {
            float f7 = (f2 - f3) / (fArr2[i4] - f3);
            int i8 = i4 * 3;
            fArr[i2] = a.C(fArr3[i8], f4, f7, f4);
            fArr[i2 + 1] = a.C(fArr3[i8 + 1], f5, f7, f5);
            fArr[i2 + 2] = a.C(fArr3[i8 + 2], f6, f7, f6);
        }
    }
}
