package com.badlogic.gdx.utils;

import android.app.AppOpsManager;
import android.content.Context;
import android.os.Bundle;
import android.os.Process;
import android.view.View;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import net.fdgames.ek.ExiledKingdoms;

/* JADX INFO: compiled from: GdxNativesLoader.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class l implements android.support.v4.view.m, com.badlogic.gdx.assets.loaders.e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static boolean f1831a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static float f1832b;

    public static void d(String str) {
        if (ExiledKingdoms.f3378h) {
            try {
                Date date = new Date();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                try {
                    Files.write(Paths.get(System.getProperty("user.dir") + "/init_log.txt", new String[0]), (simpleDateFormat.format(date) + " - " + str + "\r\n").getBytes(), StandardOpenOption.APPEND);
                } catch (IOException unused) {
                }
            } catch (Exception e2) {
                System.out.println(e2.getMessage());
            }
        }
    }

    public static boolean e(Bundle bundle, Bundle bundle2) {
        if (bundle == bundle2) {
            return true;
        }
        return bundle == null ? bundle2.getInt("android.media.browse.extra.PAGE", -1) == -1 && bundle2.getInt("android.media.browse.extra.PAGE_SIZE", -1) == -1 : bundle2 == null ? bundle.getInt("android.media.browse.extra.PAGE", -1) == -1 && bundle.getInt("android.media.browse.extra.PAGE_SIZE", -1) == -1 : bundle.getInt("android.media.browse.extra.PAGE", -1) == bundle2.getInt("android.media.browse.extra.PAGE", -1) && bundle.getInt("android.media.browse.extra.PAGE_SIZE", -1) == bundle2.getInt("android.media.browse.extra.PAGE_SIZE", -1);
    }

    public static int f(Context context, String str) {
        int iMyPid = Process.myPid();
        int iMyUid = Process.myUid();
        String packageName = context.getPackageName();
        if (context.checkPermission(str, iMyPid, iMyUid) == -1) {
            return -1;
        }
        String strPermissionToOp = AppOpsManager.permissionToOp(str);
        if (strPermissionToOp != null) {
            if (packageName == null) {
                String[] packagesForUid = context.getPackageManager().getPackagesForUid(iMyUid);
                if (packagesForUid == null || packagesForUid.length <= 0) {
                    return -1;
                }
                packageName = packagesForUid[0];
            }
            if (((AppOpsManager) context.getSystemService(AppOpsManager.class)).noteProxyOp(strPermissionToOp, packageName) != 0) {
                return -2;
            }
        }
        return 0;
    }

    public static int g(float f2) {
        return Float.floatToRawIntBits(f2) | (((int) ((r2 >>> 24) * 1.003937f)) << 24);
    }

    public static float h(int i2) {
        return Float.intBitsToFloat(i2 & (-16777217));
    }

    public static synchronized void i() {
        if (f1831a) {
            return;
        }
        f1831a = true;
        new i0();
        i0.c("gdx");
    }

    @Override // android.support.v4.view.m
    public void b(View view) {
    }

    @Override // android.support.v4.view.m
    public void c() {
    }
}
