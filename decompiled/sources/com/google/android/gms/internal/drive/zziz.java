package com.google.android.gms.internal.drive;

import java.util.Arrays;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zziz {
    final int tag;
    final byte[] zzng;

    zziz(int i2, byte[] bArr) {
        this.tag = i2;
        this.zzng = bArr;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zziz)) {
            return false;
        }
        zziz zzizVar = (zziz) obj;
        return this.tag == zzizVar.tag && Arrays.equals(this.zzng, zzizVar.zzng);
    }

    public final int hashCode() {
        return Arrays.hashCode(this.zzng) + ((this.tag + 527) * 31);
    }
}
