package com.google.android.gms.internal.drive;

import android.content.IntentSender;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.drive.Drive;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.MetadataChangeSet;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@Deprecated
public final class zzt {
    private String zzay;
    private DriveId zzbb;
    private Integer zzdi;
    private final int zzdj = 0;
    private MetadataChangeSet zzdk;

    public zzt(int i2) {
    }

    public final IntentSender build(GoogleApiClient googleApiClient) {
        Preconditions.checkState(googleApiClient.isConnected(), "Client must be connected");
        zzf();
        zzaw zzawVar = (zzaw) googleApiClient.getClient(Drive.CLIENT_KEY);
        this.zzdk.zzp().zza(zzawVar.getContext());
        try {
            return ((zzeo) zzawVar.getService()).zza(new zzu(this.zzdk.zzp(), this.zzdi.intValue(), this.zzay, this.zzbb, 0));
        } catch (RemoteException e2) {
            throw new RuntimeException("Unable to connect Drive Play Service", e2);
        }
    }

    public final int getRequestId() {
        return this.zzdi.intValue();
    }

    public final void zza(DriveId driveId) {
        this.zzbb = (DriveId) Preconditions.checkNotNull(driveId);
    }

    public final MetadataChangeSet zzb() {
        return this.zzdk;
    }

    public final DriveId zzc() {
        return this.zzbb;
    }

    public final String zzd() {
        return this.zzay;
    }

    public final void zzf() {
        Preconditions.checkNotNull(this.zzdk, "Must provide initial metadata via setInitialMetadata.");
        Integer num = this.zzdi;
        this.zzdi = Integer.valueOf(num == null ? 0 : num.intValue());
    }

    public final void zza(MetadataChangeSet metadataChangeSet) {
        this.zzdk = (MetadataChangeSet) Preconditions.checkNotNull(metadataChangeSet);
    }

    public final void zzc(String str) {
        this.zzay = (String) Preconditions.checkNotNull(str);
    }

    public final void zzd(int i2) {
        this.zzdi = Integer.valueOf(i2);
    }
}
