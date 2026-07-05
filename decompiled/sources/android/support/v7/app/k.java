package android.support.v7.app;

import android.view.View;

/* JADX INFO: compiled from: AppCompatDelegateImplV9.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class k implements android.support.v4.view.g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ AppCompatDelegateImplV9 f780a;

    k(AppCompatDelegateImplV9 appCompatDelegateImplV9) {
        this.f780a = appCompatDelegateImplV9;
    }

    @Override // android.support.v4.view.g
    public final android.support.v4.view.o a(View view, android.support.v4.view.o oVar) {
        int iD = oVar.d();
        int iP = this.f780a.P(iD);
        if (iD != iP) {
            oVar = oVar.e(oVar.b(), iP, oVar.c(), oVar.a());
        }
        return android.support.v4.view.h.b(view, oVar);
    }
}
