package com.google.android.gms.internal.play_billing;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.0.1 */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzdl {
    private static final zzdk zza;
    private static final zzdk zzb;

    static {
        zzdk zzdkVar = null;
        try {
            zzdkVar = (zzdk) Class.forName("com.google.protobuf.NewInstanceSchemaFull").getDeclaredConstructor(null).newInstance(null);
        } catch (Exception unused) {
        }
        zza = zzdkVar;
        zzb = new zzdk();
    }

    static zzdk zza() {
        return zza;
    }

    static zzdk zzb() {
        return zzb;
    }
}
