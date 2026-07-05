package com.android.billingclient.api;

import com.google.android.datatransport.Transformer;
import com.google.android.gms.internal.play_billing.zzfa;
import com.google.android.gms.internal.play_billing.zzfb;
import com.google.android.gms.internal.play_billing.zzfh;
import com.google.android.gms.internal.play_billing.zzfj;
import com.google.android.gms.internal.play_billing.zzfz;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.0.1 */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final /* synthetic */ class x implements Transformer {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ x f1559a = new x();

    public static zzfb a(int i2, int i3, g gVar) {
        zzfa zzfaVarZzv = zzfb.zzv();
        zzfh zzfhVarZzv = zzfj.zzv();
        zzfhVarZzv.zzj(gVar.b());
        zzfhVarZzv.zzi(gVar.a());
        zzfhVarZzv.zzk(i2);
        zzfaVarZzv.zzi(zzfhVarZzv);
        zzfaVarZzv.zzk(i3);
        return (zzfb) zzfaVarZzv.zzc();
    }

    @Override // com.google.android.datatransport.Transformer
    public Object apply(Object obj) {
        return ((zzfz) obj).zzc();
    }
}
