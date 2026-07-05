package com.google.android.gms.common.internal.safeparcel;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Base64Utils;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@VisibleForTesting
@KeepForSdk
public final class SafeParcelableSerializer {
    @KeepForSdk
    public static <T extends SafeParcelable> T deserializeFromBytes(byte[] bArr, Parcelable.Creator<T> creator) {
        Preconditions.checkNotNull(creator);
        Parcel parcelObtain = Parcel.obtain();
        parcelObtain.unmarshall(bArr, 0, bArr.length);
        parcelObtain.setDataPosition(0);
        T tCreateFromParcel = creator.createFromParcel(parcelObtain);
        parcelObtain.recycle();
        return tCreateFromParcel;
    }

    @KeepForSdk
    public static <T extends SafeParcelable> T deserializeFromIntentExtra(Intent intent, String str, Parcelable.Creator<T> creator) {
        byte[] byteArrayExtra = intent.getByteArrayExtra(str);
        if (byteArrayExtra == null) {
            return null;
        }
        return (T) deserializeFromBytes(byteArrayExtra, creator);
    }

    public static <T extends SafeParcelable> T deserializeFromString(String str, Parcelable.Creator<T> creator) {
        return (T) deserializeFromBytes(Base64Utils.decodeUrlSafe(str), creator);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T extends SafeParcelable> ArrayList<T> deserializeIterableFromBundle(Bundle bundle, String str, Parcelable.Creator<T> creator) {
        ArrayList arrayList = (ArrayList) bundle.getSerializable(str);
        if (arrayList == null) {
            return null;
        }
        ArrayList<T> arrayList2 = (ArrayList<T>) new ArrayList(arrayList.size());
        int size = arrayList.size();
        int i2 = 0;
        while (i2 < size) {
            Object obj = arrayList.get(i2);
            i2++;
            arrayList2.add(deserializeFromBytes((byte[]) obj, creator));
        }
        return arrayList2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @KeepForSdk
    public static <T extends SafeParcelable> ArrayList<T> deserializeIterableFromIntentExtra(Intent intent, String str, Parcelable.Creator<T> creator) {
        ArrayList arrayList = (ArrayList) intent.getSerializableExtra(str);
        if (arrayList == null) {
            return null;
        }
        ArrayList<T> arrayList2 = (ArrayList<T>) new ArrayList(arrayList.size());
        int size = arrayList.size();
        int i2 = 0;
        while (i2 < size) {
            Object obj = arrayList.get(i2);
            i2++;
            arrayList2.add(deserializeFromBytes((byte[]) obj, creator));
        }
        return arrayList2;
    }

    public static <T extends SafeParcelable> void serializeIterableToBundle(Iterable<T> iterable, Bundle bundle, String str) {
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = iterable.iterator();
        while (it.hasNext()) {
            arrayList.add(serializeToBytes(it.next()));
        }
        bundle.putSerializable(str, arrayList);
    }

    @KeepForSdk
    public static <T extends SafeParcelable> void serializeIterableToIntentExtra(Iterable<T> iterable, Intent intent, String str) {
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = iterable.iterator();
        while (it.hasNext()) {
            arrayList.add(serializeToBytes(it.next()));
        }
        intent.putExtra(str, arrayList);
    }

    @KeepForSdk
    public static <T extends SafeParcelable> byte[] serializeToBytes(T t2) {
        Parcel parcelObtain = Parcel.obtain();
        t2.writeToParcel(parcelObtain, 0);
        byte[] bArrMarshall = parcelObtain.marshall();
        parcelObtain.recycle();
        return bArrMarshall;
    }

    @KeepForSdk
    public static <T extends SafeParcelable> void serializeToIntentExtra(T t2, Intent intent, String str) {
        intent.putExtra(str, serializeToBytes(t2));
    }

    public static <T extends SafeParcelable> String serializeToString(T t2) {
        return Base64Utils.encodeUrlSafe(serializeToBytes(t2));
    }
}
