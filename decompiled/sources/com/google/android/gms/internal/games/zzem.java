package com.google.android.gms.internal.games;

import a.a;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.HashMap;
import java.util.Set;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzem {
    private static final String[] zzmt = {"requestId", "outcome"};
    private final int statusCode;
    private final HashMap<String, Integer> zzmu;

    private zzem(int i2, HashMap<String, Integer> map) {
        this.statusCode = i2;
        this.zzmu = map;
    }

    @VisibleForTesting
    public static zzem zzbd(DataHolder dataHolder) {
        zzeo zzeoVar = new zzeo();
        zzeoVar.zzo(dataHolder.getStatusCode());
        int count = dataHolder.getCount();
        for (int i2 = 0; i2 < count; i2++) {
            int windowIndex = dataHolder.getWindowIndex(i2);
            zzeoVar.zzh(dataHolder.getString("requestId", i2, windowIndex), dataHolder.getInteger("outcome", i2, windowIndex));
        }
        return zzeoVar.zzca();
    }

    @VisibleForTesting
    public final Set<String> getRequestIds() {
        return this.zzmu.keySet();
    }

    @VisibleForTesting
    public final int getRequestOutcome(String str) {
        boolean zContainsKey = this.zzmu.containsKey(str);
        StringBuilder sb = new StringBuilder(a.e(46, str));
        sb.append("Request ");
        sb.append(str);
        sb.append(" was not part of the update operation!");
        Preconditions.checkArgument(zContainsKey, sb.toString());
        return this.zzmu.get(str).intValue();
    }
}
