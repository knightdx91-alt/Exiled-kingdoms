package android.support.v7.view.menu;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.v7.view.menu.o;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

/* JADX INFO: compiled from: MenuPopupHelper.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Context f986a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final h f987b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final boolean f988c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final int f989d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private View f990e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private boolean f992g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private o.a f993h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private m f994i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private PopupWindow.OnDismissListener f995j;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int f991f = 8388611;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private final PopupWindow.OnDismissListener f996k = new a();

    /* JADX INFO: compiled from: MenuPopupHelper.java */
    final class a implements PopupWindow.OnDismissListener {
        a() {
        }

        @Override // android.widget.PopupWindow.OnDismissListener
        public final void onDismiss() {
            n.this.d();
        }
    }

    public n(int i2, Context context, h hVar, View view, boolean z2) {
        this.f986a = context;
        this.f987b = hVar;
        this.f990e = view;
        this.f988c = z2;
        this.f989d = i2;
    }

    private void j(int i2, int i3, boolean z2, boolean z3) {
        m mVarB = b();
        mVarB.u(z3);
        if (z2) {
            if ((Gravity.getAbsoluteGravity(this.f991f, this.f990e.getLayoutDirection()) & 7) == 5) {
                i2 += this.f990e.getWidth();
            }
            mVarB.s(i2);
            mVarB.v(i3);
            int i4 = (int) ((this.f986a.getResources().getDisplayMetrics().density * 48.0f) / 2.0f);
            mVarB.p(new Rect(i2 - i4, i3 - i4, i2 + i4, i3 + i4));
        }
        mVarB.a();
    }

    public final void a() {
        if (c()) {
            this.f994i.dismiss();
        }
    }

    public final m b() {
        m sVar;
        if (this.f994i == null) {
            Context context = this.f986a;
            Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
            Point point = new Point();
            defaultDisplay.getRealSize(point);
            if (Math.min(point.x, point.y) >= context.getResources().getDimensionPixelSize(k.d.abc_cascading_menus_min_smallest_width)) {
                sVar = new d(context, this.f990e, this.f989d, this.f988c);
            } else {
                View view = this.f990e;
                Context context2 = this.f986a;
                boolean z2 = this.f988c;
                sVar = new s(this.f989d, context2, this.f987b, view, z2);
            }
            sVar.l(this.f987b);
            sVar.t(this.f996k);
            sVar.o(this.f990e);
            sVar.d(this.f993h);
            sVar.q(this.f992g);
            sVar.r(this.f991f);
            this.f994i = sVar;
        }
        return this.f994i;
    }

    public final boolean c() {
        m mVar = this.f994i;
        return mVar != null && mVar.k();
    }

    protected void d() {
        this.f994i = null;
        PopupWindow.OnDismissListener onDismissListener = this.f995j;
        if (onDismissListener != null) {
            onDismissListener.onDismiss();
        }
    }

    public final void e(View view) {
        this.f990e = view;
    }

    public final void f(boolean z2) {
        this.f992g = z2;
        m mVar = this.f994i;
        if (mVar != null) {
            mVar.q(z2);
        }
    }

    public final void g(int i2) {
        this.f991f = i2;
    }

    public final void h(PopupWindow.OnDismissListener onDismissListener) {
        this.f995j = onDismissListener;
    }

    public final void i(o.a aVar) {
        this.f993h = aVar;
        m mVar = this.f994i;
        if (mVar != null) {
            mVar.d(aVar);
        }
    }

    public final boolean k() {
        if (c()) {
            return true;
        }
        if (this.f990e == null) {
            return false;
        }
        j(0, 0, false, false);
        return true;
    }

    public final boolean l(int i2, int i3) {
        if (c()) {
            return true;
        }
        if (this.f990e == null) {
            return false;
        }
        j(i2, i3, true, true);
        return true;
    }
}
