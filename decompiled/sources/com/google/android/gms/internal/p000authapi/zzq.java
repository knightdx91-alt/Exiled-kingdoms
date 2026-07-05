package com.google.android.gms.internal.p000authapi;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.credentials.HintRequest;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzq {
    public static PendingIntent zzc(Context context, Auth.AuthCredentialsOptions authCredentialsOptions, HintRequest hintRequest) {
        Preconditions.checkNotNull(context, "context must not be null");
        Preconditions.checkNotNull(hintRequest, "request must not be null");
        Intent intentPutExtra = new Intent("com.google.android.gms.auth.api.credentials.PICKER").putExtra("claimedCallingPackage", (String) null);
        SafeParcelableSerializer.serializeToIntentExtra(hintRequest, intentPutExtra, "com.google.android.gms.credentials.HintRequest");
        return PendingIntent.getActivity(context, 2000, intentPutExtra, 134217728);
    }
}
