package android.support.v7.app;

import android.R;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.app.AppCompatDelegateImplV9;
import android.support.v7.app.a;
import android.support.v7.view.menu.h;
import android.support.v7.widget.ActionBarContainer;
import android.support.v7.widget.ActionBarContextView;
import android.support.v7.widget.ActionBarOverlayLayout;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.d0;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import n.b;

/* JADX INFO: compiled from: WindowDecorActionBar.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class s extends android.support.v7.app.a implements ActionBarOverlayLayout.d {

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    private static final AccelerateInterpolator f805y = new AccelerateInterpolator();

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    private static final DecelerateInterpolator f806z = new DecelerateInterpolator();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    Context f807a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Context f808b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    ActionBarOverlayLayout f809c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    ActionBarContainer f810d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    d0 f811e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    ActionBarContextView f812f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    View f813g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private boolean f814h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    d f815i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    d f816j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    b.a f817k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private boolean f818l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private ArrayList<a.b> f819m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private int f820n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    boolean f821o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    boolean f822p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private boolean f823q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private boolean f824r;
    n.h s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    private boolean f825t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    boolean f826u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    final android.support.v4.view.m f827v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    final android.support.v4.view.m f828w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    final android.support.v4.view.n f829x;

    /* JADX INFO: compiled from: WindowDecorActionBar.java */
    final class a extends com.badlogic.gdx.utils.l {
        a() {
        }

        @Override // android.support.v4.view.m
        public final void a() {
            View view;
            s sVar = s.this;
            if (sVar.f821o && (view = sVar.f813g) != null) {
                view.setTranslationY(0.0f);
                sVar.f810d.setTranslationY(0.0f);
            }
            sVar.f810d.setVisibility(8);
            sVar.f810d.setTransitioning(false);
            sVar.s = null;
            b.a aVar = sVar.f817k;
            if (aVar != null) {
                ((AppCompatDelegateImplV9.c) aVar).c(sVar.f816j);
                sVar.f816j = null;
                sVar.f817k = null;
            }
            ActionBarOverlayLayout actionBarOverlayLayout = sVar.f809c;
            if (actionBarOverlayLayout != null) {
                actionBarOverlayLayout.requestApplyInsets();
            }
        }
    }

    /* JADX INFO: compiled from: WindowDecorActionBar.java */
    final class b extends com.badlogic.gdx.utils.l {
        b() {
        }

        @Override // android.support.v4.view.m
        public final void a() {
            s sVar = s.this;
            sVar.s = null;
            sVar.f810d.requestLayout();
        }
    }

    /* JADX INFO: compiled from: WindowDecorActionBar.java */
    final class c implements android.support.v4.view.n {
        c() {
        }

        @Override // android.support.v4.view.n
        public final void a() {
            ((View) s.this.f810d.getParent()).invalidate();
        }
    }

    /* JADX INFO: compiled from: WindowDecorActionBar.java */
    public class d extends n.b implements h.a {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final Context f833d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final android.support.v7.view.menu.h f834e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private b.a f835f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private WeakReference<View> f836g;

        public d(Context context, b.a aVar) {
            this.f833d = context;
            this.f835f = aVar;
            android.support.v7.view.menu.h hVar = new android.support.v7.view.menu.h(context);
            hVar.C();
            this.f834e = hVar;
            hVar.B(this);
        }

        @Override // android.support.v7.view.menu.h.a
        public final boolean a(android.support.v7.view.menu.h hVar, android.support.v7.view.menu.j jVar) {
            b.a aVar = this.f835f;
            if (aVar != null) {
                return ((AppCompatDelegateImplV9.c) aVar).a(this, jVar);
            }
            return false;
        }

        @Override // android.support.v7.view.menu.h.a
        public final void b(android.support.v7.view.menu.h hVar) {
            if (this.f835f == null) {
                return;
            }
            k();
            s.this.f812f.k();
        }

        @Override // n.b
        public final void c() {
            s sVar = s.this;
            if (sVar.f815i != this) {
                return;
            }
            if (sVar.f822p) {
                sVar.f816j = this;
                sVar.f817k = this.f835f;
            } else {
                ((AppCompatDelegateImplV9.c) this.f835f).c(this);
            }
            this.f835f = null;
            sVar.a(false);
            sVar.f812f.e();
            sVar.f811e.j().sendAccessibilityEvent(32);
            sVar.f809c.setHideOnContentScrollEnabled(sVar.f826u);
            sVar.f815i = null;
        }

        @Override // n.b
        public final View d() {
            WeakReference<View> weakReference = this.f836g;
            if (weakReference != null) {
                return weakReference.get();
            }
            return null;
        }

        @Override // n.b
        public final android.support.v7.view.menu.h e() {
            return this.f834e;
        }

        @Override // n.b
        public final n.g f() {
            return new n.g(this.f833d);
        }

        @Override // n.b
        public final CharSequence g() {
            return s.this.f812f.getSubtitle();
        }

        @Override // n.b
        public final CharSequence i() {
            return s.this.f812f.getTitle();
        }

        @Override // n.b
        public final void k() {
            if (s.this.f815i != this) {
                return;
            }
            android.support.v7.view.menu.h hVar = this.f834e;
            hVar.M();
            try {
                ((AppCompatDelegateImplV9.c) this.f835f).b(this, hVar);
            } finally {
                hVar.L();
            }
        }

        @Override // n.b
        public final boolean l() {
            return s.this.f812f.h();
        }

        @Override // n.b
        public final void m(View view) {
            s.this.f812f.setCustomView(view);
            this.f836g = new WeakReference<>(view);
        }

        @Override // n.b
        public final void n(int i2) {
            o(s.this.f807a.getResources().getString(i2));
        }

        @Override // n.b
        public final void o(CharSequence charSequence) {
            s.this.f812f.setSubtitle(charSequence);
        }

        @Override // n.b
        public final void q(int i2) {
            r(s.this.f807a.getResources().getString(i2));
        }

        @Override // n.b
        public final void r(CharSequence charSequence) {
            s.this.f812f.setTitle(charSequence);
        }

        @Override // n.b
        public final void s(boolean z2) {
            super.s(z2);
            s.this.f812f.setTitleOptional(z2);
        }

        public final boolean t() {
            android.support.v7.view.menu.h hVar = this.f834e;
            hVar.M();
            try {
                return ((AppCompatDelegateImplV9.c) this.f835f).d(this, hVar);
            } finally {
                hVar.L();
            }
        }
    }

    public s(Activity activity, boolean z2) {
        new ArrayList();
        this.f819m = new ArrayList<>();
        this.f820n = 0;
        this.f821o = true;
        this.f824r = true;
        this.f827v = new a();
        this.f828w = new b();
        this.f829x = new c();
        View decorView = activity.getWindow().getDecorView();
        f(decorView);
        if (z2) {
            return;
        }
        this.f813g = decorView.findViewById(R.id.content);
    }

    private void f(View view) {
        d0 wrapper;
        ActionBarOverlayLayout actionBarOverlayLayout = (ActionBarOverlayLayout) view.findViewById(k.f.decor_content_parent);
        this.f809c = actionBarOverlayLayout;
        if (actionBarOverlayLayout != null) {
            actionBarOverlayLayout.setActionBarVisibilityCallback(this);
        }
        Object objFindViewById = view.findViewById(k.f.action_bar);
        if (objFindViewById instanceof d0) {
            wrapper = (d0) objFindViewById;
        } else {
            if (!(objFindViewById instanceof Toolbar)) {
                StringBuilder sb = new StringBuilder("Can't make a decor toolbar out of ");
                sb.append(objFindViewById);
                throw new IllegalStateException(sb.toString() != null ? objFindViewById.getClass().getSimpleName() : "null");
            }
            wrapper = ((Toolbar) objFindViewById).getWrapper();
        }
        this.f811e = wrapper;
        this.f812f = (ActionBarContextView) view.findViewById(k.f.action_context_bar);
        ActionBarContainer actionBarContainer = (ActionBarContainer) view.findViewById(k.f.action_bar_container);
        this.f810d = actionBarContainer;
        d0 d0Var = this.f811e;
        if (d0Var == null || this.f812f == null || actionBarContainer == null) {
            throw new IllegalStateException(s.class.getSimpleName().concat(" can only be used with a compatible window decor layout"));
        }
        this.f807a = d0Var.getContext();
        if ((this.f811e.n() & 4) != 0) {
            this.f814h = true;
        }
        n.a aVarB = n.a.b(this.f807a);
        aVarB.a();
        this.f811e.getClass();
        k(aVarB.g());
        TypedArray typedArrayObtainStyledAttributes = this.f807a.obtainStyledAttributes(null, k.j.ActionBar, k.a.actionBarStyle, 0);
        if (typedArrayObtainStyledAttributes.getBoolean(k.j.ActionBar_hideOnContentScroll, false)) {
            if (!this.f809c.m()) {
                throw new IllegalStateException("Action bar must be in overlay mode (Window.FEATURE_OVERLAY_ACTION_BAR) to enable hide on content scroll");
            }
            this.f826u = true;
            this.f809c.setHideOnContentScrollEnabled(true);
        }
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(k.j.ActionBar_elevation, 0);
        if (dimensionPixelSize != 0) {
            this.f810d.setElevation(dimensionPixelSize);
        }
        typedArrayObtainStyledAttributes.recycle();
    }

    private void k(boolean z2) {
        if (z2) {
            this.f810d.setTabContainer(null);
            this.f811e.m();
        } else {
            this.f811e.m();
            this.f810d.setTabContainer(null);
        }
        this.f811e.getClass();
        this.f811e.s(false);
        this.f809c.setHasNonEmbeddedTabs(false);
    }

    private void n(boolean z2) {
        boolean z3 = this.f823q || !this.f822p;
        android.support.v4.view.n nVar = this.f829x;
        View view = this.f813g;
        if (!z3) {
            if (this.f824r) {
                this.f824r = false;
                n.h hVar = this.s;
                if (hVar != null) {
                    hVar.a();
                }
                int i2 = this.f820n;
                android.support.v4.view.m mVar = this.f827v;
                if (i2 != 0 || (!this.f825t && !z2)) {
                    ((a) mVar).a();
                    return;
                }
                this.f810d.setAlpha(1.0f);
                this.f810d.setTransitioning(true);
                n.h hVar2 = new n.h();
                float f2 = -this.f810d.getHeight();
                if (z2) {
                    this.f810d.getLocationInWindow(new int[]{0, 0});
                    f2 -= r9[1];
                }
                android.support.v4.view.l lVarA = android.support.v4.view.h.a(this.f810d);
                lVarA.j(f2);
                lVarA.h(nVar);
                hVar2.c(lVarA);
                if (this.f821o && view != null) {
                    android.support.v4.view.l lVarA2 = android.support.v4.view.h.a(view);
                    lVarA2.j(f2);
                    hVar2.c(lVarA2);
                }
                hVar2.f(f805y);
                hVar2.e();
                hVar2.g((com.badlogic.gdx.utils.l) mVar);
                this.s = hVar2;
                hVar2.h();
                return;
            }
            return;
        }
        if (this.f824r) {
            return;
        }
        this.f824r = true;
        n.h hVar3 = this.s;
        if (hVar3 != null) {
            hVar3.a();
        }
        this.f810d.setVisibility(0);
        int i3 = this.f820n;
        android.support.v4.view.m mVar2 = this.f828w;
        if (i3 == 0 && (this.f825t || z2)) {
            this.f810d.setTranslationY(0.0f);
            float f3 = -this.f810d.getHeight();
            if (z2) {
                this.f810d.getLocationInWindow(new int[]{0, 0});
                f3 -= r9[1];
            }
            this.f810d.setTranslationY(f3);
            n.h hVar4 = new n.h();
            android.support.v4.view.l lVarA3 = android.support.v4.view.h.a(this.f810d);
            lVarA3.j(0.0f);
            lVarA3.h(nVar);
            hVar4.c(lVarA3);
            if (this.f821o && view != null) {
                view.setTranslationY(f3);
                android.support.v4.view.l lVarA4 = android.support.v4.view.h.a(view);
                lVarA4.j(0.0f);
                hVar4.c(lVarA4);
            }
            hVar4.f(f806z);
            hVar4.e();
            hVar4.g((com.badlogic.gdx.utils.l) mVar2);
            this.s = hVar4;
            hVar4.h();
        } else {
            this.f810d.setAlpha(1.0f);
            this.f810d.setTranslationY(0.0f);
            if (this.f821o && view != null) {
                view.setTranslationY(0.0f);
            }
            ((b) mVar2).a();
        }
        ActionBarOverlayLayout actionBarOverlayLayout = this.f809c;
        if (actionBarOverlayLayout != null) {
            actionBarOverlayLayout.requestApplyInsets();
        }
    }

    public final void a(boolean z2) {
        android.support.v4.view.l lVarP;
        android.support.v4.view.l lVarJ;
        if (z2) {
            if (!this.f823q) {
                this.f823q = true;
                ActionBarOverlayLayout actionBarOverlayLayout = this.f809c;
                if (actionBarOverlayLayout != null) {
                    actionBarOverlayLayout.setShowingForActionMode(true);
                }
                n(false);
            }
        } else if (this.f823q) {
            this.f823q = false;
            ActionBarOverlayLayout actionBarOverlayLayout2 = this.f809c;
            if (actionBarOverlayLayout2 != null) {
                actionBarOverlayLayout2.setShowingForActionMode(false);
            }
            n(false);
        }
        if (!this.f810d.isLaidOut()) {
            if (z2) {
                this.f811e.i(4);
                this.f812f.setVisibility(0);
                return;
            } else {
                this.f811e.i(0);
                this.f812f.setVisibility(8);
                return;
            }
        }
        if (z2) {
            lVarJ = this.f811e.p(4, 100L);
            lVarP = this.f812f.j(0, 200L);
        } else {
            lVarP = this.f811e.p(0, 200L);
            lVarJ = this.f812f.j(8, 100L);
        }
        n.h hVar = new n.h();
        hVar.d(lVarJ, lVarP);
        hVar.h();
    }

    public final void b(boolean z2) {
        if (z2 == this.f818l) {
            return;
        }
        this.f818l = z2;
        ArrayList<a.b> arrayList = this.f819m;
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            arrayList.get(i2).a();
        }
    }

    public final void c(boolean z2) {
        this.f821o = z2;
    }

    public final Context d() {
        if (this.f808b == null) {
            TypedValue typedValue = new TypedValue();
            this.f807a.getTheme().resolveAttribute(k.a.actionBarWidgetTheme, typedValue, true);
            int i2 = typedValue.resourceId;
            if (i2 != 0) {
                this.f808b = new ContextThemeWrapper(this.f807a, i2);
            } else {
                this.f808b = this.f807a;
            }
        }
        return this.f808b;
    }

    public final void e() {
        if (this.f822p) {
            return;
        }
        this.f822p = true;
        n(true);
    }

    public final void g() {
        k(n.a.b(this.f807a).g());
    }

    public final void h() {
        n.h hVar = this.s;
        if (hVar != null) {
            hVar.a();
            this.s = null;
        }
    }

    public final void i(int i2) {
        this.f820n = i2;
    }

    public final void j(boolean z2) {
        if (this.f814h) {
            return;
        }
        int i2 = z2 ? 4 : 0;
        int iN = this.f811e.n();
        this.f814h = true;
        this.f811e.l((i2 & 4) | (iN & (-5)));
    }

    public final void l(boolean z2) {
        n.h hVar;
        this.f825t = z2;
        if (z2 || (hVar = this.s) == null) {
            return;
        }
        hVar.a();
    }

    public final void m() {
        if (this.f822p) {
            this.f822p = false;
            n(true);
        }
    }

    public s(Dialog dialog) {
        new ArrayList();
        this.f819m = new ArrayList<>();
        this.f820n = 0;
        this.f821o = true;
        this.f824r = true;
        this.f827v = new a();
        this.f828w = new b();
        this.f829x = new c();
        f(dialog.getWindow().getDecorView());
    }
}
