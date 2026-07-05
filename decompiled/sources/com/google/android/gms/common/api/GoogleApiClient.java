package com.google.android.gms.common.api;

import a.a;
import android.accounts.Account;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.FragmentActivity;
import android.support.v4.util.b;
import android.view.View;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.api.internal.LifecycleActivity;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.SignInConnectionListener;
import com.google.android.gms.common.api.internal.zaaw;
import com.google.android.gms.common.api.internal.zacm;
import com.google.android.gms.common.api.internal.zaj;
import com.google.android.gms.common.api.internal.zaq;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.signin.SignInOptions;
import com.google.android.gms.signin.zad;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@KeepForSdk
public abstract class GoogleApiClient {

    @KeepForSdk
    public static final String DEFAULT_ACCOUNT = "<<default account>>";
    public static final int SIGN_IN_MODE_OPTIONAL = 2;
    public static final int SIGN_IN_MODE_REQUIRED = 1;
    private static final Set<GoogleApiClient> zabq = Collections.newSetFromMap(new WeakHashMap());

    public interface ConnectionCallbacks {
        public static final int CAUSE_NETWORK_LOST = 2;
        public static final int CAUSE_SERVICE_DISCONNECTED = 1;

        void onConnected(Bundle bundle);

        void onConnectionSuspended(int i2);
    }

    public interface OnConnectionFailedListener {
        void onConnectionFailed(ConnectionResult connectionResult);
    }

