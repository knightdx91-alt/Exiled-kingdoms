package com.google.android.gms.auth.api.proxy;

import android.app.PendingIntent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.google.android.gms.common.annotation.KeepForSdkWithMembers;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@KeepForSdkWithMembers
@SafeParcelable.Class(creator = "ProxyResponseCreator")
public class ProxyResponse extends AbstractSafeParcelable {
    public static final Parcelable.Creator<ProxyResponse> CREATOR = new zzb();
    public static final int STATUS_CODE_NO_CONNECTION = -1;

    @SafeParcelable.Field(id = 5)
    public final byte[] body;

    @SafeParcelable.Field(id = 1)
    public final int googlePlayServicesStatusCode;

    @SafeParcelable.Field(id = 2)
    public final PendingIntent recoveryAction;

    @SafeParcelable.Field(id = 3)
    public final int statusCode;

    @SafeParcelable.VersionField(id = CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT)
    private final int versionCode;

    @SafeParcelable.Field(id = 4)
    private final Bundle zzby;

    @SafeParcelable.Constructor
    ProxyResponse(@SafeParcelable.Param(id = CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT) int i2, @SafeParcelable.Param(id = 1) int i3, @SafeParcelable.Param(id = 2) PendingIntent pendingIntent, @SafeParcelable.Param(id = 3) int i4, @SafeParcelable.Param(id = 4) Bundle bundle, @SafeParcelable.Param(id = 5) byte[] bArr) {
        this.versionCode = i2;
        this.googlePlayServicesStatusCode = i3;
        this.statusCode = i4;
        this.zzby = bundle;
        this.body = bArr;
        this.recoveryAction = pendingIntent;
    }

    public static ProxyResponse createErrorProxyResponse(int i2, PendingIntent pendingIntent, int i3, Map<String, String> map, byte[] bArr) {
        return new ProxyResponse(1, i2, pendingIntent, i3, zza(map), bArr);
    }

    private static Bundle zza(Map<String, String> map) {
        Bundle bundle = new Bundle();
        if (map == null) {
            return bundle;
        }
        for (Map.Entry<String, String> entry : map.entrySet()) {
            bundle.putString(entry.getKey(), entry.getValue());
        }
        return bundle;
    }

    public Map<String, String> getHeaders() {
        if (this.zzby == null) {
            return Collections.emptyMap();
        }
        HashMap map = new HashMap();
        for (String str : this.zzby.keySet()) {
            map.put(str, this.zzby.getString(str));
        }
        return map;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.googlePlayServicesStatusCode);
        SafeParcelWriter.writeParcelable(parcel, 2, this.recoveryAction, i2, false);
        SafeParcelWriter.writeInt(parcel, 3, this.statusCode);
        SafeParcelWriter.writeBundle(parcel, 4, this.zzby, false);
        SafeParcelWriter.writeByteArray(parcel, 5, this.body, false);
        SafeParcelWriter.writeInt(parcel, CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT, this.versionCode);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public ProxyResponse(int i2, PendingIntent pendingIntent, int i3, Bundle bundle, byte[] bArr) {
        this(1, i2, pendingIntent, i3, bundle, bArr);
    }

    private ProxyResponse(int i2, Bundle bundle, byte[] bArr) {
        this(1, 0, null, i2, bundle, bArr);
    }

    public ProxyResponse(int i2, Map<String, String> map, byte[] bArr) {
        this(i2, zza(map), bArr);
    }
}
