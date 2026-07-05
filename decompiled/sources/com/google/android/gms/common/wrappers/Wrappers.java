package com.google.android.gms.common.wrappers;

import android.content.Context;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.util.VisibleForTesting;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@KeepForSdk
public class Wrappers {
    private static Wrappers zzhx = new Wrappers();
    private PackageManagerWrapper zzhw = null;

    @KeepForSdk
    public static PackageManagerWrapper packageManager(Context context) {
        return zzhx.zzi(context);
    }

    @VisibleForTesting
    private final synchronized PackageManagerWrapper zzi(Context context) {
        try {
            if (this.zzhw == null) {
                if (context.getApplicationContext() != null) {
                    context = context.getApplicationContext();
                }
                this.zzhw = new PackageManagerWrapper(context);
            }
        } catch (Throwable th) {
            throw th;
        }
        return this.zzhw;
    }
}
