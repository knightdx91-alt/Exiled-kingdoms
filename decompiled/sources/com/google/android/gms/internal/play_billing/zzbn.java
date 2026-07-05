package com.google.android.gms.internal.play_billing;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.0.1 */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzbn {
    static final zzbn zza = new zzbn(true);
    public static final /* synthetic */ int zzb = 0;
    private static volatile boolean zzc;
    private static volatile zzbn zzd;
    private final Map zze;

    zzbn() {
        this.zze = new HashMap();
    }

    public static zzbn zza() {
        zzbn zzbnVar = zzd;
        if (zzbnVar != null) {
            return zzbnVar;
        }
        synchronized (zzbn.class) {
            try {
                zzbn zzbnVar2 = zzd;
                if (zzbnVar2 != null) {
                    return zzbnVar2;
                }
                zzbn zzbnVarZzb = zzbv.zzb(zzbn.class);
                zzd = zzbnVarZzb;
                return zzbnVarZzb;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final zzbz zzb(zzdf zzdfVar, int i2) {
        return (zzbz) this.zze.get(new zzbm(zzdfVar, i2));
    }

    zzbn(boolean z2) {
        this.zze = Collections.emptyMap();
    }
}
