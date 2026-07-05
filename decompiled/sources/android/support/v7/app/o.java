package android.support.v7.app;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: compiled from: AppCompatDialog.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class o extends Dialog implements d {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private e f785b;

    public final e a() {
        if (this.f785b == null) {
            this.f785b = new g(getContext(), getWindow(), this);
        }
        return this.f785b;
    }

    @Override // android.app.Dialog
    public final void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        a().c(view, layoutParams);
    }

    @Override // android.app.Dialog
    public final <T extends View> T findViewById(int i2) {
        return (T) a().e(i2);
    }

    @Override // android.app.Dialog
    public final void invalidateOptionsMenu() {
        a().g();
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        a().f();
        super.onCreate(bundle);
        a().i(bundle);
    }

    @Override // android.app.Dialog
    protected final void onStop() {
        super.onStop();
        a().n();
    }

    @Override // android.app.Dialog
    public final void setContentView(int i2) {
        a().p(i2);
    }

    @Override // android.app.Dialog
    public void setTitle(CharSequence charSequence) {
        super.setTitle(charSequence);
        a().s(charSequence);
    }

    @Override // android.app.Dialog
    public final void setContentView(View view) {
        a().q(view);
    }

    @Override // android.app.Dialog
    public final void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        a().r(view, layoutParams);
    }

    @Override // android.app.Dialog
    public final void setTitle(int i2) {
        super.setTitle(i2);
        a().s(getContext().getString(i2));
    }
}
