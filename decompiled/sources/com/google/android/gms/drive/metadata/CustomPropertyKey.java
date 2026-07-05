package com.google.android.gms.drive.metadata;

import a.a;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.regex.Pattern;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@SafeParcelable.Class(creator = "CustomPropertyKeyCreator")
@SafeParcelable.Reserved({1})
public class CustomPropertyKey extends AbstractSafeParcelable {
    public static final int PRIVATE = 1;
    public static final int PUBLIC = 0;

    @SafeParcelable.Field(id = 3)
    private final int visibility;

    @SafeParcelable.Field(id = 2)
    private final String zzij;
    public static final Parcelable.Creator<CustomPropertyKey> CREATOR = new zzc();
    private static final Pattern zzik = Pattern.compile("[\\w.!@$%^&*()/-]+");

    @SafeParcelable.Constructor
    public CustomPropertyKey(@SafeParcelable.Param(id = 2) String str, @SafeParcelable.Param(id = 3) int i2) {
        Preconditions.checkNotNull(str, "key");
        Preconditions.checkArgument(zzik.matcher(str).matches(), "key name characters must be alphanumeric or one of .!@$%^&*()-_/");
        boolean z2 = true;
        if (i2 != 0 && i2 != 1) {
            z2 = false;
        }
        Preconditions.checkArgument(z2, "visibility must be either PUBLIC or PRIVATE");
        this.zzij = str;
        this.visibility = i2;
    }

    public static CustomPropertyKey fromJson(JSONObject jSONObject) {
        return new CustomPropertyKey(jSONObject.getString("key"), jSONObject.getInt("visibility"));
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj != null && obj.getClass() == getClass()) {
            CustomPropertyKey customPropertyKey = (CustomPropertyKey) obj;
            if (customPropertyKey.getKey().equals(this.zzij) && customPropertyKey.getVisibility() == this.visibility) {
                return true;
            }
        }
        return false;
    }

    public String getKey() {
        return this.zzij;
    }

    public int getVisibility() {
        return this.visibility;
    }

    public int hashCode() {
        String str = this.zzij;
        int i2 = this.visibility;
        StringBuilder sb = new StringBuilder(a.e(11, str));
        sb.append(str);
        sb.append(i2);
        return sb.toString().hashCode();
    }

    public JSONObject toJson() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("key", getKey());
        jSONObject.put("visibility", getVisibility());
        return jSONObject;
    }

    public String toString() {
        String str = this.zzij;
        int i2 = this.visibility;
        StringBuilder sb = new StringBuilder(a.e(31, str));
        sb.append("CustomPropertyKey(");
        sb.append(str);
        sb.append(",");
        sb.append(i2);
        sb.append(")");
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zzij, false);
        SafeParcelWriter.writeInt(parcel, 3, this.visibility);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
