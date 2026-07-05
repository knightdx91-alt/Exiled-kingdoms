package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.RadioButton;

/* JADX INFO: compiled from: AppCompatRadioButton.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class r extends RadioButton {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final h f1330b;

    /* JADX WARN: Illegal instructions before constructor call */
    public r(Context context, AttributeSet attributeSet) {
        int i2 = k.a.radioButtonStyle;
        u0.a(context);
        super(context, attributeSet, i2);
        h hVar = new h(this);
        this.f1330b = hVar;
        hVar.d(attributeSet, i2);
        new y(this).l(attributeSet, i2);
    }

    @Override // android.widget.CompoundButton, android.widget.TextView
    public int getCompoundPaddingLeft() {
        int compoundPaddingLeft = super.getCompoundPaddingLeft();
        h hVar = this.f1330b;
        if (hVar != null) {
            hVar.getClass();
        }
        return compoundPaddingLeft;
    }

    public ColorStateList getSupportButtonTintList() {
        h hVar = this.f1330b;
        if (hVar != null) {
            return hVar.b();
        }
        return null;
    }

    public PorterDuff.Mode getSupportButtonTintMode() {
        h hVar = this.f1330b;
        if (hVar != null) {
            return hVar.c();
        }
        return null;
    }

    @Override // android.widget.CompoundButton
    public void setButtonDrawable(Drawable drawable) {
        super.setButtonDrawable(drawable);
        h hVar = this.f1330b;
        if (hVar != null) {
            hVar.e();
        }
    }

    public void setSupportButtonTintList(ColorStateList colorStateList) {
        h hVar = this.f1330b;
        if (hVar != null) {
            hVar.f(colorStateList);
        }
    }

    public void setSupportButtonTintMode(PorterDuff.Mode mode) {
        h hVar = this.f1330b;
        if (hVar != null) {
            hVar.g(mode);
        }
    }

    @Override // android.widget.CompoundButton
    public void setButtonDrawable(int i2) {
        setButtonDrawable(l.a.a(getContext(), i2));
    }
}
