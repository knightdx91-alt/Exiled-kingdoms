package com.google.android.gms.games;

import com.google.android.gms.common.api.internal.ListenerHolder;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzbp implements ListenerHolder.Notifier<com.google.android.gms.games.multiplayer.realtime.zzh> {
    private final /* synthetic */ zzbo zzds;

    zzbp(zzbo zzboVar) {
        this.zzds = zzboVar;
    }

    @Override // com.google.android.gms.common.api.internal.ListenerHolder.Notifier
    public final /* synthetic */ void notifyListener(com.google.android.gms.games.multiplayer.realtime.zzh zzhVar) {
        zzhVar.onLeftRoom(0, this.zzds.zzdr.zzdg);
    }

    @Override // com.google.android.gms.common.api.internal.ListenerHolder.Notifier
    public final void onNotifyListenerFailed() {
        this.zzds.zzdr.zzdo.getRoomUpdateCallback().onLeftRoom(0, this.zzds.zzdr.zzdg);
    }
}
