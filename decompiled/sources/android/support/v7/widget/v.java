package android.support.v7.widget;

import android.support.v7.widget.w;

/* JADX INFO: compiled from: AppCompatSpinner.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class v extends h0 {

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    final /* synthetic */ w.b f1354k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    final /* synthetic */ w f1355l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    v(w wVar, w wVar2, w.b bVar) {
        super(wVar2);
        this.f1355l = wVar;
        this.f1354k = bVar;
    }

    @Override // android.support.v7.widget.h0
    public final o.b b() {
        return this.f1354k;
    }

    @Override // android.support.v7.widget.h0
    public final boolean c() {
        w wVar = this.f1355l;
        if (wVar.f1366g.f1291z.isShowing()) {
            return true;
        }
        wVar.f1366g.a();
        return true;
    }
}
