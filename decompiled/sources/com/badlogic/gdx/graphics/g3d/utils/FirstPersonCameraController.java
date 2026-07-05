package com.badlogic.gdx.graphics.g3d.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.h;
import com.badlogic.gdx.math.a;
import com.badlogic.gdx.utils.p;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class FirstPersonCameraController extends h {
    private final Camera camera;
    private final p keys = new p();
    private int STRAFE_LEFT = 29;
    private int STRAFE_RIGHT = 32;
    private int FORWARD = 51;
    private int BACKWARD = 47;
    private int UP = 45;
    private int DOWN = 33;
    private float velocity = 5.0f;
    private float degreesPerPixel = 0.5f;
    private final a tmp = new a();

    public FirstPersonCameraController(Camera camera) {
        this.camera = camera;
    }

    @Override // com.badlogic.gdx.h, com.badlogic.gdx.j
    public boolean keyDown(int i2) {
        this.keys.e(i2, i2);
        return true;
    }

    @Override // com.badlogic.gdx.h, com.badlogic.gdx.j
    public boolean keyUp(int i2) {
        this.keys.f(i2);
        return true;
    }

    public void setDegreesPerPixel(float f2) {
        this.degreesPerPixel = f2;
    }

    public void setVelocity(float f2) {
        this.velocity = f2;
    }

    @Override // com.badlogic.gdx.h, com.badlogic.gdx.j
    public boolean touchDragged(int i2, int i3, int i4) {
        float f2 = (-Gdx.input.getDeltaX()) * this.degreesPerPixel;
        float f3 = (-Gdx.input.getDeltaY()) * this.degreesPerPixel;
        Camera camera = this.camera;
        camera.direction.r(camera.up, f2);
        a aVar = this.tmp;
        aVar.u(this.camera.direction);
        aVar.d(this.camera.up);
        aVar.n();
        this.camera.direction.r(this.tmp, f3);
        return true;
    }

    public void update() {
        update(Gdx.graphics.getDeltaTime());
    }

    public void update(float f2) {
        if (this.keys.a(this.FORWARD)) {
            a aVar = this.tmp;
            aVar.u(this.camera.direction);
            aVar.n();
            aVar.s(this.velocity * f2);
            this.camera.position.b(this.tmp);
        }
        if (this.keys.a(this.BACKWARD)) {
            a aVar2 = this.tmp;
            aVar2.u(this.camera.direction);
            aVar2.n();
            aVar2.s((-f2) * this.velocity);
            this.camera.position.b(this.tmp);
        }
        if (this.keys.a(this.STRAFE_LEFT)) {
            a aVar3 = this.tmp;
            aVar3.u(this.camera.direction);
            aVar3.d(this.camera.up);
            aVar3.n();
            aVar3.s((-f2) * this.velocity);
            this.camera.position.b(this.tmp);
        }
        if (this.keys.a(this.STRAFE_RIGHT)) {
            a aVar4 = this.tmp;
            aVar4.u(this.camera.direction);
            aVar4.d(this.camera.up);
            aVar4.n();
            aVar4.s(this.velocity * f2);
            this.camera.position.b(this.tmp);
        }
        if (this.keys.a(this.UP)) {
            a aVar5 = this.tmp;
            aVar5.u(this.camera.up);
            aVar5.n();
            aVar5.s(this.velocity * f2);
            this.camera.position.b(this.tmp);
        }
        if (this.keys.a(this.DOWN)) {
            a aVar6 = this.tmp;
            aVar6.u(this.camera.up);
            aVar6.n();
            aVar6.s((-f2) * this.velocity);
            this.camera.position.b(this.tmp);
        }
        this.camera.update(true);
    }
}
