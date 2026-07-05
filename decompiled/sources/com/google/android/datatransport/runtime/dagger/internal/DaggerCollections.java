package com.google.android.datatransport.runtime.dagger.internal;

import com.google.android.gms.common.api.Api;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class DaggerCollections {
    private static final int MAX_POWER_OF_TWO = 1073741824;

    private DaggerCollections() {
    }

    private static int calculateInitialCapacity(int i2) {
        return i2 < 3 ? i2 + 1 : i2 < MAX_POWER_OF_TWO ? (int) ((i2 / 0.75f) + 1.0f) : Api.BaseClientBuilder.API_PRIORITY_OTHER;
    }

    public static boolean hasDuplicates(List<?> list) {
        if (list.size() < 2) {
            return false;
        }
        return list.size() != new HashSet(list).size();
    }

    static <T> HashSet<T> newHashSetWithExpectedSize(int i2) {
        return new HashSet<>(calculateInitialCapacity(i2));
    }

    public static <K, V> LinkedHashMap<K, V> newLinkedHashMapWithExpectedSize(int i2) {
        return new LinkedHashMap<>(calculateInitialCapacity(i2));
    }

    public static <T> List<T> presizedList(int i2) {
        return i2 == 0 ? Collections.emptyList() : new ArrayList(i2);
    }
}
