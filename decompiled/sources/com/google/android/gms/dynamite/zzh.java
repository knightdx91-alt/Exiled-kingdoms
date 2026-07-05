package com.google.android.gms.dynamite;

import dalvik.system.PathClassLoader;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
final class zzh extends PathClassLoader {
    zzh(String str, ClassLoader classLoader) {
        super(str, classLoader);
    }

    @Override // java.lang.ClassLoader
    protected final Class<?> loadClass(String str, boolean z2) {
        if (!str.startsWith("java.") && !str.startsWith("android.")) {
            try {
                return findClass(str);
            } catch (ClassNotFoundException unused) {
            }
        }
        return super.loadClass(str, z2);
    }
}
