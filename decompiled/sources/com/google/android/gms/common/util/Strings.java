package com.google.android.gms.common.util;

import android.text.TextUtils;
import com.google.android.gms.common.annotation.KeepForSdk;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@VisibleForTesting
@KeepForSdk
public class Strings {
    private static final Pattern zzhf = Pattern.compile("\\$\\{(.*?)\\}");

    private Strings() {
    }

    @KeepForSdk
    public static String emptyToNull(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return str;
    }

    @KeepForSdk
    public static boolean isEmptyOrWhitespace(String str) {
        return str == null || str.trim().isEmpty();
    }
}
