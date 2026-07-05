package com.google.android.gms.drive.metadata;

import android.os.Bundle;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public interface MetadataField<T> {
    String getName();

    T zza(Bundle bundle);

    T zza(DataHolder dataHolder, int i2, int i3);

    void zza(DataHolder dataHolder, MetadataBundle metadataBundle, int i2, int i3);

    void zza(T t2, Bundle bundle);
}
