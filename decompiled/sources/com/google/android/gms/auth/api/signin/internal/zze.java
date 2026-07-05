package com.google.android.gms.auth.api.signin.internal;

import android.content.Context;
import android.support.v4.content.a;
import android.util.Log;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.SignInConnectionListener;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zze extends a<Void> implements SignInConnectionListener {
    private Semaphore zzbg;
    private Set<GoogleApiClient> zzbh;

    public zze(Context context, Set<GoogleApiClient> set) {
        super(context);
        this.zzbg = new Semaphore(0);
        this.zzbh = set;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // android.support.v4.content.a
    /* JADX INFO: renamed from: zzf, reason: merged with bridge method [inline-methods] */
    public final Void loadInBackground() {
        Iterator<GoogleApiClient> it = this.zzbh.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            if (it.next().maybeSignIn(this)) {
                i2++;
            }
        }
        try {
            this.zzbg.tryAcquire(i2, 5L, TimeUnit.SECONDS);
            return null;
        } catch (InterruptedException e2) {
            Log.i("GACSignInLoader", "Unexpected InterruptedException", e2);
            Thread.currentThread().interrupt();
            return null;
        }
    }

    @Override // com.google.android.gms.common.api.internal.SignInConnectionListener
    public final void onComplete() {
        this.zzbg.release();
    }

    @Override // android.support.v4.content.b
    protected final void onStartLoading() {
        this.zzbg.drainPermits();
        forceLoad();
    }
}
