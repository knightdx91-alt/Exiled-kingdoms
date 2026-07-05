package com.google.android.gms.internal.games;

import a.a;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzei {
    public static String zzn(int i2) {
        if (i2 == 0) {
            return "DAILY";
        }
        if (i2 == 1) {
            return "WEEKLY";
        }
        if (i2 == 2) {
            return "ALL_TIME";
        }
        throw new IllegalArgumentException(a.g(29, i2, "Unknown time span "));
    }
}
