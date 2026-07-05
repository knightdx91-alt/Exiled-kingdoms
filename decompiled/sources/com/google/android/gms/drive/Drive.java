package com.google.android.gms.drive;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.drive.zzaf;
import com.google.android.gms.internal.drive.zzaw;
import com.google.android.gms.internal.drive.zzbb;
import com.google.android.gms.internal.drive.zzbr;
import com.google.android.gms.internal.drive.zzcb;
import com.google.android.gms.internal.drive.zzch;
import com.google.android.gms.internal.drive.zzeb;
import java.util.Set;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class Drive {

    @Deprecated
    public static final Api<Api.ApiOptions.NoOptions> API;
    private static final Api.AbstractClientBuilder<zzaw, Api.ApiOptions.NoOptions> CLIENT_BUILDER;
    public static final Api.ClientKey<zzaw> CLIENT_KEY;

    @Deprecated
    public static final DriveApi DriveApi;

    @Deprecated
    public static final DrivePreferencesApi DrivePreferencesApi;
    private static final Api<zzb> INTERNAL_API;
    public static final Scope SCOPE_APPFOLDER;
    public static final Scope SCOPE_FILE;
    private static final Api.AbstractClientBuilder<zzaw, zzb> zzq;
    private static final Api.AbstractClientBuilder<zzaw, zza> zzr;
    private static final Scope zzs;
    private static final Scope zzt;
    public static final Api<zza> zzu;

    @Deprecated
    private static final zzj zzv;
    private static final zzl zzw;

    public static class zza implements Api.ApiOptions.HasGoogleSignInAccountOptions {
        private final Bundle zzx = new Bundle();
        private final GoogleSignInAccount zzy;

        public zza(GoogleSignInAccount googleSignInAccount) {
            this.zzy = googleSignInAccount;
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj != null && obj.getClass() == zza.class) {
                zza zzaVar = (zza) obj;
                if (!Objects.equal(this.zzy, zzaVar.getGoogleSignInAccount())) {
                    return false;
                }
                String string = this.zzx.getString("method_trace_filename");
                String string2 = zzaVar.zzx.getString("method_trace_filename");
                if (((string == null && string2 == null) || (string != null && string2 != null && string.equals(string2))) && this.zzx.getBoolean("bypass_initial_sync") == zzaVar.zzx.getBoolean("bypass_initial_sync") && this.zzx.getInt("proxy_type") == zzaVar.zzx.getInt("proxy_type")) {
                    return true;
                }
            }
            return false;
        }

        @Override // com.google.android.gms.common.api.Api.ApiOptions.HasGoogleSignInAccountOptions
        public final GoogleSignInAccount getGoogleSignInAccount() {
            return this.zzy;
        }

        public final int hashCode() {
            return Objects.hashCode(this.zzy, this.zzx.getString("method_trace_filename", ""), Integer.valueOf(this.zzx.getInt("proxy_type")), Boolean.valueOf(this.zzx.getBoolean("bypass_initial_sync")));
        }

        public final Bundle zzg() {
            return this.zzx;
        }
    }

    public static class zzb implements Api.ApiOptions.Optional {
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [com.google.android.gms.drive.zzj, com.google.android.gms.internal.drive.zzbr] */
    /* JADX WARN: Type inference failed for: r0v3, types: [com.google.android.gms.drive.zzl, com.google.android.gms.internal.drive.zzeb] */
    static {
        Api.ClientKey<zzaw> clientKey = new Api.ClientKey<>();
        CLIENT_KEY = clientKey;
        zze zzeVar = new zze();
        CLIENT_BUILDER = zzeVar;
        zzf zzfVar = new zzf();
        zzq = zzfVar;
        zzg zzgVar = new zzg();
        zzr = zzgVar;
        SCOPE_FILE = new Scope(Scopes.DRIVE_FILE);
        SCOPE_APPFOLDER = new Scope(Scopes.DRIVE_APPFOLDER);
        zzs = new Scope(Scopes.DRIVE_FULL);
        zzt = new Scope(Scopes.DRIVE_APPS);
        API = new Api<>("Drive.API", zzeVar, clientKey);
        INTERNAL_API = new Api<>("Drive.INTERNAL_API", zzfVar, clientKey);
        zzu = new Api<>("Drive.API_CONNECTIONLESS", zzgVar, clientKey);
        DriveApi = new zzaf();
        zzv = new zzbr();
        zzw = new zzeb();
        DrivePreferencesApi = new zzcb();
    }

    private Drive() {
    }

    public static DriveClient getDriveClient(Activity activity, GoogleSignInAccount googleSignInAccount) {
        zza(googleSignInAccount);
        return new zzbb(activity, new zza(googleSignInAccount));
    }

    public static DriveResourceClient getDriveResourceClient(Activity activity, GoogleSignInAccount googleSignInAccount) {
        zza(googleSignInAccount);
        return new zzch(activity, new zza(googleSignInAccount));
    }

    private static void zza(GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount);
        Set<Scope> requestedScopes = googleSignInAccount.getRequestedScopes();
        Preconditions.checkArgument(requestedScopes.contains(SCOPE_FILE) || requestedScopes.contains(SCOPE_APPFOLDER) || requestedScopes.contains(zzs) || requestedScopes.contains(zzt), "You must request a Drive scope in order to interact with the Drive API.");
    }

    public static DriveClient getDriveClient(Context context, GoogleSignInAccount googleSignInAccount) {
        zza(googleSignInAccount);
        return new zzbb(context, new zza(googleSignInAccount));
    }

    public static DriveResourceClient getDriveResourceClient(Context context, GoogleSignInAccount googleSignInAccount) {
        zza(googleSignInAccount);
        return new zzch(context, new zza(googleSignInAccount));
    }
}
