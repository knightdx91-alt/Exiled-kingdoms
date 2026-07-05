package com.google.android.gms.drive;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@Deprecated
public final class zzn extends ExecutionOptions {
    private boolean zzar;

    private zzn(String str, boolean z2, int i2, boolean z3) {
        super(str, z2, i2);
        this.zzar = z3;
    }

    public static zzn zza(ExecutionOptions executionOptions) {
        zzp zzpVar = new zzp();
        if (executionOptions != null) {
            zzpVar.setConflictStrategy(executionOptions.zzm());
            zzpVar.setNotifyOnCompletion(executionOptions.zzl());
            String strZzk = executionOptions.zzk();
            if (strZzk != null) {
                zzpVar.setTrackingTag(strZzk);
            }
        }
        return (zzn) zzpVar.build();
    }

    public final boolean zzo() {
        return this.zzar;
    }
}
