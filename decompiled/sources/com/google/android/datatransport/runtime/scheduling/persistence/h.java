package com.google.android.datatransport.runtime.scheduling.persistence;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final /* synthetic */ class h implements SQLiteEventStore.Function {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f2128a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ long f2129b;

    public /* synthetic */ h(long j2, int i2) {
        this.f2128a = i2;
        this.f2129b = j2;
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore.Function
    public final Object apply(Object obj) {
        switch (this.f2128a) {
            case 0:
                return SQLiteEventStore.lambda$getTimeWindow$22(this.f2129b, (SQLiteDatabase) obj);
            default:
                return SQLiteEventStore.lambda$getTimeWindow$21(this.f2129b, (Cursor) obj);
        }
    }
}
