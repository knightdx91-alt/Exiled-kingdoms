package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zacl implements Continuation<Boolean, Void> {
    zacl() {
    }

    @Override // com.google.android.gms.tasks.Continuation
    public final /* synthetic */ Void then(Task<Boolean> task) throws ApiException {
        if (task.getResult().booleanValue()) {
            return null;
        }
        throw new ApiException(new Status(13, "listener already unregistered"));
    }
}
