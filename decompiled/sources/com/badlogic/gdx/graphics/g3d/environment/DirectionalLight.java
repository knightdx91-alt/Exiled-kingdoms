package com.badlogic.gdx.graphics.g3d.environment;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.a;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class DirectionalLight extends BaseLight<DirectionalLight> {
    public final a direction = new a();

    public boolean equals(Object obj) {
        return (obj instanceof DirectionalLight) && equals((DirectionalLight) obj);
    }

    public DirectionalLight set(DirectionalLight directionalLight) {
        return set(directionalLight.color, directionalLight.direction);
    }

    public DirectionalLight setDirection(float f2, float f3, float f4) {
        this.direction.t(f2, f3, f4);
        return this;
    }

    public boolean equals(DirectionalLight directionalLight) {
        return directionalLight != null && (directionalLight == this || (this.color.equals(directionalLight.color) && this.direction.equals(directionalLight.direction)));
    }

    public DirectionalLight set(Color color, a aVar) {
        if (color != null) {
            this.color.set(color);
        }
        if (aVar != null) {
            a aVar2 = this.direction;
            aVar2.getClass();
            aVar2.t(aVar.f1729a, aVar.f1730b, aVar.f1731c);
            aVar2.n();
        }
        return this;
    }

    public DirectionalLight setDirection(a aVar) {
        this.direction.u(aVar);
        return this;
    }

    public DirectionalLight set(float f2, float f3, float f4, a aVar) {
        this.color.set(f2, f3, f4, 1.0f);
        if (aVar != null) {
            a aVar2 = this.direction;
            aVar2.getClass();
            aVar2.t(aVar.f1729a, aVar.f1730b, aVar.f1731c);
            aVar2.n();
        }
        return this;
    }

    public DirectionalLight set(Color color, float f2, float f3, float f4) {
        if (color != null) {
            this.color.set(color);
        }
        a aVar = this.direction;
        aVar.t(f2, f3, f4);
        aVar.n();
        return this;
    }

    public DirectionalLight set(float f2, float f3, float f4, float f5, float f6, float f7) {
        this.color.set(f2, f3, f4, 1.0f);
        a aVar = this.direction;
        aVar.t(f5, f6, f7);
        aVar.n();
        return this;
    }
}
