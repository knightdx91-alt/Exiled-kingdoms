package com.google.android.gms.common.server.response;

import a.a;
import android.util.Log;
import com.badlogic.gdx.graphics.GL20;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.server.response.FastJsonResponse;
import com.google.android.gms.common.util.Base64Utils;
import com.google.android.gms.common.util.JsonUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@ShowFirstParty
@KeepForSdk
public class FastParser<T extends FastJsonResponse> {
    private static final char[] zaqf = {'u', 'l', 'l'};
    private static final char[] zaqg = {'r', 'u', 'e'};
    private static final char[] zaqh = {'r', 'u', 'e', '\"'};
    private static final char[] zaqi = {'a', 'l', 's', 'e'};
    private static final char[] zaqj = {'a', 'l', 's', 'e', '\"'};
    private static final char[] zaqk = {'\n'};
    private static final zaa<Integer> zaqm = new com.google.android.gms.common.server.response.zaa();
    private static final zaa<Long> zaqn = new zab();
    private static final zaa<Float> zaqo = new zac();
    private static final zaa<Double> zaqp = new zad();
    private static final zaa<Boolean> zaqq = new zae();
    private static final zaa<String> zaqr = new zaf();
    private static final zaa<BigInteger> zaqs = new zag();
    private static final zaa<BigDecimal> zaqt = new zah();
    private final char[] zaqa = new char[1];
    private final char[] zaqb = new char[32];
    private final char[] zaqc = new char[GL20.GL_STENCIL_BUFFER_BIT];
    private final StringBuilder zaqd = new StringBuilder(32);
    private final StringBuilder zaqe = new StringBuilder(GL20.GL_STENCIL_BUFFER_BIT);
    private final Stack<Integer> zaql = new Stack<>();

    @ShowFirstParty
    @KeepForSdk
    public static class ParseException extends Exception {
        public ParseException(String str) {
            super(str);
        }

        public ParseException(String str, Throwable th) {
            super(str, th);
        }

