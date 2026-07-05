package com.google.android.gms.internal.games;

import android.content.Intent;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.video.Videos;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzdy implements Videos {
    @Override // com.google.android.gms.games.video.Videos
    public final PendingResult<Videos.CaptureCapabilitiesResult> getCaptureCapabilities(GoogleApiClient googleApiClient) {
        return googleApiClient.enqueue(new zzdz(this, googleApiClient));
    }

    @Override // com.google.android.gms.games.video.Videos
    public final Intent getCaptureOverlayIntent(GoogleApiClient googleApiClient) {
        return Games.zza(googleApiClient).zzay();
    }

    @Override // com.google.android.gms.games.video.Videos
    public final PendingResult<Videos.CaptureStateResult> getCaptureState(GoogleApiClient googleApiClient) {
        return googleApiClient.enqueue(new zzea(this, googleApiClient));
    }

    @Override // com.google.android.gms.games.video.Videos
    public final PendingResult<Videos.CaptureAvailableResult> isCaptureAvailable(GoogleApiClient googleApiClient, int i2) {
        return googleApiClient.enqueue(new zzeb(this, googleApiClient, i2));
    }

    @Override // com.google.android.gms.games.video.Videos
    public final boolean isCaptureSupported(GoogleApiClient googleApiClient) {
        return Games.zza(googleApiClient).zzba();
    }

    @Override // com.google.android.gms.games.video.Videos
    public final void registerCaptureOverlayStateChangedListener(GoogleApiClient googleApiClient, Videos.CaptureOverlayStateListener captureOverlayStateListener) {
        com.google.android.gms.games.internal.zze zzeVarZza = Games.zza(googleApiClient, false);
        if (zzeVarZza != null) {
            zzeVarZza.zzh(googleApiClient.registerListener(captureOverlayStateListener));
        }
    }

    @Override // com.google.android.gms.games.video.Videos
    public final void unregisterCaptureOverlayStateChangedListener(GoogleApiClient googleApiClient) {
        com.google.android.gms.games.internal.zze zzeVarZza = Games.zza(googleApiClient, false);
        if (zzeVarZza != null) {
            zzeVarZza.zzbc();
        }
    }
}
