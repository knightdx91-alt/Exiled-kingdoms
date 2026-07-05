package com.google.android.gms.common.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.wrappers.Wrappers;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@KeepForSdk
public class ClientLibraryUtils {
    private ClientLibraryUtils() {
    }

    @KeepForSdk
    public static int getClientVersion(Context context, String str) {
        ApplicationInfo applicationInfo;
        Bundle bundle;
        PackageInfo packageInfoZzb = zzb(context, str);
        if (packageInfoZzb == null || (applicationInfo = packageInfoZzb.applicationInfo) == null || (bundle = applicationInfo.metaData) == null) {
            return -1;
        }
        return bundle.getInt("com.google.android.gms.version", -1);
    }

    @KeepForSdk
    public static boolean isPackageSide() {
        return false;
    }

    private static PackageInfo zzb(Context context, String str) {
        try {
            return Wrappers.packageManager(context).getPackageInfo(str, VertexAttributes.Usage.Tangent);
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    public static boolean zzc(Context context, String str) {
        "com.google.android.gms".equals(str);
        return (Wrappers.packageManager(context).getApplicationInfo(str, 0).flags & 2097152) != 0;
    }
}
