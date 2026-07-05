package com.google.android.gms.dynamite;

import android.content.Context;
import com.google.android.gms.dynamite.DynamiteModule;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzg implements DynamiteModule.VersionPolicy {
    zzg() {
    }

    @Override // com.google.android.gms.dynamite.DynamiteModule.VersionPolicy
    public final DynamiteModule.VersionPolicy.zzb zza(Context context, String str, DynamiteModule.VersionPolicy.zza zzaVar) {
        DynamiteModule.VersionPolicy.zzb zzbVar = new DynamiteModule.VersionPolicy.zzb();
        int localVersion = zzaVar.getLocalVersion(context, str);
        zzbVar.zziq = localVersion;
        if (localVersion != 0) {
            zzbVar.zzir = zzaVar.zza(context, str, false);
        } else {
            zzbVar.zzir = zzaVar.zza(context, str, true);
        }
        int i2 = zzbVar.zziq;
        if (i2 == 0 && zzbVar.zzir == 0) {
            zzbVar.zzis = 0;
        } else if (zzbVar.zzir >= i2) {
            zzbVar.zzis = 1;
        } else {
            zzbVar.zzis = -1;
        }
        return zzbVar;
    }
}
