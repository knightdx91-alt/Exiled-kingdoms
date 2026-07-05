package android.support.v7.view.menu;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.ActionProvider;
import android.view.CollapsibleActionView;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.FrameLayout;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: MenuItemWrapperICS.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class k extends android.support.v7.view.menu.b<h.b> implements MenuItem {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Method f978e;

    /* JADX INFO: compiled from: MenuItemWrapperICS.java */
    class a extends android.support.v4.view.c {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final ActionProvider f979c;

        public a(Context context, ActionProvider actionProvider) {
            super(context);
            this.f979c = actionProvider;
        }

        @Override // android.support.v4.view.c
        public final boolean a() {
            return this.f979c.hasSubMenu();
        }

        @Override // android.support.v4.view.c
        public final View c() {
            return this.f979c.onCreateActionView();
        }

        @Override // android.support.v4.view.c
        public final boolean e() {
            return this.f979c.onPerformDefaultAction();
        }

        @Override // android.support.v4.view.c
        public final void f(t tVar) {
            this.f979c.onPrepareSubMenu(k.this.d(tVar));
        }
    }

    /* JADX INFO: compiled from: MenuItemWrapperICS.java */
    static class b extends FrameLayout implements n.c {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final CollapsibleActionView f981b;

        /* JADX WARN: Multi-variable type inference failed */
        b(View view) {
            super(view.getContext());
            this.f981b = (CollapsibleActionView) view;
            addView(view);
        }

        @Override // n.c
        public final void a() {
            this.f981b.onActionViewExpanded();
        }

        @Override // n.c
        public final void d() {
            this.f981b.onActionViewCollapsed();
        }
    }

    /* JADX INFO: compiled from: MenuItemWrapperICS.java */
    private class c extends android.support.v7.view.menu.c<MenuItem.OnActionExpandListener> implements MenuItem.OnActionExpandListener {
        c(MenuItem.OnActionExpandListener onActionExpandListener) {
            super(onActionExpandListener);
        }

        @Override // android.view.MenuItem.OnActionExpandListener
        public final boolean onMenuItemActionCollapse(MenuItem menuItem) {
            return ((MenuItem.OnActionExpandListener) this.f877a).onMenuItemActionCollapse(k.this.c(menuItem));
        }

        @Override // android.view.MenuItem.OnActionExpandListener
        public final boolean onMenuItemActionExpand(MenuItem menuItem) {
            return ((MenuItem.OnActionExpandListener) this.f877a).onMenuItemActionExpand(k.this.c(menuItem));
        }
    }

    /* JADX INFO: compiled from: MenuItemWrapperICS.java */
    private class d extends android.support.v7.view.menu.c<MenuItem.OnMenuItemClickListener> implements MenuItem.OnMenuItemClickListener {
        d(MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
            super(onMenuItemClickListener);
        }

        @Override // android.view.MenuItem.OnMenuItemClickListener
        public final boolean onMenuItemClick(MenuItem menuItem) {
            return ((MenuItem.OnMenuItemClickListener) this.f877a).onMenuItemClick(k.this.c(menuItem));
        }
    }

    @Override // android.view.MenuItem
    public final boolean collapseActionView() {
        return ((h.b) this.f877a).collapseActionView();
    }

    @Override // android.view.MenuItem
    public final boolean expandActionView() {
        return ((h.b) this.f877a).expandActionView();
    }

    @Override // android.view.MenuItem
    public final ActionProvider getActionProvider() {
        android.support.v4.view.c cVarA = ((h.b) this.f877a).a();
        if (cVarA instanceof a) {
            return ((a) cVarA).f979c;
        }
        return null;
    }

    @Override // android.view.MenuItem
    public final View getActionView() {
        View actionView = ((h.b) this.f877a).getActionView();
        return actionView instanceof b ? (View) ((b) actionView).f981b : actionView;
    }

    @Override // android.view.MenuItem
    public final int getAlphabeticModifiers() {
        return ((h.b) this.f877a).getAlphabeticModifiers();
    }

    @Override // android.view.MenuItem
    public final char getAlphabeticShortcut() {
        return ((h.b) this.f877a).getAlphabeticShortcut();
    }

    @Override // android.view.MenuItem
    public final CharSequence getContentDescription() {
        return ((h.b) this.f877a).getContentDescription();
    }

    @Override // android.view.MenuItem
    public final int getGroupId() {
        return ((h.b) this.f877a).getGroupId();
    }

    @Override // android.view.MenuItem
    public final Drawable getIcon() {
        return ((h.b) this.f877a).getIcon();
    }

    @Override // android.view.MenuItem
    public final ColorStateList getIconTintList() {
        return ((h.b) this.f877a).getIconTintList();
    }

    @Override // android.view.MenuItem
    public final PorterDuff.Mode getIconTintMode() {
        return ((h.b) this.f877a).getIconTintMode();
    }

    @Override // android.view.MenuItem
    public final Intent getIntent() {
        return ((h.b) this.f877a).getIntent();
    }

    @Override // android.view.MenuItem
    public final int getItemId() {
        return ((h.b) this.f877a).getItemId();
    }

    @Override // android.view.MenuItem
    public final ContextMenu.ContextMenuInfo getMenuInfo() {
        return ((h.b) this.f877a).getMenuInfo();
    }

    @Override // android.view.MenuItem
    public final int getNumericModifiers() {
        return ((h.b) this.f877a).getNumericModifiers();
    }

    @Override // android.view.MenuItem
    public final char getNumericShortcut() {
        return ((h.b) this.f877a).getNumericShortcut();
    }

    @Override // android.view.MenuItem
    public final int getOrder() {
        return ((h.b) this.f877a).getOrder();
    }

    @Override // android.view.MenuItem
    public final SubMenu getSubMenu() {
        return d(((h.b) this.f877a).getSubMenu());
    }

    @Override // android.view.MenuItem
    public final CharSequence getTitle() {
        return ((h.b) this.f877a).getTitle();
    }

    @Override // android.view.MenuItem
    public final CharSequence getTitleCondensed() {
        return ((h.b) this.f877a).getTitleCondensed();
    }

    @Override // android.view.MenuItem
    public final CharSequence getTooltipText() {
        return ((h.b) this.f877a).getTooltipText();
    }

    a h(ActionProvider actionProvider) {
        return new a(this.f874b, actionProvider);
    }

    @Override // android.view.MenuItem
    public final boolean hasSubMenu() {
        return ((h.b) this.f877a).hasSubMenu();
    }

    public final void i() {
        try {
            Method method = this.f978e;
            Object obj = this.f877a;
            if (method == null) {
                this.f978e = ((h.b) obj).getClass().getDeclaredMethod("setExclusiveCheckable", Boolean.TYPE);
            }
            this.f978e.invoke(obj, Boolean.TRUE);
        } catch (Exception e2) {
            Log.w("MenuItemWrapper", "Error while calling setExclusiveCheckable", e2);
        }
    }

    @Override // android.view.MenuItem
    public final boolean isActionViewExpanded() {
        return ((h.b) this.f877a).isActionViewExpanded();
    }

    @Override // android.view.MenuItem
    public final boolean isCheckable() {
        return ((h.b) this.f877a).isCheckable();
    }

    @Override // android.view.MenuItem
    public final boolean isChecked() {
        return ((h.b) this.f877a).isChecked();
    }

    @Override // android.view.MenuItem
    public final boolean isEnabled() {
        return ((h.b) this.f877a).isEnabled();
    }

    @Override // android.view.MenuItem
    public final boolean isVisible() {
        return ((h.b) this.f877a).isVisible();
    }

    @Override // android.view.MenuItem
    public final MenuItem setActionProvider(ActionProvider actionProvider) {
        ((h.b) this.f877a).b(actionProvider != null ? h(actionProvider) : null);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setActionView(View view) {
        if (view instanceof CollapsibleActionView) {
            view = new b(view);
        }
        ((h.b) this.f877a).setActionView(view);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setAlphabeticShortcut(char c2) {
        ((h.b) this.f877a).setAlphabeticShortcut(c2);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setCheckable(boolean z2) {
        ((h.b) this.f877a).setCheckable(z2);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setChecked(boolean z2) {
        ((h.b) this.f877a).setChecked(z2);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setContentDescription(CharSequence charSequence) {
        ((h.b) this.f877a).setContentDescription(charSequence);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setEnabled(boolean z2) {
        ((h.b) this.f877a).setEnabled(z2);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setIcon(Drawable drawable) {
        ((h.b) this.f877a).setIcon(drawable);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setIconTintList(ColorStateList colorStateList) {
        ((h.b) this.f877a).setIconTintList(colorStateList);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setIconTintMode(PorterDuff.Mode mode) {
        ((h.b) this.f877a).setIconTintMode(mode);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setIntent(Intent intent) {
        ((h.b) this.f877a).setIntent(intent);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setNumericShortcut(char c2) {
        ((h.b) this.f877a).setNumericShortcut(c2);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setOnActionExpandListener(MenuItem.OnActionExpandListener onActionExpandListener) {
        ((h.b) this.f877a).setOnActionExpandListener(onActionExpandListener != null ? new c(onActionExpandListener) : null);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
        ((h.b) this.f877a).setOnMenuItemClickListener(onMenuItemClickListener != null ? new d(onMenuItemClickListener) : null);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setShortcut(char c2, char c3) {
        ((h.b) this.f877a).setShortcut(c2, c3);
        return this;
    }

    @Override // android.view.MenuItem
    public final void setShowAsAction(int i2) {
        ((h.b) this.f877a).setShowAsAction(i2);
    }

    @Override // android.view.MenuItem
    public final MenuItem setShowAsActionFlags(int i2) {
        ((h.b) this.f877a).setShowAsActionFlags(i2);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setTitle(CharSequence charSequence) {
        ((h.b) this.f877a).setTitle(charSequence);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setTitleCondensed(CharSequence charSequence) {
        ((h.b) this.f877a).setTitleCondensed(charSequence);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setTooltipText(CharSequence charSequence) {
        ((h.b) this.f877a).setTooltipText(charSequence);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setVisible(boolean z2) {
        return ((h.b) this.f877a).setVisible(z2);
    }

    @Override // android.view.MenuItem
    public final MenuItem setAlphabeticShortcut(char c2, int i2) {
        ((h.b) this.f877a).setAlphabeticShortcut(c2, i2);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setIcon(int i2) {
        ((h.b) this.f877a).setIcon(i2);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setNumericShortcut(char c2, int i2) {
        ((h.b) this.f877a).setNumericShortcut(c2, i2);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setShortcut(char c2, char c3, int i2, int i3) {
        ((h.b) this.f877a).setShortcut(c2, c3, i2, i3);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setTitle(int i2) {
        ((h.b) this.f877a).setTitle(i2);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setActionView(int i2) {
        T t2 = this.f877a;
        ((h.b) t2).setActionView(i2);
        View actionView = ((h.b) t2).getActionView();
        if (actionView instanceof CollapsibleActionView) {
            ((h.b) t2).setActionView(new b(actionView));
        }
        return this;
    }
}
