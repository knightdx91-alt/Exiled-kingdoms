package com.google.android.gms.common.api.internal;

import a.a;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.support.v4.util.b;
import android.support.v4.util.c;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.UnsupportedApiCallException;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.GoogleApiAvailabilityCache;
import com.google.android.gms.common.internal.IAccountAccessor;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.SimpleClientAdapter;
import com.google.android.gms.common.util.ArrayUtils;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@KeepForSdk
public class GoogleApiManager implements Handler.Callback {
    private static GoogleApiManager zaib;
    private final Handler handler;
    private final Context zaic;
    private final GoogleApiAvailability zaid;
    private final GoogleApiAvailabilityCache zaie;
    public static final Status zahw = new Status(4, "Sign-out occurred while this API call was in progress.");
    private static final Status zahx = new Status(4, "The user must be signed in to make this API call.");
    private static final Object lock = new Object();
    private long zahy = 5000;
    private long zahz = 120000;
    private long zaia = 10000;
    private final AtomicInteger zaif = new AtomicInteger(1);
    private final AtomicInteger zaig = new AtomicInteger(0);
    private final Map<zai<?>, zaa<?>> zaih = new ConcurrentHashMap(5, 0.75f, 1);
    private zaae zaii = null;
    private final Set<zai<?>> zaij = new c();
    private final Set<zai<?>> zaik = new c();

    @KeepForSdk
    private GoogleApiManager(Context context, Looper looper, GoogleApiAvailability googleApiAvailability) {
        this.zaic = context;
        com.google.android.gms.internal.base.zal zalVar = new com.google.android.gms.internal.base.zal(looper, this);
        this.handler = zalVar;
        this.zaid = googleApiAvailability;
        this.zaie = new GoogleApiAvailabilityCache(googleApiAvailability);
        zalVar.sendMessage(zalVar.obtainMessage(6));
    }

