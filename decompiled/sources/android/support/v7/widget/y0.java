package android.support.v7.widget;

import android.view.View;
import android.view.Window;

/* JADX INFO: compiled from: ToolbarWidgetWrapper.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class y0 implements View.OnClickListener {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final o.a f1389b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final /* synthetic */ z0 f1390c;

    y0(z0 z0Var) {
        this.f1390c = z0Var;
        this.f1389b = new o.a(z0Var.f1393a.getContext(), z0Var.f1400h);
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        z0 z0Var = this.f1390c;
        Window.Callback callback = z0Var.f1403k;
        if (callback == null || !z0Var.f1404l) {
            return;
        }
        callback.onMenuItemSelected(0, this.f1389b);
    }
}
