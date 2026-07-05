package com.google.android.datatransport.runtime;

import com.google.android.datatransport.runtime.dagger.Module;
import com.google.android.datatransport.runtime.dagger.Provides;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@Module
abstract class ExecutionModule {
    ExecutionModule() {
    }

    @Provides
    static Executor executor() {
        return new SafeLoggingExecutor(Executors.newSingleThreadExecutor());
    }
}
