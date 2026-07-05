package com.google.android.gms.internal.drive;

import a.a;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Process;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.GmsClient;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.android.gms.common.util.UidVerifier;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.events.ChangeListener;
import com.google.android.gms.drive.events.DriveEventService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzaw extends GmsClient<zzeo> {
    private final String zzdz;
    protected final boolean zzea;
    private volatile DriveId zzeb;
    private volatile DriveId zzec;
    private volatile boolean zzed;

    @VisibleForTesting
    private final Map<DriveId, Map<ChangeListener, zzee>> zzee;

    @VisibleForTesting
    private final Map<com.google.android.gms.drive.events.zzd, zzee> zzef;

    @VisibleForTesting
    private final Map<DriveId, Map<com.google.android.gms.drive.events.zzl, zzee>> zzeg;

    @VisibleForTesting
    private final Map<DriveId, Map<com.google.android.gms.drive.events.zzl, zzee>> zzeh;
    private final Bundle zzx;

    public zzaw(Context context, Looper looper, ClientSettings clientSettings, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener, Bundle bundle) {
        super(context, looper, 11, clientSettings, connectionCallbacks, onConnectionFailedListener);
        this.zzed = false;
        this.zzee = new HashMap();
        this.zzef = new HashMap();
        this.zzeg = new HashMap();
        this.zzeh = new HashMap();
        this.zzdz = clientSettings.getRealClientPackageName();
        this.zzx = bundle;
        Intent intent = new Intent(DriveEventService.ACTION_HANDLE_EVENT);
        intent.setPackage(context.getPackageName());
        List<ResolveInfo> listQueryIntentServices = context.getPackageManager().queryIntentServices(intent, 0);
        int size = listQueryIntentServices.size();
        if (size == 0) {
            this.zzea = false;
            return;
        }
        if (size != 1) {
            String action = intent.getAction();
            StringBuilder sb = new StringBuilder(a.e(72, action));
            sb.append("AndroidManifest.xml can only define one service that handles the ");
            sb.append(action);
            sb.append(" action");
            throw new IllegalStateException(sb.toString());
        }
        ServiceInfo serviceInfo = listQueryIntentServices.get(0).serviceInfo;
        if (serviceInfo.exported) {
            this.zzea = true;
            return;
        }
        String str = serviceInfo.name;
        StringBuilder sb2 = new StringBuilder(a.e(60, str));
        sb2.append("Drive event service ");
        sb2.append(str);
        sb2.append(" must be exported in AndroidManifest.xml");
        throw new IllegalStateException(sb2.toString());
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    protected final /* synthetic */ IInterface createServiceInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.drive.internal.IDriveService");
        return iInterfaceQueryLocalInterface instanceof zzeo ? (zzeo) iInterfaceQueryLocalInterface : new zzep(iBinder);
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient, com.google.android.gms.common.api.Api.Client
    public final void disconnect() {
        if (isConnected()) {
            try {
                ((zzeo) getService()).zza(new zzad());
            } catch (RemoteException unused) {
            }
        }
        super.disconnect();
        synchronized (this.zzee) {
            this.zzee.clear();
        }
        synchronized (this.zzef) {
            this.zzef.clear();
        }
        synchronized (this.zzeg) {
            this.zzeg.clear();
        }
        synchronized (this.zzeh) {
            this.zzeh.clear();
        }
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    protected final Bundle getGetServiceRequestExtraArgs() {
        String packageName = getContext().getPackageName();
        Preconditions.checkNotNull(packageName);
        Preconditions.checkState(!getClientSettings().getAllRequestedScopes().isEmpty());
        Bundle bundle = new Bundle();
        if (!packageName.equals(this.zzdz)) {
            bundle.putString("proxy_package_name", this.zzdz);
        }
        bundle.putAll(this.zzx);
        return bundle;
    }

    @Override // com.google.android.gms.common.internal.GmsClient, com.google.android.gms.common.internal.BaseGmsClient, com.google.android.gms.common.api.Api.Client
    public final int getMinApkVersion() {
        return 12451000;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    protected final String getServiceDescriptor() {
        return "com.google.android.gms.drive.internal.IDriveService";
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    protected final String getStartServiceAction() {
        return "com.google.android.gms.drive.ApiService.START";
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    protected final void onPostInitHandler(int i2, IBinder iBinder, Bundle bundle, int i3) {
        if (bundle != null) {
            bundle.setClassLoader(zzaw.class.getClassLoader());
            this.zzeb = (DriveId) bundle.getParcelable("com.google.android.gms.drive.root_id");
            this.zzec = (DriveId) bundle.getParcelable("com.google.android.gms.drive.appdata_id");
            this.zzed = true;
        }
        super.onPostInitHandler(i2, iBinder, bundle, i3);
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient, com.google.android.gms.common.api.Api.Client
    public final boolean requiresAccount() {
        return true;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient, com.google.android.gms.common.api.Api.Client
    public final boolean requiresSignIn() {
        return (getContext().getPackageName().equals(this.zzdz) && UidVerifier.isGooglePlayServicesUid(getContext(), Process.myUid())) ? false : true;
    }

    final PendingResult<Status> zza(GoogleApiClient googleApiClient, DriveId driveId, ChangeListener changeListener) {
        Preconditions.checkArgument(com.google.android.gms.drive.events.zzj.zza(1, driveId));
        Preconditions.checkNotNull(changeListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        Preconditions.checkState(isConnected(), "Client must be connected");
        synchronized (this.zzee) {
            try {
                Map<ChangeListener, zzee> map = this.zzee.get(driveId);
                if (map == null) {
                    map = new HashMap<>();
                    this.zzee.put(driveId, map);
                }
                zzee zzeeVar = map.get(changeListener);
                if (zzeeVar == null) {
                    zzeeVar = new zzee(getLooper(), getContext(), 1, changeListener);
                    map.put(changeListener, zzeeVar);
                } else if (zzeeVar.zzg(1)) {
                    return new zzat(googleApiClient, Status.RESULT_SUCCESS);
                }
                zzeeVar.zzf(1);
                return googleApiClient.execute(new zzax(this, googleApiClient, new zzj(1, driveId), zzeeVar));
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final DriveId zzad() {
        return this.zzeb;
    }

    public final DriveId zzae() {
        return this.zzec;
    }

    public final boolean zzaf() {
        return this.zzed;
    }

    public final boolean zzag() {
        return this.zzea;
    }

    final PendingResult<Status> zzb(GoogleApiClient googleApiClient, DriveId driveId, ChangeListener changeListener) {
        Preconditions.checkArgument(com.google.android.gms.drive.events.zzj.zza(1, driveId));
        Preconditions.checkState(isConnected(), "Client must be connected");
        Preconditions.checkNotNull(changeListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        synchronized (this.zzee) {
            try {
                Map<ChangeListener, zzee> map = this.zzee.get(driveId);
                if (map == null) {
                    return new zzat(googleApiClient, Status.RESULT_SUCCESS);
                }
                zzee zzeeVarRemove = map.remove(changeListener);
                if (zzeeVarRemove == null) {
                    return new zzat(googleApiClient, Status.RESULT_SUCCESS);
                }
                if (map.isEmpty()) {
                    this.zzee.remove(driveId);
                }
                return googleApiClient.execute(new zzay(this, googleApiClient, new zzgm(driveId, 1), zzeeVarRemove));
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
