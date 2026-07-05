package com.google.android.gms.games.snapshot;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzc implements Parcelable.Creator<SnapshotEntity> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ SnapshotEntity createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        SnapshotMetadataEntity snapshotMetadataEntity = null;
        zza zzaVar = null;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(header);
            if (fieldId == 1) {
                snapshotMetadataEntity = (SnapshotMetadataEntity) SafeParcelReader.createParcelable(parcel, header, SnapshotMetadataEntity.CREATOR);
            } else if (fieldId != 3) {
                SafeParcelReader.skipUnknownField(parcel, header);
            } else {
                zzaVar = (zza) SafeParcelReader.createParcelable(parcel, header, zza.CREATOR);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new SnapshotEntity(snapshotMetadataEntity, zzaVar);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ SnapshotEntity[] newArray(int i2) {
        return new SnapshotEntity[i2];
    }
}
