package com.badlogic.gdx.graphics.g3d.particles.values;

import a0.j;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.t;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class ScaledNumericValue extends RangedNumericValue {
    private float highMax;
    private float highMin;
    private float[] scaling = {1.0f};
    public float[] timeline = {0.0f};
    private boolean relative = false;

    public float getHighMax() {
        return this.highMax;
    }

    public float getHighMin() {
        return this.highMin;
    }

    public float getScale(float f2) {
        int length = this.timeline.length;
        int i2 = 1;
        while (true) {
            if (i2 >= length) {
                i2 = -1;
                break;
            }
            if (this.timeline[i2] > f2) {
                break;
            }
            i2++;
        }
        if (i2 == -1) {
            return this.scaling[length - 1];
        }
        int i3 = i2 - 1;
        float[] fArr = this.scaling;
        float f3 = fArr[i3];
        float[] fArr2 = this.timeline;
        float f4 = fArr2[i3];
        return (((f2 - f4) / (fArr2[i2] - f4)) * (fArr[i2] - f3)) + f3;
    }

    public float[] getScaling() {
        return this.scaling;
    }

    public float[] getTimeline() {
        return this.timeline;
    }

    public boolean isRelative() {
        return this.relative;
    }

    public void load(ScaledNumericValue scaledNumericValue) {
        super.load((RangedNumericValue) scaledNumericValue);
        this.highMax = scaledNumericValue.highMax;
        this.highMin = scaledNumericValue.highMin;
        float[] fArr = new float[scaledNumericValue.scaling.length];
        this.scaling = fArr;
        System.arraycopy(scaledNumericValue.scaling, 0, fArr, 0, fArr.length);
        float[] fArr2 = new float[scaledNumericValue.timeline.length];
        this.timeline = fArr2;
        System.arraycopy(scaledNumericValue.timeline, 0, fArr2, 0, fArr2.length);
        this.relative = scaledNumericValue.relative;
    }

    public float newHighValue() {
        float f2 = this.highMin;
        return (j.f69a.nextFloat() * (this.highMax - f2)) + f2;
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.values.RangedNumericValue, com.badlogic.gdx.graphics.g3d.particles.values.ParticleValue, com.badlogic.gdx.utils.Json.b
    public void read(Json json, t tVar) {
        super.read(json, tVar);
        Class cls = Float.TYPE;
        this.highMin = ((Float) json.readValue("highMin", cls, tVar)).floatValue();
        this.highMax = ((Float) json.readValue("highMax", cls, tVar)).floatValue();
        this.relative = ((Boolean) json.readValue("relative", Boolean.TYPE, tVar)).booleanValue();
        this.scaling = (float[]) json.readValue("scaling", float[].class, tVar);
        this.timeline = (float[]) json.readValue("timeline", float[].class, tVar);
    }

    public void setHigh(float f2) {
        this.highMin = f2;
        this.highMax = f2;
    }

    public void setHighMax(float f2) {
        this.highMax = f2;
    }

    public void setHighMin(float f2) {
        this.highMin = f2;
    }

    public void setRelative(boolean z2) {
        this.relative = z2;
    }

    public void setScaling(float[] fArr) {
        this.scaling = fArr;
    }

    public void setTimeline(float[] fArr) {
        this.timeline = fArr;
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.values.RangedNumericValue, com.badlogic.gdx.graphics.g3d.particles.values.ParticleValue, com.badlogic.gdx.utils.Json.b
    public void write(Json json) {
        super.write(json);
        json.writeValue("highMin", Float.valueOf(this.highMin));
        json.writeValue("highMax", Float.valueOf(this.highMax));
        json.writeValue("relative", Boolean.valueOf(this.relative));
        json.writeValue("scaling", this.scaling);
        json.writeValue("timeline", this.timeline);
    }

    public void setHigh(float f2, float f3) {
        this.highMin = f2;
        this.highMax = f3;
    }
}
