package com.google.android.gms.games.internal.player;

import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.common.data.DataBufferRef;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class StockProfileImageRef extends DataBufferRef implements StockProfileImage {
    @Override // android.os.Parcelable
    public int describeContents() {
        throw new NoSuchMethodError();
    }

    @Override // com.google.android.gms.common.data.Freezable
    public /* synthetic */ StockProfileImage freeze() {
        throw new NoSuchMethodError();
    }

    @Override // com.google.android.gms.games.internal.player.StockProfileImage
    public String getImageUrl() {
        return getString("image_url");
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        throw new NoSuchMethodError();
    }

    @Override // com.google.android.gms.games.internal.player.StockProfileImage
    public final Uri zzbz() {
        throw new NoSuchMethodError();
    }
}
