package n;

import android.view.animation.BaseInterpolator;
import com.badlogic.gdx.utils.l;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: compiled from: ViewPropertyAnimatorCompatSet.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class h {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private BaseInterpolator f2508c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    l f2509d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private boolean f2510e;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private long f2507b = -1;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final l f2511f = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final ArrayList<android.support.v4.view.l> f2506a = new ArrayList<>();

    /* JADX INFO: compiled from: ViewPropertyAnimatorCompatSet.java */
    final class a extends l {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private boolean f2512c = false;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private int f2513d = 0;

        a() {
        }

        @Override // android.support.v4.view.m
        public final void a() {
            int i2 = this.f2513d + 1;
            this.f2513d = i2;
            h hVar = h.this;
            if (i2 == hVar.f2506a.size()) {
                l lVar = hVar.f2509d;
                if (lVar != null) {
                    lVar.a();
                }
                this.f2513d = 0;
                this.f2512c = false;
                hVar.b();
            }
        }

        @Override // com.badlogic.gdx.utils.l, android.support.v4.view.m
        public final void c() {
            if (this.f2512c) {
                return;
            }
            this.f2512c = true;
            l lVar = h.this.f2509d;
            if (lVar != null) {
                lVar.c();
            }
        }
    }

    public final void a() {
        if (this.f2510e) {
            Iterator<android.support.v4.view.l> it = this.f2506a.iterator();
            while (it.hasNext()) {
                it.next().b();
            }
            this.f2510e = false;
        }
    }

    final void b() {
        this.f2510e = false;
    }

    public final void c(android.support.v4.view.l lVar) {
        if (this.f2510e) {
            return;
        }
        this.f2506a.add(lVar);
    }

    public final void d(android.support.v4.view.l lVar, android.support.v4.view.l lVar2) {
        ArrayList<android.support.v4.view.l> arrayList = this.f2506a;
        arrayList.add(lVar);
        lVar2.g(lVar.c());
        arrayList.add(lVar2);
    }

    public final void e() {
        if (this.f2510e) {
            return;
        }
        this.f2507b = 250L;
    }

    public final void f(BaseInterpolator baseInterpolator) {
        if (this.f2510e) {
            return;
        }
        this.f2508c = baseInterpolator;
    }

    public final void g(l lVar) {
        if (this.f2510e) {
            return;
        }
        this.f2509d = lVar;
    }

    public final void h() {
        if (this.f2510e) {
            return;
        }
        for (android.support.v4.view.l lVar : this.f2506a) {
            long j2 = this.f2507b;
            if (j2 >= 0) {
                lVar.d(j2);
            }
            BaseInterpolator baseInterpolator = this.f2508c;
            if (baseInterpolator != null) {
                lVar.e(baseInterpolator);
            }
            if (this.f2509d != null) {
                lVar.f(this.f2511f);
            }
            lVar.i();
        }
        this.f2510e = true;
    }
}
