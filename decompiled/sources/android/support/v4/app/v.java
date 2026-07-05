package android.support.v4.app;

import android.view.View;
import java.util.ArrayList;

/* JADX INFO: compiled from: FragmentTransitionImpl.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class v implements Runnable {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final /* synthetic */ int f329b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final /* synthetic */ ArrayList f330c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final /* synthetic */ ArrayList f331d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final /* synthetic */ ArrayList f332e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    final /* synthetic */ ArrayList f333f;

    v(int i2, ArrayList arrayList, ArrayList arrayList2, ArrayList arrayList3, ArrayList arrayList4) {
        this.f329b = i2;
        this.f330c = arrayList;
        this.f331d = arrayList2;
        this.f332e = arrayList3;
        this.f333f = arrayList4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        for (int i2 = 0; i2 < this.f329b; i2++) {
            ((View) this.f330c.get(i2)).setTransitionName((String) this.f331d.get(i2));
            ((View) this.f332e.get(i2)).setTransitionName((String) this.f333f.get(i2));
        }
    }
}
