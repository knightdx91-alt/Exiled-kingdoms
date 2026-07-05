package com.google.android.datatransport.runtime.scheduling.persistence;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final /* synthetic */ class e implements SQLiteEventStore.Function {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f2121a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ SQLiteEventStore f2122b;

    public /* synthetic */ e(SQLiteEventStore sQLiteEventStore, int i2) {
        this.f2121a = i2;
        this.f2122b = sQLiteEventStore;
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore.Function
    public final Object apply(Object obj) {
        switch (this.f2121a) {
            case 0:
                return this.f2122b.lambda$resetClientMetrics$23((SQLiteDatabase) obj);
            case 1:
                return this.f2122b.lambda$cleanUp$11((Cursor) obj);
            default:
                return this.f2122b.lambda$recordFailure$3((Cursor) obj);
        }
    }
}
