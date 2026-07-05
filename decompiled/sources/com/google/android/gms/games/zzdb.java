package com.google.android.gms.games;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.games.video.CaptureState;
import com.google.android.gms.games.video.Videos;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzdb implements PendingResultUtil.ResultConverter<Videos.CaptureStateResult, CaptureState> {
    zzdb() {
    }

    @Override // com.google.android.gms.common.internal.PendingResultUtil.ResultConverter
    public final /* synthetic */ CaptureState convert(Result result) {
        Videos.CaptureStateResult captureStateResult = (Videos.CaptureStateResult) result;
        if (captureStateResult == null) {
            return null;
        }
        return captureStateResult.getCaptureState();
    }
}
