package com.google.android.gms.common;

import com.google.android.gms.common.api.internal.zai;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import java.util.Map;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zaa implements Continuation<Map<zai<?>, String>, Void> {
    zaa(GoogleApiAvailability googleApiAvailability) {
    }

    @Override // com.google.android.gms.tasks.Continuation
    public final /* synthetic */ Void then(Task<Map<zai<?>, String>> task) {
        task.getResult();
        return null;
    }
}
