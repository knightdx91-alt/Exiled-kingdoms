package com.badlogic.gdx.graphics.g3d.environment;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.a;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class PointLight extends BaseLight<PointLight> {
    public float intensity;
    public final a position = new a();

    public boolean equals(Object obj) {
        return (obj instanceof PointLight) && equals((PointLight) obj);
    }

    public PointLight set(PointLight pointLight) {
        return set(pointLight.color, pointLight.position, pointLight.intensity);
    }

    public PointLight setIntensity(float f2) {
        this.intensity = f2;
        return this;
    }

    public PointLight setPosition(float f2, float f3, float f4) {
        this.position.t(f2, f3, f4);
        return this;
    }

    public boolean equals(PointLight pointLight) {
        return pointLight != null && (pointLight == this || (this.color.equals(pointLight.color) && this.position.equals(pointLight.position) && this.intensity == pointLight.intensity));
    }

    public PointLight set(Color color, a aVar, float f2) {
        if (color != null) {
            this.color.set(color);
        }
        if (aVar != null) {
            this.position.u(aVar);
        }
        this.intensity = f2;
        return this;
    }

    public PointLight setPosition(a aVar) {
        this.position.u(aVar);
        return this;
    }

    public PointLight set(float f2, float f3, float f4, a aVar, float f5) {
        this.color.set(f2, f3, f4, 1.0f);
        if (aVar != null) {
            this.position.u(aVar);
        }
        this.intensity = f5;
        return this;
    }

    public PointLight set(Color color, float f2, float f3, float f4, float f5) {
        if (color != null) {
            this.color.set(color);
        }
        this.position.t(f2, f3, f4);
        this.intensity = f5;
        return this;
    }

    public PointLight set(float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
        this.color.set(f2, f3, f4, 1.0f);
        this.position.t(f5, f6, f7);
        this.intensity = f8;
        return this;
    }
}
