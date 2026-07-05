package com.google.android.gms.common.util.concurrent;

import a.a;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@KeepForSdk
public class NumberedThreadFactory implements ThreadFactory {
    private final int priority;
    private final ThreadFactory zzhp;
    private final String zzhq;
    private final AtomicInteger zzhr;

    @KeepForSdk
    public NumberedThreadFactory(String str) {
        this(str, 0);
    }

    @Override // java.util.concurrent.ThreadFactory
    public Thread newThread(Runnable runnable) {
        Thread threadNewThread = this.zzhp.newThread(new zza(runnable, 0));
        String str = this.zzhq;
        int andIncrement = this.zzhr.getAndIncrement();
        StringBuilder sb = new StringBuilder(a.e(13, str));
        sb.append(str);
        sb.append("[");
        sb.append(andIncrement);
        sb.append("]");
        threadNewThread.setName(sb.toString());
        return threadNewThread;
    }

    private NumberedThreadFactory(String str, int i2) {
        this.zzhr = new AtomicInteger();
        this.zzhp = Executors.defaultThreadFactory();
        this.zzhq = (String) Preconditions.checkNotNull(str, "Name must not be null");
        this.priority = 0;
    }
}
