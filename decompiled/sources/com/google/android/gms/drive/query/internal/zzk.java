package com.google.android.gms.drive.query.internal;

import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.drive.query.Filter;
import java.util.List;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzk implements zzj<Boolean> {
    private Boolean zzlw = Boolean.FALSE;

    private zzk() {
    }

    @Override // com.google.android.gms.drive.query.internal.zzj
    public final /* synthetic */ Boolean zza(com.google.android.gms.drive.metadata.zzb zzbVar, Object obj) {
        return this.zzlw;
    }

    @Override // com.google.android.gms.drive.query.internal.zzj
    public final /* synthetic */ Boolean zzbb() {
        return this.zzlw;
    }

    @Override // com.google.android.gms.drive.query.internal.zzj
    public final /* synthetic */ Boolean zzbc() {
        return this.zzlw;
    }

    @Override // com.google.android.gms.drive.query.internal.zzj
    public final /* synthetic */ Boolean zzc(MetadataField metadataField, Object obj) {
        return this.zzlw;
    }

    @Override // com.google.android.gms.drive.query.internal.zzj
    public final /* synthetic */ Boolean zze(MetadataField metadataField) {
        return this.zzlw;
    }

    @Override // com.google.android.gms.drive.query.internal.zzj
    public final /* synthetic */ Boolean zzg(String str) {
        if (!str.isEmpty()) {
            this.zzlw = Boolean.TRUE;
        }
        return this.zzlw;
    }

    @Override // com.google.android.gms.drive.query.internal.zzj
    public final /* synthetic */ Boolean zza(zzx zzxVar, MetadataField metadataField, Object obj) {
        return this.zzlw;
    }

    @Override // com.google.android.gms.drive.query.internal.zzj
    public final /* synthetic */ Boolean zza(zzx zzxVar, List<Boolean> list) {
        return this.zzlw;
    }

    @Override // com.google.android.gms.drive.query.internal.zzj
    public final /* synthetic */ Boolean zza(Boolean bool) {
        return this.zzlw;
    }

    public static boolean zza(Filter filter) {
        if (filter == null) {
            return false;
        }
        return ((Boolean) filter.zza(new zzk())).booleanValue();
    }
}
