package android.support.v4.app;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;

/* JADX INFO: compiled from: FragmentController.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final h<?> f215a;

    private g(h<?> hVar) {
        this.f215a = hVar;
    }

    public static final g b(h<?> hVar) {
        return new g(hVar);
    }

    public final y A() {
        return this.f215a.k();
    }

    public final void B() {
        this.f215a.f221d.g0();
    }

    public final View C(View view, String str, Context context, AttributeSet attributeSet) {
        return this.f215a.f221d.onCreateView(view, str, context, attributeSet);
    }

    public final void D() {
        this.f215a.n();
    }

    public final void E(Parcelable parcelable, n nVar) {
        this.f215a.f221d.k0(parcelable, nVar);
    }

    public final void F(android.support.v4.util.i<String, y> iVar) {
        this.f215a.o(iVar);
    }

    public final android.support.v4.util.i<String, y> G() {
        return this.f215a.p();
    }

    public final n H() {
        return this.f215a.f221d.l0();
    }

    public final Parcelable I() {
        return this.f215a.f221d.m0();
    }

    public final void a() {
        h<?> hVar = this.f215a;
        j jVar = hVar.f221d;
        if (jVar.f241m != null) {
            throw new IllegalStateException("Already attached");
        }
        jVar.f241m = hVar;
        jVar.f242n = hVar;
        jVar.f243o = null;
    }

    public final void c() {
        this.f215a.f221d.n();
    }

    public final void d(Configuration configuration) {
        h<?> hVar = this.f215a;
        int i2 = 0;
        while (true) {
            ArrayList<Fragment> arrayList = hVar.f221d.f233e;
            if (i2 >= arrayList.size()) {
                return;
            }
            Fragment fragment = arrayList.get(i2);
            if (fragment != null) {
                fragment.performConfigurationChanged(configuration);
            }
            i2++;
        }
    }

    public final boolean e(MenuItem menuItem) {
        return this.f215a.f221d.o(menuItem);
    }

    public final void f() {
        this.f215a.f221d.p();
    }

    public final boolean g(Menu menu, MenuInflater menuInflater) {
        return this.f215a.f221d.q(menu, menuInflater);
    }

    public final void h() {
        this.f215a.f221d.r();
    }

    public final void i() {
        h<?> hVar = this.f215a;
        int i2 = 0;
        while (true) {
            ArrayList<Fragment> arrayList = hVar.f221d.f233e;
            if (i2 >= arrayList.size()) {
                return;
            }
            Fragment fragment = arrayList.get(i2);
            if (fragment != null) {
                fragment.performLowMemory();
            }
            i2++;
        }
    }

    public final void j(boolean z2) {
        ArrayList<Fragment> arrayList = this.f215a.f221d.f233e;
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            Fragment fragment = arrayList.get(size);
            if (fragment != null) {
                fragment.performMultiWindowModeChanged(z2);
            }
        }
    }

    public final boolean k(MenuItem menuItem) {
        return this.f215a.f221d.H(menuItem);
    }

    public final void l(Menu menu) {
        this.f215a.f221d.I(menu);
    }

    public final void m() {
        this.f215a.f221d.J();
    }

    public final void n(boolean z2) {
        ArrayList<Fragment> arrayList = this.f215a.f221d.f233e;
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            Fragment fragment = arrayList.get(size);
            if (fragment != null) {
                fragment.performPictureInPictureModeChanged(z2);
            }
        }
    }

    public final boolean o(Menu menu) {
        return this.f215a.f221d.K(menu);
    }

    public final void p() {
        this.f215a.f221d.L();
    }

    public final void q() {
        this.f215a.f221d.M();
    }

    public final void r() {
        this.f215a.f221d.N();
    }

    public final void s() {
        this.f215a.f221d.P();
    }

    public final void t() {
        this.f215a.d();
    }

    public final void u() {
        this.f215a.e();
    }

    public final void v(boolean z2) {
        this.f215a.f(z2);
    }

    public final void w(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        this.f215a.g(str, fileDescriptor, printWriter, strArr);
    }

    public final void x() {
        this.f215a.f221d.T();
    }

    public final Fragment y(String str) {
        return this.f215a.f221d.X(str);
    }

    public final i z() {
        return this.f215a.f221d;
    }
}
