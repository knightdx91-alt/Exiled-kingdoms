package com.google.android.gms.dynamic;

import com.google.android.gms.dynamic.DeferredLifecycleHelper;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zag implements DeferredLifecycleHelper.zaa {
    private final /* synthetic */ DeferredLifecycleHelper zari;

    zag(DeferredLifecycleHelper deferredLifecycleHelper) {
        this.zari = deferredLifecycleHelper;
    }

    @Override // com.google.android.gms.dynamic.DeferredLifecycleHelper.zaa
    public final int getState() {
        return 5;
    }

    @Override // com.google.android.gms.dynamic.DeferredLifecycleHelper.zaa
    public final void zaa(LifecycleDelegate lifecycleDelegate) {
        this.zari.zare.onResume();
    }
}
