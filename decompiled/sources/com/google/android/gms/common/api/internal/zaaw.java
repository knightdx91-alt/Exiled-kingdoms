package com.google.android.gms.common.api.internal;

import a.a;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.GmsClientEventManager;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.service.Common;
import com.google.android.gms.common.util.ClientLibraryUtils;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.signin.SignInOptions;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zaaw extends GoogleApiClient implements zabt {
    private final Context mContext;
    private final Looper zabj;
    private final int zaca;
    private final GoogleApiAvailability zacc;
    private final Api.AbstractClientBuilder<? extends com.google.android.gms.signin.zad, SignInOptions> zacd;
    private boolean zacg;
    private final Lock zaen;
    private final ClientSettings zaes;
    private final Map<Api<?>, Boolean> zaev;
    private final GmsClientEventManager zagr;
    private volatile boolean zagt;
    private long zagu;
    private long zagv;
    private final zabb zagw;

    @VisibleForTesting
    private zabq zagx;
    final Map<Api.AnyClientKey<?>, Api.Client> zagy;
    Set<Scope> zagz;
    private final ListenerHolders zaha;
    private final ArrayList<zaq> zahb;
    private Integer zahc;
    Set<zacm> zahd;
    final zacp zahe;
    private final GmsClientEventManager.GmsClientEventState zahf;
    private zabs zags = null;

    @VisibleForTesting
    final Queue<BaseImplementation.ApiMethodImpl<?, ?>> zafb = new LinkedList();

    public zaaw(Context context, Lock lock, Looper looper, ClientSettings clientSettings, GoogleApiAvailability googleApiAvailability, Api.AbstractClientBuilder<? extends com.google.android.gms.signin.zad, SignInOptions> abstractClientBuilder, Map<Api<?>, Boolean> map, List<GoogleApiClient.ConnectionCallbacks> list, List<GoogleApiClient.OnConnectionFailedListener> list2, Map<Api.AnyClientKey<?>, Api.Client> map2, int i2, int i3, ArrayList<zaq> arrayList, boolean z2) {
        this.zagu = ClientLibraryUtils.isPackageSide() ? 10000L : 120000L;
        this.zagv = 5000L;
        this.zagz = new HashSet();
        this.zaha = new ListenerHolders();
        this.zahc = null;
        this.zahd = null;
        zaax zaaxVar = new zaax(this);
        this.zahf = zaaxVar;
        this.mContext = context;
        this.zaen = lock;
        this.zacg = false;
        this.zagr = new GmsClientEventManager(looper, zaaxVar);
        this.zabj = looper;
        this.zagw = new zabb(this, looper);
        this.zacc = googleApiAvailability;
        this.zaca = i2;
        if (i2 >= 0) {
            this.zahc = Integer.valueOf(i3);
        }
        this.zaev = map;
        this.zagy = map2;
        this.zahb = arrayList;
        this.zahe = new zacp(map2);
        Iterator<GoogleApiClient.ConnectionCallbacks> it = list.iterator();
        while (it.hasNext()) {
            this.zagr.registerConnectionCallbacks(it.next());
        }
        Iterator<GoogleApiClient.OnConnectionFailedListener> it2 = list2.iterator();
        while (it2.hasNext()) {
            this.zagr.registerConnectionFailedListener(it2.next());
        }
        this.zaes = clientSettings;
        this.zacd = abstractClientBuilder;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void resume() {
        this.zaen.lock();
        try {
            if (this.zagt) {
                zaau();
            }
        } finally {
            this.zaen.unlock();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zaa(GoogleApiClient googleApiClient, StatusPendingResult statusPendingResult, boolean z2) {
        Common.zaph.zaa(googleApiClient).setResultCallback(new zaba(this, statusPendingResult, z2, googleApiClient));
    }

    private final void zaau() {
        this.zagr.enableCallbacks();
        this.zags.connect();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zaav() {
        this.zaen.lock();
        try {
            if (zaaw()) {
                zaau();
            }
        } finally {
            this.zaen.unlock();
        }
    }

    private final void zae(int i2) {
        Integer num = this.zahc;
        if (num == null) {
            this.zahc = Integer.valueOf(i2);
        } else if (num.intValue() != i2) {
            String strZaf = zaf(i2);
            String strZaf2 = zaf(this.zahc.intValue());
            StringBuilder sb = new StringBuilder(a.e(a.e(51, strZaf), strZaf2));
            sb.append("Cannot use sign-in mode: ");
            sb.append(strZaf);
            sb.append(". Mode was already set to ");
            sb.append(strZaf2);
            throw new IllegalStateException(sb.toString());
        }
        if (this.zags != null) {
            return;
        }
        boolean z2 = false;
        boolean z3 = false;
        for (Api.Client client : this.zagy.values()) {
            if (client.requiresSignIn()) {
                z2 = true;
            }
            if (client.providesSignIn()) {
                z3 = true;
            }
        }
        int iIntValue = this.zahc.intValue();
        if (iIntValue == 1) {
            if (!z2) {
                throw new IllegalStateException("SIGN_IN_MODE_REQUIRED cannot be used on a GoogleApiClient that does not contain any authenticated APIs. Use connect() instead.");
            }
            if (z3) {
                throw new IllegalStateException("Cannot use SIGN_IN_MODE_REQUIRED with GOOGLE_SIGN_IN_API. Use connect(SIGN_IN_MODE_OPTIONAL) instead.");
            }
        } else if (iIntValue == 2 && z2) {
            if (this.zacg) {
                this.zags = new zax(this.mContext, this.zaen, this.zabj, this.zacc, this.zagy, this.zaes, this.zaev, this.zacd, this.zahb, this, true);
                return;
            } else {
                this.zags = zas.zaa(this.mContext, this, this.zaen, this.zabj, this.zacc, this.zagy, this.zaes, this.zaev, this.zacd, this.zahb);
                return;
            }
        }
        if (!this.zacg || z3) {
            this.zags = new zabe(this.mContext, this, this.zaen, this.zabj, this.zacc, this.zagy, this.zaes, this.zaev, this.zacd, this.zahb, this);
        } else {
            this.zags = new zax(this.mContext, this.zaen, this.zabj, this.zacc, this.zagy, this.zaes, this.zaev, this.zacd, this.zahb, this, false);
        }
    }

    private static String zaf(int i2) {
        return i2 != 1 ? i2 != 2 ? i2 != 3 ? "UNKNOWN" : "SIGN_IN_MODE_NONE" : "SIGN_IN_MODE_OPTIONAL" : "SIGN_IN_MODE_REQUIRED";
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final ConnectionResult blockingConnect() {
        boolean z2 = true;
        Preconditions.checkState(Looper.myLooper() != Looper.getMainLooper(), "blockingConnect must not be called on the UI thread");
        this.zaen.lock();
        try {
            if (this.zaca >= 0) {
                if (this.zahc == null) {
                    z2 = false;
                }
                Preconditions.checkState(z2, "Sign-in mode should have been set explicitly by auto-manage.");
            } else {
                Integer num = this.zahc;
                if (num == null) {
                    this.zahc = Integer.valueOf(zaa(this.zagy.values(), false));
                } else if (num.intValue() == 2) {
                    throw new IllegalStateException("Cannot call blockingConnect() when sign-in mode is set to SIGN_IN_MODE_OPTIONAL. Call connect(SIGN_IN_MODE_OPTIONAL) instead.");
                }
            }
            zae(this.zahc.intValue());
            this.zagr.enableCallbacks();
            ConnectionResult connectionResultBlockingConnect = this.zags.blockingConnect();
            this.zaen.unlock();
            return connectionResultBlockingConnect;
        } catch (Throwable th) {
            this.zaen.unlock();
            throw th;
        }
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final PendingResult<Status> clearDefaultAccountAndReconnect() {
        Preconditions.checkState(isConnected(), "GoogleApiClient is not connected yet.");
        Preconditions.checkState(this.zahc.intValue() != 2, "Cannot use clearDefaultAccountAndReconnect with GOOGLE_SIGN_IN_API");
        StatusPendingResult statusPendingResult = new StatusPendingResult(this);
        if (this.zagy.containsKey(Common.CLIENT_KEY)) {
            zaa(this, statusPendingResult, false);
        } else {
            AtomicReference atomicReference = new AtomicReference();
            GoogleApiClient googleApiClientBuild = new GoogleApiClient.Builder(this.mContext).addApi(Common.API).addConnectionCallbacks(new zaay(this, atomicReference, statusPendingResult)).addOnConnectionFailedListener(new zaaz(this, statusPendingResult)).setHandler(this.zagw).build();
            atomicReference.set(googleApiClientBuild);
            googleApiClientBuild.connect();
        }
        return statusPendingResult;
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final void connect() {
        this.zaen.lock();
        try {
            if (this.zaca >= 0) {
                Preconditions.checkState(this.zahc != null, "Sign-in mode should have been set explicitly by auto-manage.");
            } else {
                Integer num = this.zahc;
                if (num == null) {
                    this.zahc = Integer.valueOf(zaa(this.zagy.values(), false));
                } else if (num.intValue() == 2) {
                    throw new IllegalStateException("Cannot call connect() when SignInMode is set to SIGN_IN_MODE_OPTIONAL. Call connect(SIGN_IN_MODE_OPTIONAL) instead.");
                }
            }
            connect(this.zahc.intValue());
            this.zaen.unlock();
        } catch (Throwable th) {
            this.zaen.unlock();
            throw th;
        }
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final void disconnect() {
        this.zaen.lock();
        try {
            this.zahe.release();
            zabs zabsVar = this.zags;
            if (zabsVar != null) {
                zabsVar.disconnect();
            }
            this.zaha.release();
            for (BaseImplementation.ApiMethodImpl<?, ?> apiMethodImpl : this.zafb) {
                apiMethodImpl.zaa((zacs) null);
                apiMethodImpl.cancel();
            }
            this.zafb.clear();
            if (this.zags == null) {
                this.zaen.unlock();
                return;
            }
            zaaw();
            this.zagr.disableCallbacks();
            this.zaen.unlock();
        } catch (Throwable th) {
            this.zaen.unlock();
            throw th;
        }
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.append((CharSequence) str).append("mContext=").println(this.mContext);
        printWriter.append((CharSequence) str).append("mResuming=").print(this.zagt);
        printWriter.append(" mWorkQueue.size()=").print(this.zafb.size());
        printWriter.append(" mUnconsumedApiCalls.size()=").println(this.zahe.zaky.size());
        zabs zabsVar = this.zags;
        if (zabsVar != null) {
            zabsVar.dump(str, fileDescriptor, printWriter, strArr);
        }
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final <A extends Api.AnyClient, R extends Result, T extends BaseImplementation.ApiMethodImpl<R, A>> T enqueue(T t2) {
        Preconditions.checkArgument(t2.getClientKey() != null, "This task can not be enqueued (it's probably a Batch or malformed)");
        boolean zContainsKey = this.zagy.containsKey(t2.getClientKey());
        String name = t2.getApi() != null ? t2.getApi().getName() : "the API";
        StringBuilder sb = new StringBuilder(a.e(65, name));
        sb.append("GoogleApiClient is not configured to use ");
        sb.append(name);
        sb.append(" required for this call.");
        Preconditions.checkArgument(zContainsKey, sb.toString());
        this.zaen.lock();
        try {
            zabs zabsVar = this.zags;
            if (zabsVar != null) {
                return (T) zabsVar.enqueue(t2);
            }
            this.zafb.add(t2);
            return t2;
        } finally {
            this.zaen.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final <A extends Api.AnyClient, T extends BaseImplementation.ApiMethodImpl<? extends Result, A>> T execute(T t2) {
        Preconditions.checkArgument(t2.getClientKey() != null, "This task can not be executed (it's probably a Batch or malformed)");
        boolean zContainsKey = this.zagy.containsKey(t2.getClientKey());
        String name = t2.getApi() != null ? t2.getApi().getName() : "the API";
        StringBuilder sb = new StringBuilder(a.e(65, name));
        sb.append("GoogleApiClient is not configured to use ");
        sb.append(name);
        sb.append(" required for this call.");
        Preconditions.checkArgument(zContainsKey, sb.toString());
        this.zaen.lock();
        try {
            if (this.zags == null) {
                throw new IllegalStateException("GoogleApiClient is not connected yet.");
            }
            if (!this.zagt) {
                return (T) this.zags.execute(t2);
            }
            this.zafb.add(t2);
            while (!this.zafb.isEmpty()) {
                BaseImplementation.ApiMethodImpl<?, ?> apiMethodImplRemove = this.zafb.remove();
                this.zahe.zab(apiMethodImplRemove);
                apiMethodImplRemove.setFailedResult(Status.RESULT_INTERNAL_ERROR);
            }
            return t2;
        } finally {
            this.zaen.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final <C extends Api.Client> C getClient(Api.AnyClientKey<C> anyClientKey) {
        C c2 = (C) this.zagy.get(anyClientKey);
        Preconditions.checkNotNull(c2, "Appropriate Api was not requested.");
        return c2;
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final ConnectionResult getConnectionResult(Api<?> api) {
        this.zaen.lock();
        try {
            if (!isConnected() && !this.zagt) {
                throw new IllegalStateException("Cannot invoke getConnectionResult unless GoogleApiClient is connected");
            }
            if (!this.zagy.containsKey(api.getClientKey())) {
                throw new IllegalArgumentException(String.valueOf(api.getName()).concat(" was never registered with GoogleApiClient"));
            }
            ConnectionResult connectionResult = this.zags.getConnectionResult(api);
            if (connectionResult != null) {
                this.zaen.unlock();
                return connectionResult;
            }
            if (this.zagt) {
                ConnectionResult connectionResult2 = ConnectionResult.RESULT_SUCCESS;
                this.zaen.unlock();
                return connectionResult2;
            }
            Log.w("GoogleApiClientImpl", zaay());
            Log.wtf("GoogleApiClientImpl", String.valueOf(api.getName()).concat(" requested in getConnectionResult is not connected but is not present in the failed  connections map"), new Exception());
            ConnectionResult connectionResult3 = new ConnectionResult(8, null);
            this.zaen.unlock();
            return connectionResult3;
        } catch (Throwable th) {
            this.zaen.unlock();
            throw th;
        }
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final Context getContext() {
        return this.mContext;
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final Looper getLooper() {
        return this.zabj;
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final boolean hasApi(Api<?> api) {
        return this.zagy.containsKey(api.getClientKey());
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final boolean hasConnectedApi(Api<?> api) {
        Api.Client client;
        return isConnected() && (client = this.zagy.get(api.getClientKey())) != null && client.isConnected();
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final boolean isConnected() {
        zabs zabsVar = this.zags;
        return zabsVar != null && zabsVar.isConnected();
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final boolean isConnecting() {
        zabs zabsVar = this.zags;
        return zabsVar != null && zabsVar.isConnecting();
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final boolean isConnectionCallbacksRegistered(GoogleApiClient.ConnectionCallbacks connectionCallbacks) {
        return this.zagr.isConnectionCallbacksRegistered(connectionCallbacks);
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final boolean isConnectionFailedListenerRegistered(GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        return this.zagr.isConnectionFailedListenerRegistered(onConnectionFailedListener);
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final boolean maybeSignIn(SignInConnectionListener signInConnectionListener) {
        zabs zabsVar = this.zags;
        return zabsVar != null && zabsVar.maybeSignIn(signInConnectionListener);
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final void maybeSignOut() {
        zabs zabsVar = this.zags;
        if (zabsVar != null) {
            zabsVar.maybeSignOut();
        }
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final void reconnect() {
        disconnect();
        connect();
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final void registerConnectionCallbacks(GoogleApiClient.ConnectionCallbacks connectionCallbacks) {
        this.zagr.registerConnectionCallbacks(connectionCallbacks);
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final void registerConnectionFailedListener(GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        this.zagr.registerConnectionFailedListener(onConnectionFailedListener);
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final <L> ListenerHolder<L> registerListener(L l2) {
        this.zaen.lock();
        try {
            return this.zaha.zaa(l2, this.zabj, "NO_TYPE");
        } finally {
            this.zaen.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final void stopAutoManage(FragmentActivity fragmentActivity) {
        LifecycleActivity lifecycleActivity = new LifecycleActivity((Activity) fragmentActivity);
        if (this.zaca < 0) {
            throw new IllegalStateException("Called stopAutoManage but automatic lifecycle management is not enabled.");
        }
        zaj.zaa(lifecycleActivity).zaa(this.zaca);
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final void unregisterConnectionCallbacks(GoogleApiClient.ConnectionCallbacks connectionCallbacks) {
        this.zagr.unregisterConnectionCallbacks(connectionCallbacks);
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final void unregisterConnectionFailedListener(GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        this.zagr.unregisterConnectionFailedListener(onConnectionFailedListener);
    }

    final boolean zaaw() {
        if (!this.zagt) {
            return false;
        }
        this.zagt = false;
        this.zagw.removeMessages(2);
        this.zagw.removeMessages(1);
        zabq zabqVar = this.zagx;
        if (zabqVar != null) {
            zabqVar.unregister();
            this.zagx = null;
        }
        return true;
    }

    final boolean zaax() {
        this.zaen.lock();
        try {
            if (this.zahd != null) {
                return !r0.isEmpty();
            }
            this.zaen.unlock();
            return false;
        } finally {
            this.zaen.unlock();
        }
    }

    final String zaay() {
        StringWriter stringWriter = new StringWriter();
        dump("", null, new PrintWriter(stringWriter), null);
        return stringWriter.toString();
    }

    @Override // com.google.android.gms.common.api.internal.zabt
    public final void zab(Bundle bundle) {
        while (!this.zafb.isEmpty()) {
            execute(this.zafb.remove());
        }
        this.zagr.onConnectionSuccess(bundle);
    }

    @Override // com.google.android.gms.common.api.internal.zabt
    public final void zac(ConnectionResult connectionResult) {
        if (!this.zacc.isPlayServicesPossiblyUpdating(this.mContext, connectionResult.getErrorCode())) {
            zaaw();
        }
        if (this.zagt) {
            return;
        }
        this.zagr.onConnectionFailure(connectionResult);
        this.zagr.disableCallbacks();
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final void zaa(zacm zacmVar) {
        this.zaen.lock();
        try {
            if (this.zahd == null) {
                this.zahd = new HashSet();
            }
            this.zahd.add(zacmVar);
            this.zaen.unlock();
        } catch (Throwable th) {
            this.zaen.unlock();
            throw th;
        }
    }

    @Override // com.google.android.gms.common.api.internal.zabt
    public final void zab(int i2, boolean z2) {
        if (i2 == 1 && !z2 && !this.zagt) {
            this.zagt = true;
            if (this.zagx == null && !ClientLibraryUtils.isPackageSide()) {
                this.zagx = this.zacc.zaa(this.mContext.getApplicationContext(), new zabc(this));
            }
            zabb zabbVar = this.zagw;
            zabbVar.sendMessageDelayed(zabbVar.obtainMessage(1), this.zagu);
            zabb zabbVar2 = this.zagw;
            zabbVar2.sendMessageDelayed(zabbVar2.obtainMessage(2), this.zagv);
        }
        this.zahe.zabx();
        this.zagr.onUnintentionalDisconnection(i2);
        this.zagr.disableCallbacks();
        if (i2 == 2) {
            zaau();
        }
    }

    public static int zaa(Iterable<Api.Client> iterable, boolean z2) {
        boolean z3 = false;
        boolean z4 = false;
        for (Api.Client client : iterable) {
            if (client.requiresSignIn()) {
                z3 = true;
            }
            if (client.providesSignIn()) {
                z4 = true;
            }
        }
        if (z3) {
            return (z4 && z2) ? 2 : 1;
        }
        return 3;
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final void connect(int i2) {
        this.zaen.lock();
        boolean z2 = true;
        if (i2 != 3 && i2 != 1 && i2 != 2) {
            z2 = false;
        }
        try {
            StringBuilder sb = new StringBuilder(33);
            sb.append("Illegal sign-in mode: ");
            sb.append(i2);
            Preconditions.checkArgument(z2, sb.toString());
            zae(i2);
            zaau();
        } finally {
            this.zaen.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final ConnectionResult blockingConnect(long j2, TimeUnit timeUnit) {
        Preconditions.checkState(Looper.myLooper() != Looper.getMainLooper(), "blockingConnect must not be called on the UI thread");
        Preconditions.checkNotNull(timeUnit, "TimeUnit must not be null");
        this.zaen.lock();
        try {
            Integer num = this.zahc;
            if (num == null) {
                this.zahc = Integer.valueOf(zaa(this.zagy.values(), false));
            } else if (num.intValue() == 2) {
                throw new IllegalStateException("Cannot call blockingConnect() when sign-in mode is set to SIGN_IN_MODE_OPTIONAL. Call connect(SIGN_IN_MODE_OPTIONAL) instead.");
            }
            zae(this.zahc.intValue());
            this.zagr.enableCallbacks();
            ConnectionResult connectionResultBlockingConnect = this.zags.blockingConnect(j2, timeUnit);
            this.zaen.unlock();
            return connectionResultBlockingConnect;
        } catch (Throwable th) {
            this.zaen.unlock();
            throw th;
        }
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final void zab(zacm zacmVar) {
        this.zaen.lock();
        try {
            Set<zacm> set = this.zahd;
            if (set == null) {
                Log.wtf("GoogleApiClientImpl", "Attempted to remove pending transform when no transforms are registered.", new Exception());
            } else if (!set.remove(zacmVar)) {
                Log.wtf("GoogleApiClientImpl", "Failed to remove pending transform - this may lead to memory leaks!", new Exception());
            } else if (!zaax()) {
                this.zags.zaw();
            }
            this.zaen.unlock();
        } catch (Throwable th) {
            this.zaen.unlock();
            throw th;
        }
    }
}
