package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.support.v4.util.b;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.util.concurrent.HandlerExecutor;
import com.google.android.gms.signin.SignInOptions;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zax implements zabs {
    private final Looper zabj;
    private final GoogleApiManager zabm;
    private final Lock zaen;
    private final ClientSettings zaes;
    private final Map<Api<?>, Boolean> zaev;
    private final zaaw zaew;
    private final GoogleApiAvailabilityLight zaex;
    private final Condition zaey;
    private final boolean zaez;
    private final boolean zafa;
    private boolean zafc;
    private Map<zai<?>, ConnectionResult> zafd;
    private Map<zai<?>, ConnectionResult> zafe;
    private zaaa zaff;
    private ConnectionResult zafg;
    private final Map<Api.AnyClientKey<?>, zaw<?>> zaet = new HashMap();
    private final Map<Api.AnyClientKey<?>, zaw<?>> zaeu = new HashMap();
    private final Queue<BaseImplementation.ApiMethodImpl<?, ?>> zafb = new LinkedList();

    public zax(Context context, Lock lock, Looper looper, GoogleApiAvailabilityLight googleApiAvailabilityLight, Map<Api.AnyClientKey<?>, Api.Client> map, ClientSettings clientSettings, Map<Api<?>, Boolean> map2, Api.AbstractClientBuilder<? extends com.google.android.gms.signin.zad, SignInOptions> abstractClientBuilder, ArrayList<zaq> arrayList, zaaw zaawVar, boolean z2) {
        boolean z3;
        boolean z4;
        boolean z5;
        this.zaen = lock;
        this.zabj = looper;
        this.zaey = lock.newCondition();
        this.zaex = googleApiAvailabilityLight;
        this.zaew = zaawVar;
        this.zaev = map2;
        this.zaes = clientSettings;
        this.zaez = z2;
        HashMap map3 = new HashMap();
        for (Api<?> api : map2.keySet()) {
            map3.put(api.getClientKey(), api);
        }
        HashMap map4 = new HashMap();
        int size = arrayList.size();
        int i2 = 0;
        while (i2 < size) {
            zaq zaqVar = arrayList.get(i2);
            i2++;
            zaq zaqVar2 = zaqVar;
            map4.put(zaqVar2.mApi, zaqVar2);
        }
        boolean z6 = true;
        boolean z7 = false;
        boolean z8 = false;
        for (Map.Entry<Api.AnyClientKey<?>, Api.Client> entry : map.entrySet()) {
            Api api2 = (Api) map3.get(entry.getKey());
            Api.Client value = entry.getValue();
            if (value.requiresGooglePlayServices()) {
                z5 = z6;
                if (this.zaev.get(api2).booleanValue()) {
                    z4 = z8;
                    z3 = true;
                } else {
                    z3 = true;
                    z4 = true;
                }
            } else {
                z3 = z7;
                z4 = z8;
                z5 = false;
            }
            zaw<?> zawVar = new zaw<>(context, api2, looper, value, (zaq) map4.get(api2), clientSettings, abstractClientBuilder);
            this.zaet.put(entry.getKey(), zawVar);
            if (value.requiresSignIn()) {
                this.zaeu.put(entry.getKey(), zawVar);
            }
            z7 = z3;
            z6 = z5;
            z8 = z4;
        }
        this.zafa = (!z7 || z6 || z8) ? false : true;
        this.zabm = GoogleApiManager.zabc();
    }

    private final ConnectionResult zaa(Api.AnyClientKey<?> anyClientKey) {
        this.zaen.lock();
        try {
            zaw<?> zawVar = this.zaet.get(anyClientKey);
            Map<zai<?>, ConnectionResult> map = this.zafd;
            if (map != null && zawVar != null) {
                return map.get(zawVar.zak());
            }
            this.zaen.unlock();
            return null;
        } finally {
            this.zaen.unlock();
        }
    }

    private final boolean zaac() {
        this.zaen.lock();
        try {
            if (this.zafc && this.zaez) {
                Iterator<Api.AnyClientKey<?>> it = this.zaeu.keySet().iterator();
                while (it.hasNext()) {
                    ConnectionResult connectionResultZaa = zaa(it.next());
                    if (connectionResultZaa == null || !connectionResultZaa.isSuccess()) {
                        return false;
                    }
                }
                this.zaen.unlock();
                return true;
            }
            return false;
        } finally {
            this.zaen.unlock();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zaad() {
        if (this.zaes == null) {
            this.zaew.zagz = Collections.emptySet();
            return;
        }
        HashSet hashSet = new HashSet(this.zaes.getRequiredScopes());
        Map<Api<?>, ClientSettings.OptionalApiSettings> optionalApiSettings = this.zaes.getOptionalApiSettings();
        for (Api<?> api : optionalApiSettings.keySet()) {
            ConnectionResult connectionResult = getConnectionResult(api);
            if (connectionResult != null && connectionResult.isSuccess()) {
                hashSet.addAll(optionalApiSettings.get(api).mScopes);
            }
        }
        this.zaew.zagz = hashSet;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zaae() {
        while (!this.zafb.isEmpty()) {
            execute(this.zafb.remove());
        }
        this.zaew.zab((Bundle) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ConnectionResult zaaf() {
        ConnectionResult connectionResult = null;
        int i2 = 0;
        int i3 = 0;
        ConnectionResult connectionResult2 = null;
        for (zaw<?> zawVar : this.zaet.values()) {
            Api<?> api = zawVar.getApi();
            ConnectionResult connectionResult3 = this.zafd.get(zawVar.zak());
            if (!connectionResult3.isSuccess() && (!this.zaev.get(api).booleanValue() || connectionResult3.hasResolution() || this.zaex.isUserResolvableError(connectionResult3.getErrorCode()))) {
                if (connectionResult3.getErrorCode() == 4 && this.zaez) {
                    int priority = api.zah().getPriority();
                    if (connectionResult2 == null || i3 > priority) {
                        connectionResult2 = connectionResult3;
                        i3 = priority;
                    }
                } else {
                    int priority2 = api.zah().getPriority();
                    if (connectionResult == null || i2 > priority2) {
                        connectionResult = connectionResult3;
                        i2 = priority2;
                    }
                }
            }
        }
        return (connectionResult == null || connectionResult2 == null || i2 <= i3) ? connectionResult : connectionResult2;
    }

    private final <T extends BaseImplementation.ApiMethodImpl<? extends Result, ? extends Api.AnyClient>> boolean zab(T t2) {
        Api.AnyClientKey<?> clientKey = t2.getClientKey();
        ConnectionResult connectionResultZaa = zaa(clientKey);
        if (connectionResultZaa == null || connectionResultZaa.getErrorCode() != 4) {
            return false;
        }
        t2.setFailedResult(new Status(4, null, this.zabm.zaa(this.zaet.get(clientKey).zak(), System.identityHashCode(this.zaew))));
        return true;
    }

    @Override // com.google.android.gms.common.api.internal.zabs
    public final ConnectionResult blockingConnect() {
        connect();
        while (isConnecting()) {
            try {
                this.zaey.await();
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
                return new ConnectionResult(15, null);
            }
        }
        if (isConnected()) {
            return ConnectionResult.RESULT_SUCCESS;
        }
        ConnectionResult connectionResult = this.zafg;
        return connectionResult != null ? connectionResult : new ConnectionResult(13, null);
    }

    @Override // com.google.android.gms.common.api.internal.zabs
    public final void connect() {
        this.zaen.lock();
        try {
            if (this.zafc) {
                return;
            }
            this.zafc = true;
            this.zafd = null;
            this.zafe = null;
            this.zaff = null;
            this.zafg = null;
            this.zabm.zao();
            this.zabm.zaa(this.zaet.values()).addOnCompleteListener(new HandlerExecutor(this.zabj), new zaz(this));
        } finally {
            this.zaen.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zabs
    public final void disconnect() {
        this.zaen.lock();
        try {
            this.zafc = false;
            this.zafd = null;
            this.zafe = null;
            zaaa zaaaVar = this.zaff;
            if (zaaaVar != null) {
                zaaaVar.cancel();
                this.zaff = null;
            }
            this.zafg = null;
            while (!this.zafb.isEmpty()) {
                BaseImplementation.ApiMethodImpl<?, ?> apiMethodImplRemove = this.zafb.remove();
                apiMethodImplRemove.zaa((zacs) null);
                apiMethodImplRemove.cancel();
            }
            this.zaey.signalAll();
            this.zaen.unlock();
        } catch (Throwable th) {
            this.zaen.unlock();
            throw th;
        }
    }

    @Override // com.google.android.gms.common.api.internal.zabs
    public final void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
    }

    @Override // com.google.android.gms.common.api.internal.zabs
    public final <A extends Api.AnyClient, R extends Result, T extends BaseImplementation.ApiMethodImpl<R, A>> T enqueue(T t2) {
        if (this.zaez && zab(t2)) {
            return t2;
        }
        if (isConnected()) {
            this.zaew.zahe.zab(t2);
            return (T) this.zaet.get(t2.getClientKey()).doRead(t2);
        }
        this.zafb.add(t2);
        return t2;
    }

    @Override // com.google.android.gms.common.api.internal.zabs
    public final <A extends Api.AnyClient, T extends BaseImplementation.ApiMethodImpl<? extends Result, A>> T execute(T t2) {
        Api.AnyClientKey<A> clientKey = t2.getClientKey();
        if (this.zaez && zab(t2)) {
            return t2;
        }
        this.zaew.zahe.zab(t2);
        return (T) this.zaet.get(clientKey).doWrite(t2);
    }

    @Override // com.google.android.gms.common.api.internal.zabs
    public final ConnectionResult getConnectionResult(Api<?> api) {
        return zaa(api.getClientKey());
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0011  */
    @Override // com.google.android.gms.common.api.internal.zabs
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final boolean isConnected() {
        boolean z2;
        this.zaen.lock();
        try {
            if (this.zafd != null) {
                z2 = this.zafg == null;
            }
            return z2;
        } finally {
            this.zaen.unlock();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0011  */
    @Override // com.google.android.gms.common.api.internal.zabs
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final boolean isConnecting() {
        boolean z2;
        this.zaen.lock();
        try {
            if (this.zafd == null) {
                z2 = this.zafc;
            }
            return z2;
        } finally {
            this.zaen.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zabs
    public final boolean maybeSignIn(SignInConnectionListener signInConnectionListener) {
        this.zaen.lock();
        try {
            if (!this.zafc || zaac()) {
                this.zaen.unlock();
                return false;
            }
            this.zabm.zao();
            this.zaff = new zaaa(this, signInConnectionListener);
            this.zabm.zaa(this.zaeu.values()).addOnCompleteListener(new HandlerExecutor(this.zabj), this.zaff);
            this.zaen.unlock();
            return true;
        } catch (Throwable th) {
            this.zaen.unlock();
            throw th;
        }
    }

    @Override // com.google.android.gms.common.api.internal.zabs
    public final void maybeSignOut() {
        this.zaen.lock();
        try {
            this.zabm.maybeSignOut();
            zaaa zaaaVar = this.zaff;
            if (zaaaVar != null) {
                zaaaVar.cancel();
                this.zaff = null;
            }
            if (this.zafe == null) {
                this.zafe = new b(this.zaeu.size());
            }
            ConnectionResult connectionResult = new ConnectionResult(4);
            Iterator<zaw<?>> it = this.zaeu.values().iterator();
            while (it.hasNext()) {
                this.zafe.put(it.next().zak(), connectionResult);
            }
            Map<zai<?>, ConnectionResult> map = this.zafd;
            if (map != null) {
                map.putAll(this.zafe);
            }
            this.zaen.unlock();
        } catch (Throwable th) {
            this.zaen.unlock();
            throw th;
        }
    }

    @Override // com.google.android.gms.common.api.internal.zabs
    public final void zaw() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean zaa(zaw<?> zawVar, ConnectionResult connectionResult) {
        return !connectionResult.isSuccess() && !connectionResult.hasResolution() && this.zaev.get(zawVar.getApi()).booleanValue() && zawVar.zaab().requiresGooglePlayServices() && this.zaex.isUserResolvableError(connectionResult.getErrorCode());
    }

    @Override // com.google.android.gms.common.api.internal.zabs
    public final ConnectionResult blockingConnect(long j2, TimeUnit timeUnit) {
        connect();
        long nanos = timeUnit.toNanos(j2);
        while (isConnecting()) {
            if (nanos <= 0) {
                disconnect();
                return new ConnectionResult(14, null);
            }
            try {
                nanos = this.zaey.awaitNanos(nanos);
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
                return new ConnectionResult(15, null);
            }
            Thread.currentThread().interrupt();
            return new ConnectionResult(15, null);
        }
        if (isConnected()) {
            return ConnectionResult.RESULT_SUCCESS;
        }
        ConnectionResult connectionResult = this.zafg;
        return connectionResult != null ? connectionResult : new ConnectionResult(13, null);
    }

    static /* synthetic */ boolean zaa(zax zaxVar, boolean z2) {
        zaxVar.zafc = false;
        return false;
    }
}
