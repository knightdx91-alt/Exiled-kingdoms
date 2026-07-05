package android.support.v7.widget;

import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.CompoundButton;

/* JADX INFO: compiled from: AppCompatCompoundButtonHelper.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final CompoundButton f1217a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private ColorStateList f1218b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private PorterDuff.Mode f1219c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f1220d = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private boolean f1221e = false;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private boolean f1222f;

    h(CompoundButton compoundButton) {
        this.f1217a = compoundButton;
    }

    final void a() {
        CompoundButton compoundButton = this.f1217a;
        Drawable buttonDrawable = compoundButton.getButtonDrawable();
        if (buttonDrawable != null) {
            if (this.f1220d || this.f1221e) {
                Drawable drawableMutate = buttonDrawable.mutate();
                if (this.f1220d) {
                    drawableMutate.setTintList(this.f1218b);
                }
                if (this.f1221e) {
                    drawableMutate.setTintMode(this.f1219c);
                }
                if (drawableMutate.isStateful()) {
                    drawableMutate.setState(compoundButton.getDrawableState());
                }
                compoundButton.setButtonDrawable(drawableMutate);
            }
        }
    }

    final ColorStateList b() {
        return this.f1218b;
    }

    final PorterDuff.Mode c() {
        return this.f1219c;
    }

    final void d(AttributeSet attributeSet, int i2) {
        int resourceId;
        CompoundButton compoundButton = this.f1217a;
        TypedArray typedArrayObtainStyledAttributes = compoundButton.getContext().obtainStyledAttributes(attributeSet, k.j.CompoundButton, i2, 0);
        try {
            if (typedArrayObtainStyledAttributes.hasValue(k.j.CompoundButton_android_button) && (resourceId = typedArrayObtainStyledAttributes.getResourceId(k.j.CompoundButton_android_button, 0)) != 0) {
                compoundButton.setButtonDrawable(l.a.a(compoundButton.getContext(), resourceId));
            }
            if (typedArrayObtainStyledAttributes.hasValue(k.j.CompoundButton_buttonTint)) {
                compoundButton.setButtonTintList(typedArrayObtainStyledAttributes.getColorStateList(k.j.CompoundButton_buttonTint));
            }
            if (typedArrayObtainStyledAttributes.hasValue(k.j.CompoundButton_buttonTintMode)) {
                compoundButton.setButtonTintMode(e0.b(typedArrayObtainStyledAttributes.getInt(k.j.CompoundButton_buttonTintMode, -1), null));
            }
            typedArrayObtainStyledAttributes.recycle();
        } catch (Throwable th) {
            typedArrayObtainStyledAttributes.recycle();
            throw th;
        }
    }

    final void e() {
        if (this.f1222f) {
            this.f1222f = false;
        } else {
            this.f1222f = true;
            a();
        }
    }

    final void f(ColorStateList colorStateList) {
        this.f1218b = colorStateList;
        this.f1220d = true;
        a();
    }

    final void g(PorterDuff.Mode mode) {
        this.f1219c = mode;
        this.f1221e = true;
        a();
    }
}
