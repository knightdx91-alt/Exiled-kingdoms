package com.google.android.gms.games.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.internal.BaseGmsClient;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzg implements BaseImplementation.ResultHolder<Status> {
    private final /* synthetic */ BaseGmsClient.SignOutCallbacks zzgb;

    zzg(zze zzeVar, BaseGmsClient.SignOutCallbacks signOutCallbacks) {
        this.zzgb = signOutCallbacks;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder
    public final void setFailedResult(Status status) {
        this.zzgb.onSignOutComplete();
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder
    public final /* synthetic */ void setResult(Status status) {
        this.zzgb.onSignOutComplete();
    }
}
