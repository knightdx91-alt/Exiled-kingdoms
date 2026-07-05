package com.google.android.gms.internal.games;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.video.Videos;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzed implements Videos.CaptureAvailableResult {
    private final /* synthetic */ Status zzbc;

    zzed(zzec zzecVar, Status status) {
        this.zzbc = status;
    }

    @Override // com.google.android.gms.common.api.Result
    public final Status getStatus() {
        return this.zzbc;
    }

    @Override // com.google.android.gms.games.video.Videos.CaptureAvailableResult
    public final boolean isAvailable() {
        return false;
    }
}
