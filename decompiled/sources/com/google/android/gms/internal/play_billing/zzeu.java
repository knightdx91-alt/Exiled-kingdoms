package com.google.android.gms.internal.play_billing;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.0.1 */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzeu extends IllegalArgumentException {
    zzeu(int i2, int i3) {
        super("Unpaired surrogate at index " + i2 + " of " + i3);
    }
}
