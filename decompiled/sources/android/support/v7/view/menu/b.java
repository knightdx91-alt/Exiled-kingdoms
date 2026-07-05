package android.support.v7.view.menu;

import android.content.Context;
import android.view.MenuItem;
import android.view.SubMenu;
import java.util.Iterator;

/* JADX INFO: compiled from: BaseMenuWrapper.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
abstract class b<T> extends c<T> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final Context f874b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private android.support.v4.util.b f875c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private android.support.v4.util.b f876d;

    b(Context context, T t2) {
        super(t2);
        this.f874b = context;
    }

    final MenuItem c(MenuItem menuItem) {
        if (!(menuItem instanceof h.b)) {
            return menuItem;
        }
        h.b bVar = (h.b) menuItem;
        if (this.f875c == null) {
            this.f875c = new android.support.v4.util.b();
        }
        MenuItem menuItem2 = (MenuItem) this.f875c.get(menuItem);
        if (menuItem2 != null) {
            return menuItem2;
        }
        l lVar = new l(this.f874b, bVar);
        this.f875c.put(bVar, lVar);
        return lVar;
    }

    final SubMenu d(SubMenu subMenu) {
        if (!(subMenu instanceof h.c)) {
            return subMenu;
        }
        h.c cVar = (h.c) subMenu;
        if (this.f876d == null) {
            this.f876d = new android.support.v4.util.b();
        }
        SubMenu subMenu2 = (SubMenu) this.f876d.get(cVar);
        if (subMenu2 != null) {
            return subMenu2;
        }
        u uVar = new u(this.f874b, cVar);
        this.f876d.put(cVar, uVar);
        return uVar;
    }

    final void e() {
        android.support.v4.util.b bVar = this.f875c;
        if (bVar != null) {
            bVar.clear();
        }
        android.support.v4.util.b bVar2 = this.f876d;
        if (bVar2 != null) {
            bVar2.clear();
        }
    }

    final void f(int i2) {
        android.support.v4.util.b bVar = this.f875c;
        if (bVar == null) {
            return;
        }
        Iterator it = bVar.keySet().iterator();
        while (it.hasNext()) {
            if (i2 == ((MenuItem) it.next()).getGroupId()) {
                it.remove();
            }
        }
    }

    final void g(int i2) {
        android.support.v4.util.b bVar = this.f875c;
        if (bVar == null) {
            return;
        }
        Iterator it = bVar.keySet().iterator();
        while (it.hasNext()) {
            if (i2 == ((MenuItem) it.next()).getItemId()) {
                it.remove();
                return;
            }
        }
    }
}
