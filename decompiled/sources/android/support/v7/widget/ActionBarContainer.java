package android.support.v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.google.android.gms.common.api.Api;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class ActionBarContainer extends FrameLayout {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private boolean f1020b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private q0 f1021c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private View f1022d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private View f1023e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    Drawable f1024f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    Drawable f1025g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    Drawable f1026h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    boolean f1027i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    boolean f1028j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private int f1029k;

    public ActionBarContainer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setBackground(new b(this));
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, k.j.ActionBar);
        this.f1024f = typedArrayObtainStyledAttributes.getDrawable(k.j.ActionBar_background);
        this.f1025g = typedArrayObtainStyledAttributes.getDrawable(k.j.ActionBar_backgroundStacked);
        this.f1029k = typedArrayObtainStyledAttributes.getDimensionPixelSize(k.j.ActionBar_height, -1);
        boolean z2 = true;
        if (getId() == k.f.split_action_bar) {
            this.f1027i = true;
            this.f1026h = typedArrayObtainStyledAttributes.getDrawable(k.j.ActionBar_backgroundSplit);
        }
        typedArrayObtainStyledAttributes.recycle();
        if (!this.f1027i ? this.f1024f != null || this.f1025g != null : this.f1026h != null) {
            z2 = false;
        }
        setWillNotDraw(z2);
    }

    private static int a(View view) {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
        return view.getMeasuredHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected final void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable drawable = this.f1024f;
        if (drawable != null && drawable.isStateful()) {
            this.f1024f.setState(getDrawableState());
        }
        Drawable drawable2 = this.f1025g;
        if (drawable2 != null && drawable2.isStateful()) {
            this.f1025g.setState(getDrawableState());
        }
        Drawable drawable3 = this.f1026h;
        if (drawable3 == null || !drawable3.isStateful()) {
            return;
        }
        this.f1026h.setState(getDrawableState());
    }

    public View getTabContainer() {
        return this.f1021c;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        Drawable drawable = this.f1024f;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
        Drawable drawable2 = this.f1025g;
        if (drawable2 != null) {
            drawable2.jumpToCurrentState();
        }
        Drawable drawable3 = this.f1026h;
        if (drawable3 != null) {
            drawable3.jumpToCurrentState();
        }
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        this.f1022d = findViewById(k.f.action_bar);
        this.f1023e = findViewById(k.f.action_context_bar);
    }

    @Override // android.view.View
    public final boolean onHoverEvent(MotionEvent motionEvent) {
        super.onHoverEvent(motionEvent);
        return true;
    }

    @Override // android.view.ViewGroup
    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return this.f1020b || super.onInterceptTouchEvent(motionEvent);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0048 A[PHI: r0
      0x0048: PHI (r0v8 boolean) = (r0v1 boolean), (r0v1 boolean), (r0v0 boolean) binds: [B:31:0x00a5, B:33:0x00a9, B:15:0x0039] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        Drawable drawable;
        super.onLayout(z2, i2, i3, i4, i5);
        q0 q0Var = this.f1021c;
        boolean z3 = true;
        boolean z4 = false;
        boolean z5 = (q0Var == null || q0Var.getVisibility() == 8) ? false : true;
        if (q0Var != null && q0Var.getVisibility() != 8) {
            int measuredHeight = getMeasuredHeight();
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) q0Var.getLayoutParams();
            int measuredHeight2 = measuredHeight - q0Var.getMeasuredHeight();
            int i6 = layoutParams.bottomMargin;
            q0Var.layout(i2, measuredHeight2 - i6, i4, measuredHeight - i6);
        }
        if (this.f1027i) {
            Drawable drawable2 = this.f1026h;
            if (drawable2 != null) {
                drawable2.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
            } else {
                z3 = z4;
            }
        } else {
            if (this.f1024f != null) {
                if (this.f1022d.getVisibility() == 0) {
                    this.f1024f.setBounds(this.f1022d.getLeft(), this.f1022d.getTop(), this.f1022d.getRight(), this.f1022d.getBottom());
                } else {
                    View view = this.f1023e;
                    if (view == null || view.getVisibility() != 0) {
                        this.f1024f.setBounds(0, 0, 0, 0);
                    } else {
                        this.f1024f.setBounds(this.f1023e.getLeft(), this.f1023e.getTop(), this.f1023e.getRight(), this.f1023e.getBottom());
                    }
                }
                z4 = true;
            }
            this.f1028j = z5;
            if (z5 && (drawable = this.f1025g) != null) {
                drawable.setBounds(q0Var.getLeft(), q0Var.getTop(), q0Var.getRight(), q0Var.getBottom());
            }
        }
        if (z3) {
            invalidate();
        }
    }

    @Override // android.widget.FrameLayout, android.view.View
    public final void onMeasure(int i2, int i3) {
        int iA;
        int i4;
        if (this.f1022d == null && View.MeasureSpec.getMode(i3) == Integer.MIN_VALUE && (i4 = this.f1029k) >= 0) {
            i3 = View.MeasureSpec.makeMeasureSpec(Math.min(i4, View.MeasureSpec.getSize(i3)), Integer.MIN_VALUE);
        }
        super.onMeasure(i2, i3);
        if (this.f1022d == null) {
            return;
        }
        int mode = View.MeasureSpec.getMode(i3);
        q0 q0Var = this.f1021c;
        if (q0Var == null || q0Var.getVisibility() == 8 || mode == 1073741824) {
            return;
        }
        View view = this.f1022d;
        if (view == null || view.getVisibility() == 8 || view.getMeasuredHeight() == 0) {
            View view2 = this.f1023e;
            iA = (view2 == null || view2.getVisibility() == 8 || view2.getMeasuredHeight() == 0) ? 0 : a(this.f1023e);
        } else {
            iA = a(this.f1022d);
        }
        setMeasuredDimension(getMeasuredWidth(), Math.min(iA + a(this.f1021c), mode == Integer.MIN_VALUE ? View.MeasureSpec.getSize(i3) : Api.BaseClientBuilder.API_PRIORITY_OTHER));
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        super.onTouchEvent(motionEvent);
        return true;
    }

    public void setPrimaryBackground(Drawable drawable) {
        Drawable drawable2 = this.f1024f;
        if (drawable2 != null) {
            drawable2.setCallback(null);
            unscheduleDrawable(this.f1024f);
        }
        this.f1024f = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
            View view = this.f1022d;
            if (view != null) {
                this.f1024f.setBounds(view.getLeft(), this.f1022d.getTop(), this.f1022d.getRight(), this.f1022d.getBottom());
            }
        }
        boolean z2 = false;
        if (!this.f1027i ? !(this.f1024f != null || this.f1025g != null) : this.f1026h == null) {
            z2 = true;
        }
        setWillNotDraw(z2);
        invalidate();
    }

    public void setSplitBackground(Drawable drawable) {
        Drawable drawable2;
        Drawable drawable3 = this.f1026h;
        if (drawable3 != null) {
            drawable3.setCallback(null);
            unscheduleDrawable(this.f1026h);
        }
        this.f1026h = drawable;
        boolean z2 = this.f1027i;
        boolean z3 = false;
        if (drawable != null) {
            drawable.setCallback(this);
            if (z2 && (drawable2 = this.f1026h) != null) {
                drawable2.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
            }
        }
        if (!z2 ? !(this.f1024f != null || this.f1025g != null) : this.f1026h == null) {
            z3 = true;
        }
        setWillNotDraw(z3);
        invalidate();
    }

    public void setStackedBackground(Drawable drawable) {
        Drawable drawable2;
        Drawable drawable3 = this.f1025g;
        if (drawable3 != null) {
            drawable3.setCallback(null);
            unscheduleDrawable(this.f1025g);
        }
        this.f1025g = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
            if (this.f1028j && (drawable2 = this.f1025g) != null) {
                drawable2.setBounds(this.f1021c.getLeft(), this.f1021c.getTop(), this.f1021c.getRight(), this.f1021c.getBottom());
            }
        }
        boolean z2 = false;
        if (!this.f1027i ? !(this.f1024f != null || this.f1025g != null) : this.f1026h == null) {
            z2 = true;
        }
        setWillNotDraw(z2);
        invalidate();
    }

    public void setTabContainer(q0 q0Var) {
        q0 q0Var2 = this.f1021c;
        if (q0Var2 != null) {
            removeView(q0Var2);
        }
        this.f1021c = q0Var;
        if (q0Var != null) {
            addView(q0Var);
            ViewGroup.LayoutParams layoutParams = q0Var.getLayoutParams();
            layoutParams.width = -1;
            layoutParams.height = -2;
            q0Var.setAllowCollapse(false);
        }
    }

    public void setTransitioning(boolean z2) {
        this.f1020b = z2;
        setDescendantFocusability(z2 ? 393216 : 262144);
    }

    @Override // android.view.View
    public void setVisibility(int i2) {
        super.setVisibility(i2);
        boolean z2 = i2 == 0;
        Drawable drawable = this.f1024f;
        if (drawable != null) {
            drawable.setVisible(z2, false);
        }
        Drawable drawable2 = this.f1025g;
        if (drawable2 != null) {
            drawable2.setVisible(z2, false);
        }
        Drawable drawable3 = this.f1026h;
        if (drawable3 != null) {
            drawable3.setVisible(z2, false);
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final ActionMode startActionModeForChild(View view, ActionMode.Callback callback) {
        return null;
    }

    @Override // android.view.View
    protected final boolean verifyDrawable(Drawable drawable) {
        Drawable drawable2 = this.f1024f;
        boolean z2 = this.f1027i;
        return (drawable == drawable2 && !z2) || (drawable == this.f1025g && this.f1028j) || ((drawable == this.f1026h && z2) || super.verifyDrawable(drawable));
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final ActionMode startActionModeForChild(View view, ActionMode.Callback callback, int i2) {
        if (i2 != 0) {
            return super.startActionModeForChild(view, callback, i2);
        }
        return null;
    }
}
