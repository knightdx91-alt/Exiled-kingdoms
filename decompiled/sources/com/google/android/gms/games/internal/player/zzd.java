package com.google.android.gms.games.internal.player;

import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.common.data.DataBufferRef;
import com.google.android.gms.common.data.DataHolder;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzd extends DataBufferRef implements zza {
    private final zze zzcw;

    public zzd(DataHolder dataHolder, int i2, zze zzeVar) {
        super(dataHolder, i2);
        this.zzcw = zzeVar;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // com.google.android.gms.common.data.DataBufferRef
    public final boolean equals(Object obj) {
        return zzb.zza(this, obj);
    }

    @Override // com.google.android.gms.common.data.Freezable
    public final /* synthetic */ zza freeze() {
        return new zzb(this);
    }

    @Override // com.google.android.gms.common.data.DataBufferRef
    public final int hashCode() {
        return zzb.zza(this);
    }

    public final String toString() {
        return zzb.zzb(this);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        ((zzb) ((zza) freeze())).writeToParcel(parcel, i2);
    }

    @Override // com.google.android.gms.games.internal.player.zza
    public final String zzbt() {
        return getString(this.zzcw.zzmd);
    }

    @Override // com.google.android.gms.games.internal.player.zza
    public final String zzbu() {
        return getString(this.zzcw.zzme);
    }

    @Override // com.google.android.gms.games.internal.player.zza
    public final long zzbv() {
        return getLong(this.zzcw.zzmf);
    }

    @Override // com.google.android.gms.games.internal.player.zza
    public final Uri zzbw() {
        return parseUri(this.zzcw.zzmg);
    }

    @Override // com.google.android.gms.games.internal.player.zza
    public final Uri zzbx() {
        return parseUri(this.zzcw.zzmh);
    }

    @Override // com.google.android.gms.games.internal.player.zza
    public final Uri zzby() {
        return parseUri(this.zzcw.zzmi);
    }
}
