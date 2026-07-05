package com.google.android.gms.internal.play_billing;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.android.billingclient.api.g;
import com.android.billingclient.api.j;
import com.android.billingclient.api.m;
import com.google.android.gms.games.GamesStatusCodes;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.0.1 */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzb {
    public static final int zza = Runtime.getRuntime().availableProcessors();

    public static int zza(Intent intent, String str) {
        if (intent != null) {
            return zzl(intent.getExtras(), "ProxyBillingActivity");
        }
        zzj("ProxyBillingActivity", "Got null intent!");
        return 0;
    }

    public static int zzb(Bundle bundle, String str) {
        if (bundle == null) {
            zzj(str, "Unexpected null bundle received!");
            return 6;
        }
        Object obj = bundle.get("RESPONSE_CODE");
        if (obj == null) {
            zzi(str, "getResponseCodeFromBundle() got null response code, assuming OK");
            return 0;
        }
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        zzj(str, "Unexpected type for bundle response code: ".concat(obj.getClass().getName()));
        return 6;
    }

    public static Bundle zzc(boolean z2, boolean z3, boolean z4, boolean z5, String str) {
        Bundle bundle = new Bundle();
        bundle.putString("playBillingLibraryVersion", str);
        if (z2) {
            bundle.putBoolean("enablePendingPurchases", true);
        }
        return bundle;
    }

    public static g zzd(Intent intent, String str) {
        if (intent != null) {
            g.a aVarC = g.c();
            aVarC.c(zzb(intent.getExtras(), str));
            aVarC.b(zzf(intent.getExtras(), str));
            return aVarC.a();
        }
        zzj("BillingHelper", "Got null intent!");
        g.a aVarC2 = g.c();
        aVarC2.c(6);
        aVarC2.b("An internal error occurred.");
        return aVarC2.a();
    }

    public static j zze(Bundle bundle, String str) {
        if (bundle == null) {
            return new j();
        }
        zzl(bundle, "BillingClient");
        bundle.getString("IN_APP_MESSAGE_PURCHASE_TOKEN");
        return new j();
    }

    public static String zzf(Bundle bundle, String str) {
        if (bundle == null) {
            zzj(str, "Unexpected null bundle received!");
            return "";
        }
        Object obj = bundle.get("DEBUG_MESSAGE");
        if (obj == null) {
            zzi(str, "getDebugMessageFromBundle() got null response code, assuming OK");
            return "";
        }
        if (obj instanceof String) {
            return (String) obj;
        }
        zzj(str, "Unexpected type for debug message: ".concat(obj.getClass().getName()));
        return "";
    }

    public static String zzg(int i2) {
        return zza.zza(i2).toString();
    }

    public static List zzh(Bundle bundle) {
        ArrayList<String> stringArrayList = bundle.getStringArrayList("INAPP_PURCHASE_DATA_LIST");
        ArrayList<String> stringArrayList2 = bundle.getStringArrayList("INAPP_DATA_SIGNATURE_LIST");
        ArrayList arrayList = new ArrayList();
        if (stringArrayList == null || stringArrayList2 == null) {
            m mVarZzm = zzm(bundle.getString("INAPP_PURCHASE_DATA"), bundle.getString("INAPP_DATA_SIGNATURE"));
            if (mVarZzm == null) {
                zzi("BillingHelper", "Couldn't find single purchase data as well.");
                return null;
            }
            arrayList.add(mVarZzm);
        } else {
            zzi("BillingHelper", "Found purchase list of " + stringArrayList.size() + " items");
            for (int i2 = 0; i2 < stringArrayList.size() && i2 < stringArrayList2.size(); i2++) {
                m mVarZzm2 = zzm(stringArrayList.get(i2), stringArrayList2.get(i2));
                if (mVarZzm2 != null) {
                    arrayList.add(mVarZzm2);
                }
            }
        }
        return arrayList;
    }

    public static void zzi(String str, String str2) {
        if (Log.isLoggable(str, 2)) {
            if (str2.isEmpty()) {
                Log.v(str, str2);
                return;
            }
            int i2 = 40000;
            while (!str2.isEmpty() && i2 > 0) {
                int iMin = Math.min(str2.length(), Math.min(GamesStatusCodes.STATUS_SNAPSHOT_NOT_FOUND, i2));
                Log.v(str, str2.substring(0, iMin));
                str2 = str2.substring(iMin);
                i2 -= iMin;
            }
        }
    }

    public static void zzj(String str, String str2) {
        if (Log.isLoggable(str, 5)) {
            Log.w(str, str2);
        }
    }

    public static void zzk(String str, String str2, Throwable th) {
        if (Log.isLoggable(str, 5)) {
            Log.w(str, str2, th);
        }
    }

    private static int zzl(Bundle bundle, String str) {
        if (bundle != null) {
            return bundle.getInt("IN_APP_MESSAGE_RESPONSE_CODE", 0);
        }
        zzj(str, "Unexpected null bundle received!");
        return 0;
    }

    private static m zzm(String str, String str2) {
        if (str == null || str2 == null) {
            zzi("BillingHelper", "Received a null purchase data.");
            return null;
        }
        try {
            return new m(str, str2);
        } catch (JSONException e2) {
            zzj("BillingHelper", "Got JSONException while parsing purchase data: ".concat(e2.toString()));
            return null;
        }
    }
}
