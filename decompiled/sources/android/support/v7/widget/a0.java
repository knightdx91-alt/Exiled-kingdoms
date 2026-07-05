package android.support.v7.widget;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.TextView;

/* JADX INFO: compiled from: AppCompatTextView.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class a0 extends TextView implements android.support.v4.widget.b {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final d f1180b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final z f1181c;

    public a0(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.textViewStyle);
    }

    @Override // android.widget.TextView, android.view.View
    protected final void drawableStateChanged() {
        super.drawableStateChanged();
        d dVar = this.f1180b;
        if (dVar != null) {
            dVar.a();
        }
        z zVar = this.f1181c;
        if (zVar != null) {
            zVar.c();
        }
    }

    @Override // android.widget.TextView
    public int getAutoSizeMaxTextSize() {
        if (android.support.v4.widget.b.f673a) {
            return super.getAutoSizeMaxTextSize();
        }
        z zVar = this.f1181c;
        if (zVar != null) {
            return zVar.f();
        }
        return -1;
    }

    @Override // android.widget.TextView
    public int getAutoSizeMinTextSize() {
        if (android.support.v4.widget.b.f673a) {
            return super.getAutoSizeMinTextSize();
        }
        z zVar = this.f1181c;
        if (zVar != null) {
            return zVar.g();
        }
        return -1;
    }

    @Override // android.widget.TextView
    public int getAutoSizeStepGranularity() {
        if (android.support.v4.widget.b.f673a) {
            return super.getAutoSizeStepGranularity();
        }
        z zVar = this.f1181c;
        if (zVar != null) {
            return zVar.h();
        }
        return -1;
    }

    @Override // android.widget.TextView
    public int[] getAutoSizeTextAvailableSizes() {
        if (android.support.v4.widget.b.f673a) {
            return super.getAutoSizeTextAvailableSizes();
        }
        z zVar = this.f1181c;
        return zVar != null ? zVar.i() : new int[0];
    }

    @Override // android.widget.TextView
    public int getAutoSizeTextType() {
        if (android.support.v4.widget.b.f673a) {
            return super.getAutoSizeTextType() == 1 ? 1 : 0;
        }
        z zVar = this.f1181c;
        if (zVar != null) {
            return zVar.j();
        }
        return 0;
    }

    public ColorStateList getSupportBackgroundTintList() {
        d dVar = this.f1180b;
        if (dVar != null) {
            return dVar.b();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        d dVar = this.f1180b;
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

    @Override // android.widget.TextView, android.view.View
    protected final void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        super.onLayout(z2, i2, i3, i4, i5);
        z zVar = this.f1181c;
        if (zVar == null || android.support.v4.widget.b.f673a) {
            return;
        }
        zVar.d();
    }

    @Override // android.widget.TextView
    protected final void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
        super.onTextChanged(charSequence, i2, i3, i4);
        z zVar = this.f1181c;
        if (zVar == null || android.support.v4.widget.b.f673a || !zVar.k()) {
            return;
        }
        zVar.d();
    }

    @Override // android.widget.TextView
    public final void setAutoSizeTextTypeUniformWithConfiguration(int i2, int i3, int i4, int i5) {
        if (android.support.v4.widget.b.f673a) {
            super.setAutoSizeTextTypeUniformWithConfiguration(i2, i3, i4, i5);
            return;
        }
        z zVar = this.f1181c;
        if (zVar != null) {
            zVar.n(i2, i3, i4, i5);
        }
    }

    @Override // android.widget.TextView
    public final void setAutoSizeTextTypeUniformWithPresetSizes(int[] iArr, int i2) {
        if (android.support.v4.widget.b.f673a) {
            super.setAutoSizeTextTypeUniformWithPresetSizes(iArr, i2);
            return;
        }
        z zVar = this.f1181c;
        if (zVar != null) {
            zVar.o(iArr, i2);
        }
    }

    @Override // android.widget.TextView
    public void setAutoSizeTextTypeWithDefaults(int i2) {
        if (android.support.v4.widget.b.f673a) {
            super.setAutoSizeTextTypeWithDefaults(i2);
            return;
        }
        z zVar = this.f1181c;
        if (zVar != null) {
            zVar.p(i2);
        }
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        d dVar = this.f1180b;
        if (dVar != null) {
            dVar.e();
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i2) {
        super.setBackgroundResource(i2);
        d dVar = this.f1180b;
        if (dVar != null) {
            dVar.f(i2);
        }
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        d dVar = this.f1180b;
        if (dVar != null) {
            dVar.h(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        d dVar = this.f1180b;
        if (dVar != null) {
            dVar.i(mode);
        }
    }

    @Override // android.widget.TextView
    public final void setTextAppearance(Context context, int i2) {
        super.setTextAppearance(context, i2);
        z zVar = this.f1181c;
        if (zVar != null) {
            zVar.m(context, i2);
        }
    }

    @Override // android.widget.TextView
    public final void setTextSize(int i2, float f2) {
        if (android.support.v4.widget.b.f673a) {
            super.setTextSize(i2, f2);
            return;
        }
        z zVar = this.f1181c;
        if (zVar != null) {
            zVar.q(i2, f2);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public a0(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        u0.a(context);
        d dVar = new d(this);
        this.f1180b = dVar;
        dVar.d(attributeSet, i2);
        z zVar = new z(this);
        this.f1181c = zVar;
        zVar.l(attributeSet, i2);
        zVar.c();
    }
}
