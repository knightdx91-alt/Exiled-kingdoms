package com.google.android.datatransport.runtime.scheduling.persistence;

import android.database.sqlite.SQLiteDatabase;
import com.google.android.datatransport.runtime.TransportContext;
import com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final /* synthetic */ class d implements SQLiteEventStore.Function {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f2118a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ long f2119b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Object f2120c;

    public /* synthetic */ d(TransportContext transportContext, long j2) {
        this.f2119b = j2;
        this.f2120c = transportContext;
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore.Function
    public final Object apply(Object obj) {
        switch (this.f2118a) {
            case 0:
                return SQLiteEventStore.lambda$recordNextCallTime$7(this.f2119b, (TransportContext) this.f2120c, (SQLiteDatabase) obj);
            default:
                return ((SQLiteEventStore) this.f2120c).lambda$cleanUp$12(this.f2119b, (SQLiteDatabase) obj);
        }
    }

    public /* synthetic */ d(SQLiteEventStore sQLiteEventStore, long j2) {
        this.f2120c = sQLiteEventStore;
        this.f2119b = j2;
    }
}
