package com.google.android.gms.games;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.games.video.Videos;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzda implements PendingResultUtil.ResultConverter<Videos.CaptureAvailableResult, Boolean> {
    zzda() {
    }

    @Override // com.google.android.gms.common.internal.PendingResultUtil.ResultConverter
    public final /* synthetic */ Boolean convert(Result result) {
        Videos.CaptureAvailableResult captureAvailableResult = (Videos.CaptureAvailableResult) result;
        return captureAvailableResult == null ? Boolean.FALSE : Boolean.valueOf(captureAvailableResult.isAvailable());
    }
}
