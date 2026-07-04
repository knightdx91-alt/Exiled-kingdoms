package i;

import android.content.ContentUris;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v4.util.i;
import i.f;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* JADX INFO: compiled from: FontsContractCompat.java */
/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public final class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final android.support.v4.util.f<String, Typeface> f2228a = new android.support.v4.util.f<>(16);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final f f2229b = new f();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Object f2230c = new Object();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final i<String, ArrayList<f.d<d>>> f2231d = new i<>();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final Comparator<byte[]> f2232e = new a();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final /* synthetic */ int f2233f = 0;

    /* JADX INFO: compiled from: FontsContractCompat.java */
    static class a implements Comparator<byte[]> {
        @Override // java.util.Comparator
        public final int compare(byte[] bArr, byte[] bArr2) {
            byte[] bArr3 = bArr;
            byte[] bArr4 = bArr2;
            if (bArr3.length != bArr4.length) {
                return bArr3.length - bArr4.length;
            }
            for (int i2 = 0; i2 < bArr3.length; i2++) {
                byte b2 = bArr3[i2];
                byte b3 = bArr4[i2];
                if (b2 != b3) {
                    return b2 - b3;
                }
            }
            return 0;
        }
    }

    /* JADX INFO: compiled from: FontsContractCompat.java */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f2234a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final c[] f2235b;

        public b(int i2, c[] cVarArr) {
            this.f2234a = i2;
            this.f2235b = cVarArr;
        }

        public final c[] a() {
            return this.f2235b;
        }

        public final int b() {
            return this.f2234a;
        }
    }

    /* JADX INFO: compiled from: FontsContractCompat.java */
    public static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Uri f2236a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final int f2237b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final int f2238c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final boolean f2239d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final int f2240e;

        public c(Uri uri, int i2, int i3, boolean z2, int i4) {
            uri.getClass();
            this.f2236a = uri;
            this.f2237b = i2;
            this.f2238c = i3;
            this.f2239d = z2;
            this.f2240e = i4;
        }

        public final int a() {
            return this.f2240e;
        }

        public final int b() {
            return this.f2237b;
        }

        public final Uri c() {
            return this.f2236a;
        }

        public final int d() {
            return this.f2238c;
        }

        public final boolean e() {
            return this.f2239d;
        }
    }

    /* JADX INFO: Access modifiers changed from: public */
    /* JADX INFO: compiled from: FontsContractCompat.java */
    static final class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final Typeface f2241a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final int f2242b;

        d(Typeface typeface, int i2) {
            this.f2241a = typeface;
            this.f2242b = i2;
        }
    }

    public static b e(Context context, i.a aVar) throws PackageManager.NameNotFoundException {
        Cursor cursorQuery;
        PackageManager packageManager = context.getPackageManager();
        Resources resources = context.getResources();
        String strC = aVar.c();
        ProviderInfo providerInfoResolveContentProvider = packageManager.resolveContentProvider(strC, 0);
        if (providerInfoResolveContentProvider == null) {
            throw new PackageManager.NameNotFoundException(a.a.A("No package found for authority: ", strC));
        }
        if (!providerInfoResolveContentProvider.packageName.equals(aVar.d())) {
            StringBuilder sbU = a.a.u("Found content provider ", strC, ", but package was not ");
            sbU.append(aVar.d());
            throw new PackageManager.NameNotFoundException(sbU.toString());
        }
        Signature[] signatureArr = packageManager.getPackageInfo(providerInfoResolveContentProvider.packageName, 64).signatures;
        ArrayList arrayList = new ArrayList();
        for (Signature signature : signatureArr) {
            arrayList.add(signature.toByteArray());
        }
        Comparator<byte[]> comparator = f2232e;
        Collections.sort(arrayList, comparator);
        List<List<byte[]>> listA = aVar.a() != null ? aVar.a() : e.a.b(resources, 0);
        int i2 = 0;
        loop1: while (true) {
            cursorQuery = null;
            if (i2 >= listA.size()) {
                providerInfoResolveContentProvider = null;
                break;
            }
            ArrayList arrayList2 = new ArrayList(listA.get(i2));
            Collections.sort(arrayList2, comparator);
            if (arrayList.size() == arrayList2.size()) {
                for (int i3 = 0; i3 < arrayList.size(); i3++) {
                    if (!Arrays.equals((byte[]) arrayList.get(i3), (byte[]) arrayList2.get(i3))) {
                        break;
                    }
                }
                break loop1;
            }
            i2++;
        }
        if (providerInfoResolveContentProvider == null) {
            return new b(1, null);
        }
        String str = providerInfoResolveContentProvider.authority;
        ArrayList arrayList3 = new ArrayList();
        Uri uriBuild = new Uri.Builder().scheme("content").authority(str).build();
        Uri uriBuild2 = new Uri.Builder().scheme("content").authority(str).appendPath("file").build();
        try {
            cursorQuery = context.getContentResolver().query(uriBuild, new String[]{"_id", "file_id", "font_ttc_index", "font_variation_settings", "font_weight", "font_italic", "result_code"}, "query = ?", new String[]{aVar.e()}, null, null);
            if (cursorQuery != null && cursorQuery.getCount() > 0) {
                int columnIndex = cursorQuery.getColumnIndex("result_code");
                ArrayList arrayList4 = new ArrayList();
                int columnIndex2 = cursorQuery.getColumnIndex("_id");
                int columnIndex3 = cursorQuery.getColumnIndex("file_id");
                int columnIndex4 = cursorQuery.getColumnIndex("font_ttc_index");
                int columnIndex5 = cursorQuery.getColumnIndex("font_weight");
                int columnIndex6 = cursorQuery.getColumnIndex("font_italic");
                while (cursorQuery.moveToNext()) {
                    arrayList4.add(new c(columnIndex3 == -1 ? ContentUris.withAppendedId(uriBuild, cursorQuery.getLong(columnIndex2)) : ContentUris.withAppendedId(uriBuild2, cursorQuery.getLong(columnIndex3)), columnIndex4 != -1 ? cursorQuery.getInt(columnIndex4) : 0, columnIndex5 != -1 ? cursorQuery.getInt(columnIndex5) : 400, columnIndex6 != -1 && cursorQuery.getInt(columnIndex6) == 1, columnIndex != -1 ? cursorQuery.getInt(columnIndex) : 0));
                }
                arrayList3 = arrayList4;
            }
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            return new b(0, (c[]) arrayList3.toArray(new c[0]));
        } catch (Throwable th) {
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: public */
    public static d f(Context context, i.a aVar, int i2) {
        try {
            b bVarE = e(context, aVar);
            if (bVarE.b() != 0) {
                return new d(null, bVarE.b() == 1 ? -2 : -3);
            }
            Typeface typefaceA = f.b.a(context, bVarE.a(), i2);
            return new d(typefaceA, typefaceA != null ? 0 : -3);
        } catch (PackageManager.NameNotFoundException unused) {
            return new d(null, -1);
        }
    }

    public static Typeface g(Context context, i.a aVar, e.d dVar, boolean z2, int i2, int i3) {
        String str = aVar.b() + "-" + i3;
        Typeface typeface = f2228a.get(str);
        if (typeface != null) {
            dVar.c(typeface);
            return typeface;
        }
        if (z2 && i2 == -1) {
            d dVarF = f(context, aVar, i3);
            int i4 = dVarF.f2242b;
            if (i4 == 0) {
                dVar.b(dVarF.f2241a);
            } else {
                dVar.a(i4);
            }
            return dVarF.f2241a;
        }
        i.b bVar = new i.b(context, aVar, i3, str);
        if (z2) {
            try {
                return ((d) f2229b.e(bVar, i2)).f2241a;
            } catch (InterruptedException unused) {
                return null;
            }
        }
        i.c cVar = new i.c(dVar);
        synchronized (f2230c) {
            try {
                i<String, ArrayList<f.d<d>>> iVar = f2231d;
                if (iVar.containsKey(str)) {
                    iVar.get(str).add(cVar);
                    return null;
                }
                ArrayList<f.d<d>> arrayList = new ArrayList<>();
                arrayList.add(cVar);
                iVar.put(str, arrayList);
                f2229b.d(bVar, new i.d(str));
                return null;
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
