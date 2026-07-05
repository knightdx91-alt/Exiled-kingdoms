package android.support.v7.view.menu;

import android.content.Context;
import android.support.v7.view.menu.o;
import android.support.v7.view.menu.p;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

/* JADX INFO: compiled from: BaseMenuPresenter.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public abstract class a implements o {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected Context f866b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected Context f867c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    protected h f868d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    protected LayoutInflater f869e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private o.a f870f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private int f871g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private int f872h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    protected p f873i;

    public a(Context context, int i2, int i3) {
        this.f866b = context;
        this.f869e = LayoutInflater.from(context);
        this.f871g = i2;
        this.f872h = i3;
    }

    public abstract void a(j jVar, p.a aVar);

    @Override // android.support.v7.view.menu.o
    public void b(h hVar, boolean z2) {
        o.a aVar = this.f870f;
        if (aVar != null) {
            aVar.b(hVar, z2);
        }
    }

    @Override // android.support.v7.view.menu.o
    public boolean c(t tVar) {
        o.a aVar = this.f870f;
        if (aVar != null) {
            return aVar.c(tVar);
        }
        return false;
    }

    @Override // android.support.v7.view.menu.o
    public final void d(o.a aVar) {
        this.f870f = aVar;
    }

    @Override // android.support.v7.view.menu.o
    public void e(Context context, h hVar) {
        this.f867c = context;
        LayoutInflater.from(context);
        this.f868d = hVar;
    }

    protected abstract boolean f(ViewGroup viewGroup, int i2);

    @Override // android.support.v7.view.menu.o
    public final boolean g(j jVar) {
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.support.v7.view.menu.o
    public void h(boolean z2) {
        ViewGroup viewGroup = (ViewGroup) this.f873i;
        if (viewGroup == null) {
            return;
        }
        h hVar = this.f868d;
        int i2 = 0;
        if (hVar != null) {
            hVar.k();
            ArrayList<j> arrayListR = this.f868d.r();
            int size = arrayListR.size();
            int i3 = 0;
            for (int i4 = 0; i4 < size; i4++) {
                j jVar = arrayListR.get(i4);
                if (n(jVar)) {
                    View childAt = viewGroup.getChildAt(i3);
                    j itemData = childAt instanceof p.a ? ((p.a) childAt).getItemData() : null;
                    View viewL = l(jVar, childAt, viewGroup);
                    if (jVar != itemData) {
                        viewL.setPressed(false);
                        viewL.jumpDrawablesToCurrentState();
                    }
                    if (viewL != childAt) {
                        ViewGroup viewGroup2 = (ViewGroup) viewL.getParent();
                        if (viewGroup2 != null) {
                            viewGroup2.removeView(viewL);
                        }
                        ((ViewGroup) this.f873i).addView(viewL, i3);
                    }
                    i3++;
                }
            }
            i2 = i3;
        }
        while (i2 < viewGroup.getChildCount()) {
            if (!f(viewGroup, i2)) {
                i2++;
            }
        }
    }

    @Override // android.support.v7.view.menu.o
    public final boolean i(j jVar) {
        return false;
    }

    public final o.a k() {
        return this.f870f;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public View l(j jVar, View view, ViewGroup viewGroup) {
        p.a aVar = view instanceof p.a ? (p.a) view : (p.a) this.f869e.inflate(this.f872h, viewGroup, false);
        a(jVar, aVar);
        return (View) aVar;
    }

    public p m(ViewGroup viewGroup) {
        if (this.f873i == null) {
            p pVar = (p) this.f869e.inflate(this.f871g, viewGroup, false);
            this.f873i = pVar;
            pVar.c(this.f868d);
            h(true);
        }
        return this.f873i;
    }

    public abstract boolean n(j jVar);
}
