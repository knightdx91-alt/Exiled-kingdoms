package com.google.android.gms.drive.metadata.internal;

import android.os.Bundle;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.util.GmsVersion;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import org.json.JSONArray;
import org.json.JSONException;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzs extends com.google.android.gms.drive.metadata.zzb<String> {
    public zzs(String str, int i2) {
        super(str, Collections.singleton(str), Collections.emptySet(), GmsVersion.VERSION_JARLSBERG);
    }

    @Override // com.google.android.gms.drive.metadata.zza
    protected final /* synthetic */ void zza(Bundle bundle, Object obj) {
        bundle.putStringArrayList(getName(), new ArrayList<>((Collection) obj));
    }

    @Override // com.google.android.gms.drive.metadata.zza
    protected final /* synthetic */ Object zzb(Bundle bundle) {
        return bundle.getStringArrayList(getName());
    }

    @Override // com.google.android.gms.drive.metadata.zzb, com.google.android.gms.drive.metadata.zza
    protected final /* synthetic */ Object zzc(DataHolder dataHolder, int i2, int i3) {
        return zzc(dataHolder, i2, i3);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.drive.metadata.zzb
    /* JADX INFO: renamed from: zzd */
    public final Collection<String> zzc(DataHolder dataHolder, int i2, int i3) {
        try {
            String string = dataHolder.getString(getName(), i2, i3);
            if (string == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            JSONArray jSONArray = new JSONArray(string);
            for (int i4 = 0; i4 < jSONArray.length(); i4++) {
                arrayList.add(jSONArray.getString(i4));
            }
            return Collections.unmodifiableCollection(arrayList);
        } catch (JSONException e2) {
            throw new IllegalStateException("DataHolder supplied invalid JSON", e2);
        }
    }
}
