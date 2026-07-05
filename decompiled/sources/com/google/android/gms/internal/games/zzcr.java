package com.google.android.gms.internal.games;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.snapshot.Snapshots;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzcr implements Snapshots.DeleteSnapshotResult {
    private final /* synthetic */ Status zzbc;

    zzcr(zzcq zzcqVar, Status status) {
        this.zzbc = status;
    }

    @Override // com.google.android.gms.games.snapshot.Snapshots.DeleteSnapshotResult
    public final String getSnapshotId() {
        return null;
    }

    @Override // com.google.android.gms.common.api.Result
    public final Status getStatus() {
        return this.zzbc;
    }
}
