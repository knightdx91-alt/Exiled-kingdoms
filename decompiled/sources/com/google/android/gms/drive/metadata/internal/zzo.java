package com.google.android.gms.drive.metadata.internal;

import android.os.Bundle;
import android.os.Parcelable;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.util.GmsVersion;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.metadata.SearchableCollectionMetadataField;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzo extends zzl<DriveId> implements SearchableCollectionMetadataField<DriveId> {
    public static final zzg zziu = new zzp();

    public zzo(int i2) {
        super("parents", Collections.emptySet(), Arrays.asList("parentsExtra", "dbInstanceId", "parentsExtraHolder"), GmsVersion.VERSION_HALLOUMI);
    }

    @Override // com.google.android.gms.drive.metadata.zzb, com.google.android.gms.drive.metadata.zza
    protected final /* synthetic */ Object zzc(DataHolder dataHolder, int i2, int i3) {
        return zzc(dataHolder, i2, i3);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.drive.metadata.zzb
    /* JADX INFO: renamed from: zzd */
    public final Collection<DriveId> zzc(DataHolder dataHolder, int i2, int i3) {
        Bundle metadata = dataHolder.getMetadata();
        ArrayList parcelableArrayList = metadata.getParcelableArrayList("parentsExtra");
        if (parcelableArrayList == null) {
            if (metadata.getParcelable("parentsExtraHolder") != null) {
                synchronized (dataHolder) {
                    DataHolder dataHolder2 = (DataHolder) dataHolder.getMetadata().getParcelable("parentsExtraHolder");
                    if (dataHolder2 != null) {
                        try {
                            int count = dataHolder.getCount();
                            ArrayList<? extends Parcelable> arrayList = new ArrayList<>(count);
                            HashMap map = new HashMap(count);
                            for (int i4 = 0; i4 < count; i4++) {
                                int windowIndex = dataHolder.getWindowIndex(i4);
                                ParentDriveIdSet parentDriveIdSet = new ParentDriveIdSet();
                                arrayList.add(parentDriveIdSet);
                                map.put(Long.valueOf(dataHolder.getLong("sqlId", i4, windowIndex)), parentDriveIdSet);
                            }
                            Bundle metadata2 = dataHolder2.getMetadata();
                            String string = metadata2.getString("childSqlIdColumn");
                            String string2 = metadata2.getString("parentSqlIdColumn");
                            String string3 = metadata2.getString("parentResIdColumn");
                            int count2 = dataHolder2.getCount();
                            for (int i5 = 0; i5 < count2; i5++) {
                                int windowIndex2 = dataHolder2.getWindowIndex(i5);
                                ((ParentDriveIdSet) map.get(Long.valueOf(dataHolder2.getLong(string, i5, windowIndex2)))).zzit.add(new zzq(dataHolder2.getString(string3, i5, windowIndex2), dataHolder2.getLong(string2, i5, windowIndex2), 1));
                            }
                            dataHolder.getMetadata().putParcelableArrayList("parentsExtra", arrayList);
                            dataHolder2.close();
                            dataHolder.getMetadata().remove("parentsExtraHolder");
                        } catch (Throwable th) {
                            dataHolder2.close();
                            dataHolder.getMetadata().remove("parentsExtraHolder");
                            throw th;
                        }
                    }
                }
                parcelableArrayList = metadata.getParcelableArrayList("parentsExtra");
            }
            if (parcelableArrayList == null) {
                return null;
            }
        }
        long j2 = metadata.getLong("dbInstanceId");
        ParentDriveIdSet parentDriveIdSet2 = (ParentDriveIdSet) parcelableArrayList.get(i2);
        HashSet hashSet = new HashSet();
        for (zzq zzqVar : parentDriveIdSet2.zzit) {
            hashSet.add(new DriveId(zzqVar.zzab, zzqVar.zzac, j2, zzqVar.zzad));
        }
        return hashSet;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.drive.metadata.internal.zzl, com.google.android.gms.drive.metadata.zza
    /* JADX INFO: renamed from: zzc */
    public final Collection<DriveId> zzb(Bundle bundle) {
        Collection collectionZzb = super.zzb(bundle);
        if (collectionZzb == null) {
            return null;
        }
        return new HashSet(collectionZzb);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void zzc(DataHolder dataHolder) {
        Bundle metadata = dataHolder.getMetadata();
        if (metadata == null) {
            return;
        }
        synchronized (dataHolder) {
            try {
                DataHolder dataHolder2 = (DataHolder) metadata.getParcelable("parentsExtraHolder");
                if (dataHolder2 != null) {
                    dataHolder2.close();
                    metadata.remove("parentsExtraHolder");
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
