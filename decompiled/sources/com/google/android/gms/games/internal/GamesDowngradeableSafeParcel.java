package com.google.android.gms.games.internal;

import com.google.android.gms.common.internal.DowngradeableSafeParcel;
import com.google.android.gms.common.util.GmsVersion;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public abstract class GamesDowngradeableSafeParcel extends DowngradeableSafeParcel {
    protected static boolean zzb(Integer num) {
        if (num == null) {
            return false;
        }
        return GmsVersion.isAtLeastFenacho(num.intValue());
    }

    @Override // com.google.android.gms.common.internal.DowngradeableSafeParcel
    public boolean prepareForClientVersion(int i2) {
        setShouldDowngrade(!zzb(Integer.valueOf(i2)));
        return true;
    }
}
