package com.google.android.gms.drive.metadata.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.data.BitmapTeleporter;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.internal.drive.zzhp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@SafeParcelable.Class(creator = "MetadataBundleCreator")
@SafeParcelable.Reserved({1})
public final class MetadataBundle extends AbstractSafeParcelable implements ReflectedParcelable {

    @SafeParcelable.Field(id = 2)
    private final Bundle zzir;
    private static final GmsLogger zzbx = new GmsLogger("MetadataBundle", "");
    public static final Parcelable.Creator<MetadataBundle> CREATOR = new zzj();

    @SafeParcelable.Constructor
    MetadataBundle(@SafeParcelable.Param(id = 2) Bundle bundle) {
        Bundle bundle2 = (Bundle) Preconditions.checkNotNull(bundle);
        this.zzir = bundle2;
        bundle2.setClassLoader(MetadataBundle.class.getClassLoader());
        ArrayList arrayList = new ArrayList();
        for (String str : bundle2.keySet()) {
            if (zzf.zzd(str) == null) {
                arrayList.add(str);
                zzbx.wfmt("MetadataBundle", "Ignored unknown metadata field in bundle: %s", str);
            }
        }
        int size = arrayList.size();
        int i2 = 0;
        while (i2 < size) {
            Object obj = arrayList.get(i2);
            i2++;
            this.zzir.remove((String) obj);
        }
    }

    public static <T> MetadataBundle zza(MetadataField<T> metadataField, T t2) {
        MetadataBundle metadataBundleZzaw = zzaw();
        metadataBundleZzaw.zzb(metadataField, t2);
        return metadataBundleZzaw;
    }

    public static MetadataBundle zzaw() {
        return new MetadataBundle(new Bundle());
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != MetadataBundle.class) {
            return false;
        }
        MetadataBundle metadataBundle = (MetadataBundle) obj;
        Set<String> setKeySet = this.zzir.keySet();
        if (!setKeySet.equals(metadataBundle.zzir.keySet())) {
            return false;
        }
        for (String str : setKeySet) {
            if (!Objects.equal(this.zzir.get(str), metadataBundle.zzir.get(str))) {
                return false;
            }
        }
        return true;
    }

    public final int hashCode() {
        Iterator<String> it = this.zzir.keySet().iterator();
        int iHashCode = 1;
        while (it.hasNext()) {
            iHashCode = (iHashCode * 31) + this.zzir.get(it.next()).hashCode();
        }
        return iHashCode;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBundle(parcel, 2, this.zzir, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public final MetadataBundle zzax() {
        return new MetadataBundle(new Bundle(this.zzir));
    }

    public final Set<MetadataField<?>> zzay() {
        HashSet hashSet = new HashSet();
        Iterator<String> it = this.zzir.keySet().iterator();
        while (it.hasNext()) {
            hashSet.add(zzf.zzd(it.next()));
        }
        return hashSet;
    }

    public final <T> void zzb(MetadataField<T> metadataField, T t2) {
        if (zzf.zzd(metadataField.getName()) == null) {
            String strValueOf = String.valueOf(metadataField.getName());
            throw new IllegalArgumentException(strValueOf.length() != 0 ? "Unregistered field: ".concat(strValueOf) : new String("Unregistered field: "));
        }
        metadataField.zza(t2, this.zzir);
    }

    public final <T> T zzc(MetadataField<T> metadataField) {
        T t2 = (T) zza(metadataField);
        this.zzir.remove(metadataField.getName());
        return t2;
    }

    public final boolean zzd(MetadataField<?> metadataField) {
        return this.zzir.containsKey(metadataField.getName());
    }

    public final <T> T zza(MetadataField<T> metadataField) {
        return metadataField.zza(this.zzir);
    }

    public final void zza(Context context) {
        BitmapTeleporter bitmapTeleporter = (BitmapTeleporter) zza(zzhp.zzka);
        if (bitmapTeleporter != null) {
            bitmapTeleporter.setTempDir(context.getCacheDir());
        }
    }
}
