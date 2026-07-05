package android.support.v4.app;

import java.util.ArrayList;

/* JADX INFO: compiled from: FragmentTransition.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class p implements Runnable {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final /* synthetic */ ArrayList f283b;

    p(ArrayList arrayList) {
        this.f283b = arrayList;
    }

    @Override // java.lang.Runnable
    public final void run() {
        t.o(4, this.f283b);
    }
}
