package e;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.Base64;
import android.util.Xml;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: compiled from: FontResourcesParserCompat.java */
/* JADX INFO: loaded from: /tmp/tmp.15aGftnP89/classes.dex */
public final class a {

    /* JADX INFO: renamed from: e.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: FontResourcesParserCompat.java */
    public interface InterfaceC0030a {
    }

    /* JADX INFO: compiled from: FontResourcesParserCompat.java */
    public static final class b implements InterfaceC0030a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final c[] f2169a;

        public b(c[] cVarArr) {
            this.f2169a = cVarArr;
        }

        public final c[] a() {
            return this.f2169a;
        }
    }

    /* JADX INFO: compiled from: FontResourcesParserCompat.java */
    public static final class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final String f2170a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f2171b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private boolean f2172c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private int f2173d;

        public c(String str, int i2, boolean z2, int i3) {
            this.f2170a = str;
            this.f2171b = i2;
            this.f2172c = z2;
            this.f2173d = i3;
        }

        public final String a() {
            return this.f2170a;
        }

        public final int b() {
            return this.f2173d;
        }

        public final int c() {
            return this.f2171b;
        }

        public final boolean d() {
            return this.f2172c;
        }
    }

    /* JADX INFO: compiled from: FontResourcesParserCompat.java */
    public static final class d implements InterfaceC0030a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final i.a f2174a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final int f2175b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final int f2176c;

        public d(i.a aVar, int i2, int i3) {
            this.f2174a = aVar;
            this.f2176c = i2;
            this.f2175b = i3;
        }

        public final int a() {
            return this.f2176c;
        }

        public final i.a b() {
            return this.f2174a;
        }

        public final int c() {
            return this.f2175b;
        }
    }

    public static InterfaceC0030a a(XmlResourceParser xmlResourceParser, Resources resources) throws XmlPullParserException, IOException {
        int next;
        do {
            next = xmlResourceParser.next();
            if (next == 2) {
                break;
            }
        } while (next != 1);
        if (next != 2) {
            throw new XmlPullParserException("No start tag found");
        }
        xmlResourceParser.require(2, null, "font-family");
        if (!xmlResourceParser.getName().equals("font-family")) {
            c(xmlResourceParser);
            return null;
        }
        TypedArray typedArrayObtainAttributes = resources.obtainAttributes(Xml.asAttributeSet(xmlResourceParser), c.a.FontFamily);
        String string = typedArrayObtainAttributes.getString(c.a.FontFamily_fontProviderAuthority);
        String string2 = typedArrayObtainAttributes.getString(c.a.FontFamily_fontProviderPackage);
        String string3 = typedArrayObtainAttributes.getString(c.a.FontFamily_fontProviderQuery);
        int resourceId = typedArrayObtainAttributes.getResourceId(c.a.FontFamily_fontProviderCerts, 0);
        int integer = typedArrayObtainAttributes.getInteger(c.a.FontFamily_fontProviderFetchStrategy, 1);
        int integer2 = typedArrayObtainAttributes.getInteger(c.a.FontFamily_fontProviderFetchTimeout, 500);
        typedArrayObtainAttributes.recycle();
        if (string != null && string2 != null && string3 != null) {
            while (xmlResourceParser.next() != 3) {
                c(xmlResourceParser);
            }
            return new d(new i.a(string, string2, string3, b(resources, resourceId)), integer, integer2);
        }
        ArrayList arrayList = new ArrayList();
        while (xmlResourceParser.next() != 3) {
            if (xmlResourceParser.getEventType() == 2) {
                if (xmlResourceParser.getName().equals("font")) {
                    TypedArray typedArrayObtainAttributes2 = resources.obtainAttributes(Xml.asAttributeSet(xmlResourceParser), c.a.FontFamilyFont);
                    int i2 = typedArrayObtainAttributes2.getInt(typedArrayObtainAttributes2.hasValue(c.a.FontFamilyFont_fontWeight) ? c.a.FontFamilyFont_fontWeight : c.a.FontFamilyFont_android_fontWeight, 400);
                    boolean z2 = 1 == typedArrayObtainAttributes2.getInt(typedArrayObtainAttributes2.hasValue(c.a.FontFamilyFont_fontStyle) ? c.a.FontFamilyFont_fontStyle : c.a.FontFamilyFont_android_fontStyle, 0);
                    int i3 = typedArrayObtainAttributes2.hasValue(c.a.FontFamilyFont_font) ? c.a.FontFamilyFont_font : c.a.FontFamilyFont_android_font;
                    int resourceId2 = typedArrayObtainAttributes2.getResourceId(i3, 0);
                    String string4 = typedArrayObtainAttributes2.getString(i3);
                    typedArrayObtainAttributes2.recycle();
                    while (xmlResourceParser.next() != 3) {
                        c(xmlResourceParser);
                    }
                    arrayList.add(new c(string4, i2, z2, resourceId2));
                } else {
                    c(xmlResourceParser);
                }
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return new b((c[]) arrayList.toArray(new c[arrayList.size()]));
    }

    public static List<List<byte[]>> b(Resources resources, int i2) {
        ArrayList arrayList = null;
        if (i2 != 0) {
            TypedArray typedArrayObtainTypedArray = resources.obtainTypedArray(i2);
            if (typedArrayObtainTypedArray.length() > 0) {
                arrayList = new ArrayList();
                if (typedArrayObtainTypedArray.getResourceId(0, 0) != 0) {
                    for (int i3 = 0; i3 < typedArrayObtainTypedArray.length(); i3++) {
                        String[] stringArray = resources.getStringArray(typedArrayObtainTypedArray.getResourceId(i3, 0));
                        ArrayList arrayList2 = new ArrayList();
                        for (String str : stringArray) {
                            arrayList2.add(Base64.decode(str, 0));
                        }
                        arrayList.add(arrayList2);
                    }
                } else {
                    String[] stringArray2 = resources.getStringArray(i2);
                    ArrayList arrayList3 = new ArrayList();
                    for (String str2 : stringArray2) {
                        arrayList3.add(Base64.decode(str2, 0));
                    }
                    arrayList.add(arrayList3);
                }
            }
            typedArrayObtainTypedArray.recycle();
        }
        return arrayList != null ? arrayList : Collections.emptyList();
    }

    private static void c(XmlResourceParser xmlResourceParser) throws XmlPullParserException, IOException {
        int i2 = 1;
        while (i2 > 0) {
            int next = xmlResourceParser.next();
            if (next == 2) {
                i2++;
            } else if (next == 3) {
                i2--;
            }
        }
    }
}
