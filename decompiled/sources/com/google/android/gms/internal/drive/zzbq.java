package com.google.android.gms.internal.drive;

import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.drive.DriveFile;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzbq implements ListenerHolder.Notifier<DriveFile.DownloadProgressListener> {
    private final /* synthetic */ long zzez;
    private final /* synthetic */ long zzfa;

    zzbq(zzbp zzbpVar, long j2, long j3) {
        this.zzez = j2;
        this.zzfa = j3;
    }

    @Override // com.google.android.gms.common.api.internal.ListenerHolder.Notifier
    public final /* synthetic */ void notifyListener(DriveFile.DownloadProgressListener downloadProgressListener) {
        downloadProgressListener.onProgress(this.zzez, this.zzfa);
    }

    @Override // com.google.android.gms.common.api.internal.ListenerHolder.Notifier
    public final void onNotifyListenerFailed() {
    }
}
