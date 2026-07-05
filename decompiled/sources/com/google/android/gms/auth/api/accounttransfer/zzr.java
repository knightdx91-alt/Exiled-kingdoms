package com.google.android.gms.auth.api.accounttransfer;

import a.a;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.server.response.FastJsonResponse;
import com.google.android.gms.internal.auth.zzaz;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@SafeParcelable.Class(creator = "AuthenticatorAnnotatedDataCreator")
public class zzr extends zzaz {
    public static final Parcelable.Creator<zzr> CREATOR = new zzs();
    private static final HashMap<String, FastJsonResponse.Field<?, ?>> zzaz;

    @SafeParcelable.Field(getter = "getPackageName", id = 4)
    private String mPackageName;

    @SafeParcelable.Indicator
    private final Set<Integer> zzba;

    @SafeParcelable.Field(getter = "getInfo", id = 2)
    private zzt zzbk;

    @SafeParcelable.Field(getter = "getSignature", id = 3)
    private String zzbl;

    @SafeParcelable.Field(getter = "getId", id = 5)
    private String zzbm;

    @SafeParcelable.VersionField(id = 1)
    private final int zzv;

    static {
        HashMap<String, FastJsonResponse.Field<?, ?>> map = new HashMap<>();
        zzaz = map;
        map.put("authenticatorInfo", FastJsonResponse.Field.forConcreteType("authenticatorInfo", 2, zzt.class));
        map.put("signature", FastJsonResponse.Field.forString("signature", 3));
        map.put("package", FastJsonResponse.Field.forString("package", 4));
    }

    public zzr() {
        this.zzba = new HashSet(3);
        this.zzv = 1;
    }

    @Override // com.google.android.gms.common.server.response.FastJsonResponse
    public <T extends FastJsonResponse> void addConcreteTypeInternal(FastJsonResponse.Field<?, ?> field, String str, T t2) {
        int safeParcelableFieldId = field.getSafeParcelableFieldId();
        if (safeParcelableFieldId != 2) {
            throw new IllegalArgumentException(String.format("Field with id=%d is not a known custom type. Found %s", Integer.valueOf(safeParcelableFieldId), t2.getClass().getCanonicalName()));
        }
        this.zzbk = (zzt) t2;
        this.zzba.add(Integer.valueOf(safeParcelableFieldId));
    }

    @Override // com.google.android.gms.common.server.response.FastJsonResponse
    public /* synthetic */ Map getFieldMappings() {
        return zzaz;
    }

    @Override // com.google.android.gms.common.server.response.FastJsonResponse
    protected Object getFieldValue(FastJsonResponse.Field field) {
        int safeParcelableFieldId = field.getSafeParcelableFieldId();
        if (safeParcelableFieldId == 1) {
            return Integer.valueOf(this.zzv);
        }
        if (safeParcelableFieldId == 2) {
            return this.zzbk;
        }
        if (safeParcelableFieldId == 3) {
            return this.zzbl;
        }
        if (safeParcelableFieldId == 4) {
            return this.mPackageName;
        }
        throw new IllegalStateException(a.g(37, field.getSafeParcelableFieldId(), "Unknown SafeParcelable id="));
    }

    @Override // com.google.android.gms.common.server.response.FastJsonResponse
    protected boolean isFieldSet(FastJsonResponse.Field field) {
        return this.zzba.contains(Integer.valueOf(field.getSafeParcelableFieldId()));
    }

    @Override // com.google.android.gms.common.server.response.FastJsonResponse
    protected void setStringInternal(FastJsonResponse.Field<?, ?> field, String str, String str2) {
        int safeParcelableFieldId = field.getSafeParcelableFieldId();
        if (safeParcelableFieldId == 3) {
            this.zzbl = str2;
        } else {
            if (safeParcelableFieldId != 4) {
                throw new IllegalArgumentException(String.format("Field with id=%d is not known to be a string.", Integer.valueOf(safeParcelableFieldId)));
            }
            this.mPackageName = str2;
        }
        this.zzba.add(Integer.valueOf(safeParcelableFieldId));
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        Set<Integer> set = this.zzba;
        if (set.contains(1)) {
            SafeParcelWriter.writeInt(parcel, 1, this.zzv);
        }
        if (set.contains(2)) {
            SafeParcelWriter.writeParcelable(parcel, 2, this.zzbk, i2, true);
        }
        if (set.contains(3)) {
            SafeParcelWriter.writeString(parcel, 3, this.zzbl, true);
        }
        if (set.contains(4)) {
            SafeParcelWriter.writeString(parcel, 4, this.mPackageName, true);
        }
        if (set.contains(5)) {
            SafeParcelWriter.writeString(parcel, 5, this.zzbm, true);
        }
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    @SafeParcelable.Constructor
    zzr(@SafeParcelable.Indicator Set<Integer> set, @SafeParcelable.Param(id = 1) int i2, @SafeParcelable.Param(id = 2) zzt zztVar, @SafeParcelable.Param(id = 3) String str, @SafeParcelable.Param(id = 4) String str2, @SafeParcelable.Param(id = 5) String str3) {
        this.zzba = set;
        this.zzv = i2;
        this.zzbk = zztVar;
        this.zzbl = str;
        this.mPackageName = str2;
        this.zzbm = str3;
    }
}
