package com.google.android.datatransport.runtime.scheduling.persistence;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore;
import java.util.HashMap;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final /* synthetic */ class f implements SQLiteEventStore.Function, SQLiteEventStore.Producer {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f2123a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f2124b;

    public /* synthetic */ f(Object obj, int i2) {
        this.f2123a = i2;
        this.f2124b = obj;
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore.Function
    public Object apply(Object obj) {
        return SQLiteEventStore.lambda$loadMetadata$16((HashMap) this.f2124b, (Cursor) obj);
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore.Producer
    public Object produce() {
        switch (this.f2123a) {
            case 1:
                return SQLiteEventStore.lambda$ensureBeginTransaction$24((SQLiteDatabase) this.f2124b);
            default:
                return ((SchemaManager) this.f2124b).getWritableDatabase();
        }
    }
}
