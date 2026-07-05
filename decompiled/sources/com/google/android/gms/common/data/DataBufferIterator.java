package com.google.android.gms.common.data;

import a.a;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@KeepForSdk
public class DataBufferIterator<T> implements Iterator<T> {
    protected final DataBuffer<T> zalj;
    protected int zalk = -1;

    public DataBufferIterator(DataBuffer<T> dataBuffer) {
        this.zalj = (DataBuffer) Preconditions.checkNotNull(dataBuffer);
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.zalk < this.zalj.getCount() - 1;
    }

    @Override // java.util.Iterator
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException(a.g(46, this.zalk, "Cannot advance the iterator beyond "));
        }
        DataBuffer<T> dataBuffer = this.zalj;
        int i2 = this.zalk + 1;
        this.zalk = i2;
        return dataBuffer.get(i2);
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Cannot remove elements from a DataBufferIterator");
    }
}
