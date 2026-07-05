package com.android.billingclient.api;

import android.text.TextUtils;
import com.google.android.gms.internal.play_billing.zzu;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.0.1 */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f1502a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final JSONObject f1503b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final String f1504c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final String f1505d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final String f1506e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final String f1507f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final String f1508g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final String f1509h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private final ArrayList f1510i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private final ArrayList f1511j;

    /* JADX INFO: compiled from: com.android.billingclient:billing@@6.0.1 */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final String f1512a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final long f1513b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final String f1514c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final String f1515d;

        a(JSONObject jSONObject) throws JSONException {
            this.f1512a = jSONObject.optString("formattedPrice");
            this.f1513b = jSONObject.optLong("priceAmountMicros");
            this.f1514c = jSONObject.optString("priceCurrencyCode");
            this.f1515d = jSONObject.optString("offerIdToken");
            jSONObject.optString("offerId");
            jSONObject.optInt("offerType");
            JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("offerTags");
            ArrayList arrayList = new ArrayList();
            if (jSONArrayOptJSONArray != null) {
                for (int i2 = 0; i2 < jSONArrayOptJSONArray.length(); i2++) {
                    arrayList.add(jSONArrayOptJSONArray.getString(i2));
                }
            }
            zzu.zzj(arrayList);
            if (jSONObject.has("fullPriceMicros")) {
                jSONObject.optLong("fullPriceMicros");
            }
            JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("discountDisplayInfo");
            if (jSONObjectOptJSONObject != null) {
                jSONObjectOptJSONObject.getInt("percentageDiscount");
            }
            JSONObject jSONObjectOptJSONObject2 = jSONObject.optJSONObject("validTimeWindow");
            if (jSONObjectOptJSONObject2 != null) {
                jSONObjectOptJSONObject2.getLong("startTimeMillis");
                jSONObjectOptJSONObject2.getLong("endTimeMillis");
            }
            JSONObject jSONObjectOptJSONObject3 = jSONObject.optJSONObject("limitedQuantityInfo");
            if (jSONObjectOptJSONObject3 == null) {
                return;
            }
            jSONObjectOptJSONObject3.getInt("maximumQuantity");
            jSONObjectOptJSONObject3.getInt("remainingQuantity");
        }

        public final String a() {
            return this.f1512a;
        }

        public final long b() {
            return this.f1513b;
        }

        public final String c() {
            return this.f1514c;
        }

        public final String d() {
            return this.f1515d;
        }
    }

    /* JADX INFO: compiled from: com.android.billingclient:billing@@6.0.1 */
    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final String f1516a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final long f1517b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final String f1518c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final String f1519d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final int f1520e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private final int f1521f;

        b(JSONObject jSONObject) {
            this.f1519d = jSONObject.optString("billingPeriod");
            this.f1518c = jSONObject.optString("priceCurrencyCode");
            this.f1516a = jSONObject.optString("formattedPrice");
            this.f1517b = jSONObject.optLong("priceAmountMicros");
            this.f1521f = jSONObject.optInt("recurrenceMode");
            this.f1520e = jSONObject.optInt("billingCycleCount");
        }

        public final int a() {
            return this.f1520e;
        }

        public final String b() {
            return this.f1519d;
        }

        public final String c() {
            return this.f1516a;
        }

        public final long d() {
            return this.f1517b;
        }

        public final String e() {
            return this.f1518c;
        }

        public final int f() {
            return this.f1521f;
        }
    }

    /* JADX INFO: compiled from: com.android.billingclient:billing@@6.0.1 */
    public static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final ArrayList f1522a;

        c(JSONArray jSONArray) {
            ArrayList arrayList = new ArrayList();
            if (jSONArray != null) {
                for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                    JSONObject jSONObjectOptJSONObject = jSONArray.optJSONObject(i2);
                    if (jSONObjectOptJSONObject != null) {
                        arrayList.add(new b(jSONObjectOptJSONObject));
                    }
                }
            }
            this.f1522a = arrayList;
        }

        public final ArrayList a() {
            return this.f1522a;
        }
    }

    /* JADX INFO: compiled from: com.android.billingclient:billing@@6.0.1 */
    public static final class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final String f1523a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final c f1524b;

        d(JSONObject jSONObject) throws JSONException {
            jSONObject.optString("basePlanId");
            jSONObject.optString("offerId").getClass();
            this.f1523a = jSONObject.getString("offerIdToken");
            this.f1524b = new c(jSONObject.getJSONArray("pricingPhases"));
            JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("installmentPlanDetails");
            if (jSONObjectOptJSONObject != null) {
                jSONObjectOptJSONObject.getInt("commitmentPaymentsCount");
                jSONObjectOptJSONObject.optInt("subsequentCommitmentPaymentsCount");
            }
            ArrayList arrayList = new ArrayList();
            JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("offerTags");
            if (jSONArrayOptJSONArray != null) {
                for (int i2 = 0; i2 < jSONArrayOptJSONArray.length(); i2++) {
                    arrayList.add(jSONArrayOptJSONArray.getString(i2));
                }
            }
        }

        public final String a() {
            return this.f1523a;
        }

        public final c b() {
            return this.f1524b;
        }
    }

    k(String str) {
        this.f1502a = str;
        JSONObject jSONObject = new JSONObject(str);
        this.f1503b = jSONObject;
        String strOptString = jSONObject.optString("productId");
        this.f1504c = strOptString;
        String strOptString2 = jSONObject.optString("type");
        this.f1505d = strOptString2;
        if (TextUtils.isEmpty(strOptString)) {
            throw new IllegalArgumentException("Product id cannot be empty.");
        }
        if (TextUtils.isEmpty(strOptString2)) {
            throw new IllegalArgumentException("Product type cannot be empty.");
        }
        this.f1506e = jSONObject.optString("title");
        jSONObject.optString("name");
        this.f1507f = jSONObject.optString("description");
        jSONObject.optString("packageDisplayName");
        jSONObject.optString("iconUrl");
        this.f1508g = jSONObject.optString("skuDetailsToken");
        this.f1509h = jSONObject.optString("serializedDocid");
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("subscriptionOfferDetails");
        if (jSONArrayOptJSONArray != null) {
            ArrayList arrayList = new ArrayList();
            for (int i2 = 0; i2 < jSONArrayOptJSONArray.length(); i2++) {
                arrayList.add(new d(jSONArrayOptJSONArray.getJSONObject(i2)));
            }
            this.f1510i = arrayList;
        } else {
            this.f1510i = (strOptString2.equals("subs") || strOptString2.equals("play_pass_subs")) ? new ArrayList() : null;
        }
        JSONObject jSONObjectOptJSONObject = this.f1503b.optJSONObject("oneTimePurchaseOfferDetails");
        JSONArray jSONArrayOptJSONArray2 = this.f1503b.optJSONArray("oneTimePurchaseOfferDetailsList");
        ArrayList arrayList2 = new ArrayList();
        if (jSONArrayOptJSONArray2 != null) {
            for (int i3 = 0; i3 < jSONArrayOptJSONArray2.length(); i3++) {
                arrayList2.add(new a(jSONArrayOptJSONArray2.getJSONObject(i3)));
            }
            this.f1511j = arrayList2;
            return;
        }
        if (jSONObjectOptJSONObject == null) {
            this.f1511j = null;
        } else {
            arrayList2.add(new a(jSONObjectOptJSONObject));
            this.f1511j = arrayList2;
        }
    }

    public final String a() {
        return this.f1507f;
    }

    public final a b() {
        ArrayList arrayList = this.f1511j;
        if (arrayList == null || arrayList.isEmpty()) {
            return null;
        }
        return (a) arrayList.get(0);
    }

    public final String c() {
        return this.f1504c;
    }

    public final String d() {
        return this.f1505d;
    }

    public final ArrayList e() {
        return this.f1510i;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof k) {
            return TextUtils.equals(this.f1502a, ((k) obj).f1502a);
        }
        return false;
    }

    public final String f() {
        return this.f1506e;
    }

    public final String g() {
        return this.f1503b.optString("packageName");
    }

    final String h() {
        return this.f1508g;
    }

    public final int hashCode() {
        return this.f1502a.hashCode();
    }

    public final String i() {
        return this.f1509h;
    }

    public final String toString() {
        return "ProductDetails{jsonString='" + this.f1502a + "', parsedJson=" + this.f1503b.toString() + ", productId='" + this.f1504c + "', productType='" + this.f1505d + "', title='" + this.f1506e + "', productDetailsToken='" + this.f1508g + "', subscriptionOfferDetails=" + String.valueOf(this.f1510i) + "}";
    }
}
