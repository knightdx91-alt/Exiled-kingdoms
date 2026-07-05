package android.support.v7.view.menu;

import android.content.ComponentName;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;

/* JADX INFO: compiled from: MenuWrapperICS.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
class r extends b<h.a> implements Menu {
    @Override // android.view.Menu
    public final MenuItem add(CharSequence charSequence) {
        return c(((h.a) this.f877a).add(charSequence));
    }

    @Override // android.view.Menu
    public final int addIntentOptions(int i2, int i3, int i4, ComponentName componentName, Intent[] intentArr, Intent intent, int i5, MenuItem[] menuItemArr) {
        MenuItem[] menuItemArr2 = menuItemArr != null ? new MenuItem[menuItemArr.length] : null;
        int iAddIntentOptions = ((h.a) this.f877a).addIntentOptions(i2, i3, i4, componentName, intentArr, intent, i5, menuItemArr2);
        if (menuItemArr2 != null) {
            int length = menuItemArr2.length;
            for (int i6 = 0; i6 < length; i6++) {
                menuItemArr[i6] = c(menuItemArr2[i6]);
            }
        }
        return iAddIntentOptions;
    }

    @Override // android.view.Menu
    public final SubMenu addSubMenu(CharSequence charSequence) {
        return d(((h.a) this.f877a).addSubMenu(charSequence));
    }

    @Override // android.view.Menu
    public final void clear() {
        e();
        ((h.a) this.f877a).clear();
    }

    @Override // android.view.Menu
    public final void close() {
        ((h.a) this.f877a).close();
    }

    @Override // android.view.Menu
    public final MenuItem findItem(int i2) {
        return c(((h.a) this.f877a).findItem(i2));
    }

    @Override // android.view.Menu
    public final MenuItem getItem(int i2) {
        return c(((h.a) this.f877a).getItem(i2));
    }

    @Override // android.view.Menu
    public final boolean hasVisibleItems() {
        return ((h.a) this.f877a).hasVisibleItems();
    }

    @Override // android.view.Menu
    public final boolean isShortcutKey(int i2, KeyEvent keyEvent) {
        return ((h.a) this.f877a).isShortcutKey(i2, keyEvent);
    }

    @Override // android.view.Menu
    public final boolean performIdentifierAction(int i2, int i3) {
        return ((h.a) this.f877a).performIdentifierAction(i2, i3);
    }

    @Override // android.view.Menu
    public final boolean performShortcut(int i2, KeyEvent keyEvent, int i3) {
        return ((h.a) this.f877a).performShortcut(i2, keyEvent, i3);
    }

    @Override // android.view.Menu
    public final void removeGroup(int i2) {
        f(i2);
        ((h.a) this.f877a).removeGroup(i2);
    }

    @Override // android.view.Menu
    public final void removeItem(int i2) {
        g(i2);
        ((h.a) this.f877a).removeItem(i2);
    }

    @Override // android.view.Menu
    public final void setGroupCheckable(int i2, boolean z2, boolean z3) {
        ((h.a) this.f877a).setGroupCheckable(i2, z2, z3);
    }

    @Override // android.view.Menu
    public final void setGroupEnabled(int i2, boolean z2) {
        ((h.a) this.f877a).setGroupEnabled(i2, z2);
    }

    @Override // android.view.Menu
    public final void setGroupVisible(int i2, boolean z2) {
        ((h.a) this.f877a).setGroupVisible(i2, z2);
    }

    @Override // android.view.Menu
    public final void setQwertyMode(boolean z2) {
        ((h.a) this.f877a).setQwertyMode(z2);
    }

    @Override // android.view.Menu
    public final int size() {
        return ((h.a) this.f877a).size();
    }

    @Override // android.view.Menu
    public final MenuItem add(int i2) {
        return c(((h.a) this.f877a).add(i2));
    }

    @Override // android.view.Menu
    public final SubMenu addSubMenu(int i2) {
        return d(((h.a) this.f877a).addSubMenu(i2));
    }

    @Override // android.view.Menu
    public final MenuItem add(int i2, int i3, int i4, CharSequence charSequence) {
        return c(((h.a) this.f877a).add(i2, i3, i4, charSequence));
    }

    @Override // android.view.Menu
    public final SubMenu addSubMenu(int i2, int i3, int i4, CharSequence charSequence) {
        return d(((h.a) this.f877a).addSubMenu(i2, i3, i4, charSequence));
    }

    @Override // android.view.Menu
    public final MenuItem add(int i2, int i3, int i4, int i5) {
        return c(((h.a) this.f877a).add(i2, i3, i4, i5));
    }

    @Override // android.view.Menu
    public final SubMenu addSubMenu(int i2, int i3, int i4, int i5) {
        return d(((h.a) this.f877a).addSubMenu(i2, i3, i4, i5));
    }
}
