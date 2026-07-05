package android.support.v7.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.view.menu.o;
import android.view.Window;

/* JADX INFO: compiled from: DecorToolbar.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public interface d0 {
    void a(android.support.v7.view.menu.h hVar, o.a aVar);

    boolean b();

    void c();

    void collapseActionView();

    boolean d();

    boolean e();

    boolean f();

    boolean g();

    Context getContext();

    CharSequence getTitle();

    void h();

    void i(int i2);

    Toolbar j();

    boolean k();

    void l(int i2);

    void m();

    int n();

    void o(int i2);

    android.support.v4.view.l p(int i2, long j2);

    void q();

    void r();

    void s(boolean z2);

    void setIcon(int i2);

    void setIcon(Drawable drawable);

    void setWindowCallback(Window.Callback callback);

    void setWindowTitle(CharSequence charSequence);
}
