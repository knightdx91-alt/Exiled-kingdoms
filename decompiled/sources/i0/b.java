package i0;

import a0.p;
import a0.q;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.HdpiUtils;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;

/* JADX INFO: compiled from: Viewport.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public abstract class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private OrthographicCamera f2259a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private float f2260b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private float f2261c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f2262d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f2263e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int f2264f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private int f2265g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final com.badlogic.gdx.math.a f2266h = new com.badlogic.gdx.math.a();

    public final void a() {
        HdpiUtils.glViewport(this.f2262d, this.f2263e, this.f2264f, this.f2265g);
        OrthographicCamera orthographicCamera = this.f2259a;
        float f2 = this.f2260b;
        orthographicCamera.viewportWidth = f2;
        float f3 = this.f2261c;
        orthographicCamera.viewportHeight = f3;
        orthographicCamera.position.t(f2 / 2.0f, f3 / 2.0f, 0.0f);
        this.f2259a.update();
    }

    public final void b(Matrix4 matrix4, p pVar, p pVar2) {
        ScissorStack.calculateScissors(this.f2259a, this.f2262d, this.f2263e, this.f2264f, this.f2265g, matrix4, pVar, pVar2);
    }

    public final Camera c() {
        return this.f2259a;
    }

    public final int d() {
        return this.f2265g;
    }

    public final int e() {
        return this.f2264f;
    }

    public final int f() {
        return this.f2262d;
    }

    public final int g() {
        return this.f2263e;
    }

    public final float h() {
        return this.f2261c;
    }

    public final float i() {
        return this.f2260b;
    }

    public final void j(q qVar) {
        float f2 = qVar.f91a;
        float f3 = qVar.f92b;
        com.badlogic.gdx.math.a aVar = this.f2266h;
        aVar.t(f2, f3, 1.0f);
        this.f2259a.project(this.f2266h, this.f2262d, this.f2263e, this.f2264f, this.f2265g);
        float f4 = aVar.f1729a;
        float f5 = aVar.f1730b;
        qVar.f91a = f4;
        qVar.f92b = f5;
    }

    public final void k(OrthographicCamera orthographicCamera) {
        this.f2259a = orthographicCamera;
    }

    public final void l(int i2, int i3, int i4, int i5) {
        this.f2262d = i2;
        this.f2263e = i3;
        this.f2264f = i4;
        this.f2265g = i5;
    }

    public final void m(float f2, float f3) {
        this.f2260b = f2;
        this.f2261c = f3;
    }

    public final void n(q qVar, Matrix4 matrix4) {
        float f2 = qVar.f91a;
        float f3 = qVar.f92b;
        com.badlogic.gdx.math.a aVar = this.f2266h;
        aVar.t(f2, f3, 0.0f);
        aVar.m(matrix4);
        this.f2259a.project(this.f2266h, this.f2262d, this.f2263e, this.f2264f, this.f2265g);
        float height = Gdx.graphics.getHeight() - aVar.f1730b;
        aVar.f1730b = height;
        qVar.f91a = aVar.f1729a;
        qVar.f92b = height;
    }

    public final void o(q qVar) {
        float f2 = qVar.f91a;
        float f3 = qVar.f92b;
        com.badlogic.gdx.math.a aVar = this.f2266h;
        aVar.t(f2, f3, 1.0f);
        this.f2259a.unproject(this.f2266h, this.f2262d, this.f2263e, this.f2264f, this.f2265g);
        float f4 = aVar.f1729a;
        float f5 = aVar.f1730b;
        qVar.f91a = f4;
        qVar.f92b = f5;
    }

    public abstract void p(int i2, int i3);
}
