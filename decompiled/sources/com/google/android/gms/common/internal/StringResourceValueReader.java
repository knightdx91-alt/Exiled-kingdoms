package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.res.Resources;
import com.google.android.gms.common.R;
import com.google.android.gms.common.annotation.KeepForSdk;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@KeepForSdk
public class StringResourceValueReader {
    private final Resources zzeu;
    private final String zzev;

    public StringResourceValueReader(Context context) {
        Preconditions.checkNotNull(context);
        Resources resources = context.getResources();
        this.zzeu = resources;
        this.zzev = resources.getResourcePackageName(R.string.common_google_play_services_unknown_issue);
    }

    @KeepForSdk
    public String getString(String str) {
        int identifier = this.zzeu.getIdentifier(str, "string", this.zzev);
        if (identifier == 0) {
            return null;
        }
        return this.zzeu.getString(identifier);
    }
}
