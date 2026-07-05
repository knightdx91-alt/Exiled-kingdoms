package com.google.android.gms.games.snapshot;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.google.android.gms.common.data.BitmapTeleporter;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@SafeParcelable.Class(creator = "SnapshotMetadataChangeCreator")
@SafeParcelable.Reserved({CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT})
public final class zze extends com.google.android.gms.games.internal.zzd implements SnapshotMetadataChange {
    public static final Parcelable.Creator<zze> CREATOR = new zzd();

    @SafeParcelable.Field(getter = "getDescription", id = 1)
    private final String description;

    @SafeParcelable.Field(getter = "getProgressValue", id = 6)
    private final Long zzqq;

    @SafeParcelable.Field(getter = "getCoverImageUri", id = 4)
    private final Uri zzqs;

    @SafeParcelable.Field(getter = "getPlayedTimeMillis", id = 2)
    private final Long zzqt;

    @SafeParcelable.Field(getter = "getCoverImageTeleporter", id = 5)
    private BitmapTeleporter zzqu;

    zze() {
        this(null, null, null, null, null);
    }

    @Override // com.google.android.gms.games.snapshot.SnapshotMetadataChange
    public final Bitmap getCoverImage() {
        BitmapTeleporter bitmapTeleporter = this.zzqu;
        if (bitmapTeleporter == null) {
            return null;
        }
        return bitmapTeleporter.get();
    }

    @Override // com.google.android.gms.games.snapshot.SnapshotMetadataChange
    public final String getDescription() {
        return this.description;
    }

    @Override // com.google.android.gms.games.snapshot.SnapshotMetadataChange
    public final Long getPlayedTimeMillis() {
        return this.zzqt;
    }

    @Override // com.google.android.gms.games.snapshot.SnapshotMetadataChange
    public final Long getProgressValue() {
        return this.zzqq;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, getDescription(), false);
        SafeParcelWriter.writeLongObject(parcel, 2, getPlayedTimeMillis(), false);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zzqs, i2, false);
        SafeParcelWriter.writeParcelable(parcel, 5, this.zzqu, i2, false);
        SafeParcelWriter.writeLongObject(parcel, 6, getProgressValue(), false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    @Override // com.google.android.gms.games.snapshot.SnapshotMetadataChange
    public final BitmapTeleporter zzcm() {
        return this.zzqu;
    }

    @SafeParcelable.Constructor
    zze(@SafeParcelable.Param(id = 1) String str, @SafeParcelable.Param(id = 2) Long l2, @SafeParcelable.Param(id = 5) BitmapTeleporter bitmapTeleporter, @SafeParcelable.Param(id = 4) Uri uri, @SafeParcelable.Param(id = 6) Long l3) {
        this.description = str;
        this.zzqt = l2;
        this.zzqu = bitmapTeleporter;
        this.zzqs = uri;
        this.zzqq = l3;
        if (bitmapTeleporter != null) {
            Preconditions.checkState(uri == null, "Cannot set both a URI and an image");
        } else if (uri != null) {
            Preconditions.checkState(bitmapTeleporter == null, "Cannot set both a URI and an image");
        }
    }
}
