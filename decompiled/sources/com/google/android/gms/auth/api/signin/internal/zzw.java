package com.google.android.gms.auth.api.signin.internal;

import android.content.Context;
import android.os.Binder;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.api.GoogleApiClient;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzw extends zzr {
    private final Context mContext;

    public zzw(Context context) {
        this.mContext = context;
    }

    private final void zzl() {
        if (GooglePlayServicesUtilLight.isGooglePlayServicesUid(this.mContext, Binder.getCallingUid())) {
            return;
        }
        int callingUid = Binder.getCallingUid();
        StringBuilder sb = new StringBuilder(52);
        sb.append("Calling UID ");
        sb.append(callingUid);
        sb.append(" is not Google Play services.");
        throw new SecurityException(sb.toString());
    }

    @Override // com.google.android.gms.auth.api.signin.internal.zzq
    public final void zzj() {
        zzl();
        Storage storage = Storage.getInstance(this.mContext);
        GoogleSignInAccount savedDefaultGoogleSignInAccount = storage.getSavedDefaultGoogleSignInAccount();
        GoogleSignInOptions savedDefaultGoogleSignInOptions = GoogleSignInOptions.DEFAULT_SIGN_IN;
        if (savedDefaultGoogleSignInAccount != null) {
            savedDefaultGoogleSignInOptions = storage.getSavedDefaultGoogleSignInOptions();
        }
        GoogleApiClient googleApiClientBuild = new GoogleApiClient.Builder(this.mContext).addApi(Auth.GOOGLE_SIGN_IN_API, savedDefaultGoogleSignInOptions).build();
        try {
            if (googleApiClientBuild.blockingConnect().isSuccess()) {
                if (savedDefaultGoogleSignInAccount != null) {
                    Auth.GoogleSignInApi.revokeAccess(googleApiClientBuild);
                } else {
                    googleApiClientBuild.clearDefaultAccountAndReconnect();
                }
            }
            googleApiClientBuild.disconnect();
        } catch (Throwable th) {
            googleApiClientBuild.disconnect();
            throw th;
        }
    }

    @Override // com.google.android.gms.auth.api.signin.internal.zzq
    public final void zzk() {
        zzl();
        zzp.zzd(this.mContext).clear();
    }
}
