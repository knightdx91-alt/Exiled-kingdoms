package com.google.android.gms.drive;

import android.app.Activity;
import android.content.Context;
import android.content.IntentSender;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.drive.Drive;
import com.google.android.gms.tasks.Task;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public abstract class DriveClient extends GoogleApi<Drive.zza> {
    public DriveClient(Activity activity, Drive.zza zzaVar) {
        super(activity, Drive.zzu, zzaVar, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    public abstract Task<DriveId> getDriveId(String str);

    public abstract Task<TransferPreferences> getUploadPreferences();

    public abstract Task<IntentSender> newCreateFileActivityIntentSender(CreateFileActivityOptions createFileActivityOptions);

    public abstract Task<IntentSender> newOpenFileActivityIntentSender(OpenFileActivityOptions openFileActivityOptions);

    public abstract Task<Void> requestSync();

    public abstract Task<Void> setUploadPreferences(TransferPreferences transferPreferences);

    public DriveClient(Context context, Drive.zza zzaVar) {
        super(context, Drive.zzu, zzaVar, GoogleApi.Settings.DEFAULT_SETTINGS);
    }
}
