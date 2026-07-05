package com.google.android.gms.common.api;

import android.accounts.Account;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.api.internal.ApiExceptionMapper;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.api.internal.GoogleApiManager;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.ListenerHolders;
import com.google.android.gms.common.api.internal.RegisterListenerMethod;
import com.google.android.gms.common.api.internal.RegistrationMethods;
import com.google.android.gms.common.api.internal.StatusExceptionMapper;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.api.internal.UnregisterListenerMethod;
import com.google.android.gms.common.api.internal.zaae;
import com.google.android.gms.common.api.internal.zabp;
import com.google.android.gms.common.api.internal.zace;
import com.google.android.gms.common.api.internal.zai;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Collections;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@KeepForSdk
public class GoogleApi<O extends Api.ApiOptions> {
    private final Api<O> mApi;
    private final Context mContext;
    private final int mId;
    private final O zabh;
    private final zai<O> zabi;
    private final Looper zabj;
    private final GoogleApiClient zabk;
    private final StatusExceptionMapper zabl;
    protected final GoogleApiManager zabm;

    @KeepForSdk
    public static class Settings {

        @KeepForSdk
        public static final Settings DEFAULT_SETTINGS = new Builder().build();
        public final StatusExceptionMapper zabn;
        public final Looper zabo;

        @KeepForSdk
        public static class Builder {
            private Looper zabj;
            private StatusExceptionMapper zabl;

            @KeepForSdk
            public Builder() {
            }

            /* JADX WARN: Multi-variable type inference failed */
            @KeepForSdk
            public Settings build() {
                if (this.zabl == null) {
                    this.zabl = new ApiExceptionMapper();
                }
                if (this.zabj == null) {
                    this.zabj = Looper.getMainLooper();
                }
                return new Settings(this.zabl, this.zabj);
            }

            @KeepForSdk
            public Builder setLooper(Looper looper) {
                Preconditions.checkNotNull(looper, "Looper must not be null.");
                this.zabj = looper;
                return this;
            }

            @KeepForSdk
            public Builder setMapper(StatusExceptionMapper statusExceptionMapper) {
                Preconditions.checkNotNull(statusExceptionMapper, "StatusExceptionMapper must not be null.");
                this.zabl = statusExceptionMapper;
                return this;
            }
        }

        @KeepForSdk
        private Settings(StatusExceptionMapper statusExceptionMapper, Account account, Looper looper) {
            this.zabn = statusExceptionMapper;
            this.zabo = looper;
        }
    }

    @KeepForSdk
    protected GoogleApi(Context context, Api<O> api, Looper looper) {
        Preconditions.checkNotNull(context, "Null context is not permitted.");
        Preconditions.checkNotNull(api, "Api must not be null.");
        Preconditions.checkNotNull(looper, "Looper must not be null.");
        Context applicationContext = context.getApplicationContext();
        this.mContext = applicationContext;
        this.mApi = api;
        this.zabh = null;
        this.zabj = looper;
        this.zabi = zai.zaa(api);
        this.zabk = new zabp(this);
        GoogleApiManager googleApiManagerZab = GoogleApiManager.zab(applicationContext);
        this.zabm = googleApiManagerZab;
        this.mId = googleApiManagerZab.zabd();
        this.zabl = new ApiExceptionMapper();
    }

    private final <A extends Api.AnyClient, T extends BaseImplementation.ApiMethodImpl<? extends Result, A>> T zaa(int i2, T t2) {
        t2.zau();
        this.zabm.zaa(this, i2, (BaseImplementation.ApiMethodImpl<? extends Result, Api.AnyClient>) t2);
        return t2;
    }

    @KeepForSdk
    public GoogleApiClient asGoogleApiClient() {
        return this.zabk;
    }

