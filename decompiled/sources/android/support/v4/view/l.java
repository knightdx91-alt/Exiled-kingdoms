package android.support.v4.view;

import android.view.View;
import android.view.animation.BaseInterpolator;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: ViewPropertyAnimatorCompat.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private WeakReference<View> f612a;

    l(View view) {
        this.f612a = new WeakReference<>(view);
    }

    public final void a(float f2) {
        View view = this.f612a.get();
        if (view != null) {
            view.animate().alpha(f2);
        }
    }

    public final void b() {
        View view = this.f612a.get();
        if (view != null) {
            view.animate().cancel();
        }
    }

    public final long c() {
        View view = this.f612a.get();
        if (view != null) {
            return view.animate().getDuration();
        }
        return 0L;
    }

    public final void d(long j2) {
        View view = this.f612a.get();
        if (view != null) {
            view.animate().setDuration(j2);
        }
    }

    public final void e(BaseInterpolator baseInterpolator) {
        View view = this.f612a.get();
        if (view != null) {
            view.animate().setInterpolator(baseInterpolator);
        }
    }

    public final void f(m mVar) {
        View view = this.f612a.get();
        if (view != null) {
            if (mVar != null) {
                view.animate().setListener(new j(mVar, view));
            } else {
                view.animate().setListener(null);
            }
        }
    }

    public final void g(long j2) {
        View view = this.f612a.get();
        if (view != null) {
            view.animate().setStartDelay(j2);
        }
    }

    public final void h(n nVar) {
        View view = this.f612a.get();
        if (view != null) {
            view.animate().setUpdateListener(nVar != null ? new k(nVar, view) : null);
        }
    }

    public final void i() {
        View view = this.f612a.get();
        if (view != null) {
            view.animate().start();
        }
    }

    public final void j(float f2) {
        View view = this.f612a.get();
        if (view != null) {
            view.animate().translationY(f2);
        }
    }
}
