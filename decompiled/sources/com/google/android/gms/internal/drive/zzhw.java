package com.google.android.gms.internal.drive;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.util.GmsVersion;
import com.google.android.gms.drive.DriveSpace;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzhw extends com.google.android.gms.drive.metadata.internal.zzl<DriveSpace> {
    public zzhw(int i2) {
        super("spaces", Arrays.asList("inDriveSpace", "isAppData", "inGooglePhotosSpace"), Collections.emptySet(), GmsVersion.VERSION_ORLA);
    }

    @Override // com.google.android.gms.drive.metadata.zzb, com.google.android.gms.drive.metadata.zza
    protected final /* synthetic */ Object zzc(DataHolder dataHolder, int i2, int i3) {
        return zzc(dataHolder, i2, i3);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.drive.metadata.zzb
    /* JADX INFO: renamed from: zzd */
    public final Collection<DriveSpace> zzc(DataHolder dataHolder, int i2, int i3) {
        ArrayList arrayList = new ArrayList();
        if (dataHolder.getBoolean("inDriveSpace", i2, i3)) {
            arrayList.add(DriveSpace.zzaf);
        }
        if (dataHolder.getBoolean("isAppData", i2, i3)) {
            arrayList.add(DriveSpace.zzag);
        }
        if (dataHolder.getBoolean("inGooglePhotosSpace", i2, i3)) {
            arrayList.add(DriveSpace.zzah);
        }
        return arrayList;
    }
}
