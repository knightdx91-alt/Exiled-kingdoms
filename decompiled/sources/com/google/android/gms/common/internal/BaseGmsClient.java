package com.google.android.gms.common.internal;

import a.a;
import android.accounts.Account;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.GmsClientSupervisor;
import com.google.android.gms.common.internal.IGmsCallbacks;
import com.google.android.gms.common.internal.IGmsServiceBroker;
import com.google.android.gms.common.util.VisibleForTesting;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@KeepForSdk
public abstract class BaseGmsClient<T extends IInterface> {

    @KeepForSdk
    public static final int CONNECT_STATE_CONNECTED = 4;

    @KeepForSdk
    public static final int CONNECT_STATE_DISCONNECTED = 1;

    @KeepForSdk
    public static final int CONNECT_STATE_DISCONNECTING = 5;

    @KeepForSdk
    public static final String DEFAULT_ACCOUNT = "<<default account>>";

    @KeepForSdk
    public static final String KEY_PENDING_INTENT = "pendingIntent";
    private final Context mContext;
    final Handler mHandler;
    private final Object mLock;
    private int zzbt;
    private long zzbu;
    private long zzbv;
    private int zzbw;
    private long zzbx;

    @VisibleForTesting
    private zzh zzby;
    private final Looper zzbz;
    private final GmsClientSupervisor zzca;
    private final GoogleApiAvailabilityLight zzcb;
    private final Object zzcc;
    private IGmsServiceBroker zzcd;

    @VisibleForTesting
    protected ConnectionProgressReportCallbacks zzce;
    private T zzcf;
    private final ArrayList<zzc<?>> zzcg;
    private zze zzch;
    private int zzci;
    private final BaseConnectionCallbacks zzcj;
    private final BaseOnConnectionFailedListener zzck;
    private final int zzcl;
    private final String zzcm;
    private ConnectionResult zzcn;
    private boolean zzco;
    private volatile com.google.android.gms.common.internal.zzb zzcp;

    @VisibleForTesting
    protected AtomicInteger zzcq;
    private static final Feature[] zzbs = new Feature[0];

    @KeepForSdk
    public static final String[] GOOGLE_PLUS_REQUIRED_FEATURES = {"service_esmobile", "service_googleme"};

    @KeepForSdk
    public interface BaseConnectionCallbacks {
        @KeepForSdk
        void onConnected(Bundle bundle);

        @KeepForSdk
        void onConnectionSuspended(int i2);
    }

    @KeepForSdk
    public interface BaseOnConnectionFailedListener {
        void onConnectionFailed(ConnectionResult connectionResult);
    }

    @KeepForSdk
    public interface ConnectionProgressReportCallbacks {
        @KeepForSdk
        void onReportServiceBinding(ConnectionResult connectionResult);
    }

    protected class LegacyClientCallbackAdapter implements ConnectionProgressReportCallbacks {
        @KeepForSdk
        public LegacyClientCallbackAdapter() {
        }

        @Override // com.google.android.gms.common.internal.BaseGmsClient.ConnectionProgressReportCallbacks
        public void onReportServiceBinding(ConnectionResult connectionResult) {
            if (connectionResult.isSuccess()) {
                BaseGmsClient baseGmsClient = BaseGmsClient.this;
                baseGmsClient.getRemoteService(null, baseGmsClient.getScopes());
            } else if (BaseGmsClient.this.zzck != null) {
                BaseGmsClient.this.zzck.onConnectionFailed(connectionResult);
            }
        }
    }

    @KeepForSdk
    public interface SignOutCallbacks {
        @KeepForSdk
        void onSignOutComplete();
    }

    private abstract class zza extends zzc<Boolean> {
        private final int statusCode;
        private final Bundle zzcr;

        protected zza(int i2, Bundle bundle) {
            super(Boolean.TRUE);
            this.statusCode = i2;
            this.zzcr = bundle;
        }

        protected abstract void zza(ConnectionResult connectionResult);

