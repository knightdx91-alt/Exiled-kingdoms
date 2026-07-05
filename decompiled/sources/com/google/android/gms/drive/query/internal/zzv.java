package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.query.Filter;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@SafeParcelable.Class(creator = "NotFilterCreator")
@SafeParcelable.Reserved({CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT})
public final class zzv extends zza {
    public static final Parcelable.Creator<zzv> CREATOR = new zzw();

    @SafeParcelable.Field(id = 1)
    private final FilterHolder zzlz;

    public zzv(Filter filter) {
        this(new FilterHolder(filter));
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.zzlz, i2, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.drive.query.Filter
    public final <T> T zza(zzj<T> zzjVar) {
        return (T) zzjVar.zza(this.zzlz.getFilter().zza(zzjVar));
    }

    @SafeParcelable.Constructor
    zzv(@SafeParcelable.Param(id = 1) FilterHolder filterHolder) {
        this.zzlz = filterHolder;
    }
}
