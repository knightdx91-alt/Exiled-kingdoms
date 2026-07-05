package com.google.android.gms.dynamic;

import android.os.Bundle;
import com.google.android.gms.dynamic.DeferredLifecycleHelper;
import java.util.Iterator;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zaa implements OnDelegateCreatedListener {
    private final /* synthetic */ DeferredLifecycleHelper zari;

    zaa(DeferredLifecycleHelper deferredLifecycleHelper) {
        this.zari = deferredLifecycleHelper;
    }

    @Override // com.google.android.gms.dynamic.OnDelegateCreatedListener
    public final void onDelegateCreated(LifecycleDelegate lifecycleDelegate) {
        this.zari.zare = lifecycleDelegate;
        Iterator it = this.zari.zarg.iterator();
        while (it.hasNext()) {
            ((DeferredLifecycleHelper.zaa) it.next()).zaa(this.zari.zare);
        }
        this.zari.zarg.clear();
        DeferredLifecycleHelper.zaa(this.zari, (Bundle) null);
    }
}
