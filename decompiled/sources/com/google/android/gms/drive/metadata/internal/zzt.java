package com.google.android.gms.drive.metadata.internal;

import android.os.Bundle;
import com.google.android.gms.common.data.DataHolder;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class zzt extends com.google.android.gms.drive.metadata.zza<String> {
    public zzt(String str, int i2) {
        super(str, i2);
    }

    @Override // com.google.android.gms.drive.metadata.zza
    protected final /* synthetic */ void zza(Bundle bundle, String str) {
        bundle.putString(getName(), str);
    }

    @Override // com.google.android.gms.drive.metadata.zza
    protected final /* synthetic */ String zzb(Bundle bundle) {
        return bundle.getString(getName());
    }

    @Override // com.google.android.gms.drive.metadata.zza
    protected final /* synthetic */ String zzc(DataHolder dataHolder, int i2, int i3) {
        return dataHolder.getString(getName(), i2, i3);
    }
}