    @KeepForSdk
    public static void reportSignOut() {
        synchronized (lock) {
            try {
                GoogleApiManager googleApiManager = zaib;
                if (googleApiManager != null) {
                    googleApiManager.zaig.incrementAndGet();
                    Handler handler = googleApiManager.handler;
                    handler.sendMessageAtFrontOfQueue(handler.obtainMessage(10));
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public static GoogleApiManager zab(Context context) {
        GoogleApiManager googleApiManager;
        synchronized (lock) {
            try {
                if (zaib == null) {
                    HandlerThread handlerThread = new HandlerThread("GoogleApiHandler", 9);
                    handlerThread.start();
                    zaib = new GoogleApiManager(context.getApplicationContext(), handlerThread.getLooper(), GoogleApiAvailability.getInstance());
                }
                googleApiManager = zaib;
            } catch (Throwable th) {
                throw th;
            }
        }
        return googleApiManager;
    }

    public static GoogleApiManager zabc() {
        GoogleApiManager googleApiManager;
        synchronized (lock) {
            Preconditions.checkNotNull(zaib, "Must guarantee manager is non-null before using getInstance");
            googleApiManager = zaib;
        }
        return googleApiManager;
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        int i2 = message.what;
        zaa<?> zaaVar = null;
        switch (i2) {
            case 1:
                this.zaia = ((Boolean) message.obj).booleanValue() ? 10000L : 300000L;
                this.handler.removeMessages(12);
                for (zai<?> zaiVar : this.zaih.keySet()) {
                    Handler handler = this.handler;
                    handler.sendMessageDelayed(handler.obtainMessage(12, zaiVar), this.zaia);
                }
                return true;
            case 2:
                zak zakVar = (zak) message.obj;
                Iterator<zai<?>> it = zakVar.zap().iterator();
                while (true) {
                    if (it.hasNext()) {
                        zai<?> next = it.next();
                        zaa<?> zaaVar2 = this.zaih.get(next);
                        if (zaaVar2 == null) {
                            zakVar.zaa(next, new ConnectionResult(13), null);
                        } else if (zaaVar2.isConnected()) {
                            zakVar.zaa(next, ConnectionResult.RESULT_SUCCESS, zaaVar2.zaab().getEndpointPackageName());
                        } else if (zaaVar2.zabm() != null) {
                            zakVar.zaa(next, zaaVar2.zabm(), null);
                        } else {
                            zaaVar2.zaa(zakVar);
                            zaaVar2.connect();
                        }
                    }
                }
                return true;
            case 3:
                for (zaa<?> zaaVar3 : this.zaih.values()) {
                    zaaVar3.zabl();
                    zaaVar3.connect();
                }
                return true;
            case 4:
            case 8:
            case 13:
                zabv zabvVar = (zabv) message.obj;
                zaa<?> zaaVar4 = this.zaih.get(zabvVar.zajs.zak());
                if (zaaVar4 == null) {
                    zab(zabvVar.zajs);
                    zaaVar4 = this.zaih.get(zabvVar.zajs.zak());
                }
                if (!zaaVar4.requiresSignIn() || this.zaig.get() == zabvVar.zajr) {
                    zaaVar4.zaa(zabvVar.zajq);
                } else {
                    zabvVar.zajq.zaa(zahw);
                    zaaVar4.zabj();
                }
                return true;
            case 5:
                int i3 = message.arg1;
                ConnectionResult connectionResult = (ConnectionResult) message.obj;
                Iterator<zaa<?>> it2 = this.zaih.values().iterator();
                while (true) {
                    if (it2.hasNext()) {
                        zaa<?> next2 = it2.next();
                        if (next2.getInstanceId() == i3) {
                            zaaVar = next2;
                        }
                    }
                }
                if (zaaVar != null) {
                    String errorString = this.zaid.getErrorString(connectionResult.getErrorCode());
                    String errorMessage = connectionResult.getErrorMessage();
                    StringBuilder sb = new StringBuilder(a.e(a.e(69, errorString), errorMessage));
                    sb.append("Error resolution was canceled by the user, original error message: ");
                    sb.append(errorString);
                    sb.append(": ");
                    sb.append(errorMessage);
                    zaaVar.zac(new Status(17, sb.toString()));
                } else {
                    StringBuilder sb2 = new StringBuilder(76);
                    sb2.append("Could not find API instance ");
                    sb2.append(i3);
                    sb2.append(" while trying to fail enqueued calls.");
                    Log.wtf("GoogleApiManager", sb2.toString(), new Exception());
                }
                return true;
            case 6:
                if (PlatformVersion.isAtLeastIceCreamSandwich() && (this.zaic.getApplicationContext() instanceof Application)) {
                    BackgroundDetector.initialize((Application) this.zaic.getApplicationContext());
                    BackgroundDetector.getInstance().addListener(new zabi(this));
                    if (!BackgroundDetector.getInstance().readCurrentStateIfPossible(true)) {
                        this.zaia = 300000L;
                    }
                }
                return true;
            case 7:
                zab((GoogleApi<?>) message.obj);
                return true;
            case 9:
                if (this.zaih.containsKey(message.obj)) {
                    this.zaih.get(message.obj).resume();
                }
                return true;
            case 10:
                Iterator<zai<?>> it3 = this.zaik.iterator();
                while (it3.hasNext()) {
                    this.zaih.remove(it3.next()).zabj();
                }
                this.zaik.clear();
                return true;
            case 11:
                if (this.zaih.containsKey(message.obj)) {
                    this.zaih.get(message.obj).zaav();
                }
                return true;
            case 12:
                if (this.zaih.containsKey(message.obj)) {
                    this.zaih.get(message.obj).zabp();
                }
                return true;
            case 14:
                zaaf zaafVar = (zaaf) message.obj;
                zai<?> zaiVarZak = zaafVar.zak();
                if (this.zaih.containsKey(zaiVarZak)) {
                    zaafVar.zaal().setResult(Boolean.valueOf(zaa.zaa((zaa) this.zaih.get(zaiVarZak), false)));
                } else {
                    zaafVar.zaal().setResult(Boolean.FALSE);
                }
                return true;
            case 15:
                zab zabVar = (zab) message.obj;
                if (this.zaih.containsKey(zabVar.zaja)) {
                    this.zaih.get(zabVar.zaja).zaa(zabVar);
                }
                return true;
            case 16:
                zab zabVar2 = (zab) message.obj;
                if (this.zaih.containsKey(zabVar2.zaja)) {
                    this.zaih.get(zabVar2.zaja).zab(zabVar2);
                }
                return true;
            default:
                StringBuilder sb3 = new StringBuilder(31);
                sb3.append("Unknown message id: ");
                sb3.append(i2);
                Log.w("GoogleApiManager", sb3.toString());
                return false;
        }
    }

    final void maybeSignOut() {
        this.zaig.incrementAndGet();
        Handler handler = this.handler;
        handler.sendMessage(handler.obtainMessage(10));
    }

    public final void zaa(GoogleApi<?> googleApi) {
        Handler handler = this.handler;
        handler.sendMessage(handler.obtainMessage(7, googleApi));
    }

    public final int zabd() {
        return this.zaif.getAndIncrement();
    }

    public final Task<Boolean> zac(GoogleApi<?> googleApi) {
        zaaf zaafVar = new zaaf(googleApi.zak());
        Handler handler = this.handler;
        handler.sendMessage(handler.obtainMessage(14, zaafVar));
        return zaafVar.zaal().getTask();
    }

    public final void zao() {
        Handler handler = this.handler;
        handler.sendMessage(handler.obtainMessage(3));
    }

    public final void zaa(zaae zaaeVar) {
        synchronized (lock) {
            try {
                if (this.zaii != zaaeVar) {
                    this.zaii = zaaeVar;
                    this.zaij.clear();
                }
                this.zaij.addAll(zaaeVar.zaaj());
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public class zaa<O extends Api.ApiOptions> implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, zar {
        private final zai<O> zafp;
        private final Api.Client zain;
        private final Api.AnyClient zaio;
        private final zaab zaip;
        private final int zais;
        private final zace zait;
        private boolean zaiu;
        private final Queue<com.google.android.gms.common.api.internal.zab> zaim = new LinkedList();
        private final Set<zak> zaiq = new HashSet();
        private final Map<ListenerHolder.ListenerKey<?>, zabw> zair = new HashMap();
        private final List<zab> zaiv = new ArrayList();
        private ConnectionResult zaiw = null;

        public zaa(GoogleApi<O> googleApi) {
            Api.Client clientZaa = googleApi.zaa(GoogleApiManager.this.handler.getLooper(), this);
            this.zain = clientZaa;
            if (clientZaa instanceof SimpleClientAdapter) {
                this.zaio = ((SimpleClientAdapter) clientZaa).getClient();
            } else {
                this.zaio = clientZaa;
            }
            this.zafp = googleApi.zak();
            this.zaip = new zaab();
            this.zais = googleApi.getInstanceId();
            if (clientZaa.requiresSignIn()) {
                this.zait = googleApi.zaa(GoogleApiManager.this.zaic, GoogleApiManager.this.handler);
            } else {
                this.zait = null;
            }
        }

        private final boolean zab(com.google.android.gms.common.api.internal.zab zabVar) {
            if (!(zabVar instanceof com.google.android.gms.common.api.internal.zac)) {
                zac(zabVar);
                return true;
            }
            com.google.android.gms.common.api.internal.zac zacVar = (com.google.android.gms.common.api.internal.zac) zabVar;
            Feature featureZaa = zaa(zacVar.zab((zaa<?>) this));
            if (featureZaa == null) {
                zac(zabVar);
                return true;
            }
            if (!zacVar.zac(this)) {
                zacVar.zaa(new UnsupportedApiCallException(featureZaa));
                return false;
            }
            zab zabVar2 = new zab(this.zafp, featureZaa, null);
            int iIndexOf = this.zaiv.indexOf(zabVar2);
            if (iIndexOf >= 0) {
                zab zabVar3 = this.zaiv.get(iIndexOf);
                GoogleApiManager.this.handler.removeMessages(15, zabVar3);
                GoogleApiManager.this.handler.sendMessageDelayed(Message.obtain(GoogleApiManager.this.handler, 15, zabVar3), GoogleApiManager.this.zahy);
                return false;
            }
            this.zaiv.add(zabVar2);
            GoogleApiManager.this.handler.sendMessageDelayed(Message.obtain(GoogleApiManager.this.handler, 15, zabVar2), GoogleApiManager.this.zahy);
            GoogleApiManager.this.handler.sendMessageDelayed(Message.obtain(GoogleApiManager.this.handler, 16, zabVar2), GoogleApiManager.this.zahz);
            ConnectionResult connectionResult = new ConnectionResult(2, null);
            if (zah(connectionResult)) {
                return false;
            }
            GoogleApiManager.this.zac(connectionResult, this.zais);
            return false;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zabg() {
            zabl();
            zai(ConnectionResult.RESULT_SUCCESS);
            zabn();
            Iterator<zabw> it = this.zair.values().iterator();
            while (it.hasNext()) {
                zabw next = it.next();
                if (zaa(next.zajw.getRequiredFeatures()) != null) {
                    it.remove();
                } else {
                    try {
                        next.zajw.registerListener(this.zaio, new TaskCompletionSource<>());
                    } catch (DeadObjectException unused) {
                        onConnectionSuspended(1);
                        this.zain.disconnect();
                    } catch (RemoteException unused2) {
                        it.remove();
                    }
                }
            }
            zabi();
            zabo();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zabh() {
            zabl();
            this.zaiu = true;
            this.zaip.zaai();
            GoogleApiManager.this.handler.sendMessageDelayed(Message.obtain(GoogleApiManager.this.handler, 9, this.zafp), GoogleApiManager.this.zahy);
            GoogleApiManager.this.handler.sendMessageDelayed(Message.obtain(GoogleApiManager.this.handler, 11, this.zafp), GoogleApiManager.this.zahz);
            GoogleApiManager.this.zaie.flush();
        }

        private final void zabi() {
            ArrayList arrayList = new ArrayList(this.zaim);
            int size = arrayList.size();
            int i2 = 0;
            while (i2 < size) {
                Object obj = arrayList.get(i2);
                i2++;
                com.google.android.gms.common.api.internal.zab zabVar = (com.google.android.gms.common.api.internal.zab) obj;
                if (!this.zain.isConnected()) {
                    return;
                }
                if (zab(zabVar)) {
                    this.zaim.remove(zabVar);
                }
            }
        }

        private final void zabn() {
            if (this.zaiu) {
                GoogleApiManager.this.handler.removeMessages(11, this.zafp);
                GoogleApiManager.this.handler.removeMessages(9, this.zafp);
                this.zaiu = false;
            }
        }

        private final void zabo() {
            GoogleApiManager.this.handler.removeMessages(12, this.zafp);
            GoogleApiManager.this.handler.sendMessageDelayed(GoogleApiManager.this.handler.obtainMessage(12, this.zafp), GoogleApiManager.this.zaia);
        }

        private final void zac(com.google.android.gms.common.api.internal.zab zabVar) {
            zabVar.zaa(this.zaip, requiresSignIn());
            try {
                zabVar.zaa((zaa<?>) this);
            } catch (DeadObjectException unused) {
                onConnectionSuspended(1);
                this.zain.disconnect();
            }
        }

        private final boolean zah(ConnectionResult connectionResult) {
            synchronized (GoogleApiManager.lock) {
                try {
                    if (GoogleApiManager.this.zaii == null || !GoogleApiManager.this.zaij.contains(this.zafp)) {
                        return false;
                    }
                    GoogleApiManager.this.zaii.zab(connectionResult, this.zais);
                    return true;
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        private final void zai(ConnectionResult connectionResult) {
            Iterator<zak> it = this.zaiq.iterator();
            while (it.hasNext()) {
                it.next().zaa(this.zafp, connectionResult, Objects.equal(connectionResult, ConnectionResult.RESULT_SUCCESS) ? this.zain.getEndpointPackageName() : null);
            }
            this.zaiq.clear();
        }

        public final void connect() {
            Preconditions.checkHandlerThread(GoogleApiManager.this.handler);
            if (this.zain.isConnected() || this.zain.isConnecting()) {
                return;
            }
            int clientAvailability = GoogleApiManager.this.zaie.getClientAvailability(GoogleApiManager.this.zaic, this.zain);
            if (clientAvailability != 0) {
                onConnectionFailed(new ConnectionResult(clientAvailability, null));
                return;
            }
            zac zacVar = GoogleApiManager.this.new zac(this.zain, this.zafp);
            if (this.zain.requiresSignIn()) {
                this.zait.zaa(zacVar);
            }
            this.zain.connect(zacVar);
        }

        public final int getInstanceId() {
            return this.zais;
        }

        final boolean isConnected() {
            return this.zain.isConnected();
        }

        @Override // com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks
        public final void onConnected(Bundle bundle) {
            if (Looper.myLooper() == GoogleApiManager.this.handler.getLooper()) {
                zabg();
            } else {
                GoogleApiManager.this.handler.post(new zabj(this));
            }
        }

        @Override // com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener
        public final void onConnectionFailed(ConnectionResult connectionResult) {
            Preconditions.checkHandlerThread(GoogleApiManager.this.handler);
            zace zaceVar = this.zait;
            if (zaceVar != null) {
                zaceVar.zabs();
            }
            zabl();
            GoogleApiManager.this.zaie.flush();
            zai(connectionResult);
            if (connectionResult.getErrorCode() == 4) {
                zac(GoogleApiManager.zahx);
                return;
            }
            if (this.zaim.isEmpty()) {
                this.zaiw = connectionResult;
                return;
            }
            if (zah(connectionResult) || GoogleApiManager.this.zac(connectionResult, this.zais)) {
                return;
            }
            if (connectionResult.getErrorCode() == 18) {
                this.zaiu = true;
            }
            if (this.zaiu) {
                GoogleApiManager.this.handler.sendMessageDelayed(Message.obtain(GoogleApiManager.this.handler, 9, this.zafp), GoogleApiManager.this.zahy);
                return;
            }
            String strZan = this.zafp.zan();
            StringBuilder sb = new StringBuilder(a.e(38, strZan));
            sb.append("API: ");
            sb.append(strZan);
            sb.append(" is not available on this device.");
            zac(new Status(17, sb.toString()));
        }

        @Override // com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks
        public final void onConnectionSuspended(int i2) {
            if (Looper.myLooper() == GoogleApiManager.this.handler.getLooper()) {
                zabh();
            } else {
                GoogleApiManager.this.handler.post(new zabk(this));
            }
        }

        public final boolean requiresSignIn() {
            return this.zain.requiresSignIn();
        }

        public final void resume() {
            Preconditions.checkHandlerThread(GoogleApiManager.this.handler);
            if (this.zaiu) {
                connect();
            }
        }

        @Override // com.google.android.gms.common.api.internal.zar
        public final void zaa(ConnectionResult connectionResult, Api<?> api, boolean z2) {
            if (Looper.myLooper() == GoogleApiManager.this.handler.getLooper()) {
                onConnectionFailed(connectionResult);
            } else {
                GoogleApiManager.this.handler.post(new zabl(this, connectionResult));
            }
        }

        public final Api.Client zaab() {
            return this.zain;
        }

        public final void zaav() {
            Preconditions.checkHandlerThread(GoogleApiManager.this.handler);
            if (this.zaiu) {
                zabn();
                zac(GoogleApiManager.this.zaid.isGooglePlayServicesAvailable(GoogleApiManager.this.zaic) == 18 ? new Status(8, "Connection timed out while waiting for Google Play services update to complete.") : new Status(8, "API failed to connect while resuming due to an unknown error."));
                this.zain.disconnect();
            }
        }

        public final void zabj() {
            Preconditions.checkHandlerThread(GoogleApiManager.this.handler);
            zac(GoogleApiManager.zahw);
            this.zaip.zaah();
            for (ListenerHolder.ListenerKey listenerKey : (ListenerHolder.ListenerKey[]) this.zair.keySet().toArray(new ListenerHolder.ListenerKey[this.zair.size()])) {
                zaa(new zah(listenerKey, new TaskCompletionSource()));
            }
            zai(new ConnectionResult(4));
            if (this.zain.isConnected()) {
                this.zain.onUserSignOut(new zabm(this));
            }
        }

        public final Map<ListenerHolder.ListenerKey<?>, zabw> zabk() {
            return this.zair;
        }

        public final void zabl() {
            Preconditions.checkHandlerThread(GoogleApiManager.this.handler);
            this.zaiw = null;
        }

        public final ConnectionResult zabm() {
            Preconditions.checkHandlerThread(GoogleApiManager.this.handler);
            return this.zaiw;
        }

        public final boolean zabp() {
            return zac(true);
        }

        final com.google.android.gms.signin.zad zabq() {
            zace zaceVar = this.zait;
            if (zaceVar == null) {
                return null;
            }
            return zaceVar.zabq();
        }

        public final void zag(ConnectionResult connectionResult) {
            Preconditions.checkHandlerThread(GoogleApiManager.this.handler);
            this.zain.disconnect();
            onConnectionFailed(connectionResult);
        }

        public final void zaa(com.google.android.gms.common.api.internal.zab zabVar) {
            Preconditions.checkHandlerThread(GoogleApiManager.this.handler);
            if (this.zain.isConnected()) {
                if (zab(zabVar)) {
                    zabo();
                    return;
                } else {
                    this.zaim.add(zabVar);
                    return;
                }
            }
            this.zaim.add(zabVar);
            ConnectionResult connectionResult = this.zaiw;
            if (connectionResult != null && connectionResult.hasResolution()) {
                onConnectionFailed(this.zaiw);
            } else {
                connect();
            }
        }

        public final void zac(Status status) {
            Preconditions.checkHandlerThread(GoogleApiManager.this.handler);
            Iterator<com.google.android.gms.common.api.internal.zab> it = this.zaim.iterator();
            while (it.hasNext()) {
                it.next().zaa(status);
            }
            this.zaim.clear();
        }

        private final boolean zac(boolean z2) {
            Preconditions.checkHandlerThread(GoogleApiManager.this.handler);
            if (!this.zain.isConnected() || this.zair.size() != 0) {
                return false;
            }
            if (!this.zaip.zaag()) {
                this.zain.disconnect();
                return true;
            }
            if (z2) {
                zabo();
            }
            return false;
        }

        public final void zaa(zak zakVar) {
            Preconditions.checkHandlerThread(GoogleApiManager.this.handler);
            this.zaiq.add(zakVar);
        }

        /* JADX WARN: Multi-variable type inference failed */
        private final Feature zaa(Feature[] featureArr) {
            if (featureArr != null && featureArr.length != 0) {
                Feature[] availableFeatures = this.zain.getAvailableFeatures();
                if (availableFeatures == null) {
                    availableFeatures = new Feature[0];
                }
                b bVar = new b(availableFeatures.length);
                for (Feature feature : availableFeatures) {
                    bVar.put(feature.getName(), Long.valueOf(feature.getVersion()));
                }
                for (Feature feature2 : featureArr) {
                    if (!bVar.containsKey(feature2.getName()) || ((Long) bVar.get(feature2.getName())).longValue() < feature2.getVersion()) {
                        return feature2;
                    }
                }
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zab(zab zabVar) {
            Feature[] featureArrZab;
            if (this.zaiv.remove(zabVar)) {
                GoogleApiManager.this.handler.removeMessages(15, zabVar);
                GoogleApiManager.this.handler.removeMessages(16, zabVar);
                Feature feature = zabVar.zajb;
                ArrayList arrayList = new ArrayList(this.zaim.size());
                for (com.google.android.gms.common.api.internal.zab zabVar2 : this.zaim) {
                    if ((zabVar2 instanceof com.google.android.gms.common.api.internal.zac) && (featureArrZab = ((com.google.android.gms.common.api.internal.zac) zabVar2).zab((zaa<?>) this)) != null && ArrayUtils.contains(featureArrZab, feature)) {
                        arrayList.add(zabVar2);
                    }
                }
                int size = arrayList.size();
                int i2 = 0;
                while (i2 < size) {
                    Object obj = arrayList.get(i2);
                    i2++;
                    com.google.android.gms.common.api.internal.zab zabVar3 = (com.google.android.gms.common.api.internal.zab) obj;
                    this.zaim.remove(zabVar3);
                    zabVar3.zaa(new UnsupportedApiCallException(feature));
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zaa(zab zabVar) {
            if (this.zaiv.contains(zabVar) && !this.zaiu) {
                if (!this.zain.isConnected()) {
                    connect();
                } else {
                    zabi();
                }
            }
        }

        static /* synthetic */ boolean zaa(zaa zaaVar, boolean z2) {
            return zaaVar.zac(false);
        }
    }

    private static class zab {
        private final zai<?> zaja;
        private final Feature zajb;

        private zab(zai<?> zaiVar, Feature feature) {
            this.zaja = zaiVar;
            this.zajb = feature;
        }

        public final boolean equals(Object obj) {
            if (obj != null && (obj instanceof zab)) {
                zab zabVar = (zab) obj;
                if (Objects.equal(this.zaja, zabVar.zaja) && Objects.equal(this.zajb, zabVar.zajb)) {
                    return true;
                }
            }
            return false;
        }

        public final int hashCode() {
            return Objects.hashCode(this.zaja, this.zajb);
        }

        public final String toString() {
            return Objects.toStringHelper(this).add("key", this.zaja).add("feature", this.zajb).toString();
        }

        /* synthetic */ zab(zai zaiVar, Feature feature, zabi zabiVar) {
            this(zaiVar, feature);
        }
    }

    final boolean zac(ConnectionResult connectionResult, int i2) {
        return this.zaid.zaa(this.zaic, connectionResult, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    class zac implements zach, BaseGmsClient.ConnectionProgressReportCallbacks {
        private final zai<?> zafp;
        private final Api.Client zain;
        private IAccountAccessor zajc = null;
        private Set<Scope> zajd = null;
        private boolean zaje = false;

        public zac(Api.Client client, zai<?> zaiVar) {
            this.zain = client;
            this.zafp = zaiVar;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zabr() {
            IAccountAccessor iAccountAccessor;
            if (!this.zaje || (iAccountAccessor = this.zajc) == null) {
                return;
            }
            this.zain.getRemoteService(iAccountAccessor, this.zajd);
        }

        @Override // com.google.android.gms.common.internal.BaseGmsClient.ConnectionProgressReportCallbacks
        public final void onReportServiceBinding(ConnectionResult connectionResult) {
            GoogleApiManager.this.handler.post(new zabo(this, connectionResult));
        }

        @Override // com.google.android.gms.common.api.internal.zach
        public final void zaa(IAccountAccessor iAccountAccessor, Set<Scope> set) {
            if (iAccountAccessor == null || set == null) {
                Log.wtf("GoogleApiManager", "Received null response from onSignInSuccess", new Exception());
                zag(new ConnectionResult(4));
            } else {
                this.zajc = iAccountAccessor;
                this.zajd = set;
                zabr();
            }
        }

        @Override // com.google.android.gms.common.api.internal.zach
        public final void zag(ConnectionResult connectionResult) {
            ((zaa) GoogleApiManager.this.zaih.get(this.zafp)).zag(connectionResult);
        }

        static /* synthetic */ boolean zaa(zac zacVar, boolean z2) {
            zacVar.zaje = true;
            return true;
        }
    }

    public final Task<Map<zai<?>, String>> zaa(Iterable<? extends GoogleApi<?>> iterable) {
        zak zakVar = new zak(iterable);
        Handler handler = this.handler;
        handler.sendMessage(handler.obtainMessage(2, zakVar));
        return zakVar.getTask();
    }

    private final void zab(GoogleApi<?> googleApi) {
        Object objZak = googleApi.zak();
        zaa<?> zaaVar = this.zaih.get(objZak);
        if (zaaVar == null) {
            zaaVar = new zaa<>(googleApi);
            this.zaih.put((zai<?>) objZak, zaaVar);
        }
        if (zaaVar.requiresSignIn()) {
            this.zaik.add((zai<?>) objZak);
        }
        zaaVar.connect();
    }

    public final <O extends Api.ApiOptions> void zaa(GoogleApi<O> googleApi, int i2, BaseImplementation.ApiMethodImpl<? extends Result, Api.AnyClient> apiMethodImpl) {
        zae zaeVar = new zae(i2, apiMethodImpl);
        Handler handler = this.handler;
        handler.sendMessage(handler.obtainMessage(4, new zabv(zaeVar, this.zaig.get(), googleApi)));
    }

    public final <O extends Api.ApiOptions, ResultT> void zaa(GoogleApi<O> googleApi, int i2, TaskApiCall<Api.AnyClient, ResultT> taskApiCall, TaskCompletionSource<ResultT> taskCompletionSource, StatusExceptionMapper statusExceptionMapper) {
        zag zagVar = new zag(i2, taskApiCall, taskCompletionSource, statusExceptionMapper);
        Handler handler = this.handler;
        handler.sendMessage(handler.obtainMessage(4, new zabv(zagVar, this.zaig.get(), googleApi)));
    }

    final void zab(zaae zaaeVar) {
        synchronized (lock) {
            try {
                if (this.zaii == zaaeVar) {
                    this.zaii = null;
                    this.zaij.clear();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final <O extends Api.ApiOptions> Task<Void> zaa(GoogleApi<O> googleApi, RegisterListenerMethod<Api.AnyClient, ?> registerListenerMethod, UnregisterListenerMethod<Api.AnyClient, ?> unregisterListenerMethod) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        zaf zafVar = new zaf(new zabw(registerListenerMethod, unregisterListenerMethod), taskCompletionSource);
        Handler handler = this.handler;
        handler.sendMessage(handler.obtainMessage(8, new zabv(zafVar, this.zaig.get(), googleApi)));
        return taskCompletionSource.getTask();
    }

    public final <O extends Api.ApiOptions> Task<Boolean> zaa(GoogleApi<O> googleApi, ListenerHolder.ListenerKey<?> listenerKey) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        zah zahVar = new zah(listenerKey, taskCompletionSource);
        Handler handler = this.handler;
        handler.sendMessage(handler.obtainMessage(13, new zabv(zahVar, this.zaig.get(), googleApi)));
        return taskCompletionSource.getTask();
    }

    final PendingIntent zaa(zai<?> zaiVar, int i2) {
        com.google.android.gms.signin.zad zadVarZabq;
        zaa<?> zaaVar = this.zaih.get(zaiVar);
        if (zaaVar == null || (zadVarZabq = zaaVar.zabq()) == null) {
            return null;
        }
        return PendingIntent.getActivity(this.zaic, i2, zadVarZabq.getSignInIntent(), 134217728);
    }

    public final void zaa(ConnectionResult connectionResult, int i2) {
        if (zac(connectionResult, i2)) {
            return;
        }
        Handler handler = this.handler;
        handler.sendMessage(handler.obtainMessage(5, i2, 0, connectionResult));
    }
}
