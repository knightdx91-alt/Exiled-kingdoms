package com.google.android.datatransport.runtime.util;

import a.a;
import android.util.SparseArray;
import com.google.android.datatransport.Priority;
import java.util.HashMap;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class PriorityMapping {
    private static HashMap<Priority, Integer> PRIORITY_INT_MAP;
    private static SparseArray<Priority> PRIORITY_MAP = new SparseArray<>();

    static {
        HashMap<Priority, Integer> map = new HashMap<>();
        PRIORITY_INT_MAP = map;
        map.put(Priority.DEFAULT, 0);
        PRIORITY_INT_MAP.put(Priority.VERY_LOW, 1);
        PRIORITY_INT_MAP.put(Priority.HIGHEST, 2);
        for (Priority priority : PRIORITY_INT_MAP.keySet()) {
            PRIORITY_MAP.append(PRIORITY_INT_MAP.get(priority).intValue(), priority);
        }
    }

    public static int toInt(Priority priority) {
        Integer num = PRIORITY_INT_MAP.get(priority);
        if (num != null) {
            return num.intValue();
        }
        throw new IllegalStateException("PriorityMapping is missing known Priority value " + priority);
    }

    public static Priority valueOf(int i2) {
        Priority priority = PRIORITY_MAP.get(i2);
        if (priority != null) {
            return priority;
        }
        throw new IllegalArgumentException(a.h(i2, "Unknown Priority for value "));
    }
}
