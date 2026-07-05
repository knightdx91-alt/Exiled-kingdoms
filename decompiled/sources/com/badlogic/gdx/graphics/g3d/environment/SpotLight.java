package com.badlogic.gdx.graphics.g3d.environment;

import a0.j;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.a;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class SpotLight extends BaseLight<SpotLight> {
    public float cutoffAngle;
    public float exponent;
    public float intensity;
    public final a position = new a();
    public final a direction = new a();

    public boolean equals(Object obj) {
        return (obj instanceof SpotLight) && equals((SpotLight) obj);
    }

    public SpotLight set(SpotLight spotLight) {
        return set(spotLight.color, spotLight.position, spotLight.direction, spotLight.intensity, spotLight.cutoffAngle, spotLight.exponent);
    }

    public SpotLight setCutoffAngle(float f2) {
        this.cutoffAngle = f2;
        return this;
    }

    public SpotLight setDirection(float f2, float f3, float f4) {
        this.direction.t(f2, f3, f4);
        return this;
    }

    public SpotLight setExponent(float f2) {
        this.exponent = f2;
        return this;
    }

    public SpotLight setIntensity(float f2) {
        this.intensity = f2;
        return this;
    }

    public SpotLight setPosition(float f2, float f3, float f4) {
        this.position.t(f2, f3, f4);
        return this;
    }

    public SpotLight setTarget(a aVar) {
        a aVar2 = this.direction;
        aVar2.u(aVar);
        aVar2.w(this.position);
        aVar2.n();
        return this;
    }

    public boolean equals(SpotLight spotLight) {
        return spotLight != null && (spotLight == this || (this.color.equals(spotLight.color) && this.position.equals(spotLight.position) && this.direction.equals(spotLight.direction) && j.d(this.intensity, spotLight.intensity) && j.d(this.cutoffAngle, spotLight.cutoffAngle) && j.d(this.exponent, spotLight.exponent)));
    }

    public SpotLight set(Color color, a aVar, a aVar2, float f2, float f3, float f4) {
        if (color != null) {
            this.color.set(color);
        }
        if (aVar != null) {
            this.position.u(aVar);
        }
        if (aVar2 != null) {
            a aVar3 = this.direction;
            aVar3.getClass();
            aVar3.t(aVar2.f1729a, aVar2.f1730b, aVar2.f1731c);
            aVar3.n();
        }
        this.intensity = f2;
        this.cutoffAngle = f3;
        this.exponent = f4;
        return this;
    }

    public SpotLight setDirection(a aVar) {
        this.direction.u(aVar);
        return this;
    }

    public SpotLight setPosition(a aVar) {
        this.position.u(aVar);
        return this;
    }

    public SpotLight set(float f2, float f3, float f4, a aVar, a aVar2, float f5, float f6, float f7) {
        this.color.set(f2, f3, f4, 1.0f);
        if (aVar != null) {
            this.position.u(aVar);
        }
        if (aVar2 != null) {
            a aVar3 = this.direction;
            aVar3.getClass();
            aVar3.t(aVar2.f1729a, aVar2.f1730b, aVar2.f1731c);
            aVar3.n();
        }
        this.intensity = f5;
        this.cutoffAngle = f6;
        this.exponent = f7;
        return this;
    }

    public SpotLight set(Color color, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10) {
        if (color != null) {
            this.color.set(color);
        }
        this.position.t(f2, f3, f4);
        a aVar = this.direction;
        aVar.t(f5, f6, f7);
        aVar.n();
        this.intensity = f8;
        this.cutoffAngle = f9;
        this.exponent = f10;
        return this;
    }

    public SpotLight set(float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13) {
        this.color.set(f2, f3, f4, 1.0f);
        this.position.t(f5, f6, f7);
        a aVar = this.direction;
        aVar.t(f8, f9, f10);
        aVar.n();
        this.intensity = f11;
        this.cutoffAngle = f12;
        this.exponent = f13;
        return this;
    }
}
