package a0;

import java.util.Random;

/* JADX INFO: compiled from: RandomXS128.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class o extends Random {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private long f87a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private long f88b;

    @Override // java.util.Random
    protected final int next(int i2) {
        return (int) (nextLong() & ((1 << i2) - 1));
    }

    @Override // java.util.Random
    public final boolean nextBoolean() {
        return (nextLong() & 1) != 0;
    }

    @Override // java.util.Random
    public final void nextBytes(byte[] bArr) {
        int length = bArr.length;
        while (length != 0) {
            int i2 = length < 8 ? length : 8;
            long jNextLong = nextLong();
            while (true) {
                int i3 = i2 - 1;
                if (i2 != 0) {
                    length--;
                    bArr[length] = (byte) jNextLong;
                    jNextLong >>= 8;
                    i2 = i3;
                }
            }
        }
    }

    @Override // java.util.Random
    public final double nextDouble() {
        return (nextLong() >>> 11) * 1.1102230246251565E-16d;
    }

    @Override // java.util.Random
    public final float nextFloat() {
        return (float) ((nextLong() >>> 40) * 5.960464477539063E-8d);
    }

    @Override // java.util.Random
    public final int nextInt() {
        return (int) nextLong();
    }

    @Override // java.util.Random
    public final long nextLong() {
        long j2 = this.f87a;
        long j3 = this.f88b;
        this.f87a = j3;
        long j4 = j2 ^ (j2 << 23);
        long j5 = ((j4 >>> 17) ^ (j4 ^ j3)) ^ (j3 >>> 26);
        this.f88b = j5;
        return j5 + j3;
    }

    @Override // java.util.Random
    public final void setSeed(long j2) {
        if (j2 == 0) {
            j2 = Long.MIN_VALUE;
        }
        long j3 = (j2 ^ (j2 >>> 33)) * (-49064778989728563L);
        long j4 = (j3 ^ (j3 >>> 33)) * (-4265267296055464877L);
        long j5 = j4 ^ (j4 >>> 33);
        long j6 = ((j5 >>> 33) ^ j5) * (-49064778989728563L);
        long j7 = ((j6 >>> 33) ^ j6) * (-4265267296055464877L);
        this.f87a = j5;
        this.f88b = j7 ^ (j7 >>> 33);
    }

    @Override // java.util.Random
    public final int nextInt(int i2) {
        return (int) nextLong(i2);
    }

    public final long nextLong(long j2) {
        long jNextLong;
        long j3;
        if (j2 <= 0) {
            throw new IllegalArgumentException("n must be positive");
        }
        do {
            jNextLong = nextLong() >>> 1;
            j3 = jNextLong % j2;
        } while ((j2 - 1) + (jNextLong - j3) < 0);
        return j3;
    }
}
