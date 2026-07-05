package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.CheckBox;

/* JADX INFO: compiled from: AppCompatCheckBox.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class f extends CheckBox {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final h f1210b;

    /* JADX WARN: Illegal instructions before constructor call */
    public f(Context context, AttributeSet attributeSet) {
        int i2 = k.a.checkboxStyle;
        u0.a(context);
        super(context, attributeSet, i2);
        h hVar = new h(this);
        this.f1210b = hVar;
        hVar.d(attributeSet, i2);
    }

    @Override // android.widget.CompoundButton, android.widget.TextView
    public int getCompoundPaddingLeft() {
        int compoundPaddingLeft = super.getCompoundPaddingLeft();
        h hVar = this.f1210b;
        if (hVar != null) {
            hVar.getClass();
        }
        return compoundPaddingLeft;
    }

    public ColorStateList getSupportButtonTintList() {
        h hVar = this.f1210b;
        if (hVar != null) {
            return hVar.b();
        }
        return null;
    }

    public PorterDuff.Mode getSupportButtonTintMode() {
        h hVar = this.f1210b;
        if (hVar != null) {
            return hVar.c();
        }
        return null;
    }

    @Override // android.widget.CompoundButton
    public void setButtonDrawable(Drawable drawable) {
        super.setButtonDrawable(drawable);
        h hVar = this.f1210b;
        if (hVar != null) {
            hVar.e();
        }
    }

    public void setSupportButtonTintList(ColorStateList colorStateList) {
        h hVar = this.f1210b;
        if (hVar != null) {
            hVar.f(colorStateList);
        }
    }

    public void setSupportButtonTintMode(PorterDuff.Mode mode) {
        h hVar = this.f1210b;
        if (hVar != null) {
            hVar.g(mode);
        }
    }

    @Override // android.widget.CompoundButton
    public void setButtonDrawable(int i2) {
        setButtonDrawable(l.a.a(getContext(), i2));
    }
}
