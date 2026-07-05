package com.google.android.gms.games.snapshot;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.IOUtils;
import com.google.android.gms.drive.Contents;
import com.google.android.gms.games.internal.zzh;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@SafeParcelable.Class(creator = "SnapshotContentsEntityCreator")
@SafeParcelable.Reserved({CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT})
public final class zza extends com.google.android.gms.games.internal.zzd implements SnapshotContents {

    @SafeParcelable.Field(getter = "getContents", id = 1)
    private Contents zzqm;
    private static final Object zzql = new Object();
    public static final Parcelable.Creator<zza> CREATOR = new zzb();

    @SafeParcelable.Constructor
    public zza(@SafeParcelable.Param(id = 1) Contents contents) {
        this.zzqm = contents;
    }

    private final boolean zza(int i2, byte[] bArr, int i3, int i4, boolean z2) {
        Preconditions.checkState(!isClosed(), "Must provide a previously opened SnapshotContents");
        synchronized (zzql) {
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(this.zzqm.getParcelFileDescriptor().getFileDescriptor());
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
                try {
                    FileChannel channel = fileOutputStream.getChannel();
                    channel.position(i2);
                    bufferedOutputStream.write(bArr, i3, i4);
                    if (z2) {
                        channel.truncate(bArr.length);
                    }
                    bufferedOutputStream.flush();
                } catch (IOException e2) {
                    zzh.i("SnapshotContentsEntity", "Failed to write snapshot data", e2);
                    return false;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return true;
    }

    @Override // com.google.android.gms.games.snapshot.SnapshotContents
    public final void close() {
        this.zzqm = null;
    }

    @Override // com.google.android.gms.games.snapshot.SnapshotContents
    public final ParcelFileDescriptor getParcelFileDescriptor() {
        Preconditions.checkState(!isClosed(), "Cannot mutate closed contents!");
        return this.zzqm.getParcelFileDescriptor();
    }

    @Override // com.google.android.gms.games.snapshot.SnapshotContents
    public final boolean isClosed() {
        return this.zzqm == null;
    }

    @Override // com.google.android.gms.games.snapshot.SnapshotContents
    public final boolean modifyBytes(int i2, byte[] bArr, int i3, int i4) {
        return zza(i2, bArr, i3, bArr.length, false);
    }

    @Override // com.google.android.gms.games.snapshot.SnapshotContents
    public final byte[] readFully() {
        byte[] inputStreamFully;
        Preconditions.checkState(!isClosed(), "Must provide a previously opened Snapshot");
        synchronized (zzql) {
            try {
                FileInputStream fileInputStream = new FileInputStream(this.zzqm.getParcelFileDescriptor().getFileDescriptor());
                BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
                try {
                    fileInputStream.getChannel().position(0L);
                    inputStreamFully = IOUtils.readInputStreamFully(bufferedInputStream, false);
                    fileInputStream.getChannel().position(0L);
                } catch (IOException e2) {
                    zzh.w("SnapshotContentsEntity", "Failed to read snapshot data", e2);
                    throw e2;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return inputStreamFully;
    }

    @Override // com.google.android.gms.games.snapshot.SnapshotContents
    public final boolean writeBytes(byte[] bArr) {
        return zza(0, bArr, 0, bArr.length, true);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.zzqm, i2, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    @Override // com.google.android.gms.games.snapshot.SnapshotContents
    public final Contents zzcl() {
        return this.zzqm;
    }
}
