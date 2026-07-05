package com.google.android.gms.internal.drive;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.util.GmsVersion;
import com.google.android.gms.drive.DriveFolder;
import com.google.android.gms.drive.DriveId;
import java.util.Arrays;
import java.util.Iterator;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzij extends com.google.android.gms.drive.metadata.internal.zzm<DriveId> {
    public static final zzij zzkt = new zzij();

    private zzij() {
        super("driveId", Arrays.asList("sqlId", "resourceId", "mimeType"), Arrays.asList("dbInstanceId"), GmsVersion.VERSION_HALLOUMI);
    }

    @Override // com.google.android.gms.drive.metadata.zza
    protected final boolean zzb(DataHolder dataHolder, int i2, int i3) {
        Iterator<String> it = zzar().iterator();
        while (it.hasNext()) {
            if (!dataHolder.hasColumn(it.next())) {
                return false;
            }
        }
        return true;
    }

    @Override // com.google.android.gms.drive.metadata.zza
    protected final /* synthetic */ Object zzc(DataHolder dataHolder, int i2, int i3) {
        long j2 = dataHolder.getMetadata().getLong("dbInstanceId");
        boolean zEquals = DriveFolder.MIME_TYPE.equals(dataHolder.getString(zzhp.zzjs.getName(), i2, i3));
        String string = dataHolder.getString("resourceId", i2, i3);
        return new DriveId("generated-android-null".equals(string) ? null : string, dataHolder.getLong("sqlId", i2, i3), j2, zEquals ? 1 : 0);
    }
}
