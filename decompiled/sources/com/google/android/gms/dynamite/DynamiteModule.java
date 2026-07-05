package com.google.android.gms.dynamite;

import a.a;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.CrashUtils;
import com.google.android.gms.common.util.DynamiteApi;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
@KeepForSdk
public final class DynamiteModule {
    private static Boolean zzid = null;
    private static zzi zzie = null;
    private static zzk zzif = null;
    private static String zzig = null;
    private static int zzih = -1;
    private final Context zzim;
    private static final ThreadLocal<zza> zzii = new ThreadLocal<>();
    private static final VersionPolicy.zza zzij = new com.google.android.gms.dynamite.zza();

    @KeepForSdk
    public static final VersionPolicy PREFER_REMOTE = new com.google.android.gms.dynamite.zzb();
    private static final VersionPolicy zzik = new zzc();

    @KeepForSdk
    public static final VersionPolicy PREFER_HIGHEST_OR_LOCAL_VERSION = new zzd();

    @KeepForSdk
    public static final VersionPolicy PREFER_HIGHEST_OR_LOCAL_VERSION_NO_FORCE_STAGING = new zze();

    @KeepForSdk
    public static final VersionPolicy PREFER_HIGHEST_OR_REMOTE_VERSION = new zzf();
    private static final VersionPolicy zzil = new zzg();

    @DynamiteApi
    public static class DynamiteLoaderClassLoader {
        public static ClassLoader sClassLoader;
    }

    @KeepForSdk
    public static class LoadingException extends Exception {
        private LoadingException(String str) {
            super(str);
        }

        private LoadingException(String str, Throwable th) {
            super(str, th);
        }

        /* synthetic */ LoadingException(String str, com.google.android.gms.dynamite.zza zzaVar) {
            this(str);
        }

        /* synthetic */ LoadingException(String str, Throwable th, com.google.android.gms.dynamite.zza zzaVar) {
            this(str, th);
        }
    }

    public interface VersionPolicy {

        public interface zza {
            int getLocalVersion(Context context, String str);

            int zza(Context context, String str, boolean z2);
        }

        public static class zzb {
            public int zziq = 0;
            public int zzir = 0;
            public int zzis = 0;
        }

        zzb zza(Context context, String str, zza zzaVar);
    }

    private static class zza {
        public Cursor zzin;

        private zza() {
        }

        /* synthetic */ zza(com.google.android.gms.dynamite.zza zzaVar) {
            this();
        }
    }

    private static class zzb implements VersionPolicy.zza {
        private final int zzio;
        private final int zzip = 0;

        public zzb(int i2, int i3) {
            this.zzio = i2;
        }

        @Override // com.google.android.gms.dynamite.DynamiteModule.VersionPolicy.zza
        public final int getLocalVersion(Context context, String str) {
            return this.zzio;
        }

        @Override // com.google.android.gms.dynamite.DynamiteModule.VersionPolicy.zza
        public final int zza(Context context, String str, boolean z2) {
            return 0;
        }
    }

    private DynamiteModule(Context context) {
        this.zzim = (Context) Preconditions.checkNotNull(context);
    }

