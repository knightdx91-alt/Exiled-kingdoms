package com.google.android.gms.drive.events;

import android.os.IBinder;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import java.util.ArrayList;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzg implements Parcelable.Creator<CompletionEvent> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ CompletionEvent createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        DriveId driveId = null;
        String strCreateString = null;
        ParcelFileDescriptor parcelFileDescriptor = null;
        ParcelFileDescriptor parcelFileDescriptor2 = null;
        MetadataBundle metadataBundle = null;
        ArrayList<String> arrayListCreateStringList = null;
        IBinder iBinder = null;
        int i2 = 0;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case 2:
                    driveId = (DriveId) SafeParcelReader.createParcelable(parcel, header, DriveId.CREATOR);
                    break;
                case 3:
                    strCreateString = SafeParcelReader.createString(parcel, header);
                    break;
                case 4:
                    parcelFileDescriptor = (ParcelFileDescriptor) SafeParcelReader.createParcelable(parcel, header, ParcelFileDescriptor.CREATOR);
                    break;
                case 5:
                    parcelFileDescriptor2 = (ParcelFileDescriptor) SafeParcelReader.createParcelable(parcel, header, ParcelFileDescriptor.CREATOR);
                    break;
                case 6:
                    metadataBundle = (MetadataBundle) SafeParcelReader.createParcelable(parcel, header, MetadataBundle.CREATOR);
                    break;
                case 7:
                    arrayListCreateStringList = SafeParcelReader.createStringList(parcel, header);
                    break;
                case 8:
                    i2 = SafeParcelReader.readInt(parcel, header);
                    break;
                case 9:
                    iBinder = SafeParcelReader.readIBinder(parcel, header);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new CompletionEvent(driveId, strCreateString, parcelFileDescriptor, parcelFileDescriptor2, metadataBundle, arrayListCreateStringList, i2, iBinder);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ CompletionEvent[] newArray(int i2) {
        return new CompletionEvent[i2];
    }
}