    public static void dumpAll(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        Set<GoogleApiClient> set = zabq;
        synchronized (set) {
            try {
                String strConcat = String.valueOf(str).concat("  ");
                int i2 = 0;
                for (GoogleApiClient googleApiClient : set) {
                    printWriter.append((CharSequence) str).append("GoogleApiClient#").println(i2);
                    googleApiClient.dump(strConcat, fileDescriptor, printWriter, strArr);
                    i2++;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @KeepForSdk
    public static Set<GoogleApiClient> getAllClients() {
        Set<GoogleApiClient> set = zabq;
        synchronized (set) {
        }
        return set;
    }

    public abstract ConnectionResult blockingConnect();

    public abstract ConnectionResult blockingConnect(long j2, TimeUnit timeUnit);

    public abstract PendingResult<Status> clearDefaultAccountAndReconnect();

    public abstract void connect();

    public void connect(int i2) {
        throw new UnsupportedOperationException();
    }

    public abstract void disconnect();

    public abstract void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr);

    @KeepForSdk
    public <A extends Api.AnyClient, R extends Result, T extends BaseImplementation.ApiMethodImpl<R, A>> T enqueue(T t2) {
        throw new UnsupportedOperationException();
    }

    @KeepForSdk
    public <A extends Api.AnyClient, T extends BaseImplementation.ApiMethodImpl<? extends Result, A>> T execute(T t2) {
        throw new UnsupportedOperationException();
    }

    @KeepForSdk
    public <C extends Api.Client> C getClient(Api.AnyClientKey<C> anyClientKey) {
        throw new UnsupportedOperationException();
    }

    public abstract ConnectionResult getConnectionResult(Api<?> api);

    @KeepForSdk
    public Context getContext() {
        throw new UnsupportedOperationException();
    }

    @KeepForSdk
    public Looper getLooper() {
        throw new UnsupportedOperationException();
    }

    @KeepForSdk
    public boolean hasApi(Api<?> api) {
        throw new UnsupportedOperationException();
    }

    public abstract boolean hasConnectedApi(Api<?> api);

    public abstract boolean isConnected();

    public abstract boolean isConnecting();

    public abstract boolean isConnectionCallbacksRegistered(ConnectionCallbacks connectionCallbacks);

    public abstract boolean isConnectionFailedListenerRegistered(OnConnectionFailedListener onConnectionFailedListener);

    @KeepForSdk
    public boolean maybeSignIn(SignInConnectionListener signInConnectionListener) {
        throw new UnsupportedOperationException();
    }

    @KeepForSdk
    public void maybeSignOut() {
        throw new UnsupportedOperationException();
    }

    public abstract void reconnect();

    public abstract void registerConnectionCallbacks(ConnectionCallbacks connectionCallbacks);

    public abstract void registerConnectionFailedListener(OnConnectionFailedListener onConnectionFailedListener);

    @KeepForSdk
    public <L> ListenerHolder<L> registerListener(L l2) {
        throw new UnsupportedOperationException();
    }

    public abstract void stopAutoManage(FragmentActivity fragmentActivity);

    public abstract void unregisterConnectionCallbacks(ConnectionCallbacks connectionCallbacks);

    public abstract void unregisterConnectionFailedListener(OnConnectionFailedListener onConnectionFailedListener);

    public void zaa(zacm zacmVar) {
        throw new UnsupportedOperationException();
    }

    public void zab(zacm zacmVar) {
        throw new UnsupportedOperationException();
    }

    @KeepForSdk
    public static final class Builder {
        private final Context mContext;
        private Looper zabj;
        private final Set<Scope> zabr;
        private final Set<Scope> zabs;
        private int zabt;
        private View zabu;
        private String zabv;
        private String zabw;
        private final Map<Api<?>, ClientSettings.OptionalApiSettings> zabx;
        private final Map<Api<?>, Api.ApiOptions> zaby;
        private LifecycleActivity zabz;
        private int zaca;
        private OnConnectionFailedListener zacb;
        private GoogleApiAvailability zacc;
        private Api.AbstractClientBuilder<? extends zad, SignInOptions> zacd;
        private final ArrayList<ConnectionCallbacks> zace;
        private final ArrayList<OnConnectionFailedListener> zacf;
        private boolean zacg;
        private Account zax;

        @KeepForSdk
        public Builder(Context context) {
            this.zabr = new HashSet();
            this.zabs = new HashSet();
            this.zabx = new b();
            this.zaby = new b();
            this.zaca = -1;
            this.zacc = GoogleApiAvailability.getInstance();
            this.zacd = com.google.android.gms.signin.zaa.zapg;
            this.zace = new ArrayList<>();
            this.zacf = new ArrayList<>();
            this.zacg = false;
            this.mContext = context;
            this.zabj = context.getMainLooper();
            this.zabv = context.getPackageName();
            this.zabw = context.getClass().getName();
        }

        private final <O extends Api.ApiOptions> void zaa(Api<O> api, O o2, Scope... scopeArr) {
            HashSet hashSet = new HashSet(api.zah().getImpliedScopes(o2));
            for (Scope scope : scopeArr) {
                hashSet.add(scope);
            }
            this.zabx.put(api, new ClientSettings.OptionalApiSettings(hashSet));
        }

        public final Builder addApi(Api<? extends Api.ApiOptions.NotRequiredOptions> api) {
            Preconditions.checkNotNull(api, "Api must not be null");
            this.zaby.put(api, null);
            List<Scope> impliedScopes = api.zah().getImpliedScopes(null);
            this.zabs.addAll(impliedScopes);
            this.zabr.addAll(impliedScopes);
            return this;
        }

        public final Builder addApiIfAvailable(Api<? extends Api.ApiOptions.NotRequiredOptions> api, Scope... scopeArr) {
            Preconditions.checkNotNull(api, "Api must not be null");
            this.zaby.put(api, null);
            zaa(api, null, scopeArr);
            return this;
        }

        public final Builder addConnectionCallbacks(ConnectionCallbacks connectionCallbacks) {
            Preconditions.checkNotNull(connectionCallbacks, "Listener must not be null");
            this.zace.add(connectionCallbacks);
            return this;
        }

        public final Builder addOnConnectionFailedListener(OnConnectionFailedListener onConnectionFailedListener) {
            Preconditions.checkNotNull(onConnectionFailedListener, "Listener must not be null");
            this.zacf.add(onConnectionFailedListener);
            return this;
        }

        public final Builder addScope(Scope scope) {
            Preconditions.checkNotNull(scope, "Scope must not be null");
            this.zabr.add(scope);
            return this;
        }

        @KeepForSdk
        public final Builder addScopeNames(String[] strArr) {
            for (String str : strArr) {
                this.zabr.add(new Scope(str));
            }
            return this;
        }

        public final GoogleApiClient build() {
            Preconditions.checkArgument(!this.zaby.isEmpty(), "must call addApi() to add at least one API");
            ClientSettings clientSettingsBuildClientSettings = buildClientSettings();
            Map<Api<?>, ClientSettings.OptionalApiSettings> optionalApiSettings = clientSettingsBuildClientSettings.getOptionalApiSettings();
            b bVar = new b();
            b bVar2 = new b();
            ArrayList arrayList = new ArrayList();
            Api<?> api = null;
            boolean z2 = false;
            for (Api<?> api2 : this.zaby.keySet()) {
                Api.ApiOptions apiOptions = this.zaby.get(api2);
                boolean z3 = optionalApiSettings.get(api2) != null;
                bVar.put(api2, Boolean.valueOf(z3));
                zaq zaqVar = new zaq(api2, z3);
                arrayList.add(zaqVar);
                Api.AbstractClientBuilder<?, O> abstractClientBuilderZai = api2.zai();
                Api.Client clientBuildClient = abstractClientBuilderZai.buildClient(this.mContext, this.zabj, clientSettingsBuildClientSettings, apiOptions, zaqVar, zaqVar);
                bVar2.put(api2.getClientKey(), clientBuildClient);
                if (abstractClientBuilderZai.getPriority() == 1) {
                    z2 = apiOptions != null;
                }
                if (clientBuildClient.providesSignIn()) {
                    if (api != null) {
                        String name = api2.getName();
                        String name2 = api.getName();
                        StringBuilder sb = new StringBuilder(a.e(a.e(21, name), name2));
                        sb.append(name);
                        sb.append(" cannot be used with ");
                        sb.append(name2);
                        throw new IllegalStateException(sb.toString());
                    }
                    api = api2;
                }
            }
            if (api != null) {
                if (z2) {
                    String name3 = api.getName();
                    StringBuilder sb2 = new StringBuilder(a.e(82, name3));
                    sb2.append("With using ");
                    sb2.append(name3);
                    sb2.append(", GamesOptions can only be specified within GoogleSignInOptions.Builder");
                    throw new IllegalStateException(sb2.toString());
                }
                Preconditions.checkState(this.zax == null, "Must not set an account in GoogleApiClient.Builder when using %s. Set account in GoogleSignInOptions.Builder instead", api.getName());
                Preconditions.checkState(this.zabr.equals(this.zabs), "Must not set scopes in GoogleApiClient.Builder when using %s. Set account in GoogleSignInOptions.Builder instead.", api.getName());
            }
            zaaw zaawVar = new zaaw(this.mContext, new ReentrantLock(), this.zabj, clientSettingsBuildClientSettings, this.zacc, this.zacd, bVar, this.zace, this.zacf, bVar2, this.zaca, zaaw.zaa(bVar2.values(), true), arrayList, false);
            synchronized (GoogleApiClient.zabq) {
                GoogleApiClient.zabq.add(zaawVar);
            }
            if (this.zaca >= 0) {
                zaj.zaa(this.zabz).zaa(this.zaca, zaawVar, this.zacb);
            }
            return zaawVar;
        }

        @VisibleForTesting
        @KeepForSdk
        public final ClientSettings buildClientSettings() {
            SignInOptions signInOptions = SignInOptions.DEFAULT;
            Map<Api<?>, Api.ApiOptions> map = this.zaby;
            Api<SignInOptions> api = com.google.android.gms.signin.zaa.API;
            if (map.containsKey(api)) {
                signInOptions = (SignInOptions) this.zaby.get(api);
            }
            return new ClientSettings(this.zax, this.zabr, this.zabx, this.zabt, this.zabu, this.zabv, this.zabw, signInOptions);
        }

        public final Builder enableAutoManage(FragmentActivity fragmentActivity, int i2, OnConnectionFailedListener onConnectionFailedListener) {
            LifecycleActivity lifecycleActivity = new LifecycleActivity((Activity) fragmentActivity);
            Preconditions.checkArgument(i2 >= 0, "clientId must be non-negative");
            this.zaca = i2;
            this.zacb = onConnectionFailedListener;
            this.zabz = lifecycleActivity;
            return this;
        }

        public final Builder setAccountName(String str) {
            this.zax = str == null ? null : new Account(str, "com.google");
            return this;
        }

        public final Builder setGravityForPopups(int i2) {
            this.zabt = i2;
            return this;
        }

        public final Builder setHandler(Handler handler) {
            Preconditions.checkNotNull(handler, "Handler must not be null");
            this.zabj = handler.getLooper();
            return this;
        }

        public final Builder setViewForPopups(View view) {
            Preconditions.checkNotNull(view, "View must not be null");
            this.zabu = view;
            return this;
        }

        public final Builder useDefaultAccount() {
            return setAccountName("<<default account>>");
        }

        public final <O extends Api.ApiOptions.HasOptions> Builder addApiIfAvailable(Api<O> api, O o2, Scope... scopeArr) {
            Preconditions.checkNotNull(api, "Api must not be null");
            Preconditions.checkNotNull(o2, "Null options are not permitted for this Api");
            this.zaby.put(api, o2);
            zaa(api, o2, scopeArr);
            return this;
        }

        public final <O extends Api.ApiOptions.HasOptions> Builder addApi(Api<O> api, O o2) {
            Preconditions.checkNotNull(api, "Api must not be null");
            Preconditions.checkNotNull(o2, "Null options are not permitted for this Api");
            this.zaby.put(api, o2);
            List<Scope> impliedScopes = api.zah().getImpliedScopes(o2);
            this.zabs.addAll(impliedScopes);
            this.zabr.addAll(impliedScopes);
            return this;
        }

        public final Builder enableAutoManage(FragmentActivity fragmentActivity, OnConnectionFailedListener onConnectionFailedListener) {
            return enableAutoManage(fragmentActivity, 0, onConnectionFailedListener);
        }

        @KeepForSdk
        public Builder(Context context, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
            this(context);
            Preconditions.checkNotNull(connectionCallbacks, "Must provide a connected listener");
            this.zace.add(connectionCallbacks);
            Preconditions.checkNotNull(onConnectionFailedListener, "Must provide a connection failed listener");
            this.zacf.add(onConnectionFailedListener);
        }
    }
}
