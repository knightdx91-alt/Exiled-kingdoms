package com.google.android.gms.drive.metadata;

import com.google.android.gms.common.data.DataHolder;
import java.util.Collection;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public abstract class zzb<T> extends zza<Collection<T>> {
    protected zzb(String str, Collection<String> collection, Collection<String> collection2, int i2) {
        super(str, collection, collection2, i2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.drive.metadata.zza
    /* JADX INFO: renamed from: zzd, reason: merged with bridge method [inline-methods] */
    public Collection<T> zzc(DataHolder dataHolder, int i2, int i3) {
        throw new UnsupportedOperationException("Cannot read collections from a dataHolder.");
    }
}
