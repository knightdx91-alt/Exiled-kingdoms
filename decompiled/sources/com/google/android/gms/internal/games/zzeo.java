package com.google.android.gms.internal.games;

import java.util.HashMap;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzeo {
    private HashMap<String, Integer> zzmu = new HashMap<>();
    private int statusCode = 0;

    public final zzem zzca() {
        return new zzem(this.statusCode, this.zzmu);
    }

    public final zzeo zzh(String str, int i2) {
        if (i2 == 0 || i2 == 1 || i2 == 2 || i2 == 3) {
            this.zzmu.put(str, Integer.valueOf(i2));
        }
        return this;
    }

    public final zzeo zzo(int i2) {
        this.statusCode = i2;
        return this;
    }
}
