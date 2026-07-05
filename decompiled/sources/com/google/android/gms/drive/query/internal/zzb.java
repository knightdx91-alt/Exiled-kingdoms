package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.drive.metadata.SearchableMetadataField;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@SafeParcelable.Class(creator = "ComparisonFilterCreator")
@SafeParcelable.Reserved({CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT})
public final class zzb<T> extends zza {
    public static final zzc CREATOR = new zzc();

    @SafeParcelable.Field(id = 1)
    private final zzx zzlj;

    @SafeParcelable.Field(id = 2)
    private final MetadataBundle zzlk;
    private final MetadataField<T> zzll;

    public zzb(zzx zzxVar, SearchableMetadataField<T> searchableMetadataField, T t2) {
        this(zzxVar, MetadataBundle.zza(searchableMetadataField, t2));
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.zzlj, i2, false);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzlk, i2, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    @Override // com.google.android.gms.drive.query.Filter
    public final <F> F zza(zzj<F> zzjVar) {
        zzx zzxVar = this.zzlj;
        MetadataField<T> metadataField = this.zzll;
        return zzjVar.zza(zzxVar, metadataField, this.zzlk.zza(metadataField));
    }

    @SafeParcelable.Constructor
    zzb(@SafeParcelable.Param(id = 1) zzx zzxVar, @SafeParcelable.Param(id = 2) MetadataBundle metadataBundle) {
        this.zzlj = zzxVar;
        this.zzlk = metadataBundle;
        this.zzll = (MetadataField<T>) zzi.zza(metadataBundle);
    }
}
