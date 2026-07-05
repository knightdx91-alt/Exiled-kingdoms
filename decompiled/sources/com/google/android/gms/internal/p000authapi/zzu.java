package com.google.android.gms.internal.p000authapi;

import android.os.IInterface;
import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.common.api.Status;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public interface zzu extends IInterface {
    void zzc(Status status);

    void zzc(Status status, Credential credential);

    void zzc(Status status, String str);
}
