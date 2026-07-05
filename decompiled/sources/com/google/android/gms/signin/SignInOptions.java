package com.google.android.gms.signin;

import com.google.android.gms.common.api.Api;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class SignInOptions implements Api.ApiOptions.Optional {
    public static final SignInOptions DEFAULT;
    private final boolean zars = false;
    private final boolean zay = false;
    private final String zaab = null;
    private final boolean zaaa = false;
    private final boolean zart = false;
    private final String zaac = null;
    private final Long zaru = null;
    private final Long zarv = null;

    public static final class zaa {
    }

    static {
        new zaa();
        DEFAULT = new SignInOptions(false, false, null, false, null, false, null, null);
    }

    private SignInOptions(boolean z2, boolean z3, String str, boolean z4, String str2, boolean z5, Long l2, Long l3) {
    }

    public final Long getAuthApiSignInModuleVersion() {
        return this.zaru;
    }

    public final String getHostedDomain() {
        return this.zaac;
    }

    public final Long getRealClientLibraryVersion() {
        return this.zarv;
    }

    public final String getServerClientId() {
        return this.zaab;
    }

    public final boolean isForceCodeForRefreshToken() {
        return this.zaaa;
    }

    public final boolean isIdTokenRequested() {
        return this.zay;
    }

    public final boolean isOfflineAccessRequested() {
        return this.zars;
    }

    public final boolean waitForAccessTokenRefresh() {
        return this.zart;
    }
}
