package com.google.android.gms.internal.play_billing;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.0.1 */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzds extends zzec {
    zzds(int i2) {
        super(i2, null);
    }

    @Override // com.google.android.gms.internal.play_billing.zzec
    public final void zza() {
        if (!zzj()) {
            for (int i2 = 0; i2 < zzb(); i2++) {
                Map.Entry entryZzg = zzg(i2);
                if (((zzbr) entryZzg.getKey()).zzc()) {
                    entryZzg.setValue(Collections.unmodifiableList((List) entryZzg.getValue()));
                }
            }
            for (Map.Entry entry : zzc()) {
                if (((zzbr) entry.getKey()).zzc()) {
                    entry.setValue(Collections.unmodifiableList((List) entry.getValue()));
                }
            }
        }
        super.zza();
    }
}
