package com.google.android.gms.drive;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.drive.DriveApi;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public interface DriveFile extends DriveResource {
    public static final int MODE_READ_ONLY = 268435456;
    public static final int MODE_READ_WRITE = 805306368;
    public static final int MODE_WRITE_ONLY = 536870912;

    @Deprecated
    public interface DownloadProgressListener {
        void onProgress(long j2, long j3);
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface OpenMode {
    }

    @Deprecated
    PendingResult<DriveApi.DriveContentsResult> open(GoogleApiClient googleApiClient, int i2, DownloadProgressListener downloadProgressListener);
}
