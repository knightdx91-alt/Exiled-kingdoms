package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@SafeParcelable.Class(creator = "SignInButtonConfigCreator")
public class SignInButtonConfig extends AbstractSafeParcelable {
    public static final Parcelable.Creator<SignInButtonConfig> CREATOR = new zao();

    @SafeParcelable.VersionField(id = 1)
    private final int zale;

    @SafeParcelable.Field(getter = "getScopes", id = 4)
    @Deprecated
    private final Scope[] zanx;

    @SafeParcelable.Field(getter = "getButtonSize", id = 2)
    private final int zapc;

    @SafeParcelable.Field(getter = "getColorScheme", id = 3)
    private final int zapd;

    @SafeParcelable.Constructor
    SignInButtonConfig(@SafeParcelable.Param(id = 1) int i2, @SafeParcelable.Param(id = 2) int i3, @SafeParcelable.Param(id = 3) int i4, @SafeParcelable.Param(id = 4) Scope[] scopeArr) {
        this.zale = i2;
        this.zapc = i3;
        this.zapd = i4;
        this.zanx = scopeArr;
    }

    public int getButtonSize() {
        return this.zapc;
    }

    public int getColorScheme() {
        return this.zapd;
    }

    @Deprecated
    public Scope[] getScopes() {
        return this.zanx;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zale);
        SafeParcelWriter.writeInt(parcel, 2, getButtonSize());
        SafeParcelWriter.writeInt(parcel, 3, getColorScheme());
        SafeParcelWriter.writeTypedArray(parcel, 4, getScopes(), i2, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public SignInButtonConfig(int i2, int i3, Scope[] scopeArr) {
        this(1, i2, i3, null);
    }
}
