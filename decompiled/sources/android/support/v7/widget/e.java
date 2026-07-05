package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;

/* JADX INFO: compiled from: AppCompatButton.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class e extends Button implements android.support.v4.widget.b {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final d f1207b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final z f1208c;

    /* JADX WARN: Illegal instructions before constructor call */
    public e(Context context, AttributeSet attributeSet) {
        int i2 = k.a.buttonStyle;
        u0.a(context);
        super(context, attributeSet, i2);
        d dVar = new d(this);
        this.f1207b = dVar;
        dVar.d(attributeSet, i2);
        z zVar = new z(this);
        this.f1208c = zVar;
        zVar.l(attributeSet, i2);
        zVar.c();
    }

    @Override // android.widget.TextView, android.view.View
    protected final void drawableStateChanged() {
        super.drawableStateChanged();
        d dVar = this.f1207b;
        if (dVar != null) {
            dVar.a();
        }
        z zVar = this.f1208c;
        if (zVar != null) {
            zVar.c();
        }
    }

    @Override // android.widget.TextView
    public int getAutoSizeMaxTextSize() {
        if (android.support.v4.widget.b.f673a) {
            return super.getAutoSizeMaxTextSize();
        }
        z zVar = this.f1208c;
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
        z zVar = this.f1208c;
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
        z zVar = this.f1208c;
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
        z zVar = this.f1208c;
        return zVar != null ? zVar.i() : new int[0];
    }

    @Override // android.widget.TextView
    public int getAutoSizeTextType() {
        if (android.support.v4.widget.b.f673a) {
            return super.getAutoSizeTextType() == 1 ? 1 : 0;
        }
        z zVar = this.f1208c;
        if (zVar != null) {
            return zVar.j();
        }
        return 0;
    }

    public ColorStateList getSupportBackgroundTintList() {
        d dVar = this.f1207b;
        if (dVar != null) {
            return dVar.b();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        d dVar = this.f1207b;
        if (dVar != null) {
            return dVar.c();
        }
        return null;
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(Button.class.getName());
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(Button.class.getName());
    }

    @Override // android.widget.TextView, android.view.View
    protected final void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        super.onLayout(z2, i2, i3, i4, i5);
        z zVar = this.f1208c;
        if (zVar == null || android.support.v4.widget.b.f673a) {
            return;
        }
        zVar.d();
    }

    @Override // android.widget.TextView
    protected final void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
        super.onTextChanged(charSequence, i2, i3, i4);
        z zVar = this.f1208c;
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
        z zVar = this.f1208c;
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
        z zVar = this.f1208c;
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
        z zVar = this.f1208c;
        if (zVar != null) {
            zVar.p(i2);
        }
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        d dVar = this.f1207b;
        if (dVar != null) {
            dVar.e();
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i2) {
        super.setBackgroundResource(i2);
        d dVar = this.f1207b;
        if (dVar != null) {
            dVar.f(i2);
        }
    }

    public void setSupportAllCaps(boolean z2) {
        z zVar = this.f1208c;
        if (zVar != null) {
            zVar.f1378a.setAllCaps(z2);
        }
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        d dVar = this.f1207b;
        if (dVar != null) {
            dVar.h(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        d dVar = this.f1207b;
        if (dVar != null) {
            dVar.i(mode);
        }
    }

    @Override // android.widget.TextView
    public final void setTextAppearance(Context context, int i2) {
        super.setTextAppearance(context, i2);
        z zVar = this.f1208c;
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
        z zVar = this.f1208c;
        if (zVar != null) {
            zVar.q(i2, f2);
        }
    }
}
