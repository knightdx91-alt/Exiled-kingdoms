package com.google.android.gms.internal.play_billing;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.0.1 */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzbq {
    private static final zzbo zza = new zzbp();
    private static final zzbo zzb;

    static {
        zzbo zzboVar = null;
        try {
            zzboVar = (zzbo) Class.forName("com.google.protobuf.ExtensionSchemaFull").getDeclaredConstructor(null).newInstance(null);
        } catch (Exception unused) {
        }
        zzb = zzboVar;
    }

    static zzbo zza() {
        zzbo zzboVar = zzb;
        if (zzboVar != null) {
            return zzboVar;
        }
        throw new IllegalStateException("Protobuf runtime is not correctly loaded.");
    }

    static zzbo zzb() {
        return zza;
    }
}
