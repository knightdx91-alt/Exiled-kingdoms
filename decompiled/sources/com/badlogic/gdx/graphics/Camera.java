package com.badlogic.gdx.graphics;

import a0.f;
import a0.n;
import b0.b;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.a;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public abstract class Camera {
    public final a position = new a();
    public final a direction = new a(0.0f, 0.0f, -1.0f);
    public final a up = new a(0.0f, 1.0f, 0.0f);
    public final Matrix4 projection = new Matrix4();
    public final Matrix4 view = new Matrix4();
    public final Matrix4 combined = new Matrix4();
    public final Matrix4 invProjectionView = new Matrix4();
    public float near = 1.0f;
    public float far = 100.0f;
    public float viewportWidth = 0.0f;
    public float viewportHeight = 0.0f;
    public final f frustum = new f();
    private final a tmpVec = new a();
    private final b ray = new b(new a(), new a());

    public b getPickRay(float f2, float f3, float f4, float f5, float f6, float f7) {
        a aVar = this.ray.f1416a;
        aVar.t(f2, f3, 0.0f);
        unproject(aVar, f4, f5, f6, f7);
        a aVar2 = this.ray.f1417b;
        aVar2.t(f2, f3, 1.0f);
        unproject(aVar2, f4, f5, f6, f7);
        b bVar = this.ray;
        a aVar3 = bVar.f1417b;
        aVar3.w(bVar.f1416a);
        aVar3.n();
        return this.ray;
    }

    public void lookAt(float f2, float f3, float f4) {
        a aVar = this.tmpVec;
        aVar.t(f2, f3, f4);
        aVar.w(this.position);
        aVar.n();
        if (this.tmpVec.g()) {
            return;
        }
        a aVar2 = this.tmpVec;
        a aVar3 = this.up;
        float f5 = (aVar2.f1731c * aVar3.f1731c) + (aVar2.f1730b * aVar3.f1730b) + (aVar2.f1729a * aVar3.f1729a);
        if (Math.abs(f5 - 1.0f) < 1.0E-9f) {
            a aVar4 = this.up;
            aVar4.u(this.direction);
            aVar4.s(-1.0f);
        } else if (Math.abs(f5 + 1.0f) < 1.0E-9f) {
            this.up.u(this.direction);
        }
        this.direction.u(this.tmpVec);
        normalizeUp();
    }

    public void normalizeUp() {
        a aVar = this.tmpVec;
        aVar.u(this.direction);
        aVar.d(this.up);
        a aVar2 = this.up;
        aVar2.u(this.tmpVec);
        aVar2.d(this.direction);
        aVar2.n();
    }

    public a project(a aVar) {
        project(aVar, 0.0f, 0.0f, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        return aVar;
    }

    public void rotate(float f2, float f3, float f4, float f5) {
        this.direction.q(f2, f3, f4, f5);
        this.up.q(f2, f3, f4, f5);
    }

    public void rotateAround(a aVar, a aVar2, float f2) {
        this.tmpVec.u(aVar);
        this.tmpVec.w(this.position);
        translate(this.tmpVec);
        rotate(aVar2, f2);
        this.tmpVec.r(aVar2, f2);
        a aVar3 = this.tmpVec;
        translate(-aVar3.f1729a, -aVar3.f1730b, -aVar3.f1731c);
    }

    public void transform(Matrix4 matrix4) {
        this.position.m(matrix4);
        rotate(matrix4);
    }

    public void translate(float f2, float f3, float f4) {
        this.position.a(f2, f3, f4);
    }

    public a unproject(a aVar, float f2, float f3, float f4, float f5) {
        float f6 = aVar.f1729a - f2;
        float height = (Gdx.graphics.getHeight() - aVar.f1730b) - f3;
        aVar.f1729a = ((f6 * 2.0f) / f4) - 1.0f;
        aVar.f1730b = ((height * 2.0f) / f5) - 1.0f;
        aVar.f1731c = (aVar.f1731c * 2.0f) - 1.0f;
        aVar.o(this.invProjectionView);
        return aVar;
    }

    public abstract void update();

    public abstract void update(boolean z2);

    public a project(a aVar, float f2, float f3, float f4, float f5) {
        aVar.o(this.combined);
        aVar.f1729a = (((aVar.f1729a + 1.0f) * f4) / 2.0f) + f2;
        aVar.f1730b = (((aVar.f1730b + 1.0f) * f5) / 2.0f) + f3;
        aVar.f1731c = (aVar.f1731c + 1.0f) / 2.0f;
        return aVar;
    }

    public void translate(a aVar) {
        this.position.b(aVar);
    }

    public void rotate(a aVar, float f2) {
        this.direction.r(aVar, f2);
        this.up.r(aVar, f2);
    }

    public b getPickRay(float f2, float f3) {
        return getPickRay(f2, f3, 0.0f, 0.0f, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    public void rotate(Matrix4 matrix4) {
        this.direction.p(matrix4);
        this.up.p(matrix4);
    }

    public void rotate(n nVar) {
        nVar.k(this.direction);
        nVar.k(this.up);
    }

    public a unproject(a aVar) {
        unproject(aVar, 0.0f, 0.0f, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        return aVar;
    }

    public void lookAt(a aVar) {
        lookAt(aVar.f1729a, aVar.f1730b, aVar.f1731c);
    }
}
