package android.support.v4.view;

import android.view.View;
import android.view.WindowInsets;

/* JADX INFO: compiled from: ViewCompat.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class i implements View.OnApplyWindowInsetsListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ g f607a;

    i(g gVar) {
        this.f607a = gVar;
    }

    @Override // android.view.View.OnApplyWindowInsetsListener
    public final WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
        return (WindowInsets) o.f(this.f607a.a(view, o.g(windowInsets)));
    }
}
