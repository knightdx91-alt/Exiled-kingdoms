package com.google.android.gms.common.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Process;
import android.os.WorkSource;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.wrappers.Wrappers;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@KeepForSdk
public class WorkSourceUtil {
    private static final int zzhh = Process.myUid();
    private static final Method zzhi = zzw();
    private static final Method zzhj = zzx();
    private static final Method zzhk = zzy();
    private static final Method zzhl = zzz();
    private static final Method zzhm = zzaa();
    private static final Method zzhn = zzab();
    private static final Method zzho = zzac();

    private WorkSourceUtil() {
    }

    @KeepForSdk
    public static WorkSource fromPackage(Context context, String str) {
        if (context != null && context.getPackageManager() != null && str != null) {
            try {
                ApplicationInfo applicationInfo = Wrappers.packageManager(context).getApplicationInfo(str, 0);
                if (applicationInfo != null) {
                    return zza(applicationInfo.uid, str);
                }
                Log.e("WorkSourceUtil", str.length() != 0 ? "Could not get applicationInfo from package: ".concat(str) : new String("Could not get applicationInfo from package: "));
                return null;
            } catch (PackageManager.NameNotFoundException unused) {
                Log.e("WorkSourceUtil", str.length() != 0 ? "Could not find package: ".concat(str) : new String("Could not find package: "));
            }
        }
        return null;
    }

    @KeepForSdk
    public static WorkSource fromPackageAndModuleExperimentalPi(Context context, String str, String str2) {
        Method method;
        if (context == null || context.getPackageManager() == null || str2 == null || str == null) {
            Log.w("WorkSourceUtil", "Unexpected null arguments");
            return null;
        }
        int iZzd = zzd(context, str);
        if (iZzd < 0) {
            return null;
        }
        WorkSource workSource = new WorkSource();
        Method method2 = zzhn;
        if (method2 == null || (method = zzho) == null) {
            zza(workSource, iZzd, str);
        } else {
            try {
                Object objInvoke = method2.invoke(workSource, null);
                int i2 = zzhh;
                if (iZzd != i2) {
                    method.invoke(objInvoke, Integer.valueOf(iZzd), str);
                }
                method.invoke(objInvoke, Integer.valueOf(i2), str2);
            } catch (Exception e2) {
                Log.w("WorkSourceUtil", "Unable to assign chained blame through WorkSource", e2);
            }
        }
        return workSource;
    }

    @KeepForSdk
    public static List<String> getNames(WorkSource workSource) {
        int iZza = workSource == null ? 0 : zza(workSource);
        if (iZza == 0) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < iZza; i2++) {
            String strZza = zza(workSource, i2);
            if (!Strings.isEmptyOrWhitespace(strZza)) {
                arrayList.add(strZza);
            }
        }
        return arrayList;
    }

    @KeepForSdk
    public static boolean hasWorkSourcePermission(Context context) {
        return (context == null || context.getPackageManager() == null || Wrappers.packageManager(context).checkPermission("android.permission.UPDATE_DEVICE_STATS", context.getPackageName()) != 0) ? false : true;
    }

    private static WorkSource zza(int i2, String str) {
        WorkSource workSource = new WorkSource();
        zza(workSource, i2, str);
        return workSource;
    }

    private static Method zzaa() {
        if (PlatformVersion.isAtLeastJellyBeanMR2()) {
            try {
                return WorkSource.class.getMethod("getName", Integer.TYPE);
            } catch (Exception unused) {
            }
        }
        return null;
    }

    private static final Method zzab() {
        if (!PlatformVersion.isAtLeastP()) {
            return null;
        }
        try {
            return WorkSource.class.getMethod("createWorkChain", null);
        } catch (Exception e2) {
            Log.w("WorkSourceUtil", "Missing WorkChain API createWorkChain", e2);
            return null;
        }
    }

    @SuppressLint({"PrivateApi"})
    private static final Method zzac() {
        if (PlatformVersion.isAtLeastP()) {
            try {
                return Class.forName("android.os.WorkSource$WorkChain").getMethod("addNode", Integer.TYPE, String.class);
            } catch (Exception e2) {
                Log.w("WorkSourceUtil", "Missing WorkChain class", e2);
            }
        }
        return null;
    }

    private static int zzd(Context context, String str) {
        try {
            ApplicationInfo applicationInfo = Wrappers.packageManager(context).getApplicationInfo(str, 0);
            if (applicationInfo != null) {
                return applicationInfo.uid;
            }
            String strValueOf = String.valueOf(str);
            Log.e("WorkSourceUtil", strValueOf.length() != 0 ? "Could not get applicationInfo from package: ".concat(strValueOf) : new String("Could not get applicationInfo from package: "));
            return -1;
        } catch (PackageManager.NameNotFoundException unused) {
            String strValueOf2 = String.valueOf(str);
            Log.e("WorkSourceUtil", strValueOf2.length() != 0 ? "Could not find package: ".concat(strValueOf2) : new String("Could not find package: "));
            return -1;
        }
    }

    private static Method zzw() {
        try {
            return WorkSource.class.getMethod("add", Integer.TYPE);
        } catch (Exception unused) {
            return null;
        }
    }

    private static Method zzx() {
        if (PlatformVersion.isAtLeastJellyBeanMR2()) {
            try {
                return WorkSource.class.getMethod("add", Integer.TYPE, String.class);
            } catch (Exception unused) {
            }
        }
        return null;
    }

    private static Method zzy() {
        try {
            return WorkSource.class.getMethod("size", null);
        } catch (Exception unused) {
            return null;
        }
    }

    private static Method zzz() {
        try {
            return WorkSource.class.getMethod("get", Integer.TYPE);
        } catch (Exception unused) {
            return null;
        }
    }

    private static void zza(WorkSource workSource, int i2, String str) {
        Method method = zzhj;
        if (method != null) {
            if (str == null) {
                str = "";
            }
            try {
                method.invoke(workSource, Integer.valueOf(i2), str);
                return;
            } catch (Exception e2) {
                Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource", e2);
                return;
            }
        }
        Method method2 = zzhi;
        if (method2 != null) {
            try {
                method2.invoke(workSource, Integer.valueOf(i2));
            } catch (Exception e3) {
                Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource", e3);
            }
        }
    }

    private static int zza(WorkSource workSource) {
        Method method = zzhk;
        if (method == null) {
            return 0;
        }
        try {
            return ((Integer) method.invoke(workSource, null)).intValue();
        } catch (Exception e2) {
            Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource", e2);
            return 0;
        }
    }

    private static String zza(WorkSource workSource, int i2) {
        Method method = zzhm;
        if (method == null) {
            return null;
        }
        try {
            return (String) method.invoke(workSource, Integer.valueOf(i2));
        } catch (Exception e2) {
            Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource", e2);
            return null;
        }
    }
}
