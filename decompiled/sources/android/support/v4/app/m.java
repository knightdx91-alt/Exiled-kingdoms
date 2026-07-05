package android.support.v4.app;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: compiled from: FragmentManager.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class m extends AnimatorListenerAdapter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ ViewGroup f278a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final /* synthetic */ View f279b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final /* synthetic */ Fragment f280c;

    m(ViewGroup viewGroup, View view, Fragment fragment) {
        this.f278a = viewGroup;
        this.f279b = view;
        this.f280c = fragment;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        this.f278a.endViewTransition(this.f279b);
        animator.removeListener(this);
        View view = this.f280c.mView;
        if (view != null) {
            view.setVisibility(8);
        }
    }
}
