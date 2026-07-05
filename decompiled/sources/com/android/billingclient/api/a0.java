package com.android.billingclient.api;

import android.content.Context;
import android.content.IntentFilter;
import com.google.android.gms.internal.play_billing.zzb;
import com.google.android.gms.internal.play_billing.zzfb;
import com.google.android.gms.internal.play_billing.zzff;
import com.google.android.gms.internal.play_billing.zzfm;
import com.google.android.gms.internal.play_billing.zzfy;
import com.google.android.gms.internal.play_billing.zzfz;
import com.google.android.gms.internal.play_billing.zzgd;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.0.1 */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class a0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Object f1436a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Object f1437b;

    public a0(g gVar, ArrayList arrayList) {
        this.f1436a = arrayList;
        this.f1437b = gVar;
    }

    static /* bridge */ /* synthetic */ c0 b(a0 a0Var) {
        return (c0) a0Var.f1437b;
    }

    public g a() {
        return (g) this.f1437b;
    }

    public void c(zzfb zzfbVar) {
        try {
            zzfy zzfyVarZzv = zzfz.zzv();
            zzfm zzfmVar = (zzfm) this.f1436a;
            if (zzfmVar != null) {
                zzfyVarZzv.zzk(zzfmVar);
            }
            zzfyVarZzv.zzi(zzfbVar);
            ((y) this.f1437b).a((zzfz) zzfyVarZzv.zzc());
        } catch (Throwable unused) {
            zzb.zzj("BillingLogger", "Unable to log.");
        }
    }

    public List d() {
        return (ArrayList) this.f1436a;
    }

    void e() {
        int i2 = c0.f1443e;
        ((c0) this.f1437b).getClass();
    }

    public void f(zzff zzffVar) {
        try {
            zzfy zzfyVarZzv = zzfz.zzv();
            zzfm zzfmVar = (zzfm) this.f1436a;
            if (zzfmVar != null) {
                zzfyVarZzv.zzk(zzfmVar);
            }
            zzfyVarZzv.zzj(zzffVar);
            ((y) this.f1437b).a((zzfz) zzfyVarZzv.zzc());
        } catch (Throwable unused) {
            zzb.zzj("BillingLogger", "Unable to log.");
        }
    }

    o g() {
        return ((c0) this.f1437b).f1444a;
    }

    public void h(zzgd zzgdVar) {
        try {
            zzfy zzfyVarZzv = zzfz.zzv();
            zzfm zzfmVar = (zzfm) this.f1436a;
            if (zzfmVar != null) {
                zzfyVarZzv.zzk(zzfmVar);
            }
            zzfyVarZzv.zzl(zzgdVar);
            ((y) this.f1437b).a((zzfz) zzfyVarZzv.zzc());
        } catch (Throwable unused) {
            zzb.zzj("BillingLogger", "Unable to log.");
        }
    }

    void i() {
        IntentFilter intentFilter = new IntentFilter("com.android.vending.billing.PURCHASES_UPDATED");
        intentFilter.addAction("com.android.vending.billing.ALTERNATIVE_BILLING");
        ((c0) this.f1437b).b((Context) this.f1436a, intentFilter);
    }

    a0(Context context, a0 a0Var) {
        this.f1436a = context;
        this.f1437b = new c0(this, a0Var);
    }

    a0(Context context, zzfm zzfmVar) {
        this.f1437b = new y(context);
        this.f1436a = zzfmVar;
    }

    a0(Context context, e0.f fVar, a0 a0Var) {
        this.f1436a = context;
        this.f1437b = new c0(this, fVar, a0Var);
    }
}
