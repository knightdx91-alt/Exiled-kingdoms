package com.google.android.gms.drive.metadata.internal;

import android.os.Bundle;
import com.google.android.gms.common.data.DataHolder;
import java.util.Date;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class zze extends com.google.android.gms.drive.metadata.zzd<Date> {
    public zze(String str, int i2) {
        super(str, i2);
    }

    @Override // com.google.android.gms.drive.metadata.zza
    protected final /* synthetic */ void zza(Bundle bundle, Object obj) {
        bundle.putLong(getName(), ((Date) obj).getTime());
    }

    @Override // com.google.android.gms.drive.metadata.zza
    protected final /* synthetic */ Object zzb(Bundle bundle) {
        return new Date(bundle.getLong(getName()));
    }

    @Override // com.google.android.gms.drive.metadata.zza
    protected final /* synthetic */ Object zzc(DataHolder dataHolder, int i2, int i3) {
        return new Date(dataHolder.getLong(getName(), i2, i3));
    }
}
