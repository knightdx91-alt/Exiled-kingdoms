package com.google.android.gms.games;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.games.video.VideoCapabilities;
import com.google.android.gms.games.video.Videos;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzdc implements PendingResultUtil.ResultConverter<Videos.CaptureCapabilitiesResult, VideoCapabilities> {
    zzdc() {
    }

    @Override // com.google.android.gms.common.internal.PendingResultUtil.ResultConverter
    public final /* synthetic */ VideoCapabilities convert(Result result) {
        Videos.CaptureCapabilitiesResult captureCapabilitiesResult = (Videos.CaptureCapabilitiesResult) result;
        if (captureCapabilitiesResult == null) {
            return null;
        }
        return captureCapabilitiesResult.getCapabilities();
    }
}
