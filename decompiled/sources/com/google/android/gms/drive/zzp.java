package com.google.android.gms.drive;

import com.google.android.gms.drive.ExecutionOptions;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzp extends ExecutionOptions.Builder {
    private boolean zzar = true;

    @Override // com.google.android.gms.drive.ExecutionOptions.Builder
    public final /* synthetic */ ExecutionOptions build() {
        zzn();
        return new zzn(this.zzao, this.zzap, this.zzaq, this.zzar);
    }

    @Override // com.google.android.gms.drive.ExecutionOptions.Builder
    public final /* synthetic */ ExecutionOptions.Builder setConflictStrategy(int i2) {
        super.setConflictStrategy(i2);
        return this;
    }

    @Override // com.google.android.gms.drive.ExecutionOptions.Builder
    public final /* synthetic */ ExecutionOptions.Builder setNotifyOnCompletion(boolean z2) {
        super.setNotifyOnCompletion(z2);
        return this;
    }

    @Override // com.google.android.gms.drive.ExecutionOptions.Builder
    public final /* synthetic */ ExecutionOptions.Builder setTrackingTag(String str) {
        super.setTrackingTag(str);
        return this;
    }
}
