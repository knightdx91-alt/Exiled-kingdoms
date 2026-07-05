package com.google.android.gms.internal.drive;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.android.gms.drive.Drive;
import com.google.android.gms.drive.DriveContents;
import com.google.android.gms.drive.DriveFile;
import com.google.android.gms.drive.DriveFolder;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.DriveResource;
import com.google.android.gms.drive.DriveResourceClient;
import com.google.android.gms.drive.ExecutionOptions;
import com.google.android.gms.drive.Metadata;
import com.google.android.gms.drive.MetadataBuffer;
import com.google.android.gms.drive.MetadataChangeSet;
import com.google.android.gms.drive.events.ListenerToken;
import com.google.android.gms.drive.events.OnChangeListener;
import com.google.android.gms.drive.events.OpenFileCallback;
import com.google.android.gms.drive.query.Query;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzch extends DriveResourceClient {
    private static final AtomicInteger zzfl = new AtomicInteger();

    public zzch(Activity activity, Drive.zza zzaVar) {
        super(activity, zzaVar);
    }

    static final /* synthetic */ ListenerToken zza(ListenerHolder listenerHolder, Task task) throws Exception {
        if (task.isSuccessful()) {
            return new zzg(listenerHolder.getListenerKey());
        }
        throw task.getException();
    }

    private static void zze(int i2) {
        if (i2 != 268435456 && i2 != 536870912 && i2 != 805306368) {
            throw new IllegalArgumentException("Invalid openMode provided");
        }
    }

    @Override // com.google.android.gms.drive.DriveResourceClient
    public final Task<ListenerToken> addChangeListener(DriveResource driveResource, OnChangeListener onChangeListener) {
        Preconditions.checkNotNull(driveResource.getDriveId());
        Preconditions.checkNotNull(onChangeListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        zzdi zzdiVar = new zzdi(this, onChangeListener, driveResource.getDriveId());
        int iIncrementAndGet = zzfl.incrementAndGet();
        StringBuilder sb = new StringBuilder(27);
        sb.append("OnChangeListener");
        sb.append(iIncrementAndGet);
        final ListenerHolder<L> listenerHolderRegisterListener = registerListener(zzdiVar, sb.toString());
        return doRegisterEventListener(new zzcp(this, listenerHolderRegisterListener, driveResource, zzdiVar), new zzcq(this, listenerHolderRegisterListener.getListenerKey(), driveResource, zzdiVar)).continueWith(new Continuation(listenerHolderRegisterListener) { // from class: com.google.android.gms.internal.drive.zzci
            private final ListenerHolder zzfm;

            {
                this.zzfm = listenerHolderRegisterListener;
            }

            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task) {
                return zzch.zza(this.zzfm, task);
            }
        });
    }

    @Override // com.google.android.gms.drive.DriveResourceClient
    public final Task<Void> addChangeSubscription(DriveResource driveResource) {
        Preconditions.checkNotNull(driveResource.getDriveId());
        Preconditions.checkArgument(com.google.android.gms.drive.events.zzj.zza(1, driveResource.getDriveId()));
        return doWrite(new zzcr(this, driveResource));
    }

    @Override // com.google.android.gms.drive.DriveResourceClient
    public final Task<Boolean> cancelOpenFileCallback(ListenerToken listenerToken) {
        if (listenerToken instanceof zzg) {
            return doUnregisterEventListener(((zzg) listenerToken).zzac());
        }
        throw new IllegalArgumentException("Unrecognized ListenerToken");
    }

    @Override // com.google.android.gms.drive.DriveResourceClient
    public final Task<Void> commitContents(DriveContents driveContents, MetadataChangeSet metadataChangeSet) {
        return commitContents(driveContents, metadataChangeSet, (com.google.android.gms.drive.zzn) new com.google.android.gms.drive.zzp().build());
    }

    @Override // com.google.android.gms.drive.DriveResourceClient
    public final Task<DriveContents> createContents() {
        Preconditions.checkArgument(true, "Contents can only be created in MODE_WRITE_ONLY or MODE_READ_WRITE.");
        return doWrite(new zzcw(this, DriveFile.MODE_WRITE_ONLY));
    }

    @Override // com.google.android.gms.drive.DriveResourceClient
    public final Task<DriveFile> createFile(DriveFolder driveFolder, MetadataChangeSet metadataChangeSet, DriveContents driveContents) {
        return createFile(driveFolder, metadataChangeSet, driveContents, new ExecutionOptions.Builder().build());
    }

    @Override // com.google.android.gms.drive.DriveResourceClient
    public final Task<DriveFolder> createFolder(DriveFolder driveFolder, MetadataChangeSet metadataChangeSet) {
        Preconditions.checkNotNull(metadataChangeSet, "MetadataChangeSet must be provided.");
        if (metadataChangeSet.getMimeType() == null || metadataChangeSet.getMimeType().equals(DriveFolder.MIME_TYPE)) {
            return doWrite(new zzdb(this, metadataChangeSet, driveFolder));
        }
        throw new IllegalArgumentException("The mimetype must be of type application/vnd.google-apps.folder");
    }

    @Override // com.google.android.gms.drive.DriveResourceClient
    public final Task<Void> delete(DriveResource driveResource) {
        Preconditions.checkNotNull(driveResource.getDriveId());
        return doWrite(new zzcl(this, driveResource));
    }

    @Override // com.google.android.gms.drive.DriveResourceClient
    public final Task<Void> discardContents(DriveContents driveContents) {
        Preconditions.checkArgument(!driveContents.zzj(), "DriveContents is already closed");
        driveContents.zzi();
        return doWrite(new zzda(this, driveContents));
    }

    @Override // com.google.android.gms.drive.DriveResourceClient
    public final Task<DriveFolder> getAppFolder() {
        return doRead(new zzco(this));
    }

    @Override // com.google.android.gms.drive.DriveResourceClient
    public final Task<Metadata> getMetadata(DriveResource driveResource) {
        Preconditions.checkNotNull(driveResource, "DriveResource must not be null");
        Preconditions.checkNotNull(driveResource.getDriveId(), "Resource's DriveId must not be null");
        return doRead(new zzdc(this, driveResource, false));
    }

    @Override // com.google.android.gms.drive.DriveResourceClient
    public final Task<DriveFolder> getRootFolder() {
        return doRead(new zzck(this));
    }

    @Override // com.google.android.gms.drive.DriveResourceClient
    public final Task<MetadataBuffer> listChildren(DriveFolder driveFolder) {
        Preconditions.checkNotNull(driveFolder, "folder cannot be null.");
        return query(zzbs.zza((Query) null, driveFolder.getDriveId()));
    }

    @Override // com.google.android.gms.drive.DriveResourceClient
    public final Task<MetadataBuffer> listParents(DriveResource driveResource) {
        Preconditions.checkNotNull(driveResource.getDriveId());
        return doRead(new zzde(this, driveResource));
    }

    @Override // com.google.android.gms.drive.DriveResourceClient
    public final Task<DriveContents> openFile(DriveFile driveFile, int i2) {
        zze(i2);
        return doRead(new zzct(this, driveFile, i2));
    }

    @Override // com.google.android.gms.drive.DriveResourceClient
    public final Task<MetadataBuffer> query(Query query) {
        Preconditions.checkNotNull(query, "query cannot be null.");
        return doRead(new zzcz(this, query));
    }

    @Override // com.google.android.gms.drive.DriveResourceClient
    public final Task<MetadataBuffer> queryChildren(DriveFolder driveFolder, Query query) {
        Preconditions.checkNotNull(driveFolder, "folder cannot be null.");
        Preconditions.checkNotNull(query, "query cannot be null.");
        return query(zzbs.zza(query, driveFolder.getDriveId()));
    }

    @Override // com.google.android.gms.drive.DriveResourceClient
    public final Task<Boolean> removeChangeListener(ListenerToken listenerToken) {
        Preconditions.checkNotNull(listenerToken, "Token is required to unregister listener.");
        if (listenerToken instanceof zzg) {
            return doUnregisterEventListener(((zzg) listenerToken).zzac());
        }
        throw new IllegalStateException("Could not recover key from ListenerToken");
    }

    @Override // com.google.android.gms.drive.DriveResourceClient
    public final Task<Void> removeChangeSubscription(DriveResource driveResource) {
        Preconditions.checkNotNull(driveResource.getDriveId());
        Preconditions.checkArgument(com.google.android.gms.drive.events.zzj.zza(1, driveResource.getDriveId()));
        return doWrite(new zzcs(this, driveResource));
    }

    @Override // com.google.android.gms.drive.DriveResourceClient
    public final Task<DriveContents> reopenContentsForWrite(DriveContents driveContents) {
        Preconditions.checkArgument(!driveContents.zzj(), "DriveContents is already closed");
        Preconditions.checkArgument(driveContents.getMode() == 268435456, "This method can only be called on contents that are currently opened in MODE_READ_ONLY.");
        driveContents.zzi();
        return doRead(new zzcx(this, driveContents));
    }

    @Override // com.google.android.gms.drive.DriveResourceClient
    public final Task<Void> setParents(DriveResource driveResource, Set<DriveId> set) {
        Preconditions.checkNotNull(driveResource.getDriveId());
        Preconditions.checkNotNull(set);
        return doWrite(new zzdf(this, driveResource, new ArrayList(set)));
    }

    @Override // com.google.android.gms.drive.DriveResourceClient
    public final Task<Void> trash(DriveResource driveResource) {
        Preconditions.checkNotNull(driveResource.getDriveId());
        return doWrite(new zzcm(this, driveResource));
    }

    @Override // com.google.android.gms.drive.DriveResourceClient
    public final Task<Void> untrash(DriveResource driveResource) {
        Preconditions.checkNotNull(driveResource.getDriveId());
        return doWrite(new zzcn(this, driveResource));
    }

    @Override // com.google.android.gms.drive.DriveResourceClient
    public final Task<Metadata> updateMetadata(DriveResource driveResource, MetadataChangeSet metadataChangeSet) {
        Preconditions.checkNotNull(driveResource.getDriveId());
        Preconditions.checkNotNull(metadataChangeSet);
        return doWrite(new zzdd(this, metadataChangeSet, driveResource));
    }

    public zzch(Context context, Drive.zza zzaVar) {
        super(context, zzaVar);
    }

    static final /* synthetic */ ListenerToken zza(zzg zzgVar, Task task) throws Exception {
        if (task.isSuccessful()) {
            return zzgVar;
        }
        throw task.getException();
    }

    @Override // com.google.android.gms.drive.DriveResourceClient
    public final Task<Void> commitContents(DriveContents driveContents, MetadataChangeSet metadataChangeSet, ExecutionOptions executionOptions) {
        Preconditions.checkNotNull(executionOptions, "Execution options cannot be null.");
        Preconditions.checkArgument(!driveContents.zzj(), "DriveContents is already closed");
        Preconditions.checkArgument(driveContents.getMode() != 268435456, "Cannot commit contents opened in MODE_READ_ONLY.");
        Preconditions.checkNotNull(driveContents.getDriveId(), "Only DriveContents obtained through DriveFile.open can be committed.");
        com.google.android.gms.drive.zzn zznVarZza = com.google.android.gms.drive.zzn.zza(executionOptions);
        if (ExecutionOptions.zza(zznVarZza.zzm()) && !driveContents.zzh().zza()) {
            throw new IllegalStateException("DriveContents must be valid for conflict detection.");
        }
        if (metadataChangeSet == null) {
            metadataChangeSet = MetadataChangeSet.zzav;
        }
        return doWrite(new zzcy(this, zznVarZza, driveContents, metadataChangeSet));
    }

    @Override // com.google.android.gms.drive.DriveResourceClient
    public final Task<DriveFile> createFile(DriveFolder driveFolder, MetadataChangeSet metadataChangeSet, DriveContents driveContents, ExecutionOptions executionOptions) {
        zzbs.zzb(metadataChangeSet);
        return doWrite(new zzdh(driveFolder, metadataChangeSet, driveContents, executionOptions, null));
    }

    @Override // com.google.android.gms.drive.DriveResourceClient
    public final Task<ListenerToken> openFile(DriveFile driveFile, int i2, OpenFileCallback openFileCallback) {
        zze(i2);
        int iIncrementAndGet = zzfl.incrementAndGet();
        StringBuilder sb = new StringBuilder(27);
        sb.append("OpenFileCallback");
        sb.append(iIncrementAndGet);
        ListenerHolder<L> listenerHolderRegisterListener = registerListener(openFileCallback, sb.toString());
        ListenerHolder.ListenerKey listenerKey = listenerHolderRegisterListener.getListenerKey();
        final zzg zzgVar = new zzg(listenerKey);
        return doRegisterEventListener(new zzcu(this, listenerHolderRegisterListener, driveFile, i2, zzgVar, listenerHolderRegisterListener), new zzcv(this, listenerKey, zzgVar)).continueWith(new Continuation(zzgVar) { // from class: com.google.android.gms.internal.drive.zzcj
            private final zzg zzfn;

            {
                this.zzfn = zzgVar;
            }

            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task) {
                return zzch.zza(this.zzfn, task);
            }
        });
    }
}
