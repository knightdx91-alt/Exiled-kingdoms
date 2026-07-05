package com.google.android.gms.internal.drive;

import java.nio.charset.Charset;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zziv {
    protected static final Charset UTF_8 = Charset.forName("UTF-8");
    private static final Charset ISO_8859_1 = Charset.forName("ISO-8859-1");
    private static final Object zzne = new Object();

    public static void zza(zzir zzirVar, zzir zzirVar2) {
        zzit zzitVar = zzirVar.zzmw;
        if (zzitVar != null) {
            zzirVar2.zzmw = (zzit) zzitVar.clone();
        }
    }
}
