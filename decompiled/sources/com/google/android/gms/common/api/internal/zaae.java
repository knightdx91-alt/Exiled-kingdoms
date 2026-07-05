package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.support.v4.util.c;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.Preconditions;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class zaae extends zal {
    private GoogleApiManager zabm;
    private final c<zai<?>> zafo;

    private zaae(LifecycleFragment lifecycleFragment) {
        super(lifecycleFragment);
        this.zafo = new c<>();
        this.mLifecycleFragment.addCallback("ConnectionlessLifecycleHelper", this);
    }

    public static void zaa(Activity activity, GoogleApiManager googleApiManager, zai<?> zaiVar) {
        LifecycleFragment fragment = LifecycleCallback.getFragment(activity);
        zaae zaaeVar = (zaae) fragment.getCallbackOrNull("ConnectionlessLifecycleHelper", zaae.class);
        if (zaaeVar == null) {
            zaaeVar = new zaae(fragment);
        }
        zaaeVar.zabm = googleApiManager;
        Preconditions.checkNotNull(zaiVar, "ApiKey cannot be null");
        zaaeVar.zafo.add(zaiVar);
        googleApiManager.zaa(zaaeVar);
    }

    private final void zaak() {
        if (this.zafo.isEmpty()) {
            return;
        }
        this.zabm.zaa(this);
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleCallback
    public void onResume() {
        super.onResume();
        zaak();
    }

    @Override // com.google.android.gms.common.api.internal.zal, com.google.android.gms.common.api.internal.LifecycleCallback
    public void onStart() {
        super.onStart();
        zaak();
    }

    @Override // com.google.android.gms.common.api.internal.zal, com.google.android.gms.common.api.internal.LifecycleCallback
    public void onStop() {
        super.onStop();
        this.zabm.zab(this);
    }

    final c<zai<?>> zaaj() {
        return this.zafo;
    }

    @Override // com.google.android.gms.common.api.internal.zal
    protected final void zao() {
        this.zabm.zao();
    }

    @Override // com.google.android.gms.common.api.internal.zal
    protected final void zaa(ConnectionResult connectionResult, int i2) {
        this.zabm.zaa(connectionResult, i2);
    }
}
