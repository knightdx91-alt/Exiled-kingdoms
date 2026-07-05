package com.google.android.gms.drive.events;

import com.google.android.gms.drive.DriveId;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzj {
    public static boolean zza(int i2, DriveId driveId) {
        if (i2 != 1) {
            if (i2 == 4 || i2 == 7) {
                return driveId == null;
            }
            if (i2 != 8) {
                return false;
            }
        }
        return driveId != null;
    }
}
