package android.support.v4.app;

import android.view.View;
import java.util.ArrayList;

/* JADX INFO: compiled from: FragmentTransition.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class q implements Runnable {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final /* synthetic */ Object f284b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final /* synthetic */ x f285c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final /* synthetic */ View f286d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final /* synthetic */ Fragment f287e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    final /* synthetic */ ArrayList f288f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    final /* synthetic */ ArrayList f289g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    final /* synthetic */ ArrayList f290h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    final /* synthetic */ Object f291i;

    q(Object obj, x xVar, View view, Fragment fragment, ArrayList arrayList, ArrayList arrayList2, ArrayList arrayList3, Object obj2) {
        this.f284b = obj;
        this.f285c = xVar;
        this.f286d = view;
        this.f287e = fragment;
        this.f288f = arrayList;
        this.f289g = arrayList2;
        this.f290h = arrayList3;
        this.f291i = obj2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        x xVar = this.f285c;
        View view = this.f286d;
        Object obj = this.f284b;
        if (obj != null) {
            xVar.k(obj, view);
            this.f289g.addAll(t.k(xVar, obj, this.f287e, this.f288f, view));
        }
        ArrayList<View> arrayList = this.f290h;
        if (arrayList != null) {
            Object obj2 = this.f291i;
            if (obj2 != null) {
                ArrayList<View> arrayList2 = new ArrayList<>();
                arrayList2.add(view);
                xVar.l(obj2, arrayList, arrayList2);
            }
            arrayList.clear();
            arrayList.add(view);
        }
    }
}
