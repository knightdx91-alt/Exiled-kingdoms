package android.support.v7.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.widget.TextView;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: AppCompatTextHelper.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
class y {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final TextView f1378a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private v0 f1379b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private v0 f1380c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private v0 f1381d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private v0 f1382e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final b0 f1383f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private int f1384g = 0;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private Typeface f1385h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private boolean f1386i;

    /* JADX INFO: compiled from: AppCompatTextHelper.java */
    final class a extends e.d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ WeakReference f1387a;

        a(WeakReference weakReference) {
            this.f1387a = weakReference;
        }

        @Override // e.d
        public final void c(Typeface typeface) {
            y.a(y.this, this.f1387a, typeface);
        }
    }

    y(TextView textView) {
        this.f1378a = textView;
        this.f1383f = new b0(textView);
    }

    static void a(y yVar, WeakReference weakReference, Typeface typeface) {
        if (yVar.f1386i) {
            yVar.f1385h = typeface;
            TextView textView = (TextView) weakReference.get();
            if (textView != null) {
                textView.setTypeface(typeface, yVar.f1384g);
            }
        }
    }

    protected static v0 e(Context context, i iVar, int i2) {
        ColorStateList colorStateListG = iVar.g(context, i2);
        if (colorStateListG == null) {
            return null;
        }
        v0 v0Var = new v0();
        v0Var.f1359d = true;
        v0Var.f1356a = colorStateListG;
        return v0Var;
    }

    private void r(Context context, x0 x0Var) {
        String strN;
        this.f1384g = x0Var.j(k.j.TextAppearance_android_textStyle, this.f1384g);
        if (x0Var.q(k.j.TextAppearance_android_fontFamily) || x0Var.q(k.j.TextAppearance_fontFamily)) {
            this.f1385h = null;
            int i2 = x0Var.q(k.j.TextAppearance_fontFamily) ? k.j.TextAppearance_fontFamily : k.j.TextAppearance_android_fontFamily;
            if (!context.isRestricted()) {
                try {
                    Typeface typefaceI = x0Var.i(i2, this.f1384g, new a(new WeakReference(this.f1378a)));
                    this.f1385h = typefaceI;
                    this.f1386i = typefaceI == null;
                } catch (Resources.NotFoundException | UnsupportedOperationException unused) {
                }
            }
            if (this.f1385h != null || (strN = x0Var.n(i2)) == null) {
                return;
            }
            this.f1385h = Typeface.create(strN, this.f1384g);
            return;
        }
        if (x0Var.q(k.j.TextAppearance_android_typeface)) {
            this.f1386i = false;
            int iJ = x0Var.j(k.j.TextAppearance_android_typeface, 1);
            if (iJ == 1) {
                this.f1385h = Typeface.SANS_SERIF;
            } else if (iJ == 2) {
                this.f1385h = Typeface.SERIF;
            } else {
                if (iJ != 3) {
                    return;
                }
                this.f1385h = Typeface.MONOSPACE;
            }
        }
    }

    final void b(Drawable drawable, v0 v0Var) {
        if (drawable == null || v0Var == null) {
            return;
        }
        i.j(drawable, v0Var, this.f1378a.getDrawableState());
    }

    void c() {
        if (this.f1379b == null && this.f1380c == null && this.f1381d == null && this.f1382e == null) {
            return;
        }
        Drawable[] compoundDrawables = this.f1378a.getCompoundDrawables();
        b(compoundDrawables[0], this.f1379b);
        b(compoundDrawables[1], this.f1380c);
        b(compoundDrawables[2], this.f1381d);
        b(compoundDrawables[3], this.f1382e);
    }

    final void d() {
        this.f1383f.a();
    }

    final int f() {
        return this.f1383f.d();
    }

    final int g() {
        return this.f1383f.e();
    }

    final int h() {
        return this.f1383f.f();
    }

    final int[] i() {
        return this.f1383f.g();
    }

    final int j() {
        return this.f1383f.h();
    }

    final boolean k() {
        return this.f1383f.k();
    }

    @SuppressLint({"NewApi"})
    void l(AttributeSet attributeSet, int i2) {
        boolean zA;
        boolean z2;
        TextView textView = this.f1378a;
        Context context = textView.getContext();
        i iVarC = i.c();
        x0 x0VarT = x0.t(context, attributeSet, k.j.AppCompatTextHelper, i2);
        int iM = x0VarT.m(k.j.AppCompatTextHelper_android_textAppearance, -1);
        if (x0VarT.q(k.j.AppCompatTextHelper_android_drawableLeft)) {
            this.f1379b = e(context, iVarC, x0VarT.m(k.j.AppCompatTextHelper_android_drawableLeft, 0));
        }
        if (x0VarT.q(k.j.AppCompatTextHelper_android_drawableTop)) {
            this.f1380c = e(context, iVarC, x0VarT.m(k.j.AppCompatTextHelper_android_drawableTop, 0));
        }
        if (x0VarT.q(k.j.AppCompatTextHelper_android_drawableRight)) {
            this.f1381d = e(context, iVarC, x0VarT.m(k.j.AppCompatTextHelper_android_drawableRight, 0));
        }
        if (x0VarT.q(k.j.AppCompatTextHelper_android_drawableBottom)) {
            this.f1382e = e(context, iVarC, x0VarT.m(k.j.AppCompatTextHelper_android_drawableBottom, 0));
        }
        x0VarT.u();
        boolean z3 = textView.getTransformationMethod() instanceof PasswordTransformationMethod;
        boolean z4 = true;
        if (iM != -1) {
            x0 x0VarR = x0.r(context, iM, k.j.TextAppearance);
            if (z3 || !x0VarR.q(k.j.TextAppearance_textAllCaps)) {
                zA = false;
                z2 = false;
            } else {
                zA = x0VarR.a(k.j.TextAppearance_textAllCaps, false);
                z2 = true;
            }
            r(context, x0VarR);
            x0VarR.u();
        } else {
            zA = false;
            z2 = false;
        }
        x0 x0VarT2 = x0.t(context, attributeSet, k.j.TextAppearance, i2);
        if (z3 || !x0VarT2.q(k.j.TextAppearance_textAllCaps)) {
            z4 = z2;
        } else {
            zA = x0VarT2.a(k.j.TextAppearance_textAllCaps, false);
        }
        r(context, x0VarT2);
        x0VarT2.u();
        if (!z3 && z4) {
            textView.setAllCaps(zA);
        }
        Typeface typeface = this.f1385h;
        if (typeface != null) {
            textView.setTypeface(typeface, this.f1384g);
        }
        b0 b0Var = this.f1383f;
        b0Var.l(attributeSet, i2);
        if (!android.support.v4.widget.b.f673a || b0Var.h() == 0) {
            return;
        }
        int[] iArrG = b0Var.g();
        if (iArrG.length > 0) {
            if (textView.getAutoSizeStepGranularity() != -1.0f) {
                textView.setAutoSizeTextTypeUniformWithConfiguration(b0Var.e(), b0Var.d(), b0Var.f(), 0);
            } else {
                textView.setAutoSizeTextTypeUniformWithPresetSizes(iArrG, 0);
            }
        }
    }

    final void m(Context context, int i2) {
        x0 x0VarR = x0.r(context, i2, k.j.TextAppearance);
        boolean zQ = x0VarR.q(k.j.TextAppearance_textAllCaps);
        TextView textView = this.f1378a;
        if (zQ) {
            textView.setAllCaps(x0VarR.a(k.j.TextAppearance_textAllCaps, false));
        }
        r(context, x0VarR);
        x0VarR.u();
        Typeface typeface = this.f1385h;
        if (typeface != null) {
            textView.setTypeface(typeface, this.f1384g);
        }
    }

    final void n(int i2, int i3, int i4, int i5) {
        this.f1383f.m(i2, i3, i4, i5);
    }

    final void o(int[] iArr, int i2) {
        this.f1383f.n(iArr, i2);
    }

    final void p(int i2) {
        this.f1383f.o(i2);
    }

    final void q(int i2, float f2) {
        if (android.support.v4.widget.b.f673a) {
            return;
        }
        b0 b0Var = this.f1383f;
        if (b0Var.k()) {
            return;
        }
        b0Var.p(i2, f2);
    }
}
