package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.internal.ICancelToken;
import com.google.android.gms.drive.events.ListenerToken;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzg implements ListenerToken {
    private final ListenerHolder.ListenerKey zzcw;
    private ICancelToken zzcx = null;

    public zzg(ListenerHolder.ListenerKey listenerKey) {
        this.zzcw = listenerKey;
    }

    public final boolean cancel() {
        ICancelToken iCancelToken = this.zzcx;
        if (iCancelToken == null) {
            return false;
        }
        try {
            iCancelToken.cancel();
            return true;
        } catch (RemoteException unused) {
            return false;
        }
    }

    public final void setCancelToken(ICancelToken iCancelToken) {
        this.zzcx = iCancelToken;
    }

    public final ListenerHolder.ListenerKey zzac() {
        return this.zzcw;
    }
}
