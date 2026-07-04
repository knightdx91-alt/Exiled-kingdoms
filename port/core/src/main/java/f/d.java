package f;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.fonts.FontVariationAxis;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.os.Process;
import android.util.Log;
import e.a;
import i.e;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: TypefaceCompatApi26Impl.java */
/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public final class d extends c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Class f2201a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Constructor f2202b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Method f2203c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final Method f2204d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final Method f2205e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final Method f2206f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final Method f2207g;

    static {
        Class<?> cls;
        Method method;
        Method method2;
        Method method3;
        Method method4;
        Method declaredMethod;
        Constructor<?> constructor = null;
        try {
            cls = Class.forName("android.graphics.FontFamily");
            Constructor<?> constructor2 = cls.getConstructor(null);
            Class cls2 = Integer.TYPE;
            method = cls.getMethod("addFontFromAssetManager", AssetManager.class, String.class, cls2, Boolean.TYPE, cls2, cls2, cls2, FontVariationAxis[].class);
            method2 = cls.getMethod("addFontFromBuffer", ByteBuffer.class, cls2, FontVariationAxis[].class, cls2, cls2);
            method3 = cls.getMethod("freeze", null);
            method4 = cls.getMethod("abortCreation", null);
            declaredMethod = Typeface.class.getDeclaredMethod("createFromFamiliesWithDefault", Array.newInstance(cls, 1).getClass(), cls2, cls2);
            declaredMethod.setAccessible(true);
            constructor = constructor2;
        } catch (ClassNotFoundException | NoSuchMethodException e2) {
            Log.e("TypefaceCompatApi26Impl", "Unable to collect necessary methods for class ".concat(e2.getClass().getName()), e2);
            cls = null;
            method = null;
            method2 = null;
            method3 = null;
            method4 = null;
            declaredMethod = null;
        }
        f2202b = constructor;
        f2201a = cls;
        f2203c = method;
        f2204d = method2;
        f2205e = method3;
        f2206f = method4;
        f2207g = declaredMethod;
    }

    public static void d(Object obj) {
        try {
            f2206f.invoke(obj, null);
        } catch (IllegalAccessException | InvocationTargetException e2) {
            throw new RuntimeException(e2);
        }
    }

    public static boolean e(Context context, Object obj, String str, int i2, int i3) {
        try {
            return ((Boolean) f2203c.invoke(obj, context.getAssets(), str, 0, Boolean.FALSE, 0, Integer.valueOf(i2), Integer.valueOf(i3), null)).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException e2) {
            throw new RuntimeException(e2);
        }
    }

    public static Typeface f(Object obj) {
        try {
            Object objNewInstance = Array.newInstance((Class<?>) f2201a, 1);
            Array.set(objNewInstance, 0, obj);
            return (Typeface) f2207g.invoke(null, objNewInstance, -1, -1);
        } catch (IllegalAccessException | InvocationTargetException e2) {
            throw new RuntimeException(e2);
        }
    }

    public static boolean i(Object obj) {
        try {
            return ((Boolean) f2205e.invoke(obj, null)).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException e2) {
            throw new RuntimeException(e2);
        }
    }

    public static boolean j() {
        Method method = f2203c;
        if (method == null) {
            Log.w("TypefaceCompatApi26Impl", "Unable to collect necessary public methods. Fallback to legacy implementation.");
        }
        return method != null;
    }

    public static Object k() {
        try {
            return f2202b.newInstance(null);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e2) {
            throw new RuntimeException(e2);
        }
    }

    @Override // f.g
    public final Typeface a(Context context, a.b bVar, Resources resources, int i2) {
        if (!j()) {
            return super.a(context, bVar, resources, i2);
        }
        Object objK = k();
        for (a.c cVar : bVar.a()) {
            if (!e(context, objK, cVar.a(), cVar.c(), cVar.d() ? 1 : 0)) {
                d(objK);
                return null;
            }
        }
        if (i(objK)) {
            return f(objK);
        }
        return null;
    }

    public final Typeface g(Context context, e.c[] cVarArr, int i2) {
        MappedByteBuffer map;
        if (cVarArr.length < 1) {
            return null;
        }
        if (j()) {
            int i3 = i.e.f2233f;
            HashMap map2 = new HashMap();
            int i4 = 0;
            for (e.c cVar : cVarArr) {
                if (cVar.a() == 0) {
                    Uri uriC = cVar.c();
                    if (map2.containsKey(uriC)) {
                        continue;
                    } else {
                        try {
                            ParcelFileDescriptor parcelFileDescriptorOpenFileDescriptor = context.getContentResolver().openFileDescriptor(uriC, "r", null);
                            try {
                                FileInputStream fileInputStream = new FileInputStream(parcelFileDescriptorOpenFileDescriptor.getFileDescriptor());
                                try {
                                    FileChannel channel = fileInputStream.getChannel();
                                    map = channel.map(FileChannel.MapMode.READ_ONLY, 0L, channel.size());
                                    fileInputStream.close();
                                    parcelFileDescriptorOpenFileDescriptor.close();
                                } catch (Throwable th) {
                                    try {
                                        throw th;
                                    } finally {
                                    }
                                }
                            } catch (Throwable th2) {
                                try {
                                    throw th2;
                                } catch (Throwable th3) {
                                    if (parcelFileDescriptorOpenFileDescriptor != null) {
                                        try {
                                            parcelFileDescriptorOpenFileDescriptor.close();
                                        } catch (Throwable th4) {
                                            th2.addSuppressed(th4);
                                        }
                                    }
                                    throw th3;
                                }
                            }
                        } catch (IOException unused) {
                            map = null;
                        }
                        map2.put(uriC, map);
                    }
                }
            }
            Map mapUnmodifiableMap = Collections.unmodifiableMap(map2);
            Object objK = k();
            int length = cVarArr.length;
            boolean z2 = false;
            while (i4 < length) {
                e.c cVar2 = cVarArr[i4];
                ByteBuffer byteBuffer = (ByteBuffer) mapUnmodifiableMap.get(cVar2.c());
                if (byteBuffer != null) {
                    try {
                        if (!((Boolean) f2204d.invoke(objK, byteBuffer, Integer.valueOf(cVar2.b()), null, Integer.valueOf(cVar2.d()), Integer.valueOf(cVar2.e() ? 1 : 0))).booleanValue()) {
                            d(objK);
                            return null;
                        }
                        z2 = true;
                    } catch (IllegalAccessException | InvocationTargetException e2) {
                        throw new RuntimeException(e2);
                    }
                }
                i4++;
                z2 = z2;
            }
            if (!z2) {
                d(objK);
                return null;
            }
            if (i(objK)) {
                return Typeface.create(f(objK), i2);
            }
            return null;
        }
        e.c cVarC = g.c(i2, cVarArr);
        try {
            ParcelFileDescriptor parcelFileDescriptorOpenFileDescriptor2 = context.getContentResolver().openFileDescriptor(cVarC.c(), "r", null);
            try {
                Typeface typefaceBuild = new Typeface.Builder(parcelFileDescriptorOpenFileDescriptor2.getFileDescriptor()).setWeight(cVarC.d()).setItalic(cVarC.e()).build();
                parcelFileDescriptorOpenFileDescriptor2.close();
                return typefaceBuild;
            } finally {
            }
        } catch (IOException unused2) {
            return null;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0050, code lost:
    
        if (r1 != null) goto L43;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0053, code lost:
    
        r5 = r6.openRawResource(r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0057, code lost:
    
        r6 = f.h.b(r1, r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x005b, code lost:
    
        f.h.a(r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x005e, code lost:
    
        if (r6 != false) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0064, code lost:
    
        r0 = android.graphics.Typeface.createFromFile(r1.getPath());
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x006d, code lost:
    
        r5 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x006f, code lost:
    
        r6 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0071, code lost:
    
        r6 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0072, code lost:
    
        r5 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0073, code lost:
    
        f.h.a(r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0076, code lost:
    
        throw r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0077, code lost:
    
        r1.delete();
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x007a, code lost:
    
        throw r5;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Typeface h(Context context, Resources resources, int i2, String str, int i3) {
        File file;
        Typeface typefaceCreateFromFile = null;
        if (!j()) {
            String str2 = ".font" + Process.myPid() + "-" + Process.myTid() + "-";
            int i4 = 0;
            while (true) {
                if (i4 >= 100) {
                    file = null;
                    break;
                }
                file = new File(context.getCacheDir(), str2 + i4);
                if (!file.createNewFile()) {
                    i4++;
                }
            }
        } else {
            Object objK = k();
            if (!e(context, objK, str, -1, -1)) {
                d(objK);
                return null;
            }
            if (i(objK)) {
                return f(objK);
            }
            return null;
        }
        file.delete();
        return typefaceCreateFromFile;
    }
}
