package com.google.android.gms.internal.drive;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.util.e;
import android.util.SparseArray;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.util.GmsVersion;
import com.google.android.gms.drive.metadata.CustomPropertyKey;
import com.google.android.gms.drive.metadata.internal.AppVisibleCustomProperties;
import java.util.Arrays;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class zzia extends com.google.android.gms.drive.metadata.internal.zzm<AppVisibleCustomProperties> {
    public static final com.google.android.gms.drive.metadata.internal.zzg zzkm = new zzib();

    public zzia(int i2) {
        super("customProperties", Arrays.asList("hasCustomProperties", "sqlId"), Arrays.asList("customPropertiesExtra", "customPropertiesExtraHolder"), GmsVersion.VERSION_LONGHORN);
    }

    private static AppVisibleCustomProperties zzf(DataHolder dataHolder, int i2, int i3) {
        Bundle metadata = dataHolder.getMetadata();
        SparseArray sparseParcelableArray = metadata.getSparseParcelableArray("customPropertiesExtra");
        if (sparseParcelableArray == null) {
            if (metadata.getParcelable("customPropertiesExtraHolder") != null) {
                synchronized (dataHolder) {
                    DataHolder dataHolder2 = (DataHolder) dataHolder.getMetadata().getParcelable("customPropertiesExtraHolder");
                    if (dataHolder2 != null) {
                        try {
                            Bundle metadata2 = dataHolder2.getMetadata();
                            String string = metadata2.getString("entryIdColumn");
                            String string2 = metadata2.getString("keyColumn");
                            String string3 = metadata2.getString("visibilityColumn");
                            String string4 = metadata2.getString("valueColumn");
                            e eVar = new e();
                            for (int i4 = 0; i4 < dataHolder2.getCount(); i4++) {
                                int windowIndex = dataHolder2.getWindowIndex(i4);
                                long j2 = dataHolder2.getLong(string, i4, windowIndex);
                                String string5 = dataHolder2.getString(string2, i4, windowIndex);
                                int integer = dataHolder2.getInteger(string3, i4, windowIndex);
                                com.google.android.gms.drive.metadata.internal.zzc zzcVar = new com.google.android.gms.drive.metadata.internal.zzc(new CustomPropertyKey(string5, integer), dataHolder2.getString(string4, i4, windowIndex));
                                AppVisibleCustomProperties.zza zzaVar = (AppVisibleCustomProperties.zza) eVar.d(j2);
                                if (zzaVar == null) {
                                    zzaVar = new AppVisibleCustomProperties.zza();
                                    eVar.e(j2, zzaVar);
                                }
                                zzaVar.zza(zzcVar);
                            }
                            SparseArray<? extends Parcelable> sparseArray = new SparseArray<>();
                            for (int i5 = 0; i5 < dataHolder.getCount(); i5++) {
                                AppVisibleCustomProperties.zza zzaVar2 = (AppVisibleCustomProperties.zza) eVar.d(dataHolder.getLong("sqlId", i5, dataHolder.getWindowIndex(i5)));
                                if (zzaVar2 != null) {
                                    sparseArray.append(i5, zzaVar2.zzat());
                                }
                            }
                            dataHolder.getMetadata().putSparseParcelableArray("customPropertiesExtra", sparseArray);
                            dataHolder2.close();
                            dataHolder.getMetadata().remove("customPropertiesExtraHolder");
                        } catch (Throwable th) {
                            dataHolder2.close();
                            dataHolder.getMetadata().remove("customPropertiesExtraHolder");
                            throw th;
                        }
                    }
                }
                sparseParcelableArray = metadata.getSparseParcelableArray("customPropertiesExtra");
            }
            if (sparseParcelableArray == null) {
                return AppVisibleCustomProperties.zzil;
            }
        }
        return (AppVisibleCustomProperties) sparseParcelableArray.get(i2, AppVisibleCustomProperties.zzil);
    }

    @Override // com.google.android.gms.drive.metadata.zza
    protected final /* synthetic */ Object zzc(DataHolder dataHolder, int i2, int i3) {
        return zzf(dataHolder, i2, i3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void zzc(DataHolder dataHolder) {
        Bundle metadata = dataHolder.getMetadata();
        if (metadata == null) {
            return;
        }
        synchronized (dataHolder) {
            try {
                DataHolder dataHolder2 = (DataHolder) metadata.getParcelable("customPropertiesExtraHolder");
                if (dataHolder2 != null) {
                    dataHolder2.close();
                    metadata.remove("customPropertiesExtraHolder");
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
