package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.drive.metadata.SearchableMetadataField;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@SafeParcelable.Class(creator = "HasFilterCreator")
@SafeParcelable.Reserved({CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT})
public final class zzn<T> extends zza {
    public static final zzo CREATOR = new zzo();

    @SafeParcelable.Field(id = 1)
    private final MetadataBundle zzlk;
    private final MetadataField<T> zzll;

    public zzn(SearchableMetadataField<T> searchableMetadataField, T t2) {
        this(MetadataBundle.zza(searchableMetadataField, t2));
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.zzlk, i2, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    @Override // com.google.android.gms.drive.query.Filter
    public final <F> F zza(zzj<F> zzjVar) {
        MetadataField<T> metadataField = this.zzll;
        return zzjVar.zzc(metadataField, this.zzlk.zza(metadataField));
    }

    @SafeParcelable.Constructor
    zzn(@SafeParcelable.Param(id = 1) MetadataBundle metadataBundle) {
        this.zzlk = metadataBundle;
        this.zzll = (MetadataField<T>) zzi.zza(metadataBundle);
    }
}
