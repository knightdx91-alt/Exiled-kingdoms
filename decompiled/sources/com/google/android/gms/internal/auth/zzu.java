package com.google.android.gms.internal.auth;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.GmsClient;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzu extends GmsClient<zzz> {
    private final Bundle zzbv;

    public zzu(Context context, Looper looper, ClientSettings clientSettings, com.google.android.gms.auth.api.accounttransfer.zzn zznVar, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, VertexAttributes.Usage.Tangent, clientSettings, connectionCallbacks, onConnectionFailedListener);
        if (zznVar != null) {
            throw new NoSuchMethodError();
        }
        this.zzbv = new Bundle();
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    protected final /* synthetic */ IInterface createServiceInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.auth.api.accounttransfer.internal.IAccountTransferService");
        return iInterfaceQueryLocalInterface instanceof zzz ? (zzz) iInterfaceQueryLocalInterface : new zzaa(iBinder);
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    protected final Bundle getGetServiceRequestExtraArgs() {
        return this.zzbv;
    }

    @Override // com.google.android.gms.common.internal.GmsClient, com.google.android.gms.common.internal.BaseGmsClient, com.google.android.gms.common.api.Api.Client
    public final int getMinApkVersion() {
        return 12451000;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    protected final String getServiceDescriptor() {
        return "com.google.android.gms.auth.api.accounttransfer.internal.IAccountTransferService";
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    protected final String getStartServiceAction() {
        return "com.google.android.gms.auth.api.accounttransfer.service.START";
    }
}
