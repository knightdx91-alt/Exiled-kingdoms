package o;

import android.R;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.view.c;
import android.view.ActionProvider;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

/* JADX INFO: compiled from: ActionMenuItem.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class a implements h.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private CharSequence f3423a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private CharSequence f3424b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Intent f3425c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private char f3426d;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private char f3428f;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private Drawable f3430h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private Context f3431i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private CharSequence f3432j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private CharSequence f3433k;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f3427e = 4096;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private int f3429g = 4096;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private ColorStateList f3434l = null;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private PorterDuff.Mode f3435m = null;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private boolean f3436n = false;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private boolean f3437o = false;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private int f3438p = 16;

    public a(Context context, CharSequence charSequence) {
        this.f3431i = context;
        this.f3423a = charSequence;
    }

    private void c() {
        Drawable drawable = this.f3430h;
        if (drawable != null) {
            if (this.f3436n || this.f3437o) {
                this.f3430h = drawable;
                Drawable drawableMutate = drawable.mutate();
                this.f3430h = drawableMutate;
                if (this.f3436n) {
                    drawableMutate.setTintList(this.f3434l);
                }
                if (this.f3437o) {
                    this.f3430h.setTintMode(this.f3435m);
                }
            }
        }
    }

    @Override // h.b
    public final c a() {
        return null;
    }

    @Override // h.b
    public final h.b b(c cVar) {
        throw new UnsupportedOperationException();
    }

    @Override // android.view.MenuItem
    public final boolean collapseActionView() {
        return false;
    }

    @Override // android.view.MenuItem
    public final boolean expandActionView() {
        return false;
    }

    @Override // android.view.MenuItem
    public final ActionProvider getActionProvider() {
        throw new UnsupportedOperationException();
    }

    @Override // android.view.MenuItem
    public final View getActionView() {
        return null;
    }

    @Override // h.b, android.view.MenuItem
    public final int getAlphabeticModifiers() {
        return this.f3429g;
    }

    @Override // android.view.MenuItem
    public final char getAlphabeticShortcut() {
        return this.f3428f;
    }

    @Override // h.b, android.view.MenuItem
    public final CharSequence getContentDescription() {
        return this.f3432j;
    }

    @Override // android.view.MenuItem
    public final int getGroupId() {
        return 0;
    }

    @Override // android.view.MenuItem
    public final Drawable getIcon() {
        return this.f3430h;
    }

    @Override // h.b, android.view.MenuItem
    public final ColorStateList getIconTintList() {
        return this.f3434l;
    }

    @Override // h.b, android.view.MenuItem
    public final PorterDuff.Mode getIconTintMode() {
        return this.f3435m;
    }

    @Override // android.view.MenuItem
    public final Intent getIntent() {
        return this.f3425c;
    }

    @Override // android.view.MenuItem
    public final int getItemId() {
        return R.id.home;
    }

    @Override // android.view.MenuItem
    public final ContextMenu.ContextMenuInfo getMenuInfo() {
        return null;
    }

    @Override // h.b, android.view.MenuItem
    public final int getNumericModifiers() {
        return this.f3427e;
    }

    @Override // android.view.MenuItem
    public final char getNumericShortcut() {
        return this.f3426d;
    }

    @Override // android.view.MenuItem
    public final int getOrder() {
        return 0;
    }

    @Override // android.view.MenuItem
    public final SubMenu getSubMenu() {
        return null;
    }

    @Override // android.view.MenuItem
    public final CharSequence getTitle() {
        return this.f3423a;
    }

    @Override // android.view.MenuItem
    public final CharSequence getTitleCondensed() {
        CharSequence charSequence = this.f3424b;
        return charSequence != null ? charSequence : this.f3423a;
    }

    @Override // h.b, android.view.MenuItem
    public final CharSequence getTooltipText() {
        return this.f3433k;
    }

    @Override // android.view.MenuItem
    public final boolean hasSubMenu() {
        return false;
    }

    @Override // android.view.MenuItem
    public final boolean isActionViewExpanded() {
        return false;
    }

    @Override // android.view.MenuItem
    public final boolean isCheckable() {
        return (this.f3438p & 1) != 0;
    }

    @Override // android.view.MenuItem
    public final boolean isChecked() {
        return (this.f3438p & 2) != 0;
    }

    @Override // android.view.MenuItem
    public final boolean isEnabled() {
        return (this.f3438p & 16) != 0;
    }

    @Override // android.view.MenuItem
    public final boolean isVisible() {
        return (this.f3438p & 8) == 0;
    }

    @Override // android.view.MenuItem
    public final MenuItem setActionProvider(ActionProvider actionProvider) {
        throw new UnsupportedOperationException();
    }

    @Override // android.view.MenuItem
    public final MenuItem setActionView(View view) {
        throw new UnsupportedOperationException();
    }

    @Override // android.view.MenuItem
    public final MenuItem setAlphabeticShortcut(char c2) {
        this.f3428f = Character.toLowerCase(c2);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setCheckable(boolean z2) {
        this.f3438p = (z2 ? 1 : 0) | (this.f3438p & (-2));
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setChecked(boolean z2) {
        this.f3438p = (z2 ? 2 : 0) | (this.f3438p & (-3));
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setContentDescription(CharSequence charSequence) {
        this.f3432j = charSequence;
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setEnabled(boolean z2) {
        this.f3438p = (z2 ? 16 : 0) | (this.f3438p & (-17));
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setIcon(Drawable drawable) {
        this.f3430h = drawable;
        c();
        return this;
    }

    @Override // h.b, android.view.MenuItem
    public final MenuItem setIconTintList(ColorStateList colorStateList) {
        this.f3434l = colorStateList;
        this.f3436n = true;
        c();
        return this;
    }

    @Override // h.b, android.view.MenuItem
    public final MenuItem setIconTintMode(PorterDuff.Mode mode) {
        this.f3435m = mode;
        this.f3437o = true;
        c();
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setIntent(Intent intent) {
        this.f3425c = intent;
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setNumericShortcut(char c2) {
        this.f3426d = c2;
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setOnActionExpandListener(MenuItem.OnActionExpandListener onActionExpandListener) {
        throw new UnsupportedOperationException();
    }

    @Override // android.view.MenuItem
    public final MenuItem setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setShortcut(char c2, char c3) {
        this.f3426d = c2;
        this.f3428f = Character.toLowerCase(c3);
        return this;
    }

    @Override // android.view.MenuItem
    public final void setShowAsAction(int i2) {
    }

    @Override // android.view.MenuItem
    public final MenuItem setShowAsActionFlags(int i2) {
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setTitle(CharSequence charSequence) {
        this.f3423a = charSequence;
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setTitleCondensed(CharSequence charSequence) {
        this.f3424b = charSequence;
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setTooltipText(CharSequence charSequence) {
        this.f3433k = charSequence;
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setVisible(boolean z2) {
        this.f3438p = (this.f3438p & 8) | (z2 ? 0 : 8);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setActionView(int i2) {
        throw new UnsupportedOperationException();
    }

    @Override // h.b, android.view.MenuItem
    public final MenuItem setAlphabeticShortcut(char c2, int i2) {
        this.f3428f = Character.toLowerCase(c2);
        this.f3429g = KeyEvent.normalizeMetaState(i2);
        return this;
    }

    @Override // h.b, android.view.MenuItem
    public final h.b setContentDescription(CharSequence charSequence) {
        this.f3432j = charSequence;
        return this;
    }

    @Override // h.b, android.view.MenuItem
    public final MenuItem setNumericShortcut(char c2, int i2) {
        this.f3426d = c2;
        this.f3427e = KeyEvent.normalizeMetaState(i2);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setTitle(int i2) {
        this.f3423a = this.f3431i.getResources().getString(i2);
        return this;
    }

    @Override // h.b, android.view.MenuItem
    public final h.b setTooltipText(CharSequence charSequence) {
        this.f3433k = charSequence;
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setIcon(int i2) {
        this.f3430h = this.f3431i.getDrawable(i2);
        c();
        return this;
    }

    @Override // h.b, android.view.MenuItem
    public final MenuItem setShortcut(char c2, char c3, int i2, int i3) {
        this.f3426d = c2;
        this.f3427e = KeyEvent.normalizeMetaState(i2);
        this.f3428f = Character.toLowerCase(c3);
        this.f3429g = KeyEvent.normalizeMetaState(i3);
        return this;
    }
}
