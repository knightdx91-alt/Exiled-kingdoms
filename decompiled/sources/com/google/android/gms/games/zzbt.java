package com.google.android.gms.games;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.games.SnapshotsClient;
import com.google.android.gms.games.snapshot.Snapshot;
import com.google.android.gms.games.snapshot.Snapshots;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzbt implements PendingResultUtil.ResultConverter<Snapshots.OpenSnapshotResult, SnapshotsClient.DataOrConflict<Snapshot>> {
    zzbt() {
    }

    @Override // com.google.android.gms.common.internal.PendingResultUtil.ResultConverter
    public final /* synthetic */ SnapshotsClient.DataOrConflict<Snapshot> convert(Result result) {
        Snapshots.OpenSnapshotResult openSnapshotResult = (Snapshots.OpenSnapshotResult) result;
        if (openSnapshotResult != null) {
            Snapshot snapshotFreeze = openSnapshotResult.getSnapshot() != null ? openSnapshotResult.getSnapshot().freeze() : null;
            if (openSnapshotResult.getStatus().getStatusCode() == 0) {
                return new SnapshotsClient.DataOrConflict<>(snapshotFreeze, null);
            }
            if (openSnapshotResult.getStatus().getStatusCode() == 4004) {
                SnapshotsClient.SnapshotConflict snapshotConflict = (snapshotFreeze == null || openSnapshotResult.getConflictId() == null || openSnapshotResult.getConflictingSnapshot() == null || openSnapshotResult.getResolutionSnapshotContents() == null) ? null : new SnapshotsClient.SnapshotConflict(snapshotFreeze, openSnapshotResult.getConflictId(), openSnapshotResult.getConflictingSnapshot().freeze(), openSnapshotResult.getResolutionSnapshotContents());
                if (snapshotConflict != null) {
                    return new SnapshotsClient.DataOrConflict<>(null, snapshotConflict);
                }
            }
        }
        return null;
    }
}
