package android.support.v7.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.view.menu.ActionMenuItemView;
import android.support.v7.view.menu.o;
import android.support.v7.view.menu.p;
import android.support.v7.widget.ActionMenuView;
import android.util.SparseBooleanArray;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class ActionMenuPresenter extends android.support.v7.view.menu.a {

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    d f1068j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private Drawable f1069k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private boolean f1070l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private boolean f1071m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private boolean f1072n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private int f1073o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private int f1074p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private int f1075q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private boolean f1076r;
    private final SparseBooleanArray s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    private View f1077t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    e f1078u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    a f1079v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    c f1080w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    private b f1081x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    final f f1082y;

    private static class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f1083a;

        static class a implements Parcelable.Creator<SavedState> {
            @Override // android.os.Parcelable.Creator
            public final SavedState createFromParcel(Parcel parcel) {
                SavedState savedState = new SavedState();
                savedState.f1083a = parcel.readInt();
                return savedState;
            }

            @Override // android.os.Parcelable.Creator
            public final SavedState[] newArray(int i2) {
                return new SavedState[i2];
            }
        }

        SavedState() {
        }

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i2) {
            parcel.writeInt(this.f1083a);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    class a extends android.support.v7.view.menu.n {
        public a(Context context, android.support.v7.view.menu.t tVar, View view) {
            super(k.a.actionOverflowMenuStyle, context, tVar, view, false);
            if (!((android.support.v7.view.menu.j) tVar.getItem()).i()) {
                View view2 = ActionMenuPresenter.this.f1068j;
                e(view2 == null ? (View) ((android.support.v7.view.menu.a) ActionMenuPresenter.this).f873i : view2);
            }
            i(ActionMenuPresenter.this.f1082y);
        }

        @Override // android.support.v7.view.menu.n
        protected final void d() {
            ActionMenuPresenter actionMenuPresenter = ActionMenuPresenter.this;
            actionMenuPresenter.f1079v = null;
            actionMenuPresenter.getClass();
            super.d();
        }
    }

    private class b extends ActionMenuItemView.b {
        b() {
        }

        @Override // android.support.v7.view.menu.ActionMenuItemView.b
        public final o.b a() {
            a aVar = ActionMenuPresenter.this.f1079v;
            if (aVar != null) {
                return aVar.b();
            }
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    class c implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private e f1086b;

        public c(e eVar) {
            this.f1086b = eVar;
        }

        @Override // java.lang.Runnable
        public final void run() {
            ActionMenuPresenter actionMenuPresenter = ActionMenuPresenter.this;
            if (((android.support.v7.view.menu.a) actionMenuPresenter).f868d != null) {
                ((android.support.v7.view.menu.a) actionMenuPresenter).f868d.d();
            }
            View view = (View) ((android.support.v7.view.menu.a) actionMenuPresenter).f873i;
            if (view != null && view.getWindowToken() != null) {
                e eVar = this.f1086b;
                if (eVar.k()) {
                    actionMenuPresenter.f1078u = eVar;
                }
            }
            actionMenuPresenter.f1080w = null;
        }
    }

    private class d extends n implements ActionMenuView.a {

        final class a extends h0 {
            a(View view) {
                super(view);
            }

            @Override // android.support.v7.widget.h0
            public final o.b b() {
                e eVar = ActionMenuPresenter.this.f1078u;
                if (eVar == null) {
                    return null;
                }
                return eVar.b();
            }

            @Override // android.support.v7.widget.h0
            public final boolean c() {
                ActionMenuPresenter.this.C();
                return true;
            }

            @Override // android.support.v7.widget.h0
            public final boolean d() {
                ActionMenuPresenter actionMenuPresenter = ActionMenuPresenter.this;
                if (actionMenuPresenter.f1080w != null) {
                    return false;
                }
                actionMenuPresenter.v();
                return true;
            }
        }

        public d(Context context) {
            super(context, null, k.a.actionOverflowButtonStyle);
            setClickable(true);
            setFocusable(true);
            setVisibility(0);
            setEnabled(true);
            setTooltipText(getContentDescription());
            setOnTouchListener(new a(this));
        }

        @Override // android.support.v7.widget.ActionMenuView.a
        public final boolean c() {
            return false;
        }

        @Override // android.support.v7.widget.ActionMenuView.a
        public final boolean d() {
            return false;
        }

        @Override // android.view.View
        public final boolean performClick() {
            if (super.performClick()) {
                return true;
            }
            playSoundEffect(0);
            ActionMenuPresenter.this.C();
            return true;
        }

        @Override // android.widget.ImageView
        protected final boolean setFrame(int i2, int i3, int i4, int i5) {
            boolean frame = super.setFrame(i2, i3, i4, i5);
            Drawable drawable = getDrawable();
            Drawable background = getBackground();
            if (drawable != null && background != null) {
                int width = getWidth();
                int height = getHeight();
                int iMax = Math.max(width, height) / 2;
                int paddingLeft = (width + (getPaddingLeft() - getPaddingRight())) / 2;
                int paddingTop = (height + (getPaddingTop() - getPaddingBottom())) / 2;
                background.setHotspotBounds(paddingLeft - iMax, paddingTop - iMax, paddingLeft + iMax, paddingTop + iMax);
            }
            return frame;
        }
    }

    private class e extends android.support.v7.view.menu.n {
        public e(Context context, android.support.v7.view.menu.h hVar, View view) {
            super(k.a.actionOverflowMenuStyle, context, hVar, view, true);
            g(8388613);
            i(ActionMenuPresenter.this.f1082y);
        }

        @Override // android.support.v7.view.menu.n
        protected final void d() {
            ActionMenuPresenter actionMenuPresenter = ActionMenuPresenter.this;
            if (((android.support.v7.view.menu.a) actionMenuPresenter).f868d != null) {
                ((android.support.v7.view.menu.a) actionMenuPresenter).f868d.e(true);
            }
            actionMenuPresenter.f1078u = null;
            super.d();
        }
    }

    private class f implements o.a {
        f() {
        }

        @Override // android.support.v7.view.menu.o.a
        public final void b(android.support.v7.view.menu.h hVar, boolean z2) {
            if (hVar instanceof android.support.v7.view.menu.t) {
                hVar.q().e(false);
            }
            o.a aVarK = ActionMenuPresenter.this.k();
            if (aVarK != null) {
                aVarK.b(hVar, z2);
            }
        }

        @Override // android.support.v7.view.menu.o.a
        public final boolean c(android.support.v7.view.menu.t tVar) {
            if (tVar == null) {
                return false;
            }
            tVar.getItem().getClass();
            ActionMenuPresenter actionMenuPresenter = ActionMenuPresenter.this;
            actionMenuPresenter.getClass();
            o.a aVarK = actionMenuPresenter.k();
            if (aVarK != null) {
                return aVarK.c(tVar);
            }
            return false;
        }
    }

    public ActionMenuPresenter(Context context) {
        super(context, k.g.abc_action_menu_layout, k.g.abc_action_menu_item_layout);
        this.s = new SparseBooleanArray();
        this.f1082y = new f();
    }

    public final void A(Drawable drawable) {
        d dVar = this.f1068j;
        if (dVar != null) {
            dVar.setImageDrawable(drawable);
        } else {
            this.f1070l = true;
            this.f1069k = drawable;
        }
    }

    public final void B() {
        this.f1071m = true;
        this.f1072n = true;
    }

    public final boolean C() {
        android.support.v7.view.menu.h hVar;
        if (!this.f1071m || w() || (hVar = this.f868d) == null || this.f873i == null || this.f1080w != null || hVar.p().isEmpty()) {
            return false;
        }
        c cVar = new c(new e(this.f867c, this.f868d, this.f1068j));
        this.f1080w = cVar;
        ((View) this.f873i).post(cVar);
        super.c(null);
        return true;
    }

    @Override // android.support.v7.view.menu.a
    public final void a(android.support.v7.view.menu.j jVar, p.a aVar) {
        aVar.a(jVar);
        ActionMenuItemView actionMenuItemView = (ActionMenuItemView) aVar;
        actionMenuItemView.setItemInvoker((ActionMenuView) this.f873i);
        if (this.f1081x == null) {
            this.f1081x = new b();
        }
        actionMenuItemView.setPopupCallback(this.f1081x);
    }

    @Override // android.support.v7.view.menu.a, android.support.v7.view.menu.o
    public final void b(android.support.v7.view.menu.h hVar, boolean z2) {
        v();
        a aVar = this.f1079v;
        if (aVar != null) {
            aVar.a();
        }
        super.b(hVar, z2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.support.v7.view.menu.a, android.support.v7.view.menu.o
    public final boolean c(android.support.v7.view.menu.t tVar) {
        boolean z2 = false;
        if (!tVar.hasVisibleItems()) {
            return false;
        }
        android.support.v7.view.menu.t tVar2 = tVar;
        while (tVar2.N() != this.f868d) {
            tVar2 = (android.support.v7.view.menu.t) tVar2.N();
        }
        MenuItem item = tVar2.getItem();
        ViewGroup viewGroup = (ViewGroup) this.f873i;
        View view = null;
        view = null;
        if (viewGroup != null) {
            int childCount = viewGroup.getChildCount();
            int i2 = 0;
            while (true) {
                if (i2 >= childCount) {
                    break;
                }
                View childAt = viewGroup.getChildAt(i2);
                if ((childAt instanceof p.a) && ((p.a) childAt).getItemData() == item) {
                    view = childAt;
                    break;
                }
                i2++;
            }
        }
        if (view == null) {
            return false;
        }
        tVar.getItem().getClass();
        int size = tVar.size();
        int i3 = 0;
        while (true) {
            if (i3 >= size) {
                break;
            }
            MenuItem item2 = tVar.getItem(i3);
            if (item2.isVisible() && item2.getIcon() != null) {
                z2 = true;
                break;
            }
            i3++;
        }
        a aVar = new a(this.f867c, tVar, view);
        this.f1079v = aVar;
        aVar.f(z2);
        if (!this.f1079v.k()) {
            throw new IllegalStateException("MenuPopupHelper cannot be used without an anchor");
        }
        super.c(tVar);
        return true;
    }

    @Override // android.support.v7.view.menu.a, android.support.v7.view.menu.o
    public final void e(Context context, android.support.v7.view.menu.h hVar) {
        super.e(context, hVar);
        Resources resources = context.getResources();
        n.a aVarB = n.a.b(context);
        if (!this.f1072n) {
            this.f1071m = true;
        }
        this.f1073o = aVarB.c();
        this.f1075q = aVarB.d();
        int measuredWidth = this.f1073o;
        if (this.f1071m) {
            if (this.f1068j == null) {
                d dVar = new d(this.f866b);
                this.f1068j = dVar;
                if (this.f1070l) {
                    dVar.setImageDrawable(this.f1069k);
                    this.f1069k = null;
                    this.f1070l = false;
                }
                int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
                this.f1068j.measure(iMakeMeasureSpec, iMakeMeasureSpec);
            }
            measuredWidth -= this.f1068j.getMeasuredWidth();
        } else {
            this.f1068j = null;
        }
        this.f1074p = measuredWidth;
        float f2 = resources.getDisplayMetrics().density;
        this.f1077t = null;
    }

    @Override // android.support.v7.view.menu.a
    public final boolean f(ViewGroup viewGroup, int i2) {
        if (viewGroup.getChildAt(i2) == this.f1068j) {
            return false;
        }
        viewGroup.removeViewAt(i2);
        return true;
    }

    @Override // android.support.v7.view.menu.a, android.support.v7.view.menu.o
    public final void h(boolean z2) {
        super.h(z2);
        ((View) this.f873i).requestLayout();
        android.support.v7.view.menu.h hVar = this.f868d;
        boolean z3 = false;
        if (hVar != null) {
            ArrayList<android.support.v7.view.menu.j> arrayListL = hVar.l();
            int size = arrayListL.size();
            for (int i2 = 0; i2 < size; i2++) {
                arrayListL.get(i2).getClass();
            }
        }
        android.support.v7.view.menu.h hVar2 = this.f868d;
        ArrayList<android.support.v7.view.menu.j> arrayListP = hVar2 != null ? hVar2.p() : null;
        if (this.f1071m && arrayListP != null) {
            int size2 = arrayListP.size();
            if (size2 == 1) {
                z3 = !arrayListP.get(0).isActionViewExpanded();
            } else if (size2 > 0) {
                z3 = true;
            }
        }
        if (z3) {
            if (this.f1068j == null) {
                this.f1068j = new d(this.f866b);
            }
            ViewGroup viewGroup = (ViewGroup) this.f1068j.getParent();
            if (viewGroup != this.f873i) {
                if (viewGroup != null) {
                    viewGroup.removeView(this.f1068j);
                }
                ActionMenuView actionMenuView = (ActionMenuView) this.f873i;
                d dVar = this.f1068j;
                actionMenuView.getClass();
                ActionMenuView.c cVarL = ActionMenuView.l();
                cVarL.f1101c = true;
                actionMenuView.addView(dVar, cVarL);
            }
        } else {
            d dVar2 = this.f1068j;
            if (dVar2 != null) {
                Object parent = dVar2.getParent();
                Object obj = this.f873i;
                if (parent == obj) {
                    ((ViewGroup) obj).removeView(this.f1068j);
                }
            }
        }
        ((ActionMenuView) this.f873i).setOverflowReserved(this.f1071m);
    }

    @Override // android.support.v7.view.menu.o
    public final boolean j() {
        ArrayList<android.support.v7.view.menu.j> arrayListR;
        int size;
        boolean z2;
        android.support.v7.view.menu.h hVar = this.f868d;
        boolean z3 = false;
        if (hVar != null) {
            arrayListR = hVar.r();
            size = arrayListR.size();
        } else {
            arrayListR = null;
            size = 0;
        }
        int i2 = this.f1075q;
        int i3 = this.f1074p;
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        ViewGroup viewGroup = (ViewGroup) this.f873i;
        int i4 = 0;
        boolean z4 = false;
        int i5 = 0;
        int i6 = 0;
        while (true) {
            z2 = true;
            if (i4 >= size) {
                break;
            }
            android.support.v7.view.menu.j jVar = arrayListR.get(i4);
            if (jVar.l()) {
                i5++;
            } else if (jVar.k()) {
                i6++;
            } else {
                z4 = true;
            }
            if (this.f1076r && jVar.isActionViewExpanded()) {
                i2 = 0;
            }
            i4++;
        }
        if (this.f1071m && (z4 || i6 + i5 > i2)) {
            i2--;
        }
        int i7 = i2 - i5;
        SparseBooleanArray sparseBooleanArray = this.s;
        sparseBooleanArray.clear();
        int i8 = 0;
        int i9 = 0;
        while (i8 < size) {
            android.support.v7.view.menu.j jVar2 = arrayListR.get(i8);
            if (jVar2.l()) {
                View viewL = l(jVar2, this.f1077t, viewGroup);
                if (this.f1077t == null) {
                    this.f1077t = viewL;
                }
                viewL.measure(iMakeMeasureSpec, iMakeMeasureSpec);
                int measuredWidth = viewL.getMeasuredWidth();
                i3 -= measuredWidth;
                if (i9 == 0) {
                    i9 = measuredWidth;
                }
                int groupId = jVar2.getGroupId();
                if (groupId != 0) {
                    sparseBooleanArray.put(groupId, z2);
                }
                jVar2.p(z2);
            } else if (jVar2.k()) {
                int groupId2 = jVar2.getGroupId();
                boolean z5 = sparseBooleanArray.get(groupId2);
                boolean z6 = ((i7 > 0 || z5) && i3 > 0) ? z2 : z3;
                if (z6) {
                    View viewL2 = l(jVar2, this.f1077t, viewGroup);
                    if (this.f1077t == null) {
                        this.f1077t = viewL2;
                    }
                    viewL2.measure(iMakeMeasureSpec, iMakeMeasureSpec);
                    int measuredWidth2 = viewL2.getMeasuredWidth();
                    i3 -= measuredWidth2;
                    if (i9 == 0) {
                        i9 = measuredWidth2;
                    }
                    z6 &= i3 + i9 > 0;
                }
                if (z6 && groupId2 != 0) {
                    sparseBooleanArray.put(groupId2, true);
                } else if (z5) {
                    sparseBooleanArray.put(groupId2, false);
                    for (int i10 = 0; i10 < i8; i10++) {
                        android.support.v7.view.menu.j jVar3 = arrayListR.get(i10);
                        if (jVar3.getGroupId() == groupId2) {
                            if (jVar3.i()) {
                                i7++;
                            }
                            jVar3.p(false);
                        }
                    }
                }
                if (z6) {
                    i7--;
                }
                jVar2.p(z6);
                z3 = false;
            } else {
                jVar2.p(z3);
            }
            i8++;
            z2 = true;
        }
        return z2;
    }

    @Override // android.support.v7.view.menu.a
    public final View l(android.support.v7.view.menu.j jVar, View view, ViewGroup viewGroup) {
        View actionView = jVar.getActionView();
        if (actionView == null || jVar.g()) {
            actionView = super.l(jVar, view, viewGroup);
        }
        actionView.setVisibility(jVar.isActionViewExpanded() ? 8 : 0);
        ViewGroup.LayoutParams layoutParams = actionView.getLayoutParams();
        if (!((ActionMenuView) viewGroup).checkLayoutParams(layoutParams)) {
            actionView.setLayoutParams(ActionMenuView.m(layoutParams));
        }
        return actionView;
    }

    @Override // android.support.v7.view.menu.a
    public final android.support.v7.view.menu.p m(ViewGroup viewGroup) {
        android.support.v7.view.menu.p pVar = this.f873i;
        android.support.v7.view.menu.p pVarM = super.m(viewGroup);
        if (pVar != pVarM) {
            ((ActionMenuView) pVarM).setPresenter(this);
        }
        return pVarM;
    }

    @Override // android.support.v7.view.menu.a
    public final boolean n(android.support.v7.view.menu.j jVar) {
        return jVar.i();
    }

    public final Drawable u() {
        d dVar = this.f1068j;
        if (dVar != null) {
            return dVar.getDrawable();
        }
        if (this.f1070l) {
            return this.f1069k;
        }
        return null;
    }

    public final boolean v() {
        Object obj;
        c cVar = this.f1080w;
        if (cVar != null && (obj = this.f873i) != null) {
            ((View) obj).removeCallbacks(cVar);
            this.f1080w = null;
            return true;
        }
        e eVar = this.f1078u;
        if (eVar == null) {
            return false;
        }
        eVar.a();
        return true;
    }

    public final boolean w() {
        e eVar = this.f1078u;
        return eVar != null && eVar.c();
    }

    public final void x() {
        this.f1075q = n.a.b(this.f867c).d();
        android.support.v7.view.menu.h hVar = this.f868d;
        if (hVar != null) {
            hVar.w(true);
        }
    }

    public final void y(boolean z2) {
        this.f1076r = z2;
    }

    public final void z(ActionMenuView actionMenuView) {
        this.f873i = actionMenuView;
        actionMenuView.c(this.f868d);
    }
}
