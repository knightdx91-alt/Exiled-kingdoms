package android.support.v7.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/* JADX INFO: compiled from: AppCompatImageHelper.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final ImageView f1308a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private v0 f1309b;

    public m(ImageView imageView) {
        this.f1308a = imageView;
    }

    final void a() {
        v0 v0Var;
        ImageView imageView = this.f1308a;
        Drawable drawable = imageView.getDrawable();
        if (drawable != null) {
            int i2 = e0.f1209a;
        }
        if (drawable == null || (v0Var = this.f1309b) == null) {
            return;
        }
        i.j(drawable, v0Var, imageView.getDrawableState());
    }

    final ColorStateList b() {
        v0 v0Var = this.f1309b;
        if (v0Var != null) {
            return v0Var.f1356a;
        }
        return null;
    }

    final PorterDuff.Mode c() {
        v0 v0Var = this.f1309b;
        if (v0Var != null) {
            return v0Var.f1357b;
        }
        return null;
    }

    final boolean d() {
        return !(this.f1308a.getBackground() instanceof RippleDrawable);
    }

    public final void e(AttributeSet attributeSet, int i2) {
        int iM;
        ImageView imageView = this.f1308a;
        x0 x0VarT = x0.t(imageView.getContext(), attributeSet, k.j.AppCompatImageView, i2);
        try {
            Drawable drawable = imageView.getDrawable();
            if (drawable == null && (iM = x0VarT.m(k.j.AppCompatImageView_srcCompat, -1)) != -1 && (drawable = l.a.a(imageView.getContext(), iM)) != null) {
                imageView.setImageDrawable(drawable);
            }
            if (drawable != null) {
                int i3 = e0.f1209a;
            }
            if (x0VarT.q(k.j.AppCompatImageView_tint)) {
                imageView.setImageTintList(x0VarT.c(k.j.AppCompatImageView_tint));
            }
            if (x0VarT.q(k.j.AppCompatImageView_tintMode)) {
                imageView.setImageTintMode(e0.b(x0VarT.j(k.j.AppCompatImageView_tintMode, -1), null));
            }
            x0VarT.u();
        } catch (Throwable th) {
            x0VarT.u();
            throw th;
        }
    }

    public final void f(int i2) {
        ImageView imageView = this.f1308a;
        if (i2 != 0) {
            Drawable drawableA = l.a.a(imageView.getContext(), i2);
            if (drawableA != null) {
                int i3 = e0.f1209a;
            }
            imageView.setImageDrawable(drawableA);
        } else {
            imageView.setImageDrawable(null);
        }
        a();
    }

    final void g(ColorStateList colorStateList) {
        if (this.f1309b == null) {
            this.f1309b = new v0();
        }
        v0 v0Var = this.f1309b;
        v0Var.f1356a = colorStateList;
        v0Var.f1359d = true;
        a();
    }

    final void h(PorterDuff.Mode mode) {
        if (this.f1309b == null) {
            this.f1309b = new v0();
        }
        v0 v0Var = this.f1309b;
        v0Var.f1357b = mode;
        v0Var.f1358c = true;
        a();
    }
}
