package android.support.v4.app;

import android.graphics.Rect;
import android.view.View;

/* JADX INFO: compiled from: FragmentTransition.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class r implements Runnable {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final /* synthetic */ Fragment f292b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final /* synthetic */ Fragment f293c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final /* synthetic */ boolean f294d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final /* synthetic */ View f295e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    final /* synthetic */ Rect f296f;

    r(Fragment fragment, Fragment fragment2, boolean z2, android.support.v4.util.b bVar, View view, x xVar, Rect rect) {
        this.f292b = fragment;
        this.f293c = fragment2;
        this.f294d = z2;
        this.f295e = view;
        this.f296f = rect;
    }

    @Override // java.lang.Runnable
    public final void run() {
        t.c(this.f292b, this.f293c, this.f294d);
        View view = this.f295e;
        if (view != null) {
            x.h(view, this.f296f);
        }
    }
}
