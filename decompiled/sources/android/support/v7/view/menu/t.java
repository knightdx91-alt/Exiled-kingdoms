package android.support.v7.view.menu;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.view.menu.h;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

/* JADX INFO: compiled from: SubMenuBuilder.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class t extends h implements SubMenu {

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    private h f1018y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    private j f1019z;

    public t(Context context, h hVar, j jVar) {
        super(context);
        this.f1018y = hVar;
        this.f1019z = jVar;
    }

    @Override // android.support.v7.view.menu.h
    public final void B(h.a aVar) {
        throw null;
    }

    public final h N() {
        return this.f1018y;
    }

    @Override // android.support.v7.view.menu.h
    public final boolean f(j jVar) {
        return this.f1018y.f(jVar);
    }

    @Override // android.support.v7.view.menu.h
    final boolean g(h hVar, j jVar) {
        return super.g(hVar, jVar) || this.f1018y.g(hVar, jVar);
    }

    @Override // android.view.SubMenu
    public final MenuItem getItem() {
        return this.f1019z;
    }

    @Override // android.support.v7.view.menu.h
    public final boolean h(j jVar) {
        return this.f1018y.h(jVar);
    }

    @Override // android.support.v7.view.menu.h
    public final String m() {
        j jVar = this.f1019z;
        int itemId = jVar != null ? jVar.getItemId() : 0;
        if (itemId == 0) {
            return null;
        }
        return a.a.h(itemId, "android:menu:actionviewstates:");
    }

    @Override // android.support.v7.view.menu.h
    public final h q() {
        return this.f1018y.q();
    }

    @Override // android.support.v7.view.menu.h
    public final boolean s() {
        return this.f1018y.s();
    }

    @Override // android.view.SubMenu
    public final SubMenu setHeaderIcon(Drawable drawable) {
        F(drawable);
        return this;
    }

    @Override // android.view.SubMenu
    public final SubMenu setHeaderTitle(CharSequence charSequence) {
        I(charSequence);
        return this;
    }

    @Override // android.view.SubMenu
    public final SubMenu setHeaderView(View view) {
        J(view);
        return this;
    }

    @Override // android.view.SubMenu
    public final SubMenu setIcon(Drawable drawable) {
        this.f1019z.setIcon(drawable);
        return this;
    }

    @Override // android.support.v7.view.menu.h, android.view.Menu
    public final void setQwertyMode(boolean z2) {
        this.f1018y.setQwertyMode(z2);
    }

    @Override // android.support.v7.view.menu.h
    public final boolean t() {
        return this.f1018y.t();
    }

    @Override // android.view.SubMenu
    public final SubMenu setHeaderIcon(int i2) {
        E(i2);
        return this;
    }

    @Override // android.view.SubMenu
    public final SubMenu setHeaderTitle(int i2) {
        H(i2);
        return this;
    }

    @Override // android.view.SubMenu
    public final SubMenu setIcon(int i2) {
        this.f1019z.setIcon(i2);
        return this;
    }
}
