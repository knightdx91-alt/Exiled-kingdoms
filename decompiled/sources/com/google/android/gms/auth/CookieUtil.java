package com.google.android.gms.auth;

import a.a;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class CookieUtil {
    private CookieUtil() {
    }

    public static String getCookieUrl(String str, Boolean bool) {
        Preconditions.checkNotEmpty(str);
        String str2 = zza(bool) ? "https" : "http";
        StringBuilder sb = new StringBuilder(a.e(str2.length() + 3, str));
        sb.append(str2);
        sb.append("://");
        sb.append(str);
        return sb.toString();
    }

    public static String getCookieValue(String str, String str2, String str3, String str4, Boolean bool, Boolean bool2, Long l2) {
        StringBuilder sb = new StringBuilder(str);
        sb.append('=');
        if (!TextUtils.isEmpty(str2)) {
            sb.append(str2);
        }
        if (zza(bool)) {
            sb.append(";HttpOnly");
        }
        if (zza(bool2)) {
            sb.append(";Secure");
        }
        if (!TextUtils.isEmpty(str3)) {
            sb.append(";Domain=");
            sb.append(str3);
        }
        if (!TextUtils.isEmpty(str4)) {
            sb.append(";Path=");
            sb.append(str4);
        }
        if (l2 != null && l2.longValue() > 0) {
            sb.append(";Max-Age=");
            sb.append(l2);
        }
        return sb.toString();
    }

    private static boolean zza(Boolean bool) {
        return bool != null && bool.booleanValue();
    }
}
