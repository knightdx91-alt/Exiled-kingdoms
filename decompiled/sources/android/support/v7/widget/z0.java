package android.support.v7.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.view.menu.o;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

/* JADX INFO: compiled from: ToolbarWidgetWrapper.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class z0 implements d0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    Toolbar f1393a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f1394b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private View f1395c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Drawable f1396d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Drawable f1397e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private Drawable f1398f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private boolean f1399g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    CharSequence f1400h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private CharSequence f1401i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private CharSequence f1402j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    Window.Callback f1403k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    boolean f1404l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private ActionMenuPresenter f1405m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private int f1406n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private Drawable f1407o;

    /* JADX INFO: compiled from: ToolbarWidgetWrapper.java */
    final class a extends com.badlogic.gdx.utils.l {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private boolean f1408c = false;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ int f1409d;

        a(int i2) {
            this.f1409d = i2;
        }

        @Override // android.support.v4.view.m
        public final void a() {
            if (this.f1408c) {
                return;
            }
            z0.this.f1393a.setVisibility(this.f1409d);
        }

        @Override // com.badlogic.gdx.utils.l, android.support.v4.view.m
        public final void b(View view) {
            this.f1408c = true;
        }

        @Override // com.badlogic.gdx.utils.l, android.support.v4.view.m
        public final void c() {
            z0.this.f1393a.setVisibility(0);
        }
    }

    public z0(Toolbar toolbar) {
        Drawable drawable;
        int i2 = k.h.abc_action_bar_up_description;
        this.f1406n = 0;
        this.f1393a = toolbar;
        this.f1400h = toolbar.getTitle();
        this.f1401i = toolbar.getSubtitle();
        this.f1399g = this.f1400h != null;
        this.f1398f = toolbar.getNavigationIcon();
        x0 x0VarT = x0.t(toolbar.getContext(), null, k.j.ActionBar, k.a.actionBarStyle);
        this.f1407o = x0VarT.f(k.j.ActionBar_homeAsUpIndicator);
        CharSequence charSequenceO = x0VarT.o(k.j.ActionBar_title);
        if (!TextUtils.isEmpty(charSequenceO)) {
            this.f1399g = true;
            this.f1400h = charSequenceO;
            if ((this.f1394b & 8) != 0) {
                this.f1393a.setTitle(charSequenceO);
            }
        }
        CharSequence charSequenceO2 = x0VarT.o(k.j.ActionBar_subtitle);
        if (!TextUtils.isEmpty(charSequenceO2)) {
            this.f1401i = charSequenceO2;
            if ((this.f1394b & 8) != 0) {
                toolbar.setSubtitle(charSequenceO2);
            }
        }
        Drawable drawableF = x0VarT.f(k.j.ActionBar_logo);
        if (drawableF != null) {
            this.f1397e = drawableF;
            u();
        }
        Drawable drawableF2 = x0VarT.f(k.j.ActionBar_icon);
        if (drawableF2 != null) {
            setIcon(drawableF2);
        }
        if (this.f1398f == null && (drawable = this.f1407o) != null) {
            this.f1398f = drawable;
            int i3 = this.f1394b & 4;
            Toolbar toolbar2 = this.f1393a;
            if (i3 != 0) {
                toolbar2.setNavigationIcon(drawable);
            } else {
                toolbar2.setNavigationIcon((Drawable) null);
            }
        }
        l(x0VarT.j(k.j.ActionBar_displayOptions, 0));
        int iM = x0VarT.m(k.j.ActionBar_customNavigationLayout, 0);
        if (iM != 0) {
            View viewInflate = LayoutInflater.from(toolbar.getContext()).inflate(iM, (ViewGroup) toolbar, false);
            View view = this.f1395c;
            if (view != null && (this.f1394b & 16) != 0) {
                toolbar.removeView(view);
            }
            this.f1395c = viewInflate;
            if (viewInflate != null && (this.f1394b & 16) != 0) {
                toolbar.addView(viewInflate);
            }
            l(this.f1394b | 16);
        }
        int iL = x0VarT.l(k.j.ActionBar_height, 0);
        if (iL > 0) {
            ViewGroup.LayoutParams layoutParams = toolbar.getLayoutParams();
            layoutParams.height = iL;
            toolbar.setLayoutParams(layoutParams);
        }
        int iD = x0VarT.d(k.j.ActionBar_contentInsetStart, -1);
        int iD2 = x0VarT.d(k.j.ActionBar_contentInsetEnd, -1);
        if (iD >= 0 || iD2 >= 0) {
            toolbar.z(Math.max(iD, 0), Math.max(iD2, 0));
        }
        int iM2 = x0VarT.m(k.j.ActionBar_titleTextStyle, 0);
        if (iM2 != 0) {
            toolbar.C(toolbar.getContext(), iM2);
        }
        int iM3 = x0VarT.m(k.j.ActionBar_subtitleTextStyle, 0);
        if (iM3 != 0) {
            toolbar.B(toolbar.getContext(), iM3);
        }
        int iM4 = x0VarT.m(k.j.ActionBar_popupTheme, 0);
        if (iM4 != 0) {
            toolbar.setPopupTheme(iM4);
        }
        x0VarT.u();
        if (i2 != this.f1406n) {
            this.f1406n = i2;
            if (TextUtils.isEmpty(toolbar.getNavigationContentDescription())) {
                int i4 = this.f1406n;
                this.f1402j = i4 != 0 ? toolbar.getContext().getString(i4) : null;
                t();
            }
        }
        this.f1402j = toolbar.getNavigationContentDescription();
        toolbar.setNavigationOnClickListener(new y0(this));
    }

    private void t() {
        if ((this.f1394b & 4) != 0) {
            boolean zIsEmpty = TextUtils.isEmpty(this.f1402j);
            Toolbar toolbar = this.f1393a;
            if (zIsEmpty) {
                toolbar.setNavigationContentDescription(this.f1406n);
            } else {
                toolbar.setNavigationContentDescription(this.f1402j);
            }
        }
    }

    private void u() {
        Drawable drawable;
        int i2 = this.f1394b;
        if ((i2 & 2) == 0) {
            drawable = null;
        } else if ((i2 & 1) == 0 || (drawable = this.f1397e) == null) {
            drawable = this.f1396d;
        }
        this.f1393a.setLogo(drawable);
    }

    @Override // android.support.v7.widget.d0
    public final void a(android.support.v7.view.menu.h hVar, o.a aVar) {
        ActionMenuPresenter actionMenuPresenter = this.f1405m;
        Toolbar toolbar = this.f1393a;
        if (actionMenuPresenter == null) {
            this.f1405m = new ActionMenuPresenter(toolbar.getContext());
        }
        this.f1405m.d(aVar);
        toolbar.A(hVar, this.f1405m);
    }

    @Override // android.support.v7.widget.d0
    public final boolean b() {
        return this.f1393a.t();
    }

    @Override // android.support.v7.widget.d0
    public final void c() {
        this.f1404l = true;
    }

    @Override // android.support.v7.widget.d0
    public final void collapseActionView() {
        this.f1393a.e();
    }

    @Override // android.support.v7.widget.d0
    public final boolean d() {
        return this.f1393a.s();
    }

    @Override // android.support.v7.widget.d0
    public final boolean e() {
        return this.f1393a.q();
    }

    @Override // android.support.v7.widget.d0
    public final boolean f() {
        return this.f1393a.E();
    }

    @Override // android.support.v7.widget.d0
    public final boolean g() {
        return this.f1393a.d();
    }

    @Override // android.support.v7.widget.d0
    public final Context getContext() {
        return this.f1393a.getContext();
    }

    @Override // android.support.v7.widget.d0
    public final CharSequence getTitle() {
        return this.f1393a.getTitle();
    }

    @Override // android.support.v7.widget.d0
    public final void h() {
        this.f1393a.f();
    }

    @Override // android.support.v7.widget.d0
    public final void i(int i2) {
        this.f1393a.setVisibility(i2);
    }

    @Override // android.support.v7.widget.d0
    public final Toolbar j() {
        return this.f1393a;
    }

    @Override // android.support.v7.widget.d0
    public final boolean k() {
        return this.f1393a.p();
    }

    @Override // android.support.v7.widget.d0
    public final void l(int i2) {
        View view;
        int i3 = this.f1394b ^ i2;
        this.f1394b = i2;
        if (i3 != 0) {
            if ((i3 & 4) != 0) {
                if ((i2 & 4) != 0) {
                    t();
                }
                int i4 = this.f1394b & 4;
                Toolbar toolbar = this.f1393a;
                if (i4 != 0) {
                    Drawable drawable = this.f1398f;
                    if (drawable == null) {
                        drawable = this.f1407o;
                    }
                    toolbar.setNavigationIcon(drawable);
                } else {
                    toolbar.setNavigationIcon((Drawable) null);
                }
            }
            if ((i3 & 3) != 0) {
                u();
            }
            int i5 = i3 & 8;
            Toolbar toolbar2 = this.f1393a;
            if (i5 != 0) {
                if ((i2 & 8) != 0) {
                    toolbar2.setTitle(this.f1400h);
                    toolbar2.setSubtitle(this.f1401i);
                } else {
                    toolbar2.setTitle((CharSequence) null);
                    toolbar2.setSubtitle((CharSequence) null);
                }
            }
            if ((i3 & 16) == 0 || (view = this.f1395c) == null) {
                return;
            }
            if ((i2 & 16) != 0) {
                toolbar2.addView(view);
            } else {
                toolbar2.removeView(view);
            }
        }
    }

    @Override // android.support.v7.widget.d0
    public final void m() {
    }

    @Override // android.support.v7.widget.d0
    public final int n() {
        return this.f1394b;
    }

    @Override // android.support.v7.widget.d0
    public final void o(int i2) {
        this.f1397e = i2 != 0 ? l.a.a(this.f1393a.getContext(), i2) : null;
        u();
    }

    @Override // android.support.v7.widget.d0
    public final android.support.v4.view.l p(int i2, long j2) {
        android.support.v4.view.l lVarA = android.support.v4.view.h.a(this.f1393a);
        lVarA.a(i2 == 0 ? 1.0f : 0.0f);
        lVarA.d(j2);
        lVarA.f(new a(i2));
        return lVarA;
    }

    @Override // android.support.v7.widget.d0
    public final void q() {
        Log.i("ToolbarWidgetWrapper", "Progress display unsupported");
    }

    @Override // android.support.v7.widget.d0
    public final void r() {
        Log.i("ToolbarWidgetWrapper", "Progress display unsupported");
    }

    @Override // android.support.v7.widget.d0
    public final void s(boolean z2) {
        this.f1393a.setCollapsible(z2);
    }

    @Override // android.support.v7.widget.d0
    public final void setIcon(int i2) {
        setIcon(i2 != 0 ? l.a.a(this.f1393a.getContext(), i2) : null);
    }

    @Override // android.support.v7.widget.d0
    public final void setWindowCallback(Window.Callback callback) {
        this.f1403k = callback;
    }

    @Override // android.support.v7.widget.d0
    public final void setWindowTitle(CharSequence charSequence) {
        if (this.f1399g) {
            return;
        }
        this.f1400h = charSequence;
        if ((this.f1394b & 8) != 0) {
            this.f1393a.setTitle(charSequence);
        }
    }

    @Override // android.support.v7.widget.d0
    public final void setIcon(Drawable drawable) {
        this.f1396d = drawable;
        u();
    }
}
