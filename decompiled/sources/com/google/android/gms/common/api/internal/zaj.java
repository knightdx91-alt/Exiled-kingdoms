package com.google.android.gms.common.api.internal;

import android.util.Log;
import android.util.SparseArray;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.Preconditions;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class zaj extends zal {
    private final SparseArray<zaa> zacv;

    private class zaa implements GoogleApiClient.OnConnectionFailedListener {
        public final int zacw;
        public final GoogleApiClient zacx;
        public final GoogleApiClient.OnConnectionFailedListener zacy;

        public zaa(int i2, GoogleApiClient googleApiClient, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
            this.zacw = i2;
            this.zacx = googleApiClient;
            this.zacy = onConnectionFailedListener;
            googleApiClient.registerConnectionFailedListener(this);
        }

        @Override // com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener
        public final void onConnectionFailed(ConnectionResult connectionResult) {
            String strValueOf = String.valueOf(connectionResult);
            StringBuilder sb = new StringBuilder(strValueOf.length() + 27);
            sb.append("beginFailureResolution for ");
            sb.append(strValueOf);
            Log.d("AutoManageHelper", sb.toString());
            zaj.this.zab(connectionResult, this.zacw);
        }
    }

    private zaj(LifecycleFragment lifecycleFragment) {
        super(lifecycleFragment);
        this.zacv = new SparseArray<>();
        this.mLifecycleFragment.addCallback("AutoManageHelper", this);
    }

    public static zaj zaa(LifecycleActivity lifecycleActivity) {
        LifecycleFragment fragment = LifecycleCallback.getFragment(lifecycleActivity);
        zaj zajVar = (zaj) fragment.getCallbackOrNull("AutoManageHelper", zaj.class);
        return zajVar != null ? zajVar : new zaj(fragment);
    }

    private final zaa zab(int i2) {
        if (this.zacv.size() <= i2) {
            return null;
        }
        SparseArray<zaa> sparseArray = this.zacv;
        return sparseArray.get(sparseArray.keyAt(i2));
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleCallback
    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        for (int i2 = 0; i2 < this.zacv.size(); i2++) {
            zaa zaaVarZab = zab(i2);
            if (zaaVarZab != null) {
                printWriter.append((CharSequence) str).append("GoogleApiClient #").print(zaaVarZab.zacw);
                printWriter.println(":");
                zaaVarZab.zacx.dump(String.valueOf(str).concat("  "), fileDescriptor, printWriter, strArr);
            }
        }
    }

    @Override // com.google.android.gms.common.api.internal.zal, com.google.android.gms.common.api.internal.LifecycleCallback
    public void onStart() {
        super.onStart();
        boolean z2 = this.mStarted;
        String strValueOf = String.valueOf(this.zacv);
        StringBuilder sb = new StringBuilder(strValueOf.length() + 14);
        sb.append("onStart ");
        sb.append(z2);
        sb.append(" ");
        sb.append(strValueOf);
        Log.d("AutoManageHelper", sb.toString());
        if (this.zade.get() == null) {
            for (int i2 = 0; i2 < this.zacv.size(); i2++) {
                zaa zaaVarZab = zab(i2);
                if (zaaVarZab != null) {
                    zaaVarZab.zacx.connect();
                }
            }
        }
    }

    @Override // com.google.android.gms.common.api.internal.zal, com.google.android.gms.common.api.internal.LifecycleCallback
    public void onStop() {
        super.onStop();
        for (int i2 = 0; i2 < this.zacv.size(); i2++) {
            zaa zaaVarZab = zab(i2);
            if (zaaVarZab != null) {
                zaaVarZab.zacx.disconnect();
            }
        }
    }

    @Override // com.google.android.gms.common.api.internal.zal
    protected final void zao() {
        for (int i2 = 0; i2 < this.zacv.size(); i2++) {
            zaa zaaVarZab = zab(i2);
            if (zaaVarZab != null) {
                zaaVarZab.zacx.connect();
            }
        }
    }

    public final void zaa(int i2, GoogleApiClient googleApiClient, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        Preconditions.checkNotNull(googleApiClient, "GoogleApiClient instance cannot be null");
        boolean z2 = this.zacv.indexOfKey(i2) < 0;
        StringBuilder sb = new StringBuilder(54);
        sb.append("Already managing a GoogleApiClient with id ");
        sb.append(i2);
        Preconditions.checkState(z2, sb.toString());
        zam zamVar = this.zade.get();
        boolean z3 = this.mStarted;
        String strValueOf = String.valueOf(zamVar);
        StringBuilder sb2 = new StringBuilder(strValueOf.length() + 49);
        sb2.append("starting AutoManage for client ");
        sb2.append(i2);
        sb2.append(" ");
        sb2.append(z3);
        sb2.append(" ");
        sb2.append(strValueOf);
        Log.d("AutoManageHelper", sb2.toString());
        this.zacv.put(i2, new zaa(i2, googleApiClient, onConnectionFailedListener));
        if (this.mStarted && zamVar == null) {
            String strValueOf2 = String.valueOf(googleApiClient);
            StringBuilder sb3 = new StringBuilder(strValueOf2.length() + 11);
            sb3.append("connecting ");
            sb3.append(strValueOf2);
            Log.d("AutoManageHelper", sb3.toString());
            googleApiClient.connect();
        }
    }

    public final void zaa(int i2) {
        zaa zaaVar = this.zacv.get(i2);
        this.zacv.remove(i2);
        if (zaaVar != null) {
            zaaVar.zacx.unregisterConnectionFailedListener(zaaVar);
            zaaVar.zacx.disconnect();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zal
    protected final void zaa(ConnectionResult connectionResult, int i2) {
        Log.w("AutoManageHelper", "Unresolved error while connecting client. Stopping auto-manage.");
        if (i2 < 0) {
            Log.wtf("AutoManageHelper", "AutoManageLifecycleHelper received onErrorResolutionFailed callback but no failing client ID is set", new Exception());
            return;
        }
        zaa zaaVar = this.zacv.get(i2);
        if (zaaVar != null) {
            zaa(i2);
            GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener = zaaVar.zacy;
            if (onConnectionFailedListener != null) {
                onConnectionFailedListener.onConnectionFailed(connectionResult);
            }
        }
    }
}
