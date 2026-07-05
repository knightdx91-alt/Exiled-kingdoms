package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.query.Filter;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@SafeParcelable.Class(creator = "FilterHolderCreator")
@SafeParcelable.Reserved({CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT})
public class FilterHolder extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<FilterHolder> CREATOR = new zzh();
    private final Filter zzba;

    @SafeParcelable.Field(id = 1)
    private final zzb<?> zzln;

    @SafeParcelable.Field(id = 2)
    private final zzd zzlo;

    @SafeParcelable.Field(id = 3)
    private final zzr zzlp;

    @SafeParcelable.Field(id = 4)
    private final zzv zzlq;

    @SafeParcelable.Field(id = 5)
    private final zzp<?> zzlr;

    @SafeParcelable.Field(id = 6)
    private final zzt zzls;

    @SafeParcelable.Field(id = 7)
    private final zzn zzlt;

    @SafeParcelable.Field(id = 8)
    private final zzl zzlu;

    @SafeParcelable.Field(id = 9)
    private final zzz zzlv;

    public FilterHolder(Filter filter) {
        Preconditions.checkNotNull(filter, "Null filter.");
        zzb<?> zzbVar = filter instanceof zzb ? (zzb) filter : null;
        this.zzln = zzbVar;
        zzd zzdVar = filter instanceof zzd ? (zzd) filter : null;
        this.zzlo = zzdVar;
        zzr zzrVar = filter instanceof zzr ? (zzr) filter : null;
        this.zzlp = zzrVar;
        zzv zzvVar = filter instanceof zzv ? (zzv) filter : null;
        this.zzlq = zzvVar;
        zzp<?> zzpVar = filter instanceof zzp ? (zzp) filter : null;
        this.zzlr = zzpVar;
        zzt zztVar = filter instanceof zzt ? (zzt) filter : null;
        this.zzls = zztVar;
        zzn zznVar = filter instanceof zzn ? (zzn) filter : null;
        this.zzlt = zznVar;
        zzl zzlVar = filter instanceof zzl ? (zzl) filter : null;
        this.zzlu = zzlVar;
        zzz zzzVar = filter instanceof zzz ? (zzz) filter : null;
        this.zzlv = zzzVar;
        if (zzbVar == null && zzdVar == null && zzrVar == null && zzvVar == null && zzpVar == null && zztVar == null && zznVar == null && zzlVar == null && zzzVar == null) {
            throw new IllegalArgumentException("Invalid filter type.");
        }
        this.zzba = filter;
    }

    public final Filter getFilter() {
        return this.zzba;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.zzln, i2, false);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzlo, i2, false);
        SafeParcelWriter.writeParcelable(parcel, 3, this.zzlp, i2, false);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zzlq, i2, false);
        SafeParcelWriter.writeParcelable(parcel, 5, this.zzlr, i2, false);
        SafeParcelWriter.writeParcelable(parcel, 6, this.zzls, i2, false);
        SafeParcelWriter.writeParcelable(parcel, 7, this.zzlt, i2, false);
        SafeParcelWriter.writeParcelable(parcel, 8, this.zzlu, i2, false);
        SafeParcelWriter.writeParcelable(parcel, 9, this.zzlv, i2, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    @SafeParcelable.Constructor
    FilterHolder(@SafeParcelable.Param(id = 1) zzb<?> zzbVar, @SafeParcelable.Param(id = 2) zzd zzdVar, @SafeParcelable.Param(id = 3) zzr zzrVar, @SafeParcelable.Param(id = 4) zzv zzvVar, @SafeParcelable.Param(id = 5) zzp<?> zzpVar, @SafeParcelable.Param(id = 6) zzt zztVar, @SafeParcelable.Param(id = 7) zzn<?> zznVar, @SafeParcelable.Param(id = 8) zzl zzlVar, @SafeParcelable.Param(id = 9) zzz zzzVar) {
        this.zzln = zzbVar;
        this.zzlo = zzdVar;
        this.zzlp = zzrVar;
        this.zzlq = zzvVar;
        this.zzlr = zzpVar;
        this.zzls = zztVar;
        this.zzlt = zznVar;
        this.zzlu = zzlVar;
        this.zzlv = zzzVar;
        if (zzbVar != null) {
            this.zzba = zzbVar;
            return;
        }
        if (zzdVar != null) {
            this.zzba = zzdVar;
            return;
        }
        if (zzrVar != null) {
            this.zzba = zzrVar;
            return;
        }
        if (zzvVar != null) {
            this.zzba = zzvVar;
            return;
        }
        if (zzpVar != null) {
            this.zzba = zzpVar;
            return;
        }
        if (zztVar != null) {
            this.zzba = zztVar;
            return;
        }
        if (zznVar != null) {
            this.zzba = zznVar;
        } else if (zzlVar != null) {
            this.zzba = zzlVar;
        } else {
            if (zzzVar == null) {
                throw new IllegalArgumentException("At least one filter must be set.");
            }
            this.zzba = zzzVar;
        }
    }
}
