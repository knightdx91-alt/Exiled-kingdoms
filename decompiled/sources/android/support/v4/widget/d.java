package android.support.v4.widget;

import android.database.Cursor;
import android.widget.Filter;

/* JADX INFO: compiled from: CursorFilter.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class d extends Filter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    c f678a;

    /* JADX INFO: compiled from: CursorFilter.java */
    interface a {
        void a(Cursor cursor);

        String c(Cursor cursor);

        Cursor d(CharSequence charSequence);
    }

    @Override // android.widget.Filter
    public final CharSequence convertResultToString(Object obj) {
        return this.f678a.c((Cursor) obj);
    }

    @Override // android.widget.Filter
    protected final Filter.FilterResults performFiltering(CharSequence charSequence) {
        this.f678a.d(charSequence);
        throw null;
    }

    @Override // android.widget.Filter
    protected final void publishResults(CharSequence charSequence, Filter.FilterResults filterResults) {
        c cVar = this.f678a;
        Cursor cursor = cVar.f675c;
        Object obj = filterResults.values;
        if (obj == null || obj == cursor) {
            return;
        }
        cVar.a((Cursor) obj);
    }
}
