package com.google.android.gms.internal.games;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.video.CaptureState;
import com.google.android.gms.games.video.Videos;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzeh implements Videos.CaptureStateResult {
    private final /* synthetic */ Status zzbc;

    zzeh(zzeg zzegVar, Status status) {
        this.zzbc = status;
    }

    @Override // com.google.android.gms.games.video.Videos.CaptureStateResult
    public final CaptureState getCaptureState() {
        return null;
    }

    @Override // com.google.android.gms.common.api.Result
    public final Status getStatus() {
        return this.zzbc;
    }
}
