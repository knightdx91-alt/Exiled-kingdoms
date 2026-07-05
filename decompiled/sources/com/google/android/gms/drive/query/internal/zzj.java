package com.google.android.gms.drive.query.internal;

import com.google.android.gms.drive.metadata.MetadataField;
import java.util.List;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public interface zzj<F> {
    <T> F zza(com.google.android.gms.drive.metadata.zzb<T> zzbVar, T t2);

    <T> F zza(zzx zzxVar, MetadataField<T> metadataField, T t2);

    F zza(zzx zzxVar, List<F> list);

    F zza(F f2);

    F zzbb();

    F zzbc();

    <T> F zzc(MetadataField<T> metadataField, T t2);

    F zze(MetadataField<?> metadataField);

    F zzg(String str);
}
