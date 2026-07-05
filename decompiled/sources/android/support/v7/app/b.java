package android.support.v7.app;

import android.content.DialogInterface;
import android.support.v7.app.AlertController;
import android.view.View;
import android.widget.AdapterView;

/* JADX INFO: compiled from: AlertController.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class b implements AdapterView.OnItemClickListener {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final /* synthetic */ AlertController f751b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final /* synthetic */ AlertController.b f752c;

    b(AlertController.b bVar, AlertController alertController) {
        this.f752c = bVar;
        this.f751b = alertController;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public final void onItemClick(AdapterView<?> adapterView, View view, int i2, long j2) {
        DialogInterface.OnClickListener onClickListener = this.f752c.f710h;
        AlertController alertController = this.f751b;
        onClickListener.onClick(alertController.f681b, i2);
        alertController.f681b.dismiss();
    }
}
