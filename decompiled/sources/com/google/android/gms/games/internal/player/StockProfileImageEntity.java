package com.google.android.gms.games.internal.player;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@SafeParcelable.Class(creator = "StockProfileImageEntityCreator")
@SafeParcelable.Reserved({CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT})
public final class StockProfileImageEntity extends com.google.android.gms.games.internal.zzd implements StockProfileImage {
    public static final Parcelable.Creator<StockProfileImageEntity> CREATOR = new zzf();

    @SafeParcelable.Field(getter = "getImageUrl", id = 1)
    private final String zzmr;

    @SafeParcelable.Field(getter = "getImageUri", id = 2)
    private final Uri zzms;

    @SafeParcelable.Constructor
    public StockProfileImageEntity(@SafeParcelable.Param(id = 1) String str, @SafeParcelable.Param(id = 2) Uri uri) {
        this.zzmr = str;
        this.zzms = uri;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof StockProfileImage)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        StockProfileImage stockProfileImage = (StockProfileImage) obj;
        return Objects.equal(this.zzmr, stockProfileImage.getImageUrl()) && Objects.equal(this.zzms, stockProfileImage.zzbz());
    }

    @Override // com.google.android.gms.common.data.Freezable
    public final /* bridge */ /* synthetic */ StockProfileImage freeze() {
        return this;
    }

    @Override // com.google.android.gms.games.internal.player.StockProfileImage
    public final String getImageUrl() {
        return this.zzmr;
    }

    public final int hashCode() {
        return Objects.hashCode(this.zzmr, this.zzms);
    }

    @Override // com.google.android.gms.common.data.Freezable
    public final boolean isDataValid() {
        return true;
    }

    public final String toString() {
        return Objects.toStringHelper(this).add("ImageId", this.zzmr).add("ImageUri", this.zzms).toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, getImageUrl(), false);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzms, i2, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    @Override // com.google.android.gms.games.internal.player.StockProfileImage
    public final Uri zzbz() {
        return this.zzms;
    }
}
