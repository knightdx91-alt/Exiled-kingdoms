package com.google.android.datatransport.runtime.scheduling.persistence;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.google.android.datatransport.runtime.EventInternal;
import com.google.android.datatransport.runtime.TransportContext;
import com.google.android.datatransport.runtime.firebase.transport.ClientMetrics;
import com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final /* synthetic */ class a implements SQLiteEventStore.Function {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f2110a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ SQLiteEventStore f2111b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Object f2112c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ Object f2113d;

    public /* synthetic */ a(SQLiteEventStore sQLiteEventStore, Object obj, Object obj2, int i2) {
        this.f2110a = i2;
        this.f2111b = sQLiteEventStore;
        this.f2112c = obj;
        this.f2113d = obj2;
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore.Function
    public final Object apply(Object obj) {
        switch (this.f2110a) {
            case 0:
                HashMap map = (HashMap) this.f2112c;
                return this.f2111b.lambda$loadClientMetrics$20("SELECT log_source, reason, events_dropped_count FROM log_event_dropped", map, (ClientMetrics.Builder) this.f2113d, (SQLiteDatabase) obj);
            case 1:
                ArrayList arrayList = (ArrayList) this.f2112c;
                return this.f2111b.lambda$loadEvents$14(arrayList, (TransportContext) this.f2113d, (Cursor) obj);
            case 2:
                return this.f2111b.lambda$persist$1((EventInternal) this.f2112c, (TransportContext) this.f2113d, (SQLiteDatabase) obj);
            default:
                return this.f2111b.lambda$loadClientMetrics$19((Map) this.f2112c, (ClientMetrics.Builder) this.f2113d, (Cursor) obj);
        }
    }
}
