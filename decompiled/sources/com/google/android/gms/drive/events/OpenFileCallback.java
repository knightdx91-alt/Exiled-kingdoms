package com.google.android.gms.drive.events;

import com.google.android.gms.drive.DriveContents;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public abstract class OpenFileCallback {
    public abstract void onContents(DriveContents driveContents);

    public abstract void onError(Exception exc);

    public abstract void onProgress(long j2, long j3);
}
