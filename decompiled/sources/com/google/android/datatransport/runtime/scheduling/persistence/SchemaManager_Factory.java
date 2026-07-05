package com.google.android.datatransport.runtime.scheduling.persistence;

import android.content.Context;
import com.google.android.datatransport.runtime.dagger.internal.Factory;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class SchemaManager_Factory implements Factory<SchemaManager> {
    private final j0.a<Context> contextProvider;
    private final j0.a<String> dbNameProvider;
    private final j0.a<Integer> schemaVersionProvider;

    public SchemaManager_Factory(j0.a<Context> aVar, j0.a<String> aVar2, j0.a<Integer> aVar3) {
        this.contextProvider = aVar;
        this.dbNameProvider = aVar2;
        this.schemaVersionProvider = aVar3;
    }

    public static SchemaManager_Factory create(j0.a<Context> aVar, j0.a<String> aVar2, j0.a<Integer> aVar3) {
        return new SchemaManager_Factory(aVar, aVar2, aVar3);
    }

    public static SchemaManager newInstance(Context context, String str, int i2) {
        return new SchemaManager(context, str, i2);
    }

    @Override // com.google.android.datatransport.runtime.dagger.internal.Factory, j0.a
    public SchemaManager get() {
        return newInstance(this.contextProvider.get(), this.dbNameProvider.get(), this.schemaVersionProvider.get().intValue());
    }
}
