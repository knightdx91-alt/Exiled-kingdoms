package com.google.android.gms.drive.query;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.metadata.SortableMetadataField;
import com.google.android.gms.drive.query.internal.zzf;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@SafeParcelable.Class(creator = "SortOrderCreator")
@SafeParcelable.Reserved({CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT})
public class SortOrder extends AbstractSafeParcelable {
    public static final Parcelable.Creator<SortOrder> CREATOR = new zzc();

    @SafeParcelable.Field(id = 1)
    private final List<zzf> zzlg;

    @SafeParcelable.Field(defaultValue = "false", id = 2)
    private final boolean zzlh;

    public static class Builder {
        private final List<zzf> zzlg = new ArrayList();
        private boolean zzlh = false;

        public Builder addSortAscending(SortableMetadataField sortableMetadataField) {
            this.zzlg.add(new zzf(sortableMetadataField.getName(), true));
            return this;
        }

        public Builder addSortDescending(SortableMetadataField sortableMetadataField) {
            this.zzlg.add(new zzf(sortableMetadataField.getName(), false));
            return this;
        }

        public SortOrder build() {
            return new SortOrder(this.zzlg, false);
        }
    }

    @SafeParcelable.Constructor
    SortOrder(@SafeParcelable.Param(id = 1) List<zzf> list, @SafeParcelable.Param(id = 2) boolean z2) {
        this.zzlg = list;
        this.zzlh = z2;
    }

    public String toString() {
        Locale locale = Locale.US;
        return "SortOrder[" + TextUtils.join(",", this.zzlg) + ", " + this.zzlh + "]";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 1, this.zzlg, false);
        SafeParcelWriter.writeBoolean(parcel, 2, this.zzlh);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
