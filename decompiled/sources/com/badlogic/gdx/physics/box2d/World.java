package com.badlogic.gdx.physics.box2d;

import a0.q;
import com.badlogic.gdx.utils.c0;
import com.badlogic.gdx.utils.i;
import com.badlogic.gdx.utils.i0;
import com.badlogic.gdx.utils.v;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class World implements i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected final long f1735a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected final v<Fixture> f1736b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private long[] f1737c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final com.badlogic.gdx.utils.a<Contact> f1738d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private f0.b f1739e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private q f1740f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private q f1741g;

    final class a extends c0<Body> {
        @Override // com.badlogic.gdx.utils.c0
        protected final Body newObject() {
            Body body = new Body();
            new q();
            new q();
            new q();
            new q();
            new q();
            new q();
            new q();
            new q();
            new q();
            new q();
            new q();
            new q();
            new q();
            return body;
        }
    }

    final class b extends c0<Fixture> {
        @Override // com.badlogic.gdx.utils.c0
        protected final Fixture newObject() {
            return new Fixture();
        }
    }

    static {
        new i0();
        i0.c("gdx-box2d");
    }

    public World(q qVar) {
        new a(100, 200);
        new b(100, 200);
        new v(0);
        this.f1736b = new v<>(0);
        new v(0);
        new q();
        this.f1737c = new long[200];
        com.badlogic.gdx.utils.a aVar = new com.badlogic.gdx.utils.a();
        com.badlogic.gdx.utils.a<Contact> aVar2 = new com.badlogic.gdx.utils.a<>();
        this.f1738d = aVar2;
        new Contact();
        new q();
        new q();
        new q();
        new q();
        this.f1739e = null;
        this.f1740f = new q();
        this.f1741g = new q();
        this.f1735a = newWorld(qVar.f91a, qVar.f92b, true);
        aVar.f(200);
        aVar2.f(200);
        for (int i2 = 0; i2 < this.f1737c.length; i2++) {
            this.f1738d.a(new Contact());
        }
    }

    private void beginContact(long j2) {
    }

    private boolean contactFilter(long j2, long j3) {
        v<Fixture> vVar = this.f1736b;
        f0.a aVarA = vVar.b(j2).a();
        f0.a aVarA2 = vVar.b(j3).a();
        short s = aVarA.f2210c;
        return (s != aVarA2.f2210c || s == 0) ? ((aVarA.f2209b & aVarA2.f2208a) == 0 || (aVarA.f2208a & aVarA2.f2209b) == 0) ? false : true : s > 0;
    }

    private void endContact(long j2) {
    }

    private native void jniDispose(long j2);

    private native void jniRayCast(long j2, float f2, float f3, float f4, float f5);

    private native long newWorld(float f2, float f3, boolean z2);

    private void postSolve(long j2, long j3) {
    }

    private void preSolve(long j2, long j3) {
    }

    private boolean reportFixture(long j2) {
        return false;
    }

    private float reportRayFixture(long j2, float f2, float f3, float f4, float f5, float f6) {
        f0.b bVar = this.f1739e;
        if (bVar == null) {
            return 0.0f;
        }
        q qVar = this.f1740f;
        qVar.f91a = f2;
        qVar.f92b = f3;
        q qVar2 = this.f1741g;
        qVar2.f91a = f4;
        qVar2.f92b = f5;
        this.f1736b.b(j2);
        bVar.a(qVar, f6);
        return f6;
    }

    public final void a(f0.b bVar, q qVar, q qVar2) {
        float f2 = qVar.f91a;
        float f3 = qVar.f92b;
        float f4 = qVar2.f91a;
        float f5 = qVar2.f92b;
        this.f1739e = bVar;
        jniRayCast(this.f1735a, f2, f3, f4, f5);
    }

    @Override // com.badlogic.gdx.utils.i
    public final void dispose() {
        jniDispose(this.f1735a);
    }
}
