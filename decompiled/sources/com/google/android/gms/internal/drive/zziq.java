package com.google.android.gms.internal.drive;

import java.io.IOException;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zziq extends IOException {
    zziq(int i2, int i3) {
        StringBuilder sb = new StringBuilder(108);
        sb.append("CodedOutputStream was writing to a flat byte array and ran out of space (pos ");
        sb.append(i2);
        sb.append(" limit ");
        sb.append(i3);
        sb.append(").");
        super(sb.toString());
    }
}
