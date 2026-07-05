package com.google.android.gms.common.internal.service;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class Common {

    @KeepForSdk
    public static final Api<Api.ApiOptions.NoOptions> API;

    @KeepForSdk
    public static final Api.ClientKey<zai> CLIENT_KEY;
    private static final Api.AbstractClientBuilder<zai, Api.ApiOptions.NoOptions> zapg;
    public static final zac zaph;

    static {
        Api.ClientKey<zai> clientKey = new Api.ClientKey<>();
        CLIENT_KEY = clientKey;
        zab zabVar = new zab();
        zapg = zabVar;
        API = new Api<>("Common.API", zabVar, clientKey);
        zaph = new zad();
    }
}
