package com.google.android.gms.games.snapshot;

import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import com.google.android.gms.drive.Contents;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public interface SnapshotContents extends Parcelable {
    void close();

    ParcelFileDescriptor getParcelFileDescriptor();

    boolean isClosed();

    boolean modifyBytes(int i2, byte[] bArr, int i3, int i4);

    byte[] readFully();

    boolean writeBytes(byte[] bArr);

    Contents zzcl();
}
