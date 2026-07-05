package com.google.android.gms.auth.api.signin.internal;

import android.accounts.Account;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.PendingResults;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.GoogleApiManager;
import com.google.android.gms.common.api.internal.OptionalPendingResultImpl;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.logging.Logger;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzh {
    private static Logger zzbd = new Logger("GoogleSignInCommon", new String[0]);

    public static GoogleSignInResult getSignInResultFromIntent(Intent intent) {
        if (intent == null) {
            return null;
        }
        if (!intent.hasExtra("googleSignInStatus") && !intent.hasExtra("googleSignInAccount")) {
            return null;
        }
        GoogleSignInAccount googleSignInAccount = (GoogleSignInAccount) intent.getParcelableExtra("googleSignInAccount");
        Status status = (Status) intent.getParcelableExtra("googleSignInStatus");
        if (googleSignInAccount != null) {
            status = Status.RESULT_SUCCESS;
        }
        return new GoogleSignInResult(googleSignInAccount, status);
    }

    public static Intent zzc(Context context, GoogleSignInOptions googleSignInOptions) {
        zzbd.d("getSignInIntent()", new Object[0]);
        SignInConfiguration signInConfiguration = new SignInConfiguration(context.getPackageName(), googleSignInOptions);
        Intent intent = new Intent("com.google.android.gms.auth.GOOGLE_SIGN_IN");
        intent.setPackage(context.getPackageName());
        intent.setClass(context, SignInHubActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("config", signInConfiguration);
        intent.putExtra("config", bundle);
        return intent;
    }

    public static Intent zzd(Context context, GoogleSignInOptions googleSignInOptions) {
        zzbd.d("getFallbackSignInIntent()", new Object[0]);
        Intent intentZzc = zzc(context, googleSignInOptions);
        intentZzc.setAction("com.google.android.gms.auth.APPAUTH_SIGN_IN");
        return intentZzc;
    }

    public static Intent zze(Context context, GoogleSignInOptions googleSignInOptions) {
        zzbd.d("getNoImplementationSignInIntent()", new Object[0]);
        Intent intentZzc = zzc(context, googleSignInOptions);
        intentZzc.setAction("com.google.android.gms.auth.NO_IMPL");
        return intentZzc;
    }

    public static PendingResult<Status> zzd(GoogleApiClient googleApiClient, Context context, boolean z2) {
        zzbd.d("Revoking access", new Object[0]);
        String savedRefreshToken = Storage.getInstance(context).getSavedRefreshToken();
        zzc(context);
        if (z2) {
            return zzd.zzc(savedRefreshToken);
        }
        return googleApiClient.execute(new zzm(googleApiClient));
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0087  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static OptionalPendingResult<GoogleSignInResult> zzc(GoogleApiClient googleApiClient, Context context, GoogleSignInOptions googleSignInOptions, boolean z2) {
        GoogleSignInResult googleSignInResult;
        boolean zEquals;
        GoogleSignInAccount googleSignInAccountZzh;
        zzbd.d("silentSignIn()", new Object[0]);
        zzbd.d("getEligibleSavedSignInResult()", new Object[0]);
        Preconditions.checkNotNull(googleSignInOptions);
        GoogleSignInOptions googleSignInOptionsZzi = zzp.zzd(context).zzi();
        if (googleSignInOptionsZzi != null) {
            Account account = googleSignInOptionsZzi.getAccount();
            Account account2 = googleSignInOptions.getAccount();
            if (account == null) {
                zEquals = account2 == null;
            } else {
                zEquals = account.equals(account2);
            }
            googleSignInResult = (!zEquals || googleSignInOptions.isServerAuthCodeRequested() || (googleSignInOptions.isIdTokenRequested() && !(googleSignInOptionsZzi.isIdTokenRequested() && googleSignInOptions.getServerClientId().equals(googleSignInOptionsZzi.getServerClientId()))) || !new HashSet(googleSignInOptionsZzi.getScopes()).containsAll(new HashSet(googleSignInOptions.getScopes())) || (googleSignInAccountZzh = zzp.zzd(context).zzh()) == null || googleSignInAccountZzh.isExpired()) ? null : new GoogleSignInResult(googleSignInAccountZzh, Status.RESULT_SUCCESS);
        }
        if (googleSignInResult != null) {
            zzbd.d("Eligible saved sign in result found", new Object[0]);
            return PendingResults.immediatePendingResult(googleSignInResult, googleApiClient);
        }
        if (z2) {
            return PendingResults.immediatePendingResult(new GoogleSignInResult(null, new Status(4)), googleApiClient);
        }
        zzbd.d("trySilentSignIn()", new Object[0]);
        return new OptionalPendingResultImpl(googleApiClient.enqueue(new zzi(googleApiClient, context, googleSignInOptions)));
    }

    public static PendingResult<Status> zzc(GoogleApiClient googleApiClient, Context context, boolean z2) {
        zzbd.d("Signing out", new Object[0]);
        zzc(context);
        if (z2) {
            return PendingResults.immediatePendingResult(Status.RESULT_SUCCESS, googleApiClient);
        }
        return googleApiClient.execute(new zzk(googleApiClient));
    }

    private static void zzc(Context context) {
        zzp.zzd(context).clear();
        Iterator<GoogleApiClient> it = GoogleApiClient.getAllClients().iterator();
        while (it.hasNext()) {
            it.next().maybeSignOut();
        }
        GoogleApiManager.reportSignOut();
    }
}
