package com.google.android.gms.internal.play_billing;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.0.1 */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
enum zza {
    RESPONSE_CODE_UNSPECIFIED(-999),
    SERVICE_TIMEOUT(-3),
    FEATURE_NOT_SUPPORTED(-2),
    SERVICE_DISCONNECTED(-1),
    OK(0),
    USER_CANCELED(1),
    SERVICE_UNAVAILABLE(2),
    BILLING_UNAVAILABLE(3),
    ITEM_UNAVAILABLE(4),
    DEVELOPER_ERROR(5),
    ERROR(6),
    ITEM_ALREADY_OWNED(7),
    ITEM_NOT_OWNED(8),
    EXPIRED_OFFER_TOKEN(11),
    NETWORK_ERROR(12);

    private static final zzx zzp;
    private final int zzr;

    static {
        zzw zzwVar = new zzw();
        for (zza zzaVar : values()) {
            zzwVar.zza(Integer.valueOf(zzaVar.zzr), zzaVar);
        }
        zzp = zzwVar.zzb();
    }

    zza(int i2) {
        this.zzr = i2;
    }

    static zza zza(int i2) {
        zzx zzxVar = zzp;
        Integer numValueOf = Integer.valueOf(i2);
        return !zzxVar.containsKey(numValueOf) ? RESPONSE_CODE_UNSPECIFIED : (zza) zzxVar.get(numValueOf);
    }
}