    @KeepForSdk
    protected ClientSettings.Builder createClientSettingsBuilder() {
        Account account;
        GoogleSignInAccount googleSignInAccount;
        GoogleSignInAccount googleSignInAccount2;
        ClientSettings.Builder builder = new ClientSettings.Builder();
        O o2 = this.zabh;
        if (!(o2 instanceof Api.ApiOptions.HasGoogleSignInAccountOptions) || (googleSignInAccount2 = ((Api.ApiOptions.HasGoogleSignInAccountOptions) o2).getGoogleSignInAccount()) == null) {
            O o3 = this.zabh;
            account = o3 instanceof Api.ApiOptions.HasAccountOptions ? ((Api.ApiOptions.HasAccountOptions) o3).getAccount() : null;
        } else {
            account = googleSignInAccount2.getAccount();
        }
        ClientSettings.Builder account2 = builder.setAccount(account);
        O o4 = this.zabh;
        return account2.addAllRequiredScopes((!(o4 instanceof Api.ApiOptions.HasGoogleSignInAccountOptions) || (googleSignInAccount = ((Api.ApiOptions.HasGoogleSignInAccountOptions) o4).getGoogleSignInAccount()) == null) ? Collections.emptySet() : googleSignInAccount.getRequestedScopes()).setRealClientClassName(this.mContext.getClass().getName()).setRealClientPackageName(this.mContext.getPackageName());
    }

    @KeepForSdk
    protected Task<Boolean> disconnectService() {
        return this.zabm.zac((GoogleApi<?>) this);
    }

    @KeepForSdk
    public <A extends Api.AnyClient, T extends BaseImplementation.ApiMethodImpl<? extends Result, A>> T doBestEffortWrite(T t2) {
        return (T) zaa(2, t2);
    }

    @KeepForSdk
    public <A extends Api.AnyClient, T extends BaseImplementation.ApiMethodImpl<? extends Result, A>> T doRead(T t2) {
        return (T) zaa(0, t2);
    }

    @KeepForSdk
    @Deprecated
    public <A extends Api.AnyClient, T extends RegisterListenerMethod<A, ?>, U extends UnregisterListenerMethod<A, ?>> Task<Void> doRegisterEventListener(T t2, U u2) {
        Preconditions.checkNotNull(t2);
        Preconditions.checkNotNull(u2);
        Preconditions.checkNotNull(t2.getListenerKey(), "Listener has already been released.");
        Preconditions.checkNotNull(u2.getListenerKey(), "Listener has already been released.");
        Preconditions.checkArgument(t2.getListenerKey().equals(u2.getListenerKey()), "Listener registration and unregistration methods must be constructed with the same ListenerHolder.");
        return this.zabm.zaa(this, (RegisterListenerMethod<Api.AnyClient, ?>) t2, (UnregisterListenerMethod<Api.AnyClient, ?>) u2);
    }

    @KeepForSdk
    public Task<Boolean> doUnregisterEventListener(ListenerHolder.ListenerKey<?> listenerKey) {
        Preconditions.checkNotNull(listenerKey, "Listener key cannot be null.");
        return this.zabm.zaa(this, listenerKey);
    }

    @KeepForSdk
    public <A extends Api.AnyClient, T extends BaseImplementation.ApiMethodImpl<? extends Result, A>> T doWrite(T t2) {
        return (T) zaa(1, t2);
    }

    public final Api<O> getApi() {
        return this.mApi;
    }

    @KeepForSdk
    public O getApiOptions() {
        return this.zabh;
    }

    @KeepForSdk
    public Context getApplicationContext() {
        return this.mContext;
    }

    public final int getInstanceId() {
        return this.mId;
    }

    @KeepForSdk
    public Looper getLooper() {
        return this.zabj;
    }

    @KeepForSdk
    public <L> ListenerHolder<L> registerListener(L l2, String str) {
        return ListenerHolders.createListenerHolder(l2, this.zabj, str);
    }

    public final zai<O> zak() {
        return this.zabi;
    }

    @KeepForSdk
    public <TResult, A extends Api.AnyClient> Task<TResult> doBestEffortWrite(TaskApiCall<A, TResult> taskApiCall) {
        return zaa(2, taskApiCall);
    }

    @KeepForSdk
    public <TResult, A extends Api.AnyClient> Task<TResult> doRead(TaskApiCall<A, TResult> taskApiCall) {
        return zaa(0, taskApiCall);
    }

    @KeepForSdk
    public <TResult, A extends Api.AnyClient> Task<TResult> doWrite(TaskApiCall<A, TResult> taskApiCall) {
        return zaa(1, taskApiCall);
    }

