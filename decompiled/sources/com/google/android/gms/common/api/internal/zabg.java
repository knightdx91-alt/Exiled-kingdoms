package com.google.android.gms.common.api.internal;

import android.os.Looper;
import android.os.Message;
import android.util.Log;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zabg extends com.google.android.gms.internal.base.zal {
    private final /* synthetic */ zabe zahu;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zabg(zabe zabeVar, Looper looper) {
        super(looper);
        this.zahu = zabeVar;
    }

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        int i2 = message.what;
        if (i2 == 1) {
            ((zabf) message.obj).zac(this.zahu);
        } else {
            if (i2 == 2) {
                throw ((RuntimeException) message.obj);
            }
            StringBuilder sb = new StringBuilder(31);
            sb.append("Unknown message id: ");
            sb.append(i2);
            Log.w("GACStateManager", sb.toString());
        }
    }
}
