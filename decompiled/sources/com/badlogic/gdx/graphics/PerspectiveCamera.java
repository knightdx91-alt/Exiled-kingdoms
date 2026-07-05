package com.badlogic.gdx.graphics;

import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.a;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class PerspectiveCamera extends Camera {
    public float fieldOfView;
    final a tmp;

    public PerspectiveCamera() {
        this.fieldOfView = 67.0f;
        this.tmp = new a();
    }

    @Override // com.badlogic.gdx.graphics.Camera
    public void update() {
        update(true);
    }

    @Override // com.badlogic.gdx.graphics.Camera
    public void update(boolean z2) {
        float f2 = this.viewportWidth / this.viewportHeight;
        Matrix4 matrix4 = this.projection;
        float fAbs = Math.abs(this.near);
        float fAbs2 = Math.abs(this.far);
        float f3 = this.fieldOfView;
        matrix4.d();
        float fTan = (float) (1.0d / Math.tan((((double) f3) * 0.017453292519943295d) / 2.0d));
        float f4 = fAbs - fAbs2;
        float[] fArr = matrix4.f1724a;
        fArr[0] = fTan / f2;
        fArr[1] = 0.0f;
        fArr[2] = 0.0f;
        fArr[3] = 0.0f;
        fArr[4] = 0.0f;
        fArr[5] = fTan;
        fArr[6] = 0.0f;
        fArr[7] = 0.0f;
        fArr[8] = 0.0f;
        fArr[9] = 0.0f;
        fArr[10] = (fAbs2 + fAbs) / f4;
        fArr[11] = -1.0f;
        fArr[12] = 0.0f;
        fArr[13] = 0.0f;
        fArr[14] = ((fAbs2 * 2.0f) * fAbs) / f4;
        fArr[15] = 0.0f;
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

    public PerspectiveCamera(float f2, float f3, float f4) {
        this.fieldOfView = 67.0f;
        this.tmp = new a();
        this.fieldOfView = f2;
        this.viewportWidth = f3;
        this.viewportHeight = f4;
        update();
    }
}
