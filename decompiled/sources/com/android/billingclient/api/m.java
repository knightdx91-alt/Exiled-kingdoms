package com.android.billingclient.api;

import android.text.TextUtils;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.0.1 */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f1525a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f1526b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final JSONObject f1527c;

    public m(String str, String str2) {
        this.f1525a = str;
        this.f1526b = str2;
        this.f1527c = new JSONObject(str);
    }

    public final String a() {
        String strOptString = this.f1527c.optString("orderId");
        if (TextUtils.isEmpty(strOptString)) {
            return null;
        }
        return strOptString;
    }

    public final String b() {
        return this.f1525a;
    }

    public final ArrayList c() {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = this.f1527c;
        if (jSONObject.has("productIds")) {
            JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("productIds");
            if (jSONArrayOptJSONArray != null) {
                for (int i2 = 0; i2 < jSONArrayOptJSONArray.length(); i2++) {
                    arrayList.add(jSONArrayOptJSONArray.optString(i2));
                }
            }
        } else if (jSONObject.has("productId")) {
            arrayList.add(jSONObject.optString("productId"));
        }
        return arrayList;
    }

    public final int d() {
        return this.f1527c.optInt("purchaseState", 1) != 4 ? 1 : 2;
    }

    public final long e() {
        return this.f1527c.optLong("purchaseTime");
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof m)) {
            return false;
        }
        m mVar = (m) obj;
        return TextUtils.equals(this.f1525a, mVar.f1525a) && TextUtils.equals(this.f1526b, mVar.f1526b);
    }

    public final String f() {
        JSONObject jSONObject = this.f1527c;
        return jSONObject.optString("token", jSONObject.optString("purchaseToken"));
    }

    public final String g() {
        return this.f1526b;
    }

    public final boolean h() {
        return this.f1527c.optBoolean("acknowledged", true);
    }

    public final int hashCode() {
        return this.f1525a.hashCode();
    }

    public final String toString() {
        return "Purchase. Json: ".concat(String.valueOf(this.f1525a));
    }
}
