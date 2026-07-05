package com.google.android.gms.games;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.games.snapshot.SnapshotMetadataBuffer;
import com.google.android.gms.games.snapshot.Snapshots;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzbu implements PendingResultUtil.ResultConverter<Snapshots.LoadSnapshotsResult, SnapshotMetadataBuffer> {
    zzbu() {
    }

    @Override // com.google.android.gms.common.internal.PendingResultUtil.ResultConverter
    public final /* synthetic */ SnapshotMetadataBuffer convert(Result result) {
        Snapshots.LoadSnapshotsResult loadSnapshotsResult = (Snapshots.LoadSnapshotsResult) result;
        if (loadSnapshotsResult == null) {
            return null;
        }
        return loadSnapshotsResult.getSnapshots();
    }
}