        @Override // com.google.android.gms.common.internal.BaseGmsClient.zzc
        protected final /* synthetic */ void zza(Boolean bool) {
            if (bool == null) {
                BaseGmsClient.this.zza(1, (IInterface) null);
                return;
            }
            int i2 = this.statusCode;
            if (i2 == 0) {
                if (zzm()) {
                    return;
                }
                BaseGmsClient.this.zza(1, (IInterface) null);
                zza(new ConnectionResult(8, null));
                return;
            }
            if (i2 != 10) {
                BaseGmsClient.this.zza(1, (IInterface) null);
                Bundle bundle = this.zzcr;
                zza(new ConnectionResult(this.statusCode, bundle != null ? (PendingIntent) bundle.getParcelable(BaseGmsClient.KEY_PENDING_INTENT) : null));
                return;
            }
            BaseGmsClient.this.zza(1, (IInterface) null);
            String simpleName = getClass().getSimpleName();
            String startServiceAction = BaseGmsClient.this.getStartServiceAction();
            String serviceDescriptor = BaseGmsClient.this.getServiceDescriptor();
            StringBuilder sb = new StringBuilder("A fatal developer error has occurred. Class name: ");
            sb.append(simpleName);
            sb.append(". Start service action: ");
            sb.append(startServiceAction);
            sb.append(". Service Descriptor: ");
            throw new IllegalStateException(a.m(serviceDescriptor, ". ", sb));
        }

        protected abstract boolean zzm();

        @Override // com.google.android.gms.common.internal.BaseGmsClient.zzc
        protected final void zzn() {
        }
    }

    final class zzb extends com.google.android.gms.internal.common.zze {
        public zzb(Looper looper) {
            super(looper);
        }

        private static void zza(Message message) {
            zzc zzcVar = (zzc) message.obj;
            zzcVar.zzn();
            zzcVar.unregister();
        }

        private static boolean zzb(Message message) {
            int i2 = message.what;
            return i2 == 2 || i2 == 1 || i2 == 7;
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            if (BaseGmsClient.this.zzcq.get() != message.arg1) {
                if (zzb(message)) {
                    zza(message);
                    return;
                }
                return;
            }
            int i2 = message.what;
            if ((i2 == 1 || i2 == 7 || i2 == 4 || i2 == 5) && !BaseGmsClient.this.isConnecting()) {
                zza(message);
                return;
            }
            int i3 = message.what;
            if (i3 == 4) {
                BaseGmsClient.this.zzcn = new ConnectionResult(message.arg2);
                if (BaseGmsClient.this.zzl() && !BaseGmsClient.this.zzco) {
                    BaseGmsClient.this.zza(3, (IInterface) null);
                    return;
                }
                ConnectionResult connectionResult = BaseGmsClient.this.zzcn != null ? BaseGmsClient.this.zzcn : new ConnectionResult(8);
                BaseGmsClient.this.zzce.onReportServiceBinding(connectionResult);
                BaseGmsClient.this.onConnectionFailed(connectionResult);
                return;
            }
            if (i3 == 5) {
                ConnectionResult connectionResult2 = BaseGmsClient.this.zzcn != null ? BaseGmsClient.this.zzcn : new ConnectionResult(8);
                BaseGmsClient.this.zzce.onReportServiceBinding(connectionResult2);
                BaseGmsClient.this.onConnectionFailed(connectionResult2);
                return;
            }
            if (i3 == 3) {
                Object obj = message.obj;
                ConnectionResult connectionResult3 = new ConnectionResult(message.arg2, obj instanceof PendingIntent ? (PendingIntent) obj : null);
                BaseGmsClient.this.zzce.onReportServiceBinding(connectionResult3);
                BaseGmsClient.this.onConnectionFailed(connectionResult3);
                return;
            }
            if (i3 == 6) {
                BaseGmsClient.this.zza(5, (IInterface) null);
                if (BaseGmsClient.this.zzcj != null) {
                    BaseGmsClient.this.zzcj.onConnectionSuspended(message.arg2);
                }
                BaseGmsClient.this.onConnectionSuspended(message.arg2);
                BaseGmsClient.this.zza(5, 1, (IInterface) null);
                return;
            }
            if (i3 == 2 && !BaseGmsClient.this.isConnected()) {
                zza(message);
            } else if (zzb(message)) {
                ((zzc) message.obj).zzo();
            } else {
                Log.wtf("GmsClient", a.g(45, message.what, "Don't know how to handle message: "), new Exception());
            }
        }
    }

    protected abstract class zzc<TListener> {
        private TListener zzct;
        private boolean zzcu = false;

        public zzc(TListener tlistener) {
            this.zzct = tlistener;
        }

