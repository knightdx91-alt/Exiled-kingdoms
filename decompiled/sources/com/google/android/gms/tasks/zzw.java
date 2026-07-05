package com.google.android.gms.tasks;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzw implements Continuation {
    private final /* synthetic */ Collection zzae;

    zzw(Collection collection) {
        this.zzae = collection;
    }

    @Override // com.google.android.gms.tasks.Continuation
    public final /* synthetic */ Object then(Task task) {
        if (this.zzae.size() == 0) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        Iterator it = this.zzae.iterator();
        while (it.hasNext()) {
            arrayList.add(((Task) it.next()).getResult());
        }
        return arrayList;
    }
}
