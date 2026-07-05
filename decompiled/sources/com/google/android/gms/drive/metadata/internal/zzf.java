package com.google.android.gms.drive.metadata.internal;

import a.a;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.internal.drive.zzhp;
import com.google.android.gms.internal.drive.zzia;
import com.google.android.gms.internal.drive.zzic;
import com.google.android.gms.internal.drive.zzik;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzf {
    private static final Map<String, MetadataField<?>> zzip = new HashMap();
    private static final Map<String, zzg> zziq = new HashMap();

    static {
        zzb(zzhp.zziv);
        zzb(zzhp.zzkb);
        zzb(zzhp.zzjs);
        zzb(zzhp.zzjz);
        zzb(zzhp.zzkc);
        zzb(zzhp.zzji);
        zzb(zzhp.zzjh);
        zzb(zzhp.zzjj);
        zzb(zzhp.zzjk);
        zzb(zzhp.zzjl);
        zzb(zzhp.zzjf);
        zzb(zzhp.zzjn);
        zzb(zzhp.zzjo);
        zzb(zzhp.zzjp);
        zzb(zzhp.zzjx);
        zzb(zzhp.zziw);
        zzb(zzhp.zzju);
        zzb(zzhp.zziy);
        zzb(zzhp.zzjg);
        zzb(zzhp.zziz);
        zzb(zzhp.zzja);
        zzb(zzhp.zzjb);
        zzb(zzhp.zzjc);
        zzb(zzhp.zzjr);
        zzb(zzhp.zzjm);
        zzb(zzhp.zzjt);
        zzb(zzhp.zzjv);
        zzb(zzhp.zzjw);
        zzb(zzhp.zzjy);
        zzb(zzhp.zzkd);
        zzb(zzhp.zzke);
        zzb(zzhp.zzje);
        zzb(zzhp.zzjd);
        zzb(zzhp.zzka);
        zzb(zzhp.zzjq);
        zzb(zzhp.zzix);
        zzb(zzhp.zzkf);
        zzb(zzhp.zzkg);
        zzb(zzhp.zzkh);
        zzb(zzhp.zzki);
        zzb(zzhp.zzkj);
        zzb(zzhp.zzkk);
        zzb(zzhp.zzkl);
        zzb(zzic.zzkn);
        zzb(zzic.zzkp);
        zzb(zzic.zzkq);
        zzb(zzic.zzkr);
        zzb(zzic.zzko);
        zzb(zzic.zzks);
        zzb(zzik.zzku);
        zzb(zzik.zzkv);
        zza(zzo.zziu);
        zza(zzia.zzkm);
    }

    public static void zza(DataHolder dataHolder) {
        Iterator<zzg> it = zziq.values().iterator();
        while (it.hasNext()) {
            it.next().zzb(dataHolder);
        }
    }

    public static Collection<MetadataField<?>> zzau() {
        return Collections.unmodifiableCollection(zzip.values());
    }

    private static void zzb(MetadataField<?> metadataField) {
        Map<String, MetadataField<?>> map = zzip;
        if (map.containsKey(metadataField.getName())) {
            String strValueOf = String.valueOf(metadataField.getName());
            throw new IllegalArgumentException(strValueOf.length() != 0 ? "Duplicate field name registered: ".concat(strValueOf) : new String("Duplicate field name registered: "));
        }
        map.put(metadataField.getName(), metadataField);
    }

    public static MetadataField<?> zzd(String str) {
        return zzip.get(str);
    }

    private static void zza(zzg zzgVar) {
        if (zziq.put(zzgVar.zzav(), zzgVar) == null) {
            return;
        }
        String strZzav = zzgVar.zzav();
        StringBuilder sb = new StringBuilder(a.e(46, strZzav));
        sb.append("A cleaner for key ");
        sb.append(strZzav);
        sb.append(" has already been registered");
        throw new IllegalStateException(sb.toString());
    }
}
