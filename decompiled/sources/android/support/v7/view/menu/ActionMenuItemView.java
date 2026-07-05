package android.support.v7.view.menu;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.support.v7.view.menu.h;
import android.support.v7.view.menu.p;
import android.support.v7.widget.ActionMenuView;
import android.support.v7.widget.a0;
import android.support.v7.widget.h0;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class ActionMenuItemView extends a0 implements p.a, View.OnClickListener, ActionMenuView.a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    j f838d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private CharSequence f839e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private Drawable f840f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    h.b f841g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private h0 f842h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    b f843i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private boolean f844j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private boolean f845k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private int f846l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private int f847m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private int f848n;

    private class a extends h0 {
        public a() {
            super(ActionMenuItemView.this);
        }

        @Override // android.support.v7.widget.h0
        public final o.b b() {
            b bVar = ActionMenuItemView.this.f843i;
            if (bVar != null) {
                return bVar.a();
            }
            return null;
        }

        @Override // android.support.v7.widget.h0
        protected final boolean c() {
            o.b bVarB;
            ActionMenuItemView actionMenuItemView = ActionMenuItemView.this;
            h.b bVar = actionMenuItemView.f841g;
            return bVar != null && bVar.b(actionMenuItemView.f838d) && (bVarB = b()) != null && bVarB.k();
        }
    }

    public static abstract class b {
        public abstract o.b a();
    }

    public ActionMenuItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        Resources resources = context.getResources();
        this.f844j = e();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, k.j.ActionMenuItemView, 0, 0);
        this.f846l = typedArrayObtainStyledAttributes.getDimensionPixelSize(k.j.ActionMenuItemView_android_minWidth, 0);
        typedArrayObtainStyledAttributes.recycle();
        this.f848n = (int) ((resources.getDisplayMetrics().density * 32.0f) + 0.5f);
        setOnClickListener(this);
        this.f847m = -1;
        setSaveEnabled(false);
    }

    private boolean e() {
        Configuration configuration = getContext().getResources().getConfiguration();
        int i2 = configuration.screenWidthDp;
        return i2 >= 480 || (i2 >= 640 && configuration.screenHeightDp >= 480) || configuration.orientation == 2;
    }

    private void f() {
        boolean z2 = true;
        boolean z3 = !TextUtils.isEmpty(this.f839e);
        if (this.f840f != null && (!this.f838d.s() || (!this.f844j && !this.f845k))) {
            z2 = false;
        }
        boolean z4 = z3 & z2;
        setText(z4 ? this.f839e : null);
        CharSequence contentDescription = this.f838d.getContentDescription();
        if (TextUtils.isEmpty(contentDescription)) {
            setContentDescription(z4 ? null : this.f838d.getTitle());
        } else {
            setContentDescription(contentDescription);
        }
        CharSequence tooltipText = this.f838d.getTooltipText();
        if (TextUtils.isEmpty(tooltipText)) {
            setTooltipText(z4 ? null : this.f838d.getTitle());
        } else {
            setTooltipText(tooltipText);
        }
    }

    @Override // android.support.v7.view.menu.p.a
    public final void a(j jVar) {
        this.f838d = jVar;
        setIcon(jVar.getIcon());
        setTitle(jVar.f(this));
        setId(jVar.getItemId());
        setVisibility(jVar.isVisible() ? 0 : 8);
        setEnabled(jVar.isEnabled());
        if (jVar.hasSubMenu() && this.f842h == null) {
            this.f842h = new a();
        }
    }

    @Override // android.support.v7.view.menu.p.a
    public final boolean b() {
        return true;
    }

    @Override // android.support.v7.widget.ActionMenuView.a
    public final boolean c() {
        return !TextUtils.isEmpty(getText());
    }

    @Override // android.support.v7.widget.ActionMenuView.a
    public final boolean d() {
        return !TextUtils.isEmpty(getText()) && this.f838d.getIcon() == null;
    }

    @Override // android.support.v7.view.menu.p.a
    public j getItemData() {
        return this.f838d;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        h.b bVar = this.f841g;
        if (bVar != null) {
            bVar.b(this.f838d);
        }
    }

    @Override // android.widget.TextView, android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.f844j = e();
        f();
    }

    @Override // android.widget.TextView, android.view.View
    protected final void onMeasure(int i2, int i3) {
        int i4;
        boolean zIsEmpty = TextUtils.isEmpty(getText());
        if (!zIsEmpty && (i4 = this.f847m) >= 0) {
            super.setPadding(i4, getPaddingTop(), getPaddingRight(), getPaddingBottom());
        }
        super.onMeasure(i2, i3);
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        int measuredWidth = getMeasuredWidth();
        int i5 = this.f846l;
        int iMin = mode == Integer.MIN_VALUE ? Math.min(size, i5) : i5;
        if (mode != 1073741824 && i5 > 0 && measuredWidth < iMin) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(iMin, 1073741824), i3);
        }
        if (!zIsEmpty || this.f840f == null) {
            return;
        }
        super.setPadding((getMeasuredWidth() - this.f840f.getBounds().width()) / 2, getPaddingTop(), getPaddingRight(), getPaddingBottom());
    }

    @Override // android.widget.TextView, android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        super.onRestoreInstanceState(null);
    }

    @Override // android.widget.TextView, android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        h0 h0Var;
        if (this.f838d.hasSubMenu() && (h0Var = this.f842h) != null && h0Var.onTouch(this, motionEvent)) {
            return true;
        }
        return super.onTouchEvent(motionEvent);
    }

    public void setCheckable(boolean z2) {
    }

    public void setChecked(boolean z2) {
    }

    public void setExpandedFormat(boolean z2) {
        if (this.f845k != z2) {
            this.f845k = z2;
            j jVar = this.f838d;
            if (jVar != null) {
                jVar.f965n.u();
            }
        }
    }

    public void setIcon(Drawable drawable) {
        this.f840f = drawable;
        if (drawable != null) {
            int intrinsicWidth = drawable.getIntrinsicWidth();
            int intrinsicHeight = drawable.getIntrinsicHeight();
            int i2 = this.f848n;
            if (intrinsicWidth > i2) {
                intrinsicHeight = (int) (intrinsicHeight * (i2 / intrinsicWidth));
                intrinsicWidth = i2;
            }
            if (intrinsicHeight > i2) {
                intrinsicWidth = (int) (intrinsicWidth * (i2 / intrinsicHeight));
            } else {
                i2 = intrinsicHeight;
            }
            drawable.setBounds(0, 0, intrinsicWidth, i2);
        }
        setCompoundDrawables(drawable, null, null, null);
        f();
    }

    public void setItemInvoker(h.b bVar) {
        this.f841g = bVar;
    }

    @Override // android.widget.TextView, android.view.View
    public final void setPadding(int i2, int i3, int i4, int i5) {
        this.f847m = i2;
        super.setPadding(i2, i3, i4, i5);
    }

    public void setPopupCallback(b bVar) {
        this.f843i = bVar;
    }

    public void setTitle(CharSequence charSequence) {
        this.f839e = charSequence;
        f();
    }
}
