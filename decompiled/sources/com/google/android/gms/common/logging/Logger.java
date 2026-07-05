package com.google.android.gms.common.logging;

import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.GmsLogger;
import java.util.Locale;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@KeepForSdk
public class Logger {
    private final String mTag;
    private final String zzei;
    private final GmsLogger zzew;
    private final int zzex;

    /* JADX WARN: Illegal instructions before constructor call */
    @KeepForSdk
    public Logger(String str, String... strArr) {
        String string;
        if (strArr.length == 0) {
            string = "";
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append('[');
            for (String str2 : strArr) {
                if (sb.length() > 1) {
                    sb.append(",");
                }
                sb.append(str2);
            }
            sb.append(']');
            sb.append(' ');
            string = sb.toString();
        }
        this(str, string);
    }

    private final String format(String str, Object... objArr) {
        if (objArr != null && objArr.length > 0) {
            str = String.format(Locale.US, str, objArr);
        }
        return this.zzei.concat(str);
    }

    @KeepForSdk
    public void d(String str, Object... objArr) {
        if (isLoggable(3)) {
            Log.d(this.mTag, format(str, objArr));
        }
    }

    @KeepForSdk
    public void e(String str, Object... objArr) {
        Log.e(this.mTag, format(str, objArr));
    }

    @KeepForSdk
    public void i(String str, Object... objArr) {
        Log.i(this.mTag, format(str, objArr));
    }

    @KeepForSdk
    public boolean isLoggable(int i2) {
        return this.zzex <= i2;
    }

    @KeepForSdk
    public void v(String str, Object... objArr) {
        if (isLoggable(2)) {
            Log.v(this.mTag, format(str, objArr));
        }
    }

    @KeepForSdk
    public void w(String str, Object... objArr) {
        Log.w(this.mTag, format(str, objArr));
    }

    @KeepForSdk
    public void wtf(String str, Throwable th, Object... objArr) {
        Log.wtf(this.mTag, format(str, objArr), th);
    }

    @KeepForSdk
    public void e(String str, Throwable th, Object... objArr) {
        Log.e(this.mTag, format(str, objArr), th);
    }

    @KeepForSdk
    public void wtf(Throwable th) {
        Log.wtf(this.mTag, th);
    }

    private Logger(String str, String str2) {
        this.zzei = str2;
        this.mTag = str;
        this.zzew = new GmsLogger(str);
        int i2 = 2;
        while (7 >= i2 && !Log.isLoggable(this.mTag, i2)) {
            i2++;
        }
        this.zzex = i2;
    }
}
