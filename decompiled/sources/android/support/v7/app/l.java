package android.support.v7.app;

import android.support.v7.widget.ContentFrameLayout;

/* JADX INFO: compiled from: AppCompatDelegateImplV9.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class l implements ContentFrameLayout.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ AppCompatDelegateImplV9 f781a;

    l(AppCompatDelegateImplV9 appCompatDelegateImplV9) {
        this.f781a = appCompatDelegateImplV9;
    }

    @Override // android.support.v7.widget.ContentFrameLayout.a
    public final void onDetachedFromWindow() {
        this.f781a.E();
    }
}
