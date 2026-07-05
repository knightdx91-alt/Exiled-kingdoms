package com.google.android.gms.games;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.games.snapshot.Snapshots;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzbz implements PendingResultUtil.ResultConverter<Snapshots.DeleteSnapshotResult, String> {
    zzbz() {
    }

    @Override // com.google.android.gms.common.internal.PendingResultUtil.ResultConverter
    public final /* synthetic */ String convert(Result result) {
        Snapshots.DeleteSnapshotResult deleteSnapshotResult = (Snapshots.DeleteSnapshotResult) result;
        if (deleteSnapshotResult == null) {
            return null;
        }
        return deleteSnapshotResult.getSnapshotId();
    }
}
