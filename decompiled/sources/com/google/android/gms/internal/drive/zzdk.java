package com.google.android.gms.internal.drive;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.internal.ApiExceptionUtil;
import com.google.android.gms.drive.events.ListenerToken;
import com.google.android.gms.drive.events.OpenFileCallback;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzdk extends zzl {
    private final /* synthetic */ zzch zzfu;
    private final ListenerToken zzgh;
    private final ListenerHolder<OpenFileCallback> zzgi;

    zzdk(zzch zzchVar, ListenerToken listenerToken, ListenerHolder<OpenFileCallback> listenerHolder) {
        this.zzfu = zzchVar;
        this.zzgh = listenerToken;
        this.zzgi = listenerHolder;
    }

    @Override // com.google.android.gms.internal.drive.zzl, com.google.android.gms.internal.drive.zzeq
    public final void zza(final Status status) {
        zza(new zzdg(this, status) { // from class: com.google.android.gms.internal.drive.zzdl
            private final zzdk zzgj;
            private final Status zzgk;

            {
                this.zzgj = this;
                this.zzgk = status;
            }

            @Override // com.google.android.gms.internal.drive.zzdg
            public final void accept(Object obj) {
                this.zzgj.zza(this.zzgk, (OpenFileCallback) obj);
            }
        });
    }

    final /* synthetic */ void zza(Status status, OpenFileCallback openFileCallback) {
        openFileCallback.onError(ApiExceptionUtil.fromStatus(status));
        this.zzfu.cancelOpenFileCallback(this.zzgh);
    }

    private final void zza(zzdg<OpenFileCallback> zzdgVar) {
        this.zzgi.notifyListener(new zzdo(this, zzdgVar));
    }

    @Override // com.google.android.gms.internal.drive.zzl, com.google.android.gms.internal.drive.zzeq
    public final void zza(final zzfb zzfbVar) {
        zza(new zzdg(this, zzfbVar) { // from class: com.google.android.gms.internal.drive.zzdn
            private final zzdk zzgj;
            private final zzfb zzgm;

            {
                this.zzgj = this;
                this.zzgm = zzfbVar;
            }

            @Override // com.google.android.gms.internal.drive.zzdg
            public final void accept(Object obj) {
                this.zzgj.zza(this.zzgm, (OpenFileCallback) obj);
            }
        });
    }

    final /* synthetic */ void zza(zzfb zzfbVar, OpenFileCallback openFileCallback) {
        openFileCallback.onContents(new zzbi(zzfbVar.zzeq));
        this.zzfu.cancelOpenFileCallback(this.zzgh);
    }

    @Override // com.google.android.gms.internal.drive.zzl, com.google.android.gms.internal.drive.zzeq
    public final void zza(final zzff zzffVar) {
        zza(new zzdg(zzffVar) { // from class: com.google.android.gms.internal.drive.zzdm
            private final zzff zzgl;

            {
                this.zzgl = zzffVar;
            }

            @Override // com.google.android.gms.internal.drive.zzdg
            public final void accept(Object obj) {
                zzff zzffVar2 = this.zzgl;
                ((OpenFileCallback) obj).onProgress(zzffVar2.zzhi, zzffVar2.zzhj);
            }
        });
    }
}
