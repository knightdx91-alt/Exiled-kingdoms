package com.google.android.gms.common.api;

import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Result;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public abstract class ResultCallbacks<R extends Result> implements ResultCallback<R> {
    public abstract void onFailure(Status status);

    @Override // com.google.android.gms.common.api.ResultCallback
    @KeepForSdk
    public final void onResult(R r2) {
        Status status = r2.getStatus();
        if (status.isSuccess()) {
            onSuccess(r2);
            return;
        }
        onFailure(status);
        if (r2 instanceof Releasable) {
            try {
                ((Releasable) r2).release();
            } catch (RuntimeException e2) {
                String strValueOf = String.valueOf(r2);
                StringBuilder sb = new StringBuilder(strValueOf.length() + 18);
                sb.append("Unable to release ");
                sb.append(strValueOf);
                Log.w("ResultCallbacks", sb.toString(), e2);
            }
        }
    }

    public abstract void onSuccess(R r2);
}
