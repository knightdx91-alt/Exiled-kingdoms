package com.google.android.gms.internal.p001authapiphone;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.auth.api.phone.SmsRetrieverClient;
import com.google.android.gms.tasks.Task;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzj extends SmsRetrieverClient {
    public zzj(Activity activity) {
        super(activity);
    }

    @Override // com.google.android.gms.auth.api.phone.SmsRetrieverClient, com.google.android.gms.auth.api.phone.SmsRetrieverApi
    public final Task<Void> startSmsRetriever() {
        return doWrite(new zzk(this));
    }

    public zzj(Context context) {
        super(context);
    }
}
