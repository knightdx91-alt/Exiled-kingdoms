package com.google.android.gms.common.images;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@SafeParcelable.Class(creator = "WebImageCreator")
public final class WebImage extends AbstractSafeParcelable {
    public static final Parcelable.Creator<WebImage> CREATOR = new zae();

    @SafeParcelable.VersionField(id = 1)
    private final int zale;

    @SafeParcelable.Field(getter = "getWidth", id = 3)
    private final int zand;

    @SafeParcelable.Field(getter = "getHeight", id = 4)
    private final int zane;

    @SafeParcelable.Field(getter = "getUrl", id = 2)
    private final Uri zanf;

    @SafeParcelable.Constructor
    WebImage(@SafeParcelable.Param(id = 1) int i2, @SafeParcelable.Param(id = 2) Uri uri, @SafeParcelable.Param(id = 3) int i3, @SafeParcelable.Param(id = 4) int i4) {
        this.zale = i2;
        this.zanf = uri;
        this.zand = i3;
        this.zane = i4;
    }

    private static Uri zaa(JSONObject jSONObject) {
        if (jSONObject.has(ImagesContract.URL)) {
            try {
                return Uri.parse(jSONObject.getString(ImagesContract.URL));
            } catch (JSONException unused) {
            }
        }
        return null;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && (obj instanceof WebImage)) {
            WebImage webImage = (WebImage) obj;
            if (Objects.equal(this.zanf, webImage.zanf) && this.zand == webImage.zand && this.zane == webImage.zane) {
                return true;
            }
        }
        return false;
    }

    public final int getHeight() {
        return this.zane;
    }

    public final Uri getUrl() {
        return this.zanf;
    }

    public final int getWidth() {
        return this.zand;
    }

    public final int hashCode() {
        return Objects.hashCode(this.zanf, Integer.valueOf(this.zand), Integer.valueOf(this.zane));
    }

    @KeepForSdk
    public final JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(ImagesContract.URL, this.zanf.toString());
            jSONObject.put("width", this.zand);
            jSONObject.put("height", this.zane);
        } catch (JSONException unused) {
        }
        return jSONObject;
    }

    public final String toString() {
        Locale locale = Locale.US;
        return "Image " + this.zand + "x" + this.zane + " " + this.zanf.toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zale);
        SafeParcelWriter.writeParcelable(parcel, 2, getUrl(), i2, false);
        SafeParcelWriter.writeInt(parcel, 3, getWidth());
        SafeParcelWriter.writeInt(parcel, 4, getHeight());
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public WebImage(Uri uri, int i2, int i3) {
        this(1, uri, i2, i3);
        if (uri == null) {
            throw new IllegalArgumentException("url cannot be null");
        }
        if (i2 < 0 || i3 < 0) {
            throw new IllegalArgumentException("width and height must not be negative");
        }
    }

    public WebImage(Uri uri) {
        this(uri, 0, 0);
    }

    @KeepForSdk
    public WebImage(JSONObject jSONObject) {
        this(zaa(jSONObject), jSONObject.optInt("width", 0), jSONObject.optInt("height", 0));
    }
}
