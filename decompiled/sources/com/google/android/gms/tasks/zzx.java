package com.google.android.gms.tasks;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzx implements Continuation<Void, Task<List<Task<?>>>> {
    private final /* synthetic */ Collection zzae;

    zzx(Collection collection) {
        this.zzae = collection;
    }

    @Override // com.google.android.gms.tasks.Continuation
    public final /* synthetic */ Task<List<Task<?>>> then(Task<Void> task) {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.zzae);
        return Tasks.forResult(arrayList);
    }
}
