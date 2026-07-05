package com.google.android.gms.common.api.internal;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zap extends ThreadLocal<Boolean> {
    zap() {
    }

    @Override // java.lang.ThreadLocal
    protected final /* synthetic */ Boolean initialValue() {
        return Boolean.FALSE;
    }
}
