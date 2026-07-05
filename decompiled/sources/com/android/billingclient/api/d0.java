package com.android.billingclient.api;

import java.util.concurrent.Callable;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.0.1 */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final /* synthetic */ class d0 implements Callable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1467a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ d f1468b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Object f1469c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ Object f1470d;

    public /* synthetic */ d0(d dVar, Object obj, Object obj2, int i2) {
        this.f1467a = i2;
        this.f1468b = dVar;
        this.f1469c = obj;
        this.f1470d = obj2;
    }

    @Override // java.util.concurrent.Callable
    public final Object call() {
        switch (this.f1467a) {
            case 0:
                this.f1468b.H((p) this.f1469c, (l) this.f1470d);
                return null;
            case 1:
                this.f1468b.G((h) this.f1469c, (i) this.f1470d);
                return null;
            case 2:
                this.f1468b.F((a) this.f1469c, (b) this.f1470d);
                return null;
            default:
                return this.f1468b.A((String) this.f1469c, (String) this.f1470d);
        }
    }
}
