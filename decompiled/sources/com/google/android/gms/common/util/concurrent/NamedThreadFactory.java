package com.google.android.gms.common.util.concurrent;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@KeepForSdk
public class NamedThreadFactory implements ThreadFactory {
    private final String name;
    private final int priority;
    private final ThreadFactory zzhp;

    @KeepForSdk
    public NamedThreadFactory(String str) {
        this(str, 0);
    }

    @Override // java.util.concurrent.ThreadFactory
    public Thread newThread(Runnable runnable) {
        Thread threadNewThread = this.zzhp.newThread(new zza(runnable, 0));
        threadNewThread.setName(this.name);
        return threadNewThread;
    }

    private NamedThreadFactory(String str, int i2) {
        this.zzhp = Executors.defaultThreadFactory();
        this.name = (String) Preconditions.checkNotNull(str, "Name must not be null");
        this.priority = 0;
    }
}
