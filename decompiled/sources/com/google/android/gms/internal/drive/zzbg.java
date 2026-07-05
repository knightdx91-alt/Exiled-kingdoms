package com.google.android.gms.internal.drive;

import android.content.IntentSender;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.drive.CreateFileActivityOptions;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzbg extends TaskApiCall<zzaw, IntentSender> {
    private final /* synthetic */ CreateFileActivityOptions zzep;

    zzbg(zzbb zzbbVar, CreateFileActivityOptions createFileActivityOptions) {
        this.zzep = createFileActivityOptions;
    }

    @Override // com.google.android.gms.common.api.internal.TaskApiCall
    protected final /* synthetic */ void doExecute(Api.AnyClient anyClient, TaskCompletionSource<IntentSender> taskCompletionSource) {
        zzaw zzawVar = (zzaw) anyClient;
        zzeo zzeoVar = (zzeo) zzawVar.getService();
        this.zzep.zzdc.zza(zzawVar.getContext());
        CreateFileActivityOptions createFileActivityOptions = this.zzep;
        MetadataBundle metadataBundle = createFileActivityOptions.zzdc;
        int iIntValue = createFileActivityOptions.zzdi.intValue();
        CreateFileActivityOptions createFileActivityOptions2 = this.zzep;
        taskCompletionSource.setResult(zzeoVar.zza(new zzu(metadataBundle, iIntValue, createFileActivityOptions2.zzay, createFileActivityOptions2.zzbb, Integer.valueOf(createFileActivityOptions2.zzdj))));
    }
}
