package com.google.android.gms.drive.metadata.internal;

import android.os.Bundle;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.util.GmsVersion;
import java.util.Collection;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class zzb extends com.google.android.gms.drive.metadata.zza<Boolean> {
    public zzb(String str, int i2) {
        super(str, i2);
    }

    @Override // com.google.android.gms.drive.metadata.zza
    protected final /* synthetic */ void zza(Bundle bundle, Boolean bool) {
        bundle.putBoolean(getName(), bool.booleanValue());
    }

    @Override // com.google.android.gms.drive.metadata.zza
    protected final /* synthetic */ Boolean zzb(Bundle bundle) {
        return Boolean.valueOf(bundle.getBoolean(getName()));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.drive.metadata.zza
    /* JADX INFO: renamed from: zze, reason: merged with bridge method [inline-methods] */
    public Boolean zzc(DataHolder dataHolder, int i2, int i3) {
        return Boolean.valueOf(dataHolder.getBoolean(getName(), i2, i3));
    }

    public zzb(String str, Collection<String> collection, Collection<String> collection2, int i2) {
        super(str, collection, collection2, GmsVersion.VERSION_ORLA);
    }
}
