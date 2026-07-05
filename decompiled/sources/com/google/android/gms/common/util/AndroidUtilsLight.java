package com.google.android.gms.common.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.Signature;
import com.badlogic.gdx.graphics.g3d.decals.Decal;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.wrappers.Wrappers;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@KeepForSdk
public class AndroidUtilsLight {
    private static volatile int zzgd = -1;

    @KeepForSdk
    @TargetApi(Decal.SIZE)
    public static Context getDeviceProtectedStorageContext(Context context) {
        return (!PlatformVersion.isAtLeastN() || context.isDeviceProtectedStorage()) ? context : context.createDeviceProtectedStorageContext();
    }

    @KeepForSdk
    public static byte[] getPackageCertificateHashBytes(Context context, String str) {
        MessageDigest messageDigestZzi;
        PackageInfo packageInfo = Wrappers.packageManager(context).getPackageInfo(str, 64);
        Signature[] signatureArr = packageInfo.signatures;
        if (signatureArr == null || signatureArr.length != 1 || (messageDigestZzi = zzi("SHA1")) == null) {
            return null;
        }
        return messageDigestZzi.digest(packageInfo.signatures[0].toByteArray());
    }

    public static MessageDigest zzi(String str) {
        MessageDigest messageDigest;
        for (int i2 = 0; i2 < 2; i2++) {
            try {
                messageDigest = MessageDigest.getInstance(str);
            } catch (NoSuchAlgorithmException unused) {
            }
            if (messageDigest != null) {
                return messageDigest;
            }
        }
        return null;
    }
}
