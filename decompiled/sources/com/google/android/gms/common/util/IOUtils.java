package com.google.android.gms.common.util;

import android.os.ParcelFileDescriptor;
import com.badlogic.gdx.graphics.GL20;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@ShowFirstParty
@KeepForSdk
public final class IOUtils {
    private IOUtils() {
    }

    @KeepForSdk
    public static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }

    @KeepForSdk
    public static long copyStream(InputStream inputStream, OutputStream outputStream) {
        return zza(inputStream, outputStream, false);
    }

    @KeepForSdk
    public static boolean isGzipByteBuffer(byte[] bArr) {
        if (bArr.length > 1) {
            if ((((bArr[1] & 255) << 8) | (bArr[0] & 255)) == 35615) {
                return true;
            }
        }
        return false;
    }

    @KeepForSdk
    public static byte[] readInputStreamFully(InputStream inputStream) {
        return readInputStreamFully(inputStream, true);
    }

    @KeepForSdk
    public static byte[] toByteArray(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Preconditions.checkNotNull(inputStream);
        Preconditions.checkNotNull(byteArrayOutputStream);
        byte[] bArr = new byte[4096];
        while (true) {
            int i2 = inputStream.read(bArr);
            if (i2 == -1) {
                return byteArrayOutputStream.toByteArray();
            }
            byteArrayOutputStream.write(bArr, 0, i2);
        }
    }

    private static long zza(InputStream inputStream, OutputStream outputStream, boolean z2) {
        return copyStream(inputStream, outputStream, z2, GL20.GL_STENCIL_BUFFER_BIT);
    }

    @KeepForSdk
    public static void closeQuietly(ParcelFileDescriptor parcelFileDescriptor) {
        if (parcelFileDescriptor != null) {
            try {
                parcelFileDescriptor.close();
            } catch (IOException unused) {
            }
        }
    }

    @KeepForSdk
    public static long copyStream(InputStream inputStream, OutputStream outputStream, boolean z2, int i2) {
        byte[] bArr = new byte[i2];
        long j2 = 0;
        while (true) {
            try {
                int i3 = inputStream.read(bArr, 0, i2);
                if (i3 == -1) {
                    break;
                }
                j2 += (long) i3;
                outputStream.write(bArr, 0, i3);
            } finally {
                if (z2) {
                    closeQuietly(inputStream);
                    closeQuietly(outputStream);
                }
            }
        }
        return j2;
    }

    @KeepForSdk
    public static byte[] readInputStreamFully(InputStream inputStream, boolean z2) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        zza(inputStream, byteArrayOutputStream, z2);
        return byteArrayOutputStream.toByteArray();
    }
}
