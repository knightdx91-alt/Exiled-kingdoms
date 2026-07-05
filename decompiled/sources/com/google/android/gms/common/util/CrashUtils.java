package com.google.android.gms.common.util;

import android.content.Context;
import android.os.DropBoxManager;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.drive.DriveFile;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@KeepForSdk
public final class CrashUtils {
    private static final String[] zzge = {"android.", "com.android.", "dalvik.", "java.", "javax."};
    private static DropBoxManager zzgf = null;
    private static boolean zzgg = false;
    private static int zzgh = -1;
    private static int zzgi = 0;
    private static int zzgj = 0;

    @KeepForSdk
    public static boolean addDynamiteErrorToDropBox(Context context, Throwable th) {
        return zza(context, th, DriveFile.MODE_WRITE_ONLY);
    }

    private static boolean zza(Context context, Throwable th, int i2) {
        try {
            Preconditions.checkNotNull(context);
            Preconditions.checkNotNull(th);
            return false;
        } catch (Exception e2) {
            Log.e("CrashUtils", "Error adding exception to DropBox!", e2);
            return false;
        }
    }
}
