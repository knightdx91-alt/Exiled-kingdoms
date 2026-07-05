package com.google.android.datatransport.runtime.scheduling.persistence;

import android.content.Context;
import com.google.android.datatransport.runtime.dagger.internal.Factory;
import com.google.android.datatransport.runtime.dagger.internal.Preconditions;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class EventStoreModule_PackageNameFactory implements Factory<String> {
    private final j0.a<Context> contextProvider;

    public EventStoreModule_PackageNameFactory(j0.a<Context> aVar) {
        this.contextProvider = aVar;
    }

    public static EventStoreModule_PackageNameFactory create(j0.a<Context> aVar) {
        return new EventStoreModule_PackageNameFactory(aVar);
    }

    public static String packageName(Context context) {
        return (String) Preconditions.checkNotNull(EventStoreModule.packageName(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // com.google.android.datatransport.runtime.dagger.internal.Factory, j0.a
    public String get() {
        return packageName(this.contextProvider.get());
    }
}
