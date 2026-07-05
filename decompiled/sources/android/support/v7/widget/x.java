package android.support.v7.widget;

import android.support.v7.widget.w;
import android.view.View;
import android.widget.AdapterView;

/* JADX INFO: compiled from: AppCompatSpinner.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class x implements AdapterView.OnItemClickListener {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final /* synthetic */ w.b f1374b;

    x(w.b bVar) {
        this.f1374b = bVar;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public final void onItemClick(AdapterView<?> adapterView, View view, int i2, long j2) {
        w.b bVar = this.f1374b;
        w.this.setSelection(i2);
        if (w.this.getOnItemClickListener() != null) {
            w.this.performItemClick(view, i2, bVar.E.getItemId(i2));
        }
        bVar.dismiss();
    }
}
