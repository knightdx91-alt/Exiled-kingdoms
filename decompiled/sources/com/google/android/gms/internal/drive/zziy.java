package com.google.android.gms.internal.drive;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class zziy {
    private static void zza(String str, Object obj, StringBuffer stringBuffer, StringBuffer stringBuffer2) throws IllegalAccessException {
        if (obj != null) {
            int i2 = 0;
            if (!(obj instanceof zzix)) {
                String strZzj = zzj(str);
                stringBuffer2.append(stringBuffer);
                stringBuffer2.append(strZzj);
                stringBuffer2.append(": ");
                if (obj instanceof String) {
                    String strConcat = (String) obj;
                    if (!strConcat.startsWith("http") && strConcat.length() > 200) {
                        strConcat = String.valueOf(strConcat.substring(0, 200)).concat("[...]");
                    }
                    int length = strConcat.length();
                    StringBuilder sb = new StringBuilder(length);
                    while (i2 < length) {
                        char cCharAt = strConcat.charAt(i2);
                        if (cCharAt < ' ' || cCharAt > '~' || cCharAt == '\"' || cCharAt == '\'') {
                            sb.append(String.format("\\u%04x", Integer.valueOf(cCharAt)));
                        } else {
                            sb.append(cCharAt);
                        }
                        i2++;
                    }
                    String string = sb.toString();
                    stringBuffer2.append("\"");
                    stringBuffer2.append(string);
                    stringBuffer2.append("\"");
                } else if (obj instanceof byte[]) {
                    byte[] bArr = (byte[]) obj;
                    stringBuffer2.append('\"');
                    while (i2 < bArr.length) {
                        int i3 = bArr[i2] & 255;
                        if (i3 == 92 || i3 == 34) {
                            stringBuffer2.append('\\');
                        } else {
                            if (i3 < 32 || i3 >= 127) {
                                stringBuffer2.append(String.format("\\%03o", Integer.valueOf(i3)));
                            }
                            i2++;
                        }
                        stringBuffer2.append((char) i3);
                        i2++;
                    }
                    stringBuffer2.append('\"');
                } else {
                    stringBuffer2.append(obj);
                }
                stringBuffer2.append("\n");
                return;
            }
            int length2 = stringBuffer.length();
            if (str != null) {
                stringBuffer2.append(stringBuffer);
                stringBuffer2.append(zzj(str));
                stringBuffer2.append(" <\n");
                stringBuffer.append("  ");
            }
            Class<?> cls = obj.getClass();
            for (Field field : cls.getFields()) {
                int modifiers = field.getModifiers();
                String name = field.getName();
                if (!"cachedSize".equals(name) && (modifiers & 1) == 1 && (modifiers & 8) != 8 && !name.startsWith("_") && !name.endsWith("_")) {
                    Class<?> type = field.getType();
                    Object obj2 = field.get(obj);
                    if (!type.isArray() || type.getComponentType() == Byte.TYPE) {
                        zza(name, obj2, stringBuffer, stringBuffer2);
                    } else {
                        int length3 = obj2 == null ? 0 : Array.getLength(obj2);
                        for (int i4 = 0; i4 < length3; i4++) {
                            zza(name, Array.get(obj2, i4), stringBuffer, stringBuffer2);
                        }
                    }
                }
            }
            Method[] methods = cls.getMethods();
            int length4 = methods.length;
            while (i2 < length4) {
                String name2 = methods[i2].getName();
                if (name2.startsWith("set")) {
                    String strSubstring = name2.substring(3);
                    try {
                        String strValueOf = String.valueOf(strSubstring);
                        if (((Boolean) cls.getMethod(strValueOf.length() != 0 ? "has".concat(strValueOf) : new String("has"), null).invoke(obj, null)).booleanValue()) {
                            String strValueOf2 = String.valueOf(strSubstring);
                            zza(strSubstring, cls.getMethod(strValueOf2.length() != 0 ? "get".concat(strValueOf2) : new String("get"), null).invoke(obj, null), stringBuffer, stringBuffer2);
                        }
                    } catch (NoSuchMethodException unused) {
                    }
                }
                i2++;
            }
            if (str != null) {
                stringBuffer.setLength(length2);
                stringBuffer2.append(stringBuffer);
                stringBuffer2.append(">\n");
            }
        }
    }

    public static <T extends zzix> String zzb(T t2) {
        if (t2 == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        try {
            zza(null, t2, new StringBuffer(), stringBuffer);
            return stringBuffer.toString();
        } catch (IllegalAccessException e2) {
            String strValueOf = String.valueOf(e2.getMessage());
            return strValueOf.length() != 0 ? "Error printing proto: ".concat(strValueOf) : new String("Error printing proto: ");
        } catch (InvocationTargetException e3) {
            String strValueOf2 = String.valueOf(e3.getMessage());
            return strValueOf2.length() != 0 ? "Error printing proto: ".concat(strValueOf2) : new String("Error printing proto: ");
        }
    }

    private static String zzj(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i2 = 0; i2 < str.length(); i2++) {
            char cCharAt = str.charAt(i2);
            if (i2 == 0) {
                cCharAt = Character.toLowerCase(cCharAt);
            } else if (Character.isUpperCase(cCharAt)) {
                stringBuffer.append('_');
                cCharAt = Character.toLowerCase(cCharAt);
            }
            stringBuffer.append(cCharAt);
        }
        return stringBuffer.toString();
    }
}
