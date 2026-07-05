package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@SafeParcelable.Class(creator = "OperatorCreator")
@SafeParcelable.Reserved({CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT})
public final class zzx extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzx> CREATOR = new zzy();
    public static final zzx zzma = new zzx("=");
    public static final zzx zzmb = new zzx("<");
    public static final zzx zzmc = new zzx("<=");
    public static final zzx zzmd = new zzx(">");
    public static final zzx zzme = new zzx(">=");
    public static final zzx zzmf = new zzx("and");
    public static final zzx zzmg = new zzx("or");
    private static final zzx zzmh = new zzx("not");
    public static final zzx zzmi = new zzx("contains");

    @SafeParcelable.Field(id = 1)
    private final String tag;

    @SafeParcelable.Constructor
    zzx(@SafeParcelable.Param(id = 1) String str) {
        this.tag = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || zzx.class != obj.getClass()) {
            return false;
        }
        String str = this.tag;
        String str2 = ((zzx) obj).tag;
        if (str == null) {
            if (str2 != null) {
                return false;
            }
        } else if (!str.equals(str2)) {
            return false;
        }
        return true;
    }

    public final String getTag() {
        return this.tag;
    }

    public final int hashCode() {
        String str = this.tag;
        return (str == null ? 0 : str.hashCode()) + 31;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.tag, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
