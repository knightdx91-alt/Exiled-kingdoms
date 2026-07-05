package com.google.android.gms.drive.events;

import a.a;
import android.os.IBinder;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import android.os.RemoteException;
import android.text.TextUtils;
import com.google.android.gms.common.data.BitmapTeleporter;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.IOUtils;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.MetadataChangeSet;
import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import com.google.android.gms.internal.drive.zzev;
import com.google.android.gms.internal.drive.zzhp;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@SafeParcelable.Class(creator = "CompletionEventCreator")
@SafeParcelable.Reserved({1})
public final class CompletionEvent extends AbstractSafeParcelable implements ResourceEvent {
    public static final int STATUS_CANCELED = 3;
    public static final int STATUS_CONFLICT = 2;
    public static final int STATUS_FAILURE = 1;
    public static final int STATUS_SUCCESS = 0;

    @SafeParcelable.Field(id = 8)
    private final int status;

    @SafeParcelable.Field(id = 3)
    private final String zzby;

    @SafeParcelable.Field(id = 4)
    private final ParcelFileDescriptor zzbz;

    @SafeParcelable.Field(id = 5)
    private final ParcelFileDescriptor zzca;

    @SafeParcelable.Field(id = 6)
    private final MetadataBundle zzcb;

    @SafeParcelable.Field(id = 7)
    private final List<String> zzcc;

    @SafeParcelable.Field(id = 9)
    private final IBinder zzcd;
    private boolean zzce = false;
    private boolean zzcf = false;
    private boolean zzcg = false;

    @SafeParcelable.Field(id = 2)
    private final DriveId zzk;
    private static final GmsLogger zzbx = new GmsLogger("CompletionEvent", "");
    public static final Parcelable.Creator<CompletionEvent> CREATOR = new zzg();

    @SafeParcelable.Constructor
    CompletionEvent(@SafeParcelable.Param(id = 2) DriveId driveId, @SafeParcelable.Param(id = 3) String str, @SafeParcelable.Param(id = 4) ParcelFileDescriptor parcelFileDescriptor, @SafeParcelable.Param(id = 5) ParcelFileDescriptor parcelFileDescriptor2, @SafeParcelable.Param(id = 6) MetadataBundle metadataBundle, @SafeParcelable.Param(id = 7) List<String> list, @SafeParcelable.Param(id = 8) int i2, @SafeParcelable.Param(id = 9) IBinder iBinder) {
        this.zzk = driveId;
        this.zzby = str;
        this.zzbz = parcelFileDescriptor;
        this.zzca = parcelFileDescriptor2;
        this.zzcb = metadataBundle;
        this.zzcc = list;
        this.status = i2;
        this.zzcd = iBinder;
    }

    private final void zza(boolean z2) {
        zzu();
        this.zzcg = true;
        IOUtils.closeQuietly(this.zzbz);
        IOUtils.closeQuietly(this.zzca);
        MetadataBundle metadataBundle = this.zzcb;
        if (metadataBundle != null) {
            MetadataField<BitmapTeleporter> metadataField = zzhp.zzka;
            if (metadataBundle.zzd(metadataField)) {
                ((BitmapTeleporter) this.zzcb.zza(metadataField)).release();
            }
        }
        IBinder iBinder = this.zzcd;
        if (iBinder == null) {
            zzbx.efmt("CompletionEvent", "No callback on %s", z2 ? "snooze" : "dismiss");
            return;
        }
        try {
            zzev.zza(iBinder).zza(z2);
        } catch (RemoteException e2) {
            zzbx.e("CompletionEvent", "RemoteException on ".concat(z2 ? "snooze" : "dismiss"), e2);
        }
    }

    private final void zzu() {
        if (this.zzcg) {
            throw new IllegalStateException("Event has already been dismissed or snoozed.");
        }
    }

    public final void dismiss() {
        zza(false);
    }

    public final String getAccountName() {
        zzu();
        return this.zzby;
    }

    public final InputStream getBaseContentsInputStream() {
        zzu();
        if (this.zzbz == null) {
            return null;
        }
        if (this.zzce) {
            throw new IllegalStateException("getBaseInputStream() can only be called once per CompletionEvent instance.");
        }
        this.zzce = true;
        return new FileInputStream(this.zzbz.getFileDescriptor());
    }

    @Override // com.google.android.gms.drive.events.ResourceEvent
    public final DriveId getDriveId() {
        zzu();
        return this.zzk;
    }

    public final InputStream getModifiedContentsInputStream() {
        zzu();
        if (this.zzca == null) {
            return null;
        }
        if (this.zzcf) {
            throw new IllegalStateException("getModifiedInputStream() can only be called once per CompletionEvent instance.");
        }
        this.zzcf = true;
        return new FileInputStream(this.zzca.getFileDescriptor());
    }

    public final MetadataChangeSet getModifiedMetadataChangeSet() {
        zzu();
        if (this.zzcb != null) {
            return new MetadataChangeSet(this.zzcb);
        }
        return null;
    }

    public final int getStatus() {
        zzu();
        return this.status;
    }

    public final List<String> getTrackingTags() {
        zzu();
        return new ArrayList(this.zzcc);
    }

    @Override // com.google.android.gms.drive.events.DriveEvent
    public final int getType() {
        return 2;
    }

    public final void snooze() {
        zza(true);
    }

    public final String toString() {
        String string;
        List<String> list = this.zzcc;
        if (list == null) {
            string = "<null>";
        } else {
            String strJoin = TextUtils.join("','", list);
            StringBuilder sb = new StringBuilder(a.e(2, strJoin));
            sb.append("'");
            sb.append(strJoin);
            sb.append("'");
            string = sb.toString();
        }
        return String.format(Locale.US, "CompletionEvent [id=%s, status=%s, trackingTag=%s]", this.zzk, Integer.valueOf(this.status), string);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int i3 = i2 | 1;
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzk, i3, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzby, false);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zzbz, i3, false);
        SafeParcelWriter.writeParcelable(parcel, 5, this.zzca, i3, false);
        SafeParcelWriter.writeParcelable(parcel, 6, this.zzcb, i3, false);
        SafeParcelWriter.writeStringList(parcel, 7, this.zzcc, false);
        SafeParcelWriter.writeInt(parcel, 8, this.status);
        SafeParcelWriter.writeIBinder(parcel, 9, this.zzcd, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
