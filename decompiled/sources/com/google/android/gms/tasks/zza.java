package com.google.android.gms.tasks;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zza extends CancellationToken {
    private final zzu<Void> zza = new zzu<>();

    zza() {
    }

    public final void cancel() {
        this.zza.trySetResult(null);
    }

    @Override // com.google.android.gms.tasks.CancellationToken
    public final boolean isCancellationRequested() {
        return this.zza.isComplete();
    }

    @Override // com.google.android.gms.tasks.CancellationToken
    public final CancellationToken onCanceledRequested(OnTokenCanceledListener onTokenCanceledListener) {
        this.zza.addOnSuccessListener(new zzb(this, onTokenCanceledListener));
        return this;
    }
}
