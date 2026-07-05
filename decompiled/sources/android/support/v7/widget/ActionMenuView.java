package android.support.v7.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.support.v7.view.menu.ActionMenuItemView;
import android.support.v7.view.menu.h;
import android.support.v7.view.menu.o;
import android.support.v7.widget.ActionMenuPresenter;
import android.support.v7.widget.i0;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import com.google.android.gms.common.api.Api;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class ActionMenuView extends i0 implements h.b, android.support.v7.view.menu.p {

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private android.support.v7.view.menu.h f1092q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private Context f1093r;
    private int s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    private boolean f1094t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    private ActionMenuPresenter f1095u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    private boolean f1096v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    private int f1097w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    private int f1098x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    private int f1099y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    e f1100z;

    public interface a {
        boolean c();

        boolean d();
    }

    private static class b implements o.a {
        @Override // android.support.v7.view.menu.o.a
        public final void b(android.support.v7.view.menu.h hVar, boolean z2) {
        }

        @Override // android.support.v7.view.menu.o.a
        public final boolean c(android.support.v7.view.menu.t tVar) {
            return false;
        }
    }

    public static class c extends i0.a {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        @ViewDebug.ExportedProperty
        public boolean f1101c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        @ViewDebug.ExportedProperty
        public int f1102d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        @ViewDebug.ExportedProperty
        public int f1103e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        @ViewDebug.ExportedProperty
        public boolean f1104f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        @ViewDebug.ExportedProperty
        public boolean f1105g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        boolean f1106h;
    }

    private class d implements h.a {
        d() {
        }

        @Override // android.support.v7.view.menu.h.a
        public final boolean a(android.support.v7.view.menu.h hVar, android.support.v7.view.menu.j jVar) {
            e eVar = ActionMenuView.this.f1100z;
            if (eVar == null) {
                return false;
            }
            Toolbar.this.getClass();
            return false;
        }

        @Override // android.support.v7.view.menu.h.a
        public final void b(android.support.v7.view.menu.h hVar) {
            ActionMenuView.this.getClass();
        }
    }

    public interface e {
    }

    public ActionMenuView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setBaselineAligned(false);
        float f2 = context.getResources().getDisplayMetrics().density;
        this.f1098x = (int) (56.0f * f2);
        this.f1099y = (int) (f2 * 4.0f);
        this.f1093r = context;
        this.s = 0;
    }

    protected static c l() {
        c cVar = new c(-2, -2);
        cVar.f1101c = false;
        cVar.f1264b = 16;
        return cVar;
    }

    protected static c m(ViewGroup.LayoutParams layoutParams) {
        c cVar;
        if (layoutParams == null) {
            return l();
        }
        if (layoutParams instanceof c) {
            c cVar2 = (c) layoutParams;
            cVar = new c(cVar2);
            cVar.f1101c = cVar2.f1101c;
        } else {
            cVar = new c(layoutParams);
        }
        if (cVar.f1264b <= 0) {
            cVar.f1264b = 16;
        }
        return cVar;
    }

    @Override // android.support.v7.view.menu.h.b
    public final boolean b(android.support.v7.view.menu.j jVar) {
        return this.f1092q.x(jVar, null, 0);
    }

    @Override // android.support.v7.view.menu.p
    public final void c(android.support.v7.view.menu.h hVar) {
        this.f1092q = hVar;
    }

    @Override // android.support.v7.widget.i0, android.view.ViewGroup
    protected final boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams != null && (layoutParams instanceof c);
    }

    @Override // android.view.View
    public final boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v7.widget.i0
    /* JADX INFO: renamed from: g */
    public final /* bridge */ /* synthetic */ i0.a generateDefaultLayoutParams() {
        return l();
    }

    @Override // android.support.v7.widget.i0, android.view.ViewGroup
    protected final /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return l();
    }

    @Override // android.support.v7.widget.i0, android.view.ViewGroup
    protected final /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return m(layoutParams);
    }

    public Menu getMenu() {
        if (this.f1092q == null) {
            Context context = getContext();
            android.support.v7.view.menu.h hVar = new android.support.v7.view.menu.h(context);
            this.f1092q = hVar;
            hVar.B(new d());
            ActionMenuPresenter actionMenuPresenter = new ActionMenuPresenter(context);
            this.f1095u = actionMenuPresenter;
            actionMenuPresenter.B();
            this.f1095u.d(new b());
            this.f1092q.c(this.f1095u, this.f1093r);
            this.f1095u.z(this);
        }
        return this.f1092q;
    }

    public Drawable getOverflowIcon() {
        getMenu();
        return this.f1095u.u();
    }

    public int getPopupTheme() {
        return this.s;
    }

    public int getWindowAnimations() {
        return 0;
    }

    @Override // android.support.v7.widget.i0
    /* JADX INFO: renamed from: h */
    public final i0.a generateLayoutParams(AttributeSet attributeSet) {
        return new c(getContext(), attributeSet);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v7.widget.i0
    /* JADX INFO: renamed from: i */
    public final /* bridge */ /* synthetic */ i0.a generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return m(layoutParams);
    }

    public final void k() {
        ActionMenuPresenter actionMenuPresenter = this.f1095u;
        if (actionMenuPresenter != null) {
            actionMenuPresenter.v();
            ActionMenuPresenter.a aVar = actionMenuPresenter.f1079v;
            if (aVar != null) {
                aVar.a();
            }
        }
    }

    protected final boolean n(int i2) {
        boolean zC = false;
        if (i2 == 0) {
            return false;
        }
        KeyEvent.Callback childAt = getChildAt(i2 - 1);
        KeyEvent.Callback childAt2 = getChildAt(i2);
        if (i2 < getChildCount() && (childAt instanceof a)) {
            zC = ((a) childAt).c();
        }
        return (i2 <= 0 || !(childAt2 instanceof a)) ? zC : zC | ((a) childAt2).d();
    }

    public final boolean o() {
        ActionMenuPresenter actionMenuPresenter = this.f1095u;
        return actionMenuPresenter != null && actionMenuPresenter.v();
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        ActionMenuPresenter actionMenuPresenter = this.f1095u;
        if (actionMenuPresenter != null) {
            actionMenuPresenter.h(false);
            if (this.f1095u.w()) {
                this.f1095u.v();
                this.f1095u.C();
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        k();
    }

    @Override // android.support.v7.widget.i0, android.view.ViewGroup, android.view.View
    protected final void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        int width;
        int paddingLeft;
        if (!this.f1096v) {
            super.onLayout(z2, i2, i3, i4, i5);
            return;
        }
        int childCount = getChildCount();
        int i6 = (i5 - i3) / 2;
        int dividerWidth = getDividerWidth();
        int i7 = i4 - i2;
        int paddingRight = (i7 - getPaddingRight()) - getPaddingLeft();
        int i8 = b1.f1197b;
        boolean z3 = getLayoutDirection() == 1;
        int i9 = 0;
        int i10 = 0;
        for (int i11 = 0; i11 < childCount; i11++) {
            View childAt = getChildAt(i11);
            if (childAt.getVisibility() != 8) {
                c cVar = (c) childAt.getLayoutParams();
                if (cVar.f1101c) {
                    int measuredWidth = childAt.getMeasuredWidth();
                    if (n(i11)) {
                        measuredWidth += dividerWidth;
                    }
                    int measuredHeight = childAt.getMeasuredHeight();
                    if (z3) {
                        paddingLeft = getPaddingLeft() + ((ViewGroup.MarginLayoutParams) cVar).leftMargin;
                        width = paddingLeft + measuredWidth;
                    } else {
                        width = (getWidth() - getPaddingRight()) - ((ViewGroup.MarginLayoutParams) cVar).rightMargin;
                        paddingLeft = width - measuredWidth;
                    }
                    int i12 = i6 - (measuredHeight / 2);
                    childAt.layout(paddingLeft, i12, width, measuredHeight + i12);
                    paddingRight -= measuredWidth;
                    i9 = 1;
                } else {
                    paddingRight -= (childAt.getMeasuredWidth() + ((ViewGroup.MarginLayoutParams) cVar).leftMargin) + ((ViewGroup.MarginLayoutParams) cVar).rightMargin;
                    n(i11);
                    i10++;
                }
            }
        }
        if (childCount == 1 && i9 == 0) {
            View childAt2 = getChildAt(0);
            int measuredWidth2 = childAt2.getMeasuredWidth();
            int measuredHeight2 = childAt2.getMeasuredHeight();
            int i13 = (i7 / 2) - (measuredWidth2 / 2);
            int i14 = i6 - (measuredHeight2 / 2);
            childAt2.layout(i13, i14, measuredWidth2 + i13, measuredHeight2 + i14);
            return;
        }
        int i15 = i10 - (i9 ^ 1);
        int iMax = Math.max(0, i15 > 0 ? paddingRight / i15 : 0);
        if (z3) {
            int width2 = getWidth() - getPaddingRight();
            for (int i16 = 0; i16 < childCount; i16++) {
                View childAt3 = getChildAt(i16);
                c cVar2 = (c) childAt3.getLayoutParams();
                if (childAt3.getVisibility() != 8 && !cVar2.f1101c) {
                    int i17 = width2 - ((ViewGroup.MarginLayoutParams) cVar2).rightMargin;
                    int measuredWidth3 = childAt3.getMeasuredWidth();
                    int measuredHeight3 = childAt3.getMeasuredHeight();
                    int i18 = i6 - (measuredHeight3 / 2);
                    childAt3.layout(i17 - measuredWidth3, i18, i17, measuredHeight3 + i18);
                    width2 = i17 - ((measuredWidth3 + ((ViewGroup.MarginLayoutParams) cVar2).leftMargin) + iMax);
                }
            }
            return;
        }
        int paddingLeft2 = getPaddingLeft();
        for (int i19 = 0; i19 < childCount; i19++) {
            View childAt4 = getChildAt(i19);
            c cVar3 = (c) childAt4.getLayoutParams();
            if (childAt4.getVisibility() != 8 && !cVar3.f1101c) {
                int i20 = paddingLeft2 + ((ViewGroup.MarginLayoutParams) cVar3).leftMargin;
                int measuredWidth4 = childAt4.getMeasuredWidth();
                int measuredHeight4 = childAt4.getMeasuredHeight();
                int i21 = i6 - (measuredHeight4 / 2);
                childAt4.layout(i20, i21, i20 + measuredWidth4, measuredHeight4 + i21);
                paddingLeft2 = measuredWidth4 + ((ViewGroup.MarginLayoutParams) cVar3).rightMargin + iMax + i20;
            }
        }
    }

    @Override // android.support.v7.widget.i0, android.view.View
    protected final void onMeasure(int i2, int i3) {
        int i4;
        boolean z2;
        int i5;
        int i6;
        boolean z3;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        android.support.v7.view.menu.h hVar;
        boolean z4 = this.f1096v;
        boolean z5 = View.MeasureSpec.getMode(i2) == 1073741824;
        this.f1096v = z5;
        if (z4 != z5) {
            this.f1097w = 0;
        }
        int size = View.MeasureSpec.getSize(i2);
        if (this.f1096v && (hVar = this.f1092q) != null && size != this.f1097w) {
            this.f1097w = size;
            hVar.w(true);
        }
        int childCount = getChildCount();
        if (!this.f1096v || childCount <= 0) {
            for (int i14 = 0; i14 < childCount; i14++) {
                c cVar = (c) getChildAt(i14).getLayoutParams();
                ((ViewGroup.MarginLayoutParams) cVar).rightMargin = 0;
                ((ViewGroup.MarginLayoutParams) cVar).leftMargin = 0;
            }
            super.onMeasure(i2, i3);
            return;
        }
        int mode = View.MeasureSpec.getMode(i3);
        int size2 = View.MeasureSpec.getSize(i2);
        int size3 = View.MeasureSpec.getSize(i3);
        int paddingRight = getPaddingRight() + getPaddingLeft();
        int paddingBottom = getPaddingBottom() + getPaddingTop();
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(i3, paddingBottom, -2);
        int i15 = size2 - paddingRight;
        int i16 = this.f1098x;
        int i17 = i15 / i16;
        int i18 = i15 % i16;
        if (i17 == 0) {
            setMeasuredDimension(i15, 0);
            return;
        }
        int i19 = (i18 / i17) + i16;
        int childCount2 = getChildCount();
        int iMax = 0;
        int i20 = 0;
        int iMax2 = 0;
        int i21 = 0;
        boolean z6 = false;
        int i22 = 0;
        long j2 = 0;
        while (true) {
            i4 = this.f1099y;
            if (i21 >= childCount2) {
                break;
            }
            View childAt = getChildAt(i21);
            int i23 = size3;
            int i24 = i15;
            if (childAt.getVisibility() == 8) {
                i11 = mode;
                i12 = paddingBottom;
            } else {
                boolean z7 = childAt instanceof ActionMenuItemView;
                int i25 = i20 + 1;
                if (z7) {
                    childAt.setPadding(i4, 0, i4, 0);
                }
                c cVar2 = (c) childAt.getLayoutParams();
                cVar2.f1106h = false;
                cVar2.f1103e = 0;
                cVar2.f1102d = 0;
                cVar2.f1104f = false;
                ((ViewGroup.MarginLayoutParams) cVar2).leftMargin = 0;
                ((ViewGroup.MarginLayoutParams) cVar2).rightMargin = 0;
                cVar2.f1105g = z7 && !TextUtils.isEmpty(((ActionMenuItemView) childAt).getText());
                int i26 = cVar2.f1101c ? 1 : i17;
                c cVar3 = (c) childAt.getLayoutParams();
                i11 = mode;
                i12 = paddingBottom;
                int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(childMeasureSpec) - paddingBottom, View.MeasureSpec.getMode(childMeasureSpec));
                ActionMenuItemView actionMenuItemView = z7 ? (ActionMenuItemView) childAt : null;
                boolean z8 = (actionMenuItemView == null || TextUtils.isEmpty(actionMenuItemView.getText())) ? false : true;
                if (i26 <= 0 || (z8 && i26 < 2)) {
                    i13 = 0;
                } else {
                    childAt.measure(View.MeasureSpec.makeMeasureSpec(i26 * i19, Integer.MIN_VALUE), iMakeMeasureSpec);
                    int measuredWidth = childAt.getMeasuredWidth();
                    i13 = measuredWidth / i19;
                    if (measuredWidth % i19 != 0) {
                        i13++;
                    }
                    if (z8 && i13 < 2) {
                        i13 = 2;
                    }
                }
                cVar3.f1104f = !cVar3.f1101c && z8;
                cVar3.f1102d = i13;
                childAt.measure(View.MeasureSpec.makeMeasureSpec(i13 * i19, 1073741824), iMakeMeasureSpec);
                iMax2 = Math.max(iMax2, i13);
                if (cVar2.f1104f) {
                    i22++;
                }
                if (cVar2.f1101c) {
                    z6 = true;
                }
                i17 -= i13;
                iMax = Math.max(iMax, childAt.getMeasuredHeight());
                if (i13 == 1) {
                    j2 |= (long) (1 << i21);
                }
                i20 = i25;
            }
            i21++;
            size3 = i23;
            i15 = i24;
            paddingBottom = i12;
            mode = i11;
        }
        int i27 = mode;
        int i28 = i15;
        int i29 = size3;
        boolean z9 = z6 && i20 == 2;
        boolean z10 = false;
        while (i22 > 0 && i17 > 0) {
            int i30 = Api.BaseClientBuilder.API_PRIORITY_OTHER;
            int i31 = 0;
            int i32 = 0;
            long j3 = 0;
            while (i32 < childCount2) {
                c cVar4 = (c) getChildAt(i32).getLayoutParams();
                boolean z11 = z10;
                if (cVar4.f1104f) {
                    int i33 = cVar4.f1102d;
                    if (i33 < i30) {
                        int i34 = iMax;
                        j3 = 1 << i32;
                        i9 = iMax2;
                        i10 = i34;
                        i31 = 1;
                        i30 = i33;
                    } else {
                        int i35 = iMax;
                        if (i33 == i30) {
                            i9 = iMax2;
                            i10 = i35;
                            i31++;
                            j3 |= (long) (1 << i32);
                        } else {
                            i9 = iMax2;
                            i10 = i35;
                        }
                    }
                } else {
                    i10 = iMax;
                    i9 = iMax2;
                }
                i32++;
                iMax2 = i9;
                z10 = z11;
                iMax = i10;
            }
            z2 = z10;
            i5 = iMax;
            i6 = iMax2;
            j2 |= j3;
            if (i31 > i17) {
                break;
            }
            int i36 = i30 + 1;
            for (int i37 = 0; i37 < childCount2; i37++) {
                View childAt2 = getChildAt(i37);
                c cVar5 = (c) childAt2.getLayoutParams();
                long j4 = 1 << i37;
                if ((j3 & j4) != 0) {
                    if (z9 && cVar5.f1105g && i17 == 1) {
                        childAt2.setPadding(i4 + i19, 0, i4, 0);
                    }
                    cVar5.f1102d++;
                    cVar5.f1106h = true;
                    i17--;
                } else if (cVar5.f1102d == i36) {
                    j2 |= j4;
                }
            }
            iMax2 = i6;
            iMax = i5;
            z10 = true;
        }
        z2 = z10;
        i5 = iMax;
        i6 = iMax2;
        boolean z12 = !z6 && i20 == 1;
        if (i17 <= 0 || j2 == 0 || (i17 >= i20 - 1 && !z12 && i6 <= 1)) {
            z3 = z2;
        } else {
            float fBitCount = Long.bitCount(j2);
            if (!z12) {
                if ((j2 & 1) != 0 && !((c) getChildAt(0).getLayoutParams()).f1105g) {
                    fBitCount -= 0.5f;
                }
                int i38 = childCount2 - 1;
                if ((j2 & ((long) (1 << i38))) != 0 && !((c) getChildAt(i38).getLayoutParams()).f1105g) {
                    fBitCount -= 0.5f;
                }
            }
            int i39 = fBitCount > 0.0f ? (int) ((i17 * i19) / fBitCount) : 0;
            boolean z13 = z2;
            for (int i40 = 0; i40 < childCount2; i40++) {
                if ((j2 & ((long) (1 << i40))) != 0) {
                    View childAt3 = getChildAt(i40);
                    c cVar6 = (c) childAt3.getLayoutParams();
                    if (childAt3 instanceof ActionMenuItemView) {
                        cVar6.f1103e = i39;
                        cVar6.f1106h = true;
                        if (i40 == 0 && !cVar6.f1105g) {
                            ((ViewGroup.MarginLayoutParams) cVar6).leftMargin = (-i39) / 2;
                        }
                        z13 = true;
                    } else {
                        if (cVar6.f1101c) {
                            cVar6.f1103e = i39;
                            cVar6.f1106h = true;
                            ((ViewGroup.MarginLayoutParams) cVar6).rightMargin = (-i39) / 2;
                            z13 = true;
                        } else {
                            if (i40 != 0) {
                                ((ViewGroup.MarginLayoutParams) cVar6).leftMargin = i39 / 2;
                            }
                            if (i40 != childCount2 - 1) {
                                ((ViewGroup.MarginLayoutParams) cVar6).rightMargin = i39 / 2;
                            }
                        }
                    }
                }
            }
            z3 = z13;
        }
        if (z3) {
            for (int i41 = 0; i41 < childCount2; i41++) {
                View childAt4 = getChildAt(i41);
                c cVar7 = (c) childAt4.getLayoutParams();
                if (cVar7.f1106h) {
                    childAt4.measure(View.MeasureSpec.makeMeasureSpec((cVar7.f1102d * i19) + cVar7.f1103e, 1073741824), childMeasureSpec);
                }
            }
        }
        if (i27 != 1073741824) {
            i8 = i28;
            i7 = i5;
        } else {
            i7 = i29;
            i8 = i28;
        }
        setMeasuredDimension(i8, i7);
    }

    public final boolean p() {
        ActionMenuPresenter actionMenuPresenter = this.f1095u;
        return actionMenuPresenter != null && (actionMenuPresenter.f1080w != null || actionMenuPresenter.w());
    }

    public final boolean q() {
        ActionMenuPresenter actionMenuPresenter = this.f1095u;
        return actionMenuPresenter != null && actionMenuPresenter.w();
    }

    public final boolean r() {
        return this.f1094t;
    }

    public final android.support.v7.view.menu.h s() {
        return this.f1092q;
    }

    public void setExpandedActionViewsExclusive(boolean z2) {
        this.f1095u.y(z2);
    }

    public void setOnMenuItemClickListener(e eVar) {
        this.f1100z = eVar;
    }

    public void setOverflowIcon(Drawable drawable) {
        getMenu();
        this.f1095u.A(drawable);
    }

    public void setOverflowReserved(boolean z2) {
        this.f1094t = z2;
    }

    public void setPopupTheme(int i2) {
        if (this.s != i2) {
            this.s = i2;
            if (i2 == 0) {
                this.f1093r = getContext();
            } else {
                this.f1093r = new ContextThemeWrapper(getContext(), i2);
            }
        }
    }

    public void setPresenter(ActionMenuPresenter actionMenuPresenter) {
        this.f1095u = actionMenuPresenter;
        actionMenuPresenter.z(this);
    }

    public final boolean t() {
        ActionMenuPresenter actionMenuPresenter = this.f1095u;
        return actionMenuPresenter != null && actionMenuPresenter.C();
    }

    @Override // android.support.v7.widget.i0, android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new c(getContext(), attributeSet);
    }
}
