package com.google.android.gms.internal.drive;

import a.a;
import com.badlogic.gdx.graphics.VertexAttributes;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ReadOnlyBufferException;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zzip {
    private final ByteBuffer zzmv;

    private zzip(ByteBuffer byteBuffer) {
        this.zzmv = byteBuffer;
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
    }

    private static int zza(CharSequence charSequence) {
        int length = charSequence.length();
        int i2 = 0;
        int i3 = 0;
        while (i3 < length && charSequence.charAt(i3) < 128) {
            i3++;
        }
        int i4 = length;
        while (true) {
            if (i3 >= length) {
                break;
            }
            char cCharAt = charSequence.charAt(i3);
            if (cCharAt < 2048) {
                i4 += (127 - cCharAt) >>> 31;
                i3++;
            } else {
                int length2 = charSequence.length();
                while (i3 < length2) {
                    char cCharAt2 = charSequence.charAt(i3);
                    if (cCharAt2 < 2048) {
                        i2 += (127 - cCharAt2) >>> 31;
                    } else {
                        i2 += 2;
                        if (55296 <= cCharAt2 && cCharAt2 <= 57343) {
                            if (Character.codePointAt(charSequence, i3) < 65536) {
                                throw new IllegalArgumentException(a.g(39, i3, "Unpaired surrogate at index "));
                            }
                            i3++;
                        }
                    }
                    i3++;
                }
                i4 += i2;
            }
        }
        if (i4 >= length) {
            return i4;
        }
        StringBuilder sb = new StringBuilder(54);
        sb.append("UTF-8 length does not fit in int: ");
        sb.append(((long) i4) + 4294967296L);
        throw new IllegalArgumentException(sb.toString());
    }

    public static int zzb(int i2, long j2) {
        int iZzo = zzo(i2);
        long jZzb = zzb(j2);
        return iZzo + (((-128) & jZzb) == 0 ? 1 : ((-16384) & jZzb) == 0 ? 2 : ((-2097152) & jZzb) == 0 ? 3 : ((-268435456) & jZzb) == 0 ? 4 : ((-34359738368L) & jZzb) == 0 ? 5 : ((-4398046511104L) & jZzb) == 0 ? 6 : ((-562949953421312L) & jZzb) == 0 ? 7 : ((-72057594037927936L) & jZzb) == 0 ? 8 : (jZzb & Long.MIN_VALUE) == 0 ? 9 : 10);
    }

    public static int zzc(int i2, int i3) {
        return zzm(i3) + zzo(i2);
    }

    public static int zzi(String str) {
        int iZza = zza(str);
        return zzq(iZza) + iZza;
    }

    public static int zzm(int i2) {
        if (i2 >= 0) {
            return zzq(i2);
        }
        return 10;
    }

    private final void zzn(int i2) throws zziq {
        byte b2 = (byte) i2;
        if (!this.zzmv.hasRemaining()) {
            throw new zziq(this.zzmv.position(), this.zzmv.limit());
        }
        this.zzmv.put(b2);
    }

    public static int zzo(int i2) {
        return zzq(i2 << 3);
    }

    public static int zzq(int i2) {
        if ((i2 & (-128)) == 0) {
            return 1;
        }
        if ((i2 & (-16384)) == 0) {
            return 2;
        }
        if (((-2097152) & i2) == 0) {
            return 3;
        }
        return (i2 & (-268435456)) == 0 ? 4 : 5;
    }

    public final void zzbh() {
        if (this.zzmv.remaining() == 0) {
            return;
        }
        throw new IllegalStateException("Did not write as much data as expected, " + this.zzmv.remaining() + " bytes remaining.");
    }

    public final void zzd(int i2, int i3) throws zziq {
        zzp((i2 << 3) | i3);
    }

    public final void zzh(String str) throws zziq {
        try {
            int iZzq = zzq(str.length());
            if (iZzq != zzq(str.length() * 3)) {
                zzp(zza(str));
                zza(str, this.zzmv);
                return;
            }
            int iPosition = this.zzmv.position();
            if (this.zzmv.remaining() < iZzq) {
                throw new zziq(iPosition + iZzq, this.zzmv.limit());
            }
            this.zzmv.position(iPosition + iZzq);
            zza(str, this.zzmv);
            int iPosition2 = this.zzmv.position();
            this.zzmv.position(iPosition);
            zzp((iPosition2 - iPosition) - iZzq);
            this.zzmv.position(iPosition2);
        } catch (BufferOverflowException e2) {
            zziq zziqVar = new zziq(this.zzmv.position(), this.zzmv.limit());
            zziqVar.initCause(e2);
            throw zziqVar;
        }
    }

    public final void zzp(int i2) throws zziq {
        while ((i2 & (-128)) != 0) {
            zzn((i2 & 127) | VertexAttributes.Usage.Tangent);
            i2 >>>= 7;
        }
        zzn(i2);
    }

    private zzip(byte[] bArr, int i2, int i3) {
        this(ByteBuffer.wrap(bArr, i2, i3));
    }

    private static long zzb(long j2) {
        return (j2 >> 63) ^ (j2 << 1);
    }

    public final void zzc(byte[] bArr) throws zziq {
        int length = bArr.length;
        if (this.zzmv.remaining() < length) {
            throw new zziq(this.zzmv.position(), this.zzmv.limit());
        }
        this.zzmv.put(bArr, 0, length);
    }

    public static zzip zzb(byte[] bArr) {
        return zzb(bArr, 0, bArr.length);
    }

    public static zzip zzb(byte[] bArr, int i2, int i3) {
        return new zzip(bArr, 0, i3);
    }

    public final void zzb(int i2, int i3) throws zziq {
        zzd(i2, 0);
        if (i3 >= 0) {
            zzp(i3);
        } else {
            zza(i3);
        }
    }

    public final void zza(int i2, long j2) throws zziq {
        zzd(i2, 0);
        zza(zzb(j2));
    }

    private final void zza(long j2) throws zziq {
        while (((-128) & j2) != 0) {
            zzn((((int) j2) & 127) | VertexAttributes.Usage.Tangent);
            j2 >>>= 7;
        }
        zzn((int) j2);
    }

    private static void zza(CharSequence charSequence, ByteBuffer byteBuffer) {
        int i2;
        char cCharAt;
        if (byteBuffer.isReadOnly()) {
            throw new ReadOnlyBufferException();
        }
        char c2 = 57343;
        int i3 = 0;
        if (!byteBuffer.hasArray()) {
            int length = charSequence.length();
            while (i3 < length) {
                char cCharAt2 = charSequence.charAt(i3);
                int i4 = cCharAt2;
                if (cCharAt2 < 128) {
                    byteBuffer.put((byte) i4);
                } else if (cCharAt2 < 2048) {
                    byteBuffer.put((byte) ((cCharAt2 >>> 6) | 960));
                    i4 = (cCharAt2 & '?') | VertexAttributes.Usage.Tangent;
                    byteBuffer.put((byte) i4);
                } else {
                    if (cCharAt2 >= 55296 && 57343 >= cCharAt2) {
                        int i5 = i3 + 1;
                        if (i5 != charSequence.length()) {
                            char cCharAt3 = charSequence.charAt(i5);
                            if (Character.isSurrogatePair(cCharAt2, cCharAt3)) {
                                int codePoint = Character.toCodePoint(cCharAt2, cCharAt3);
                                byteBuffer.put((byte) ((codePoint >>> 18) | 240));
                                byteBuffer.put((byte) (((codePoint >>> 12) & 63) | VertexAttributes.Usage.Tangent));
                                byteBuffer.put((byte) (((codePoint >>> 6) & 63) | VertexAttributes.Usage.Tangent));
                                byteBuffer.put((byte) ((codePoint & 63) | VertexAttributes.Usage.Tangent));
                                i3 = i5;
                            } else {
                                i3 = i5;
                            }
                        }
                        throw new IllegalArgumentException(a.g(39, i3 - 1, "Unpaired surrogate at index "));
                    }
                    byteBuffer.put((byte) ((cCharAt2 >>> '\f') | 480));
                    byteBuffer.put((byte) (((cCharAt2 >>> 6) & 63) | VertexAttributes.Usage.Tangent));
                    byteBuffer.put((byte) ((cCharAt2 & '?') | VertexAttributes.Usage.Tangent));
                }
                i3++;
            }
            return;
        }
        try {
            byte[] bArrArray = byteBuffer.array();
            int iArrayOffset = byteBuffer.arrayOffset() + byteBuffer.position();
            int iRemaining = byteBuffer.remaining();
            int length2 = charSequence.length();
            int i6 = iRemaining + iArrayOffset;
            while (i3 < length2) {
                int i7 = i3 + iArrayOffset;
                if (i7 >= i6 || (cCharAt = charSequence.charAt(i3)) >= 128) {
                    break;
                }
                bArrArray[i7] = (byte) cCharAt;
                i3++;
            }
            if (i3 == length2) {
                i2 = iArrayOffset + length2;
            } else {
                i2 = iArrayOffset + i3;
                while (i3 < length2) {
                    char cCharAt4 = charSequence.charAt(i3);
                    if (cCharAt4 < 128 && i2 < i6) {
                        bArrArray[i2] = (byte) cCharAt4;
                        i2++;
                    } else if (cCharAt4 < 2048 && i2 <= i6 - 2) {
                        int i8 = i2 + 1;
                        bArrArray[i2] = (byte) ((cCharAt4 >>> 6) | 960);
                        i2 += 2;
                        bArrArray[i8] = (byte) ((cCharAt4 & '?') | VertexAttributes.Usage.Tangent);
                    } else {
                        if ((cCharAt4 >= 55296 && c2 >= cCharAt4) || i2 > i6 - 3) {
                            if (i2 > i6 - 4) {
                                StringBuilder sb = new StringBuilder(37);
                                sb.append("Failed writing ");
                                sb.append(cCharAt4);
                                sb.append(" at index ");
                                sb.append(i2);
                                throw new ArrayIndexOutOfBoundsException(sb.toString());
                            }
                            int i9 = i3 + 1;
                            if (i9 != charSequence.length()) {
                                char cCharAt5 = charSequence.charAt(i9);
                                if (Character.isSurrogatePair(cCharAt4, cCharAt5)) {
                                    int codePoint2 = Character.toCodePoint(cCharAt4, cCharAt5);
                                    bArrArray[i2] = (byte) ((codePoint2 >>> 18) | 240);
                                    bArrArray[i2 + 1] = (byte) (((codePoint2 >>> 12) & 63) | VertexAttributes.Usage.Tangent);
                                    int i10 = i2 + 3;
                                    bArrArray[i2 + 2] = (byte) (((codePoint2 >>> 6) & 63) | VertexAttributes.Usage.Tangent);
                                    i2 += 4;
                                    bArrArray[i10] = (byte) ((codePoint2 & 63) | VertexAttributes.Usage.Tangent);
                                    i3 = i9;
                                } else {
                                    i3 = i9;
                                }
                            }
                            StringBuilder sb2 = new StringBuilder(39);
                            sb2.append("Unpaired surrogate at index ");
                            sb2.append(i3 - 1);
                            throw new IllegalArgumentException(sb2.toString());
                        }
                        bArrArray[i2] = (byte) ((cCharAt4 >>> '\f') | 480);
                        int i11 = i2 + 2;
                        bArrArray[i2 + 1] = (byte) (((cCharAt4 >>> 6) & 63) | VertexAttributes.Usage.Tangent);
                        i2 += 3;
                        bArrArray[i11] = (byte) ((cCharAt4 & '?') | VertexAttributes.Usage.Tangent);
                    }
                    i3++;
                    c2 = 57343;
                }
            }
            byteBuffer.position(i2 - byteBuffer.arrayOffset());
        } catch (ArrayIndexOutOfBoundsException e2) {
            BufferOverflowException bufferOverflowException = new BufferOverflowException();
            bufferOverflowException.initCause(e2);
            throw bufferOverflowException;
        }
    }
}
