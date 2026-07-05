package android.support.v4.app;

import android.view.View;
import android.view.ViewTreeObserver;

/* JADX INFO: compiled from: OneShotPreDrawListener.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class h0 implements ViewTreeObserver.OnPreDrawListener, View.OnAttachStateChangeListener {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final View f227b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private ViewTreeObserver f228c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Runnable f229d;

    private h0(View view, Runnable runnable) {
        this.f227b = view;
        this.f228c = view.getViewTreeObserver();
        this.f229d = runnable;
    }

    public static void a(View view, Runnable runnable) {
        h0 h0Var = new h0(view, runnable);
        view.getViewTreeObserver().addOnPreDrawListener(h0Var);
        view.addOnAttachStateChangeListener(h0Var);
    }

    @Override // android.view.ViewTreeObserver.OnPreDrawListener
    public final boolean onPreDraw() {
        boolean zIsAlive = this.f228c.isAlive();
        View view = this.f227b;
        if (zIsAlive) {
            this.f228c.removeOnPreDrawListener(this);
        } else {
            view.getViewTreeObserver().removeOnPreDrawListener(this);
        }
        view.removeOnAttachStateChangeListener(this);
        this.f229d.run();
        return true;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewAttachedToWindow(View view) {
        this.f228c = view.getViewTreeObserver();
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewDetachedFromWindow(View view) {
        boolean zIsAlive = this.f228c.isAlive();
        View view2 = this.f227b;
        if (zIsAlive) {
            this.f228c.removeOnPreDrawListener(this);
        } else {
            view2.getViewTreeObserver().removeOnPreDrawListener(this);
        }
        view2.removeOnAttachStateChangeListener(this);
    }
}
