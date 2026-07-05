package com.google.android.gms.auth;

import android.os.IBinder;
import java.util.List;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzg implements zzj<List<AccountChangeEvent>> {
    private final /* synthetic */ String zzr;
    private final /* synthetic */ int zzs;

    zzg(String str, int i2) {
        this.zzr = str;
        this.zzs = i2;
    }

    @Override // com.google.android.gms.auth.zzj
    public final /* synthetic */ List<AccountChangeEvent> zzb(IBinder iBinder) {
        return ((AccountChangeEventsResponse) zzd.zza(com.google.android.gms.internal.auth.zzf.zza(iBinder).zza(new AccountChangeEventsRequest().setAccountName(this.zzr).setEventIndex(this.zzs)))).getEvents();
    }
}
