package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.internal.GoogleApiManager;
import com.google.android.gms.common.internal.BaseGmsClient;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zabm implements BaseGmsClient.SignOutCallbacks {
    final /* synthetic */ GoogleApiManager.zaa zaix;

    zabm(GoogleApiManager.zaa zaaVar) {
        this.zaix = zaaVar;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient.SignOutCallbacks
    public final void onSignOutComplete() {
        GoogleApiManager.this.handler.post(new zabn(this));
    }
}
