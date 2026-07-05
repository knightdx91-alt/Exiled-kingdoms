package com.google.android.gms.common.images;

import a.a;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class Size {
    private final int zand;
    private final int zane;

    public Size(int i2, int i3) {
        this.zand = i2;
        this.zane = i3;
    }

    public static Size parseSize(String str) {
        if (str == null) {
            throw new IllegalArgumentException("string must not be null");
        }
        int iIndexOf = str.indexOf(42);
        if (iIndexOf < 0) {
            iIndexOf = str.indexOf(120);
        }
        if (iIndexOf < 0) {
            throw zah(str);
        }
        try {
            return new Size(Integer.parseInt(str.substring(0, iIndexOf)), Integer.parseInt(str.substring(iIndexOf + 1)));
        } catch (NumberFormatException unused) {
            throw zah(str);
        }
    }

    private static NumberFormatException zah(String str) {
        StringBuilder sb = new StringBuilder(a.e(16, str));
        sb.append("Invalid Size: \"");
        sb.append(str);
        sb.append("\"");
        throw new NumberFormatException(sb.toString());
    }

    public final boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (obj instanceof Size) {
            Size size = (Size) obj;
            if (this.zand == size.zand && this.zane == size.zane) {
                return true;
            }
        }
        return false;
    }

    public final int getHeight() {
        return this.zane;
    }

    public final int getWidth() {
        return this.zand;
    }

    public final int hashCode() {
        int i2 = this.zane;
        int i3 = this.zand;
        return i2 ^ ((i3 >>> 16) | (i3 << 16));
    }

    public final String toString() {
        int i2 = this.zand;
        int i3 = this.zane;
        StringBuilder sb = new StringBuilder(23);
        sb.append(i2);
        sb.append("x");
        sb.append(i3);
        return sb.toString();
    }
}
