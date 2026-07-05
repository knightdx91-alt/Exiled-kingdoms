package android.support.v7.view.menu;

import android.graphics.drawable.Drawable;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

/* JADX INFO: compiled from: SubMenuWrapperICS.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class u extends r implements SubMenu {
    @Override // android.view.SubMenu
    public final void clearHeader() {
        ((h.c) this.f877a).clearHeader();
    }

    @Override // android.view.SubMenu
    public final MenuItem getItem() {
        return c(((h.c) this.f877a).getItem());
    }

    @Override // android.view.SubMenu
    public final SubMenu setHeaderIcon(int i2) {
        ((h.c) this.f877a).setHeaderIcon(i2);
        return this;
    }

    @Override // android.view.SubMenu
    public final SubMenu setHeaderTitle(int i2) {
        ((h.c) this.f877a).setHeaderTitle(i2);
        return this;
    }

    @Override // android.view.SubMenu
    public final SubMenu setHeaderView(View view) {
        ((h.c) this.f877a).setHeaderView(view);
        return this;
    }

    @Override // android.view.SubMenu
    public final SubMenu setIcon(int i2) {
        ((h.c) this.f877a).setIcon(i2);
        return this;
    }

    @Override // android.view.SubMenu
    public final SubMenu setHeaderIcon(Drawable drawable) {
        ((h.c) this.f877a).setHeaderIcon(drawable);
        return this;
    }

    @Override // android.view.SubMenu
    public final SubMenu setHeaderTitle(CharSequence charSequence) {
        ((h.c) this.f877a).setHeaderTitle(charSequence);
        return this;
    }

    @Override // android.view.SubMenu
    public final SubMenu setIcon(Drawable drawable) {
        ((h.c) this.f877a).setIcon(drawable);
        return this;
    }
}
