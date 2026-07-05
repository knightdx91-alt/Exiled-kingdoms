package com.google.android.gms.games;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.ApiExceptionUtil;
import com.google.android.gms.games.SnapshotsClient;
import com.google.android.gms.games.snapshot.Snapshots;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzby implements com.google.android.gms.games.internal.zzp<Snapshots.OpenSnapshotResult> {
    zzby() {
    }

    @Override // com.google.android.gms.games.internal.zzp
    public final /* synthetic */ ApiException zza(Status status, Snapshots.OpenSnapshotResult openSnapshotResult) {
        Snapshots.OpenSnapshotResult openSnapshotResult2 = openSnapshotResult;
        return (status.getStatusCode() != 26572 || openSnapshotResult2.getSnapshot() == null || openSnapshotResult2.getSnapshot().getMetadata() == null) ? ApiExceptionUtil.fromStatus(status) : new SnapshotsClient.SnapshotContentUnavailableApiException(status, openSnapshotResult2.getSnapshot().getMetadata().freeze());
    }
}
