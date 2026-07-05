package android.support.v7.app;

import android.R;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatDelegateImplV9;
import android.support.v7.widget.x0;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.Window;

/* JADX INFO: compiled from: AppCompatDelegateImplBase.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
abstract class f extends e {

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private static final int[] f756o = {R.attr.windowBackground};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final Context f757b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final Window f758c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final Window.Callback f759d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final Object f760e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    s f761f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    n.g f762g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    boolean f763h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    boolean f764i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    boolean f765j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    boolean f766k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    boolean f767l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private CharSequence f768m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private boolean f769n;

    /* JADX INFO: compiled from: AppCompatDelegateImplBase.java */
    class a extends n.i {
        a(Window.Callback callback) {
            super(callback);
        }

        @Override // n.i, android.view.Window.Callback
        public final boolean dispatchKeyEvent(KeyEvent keyEvent) {
            return f.this.t(keyEvent) || super.dispatchKeyEvent(keyEvent);
        }

        @Override // n.i, android.view.Window.Callback
        public final boolean dispatchKeyShortcutEvent(KeyEvent keyEvent) {
            if (!super.dispatchKeyShortcutEvent(keyEvent)) {
                if (!f.this.x(keyEvent.getKeyCode(), keyEvent)) {
                    return false;
                }
            }
            return true;
        }

        @Override // android.view.Window.Callback
        public final void onContentChanged() {
        }

        @Override // n.i, android.view.Window.Callback
        public final boolean onCreatePanelMenu(int i2, Menu menu) {
            if (i2 != 0 || (menu instanceof android.support.v7.view.menu.h)) {
                return super.onCreatePanelMenu(i2, menu);
            }
            return false;
        }

        @Override // n.i, android.view.Window.Callback
        public final boolean onMenuOpened(int i2, Menu menu) {
            super.onMenuOpened(i2, menu);
            AppCompatDelegateImplV9 appCompatDelegateImplV9 = (AppCompatDelegateImplV9) f.this;
            if (i2 == 108) {
                appCompatDelegateImplV9.v();
                s sVar = appCompatDelegateImplV9.f761f;
                if (sVar != null) {
                    sVar.b(true);
                }
            } else {
                appCompatDelegateImplV9.getClass();
            }
            return true;
        }

        @Override // n.i, android.view.Window.Callback
        public final void onPanelClosed(int i2, Menu menu) {
            super.onPanelClosed(i2, menu);
            AppCompatDelegateImplV9 appCompatDelegateImplV9 = (AppCompatDelegateImplV9) f.this;
            if (i2 == 108) {
                appCompatDelegateImplV9.v();
                s sVar = appCompatDelegateImplV9.f761f;
                if (sVar != null) {
                    sVar.b(false);
                    return;
                }
                return;
            }
            if (i2 != 0) {
                appCompatDelegateImplV9.getClass();
                return;
            }
            AppCompatDelegateImplV9.PanelFeatureState panelFeatureStateI = appCompatDelegateImplV9.I(i2);
            if (panelFeatureStateI.f736m) {
                appCompatDelegateImplV9.D(panelFeatureStateI, false);
            }
        }

        @Override // n.i, android.view.Window.Callback
        public final boolean onPreparePanel(int i2, View view, Menu menu) {
            android.support.v7.view.menu.h hVar = menu instanceof android.support.v7.view.menu.h ? (android.support.v7.view.menu.h) menu : null;
            if (i2 == 0 && hVar == null) {
                return false;
            }
            if (hVar != null) {
                hVar.K(true);
            }
            boolean zOnPreparePanel = super.onPreparePanel(i2, view, menu);
            if (hVar != null) {
                hVar.K(false);
            }
            return zOnPreparePanel;
        }
    }

    f(Context context, Window window, d dVar) {
        this.f757b = context;
        this.f758c = window;
        Window.Callback callback = window.getCallback();
        this.f759d = callback;
        if (callback instanceof a) {
            throw new IllegalStateException("AppCompat has already installed itself into the Window");
        }
        window.setCallback(z(callback));
        x0 x0VarS = x0.s(context, null, f756o);
        Drawable drawableG = x0VarS.g(0);
        if (drawableG != null) {
            window.setBackgroundDrawable(drawableG);
        }
        x0VarS.u();
    }

    @Override // android.support.v7.app.e
    public void j() {
        this.f769n = true;
    }

    @Override // android.support.v7.app.e
    public void m() {
        d();
    }

    @Override // android.support.v7.app.e
    public final void s(CharSequence charSequence) {
        this.f768m = charSequence;
        y(charSequence);
    }

    abstract boolean t(KeyEvent keyEvent);

    final CharSequence u() {
        Window.Callback callback = this.f759d;
        return callback instanceof Activity ? ((Activity) callback).getTitle() : this.f768m;
    }

    abstract void v();

    final boolean w() {
        return this.f769n;
    }

    abstract boolean x(int i2, KeyEvent keyEvent);

    abstract void y(CharSequence charSequence);

    abstract Window.Callback z(Window.Callback callback);
}
