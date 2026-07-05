package com.google.android.gms.auth.api.credentials;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@SafeParcelable.Class(creator = "CredentialPickerConfigCreator")
public final class CredentialPickerConfig extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<CredentialPickerConfig> CREATOR = new zze();

    @SafeParcelable.Field(getter = "shouldShowCancelButton", id = 2)
    private final boolean mShowCancelButton;

    @SafeParcelable.Field(id = CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT)
    private final int zzu;

    @SafeParcelable.Field(getter = "shouldShowAddAccountButton", id = 1)
    private final boolean zzv;

    @SafeParcelable.Field(getter = "isForNewAccount", id = 3)
    @Deprecated
    private final boolean zzw;

    @SafeParcelable.Field(getter = "getPromptInternalId", id = 4)
    private final int zzx;

    public static class Builder {
        private boolean zzv = false;
        private boolean mShowCancelButton = true;
        private int zzy = 1;

        public CredentialPickerConfig build() {
            return new CredentialPickerConfig(this);
        }

        @Deprecated
        public Builder setForNewAccount(boolean z2) {
            this.zzy = z2 ? 3 : 1;
            return this;
        }

        public Builder setPrompt(int i2) {
            this.zzy = i2;
            return this;
        }

        public Builder setShowAddAccountButton(boolean z2) {
            this.zzv = z2;
            return this;
        }

        public Builder setShowCancelButton(boolean z2) {
            this.mShowCancelButton = z2;
            return this;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Prompt {
        public static final int CONTINUE = 1;
        public static final int SIGN_IN = 2;
        public static final int SIGN_UP = 3;
    }

    @SafeParcelable.Constructor
    CredentialPickerConfig(@SafeParcelable.Param(id = CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT) int i2, @SafeParcelable.Param(id = 1) boolean z2, @SafeParcelable.Param(id = 2) boolean z3, @SafeParcelable.Param(id = 3) boolean z4, @SafeParcelable.Param(id = 4) int i3) {
        this.zzu = i2;
        this.zzv = z2;
        this.mShowCancelButton = z3;
        if (i2 < 2) {
            this.zzw = z4;
            this.zzx = z4 ? 3 : 1;
        } else {
            this.zzw = i3 == 3;
            this.zzx = i3;
        }
    }

    @Deprecated
    public final boolean isForNewAccount() {
        return this.zzx == 3;
    }

    public final boolean shouldShowAddAccountButton() {
        return this.zzv;
    }

    public final boolean shouldShowCancelButton() {
        return this.mShowCancelButton;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBoolean(parcel, 1, shouldShowAddAccountButton());
        SafeParcelWriter.writeBoolean(parcel, 2, shouldShowCancelButton());
        SafeParcelWriter.writeBoolean(parcel, 3, isForNewAccount());
        SafeParcelWriter.writeInt(parcel, 4, this.zzx);
        SafeParcelWriter.writeInt(parcel, CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT, this.zzu);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    private CredentialPickerConfig(Builder builder) {
        this(2, builder.zzv, builder.mShowCancelButton, false, builder.zzy);
    }
}
