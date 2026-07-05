package com.google.android.gms.internal.games;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzaq extends zzay {
    private final /* synthetic */ String zzbq;
    private final /* synthetic */ boolean zzjg;
    private final /* synthetic */ int zzjm;
    private final /* synthetic */ int zzjn;
    private final /* synthetic */ int zzjo;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzaq(zzam zzamVar, GoogleApiClient googleApiClient, String str, int i2, int i3, int i4, boolean z2) {
        super(googleApiClient, null);
        this.zzbq = str;
        this.zzjm = i2;
        this.zzjn = i3;
        this.zzjo = i4;
        this.zzjg = z2;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    protected final /* synthetic */ void doExecute(Api.AnyClient anyClient) {
        ((com.google.android.gms.games.internal.zze) anyClient).zza(this, this.zzbq, this.zzjm, this.zzjn, this.zzjo, this.zzjg);
    }
}
