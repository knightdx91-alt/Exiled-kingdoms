package com.google.android.gms.common.server.response;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.server.response.FastJsonResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@ShowFirstParty
@SafeParcelable.Class(creator = "FieldMappingDictionaryCreator")
public final class zak extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zak> CREATOR = new zan();

    @SafeParcelable.VersionField(id = 1)
    private final int zale;
    private final HashMap<String, Map<String, FastJsonResponse.Field<?, ?>>> zaqu;

    @SafeParcelable.Field(getter = "getSerializedDictionary", id = 2)
    private final ArrayList<zal> zaqv;

    @SafeParcelable.Field(getter = "getRootClassName", id = 3)
    private final String zaqw;

    @SafeParcelable.Constructor
    zak(@SafeParcelable.Param(id = 1) int i2, @SafeParcelable.Param(id = 2) ArrayList<zal> arrayList, @SafeParcelable.Param(id = 3) String str) {
        this.zale = i2;
        this.zaqv = null;
        HashMap<String, Map<String, FastJsonResponse.Field<?, ?>>> map = new HashMap<>();
        int size = arrayList.size();
        for (int i3 = 0; i3 < size; i3++) {
            zal zalVar = arrayList.get(i3);
            String str2 = zalVar.className;
            HashMap map2 = new HashMap();
            int size2 = zalVar.zaqx.size();
            for (int i4 = 0; i4 < size2; i4++) {
                zam zamVar = zalVar.zaqx.get(i4);
                map2.put(zamVar.zaqy, zamVar.zaqz);
            }
            map.put(str2, map2);
        }
        this.zaqu = map;
        this.zaqw = (String) Preconditions.checkNotNull(str);
        zacr();
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        for (String str : this.zaqu.keySet()) {
            sb.append(str);
            sb.append(":\n");
            Map<String, FastJsonResponse.Field<?, ?>> map = this.zaqu.get(str);
            for (String str2 : map.keySet()) {
                sb.append("  ");
                sb.append(str2);
                sb.append(": ");
                sb.append(map.get(str2));
            }
        }
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zale);
        ArrayList arrayList = new ArrayList();
        for (String str : this.zaqu.keySet()) {
            arrayList.add(new zal(str, this.zaqu.get(str)));
        }
        SafeParcelWriter.writeTypedList(parcel, 2, arrayList, false);
        SafeParcelWriter.writeString(parcel, 3, this.zaqw, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public final void zaa(Class<? extends FastJsonResponse> cls, Map<String, FastJsonResponse.Field<?, ?>> map) {
        this.zaqu.put(cls.getCanonicalName(), map);
    }

    public final void zacr() {
        Iterator<String> it = this.zaqu.keySet().iterator();
        while (it.hasNext()) {
            Map<String, FastJsonResponse.Field<?, ?>> map = this.zaqu.get(it.next());
            Iterator<String> it2 = map.keySet().iterator();
            while (it2.hasNext()) {
                map.get(it2.next()).zaa(this);
            }
        }
    }

    public final void zacs() {
        for (String str : this.zaqu.keySet()) {
            Map<String, FastJsonResponse.Field<?, ?>> map = this.zaqu.get(str);
            HashMap map2 = new HashMap();
            for (String str2 : map.keySet()) {
                map2.put(str2, map.get(str2).zacl());
            }
            this.zaqu.put(str, map2);
        }
    }

    public final String zact() {
        return this.zaqw;
    }

    public final Map<String, FastJsonResponse.Field<?, ?>> zai(String str) {
        return this.zaqu.get(str);
    }

    public final boolean zaa(Class<? extends FastJsonResponse> cls) {
        return this.zaqu.containsKey(cls.getCanonicalName());
    }

    public zak(Class<? extends FastJsonResponse> cls) {
        this.zale = 1;
        this.zaqv = null;
        this.zaqu = new HashMap<>();
        this.zaqw = cls.getCanonicalName();
    }
}
