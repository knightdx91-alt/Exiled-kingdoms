package com.google.android.gms.common.stats;

import a.a;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@KeepForSdk
public abstract class StatsEvent extends AbstractSafeParcelable implements ReflectedParcelable {

    @KeepForSdk
    public interface Types {

        @KeepForSdk
        public static final int EVENT_TYPE_ACQUIRE_WAKE_LOCK = 7;

        @KeepForSdk
        public static final int EVENT_TYPE_RELEASE_WAKE_LOCK = 8;
    }

    public abstract int getEventType();

    public abstract long getTimeMillis();

    public String toString() {
        long timeMillis = getTimeMillis();
        int eventType = getEventType();
        long jZzu = zzu();
        String strZzv = zzv();
        StringBuilder sb = new StringBuilder(a.e(53, strZzv));
        sb.append(timeMillis);
        sb.append("\t");
        sb.append(eventType);
        sb.append("\t");
        sb.append(jZzu);
        sb.append(strZzv);
        return sb.toString();
    }

    public abstract long zzu();

    public abstract String zzv();
}
