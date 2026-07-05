package com.badlogic.gdx.utils;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;

/* JADX INFO: compiled from: StreamUtils.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class n0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final byte[] f1848a = new byte[0];

    /* JADX INFO: compiled from: StreamUtils.java */
    public static class a extends ByteArrayOutputStream {
        @Override // java.io.ByteArrayOutputStream
        public final synchronized byte[] toByteArray() {
            int i2 = ((ByteArrayOutputStream) this).count;
            byte[] bArr = ((ByteArrayOutputStream) this).buf;
            if (i2 == bArr.length) {
                return bArr;
            }
            return super.toByteArray();
        }
    }

    public static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Throwable unused) {
            }
        }
    }
}
