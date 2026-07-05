package com.google.android.gms.internal.drive;

import com.google.android.gms.drive.Metadata;
import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzaa extends Metadata {
    private final MetadataBundle zzdr;

    public zzaa(MetadataBundle metadataBundle) {
        this.zzdr = metadataBundle;
    }

    @Override // com.google.android.gms.common.data.Freezable
    public final /* synthetic */ Metadata freeze() {
        return new zzaa(this.zzdr.zzax());
    }

    @Override // com.google.android.gms.common.data.Freezable
    public final boolean isDataValid() {
        return this.zzdr != null;
    }

    public final String toString() {
        String strValueOf = String.valueOf(this.zzdr);
        StringBuilder sb = new StringBuilder(strValueOf.length() + 17);
        sb.append("Metadata [mImpl=");
        sb.append(strValueOf);
        sb.append("]");
        return sb.toString();
    }

    @Override // com.google.android.gms.drive.Metadata
    public final <T> T zza(MetadataField<T> metadataField) {
        return (T) this.zzdr.zza(metadataField);
    }
}
