package com.google.android.gms.internal.games;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.request.GameRequest;
import com.google.android.gms.games.request.OnRequestReceivedListener;
import com.google.android.gms.games.request.Requests;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzca implements Requests {
    @Override // com.google.android.gms.games.request.Requests
    public final PendingResult<Requests.UpdateRequestsResult> acceptRequest(GoogleApiClient googleApiClient, String str) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(str);
        return acceptRequests(googleApiClient, arrayList);
    }

    @Override // com.google.android.gms.games.request.Requests
    public final PendingResult<Requests.UpdateRequestsResult> acceptRequests(GoogleApiClient googleApiClient, List<String> list) {
        return googleApiClient.execute(new zzcb(this, googleApiClient, list == null ? null : (String[]) list.toArray(new String[list.size()])));
    }

    @Override // com.google.android.gms.games.request.Requests
    public final PendingResult<Requests.UpdateRequestsResult> dismissRequest(GoogleApiClient googleApiClient, String str) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(str);
        return dismissRequests(googleApiClient, arrayList);
    }

    @Override // com.google.android.gms.games.request.Requests
    public final PendingResult<Requests.UpdateRequestsResult> dismissRequests(GoogleApiClient googleApiClient, List<String> list) {
        return googleApiClient.execute(new zzcc(this, googleApiClient, list == null ? null : (String[]) list.toArray(new String[list.size()])));
    }

    @Override // com.google.android.gms.games.request.Requests
    public final ArrayList<GameRequest> getGameRequestsFromBundle(Bundle bundle) {
        if (bundle == null || !bundle.containsKey(Requests.EXTRA_REQUESTS)) {
            return new ArrayList<>();
        }
        ArrayList arrayList = (ArrayList) bundle.get(Requests.EXTRA_REQUESTS);
        ArrayList<GameRequest> arrayList2 = new ArrayList<>();
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            arrayList2.add((GameRequest) arrayList.get(i2));
        }
        return arrayList2;
    }

    @Override // com.google.android.gms.games.request.Requests
    public final ArrayList<GameRequest> getGameRequestsFromInboxResponse(Intent intent) {
        return intent == null ? new ArrayList<>() : getGameRequestsFromBundle(intent.getExtras());
    }

    @Override // com.google.android.gms.games.request.Requests
    public final Intent getInboxIntent(GoogleApiClient googleApiClient) {
        return Games.zza(googleApiClient).zzaq();
    }

    @Override // com.google.android.gms.games.request.Requests
    public final int getMaxLifetimeDays(GoogleApiClient googleApiClient) {
        return Games.zza(googleApiClient).zzas();
    }

    @Override // com.google.android.gms.games.request.Requests
    public final int getMaxPayloadSize(GoogleApiClient googleApiClient) {
        return Games.zza(googleApiClient).zzar();
    }

    @Override // com.google.android.gms.games.request.Requests
    public final Intent getSendIntent(GoogleApiClient googleApiClient, int i2, byte[] bArr, int i3, Bitmap bitmap, String str) {
        return Games.zza(googleApiClient).zza(i2, bArr, i3, bitmap, str);
    }

    @Override // com.google.android.gms.games.request.Requests
    public final PendingResult<Requests.LoadRequestsResult> loadRequests(GoogleApiClient googleApiClient, int i2, int i3, int i4) {
        return googleApiClient.enqueue(new zzcd(this, googleApiClient, i2, i3, i4));
    }

    @Override // com.google.android.gms.games.request.Requests
    public final void registerRequestListener(GoogleApiClient googleApiClient, OnRequestReceivedListener onRequestReceivedListener) {
        com.google.android.gms.games.internal.zze zzeVarZza = Games.zza(googleApiClient, false);
        if (zzeVarZza != null) {
            zzeVarZza.zzf(googleApiClient.registerListener(onRequestReceivedListener));
        }
    }

    @Override // com.google.android.gms.games.request.Requests
    public final void unregisterRequestListener(GoogleApiClient googleApiClient) {
        com.google.android.gms.games.internal.zze zzeVarZza = Games.zza(googleApiClient, false);
        if (zzeVarZza != null) {
            zzeVarZza.zzaf();
        }
    }
}
