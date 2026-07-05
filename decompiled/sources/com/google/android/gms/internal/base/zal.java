package com.google.android.gms.internal.base;

import android.os.Handler;
import android.os.Looper;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class zal extends Handler {
    private static volatile zam zaro;

    public zal() {
    }

    public zal(Looper looper) {
        super(looper);
    }

    public zal(Looper looper, Handler.Callback callback) {
        super(looper, callback);
    }
}