    @KeepForSdk
    public static int getLocalVersion(Context context, String str) {
        try {
            ClassLoader classLoader = context.getApplicationContext().getClassLoader();
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 61);
            sb.append("com.google.android.gms.dynamite.descriptors.");
            sb.append(str);
            sb.append(".ModuleDescriptor");
            Class<?> clsLoadClass = classLoader.loadClass(sb.toString());
            Field declaredField = clsLoadClass.getDeclaredField("MODULE_ID");
            Field declaredField2 = clsLoadClass.getDeclaredField("MODULE_VERSION");
            if (declaredField.get(null).equals(str)) {
                return declaredField2.getInt(null);
            }
            String strValueOf = String.valueOf(declaredField.get(null));
            StringBuilder sb2 = new StringBuilder(strValueOf.length() + 51 + String.valueOf(str).length());
            sb2.append("Module descriptor id '");
            sb2.append(strValueOf);
            sb2.append("' didn't match expected id '");
            sb2.append(str);
            sb2.append("'");
            Log.e("DynamiteModule", sb2.toString());
            return 0;
        } catch (ClassNotFoundException unused) {
            StringBuilder sb3 = new StringBuilder(a.e(45, str));
            sb3.append("Local module descriptor class for ");
            sb3.append(str);
            sb3.append(" not found.");
            Log.w("DynamiteModule", sb3.toString());
            return 0;
        } catch (Exception e2) {
            String strValueOf2 = String.valueOf(e2.getMessage());
            Log.e("DynamiteModule", strValueOf2.length() != 0 ? "Failed to load module descriptor class: ".concat(strValueOf2) : new String("Failed to load module descriptor class: "));
            return 0;
        }
    }

    @KeepForSdk
    public static int getRemoteVersion(Context context, String str) {
        return zza(context, str, false);
    }

    @KeepForSdk
    public static DynamiteModule load(Context context, VersionPolicy versionPolicy, String str) {
        ThreadLocal<zza> threadLocal = zzii;
        zza zzaVar = threadLocal.get();
        com.google.android.gms.dynamite.zza zzaVar2 = null;
        zza zzaVar3 = new zza(zzaVar2);
        threadLocal.set(zzaVar3);
        try {
            VersionPolicy.zzb zzbVarZza = versionPolicy.zza(context, str, zzij);
            int i2 = zzbVarZza.zziq;
            int i3 = zzbVarZza.zzir;
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 68 + String.valueOf(str).length());
            sb.append("Considering local module ");
            sb.append(str);
            sb.append(":");
            sb.append(i2);
            sb.append(" and remote module ");
            sb.append(str);
            sb.append(":");
            sb.append(i3);
            Log.i("DynamiteModule", sb.toString());
            int i4 = zzbVarZza.zzis;
            if (i4 != 0 && (i4 != -1 || zzbVarZza.zziq != 0)) {
                if (i4 != 1 || zzbVarZza.zzir != 0) {
                    if (i4 == -1) {
                        DynamiteModule dynamiteModuleZze = zze(context, str);
                        Cursor cursor = zzaVar3.zzin;
                        if (cursor != null) {
                            cursor.close();
                        }
                        threadLocal.set(zzaVar);
                        return dynamiteModuleZze;
                    }
                    if (i4 != 1) {
                        int i5 = zzbVarZza.zzis;
                        StringBuilder sb2 = new StringBuilder(47);
                        sb2.append("VersionPolicy returned invalid code:");
                        sb2.append(i5);
                        throw new LoadingException(sb2.toString(), zzaVar2);
                    }
                    try {
                        DynamiteModule dynamiteModuleZza = zza(context, str, zzbVarZza.zzir);
                        Cursor cursor2 = zzaVar3.zzin;
                        if (cursor2 != null) {
                            cursor2.close();
                        }
                        threadLocal.set(zzaVar);
                        return dynamiteModuleZza;
                    } catch (LoadingException e2) {
                        String strValueOf = String.valueOf(e2.getMessage());
                        Log.w("DynamiteModule", strValueOf.length() != 0 ? "Failed to load remote module: ".concat(strValueOf) : new String("Failed to load remote module: "));
                        int i6 = zzbVarZza.zziq;
                        if (i6 == 0 || versionPolicy.zza(context, str, new zzb(i6, 0)).zzis != -1) {
                            throw new LoadingException("Remote load failed. No local fallback found.", e2, zzaVar2);
                        }
                        DynamiteModule dynamiteModuleZze2 = zze(context, str);
                        Cursor cursor3 = zzaVar3.zzin;
                        if (cursor3 != null) {
                            cursor3.close();
                        }
                        zzii.set(zzaVar);
                        return dynamiteModuleZze2;
                    }
                }
            }
            int i7 = zzbVarZza.zziq;
            int i8 = zzbVarZza.zzir;
            StringBuilder sb3 = new StringBuilder(91);
            sb3.append("No acceptable module found. Local version is ");
            sb3.append(i7);
            sb3.append(" and remote version is ");
            sb3.append(i8);
            sb3.append(".");
            throw new LoadingException(sb3.toString(), zzaVar2);
        } catch (Throwable th) {
            Cursor cursor4 = zzaVar3.zzin;
            if (cursor4 != null) {
                cursor4.close();
            }
            zzii.set(zzaVar);
            throw th;
        }
    }

    public static int zza(Context context, String str, boolean z2) {
        Class<?> clsLoadClass;
        Field declaredField;
        Boolean bool;
        try {
            synchronized (DynamiteModule.class) {
                Boolean bool2 = zzid;
                if (bool2 == null) {
                    try {
                        clsLoadClass = context.getApplicationContext().getClassLoader().loadClass(DynamiteLoaderClassLoader.class.getName());
                        declaredField = clsLoadClass.getDeclaredField("sClassLoader");
                    } catch (ClassNotFoundException | IllegalAccessException | NoSuchFieldException e2) {
                        String strValueOf = String.valueOf(e2);
                        StringBuilder sb = new StringBuilder(strValueOf.length() + 30);
                        sb.append("Failed to load module via V2: ");
                        sb.append(strValueOf);
                        Log.w("DynamiteModule", sb.toString());
                        bool2 = Boolean.FALSE;
                    }
                    synchronized (clsLoadClass) {
                        ClassLoader classLoader = (ClassLoader) declaredField.get(null);
                        if (classLoader != null) {
                            if (classLoader == ClassLoader.getSystemClassLoader()) {
                                bool = Boolean.FALSE;
                            } else {
                                try {
                                    zza(classLoader);
                                } catch (LoadingException unused) {
                                }
                                bool = Boolean.TRUE;
                            }
                        } else if ("com.google.android.gms".equals(context.getApplicationContext().getPackageName())) {
                            declaredField.set(null, ClassLoader.getSystemClassLoader());
                            bool = Boolean.FALSE;
                        } else {
                            try {
                                int iZzc = zzc(context, str, z2);
                                String str2 = zzig;
                                if (str2 != null && !str2.isEmpty()) {
                                    zzh zzhVar = new zzh(zzig, ClassLoader.getSystemClassLoader());
                                    zza(zzhVar);
                                    declaredField.set(null, zzhVar);
                                    zzid = Boolean.TRUE;
                                    return iZzc;
                                }
                                return iZzc;
                            } catch (LoadingException unused2) {
                                declaredField.set(null, ClassLoader.getSystemClassLoader());
                                bool = Boolean.FALSE;
                            }
                        }
                        bool2 = bool;
                        zzid = bool2;
                    }
                }
                if (!bool2.booleanValue()) {
                    return zzb(context, str, z2);
                }
                try {
                    return zzc(context, str, z2);
                } catch (LoadingException e3) {
                    String strValueOf2 = String.valueOf(e3.getMessage());
                    Log.w("DynamiteModule", strValueOf2.length() != 0 ? "Failed to retrieve remote module version: ".concat(strValueOf2) : new String("Failed to retrieve remote module version: "));
                    return 0;
                }
            }
        } catch (Throwable th) {
            CrashUtils.addDynamiteErrorToDropBox(context, th);
            throw th;
        }
    }

    private static Boolean zzai() {
        Boolean boolValueOf;
        synchronized (DynamiteModule.class) {
            boolValueOf = Boolean.valueOf(zzih >= 2);
        }
        return boolValueOf;
    }

    private static int zzb(Context context, String str, boolean z2) {
        zzi zziVarZzj = zzj(context);
        if (zziVarZzj == null) {
            return 0;
        }
        try {
            if (zziVarZzj.zzaj() >= 2) {
                return zziVarZzj.zzb(ObjectWrapper.wrap(context), str, z2);
            }
            Log.w("DynamiteModule", "IDynamite loader version < 2, falling back to getModuleVersion2");
            return zziVarZzj.zza(ObjectWrapper.wrap(context), str, z2);
        } catch (RemoteException e2) {
            String strValueOf = String.valueOf(e2.getMessage());
            Log.w("DynamiteModule", strValueOf.length() != 0 ? "Failed to retrieve remote module version: ".concat(strValueOf) : new String("Failed to retrieve remote module version: "));
            return 0;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:38:0x008e  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00b6  */
    /* JADX WARN: Type inference failed for: r0v0, types: [com.google.android.gms.dynamite.zza] */
    /* JADX WARN: Type inference failed for: r0v1, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v3 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static int zzc(Context context, String str, boolean z2) throws Throwable {
        Cursor cursor;
        ?? r02 = 0;
        Cursor cursor2 = null;
        try {
            ContentResolver contentResolver = context.getContentResolver();
            String str2 = z2 ? "api_force_staging" : "api";
            StringBuilder sb = new StringBuilder(str2.length() + 42 + String.valueOf(str).length());
            sb.append("content://com.google.android.gms.chimera/");
            sb.append(str2);
            sb.append("/");
            sb.append(str);
            Cursor cursorQuery = contentResolver.query(Uri.parse(sb.toString()), null, null, null, null);
            if (cursorQuery != null) {
                try {
                    if (cursorQuery.moveToFirst()) {
                        int i2 = cursorQuery.getInt(0);
                        if (i2 > 0) {
                            synchronized (DynamiteModule.class) {
                                try {
                                    zzig = cursorQuery.getString(2);
                                    int columnIndex = cursorQuery.getColumnIndex("loaderVersion");
                                    if (columnIndex >= 0) {
                                        zzih = cursorQuery.getInt(columnIndex);
                                    }
                                } finally {
                                }
                            }
                            zza zzaVar = zzii.get();
                            if (zzaVar == null || zzaVar.zzin != null) {
                                cursor2 = cursorQuery;
                            } else {
                                zzaVar.zzin = cursorQuery;
                            }
                        }
                        if (cursor2 != null) {
                            cursor2.close();
                        }
                        return i2;
                    }
                } catch (Exception e2) {
                    cursor = cursorQuery;
                    e = e2;
                    try {
                        if (e instanceof LoadingException) {
                            throw e;
                        }
                        throw new LoadingException("V2 version check failed", e, r02);
                    } catch (Throwable th) {
                        th = th;
                        r02 = cursor;
                        if (r02 != 0) {
                            r02.close();
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    r02 = cursorQuery;
                    th = th2;
                    if (r02 != 0) {
                    }
                    throw th;
                }
            }
            Log.w("DynamiteModule", "Failed to retrieve remote module version.");
            throw new LoadingException("Failed to connect to dynamite module ContentResolver.", (com.google.android.gms.dynamite.zza) r02);
        } catch (Exception e3) {
            e = e3;
            cursor = null;
        } catch (Throwable th3) {
            th = th3;
        }
    }

    private static DynamiteModule zze(Context context, String str) {
        String strValueOf = String.valueOf(str);
        Log.i("DynamiteModule", strValueOf.length() != 0 ? "Selected local version of ".concat(strValueOf) : new String("Selected local version of "));
        return new DynamiteModule(context.getApplicationContext());
    }

    private static zzi zzj(Context context) {
        zzi zziVar;
        zzi zzjVar;
        synchronized (DynamiteModule.class) {
            try {
                zziVar = zzie;
            } catch (Exception e2) {
                String strValueOf = String.valueOf(e2.getMessage());
                Log.e("DynamiteModule", strValueOf.length() != 0 ? "Failed to load IDynamiteLoader from GmsCore: ".concat(strValueOf) : new String("Failed to load IDynamiteLoader from GmsCore: "));
            } finally {
            }
            if (zziVar != null) {
                return zziVar;
            }
            if (GoogleApiAvailabilityLight.getInstance().isGooglePlayServicesAvailable(context) != 0) {
                return null;
            }
            IBinder iBinder = (IBinder) context.createPackageContext("com.google.android.gms", 3).getClassLoader().loadClass("com.google.android.gms.chimera.container.DynamiteLoaderImpl").newInstance();
            if (iBinder == null) {
                zzjVar = null;
            } else {
                IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.dynamite.IDynamiteLoader");
                zzjVar = iInterfaceQueryLocalInterface instanceof zzi ? (zzi) iInterfaceQueryLocalInterface : new zzj(iBinder);
            }
            if (zzjVar != null) {
                zzie = zzjVar;
                return zzjVar;
            }
            return null;
        }
    }

    @KeepForSdk
    public final Context getModuleContext() {
        return this.zzim;
    }

    @KeepForSdk
    public final IBinder instantiate(String str) throws LoadingException {
        try {
            return (IBinder) this.zzim.getClassLoader().loadClass(str).newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e2) {
            String strValueOf = String.valueOf(str);
            throw new LoadingException(strValueOf.length() != 0 ? "Failed to instantiate module class: ".concat(strValueOf) : new String("Failed to instantiate module class: "), e2, null);
        }
    }

    private static DynamiteModule zzb(Context context, String str, int i2) throws LoadingException {
        IObjectWrapper iObjectWrapperZza;
        String str2 = "Failed to load remote module.";
        StringBuilder sb = new StringBuilder(a.e(51, str));
        sb.append("Selected remote version of ");
        sb.append(str);
        sb.append(", version >= ");
        sb.append(i2);
        Log.i("DynamiteModule", sb.toString());
        zzi zziVarZzj = zzj(context);
        com.google.android.gms.dynamite.zza zzaVar = null;
        if (zziVarZzj != null) {
            try {
                if (zziVarZzj.zzaj() >= 2) {
                    iObjectWrapperZza = zziVarZzj.zzb(ObjectWrapper.wrap(context), str, i2);
                } else {
                    Log.w("DynamiteModule", "Dynamite loader version < 2, falling back to createModuleContext");
                    iObjectWrapperZza = zziVarZzj.zza(ObjectWrapper.wrap(context), str, i2);
                }
                if (ObjectWrapper.unwrap(iObjectWrapperZza) != null) {
                    return new DynamiteModule((Context) ObjectWrapper.unwrap(iObjectWrapperZza));
                }
                throw new LoadingException(str2, zzaVar);
            } catch (RemoteException e2) {
                throw new LoadingException(str2, e2, zzaVar);
            }
        }
        throw new LoadingException("Failed to create IDynamiteLoader.", zzaVar);
    }

    private static DynamiteModule zzc(Context context, String str, int i2) throws LoadingException {
        zzk zzkVar;
        StringBuilder sb = new StringBuilder(a.e(51, str));
        sb.append("Selected remote version of ");
        sb.append(str);
        sb.append(", version >= ");
        sb.append(i2);
        Log.i("DynamiteModule", sb.toString());
        synchronized (DynamiteModule.class) {
            zzkVar = zzif;
        }
        com.google.android.gms.dynamite.zza zzaVar = null;
        if (zzkVar != null) {
            zza zzaVar2 = zzii.get();
            if (zzaVar2 != null && zzaVar2.zzin != null) {
                Context contextZza = zza(context.getApplicationContext(), str, i2, zzaVar2.zzin, zzkVar);
                if (contextZza != null) {
                    return new DynamiteModule(contextZza);
                }
                throw new LoadingException("Failed to get module context", zzaVar);
            }
            throw new LoadingException("No result cursor", zzaVar);
        }
        throw new LoadingException("DynamiteLoaderV2 was not cached.", zzaVar);
    }

    private static DynamiteModule zza(Context context, String str, int i2) {
        Boolean bool;
        try {
            synchronized (DynamiteModule.class) {
                bool = zzid;
            }
            if (bool != null) {
                if (bool.booleanValue()) {
                    return zzc(context, str, i2);
                }
                return zzb(context, str, i2);
            }
            throw new LoadingException("Failed to determine which loading route to use.", (com.google.android.gms.dynamite.zza) null);
        } catch (Throwable th) {
            CrashUtils.addDynamiteErrorToDropBox(context, th);
            throw th;
        }
    }

    private static Context zza(Context context, String str, int i2, Cursor cursor, zzk zzkVar) {
        IObjectWrapper iObjectWrapperZza;
        try {
            ObjectWrapper.wrap(null);
            if (zzai().booleanValue()) {
                Log.v("DynamiteModule", "Dynamite loader version >= 2, using loadModule2NoCrashUtils");
                iObjectWrapperZza = zzkVar.zzb(ObjectWrapper.wrap(context), str, i2, ObjectWrapper.wrap(cursor));
            } else {
                Log.w("DynamiteModule", "Dynamite loader version < 2, falling back to loadModule2");
                iObjectWrapperZza = zzkVar.zza(ObjectWrapper.wrap(context), str, i2, ObjectWrapper.wrap(cursor));
            }
            return (Context) ObjectWrapper.unwrap(iObjectWrapperZza);
        } catch (Exception e2) {
            String strValueOf = String.valueOf(e2.toString());
            Log.e("DynamiteModule", strValueOf.length() != 0 ? "Failed to load DynamiteLoader: ".concat(strValueOf) : new String("Failed to load DynamiteLoader: "));
            return null;
        }
    }

    private static void zza(ClassLoader classLoader) throws LoadingException {
        zzk zzlVar;
        com.google.android.gms.dynamite.zza zzaVar = null;
        try {
            IBinder iBinder = (IBinder) classLoader.loadClass("com.google.android.gms.dynamiteloader.DynamiteLoaderV2").getConstructor(null).newInstance(null);
            if (iBinder == null) {
                zzlVar = null;
            } else {
                IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.dynamite.IDynamiteLoaderV2");
                if (iInterfaceQueryLocalInterface instanceof zzk) {
                    zzlVar = (zzk) iInterfaceQueryLocalInterface;
                } else {
                    zzlVar = new zzl(iBinder);
                }
            }
            zzif = zzlVar;
        } catch (ClassNotFoundException e2) {
            e = e2;
            throw new LoadingException("Failed to instantiate dynamite loader", e, zzaVar);
        } catch (IllegalAccessException e3) {
            e = e3;
            throw new LoadingException("Failed to instantiate dynamite loader", e, zzaVar);
        } catch (InstantiationException e4) {
            e = e4;
            throw new LoadingException("Failed to instantiate dynamite loader", e, zzaVar);
        } catch (NoSuchMethodException e5) {
            e = e5;
            throw new LoadingException("Failed to instantiate dynamite loader", e, zzaVar);
        } catch (InvocationTargetException e6) {
            e = e6;
            throw new LoadingException("Failed to instantiate dynamite loader", e, zzaVar);
        }
    }
}
