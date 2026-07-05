package com.google.android.gms.internal.drive;

import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.drive.DriveFile;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzbp implements DriveFile.DownloadProgressListener {
    private final ListenerHolder<DriveFile.DownloadProgressListener> zzey;

    public zzbp(ListenerHolder<DriveFile.DownloadProgressListener> listenerHolder) {
        this.zzey = listenerHolder;
    }

    @Override // com.google.android.gms.drive.DriveFile.DownloadProgressListener
    public final void onProgress(long j2, long j3) {
        this.zzey.notifyListener(new zzbq(this, j2, j3));
    }
}
