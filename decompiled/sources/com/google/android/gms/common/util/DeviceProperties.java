package com.google.android.gms.common.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import com.badlogic.gdx.graphics.g3d.decals.Decal;
import com.google.android.gms.common.annotation.KeepForSdk;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@KeepForSdk
public final class DeviceProperties {
    private static Boolean zzgl;
    private static Boolean zzgm;
    private static Boolean zzgn;
    private static Boolean zzgo;
    private static Boolean zzgp;
    private static Boolean zzgq;
    private static Boolean zzgr;
    private static Boolean zzgs;

    private DeviceProperties() {
    }

    @KeepForSdk
    public static boolean isAuto(Context context) {
        if (zzgr == null) {
            zzgr = Boolean.valueOf(PlatformVersion.isAtLeastO() && context.getPackageManager().hasSystemFeature("android.hardware.type.automotive"));
        }
        return zzgr.booleanValue();
    }

    @KeepForSdk
    public static boolean isLatchsky(Context context) {
        if (zzgp == null) {
            PackageManager packageManager = context.getPackageManager();
            zzgp = Boolean.valueOf(packageManager.hasSystemFeature("com.google.android.feature.services_updater") && packageManager.hasSystemFeature("cn.google.services"));
        }
        return zzgp.booleanValue();
    }

    @KeepForSdk
    @TargetApi(Decal.C4)
    public static boolean isSidewinder(Context context) {
        if (zzgo == null) {
            zzgo = Boolean.valueOf(PlatformVersion.isAtLeastLollipop() && context.getPackageManager().hasSystemFeature("cn.google"));
        }
        return zzgo.booleanValue();
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x003a  */
    @KeepForSdk
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static boolean isTablet(Resources resources) {
        if (resources == null) {
            return false;
        }
        if (zzgl == null) {
            if ((resources.getConfiguration().screenLayout & 15) <= 3) {
                if (zzgm == null) {
                    Configuration configuration = resources.getConfiguration();
                    zzgm = Boolean.valueOf((configuration.screenLayout & 15) <= 3 && configuration.smallestScreenWidthDp >= 600);
                }
                boolean z2 = zzgm.booleanValue();
                zzgl = Boolean.valueOf(z2);
            }
        }
        return zzgl.booleanValue();
    }

    @KeepForSdk
    public static boolean isTv(Context context) {
        if (zzgs == null) {
            PackageManager packageManager = context.getPackageManager();
            zzgs = Boolean.valueOf(packageManager.hasSystemFeature("com.google.android.tv") || packageManager.hasSystemFeature("android.hardware.type.television") || packageManager.hasSystemFeature("android.software.leanback"));
        }
        return zzgs.booleanValue();
    }

    @KeepForSdk
    public static boolean isUserBuild() {
        return "user".equals(Build.TYPE);
    }

    @KeepForSdk
    @TargetApi(20)
    public static boolean isWearable(Context context) {
        if (zzgn == null) {
            zzgn = Boolean.valueOf(PlatformVersion.isAtLeastKitKatWatch() && context.getPackageManager().hasSystemFeature("android.hardware.type.watch"));
        }
        return zzgn.booleanValue();
    }

    @KeepForSdk
    @TargetApi(26)
    public static boolean isWearableWithoutPlayStore(Context context) {
        if (!isWearable(context)) {
            return false;
        }
        if (PlatformVersion.isAtLeastN()) {
            return isSidewinder(context) && !PlatformVersion.isAtLeastO();
        }
        return true;
    }

    public static boolean zzf(Context context) {
        if (zzgq == null) {
            zzgq = Boolean.valueOf(context.getPackageManager().hasSystemFeature("android.hardware.type.iot") || context.getPackageManager().hasSystemFeature("android.hardware.type.embedded"));
        }
        return zzgq.booleanValue();
    }
}
