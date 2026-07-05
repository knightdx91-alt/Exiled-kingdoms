package android.support.v7.widget;

import android.content.Context;
import android.support.v7.view.menu.ListMenuItemView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.PopupWindow;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: MenuPopupWindow.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class n0 extends k0 implements m0 {
    private static Method E;
    private m0 D;

    /* JADX INFO: compiled from: MenuPopupWindow.java */
    public static class a extends f0 {

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        final int f1312n;

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        final int f1313o;

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        private m0 f1314p;

        /* JADX INFO: renamed from: q, reason: collision with root package name */
        private android.support.v7.view.menu.j f1315q;

        public a(Context context, boolean z2) {
            super(context, z2);
            if (1 == context.getResources().getConfiguration().getLayoutDirection()) {
                this.f1312n = 21;
                this.f1313o = 22;
            } else {
                this.f1312n = 22;
                this.f1313o = 21;
            }
        }

        @Override // android.view.View
        public final boolean onHoverEvent(MotionEvent motionEvent) {
            android.support.v7.view.menu.g gVar;
            int headersCount;
            int iPointToPosition;
            int i2;
            if (this.f1314p != null) {
                ListAdapter adapter = getAdapter();
                if (adapter instanceof HeaderViewListAdapter) {
                    HeaderViewListAdapter headerViewListAdapter = (HeaderViewListAdapter) adapter;
                    headersCount = headerViewListAdapter.getHeadersCount();
                    gVar = (android.support.v7.view.menu.g) headerViewListAdapter.getWrappedAdapter();
                } else {
                    gVar = (android.support.v7.view.menu.g) adapter;
                    headersCount = 0;
                }
                android.support.v7.view.menu.j item = (motionEvent.getAction() == 10 || (iPointToPosition = pointToPosition((int) motionEvent.getX(), (int) motionEvent.getY())) == -1 || (i2 = iPointToPosition - headersCount) < 0 || i2 >= gVar.getCount()) ? null : gVar.getItem(i2);
                android.support.v7.view.menu.j jVar = this.f1315q;
                if (jVar != item) {
                    android.support.v7.view.menu.h hVarC = gVar.c();
                    if (jVar != null) {
                        this.f1314p.c(hVarC, jVar);
                    }
                    this.f1315q = item;
                    if (item != null) {
                        this.f1314p.b(hVarC, item);
                    }
                }
            }
            return super.onHoverEvent(motionEvent);
        }

        @Override // android.widget.ListView, android.widget.AbsListView, android.view.View, android.view.KeyEvent.Callback
        public final boolean onKeyDown(int i2, KeyEvent keyEvent) {
            ListMenuItemView listMenuItemView = (ListMenuItemView) getSelectedView();
            if (listMenuItemView != null && i2 == this.f1312n) {
                if (listMenuItemView.isEnabled() && listMenuItemView.getItemData().hasSubMenu()) {
                    performItemClick(listMenuItemView, getSelectedItemPosition(), getSelectedItemId());
                }
                return true;
            }
            if (listMenuItemView == null || i2 != this.f1313o) {
                return super.onKeyDown(i2, keyEvent);
            }
            setSelection(-1);
            ((android.support.v7.view.menu.g) getAdapter()).c().e(false);
            return true;
        }

        public void setHoverListener(m0 m0Var) {
            this.f1314p = m0Var;
        }
    }

    static {
        try {
            E = PopupWindow.class.getDeclaredMethod("setTouchModal", Boolean.TYPE);
        } catch (NoSuchMethodException unused) {
            Log.i("MenuPopupWindow", "Could not find method setTouchModal() on PopupWindow. Oh well.");
        }
    }

    public final void A() {
        Method method = E;
        if (method != null) {
            try {
                method.invoke(this.f1291z, Boolean.FALSE);
            } catch (Exception unused) {
                Log.i("MenuPopupWindow", "Could not invoke setTouchModal() on PopupWindow. Oh well.");
            }
        }
    }

    @Override // android.support.v7.widget.m0
    public final void b(android.support.v7.view.menu.h hVar, android.support.v7.view.menu.j jVar) {
        m0 m0Var = this.D;
        if (m0Var != null) {
            m0Var.b(hVar, jVar);
        }
    }

    @Override // android.support.v7.widget.m0
    public final void c(android.support.v7.view.menu.h hVar, android.support.v7.view.menu.j jVar) {
        m0 m0Var = this.D;
        if (m0Var != null) {
            m0Var.c(hVar, jVar);
        }
    }

    @Override // android.support.v7.widget.k0
    final f0 d(Context context, boolean z2) {
        a aVar = new a(context, z2);
        aVar.setHoverListener(this);
        return aVar;
    }

    public final void x() {
        this.f1291z.setEnterTransition(null);
    }

    public final void y() {
        this.f1291z.setExitTransition(null);
    }

    public final void z(m0 m0Var) {
        this.D = m0Var;
    }
}
