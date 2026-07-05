package e0;

import d0.b;

/* JADX INFO: compiled from: Iso8601DurationStringToFreeTrialPeriodConverter.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class a {
    public static d0.b a(String str) {
        b.a aVar;
        int i2 = Integer.parseInt(str.substring(1, str.length() - 1));
        char cCharAt = str.substring(str.length() - 1).charAt(0);
        if (cCharAt == 'D') {
            aVar = b.a.f2135a;
        } else if (cCharAt == 'M') {
            aVar = b.a.f2136b;
        } else if (cCharAt == 'W') {
            aVar = b.a.f2137c;
        } else {
            if (cCharAt != 'Y') {
                throw new IllegalArgumentException("Character not mapped to PeriodUnit: " + cCharAt);
            }
            aVar = b.a.f2138d;
        }
        return new d0.b(i2, aVar);
    }
}
