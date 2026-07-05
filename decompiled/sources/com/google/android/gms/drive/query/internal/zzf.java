package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Locale;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@SafeParcelable.Class(creator = "FieldWithSortOrderCreator")
@SafeParcelable.Reserved({CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT})
public final class zzf extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzf> CREATOR = new zzg();

    @SafeParcelable.Field(id = 1)
    private final String fieldName;

    @SafeParcelable.Field(id = 2)
    private final boolean zzlm;

    @SafeParcelable.Constructor
    public zzf(@SafeParcelable.Param(id = 1) String str, @SafeParcelable.Param(id = 2) boolean z2) {
        this.fieldName = str;
        this.zzlm = z2;
    }

    public final String toString() {
        Locale locale = Locale.US;
        return "FieldWithSortOrder[" + this.fieldName + " " + (this.zzlm ? "ASC" : "DESC") + "]";
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.fieldName, false);
        SafeParcelWriter.writeBoolean(parcel, 2, this.zzlm);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
