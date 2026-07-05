package com.badlogic.gdx.utils;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class BufferUtils {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static a<ByteBuffer> f1744a = new a<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final /* synthetic */ int f1745b = 0;

    private static int a(int i2, Buffer buffer) {
        if (buffer instanceof ByteBuffer) {
            return i2;
        }
        if (buffer instanceof ShortBuffer) {
            return i2 >>> 1;
        }
        if (buffer instanceof CharBuffer) {
            return i2 >>> 1;
        }
        if (buffer instanceof IntBuffer) {
            return i2 >>> 2;
        }
        if (buffer instanceof LongBuffer) {
            return i2 >>> 3;
        }
        if (buffer instanceof FloatBuffer) {
            return i2 >>> 2;
        }
        if (buffer instanceof DoubleBuffer) {
            return i2 >>> 3;
        }
        throw new m("Can't copy to a " + buffer.getClass().getName() + " instance");
    }

    public static void b(FloatBuffer floatBuffer, Buffer buffer, int i2) {
        if (floatBuffer != null) {
            int i3 = i2 << 2;
            buffer.limit(buffer.position() + a(i3, buffer));
            copyJni(floatBuffer, j(floatBuffer), buffer, j(buffer), i3);
        } else {
            throw new m("Can't copy to a " + floatBuffer.getClass().getName() + " instance");
        }
    }

    public static void c(float[] fArr, int i2, int i3, Buffer buffer) {
        copyJni(fArr, i2, buffer, j(buffer), i3 << 2);
    }

    private static native void copyJni(Buffer buffer, int i2, Buffer buffer2, int i3, int i4);

    private static native void copyJni(float[] fArr, int i2, Buffer buffer, int i3, int i4);

    private static native void copyJni(float[] fArr, Buffer buffer, int i2, int i3);

    private static native void copyJni(short[] sArr, int i2, Buffer buffer, int i3, int i4);

    public static void d(float[] fArr, Buffer buffer, int i2, int i3) {
        if (buffer instanceof ByteBuffer) {
            buffer.limit(i2 << 2);
        } else if (buffer instanceof FloatBuffer) {
            buffer.limit(i2);
        }
        copyJni(fArr, buffer, i2, i3);
        buffer.position(0);
    }

    public static void e(short[] sArr, int i2, Buffer buffer, int i3) {
        int i4 = i3 << 1;
        buffer.limit(buffer.position() + a(i4, buffer));
        copyJni(sArr, i2, buffer, j(buffer), i4);
    }

    public static void f(ByteBuffer byteBuffer) {
        byteBuffer.capacity();
        a<ByteBuffer> aVar = f1744a;
        synchronized (aVar) {
            if (!aVar.q(byteBuffer, true)) {
                throw new IllegalArgumentException("buffer not allocated with newUnsafeByteBuffer or already disposed");
            }
        }
        freeMemory(byteBuffer);
    }

    private static native void freeMemory(ByteBuffer byteBuffer);

    public static IntBuffer g(int i2) {
        ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(i2 * 4);
        byteBufferAllocateDirect.order(ByteOrder.nativeOrder());
        return byteBufferAllocateDirect.asIntBuffer();
    }

    public static ByteBuffer h(int i2) {
        ByteBuffer byteBufferNewDisposableByteBuffer = newDisposableByteBuffer(i2);
        byteBufferNewDisposableByteBuffer.order(ByteOrder.nativeOrder());
        a<ByteBuffer> aVar = f1744a;
        synchronized (aVar) {
            aVar.a(byteBufferNewDisposableByteBuffer);
        }
        return byteBufferNewDisposableByteBuffer;
    }

    public static void i(ByteBuffer byteBuffer) {
        byteBuffer.capacity();
        a<ByteBuffer> aVar = f1744a;
        synchronized (aVar) {
            aVar.a(byteBuffer);
        }
    }

    private static int j(Buffer buffer) {
        if (buffer instanceof ByteBuffer) {
            return buffer.position();
        }
        if (buffer instanceof ShortBuffer) {
            return buffer.position() << 1;
        }
        if (buffer instanceof CharBuffer) {
            return buffer.position() << 1;
        }
        if (buffer instanceof IntBuffer) {
            return buffer.position() << 2;
        }
        if (buffer instanceof LongBuffer) {
            return buffer.position() << 3;
        }
        if (buffer instanceof FloatBuffer) {
            return buffer.position() << 2;
        }
        if (buffer instanceof DoubleBuffer) {
            return buffer.position() << 3;
        }
        throw new m("Can't copy to a " + buffer.getClass().getName() + " instance");
    }

    private static native ByteBuffer newDisposableByteBuffer(int i2);
}
