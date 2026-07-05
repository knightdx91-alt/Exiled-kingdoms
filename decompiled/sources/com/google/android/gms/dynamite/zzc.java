package com.google.android.gms.dynamite;

import android.content.Context;
import com.google.android.gms.dynamite.DynamiteModule;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzc implements DynamiteModule.VersionPolicy {
    zzc() {
    }

    @Override // com.google.android.gms.dynamite.DynamiteModule.VersionPolicy
    public final DynamiteModule.VersionPolicy.zzb zza(Context context, String str, DynamiteModule.VersionPolicy.zza zzaVar) {
        DynamiteModule.VersionPolicy.zzb zzbVar = new DynamiteModule.VersionPolicy.zzb();
        int localVersion = zzaVar.getLocalVersion(context, str);
        zzbVar.zziq = localVersion;
        if (localVersion != 0) {
            zzbVar.zzis = -1;
        } else {
            int iZza = zzaVar.zza(context, str, true);
            zzbVar.zzir = iZza;
            if (iZza != 0) {
                zzbVar.zzis = 1;
            }
        }
        return zzbVar;
    }
}
