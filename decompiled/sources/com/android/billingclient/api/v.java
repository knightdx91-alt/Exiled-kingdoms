package com.android.billingclient.api;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.text.TextUtils;
import com.google.android.gms.internal.play_billing.zzb;
import com.google.android.gms.internal.play_billing.zzd;
import com.google.android.gms.internal.play_billing.zze;
import com.google.android.gms.internal.play_billing.zzfe;
import com.google.android.gms.internal.play_billing.zzff;
import com.google.android.gms.internal.play_billing.zzgd;
import java.util.concurrent.Callable;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.0.1 */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class v implements ServiceConnection {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Object f1540a = new Object();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private e f1541b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final /* synthetic */ d f1542c;

    /* synthetic */ v(d dVar, e eVar) {
        this.f1542c = dVar;
        this.f1541b = eVar;
    }

    private final void c(g gVar) {
        synchronized (this.f1540a) {
            this.f1541b.a(gVar);
        }
    }

    final void a() {
        Bundle bundle;
        int i2;
        int iZzq;
        synchronized (this.f1540a) {
        }
        if (TextUtils.isEmpty(null)) {
            bundle = null;
        } else {
            bundle = new Bundle();
            bundle.putString("accountName", null);
        }
        int i3 = 3;
        try {
            String packageName = this.f1542c.f1452e.getPackageName();
            iZzq = 3;
            int i4 = 20;
            while (true) {
                if (i4 < 3) {
                    i4 = 0;
                    break;
                }
                if (bundle == null) {
                    try {
                        iZzq = this.f1542c.f1454g.zzq(i4, packageName, "subs");
                    } catch (Exception e2) {
                        e = e2;
                        i3 = iZzq;
                        zzb.zzk("BillingClient", "Exception while checking if billing is supported; try to reconnect", e);
                        this.f1542c.f1448a = 0;
                        this.f1542c.f1454g = null;
                        i2 = 42;
                        iZzq = i3;
                    }
                } else {
                    iZzq = this.f1542c.f1454g.zzc(i4, packageName, "subs", bundle);
                }
                if (iZzq == 0) {
                    zzb.zzi("BillingClient", "highestLevelSupportedForSubs: " + i4);
                    break;
                }
                i4--;
            }
            this.f1542c.getClass();
            boolean z2 = true;
            this.f1542c.f1456i = i4 >= 3;
            if (i4 < 3) {
                zzb.zzi("BillingClient", "In-app billing API does not support subscription on this device.");
                i2 = 9;
            } else {
                i2 = 1;
            }
            int i5 = 20;
            while (true) {
                if (i5 < 3) {
                    break;
                }
                iZzq = bundle == null ? this.f1542c.f1454g.zzq(i5, packageName, "inapp") : this.f1542c.f1454g.zzc(i5, packageName, "inapp", bundle);
                if (iZzq == 0) {
                    this.f1542c.f1457j = i5;
                    zzb.zzi("BillingClient", "mHighestLevelSupportedForInApp: " + this.f1542c.f1457j);
                    break;
                }
                i5--;
            }
            d dVar = this.f1542c;
            dVar.f1465r = dVar.f1457j >= 20;
            d dVar2 = this.f1542c;
            dVar2.f1464q = dVar2.f1457j >= 19;
            int unused = this.f1542c.f1457j;
            d dVar3 = this.f1542c;
            dVar3.f1463p = dVar3.f1457j >= 17;
            d dVar4 = this.f1542c;
            dVar4.f1462o = dVar4.f1457j >= 16;
            d dVar5 = this.f1542c;
            dVar5.f1461n = dVar5.f1457j >= 15;
            d dVar6 = this.f1542c;
            dVar6.f1460m = dVar6.f1457j >= 14;
            int unused2 = this.f1542c.f1457j;
            int unused3 = this.f1542c.f1457j;
            d dVar7 = this.f1542c;
            dVar7.f1459l = dVar7.f1457j >= 9;
            int unused4 = this.f1542c.f1457j;
            d dVar8 = this.f1542c;
            if (dVar8.f1457j < 6) {
                z2 = false;
            }
            dVar8.f1458k = z2;
            if (this.f1542c.f1457j < 3) {
                zzb.zzj("BillingClient", "In-app billing API version 3 is not supported on this device.");
                i2 = 36;
            }
            if (iZzq == 0) {
                this.f1542c.f1448a = 2;
            } else {
                this.f1542c.f1448a = 0;
                this.f1542c.f1454g = null;
            }
        } catch (Exception e3) {
            e = e3;
        }
        if (iZzq != 0) {
            a0 a0Var = this.f1542c.f1453f;
            g gVar = w.f1543a;
            a0Var.c(x.a(i2, 6, gVar));
            c(gVar);
            return;
        }
        a0 a0Var2 = this.f1542c.f1453f;
        zzfe zzfeVarZzv = zzff.zzv();
        zzfeVarZzv.zzj(6);
        a0Var2.f((zzff) zzfeVarZzv.zzc());
        c(w.f1551i);
    }

    final /* synthetic */ void b() {
        d dVar = this.f1542c;
        dVar.f1448a = 0;
        dVar.f1454g = null;
        a0 a0Var = dVar.f1453f;
        g gVar = w.f1553k;
        a0Var.c(x.a(24, 6, gVar));
        c(gVar);
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [com.android.billingclient.api.u] */
    /* JADX WARN: Type inference failed for: r3v3, types: [com.android.billingclient.api.t] */
    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        zzb.zzi("BillingClient", "Billing service connected.");
        zze zzeVarZzn = zzd.zzn(iBinder);
        d dVar = this.f1542c;
        dVar.f1454g = zzeVarZzn;
        if (dVar.w(new Callable() { // from class: com.android.billingclient.api.t
            @Override // java.util.concurrent.Callable
            public final Object call() {
                this.f1538a.a();
                return null;
            }
        }, 30000L, new Runnable() { // from class: com.android.billingclient.api.u
            @Override // java.lang.Runnable
            public final void run() {
                this.f1539b.b();
            }
        }, dVar.s()) == null) {
            g gVarU = dVar.u();
            dVar.f1453f.c(x.a(25, 6, gVarU));
            c(gVarU);
        }
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
        zzb.zzj("BillingClient", "Billing service disconnected.");
        this.f1542c.f1453f.h(zzgd.zzw());
        this.f1542c.f1454g = null;
        this.f1542c.f1448a = 0;
        synchronized (this.f1540a) {
            this.f1541b.b();
        }
    }
}
