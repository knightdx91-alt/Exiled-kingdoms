package com.android.billingclient.api;

import com.google.android.gms.internal.play_billing.zzu;
import java.util.List;
import java.util.concurrent.Callable;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.0.1 */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class h0 implements Callable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ String f1499a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final /* synthetic */ n f1500b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final /* synthetic */ d f1501c;

    h0(d dVar, String str, n nVar) {
        this.f1501c = dVar;
        this.f1499a = str;
        this.f1500b = nVar;
    }

    @Override // java.util.concurrent.Callable
    public final /* bridge */ /* synthetic */ Object call() {
        a0 a0VarR = d.r(this.f1501c, this.f1499a);
        List listD = a0VarR.d();
        n nVar = this.f1500b;
        if (listD != null) {
            nVar.a(a0VarR.a(), a0VarR.d());
            return null;
        }
        nVar.a(a0VarR.a(), zzu.zzk());
        return null;
    }
}
