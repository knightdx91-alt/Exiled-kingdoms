package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.internal.BackgroundDetector;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zabi implements BackgroundDetector.BackgroundStateChangeListener {
    private final /* synthetic */ GoogleApiManager zail;

    zabi(GoogleApiManager googleApiManager) {
        this.zail = googleApiManager;
    }

    @Override // com.google.android.gms.common.api.internal.BackgroundDetector.BackgroundStateChangeListener
    public final void onBackgroundStateChanged(boolean z2) {
        this.zail.handler.sendMessage(this.zail.handler.obtainMessage(1, Boolean.valueOf(z2)));
    }
}
