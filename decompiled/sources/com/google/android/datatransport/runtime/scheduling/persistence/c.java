package com.google.android.datatransport.runtime.scheduling.persistence;

import android.database.sqlite.SQLiteDatabase;
import com.google.android.datatransport.runtime.TransportContext;
import com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final /* synthetic */ class c implements SQLiteEventStore.Function {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f2115a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ SQLiteEventStore f2116b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Object f2117c;

    public /* synthetic */ c(SQLiteEventStore sQLiteEventStore, Object obj, int i2) {
        this.f2115a = i2;
        this.f2116b = sQLiteEventStore;
        this.f2117c = obj;
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore.Function
    public final Object apply(Object obj) {
        switch (this.f2115a) {
            case 0:
                return this.f2116b.lambda$hasPendingEventsFor$6((TransportContext) this.f2117c, (SQLiteDatabase) obj);
            case 1:
                return this.f2116b.lambda$loadBatch$8((TransportContext) this.f2117c, (SQLiteDatabase) obj);
            default:
                return this.f2116b.lambda$recordFailure$4((String) this.f2117c, "SELECT COUNT(*), transport_name FROM events WHERE num_attempts >= 16 GROUP BY transport_name", (SQLiteDatabase) obj);
        }
    }
}
