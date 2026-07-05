package com.google.android.gms.drive.widget;

import android.content.Context;
import android.database.CursorIndexOutOfBoundsException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.google.android.gms.common.data.DataBuffer;
import com.google.android.gms.common.internal.GmsLogger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public class DataBufferAdapter<T> extends BaseAdapter {
    private static final GmsLogger zzbx = new GmsLogger("DataBufferAdapter", "");
    private final int fieldId;
    private final int resource;
    private final Context zzgu;
    private int zzmj;
    private final List<DataBuffer<T>> zzmk;
    private final LayoutInflater zzml;
    private boolean zzmm;

    public DataBufferAdapter(Context context, int i2) {
        this(context, i2, 0, new ArrayList());
    }

    private final View zza(int i2, View view, ViewGroup viewGroup, int i3) {
        if (view == null) {
            view = this.zzml.inflate(i3, viewGroup, false);
        }
        try {
            int i4 = this.fieldId;
            TextView textView = i4 == 0 ? (TextView) view : (TextView) view.findViewById(i4);
            T item = getItem(i2);
            textView.setText(item instanceof CharSequence ? (CharSequence) item : item.toString());
            return view;
        } catch (ClassCastException e2) {
            zzbx.e("DataBufferAdapter", "You must supply a resource ID for a TextView", e2);
            throw new IllegalStateException("DataBufferAdapter requires the resource ID to be a TextView", e2);
        }
    }

    public void append(DataBuffer<T> dataBuffer) {
        this.zzmk.add(dataBuffer);
        if (this.zzmm) {
            notifyDataSetChanged();
        }
    }

    public void clear() {
        Iterator<DataBuffer<T>> it = this.zzmk.iterator();
        while (it.hasNext()) {
            it.next().release();
        }
        this.zzmk.clear();
        if (this.zzmm) {
            notifyDataSetChanged();
        }
    }

    public Context getContext() {
        return this.zzgu;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        Iterator<DataBuffer<T>> it = this.zzmk.iterator();
        int count = 0;
        while (it.hasNext()) {
            count += it.next().getCount();
        }
        return count;
    }

    @Override // android.widget.BaseAdapter, android.widget.SpinnerAdapter
    public View getDropDownView(int i2, View view, ViewGroup viewGroup) {
        return zza(i2, view, viewGroup, this.zzmj);
    }

    @Override // android.widget.Adapter
    public T getItem(int i2) {
        int i3 = i2;
        for (DataBuffer<T> dataBuffer : this.zzmk) {
            int count = dataBuffer.getCount();
            if (count > i3) {
                try {
                    return dataBuffer.get(i3);
                } catch (CursorIndexOutOfBoundsException unused) {
                    throw new CursorIndexOutOfBoundsException(i2, getCount());
                }
            }
            i3 -= count;
        }
        throw new CursorIndexOutOfBoundsException(i2, getCount());
    }

    @Override // android.widget.Adapter
    public long getItemId(int i2) {
        return i2;
    }

    @Override // android.widget.Adapter
    public View getView(int i2, View view, ViewGroup viewGroup) {
        return zza(i2, view, viewGroup, this.resource);
    }

    @Override // android.widget.BaseAdapter
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        this.zzmm = true;
    }

    public void setDropDownViewResource(int i2) {
        this.zzmj = i2;
    }

    public void setNotifyOnChange(boolean z2) {
        this.zzmm = z2;
    }

    public DataBufferAdapter(Context context, int i2, int i3) {
        this(context, i2, i3, new ArrayList());
    }

    public DataBufferAdapter(Context context, int i2, int i3, List<DataBuffer<T>> list) {
        this.zzmm = true;
        this.zzgu = context;
        this.zzmj = i2;
        this.resource = i2;
        this.fieldId = i3;
        this.zzmk = list;
        this.zzml = (LayoutInflater) context.getSystemService("layout_inflater");
    }

    public DataBufferAdapter(Context context, int i2, int i3, DataBuffer<T>... dataBufferArr) {
        this(context, i2, i3, Arrays.asList(dataBufferArr));
    }

    public DataBufferAdapter(Context context, int i2, List<DataBuffer<T>> list) {
        this(context, i2, 0, list);
    }

    public DataBufferAdapter(Context context, int i2, DataBuffer<T>... dataBufferArr) {
        this(context, i2, 0, Arrays.asList(dataBufferArr));
    }
}
