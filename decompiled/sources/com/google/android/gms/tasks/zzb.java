package com.google.android.gms.tasks;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzb implements OnSuccessListener<Void> {
    private final /* synthetic */ OnTokenCanceledListener zzb;

    zzb(zza zzaVar, OnTokenCanceledListener onTokenCanceledListener) {
        this.zzb = onTokenCanceledListener;
    }

    @Override // com.google.android.gms.tasks.OnSuccessListener
    public final /* synthetic */ void onSuccess(Void r1) {
        this.zzb.onCanceled();
    }
}