        public final void removeListener() {
            synchronized (this) {
                this.zzct = null;
            }
        }

        public final void unregister() {
            removeListener();
            synchronized (BaseGmsClient.this.zzcg) {
                BaseGmsClient.this.zzcg.remove(this);
            }
        }

        protected abstract void zza(TListener tlistener);

        protected abstract void zzn();

        public final void zzo() {
            TListener tlistener;
            synchronized (this) {
                try {
                    tlistener = this.zzct;
                    if (this.zzcu) {
                        String strValueOf = String.valueOf(this);
                        StringBuilder sb = new StringBuilder(strValueOf.length() + 47);
                        sb.append("Callback proxy ");
                        sb.append(strValueOf);
                        sb.append(" being reused. This is not safe.");
                        Log.w("GmsClient", sb.toString());
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            if (tlistener != null) {
                try {
                    zza(tlistener);
                } catch (RuntimeException e2) {
                    zzn();
                    throw e2;
                }
            } else {
                zzn();
            }
            synchronized (this) {
                this.zzcu = true;
            }
            unregister();
        }
    }

    @VisibleForTesting
    public static final class zzd extends IGmsCallbacks.zza {
        private BaseGmsClient zzcv;
        private final int zzcw;

        public zzd(BaseGmsClient baseGmsClient, int i2) {
            this.zzcv = baseGmsClient;
            this.zzcw = i2;
        }

        @Override // com.google.android.gms.common.internal.IGmsCallbacks
        public final void onPostInitComplete(int i2, IBinder iBinder, Bundle bundle) {
            Preconditions.checkNotNull(this.zzcv, "onPostInitComplete can be called only once per call to getRemoteService");
            this.zzcv.onPostInitHandler(i2, iBinder, bundle, this.zzcw);
            this.zzcv = null;
        }

        @Override // com.google.android.gms.common.internal.IGmsCallbacks
        public final void zza(int i2, Bundle bundle) {
            Log.wtf("GmsClient", "received deprecated onAccountValidationComplete callback, ignoring", new Exception());
        }

        @Override // com.google.android.gms.common.internal.IGmsCallbacks
        public final void zza(int i2, IBinder iBinder, com.google.android.gms.common.internal.zzb zzbVar) {
            Preconditions.checkNotNull(this.zzcv, "onPostInitCompleteWithConnectionInfo can be called only once per call togetRemoteService");
            Preconditions.checkNotNull(zzbVar);
            this.zzcv.zza(zzbVar);
            onPostInitComplete(i2, iBinder, zzbVar.zzcz);
        }
    }

    @VisibleForTesting
    public final class zze implements ServiceConnection {
        private final int zzcw;

        public zze(int i2) {
            this.zzcw = i2;
        }

        @Override // android.content.ServiceConnection
        public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            if (iBinder == null) {
                BaseGmsClient.this.zzb(16);
                return;
            }
            synchronized (BaseGmsClient.this.zzcc) {
                try {
                    BaseGmsClient baseGmsClient = BaseGmsClient.this;
                    IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                    baseGmsClient.zzcd = (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof IGmsServiceBroker)) ? new IGmsServiceBroker.Stub.zza(iBinder) : (IGmsServiceBroker) iInterfaceQueryLocalInterface;
                } catch (Throwable th) {
                    throw th;
                }
            }
            BaseGmsClient.this.zza(0, (Bundle) null, this.zzcw);
        }

        @Override // android.content.ServiceConnection
        public final void onServiceDisconnected(ComponentName componentName) {
            synchronized (BaseGmsClient.this.zzcc) {
                BaseGmsClient.this.zzcd = null;
            }
            Handler handler = BaseGmsClient.this.mHandler;
            handler.sendMessage(handler.obtainMessage(6, this.zzcw, 1));
        }
    }

    protected final class zzf extends zza {
        private final IBinder zzcx;

        public zzf(int i2, IBinder iBinder, Bundle bundle) {
            super(i2, bundle);
            this.zzcx = iBinder;
        }

        @Override // com.google.android.gms.common.internal.BaseGmsClient.zza
        protected final void zza(ConnectionResult connectionResult) {
            if (BaseGmsClient.this.zzck != null) {
                BaseGmsClient.this.zzck.onConnectionFailed(connectionResult);
            }
            BaseGmsClient.this.onConnectionFailed(connectionResult);
        }

