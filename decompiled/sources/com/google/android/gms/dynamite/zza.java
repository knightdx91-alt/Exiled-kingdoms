package com.google.android.gms.dynamite;

import android.content.Context;
import com.google.android.gms.dynamite.DynamiteModule;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zza implements DynamiteModule.VersionPolicy.zza {
    zza() {
    }

    @Override // com.google.android.gms.dynamite.DynamiteModule.VersionPolicy.zza
    public final int getLocalVersion(Context context, String str) {
        return DynamiteModule.getLocalVersion(context, str);
    }

    @Override // com.google.android.gms.dynamite.DynamiteModule.VersionPolicy.zza
    public final int zza(Context context, String str, boolean z2) {
        return DynamiteModule.zza(context, str, z2);
    }
}
