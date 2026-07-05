package android.support.v7.widget;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.AutoCompleteTextView;

/* JADX INFO: compiled from: AppCompatAutoCompleteTextView.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class c extends AutoCompleteTextView {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final int[] f1198d = {R.attr.popupBackground};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final d f1199b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final z f1200c;

    public c(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, k.a.autoCompleteTextViewStyle);
    }

    @Override // android.widget.TextView, android.view.View
    protected final void drawableStateChanged() {
        super.drawableStateChanged();
        d dVar = this.f1199b;
        if (dVar != null) {
            dVar.a();
        }
        z zVar = this.f1200c;
        if (zVar != null) {
            zVar.c();
        }
    }

    public ColorStateList getSupportBackgroundTintList() {
        d dVar = this.f1199b;
        if (dVar != null) {
            return dVar.b();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        d dVar = this.f1199b;
        if (dVar != null) {
            return dVar.c();
        }
        return null;
    }

    @Override // android.widget.TextView, android.view.View
    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        InputConnection inputConnectionOnCreateInputConnection = super.onCreateInputConnection(editorInfo);
        k.a(inputConnectionOnCreateInputConnection, editorInfo, this);
        return inputConnectionOnCreateInputConnection;
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        d dVar = this.f1199b;
        if (dVar != null) {
            dVar.e();
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i2) {
        super.setBackgroundResource(i2);
        d dVar = this.f1199b;
        if (dVar != null) {
            dVar.f(i2);
        }
    }

    @Override // android.widget.AutoCompleteTextView
    public void setDropDownBackgroundResource(int i2) {
        setDropDownBackgroundDrawable(l.a.a(getContext(), i2));
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        d dVar = this.f1199b;
        if (dVar != null) {
            dVar.h(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        d dVar = this.f1199b;
        if (dVar != null) {
            dVar.i(mode);
        }
    }

    @Override // android.widget.TextView
    public final void setTextAppearance(Context context, int i2) {
        super.setTextAppearance(context, i2);
        z zVar = this.f1200c;
        if (zVar != null) {
            zVar.m(context, i2);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        u0.a(context);
        x0 x0VarT = x0.t(getContext(), attributeSet, f1198d, i2);
        if (x0VarT.q(0)) {
            setDropDownBackgroundDrawable(x0VarT.f(0));
        }
        x0VarT.u();
        d dVar = new d(this);
        this.f1199b = dVar;
        dVar.d(attributeSet, i2);
        z zVar = new z(this);
        this.f1200c = zVar;
        zVar.l(attributeSet, i2);
        zVar.c();
    }
}