    private final <TResult, A extends Api.AnyClient> Task<TResult> zaa(int i2, TaskApiCall<A, TResult> taskApiCall) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.zabm.zaa(this, i2, taskApiCall, taskCompletionSource, this.zabl);
        return taskCompletionSource.getTask();
    }

    public Api.Client zaa(Looper looper, GoogleApiManager.zaa<O> zaaVar) {
        return this.mApi.zai().buildClient(this.mContext, looper, createClientSettingsBuilder().build(), this.zabh, zaaVar, zaaVar);
    }

    public zace zaa(Context context, Handler handler) {
        return new zace(context, handler, createClientSettingsBuilder().build());
    }

    @KeepForSdk
    public <A extends Api.AnyClient> Task<Void> doRegisterEventListener(RegistrationMethods<A, ?> registrationMethods) {
        Preconditions.checkNotNull(registrationMethods);
        Preconditions.checkNotNull(registrationMethods.zajy.getListenerKey(), "Listener has already been released.");
        Preconditions.checkNotNull(registrationMethods.zajz.getListenerKey(), "Listener has already been released.");
        return this.zabm.zaa(this, registrationMethods.zajy, registrationMethods.zajz);
    }

    @KeepForSdk
    @Deprecated
    public GoogleApi(Context context, Api<O> api, O o2, Looper looper, StatusExceptionMapper statusExceptionMapper) {
        this(context, api, o2, new Settings.Builder().setLooper(looper).setMapper(statusExceptionMapper).build());
    }

    @KeepForSdk
    public GoogleApi(Activity activity, Api<O> api, O o2, Settings settings) {
        Preconditions.checkNotNull(activity, "Null activity is not permitted.");
        Preconditions.checkNotNull(api, "Api must not be null.");
        Preconditions.checkNotNull(settings, "Settings must not be null; use Settings.DEFAULT_SETTINGS instead.");
        Context applicationContext = activity.getApplicationContext();
        this.mContext = applicationContext;
        this.mApi = api;
        this.zabh = o2;
        this.zabj = settings.zabo;
        zai<O> zaiVarZaa = zai.zaa(api, o2);
        this.zabi = zaiVarZaa;
        this.zabk = new zabp(this);
        GoogleApiManager googleApiManagerZab = GoogleApiManager.zab(applicationContext);
        this.zabm = googleApiManagerZab;
        this.mId = googleApiManagerZab.zabd();
        this.zabl = settings.zabn;
        if (!(activity instanceof GoogleApiActivity)) {
            zaae.zaa(activity, googleApiManagerZab, zaiVarZaa);
        }
        googleApiManagerZab.zaa((GoogleApi<?>) this);
    }

    @KeepForSdk
    public GoogleApi(Context context, Api<O> api, O o2, Settings settings) {
        Preconditions.checkNotNull(context, "Null context is not permitted.");
        Preconditions.checkNotNull(api, "Api must not be null.");
        Preconditions.checkNotNull(settings, "Settings must not be null; use Settings.DEFAULT_SETTINGS instead.");
        Context applicationContext = context.getApplicationContext();
        this.mContext = applicationContext;
        this.mApi = api;
        this.zabh = o2;
        this.zabj = settings.zabo;
        this.zabi = zai.zaa(api, o2);
        this.zabk = new zabp(this);
        GoogleApiManager googleApiManagerZab = GoogleApiManager.zab(applicationContext);
        this.zabm = googleApiManagerZab;
        this.mId = googleApiManagerZab.zabd();
        this.zabl = settings.zabn;
        googleApiManagerZab.zaa((GoogleApi<?>) this);
    }

    @KeepForSdk
    @Deprecated
    public GoogleApi(Activity activity, Api<O> api, O o2, StatusExceptionMapper statusExceptionMapper) {
        this(activity, (Api) api, (Api.ApiOptions) o2, new Settings.Builder().setMapper(statusExceptionMapper).setLooper(activity.getMainLooper()).build());
    }

    @KeepForSdk
    @Deprecated
    public GoogleApi(Context context, Api<O> api, O o2, StatusExceptionMapper statusExceptionMapper) {
        this(context, api, o2, new Settings.Builder().setMapper(statusExceptionMapper).build());
    }
}
