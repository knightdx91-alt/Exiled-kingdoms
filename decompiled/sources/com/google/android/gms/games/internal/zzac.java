package com.google.android.gms.games.internal;

import android.os.Binder;
import android.view.View;
import com.google.android.gms.common.util.PlatformVersion;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class zzac {
    protected zze zzjc;
    protected zzae zzjd;

    private zzac(zze zzeVar, int i2) {
        this.zzjc = zzeVar;
        zzm(i2);
    }

    public static zzac zza(zze zzeVar, int i2) {
        return PlatformVersion.isAtLeastHoneycombMR1() ? new zzaf(zzeVar, i2) : new zzac(zzeVar, i2);
    }

    public void zzb(View view) {
    }

    public void zzbj() {
        zze zzeVar = this.zzjc;
        zzae zzaeVar = this.zzjd;
        zzeVar.zza(zzaeVar.zzjb, zzaeVar.zzbk());
    }

    protected void zzm(int i2) {
        this.zzjd = new zzae(i2, new Binder());
    }
}
