package com.badlogic.gdx.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.zip.CRC32;

/* JADX INFO: compiled from: SharedLibraryLoader.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class i0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static boolean f1815a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static boolean f1816b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static boolean f1817c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static boolean f1818d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static boolean f1819e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static boolean f1820f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static boolean f1821g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private static final HashSet<String> f1822h;

    static {
        f1815a = System.getProperty("os.name").contains("Windows");
        f1816b = System.getProperty("os.name").contains("Linux");
        f1817c = System.getProperty("os.name").contains("Mac");
        f1818d = false;
        f1819e = false;
        f1820f = System.getProperty("os.arch").startsWith("arm") || System.getProperty("os.arch").startsWith("aarch64");
        f1821g = System.getProperty("os.arch").contains("64") || System.getProperty("os.arch").startsWith("armv8");
        String property = System.getProperty("java.runtime.name");
        if (property != null && property.contains("Android Runtime")) {
            f1819e = true;
            f1815a = false;
            f1816b = false;
            f1817c = false;
            f1821g = false;
        }
        if (!f1819e && !f1815a && !f1816b && !f1817c) {
            f1818d = true;
            f1819e = false;
            f1815a = false;
            f1816b = false;
            f1817c = false;
            f1821g = false;
        }
        f1822h = new HashSet<>();
    }

    public static String a(InputStream inputStream) {
        CRC32 crc32 = new CRC32();
        byte[] bArr = new byte[4096];
        while (true) {
            try {
                int i2 = inputStream.read(bArr);
                if (i2 == -1) {
                    break;
                }
                crc32.update(bArr, 0, i2);
            } catch (Exception unused) {
            } catch (Throwable th) {
                n0.a(inputStream);
                throw th;
            }
        }
        n0.a(inputStream);
        return Long.toString(crc32.getValue(), 16);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v15 */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r0v6, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v2, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r8v0, types: [java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r8v1 */
    /* JADX WARN: Type inference failed for: r8v4 */
    /* JADX WARN: Type inference failed for: r8v5, types: [java.io.Closeable, java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r8v6, types: [boolean] */
    private static void b(String str, File file, String str2) throws Throwable {
        String strA;
        ?? r02;
        IOException e2;
        ?? r1 = 0;
        if (file.exists()) {
            try {
                strA = a(new FileInputStream(file));
            } catch (FileNotFoundException unused) {
                strA = null;
            }
        } else {
            strA = null;
        }
        if (strA != null && (str2 = strA.equals(str2)) != 0) {
            return;
        }
        try {
            try {
                str2 = g(str);
            } catch (Throwable th) {
                th = th;
            }
            try {
                file.getParentFile().mkdirs();
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                try {
                    byte[] bArr = new byte[4096];
                    while (true) {
                        int i2 = str2.read(bArr);
                        if (i2 == -1) {
                            n0.a(str2);
                            n0.a(fileOutputStream);
                            return;
                        }
                        fileOutputStream.write(bArr, 0, i2);
                    }
                } catch (IOException e3) {
                    e2 = e3;
                    throw new m("Error extracting file: " + str + "\nTo: " + file.getAbsolutePath(), (Throwable) e2);
                }
            } catch (IOException e4) {
                e2 = e4;
            } catch (Throwable th2) {
                th = th2;
                strA = null;
                r1 = str2;
                r02 = strA;
                n0.a(r1);
                n0.a(r02);
                throw th;
            }
        } catch (IOException e5) {
            e2 = e5;
        } catch (Throwable th3) {
            th = th3;
            r02 = 0;
            n0.a(r1);
            n0.a(r02);
            throw th;
        }
    }

    public static void c(String str) {
        HashSet<String> hashSet;
        boolean zContains;
        String strF;
        if (f1818d) {
            return;
        }
        synchronized (i0.class) {
            try {
                synchronized (i0.class) {
                    hashSet = f1822h;
                    zContains = hashSet.contains(str);
                }
            } catch (Throwable th) {
                StringBuilder sb = new StringBuilder();
                sb.append("Couldn't load shared library '");
                sb.append(strF);
                sb.append("' for target: ");
                sb.append(System.getProperty("os.name"));
                sb.append(f1821g ? ", 64-bit" : ", 32-bit");
                throw new m(sb.toString(), th);
            } finally {
            }
        }
        if (zContains) {
            return;
        }
        strF = f(str);
        if (f1819e) {
            System.loadLibrary(strF);
        } else {
            e(strF);
        }
        synchronized (i0.class) {
            hashSet.add(str);
        }
    }

    private static Throwable d(String str, File file, String str2) {
        try {
            b(str, file, str2);
            System.load(file.getAbsolutePath());
            return null;
        } catch (Throwable th) {
            return th;
        }
    }

    private static void e(String str) {
        String strA = a(g(str));
        String name = new File(str).getName();
        Throwable thD = d(str, new File(System.getProperty("java.io.tmpdir") + "/libgdx" + System.getProperty("user.name") + "/" + strA, name), strA);
        if (thD == null) {
            return;
        }
        try {
            File fileCreateTempFile = File.createTempFile(strA, null);
            if (fileCreateTempFile.delete()) {
                if (d(str, fileCreateTempFile, strA) == null) {
                    return;
                }
            }
        } catch (Throwable unused) {
        }
        if (d(str, new File(System.getProperty("user.home") + "/.libgdx/" + strA, name), strA) == null || d(str, new File(a.a.A(".temp/", strA), name), strA) == null) {
            return;
        }
        File file = new File(System.getProperty("java.library.path"), str);
        if (!file.exists()) {
            throw new m(thD);
        }
        System.load(file.getAbsolutePath());
    }

    public static String f(String str) {
        boolean z2 = f1815a;
        boolean z3 = f1821g;
        if (z2) {
            return str.concat(z3 ? "64.dll" : ".dll");
        }
        if (f1816b) {
            StringBuilder sb = new StringBuilder("lib");
            sb.append(str);
            sb.append(f1820f ? "arm" : "");
            sb.append(z3 ? "64.so" : ".so");
            return sb.toString();
        }
        if (!f1817c) {
            return str;
        }
        StringBuilder sb2 = new StringBuilder("lib");
        sb2.append(str);
        sb2.append(z3 ? "64.dylib" : ".dylib");
        return sb2.toString();
    }

    private static InputStream g(String str) {
        InputStream resourceAsStream = i0.class.getResourceAsStream("/" + str);
        if (resourceAsStream != null) {
            return resourceAsStream;
        }
        throw new m(a.a.A("Unable to read file for extraction: ", str));
    }
}
