package com.google.android.gms.internal.drive;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.util.GmsVersion;
import com.google.android.gms.drive.metadata.SearchableMetadataField;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzhz extends com.google.android.gms.drive.metadata.internal.zzb implements SearchableMetadataField<Boolean> {
    public zzhz(String str, int i2) {
        super(str, GmsVersion.VERSION_HALLOUMI);
    }

    @Override // com.google.android.gms.drive.metadata.internal.zzb, com.google.android.gms.drive.metadata.zza
    protected final /* synthetic */ Boolean zzc(DataHolder dataHolder, int i2, int i3) {
        return zzc(dataHolder, i2, i3);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.drive.metadata.internal.zzb
    /* JADX INFO: renamed from: zze */
    public final Boolean zzc(DataHolder dataHolder, int i2, int i3) {
        return Boolean.valueOf(dataHolder.getInteger(getName(), i2, i3) != 0);
    }
}
