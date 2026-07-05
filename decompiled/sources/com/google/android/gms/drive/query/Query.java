package com.google.android.gms.drive.query;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.drive.DriveSpace;
import com.google.android.gms.drive.query.internal.zzr;
import com.google.android.gms.drive.query.internal.zzt;
import com.google.android.gms.drive.query.internal.zzx;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@SafeParcelable.Class(creator = "QueryCreator")
@SafeParcelable.Reserved({CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT})
public class Query extends AbstractSafeParcelable {
    public static final Parcelable.Creator<Query> CREATOR = new zzb();

    @SafeParcelable.Field(id = 7)
    private final List<DriveSpace> zzbw;

    @SafeParcelable.Field(id = 1)
    private final zzr zzkw;

    @SafeParcelable.Field(id = 3)
    private final String zzkx;

    @SafeParcelable.Field(id = 4)
    private final SortOrder zzky;

    @SafeParcelable.Field(id = 5)
    final List<String> zzkz;

    @SafeParcelable.Field(id = 6)
    final boolean zzla;

    @SafeParcelable.Field(id = 8)
    final boolean zzlb;

    @VisibleForTesting
    public static class Builder {
        private String zzkx;
        private SortOrder zzky;
        private List<String> zzkz;
        private boolean zzla;
        private boolean zzlb;
        private final List<Filter> zzlc;
        private Set<DriveSpace> zzld;

        public Builder() {
            this.zzlc = new ArrayList();
            this.zzkz = Collections.emptyList();
            this.zzld = Collections.emptySet();
        }

        public Builder addFilter(Filter filter) {
            Preconditions.checkNotNull(filter, "Filter may not be null.");
            if (!(filter instanceof zzt)) {
                this.zzlc.add(filter);
            }
            return this;
        }

        public Query build() {
            return new Query(new zzr(zzx.zzmf, this.zzlc), this.zzkx, this.zzky, this.zzkz, this.zzla, this.zzld, this.zzlb);
        }

        @Deprecated
        public Builder setPageToken(String str) {
            this.zzkx = str;
            return this;
        }

        public Builder setSortOrder(SortOrder sortOrder) {
            this.zzky = sortOrder;
            return this;
        }

        public Builder(Query query) {
            ArrayList arrayList = new ArrayList();
            this.zzlc = arrayList;
            this.zzkz = Collections.emptyList();
            this.zzld = Collections.emptySet();
            arrayList.add(query.getFilter());
            this.zzkx = query.getPageToken();
            this.zzky = query.getSortOrder();
            this.zzkz = query.zzkz;
            this.zzla = query.zzla;
            query.zzba();
            this.zzld = query.zzba();
            this.zzlb = query.zzlb;
        }
    }

    @SafeParcelable.Constructor
    Query(@SafeParcelable.Param(id = 1) zzr zzrVar, @SafeParcelable.Param(id = 3) String str, @SafeParcelable.Param(id = 4) SortOrder sortOrder, @SafeParcelable.Param(id = 5) List<String> list, @SafeParcelable.Param(id = 6) boolean z2, @SafeParcelable.Param(id = 7) List<DriveSpace> list2, @SafeParcelable.Param(id = 8) boolean z3) {
        this.zzkw = zzrVar;
        this.zzkx = str;
        this.zzky = sortOrder;
        this.zzkz = list;
        this.zzla = z2;
        this.zzbw = list2;
        this.zzlb = z3;
    }

    public Filter getFilter() {
        return this.zzkw;
    }

    @Deprecated
    public String getPageToken() {
        return this.zzkx;
    }

    public SortOrder getSortOrder() {
        return this.zzky;
    }

    public String toString() {
        return String.format(Locale.US, "Query[%s,%s,PageToken=%s,Spaces=%s]", this.zzkw, this.zzky, this.zzkx, this.zzbw);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.zzkw, i2, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzkx, false);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zzky, i2, false);
        SafeParcelWriter.writeStringList(parcel, 5, this.zzkz, false);
        SafeParcelWriter.writeBoolean(parcel, 6, this.zzla);
        SafeParcelWriter.writeTypedList(parcel, 7, this.zzbw, false);
        SafeParcelWriter.writeBoolean(parcel, 8, this.zzlb);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public final Set<DriveSpace> zzba() {
        return new HashSet(this.zzbw);
    }

    private Query(zzr zzrVar, String str, SortOrder sortOrder, List<String> list, boolean z2, Set<DriveSpace> set, boolean z3) {
        this(zzrVar, str, sortOrder, list, z2, new ArrayList(set), z3);
    }
}
