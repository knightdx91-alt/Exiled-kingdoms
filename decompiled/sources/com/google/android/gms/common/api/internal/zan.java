package com.google.android.gms.common.api.internal;

import android.app.Dialog;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiActivity;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zan implements Runnable {
    private final zam zadi;
    final /* synthetic */ zal zadj;

    zan(zal zalVar, zam zamVar) {
        this.zadj = zalVar;
        this.zadi = zamVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.zadj.mStarted) {
            ConnectionResult connectionResult = this.zadi.getConnectionResult();
            if (connectionResult.hasResolution()) {
                zal zalVar = this.zadj;
                zalVar.mLifecycleFragment.startActivityForResult(GoogleApiActivity.zaa(zalVar.getActivity(), connectionResult.getResolution(), this.zadi.zar(), false), 1);
            } else if (this.zadj.zacc.isUserResolvableError(connectionResult.getErrorCode())) {
                zal zalVar2 = this.zadj;
                zalVar2.zacc.zaa(zalVar2.getActivity(), this.zadj.mLifecycleFragment, connectionResult.getErrorCode(), 2, this.zadj);
            } else {
                if (connectionResult.getErrorCode() != 18) {
                    this.zadj.zaa(connectionResult, this.zadi.zar());
                    return;
                }
                Dialog dialogZaa = GoogleApiAvailability.zaa(this.zadj.getActivity(), this.zadj);
                zal zalVar3 = this.zadj;
                zalVar3.zacc.zaa(zalVar3.getActivity().getApplicationContext(), new zao(this, dialogZaa));
            }
        }
    }
}
