package android.support.v7.view.menu;

import android.R;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.view.menu.n;
import android.support.v7.view.menu.o;
import android.support.v7.widget.m0;
import android.support.v7.widget.n0;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/* JADX INFO: compiled from: CascadingMenuPopup.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class d extends m implements View.OnKeyListener, PopupWindow.OnDismissListener {
    boolean A;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Context f878c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final int f879d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final int f880e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final boolean f881f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    final Handler f882g;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private View f890o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    View f891p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private int f892q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private boolean f893r;
    private boolean s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    private int f894t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    private int f895u;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    private boolean f897w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    private o.a f898x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    private ViewTreeObserver f899y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    private PopupWindow.OnDismissListener f900z;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final LinkedList f883h = new LinkedList();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    final ArrayList f884i = new ArrayList();

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private final ViewTreeObserver.OnGlobalLayoutListener f885j = new a();

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private final View.OnAttachStateChangeListener f886k = new b();

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private final m0 f887l = new c();

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private int f888m = 0;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private int f889n = 0;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    private boolean f896v = false;

    /* JADX INFO: compiled from: CascadingMenuPopup.java */
    final class a implements ViewTreeObserver.OnGlobalLayoutListener {
        a() {
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public final void onGlobalLayout() {
            d dVar = d.this;
            if (dVar.k()) {
                ArrayList arrayList = dVar.f884i;
                if (arrayList.size() <= 0 || ((C0015d) arrayList.get(0)).f904a.i()) {
                    return;
                }
                View view = dVar.f891p;
                if (view == null || !view.isShown()) {
                    dVar.dismiss();
                    return;
                }
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    ((C0015d) it.next()).f904a.a();
                }
            }
        }
    }

    /* JADX INFO: compiled from: CascadingMenuPopup.java */
    final class b implements View.OnAttachStateChangeListener {
        b() {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public final void onViewAttachedToWindow(View view) {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public final void onViewDetachedFromWindow(View view) {
            d dVar = d.this;
            if (dVar.f899y != null) {
                if (!dVar.f899y.isAlive()) {
                    dVar.f899y = view.getViewTreeObserver();
                }
                dVar.f899y.removeGlobalOnLayoutListener(dVar.f885j);
            }
            view.removeOnAttachStateChangeListener(this);
        }
    }

    /* JADX INFO: compiled from: CascadingMenuPopup.java */
    final class c implements m0 {
        c() {
        }

        @Override // android.support.v7.widget.m0
        public final void b(h hVar, j jVar) {
            d dVar = d.this;
            dVar.f882g.removeCallbacksAndMessages(null);
            ArrayList arrayList = dVar.f884i;
            int size = arrayList.size();
            int i2 = 0;
            while (true) {
                if (i2 >= size) {
                    i2 = -1;
                    break;
                } else if (hVar == ((C0015d) arrayList.get(i2)).f905b) {
                    break;
                } else {
                    i2++;
                }
            }
            if (i2 == -1) {
                return;
            }
            int i3 = i2 + 1;
            dVar.f882g.postAtTime(new e(this, i3 < arrayList.size() ? (C0015d) arrayList.get(i3) : null, jVar, hVar), hVar, SystemClock.uptimeMillis() + 200);
        }

        @Override // android.support.v7.widget.m0
        public final void c(h hVar, j jVar) {
            d.this.f882g.removeCallbacksAndMessages(hVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: android.support.v7.view.menu.d$d, reason: collision with other inner class name */
    /* JADX INFO: compiled from: CascadingMenuPopup.java */
    static class C0015d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final n0 f904a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final h f905b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final int f906c;

        public C0015d(n0 n0Var, h hVar, int i2) {
            this.f904a = n0Var;
            this.f905b = hVar;
            this.f906c = i2;
        }
    }

    public d(Context context, View view, int i2, boolean z2) {
        this.f878c = context;
        this.f890o = view;
        this.f880e = i2;
        this.f881f = z2;
        this.f892q = view.getLayoutDirection() != 1 ? 1 : 0;
        Resources resources = context.getResources();
        this.f879d = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(k.d.abc_config_prefDialogWidth));
        this.f882g = new Handler();
    }

    /* JADX WARN: Removed duplicated region for block: B:55:0x00fb  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0137  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0145  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0151  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x015d  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0171  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void z(h hVar) {
        boolean z2;
        C0015d c0015d;
        View childAt;
        int i2;
        int i3;
        MenuItem item;
        g gVar;
        int headersCount;
        int firstVisiblePosition;
        Context context = this.f878c;
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(context);
        g gVar2 = new g(hVar, layoutInflaterFrom, this.f881f);
        if (!k() && this.f896v) {
            gVar2.e(true);
        } else if (k()) {
            int size = hVar.size();
            int i4 = 0;
            while (true) {
                if (i4 >= size) {
                    z2 = false;
                    break;
                }
                MenuItem item2 = hVar.getItem(i4);
                if (item2.isVisible() && item2.getIcon() != null) {
                    z2 = true;
                    break;
                }
                i4++;
            }
            gVar2.e(z2);
        }
        int iN = m.n(gVar2, context, this.f879d);
        n0 n0Var = new n0(context, null, this.f880e);
        n0Var.z(this.f887l);
        n0Var.u(this);
        n0Var.t(this);
        n0Var.l(this.f890o);
        n0Var.o(this.f889n);
        n0Var.s();
        n0Var.r();
        n0Var.j(gVar2);
        n0Var.n(iN);
        n0Var.o(this.f889n);
        ArrayList arrayList = this.f884i;
        if (arrayList.size() > 0) {
            c0015d = (C0015d) arrayList.get(arrayList.size() - 1);
            h hVar2 = c0015d.f905b;
            int size2 = hVar2.size();
            int i5 = 0;
            while (true) {
                if (i5 >= size2) {
                    item = null;
                    break;
                }
                item = hVar2.getItem(i5);
                if (item.hasSubMenu() && hVar == item.getSubMenu()) {
                    break;
                } else {
                    i5++;
                }
            }
            if (item == null) {
                childAt = null;
            } else {
                ListView listViewF = c0015d.f904a.f();
                ListAdapter adapter = listViewF.getAdapter();
                if (adapter instanceof HeaderViewListAdapter) {
                    HeaderViewListAdapter headerViewListAdapter = (HeaderViewListAdapter) adapter;
                    headersCount = headerViewListAdapter.getHeadersCount();
                    gVar = (g) headerViewListAdapter.getWrappedAdapter();
                } else {
                    gVar = (g) adapter;
                    headersCount = 0;
                }
                int count = gVar.getCount();
                int i6 = 0;
                while (true) {
                    if (i6 >= count) {
                        i6 = -1;
                        break;
                    } else if (item == gVar.getItem(i6)) {
                        break;
                    } else {
                        i6++;
                    }
                }
                if (i6 != -1 && (firstVisiblePosition = (i6 + headersCount) - listViewF.getFirstVisiblePosition()) >= 0 && firstVisiblePosition < listViewF.getChildCount()) {
                    childAt = listViewF.getChildAt(firstVisiblePosition);
                }
            }
            if (childAt == null) {
                n0Var.A();
                n0Var.x();
                ListView listViewF2 = ((C0015d) arrayList.get(arrayList.size() - 1)).f904a.f();
                int[] iArr = new int[2];
                listViewF2.getLocationOnScreen(iArr);
                Rect rect = new Rect();
                this.f891p.getWindowVisibleDisplayFrame(rect);
                if (this.f892q == 1) {
                    i2 = (listViewF2.getWidth() + iArr[0]) + iN > rect.right ? 0 : 1;
                    boolean z3 = i2 == 1;
                    this.f892q = i2;
                    n0Var.l(childAt);
                    if ((this.f889n & 5) == 5) {
                        i3 = 0;
                        iN = z3 ? childAt.getWidth() : 0 - iN;
                    } else if (z3) {
                        i3 = 0;
                    } else {
                        i3 = 0;
                        iN = 0 - childAt.getWidth();
                    }
                    n0Var.q(iN);
                    n0Var.v();
                    n0Var.w(i3);
                } else {
                    if (iArr[0] - iN >= 0) {
                        i2 = 0;
                    }
                    if (i2 == 1) {
                    }
                    this.f892q = i2;
                    n0Var.l(childAt);
                    if ((this.f889n & 5) == 5) {
                    }
                    n0Var.q(iN);
                    n0Var.v();
                    n0Var.w(i3);
                }
            } else {
                if (this.f893r) {
                    n0Var.q(this.f894t);
                }
                if (this.s) {
                    n0Var.w(this.f895u);
                }
                n0Var.p(m());
            }
            arrayList.add(new C0015d(n0Var, hVar, this.f892q));
            n0Var.a();
            ListView listViewF3 = n0Var.f();
            listViewF3.setOnKeyListener(this);
            if (c0015d == null || !this.f897w || hVar.f939m == null) {
                return;
            }
            FrameLayout frameLayout = (FrameLayout) layoutInflaterFrom.inflate(k.g.abc_popup_menu_header_item_layout, (ViewGroup) listViewF3, false);
            TextView textView = (TextView) frameLayout.findViewById(R.id.title);
            frameLayout.setEnabled(false);
            textView.setText(hVar.f939m);
            listViewF3.addHeaderView(frameLayout, null, false);
            n0Var.a();
            return;
        }
        c0015d = null;
        childAt = null;
        if (childAt == null) {
        }
        arrayList.add(new C0015d(n0Var, hVar, this.f892q));
        n0Var.a();
        ListView listViewF32 = n0Var.f();
        listViewF32.setOnKeyListener(this);
        if (c0015d == null) {
        }
    }

    @Override // o.b
    public final void a() {
        if (k()) {
            return;
        }
        LinkedList linkedList = this.f883h;
        Iterator it = linkedList.iterator();
        while (it.hasNext()) {
            z((h) it.next());
        }
        linkedList.clear();
        View view = this.f890o;
        this.f891p = view;
        if (view != null) {
            boolean z2 = this.f899y == null;
            ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
            this.f899y = viewTreeObserver;
            if (z2) {
                viewTreeObserver.addOnGlobalLayoutListener(this.f885j);
            }
            this.f891p.addOnAttachStateChangeListener(this.f886k);
        }
    }

    @Override // android.support.v7.view.menu.o
    public final void b(h hVar, boolean z2) {
        ArrayList arrayList = this.f884i;
        int size = arrayList.size();
        int i2 = 0;
        while (true) {
            if (i2 >= size) {
                i2 = -1;
                break;
            } else if (hVar == ((C0015d) arrayList.get(i2)).f905b) {
                break;
            } else {
                i2++;
            }
        }
        if (i2 < 0) {
            return;
        }
        int i3 = i2 + 1;
        if (i3 < arrayList.size()) {
            ((C0015d) arrayList.get(i3)).f905b.e(false);
        }
        C0015d c0015d = (C0015d) arrayList.remove(i2);
        c0015d.f905b.y(this);
        boolean z3 = this.A;
        n0 n0Var = c0015d.f904a;
        if (z3) {
            n0Var.y();
            n0Var.m();
        }
        n0Var.dismiss();
        int size2 = arrayList.size();
        if (size2 > 0) {
            this.f892q = ((C0015d) arrayList.get(size2 - 1)).f906c;
        } else {
            this.f892q = this.f890o.getLayoutDirection() == 1 ? 0 : 1;
        }
        if (size2 != 0) {
            if (z2) {
                ((C0015d) arrayList.get(0)).f905b.e(false);
                return;
            }
            return;
        }
        dismiss();
        o.a aVar = this.f898x;
        if (aVar != null) {
            aVar.b(hVar, true);
        }
        ViewTreeObserver viewTreeObserver = this.f899y;
        if (viewTreeObserver != null) {
            if (viewTreeObserver.isAlive()) {
                this.f899y.removeGlobalOnLayoutListener(this.f885j);
            }
            this.f899y = null;
        }
        this.f891p.removeOnAttachStateChangeListener(this.f886k);
        ((n.a) this.f900z).onDismiss();
    }

    @Override // android.support.v7.view.menu.o
    public final boolean c(t tVar) {
        for (C0015d c0015d : this.f884i) {
            if (tVar == c0015d.f905b) {
                c0015d.f904a.f().requestFocus();
                return true;
            }
        }
        if (!tVar.hasVisibleItems()) {
            return false;
        }
        l(tVar);
        o.a aVar = this.f898x;
        if (aVar != null) {
            aVar.c(tVar);
        }
        return true;
    }

    @Override // android.support.v7.view.menu.o
    public final void d(o.a aVar) {
        this.f898x = aVar;
    }

    @Override // o.b
    public final void dismiss() {
        ArrayList arrayList = this.f884i;
        int size = arrayList.size();
        if (size > 0) {
            C0015d[] c0015dArr = (C0015d[]) arrayList.toArray(new C0015d[size]);
            for (int i2 = size - 1; i2 >= 0; i2--) {
                C0015d c0015d = c0015dArr[i2];
                if (c0015d.f904a.k()) {
                    c0015d.f904a.dismiss();
                }
            }
        }
    }

    @Override // o.b
    public final ListView f() {
        ArrayList arrayList = this.f884i;
        if (arrayList.isEmpty()) {
            return null;
        }
        return ((C0015d) arrayList.get(arrayList.size() - 1)).f904a.f();
    }

    @Override // android.support.v7.view.menu.o
    public final void h(boolean z2) {
        Iterator it = this.f884i.iterator();
        while (it.hasNext()) {
            ListAdapter adapter = ((C0015d) it.next()).f904a.f().getAdapter();
            if (adapter instanceof HeaderViewListAdapter) {
                adapter = ((HeaderViewListAdapter) adapter).getWrappedAdapter();
            }
            ((g) adapter).notifyDataSetChanged();
        }
    }

    @Override // android.support.v7.view.menu.o
    public final boolean j() {
        return false;
    }

    @Override // o.b
    public final boolean k() {
        ArrayList arrayList = this.f884i;
        return arrayList.size() > 0 && ((C0015d) arrayList.get(0)).f904a.k();
    }

    @Override // android.support.v7.view.menu.m
    public final void l(h hVar) {
        hVar.c(this, this.f878c);
        if (k()) {
            z(hVar);
        } else {
            this.f883h.add(hVar);
        }
    }

    @Override // android.support.v7.view.menu.m
    public final void o(View view) {
        if (this.f890o != view) {
            this.f890o = view;
            this.f889n = Gravity.getAbsoluteGravity(this.f888m, view.getLayoutDirection());
        }
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public final void onDismiss() {
        C0015d c0015d;
        ArrayList arrayList = this.f884i;
        int size = arrayList.size();
        int i2 = 0;
        while (true) {
            if (i2 >= size) {
                c0015d = null;
                break;
            }
            c0015d = (C0015d) arrayList.get(i2);
            if (!c0015d.f904a.k()) {
                break;
            } else {
                i2++;
            }
        }
        if (c0015d != null) {
            c0015d.f905b.e(false);
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
        this.f896v = z2;
    }

    @Override // android.support.v7.view.menu.m
    public final void r(int i2) {
        if (this.f888m != i2) {
            this.f888m = i2;
            this.f889n = Gravity.getAbsoluteGravity(i2, this.f890o.getLayoutDirection());
        }
    }

    @Override // android.support.v7.view.menu.m
    public final void s(int i2) {
        this.f893r = true;
        this.f894t = i2;
    }

    @Override // android.support.v7.view.menu.m
    public final void t(PopupWindow.OnDismissListener onDismissListener) {
        this.f900z = onDismissListener;
    }

    @Override // android.support.v7.view.menu.m
    public final void u(boolean z2) {
        this.f897w = z2;
    }

    @Override // android.support.v7.view.menu.m
    public final void v(int i2) {
        this.s = true;
        this.f895u = i2;
    }
}
