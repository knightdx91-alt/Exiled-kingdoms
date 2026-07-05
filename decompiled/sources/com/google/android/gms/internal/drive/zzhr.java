package com.google.android.gms.internal.drive;

import com.google.android.gms.common.data.BitmapTeleporter;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.util.GmsVersion;
import java.util.Collection;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzhr extends com.google.android.gms.drive.metadata.internal.zzm<BitmapTeleporter> {
    zzhr(String str, Collection collection, Collection collection2, int i2) {
        super(str, collection, collection2, GmsVersion.VERSION_KENAFA);
    }

    @Override // com.google.android.gms.drive.metadata.zza
    protected final /* synthetic */ Object zzc(DataHolder dataHolder, int i2, int i3) {
        throw new IllegalStateException("Thumbnail field is write only");
    }
}
