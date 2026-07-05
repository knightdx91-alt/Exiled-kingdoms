package com.android.billingclient.api;

import android.content.Context;
import com.google.android.datatransport.Encoding;
import com.google.android.datatransport.Event;
import com.google.android.datatransport.Transport;
import com.google.android.datatransport.cct.CCTDestination;
import com.google.android.datatransport.runtime.TransportRuntime;
import com.google.android.gms.internal.play_billing.zzb;
import com.google.android.gms.internal.play_billing.zzfz;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.0.1 */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class y {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private boolean f1560a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Transport f1561b;

    y(Context context) {
        try {
            TransportRuntime.initialize(context);
            this.f1561b = TransportRuntime.getInstance().newFactory(CCTDestination.INSTANCE).getTransport("PLAY_BILLING_LIBRARY", zzfz.class, Encoding.of("proto"), x.f1559a);
        } catch (Throwable unused) {
            this.f1560a = true;
        }
    }

    public final void a(zzfz zzfzVar) {
        if (this.f1560a) {
            zzb.zzj("BillingLogger", "Skipping logging since initialization failed.");
            return;
        }
        try {
            this.f1561b.send(Event.ofData(zzfzVar));
        } catch (Throwable unused) {
            zzb.zzj("BillingLogger", "logging failed.");
        }
    }
}
