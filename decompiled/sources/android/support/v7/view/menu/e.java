package android.support.v7.view.menu;

import android.support.v7.view.menu.d;

/* JADX INFO: compiled from: CascadingMenuPopup.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class e implements Runnable {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final /* synthetic */ d.C0015d f907b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final /* synthetic */ j f908c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final /* synthetic */ h f909d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final /* synthetic */ d.c f910e;

    e(d.c cVar, d.C0015d c0015d, j jVar, h hVar) {
        this.f910e = cVar;
        this.f907b = c0015d;
        this.f908c = jVar;
        this.f909d = hVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        d.C0015d c0015d = this.f907b;
        if (c0015d != null) {
            d.c cVar = this.f910e;
            d.this.A = true;
            c0015d.f905b.e(false);
            d.this.A = false;
        }
        j jVar = this.f908c;
        if (jVar.isEnabled() && jVar.hasSubMenu()) {
            this.f909d.x(jVar, null, 4);
        }
    }
}
