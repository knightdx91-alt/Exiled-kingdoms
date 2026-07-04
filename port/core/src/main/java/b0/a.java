package b0;

import java.io.Serializable;

/* JADX INFO: compiled from: BoundingBox.java */
/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public final class a implements Serializable {
    public static final long serialVersionUID = -1286036817192127343L;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final com.badlogic.gdx.math.Vector3 f1412a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final com.badlogic.gdx.math.Vector3 f1413b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final com.badlogic.gdx.math.Vector3 f1414c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final com.badlogic.gdx.math.Vector3 f1415d;

    static {
        new com.badlogic.gdx.math.Vector3();
    }

    public a() {
        com.badlogic.gdx.math.Vector3 aVar = new com.badlogic.gdx.math.Vector3();
        this.f1412a = aVar;
        com.badlogic.gdx.math.Vector3 aVar2 = new com.badlogic.gdx.math.Vector3();
        this.f1413b = aVar2;
        this.f1414c = new com.badlogic.gdx.math.Vector3();
        this.f1415d = new com.badlogic.gdx.math.Vector3();
        aVar.t(0.0f, 0.0f, 0.0f);
        aVar2.t(0.0f, 0.0f, 0.0f);
        i(aVar, aVar2);
    }

    static final float g(float f2, float f3) {
        return f2 > f3 ? f2 : f3;
    }

    static final float h(float f2, float f3) {
        return f2 > f3 ? f3 : f2;
    }

    public final void a(float f2, float f3, float f4) {
        com.badlogic.gdx.math.Vector3 aVar = this.f1412a;
        aVar.t(h(aVar.f1729a, f2), h(aVar.f1730b, f3), h(aVar.f1731c, f4));
        com.badlogic.gdx.math.Vector3 aVar2 = this.f1413b;
        aVar2.t(g(aVar2.f1729a, f2), g(aVar2.f1730b, f3), g(aVar2.f1731c, f4));
        i(aVar, aVar2);
    }

    public final void b(a aVar) {
        com.badlogic.gdx.math.Vector3 aVar2 = this.f1412a;
        float fH = h(aVar2.f1729a, aVar.f1412a.f1729a);
        float f2 = aVar2.f1730b;
        com.badlogic.gdx.math.Vector3 aVar3 = aVar.f1412a;
        aVar2.t(fH, h(f2, aVar3.f1730b), h(aVar2.f1731c, aVar3.f1731c));
        com.badlogic.gdx.math.Vector3 aVar4 = this.f1413b;
        float f3 = aVar4.f1729a;
        com.badlogic.gdx.math.Vector3 aVar5 = aVar.f1413b;
        aVar4.t(g(f3, aVar5.f1729a), g(aVar4.f1730b, aVar5.f1730b), g(aVar4.f1731c, aVar5.f1731c));
        i(aVar2, aVar4);
    }

    public final void c(com.badlogic.gdx.math.Vector3 aVar) {
        com.badlogic.gdx.math.Vector3 aVar2 = this.f1412a;
        aVar2.t(h(aVar2.f1729a, aVar.f1729a), h(aVar2.f1730b, aVar.f1730b), h(aVar2.f1731c, aVar.f1731c));
        com.badlogic.gdx.math.Vector3 aVar3 = this.f1413b;
        aVar3.t(Math.max(aVar3.f1729a, aVar.f1729a), Math.max(aVar3.f1730b, aVar.f1730b), Math.max(aVar3.f1731c, aVar.f1731c));
        i(aVar2, aVar3);
    }

    public final void d(com.badlogic.gdx.math.Vector3 aVar) {
        aVar.u(this.f1414c);
    }

    public final void e(com.badlogic.gdx.math.Vector3 aVar) {
        aVar.u(this.f1415d);
    }

    public final void f() {
        this.f1412a.t(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY);
        this.f1413b.t(Float.NEGATIVE_INFINITY, Float.NEGATIVE_INFINITY, Float.NEGATIVE_INFINITY);
        this.f1414c.t(0.0f, 0.0f, 0.0f);
        this.f1415d.t(0.0f, 0.0f, 0.0f);
    }

    public final void i(com.badlogic.gdx.math.Vector3 aVar, com.badlogic.gdx.math.Vector3 aVar2) {
        float f2 = aVar.f1729a;
        float f3 = aVar2.f1729a;
        if (f2 >= f3) {
            f2 = f3;
        }
        float f4 = aVar.f1730b;
        float f5 = aVar2.f1730b;
        if (f4 >= f5) {
            f4 = f5;
        }
        float f6 = aVar.f1731c;
        float f7 = aVar2.f1731c;
        if (f6 >= f7) {
            f6 = f7;
        }
        com.badlogic.gdx.math.Vector3 aVar3 = this.f1412a;
        aVar3.t(f2, f4, f6);
        float f8 = aVar.f1729a;
        float f9 = aVar2.f1729a;
        if (f8 <= f9) {
            f8 = f9;
        }
        float f10 = aVar.f1730b;
        float f11 = aVar2.f1730b;
        if (f10 <= f11) {
            f10 = f11;
        }
        float f12 = aVar.f1731c;
        float f13 = aVar2.f1731c;
        if (f12 <= f13) {
            f12 = f13;
        }
        com.badlogic.gdx.math.Vector3 aVar4 = this.f1413b;
        aVar4.t(f8, f10, f12);
        com.badlogic.gdx.math.Vector3 aVar5 = this.f1414c;
        aVar5.u(aVar3);
        aVar5.b(aVar4);
        aVar5.s(0.5f);
        com.badlogic.gdx.math.Vector3 aVar6 = this.f1415d;
        aVar6.u(aVar4);
        aVar6.w(aVar3);
    }

    public final String toString() {
        return "[" + this.f1412a + "|" + this.f1413b + "]";
    }
}
