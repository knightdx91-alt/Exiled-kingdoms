package com.google.android.gms.common.internal;

import a.a;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class GmsClientEventManager implements Handler.Callback {
    private final Handler mHandler;
    private final GmsClientEventState zaok;
    private final ArrayList<GoogleApiClient.ConnectionCallbacks> zaol = new ArrayList<>();

    @VisibleForTesting
    private final ArrayList<GoogleApiClient.ConnectionCallbacks> zaom = new ArrayList<>();
    private final ArrayList<GoogleApiClient.OnConnectionFailedListener> zaon = new ArrayList<>();
    private volatile boolean zaoo = false;
    private final AtomicInteger zaop = new AtomicInteger(0);
    private boolean zaoq = false;
    private final Object mLock = new Object();

    @VisibleForTesting
    public interface GmsClientEventState {
        Bundle getConnectionHint();

        boolean isConnected();
    }

    public GmsClientEventManager(Looper looper, GmsClientEventState gmsClientEventState) {
        this.zaok = gmsClientEventState;
        this.mHandler = new com.google.android.gms.internal.base.zal(looper, this);
    }

    public final boolean areCallbacksEnabled() {
        return this.zaoo;
    }

    public final void disableCallbacks() {
        this.zaoo = false;
        this.zaop.incrementAndGet();
    }

    public final void enableCallbacks() {
        this.zaoo = true;
    }

    @Override // android.os.Handler.Callback
    public final boolean handleMessage(Message message) {
        int i2 = message.what;
        if (i2 != 1) {
            Log.wtf("GmsClientEvents", a.g(45, i2, "Don't know how to handle message: "), new Exception());
            return false;
        }
        GoogleApiClient.ConnectionCallbacks connectionCallbacks = (GoogleApiClient.ConnectionCallbacks) message.obj;
        synchronized (this.mLock) {
            try {
                if (this.zaoo && this.zaok.isConnected() && this.zaol.contains(connectionCallbacks)) {
                    connectionCallbacks.onConnected(this.zaok.getConnectionHint());
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return true;
    }

    public final boolean isConnectionCallbacksRegistered(GoogleApiClient.ConnectionCallbacks connectionCallbacks) {
        boolean zContains;
        Preconditions.checkNotNull(connectionCallbacks);
        synchronized (this.mLock) {
            zContains = this.zaol.contains(connectionCallbacks);
        }
        return zContains;
    }

    public final boolean isConnectionFailedListenerRegistered(GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        boolean zContains;
        Preconditions.checkNotNull(onConnectionFailedListener);
        synchronized (this.mLock) {
            zContains = this.zaon.contains(onConnectionFailedListener);
        }
        return zContains;
    }

    @VisibleForTesting
    public final void onConnectionFailure(ConnectionResult connectionResult) {
        int i2 = 0;
        Preconditions.checkState(Looper.myLooper() == this.mHandler.getLooper(), "onConnectionFailure must only be called on the Handler thread");
        this.mHandler.removeMessages(1);
        synchronized (this.mLock) {
            try {
                ArrayList arrayList = new ArrayList(this.zaon);
                int i3 = this.zaop.get();
                int size = arrayList.size();
                while (i2 < size) {
                    Object obj = arrayList.get(i2);
                    i2++;
                    GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener = (GoogleApiClient.OnConnectionFailedListener) obj;
                    if (this.zaoo && this.zaop.get() == i3) {
                        if (this.zaon.contains(onConnectionFailedListener)) {
                            onConnectionFailedListener.onConnectionFailed(connectionResult);
                        }
                    }
                    return;
                }
            } finally {
            }
        }
    }

    @VisibleForTesting
    protected final void onConnectionSuccess() {
        synchronized (this.mLock) {
            onConnectionSuccess(this.zaok.getConnectionHint());
        }
    }

    @VisibleForTesting
    public final void onUnintentionalDisconnection(int i2) {
        Preconditions.checkState(Looper.myLooper() == this.mHandler.getLooper(), "onUnintentionalDisconnection must only be called on the Handler thread");
        this.mHandler.removeMessages(1);
        synchronized (this.mLock) {
            try {
                this.zaoq = true;
                ArrayList arrayList = new ArrayList(this.zaol);
                int i3 = this.zaop.get();
                int size = arrayList.size();
                int i4 = 0;
                while (i4 < size) {
                    Object obj = arrayList.get(i4);
                    i4++;
                    GoogleApiClient.ConnectionCallbacks connectionCallbacks = (GoogleApiClient.ConnectionCallbacks) obj;
                    if (!this.zaoo || this.zaop.get() != i3) {
                        break;
                    } else if (this.zaol.contains(connectionCallbacks)) {
                        connectionCallbacks.onConnectionSuspended(i2);
                    }
                }
                this.zaom.clear();
                this.zaoq = false;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void registerConnectionCallbacks(GoogleApiClient.ConnectionCallbacks connectionCallbacks) {
        Preconditions.checkNotNull(connectionCallbacks);
        synchronized (this.mLock) {
            try {
                if (this.zaol.contains(connectionCallbacks)) {
                    String strValueOf = String.valueOf(connectionCallbacks);
                    StringBuilder sb = new StringBuilder(strValueOf.length() + 62);
                    sb.append("registerConnectionCallbacks(): listener ");
                    sb.append(strValueOf);
                    sb.append(" is already registered");
                    Log.w("GmsClientEvents", sb.toString());
                } else {
                    this.zaol.add(connectionCallbacks);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        if (this.zaok.isConnected()) {
            Handler handler = this.mHandler;
            handler.sendMessage(handler.obtainMessage(1, connectionCallbacks));
        }
    }

    public final void registerConnectionFailedListener(GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        Preconditions.checkNotNull(onConnectionFailedListener);
        synchronized (this.mLock) {
            try {
                if (this.zaon.contains(onConnectionFailedListener)) {
                    String strValueOf = String.valueOf(onConnectionFailedListener);
                    StringBuilder sb = new StringBuilder(strValueOf.length() + 67);
                    sb.append("registerConnectionFailedListener(): listener ");
                    sb.append(strValueOf);
                    sb.append(" is already registered");
                    Log.w("GmsClientEvents", sb.toString());
                } else {
                    this.zaon.add(onConnectionFailedListener);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void unregisterConnectionCallbacks(GoogleApiClient.ConnectionCallbacks connectionCallbacks) {
        Preconditions.checkNotNull(connectionCallbacks);
        synchronized (this.mLock) {
            try {
                if (!this.zaol.remove(connectionCallbacks)) {
                    String strValueOf = String.valueOf(connectionCallbacks);
                    StringBuilder sb = new StringBuilder(strValueOf.length() + 52);
                    sb.append("unregisterConnectionCallbacks(): listener ");
                    sb.append(strValueOf);
                    sb.append(" not found");
                    Log.w("GmsClientEvents", sb.toString());
                } else if (this.zaoq) {
                    this.zaom.add(connectionCallbacks);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void unregisterConnectionFailedListener(GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        Preconditions.checkNotNull(onConnectionFailedListener);
        synchronized (this.mLock) {
            try {
                if (!this.zaon.remove(onConnectionFailedListener)) {
                    String strValueOf = String.valueOf(onConnectionFailedListener);
                    StringBuilder sb = new StringBuilder(strValueOf.length() + 57);
                    sb.append("unregisterConnectionFailedListener(): listener ");
                    sb.append(strValueOf);
                    sb.append(" not found");
                    Log.w("GmsClientEvents", sb.toString());
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @VisibleForTesting
    public final void onConnectionSuccess(Bundle bundle) {
        boolean z2 = true;
        Preconditions.checkState(Looper.myLooper() == this.mHandler.getLooper(), "onConnectionSuccess must only be called on the Handler thread");
        synchronized (this.mLock) {
            try {
                Preconditions.checkState(!this.zaoq);
                this.mHandler.removeMessages(1);
                this.zaoq = true;
                if (this.zaom.size() != 0) {
                    z2 = false;
                }
                Preconditions.checkState(z2);
                ArrayList arrayList = new ArrayList(this.zaol);
                int i2 = this.zaop.get();
                int size = arrayList.size();
                int i3 = 0;
                while (i3 < size) {
                    Object obj = arrayList.get(i3);
                    i3++;
                    GoogleApiClient.ConnectionCallbacks connectionCallbacks = (GoogleApiClient.ConnectionCallbacks) obj;
                    if (!this.zaoo || !this.zaok.isConnected() || this.zaop.get() != i2) {
                        break;
                    } else if (!this.zaom.contains(connectionCallbacks)) {
                        connectionCallbacks.onConnected(bundle);
                    }
                }
                this.zaom.clear();
                this.zaoq = false;
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
