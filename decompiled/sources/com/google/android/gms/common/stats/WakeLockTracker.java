package com.google.android.gms.common.stats;

import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@KeepForSdk
public class WakeLockTracker {
    private static Boolean zzgc;
    private static WakeLockTracker zzgb = new WakeLockTracker();

    @VisibleForTesting
    private static boolean zzfb = false;

    @KeepForSdk
    public static WakeLockTracker getInstance() {
        return zzgb;
    }

    @KeepForSdk
    public void registerAcquireEvent(Context context, Intent intent, String str, String str2, String str3, int i2, String str4) {
        registerEvent(context, intent.getStringExtra(LoggingConstants.EXTRA_WAKE_LOCK_KEY), 7, str, str2, str3, i2, Arrays.asList(str4));
    }

    @KeepForSdk
    public void registerEvent(Context context, String str, int i2, String str2, String str3, String str4, int i3, List<String> list) {
        registerEvent(context, str, i2, str2, str3, str4, i3, list, 0L);
    }

    @KeepForSdk
    public void registerReleaseEvent(Context context, Intent intent) {
        registerEvent(context, intent.getStringExtra(LoggingConstants.EXTRA_WAKE_LOCK_KEY), 8, null, null, null, 0, null);
    }

    @KeepForSdk
    public void registerEvent(Context context, String str, int i2, String str2, String str3, String str4, int i3, List<String> list, long j2) {
        List<String> list2 = list;
        if (zzgc == null) {
            zzgc = Boolean.FALSE;
        }
        if (zzgc.booleanValue()) {
            if (TextUtils.isEmpty(str)) {
                String strValueOf = String.valueOf(str);
                Log.e("WakeLockTracker", strValueOf.length() != 0 ? "missing wakeLock key. ".concat(strValueOf) : new String("missing wakeLock key. "));
                return;
            }
            long jCurrentTimeMillis = System.currentTimeMillis();
            if (7 == i2 || 8 == i2 || 10 == i2 || 11 == i2) {
                if (list2 != null && list.size() == 1 && "com.google.android.gms".equals(list2.get(0))) {
                    list2 = null;
                }
                List<String> list3 = list2;
                long jElapsedRealtime = SystemClock.elapsedRealtime();
                int iZzg = com.google.android.gms.common.util.zza.zzg(context);
                String packageName = context.getPackageName();
                try {
                    context.startService(new Intent().setComponent(LoggingConstants.zzfg).putExtra("com.google.android.gms.common.stats.EXTRA_LOG_EVENT", new WakeLockEvent(jCurrentTimeMillis, i2, str2, i3, list3, str, jElapsedRealtime, iZzg, str3, "com.google.android.gms".equals(packageName) ? null : packageName, com.google.android.gms.common.util.zza.zzh(context), j2, str4)));
                } catch (Exception e2) {
                    Log.wtf("WakeLockTracker", e2);
                }
            }
        }
    }
}
