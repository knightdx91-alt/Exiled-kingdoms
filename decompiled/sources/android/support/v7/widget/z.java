package android.support.v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

/* JADX INFO: compiled from: AppCompatTextHelperV17.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class z extends y {

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private v0 f1391j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private v0 f1392k;

    @Override // android.support.v7.widget.y
    final void c() {
        super.c();
        if (this.f1391j == null && this.f1392k == null) {
            return;
        }
        Drawable[] compoundDrawablesRelative = this.f1378a.getCompoundDrawablesRelative();
        b(compoundDrawablesRelative[0], this.f1391j);
        b(compoundDrawablesRelative[2], this.f1392k);
    }

    @Override // android.support.v7.widget.y
    final void l(AttributeSet attributeSet, int i2) {
        super.l(attributeSet, i2);
        Context context = this.f1378a.getContext();
        i iVarC = i.c();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, k.j.AppCompatTextHelper, i2, 0);
        if (typedArrayObtainStyledAttributes.hasValue(k.j.AppCompatTextHelper_android_drawableStart)) {
            this.f1391j = y.e(context, iVarC, typedArrayObtainStyledAttributes.getResourceId(k.j.AppCompatTextHelper_android_drawableStart, 0));
        }
        if (typedArrayObtainStyledAttributes.hasValue(k.j.AppCompatTextHelper_android_drawableEnd)) {
            this.f1392k = y.e(context, iVarC, typedArrayObtainStyledAttributes.getResourceId(k.j.AppCompatTextHelper_android_drawableEnd, 0));
        }
        typedArrayObtainStyledAttributes.recycle();
    }
}
