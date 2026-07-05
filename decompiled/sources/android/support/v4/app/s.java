package android.support.v4.app;

import android.graphics.Rect;
import android.support.v4.app.t;
import android.view.View;
import java.util.ArrayList;

/* JADX INFO: compiled from: FragmentTransition.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class s implements Runnable {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final /* synthetic */ x f297b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final /* synthetic */ android.support.v4.util.b f298c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final /* synthetic */ Object f299d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final /* synthetic */ t.a f300e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    final /* synthetic */ ArrayList f301f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    final /* synthetic */ View f302g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    final /* synthetic */ Fragment f303h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    final /* synthetic */ Fragment f304i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    final /* synthetic */ boolean f305j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    final /* synthetic */ ArrayList f306k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    final /* synthetic */ Object f307l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    final /* synthetic */ Rect f308m;

    s(x xVar, android.support.v4.util.b bVar, Object obj, t.a aVar, ArrayList arrayList, View view, Fragment fragment, Fragment fragment2, boolean z2, ArrayList arrayList2, Object obj2, Rect rect) {
        this.f297b = xVar;
        this.f298c = bVar;
        this.f299d = obj;
        this.f300e = aVar;
        this.f301f = arrayList;
        this.f302g = view;
        this.f303h = fragment;
        this.f304i = fragment2;
        this.f305j = z2;
        this.f306k = arrayList2;
        this.f307l = obj2;
        this.f308m = rect;
    }

    @Override // java.lang.Runnable
    public final void run() {
        x xVar = this.f297b;
        android.support.v4.util.b bVar = this.f298c;
        t.a aVar = this.f300e;
        Object obj = this.f299d;
        android.support.v4.util.b bVarH = t.h(bVar, obj, aVar);
        ArrayList<View> arrayList = this.f301f;
        if (bVarH != null) {
            arrayList.addAll(bVarH.values());
            arrayList.add(this.f302g);
        }
        Fragment fragment = this.f303h;
        Fragment fragment2 = this.f304i;
        boolean z2 = this.f305j;
        t.c(fragment, fragment2, z2);
        if (obj != null) {
            xVar.r(obj, this.f306k, arrayList);
            View viewL = t.l(bVarH, aVar, this.f307l, z2);
            if (viewL != null) {
                x.h(viewL, this.f308m);
            }
        }
    }
}
