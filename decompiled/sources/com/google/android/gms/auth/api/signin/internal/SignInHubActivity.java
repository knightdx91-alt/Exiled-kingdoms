package com.google.android.gms.auth.api.signin.internal;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.y;
import android.support.v4.content.b;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInApi;
import com.google.android.gms.auth.api.signin.GoogleSignInStatusCodes;
import com.google.android.gms.auth.api.signin.SignInAccount;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@KeepName
public class SignInHubActivity extends FragmentActivity {
    private static boolean zzbt;
    private boolean zzbu = false;
    private SignInConfiguration zzbv;
    private boolean zzbw;
    private int zzbx;
    private Intent zzby;

    private class zzc implements y.a<Void> {
        private zzc() {
        }

        @Override // android.support.v4.app.y.a
        public final b<Void> onCreateLoader(int i2, Bundle bundle) {
            return new zze(SignInHubActivity.this, GoogleApiClient.getAllClients());
        }

        @Override // android.support.v4.app.y.a
        public final /* synthetic */ void onLoadFinished(b<Void> bVar, Void r3) {
            SignInHubActivity signInHubActivity = SignInHubActivity.this;
            signInHubActivity.setResult(signInHubActivity.zzbx, SignInHubActivity.this.zzby);
            SignInHubActivity.this.finish();
        }

        @Override // android.support.v4.app.y.a
        public final void onLoaderReset(b<Void> bVar) {
        }
    }

    private final void zzc(int i2) {
        Status status = new Status(i2);
        Intent intent = new Intent();
        intent.putExtra("googleSignInStatus", status);
        setResult(0, intent);
        finish();
        zzbt = false;
    }

    private final void zzn() {
        getSupportLoaderManager().a(new zzc());
        zzbt = false;
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        return true;
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    protected void onActivityResult(int i2, int i3, Intent intent) {
        if (this.zzbu) {
            return;
        }
        setResult(0);
        if (i2 != 40962) {
            return;
        }
        if (intent != null) {
            SignInAccount signInAccount = (SignInAccount) intent.getParcelableExtra(GoogleSignInApi.EXTRA_SIGN_IN_ACCOUNT);
            if (signInAccount != null && signInAccount.getGoogleSignInAccount() != null) {
                GoogleSignInAccount googleSignInAccount = signInAccount.getGoogleSignInAccount();
                zzp.zzd(this).zzc(this.zzbv.zzm(), googleSignInAccount);
                intent.removeExtra(GoogleSignInApi.EXTRA_SIGN_IN_ACCOUNT);
                intent.putExtra("googleSignInAccount", googleSignInAccount);
                this.zzbw = true;
                this.zzbx = i3;
                this.zzby = intent;
                zzn();
                return;
            }
            if (intent.hasExtra("errorCode")) {
                int intExtra = intent.getIntExtra("errorCode", 8);
                if (intExtra == 13) {
                    intExtra = GoogleSignInStatusCodes.SIGN_IN_CANCELLED;
                }
                zzc(intExtra);
                return;
            }
        }
        zzc(8);
    }

    @Override // android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        String action = intent.getAction();
        if ("com.google.android.gms.auth.NO_IMPL".equals(action)) {
            zzc(GoogleSignInStatusCodes.SIGN_IN_FAILED);
            return;
        }
        if (!action.equals("com.google.android.gms.auth.GOOGLE_SIGN_IN") && !action.equals("com.google.android.gms.auth.APPAUTH_SIGN_IN")) {
            String strValueOf = String.valueOf(intent.getAction());
            Log.e("AuthSignInClient", strValueOf.length() != 0 ? "Unknown action: ".concat(strValueOf) : new String("Unknown action: "));
            finish();
            return;
        }
        SignInConfiguration signInConfiguration = (SignInConfiguration) intent.getBundleExtra("config").getParcelable("config");
        this.zzbv = signInConfiguration;
        if (signInConfiguration == null) {
            Log.e("AuthSignInClient", "Activity started with invalid configuration.");
            setResult(0);
            finish();
            return;
        }
        if (bundle != null) {
            boolean z2 = bundle.getBoolean("signingInGoogleApiClients");
            this.zzbw = z2;
            if (z2) {
                this.zzbx = bundle.getInt("signInResultCode");
                this.zzby = (Intent) bundle.getParcelable("signInResultData");
                zzn();
                return;
            }
            return;
        }
        if (zzbt) {
            setResult(0);
            zzc(GoogleSignInStatusCodes.SIGN_IN_CURRENTLY_IN_PROGRESS);
            return;
        }
        zzbt = true;
        Intent intent2 = new Intent(action);
        if (action.equals("com.google.android.gms.auth.GOOGLE_SIGN_IN")) {
            intent2.setPackage("com.google.android.gms");
        } else {
            intent2.setPackage(getPackageName());
        }
        intent2.putExtra("config", this.zzbv);
        try {
            startActivityForResult(intent2, 40962);
        } catch (ActivityNotFoundException unused) {
            this.zzbu = true;
            Log.w("AuthSignInClient", "Could not launch sign in Intent. Google Play Service is probably being updated...");
            zzc(17);
        }
    }

    @Override // android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("signingInGoogleApiClients", this.zzbw);
        if (this.zzbw) {
            bundle.putInt("signInResultCode", this.zzbx);
            bundle.putParcelable("signInResultData", this.zzby);
        }
    }
}
