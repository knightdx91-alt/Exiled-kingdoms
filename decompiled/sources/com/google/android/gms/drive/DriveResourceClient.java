package com.google.android.gms.drive;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.drive.Drive;
import com.google.android.gms.drive.events.ListenerToken;
import com.google.android.gms.drive.events.OnChangeListener;
import com.google.android.gms.drive.events.OpenFileCallback;
import com.google.android.gms.drive.query.Query;
import com.google.android.gms.tasks.Task;
import java.util.Set;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public abstract class DriveResourceClient extends GoogleApi<Drive.zza> {
    public DriveResourceClient(Activity activity, Drive.zza zzaVar) {
        super(activity, Drive.zzu, zzaVar, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    public abstract Task<ListenerToken> addChangeListener(DriveResource driveResource, OnChangeListener onChangeListener);

    public abstract Task<Void> addChangeSubscription(DriveResource driveResource);

    public abstract Task<Boolean> cancelOpenFileCallback(ListenerToken listenerToken);

    public abstract Task<Void> commitContents(DriveContents driveContents, MetadataChangeSet metadataChangeSet);

    public abstract Task<Void> commitContents(DriveContents driveContents, MetadataChangeSet metadataChangeSet, ExecutionOptions executionOptions);

    public abstract Task<DriveContents> createContents();

    public abstract Task<DriveFile> createFile(DriveFolder driveFolder, MetadataChangeSet metadataChangeSet, DriveContents driveContents);

    public abstract Task<DriveFile> createFile(DriveFolder driveFolder, MetadataChangeSet metadataChangeSet, DriveContents driveContents, ExecutionOptions executionOptions);

    public abstract Task<DriveFolder> createFolder(DriveFolder driveFolder, MetadataChangeSet metadataChangeSet);

    public abstract Task<Void> delete(DriveResource driveResource);

    public abstract Task<Void> discardContents(DriveContents driveContents);

    public abstract Task<DriveFolder> getAppFolder();

    public abstract Task<Metadata> getMetadata(DriveResource driveResource);

    public abstract Task<DriveFolder> getRootFolder();

    public abstract Task<MetadataBuffer> listChildren(DriveFolder driveFolder);

    public abstract Task<MetadataBuffer> listParents(DriveResource driveResource);

    public abstract Task<DriveContents> openFile(DriveFile driveFile, int i2);

    public abstract Task<ListenerToken> openFile(DriveFile driveFile, int i2, OpenFileCallback openFileCallback);

    public abstract Task<MetadataBuffer> query(Query query);

    public abstract Task<MetadataBuffer> queryChildren(DriveFolder driveFolder, Query query);

    public abstract Task<Boolean> removeChangeListener(ListenerToken listenerToken);

    public abstract Task<Void> removeChangeSubscription(DriveResource driveResource);

    public abstract Task<DriveContents> reopenContentsForWrite(DriveContents driveContents);

    public abstract Task<Void> setParents(DriveResource driveResource, Set<DriveId> set);

    public abstract Task<Void> trash(DriveResource driveResource);

    public abstract Task<Void> untrash(DriveResource driveResource);

    public abstract Task<Metadata> updateMetadata(DriveResource driveResource, MetadataChangeSet metadataChangeSet);

    public DriveResourceClient(Context context, Drive.zza zzaVar) {
        super(context, Drive.zzu, zzaVar, GoogleApi.Settings.DEFAULT_SETTINGS);
    }
}
