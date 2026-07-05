package com.badlogic.gdx.graphics;

import a0.q;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.a;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class OrthographicCamera extends Camera {
    public float zoom = 1.0f;
    private final a tmp = new a();

    public OrthographicCamera() {
        this.near = 0.0f;
    }

    public void rotate(float f2) {
        rotate(this.direction, f2);
    }

    public void setToOrtho(boolean z2) {
        setToOrtho(z2, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    public void translate(float f2, float f3) {
        translate(f2, f3, 0.0f);
    }

    @Override // com.badlogic.gdx.graphics.Camera
    public void update() {
        update(true);
    }

    public void setToOrtho(boolean z2, float f2, float f3) {
        if (z2) {
            this.up.t(0.0f, -1.0f, 0.0f);
            this.direction.t(0.0f, 0.0f, 1.0f);
        } else {
            this.up.t(0.0f, 1.0f, 0.0f);
            this.direction.t(0.0f, 0.0f, -1.0f);
        }
        a aVar = this.position;
        float f4 = this.zoom;
        aVar.t((f4 * f2) / 2.0f, (f4 * f3) / 2.0f, 0.0f);
        this.viewportWidth = f2;
        this.viewportHeight = f3;
        update();
    }

    public void translate(q qVar) {
        translate(qVar.f91a, qVar.f92b, 0.0f);
    }

    @Override // com.badlogic.gdx.graphics.Camera
    public void update(boolean z2) {
        Matrix4 matrix4 = this.projection;
        float f2 = this.zoom;
        float f3 = this.viewportWidth;
        float f4 = this.viewportHeight;
        matrix4.s(((-f3) * f2) / 2.0f, (f3 / 2.0f) * f2, (-(f4 / 2.0f)) * f2, (f2 * f4) / 2.0f, this.near, this.far);
        Matrix4 matrix42 = this.view;
        a aVar = this.position;
        a aVar2 = this.tmp;
        aVar2.u(aVar);
        aVar2.b(this.direction);
        matrix42.r(aVar, aVar2, this.up);
        this.combined.o(this.projection);
        Matrix4.h(this.combined.f1724a, this.view.f1724a);
        if (z2) {
            this.invProjectionView.o(this.combined);
            Matrix4.f(this.invProjectionView.f1724a);
            this.frustum.a(this.invProjectionView);
        }
    }

    public OrthographicCamera(float f2, float f3) {
        this.viewportWidth = f2;
        this.viewportHeight = f3;
        this.near = 0.0f;
        update();
    }
}
