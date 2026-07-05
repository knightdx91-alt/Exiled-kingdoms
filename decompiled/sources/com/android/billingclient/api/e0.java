package com.android.billingclient.api;

import com.google.android.gms.internal.play_billing.zzb;
import java.util.concurrent.Future;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.0.1 */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final /* synthetic */ class e0 implements Runnable {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ int f1471b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Object f1472c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ Object f1473d;

    public /* synthetic */ e0(Object obj, Object obj2, int i2) {
        this.f1471b = i2;
        this.f1472c = obj;
        this.f1473d = obj2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f1471b) {
            case 0:
                ((d) this.f1472c).p((l) this.f1473d);
                break;
            case 1:
                ((d) this.f1472c).m((b) this.f1473d);
                break;
            case 2:
                ((d) this.f1472c).q((n) this.f1473d);
                break;
            case 3:
                ((d) this.f1472c).n((g) this.f1473d);
                break;
            default:
                Future future = (Future) this.f1472c;
                if (!future.isDone() && !future.isCancelled()) {
                    future.cancel(true);
                    zzb.zzj("BillingClient", "Async task is taking too long, cancel it!");
                    Runnable runnable = (Runnable) this.f1473d;
                    if (runnable != null) {
                        runnable.run();
                    }
                    break;
                }
                break;
        }
    }
}
