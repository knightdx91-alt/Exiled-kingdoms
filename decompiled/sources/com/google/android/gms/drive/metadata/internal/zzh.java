package com.google.android.gms.drive.metadata.internal;

import android.os.Bundle;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.util.GmsVersion;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzh extends com.google.android.gms.drive.metadata.zza<Integer> {
    public zzh(String str, int i2) {
        super(str, GmsVersion.VERSION_JARLSBERG);
    }

    @Override // com.google.android.gms.drive.metadata.zza
    protected final /* synthetic */ void zza(Bundle bundle, Integer num) {
        bundle.putInt(getName(), num.intValue());
    }

    @Override // com.google.android.gms.drive.metadata.zza
    protected final /* synthetic */ Integer zzb(Bundle bundle) {
        return Integer.valueOf(bundle.getInt(getName()));
    }

    @Override // com.google.android.gms.drive.metadata.zza
    protected final /* synthetic */ Integer zzc(DataHolder dataHolder, int i2, int i3) {
        return Integer.valueOf(dataHolder.getInteger(getName(), i2, i3));
    }
}
