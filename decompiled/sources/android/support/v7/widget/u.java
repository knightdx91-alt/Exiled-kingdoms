package android.support.v7.widget;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

/* JADX INFO: compiled from: AppCompatSeekBarHelper.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class u extends q {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final t f1347d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Drawable f1348e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private ColorStateList f1349f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private PorterDuff.Mode f1350g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private boolean f1351h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private boolean f1352i;

    u(t tVar) {
        super(tVar);
        this.f1349f = null;
        this.f1350g = null;
        this.f1351h = false;
        this.f1352i = false;
        this.f1347d = tVar;
    }

    private void d() {
        Drawable drawable = this.f1348e;
        if (drawable != null) {
            if (this.f1351h || this.f1352i) {
                Drawable drawableMutate = drawable.mutate();
                this.f1348e = drawableMutate;
                if (this.f1351h) {
                    drawableMutate.setTintList(this.f1349f);
                }
                if (this.f1352i) {
                    this.f1348e.setTintMode(this.f1350g);
                }
                if (this.f1348e.isStateful()) {
                    this.f1348e.setState(this.f1347d.getDrawableState());
                }
            }
        }
    }

    @Override // android.support.v7.widget.q
    final void b(AttributeSet attributeSet, int i2) {
        super.b(attributeSet, i2);
        t tVar = this.f1347d;
        x0 x0VarT = x0.t(tVar.getContext(), attributeSet, k.j.AppCompatSeekBar, i2);
        Drawable drawableG = x0VarT.g(k.j.AppCompatSeekBar_android_thumb);
        if (drawableG != null) {
            tVar.setThumb(drawableG);
        }
        Drawable drawableF = x0VarT.f(k.j.AppCompatSeekBar_tickMark);
        Drawable drawable = this.f1348e;
        if (drawable != null) {
            drawable.setCallback(null);
        }
        this.f1348e = drawableF;
        if (drawableF != null) {
            drawableF.setCallback(tVar);
            drawableF.setLayoutDirection(tVar.getLayoutDirection());
            if (drawableF.isStateful()) {
                drawableF.setState(tVar.getDrawableState());
            }
            d();
        }
        tVar.invalidate();
        if (x0VarT.q(k.j.AppCompatSeekBar_tickMarkTintMode)) {
            this.f1350g = e0.b(x0VarT.j(k.j.AppCompatSeekBar_tickMarkTintMode, -1), this.f1350g);
            this.f1352i = true;
        }
        if (x0VarT.q(k.j.AppCompatSeekBar_tickMarkTint)) {
            this.f1349f = x0VarT.c(k.j.AppCompatSeekBar_tickMarkTint);
            this.f1351h = true;
        }
        x0VarT.u();
        d();
    }

    final void e(Canvas canvas) {
        if (this.f1348e != null) {
            int max = this.f1347d.getMax();
            if (max > 1) {
                int intrinsicWidth = this.f1348e.getIntrinsicWidth();
                int intrinsicHeight = this.f1348e.getIntrinsicHeight();
                int i2 = intrinsicWidth >= 0 ? intrinsicWidth / 2 : 1;
                int i3 = intrinsicHeight >= 0 ? intrinsicHeight / 2 : 1;
                this.f1348e.setBounds(-i2, -i3, i2, i3);
                float width = ((r0.getWidth() - r0.getPaddingLeft()) - r0.getPaddingRight()) / max;
                int iSave = canvas.save();
                canvas.translate(r0.getPaddingLeft(), r0.getHeight() / 2);
                for (int i4 = 0; i4 <= max; i4++) {
                    this.f1348e.draw(canvas);
                    canvas.translate(width, 0.0f);
                }
                canvas.restoreToCount(iSave);
            }
        }
    }

    final void f() {
        Drawable drawable = this.f1348e;
        if (drawable == null || !drawable.isStateful()) {
            return;
        }
        t tVar = this.f1347d;
        if (drawable.setState(tVar.getDrawableState())) {
            tVar.invalidateDrawable(drawable);
        }
    }

    final void g() {
        Drawable drawable = this.f1348e;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
    }
}
