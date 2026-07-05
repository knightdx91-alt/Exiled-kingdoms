package com.google.android.gms.games.internal;

import android.os.Bundle;
import android.os.Parcelable;
import android.util.SparseArray;
import java.util.Arrays;
import java.util.Set;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzc {
    public static int zza(Bundle bundle) {
        int size;
        int iHashCode;
        int i2;
        int iHashCode2;
        int iHashCode3;
        if (bundle == null || (size = bundle.size()) == 0) {
            return 0;
        }
        String[] strArr = (String[]) bundle.keySet().toArray(new String[size]);
        Arrays.sort(strArr);
        int iHashCode4 = 1;
        for (String str : strArr) {
            iHashCode4 *= 31;
            Object obj = bundle.get(str);
            if (obj != null) {
                if (obj instanceof Bundle) {
                    iHashCode = zza((Bundle) obj);
                } else if (obj instanceof byte[]) {
                    iHashCode = Arrays.hashCode((byte[]) obj);
                } else if (obj instanceof char[]) {
                    iHashCode = Arrays.hashCode((char[]) obj);
                } else if (obj instanceof short[]) {
                    iHashCode = Arrays.hashCode((short[]) obj);
                } else if (obj instanceof float[]) {
                    iHashCode = Arrays.hashCode((float[]) obj);
                } else if (obj instanceof CharSequence[]) {
                    iHashCode4 += Arrays.hashCode((CharSequence[]) obj);
                } else {
                    if (obj instanceof Object[]) {
                        i2 = 1;
                        for (Object obj2 : (Object[]) obj) {
                            i2 *= 31;
                            if (obj2 instanceof Bundle) {
                                iHashCode3 = zza((Bundle) obj2);
                            } else if (obj2 != null) {
                                iHashCode3 = obj2.hashCode();
                            }
                            i2 = iHashCode3 + i2;
                        }
                    } else if (obj instanceof SparseArray) {
                        SparseArray sparseArray = (SparseArray) obj;
                        int size2 = sparseArray.size();
                        i2 = 1;
                        for (int i3 = 0; i3 < size2; i3++) {
                            int iKeyAt = (sparseArray.keyAt(i3) + (i2 * 31)) * 31;
                            Object objValueAt = sparseArray.valueAt(i3);
                            if (objValueAt instanceof Bundle) {
                                iHashCode2 = zza((Bundle) objValueAt);
                            } else if (objValueAt != null) {
                                iHashCode2 = objValueAt.hashCode();
                            } else {
                                i2 = iKeyAt;
                            }
                            i2 = iHashCode2 + iKeyAt;
                        }
                    } else {
                        iHashCode = obj.hashCode();
                    }
                    iHashCode4 += i2;
                }
                iHashCode4 = iHashCode + iHashCode4;
            }
        }
        return iHashCode4;
    }

    public static boolean zza(Bundle bundle, Bundle bundle2) {
        if (bundle == bundle2) {
            return true;
        }
        if (bundle == null || bundle2 == null || bundle.size() != bundle2.size()) {
            return false;
        }
        Set<String> setKeySet = bundle.keySet();
        if (!setKeySet.equals(bundle2.keySet())) {
            return false;
        }
        for (String str : setKeySet) {
            Object obj = bundle.get(str);
            Object obj2 = bundle2.get(str);
            if (obj == null) {
                if (obj2 != null) {
                    return false;
                }
            } else if (obj instanceof Bundle) {
                if (!(obj2 instanceof Bundle) || !zza((Bundle) obj, (Bundle) obj2)) {
                    return false;
                }
            } else if (obj instanceof byte[]) {
                if (!(obj2 instanceof byte[]) || !Arrays.equals((byte[]) obj, (byte[]) obj2)) {
                    return false;
                }
            } else if (obj instanceof char[]) {
                if (!(obj2 instanceof char[]) || !Arrays.equals((char[]) obj, (char[]) obj2)) {
                    return false;
                }
            } else if (obj instanceof short[]) {
                if (!(obj2 instanceof short[]) || !Arrays.equals((short[]) obj, (short[]) obj2)) {
                    return false;
                }
            } else if (obj instanceof float[]) {
                if (!(obj2 instanceof float[]) || !Arrays.equals((float[]) obj, (float[]) obj2)) {
                    return false;
                }
            } else if (obj instanceof CharSequence[]) {
                if (!(obj2 instanceof CharSequence[]) || !Arrays.equals((CharSequence[]) obj, (CharSequence[]) obj2)) {
                    return false;
                }
            } else {
                if (obj instanceof Object[]) {
                    if (obj2 instanceof Object[]) {
                        Parcelable[] parcelableArr = (Parcelable[]) obj;
                        Parcelable[] parcelableArr2 = (Parcelable[]) obj2;
                        if (parcelableArr == parcelableArr2) {
                            continue;
                        } else {
                            int length = parcelableArr.length;
                            if (parcelableArr2.length == length) {
                                for (int i2 = 0; i2 < length; i2++) {
                                    Parcelable parcelable = parcelableArr[i2];
                                    Parcelable parcelable2 = parcelableArr2[i2];
                                    if (parcelable == null) {
                                        if (parcelable2 == null) {
                                        }
                                    } else if (parcelable instanceof Bundle) {
                                        if ((parcelable2 instanceof Bundle) && zza((Bundle) parcelable, (Bundle) parcelable2)) {
                                        }
                                    } else if (parcelable.equals(parcelable2)) {
                                    }
                                }
                            }
                        }
                    }
                    return false;
                }
                if (obj instanceof SparseArray) {
                    if (obj2 instanceof SparseArray) {
                        SparseArray sparseArray = (SparseArray) obj;
                        SparseArray sparseArray2 = (SparseArray) obj2;
                        if (sparseArray == sparseArray2) {
                            continue;
                        } else {
                            int size = sparseArray.size();
                            if (sparseArray2.size() == size) {
                                for (int i3 = 0; i3 < size; i3++) {
                                    if (sparseArray.keyAt(i3) == sparseArray2.keyAt(i3)) {
                                        Object objValueAt = sparseArray.valueAt(i3);
                                        Object objValueAt2 = sparseArray2.valueAt(i3);
                                        if (objValueAt == null) {
                                            if (objValueAt2 == null) {
                                            }
                                        } else if (objValueAt instanceof Bundle) {
                                            if ((objValueAt2 instanceof Bundle) && zza((Bundle) objValueAt, (Bundle) objValueAt2)) {
                                            }
                                        } else if (objValueAt.equals(objValueAt2)) {
                                        }
                                    }
                                }
                            }
                        }
                    }
                    return false;
                }
                if (!obj.equals(obj2)) {
                    return false;
                }
            }
        }
        return true;
    }
}
