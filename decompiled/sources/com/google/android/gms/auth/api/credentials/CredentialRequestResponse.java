package com.google.android.gms.auth.api.credentials;

import com.google.android.gms.common.api.Response;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class CredentialRequestResponse extends Response<CredentialRequestResult> {
    public Credential getCredential() {
        return getResult().getCredential();
    }
}
