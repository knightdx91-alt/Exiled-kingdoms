package android.support.v7.view.menu;

import android.content.Context;
import android.graphics.Rect;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.PopupWindow;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: compiled from: MenuPopup.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public abstract class m implements o.b, o, AdapterView.OnItemClickListener {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Rect f985b;

    protected static int n(ListAdapter listAdapter, Context context, int i2) {
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(0, 0);
        int count = listAdapter.getCount();
        int i3 = 0;
        int i4 = 0;
        FrameLayout frameLayout = null;
        View view = null;
        for (int i5 = 0; i5 < count; i5++) {
            int itemViewType = listAdapter.getItemViewType(i5);
            if (itemViewType != i4) {
                view = null;
                i4 = itemViewType;
            }
            if (frameLayout == null) {
                frameLayout = new FrameLayout(context);
            }
            view = listAdapter.getView(i5, view, frameLayout);
            view.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
            int measuredWidth = view.getMeasuredWidth();
            if (measuredWidth >= i2) {
                return i2;
            }
            if (measuredWidth > i3) {
                i3 = measuredWidth;
            }
        }
        return i3;
    }

    @Override // android.support.v7.view.menu.o
    public final void e(Context context, h hVar) {
    }

    @Override // android.support.v7.view.menu.o
    public final boolean g(j jVar) {
        return false;
    }

    @Override // android.support.v7.view.menu.o
    public final boolean i(j jVar) {
        return false;
    }

    public abstract void l(h hVar);

    public final Rect m() {
        return this.f985b;
    }

    public abstract void o(View view);

    @Override // android.widget.AdapterView.OnItemClickListener
    public final void onItemClick(AdapterView<?> adapterView, View view, int i2, long j2) {
        ListAdapter listAdapter = (ListAdapter) adapterView.getAdapter();
        (listAdapter instanceof HeaderViewListAdapter ? (g) ((HeaderViewListAdapter) listAdapter).getWrappedAdapter() : (g) listAdapter).f921b.x((MenuItem) listAdapter.getItem(i2), this, !(this instanceof d) ? 0 : 4);
    }

    public final void p(Rect rect) {
        this.f985b = rect;
    }

    public abstract void q(boolean z2);

    public abstract void r(int i2);

    public abstract void s(int i2);

    public abstract void t(PopupWindow.OnDismissListener onDismissListener);

    public abstract void u(boolean z2);

    public abstract void v(int i2);
}
