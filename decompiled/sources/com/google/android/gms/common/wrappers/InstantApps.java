package com.google.android.gms.common.wrappers;

import android.content.Context;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.util.PlatformVersion;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@KeepForSdk
public class InstantApps {
    private static Context zzht;
    private static Boolean zzhu;

    @KeepForSdk
    public static synchronized boolean isInstantApp(Context context) {
        Boolean bool;
        Context applicationContext = context.getApplicationContext();
        Context context2 = zzht;
        if (context2 != null && (bool = zzhu) != null && context2 == applicationContext) {
            return bool.booleanValue();
        }
        zzhu = null;
        if (PlatformVersion.isAtLeastO()) {
            zzhu = Boolean.valueOf(applicationContext.getPackageManager().isInstantApp());
        } else {
            try {
                context.getClassLoader().loadClass("com.google.android.instantapps.supervisor.InstantAppsRuntime");
                zzhu = Boolean.TRUE;
            } catch (ClassNotFoundException unused) {
                zzhu = Boolean.FALSE;
            }
        }
        zzht = applicationContext;
        return zzhu.booleanValue();
    }
}
