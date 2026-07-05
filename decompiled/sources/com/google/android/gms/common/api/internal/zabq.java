package com.google.android.gms.common.api.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zabq extends BroadcastReceiver {
    private Context mContext;
    private final zabr zajh;

    public zabq(zabr zabrVar) {
        this.zajh = zabrVar;
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        Uri data = intent.getData();
        if ("com.google.android.gms".equals(data != null ? data.getSchemeSpecificPart() : null)) {
            this.zajh.zas();
            unregister();
        }
    }

    public final synchronized void unregister() {
        try {
            Context context = this.mContext;
            if (context != null) {
                context.unregisterReceiver(this);
            }
            this.mContext = null;
        } catch (Throwable th) {
            throw th;
        }
    }

    public final void zac(Context context) {
        this.mContext = context;
    }
}
