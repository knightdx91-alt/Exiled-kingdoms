package a0;

import java.io.Serializable;

/* JADX INFO: compiled from: Plane.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class l implements Serializable {
    private static final long serialVersionUID = -1240652082930747866L;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final com.badlogic.gdx.math.a f73a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public float f74b;

    public l(com.badlogic.gdx.math.a aVar) {
        com.badlogic.gdx.math.a aVar2 = new com.badlogic.gdx.math.a();
        this.f73a = aVar2;
        this.f74b = 0.0f;
        aVar2.u(aVar);
        aVar2.n();
        this.f74b = 0.0f;
    }

    public final void a(com.badlogic.gdx.math.a aVar, com.badlogic.gdx.math.a aVar2, com.badlogic.gdx.math.a aVar3) {
        com.badlogic.gdx.math.a aVar4 = this.f73a;
        aVar4.u(aVar);
        aVar4.w(aVar2);
        aVar4.c(aVar2.f1729a - aVar3.f1729a, aVar2.f1730b - aVar3.f1730b, aVar2.f1731c - aVar3.f1731c);
        aVar4.n();
        this.f74b = -((aVar.f1731c * aVar4.f1731c) + (aVar.f1730b * aVar4.f1730b) + (aVar.f1729a * aVar4.f1729a));
    }

    public final String toString() {
        return this.f73a.toString() + ", " + this.f74b;
    }
}