        public ParseException(Throwable th) {
            super(th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    interface zaa<O> {
        O zah(FastParser fastParser, BufferedReader bufferedReader);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:140:0x028e A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:142:0x0270 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final boolean zaa(BufferedReader bufferedReader, FastJsonResponse fastJsonResponse) throws ParseException, IOException {
        int i2;
        HashMap map;
        char cZaj;
        Map<String, FastJsonResponse.Field<?, ?>> fieldMappings = fastJsonResponse.getFieldMappings();
        String strZaa = zaa(bufferedReader);
        if (strZaa == null) {
            zak(1);
            return false;
        }
        while (strZaa != null) {
            FastJsonResponse.Field<?, ?> field = fieldMappings.get(strZaa);
            if (field == null) {
                strZaa = zab(bufferedReader);
            } else {
                this.zaql.push(4);
                switch (field.zapq) {
                    case 0:
                        if (field.zapr) {
                            fastJsonResponse.zaa((FastJsonResponse.Field) field, (ArrayList<Integer>) zaa(bufferedReader, zaqm));
                        } else {
                            fastJsonResponse.zaa((FastJsonResponse.Field) field, zad(bufferedReader));
                        }
                        i2 = 4;
                        zak(i2);
                        zak(2);
                        cZaj = zaj(bufferedReader);
                        if (cZaj != ',') {
                            strZaa = zaa(bufferedReader);
                        } else {
                            if (cZaj != '}') {
                                StringBuilder sb = new StringBuilder(55);
                                sb.append("Expected end of object or field separator, but found: ");
                                sb.append(cZaj);
                                throw new ParseException(sb.toString());
                            }
                            strZaa = null;
                        }
                        break;
                    case 1:
                        if (field.zapr) {
                            fastJsonResponse.zab((FastJsonResponse.Field) field, (ArrayList<BigInteger>) zaa(bufferedReader, zaqs));
                        } else {
                            fastJsonResponse.zaa((FastJsonResponse.Field) field, zaf(bufferedReader));
                        }
                        i2 = 4;
                        zak(i2);
                        zak(2);
                        cZaj = zaj(bufferedReader);
                        if (cZaj != ',') {
                        }
                        break;
                    case 2:
                        if (field.zapr) {
                            fastJsonResponse.zac(field, zaa(bufferedReader, zaqn));
                        } else {
                            fastJsonResponse.zaa((FastJsonResponse.Field) field, zae(bufferedReader));
                        }
                        i2 = 4;
                        zak(i2);
                        zak(2);
                        cZaj = zaj(bufferedReader);
                        if (cZaj != ',') {
                        }
                        break;
                    case 3:
                        if (field.zapr) {
                            fastJsonResponse.zad(field, zaa(bufferedReader, zaqo));
                        } else {
                            fastJsonResponse.zaa((FastJsonResponse.Field) field, zag(bufferedReader));
                        }
                        i2 = 4;
                        zak(i2);
                        zak(2);
                        cZaj = zaj(bufferedReader);
                        if (cZaj != ',') {
                        }
                        break;
                    case 4:
                        if (field.zapr) {
                            fastJsonResponse.zae(field, zaa(bufferedReader, zaqp));
                        } else {
                            fastJsonResponse.zaa(field, zah(bufferedReader));
                        }
                        i2 = 4;
                        zak(i2);
                        zak(2);
                        cZaj = zaj(bufferedReader);
                        if (cZaj != ',') {
                        }
                        break;
                    case 5:
                        if (field.zapr) {
                            fastJsonResponse.zaf(field, zaa(bufferedReader, zaqt));
                        } else {
                            fastJsonResponse.zaa((FastJsonResponse.Field) field, zai(bufferedReader));
                        }
                        i2 = 4;
                        zak(i2);
                        zak(2);
                        cZaj = zaj(bufferedReader);
                        if (cZaj != ',') {
                        }
                        break;
                    case 6:
                        if (field.zapr) {
                            fastJsonResponse.zag(field, zaa(bufferedReader, zaqq));
                            i2 = 4;
                            zak(i2);
                            zak(2);
                            cZaj = zaj(bufferedReader);
                            if (cZaj != ',') {
                            }
                        } else {
                            fastJsonResponse.zaa(field, zaa(bufferedReader, false));
                            i2 = 4;
                            zak(i2);
                            zak(2);
                            cZaj = zaj(bufferedReader);
                            if (cZaj != ',') {
                            }
                        }
                        break;
                    case 7:
                        if (field.zapr) {
                            fastJsonResponse.zah(field, zaa(bufferedReader, zaqr));
                        } else {
                            fastJsonResponse.zaa((FastJsonResponse.Field) field, zac(bufferedReader));
                        }
                        i2 = 4;
                        zak(i2);
                        zak(2);
                        cZaj = zaj(bufferedReader);
                        if (cZaj != ',') {
                        }
                        break;
                    case 8:
                        fastJsonResponse.zaa((FastJsonResponse.Field) field, Base64Utils.decode(zaa(bufferedReader, this.zaqc, this.zaqe, zaqk)));
                        i2 = 4;
                        zak(i2);
                        zak(2);
                        cZaj = zaj(bufferedReader);
                        if (cZaj != ',') {
                        }
                        break;
                    case 9:
                        fastJsonResponse.zaa((FastJsonResponse.Field) field, Base64Utils.decodeUrlSafe(zaa(bufferedReader, this.zaqc, this.zaqe, zaqk)));
                        i2 = 4;
                        zak(i2);
                        zak(2);
                        cZaj = zaj(bufferedReader);
                        if (cZaj != ',') {
                        }
                        break;
                    case 10:
                        char cZaj2 = zaj(bufferedReader);
                        if (cZaj2 == 'n') {
                            zab(bufferedReader, zaqf);
                            map = null;
                        } else {
                            if (cZaj2 != '{') {
                                throw new ParseException("Expected start of a map object");
                            }
                            this.zaql.push(1);
                            map = new HashMap();
                            while (true) {
                                char cZaj3 = zaj(bufferedReader);
                                if (cZaj3 == 0) {
                                    throw new ParseException("Unexpected EOF");
                                }
                                if (cZaj3 == '\"') {
                                    String strZab = zab(bufferedReader, this.zaqb, this.zaqd, null);
                                    if (zaj(bufferedReader) != ':') {
                                        String strValueOf = String.valueOf(strZab);
                                        throw new ParseException(strValueOf.length() != 0 ? "No map value found for key ".concat(strValueOf) : new String("No map value found for key "));
                                    }
                                    if (zaj(bufferedReader) != '\"') {
                                        String strValueOf2 = String.valueOf(strZab);
                                        throw new ParseException(strValueOf2.length() != 0 ? "Expected String value for key ".concat(strValueOf2) : new String("Expected String value for key "));
                                    }
                                    map.put(strZab, zab(bufferedReader, this.zaqb, this.zaqd, null));
                                    char cZaj4 = zaj(bufferedReader);
                                    if (cZaj4 != ',') {
                                        if (cZaj4 != '}') {
                                            StringBuilder sb2 = new StringBuilder(48);
                                            sb2.append("Unexpected character while parsing string map: ");
                                            sb2.append(cZaj4);
                                            throw new ParseException(sb2.toString());
                                        }
                                        zak(1);
                                    }
                                } else if (cZaj3 == '}') {
                                    zak(1);
                                }
                                i2 = 4;
                                zak(i2);
                                zak(2);
                                cZaj = zaj(bufferedReader);
                                if (cZaj != ',') {
                                }
                            }
                        }
                        fastJsonResponse.zaa((FastJsonResponse.Field) field, (Map<String, String>) map);
                        i2 = 4;
                        zak(i2);
                        zak(2);
                        cZaj = zaj(bufferedReader);
                        if (cZaj != ',') {
                        }
                        break;
                    case 11:
                        if (!field.zapr) {
                            char cZaj5 = zaj(bufferedReader);
                            if (cZaj5 == 'n') {
                                zab(bufferedReader, zaqf);
                                fastJsonResponse.addConcreteTypeInternal(field, field.zapu, null);
                            } else {
                                this.zaql.push(1);
                                if (cZaj5 != '{') {
                                    throw new ParseException("Expected start of object");
                                }
                                try {
                                    FastJsonResponse fastJsonResponseZacp = field.zacp();
                                    zaa(bufferedReader, fastJsonResponseZacp);
                                    fastJsonResponse.addConcreteTypeInternal(field, field.zapu, fastJsonResponseZacp);
                                } catch (IllegalAccessException e2) {
                                    throw new ParseException("Error instantiating inner object", e2);
                                } catch (InstantiationException e3) {
                                    throw new ParseException("Error instantiating inner object", e3);
                                }
                            }
                            break;
                        } else {
                            char cZaj6 = zaj(bufferedReader);
                            if (cZaj6 == 'n') {
                                zab(bufferedReader, zaqf);
                                fastJsonResponse.addConcreteTypeArrayInternal(field, field.zapu, null);
                            } else {
                                this.zaql.push(5);
                                if (cZaj6 != '[') {
                                    throw new ParseException("Expected array start");
                                }
                                fastJsonResponse.addConcreteTypeArrayInternal(field, field.zapu, zaa(bufferedReader, field));
                            }
                        }
                        i2 = 4;
                        zak(i2);
                        zak(2);
                        cZaj = zaj(bufferedReader);
                        if (cZaj != ',') {
                        }
                        break;
                    default:
                        throw new ParseException(a.g(30, field.zapq, "Invalid field type "));
                }
            }
        }
        zak(1);
        return true;
    }

    private final String zab(BufferedReader bufferedReader) throws ParseException, IOException {
        bufferedReader.mark(GL20.GL_STENCIL_BUFFER_BIT);
        char cZaj = zaj(bufferedReader);
        if (cZaj == '\"') {
            if (bufferedReader.read(this.zaqa) == -1) {
                throw new ParseException("Unexpected EOF while parsing string");
            }
            char c2 = this.zaqa[0];
            boolean z2 = false;
            do {
                if (c2 != '\"' || z2) {
                    z2 = c2 == '\\' ? !z2 : false;
                    if (bufferedReader.read(this.zaqa) == -1) {
                        throw new ParseException("Unexpected EOF while parsing string");
                    }
                    c2 = this.zaqa[0];
                }
            } while (!Character.isISOControl(c2));
            throw new ParseException("Unexpected control character while reading string");
        }
        if (cZaj == ',') {
            throw new ParseException("Missing value");
        }
        int i2 = 1;
        if (cZaj == '[') {
            this.zaql.push(5);
            bufferedReader.mark(32);
            if (zaj(bufferedReader) == ']') {
                zak(5);
            } else {
                bufferedReader.reset();
                boolean z3 = false;
                boolean z4 = false;
                while (i2 > 0) {
                    char cZaj2 = zaj(bufferedReader);
                    if (cZaj2 == 0) {
                        throw new ParseException("Unexpected EOF while parsing array");
                    }
                    if (Character.isISOControl(cZaj2)) {
                        throw new ParseException("Unexpected control character while reading array");
                    }
                    if (cZaj2 == '\"' && !z3) {
                        z4 = !z4;
                    }
                    if (cZaj2 == '[' && !z4) {
                        i2++;
                    }
                    if (cZaj2 == ']' && !z4) {
                        i2--;
                    }
                    z3 = (cZaj2 == '\\' && z4) ? !z3 : false;
                }
                zak(5);
            }
        } else if (cZaj != '{') {
            bufferedReader.reset();
            zaa(bufferedReader, this.zaqc);
        } else {
            this.zaql.push(1);
            bufferedReader.mark(32);
            char cZaj3 = zaj(bufferedReader);
            if (cZaj3 == '}') {
                zak(1);
            } else {
                if (cZaj3 != '\"') {
                    StringBuilder sb = new StringBuilder(18);
                    sb.append("Unexpected token ");
                    sb.append(cZaj3);
                    throw new ParseException(sb.toString());
                }
                bufferedReader.reset();
                zaa(bufferedReader);
                while (zab(bufferedReader) != null) {
                }
                zak(1);
            }
        }
        char cZaj4 = zaj(bufferedReader);
        if (cZaj4 == ',') {
            zak(2);
            return zaa(bufferedReader);
        }
        if (cZaj4 == '}') {
            zak(2);
            return null;
        }
        StringBuilder sb2 = new StringBuilder(18);
        sb2.append("Unexpected token ");
        sb2.append(cZaj4);
        throw new ParseException(sb2.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String zac(BufferedReader bufferedReader) {
        return zaa(bufferedReader, this.zaqb, this.zaqd, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int zad(BufferedReader bufferedReader) throws ParseException, IOException {
        int i2;
        int i3;
        int iZaa = zaa(bufferedReader, this.zaqc);
        int i4 = 0;
        if (iZaa == 0) {
            return 0;
        }
        char[] cArr = this.zaqc;
        if (iZaa <= 0) {
            throw new ParseException("No number to parse");
        }
        if (cArr[0] == '-') {
            i2 = Integer.MIN_VALUE;
            i3 = 1;
        } else {
            i2 = -2147483647;
            i3 = 0;
        }
        int i5 = i3;
        if (i3 < iZaa) {
            int i6 = i3 + 1;
            int iDigit = Character.digit(cArr[i3], 10);
            if (iDigit < 0) {
                throw new ParseException("Unexpected non-digit character");
            }
            int i7 = -iDigit;
            i3 = i6;
            i4 = i7;
        }
        while (i3 < iZaa) {
            int i8 = i3 + 1;
            int iDigit2 = Character.digit(cArr[i3], 10);
            if (iDigit2 < 0) {
                throw new ParseException("Unexpected non-digit character");
            }
            if (i4 < -214748364) {
                throw new ParseException("Number too large");
            }
            int i9 = i4 * 10;
            if (i9 < i2 + iDigit2) {
                throw new ParseException("Number too large");
            }
            i4 = i9 - iDigit2;
            i3 = i8;
        }
        if (i5 == 0) {
            return -i4;
        }
        if (i3 > 1) {
            return i4;
        }
        throw new ParseException("No digits to parse");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final long zae(BufferedReader bufferedReader) throws ParseException, IOException {
        long j2;
        int iZaa = zaa(bufferedReader, this.zaqc);
        long j3 = 0;
        if (iZaa == 0) {
            return 0L;
        }
        char[] cArr = this.zaqc;
        if (iZaa <= 0) {
            throw new ParseException("No number to parse");
        }
        int i2 = 0;
        if (cArr[0] == '-') {
            j2 = Long.MIN_VALUE;
            i2 = 1;
        } else {
            j2 = -9223372036854775807L;
        }
        int i3 = i2;
        int i4 = 10;
        if (i2 < iZaa) {
            int i5 = i2 + 1;
            int iDigit = Character.digit(cArr[i2], 10);
            if (iDigit < 0) {
                throw new ParseException("Unexpected non-digit character");
            }
            i2 = i5;
            j3 = -iDigit;
        }
        while (i2 < iZaa) {
            int i6 = i2 + 1;
            int iDigit2 = Character.digit(cArr[i2], i4);
            if (iDigit2 < 0) {
                throw new ParseException("Unexpected non-digit character");
            }
            if (j3 < -922337203685477580L) {
                throw new ParseException("Number too large");
            }
            long j4 = j3 * 10;
            long j5 = iDigit2;
            if (j4 < j2 + j5) {
                throw new ParseException("Number too large");
            }
            j3 = j4 - j5;
            i2 = i6;
            i4 = 10;
        }
        if (i3 == 0) {
            return -j3;
        }
        if (i2 > 1) {
            return j3;
        }
        throw new ParseException("No digits to parse");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final BigInteger zaf(BufferedReader bufferedReader) throws ParseException, IOException {
        int iZaa = zaa(bufferedReader, this.zaqc);
        if (iZaa == 0) {
            return null;
        }
        return new BigInteger(new String(this.zaqc, 0, iZaa));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final float zag(BufferedReader bufferedReader) throws ParseException, IOException {
        int iZaa = zaa(bufferedReader, this.zaqc);
        if (iZaa == 0) {
            return 0.0f;
        }
        return Float.parseFloat(new String(this.zaqc, 0, iZaa));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final double zah(BufferedReader bufferedReader) throws ParseException, IOException {
        int iZaa = zaa(bufferedReader, this.zaqc);
        if (iZaa == 0) {
            return 0.0d;
        }
        return Double.parseDouble(new String(this.zaqc, 0, iZaa));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final BigDecimal zai(BufferedReader bufferedReader) throws ParseException, IOException {
        int iZaa = zaa(bufferedReader, this.zaqc);
        if (iZaa == 0) {
            return null;
        }
        return new BigDecimal(new String(this.zaqc, 0, iZaa));
    }

    private final char zaj(BufferedReader bufferedReader) {
        if (bufferedReader.read(this.zaqa) == -1) {
            return (char) 0;
        }
        while (Character.isWhitespace(this.zaqa[0])) {
            if (bufferedReader.read(this.zaqa) == -1) {
                return (char) 0;
            }
        }
        return this.zaqa[0];
    }

    private final void zak(int i2) throws ParseException {
        if (this.zaql.isEmpty()) {
            StringBuilder sb = new StringBuilder(46);
            sb.append("Expected state ");
            sb.append(i2);
            sb.append(" but had empty stack");
            throw new ParseException(sb.toString());
        }
        int iIntValue = this.zaql.pop().intValue();
        if (iIntValue == i2) {
            return;
        }
        StringBuilder sb2 = new StringBuilder(46);
        sb2.append("Expected state ");
        sb2.append(i2);
        sb2.append(" but had ");
        sb2.append(iIntValue);
        throw new ParseException(sb2.toString());
    }

    @KeepForSdk
    public void parse(InputStream inputStream, T t2) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream), GL20.GL_STENCIL_BUFFER_BIT);
        try {
            try {
                this.zaql.push(0);
                char cZaj = zaj(bufferedReader);
                if (cZaj == 0) {
                    throw new ParseException("No data to parse");
                }
                if (cZaj == '[') {
                    this.zaql.push(5);
                    Map<String, FastJsonResponse.Field<?, ?>> fieldMappings = t2.getFieldMappings();
                    if (fieldMappings.size() != 1) {
                        throw new ParseException("Object array response class must have a single Field");
                    }
                    FastJsonResponse.Field<?, ?> value = fieldMappings.entrySet().iterator().next().getValue();
                    t2.addConcreteTypeArrayInternal(value, value.zapu, zaa(bufferedReader, value));
                } else {
                    if (cZaj != '{') {
                        StringBuilder sb = new StringBuilder(19);
                        sb.append("Unexpected token: ");
                        sb.append(cZaj);
                        throw new ParseException(sb.toString());
                    }
                    this.zaql.push(1);
                    zaa(bufferedReader, t2);
                }
                zak(0);
                try {
                    bufferedReader.close();
                } catch (IOException unused) {
                    Log.w("FastParser", "Failed to close reader while parsing.");
                }
            } catch (IOException e2) {
                throw new ParseException(e2);
            }
        } catch (Throwable th) {
            try {
                bufferedReader.close();
            } catch (IOException unused2) {
                Log.w("FastParser", "Failed to close reader while parsing.");
            }
            throw th;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0031, code lost:
    
        throw new com.google.android.gms.common.server.response.FastParser.ParseException("Unexpected control character while reading string");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static String zab(BufferedReader bufferedReader, char[] cArr, StringBuilder sb, char[] cArr2) throws ParseException, IOException {
        sb.setLength(0);
        bufferedReader.mark(cArr.length);
        boolean z2 = false;
        boolean z3 = false;
        loop0: while (true) {
            int i2 = bufferedReader.read(cArr);
            if (i2 != -1) {
                for (int i3 = 0; i3 < i2; i3++) {
                    char c2 = cArr[i3];
                    if (Character.isISOControl(c2)) {
                        if (cArr2 == null) {
                            break loop0;
                        }
                        for (char c3 : cArr2) {
                            if (c3 != c2) {
                            }
                        }
                        break loop0;
                    }
                    if (c2 == '\"' && !z2) {
                        sb.append(cArr, 0, i3);
                        bufferedReader.reset();
                        bufferedReader.skip(i3 + 1);
                        if (z3) {
                            return JsonUtils.unescapeString(sb.toString());
                        }
                        return sb.toString();
                    }
                    if (c2 == '\\') {
                        z2 = !z2;
                        z3 = true;
                    } else {
                        z2 = false;
                    }
                }
                sb.append(cArr, 0, i2);
                bufferedReader.mark(cArr.length);
            } else {
                throw new ParseException("Unexpected EOF while parsing string");
            }
        }
    }

    private final void zab(BufferedReader bufferedReader, char[] cArr) throws ParseException, IOException {
        int i2 = 0;
        while (i2 < cArr.length) {
            int i3 = bufferedReader.read(this.zaqb, 0, cArr.length - i2);
            if (i3 == -1) {
                throw new ParseException("Unexpected EOF");
            }
            for (int i4 = 0; i4 < i3; i4++) {
                if (cArr[i4 + i2] != this.zaqb[i4]) {
                    throw new ParseException("Unexpected character");
                }
            }
            i2 += i3;
        }
    }

    private final String zaa(BufferedReader bufferedReader) throws ParseException, IOException {
        this.zaql.push(2);
        char cZaj = zaj(bufferedReader);
        if (cZaj == '\"') {
            this.zaql.push(3);
            String strZab = zab(bufferedReader, this.zaqb, this.zaqd, null);
            zak(3);
            if (zaj(bufferedReader) == ':') {
                return strZab;
            }
            throw new ParseException("Expected key/value separator");
        }
        if (cZaj == ']') {
            zak(2);
            zak(1);
            zak(5);
            return null;
        }
        if (cZaj == '}') {
            zak(2);
            return null;
        }
        StringBuilder sb = new StringBuilder(19);
        sb.append("Unexpected token: ");
        sb.append(cZaj);
        throw new ParseException(sb.toString());
    }

    private final <O> ArrayList<O> zaa(BufferedReader bufferedReader, zaa<O> zaaVar) throws ParseException, IOException {
        char cZaj = zaj(bufferedReader);
        if (cZaj == 'n') {
            zab(bufferedReader, zaqf);
            return null;
        }
        if (cZaj == '[') {
            this.zaql.push(5);
            ArrayList<O> arrayList = new ArrayList<>();
            while (true) {
                bufferedReader.mark(GL20.GL_STENCIL_BUFFER_BIT);
                char cZaj2 = zaj(bufferedReader);
                if (cZaj2 == 0) {
                    throw new ParseException("Unexpected EOF");
                }
                if (cZaj2 != ',') {
                    if (cZaj2 != ']') {
                        bufferedReader.reset();
                        arrayList.add(zaaVar.zah(this, bufferedReader));
                    } else {
                        zak(5);
                        return arrayList;
                    }
                }
            }
        } else {
            throw new ParseException("Expected start of array");
        }
    }

    private final String zaa(BufferedReader bufferedReader, char[] cArr, StringBuilder sb, char[] cArr2) throws ParseException, IOException {
        char cZaj = zaj(bufferedReader);
        if (cZaj == '\"') {
            return zab(bufferedReader, cArr, sb, cArr2);
        }
        if (cZaj == 'n') {
            zab(bufferedReader, zaqf);
            return null;
        }
        throw new ParseException("Expected string");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean zaa(BufferedReader bufferedReader, boolean z2) throws ParseException, IOException {
        while (true) {
            char cZaj = zaj(bufferedReader);
            if (cZaj != '\"') {
                if (cZaj == 'f') {
                    zab(bufferedReader, z2 ? zaqj : zaqi);
                    return false;
                }
                if (cZaj == 'n') {
                    zab(bufferedReader, zaqf);
                    return false;
                }
                if (cZaj == 't') {
                    zab(bufferedReader, z2 ? zaqh : zaqg);
                    return true;
                }
                StringBuilder sb = new StringBuilder(19);
                sb.append("Unexpected token: ");
                sb.append(cZaj);
                throw new ParseException(sb.toString());
            }
            if (z2) {
                throw new ParseException("No boolean value found in string");
            }
            z2 = true;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final <T extends FastJsonResponse> ArrayList<T> zaa(BufferedReader bufferedReader, FastJsonResponse.Field<?, ?> field) throws ParseException, IOException {
        ArrayList<T> arrayList = (ArrayList<T>) new ArrayList();
        char cZaj = zaj(bufferedReader);
        if (cZaj == ']') {
            zak(5);
            return arrayList;
        }
        if (cZaj == 'n') {
            zab(bufferedReader, zaqf);
            zak(5);
            return null;
        }
        if (cZaj != '{') {
            StringBuilder sb = new StringBuilder(19);
            sb.append("Unexpected token: ");
            sb.append(cZaj);
            throw new ParseException(sb.toString());
        }
        this.zaql.push(1);
        while (true) {
            try {
                FastJsonResponse fastJsonResponseZacp = field.zacp();
                if (!zaa(bufferedReader, fastJsonResponseZacp)) {
                    return arrayList;
                }
                arrayList.add(fastJsonResponseZacp);
                char cZaj2 = zaj(bufferedReader);
                if (cZaj2 != ',') {
                    if (cZaj2 != ']') {
                        StringBuilder sb2 = new StringBuilder(19);
                        sb2.append("Unexpected token: ");
                        sb2.append(cZaj2);
                        throw new ParseException(sb2.toString());
                    }
                    zak(5);
                    return arrayList;
                }
                if (zaj(bufferedReader) == '{') {
                    this.zaql.push(1);
                } else {
                    throw new ParseException("Expected start of next object in array");
                }
            } catch (IllegalAccessException e2) {
                throw new ParseException("Error instantiating inner object", e2);
            } catch (InstantiationException e3) {
                throw new ParseException("Error instantiating inner object", e3);
            }
        }
    }

    private final int zaa(BufferedReader bufferedReader, char[] cArr) throws ParseException, IOException {
        int i2;
        char cZaj = zaj(bufferedReader);
        if (cZaj == 0) {
            throw new ParseException("Unexpected EOF");
        }
        if (cZaj == ',') {
            throw new ParseException("Missing value");
        }
        if (cZaj == 'n') {
            zab(bufferedReader, zaqf);
            return 0;
        }
        bufferedReader.mark(GL20.GL_STENCIL_BUFFER_BIT);
        if (cZaj == '\"') {
            i2 = 0;
            boolean z2 = false;
            while (i2 < cArr.length && bufferedReader.read(cArr, i2, 1) != -1) {
                char c2 = cArr[i2];
                if (Character.isISOControl(c2)) {
                    throw new ParseException("Unexpected control character while reading string");
                }
                if (c2 == '\"' && !z2) {
                    bufferedReader.reset();
                    bufferedReader.skip(i2 + 1);
                    return i2;
                }
                z2 = c2 == '\\' ? !z2 : false;
                i2++;
            }
        } else {
            cArr[0] = cZaj;
            i2 = 1;
            while (i2 < cArr.length && bufferedReader.read(cArr, i2, 1) != -1) {
                char c3 = cArr[i2];
                if (c3 == '}' || c3 == ',' || Character.isWhitespace(c3) || cArr[i2] == ']') {
                    bufferedReader.reset();
                    bufferedReader.skip(i2 - 1);
                    cArr[i2] = 0;
                    return i2;
                }
                i2++;
            }
        }
        if (i2 == cArr.length) {
            throw new ParseException("Absurdly long value");
        }
        throw new ParseException("Unexpected EOF");
    }
}
