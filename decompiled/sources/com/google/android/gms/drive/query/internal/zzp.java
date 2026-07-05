package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.metadata.SearchableCollectionMetadataField;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import java.util.Collection;
import java.util.Collections;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@SafeParcelable.Class(creator = "InFilterCreator")
@SafeParcelable.Reserved({CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT})
public final class zzp<T> extends zza {
    public static final zzq CREATOR = new zzq();

    @SafeParcelable.Field(id = 1)
    private final MetadataBundle zzlk;
    private final com.google.android.gms.drive.metadata.zzb<T> zzlx;

    public zzp(SearchableCollectionMetadataField<T> searchableCollectionMetadataField, T t2) {
        this(MetadataBundle.zza(searchableCollectionMetadataField, Collections.singleton(t2)));
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.zzlk, i2, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    @Override // com.google.android.gms.drive.query.Filter
    public final <F> F zza(zzj<F> zzjVar) {
        com.google.android.gms.drive.metadata.zzb<T> zzbVar = this.zzlx;
        return zzjVar.zza(zzbVar, ((Collection) this.zzlk.zza(zzbVar)).iterator().next());
    }

    @SafeParcelable.Constructor
    zzp(@SafeParcelable.Param(id = 1) MetadataBundle metadataBundle) {
        this.zzlk = metadataBundle;
        this.zzlx = (com.google.android.gms.drive.metadata.zzb) zzi.zza(metadataBundle);
    }
}
