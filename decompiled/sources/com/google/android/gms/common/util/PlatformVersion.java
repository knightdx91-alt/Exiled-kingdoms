package com.google.android.gms.common.util;

import android.os.Build;
import com.google.android.gms.common.annotation.KeepForSdk;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@VisibleForTesting
@KeepForSdk
public final class PlatformVersion {
    private PlatformVersion() {
    }

    @KeepForSdk
    public static boolean isAtLeastHoneycomb() {
        return true;
    }

    @KeepForSdk
    public static boolean isAtLeastHoneycombMR1() {
        return true;
    }

    @KeepForSdk
    public static boolean isAtLeastIceCreamSandwich() {
        return true;
    }

    @KeepForSdk
    public static boolean isAtLeastIceCreamSandwichMR1() {
        return true;
    }

    @KeepForSdk
    public static boolean isAtLeastJellyBean() {
        return true;
    }

    @KeepForSdk
    public static boolean isAtLeastJellyBeanMR1() {
        return true;
    }

    @KeepForSdk
    public static boolean isAtLeastJellyBeanMR2() {
        return true;
    }

    @KeepForSdk
    public static boolean isAtLeastKitKat() {
        return true;
    }

    @KeepForSdk
    public static boolean isAtLeastKitKatWatch() {
        return true;
    }

    @KeepForSdk
    public static boolean isAtLeastLollipop() {
        return true;
    }

    @KeepForSdk
    public static boolean isAtLeastLollipopMR1() {
        return true;
    }

    @KeepForSdk
    public static boolean isAtLeastM() {
        return true;
    }

    @KeepForSdk
    public static boolean isAtLeastN() {
        return true;
    }

    @KeepForSdk
    public static boolean isAtLeastO() {
        return true;
    }

    @KeepForSdk
    public static boolean isAtLeastP() {
        return Build.VERSION.SDK_INT >= 28;
    }
}
