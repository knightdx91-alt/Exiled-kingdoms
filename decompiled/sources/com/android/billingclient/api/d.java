package com.android.billingclient.api;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.android.billingclient.api.f;
import com.android.billingclient.api.g;
import com.android.billingclient.api.p;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.google.android.gms.internal.play_billing.zzb;
import com.google.android.gms.internal.play_billing.zze;
import com.google.android.gms.internal.play_billing.zzfe;
import com.google.android.gms.internal.play_billing.zzff;
import com.google.android.gms.internal.play_billing.zzfl;
import com.google.android.gms.internal.play_billing.zzfm;
import com.google.android.gms.internal.play_billing.zzm;
import com.google.android.gms.internal.play_billing.zzu;
import com.google.android.gms.internal.play_billing.zzz;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.json.JSONException;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.0.1 */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class d extends c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private volatile int f1448a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f1449b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Handler f1450c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private volatile a0 f1451d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Context f1452e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private a0 f1453f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private volatile zze f1454g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private volatile v f1455h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private boolean f1456i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private int f1457j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private boolean f1458k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private boolean f1459l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private boolean f1460m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private boolean f1461n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private boolean f1462o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private boolean f1463p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private boolean f1464q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private boolean f1465r;
    private boolean s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    private ExecutorService f1466t;

    d(Activity activity) {
        this.f1448a = 0;
        this.f1450c = new Handler(Looper.getMainLooper());
        this.f1457j = 0;
        this.f1449b = v();
        this.f1452e = activity.getApplicationContext();
        zzfl zzflVarZzv = zzfm.zzv();
        zzflVarZzv.zzj(v());
        zzflVarZzv.zzi(this.f1452e.getPackageName());
        this.f1453f = new a0(this.f1452e, (zzfm) zzflVarZzv.zzc());
        zzb.zzj("BillingClient", "Billing client should have a valid listener but the provided is null.");
        this.f1451d = new a0(this.f1452e, this.f1453f);
    }

    static a0 r(d dVar, String str) {
        b0 b0Var;
        zzb.zzi("BillingClient", "Querying owned items, item type: ".concat(String.valueOf(str)));
        ArrayList arrayList = new ArrayList();
        int i2 = 1;
        Bundle bundleZzc = zzb.zzc(dVar.f1459l, dVar.f1464q, true, false, dVar.f1449b);
        ArrayList arrayList2 = null;
        String string = null;
        while (true) {
            try {
                Bundle bundleZzj = dVar.f1459l ? dVar.f1454g.zzj(i2 != dVar.f1464q ? 9 : 19, dVar.f1452e.getPackageName(), str, string, bundleZzc) : dVar.f1454g.zzi(3, dVar.f1452e.getPackageName(), str, string);
                g gVar = w.f1550h;
                if (bundleZzj == null) {
                    zzb.zzj("BillingClient", "getPurchase() got null owned items list");
                    b0Var = new b0(gVar, 54);
                } else {
                    int iZzb = zzb.zzb(bundleZzj, "BillingClient");
                    String strZzf = zzb.zzf(bundleZzj, "BillingClient");
                    g.a aVar = new g.a();
                    aVar.c(iZzb);
                    aVar.b(strZzf);
                    g gVarA = aVar.a();
                    if (iZzb != 0) {
                        zzb.zzj("BillingClient", "getPurchase() failed. Response code: " + iZzb);
                        b0Var = new b0(gVarA, 23);
                    } else if (bundleZzj.containsKey("INAPP_PURCHASE_ITEM_LIST") && bundleZzj.containsKey("INAPP_PURCHASE_DATA_LIST") && bundleZzj.containsKey("INAPP_DATA_SIGNATURE_LIST")) {
                        ArrayList<String> stringArrayList = bundleZzj.getStringArrayList("INAPP_PURCHASE_ITEM_LIST");
                        ArrayList<String> stringArrayList2 = bundleZzj.getStringArrayList("INAPP_PURCHASE_DATA_LIST");
                        ArrayList<String> stringArrayList3 = bundleZzj.getStringArrayList("INAPP_DATA_SIGNATURE_LIST");
                        if (stringArrayList == null) {
                            zzb.zzj("BillingClient", "Bundle returned from getPurchase() contains null SKUs list.");
                            b0Var = new b0(gVar, 56);
                        } else if (stringArrayList2 == null) {
                            zzb.zzj("BillingClient", "Bundle returned from getPurchase() contains null purchases list.");
                            b0Var = new b0(gVar, 57);
                        } else if (stringArrayList3 == null) {
                            zzb.zzj("BillingClient", "Bundle returned from getPurchase() contains null signatures list.");
                            b0Var = new b0(gVar, 58);
                        } else {
                            b0Var = new b0(w.f1551i, i2);
                        }
                    } else {
                        zzb.zzj("BillingClient", "Bundle returned from getPurchase() doesn't contain required fields.");
                        b0Var = new b0(gVar, 55);
                    }
                }
                g gVarA2 = b0Var.a();
                if (gVarA2 != w.f1551i) {
                    dVar.f1453f.c(x.a(b0Var.b(), 9, gVarA2));
                    return new a0(gVarA2, arrayList2);
                }
                ArrayList<String> stringArrayList4 = bundleZzj.getStringArrayList("INAPP_PURCHASE_ITEM_LIST");
                ArrayList<String> stringArrayList5 = bundleZzj.getStringArrayList("INAPP_PURCHASE_DATA_LIST");
                ArrayList<String> stringArrayList6 = bundleZzj.getStringArrayList("INAPP_DATA_SIGNATURE_LIST");
                boolean z2 = false;
                for (int i3 = 0; i3 < stringArrayList5.size(); i3++) {
                    String str2 = stringArrayList5.get(i3);
                    String str3 = stringArrayList6.get(i3);
                    zzb.zzi("BillingClient", "Sku is owned: ".concat(String.valueOf(stringArrayList4.get(i3))));
                    try {
                        m mVar = new m(str2, str3);
                        if (TextUtils.isEmpty(mVar.f())) {
                            zzb.zzj("BillingClient", "BUG: empty/null token!");
                            z2 = true;
                        }
                        arrayList.add(mVar);
                    } catch (JSONException e2) {
                        zzb.zzk("BillingClient", "Got an exception trying to decode the purchase!", e2);
                        a0 a0Var = dVar.f1453f;
                        g gVar2 = w.f1550h;
                        a0Var.c(x.a(51, 9, gVar2));
                        return new a0(gVar2, (ArrayList) null);
                    }
                }
                if (z2) {
                    dVar.f1453f.c(x.a(26, 9, w.f1550h));
                }
                string = bundleZzj.getString("INAPP_CONTINUATION_TOKEN");
                zzb.zzi("BillingClient", "Continuation token: ".concat(String.valueOf(string)));
                if (TextUtils.isEmpty(string)) {
                    return new a0(w.f1551i, arrayList);
                }
                arrayList2 = null;
                i2 = 1;
            } catch (Exception e3) {
                a0 a0Var2 = dVar.f1453f;
                g gVar3 = w.f1552j;
                a0Var2.c(x.a(52, 9, gVar3));
                zzb.zzk("BillingClient", "Got exception trying to get purchasesm try to reconnect", e3);
                return new a0(gVar3, (ArrayList) null);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Handler s() {
        return Looper.myLooper() == null ? this.f1450c : new Handler(Looper.myLooper());
    }

    private final void t(g gVar) {
        if (Thread.interrupted()) {
            return;
        }
        this.f1450c.post(new e0(this, gVar, 3));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final g u() {
        return (this.f1448a == 0 || this.f1448a == 3) ? w.f1552j : w.f1550h;
    }

    @SuppressLint({"PrivateApi"})
    private static String v() {
        try {
            return (String) Class.forName("com.android.billingclient.ktx.BuildConfig").getField("VERSION_NAME").get(null);
        } catch (Exception unused) {
            return "6.0.1";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Future w(Callable callable, long j2, Runnable runnable, Handler handler) {
        if (this.f1466t == null) {
            this.f1466t = Executors.newFixedThreadPool(zzb.zza, new s());
        }
        try {
            Future futureSubmit = this.f1466t.submit(callable);
            handler.postDelayed(new e0(futureSubmit, runnable, 4), (long) (j2 * 0.95d));
            return futureSubmit;
        } catch (Exception e2) {
            zzb.zzk("BillingClient", "Async task throws exception!", e2);
            return null;
        }
    }

    final /* synthetic */ Bundle A(String str, String str2) {
        return this.f1454g.zzf(3, this.f1452e.getPackageName(), str, str2, null);
    }

    final void F(a aVar, b bVar) {
        try {
            zze zzeVar = this.f1454g;
            String packageName = this.f1452e.getPackageName();
            String strA = aVar.a();
            String str = this.f1449b;
            Bundle bundle = new Bundle();
            bundle.putString("playBillingLibraryVersion", str);
            Bundle bundleZzd = zzeVar.zzd(9, packageName, strA, bundle);
            int iZzb = zzb.zzb(bundleZzd, "BillingClient");
            String strZzf = zzb.zzf(bundleZzd, "BillingClient");
            g.a aVar2 = new g.a();
            aVar2.c(iZzb);
            aVar2.b(strZzf);
            aVar2.a();
        } catch (Exception e2) {
            zzb.zzk("BillingClient", "Error acknowledge purchase!", e2);
            this.f1453f.c(x.a(28, 3, w.f1552j));
        }
    }

    final void G(h hVar, i iVar) {
        int iZza;
        String strZzf;
        String strA = hVar.a();
        try {
            zzb.zzi("BillingClient", "Consuming purchase with token: " + strA);
            if (this.f1459l) {
                zze zzeVar = this.f1454g;
                String packageName = this.f1452e.getPackageName();
                boolean z2 = this.f1459l;
                String str = this.f1449b;
                Bundle bundle = new Bundle();
                if (z2) {
                    bundle.putString("playBillingLibraryVersion", str);
                }
                Bundle bundleZze = zzeVar.zze(9, packageName, strA, bundle);
                iZza = bundleZze.getInt("RESPONSE_CODE");
                strZzf = zzb.zzf(bundleZze, "BillingClient");
            } else {
                iZza = this.f1454g.zza(3, this.f1452e.getPackageName(), strA);
                strZzf = "";
            }
            g.a aVar = new g.a();
            aVar.c(iZza);
            aVar.b(strZzf);
            g gVarA = aVar.a();
            if (iZza == 0) {
                zzb.zzi("BillingClient", "Successfully consumed purchase.");
                return;
            }
            zzb.zzj("BillingClient", "Error consuming purchase with token. Response code: " + iZza);
            this.f1453f.c(x.a(23, 4, gVarA));
        } catch (Exception e2) {
            zzb.zzk("BillingClient", "Error consuming purchase!", e2);
            this.f1453f.c(x.a(29, 4, w.f1552j));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v1, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r2v14 */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    final void H(p pVar, l lVar) {
        String strZzf;
        int iZzb;
        int i2;
        int i3;
        int i4;
        ?? r2 = 1;
        ArrayList arrayList = new ArrayList();
        String strB = pVar.b();
        zzu zzuVarA = pVar.a();
        int size = zzuVarA.size();
        int i5 = 0;
        while (true) {
            if (i5 >= size) {
                strZzf = "";
                iZzb = 0;
                break;
            }
            int i6 = i5 + 20;
            ArrayList arrayList2 = new ArrayList(zzuVarA.subList(i5, i6 > size ? size : i6));
            ArrayList<String> arrayList3 = new ArrayList<>();
            int size2 = arrayList2.size();
            for (int i7 = 0; i7 < size2; i7 += r2) {
                arrayList3.add(((p.b) arrayList2.get(i7)).a());
            }
            Bundle bundle = new Bundle();
            bundle.putStringArrayList("ITEM_ID_LIST", arrayList3);
            bundle.putString("playBillingLibraryVersion", this.f1449b);
            try {
                zze zzeVar = this.f1454g;
                int i8 = r2 != this.f1465r ? 17 : 20;
                String packageName = this.f1452e.getPackageName();
                String str = this.f1449b;
                if (TextUtils.isEmpty(null)) {
                    this.f1452e.getPackageName();
                }
                Bundle bundle2 = new Bundle();
                bundle2.putString("playBillingLibraryVersion", str);
                bundle2.putBoolean("enablePendingPurchases", r2);
                bundle2.putString("SKU_DETAILS_RESPONSE_FORMAT", "PRODUCT_DETAILS");
                ArrayList arrayList4 = new ArrayList();
                ArrayList arrayList5 = new ArrayList();
                int size3 = arrayList2.size();
                zzu zzuVar = zzuVarA;
                int i9 = 0;
                boolean z2 = false;
                boolean z3 = false;
                while (i9 < size3) {
                    p.b bVar = (p.b) arrayList2.get(i9);
                    int i10 = size3;
                    arrayList4.add(null);
                    z2 |= !TextUtils.isEmpty(null);
                    String strB2 = bVar.b();
                    ArrayList arrayList6 = arrayList2;
                    if (strB2.equals("first_party")) {
                        zzm.zzc(null, "Serialized DocId is required for constructing ExtraParams to query ProductDetails for all first party products.");
                        arrayList5.add(null);
                        i4 = 1;
                        z3 = true;
                    } else {
                        i4 = 1;
                    }
                    i9 += i4;
                    arrayList2 = arrayList6;
                    size3 = i10;
                }
                if (z2) {
                    bundle2.putStringArrayList("SKU_OFFER_ID_TOKEN_LIST", arrayList4);
                }
                if (!arrayList5.isEmpty()) {
                    bundle2.putStringArrayList("SKU_SERIALIZED_DOCID_LIST", arrayList5);
                }
                if (z3 && !TextUtils.isEmpty(null)) {
                    bundle2.putString("accountName", null);
                }
                i2 = 7;
                i3 = 6;
                try {
                    Bundle bundleZzl = zzeVar.zzl(i8, packageName, strB, bundle, bundle2);
                    strZzf = "Item is unavailable for purchase.";
                    if (bundleZzl == null) {
                        zzb.zzj("BillingClient", "queryProductDetailsAsync got empty product details response.");
                        a0 a0Var = this.f1453f;
                        g.a aVar = new g.a();
                        aVar.c(4);
                        aVar.b("Item is unavailable for purchase.");
                        a0Var.c(x.a(44, 7, aVar.a()));
                        break;
                    }
                    if (bundleZzl.containsKey("DETAILS_LIST")) {
                        ArrayList<String> stringArrayList = bundleZzl.getStringArrayList("DETAILS_LIST");
                        if (stringArrayList == null) {
                            zzb.zzj("BillingClient", "queryProductDetailsAsync got null response list");
                            this.f1453f.c(x.a(46, 7, w.f1558p));
                            break;
                        }
                        for (int i11 = 0; i11 < stringArrayList.size(); i11++) {
                            try {
                                k kVar = new k(stringArrayList.get(i11));
                                zzb.zzi("BillingClient", "Got product details: ".concat(kVar.toString()));
                                arrayList.add(kVar);
                            } catch (JSONException e2) {
                                zzb.zzk("BillingClient", "Got a JSON exception trying to decode ProductDetails. \n Exception: ", e2);
                                a0 a0Var2 = this.f1453f;
                                g.a aVar2 = new g.a();
                                aVar2.c(6);
                                strZzf = "Error trying to decode SkuDetails.";
                                aVar2.b("Error trying to decode SkuDetails.");
                                a0Var2.c(x.a(47, 7, aVar2.a()));
                                iZzb = i3;
                                g.a aVar3 = new g.a();
                                aVar3.c(iZzb);
                                aVar3.b(strZzf);
                                lVar.a(aVar3.a(), arrayList);
                            }
                        }
                        i5 = i6;
                        zzuVarA = zzuVar;
                        r2 = 1;
                    } else {
                        iZzb = zzb.zzb(bundleZzl, "BillingClient");
                        strZzf = zzb.zzf(bundleZzl, "BillingClient");
                        if (iZzb != 0) {
                            zzb.zzj("BillingClient", "getSkuDetails() failed for queryProductDetailsAsync. Response code: " + iZzb);
                            a0 a0Var3 = this.f1453f;
                            g gVar = w.f1543a;
                            g.a aVar4 = new g.a();
                            aVar4.c(iZzb);
                            aVar4.b(strZzf);
                            a0Var3.c(x.a(23, 7, aVar4.a()));
                        } else {
                            zzb.zzj("BillingClient", "getSkuDetails() returned a bundle with neither an error nor a product detail list for queryProductDetailsAsync.");
                            a0 a0Var4 = this.f1453f;
                            g.a aVar5 = new g.a();
                            aVar5.c(6);
                            aVar5.b(strZzf);
                            a0Var4.c(x.a(45, 7, aVar5.a()));
                        }
                    }
                } catch (Exception e3) {
                    e = e3;
                    zzb.zzk("BillingClient", "queryProductDetailsAsync got a remote exception (try to reconnect).", e);
                    this.f1453f.c(x.a(43, i2, w.f1550h));
                    strZzf = "An internal error occurred.";
                    iZzb = i3;
                    g.a aVar32 = new g.a();
                    aVar32.c(iZzb);
                    aVar32.b(strZzf);
                    lVar.a(aVar32.a(), arrayList);
                }
            } catch (Exception e4) {
                e = e4;
                i2 = 7;
                i3 = 6;
            }
        }
        iZzb = 4;
        g.a aVar322 = new g.a();
        aVar322.c(iZzb);
        aVar322.b(strZzf);
        lVar.a(aVar322.a(), arrayList);
    }

    @Override // com.android.billingclient.api.c
    public final void a(a aVar, b bVar) {
        if (!h()) {
            this.f1453f.c(x.a(2, 3, w.f1552j));
            return;
        }
        if (TextUtils.isEmpty(aVar.a())) {
            zzb.zzj("BillingClient", "Please provide a valid purchase token.");
            this.f1453f.c(x.a(26, 3, w.f1549g));
        } else if (!this.f1459l) {
            this.f1453f.c(x.a(27, 3, w.f1544b));
        } else if (w(new d0(this, aVar, bVar, 2), 30000L, new e0(this, bVar, 1), s()) == null) {
            this.f1453f.c(x.a(25, 3, u()));
        }
    }

    @Override // com.android.billingclient.api.c
    public final void b(final h hVar, final i iVar) {
        if (!h()) {
            this.f1453f.c(x.a(2, 4, w.f1552j));
        } else if (w(new d0(this, hVar, iVar, 1), 30000L, new Runnable() { // from class: com.android.billingclient.api.f0
            @Override // java.lang.Runnable
            public final void run() {
                i iVar2 = iVar;
                this.f1485b.o(hVar, iVar2);
            }
        }, s()) == null) {
            this.f1453f.c(x.a(25, 4, u()));
        }
    }

    @Override // com.android.billingclient.api.c
    public final g c(AndroidApplication androidApplication, final f fVar) {
        String str;
        String str2;
        Future futureW;
        boolean z2;
        String str3;
        String str4;
        String str5;
        String str6;
        f.b bVar;
        f.b bVar2;
        String str7;
        boolean z3;
        String str8;
        int i2;
        final int i3;
        if (!h()) {
            a0 a0Var = this.f1453f;
            g gVar = w.f1552j;
            a0Var.c(x.a(2, 2, gVar));
            t(gVar);
            return gVar;
        }
        ArrayList arrayListF = fVar.f();
        zzu zzuVarG = fVar.g();
        r rVar = (r) zzz.zza(arrayListF, null);
        f.b bVar3 = (f.b) zzz.zza(zzuVarG, null);
        if (rVar != null) {
            throw null;
        }
        String strC = bVar3.a().c();
        String strD = bVar3.a().d();
        if (strD.equals("subs") && !this.f1456i) {
            zzb.zzj("BillingClient", "Current client doesn't support subscriptions.");
            a0 a0Var2 = this.f1453f;
            g gVar2 = w.f1554l;
            a0Var2.c(x.a(9, 2, gVar2));
            t(gVar2);
            return gVar2;
        }
        if (fVar.l() && !this.f1458k) {
            zzb.zzj("BillingClient", "Current client doesn't support extra params for buy intent.");
            a0 a0Var3 = this.f1453f;
            g gVar3 = w.f1548f;
            a0Var3.c(x.a(18, 2, gVar3));
            t(gVar3);
            return gVar3;
        }
        if (arrayListF.size() > 1 && !this.f1462o) {
            zzb.zzj("BillingClient", "Current client doesn't support multi-item purchases.");
            a0 a0Var4 = this.f1453f;
            g gVar4 = w.f1555m;
            a0Var4.c(x.a(19, 2, gVar4));
            t(gVar4);
            return gVar4;
        }
        if (!zzuVarG.isEmpty() && !this.f1463p) {
            zzb.zzj("BillingClient", "Current client doesn't support purchases with ProductDetails.");
            a0 a0Var5 = this.f1453f;
            g gVar5 = w.f1557o;
            a0Var5.c(x.a(20, 2, gVar5));
            t(gVar5);
            return gVar5;
        }
        boolean z4 = this.f1458k;
        Handler handler = this.f1450c;
        if (z4) {
            boolean z5 = this.f1459l;
            boolean z6 = this.s;
            final Bundle bundle = new Bundle();
            str = "BUY_INTENT";
            bundle.putString("playBillingLibraryVersion", this.f1449b);
            fVar.c();
            fVar.b();
            if (!TextUtils.isEmpty(null)) {
                bundle.putString("accountId", null);
            }
            if (!TextUtils.isEmpty(null)) {
                bundle.putString("obfuscatedProfileId", null);
            }
            if (!TextUtils.isEmpty(null)) {
                bundle.putStringArrayList("skusToReplace", new ArrayList<>(Arrays.asList(null)));
            }
            fVar.d();
            if (!TextUtils.isEmpty(null)) {
                fVar.d();
                bundle.putString("oldSkuPurchaseToken", null);
            }
            if (!TextUtils.isEmpty(null)) {
                bundle.putString("oldSkuPurchaseId", null);
            }
            fVar.e();
            if (!TextUtils.isEmpty(null)) {
                fVar.e();
                bundle.putString("originalExternalTransactionId", null);
            }
            if (!TextUtils.isEmpty(null)) {
                bundle.putString("paymentsPurchaseParams", null);
            }
            if (z5) {
                z2 = true;
                bundle.putBoolean("enablePendingPurchases", true);
            } else {
                z2 = true;
            }
            if (z6) {
                bundle.putBoolean("enableAlternativeBilling", z2);
            }
            if (arrayListF.isEmpty()) {
                str3 = strD;
                ArrayList<String> arrayList = new ArrayList<>(zzuVarG.size() - 1);
                ArrayList<String> arrayList2 = new ArrayList<>(zzuVarG.size() - 1);
                ArrayList<String> arrayList3 = new ArrayList<>();
                ArrayList<String> arrayList4 = new ArrayList<>();
                str4 = strC;
                ArrayList<String> arrayList5 = new ArrayList<>();
                str5 = "proxyPackageVersion";
                str6 = "BillingClient";
                int i4 = 0;
                while (i4 < zzuVarG.size()) {
                    f.b bVar4 = (f.b) zzuVarG.get(i4);
                    k kVarA = bVar4.a();
                    if (kVarA.h().isEmpty()) {
                        bVar2 = bVar3;
                    } else {
                        bVar2 = bVar3;
                        arrayList3.add(kVarA.h());
                    }
                    arrayList4.add(bVar4.b());
                    if (!TextUtils.isEmpty(kVarA.i())) {
                        arrayList5.add(kVarA.i());
                    }
                    if (i4 > 0) {
                        arrayList.add(((f.b) zzuVarG.get(i4)).a().c());
                        arrayList2.add(((f.b) zzuVarG.get(i4)).a().d());
                    }
                    i4++;
                    bVar3 = bVar2;
                }
                bVar = bVar3;
                bundle.putStringArrayList("SKU_OFFER_ID_TOKEN_LIST", arrayList4);
                if (!arrayList3.isEmpty()) {
                    bundle.putStringArrayList("skuDetailsTokens", arrayList3);
                }
                if (!arrayList5.isEmpty()) {
                    bundle.putStringArrayList("SKU_SERIALIZED_DOCID_LIST", arrayList5);
                }
                if (!arrayList.isEmpty()) {
                    bundle.putStringArrayList("additionalSkus", arrayList);
                    bundle.putStringArrayList("additionalSkuTypes", arrayList2);
                }
            } else {
                ArrayList<String> arrayList6 = new ArrayList<>();
                new ArrayList();
                new ArrayList();
                new ArrayList();
                new ArrayList();
                Iterator it = arrayListF.iterator();
                if (it.hasNext()) {
                    ((r) it.next()).getClass();
                    throw null;
                }
                if (!arrayList6.isEmpty()) {
                    bundle.putStringArrayList("skuDetailsTokens", arrayList6);
                }
                if (arrayListF.size() > 1) {
                    ArrayList<String> arrayList7 = new ArrayList<>(arrayListF.size() - 1);
                    ArrayList<String> arrayList8 = new ArrayList<>(arrayListF.size() - 1);
                    str3 = strD;
                    if (1 < arrayListF.size()) {
                        ((r) arrayListF.get(1)).getClass();
                        throw null;
                    }
                    bundle.putStringArrayList("additionalSkus", arrayList7);
                    bundle.putStringArrayList("additionalSkuTypes", arrayList8);
                } else {
                    str3 = strD;
                }
                str5 = "proxyPackageVersion";
                bVar = bVar3;
                str4 = strC;
                str6 = "BillingClient";
            }
            if (bundle.containsKey("SKU_OFFER_ID_TOKEN_LIST") && !this.f1460m) {
                a0 a0Var6 = this.f1453f;
                g gVar6 = w.f1556n;
                a0Var6.c(x.a(21, 2, gVar6));
                t(gVar6);
                return gVar6;
            }
            if (rVar != null) {
                throw null;
            }
            if (TextUtils.isEmpty(bVar.a().g())) {
                str7 = null;
                z3 = false;
            } else {
                bundle.putString("skuPackageName", bVar.a().g());
                str7 = null;
                z3 = true;
            }
            if (!TextUtils.isEmpty(str7)) {
                bundle.putString("accountName", str7);
            }
            Intent intent = androidApplication.getIntent();
            if (intent == null) {
                str2 = str6;
                zzb.zzj(str2, "Activity's intent is null.");
            } else {
                str2 = str6;
                if (!TextUtils.isEmpty(intent.getStringExtra("PROXY_PACKAGE"))) {
                    String stringExtra = intent.getStringExtra("PROXY_PACKAGE");
                    bundle.putString("proxyPackage", stringExtra);
                    try {
                        str8 = str5;
                        try {
                            bundle.putString(str8, this.f1452e.getPackageManager().getPackageInfo(stringExtra, 0).versionName);
                        } catch (PackageManager.NameNotFoundException unused) {
                            bundle.putString(str8, "package not found");
                        }
                    } catch (PackageManager.NameNotFoundException unused2) {
                        str8 = str5;
                    }
                }
            }
            if (this.f1463p && !zzuVarG.isEmpty()) {
                i2 = 17;
            } else if (this.f1461n && z3) {
                i2 = 15;
            } else if (this.f1459l) {
                i3 = 9;
                final String str9 = str4;
                final String str10 = str3;
                futureW = w(new Callable(i3, str9, str10, fVar, bundle) { // from class: com.android.billingclient.api.g0

                    /* JADX INFO: renamed from: b, reason: collision with root package name */
                    public final /* synthetic */ int f1493b;

                    /* JADX INFO: renamed from: c, reason: collision with root package name */
                    public final /* synthetic */ String f1494c;

                    /* JADX INFO: renamed from: d, reason: collision with root package name */
                    public final /* synthetic */ String f1495d;

                    /* JADX INFO: renamed from: e, reason: collision with root package name */
                    public final /* synthetic */ Bundle f1496e;

                    {
                        this.f1496e = bundle;
                    }

                    @Override // java.util.concurrent.Callable
                    public final Object call() {
                        Bundle bundle2 = this.f1496e;
                        return this.f1492a.z(this.f1493b, this.f1494c, this.f1495d, bundle2);
                    }
                }, 5000L, null, handler);
            } else {
                i2 = 6;
            }
            i3 = i2;
            final String str92 = str4;
            final String str102 = str3;
            futureW = w(new Callable(i3, str92, str102, fVar, bundle) { // from class: com.android.billingclient.api.g0

                /* JADX INFO: renamed from: b, reason: collision with root package name */
                public final /* synthetic */ int f1493b;

                /* JADX INFO: renamed from: c, reason: collision with root package name */
                public final /* synthetic */ String f1494c;

                /* JADX INFO: renamed from: d, reason: collision with root package name */
                public final /* synthetic */ String f1495d;

                /* JADX INFO: renamed from: e, reason: collision with root package name */
                public final /* synthetic */ Bundle f1496e;

                {
                    this.f1496e = bundle;
                }

                @Override // java.util.concurrent.Callable
                public final Object call() {
                    Bundle bundle2 = this.f1496e;
                    return this.f1492a.z(this.f1493b, this.f1494c, this.f1495d, bundle2);
                }
            }, 5000L, null, handler);
        } else {
            str = "BUY_INTENT";
            str2 = "BillingClient";
            futureW = w(new d0(this, strC, strD, 3), 5000L, null, handler);
        }
        try {
            Bundle bundle2 = (Bundle) futureW.get(5000L, TimeUnit.MILLISECONDS);
            int iZzb = zzb.zzb(bundle2, str2);
            String strZzf = zzb.zzf(bundle2, str2);
            if (iZzb == 0) {
                Intent intent2 = new Intent(androidApplication, (Class<?>) ProxyBillingActivity.class);
                String str11 = str;
                intent2.putExtra(str11, (PendingIntent) bundle2.getParcelable(str11));
                androidApplication.startActivity(intent2);
                return w.f1551i;
            }
            zzb.zzj(str2, "Unable to buy item, Error response code: " + iZzb);
            g.a aVar = new g.a();
            aVar.c(iZzb);
            aVar.b(strZzf);
            g gVarA = aVar.a();
            this.f1453f.c(x.a(3, 2, gVarA));
            t(gVarA);
            return gVarA;
        } catch (CancellationException e2) {
            e = e2;
            zzb.zzk(str2, "Time out while launching billing flow. Try to reconnect", e);
            a0 a0Var7 = this.f1453f;
            g gVar7 = w.f1553k;
            a0Var7.c(x.a(4, 2, gVar7));
            t(gVar7);
            return gVar7;
        } catch (TimeoutException e3) {
            e = e3;
            zzb.zzk(str2, "Time out while launching billing flow. Try to reconnect", e);
            a0 a0Var72 = this.f1453f;
            g gVar72 = w.f1553k;
            a0Var72.c(x.a(4, 2, gVar72));
            t(gVar72);
            return gVar72;
        } catch (Exception e4) {
            zzb.zzk(str2, "Exception while launching billing flow. Try to reconnect", e4);
            a0 a0Var8 = this.f1453f;
            g gVar8 = w.f1552j;
            a0Var8.c(x.a(5, 2, gVar8));
            t(gVar8);
            return gVar8;
        }
    }

    @Override // com.android.billingclient.api.c
    public final void e(p pVar, l lVar) {
        if (!h()) {
            a0 a0Var = this.f1453f;
            g gVar = w.f1552j;
            a0Var.c(x.a(2, 7, gVar));
            lVar.a(gVar, new ArrayList());
            return;
        }
        if (this.f1463p) {
            if (w(new d0(this, pVar, lVar, 0), 30000L, new e0(this, lVar, 0), s()) == null) {
                g gVarU = u();
                this.f1453f.c(x.a(25, 7, gVarU));
                lVar.a(gVarU, new ArrayList());
                return;
            }
            return;
        }
        zzb.zzj("BillingClient", "Querying product details is not supported.");
        a0 a0Var2 = this.f1453f;
        g gVar2 = w.f1557o;
        a0Var2.c(x.a(20, 7, gVar2));
        lVar.a(gVar2, new ArrayList());
    }

    @Override // com.android.billingclient.api.c
    public final void f(q qVar, n nVar) {
        String strA = qVar.a();
        if (!h()) {
            a0 a0Var = this.f1453f;
            g gVar = w.f1552j;
            a0Var.c(x.a(2, 9, gVar));
            nVar.a(gVar, zzu.zzk());
            return;
        }
        if (TextUtils.isEmpty(strA)) {
            zzb.zzj("BillingClient", "Please provide a valid product type.");
            a0 a0Var2 = this.f1453f;
            g gVar2 = w.f1547e;
            a0Var2.c(x.a(50, 9, gVar2));
            nVar.a(gVar2, zzu.zzk());
            return;
        }
        if (w(new h0(this, strA, nVar), 30000L, new e0(this, nVar, 2), s()) == null) {
            g gVarU = u();
            this.f1453f.c(x.a(25, 9, gVarU));
            nVar.a(gVarU, zzu.zzk());
        }
    }

    @Override // com.android.billingclient.api.c
    public final void g(e eVar) {
        if (h()) {
            zzb.zzi("BillingClient", "Service connection is valid. No need to re-initialize.");
            a0 a0Var = this.f1453f;
            zzfe zzfeVarZzv = zzff.zzv();
            zzfeVarZzv.zzj(6);
            a0Var.f((zzff) zzfeVarZzv.zzc());
            eVar.a(w.f1551i);
            return;
        }
        int i2 = 1;
        if (this.f1448a == 1) {
            zzb.zzj("BillingClient", "Client is already in the process of connecting to billing service.");
            a0 a0Var2 = this.f1453f;
            g gVar = w.f1546d;
            a0Var2.c(x.a(37, 6, gVar));
            eVar.a(gVar);
            return;
        }
        if (this.f1448a == 3) {
            zzb.zzj("BillingClient", "Client was already closed and can't be reused. Please create another instance.");
            a0 a0Var3 = this.f1453f;
            g gVar2 = w.f1552j;
            a0Var3.c(x.a(38, 6, gVar2));
            eVar.a(gVar2);
            return;
        }
        this.f1448a = 1;
        this.f1451d.i();
        zzb.zzi("BillingClient", "Starting in-app billing setup.");
        this.f1455h = new v(this, eVar);
        Intent intent = new Intent("com.android.vending.billing.InAppBillingService.BIND");
        intent.setPackage("com.android.vending");
        List<ResolveInfo> listQueryIntentServices = this.f1452e.getPackageManager().queryIntentServices(intent, 0);
        if (listQueryIntentServices == null || listQueryIntentServices.isEmpty()) {
            i2 = 41;
        } else {
            ServiceInfo serviceInfo = listQueryIntentServices.get(0).serviceInfo;
            if (serviceInfo != null) {
                String str = serviceInfo.packageName;
                String str2 = serviceInfo.name;
                if (!"com.android.vending".equals(str) || str2 == null) {
                    zzb.zzj("BillingClient", "The device doesn't have valid Play Store.");
                    i2 = 40;
                } else {
                    ComponentName componentName = new ComponentName(str, str2);
                    Intent intent2 = new Intent(intent);
                    intent2.setComponent(componentName);
                    intent2.putExtra("playBillingLibraryVersion", this.f1449b);
                    if (this.f1452e.bindService(intent2, this.f1455h, 1)) {
                        zzb.zzi("BillingClient", "Service was bonded successfully.");
                        return;
                    } else {
                        zzb.zzj("BillingClient", "Connection to Billing service is blocked.");
                        i2 = 39;
                    }
                }
            }
        }
        this.f1448a = 0;
        zzb.zzi("BillingClient", "Billing service unavailable on device.");
        a0 a0Var4 = this.f1453f;
        g gVar3 = w.f1545c;
        a0Var4.c(x.a(i2, 6, gVar3));
        eVar.a(gVar3);
    }

    public final boolean h() {
        return (this.f1448a != 2 || this.f1454g == null || this.f1455h == null) ? false : true;
    }

    final /* synthetic */ void m(b bVar) {
        this.f1453f.c(x.a(24, 3, w.f1553k));
    }

    final /* synthetic */ void n(g gVar) {
        if (this.f1451d.g() != null) {
            ((e0.f) this.f1451d.g()).u(gVar, null);
        } else {
            this.f1451d.e();
            zzb.zzj("BillingClient", "No valid listener is set in BroadcastManager");
        }
    }

    final /* synthetic */ void o(h hVar, i iVar) {
        this.f1453f.c(x.a(24, 4, w.f1553k));
    }

    final /* synthetic */ void p(l lVar) {
        a0 a0Var = this.f1453f;
        g gVar = w.f1553k;
        a0Var.c(x.a(24, 7, gVar));
        lVar.a(gVar, new ArrayList());
    }

    final /* synthetic */ void q(n nVar) {
        a0 a0Var = this.f1453f;
        g gVar = w.f1553k;
        a0Var.c(x.a(24, 9, gVar));
        nVar.a(gVar, zzu.zzk());
    }

    final /* synthetic */ Bundle z(int i2, String str, String str2, Bundle bundle) {
        return this.f1454g.zzg(i2, this.f1452e.getPackageName(), str, str2, null, bundle);
    }

    d(Activity activity, e0.f fVar) {
        String strV = v();
        this.f1448a = 0;
        this.f1450c = new Handler(Looper.getMainLooper());
        this.f1457j = 0;
        this.f1449b = strV;
        this.f1452e = activity.getApplicationContext();
        zzfl zzflVarZzv = zzfm.zzv();
        zzflVarZzv.zzj(strV);
        zzflVarZzv.zzi(this.f1452e.getPackageName());
        this.f1453f = new a0(this.f1452e, (zzfm) zzflVarZzv.zzc());
        if (fVar == null) {
            zzb.zzj("BillingClient", "Billing client should have a valid listener but the provided is null.");
        }
        this.f1451d = new a0(this.f1452e, fVar, this.f1453f);
        this.s = false;
    }
}
