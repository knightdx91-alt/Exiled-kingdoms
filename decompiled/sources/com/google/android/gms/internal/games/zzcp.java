package com.google.android.gms.internal.games;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.snapshot.SnapshotMetadata;
import com.google.android.gms.games.snapshot.Snapshots;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzcp implements Snapshots.CommitSnapshotResult {
    private final /* synthetic */ Status zzbc;

    zzcp(zzco zzcoVar, Status status) {
        this.zzbc = status;
    }

    @Override // com.google.android.gms.games.snapshot.Snapshots.CommitSnapshotResult
    public final SnapshotMetadata getSnapshotMetadata() {
        return null;
    }

    @Override // com.google.android.gms.common.api.Result
    public final Status getStatus() {
        return this.zzbc;
    }
}
