package com.android.billingclient.api;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.0.1 */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class z {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private boolean f1562a;

    public final void a() {
        this.f1562a = true;
    }

    public final x b() {
        if (this.f1562a) {
            return new x();
        }
        throw new IllegalArgumentException("Pending purchases for one-time products must be supported.");
    }
}
