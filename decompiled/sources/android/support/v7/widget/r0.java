package android.support.v7.widget;

import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: compiled from: SuggestionsAdapter.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class r0 extends android.support.v4.widget.g implements View.OnClickListener {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int f1331f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private int f1332g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private int f1333h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private int f1334i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private int f1335j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private int f1336k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private int f1337l;

    /* JADX INFO: compiled from: SuggestionsAdapter.java */
    private static final class a {
    }

    public r0() {
        throw null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:?, code lost:
    
        throw null;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private Drawable h(String str) {
        if (str == null || str.isEmpty() || "0".equals(str)) {
            return null;
        }
        try {
            Integer.parseInt(str);
            throw null;
        } catch (Resources.NotFoundException unused) {
            Log.w("SuggestionsAdapter", "Icon resource not found: ".concat(str));
            return null;
        } catch (NumberFormatException unused2) {
            throw null;
        }
    }

    @Override // android.support.v4.widget.c, android.support.v4.widget.d.a
    public final void a(Cursor cursor) {
        try {
            super.a(cursor);
            if (cursor != null) {
                this.f1332g = cursor.getColumnIndex("suggest_text_1");
                this.f1333h = cursor.getColumnIndex("suggest_text_2");
                this.f1334i = cursor.getColumnIndex("suggest_text_2_url");
                this.f1335j = cursor.getColumnIndex("suggest_icon_1");
                this.f1336k = cursor.getColumnIndex("suggest_icon_2");
                this.f1337l = cursor.getColumnIndex("suggest_flags");
            }
        } catch (Exception e2) {
            Log.e("SuggestionsAdapter", "error changing cursor and caching columns", e2);
        }
    }

    @Override // android.support.v4.widget.d.a
    public final String c(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        int columnIndex = cursor.getColumnIndex("suggest_intent_query");
        String string = null;
        if (columnIndex != -1) {
            try {
                string = cursor.getString(columnIndex);
            } catch (Exception e2) {
                Log.e("SuggestionsAdapter", "unexpected error retrieving valid column from cursor, did the remote process die?", e2);
            }
        }
        string.getClass();
        return string;
    }

    @Override // android.support.v4.widget.d.a
    public final Cursor d(CharSequence charSequence) {
        if (charSequence != null) {
            charSequence.toString();
        }
        throw null;
    }

    @Override // android.support.v4.widget.c
    public final void e(View view, Cursor cursor) {
        a aVar = (a) view.getTag();
        int i2 = this.f1337l;
        int i3 = i2 != -1 ? cursor.getInt(i2) : 0;
        aVar.getClass();
        int i4 = this.f1331f;
        if (i4 != 2 && (i4 != 1 || (i3 & 1) == 0)) {
            throw null;
        }
        throw null;
    }

    @Override // android.support.v4.widget.c
    public final View g(Cursor cursor, ViewGroup viewGroup) {
        throw null;
    }

    @Override // android.support.v4.widget.c, android.widget.BaseAdapter, android.widget.SpinnerAdapter
    public final View getDropDownView(int i2, View view, ViewGroup viewGroup) {
        try {
            return super.getDropDownView(i2, view, viewGroup);
        } catch (RuntimeException e2) {
            Log.w("SuggestionsAdapter", "Search suggestions cursor threw exception.", e2);
            throw null;
        }
    }

    @Override // android.support.v4.widget.c, android.widget.Adapter
    public final View getView(int i2, View view, ViewGroup viewGroup) {
        try {
            super.getView(i2, view, viewGroup);
            return view;
        } catch (RuntimeException e2) {
            Log.w("SuggestionsAdapter", "Search suggestions cursor threw exception.", e2);
            throw null;
        }
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public final boolean hasStableIds() {
        return false;
    }

    public final void i(int i2) {
        this.f1331f = i2;
    }

    @Override // android.widget.BaseAdapter
    public final void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        Cursor cursorF = f();
        Bundle extras = cursorF != null ? cursorF.getExtras() : null;
        if (extras != null) {
            extras.getBoolean("in_progress");
        }
    }

    @Override // android.widget.BaseAdapter
    public final void notifyDataSetInvalidated() {
        super.notifyDataSetInvalidated();
        Cursor cursorF = f();
        Bundle extras = cursorF != null ? cursorF.getExtras() : null;
        if (extras != null) {
            extras.getBoolean("in_progress");
        }
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        if (view.getTag() instanceof CharSequence) {
            throw null;
        }
    }
}
