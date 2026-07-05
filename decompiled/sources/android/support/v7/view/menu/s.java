package android.support.v7.view.menu;

import android.R;
import android.content.Context;
import android.content.res.Resources;
import android.support.v7.view.menu.o;
import android.support.v7.widget.n0;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

/* JADX INFO: compiled from: StandardMenuPopup.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class s extends m implements PopupWindow.OnDismissListener, View.OnKeyListener {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Context f998c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final h f999d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final g f1000e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final boolean f1001f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final int f1002g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final int f1003h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    final n0 f1004i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private PopupWindow.OnDismissListener f1007l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private View f1008m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    View f1009n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private o.a f1010o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private ViewTreeObserver f1011p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private boolean f1012q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private boolean f1013r;
    private int s;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    private boolean f1015u;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private final ViewTreeObserver.OnGlobalLayoutListener f1005j = new a();

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private final View.OnAttachStateChangeListener f1006k = new b();

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    private int f1014t = 0;

    /* JADX INFO: compiled from: StandardMenuPopup.java */
    final class a implements ViewTreeObserver.OnGlobalLayoutListener {
        a() {
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public final void onGlobalLayout() {
            s sVar = s.this;
            if (sVar.k()) {
                n0 n0Var = sVar.f1004i;
                if (n0Var.i()) {
                    return;
                }
                View view = sVar.f1009n;
                if (view == null || !view.isShown()) {
                    sVar.dismiss();
                } else {
                    n0Var.a();
                }
            }
        }
    }

    /* JADX INFO: compiled from: StandardMenuPopup.java */
    final class b implements View.OnAttachStateChangeListener {
        b() {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public final void onViewAttachedToWindow(View view) {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public final void onViewDetachedFromWindow(View view) {
            s sVar = s.this;
            if (sVar.f1011p != null) {
                if (!sVar.f1011p.isAlive()) {
                    sVar.f1011p = view.getViewTreeObserver();
                }
                sVar.f1011p.removeGlobalOnLayoutListener(sVar.f1005j);
            }
            view.removeOnAttachStateChangeListener(this);
        }
    }

    public s(int i2, Context context, h hVar, View view, boolean z2) {
        this.f998c = context;
        this.f999d = hVar;
        this.f1001f = z2;
        this.f1000e = new g(hVar, LayoutInflater.from(context), z2);
        this.f1003h = i2;
        Resources resources = context.getResources();
        this.f1002g = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(k.d.abc_config_prefDialogWidth));
        this.f1008m = view;
        this.f1004i = new n0(context, null, i2);
        hVar.c(this, context);
    }

    @Override // o.b
    public final void a() {
        View view;
        if (k()) {
            return;
        }
        if (this.f1012q || (view = this.f1008m) == null) {
            throw new IllegalStateException("StandardMenuPopup cannot be used without an anchor");
        }
        this.f1009n = view;
        n0 n0Var = this.f1004i;
        n0Var.t(this);
        n0Var.u(this);
        n0Var.s();
        View view2 = this.f1009n;
        boolean z2 = this.f1011p == null;
        ViewTreeObserver viewTreeObserver = view2.getViewTreeObserver();
        this.f1011p = viewTreeObserver;
        if (z2) {
            viewTreeObserver.addOnGlobalLayoutListener(this.f1005j);
        }
        view2.addOnAttachStateChangeListener(this.f1006k);
        n0Var.l(view2);
        n0Var.o(this.f1014t);
        boolean z3 = this.f1013r;
        Context context = this.f998c;
        g gVar = this.f1000e;
        if (!z3) {
            this.s = m.n(gVar, context, this.f1002g);
            this.f1013r = true;
        }
        n0Var.n(this.s);
        n0Var.r();
        n0Var.p(m());
        n0Var.a();
        ListView listViewF = n0Var.f();
        listViewF.setOnKeyListener(this);
        if (this.f1015u) {
            h hVar = this.f999d;
            if (hVar.f939m != null) {
                FrameLayout frameLayout = (FrameLayout) LayoutInflater.from(context).inflate(k.g.abc_popup_menu_header_item_layout, (ViewGroup) listViewF, false);
                TextView textView = (TextView) frameLayout.findViewById(R.id.title);
                if (textView != null) {
                    textView.setText(hVar.f939m);
                }
                frameLayout.setEnabled(false);
                listViewF.addHeaderView(frameLayout, null, false);
            }
        }
        n0Var.j(gVar);
        n0Var.a();
    }

    @Override // android.support.v7.view.menu.o
    public final void b(h hVar, boolean z2) {
        if (hVar != this.f999d) {
            return;
        }
        dismiss();
        o.a aVar = this.f1010o;
        if (aVar != null) {
            aVar.b(hVar, z2);
        }
    }

    @Override // android.support.v7.view.menu.o
    public final boolean c(t tVar) {
        boolean z2;
        if (tVar.hasVisibleItems()) {
            n nVar = new n(this.f1003h, this.f998c, tVar, this.f1009n, this.f1001f);
            nVar.i(this.f1010o);
            int size = tVar.size();
            int i2 = 0;
            while (true) {
                if (i2 >= size) {
                    z2 = false;
                    break;
                }
                MenuItem item = tVar.getItem(i2);
                if (item.isVisible() && item.getIcon() != null) {
                    z2 = true;
                    break;
                }
                i2++;
            }
            nVar.f(z2);
            nVar.g(this.f1014t);
            nVar.h(this.f1007l);
            this.f1007l = null;
            this.f999d.e(false);
            n0 n0Var = this.f1004i;
            if (nVar.l(n0Var.e(), n0Var.g())) {
                o.a aVar = this.f1010o;
                if (aVar != null) {
                    aVar.c(tVar);
                }
                return true;
            }
        }
        return false;
    }

    @Override // android.support.v7.view.menu.o
    public final void d(o.a aVar) {
        this.f1010o = aVar;
    }

    @Override // o.b
    public final void dismiss() {
        if (k()) {
            this.f1004i.dismiss();
        }
    }

    @Override // o.b
    public final ListView f() {
        return this.f1004i.f();
    }

    @Override // android.support.v7.view.menu.o
    public final void h(boolean z2) {
        this.f1013r = false;
        g gVar = this.f1000e;
        if (gVar != null) {
            gVar.notifyDataSetChanged();
        }
    }

    @Override // android.support.v7.view.menu.o
    public final boolean j() {
        return false;
    }

    @Override // o.b
    public final boolean k() {
        return !this.f1012q && this.f1004i.k();
    }

    @Override // android.support.v7.view.menu.m
    public final void l(h hVar) {
    }

    @Override // android.support.v7.view.menu.m
    public final void o(View view) {
        this.f1008m = view;
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public final void onDismiss() {
        this.f1012q = true;
        this.f999d.e(true);
        ViewTreeObserver viewTreeObserver = this.f1011p;
        if (viewTreeObserver != null) {
            if (!viewTreeObserver.isAlive()) {
                this.f1011p = this.f1009n.getViewTreeObserver();
            }
            this.f1011p.removeGlobalOnLayoutListener(this.f1005j);
            this.f1011p = null;
        }
        this.f1009n.removeOnAttachStateChangeListener(this.f1006k);
        PopupWindow.OnDismissListener onDismissListener = this.f1007l;
        if (onDismissListener != null) {
            onDismissListener.onDismiss();
        }
    }

    @Override // android.view.View.OnKeyListener
    public final boolean onKey(View view, int i2, KeyEvent keyEvent) {
        if (keyEvent.getAction() != 1 || i2 != 82) {
            return false;
        }
        dismiss();
        return true;
    }

    @Override // android.support.v7.view.menu.m
    public final void q(boolean z2) {
        this.f1000e.e(z2);
    }

    @Override // android.support.v7.view.menu.m
    public final void r(int i2) {
        this.f1014t = i2;
    }

    @Override // android.support.v7.view.menu.m
    public final void s(int i2) {
        this.f1004i.q(i2);
    }

    @Override // android.support.v7.view.menu.m
    public final void t(PopupWindow.OnDismissListener onDismissListener) {
        this.f1007l = onDismissListener;
    }

    @Override // android.support.v7.view.menu.m
    public final void u(boolean z2) {
        this.f1015u = z2;
    }

    @Override // android.support.v7.view.menu.m
    public final void v(int i2) {
        this.f1004i.w(i2);
    }
}
