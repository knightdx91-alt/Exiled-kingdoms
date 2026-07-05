package com.google.android.gms.common.util;

import android.os.Process;
import android.os.StrictMode;
import com.google.android.gms.common.annotation.KeepForSdk;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@KeepForSdk
public class ProcessUtils {
    private static String zzhd;
    private static int zzhe;

    private ProcessUtils() {
    }

    @KeepForSdk
    public static String getMyProcessName() {
        if (zzhd == null) {
            if (zzhe == 0) {
                zzhe = Process.myPid();
            }
            zzhd = zzd(zzhe);
        }
        return zzhd;
    }

    private static String zzd(int i2) throws Throwable {
        Throwable th;
        BufferedReader bufferedReaderZzj;
        String strTrim = null;
        if (i2 <= 0) {
            return null;
        }
        try {
            StringBuilder sb = new StringBuilder(25);
            sb.append("/proc/");
            sb.append(i2);
            sb.append("/cmdline");
            bufferedReaderZzj = zzj(sb.toString());
            try {
                strTrim = bufferedReaderZzj.readLine().trim();
                IOUtils.closeQuietly(bufferedReaderZzj);
            } catch (IOException unused) {
                IOUtils.closeQuietly(bufferedReaderZzj);
            } catch (Throwable th2) {
                th = th2;
                IOUtils.closeQuietly(bufferedReaderZzj);
                throw th;
            }
        } catch (IOException unused2) {
            bufferedReaderZzj = null;
        } catch (Throwable th3) {
            th = th3;
            bufferedReaderZzj = null;
        }
        return strTrim;
    }

    private static BufferedReader zzj(String str) {
        StrictMode.ThreadPolicy threadPolicyAllowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            return new BufferedReader(new FileReader(str));
        } finally {
            StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskReads);
        }
    }
}
