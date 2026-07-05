package com.android.billingclient.api;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import com.google.android.gms.internal.play_billing.zzb;
import com.google.android.gms.internal.play_billing.zzbn;
import com.google.android.gms.internal.play_billing.zzfb;
import com.google.android.gms.internal.play_billing.zzfe;
import com.google.android.gms.internal.play_billing.zzff;
import com.google.android.gms.internal.play_billing.zzu;
import java.util.List;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.0.1 */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class c0 extends BroadcastReceiver {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final /* synthetic */ int f1443e = 0;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final e0.f f1444a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final a0 f1445b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f1446c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final /* synthetic */ a0 f1447d;

    /* synthetic */ c0(a0 a0Var, a0 a0Var2) {
        this.f1447d = a0Var;
        this.f1444a = null;
        this.f1445b = a0Var2;
    }

    private final void c(Bundle bundle, g gVar, int i2) {
        byte[] byteArray = bundle.getByteArray("FAILURE_LOGGING_PAYLOAD");
        a0 a0Var = this.f1445b;
        if (byteArray == null) {
            a0Var.c(x.a(23, i2, gVar));
            return;
        }
        try {
            a0Var.c(zzfb.zzx(bundle.getByteArray("FAILURE_LOGGING_PAYLOAD"), zzbn.zza()));
        } catch (Throwable unused) {
            zzb.zzj("BillingBroadcastManager", "Failed parsing Api failure.");
        }
    }

    @SuppressLint({"UnprotectedReceiver"})
    public final void b(Context context, IntentFilter intentFilter) {
        if (this.f1446c) {
            return;
        }
        int i2 = Build.VERSION.SDK_INT;
        a0 a0Var = this.f1447d;
        if (i2 >= 33) {
            context.registerReceiver(a0.b(a0Var), intentFilter, 2);
        } else {
            context.registerReceiver(a0.b(a0Var), intentFilter);
        }
        this.f1446c = true;
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        Bundle extras = intent.getExtras();
        int i2 = 1;
        a0 a0Var = this.f1445b;
        e0.f fVar = this.f1444a;
        if (extras == null) {
            zzb.zzj("BillingBroadcastManager", "Bundle is null.");
            g gVar = w.f1550h;
            a0Var.c(x.a(11, 1, gVar));
            if (fVar != null) {
                fVar.u(gVar, null);
                return;
            }
            return;
        }
        g gVarZzd = zzb.zzd(intent, "BillingBroadcastManager");
        String action = intent.getAction();
        String string = extras.getString("INTENT_SOURCE");
        if (string == "LAUNCH_BILLING_FLOW" || (string != null && string.equals("LAUNCH_BILLING_FLOW"))) {
            i2 = 2;
        }
        if (action.equals("com.android.vending.billing.PURCHASES_UPDATED")) {
            List<m> listZzh = zzb.zzh(extras);
            if (gVarZzd.b() == 0) {
                zzfe zzfeVarZzv = zzff.zzv();
                zzfeVarZzv.zzj(i2);
                a0Var.f((zzff) zzfeVarZzv.zzc());
            } else {
                c(extras, gVarZzd, i2);
            }
            fVar.u(gVarZzd, listZzh);
            return;
        }
        if (action.equals("com.android.vending.billing.ALTERNATIVE_BILLING")) {
            if (gVarZzd.b() != 0) {
                c(extras, gVarZzd, i2);
                fVar.u(gVarZzd, zzu.zzk());
            } else {
                zzb.zzj("BillingBroadcastManager", "AlternativeBillingListener is null.");
                g gVar2 = w.f1550h;
                a0Var.c(x.a(15, i2, gVar2));
                fVar.u(gVar2, zzu.zzk());
            }
        }
    }

    /* synthetic */ c0(a0 a0Var, e0.f fVar, a0 a0Var2) {
        this.f1447d = a0Var;
        this.f1444a = fVar;
        this.f1445b = a0Var2;
    }
}
