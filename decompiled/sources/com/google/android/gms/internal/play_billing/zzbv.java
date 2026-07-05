package com.google.android.gms.internal.play_billing;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.0.1 */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
abstract class zzbv {
    private static final Logger zza = Logger.getLogger(zzbi.class.getName());
    private static final String zzb = "com.google.protobuf.BlazeGeneratedExtensionRegistryLiteLoader";

    zzbv() {
    }

    static zzbn zzb(Class cls) {
        String str;
        ClassLoader classLoader = zzbv.class.getClassLoader();
        if (cls.equals(zzbn.class)) {
            str = zzb;
        } else {
            if (!cls.getPackage().equals(zzbv.class.getPackage())) {
                throw new IllegalArgumentException(cls.getName());
            }
            str = cls.getPackage().getName() + ".BlazeGenerated" + cls.getSimpleName() + "Loader";
        }
        try {
            try {
                try {
                    return (zzbn) cls.cast(((zzbv) Class.forName(str, true, classLoader).getConstructor(null).newInstance(null)).zza());
                } catch (InstantiationException e2) {
                    throw new IllegalStateException(e2);
                } catch (InvocationTargetException e3) {
                    throw new IllegalStateException(e3);
                }
            } catch (IllegalAccessException e4) {
                throw new IllegalStateException(e4);
            } catch (NoSuchMethodException e5) {
                throw new IllegalStateException(e5);
            }
        } catch (ClassNotFoundException unused) {
            Iterator it = ServiceLoader.load(zzbv.class, classLoader).iterator();
            ArrayList arrayList = new ArrayList();
            while (it.hasNext()) {
                try {
                    arrayList.add((zzbn) cls.cast(((zzbv) it.next()).zza()));
                } catch (ServiceConfigurationError e6) {
                    zza.logp(Level.SEVERE, "com.google.protobuf.GeneratedExtensionRegistryLoader", "load", "Unable to load ".concat(cls.getSimpleName()), (Throwable) e6);
                }
            }
            if (arrayList.size() == 1) {
                return (zzbn) arrayList.get(0);
            }
            if (arrayList.size() == 0) {
                return null;
            }
            try {
                return (zzbn) cls.getMethod("combine", Collection.class).invoke(null, arrayList);
            } catch (IllegalAccessException e7) {
                throw new IllegalStateException(e7);
            } catch (NoSuchMethodException e8) {
                throw new IllegalStateException(e8);
            } catch (InvocationTargetException e9) {
                throw new IllegalStateException(e9);
            }
        }
    }

    protected abstract zzbn zza();
}
