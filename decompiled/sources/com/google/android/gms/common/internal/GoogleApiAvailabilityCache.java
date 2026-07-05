package com.google.android.gms.common.internal;

import android.content.Context;
import android.util.SparseIntArray;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.Api;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class GoogleApiAvailabilityCache {
    private final SparseIntArray zaor;
    private GoogleApiAvailabilityLight zaos;

    public GoogleApiAvailabilityCache() {
        this(GoogleApiAvailability.getInstance());
    }

    public void flush() {
        this.zaor.clear();
    }

    public int getClientAvailability(Context context, Api.Client client) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(client);
        int iIsGooglePlayServicesAvailable = 0;
        if (!client.requiresGooglePlayServices()) {
            return 0;
        }
        int minApkVersion = client.getMinApkVersion();
        int i2 = this.zaor.get(minApkVersion, -1);
        if (i2 != -1) {
            return i2;
        }
        int i3 = 0;
        while (true) {
            if (i3 >= this.zaor.size()) {
                iIsGooglePlayServicesAvailable = i2;
                break;
            }
            int iKeyAt = this.zaor.keyAt(i3);
            if (iKeyAt > minApkVersion && this.zaor.get(iKeyAt) == 0) {
                break;
            }
            i3++;
        }
        if (iIsGooglePlayServicesAvailable == -1) {
            iIsGooglePlayServicesAvailable = this.zaos.isGooglePlayServicesAvailable(context, minApkVersion);
        }
        this.zaor.put(minApkVersion, iIsGooglePlayServicesAvailable);
        return iIsGooglePlayServicesAvailable;
    }

    public GoogleApiAvailabilityCache(GoogleApiAvailabilityLight googleApiAvailabilityLight) {
        this.zaor = new SparseIntArray();
        Preconditions.checkNotNull(googleApiAvailabilityLight);
        this.zaos = googleApiAvailabilityLight;
    }
}
