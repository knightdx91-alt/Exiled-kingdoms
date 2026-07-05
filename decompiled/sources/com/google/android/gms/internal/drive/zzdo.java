package com.google.android.gms.internal.drive;

import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.drive.events.OpenFileCallback;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzdo implements ListenerHolder.Notifier<OpenFileCallback> {
    private final /* synthetic */ zzdg zzgn;

    zzdo(zzdk zzdkVar, zzdg zzdgVar) {
        this.zzgn = zzdgVar;
    }

    @Override // com.google.android.gms.common.api.internal.ListenerHolder.Notifier
    public final /* synthetic */ void notifyListener(OpenFileCallback openFileCallback) {
        this.zzgn.accept(openFileCallback);
    }

    @Override // com.google.android.gms.common.api.internal.ListenerHolder.Notifier
    public final void onNotifyListenerFailed() {
    }
}
