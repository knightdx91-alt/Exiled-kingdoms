package android.support.v7.widget;

import android.view.View;
import android.widget.AdapterView;

/* JADX INFO: compiled from: ListPopupWindow.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class j0 implements AdapterView.OnItemSelectedListener {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final /* synthetic */ k0 f1267b;

    j0(k0 k0Var) {
        this.f1267b = k0Var;
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public final void onItemSelected(AdapterView<?> adapterView, View view, int i2, long j2) {
        f0 f0Var;
        if (i2 == -1 || (f0Var = this.f1267b.f1270d) == null) {
            return;
        }
        f0Var.setListSelectionHidden(false);
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public final void onNothingSelected(AdapterView<?> adapterView) {
    }
}
