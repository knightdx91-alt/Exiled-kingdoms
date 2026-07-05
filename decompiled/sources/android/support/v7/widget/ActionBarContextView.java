package android.support.v7.widget;

import android.content.Context;
import android.support.v7.widget.ActionMenuPresenter;
import android.support.v7.widget.a;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.widget.LinearLayout;
import android.widget.TextView;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class ActionBarContextView extends android.support.v7.widget.a {

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private CharSequence f1030j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private CharSequence f1031k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private View f1032l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private View f1033m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private LinearLayout f1034n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private TextView f1035o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private TextView f1036p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private int f1037q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private int f1038r;
    private boolean s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    private int f1039t;

    final class a implements View.OnClickListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ n.b f1040b;

        a(n.b bVar) {
            this.f1040b = bVar;
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f1040b.c();
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public ActionBarContextView(Context context, AttributeSet attributeSet) {
        int i2 = k.a.actionModeStyle;
        super(context, attributeSet, i2);
        x0 x0VarT = x0.t(context, attributeSet, k.j.ActionMode, i2);
        setBackground(x0VarT.f(k.j.ActionMode_background));
        this.f1037q = x0VarT.m(k.j.ActionMode_titleTextStyle, 0);
        this.f1038r = x0VarT.m(k.j.ActionMode_subtitleTextStyle, 0);
        this.f1173f = x0VarT.l(k.j.ActionMode_height, 0);
        this.f1039t = x0VarT.m(k.j.ActionMode_closeItemLayout, k.g.abc_action_mode_close_item_material);
        x0VarT.u();
    }

    private void g() {
        if (this.f1034n == null) {
            LayoutInflater.from(getContext()).inflate(k.g.abc_action_bar_title_item, this);
            LinearLayout linearLayout = (LinearLayout) getChildAt(getChildCount() - 1);
            this.f1034n = linearLayout;
            this.f1035o = (TextView) linearLayout.findViewById(k.f.action_bar_title);
            this.f1036p = (TextView) this.f1034n.findViewById(k.f.action_bar_subtitle);
            int i2 = this.f1037q;
            if (i2 != 0) {
                this.f1035o.setTextAppearance(getContext(), i2);
            }
            int i3 = this.f1038r;
            if (i3 != 0) {
                this.f1036p.setTextAppearance(getContext(), i3);
            }
        }
        this.f1035o.setText(this.f1030j);
        this.f1036p.setText(this.f1031k);
        boolean zIsEmpty = TextUtils.isEmpty(this.f1030j);
        boolean zIsEmpty2 = TextUtils.isEmpty(this.f1031k);
        this.f1036p.setVisibility(!zIsEmpty2 ? 0 : 8);
        this.f1034n.setVisibility((zIsEmpty && zIsEmpty2) ? 8 : 0);
        if (this.f1034n.getParent() == null) {
            addView(this.f1034n);
        }
    }

    public final void e() {
        if (this.f1032l == null) {
            i();
        }
    }

    public final void f(n.b bVar) {
        View view = this.f1032l;
        if (view == null) {
            View viewInflate = LayoutInflater.from(getContext()).inflate(this.f1039t, (ViewGroup) this, false);
            this.f1032l = viewInflate;
            addView(viewInflate);
        } else if (view.getParent() == null) {
            addView(this.f1032l);
        }
        this.f1032l.findViewById(k.f.action_mode_close_button).setOnClickListener(new a(bVar));
        android.support.v7.view.menu.h hVarE = bVar.e();
        ActionMenuPresenter actionMenuPresenter = this.f1172e;
        if (actionMenuPresenter != null) {
            actionMenuPresenter.v();
            ActionMenuPresenter.a aVar = actionMenuPresenter.f1079v;
            if (aVar != null) {
                aVar.a();
            }
        }
        ActionMenuPresenter actionMenuPresenter2 = new ActionMenuPresenter(getContext());
        this.f1172e = actionMenuPresenter2;
        actionMenuPresenter2.B();
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-2, -1);
        hVarE.c(this.f1172e, this.f1170c);
        ActionMenuView actionMenuView = (ActionMenuView) this.f1172e.m(this);
        this.f1171d = actionMenuView;
        actionMenuView.setBackground(null);
        addView(this.f1171d, layoutParams);
    }

    @Override // android.view.ViewGroup
    protected final ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new ViewGroup.MarginLayoutParams(-1, -2);
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new ViewGroup.MarginLayoutParams(getContext(), attributeSet);
    }

    @Override // android.support.v7.widget.a
    public /* bridge */ /* synthetic */ int getAnimatedVisibility() {
        return super.getAnimatedVisibility();
    }

    @Override // android.support.v7.widget.a
    public /* bridge */ /* synthetic */ int getContentHeight() {
        return super.getContentHeight();
    }

    public CharSequence getSubtitle() {
        return this.f1031k;
    }

    public CharSequence getTitle() {
        return this.f1030j;
    }

    public final boolean h() {
        return this.s;
    }

    public final void i() {
        removeAllViews();
        this.f1033m = null;
        this.f1171d = null;
    }

    public final android.support.v4.view.l j(int i2, long j2) {
        android.support.v4.view.l lVar = this.f1174g;
        if (lVar != null) {
            lVar.b();
        }
        a.C0016a c0016a = this.f1169b;
        if (i2 != 0) {
            android.support.v4.view.l lVarA = android.support.v4.view.h.a(this);
            lVarA.a(0.0f);
            lVarA.d(j2);
            android.support.v7.widget.a.this.f1174g = lVarA;
            c0016a.f1178b = i2;
            lVarA.f(c0016a);
            return lVarA;
        }
        if (getVisibility() != 0) {
            setAlpha(0.0f);
        }
        android.support.v4.view.l lVarA2 = android.support.v4.view.h.a(this);
        lVarA2.a(1.0f);
        lVarA2.d(j2);
        android.support.v7.widget.a.this.f1174g = lVarA2;
        c0016a.f1178b = i2;
        lVarA2.f(c0016a);
        return lVarA2;
    }

    public final void k() {
        ActionMenuPresenter actionMenuPresenter = this.f1172e;
        if (actionMenuPresenter != null) {
            actionMenuPresenter.C();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ActionMenuPresenter actionMenuPresenter = this.f1172e;
        if (actionMenuPresenter != null) {
            actionMenuPresenter.v();
            ActionMenuPresenter.a aVar = this.f1172e.f1079v;
            if (aVar != null) {
                aVar.a();
            }
        }
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        if (accessibilityEvent.getEventType() != 32) {
            super.onInitializeAccessibilityEvent(accessibilityEvent);
            return;
        }
        accessibilityEvent.setSource(this);
        accessibilityEvent.setClassName(getClass().getName());
        accessibilityEvent.setPackageName(getContext().getPackageName());
        accessibilityEvent.setContentDescription(this.f1030j);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected final void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        int i6 = b1.f1197b;
        boolean z3 = getLayoutDirection() == 1;
        int paddingRight = z3 ? (i4 - i2) - getPaddingRight() : getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingTop2 = ((i5 - i3) - getPaddingTop()) - getPaddingBottom();
        View view = this.f1032l;
        if (view != null && view.getVisibility() != 8) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.f1032l.getLayoutParams();
            int i7 = z3 ? marginLayoutParams.rightMargin : marginLayoutParams.leftMargin;
            int i8 = z3 ? marginLayoutParams.leftMargin : marginLayoutParams.rightMargin;
            int i9 = z3 ? paddingRight - i7 : paddingRight + i7;
            int iD = i9 + android.support.v7.widget.a.d(this.f1032l, i9, paddingTop, paddingTop2, z3);
            paddingRight = z3 ? iD - i8 : iD + i8;
        }
        LinearLayout linearLayout = this.f1034n;
        if (linearLayout != null && this.f1033m == null && linearLayout.getVisibility() != 8) {
            paddingRight += android.support.v7.widget.a.d(this.f1034n, paddingRight, paddingTop, paddingTop2, z3);
        }
        View view2 = this.f1033m;
        if (view2 != null) {
            android.support.v7.widget.a.d(view2, paddingRight, paddingTop, paddingTop2, z3);
        }
        int paddingLeft = z3 ? getPaddingLeft() : (i4 - i2) - getPaddingRight();
        ActionMenuView actionMenuView = this.f1171d;
        if (actionMenuView != null) {
            android.support.v7.widget.a.d(actionMenuView, paddingLeft, paddingTop, paddingTop2, !z3);
        }
    }

    @Override // android.view.View
    protected final void onMeasure(int i2, int i3) {
        if (View.MeasureSpec.getMode(i2) != 1073741824) {
            throw new IllegalStateException(getClass().getSimpleName().concat(" can only be used with android:layout_width=\"match_parent\" (or fill_parent)"));
        }
        if (View.MeasureSpec.getMode(i3) == 0) {
            throw new IllegalStateException(getClass().getSimpleName().concat(" can only be used with android:layout_height=\"wrap_content\""));
        }
        int size = View.MeasureSpec.getSize(i2);
        int size2 = this.f1173f;
        if (size2 <= 0) {
            size2 = View.MeasureSpec.getSize(i3);
        }
        int paddingBottom = getPaddingBottom() + getPaddingTop();
        int paddingLeft = (size - getPaddingLeft()) - getPaddingRight();
        int iMin = size2 - paddingBottom;
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(iMin, Integer.MIN_VALUE);
        View view = this.f1032l;
        if (view != null) {
            int iC = android.support.v7.widget.a.c(view, paddingLeft, iMakeMeasureSpec);
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.f1032l.getLayoutParams();
            paddingLeft = iC - (marginLayoutParams.leftMargin + marginLayoutParams.rightMargin);
        }
        ActionMenuView actionMenuView = this.f1171d;
        if (actionMenuView != null && actionMenuView.getParent() == this) {
            paddingLeft = android.support.v7.widget.a.c(this.f1171d, paddingLeft, iMakeMeasureSpec);
        }
        LinearLayout linearLayout = this.f1034n;
        if (linearLayout != null && this.f1033m == null) {
            if (this.s) {
                this.f1034n.measure(View.MeasureSpec.makeMeasureSpec(0, 0), iMakeMeasureSpec);
                int measuredWidth = this.f1034n.getMeasuredWidth();
                boolean z2 = measuredWidth <= paddingLeft;
                if (z2) {
                    paddingLeft -= measuredWidth;
                }
                this.f1034n.setVisibility(z2 ? 0 : 8);
            } else {
                paddingLeft = android.support.v7.widget.a.c(linearLayout, paddingLeft, iMakeMeasureSpec);
            }
        }
        View view2 = this.f1033m;
        if (view2 != null) {
            ViewGroup.LayoutParams layoutParams = view2.getLayoutParams();
            int i4 = layoutParams.width;
            int i5 = i4 != -2 ? 1073741824 : Integer.MIN_VALUE;
            if (i4 >= 0) {
                paddingLeft = Math.min(i4, paddingLeft);
            }
            int i6 = layoutParams.height;
            int i7 = i6 == -2 ? Integer.MIN_VALUE : 1073741824;
            if (i6 >= 0) {
                iMin = Math.min(i6, iMin);
            }
            this.f1033m.measure(View.MeasureSpec.makeMeasureSpec(paddingLeft, i5), View.MeasureSpec.makeMeasureSpec(iMin, i7));
        }
        if (this.f1173f > 0) {
            setMeasuredDimension(size, size2);
            return;
        }
        int childCount = getChildCount();
        int i8 = 0;
        for (int i9 = 0; i9 < childCount; i9++) {
            int measuredHeight = getChildAt(i9).getMeasuredHeight() + paddingBottom;
            if (measuredHeight > i8) {
                i8 = measuredHeight;
            }
        }
        setMeasuredDimension(size, i8);
    }

    @Override // android.support.v7.widget.a
    public void setContentHeight(int i2) {
        this.f1173f = i2;
    }

    public void setCustomView(View view) {
        LinearLayout linearLayout;
        View view2 = this.f1033m;
        if (view2 != null) {
            removeView(view2);
        }
        this.f1033m = view;
        if (view != null && (linearLayout = this.f1034n) != null) {
            removeView(linearLayout);
            this.f1034n = null;
        }
        if (view != null) {
            addView(view);
        }
        requestLayout();
    }

    public void setSubtitle(CharSequence charSequence) {
        this.f1031k = charSequence;
        g();
    }

    public void setTitle(CharSequence charSequence) {
        this.f1030j = charSequence;
        g();
    }

    public void setTitleOptional(boolean z2) {
        if (z2 != this.s) {
            requestLayout();
        }
        this.s = z2;
    }

    @Override // android.support.v7.widget.a, android.view.View
    public /* bridge */ /* synthetic */ void setVisibility(int i2) {
        super.setVisibility(i2);
    }

    @Override // android.view.ViewGroup
    public final boolean shouldDelayChildPressedState() {
        return false;
    }
}
