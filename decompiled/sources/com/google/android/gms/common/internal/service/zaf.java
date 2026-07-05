package com.google.android.gms.common.internal.service;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zaf extends zaa {
    private final BaseImplementation.ResultHolder<Status> mResultHolder;

    public zaf(BaseImplementation.ResultHolder<Status> resultHolder) {
        this.mResultHolder = resultHolder;
    }

    @Override // com.google.android.gms.common.internal.service.zaa, com.google.android.gms.common.internal.service.zaj
    public final void zaj(int i2) {
        this.mResultHolder.setResult(new Status(i2));
    }
}
