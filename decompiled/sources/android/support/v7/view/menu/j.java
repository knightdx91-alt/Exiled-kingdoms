package android.support.v7.view.menu;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.view.c;
import android.support.v7.view.menu.p;
import android.util.Log;
import android.view.ActionProvider;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/* JADX INFO: compiled from: MenuItemImpl.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class j implements h.b {
    private android.support.v4.view.c A;
    private MenuItem.OnActionExpandListener B;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f952a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final int f953b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final int f954c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final int f955d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private CharSequence f956e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private CharSequence f957f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private Intent f958g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private char f959h;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private char f961j;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private Drawable f963l;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    h f965n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private t f966o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private MenuItem.OnMenuItemClickListener f967p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private CharSequence f968q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private CharSequence f969r;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    private int f975y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    private View f976z;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private int f960i = 4096;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private int f962k = 4096;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private int f964m = 0;
    private ColorStateList s = null;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    private PorterDuff.Mode f970t = null;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    private boolean f971u = false;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    private boolean f972v = false;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    private boolean f973w = false;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    private int f974x = 16;
    private boolean C = false;

    /* JADX INFO: compiled from: MenuItemImpl.java */
    final class a implements c.a {
        a() {
        }
    }

    j(h hVar, int i2, int i3, int i4, int i5, CharSequence charSequence, int i6) {
        this.f965n = hVar;
        this.f952a = i3;
        this.f953b = i2;
        this.f954c = i4;
        this.f955d = i5;
        this.f956e = charSequence;
        this.f975y = i6;
    }

    private Drawable c(Drawable drawable) {
        if (drawable != null && this.f973w && (this.f971u || this.f972v)) {
            drawable = drawable.mutate();
            if (this.f971u) {
                drawable.setTintList(this.s);
            }
            if (this.f972v) {
                drawable.setTintMode(this.f970t);
            }
            this.f973w = false;
        }
        return drawable;
    }

    @Override // h.b
    public final android.support.v4.view.c a() {
        return this.A;
    }

    @Override // h.b
    public final h.b b(android.support.v4.view.c cVar) {
        android.support.v4.view.c cVar2 = this.A;
        if (cVar2 != null) {
            cVar2.h();
        }
        this.f976z = null;
        this.A = cVar;
        this.f965n.w(true);
        android.support.v4.view.c cVar3 = this.A;
        if (cVar3 != null) {
            cVar3.i(new a());
        }
        return this;
    }

    @Override // android.view.MenuItem
    public final boolean collapseActionView() {
        if ((this.f975y & 8) == 0) {
            return false;
        }
        if (this.f976z == null) {
            return true;
        }
        MenuItem.OnActionExpandListener onActionExpandListener = this.B;
        if (onActionExpandListener == null || onActionExpandListener.onMenuItemActionCollapse(this)) {
            return this.f965n.f(this);
        }
        return false;
    }

    public final int d() {
        return this.f955d;
    }

    final char e() {
        return this.f965n.s() ? this.f961j : this.f959h;
    }

    @Override // android.view.MenuItem
    public final boolean expandActionView() {
        if (!g()) {
            return false;
        }
        MenuItem.OnActionExpandListener onActionExpandListener = this.B;
        if (onActionExpandListener == null || onActionExpandListener.onMenuItemActionExpand(this)) {
            return this.f965n.h(this);
        }
        return false;
    }

    final CharSequence f(p.a aVar) {
        return aVar.b() ? getTitleCondensed() : this.f956e;
    }

    public final boolean g() {
        android.support.v4.view.c cVar;
        if ((this.f975y & 8) == 0) {
            return false;
        }
        if (this.f976z == null && (cVar = this.A) != null) {
            this.f976z = cVar.d(this);
        }
        return this.f976z != null;
    }

    @Override // android.view.MenuItem
    public final ActionProvider getActionProvider() {
        throw new UnsupportedOperationException("This is not supported, use MenuItemCompat.getActionProvider()");
    }

    @Override // android.view.MenuItem
    public final View getActionView() {
        View view = this.f976z;
        if (view != null) {
            return view;
        }
        android.support.v4.view.c cVar = this.A;
        if (cVar == null) {
            return null;
        }
        View viewD = cVar.d(this);
        this.f976z = viewD;
        return viewD;
    }

    @Override // h.b, android.view.MenuItem
    public final int getAlphabeticModifiers() {
        return this.f962k;
    }

    @Override // android.view.MenuItem
    public final char getAlphabeticShortcut() {
        return this.f961j;
    }

    @Override // h.b, android.view.MenuItem
    public final CharSequence getContentDescription() {
        return this.f968q;
    }

    @Override // android.view.MenuItem
    public final int getGroupId() {
        return this.f953b;
    }

    @Override // android.view.MenuItem
    public final Drawable getIcon() {
        Drawable drawable = this.f963l;
        if (drawable != null) {
            return c(drawable);
        }
        if (this.f964m == 0) {
            return null;
        }
        Drawable drawableA = l.a.a(this.f965n.n(), this.f964m);
        this.f964m = 0;
        this.f963l = drawableA;
        return c(drawableA);
    }

    @Override // h.b, android.view.MenuItem
    public final ColorStateList getIconTintList() {
        return this.s;
    }

    @Override // h.b, android.view.MenuItem
    public final PorterDuff.Mode getIconTintMode() {
        return this.f970t;
    }

    @Override // android.view.MenuItem
    public final Intent getIntent() {
        return this.f958g;
    }

    @Override // android.view.MenuItem
    @ViewDebug.CapturedViewProperty
    public final int getItemId() {
        return this.f952a;
    }

    @Override // android.view.MenuItem
    public final ContextMenu.ContextMenuInfo getMenuInfo() {
        return null;
    }

    @Override // h.b, android.view.MenuItem
    public final int getNumericModifiers() {
        return this.f960i;
    }

    @Override // android.view.MenuItem
    public final char getNumericShortcut() {
        return this.f959h;
    }

    @Override // android.view.MenuItem
    public final int getOrder() {
        return this.f954c;
    }

    @Override // android.view.MenuItem
    public final SubMenu getSubMenu() {
        return this.f966o;
    }

    @Override // android.view.MenuItem
    @ViewDebug.CapturedViewProperty
    public final CharSequence getTitle() {
        return this.f956e;
    }

    @Override // android.view.MenuItem
    public final CharSequence getTitleCondensed() {
        CharSequence charSequence = this.f957f;
        return charSequence != null ? charSequence : this.f956e;
    }

    @Override // h.b, android.view.MenuItem
    public final CharSequence getTooltipText() {
        return this.f969r;
    }

    public final boolean h() {
        MenuItem.OnMenuItemClickListener onMenuItemClickListener = this.f967p;
        if (onMenuItemClickListener != null && onMenuItemClickListener.onMenuItemClick(this)) {
            return true;
        }
        h hVar = this.f965n;
        if (hVar.g(hVar, this)) {
            return true;
        }
        if (this.f958g != null) {
            try {
                hVar.n().startActivity(this.f958g);
                return true;
            } catch (ActivityNotFoundException e2) {
                Log.e("MenuItemImpl", "Can't find activity to handle intent; ignoring", e2);
            }
        }
        android.support.v4.view.c cVar = this.A;
        return cVar != null && cVar.e();
    }

    @Override // android.view.MenuItem
    public final boolean hasSubMenu() {
        return this.f966o != null;
    }

    public final boolean i() {
        return (this.f974x & 32) == 32;
    }

    @Override // android.view.MenuItem
    public final boolean isActionViewExpanded() {
        return this.C;
    }

    @Override // android.view.MenuItem
    public final boolean isCheckable() {
        return (this.f974x & 1) == 1;
    }

    @Override // android.view.MenuItem
    public final boolean isChecked() {
        return (this.f974x & 2) == 2;
    }

    @Override // android.view.MenuItem
    public final boolean isEnabled() {
        return (this.f974x & 16) != 0;
    }

    @Override // android.view.MenuItem
    public final boolean isVisible() {
        android.support.v4.view.c cVar = this.A;
        return (cVar == null || !cVar.g()) ? (this.f974x & 8) == 0 : (this.f974x & 8) == 0 && this.A.b();
    }

    public final boolean j() {
        return (this.f974x & 4) != 0;
    }

    public final boolean k() {
        return (this.f975y & 1) == 1;
    }

    public final boolean l() {
        return (this.f975y & 2) == 2;
    }

    public final void m(boolean z2) {
        this.C = z2;
        this.f965n.w(false);
    }

    final void n(boolean z2) {
        int i2 = this.f974x;
        int i3 = (z2 ? 2 : 0) | (i2 & (-3));
        this.f974x = i3;
        if (i2 != i3) {
            this.f965n.w(false);
        }
    }

    public final void o(boolean z2) {
        this.f974x = (z2 ? 4 : 0) | (this.f974x & (-5));
    }

    public final void p(boolean z2) {
        if (z2) {
            this.f974x |= 32;
        } else {
            this.f974x &= -33;
        }
    }

    public final void q(t tVar) {
        this.f966o = tVar;
        tVar.I(this.f956e);
    }

    final boolean r(boolean z2) {
        int i2 = this.f974x;
        int i3 = (z2 ? 0 : 8) | (i2 & (-9));
        this.f974x = i3;
        return i2 != i3;
    }

    public final boolean s() {
        return (this.f975y & 4) == 4;
    }

    @Override // android.view.MenuItem
    public final MenuItem setActionProvider(ActionProvider actionProvider) {
        throw new UnsupportedOperationException("This is not supported, use MenuItemCompat.setActionProvider()");
    }

    @Override // android.view.MenuItem
    public final MenuItem setActionView(View view) {
        int i2;
        this.f976z = view;
        this.A = null;
        if (view != null && view.getId() == -1 && (i2 = this.f952a) > 0) {
            view.setId(i2);
        }
        this.f965n.u();
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setAlphabeticShortcut(char c2) {
        if (this.f961j == c2) {
            return this;
        }
        this.f961j = Character.toLowerCase(c2);
        this.f965n.w(false);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setCheckable(boolean z2) {
        int i2 = this.f974x;
        int i3 = (z2 ? 1 : 0) | (i2 & (-2));
        this.f974x = i3;
        if (i2 != i3) {
            this.f965n.w(false);
        }
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setChecked(boolean z2) {
        if ((this.f974x & 4) != 0) {
            this.f965n.D(this);
        } else {
            n(z2);
        }
        return this;
    }

    @Override // android.view.MenuItem
    public final /* bridge */ /* synthetic */ MenuItem setContentDescription(CharSequence charSequence) {
        setContentDescription(charSequence);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setEnabled(boolean z2) {
        if (z2) {
            this.f974x |= 16;
        } else {
            this.f974x &= -17;
        }
        this.f965n.w(false);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setIcon(Drawable drawable) {
        this.f964m = 0;
        this.f963l = drawable;
        this.f973w = true;
        this.f965n.w(false);
        return this;
    }

    @Override // h.b, android.view.MenuItem
    public final MenuItem setIconTintList(ColorStateList colorStateList) {
        this.s = colorStateList;
        this.f971u = true;
        this.f973w = true;
        this.f965n.w(false);
        return this;
    }

    @Override // h.b, android.view.MenuItem
    public final MenuItem setIconTintMode(PorterDuff.Mode mode) {
        this.f970t = mode;
        this.f972v = true;
        this.f973w = true;
        this.f965n.w(false);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setIntent(Intent intent) {
        this.f958g = intent;
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setNumericShortcut(char c2) {
        if (this.f959h == c2) {
            return this;
        }
        this.f959h = c2;
        this.f965n.w(false);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setOnActionExpandListener(MenuItem.OnActionExpandListener onActionExpandListener) {
        this.B = onActionExpandListener;
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
        this.f967p = onMenuItemClickListener;
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setShortcut(char c2, char c3) {
        this.f959h = c2;
        this.f961j = Character.toLowerCase(c3);
        this.f965n.w(false);
        return this;
    }

    @Override // android.view.MenuItem
    public final void setShowAsAction(int i2) {
        int i3 = i2 & 3;
        if (i3 != 0 && i3 != 1 && i3 != 2) {
            throw new IllegalArgumentException("SHOW_AS_ACTION_ALWAYS, SHOW_AS_ACTION_IF_ROOM, and SHOW_AS_ACTION_NEVER are mutually exclusive.");
        }
        this.f975y = i2;
        this.f965n.u();
    }

    @Override // android.view.MenuItem
    public final MenuItem setShowAsActionFlags(int i2) {
        setShowAsAction(i2);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setTitle(CharSequence charSequence) {
        this.f956e = charSequence;
        this.f965n.w(false);
        t tVar = this.f966o;
        if (tVar != null) {
            tVar.I(charSequence);
        }
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setTitleCondensed(CharSequence charSequence) {
        this.f957f = charSequence;
        this.f965n.w(false);
        return this;
    }

    @Override // android.view.MenuItem
    public final /* bridge */ /* synthetic */ MenuItem setTooltipText(CharSequence charSequence) {
        setTooltipText(charSequence);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setVisible(boolean z2) {
        if (r(z2)) {
            this.f965n.v();
        }
        return this;
    }

    public final String toString() {
        CharSequence charSequence = this.f956e;
        if (charSequence != null) {
            return charSequence.toString();
        }
        return null;
    }

    @Override // h.b, android.view.MenuItem
    public final h.b setContentDescription(CharSequence charSequence) {
        this.f968q = charSequence;
        this.f965n.w(false);
        return this;
    }

    @Override // h.b, android.view.MenuItem
    public final h.b setTooltipText(CharSequence charSequence) {
        this.f969r = charSequence;
        this.f965n.w(false);
        return this;
    }

    @Override // h.b, android.view.MenuItem
    public final MenuItem setAlphabeticShortcut(char c2, int i2) {
        if (this.f961j == c2 && this.f962k == i2) {
            return this;
        }
        this.f961j = Character.toLowerCase(c2);
        this.f962k = KeyEvent.normalizeMetaState(i2);
        this.f965n.w(false);
        return this;
    }

    @Override // h.b, android.view.MenuItem
    public final MenuItem setNumericShortcut(char c2, int i2) {
        if (this.f959h == c2 && this.f960i == i2) {
            return this;
        }
        this.f959h = c2;
        this.f960i = KeyEvent.normalizeMetaState(i2);
        this.f965n.w(false);
        return this;
    }

    @Override // h.b, android.view.MenuItem
    public final MenuItem setShortcut(char c2, char c3, int i2, int i3) {
        this.f959h = c2;
        this.f960i = KeyEvent.normalizeMetaState(i2);
        this.f961j = Character.toLowerCase(c3);
        this.f962k = KeyEvent.normalizeMetaState(i3);
        this.f965n.w(false);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setIcon(int i2) {
        this.f963l = null;
        this.f964m = i2;
        this.f973w = true;
        this.f965n.w(false);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setTitle(int i2) {
        setTitle(this.f965n.n().getString(i2));
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setActionView(int i2) {
        int i3;
        Context contextN = this.f965n.n();
        View viewInflate = LayoutInflater.from(contextN).inflate(i2, (ViewGroup) new LinearLayout(contextN), false);
        this.f976z = viewInflate;
        this.A = null;
        if (viewInflate != null && viewInflate.getId() == -1 && (i3 = this.f952a) > 0) {
            viewInflate.setId(i3);
        }
        this.f965n.u();
        return this;
    }
}
