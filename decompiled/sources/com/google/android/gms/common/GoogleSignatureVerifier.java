package com.google.android.gms.common;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.wrappers.Wrappers;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@ShowFirstParty
@KeepForSdk
public class GoogleSignatureVerifier {
    private static GoogleSignatureVerifier zzal;
    private final Context mContext;
    private volatile String zzam;

    private GoogleSignatureVerifier(Context context) {
        this.mContext = context.getApplicationContext();
    }

    @KeepForSdk
    public static GoogleSignatureVerifier getInstance(Context context) {
        Preconditions.checkNotNull(context);
        synchronized (GoogleSignatureVerifier.class) {
            try {
                if (zzal == null) {
                    zzc.zza(context);
                    zzal = new GoogleSignatureVerifier(context);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return zzal;
    }

    public static boolean zza(PackageInfo packageInfo, boolean z2) {
        if (packageInfo != null && packageInfo.signatures != null) {
            if ((z2 ? zza(packageInfo, zzh.zzx) : zza(packageInfo, zzh.zzx[0])) != null) {
                return true;
            }
        }
        return false;
    }

    private final zzm zzc(String str) {
        if (str == null) {
            return zzm.zzb("null pkg");
        }
        if (str.equals(this.zzam)) {
            return zzm.zze();
        }
        try {
            zzm zzmVarZza = zza(Wrappers.packageManager(this.mContext).getPackageInfo(str, 64));
            if (zzmVarZza.zzac) {
                this.zzam = str;
            }
            return zzmVarZza;
        } catch (PackageManager.NameNotFoundException unused) {
            return zzm.zzb(str.length() != 0 ? "no pkg ".concat(str) : new String("no pkg "));
        }
    }

    @KeepForSdk
    public boolean isGooglePublicSignedPackage(PackageInfo packageInfo) {
        if (packageInfo == null) {
            return false;
        }
        if (zza(packageInfo, false)) {
            return true;
        }
        if (zza(packageInfo, true)) {
            if (GooglePlayServicesUtilLight.honorsDebugCertificates(this.mContext)) {
                return true;
            }
            Log.w("GoogleSignatureVerifier", "Test-keys aren't accepted on this build.");
        }
        return false;
    }

    @ShowFirstParty
    @KeepForSdk
    public boolean isPackageGoogleSigned(String str) {
        zzm zzmVarZzc = zzc(str);
        zzmVarZzc.zzf();
        return zzmVarZzc.zzac;
    }

    @ShowFirstParty
    @KeepForSdk
    public boolean isUidGoogleSigned(int i2) {
        zzm zzmVarZzb;
        String[] packagesForUid = Wrappers.packageManager(this.mContext).getPackagesForUid(i2);
        if (packagesForUid == null || packagesForUid.length == 0) {
            zzmVarZzb = zzm.zzb("no pkgs");
        } else {
            zzmVarZzb = null;
            for (String str : packagesForUid) {
                zzmVarZzb = zza(str, i2);
                if (zzmVarZzb.zzac) {
                    break;
                }
            }
        }
        zzmVarZzb.zzf();
        return zzmVarZzb.zzac;
    }

    private final zzm zza(String str, int i2) {
        try {
            return zza(Wrappers.packageManager(this.mContext).zza(str, 64, i2));
        } catch (PackageManager.NameNotFoundException unused) {
            String strValueOf = String.valueOf(str);
            return zzm.zzb(strValueOf.length() != 0 ? "no pkg ".concat(strValueOf) : new String("no pkg "));
        }
    }

    private final zzm zza(PackageInfo packageInfo) {
        ApplicationInfo applicationInfo;
        boolean zHonorsDebugCertificates = GooglePlayServicesUtilLight.honorsDebugCertificates(this.mContext);
        if (packageInfo == null) {
            return zzm.zzb("null pkg");
        }
        if (packageInfo.signatures.length != 1) {
            return zzm.zzb("single cert required");
        }
        zzf zzfVar = new zzf(packageInfo.signatures[0].toByteArray());
        String str = packageInfo.packageName;
        zzm zzmVarZza = zzc.zza(str, zzfVar, zHonorsDebugCertificates);
        return (!zzmVarZza.zzac || (applicationInfo = packageInfo.applicationInfo) == null || (applicationInfo.flags & 2) == 0 || (zHonorsDebugCertificates && !zzc.zza(str, (zze) zzfVar, false).zzac)) ? zzmVarZza : zzm.zzb("debuggable release cert app rejected");
    }

    private static zze zza(PackageInfo packageInfo, zze... zzeVarArr) {
        Signature[] signatureArr = packageInfo.signatures;
        if (signatureArr == null) {
            return null;
        }
        if (signatureArr.length != 1) {
            Log.w("GoogleSignatureVerifier", "Package has more than one signature.");
            return null;
        }
        zzf zzfVar = new zzf(packageInfo.signatures[0].toByteArray());
        for (int i2 = 0; i2 < zzeVarArr.length; i2++) {
            if (zzeVarArr[i2].equals(zzfVar)) {
                return zzeVarArr[i2];
            }
        }
        return null;
    }
}
