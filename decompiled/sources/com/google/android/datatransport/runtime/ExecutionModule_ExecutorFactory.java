package com.google.android.datatransport.runtime;

import com.google.android.datatransport.runtime.dagger.internal.Factory;
import com.google.android.datatransport.runtime.dagger.internal.Preconditions;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class ExecutionModule_ExecutorFactory implements Factory<Executor> {

    private static final class InstanceHolder {
        private static final ExecutionModule_ExecutorFactory INSTANCE = new ExecutionModule_ExecutorFactory();

        private InstanceHolder() {
        }
    }

    public static ExecutionModule_ExecutorFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static Executor executor() {
        return (Executor) Preconditions.checkNotNull(ExecutionModule.executor(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // com.google.android.datatransport.runtime.dagger.internal.Factory, j0.a
    public Executor get() {
        return executor();
    }
}
