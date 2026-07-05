package com.badlogic.gdx.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.MissingResourceException;

/* JADX INFO: compiled from: I18NBundle.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class n {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final Locale f1846b = new Locale("", "", "");

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Locale f1847a;

    /* JADX WARN: Removed duplicated region for block: B:40:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x008e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static n a(com.badlogic.gdx.files.a aVar, Locale locale) {
        n nVarC;
        if (aVar == null || locale == null) {
            throw null;
        }
        Locale locale2 = locale;
        n nVar = null;
        do {
            String language = locale2.getLanguage();
            String country = locale2.getCountry();
            String variant = locale2.getVariant();
            ArrayList arrayList = new ArrayList(4);
            if (variant.length() > 0) {
                arrayList.add(locale2);
            }
            if (country.length() > 0) {
                arrayList.add(arrayList.isEmpty() ? locale2 : new Locale(language, country));
            }
            if (language.length() > 0) {
                arrayList.add(arrayList.isEmpty() ? locale2 : new Locale(language));
            }
            Locale locale3 = f1846b;
            arrayList.add(locale3);
            nVarC = c(aVar, "UTF-8", arrayList, 0, nVar);
            if (nVarC == null) {
                Locale locale4 = Locale.getDefault();
                locale2 = !locale2.equals(locale4) ? null : locale4;
            } else {
                Locale locale5 = nVarC.f1847a;
                boolean zEquals = locale5.equals(locale3);
                if (!zEquals || locale5.equals(locale) || (arrayList.size() == 1 && locale5.equals(arrayList.get(0)))) {
                    break;
                }
                if (zEquals && nVar == null) {
                    nVar = nVarC;
                }
                Locale locale42 = Locale.getDefault();
                if (!locale2.equals(locale42)) {
                }
            }
        } while (locale2 != null);
        if (nVarC != null) {
            return nVarC;
        }
        if (nVar != null) {
            return nVar;
        }
        throw new MissingResourceException("Can't find bundle for base file handle " + aVar.path() + ", locale " + locale, aVar + "_" + locale, "");
    }

    private static n c(com.badlogic.gdx.files.a aVar, String str, ArrayList arrayList, int i2, n nVar) {
        n nVarC;
        n nVar2;
        Locale locale = (Locale) arrayList.get(i2);
        Reader erVar = null;
        if (i2 != arrayList.size() - 1) {
            nVarC = c(aVar, str, arrayList, i2 + 1, nVar);
        } else {
            if (nVar != null && locale.equals(f1846b)) {
                return nVar;
            }
            nVarC = null;
        }
        try {
            try {
                com.badlogic.gdx.files.a aVarD = d(aVar, locale);
                try {
                    aVarD.read().close();
                    nVar2 = new n();
                    erVar = aVarD.reader(str);
                    nVar2.b(erVar);
                } catch (Exception unused) {
                    nVar2 = null;
                }
                if (nVar2 != null) {
                    nVar2.f1847a = locale;
                    new o0();
                    new MessageFormat("", locale);
                }
                return nVar2 != null ? nVar2 : nVarC;
            } catch (IOException e2) {
                throw new m(e2);
            }
        } finally {
            n0.a(erVar);
        }
    }

    private static com.badlogic.gdx.files.a d(com.badlogic.gdx.files.a aVar, Locale locale) {
        o0 o0Var = new o0(aVar.name());
        if (!locale.equals(f1846b)) {
            String language = locale.getLanguage();
            String country = locale.getCountry();
            String variant = locale.getVariant();
            boolean zEquals = "".equals(language);
            boolean zEquals2 = "".equals(country);
            boolean zEquals3 = "".equals(variant);
            if (!zEquals || !zEquals2 || !zEquals3) {
                o0Var.g('_');
                if (!zEquals3) {
                    o0Var.h(language);
                    o0Var.g('_');
                    o0Var.h(country);
                    o0Var.g('_');
                    o0Var.h(variant);
                } else if (zEquals2) {
                    o0Var.h(language);
                } else {
                    o0Var.h(language);
                    o0Var.g('_');
                    o0Var.h(country);
                }
            }
        }
        o0Var.h(".properties");
        return aVar.sibling(o0Var.toString());
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x0113  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x0119  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x0133  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x0135  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x0111 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00b8 A[PHI: r12
      0x00b8: PHI (r12v8 char) = (r12v2 char), (r12v3 char), (r12v4 char), (r12v5 char), (r12v6 char), (r12v1 char), (r12v1 char), (r12v1 char) binds: [B:59:0x00b6, B:58:0x00b3, B:57:0x00b1, B:56:0x00af, B:55:0x00ac, B:136:0x00b8, B:101:0x0111, B:103:0x0114] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0102  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected final void b(Reader reader) throws IOException {
        char c2;
        y yVar = new y();
        if (reader == null) {
            throw new NullPointerException("reader cannot be null");
        }
        char[] cArr = new char[40];
        BufferedReader bufferedReader = new BufferedReader(reader);
        int i2 = 1;
        int i3 = 0;
        boolean z2 = true;
        int i4 = -1;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        while (true) {
            int i9 = bufferedReader.read();
            if (i9 == -1) {
                if (i6 == 2 && i7 <= 4) {
                    throw new IllegalArgumentException("Invalid Unicode sequence: expected format \\uxxxx");
                }
                if (i4 == -1 && i5 > 0) {
                    i4 = i5;
                }
                if (i4 >= 0) {
                    String str = new String(cArr, i3, i5);
                    String strSubstring = str.substring(i3, i4);
                    String strSubstring2 = str.substring(i4);
                    if (i6 == i2) {
                        strSubstring2 = a.a.k(strSubstring2, "\u0000");
                    }
                    yVar.j(strSubstring, strSubstring2);
                    return;
                }
                return;
            }
            char c3 = (char) i9;
            if (i5 == cArr.length) {
                char[] cArr2 = new char[cArr.length * 2];
                System.arraycopy(cArr, i3, cArr2, i3, i5);
                cArr = cArr2;
            }
            if (i6 == 2) {
                int iDigit = Character.digit(c3, 16);
                if (iDigit >= 0) {
                    i8 = (i8 << 4) + iDigit;
                    i7++;
                    if (i7 < 4) {
                    }
                } else if (i7 <= 4) {
                    throw new IllegalArgumentException("Invalid Unicode sequence: illegal character");
                }
                cArr[i5] = (char) i8;
                i5++;
                i6 = i3;
                if (c3 != '\n') {
                }
            }
            if (i6 == i2) {
                if (c3 == '\n') {
                    i6 = 5;
                    i3 = 0;
                } else if (c3 != '\r') {
                    if (c3 == 'b') {
                        c3 = '\b';
                    } else if (c3 == 'f') {
                        c3 = '\f';
                    } else if (c3 == 'n') {
                        c3 = '\n';
                    } else if (c3 == 'r') {
                        c3 = '\r';
                    } else if (c3 == 't') {
                        c3 = '\t';
                    } else if (c3 == 'u') {
                        i3 = 0;
                        i6 = 2;
                        i7 = 0;
                        i8 = 0;
                    }
                    i6 = 0;
                    if (i6 == 4) {
                        i4 = i5;
                        i6 = 0;
                    }
                    cArr[i5] = c3;
                    i5++;
                    i2 = 1;
                    i3 = 0;
                    z2 = false;
                } else {
                    i3 = 0;
                    i6 = 3;
                }
            } else if (c3 != '\n') {
                if (c3 == '\r') {
                    if (i5 <= 0 || (i5 == 0 && i4 == 0)) {
                        if (i4 == -1) {
                            i4 = i5;
                        }
                        i3 = 0;
                        String str2 = new String(cArr, 0, i5);
                        yVar.j(str2.substring(0, i4), str2.substring(i4));
                    } else {
                        i3 = 0;
                    }
                    i4 = -1;
                    i5 = i3;
                    i6 = i5;
                    i2 = 1;
                    z2 = true;
                } else if (c3 == '!' || c3 == '#') {
                    if (z2) {
                        do {
                            int i10 = bufferedReader.read();
                            if (i10 == -1 || (c2 = (char) i10) == '\r') {
                                break;
                            }
                        } while (c2 != '\n');
                    } else {
                        if (Character.isSpace(c3)) {
                            if (i6 == 3) {
                                i6 = 5;
                            }
                            if (i5 != 0 && i5 != i4 && i6 != 5) {
                                if (i4 == -1) {
                                    i6 = 4;
                                }
                            }
                        }
                        if (i6 == 5 || i6 == 3) {
                            i6 = 0;
                        }
                        if (i6 == 4) {
                        }
                        cArr[i5] = c3;
                        i5++;
                        i2 = 1;
                        i3 = 0;
                        z2 = false;
                    }
                    i2 = 1;
                    i3 = 0;
                } else if (c3 == ':' || c3 == '=') {
                    if (i4 == -1) {
                        i4 = i5;
                        i2 = 1;
                        i3 = 0;
                        i6 = 0;
                    } else {
                        if (Character.isSpace(c3)) {
                        }
                        if (i6 == 5) {
                            i6 = 0;
                            if (i6 == 4) {
                            }
                            cArr[i5] = c3;
                            i5++;
                            i2 = 1;
                            i3 = 0;
                            z2 = false;
                        }
                    }
                } else if (c3 != '\\') {
                    if (Character.isSpace(c3)) {
                    }
                    if (i6 == 5) {
                    }
                } else {
                    if (i6 == 4) {
                        i4 = i5;
                    }
                    i2 = 1;
                    i3 = 0;
                    i6 = 1;
                }
            } else if (i6 == 3) {
                i6 = 5;
                i2 = 1;
                i3 = 0;
            } else if (i5 <= 0) {
                if (i4 == -1) {
                }
                i3 = 0;
                String str22 = new String(cArr, 0, i5);
                yVar.j(str22.substring(0, i4), str22.substring(i4));
                i4 = -1;
                i5 = i3;
                i6 = i5;
                i2 = 1;
                z2 = true;
            }
        }
    }
}
