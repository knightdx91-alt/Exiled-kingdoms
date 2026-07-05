package com.google.android.gms.common.util;

import com.google.android.gms.auth.api.credentials.CredentialsApi;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzb {
    private static Pattern zzgv;

    public static int zzc(int i2) {
        if (i2 == -1) {
            return -1;
        }
        return i2 / CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT;
    }
}
