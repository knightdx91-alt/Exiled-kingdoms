package com.android.billingclient.api;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.0.1 */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class s implements ThreadFactory {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final ThreadFactory f1536a = Executors.defaultThreadFactory();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final AtomicInteger f1537b = new AtomicInteger(1);

    s() {
    }

    @Override // java.util.concurrent.ThreadFactory
    public final Thread newThread(Runnable runnable) {
        Thread threadNewThread = this.f1536a.newThread(runnable);
        threadNewThread.setName("PlayBillingLibrary-" + this.f1537b.getAndIncrement());
        return threadNewThread;
    }
}
