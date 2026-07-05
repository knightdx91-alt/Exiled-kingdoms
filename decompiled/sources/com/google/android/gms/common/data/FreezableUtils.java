package com.google.android.gms.common.data;

import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class FreezableUtils {
    /* JADX WARN: Multi-variable type inference failed */
    public static <T, E extends Freezable<T>> ArrayList<T> freeze(ArrayList<E> arrayList) {
        ArrayList<T> arrayList2 = (ArrayList<T>) new ArrayList(arrayList.size());
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            arrayList2.add(arrayList.get(i2).freeze());
        }
        return arrayList2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T, E extends Freezable<T>> ArrayList<T> freezeIterable(Iterable<E> iterable) {
        ArrayList<T> arrayList = (ArrayList<T>) new ArrayList();
        Iterator<E> it = iterable.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().freeze());
        }
        return arrayList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T, E extends Freezable<T>> ArrayList<T> freeze(E[] eArr) {
        ArrayList<T> arrayList = (ArrayList<T>) new ArrayList(eArr.length);
        for (E e2 : eArr) {
            arrayList.add(e2.freeze());
        }
        return arrayList;
    }
}
