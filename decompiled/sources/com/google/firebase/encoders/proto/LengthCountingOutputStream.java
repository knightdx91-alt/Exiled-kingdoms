package com.google.firebase.encoders.proto;

import java.io.OutputStream;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class LengthCountingOutputStream extends OutputStream {
    private long length = 0;

    LengthCountingOutputStream() {
    }

    long getLength() {
        return this.length;
    }

    @Override // java.io.OutputStream
    public void write(int i2) {
        this.length++;
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr) {
        this.length += (long) bArr.length;
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i2, int i3) {
        int i4;
        if (i2 >= 0 && i2 <= bArr.length && i3 >= 0 && (i4 = i2 + i3) <= bArr.length && i4 >= 0) {
            this.length += (long) i3;
            return;
        }
        throw new IndexOutOfBoundsException();
    }
}
