package android.support.v7.view.menu;

import android.support.v7.view.menu.p;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.ArrayList;

/* JADX INFO: compiled from: MenuAdapter.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class g extends BaseAdapter {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    static final int f920g = k.g.abc_popup_menu_item_layout;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    h f921b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f922c = -1;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f923d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final boolean f924e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final LayoutInflater f925f;

    public g(h hVar, LayoutInflater layoutInflater, boolean z2) {
        this.f924e = z2;
        this.f925f = layoutInflater;
        this.f921b = hVar;
        a();
    }

    final void a() {
        h hVar = this.f921b;
        j jVarO = hVar.o();
        if (jVarO != null) {
            ArrayList<j> arrayListP = hVar.p();
            int size = arrayListP.size();
            for (int i2 = 0; i2 < size; i2++) {
                if (arrayListP.get(i2) == jVarO) {
                    this.f922c = i2;
                    return;
                }
            }
        }
        this.f922c = -1;
    }

    public final h c() {
        return this.f921b;
    }

    @Override // android.widget.Adapter
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public final j getItem(int i2) {
        h hVar = this.f921b;
        ArrayList<j> arrayListP = this.f924e ? hVar.p() : hVar.r();
        int i3 = this.f922c;
        if (i3 >= 0 && i2 >= i3) {
            i2++;
        }
        return arrayListP.get(i2);
    }

    public final void e(boolean z2) {
        this.f923d = z2;
    }

    @Override // android.widget.Adapter
    public final int getCount() {
        h hVar = this.f921b;
        return this.f922c < 0 ? (this.f924e ? hVar.p() : hVar.r()).size() : r0.size() - 1;
    }

    @Override // android.widget.Adapter
    public final long getItemId(int i2) {
        return i2;
    }

    @Override // android.widget.Adapter
    public final View getView(int i2, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = this.f925f.inflate(f920g, viewGroup, false);
        }
        p.a aVar = (p.a) view;
        if (this.f923d) {
            ((ListMenuItemView) view).setForceShowIcon(true);
        }
        aVar.a(getItem(i2));
        return view;
    }

    @Override // android.widget.BaseAdapter
    public final void notifyDataSetChanged() {
        a();
        super.notifyDataSetChanged();
    }
}
