package com.google.android.gms.games.internal.player;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@SafeParcelable.Class(creator = "MostRecentGameInfoEntityCreator")
@SafeParcelable.Reserved({CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT})
public final class zzb extends com.google.android.gms.games.internal.zzd implements zza {
    public static final Parcelable.Creator<zzb> CREATOR = new zzc();

    @SafeParcelable.Field(getter = "getGameId", id = 1)
    private final String zzlf;

    @SafeParcelable.Field(getter = "getGameName", id = 2)
    private final String zzlg;

    @SafeParcelable.Field(getter = "getActivityTimestampMillis", id = 3)
    private final long zzlh;

    @SafeParcelable.Field(getter = "getGameIconImageUri", id = 4)
    private final Uri zzli;

    @SafeParcelable.Field(getter = "getGameHiResImageUri", id = 5)
    private final Uri zzlj;

    @SafeParcelable.Field(getter = "getGameFeaturedImageUri", id = 6)
    private final Uri zzlk;

    public zzb(zza zzaVar) {
        this.zzlf = zzaVar.zzbt();
        this.zzlg = zzaVar.zzbu();
        this.zzlh = zzaVar.zzbv();
        this.zzli = zzaVar.zzbw();
        this.zzlj = zzaVar.zzbx();
        this.zzlk = zzaVar.zzby();
    }

    static int zza(zza zzaVar) {
        return Objects.hashCode(zzaVar.zzbt(), zzaVar.zzbu(), Long.valueOf(zzaVar.zzbv()), zzaVar.zzbw(), zzaVar.zzbx(), zzaVar.zzby());
    }

    static String zzb(zza zzaVar) {
        return Objects.toStringHelper(zzaVar).add("GameId", zzaVar.zzbt()).add("GameName", zzaVar.zzbu()).add("ActivityTimestampMillis", Long.valueOf(zzaVar.zzbv())).add("GameIconUri", zzaVar.zzbw()).add("GameHiResUri", zzaVar.zzbx()).add("GameFeaturedUri", zzaVar.zzby()).toString();
    }

    public final boolean equals(Object obj) {
        return zza(this, obj);
    }

    @Override // com.google.android.gms.common.data.Freezable
    public final /* bridge */ /* synthetic */ zza freeze() {
        return this;
    }

    public final int hashCode() {
        return zza(this);
    }

    @Override // com.google.android.gms.common.data.Freezable
    public final boolean isDataValid() {
        return true;
    }

    public final String toString() {
        return zzb(this);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zzlf, false);
        SafeParcelWriter.writeString(parcel, 2, this.zzlg, false);
        SafeParcelWriter.writeLong(parcel, 3, this.zzlh);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zzli, i2, false);
        SafeParcelWriter.writeParcelable(parcel, 5, this.zzlj, i2, false);
        SafeParcelWriter.writeParcelable(parcel, 6, this.zzlk, i2, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    @Override // com.google.android.gms.games.internal.player.zza
    public final String zzbt() {
        return this.zzlf;
    }

    @Override // com.google.android.gms.games.internal.player.zza
    public final String zzbu() {
        return this.zzlg;
    }

    @Override // com.google.android.gms.games.internal.player.zza
    public final long zzbv() {
        return this.zzlh;
    }

    @Override // com.google.android.gms.games.internal.player.zza
    public final Uri zzbw() {
        return this.zzli;
    }

    @Override // com.google.android.gms.games.internal.player.zza
    public final Uri zzbx() {
        return this.zzlj;
    }

    @Override // com.google.android.gms.games.internal.player.zza
    public final Uri zzby() {
        return this.zzlk;
    }

    @SafeParcelable.Constructor
    zzb(@SafeParcelable.Param(id = 1) String str, @SafeParcelable.Param(id = 2) String str2, @SafeParcelable.Param(id = 3) long j2, @SafeParcelable.Param(id = 4) Uri uri, @SafeParcelable.Param(id = 5) Uri uri2, @SafeParcelable.Param(id = 6) Uri uri3) {
        this.zzlf = str;
        this.zzlg = str2;
        this.zzlh = j2;
        this.zzli = uri;
        this.zzlj = uri2;
        this.zzlk = uri3;
    }

    static boolean zza(zza zzaVar, Object obj) {
        if (!(obj instanceof zza)) {
            return false;
        }
        if (zzaVar == obj) {
            return true;
        }
        zza zzaVar2 = (zza) obj;
        return Objects.equal(zzaVar2.zzbt(), zzaVar.zzbt()) && Objects.equal(zzaVar2.zzbu(), zzaVar.zzbu()) && Objects.equal(Long.valueOf(zzaVar2.zzbv()), Long.valueOf(zzaVar.zzbv())) && Objects.equal(zzaVar2.zzbw(), zzaVar.zzbw()) && Objects.equal(zzaVar2.zzbx(), zzaVar.zzbx()) && Objects.equal(zzaVar2.zzby(), zzaVar.zzby());
    }
}
