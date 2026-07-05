package com.google.android.gms.common.data;

import a.a;
import com.google.android.gms.common.annotation.KeepForSdk;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@KeepForSdk
public class SingleRefDataBufferIterator<T> extends DataBufferIterator<T> {
    private T zamf;

    public SingleRefDataBufferIterator(DataBuffer<T> dataBuffer) {
        super(dataBuffer);
    }

    @Override // com.google.android.gms.common.data.DataBufferIterator, java.util.Iterator
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException(a.g(46, this.zalk, "Cannot advance the iterator beyond "));
        }
        int i2 = this.zalk + 1;
        this.zalk = i2;
        if (i2 == 0) {
            T t2 = this.zalj.get(0);
            this.zamf = t2;
            if (!(t2 instanceof DataBufferRef)) {
                String strValueOf = String.valueOf(this.zamf.getClass());
                StringBuilder sb = new StringBuilder(strValueOf.length() + 44);
                sb.append("DataBuffer reference of type ");
                sb.append(strValueOf);
                sb.append(" is not movable");
                throw new IllegalStateException(sb.toString());
            }
        } else {
            ((DataBufferRef) this.zamf).zag(i2);
        }
        return this.zamf;
    }
}
