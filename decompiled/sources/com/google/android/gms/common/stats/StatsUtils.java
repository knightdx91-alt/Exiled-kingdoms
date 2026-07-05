package com.google.android.gms.common.stats;

import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.os.Process;
import android.text.TextUtils;
import com.google.android.gms.common.annotation.KeepForSdk;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@KeepForSdk
public class StatsUtils {
    @KeepForSdk
    public static String getEventKey(Context context, Intent intent) {
        return String.valueOf(((long) System.identityHashCode(intent)) | (((long) System.identityHashCode(context)) << 32));
    }

    @KeepForSdk
    public static String getEventKey(PowerManager.WakeLock wakeLock, String str) {
        String strValueOf = String.valueOf(String.valueOf((((long) Process.myPid()) << 32) | ((long) System.identityHashCode(wakeLock))));
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        String strValueOf2 = String.valueOf(str);
        return strValueOf2.length() != 0 ? strValueOf.concat(strValueOf2) : new String(strValueOf);
    }
}
