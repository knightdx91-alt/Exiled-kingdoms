package com.google.android.gms.drive.query.internal;

import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import java.util.Set;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzi {
    static MetadataField<?> zza(MetadataBundle metadataBundle) {
        Set<MetadataField<?>> setZzay = metadataBundle.zzay();
        if (setZzay.size() == 1) {
            return setZzay.iterator().next();
        }
        throw new IllegalArgumentException("bundle should have exactly 1 populated field");
    }
}
