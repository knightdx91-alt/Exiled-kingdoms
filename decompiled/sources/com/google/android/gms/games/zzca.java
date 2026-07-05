package com.google.android.gms.games;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.games.snapshot.SnapshotMetadata;
import com.google.android.gms.games.snapshot.Snapshots;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzca implements PendingResultUtil.ResultConverter<Snapshots.CommitSnapshotResult, SnapshotMetadata> {
    zzca() {
    }

    @Override // com.google.android.gms.common.internal.PendingResultUtil.ResultConverter
    public final /* synthetic */ SnapshotMetadata convert(Result result) {
        SnapshotMetadata snapshotMetadata;
        Snapshots.CommitSnapshotResult commitSnapshotResult = (Snapshots.CommitSnapshotResult) result;
        if (commitSnapshotResult == null || (snapshotMetadata = commitSnapshotResult.getSnapshotMetadata()) == null) {
            return null;
        }
        return snapshotMetadata.freeze();
    }
}
