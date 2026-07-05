package android.support.v7.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: compiled from: AbsActionBarView.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
abstract class a extends ViewGroup {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected final C0016a f1169b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected final Context f1170c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    protected ActionMenuView f1171d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    protected ActionMenuPresenter f1172e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    protected int f1173f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    protected android.support.v4.view.l f1174g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private boolean f1175h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private boolean f1176i;

    /* JADX INFO: renamed from: android.support.v7.widget.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AbsActionBarView.java */
    protected class C0016a implements android.support.v4.view.m {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private boolean f1177a = false;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f1178b;

        protected C0016a() {
        }

        @Override // android.support.v4.view.m
        public final void a() {
            if (this.f1177a) {
                return;
            }
            a aVar = a.this;
            aVar.f1174g = null;
            a.super.setVisibility(this.f1178b);
        }

        @Override // android.support.v4.view.m
        public final void b(View view) {
            this.f1177a = true;
        }

        @Override // android.support.v4.view.m
        public final void c() {
            a.super.setVisibility(0);
            this.f1177a = false;
        }
    }

    a(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    protected static int c(View view, int i2, int i3) {
        view.measure(View.MeasureSpec.makeMeasureSpec(i2, Integer.MIN_VALUE), i3);
        return Math.max(0, i2 - view.getMeasuredWidth());
    }

    protected static int d(View view, int i2, int i3, int i4, boolean z2) {
        int measuredWidth = view.getMeasuredWidth();
        int measuredHeight = view.getMeasuredHeight();
        int i5 = ((i4 - measuredHeight) / 2) + i3;
        if (z2) {
            view.layout(i2 - measuredWidth, i5, i2, measuredHeight + i5);
        } else {
            view.layout(i2, i5, i2 + measuredWidth, measuredHeight + i5);
        }
        return z2 ? -measuredWidth : measuredWidth;
    }

    public int getAnimatedVisibility() {
        return this.f1174g != null ? this.f1169b.f1178b : getVisibility();
    }

    public int getContentHeight() {
        return this.f1173f;
    }

    @Override // android.view.View
    protected final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(null, k.j.ActionBar, k.a.actionBarStyle, 0);
        setContentHeight(typedArrayObtainStyledAttributes.getLayoutDimension(k.j.ActionBar_height, 0));
        typedArrayObtainStyledAttributes.recycle();
        ActionMenuPresenter actionMenuPresenter = this.f1172e;
        if (actionMenuPresenter != null) {
            actionMenuPresenter.x();
        }
    }

    @Override // android.view.View
    public boolean onHoverEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 9) {
            this.f1176i = false;
        }
        if (!this.f1176i) {
            boolean zOnHoverEvent = super.onHoverEvent(motionEvent);
            if (actionMasked == 9 && !zOnHoverEvent) {
                this.f1176i = true;
            }
        }
        if (actionMasked == 10 || actionMasked == 3) {
            this.f1176i = false;
        }
        return true;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.f1175h = false;
        }
        if (!this.f1175h) {
            boolean zOnTouchEvent = super.onTouchEvent(motionEvent);
            if (actionMasked == 0 && !zOnTouchEvent) {
                this.f1175h = true;
            }
        }
        if (actionMasked == 1 || actionMasked == 3) {
            this.f1175h = false;
        }
        return true;
    }

    public void setContentHeight(int i2) {
        this.f1173f = i2;
        requestLayout();
    }

    @Override // android.view.View
    public void setVisibility(int i2) {
        if (i2 != getVisibility()) {
            android.support.v4.view.l lVar = this.f1174g;
            if (lVar != null) {
                lVar.b();
            }
            super.setVisibility(i2);
        }
    }

    a(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f1169b = new C0016a();
        TypedValue typedValue = new TypedValue();
        if (!context.getTheme().resolveAttribute(k.a.actionBarPopupTheme, typedValue, true) || typedValue.resourceId == 0) {
            this.f1170c = context;
        } else {
            this.f1170c = new ContextThemeWrapper(context, typedValue.resourceId);
        }
    }
}
