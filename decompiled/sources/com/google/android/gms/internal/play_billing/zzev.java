package com.google.android.gms.internal.play_billing;

import com.badlogic.gdx.graphics.VertexAttributes;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.0.1 */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzev {
    public static final /* synthetic */ int zza = 0;
    private static final zzes zzb;

    static {
        if (zzeq.zzx() && zzeq.zzy()) {
            int i2 = zzam.zza;
        }
        zzb = new zzet();
    }

    static /* bridge */ /* synthetic */ int zza(byte[] bArr, int i2, int i3) {
        int i4 = i3 - i2;
        byte b2 = bArr[i2 - 1];
        if (i4 != 0) {
            if (i4 == 1) {
                byte b3 = bArr[i2];
                if (b2 <= -12 && b3 <= -65) {
                    return b2 ^ (b3 << 8);
                }
            } else {
                if (i4 != 2) {
                    throw new AssertionError();
                }
                byte b4 = bArr[i2];
                byte b5 = bArr[i2 + 1];
                if (b2 <= -12 && b4 <= -65 && b5 <= -65) {
                    return ((b4 << 8) ^ b2) ^ (b5 << 16);
                }
            }
        } else if (b2 <= -12) {
            return b2;
        }
        return -1;
    }

    static int zzb(CharSequence charSequence, byte[] bArr, int i2, int i3) {
        int i4;
        int i5;
        int i6;
        char cCharAt;
        int length = charSequence.length();
        int i7 = 0;
        while (true) {
            i4 = i2 + i3;
            if (i7 >= length || (i6 = i7 + i2) >= i4 || (cCharAt = charSequence.charAt(i7)) >= 128) {
                break;
            }
            bArr[i6] = (byte) cCharAt;
            i7++;
        }
        if (i7 == length) {
            return i2 + length;
        }
        int i8 = i2 + i7;
        while (i7 < length) {
            char cCharAt2 = charSequence.charAt(i7);
            if (cCharAt2 < 128 && i8 < i4) {
                bArr[i8] = (byte) cCharAt2;
                i8++;
            } else if (cCharAt2 < 2048 && i8 <= i4 - 2) {
                int i9 = i8 + 1;
                bArr[i8] = (byte) ((cCharAt2 >>> 6) | 960);
                i8 += 2;
                bArr[i9] = (byte) ((cCharAt2 & '?') | VertexAttributes.Usage.Tangent);
            } else {
                if ((cCharAt2 >= 55296 && cCharAt2 <= 57343) || i8 > i4 - 3) {
                    if (i8 > i4 - 4) {
                        if (cCharAt2 >= 55296 && cCharAt2 <= 57343 && ((i5 = i7 + 1) == charSequence.length() || !Character.isSurrogatePair(cCharAt2, charSequence.charAt(i5)))) {
                            throw new zzeu(i7, length);
                        }
                        throw new ArrayIndexOutOfBoundsException("Failed writing " + cCharAt2 + " at index " + i8);
                    }
                    int i10 = i7 + 1;
                    if (i10 != charSequence.length()) {
                        char cCharAt3 = charSequence.charAt(i10);
                        if (Character.isSurrogatePair(cCharAt2, cCharAt3)) {
                            int codePoint = Character.toCodePoint(cCharAt2, cCharAt3);
                            bArr[i8] = (byte) ((codePoint >>> 18) | 240);
                            bArr[i8 + 1] = (byte) (((codePoint >>> 12) & 63) | VertexAttributes.Usage.Tangent);
                            int i11 = i8 + 3;
                            bArr[i8 + 2] = (byte) (((codePoint >>> 6) & 63) | VertexAttributes.Usage.Tangent);
                            i8 += 4;
                            bArr[i11] = (byte) ((codePoint & 63) | VertexAttributes.Usage.Tangent);
                            i7 = i10;
                        } else {
                            i7 = i10;
                        }
                    }
                    throw new zzeu(i7 - 1, length);
                }
                bArr[i8] = (byte) ((cCharAt2 >>> '\f') | 480);
                int i12 = i8 + 2;
                bArr[i8 + 1] = (byte) (((cCharAt2 >>> 6) & 63) | VertexAttributes.Usage.Tangent);
                i8 += 3;
                bArr[i12] = (byte) ((cCharAt2 & '?') | VertexAttributes.Usage.Tangent);
            }
            i7++;
        }
        return i8;
    }

    static int zzc(CharSequence charSequence) {
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
                        if (cCharAt2 >= 55296 && cCharAt2 <= 57343) {
                            if (Character.codePointAt(charSequence, i3) < 65536) {
                                throw new zzeu(i3, length2);
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
        throw new IllegalArgumentException("UTF-8 length does not fit in int: " + (((long) i4) + 4294967296L));
    }

    static boolean zzd(byte[] bArr) {
        return zzb.zzb(bArr, 0, bArr.length);
    }

    static boolean zze(byte[] bArr, int i2, int i3) {
        return zzb.zzb(bArr, i2, i3);
    }
}
