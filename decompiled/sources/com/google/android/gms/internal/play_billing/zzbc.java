package com.google.android.gms.internal.play_billing;

import com.google.android.gms.common.api.Api;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.0.1 */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzbc extends zzbe {
    private final byte[] zzb;
    private int zzc;
    private int zzd;
    private int zze;

    /* synthetic */ zzbc(byte[] bArr, int i2, int i3, boolean z2, zzbb zzbbVar) {
        super(null);
        this.zze = Api.BaseClientBuilder.API_PRIORITY_OTHER;
        this.zzb = bArr;
        this.zzc = 0;
    }

    public final int zza(int i2) {
        int i3 = this.zze;
        this.zze = 0;
        int i4 = this.zzc + this.zzd;
        this.zzc = i4;
        if (i4 > 0) {
            this.zzd = i4;
            this.zzc = 0;
        } else {
            this.zzd = 0;
        }
        return i3;
    }
}
