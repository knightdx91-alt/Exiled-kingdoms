package android.support.v7.view.menu;

import android.R;
import android.content.Context;
import android.support.v7.view.menu.h;
import android.support.v7.widget.x0;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class ExpandedMenuView extends ListView implements h.b, p, AdapterView.OnItemClickListener {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final int[] f850c = {R.attr.background, R.attr.divider};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private h f851b;

    public ExpandedMenuView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setOnItemClickListener(this);
        x0 x0VarT = x0.t(context, attributeSet, f850c, R.attr.listViewStyle);
        if (x0VarT.q(0)) {
            setBackgroundDrawable(x0VarT.f(0));
        }
        if (x0VarT.q(1)) {
            setDivider(x0VarT.f(1));
        }
        x0VarT.u();
    }

    @Override // android.support.v7.view.menu.h.b
    public final boolean b(j jVar) {
        return this.f851b.x(jVar, null, 0);
    }

    @Override // android.support.v7.view.menu.p
    public final void c(h hVar) {
        this.f851b = hVar;
    }

    public int getWindowAnimations() {
        return 0;
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.widget.AdapterView, android.view.ViewGroup, android.view.View
    protected final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        setChildrenDrawingCacheEnabled(false);
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public final void onItemClick(AdapterView adapterView, View view, int i2, long j2) {
        b((j) getAdapter().getItem(i2));
    }
}