        @Override // com.google.android.gms.common.internal.BaseGmsClient.zza
        protected final boolean zzm() {
            try {
                String interfaceDescriptor = this.zzcx.getInterfaceDescriptor();
                if (!BaseGmsClient.this.getServiceDescriptor().equals(interfaceDescriptor)) {
                    String serviceDescriptor = BaseGmsClient.this.getServiceDescriptor();
                    StringBuilder sb = new StringBuilder(a.e(a.e(34, serviceDescriptor), interfaceDescriptor));
                    sb.append("service descriptor mismatch: ");
                    sb.append(serviceDescriptor);
                    sb.append(" vs. ");
                    sb.append(interfaceDescriptor);
                    Log.e("GmsClient", sb.toString());
                    return false;
                }
                IInterface iInterfaceCreateServiceInterface = BaseGmsClient.this.createServiceInterface(this.zzcx);
                if (iInterfaceCreateServiceInterface == null || !(BaseGmsClient.this.zza(2, 4, iInterfaceCreateServiceInterface) || BaseGmsClient.this.zza(3, 4, iInterfaceCreateServiceInterface))) {
                    return false;
                }
                BaseGmsClient.this.zzcn = null;
                Bundle connectionHint = BaseGmsClient.this.getConnectionHint();
                if (BaseGmsClient.this.zzcj == null) {
                    return true;
                }
                BaseGmsClient.this.zzcj.onConnected(connectionHint);
                return true;
            } catch (RemoteException unused) {
                Log.w("GmsClient", "service probably died");
                return false;
            }
        }
    }

    protected final class zzg extends zza {
        public zzg(int i2, Bundle bundle) {
            super(i2, null);
        }

        @Override // com.google.android.gms.common.internal.BaseGmsClient.zza
        protected final void zza(ConnectionResult connectionResult) {
            BaseGmsClient.this.zzce.onReportServiceBinding(connectionResult);
            BaseGmsClient.this.onConnectionFailed(connectionResult);
        }

        @Override // com.google.android.gms.common.internal.BaseGmsClient.zza
        protected final boolean zzm() {
            BaseGmsClient.this.zzce.onReportServiceBinding(ConnectionResult.RESULT_SUCCESS);
            return true;
        }
    }

