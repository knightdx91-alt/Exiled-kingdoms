package com.google.android.gms.common.api.internal;

import a.a;
import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import com.google.android.gms.common.R;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.StringResourceValueReader;
import com.google.android.gms.common.internal.zzp;
import com.google.android.gms.common.util.VisibleForTesting;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@KeepForSdk
@Deprecated
public final class GoogleServices {
    private static final Object sLock = new Object();
    private static GoogleServices zzax;
    private final String zzay;
    private final Status zzaz;
    private final boolean zzba;
    private final boolean zzbb;

    @VisibleForTesting
    @KeepForSdk
    GoogleServices(Context context) {
        Resources resources = context.getResources();
        int identifier = resources.getIdentifier("google_app_measurement_enable", "integer", resources.getResourcePackageName(R.string.common_google_play_services_unknown_issue));
        boolean z2 = true;
        if (identifier != 0) {
            boolean z3 = resources.getInteger(identifier) != 0;
            this.zzbb = !z3;
            z2 = z3;
        } else {
            this.zzbb = false;
        }
        this.zzba = z2;
        String strZzc = zzp.zzc(context);
        strZzc = strZzc == null ? new StringResourceValueReader(context).getString("google_app_id") : strZzc;
        if (TextUtils.isEmpty(strZzc)) {
            this.zzaz = new Status(10, "Missing google app id value from from string resources with name google_app_id.");
            this.zzay = null;
        } else {
            this.zzay = strZzc;
            this.zzaz = Status.RESULT_SUCCESS;
        }
    }

    @KeepForSdk
    private static GoogleServices checkInitialized(String str) {
        GoogleServices googleServices;
        synchronized (sLock) {
            try {
                googleServices = zzax;
                if (googleServices == null) {
                    StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 34);
                    sb.append("Initialize must be called before ");
                    sb.append(str);
                    sb.append(".");
                    throw new IllegalStateException(sb.toString());
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return googleServices;
    }

    @VisibleForTesting
    @KeepForSdk
    static void clearInstanceForTest() {
        synchronized (sLock) {
            zzax = null;
        }
    }

    @KeepForSdk
    public static String getGoogleAppId() {
        return checkInitialized("getGoogleAppId").zzay;
    }

    @KeepForSdk
    public static Status initialize(Context context, String str, boolean z2) {
        Preconditions.checkNotNull(context, "Context must not be null.");
        Preconditions.checkNotEmpty(str, "App ID must be nonempty.");
        synchronized (sLock) {
            try {
                GoogleServices googleServices = zzax;
                if (googleServices != null) {
                    return googleServices.checkGoogleAppId(str);
                }
                GoogleServices googleServices2 = new GoogleServices(str, z2);
                zzax = googleServices2;
                return googleServices2.zzaz;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @KeepForSdk
    public static boolean isMeasurementEnabled() {
        GoogleServices googleServicesCheckInitialized = checkInitialized("isMeasurementEnabled");
        return googleServicesCheckInitialized.zzaz.isSuccess() && googleServicesCheckInitialized.zzba;
    }

    @KeepForSdk
    public static boolean isMeasurementExplicitlyDisabled() {
        return checkInitialized("isMeasurementExplicitlyDisabled").zzbb;
    }

    @VisibleForTesting
    @KeepForSdk
    final Status checkGoogleAppId(String str) {
        String str2 = this.zzay;
        if (str2 == null || str2.equals(str)) {
            return Status.RESULT_SUCCESS;
        }
        String str3 = this.zzay;
        StringBuilder sb = new StringBuilder(a.e(97, str3));
        sb.append("Initialize was called with two different Google App IDs.  Only the first app ID will be used: '");
        sb.append(str3);
        sb.append("'.");
        return new Status(10, sb.toString());
    }

    @KeepForSdk
    public static Status initialize(Context context) {
        Status status;
        Preconditions.checkNotNull(context, "Context must not be null.");
        synchronized (sLock) {
            try {
                if (zzax == null) {
                    zzax = new GoogleServices(context);
                }
                status = zzax.zzaz;
            } catch (Throwable th) {
                throw th;
            }
        }
        return status;
    }

    @VisibleForTesting
    @KeepForSdk
    GoogleServices(String str, boolean z2) {
        this.zzay = str;
        this.zzaz = Status.RESULT_SUCCESS;
        this.zzba = z2;
        this.zzbb = !z2;
    }
}
