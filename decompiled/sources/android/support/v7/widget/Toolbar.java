package android.support.v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.view.AbsSavedState;
import android.support.v7.app.a;
import android.support.v7.widget.ActionMenuView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import java.util.ArrayList;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class Toolbar extends ViewGroup {
    private int A;
    private int B;
    private boolean C;
    private boolean D;
    private final ArrayList<View> E;
    private final ArrayList<View> F;
    private final int[] G;
    private final ActionMenuView.e H;
    private z0 I;
    private ActionMenuPresenter J;
    private d K;
    private boolean L;
    private final Runnable M;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private ActionMenuView f1132b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private a0 f1133c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private a0 f1134d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private l f1135e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private n f1136f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private Drawable f1137g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private CharSequence f1138h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    l f1139i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    View f1140j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private Context f1141k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private int f1142l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private int f1143m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private int f1144n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    int f1145o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private int f1146p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private int f1147q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private int f1148r;
    private int s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    private int f1149t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    private p0 f1150u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    private int f1151v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    private int f1152w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    private int f1153x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    private CharSequence f1154y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    private CharSequence f1155z;

    final class a implements ActionMenuView.e {
        a() {
        }
    }

    final class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            Toolbar.this.E();
        }
    }

    final class c implements View.OnClickListener {
        c() {
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            Toolbar.this.e();
        }
    }

    private class d implements android.support.v7.view.menu.o {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        android.support.v7.view.menu.h f1161b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        android.support.v7.view.menu.j f1162c;

        d() {
        }

        @Override // android.support.v7.view.menu.o
        public final void b(android.support.v7.view.menu.h hVar, boolean z2) {
        }

        @Override // android.support.v7.view.menu.o
        public final boolean c(android.support.v7.view.menu.t tVar) {
            return false;
        }

        @Override // android.support.v7.view.menu.o
        public final void e(Context context, android.support.v7.view.menu.h hVar) {
            android.support.v7.view.menu.j jVar;
            android.support.v7.view.menu.h hVar2 = this.f1161b;
            if (hVar2 != null && (jVar = this.f1162c) != null) {
                hVar2.f(jVar);
            }
            this.f1161b = hVar;
        }

        @Override // android.support.v7.view.menu.o
        public final boolean g(android.support.v7.view.menu.j jVar) {
            Toolbar toolbar = Toolbar.this;
            toolbar.g();
            ViewParent parent = toolbar.f1139i.getParent();
            if (parent != toolbar) {
                if (parent instanceof ViewGroup) {
                    ((ViewGroup) parent).removeView(toolbar.f1139i);
                }
                toolbar.addView(toolbar.f1139i);
            }
            View actionView = jVar.getActionView();
            toolbar.f1140j = actionView;
            this.f1162c = jVar;
            ViewParent parent2 = actionView.getParent();
            if (parent2 != toolbar) {
                if (parent2 instanceof ViewGroup) {
                    ((ViewGroup) parent2).removeView(toolbar.f1140j);
                }
                e eVarK = Toolbar.k();
                eVarK.f750a = (toolbar.f1145o & 112) | 8388611;
                eVarK.f1164b = 2;
                toolbar.f1140j.setLayoutParams(eVarK);
                toolbar.addView(toolbar.f1140j);
            }
            toolbar.y();
            toolbar.requestLayout();
            jVar.m(true);
            KeyEvent.Callback callback = toolbar.f1140j;
            if (callback instanceof n.c) {
                ((n.c) callback).a();
            }
            return true;
        }

        @Override // android.support.v7.view.menu.o
        public final void h(boolean z2) {
            if (this.f1162c != null) {
                android.support.v7.view.menu.h hVar = this.f1161b;
                if (hVar != null) {
                    int size = hVar.size();
                    for (int i2 = 0; i2 < size; i2++) {
                        if (this.f1161b.getItem(i2) == this.f1162c) {
                            return;
                        }
                    }
                }
                i(this.f1162c);
            }
        }

        @Override // android.support.v7.view.menu.o
        public final boolean i(android.support.v7.view.menu.j jVar) {
            Toolbar toolbar = Toolbar.this;
            KeyEvent.Callback callback = toolbar.f1140j;
            if (callback instanceof n.c) {
                ((n.c) callback).d();
            }
            toolbar.removeView(toolbar.f1140j);
            toolbar.removeView(toolbar.f1139i);
            toolbar.f1140j = null;
            toolbar.a();
            this.f1162c = null;
            toolbar.requestLayout();
            jVar.m(false);
            return true;
        }

        @Override // android.support.v7.view.menu.o
        public final boolean j() {
            return false;
        }
    }

    public static class e extends a.C0014a {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f1164b;
    }

    public interface f {
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public Toolbar(Context context, AttributeSet attributeSet) {
        int i2 = k.a.toolbarStyle;
        super(context, attributeSet, i2);
        this.f1153x = 8388627;
        this.E = new ArrayList<>();
        this.F = new ArrayList<>();
        this.G = new int[2];
        this.H = new a();
        this.M = new b();
        x0 x0VarT = x0.t(getContext(), attributeSet, k.j.Toolbar, i2);
        this.f1143m = x0VarT.m(k.j.Toolbar_titleTextAppearance, 0);
        this.f1144n = x0VarT.m(k.j.Toolbar_subtitleTextAppearance, 0);
        this.f1153x = x0VarT.k(k.j.Toolbar_android_gravity, 8388627);
        this.f1145o = x0VarT.k(k.j.Toolbar_buttonGravity, 48);
        int iD = x0VarT.d(k.j.Toolbar_titleMargin, 0);
        iD = x0VarT.q(k.j.Toolbar_titleMargins) ? x0VarT.d(k.j.Toolbar_titleMargins, iD) : iD;
        this.f1149t = iD;
        this.s = iD;
        this.f1148r = iD;
        this.f1147q = iD;
        int iD2 = x0VarT.d(k.j.Toolbar_titleMarginStart, -1);
        if (iD2 >= 0) {
            this.f1147q = iD2;
        }
        int iD3 = x0VarT.d(k.j.Toolbar_titleMarginEnd, -1);
        if (iD3 >= 0) {
            this.f1148r = iD3;
        }
        int iD4 = x0VarT.d(k.j.Toolbar_titleMarginTop, -1);
        if (iD4 >= 0) {
            this.s = iD4;
        }
        int iD5 = x0VarT.d(k.j.Toolbar_titleMarginBottom, -1);
        if (iD5 >= 0) {
            this.f1149t = iD5;
        }
        this.f1146p = x0VarT.e(k.j.Toolbar_maxButtonHeight, -1);
        int iD6 = x0VarT.d(k.j.Toolbar_contentInsetStart, Integer.MIN_VALUE);
        int iD7 = x0VarT.d(k.j.Toolbar_contentInsetEnd, Integer.MIN_VALUE);
        int iE = x0VarT.e(k.j.Toolbar_contentInsetLeft, 0);
        int iE2 = x0VarT.e(k.j.Toolbar_contentInsetRight, 0);
        if (this.f1150u == null) {
            this.f1150u = new p0();
        }
        this.f1150u.e(iE, iE2);
        if (iD6 != Integer.MIN_VALUE || iD7 != Integer.MIN_VALUE) {
            this.f1150u.g(iD6, iD7);
        }
        this.f1151v = x0VarT.d(k.j.Toolbar_contentInsetStartWithNavigation, Integer.MIN_VALUE);
        this.f1152w = x0VarT.d(k.j.Toolbar_contentInsetEndWithActions, Integer.MIN_VALUE);
        this.f1137g = x0VarT.f(k.j.Toolbar_collapseIcon);
        this.f1138h = x0VarT.o(k.j.Toolbar_collapseContentDescription);
        CharSequence charSequenceO = x0VarT.o(k.j.Toolbar_title);
        if (!TextUtils.isEmpty(charSequenceO)) {
            setTitle(charSequenceO);
        }
        CharSequence charSequenceO2 = x0VarT.o(k.j.Toolbar_subtitle);
        if (!TextUtils.isEmpty(charSequenceO2)) {
            setSubtitle(charSequenceO2);
        }
        this.f1141k = getContext();
        setPopupTheme(x0VarT.m(k.j.Toolbar_popupTheme, 0));
        Drawable drawableF = x0VarT.f(k.j.Toolbar_navigationIcon);
        if (drawableF != null) {
            setNavigationIcon(drawableF);
        }
        CharSequence charSequenceO3 = x0VarT.o(k.j.Toolbar_navigationContentDescription);
        if (!TextUtils.isEmpty(charSequenceO3)) {
            setNavigationContentDescription(charSequenceO3);
        }
        Drawable drawableF2 = x0VarT.f(k.j.Toolbar_logo);
        if (drawableF2 != null) {
            setLogo(drawableF2);
        }
        CharSequence charSequenceO4 = x0VarT.o(k.j.Toolbar_logoDescription);
        if (!TextUtils.isEmpty(charSequenceO4)) {
            setLogoDescription(charSequenceO4);
        }
        if (x0VarT.q(k.j.Toolbar_titleTextColor)) {
            setTitleTextColor(x0VarT.b(k.j.Toolbar_titleTextColor, -1));
        }
        if (x0VarT.q(k.j.Toolbar_subtitleTextColor)) {
            setSubtitleTextColor(x0VarT.b(k.j.Toolbar_subtitleTextColor, -1));
        }
        x0VarT.u();
    }

    private boolean D(View view) {
        return (view == null || view.getParent() != this || view.getVisibility() == 8) ? false : true;
    }

    private void b(int i2, ArrayList arrayList) {
        boolean z2 = getLayoutDirection() == 1;
        int childCount = getChildCount();
        int absoluteGravity = Gravity.getAbsoluteGravity(i2, getLayoutDirection());
        arrayList.clear();
        if (!z2) {
            for (int i3 = 0; i3 < childCount; i3++) {
                View childAt = getChildAt(i3);
                e eVar = (e) childAt.getLayoutParams();
                if (eVar.f1164b == 0 && D(childAt)) {
                    int i4 = eVar.f750a;
                    int layoutDirection = getLayoutDirection();
                    int absoluteGravity2 = Gravity.getAbsoluteGravity(i4, layoutDirection) & 7;
                    if (absoluteGravity2 != 1 && absoluteGravity2 != 3 && absoluteGravity2 != 5) {
                        absoluteGravity2 = layoutDirection == 1 ? 5 : 3;
                    }
                    if (absoluteGravity2 == absoluteGravity) {
                        arrayList.add(childAt);
                    }
                }
            }
            return;
        }
        for (int i5 = childCount - 1; i5 >= 0; i5--) {
            View childAt2 = getChildAt(i5);
            e eVar2 = (e) childAt2.getLayoutParams();
            if (eVar2.f1164b == 0 && D(childAt2)) {
                int i6 = eVar2.f750a;
                int layoutDirection2 = getLayoutDirection();
                int absoluteGravity3 = Gravity.getAbsoluteGravity(i6, layoutDirection2) & 7;
                if (absoluteGravity3 != 1 && absoluteGravity3 != 3 && absoluteGravity3 != 5) {
                    absoluteGravity3 = layoutDirection2 == 1 ? 5 : 3;
                }
                if (absoluteGravity3 == absoluteGravity) {
                    arrayList.add(childAt2);
                }
            }
        }
    }

    private void c(View view, boolean z2) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        e eVarK = layoutParams == null ? k() : !checkLayoutParams(layoutParams) ? l(layoutParams) : (e) layoutParams;
        eVarK.f1164b = 1;
        if (!z2 || this.f1140j == null) {
            addView(view, eVarK);
        } else {
            view.setLayoutParams(eVarK);
            this.F.add(view);
        }
    }

    private MenuInflater getMenuInflater() {
        return new n.g(getContext());
    }

    private void h() {
        i();
        if (this.f1132b.s() == null) {
            android.support.v7.view.menu.h hVar = (android.support.v7.view.menu.h) this.f1132b.getMenu();
            if (this.K == null) {
                this.K = new d();
            }
            this.f1132b.setExpandedActionViewsExclusive(true);
            hVar.c(this.K, this.f1141k);
        }
    }

    private void i() {
        if (this.f1132b == null) {
            ActionMenuView actionMenuView = new ActionMenuView(getContext(), null);
            this.f1132b = actionMenuView;
            actionMenuView.setPopupTheme(this.f1142l);
            this.f1132b.setOnMenuItemClickListener(this.H);
            this.f1132b.getClass();
            e eVarK = k();
            eVarK.f750a = (this.f1145o & 112) | 8388613;
            this.f1132b.setLayoutParams(eVarK);
            c(this.f1132b, false);
        }
    }

    private void j() {
        if (this.f1135e == null) {
            this.f1135e = new l(getContext(), null, k.a.toolbarNavigationButtonStyle);
            e eVarK = k();
            eVarK.f750a = (this.f1145o & 112) | 8388611;
            this.f1135e.setLayoutParams(eVarK);
        }
    }

    protected static e k() {
        e eVar = new e(-2, -2);
        eVar.f1164b = 0;
        eVar.f750a = 8388627;
        return eVar;
    }

    protected static e l(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof e) {
            e eVar = (e) layoutParams;
            e eVar2 = new e(eVar);
            eVar2.f1164b = 0;
            eVar2.f1164b = eVar.f1164b;
            return eVar2;
        }
        if (layoutParams instanceof a.C0014a) {
            e eVar3 = new e((a.C0014a) layoutParams);
            eVar3.f1164b = 0;
            return eVar3;
        }
        if (!(layoutParams instanceof ViewGroup.MarginLayoutParams)) {
            e eVar4 = new e(layoutParams);
            eVar4.f1164b = 0;
            return eVar4;
        }
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
        e eVar5 = new e(marginLayoutParams);
        eVar5.f1164b = 0;
        ((ViewGroup.MarginLayoutParams) eVar5).leftMargin = marginLayoutParams.leftMargin;
        ((ViewGroup.MarginLayoutParams) eVar5).topMargin = marginLayoutParams.topMargin;
        ((ViewGroup.MarginLayoutParams) eVar5).rightMargin = marginLayoutParams.rightMargin;
        ((ViewGroup.MarginLayoutParams) eVar5).bottomMargin = marginLayoutParams.bottomMargin;
        return eVar5;
    }

    private int m(View view, int i2) {
        e eVar = (e) view.getLayoutParams();
        int measuredHeight = view.getMeasuredHeight();
        int i3 = i2 > 0 ? (measuredHeight - i2) / 2 : 0;
        int i4 = eVar.f750a & 112;
        if (i4 != 16 && i4 != 48 && i4 != 80) {
            i4 = this.f1153x & 112;
        }
        if (i4 == 48) {
            return getPaddingTop() - i3;
        }
        if (i4 == 80) {
            return (((getHeight() - getPaddingBottom()) - measuredHeight) - ((ViewGroup.MarginLayoutParams) eVar).bottomMargin) - i3;
        }
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int height = getHeight();
        int iMax = (((height - paddingTop) - paddingBottom) - measuredHeight) / 2;
        int i5 = ((ViewGroup.MarginLayoutParams) eVar).topMargin;
        if (iMax < i5) {
            iMax = i5;
        } else {
            int i6 = (((height - paddingBottom) - measuredHeight) - iMax) - paddingTop;
            int i7 = ((ViewGroup.MarginLayoutParams) eVar).bottomMargin;
            if (i6 < i7) {
                iMax = Math.max(0, iMax - (i7 - i6));
            }
        }
        return paddingTop + iMax;
    }

    private static int n(View view) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        return marginLayoutParams.getMarginEnd() + marginLayoutParams.getMarginStart();
    }

    private static int o(View view) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        return marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;
    }

    private boolean r(View view) {
        return view.getParent() == this || this.F.contains(view);
    }

    private int u(View view, int i2, int i3, int[] iArr) {
        e eVar = (e) view.getLayoutParams();
        int i4 = ((ViewGroup.MarginLayoutParams) eVar).leftMargin - iArr[0];
        int iMax = Math.max(0, i4) + i2;
        iArr[0] = Math.max(0, -i4);
        int iM = m(view, i3);
        int measuredWidth = view.getMeasuredWidth();
        view.layout(iMax, iM, iMax + measuredWidth, view.getMeasuredHeight() + iM);
        return measuredWidth + ((ViewGroup.MarginLayoutParams) eVar).rightMargin + iMax;
    }

    private int v(View view, int i2, int i3, int[] iArr) {
        e eVar = (e) view.getLayoutParams();
        int i4 = ((ViewGroup.MarginLayoutParams) eVar).rightMargin - iArr[1];
        int iMax = i2 - Math.max(0, i4);
        iArr[1] = Math.max(0, -i4);
        int iM = m(view, i3);
        int measuredWidth = view.getMeasuredWidth();
        view.layout(iMax - measuredWidth, iM, iMax, view.getMeasuredHeight() + iM);
        return iMax - (measuredWidth + ((ViewGroup.MarginLayoutParams) eVar).leftMargin);
    }

    private int w(View view, int i2, int i3, int i4, int i5, int[] iArr) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        int i6 = marginLayoutParams.leftMargin - iArr[0];
        int i7 = marginLayoutParams.rightMargin - iArr[1];
        int iMax = Math.max(0, i7) + Math.max(0, i6);
        iArr[0] = Math.max(0, -i6);
        iArr[1] = Math.max(0, -i7);
        view.measure(ViewGroup.getChildMeasureSpec(i2, getPaddingRight() + getPaddingLeft() + iMax + i3, marginLayoutParams.width), ViewGroup.getChildMeasureSpec(i4, getPaddingBottom() + getPaddingTop() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin + i5, marginLayoutParams.height));
        return view.getMeasuredWidth() + iMax;
    }

    private void x(View view, int i2, int i3, int i4, int i5) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(i2, getPaddingRight() + getPaddingLeft() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin + i3, marginLayoutParams.width);
        int childMeasureSpec2 = ViewGroup.getChildMeasureSpec(i4, getPaddingBottom() + getPaddingTop() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin, marginLayoutParams.height);
        int mode = View.MeasureSpec.getMode(childMeasureSpec2);
        if (mode != 1073741824 && i5 >= 0) {
            if (mode != 0) {
                i5 = Math.min(View.MeasureSpec.getSize(childMeasureSpec2), i5);
            }
            childMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(i5, 1073741824);
        }
        view.measure(childMeasureSpec, childMeasureSpec2);
    }

    public final void A(android.support.v7.view.menu.h hVar, ActionMenuPresenter actionMenuPresenter) {
        if (hVar == null && this.f1132b == null) {
            return;
        }
        i();
        android.support.v7.view.menu.h hVarS = this.f1132b.s();
        if (hVarS == hVar) {
            return;
        }
        if (hVarS != null) {
            hVarS.y(this.J);
            hVarS.y(this.K);
        }
        if (this.K == null) {
            this.K = new d();
        }
        actionMenuPresenter.y(true);
        if (hVar != null) {
            hVar.c(actionMenuPresenter, this.f1141k);
            hVar.c(this.K, this.f1141k);
        } else {
            actionMenuPresenter.e(this.f1141k, null);
            this.K.e(this.f1141k, null);
            actionMenuPresenter.h(true);
            this.K.h(true);
        }
        this.f1132b.setPopupTheme(this.f1142l);
        this.f1132b.setPresenter(actionMenuPresenter);
        this.J = actionMenuPresenter;
    }

    public final void B(Context context, int i2) {
        this.f1144n = i2;
        a0 a0Var = this.f1134d;
        if (a0Var != null) {
            a0Var.setTextAppearance(context, i2);
        }
    }

    public final void C(Context context, int i2) {
        this.f1143m = i2;
        a0 a0Var = this.f1133c;
        if (a0Var != null) {
            a0Var.setTextAppearance(context, i2);
        }
    }

    public final boolean E() {
        ActionMenuView actionMenuView = this.f1132b;
        return actionMenuView != null && actionMenuView.t();
    }

    final void a() {
        ArrayList<View> arrayList = this.F;
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            addView(arrayList.get(size));
        }
        arrayList.clear();
    }

    @Override // android.view.ViewGroup
    protected final boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return super.checkLayoutParams(layoutParams) && (layoutParams instanceof e);
    }

    public final boolean d() {
        ActionMenuView actionMenuView;
        return getVisibility() == 0 && (actionMenuView = this.f1132b) != null && actionMenuView.r();
    }

    public final void e() {
        d dVar = this.K;
        android.support.v7.view.menu.j jVar = dVar == null ? null : dVar.f1162c;
        if (jVar != null) {
            jVar.collapseActionView();
        }
    }

    public final void f() {
        ActionMenuView actionMenuView = this.f1132b;
        if (actionMenuView != null) {
            actionMenuView.k();
        }
    }

    final void g() {
        if (this.f1139i == null) {
            l lVar = new l(getContext(), null, k.a.toolbarNavigationButtonStyle);
            this.f1139i = lVar;
            lVar.setImageDrawable(this.f1137g);
            this.f1139i.setContentDescription(this.f1138h);
            e eVarK = k();
            eVarK.f750a = (this.f1145o & 112) | 8388611;
            eVarK.f1164b = 2;
            this.f1139i.setLayoutParams(eVarK);
            this.f1139i.setOnClickListener(new c());
        }
    }

    @Override // android.view.ViewGroup
    protected final /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return k();
    }

    @Override // android.view.ViewGroup
    protected final /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return l(layoutParams);
    }

    public int getContentInsetEnd() {
        p0 p0Var = this.f1150u;
        if (p0Var != null) {
            return p0Var.a();
        }
        return 0;
    }

    public int getContentInsetEndWithActions() {
        int i2 = this.f1152w;
        return i2 != Integer.MIN_VALUE ? i2 : getContentInsetEnd();
    }

    public int getContentInsetLeft() {
        p0 p0Var = this.f1150u;
        if (p0Var != null) {
            return p0Var.b();
        }
        return 0;
    }

    public int getContentInsetRight() {
        p0 p0Var = this.f1150u;
        if (p0Var != null) {
            return p0Var.c();
        }
        return 0;
    }

    public int getContentInsetStart() {
        p0 p0Var = this.f1150u;
        if (p0Var != null) {
            return p0Var.d();
        }
        return 0;
    }

    public int getContentInsetStartWithNavigation() {
        int i2 = this.f1151v;
        return i2 != Integer.MIN_VALUE ? i2 : getContentInsetStart();
    }

    public int getCurrentContentInsetEnd() {
        android.support.v7.view.menu.h hVarS;
        ActionMenuView actionMenuView = this.f1132b;
        return (actionMenuView == null || (hVarS = actionMenuView.s()) == null || !hVarS.hasVisibleItems()) ? getContentInsetEnd() : Math.max(getContentInsetEnd(), Math.max(this.f1152w, 0));
    }

    public int getCurrentContentInsetLeft() {
        return getLayoutDirection() == 1 ? getCurrentContentInsetEnd() : getCurrentContentInsetStart();
    }

    public int getCurrentContentInsetRight() {
        return getLayoutDirection() == 1 ? getCurrentContentInsetStart() : getCurrentContentInsetEnd();
    }

    public int getCurrentContentInsetStart() {
        return getNavigationIcon() != null ? Math.max(getContentInsetStart(), Math.max(this.f1151v, 0)) : getContentInsetStart();
    }

    public Drawable getLogo() {
        n nVar = this.f1136f;
        if (nVar != null) {
            return nVar.getDrawable();
        }
        return null;
    }

    public CharSequence getLogoDescription() {
        n nVar = this.f1136f;
        if (nVar != null) {
            return nVar.getContentDescription();
        }
        return null;
    }

    public Menu getMenu() {
        h();
        return this.f1132b.getMenu();
    }

    public CharSequence getNavigationContentDescription() {
        l lVar = this.f1135e;
        if (lVar != null) {
            return lVar.getContentDescription();
        }
        return null;
    }

    public Drawable getNavigationIcon() {
        l lVar = this.f1135e;
        if (lVar != null) {
            return lVar.getDrawable();
        }
        return null;
    }

    ActionMenuPresenter getOuterActionMenuPresenter() {
        return this.J;
    }

    public Drawable getOverflowIcon() {
        h();
        return this.f1132b.getOverflowIcon();
    }

    Context getPopupContext() {
        return this.f1141k;
    }

    public int getPopupTheme() {
        return this.f1142l;
    }

    public CharSequence getSubtitle() {
        return this.f1155z;
    }

    public CharSequence getTitle() {
        return this.f1154y;
    }

    public int getTitleMarginBottom() {
        return this.f1149t;
    }

    public int getTitleMarginEnd() {
        return this.f1148r;
    }

    public int getTitleMarginStart() {
        return this.f1147q;
    }

    public int getTitleMarginTop() {
        return this.s;
    }

    public d0 getWrapper() {
        if (this.I == null) {
            this.I = new z0(this);
        }
        return this.I;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks(this.M);
    }

    @Override // android.view.View
    public final boolean onHoverEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 9) {
            this.D = false;
        }
        if (!this.D) {
            boolean zOnHoverEvent = super.onHoverEvent(motionEvent);
            if (actionMasked == 9 && !zOnHoverEvent) {
                this.D = true;
            }
        }
        if (actionMasked == 10 || actionMasked == 3) {
            this.D = false;
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:105:0x0291 A[LOOP:0: B:104:0x028f->B:105:0x0291, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:108:0x02ad A[LOOP:1: B:107:0x02ab->B:108:0x02ad, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:111:0x02cc A[LOOP:2: B:110:0x02ca->B:111:0x02cc, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:115:0x030d  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x031a A[LOOP:3: B:119:0x0318->B:120:0x031a, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0077  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00b4  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00e8  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00ff  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0104  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x011c  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0129  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x012b  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x012e  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0132  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0135  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0166  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x019e  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x01ad  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x021b  */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected final void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        int iU;
        int iV;
        boolean zD;
        boolean zD2;
        int i6;
        int measuredHeight;
        int i7;
        int i8;
        int i9;
        int i10;
        int paddingTop;
        int i11;
        int i12;
        int i13;
        int i14;
        int size;
        int iU2;
        int i15;
        int size2;
        int i16;
        int size3;
        int i17;
        int i18;
        int i19;
        int size4;
        boolean z3 = getLayoutDirection() == 1;
        int width = getWidth();
        int height = getHeight();
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop2 = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int i20 = width - paddingRight;
        int[] iArr = this.G;
        iArr[1] = 0;
        iArr[0] = 0;
        int minimumHeight = getMinimumHeight();
        int iMin = minimumHeight >= 0 ? Math.min(minimumHeight, i5 - i3) : 0;
        if (!D(this.f1135e)) {
            iU = paddingLeft;
        } else {
            if (z3) {
                iV = v(this.f1135e, i20, iMin, iArr);
                iU = paddingLeft;
                if (D(this.f1139i)) {
                    if (z3) {
                        iV = v(this.f1139i, iV, iMin, iArr);
                    } else {
                        iU = u(this.f1139i, iU, iMin, iArr);
                    }
                }
                if (D(this.f1132b)) {
                    if (z3) {
                        iU = u(this.f1132b, iU, iMin, iArr);
                    } else {
                        iV = v(this.f1132b, iV, iMin, iArr);
                    }
                }
                int currentContentInsetLeft = getCurrentContentInsetLeft();
                int currentContentInsetRight = getCurrentContentInsetRight();
                iArr[0] = Math.max(0, currentContentInsetLeft - iU);
                iArr[1] = Math.max(0, currentContentInsetRight - (i20 - iV));
                int iMax = Math.max(iU, currentContentInsetLeft);
                int iMin2 = Math.min(iV, i20 - currentContentInsetRight);
                if (D(this.f1140j)) {
                    if (z3) {
                        iMin2 = v(this.f1140j, iMin2, iMin, iArr);
                    } else {
                        iMax = u(this.f1140j, iMax, iMin, iArr);
                    }
                }
                if (D(this.f1136f)) {
                    if (z3) {
                        iMin2 = v(this.f1136f, iMin2, iMin, iArr);
                    } else {
                        iMax = u(this.f1136f, iMax, iMin, iArr);
                    }
                }
                zD = D(this.f1133c);
                zD2 = D(this.f1134d);
                if (zD) {
                    i6 = paddingRight;
                    measuredHeight = 0;
                } else {
                    e eVar = (e) this.f1133c.getLayoutParams();
                    i6 = paddingRight;
                    measuredHeight = ((ViewGroup.MarginLayoutParams) eVar).bottomMargin + this.f1133c.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) eVar).topMargin;
                }
                if (zD2) {
                    i7 = width;
                } else {
                    e eVar2 = (e) this.f1134d.getLayoutParams();
                    i7 = width;
                    measuredHeight += this.f1134d.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) eVar2).topMargin + ((ViewGroup.MarginLayoutParams) eVar2).bottomMargin;
                }
                if (!zD || zD2) {
                    a0 a0Var = !zD ? this.f1133c : this.f1134d;
                    a0 a0Var2 = !zD2 ? this.f1134d : this.f1133c;
                    e eVar3 = (e) a0Var.getLayoutParams();
                    e eVar4 = (e) a0Var2.getLayoutParams();
                    boolean z4 = (zD && this.f1133c.getMeasuredWidth() > 0) || (zD2 && this.f1134d.getMeasuredWidth() > 0);
                    i8 = this.f1153x & 112;
                    i9 = paddingLeft;
                    if (i8 == 48) {
                        i10 = iMin;
                        paddingTop = getPaddingTop() + ((ViewGroup.MarginLayoutParams) eVar3).topMargin + this.s;
                    } else if (i8 != 80) {
                        int iMax2 = (((height - paddingTop2) - paddingBottom) - measuredHeight) / 2;
                        i10 = iMin;
                        int i21 = ((ViewGroup.MarginLayoutParams) eVar3).topMargin + this.s;
                        if (iMax2 < i21) {
                            iMax2 = i21;
                        } else {
                            int i22 = (((height - paddingBottom) - measuredHeight) - iMax2) - paddingTop2;
                            int i23 = ((ViewGroup.MarginLayoutParams) eVar3).bottomMargin;
                            int i24 = this.f1149t;
                            if (i22 < i23 + i24) {
                                iMax2 = Math.max(0, iMax2 - ((((ViewGroup.MarginLayoutParams) eVar4).bottomMargin + i24) - i22));
                            }
                        }
                        paddingTop = paddingTop2 + iMax2;
                    } else {
                        i10 = iMin;
                        paddingTop = (((height - paddingBottom) - ((ViewGroup.MarginLayoutParams) eVar4).bottomMargin) - this.f1149t) - measuredHeight;
                    }
                    if (z3) {
                        int i25 = (z4 ? this.f1147q : 0) - iArr[1];
                        iMin2 -= Math.max(0, i25);
                        iArr[1] = Math.max(0, -i25);
                        if (zD) {
                            e eVar5 = (e) this.f1133c.getLayoutParams();
                            int measuredWidth = iMin2 - this.f1133c.getMeasuredWidth();
                            int measuredHeight2 = this.f1133c.getMeasuredHeight() + paddingTop;
                            this.f1133c.layout(measuredWidth, paddingTop, iMin2, measuredHeight2);
                            i13 = measuredWidth - this.f1148r;
                            paddingTop = measuredHeight2 + ((ViewGroup.MarginLayoutParams) eVar5).bottomMargin;
                        } else {
                            i13 = iMin2;
                        }
                        if (zD2) {
                            int i26 = paddingTop + ((ViewGroup.MarginLayoutParams) ((e) this.f1134d.getLayoutParams())).topMargin;
                            this.f1134d.layout(iMin2 - this.f1134d.getMeasuredWidth(), i26, iMin2, this.f1134d.getMeasuredHeight() + i26);
                            i14 = iMin2 - this.f1148r;
                        } else {
                            i14 = iMin2;
                        }
                        if (z4) {
                            iMin2 = Math.min(i13, i14);
                        }
                    } else {
                        int i27 = (z4 ? this.f1147q : 0) - iArr[0];
                        iMax += Math.max(0, i27);
                        iArr[0] = Math.max(0, -i27);
                        if (zD) {
                            e eVar6 = (e) this.f1133c.getLayoutParams();
                            int measuredWidth2 = this.f1133c.getMeasuredWidth() + iMax;
                            int measuredHeight3 = this.f1133c.getMeasuredHeight() + paddingTop;
                            this.f1133c.layout(iMax, paddingTop, measuredWidth2, measuredHeight3);
                            i11 = measuredWidth2 + this.f1148r;
                            paddingTop = measuredHeight3 + ((ViewGroup.MarginLayoutParams) eVar6).bottomMargin;
                        } else {
                            i11 = iMax;
                        }
                        if (zD2) {
                            int i28 = paddingTop + ((ViewGroup.MarginLayoutParams) ((e) this.f1134d.getLayoutParams())).topMargin;
                            int measuredWidth3 = this.f1134d.getMeasuredWidth() + iMax;
                            this.f1134d.layout(iMax, i28, measuredWidth3, this.f1134d.getMeasuredHeight() + i28);
                            i12 = measuredWidth3 + this.f1148r;
                        } else {
                            i12 = iMax;
                        }
                        if (z4) {
                            iMax = Math.max(i11, i12);
                        }
                    }
                } else {
                    i9 = paddingLeft;
                    i10 = iMin;
                }
                ArrayList<View> arrayList = this.E;
                b(3, arrayList);
                size = arrayList.size();
                iU2 = iMax;
                for (i15 = 0; i15 < size; i15++) {
                    iU2 = u(arrayList.get(i15), iU2, i10, iArr);
                }
                int i29 = i10;
                b(5, arrayList);
                size2 = arrayList.size();
                for (i16 = 0; i16 < size2; i16++) {
                    iMin2 = v(arrayList.get(i16), iMin2, i29, iArr);
                }
                b(1, arrayList);
                int i30 = iArr[0];
                int i31 = iArr[1];
                size3 = arrayList.size();
                int i32 = i30;
                i17 = 0;
                int measuredWidth4 = 0;
                while (i17 < size3) {
                    View view = arrayList.get(i17);
                    e eVar7 = (e) view.getLayoutParams();
                    int i33 = ((ViewGroup.MarginLayoutParams) eVar7).leftMargin - i32;
                    int i34 = ((ViewGroup.MarginLayoutParams) eVar7).rightMargin - i31;
                    int iMax3 = Math.max(0, i33);
                    int iMax4 = Math.max(0, i34);
                    int iMax5 = Math.max(0, -i33);
                    int iMax6 = Math.max(0, -i34);
                    measuredWidth4 += view.getMeasuredWidth() + iMax3 + iMax4;
                    i17++;
                    i31 = iMax6;
                    i32 = iMax5;
                }
                i19 = ((((i7 - i9) - i6) / 2) + i9) - (measuredWidth4 / 2);
                int i35 = measuredWidth4 + i19;
                if (i19 >= iU2) {
                    iU2 = i35 > iMin2 ? i19 - (i35 - iMin2) : i19;
                }
                size4 = arrayList.size();
                for (i18 = 0; i18 < size4; i18++) {
                    iU2 = u(arrayList.get(i18), iU2, i29, iArr);
                }
                arrayList.clear();
            }
            iU = u(this.f1135e, paddingLeft, iMin, iArr);
        }
        iV = i20;
        if (D(this.f1139i)) {
        }
        if (D(this.f1132b)) {
        }
        int currentContentInsetLeft2 = getCurrentContentInsetLeft();
        int currentContentInsetRight2 = getCurrentContentInsetRight();
        iArr[0] = Math.max(0, currentContentInsetLeft2 - iU);
        iArr[1] = Math.max(0, currentContentInsetRight2 - (i20 - iV));
        int iMax7 = Math.max(iU, currentContentInsetLeft2);
        int iMin22 = Math.min(iV, i20 - currentContentInsetRight2);
        if (D(this.f1140j)) {
        }
        if (D(this.f1136f)) {
        }
        zD = D(this.f1133c);
        zD2 = D(this.f1134d);
        if (zD) {
        }
        if (zD2) {
        }
        if (zD) {
            if (!zD) {
            }
            if (!zD2) {
            }
            e eVar32 = (e) a0Var.getLayoutParams();
            e eVar42 = (e) a0Var2.getLayoutParams();
            if (zD) {
                i8 = this.f1153x & 112;
                i9 = paddingLeft;
                if (i8 == 48) {
                }
                if (z3) {
                }
            } else {
                i8 = this.f1153x & 112;
                i9 = paddingLeft;
                if (i8 == 48) {
                }
                if (z3) {
                }
            }
        }
        ArrayList<View> arrayList2 = this.E;
        b(3, arrayList2);
        size = arrayList2.size();
        iU2 = iMax7;
        while (i15 < size) {
        }
        int i292 = i10;
        b(5, arrayList2);
        size2 = arrayList2.size();
        while (i16 < size2) {
        }
        b(1, arrayList2);
        int i302 = iArr[0];
        int i312 = iArr[1];
        size3 = arrayList2.size();
        int i322 = i302;
        i17 = 0;
        int measuredWidth42 = 0;
        while (i17 < size3) {
        }
        i19 = ((((i7 - i9) - i6) / 2) + i9) - (measuredWidth42 / 2);
        int i352 = measuredWidth42 + i19;
        if (i19 >= iU2) {
        }
        size4 = arrayList2.size();
        while (i18 < size4) {
        }
        arrayList2.clear();
    }

    @Override // android.view.View
    protected final void onMeasure(int i2, int i3) {
        char c2;
        char c3;
        int measuredWidth;
        int iMax;
        int iCombineMeasuredStates;
        int measuredWidth2;
        int measuredHeight;
        int iCombineMeasuredStates2;
        int iMax2;
        int i4 = b1.f1197b;
        int i5 = 0;
        if (getLayoutDirection() == 1) {
            c3 = 1;
            c2 = 0;
        } else {
            c2 = 1;
            c3 = 0;
        }
        if (D(this.f1135e)) {
            x(this.f1135e, i2, 0, i3, this.f1146p);
            measuredWidth = this.f1135e.getMeasuredWidth() + n(this.f1135e);
            iMax = Math.max(0, this.f1135e.getMeasuredHeight() + o(this.f1135e));
            iCombineMeasuredStates = View.combineMeasuredStates(0, this.f1135e.getMeasuredState());
        } else {
            measuredWidth = 0;
            iMax = 0;
            iCombineMeasuredStates = 0;
        }
        if (D(this.f1139i)) {
            x(this.f1139i, i2, 0, i3, this.f1146p);
            measuredWidth = this.f1139i.getMeasuredWidth() + n(this.f1139i);
            iMax = Math.max(iMax, this.f1139i.getMeasuredHeight() + o(this.f1139i));
            iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, this.f1139i.getMeasuredState());
        }
        int currentContentInsetStart = getCurrentContentInsetStart();
        int iMax3 = Math.max(currentContentInsetStart, measuredWidth);
        int iMax4 = Math.max(0, currentContentInsetStart - measuredWidth);
        int[] iArr = this.G;
        iArr[c3] = iMax4;
        if (D(this.f1132b)) {
            x(this.f1132b, i2, iMax3, i3, this.f1146p);
            measuredWidth2 = this.f1132b.getMeasuredWidth() + n(this.f1132b);
            iMax = Math.max(iMax, this.f1132b.getMeasuredHeight() + o(this.f1132b));
            iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, this.f1132b.getMeasuredState());
        } else {
            measuredWidth2 = 0;
        }
        int currentContentInsetEnd = getCurrentContentInsetEnd();
        int iMax5 = iMax3 + Math.max(currentContentInsetEnd, measuredWidth2);
        iArr[c2] = Math.max(0, currentContentInsetEnd - measuredWidth2);
        if (D(this.f1140j)) {
            iMax5 += w(this.f1140j, i2, iMax5, i3, 0, iArr);
            iMax = Math.max(iMax, this.f1140j.getMeasuredHeight() + o(this.f1140j));
            iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, this.f1140j.getMeasuredState());
        }
        if (D(this.f1136f)) {
            iMax5 += w(this.f1136f, i2, iMax5, i3, 0, iArr);
            iMax = Math.max(iMax, this.f1136f.getMeasuredHeight() + o(this.f1136f));
            iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, this.f1136f.getMeasuredState());
        }
        int childCount = getChildCount();
        for (int i6 = 0; i6 < childCount; i6++) {
            View childAt = getChildAt(i6);
            if (((e) childAt.getLayoutParams()).f1164b == 0 && D(childAt)) {
                iMax5 += w(childAt, i2, iMax5, i3, 0, iArr);
                iMax = Math.max(iMax, childAt.getMeasuredHeight() + o(childAt));
                iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, childAt.getMeasuredState());
            }
        }
        int i7 = this.s + this.f1149t;
        int i8 = this.f1147q + this.f1148r;
        if (D(this.f1133c)) {
            w(this.f1133c, i2, iMax5 + i8, i3, i7, iArr);
            int measuredWidth3 = this.f1133c.getMeasuredWidth() + n(this.f1133c);
            measuredHeight = this.f1133c.getMeasuredHeight() + o(this.f1133c);
            iCombineMeasuredStates2 = View.combineMeasuredStates(iCombineMeasuredStates, this.f1133c.getMeasuredState());
            iMax2 = measuredWidth3;
        } else {
            measuredHeight = 0;
            iCombineMeasuredStates2 = iCombineMeasuredStates;
            iMax2 = 0;
        }
        if (D(this.f1134d)) {
            iMax2 = Math.max(iMax2, w(this.f1134d, i2, iMax5 + i8, i3, measuredHeight + i7, iArr));
            measuredHeight = this.f1134d.getMeasuredHeight() + o(this.f1134d) + measuredHeight;
            iCombineMeasuredStates2 = View.combineMeasuredStates(iCombineMeasuredStates2, this.f1134d.getMeasuredState());
        }
        int iMax6 = Math.max(iMax, measuredHeight);
        int paddingRight = getPaddingRight() + getPaddingLeft();
        int paddingBottom = getPaddingBottom() + getPaddingTop() + iMax6;
        int iResolveSizeAndState = View.resolveSizeAndState(Math.max(paddingRight + iMax5 + iMax2, getSuggestedMinimumWidth()), i2, (-16777216) & iCombineMeasuredStates2);
        int iResolveSizeAndState2 = View.resolveSizeAndState(Math.max(paddingBottom, getSuggestedMinimumHeight()), i3, iCombineMeasuredStates2 << 16);
        if (!this.L) {
            i5 = iResolveSizeAndState2;
            break;
        }
        int childCount2 = getChildCount();
        for (int i9 = 0; i9 < childCount2; i9++) {
            View childAt2 = getChildAt(i9);
            if (D(childAt2) && childAt2.getMeasuredWidth() > 0 && childAt2.getMeasuredHeight() > 0) {
                i5 = iResolveSizeAndState2;
                break;
            }
        }
        setMeasuredDimension(iResolveSizeAndState, i5);
    }

    @Override // android.view.View
    protected final void onRestoreInstanceState(Parcelable parcelable) {
        MenuItem menuItemFindItem;
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.a());
        ActionMenuView actionMenuView = this.f1132b;
        android.support.v7.view.menu.h hVarS = actionMenuView != null ? actionMenuView.s() : null;
        int i2 = savedState.f1156c;
        if (i2 != 0 && this.K != null && hVarS != null && (menuItemFindItem = hVarS.findItem(i2)) != null) {
            menuItemFindItem.expandActionView();
        }
        if (savedState.f1157d) {
            Runnable runnable = this.M;
            removeCallbacks(runnable);
            post(runnable);
        }
    }

    @Override // android.view.View
    public final void onRtlPropertiesChanged(int i2) {
        super.onRtlPropertiesChanged(i2);
        if (this.f1150u == null) {
            this.f1150u = new p0();
        }
        this.f1150u.f(i2 == 1);
    }

    @Override // android.view.View
    protected final Parcelable onSaveInstanceState() {
        android.support.v7.view.menu.j jVar;
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        d dVar = this.K;
        if (dVar != null && (jVar = dVar.f1162c) != null) {
            savedState.f1156c = jVar.getItemId();
        }
        savedState.f1157d = t();
        return savedState;
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.C = false;
        }
        if (!this.C) {
            boolean zOnTouchEvent = super.onTouchEvent(motionEvent);
            if (actionMasked == 0 && !zOnTouchEvent) {
                this.C = true;
            }
        }
        if (actionMasked == 1 || actionMasked == 3) {
            this.C = false;
        }
        return true;
    }

    public final boolean p() {
        d dVar = this.K;
        return (dVar == null || dVar.f1162c == null) ? false : true;
    }

    public final boolean q() {
        ActionMenuView actionMenuView = this.f1132b;
        return actionMenuView != null && actionMenuView.o();
    }

    public final boolean s() {
        ActionMenuView actionMenuView = this.f1132b;
        return actionMenuView != null && actionMenuView.p();
    }

    public void setCollapsible(boolean z2) {
        this.L = z2;
        requestLayout();
    }

    public void setContentInsetEndWithActions(int i2) {
        if (i2 < 0) {
            i2 = Integer.MIN_VALUE;
        }
        if (i2 != this.f1152w) {
            this.f1152w = i2;
            if (getNavigationIcon() != null) {
                requestLayout();
            }
        }
    }

    public void setContentInsetStartWithNavigation(int i2) {
        if (i2 < 0) {
            i2 = Integer.MIN_VALUE;
        }
        if (i2 != this.f1151v) {
            this.f1151v = i2;
            if (getNavigationIcon() != null) {
                requestLayout();
            }
        }
    }

    public void setLogo(int i2) {
        setLogo(l.a.a(getContext(), i2));
    }

    public void setLogoDescription(int i2) {
        setLogoDescription(getContext().getText(i2));
    }

    public void setNavigationContentDescription(int i2) {
        setNavigationContentDescription(i2 != 0 ? getContext().getText(i2) : null);
    }

    public void setNavigationIcon(int i2) {
        setNavigationIcon(l.a.a(getContext(), i2));
    }

    public void setNavigationOnClickListener(View.OnClickListener onClickListener) {
        j();
        this.f1135e.setOnClickListener(onClickListener);
    }

    public void setOnMenuItemClickListener(f fVar) {
    }

    public void setOverflowIcon(Drawable drawable) {
        h();
        this.f1132b.setOverflowIcon(drawable);
    }

    public void setPopupTheme(int i2) {
        if (this.f1142l != i2) {
            this.f1142l = i2;
            if (i2 == 0) {
                this.f1141k = getContext();
            } else {
                this.f1141k = new ContextThemeWrapper(getContext(), i2);
            }
        }
    }

    public void setSubtitle(int i2) {
        setSubtitle(getContext().getText(i2));
    }

    public void setSubtitleTextColor(int i2) {
        this.B = i2;
        a0 a0Var = this.f1134d;
        if (a0Var != null) {
            a0Var.setTextColor(i2);
        }
    }

    public void setTitle(int i2) {
        setTitle(getContext().getText(i2));
    }

    public void setTitleMarginBottom(int i2) {
        this.f1149t = i2;
        requestLayout();
    }

    public void setTitleMarginEnd(int i2) {
        this.f1148r = i2;
        requestLayout();
    }

    public void setTitleMarginStart(int i2) {
        this.f1147q = i2;
        requestLayout();
    }

    public void setTitleMarginTop(int i2) {
        this.s = i2;
        requestLayout();
    }

    public void setTitleTextColor(int i2) {
        this.A = i2;
        a0 a0Var = this.f1133c;
        if (a0Var != null) {
            a0Var.setTextColor(i2);
        }
    }

    public final boolean t() {
        ActionMenuView actionMenuView = this.f1132b;
        return actionMenuView != null && actionMenuView.q();
    }

    final void y() {
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = getChildAt(childCount);
            if (((e) childAt.getLayoutParams()).f1164b != 2 && childAt != this.f1132b) {
                removeViewAt(childCount);
                this.F.add(childAt);
            }
        }
    }

    public final void z(int i2, int i3) {
        if (this.f1150u == null) {
            this.f1150u = new p0();
        }
        this.f1150u.g(i2, i3);
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        Context context = getContext();
        e eVar = new e(context, attributeSet);
        eVar.f750a = 0;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, k.j.ActionBarLayout);
        eVar.f750a = typedArrayObtainStyledAttributes.getInt(k.j.ActionBarLayout_android_layout_gravity, 0);
        typedArrayObtainStyledAttributes.recycle();
        eVar.f1164b = 0;
        return eVar;
    }

    public void setLogo(Drawable drawable) {
        if (drawable != null) {
            if (this.f1136f == null) {
                this.f1136f = new n(getContext());
            }
            if (!r(this.f1136f)) {
                c(this.f1136f, true);
            }
        } else {
            n nVar = this.f1136f;
            if (nVar != null && r(nVar)) {
                removeView(this.f1136f);
                this.F.remove(this.f1136f);
            }
        }
        n nVar2 = this.f1136f;
        if (nVar2 != null) {
            nVar2.setImageDrawable(drawable);
        }
    }

    public void setLogoDescription(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence) && this.f1136f == null) {
            this.f1136f = new n(getContext());
        }
        n nVar = this.f1136f;
        if (nVar != null) {
            nVar.setContentDescription(charSequence);
        }
    }

    public void setNavigationContentDescription(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            j();
        }
        l lVar = this.f1135e;
        if (lVar != null) {
            lVar.setContentDescription(charSequence);
        }
    }

    public void setNavigationIcon(Drawable drawable) {
        if (drawable != null) {
            j();
            if (!r(this.f1135e)) {
                c(this.f1135e, true);
            }
        } else {
            l lVar = this.f1135e;
            if (lVar != null && r(lVar)) {
                removeView(this.f1135e);
                this.F.remove(this.f1135e);
            }
        }
        l lVar2 = this.f1135e;
        if (lVar2 != null) {
            lVar2.setImageDrawable(drawable);
        }
    }

    public void setSubtitle(CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            a0 a0Var = this.f1134d;
            if (a0Var != null && r(a0Var)) {
                removeView(this.f1134d);
                this.F.remove(this.f1134d);
            }
        } else {
            if (this.f1134d == null) {
                Context context = getContext();
                a0 a0Var2 = new a0(context, null);
                this.f1134d = a0Var2;
                a0Var2.setSingleLine();
                this.f1134d.setEllipsize(TextUtils.TruncateAt.END);
                int i2 = this.f1144n;
                if (i2 != 0) {
                    this.f1134d.setTextAppearance(context, i2);
                }
                int i3 = this.B;
                if (i3 != 0) {
                    this.f1134d.setTextColor(i3);
                }
            }
            if (!r(this.f1134d)) {
                c(this.f1134d, true);
            }
        }
        a0 a0Var3 = this.f1134d;
        if (a0Var3 != null) {
            a0Var3.setText(charSequence);
        }
        this.f1155z = charSequence;
    }

    public void setTitle(CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            a0 a0Var = this.f1133c;
            if (a0Var != null && r(a0Var)) {
                removeView(this.f1133c);
                this.F.remove(this.f1133c);
            }
        } else {
            if (this.f1133c == null) {
                Context context = getContext();
                a0 a0Var2 = new a0(context, null);
                this.f1133c = a0Var2;
                a0Var2.setSingleLine();
                this.f1133c.setEllipsize(TextUtils.TruncateAt.END);
                int i2 = this.f1143m;
                if (i2 != 0) {
                    this.f1133c.setTextAppearance(context, i2);
                }
                int i3 = this.A;
                if (i3 != 0) {
                    this.f1133c.setTextColor(i3);
                }
            }
            if (!r(this.f1133c)) {
                c(this.f1133c, true);
            }
        }
        a0 a0Var3 = this.f1133c;
        if (a0Var3 != null) {
            a0Var3.setText(charSequence);
        }
        this.f1154y = charSequence;
    }

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f1156c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        boolean f1157d;

        static class a implements Parcelable.ClassLoaderCreator<SavedState> {
            @Override // android.os.Parcelable.ClassLoaderCreator
            public final SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }

            @Override // android.os.Parcelable.Creator
            public final Object[] newArray(int i2) {
                return new SavedState[i2];
            }

            @Override // android.os.Parcelable.Creator
            public final Object createFromParcel(Parcel parcel) {
                return new SavedState(parcel, null);
            }
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.f1156c = parcel.readInt();
            this.f1157d = parcel.readInt() != 0;
        }

        @Override // android.support.v4.view.AbsSavedState, android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            parcel.writeInt(this.f1156c);
            parcel.writeInt(this.f1157d ? 1 : 0);
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }
    }
}
