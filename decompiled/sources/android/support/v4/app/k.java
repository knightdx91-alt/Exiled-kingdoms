package android.support.v4.app;

import android.support.v4.app.j;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

/* JADX INFO: compiled from: FragmentManager.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class k extends j.c {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final /* synthetic */ ViewGroup f267b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final /* synthetic */ View f268c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final /* synthetic */ Fragment f269d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final /* synthetic */ j f270e;

    /* JADX INFO: compiled from: FragmentManager.java */
    final class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            k kVar = k.this;
            kVar.f267b.endViewTransition(kVar.f268c);
            if (kVar.f269d.getAnimatingAway() != null) {
                kVar.f269d.setAnimatingAway(null);
                j jVar = kVar.f270e;
                Fragment fragment = kVar.f269d;
                jVar.f0(fragment, fragment.getStateAfterAnimating(), 0, 0, false);
            }
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    k(j jVar, Animation.AnimationListener animationListener, ViewGroup viewGroup, View view, Fragment fragment) {
        super(animationListener);
        this.f270e = jVar;
        this.f267b = viewGroup;
        this.f268c = view;
        this.f269d = fragment;
    }

    @Override // android.support.v4.app.j.c, android.view.animation.Animation.AnimationListener
    public final void onAnimationEnd(Animation animation) {
        super.onAnimationEnd(animation);
        this.f267b.post(new a());
    }
}
