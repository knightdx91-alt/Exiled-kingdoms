package android.support.v7.view.menu;

import android.content.Context;
import android.support.v7.view.menu.o;
import android.support.v7.view.menu.p;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import java.util.ArrayList;

/* JADX INFO: compiled from: ListMenuPresenter.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class f implements o, AdapterView.OnItemClickListener {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    Context f911b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    LayoutInflater f912c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    h f913d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    ExpandedMenuView f914e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    int f915f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private o.a f916g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    a f917h;

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: ListMenuPresenter.java */
    class a extends BaseAdapter {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f918b = -1;

        public a() {
            a();
        }

        final void a() {
            f fVar = f.this;
            j jVarO = fVar.f913d.o();
            if (jVarO != null) {
                ArrayList<j> arrayListP = fVar.f913d.p();
                int size = arrayListP.size();
                for (int i2 = 0; i2 < size; i2++) {
                    if (arrayListP.get(i2) == jVarO) {
                        this.f918b = i2;
                        return;
                    }
                }
            }
            this.f918b = -1;
        }

        @Override // android.widget.Adapter
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public final j getItem(int i2) {
            ArrayList<j> arrayListP = f.this.f913d.p();
            int i3 = this.f918b;
            if (i3 >= 0 && i2 >= i3) {
                i2++;
            }
            return arrayListP.get(i2);
        }

        @Override // android.widget.Adapter
        public final int getCount() {
            int size = f.this.f913d.p().size();
            return this.f918b < 0 ? size : size - 1;
        }

        @Override // android.widget.Adapter
        public final long getItemId(int i2) {
            return i2;
        }

        @Override // android.widget.Adapter
        public final View getView(int i2, View view, ViewGroup viewGroup) {
            if (view == null) {
                f fVar = f.this;
                view = fVar.f912c.inflate(fVar.f915f, viewGroup, false);
            }
            ((p.a) view).a(getItem(i2));
            return view;
        }

        @Override // android.widget.BaseAdapter
        public final void notifyDataSetChanged() {
            a();
            super.notifyDataSetChanged();
        }
    }

    public f(Context context, int i2) {
        this.f915f = i2;
        this.f911b = context;
        this.f912c = LayoutInflater.from(context);
    }

    public final ListAdapter a() {
        if (this.f917h == null) {
            this.f917h = new a();
        }
        return this.f917h;
    }

    @Override // android.support.v7.view.menu.o
    public final void b(h hVar, boolean z2) {
        o.a aVar = this.f916g;
        if (aVar != null) {
            aVar.b(hVar, z2);
        }
    }

    @Override // android.support.v7.view.menu.o
    public final boolean c(t tVar) {
        if (!tVar.hasVisibleItems()) {
            return false;
        }
        new i(tVar).a();
        o.a aVar = this.f916g;
        if (aVar == null) {
            return true;
        }
        aVar.c(tVar);
        return true;
    }

    @Override // android.support.v7.view.menu.o
    public final void d(o.a aVar) {
        this.f916g = aVar;
    }

    @Override // android.support.v7.view.menu.o
    public final void e(Context context, h hVar) {
        if (this.f911b != null) {
            this.f911b = context;
            if (this.f912c == null) {
                this.f912c = LayoutInflater.from(context);
            }
        }
        this.f913d = hVar;
        a aVar = this.f917h;
        if (aVar != null) {
            aVar.notifyDataSetChanged();
        }
    }

    public final p f(ViewGroup viewGroup) {
        if (this.f914e == null) {
            this.f914e = (ExpandedMenuView) this.f912c.inflate(k.g.abc_expanded_menu_layout, viewGroup, false);
            if (this.f917h == null) {
                this.f917h = new a();
            }
            this.f914e.setAdapter((ListAdapter) this.f917h);
            this.f914e.setOnItemClickListener(this);
        }
        return this.f914e;
    }

    @Override // android.support.v7.view.menu.o
    public final boolean g(j jVar) {
        return false;
    }

    @Override // android.support.v7.view.menu.o
    public final void h(boolean z2) {
        a aVar = this.f917h;
        if (aVar != null) {
            aVar.notifyDataSetChanged();
        }
    }

    @Override // android.support.v7.view.menu.o
    public final boolean i(j jVar) {
        return false;
    }

    @Override // android.support.v7.view.menu.o
    public final boolean j() {
        return false;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public final void onItemClick(AdapterView<?> adapterView, View view, int i2, long j2) {
        this.f913d.x(this.f917h.getItem(i2), this, 0);
    }
}
