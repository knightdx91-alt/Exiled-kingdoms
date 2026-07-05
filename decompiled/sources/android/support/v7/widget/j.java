package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.EditText;

/* JADX INFO: compiled from: AppCompatEditText.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class j extends EditText {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final d f1265b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final z f1266c;

    /* JADX WARN: Illegal instructions before constructor call */
    public j(Context context, AttributeSet attributeSet) {
        int i2 = k.a.editTextStyle;
        u0.a(context);
        super(context, attributeSet, i2);
        d dVar = new d(this);
        this.f1265b = dVar;
        dVar.d(attributeSet, i2);
        z zVar = new z(this);
        this.f1266c = zVar;
        zVar.l(attributeSet, i2);
        zVar.c();
    }

    @Override // android.widget.TextView, android.view.View
    protected final void drawableStateChanged() {
        super.drawableStateChanged();
        d dVar = this.f1265b;
        if (dVar != null) {
            dVar.a();
        }
        z zVar = this.f1266c;
        if (zVar != null) {
            zVar.c();
        }
    }

    public ColorStateList getSupportBackgroundTintList() {
        d dVar = this.f1265b;
        if (dVar != null) {
            return dVar.b();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        d dVar = this.f1265b;
        if (dVar != null) {
            return dVar.c();
        }
        return null;
    }

    @Override // android.widget.TextView, android.view.View
    public final InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        InputConnection inputConnectionOnCreateInputConnection = super.onCreateInputConnection(editorInfo);
        k.a(inputConnectionOnCreateInputConnection, editorInfo, this);
        return inputConnectionOnCreateInputConnection;
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        d dVar = this.f1265b;
        if (dVar != null) {
            dVar.e();
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i2) {
        super.setBackgroundResource(i2);
        d dVar = this.f1265b;
        if (dVar != null) {
            dVar.f(i2);
        }
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        d dVar = this.f1265b;
        if (dVar != null) {
            dVar.h(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        d dVar = this.f1265b;
        if (dVar != null) {
            dVar.i(mode);
        }
    }

    @Override // android.widget.TextView
    public final void setTextAppearance(Context context, int i2) {
        super.setTextAppearance(context, i2);
        z zVar = this.f1266c;
        if (zVar != null) {
            zVar.m(context, i2);
        }
    }
}
