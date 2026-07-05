package android.support.v4.view;

import android.animation.ValueAnimator;
import android.view.View;

/* JADX INFO: compiled from: ViewPropertyAnimatorCompat.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class k implements ValueAnimator.AnimatorUpdateListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ n f610a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final /* synthetic */ View f611b;

    k(n nVar, View view) {
        this.f610a = nVar;
        this.f611b = view;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.f610a.a();
    }
}
