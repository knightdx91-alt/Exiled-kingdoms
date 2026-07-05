package com.google.android.gms.games.internal;

import com.google.android.gms.common.config.GservicesValue;
import com.google.android.gms.common.internal.GmsLogger;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzh {
    private static final GmsLogger zzik = new GmsLogger("Games");
    private static final GservicesValue<Boolean> zzil = GservicesValue.value("games.play_games_dogfood", false);

    public static void e(String str, String str2) {
        zzik.e(str, str2);
    }

    public static void i(String str, String str2, Throwable th) {
        zzik.i(str, str2, th);
    }

    public static void w(String str, String str2) {
        zzik.w(str, str2);
    }

    public static void e(String str, String str2, Throwable th) {
        zzik.e(str, str2, th);
    }

    public static void w(String str, String str2, Throwable th) {
        zzik.w(str, str2, th);
    }
}
