package com.google.android.gms.drive.metadata.internal;

import a.a;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.util.GmsVersion;
import com.google.android.gms.drive.UserMetadata;
import java.util.Arrays;
import java.util.Collections;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzu extends zzm<UserMetadata> {
    public zzu(String str, int i2) {
        super(str, Arrays.asList(zza(str, "permissionId"), zza(str, "displayName"), zza(str, "picture"), zza(str, "isAuthenticatedUser"), zza(str, "emailAddress")), Collections.emptyList(), GmsVersion.VERSION_MANCHEGO);
    }

    private static String zza(String str, String str2) {
        StringBuilder sb = new StringBuilder(a.e(a.e(1, str), str2));
        sb.append(str);
        sb.append(".");
        sb.append(str2);
        return sb.toString();
    }

    private final String zzf(String str) {
        return zza(getName(), str);
    }

    @Override // com.google.android.gms.drive.metadata.zza
    protected final boolean zzb(DataHolder dataHolder, int i2, int i3) {
        return dataHolder.hasColumn(zzf("permissionId")) && !dataHolder.hasNull(zzf("permissionId"), i2, i3);
    }

    @Override // com.google.android.gms.drive.metadata.zza
    protected final /* synthetic */ Object zzc(DataHolder dataHolder, int i2, int i3) {
        String string = dataHolder.getString(zzf("permissionId"), i2, i3);
        if (string != null) {
            return new UserMetadata(string, dataHolder.getString(zzf("displayName"), i2, i3), dataHolder.getString(zzf("picture"), i2, i3), dataHolder.getBoolean(zzf("isAuthenticatedUser"), i2, i3), dataHolder.getString(zzf("emailAddress"), i2, i3));
        }
        return null;
    }
}
