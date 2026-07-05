package android.support.v7.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

/* JADX INFO: compiled from: AppCompatBackgroundHelper.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final View f1201a;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private v0 f1204d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private v0 f1205e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private v0 f1206f;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f1203c = -1;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final i f1202b = i.c();

    d(View view) {
        this.f1201a = view;
    }

    final void a() {
        View view = this.f1201a;
        Drawable background = view.getBackground();
        if (background != null) {
            if (this.f1204d != null) {
                if (this.f1206f == null) {
                    this.f1206f = new v0();
                }
                v0 v0Var = this.f1206f;
                v0Var.f1356a = null;
                v0Var.f1359d = false;
                v0Var.f1357b = null;
                v0Var.f1358c = false;
                ColorStateList backgroundTintList = view.getBackgroundTintList();
                if (backgroundTintList != null) {
                    v0Var.f1359d = true;
                    v0Var.f1356a = backgroundTintList;
                }
                PorterDuff.Mode backgroundTintMode = view.getBackgroundTintMode();
                if (backgroundTintMode != null) {
                    v0Var.f1358c = true;
                    v0Var.f1357b = backgroundTintMode;
                }
                if (v0Var.f1359d || v0Var.f1358c) {
                    i.j(background, v0Var, view.getDrawableState());
                    return;
                }
            }
            v0 v0Var2 = this.f1205e;
            if (v0Var2 != null) {
                i.j(background, v0Var2, view.getDrawableState());
                return;
            }
            v0 v0Var3 = this.f1204d;
            if (v0Var3 != null) {
                i.j(background, v0Var3, view.getDrawableState());
            }
        }
    }

    final ColorStateList b() {
        v0 v0Var = this.f1205e;
        if (v0Var != null) {
            return v0Var.f1356a;
        }
        return null;
    }

    final PorterDuff.Mode c() {
        v0 v0Var = this.f1205e;
        if (v0Var != null) {
            return v0Var.f1357b;
        }
        return null;
    }

    final void d(AttributeSet attributeSet, int i2) {
        View view = this.f1201a;
        x0 x0VarT = x0.t(view.getContext(), attributeSet, k.j.ViewBackgroundHelper, i2);
        try {
            if (x0VarT.q(k.j.ViewBackgroundHelper_android_background)) {
                this.f1203c = x0VarT.m(k.j.ViewBackgroundHelper_android_background, -1);
                ColorStateList colorStateListG = this.f1202b.g(view.getContext(), this.f1203c);
                if (colorStateListG != null) {
                    g(colorStateListG);
                }
            }
            if (x0VarT.q(k.j.ViewBackgroundHelper_backgroundTint)) {
                view.setBackgroundTintList(x0VarT.c(k.j.ViewBackgroundHelper_backgroundTint));
            }
            if (x0VarT.q(k.j.ViewBackgroundHelper_backgroundTintMode)) {
                view.setBackgroundTintMode(e0.b(x0VarT.j(k.j.ViewBackgroundHelper_backgroundTintMode, -1), null));
            }
            x0VarT.u();
        } catch (Throwable th) {
            x0VarT.u();
            throw th;
        }
    }

    final void e() {
        this.f1203c = -1;
        g(null);
        a();
    }

    final void f(int i2) {
        this.f1203c = i2;
        i iVar = this.f1202b;
        g(iVar != null ? iVar.g(this.f1201a.getContext(), i2) : null);
        a();
    }

    final void g(ColorStateList colorStateList) {
        if (colorStateList != null) {
            if (this.f1204d == null) {
                this.f1204d = new v0();
            }
            v0 v0Var = this.f1204d;
            v0Var.f1356a = colorStateList;
            v0Var.f1359d = true;
        } else {
            this.f1204d = null;
        }
        a();
    }

    final void h(ColorStateList colorStateList) {
        if (this.f1205e == null) {
            this.f1205e = new v0();
        }
        v0 v0Var = this.f1205e;
        v0Var.f1356a = colorStateList;
        v0Var.f1359d = true;
        a();
    }

    final void i(PorterDuff.Mode mode) {
        if (this.f1205e == null) {
            this.f1205e = new v0();
        }
        v0 v0Var = this.f1205e;
        v0Var.f1357b = mode;
        v0Var.f1358c = true;
        a();
    }
}