    @KeepForSdk
    protected BaseGmsClient(Context context, Looper looper, int i2, BaseConnectionCallbacks baseConnectionCallbacks, BaseOnConnectionFailedListener baseOnConnectionFailedListener, String str) {
        this(context, looper, GmsClientSupervisor.getInstance(context), GoogleApiAvailabilityLight.getInstance(), i2, (BaseConnectionCallbacks) Preconditions.checkNotNull(baseConnectionCallbacks), (BaseOnConnectionFailedListener) Preconditions.checkNotNull(baseOnConnectionFailedListener), str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(com.google.android.gms.common.internal.zzb zzbVar) {
        this.zzcp = zzbVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzb(int i2) {
        int i3;
        if (zzk()) {
            this.zzco = true;
            i3 = 5;
        } else {
            i3 = 4;
        }
        Handler handler = this.mHandler;
        handler.sendMessage(handler.obtainMessage(i3, this.zzcq.get(), 16));
    }

    private final String zzj() {
        String str = this.zzcm;
        return str == null ? this.mContext.getClass().getName() : str;
    }

    private final boolean zzk() {
        boolean z2;
        synchronized (this.mLock) {
            z2 = this.zzci == 3;
        }
        return z2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean zzl() {
        if (this.zzco || TextUtils.isEmpty(getServiceDescriptor()) || TextUtils.isEmpty(getLocalStartServiceAction())) {
            return false;
        }
        try {
            Class.forName(getServiceDescriptor());
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    @KeepForSdk
    public void checkAvailabilityAndConnect() {
        int iIsGooglePlayServicesAvailable = this.zzcb.isGooglePlayServicesAvailable(this.mContext, getMinApkVersion());
        if (iIsGooglePlayServicesAvailable == 0) {
            connect(new LegacyClientCallbackAdapter());
        } else {
            zza(1, (IInterface) null);
            triggerNotAvailable(new LegacyClientCallbackAdapter(), iIsGooglePlayServicesAvailable, null);
        }
    }

    @KeepForSdk
    protected final void checkConnected() {
        if (!isConnected()) {
            throw new IllegalStateException("Not connected. Call connect() and wait for onConnected() to be called.");
        }
    }

    @KeepForSdk
    public void connect(ConnectionProgressReportCallbacks connectionProgressReportCallbacks) {
        this.zzce = (ConnectionProgressReportCallbacks) Preconditions.checkNotNull(connectionProgressReportCallbacks, "Connection progress callbacks cannot be null.");
        zza(2, (IInterface) null);
    }

    @KeepForSdk
    protected abstract T createServiceInterface(IBinder iBinder);

    @KeepForSdk
    public void disconnect() {
        this.zzcq.incrementAndGet();
        synchronized (this.zzcg) {
            try {
                int size = this.zzcg.size();
                for (int i2 = 0; i2 < size; i2++) {
                    this.zzcg.get(i2).removeListener();
                }
                this.zzcg.clear();
            } catch (Throwable th) {
                throw th;
            }
        }
        synchronized (this.zzcc) {
            this.zzcd = null;
        }
        zza(1, (IInterface) null);
    }

    @KeepForSdk
    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        int i2;
        T t2;
        IGmsServiceBroker iGmsServiceBroker;
        synchronized (this.mLock) {
            i2 = this.zzci;
            t2 = this.zzcf;
        }
        synchronized (this.zzcc) {
            iGmsServiceBroker = this.zzcd;
        }
        printWriter.append((CharSequence) str).append("mConnectState=");
        if (i2 == 1) {
            printWriter.print("DISCONNECTED");
        } else if (i2 == 2) {
            printWriter.print("REMOTE_CONNECTING");
        } else if (i2 == 3) {
            printWriter.print("LOCAL_CONNECTING");
        } else if (i2 == 4) {
            printWriter.print("CONNECTED");
        } else if (i2 != 5) {
            printWriter.print("UNKNOWN");
        } else {
            printWriter.print("DISCONNECTING");
        }
        printWriter.append(" mService=");
        if (t2 == null) {
            printWriter.append("null");
        } else {
            printWriter.append((CharSequence) getServiceDescriptor()).append("@").append((CharSequence) Integer.toHexString(System.identityHashCode(t2.asBinder())));
        }
        printWriter.append(" mServiceBroker=");
        if (iGmsServiceBroker == null) {
            printWriter.println("null");
        } else {
            printWriter.append("IGmsServiceBroker@").println(Integer.toHexString(System.identityHashCode(iGmsServiceBroker.asBinder())));
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.US);
        if (this.zzbv > 0) {
            PrintWriter printWriterAppend = printWriter.append((CharSequence) str).append("lastConnectedTime=");
            long j2 = this.zzbv;
            String str2 = simpleDateFormat.format(new Date(this.zzbv));
            StringBuilder sb = new StringBuilder(a.e(21, str2));
            sb.append(j2);
            sb.append(" ");
            sb.append(str2);
            printWriterAppend.println(sb.toString());
        }
        if (this.zzbu > 0) {
            printWriter.append((CharSequence) str).append("lastSuspendedCause=");
            int i3 = this.zzbt;
            if (i3 == 1) {
                printWriter.append("CAUSE_SERVICE_DISCONNECTED");
            } else if (i3 != 2) {
                printWriter.append((CharSequence) String.valueOf(i3));
            } else {
                printWriter.append("CAUSE_NETWORK_LOST");
            }
            PrintWriter printWriterAppend2 = printWriter.append(" lastSuspendedTime=");
            long j3 = this.zzbu;
            String str3 = simpleDateFormat.format(new Date(this.zzbu));
            StringBuilder sb2 = new StringBuilder(a.e(21, str3));
            sb2.append(j3);
            sb2.append(" ");
            sb2.append(str3);
            printWriterAppend2.println(sb2.toString());
        }
        if (this.zzbx > 0) {
            printWriter.append((CharSequence) str).append("lastFailedStatus=").append((CharSequence) CommonStatusCodes.getStatusCodeString(this.zzbw));
            PrintWriter printWriterAppend3 = printWriter.append(" lastFailedTime=");
            long j4 = this.zzbx;
            String str4 = simpleDateFormat.format(new Date(this.zzbx));
            StringBuilder sb3 = new StringBuilder(a.e(21, str4));
            sb3.append(j4);
            sb3.append(" ");
            sb3.append(str4);
            printWriterAppend3.println(sb3.toString());
        }
    }

    @KeepForSdk
    public Account getAccount() {
        return null;
    }

    @KeepForSdk
    public Feature[] getApiFeatures() {
        return zzbs;
    }

    @KeepForSdk
    public final Feature[] getAvailableFeatures() {
        com.google.android.gms.common.internal.zzb zzbVar = this.zzcp;
        if (zzbVar == null) {
            return null;
        }
        return zzbVar.zzda;
    }

    @KeepForSdk
    public Bundle getConnectionHint() {
        return null;
    }

    @KeepForSdk
    public final Context getContext() {
        return this.mContext;
    }

    @KeepForSdk
    public String getEndpointPackageName() {
        zzh zzhVar;
        if (!isConnected() || (zzhVar = this.zzby) == null) {
            throw new RuntimeException("Failed to connect when checking package");
        }
        return zzhVar.getPackageName();
    }

    @KeepForSdk
    protected Bundle getGetServiceRequestExtraArgs() {
        return new Bundle();
    }

    @KeepForSdk
    protected String getLocalStartServiceAction() {
        return null;
    }

    @KeepForSdk
    public final Looper getLooper() {
        return this.zzbz;
    }

    @KeepForSdk
    public int getMinApkVersion() {
        return GoogleApiAvailabilityLight.GOOGLE_PLAY_SERVICES_VERSION_CODE;
    }

    @KeepForSdk
    public void getRemoteService(IAccountAccessor iAccountAccessor, Set<Scope> set) {
        Bundle getServiceRequestExtraArgs = getGetServiceRequestExtraArgs();
        GetServiceRequest getServiceRequest = new GetServiceRequest(this.zzcl);
        getServiceRequest.zzdh = this.mContext.getPackageName();
        getServiceRequest.zzdk = getServiceRequestExtraArgs;
        if (set != null) {
            getServiceRequest.zzdj = (Scope[]) set.toArray(new Scope[set.size()]);
        }
        if (requiresSignIn()) {
            getServiceRequest.zzdl = getAccount() != null ? getAccount() : new Account("<<default account>>", "com.google");
            if (iAccountAccessor != null) {
                getServiceRequest.zzdi = iAccountAccessor.asBinder();
            }
        } else if (requiresAccount()) {
            getServiceRequest.zzdl = getAccount();
        }
        getServiceRequest.zzdm = zzbs;
        getServiceRequest.zzdn = getApiFeatures();
        try {
            synchronized (this.zzcc) {
                try {
                    IGmsServiceBroker iGmsServiceBroker = this.zzcd;
                    if (iGmsServiceBroker != null) {
                        iGmsServiceBroker.getService(new zzd(this, this.zzcq.get()), getServiceRequest);
                    } else {
                        Log.w("GmsClient", "mServiceBroker is null, client disconnected");
                    }
                } finally {
                }
            }
        } catch (DeadObjectException e2) {
            Log.w("GmsClient", "IGmsServiceBroker.getService failed", e2);
            triggerConnectionSuspended(1);
        } catch (RemoteException e3) {
            e = e3;
            Log.w("GmsClient", "IGmsServiceBroker.getService failed", e);
            onPostInitHandler(8, null, null, this.zzcq.get());
        } catch (SecurityException e4) {
            throw e4;
        } catch (RuntimeException e5) {
            e = e5;
            Log.w("GmsClient", "IGmsServiceBroker.getService failed", e);
            onPostInitHandler(8, null, null, this.zzcq.get());
        }
    }

    @KeepForSdk
    protected Set<Scope> getScopes() {
        return Collections.EMPTY_SET;
    }

    @KeepForSdk
    public final T getService() {
        T t2;
        synchronized (this.mLock) {
            try {
                if (this.zzci == 5) {
                    throw new DeadObjectException();
                }
                checkConnected();
                Preconditions.checkState(this.zzcf != null, "Client is connected but service is null");
                t2 = this.zzcf;
            } catch (Throwable th) {
                throw th;
            }
        }
        return t2;
    }

    @KeepForSdk
    public IBinder getServiceBrokerBinder() {
        synchronized (this.zzcc) {
            try {
                IGmsServiceBroker iGmsServiceBroker = this.zzcd;
                if (iGmsServiceBroker == null) {
                    return null;
                }
                return iGmsServiceBroker.asBinder();
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @KeepForSdk
    protected abstract String getServiceDescriptor();

    @KeepForSdk
    public Intent getSignInIntent() {
        throw new UnsupportedOperationException("Not a sign in API");
    }

    @KeepForSdk
    protected abstract String getStartServiceAction();

    @KeepForSdk
    protected String getStartServicePackage() {
        return "com.google.android.gms";
    }

    @KeepForSdk
    public boolean isConnected() {
        boolean z2;
        synchronized (this.mLock) {
            z2 = this.zzci == 4;
        }
        return z2;
    }

    @KeepForSdk
    public boolean isConnecting() {
        boolean z2;
        synchronized (this.mLock) {
            int i2 = this.zzci;
            z2 = i2 == 2 || i2 == 3;
        }
        return z2;
    }

    @KeepForSdk
    protected void onConnectedLocked(T t2) {
        this.zzbv = System.currentTimeMillis();
    }

    @KeepForSdk
    protected void onConnectionFailed(ConnectionResult connectionResult) {
        this.zzbw = connectionResult.getErrorCode();
        this.zzbx = System.currentTimeMillis();
    }

    @KeepForSdk
    protected void onConnectionSuspended(int i2) {
        this.zzbt = i2;
        this.zzbu = System.currentTimeMillis();
    }

    @KeepForSdk
    protected void onPostInitHandler(int i2, IBinder iBinder, Bundle bundle, int i3) {
        Handler handler = this.mHandler;
        handler.sendMessage(handler.obtainMessage(1, i3, -1, new zzf(i2, iBinder, bundle)));
    }

    @KeepForSdk
    void onSetConnectState(int i2, T t2) {
    }

    @KeepForSdk
    public void onUserSignOut(SignOutCallbacks signOutCallbacks) {
        signOutCallbacks.onSignOutComplete();
    }

    @KeepForSdk
    public boolean providesSignIn() {
        return false;
    }

    @KeepForSdk
    public boolean requiresAccount() {
        return false;
    }

    @KeepForSdk
    public boolean requiresGooglePlayServices() {
        return true;
    }

    @KeepForSdk
    public boolean requiresSignIn() {
        return false;
    }

    @KeepForSdk
    public void triggerConnectionSuspended(int i2) {
        Handler handler = this.mHandler;
        handler.sendMessage(handler.obtainMessage(6, this.zzcq.get(), i2));
    }

    @VisibleForTesting
    @KeepForSdk
    protected void triggerNotAvailable(ConnectionProgressReportCallbacks connectionProgressReportCallbacks, int i2, PendingIntent pendingIntent) {
        this.zzce = (ConnectionProgressReportCallbacks) Preconditions.checkNotNull(connectionProgressReportCallbacks, "Connection progress callbacks cannot be null.");
        Handler handler = this.mHandler;
        handler.sendMessage(handler.obtainMessage(3, this.zzcq.get(), i2, pendingIntent));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(int i2, T t2) {
        zzh zzhVar;
        Preconditions.checkArgument((i2 == 4) == (t2 != null));
        synchronized (this.mLock) {
            try {
                this.zzci = i2;
                this.zzcf = t2;
                onSetConnectState(i2, t2);
                if (i2 != 1) {
                    if (i2 == 2 || i2 == 3) {
                        if (this.zzch != null && (zzhVar = this.zzby) != null) {
                            String strZzt = zzhVar.zzt();
                            String packageName = this.zzby.getPackageName();
                            StringBuilder sb = new StringBuilder(String.valueOf(strZzt).length() + 70 + String.valueOf(packageName).length());
                            sb.append("Calling connect() while still connected, missing disconnect() for ");
                            sb.append(strZzt);
                            sb.append(" on ");
                            sb.append(packageName);
                            Log.e("GmsClient", sb.toString());
                            this.zzca.zza(this.zzby.zzt(), this.zzby.getPackageName(), this.zzby.zzq(), this.zzch, zzj());
                            this.zzcq.incrementAndGet();
                        }
                        this.zzch = new zze(this.zzcq.get());
                        zzh zzhVar2 = (this.zzci != 3 || getLocalStartServiceAction() == null) ? new zzh(getStartServicePackage(), getStartServiceAction(), false, 129) : new zzh(getContext().getPackageName(), getLocalStartServiceAction(), true, 129);
                        this.zzby = zzhVar2;
                        if (!this.zzca.zza(new GmsClientSupervisor.zza(zzhVar2.zzt(), this.zzby.getPackageName(), this.zzby.zzq()), this.zzch, zzj())) {
                            String strZzt2 = this.zzby.zzt();
                            String packageName2 = this.zzby.getPackageName();
                            StringBuilder sb2 = new StringBuilder(String.valueOf(strZzt2).length() + 34 + String.valueOf(packageName2).length());
                            sb2.append("unable to connect to service: ");
                            sb2.append(strZzt2);
                            sb2.append(" on ");
                            sb2.append(packageName2);
                            Log.e("GmsClient", sb2.toString());
                            zza(16, (Bundle) null, this.zzcq.get());
                        }
                    } else if (i2 == 4) {
                        onConnectedLocked(t2);
                    }
                } else if (this.zzch != null) {
                    this.zzca.zza(getStartServiceAction(), getStartServicePackage(), 129, this.zzch, zzj());
                    this.zzch = null;
                }
            } finally {
            }
        }
    }

    @VisibleForTesting
    @KeepForSdk
    protected BaseGmsClient(Context context, Looper looper, GmsClientSupervisor gmsClientSupervisor, GoogleApiAvailabilityLight googleApiAvailabilityLight, int i2, BaseConnectionCallbacks baseConnectionCallbacks, BaseOnConnectionFailedListener baseOnConnectionFailedListener, String str) {
        this.mLock = new Object();
        this.zzcc = new Object();
        this.zzcg = new ArrayList<>();
        this.zzci = 1;
        this.zzcn = null;
        this.zzco = false;
        this.zzcp = null;
        this.zzcq = new AtomicInteger(0);
        this.mContext = (Context) Preconditions.checkNotNull(context, "Context must not be null");
        this.zzbz = (Looper) Preconditions.checkNotNull(looper, "Looper must not be null");
        this.zzca = (GmsClientSupervisor) Preconditions.checkNotNull(gmsClientSupervisor, "Supervisor must not be null");
        this.zzcb = (GoogleApiAvailabilityLight) Preconditions.checkNotNull(googleApiAvailabilityLight, "API availability must not be null");
        this.mHandler = new zzb(looper);
        this.zzcl = i2;
        this.zzcj = baseConnectionCallbacks;
        this.zzck = baseOnConnectionFailedListener;
        this.zzcm = str;
    }

    @VisibleForTesting
    @KeepForSdk
    protected BaseGmsClient(Context context, Handler handler, GmsClientSupervisor gmsClientSupervisor, GoogleApiAvailabilityLight googleApiAvailabilityLight, int i2, BaseConnectionCallbacks baseConnectionCallbacks, BaseOnConnectionFailedListener baseOnConnectionFailedListener) {
        this.mLock = new Object();
        this.zzcc = new Object();
        this.zzcg = new ArrayList<>();
        this.zzci = 1;
        this.zzcn = null;
        this.zzco = false;
        this.zzcp = null;
        this.zzcq = new AtomicInteger(0);
        this.mContext = (Context) Preconditions.checkNotNull(context, "Context must not be null");
        this.mHandler = (Handler) Preconditions.checkNotNull(handler, "Handler must not be null");
        this.zzbz = handler.getLooper();
        this.zzca = (GmsClientSupervisor) Preconditions.checkNotNull(gmsClientSupervisor, "Supervisor must not be null");
        this.zzcb = (GoogleApiAvailabilityLight) Preconditions.checkNotNull(googleApiAvailabilityLight, "API availability must not be null");
        this.zzcl = i2;
        this.zzcj = baseConnectionCallbacks;
        this.zzck = baseOnConnectionFailedListener;
        this.zzcm = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean zza(int i2, int i3, T t2) {
        synchronized (this.mLock) {
            try {
                if (this.zzci != i2) {
                    return false;
                }
                zza(i3, t2);
                return true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    protected final void zza(int i2, Bundle bundle, int i3) {
        Handler handler = this.mHandler;
        handler.sendMessage(handler.obtainMessage(7, i3, -1, new zzg(i2, null)));
    }
}
