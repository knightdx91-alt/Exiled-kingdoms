package com.google.android.gms.internal.games;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.video.VideoCapabilities;
import com.google.android.gms.games.video.Videos;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzef implements Videos.CaptureCapabilitiesResult {
    private final /* synthetic */ Status zzbc;

    zzef(zzee zzeeVar, Status status) {
        this.zzbc = status;
    }

    @Override // com.google.android.gms.games.video.Videos.CaptureCapabilitiesResult
    public final VideoCapabilities getCapabilities() {
        return null;
    }

    @Override // com.google.android.gms.common.api.Result
    public final Status getStatus() {
        return this.zzbc;
    }
}
