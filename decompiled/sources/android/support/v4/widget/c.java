package android.support.v4.widget;

import android.database.Cursor;
import android.support.v4.widget.d;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;

/* JADX INFO: compiled from: CursorAdapter.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public abstract class c extends BaseAdapter implements Filterable, d.a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected boolean f674b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected Cursor f675c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    protected int f676d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    protected d f677e;

    @Override // android.support.v4.widget.d.a
    public void a(Cursor cursor) {
        Cursor cursor2 = this.f675c;
        if (cursor == cursor2) {
            cursor2 = null;
        } else {
            this.f675c = cursor;
            if (cursor != null) {
                this.f676d = cursor.getColumnIndexOrThrow("_id");
                this.f674b = true;
                notifyDataSetChanged();
            } else {
                this.f676d = -1;
                this.f674b = false;
                notifyDataSetInvalidated();
            }
        }
        if (cursor2 != null) {
            cursor2.close();
        }
    }

    public abstract void e(View view, Cursor cursor);

    public final Cursor f() {
        return this.f675c;
    }

    public abstract View g(Cursor cursor, ViewGroup viewGroup);

    @Override // android.widget.Adapter
    public final int getCount() {
        Cursor cursor;
        if (!this.f674b || (cursor = this.f675c) == null) {
            return 0;
        }
        return cursor.getCount();
    }

    @Override // android.widget.BaseAdapter, android.widget.SpinnerAdapter
    public View getDropDownView(int i2, View view, ViewGroup viewGroup) {
        if (!this.f674b) {
            return null;
        }
        this.f675c.moveToPosition(i2);
        if (view == null) {
            throw null;
        }
        e(view, this.f675c);
        return view;
    }

    @Override // android.widget.Filterable
    public final Filter getFilter() {
        if (this.f677e == null) {
            d dVar = new d();
            dVar.f678a = this;
            this.f677e = dVar;
        }
        return this.f677e;
    }

    @Override // android.widget.Adapter
    public final Object getItem(int i2) {
        Cursor cursor;
        if (!this.f674b || (cursor = this.f675c) == null) {
            return null;
        }
        cursor.moveToPosition(i2);
        return this.f675c;
    }

    @Override // android.widget.Adapter
    public final long getItemId(int i2) {
        Cursor cursor;
        if (this.f674b && (cursor = this.f675c) != null && cursor.moveToPosition(i2)) {
            return this.f675c.getLong(this.f676d);
        }
        return 0L;
    }

    @Override // android.widget.Adapter
    public View getView(int i2, View view, ViewGroup viewGroup) {
        if (!this.f674b) {
            throw new IllegalStateException("this should only be called when the cursor is valid");
        }
        if (!this.f675c.moveToPosition(i2)) {
            throw new IllegalStateException(a.a.h(i2, "couldn't move cursor to position "));
        }
        if (view != null) {
            e(view, this.f675c);
            return view;
        }
        g(this.f675c, viewGroup);
        throw null;
    }
}
