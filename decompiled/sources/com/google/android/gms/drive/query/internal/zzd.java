package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.drive.metadata.SearchableMetadataField;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@SafeParcelable.Class(creator = "FieldOnlyFilterCreator")
@SafeParcelable.Reserved({CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT})
public final class zzd extends zza {
    public static final Parcelable.Creator<zzd> CREATOR = new zze();

    @SafeParcelable.Field(id = 1)
    private final MetadataBundle zzlk;
    private final MetadataField<?> zzll;

    public zzd(SearchableMetadataField<?> searchableMetadataField) {
        this(MetadataBundle.zza(searchableMetadataField, null));
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.zzlk, i2, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    @Override // com.google.android.gms.drive.query.Filter
    public final <T> T zza(zzj<T> zzjVar) {
        return zzjVar.zze(this.zzll);
    }

    @SafeParcelable.Constructor
    zzd(@SafeParcelable.Param(id = 1) MetadataBundle metadataBundle) {
        this.zzlk = metadataBundle;
        this.zzll = zzi.zza(metadataBundle);
    }
}
