package android.support.v4.app;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: compiled from: FragmentManager.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class l extends AnimatorListenerAdapter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ ViewGroup f274a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final /* synthetic */ View f275b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final /* synthetic */ Fragment f276c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final /* synthetic */ j f277d;

    l(j jVar, ViewGroup viewGroup, View view, Fragment fragment) {
        this.f277d = jVar;
        this.f274a = viewGroup;
        this.f275b = view;
        this.f276c = fragment;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        ViewGroup viewGroup = this.f274a;
        View view = this.f275b;
        viewGroup.endViewTransition(view);
        Fragment fragment = this.f276c;
        Animator animator2 = fragment.getAnimator();
        fragment.setAnimator(null);
        if (animator2 == null || viewGroup.indexOfChild(view) >= 0) {
            return;
        }
        Fragment fragment2 = this.f276c;
        this.f277d.f0(fragment2, fragment2.getStateAfterAnimating(), 0, 0, false);
    }
}
