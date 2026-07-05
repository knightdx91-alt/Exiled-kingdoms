package n;

import android.content.Context;
import android.support.v7.view.menu.h;
import android.support.v7.view.menu.j;
import android.support.v7.widget.ActionBarContextView;
import android.view.View;
import java.lang.ref.WeakReference;
import n.b;

/* JADX INFO: compiled from: StandaloneActionMode.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class e extends b implements h.a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Context f2460d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private ActionBarContextView f2461e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private b.a f2462f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private WeakReference<View> f2463g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private boolean f2464h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private android.support.v7.view.menu.h f2465i;

    public e(Context context, ActionBarContextView actionBarContextView, b.a aVar) {
        this.f2460d = context;
        this.f2461e = actionBarContextView;
        this.f2462f = aVar;
        android.support.v7.view.menu.h hVar = new android.support.v7.view.menu.h(actionBarContextView.getContext());
        hVar.C();
        this.f2465i = hVar;
        hVar.B(this);
    }

    @Override // android.support.v7.view.menu.h.a
    public final boolean a(android.support.v7.view.menu.h hVar, j jVar) {
        return this.f2462f.a(this, jVar);
    }

    @Override // android.support.v7.view.menu.h.a
    public final void b(android.support.v7.view.menu.h hVar) {
        k();
        this.f2461e.k();
    }

    @Override // n.b
    public final void c() {
        if (this.f2464h) {
            return;
        }
        this.f2464h = true;
        this.f2461e.sendAccessibilityEvent(32);
        this.f2462f.c(this);
    }

    @Override // n.b
    public final View d() {
        WeakReference<View> weakReference = this.f2463g;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    @Override // n.b
    public final android.support.v7.view.menu.h e() {
        return this.f2465i;
    }

    @Override // n.b
    public final g f() {
        return new g(this.f2461e.getContext());
    }

    @Override // n.b
    public final CharSequence g() {
        return this.f2461e.getSubtitle();
    }

    @Override // n.b
    public final CharSequence i() {
        return this.f2461e.getTitle();
    }

    @Override // n.b
    public final void k() {
        this.f2462f.b(this, this.f2465i);
    }

    @Override // n.b
    public final boolean l() {
        return this.f2461e.h();
    }

    @Override // n.b
    public final void m(View view) {
        this.f2461e.setCustomView(view);
        this.f2463g = view != null ? new WeakReference<>(view) : null;
    }

    @Override // n.b
    public final void n(int i2) {
        o(this.f2460d.getString(i2));
    }

    @Override // n.b
    public final void o(CharSequence charSequence) {
        this.f2461e.setSubtitle(charSequence);
    }

    @Override // n.b
    public final void q(int i2) {
        r(this.f2460d.getString(i2));
    }

    @Override // n.b
    public final void r(CharSequence charSequence) {
        this.f2461e.setTitle(charSequence);
    }

    @Override // n.b
    public final void s(boolean z2) {
        super.s(z2);
        this.f2461e.setTitleOptional(z2);
    }
}
