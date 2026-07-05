package com.google.android.gms.internal.games;

import android.annotation.SuppressLint;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.event.Events;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzv implements Events {
    @Override // com.google.android.gms.games.event.Events
    @SuppressLint({"MissingRemoteException"})
    public final void increment(GoogleApiClient googleApiClient, String str, int i2) {
        com.google.android.gms.games.internal.zze zzeVarZzb = Games.zzb(googleApiClient, false);
        if (zzeVarZzb == null) {
            return;
        }
        if (zzeVarZzb.isConnected()) {
            zzeVarZzb.zza(str, i2);
        } else {
            googleApiClient.execute(new zzy(this, googleApiClient, str, i2));
        }
    }

    @Override // com.google.android.gms.games.event.Events
    public final PendingResult<Events.LoadEventsResult> load(GoogleApiClient googleApiClient, boolean z2) {
        return googleApiClient.enqueue(new zzx(this, googleApiClient, z2));
    }

    @Override // com.google.android.gms.games.event.Events
    public final PendingResult<Events.LoadEventsResult> loadByIds(GoogleApiClient googleApiClient, boolean z2, String... strArr) {
        return googleApiClient.enqueue(new zzw(this, googleApiClient, z2, strArr));
    }
}
