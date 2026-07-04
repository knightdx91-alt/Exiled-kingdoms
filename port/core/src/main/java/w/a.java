package w;

import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.SerializationException;
import com.badlogic.gdx.utils.GdxNativesLoader;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.StreamUtils;
import com.badlogic.gdx.utils.IntArray;
import com.badlogic.gdx.utils.XmlReader;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.zip.GZIPInputStream;
import java.util.zip.InflaterInputStream;
import w.a.C0061a;
import w.e;
import w.h;

/* JADX INFO: compiled from: BaseTmxMapLoader.java */
/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public abstract class a<P extends C0061a> extends com.badlogic.gdx.assets.loaders.b<b, P> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected XmlReader f4068a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected XmlReader.a f4069b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected boolean f4070c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    protected int f4071d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    protected b f4072e;

    /* JADX INFO: renamed from: w.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: BaseTmxMapLoader.java */
    public static class C0061a extends r.b<b> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Texture.TextureFilter f4073a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public Texture.TextureFilter f4074b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f4075c;

        public C0061a() {
            Texture.TextureFilter textureFilter = Texture.TextureFilter.Nearest;
            this.f4073a = textureFilter;
            this.f4074b = textureFilter;
            this.f4075c = true;
        }
    }

    public a(GdxNativesLoader lVar) {
        super(lVar);
        this.f4068a = new XmlReader ();
        this.f4070c = true;
    }

    protected static com.badlogic.gdx.files.a a(com.badlogic.gdx.files.a aVar, String str) {
        StringTokenizer stringTokenizer = new StringTokenizer(str, "\\/");
        com.badlogic.gdx.files.a aVarParent = aVar.parent();
        while (stringTokenizer.hasMoreElements()) {
            String strNextToken = stringTokenizer.nextToken();
            aVarParent = strNextToken.equals("..") ? aVarParent.parent() : aVarParent.child(strNextToken);
        }
        return aVarParent;
    }

    protected static void b(u.d dVar, XmlReader.a aVar) {
        String strD = aVar.d("name", null);
        float f2 = Float.parseFloat(aVar.d("opacity", "1.0"));
        boolean z2 = aVar.j(1, "visible") == 1;
        aVar.i("offsetx", 0.0f);
        aVar.i("offsety", 0.0f);
        dVar.g(strD);
        dVar.h(f2);
        dVar.i(z2);
        dVar.e();
        dVar.e();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v10 */
    /* JADX WARN: Type inference failed for: r3v11 */
    /* JADX WARN: Type inference failed for: r3v2, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v5, types: [com.badlogic.gdx.graphics.Color] */
    /* JADX WARN: Type inference failed for: r3v6, types: [java.lang.Boolean] */
    /* JADX WARN: Type inference failed for: r3v7, types: [java.lang.Float] */
    /* JADX WARN: Type inference failed for: r3v8, types: [java.lang.Integer] */
    protected static void e(u.h hVar, XmlReader.a aVar) {
        if (aVar.k().equals("properties")) {
            a.b<XmlReader.a> it = aVar.h("property").iterator();
            while (it.hasNext()) {
                XmlReader.a next = it.next();
                String strD = next.d("name", null);
                String strD2 = next.d("value", null);
                String strD3 = next.d("type", null);
                Object objValueOf = strD2;
                if (strD2 == null) {
                    objValueOf = next.l();
                }
                if (strD3 != null) {
                    if (strD3.equals("int")) {
                        objValueOf = Integer.valueOf((String) objValueOf);
                    } else if (strD3.equals("float")) {
                        objValueOf = Float.valueOf((String) objValueOf);
                    } else if (strD3.equals("bool")) {
                        objValueOf = Boolean.valueOf((String) objValueOf);
                    } else {
                        if (!strD3.equals("color")) {
                            throw new GdxRuntimeException ("Wrong type given for property " + strD + ", given : " + strD3 + ", supported : string, bool, int, float, color");
                        }
                        objValueOf = Color.valueOf(objValueOf.substring(3) + objValueOf.substring(1, 3));
                    }
                }
                hVar.d(objValueOf, strD);
            }
        }
    }

    protected final void c(b bVar, u.e eVar, XmlReader.a aVar, com.badlogic.gdx.files.a aVar2, u.a aVar3) {
        InputStream bufferedInputStream;
        int i2;
        u.d dVar;
        String strK = aVar.k();
        int i3 = 0;
        if (strK.equals("group")) {
            if (aVar.k().equals("group")) {
                u.c cVar = new u.c();
                b(cVar, aVar);
                XmlReader.a aVarF = aVar.f("properties");
                if (aVarF != null) {
                    e(cVar.d(), aVarF);
                }
                int iG = aVar.g();
                for (int i4 = 0; i4 < iG; i4++) {
                    c(bVar, cVar.j(), aVar.e(i4), aVar2, aVar3);
                }
                Iterator<u.d> it = cVar.j().iterator();
                do {
                    a.b bVar2 = (a.b) it;
                    if (!bVar2.hasNext()) {
                        eVar.a(cVar);
                        return;
                    } else {
                        dVar = (u.d) bVar2.next();
                        dVar.getClass();
                    }
                } while (cVar != dVar);
                throw new GdxRuntimeException ("Can't set self as the parent");
            }
            return;
        }
        if (!strK.equals("layer")) {
            if (strK.equals("objectgroup")) {
                if (aVar.k().equals("objectgroup")) {
                    u.d dVar2 = new u.d();
                    b(dVar2, aVar);
                    XmlReader.a aVarF2 = aVar.f("properties");
                    if (aVarF2 != null) {
                        e(dVar2.d(), aVarF2);
                    }
                    a.b<XmlReader.a> it2 = aVar.h("object").iterator();
                    while (it2.hasNext()) {
                        d(bVar, dVar2.b(), it2.next(), this.f4071d);
                    }
                    eVar.a(dVar2);
                    return;
                }
                return;
            }
            if (strK.equals("imagelayer") && aVar.k().equals("imagelayer")) {
                if (aVar.m("offsetx")) {
                    Float.parseFloat(aVar.d("offsetx", "0"));
                } else {
                    Float.parseFloat(aVar.d("x", "0"));
                }
                if (aVar.m("offsety")) {
                    Float.parseFloat(aVar.d("offsety", "0"));
                } else {
                    Float.parseFloat(aVar.d("y", "0"));
                }
                XmlReader.a aVarF3 = aVar.f("image");
                if (aVarF3 != null) {
                    aVar3.a(a(aVar2, aVarF3.c("source")).path()).getRegionHeight();
                }
                u.d cVar2 = new c();
                b(cVar2, aVar);
                XmlReader.a aVarF4 = aVar.f("properties");
                if (aVarF4 != null) {
                    e(cVar2.d(), aVarF4);
                }
                eVar.a(cVar2);
                return;
            }
            return;
        }
        if (aVar.k().equals("layer")) {
            int iJ = aVar.j(0, "width");
            int iJ2 = aVar.j(0, "height");
            e eVar2 = new e(iJ, iJ2, ((Integer) bVar.b().c("tilewidth")).intValue(), ((Integer) bVar.b().c("tileheight")).intValue());
            b(eVar2, aVar);
            XmlReader.a aVarF5 = aVar.f("data");
            String strD = aVarF5.d("encoding", null);
            if (strD == null) {
                throw new GdxRuntimeException ("Unsupported encoding (XML) for TMX Layer Data");
            }
            int[] iArr = new int[iJ * iJ2];
            if (strD.equals("csv")) {
                String[] strArrSplit = aVarF5.l().split(",");
                for (int i5 = 0; i5 < strArrSplit.length; i5++) {
                    iArr[i5] = (int) Long.parseLong(strArrSplit[i5].trim());
                }
                i2 = 0;
            } else {
                try {
                    if (!strD.equals("base64")) {
                        throw new GdxRuntimeException (a.a.l("Unrecognised encoding (", strD, ") for TMX Layer Data"));
                    }
                    try {
                        String strD2 = aVarF5.d("compression", null);
                        byte[] bArrA = com.badlogic.gdx.utils.c.a(aVarF5.l());
                        if (strD2 == null) {
                            bufferedInputStream = new ByteArrayInputStream(bArrA);
                        } else if (strD2.equals("gzip")) {
                            bufferedInputStream = new BufferedInputStream(new GZIPInputStream(new ByteArrayInputStream(bArrA), bArrA.length));
                        } else {
                            if (!strD2.equals("zlib")) {
                                throw new GdxRuntimeException ("Unrecognised compression (" + strD2 + ") for TMX Layer Data");
                            }
                            bufferedInputStream = new BufferedInputStream(new InflaterInputStream(new ByteArrayInputStream(bArrA)));
                        }
                        InputStream inputStream = bufferedInputStream;
                        int i6 = 4;
                        byte[] bArr = new byte[4];
                        int i7 = 0;
                        while (i7 < iJ2) {
                            int i8 = i3;
                            while (i8 < iJ) {
                                int i9 = inputStream.read(bArr);
                                while (i9 < i6) {
                                    int i10 = inputStream.read(bArr, i9, 4 - i9);
                                    if (i10 == -1) {
                                        break;
                                    } else {
                                        i9 += i10;
                                    }
                                }
                                if (i9 != i6) {
                                    throw new GdxRuntimeException ("Error Reading TMX Layer Data: Premature end of tile data");
                                }
                                iArr[(i7 * iJ) + i8] = ((bArr[1] & 255) << 8) | (bArr[0] & 255) | ((bArr[2] & 255) << 16) | ((bArr[3] & 255) << 24);
                                i8++;
                                i3 = 0;
                                i6 = 4;
                            }
                            i7++;
                            i6 = 4;
                        }
                        i2 = i3;
                        StreamUtils.closeQuietly(inputStream);
                    } catch (IOException e2) {
                        throw new GdxRuntimeException ("Error Reading TMX Layer Data - IOException: " + e2.getMessage());
                    }
                } catch (Throwable th) {
                    StreamUtils.closeQuietly(null);
                    throw th;
                }
            }
            g gVarC = bVar.c();
            for (int i11 = i2; i11 < iJ2; i11++) {
                for (int i12 = i2; i12 < iJ; i12++) {
                    d dVarB = gVarC.b(iArr[(i11 * iJ) + i12] & 536870911);
                    if (dVarB != null) {
                        e.a aVar4 = new e.a();
                        aVar4.b(dVarB);
                        eVar2.m(i12, this.f4070c ? (iJ2 - 1) - i11 : i11, aVar4);
                    }
                }
            }
            XmlReader.a aVarF6 = aVar.f("properties");
            if (aVarF6 != null) {
                e(eVar2.d(), aVarF6);
            }
            eVar.a(eVar2);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x010e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected final void d(b bVar, u.g gVar, XmlReader.a aVar, float f2) {
        u.f bVar2;
        String str;
        if (aVar.k().equals("object")) {
            float fI = aVar.i("x", 0.0f) * 1.0f;
            float fI2 = (this.f4070c ? f2 - aVar.i("y", 0.0f) : aVar.i("y", 0.0f)) * 1.0f;
            float fI3 = aVar.i("width", 0.0f) * 1.0f;
            float fI4 = aVar.i("height", 0.0f) * 1.0f;
            char c2 = 0;
            if (aVar.g() > 0) {
                XmlReader.a aVarF = aVar.f("polygon");
                String str2 = ",";
                if (aVarF != null) {
                    String[] strArrSplit = aVarF.c("points").split(" ");
                    float[] fArr = new float[strArrSplit.length * 2];
                    int i2 = 0;
                    while (i2 < strArrSplit.length) {
                        String[] strArrSplit2 = strArrSplit[i2].split(",");
                        int i3 = i2 * 2;
                        fArr[i3] = Float.parseFloat(strArrSplit2[c2]) * 1.0f;
                        fArr[i3 + 1] = Float.parseFloat(strArrSplit2[1]) * 1.0f * (this.f4070c ? -1 : 1);
                        i2++;
                        c2 = 0;
                    }
                    new a0.m(fArr).d(fI, fI2);
                    bVar2 = new v.a();
                } else {
                    XmlReader.a aVarF2 = aVar.f("polyline");
                    if (aVarF2 != null) {
                        String[] strArrSplit3 = aVarF2.c("points").split(" ");
                        int length = strArrSplit3.length * 2;
                        float[] fArr2 = new float[length];
                        int i4 = 0;
                        while (i4 < strArrSplit3.length) {
                            String[] strArrSplit4 = strArrSplit3[i4].split(str2);
                            int i5 = i4 * 2;
                            fArr2[i5] = Float.parseFloat(strArrSplit4[0]) * 1.0f;
                            String str3 = str2;
                            fArr2[i5 + 1] = Float.parseFloat(strArrSplit4[1]) * 1.0f * (this.f4070c ? -1 : 1);
                            i4++;
                            str2 = str3;
                        }
                        if (length < 4) {
                            throw new IllegalArgumentException("polylines must contain at least 2 points.");
                        }
                        bVar2 = new v.a();
                    } else {
                        bVar2 = aVar.f("ellipse") != null ? new v.a() : null;
                    }
                }
            }
            if (bVar2 == null) {
                String strD = aVar.d("gid", null);
                if (strD != null) {
                    int i6 = (int) Long.parseLong(strD);
                    str = "y";
                    x.a aVar2 = new x.a(bVar.c().b(i6 & 536870911), (Integer.MIN_VALUE & i6) != 0, (1073741824 & i6) != 0);
                    TextureRegion textureRegionB = aVar2.b();
                    aVar2.a().d(Integer.valueOf(i6), "gid");
                    aVar.i("width", textureRegionB.getRegionWidth());
                    aVar.i("height", textureRegionB.getRegionHeight());
                    textureRegionB.getRegionWidth();
                    textureRegionB.getRegionHeight();
                    aVar.i("rotation", 0.0f);
                    bVar2 = aVar2;
                } else {
                    str = "y";
                    bVar2 = new v.b(fI, this.f4070c ? fI2 - fI4 : fI2, fI3, fI4);
                }
            } else {
                str = "y";
            }
            aVar.d("name", null);
            String strD2 = aVar.d("rotation", null);
            if (strD2 != null) {
                bVar2.a().d(Float.valueOf(Float.parseFloat(strD2)), "rotation");
            }
            Object objD = aVar.d("type", null);
            if (objD != null) {
                bVar2.a().d(objD, "type");
            }
            int iJ = aVar.j(0, "id");
            if (iJ != 0) {
                bVar2.a().d(Integer.valueOf(iJ), "id");
            }
            bVar2.a().d(Float.valueOf(fI), "x");
            if (bVar2 instanceof x.a) {
                bVar2.a().d(Float.valueOf(fI2), str);
            } else {
                String str4 = str;
                u.h hVarA = bVar2.a();
                if (this.f4070c) {
                    fI2 -= fI4;
                }
                hVarA.d(Float.valueOf(fI2), str4);
            }
            bVar2.a().d(Float.valueOf(fI3), "width");
            bVar2.a().d(Float.valueOf(fI4), "height");
            aVar.j(1, "visible");
            XmlReader.a aVarF3 = aVar.f("properties");
            if (aVarF3 != null) {
                e(bVar2.a(), aVarF3);
            }
            gVar.a(bVar2);
        }
    }

    protected final b f(com.badlogic.gdx.files.a aVar, h.a aVar2, u.a aVar3) {
        a.b<XmlReader.a> bVar;
        String str;
        XmlReader.a aVar4;
        String str2;
        a<P> aVar5;
        String str3;
        int i2;
        int i3;
        int i4;
        XmlReader.a aVar6;
        String str4;
        com.badlogic.gdx.files.a aVarA;
        String str5;
        String str6;
        com.badlogic.gdx.files.a aVarA2;
        a.b<XmlReader.a> bVar2;
        z.a aVar7;
        XmlReader.a aVar8;
        String str7;
        com.badlogic.gdx.files.a aVarA3;
        int i5;
        int iJ;
        a<P> aVar9 = this;
        aVar9.f4072e = new b();
        if (aVar2 != null) {
            aVar9.f4070c = aVar2.f4075c;
        } else {
            aVar9.f4070c = true;
        }
        String strD = aVar9.f4069b.d("orientation", null);
        String str8 = "width";
        int iJ2 = aVar9.f4069b.j(0, "width");
        String str9 = "height";
        int iJ3 = aVar9.f4069b.j(0, "height");
        int iJ4 = aVar9.f4069b.j(0, "tilewidth");
        int iJ5 = aVar9.f4069b.j(0, "tileheight");
        int iJ6 = aVar9.f4069b.j(0, "hexsidelength");
        String strD2 = aVar9.f4069b.d("staggeraxis", null);
        String strD3 = aVar9.f4069b.d("staggerindex", null);
        String strD4 = aVar9.f4069b.d("backgroundcolor", null);
        u.h hVarB = aVar9.f4072e.b();
        if (strD != null) {
            hVarB.d(strD, "orientation");
        }
        hVarB.d(Integer.valueOf(iJ2), "width");
        hVarB.d(Integer.valueOf(iJ3), "height");
        hVarB.d(Integer.valueOf(iJ4), "tilewidth");
        hVarB.d(Integer.valueOf(iJ5), "tileheight");
        hVarB.d(Integer.valueOf(iJ6), "hexsidelength");
        if (strD2 != null) {
            hVarB.d(strD2, "staggeraxis");
        }
        if (strD3 != null) {
            hVarB.d(strD3, "staggerindex");
        }
        if (strD4 != null) {
            hVarB.d(strD4, "backgroundcolor");
        }
        aVar9.f4071d = iJ3 * iJ5;
        if (strD != null && "staggered".equals(strD) && iJ3 > 1) {
            aVar9.f4071d = (iJ5 / 2) + (aVar9.f4071d / 2);
        }
        XmlReader.a aVarF = aVar9.f4069b.f("properties");
        if (aVarF != null) {
            e(aVar9.f4072e.b(), aVarF);
        }
        String str10 = "tileset";
        a.b<XmlReader.a> it = aVar9.f4069b.h("tileset").iterator();
        while (it.hasNext()) {
            XmlReader.a next = it.next();
            if (next.k().equals(str10)) {
                int iJ7 = next.j(1, "firstgid");
                String strD5 = next.d("source", null);
                if (strD5 != null) {
                    bVar = it;
                    com.badlogic.gdx.files.a aVarA4 = a(aVar, strD5);
                    str = str10;
                    try {
                        XmlReader.a aVarA5 = aVar9.f4068a.a(aVarA4);
                        XmlReader.a aVarF2 = aVarA5.f("image");
                        if (aVarF2 != null) {
                            aVar8 = aVarA5;
                            String strC = aVarF2.c("source");
                            iJ = aVarF2.j(0, str8);
                            int iJ8 = aVarF2.j(0, str9);
                            aVarA3 = a(aVarA4, strC);
                            str7 = strC;
                            i5 = iJ8;
                        } else {
                            aVar8 = aVarA5;
                            str7 = "";
                            aVarA3 = null;
                            i5 = 0;
                            iJ = 0;
                        }
                        i3 = iJ;
                        aVarA = aVarA3;
                        aVar6 = aVar8;
                        str2 = str8;
                        str4 = str7;
                        i4 = i5;
                        i2 = 0;
                    } catch (SerializationException unused) {
                        throw new GdxRuntimeException ("Error parsing external tileset.");
                    }
                } else {
                    bVar = it;
                    str = str10;
                    XmlReader.a aVarF3 = next.f("image");
                    if (aVarF3 != null) {
                        String strC2 = aVarF3.c("source");
                        i2 = 0;
                        int iJ9 = aVarF3.j(0, str8);
                        int iJ10 = aVarF3.j(0, str9);
                        str2 = str8;
                        aVarA = a(aVar, strC2);
                        str4 = strC2;
                        i3 = iJ9;
                        i4 = iJ10;
                        aVar6 = next;
                    } else {
                        i2 = 0;
                        i3 = 0;
                        i4 = 0;
                        aVar6 = next;
                        str2 = str8;
                        str4 = "";
                        aVarA = null;
                    }
                }
                aVar6.b();
                int iJ11 = aVar6.j(i2, "tilewidth");
                int iJ12 = aVar6.j(i2, "tileheight");
                str3 = str9;
                int iJ13 = aVar6.j(i2, "spacing");
                aVar4 = next;
                int iJ14 = aVar6.j(i2, "margin");
                XmlReader.a aVarF4 = aVar6.f("tileoffset");
                if (aVarF4 != null) {
                    str5 = strD5;
                    aVarF4.j(0, "x");
                    aVarF4.j(0, "y");
                } else {
                    str5 = strD5;
                }
                f fVar = new f();
                u.h hVarA = fVar.a();
                XmlReader.a aVarF5 = aVar6.f("properties");
                if (aVarF5 != null) {
                    e(hVarA, aVarF5);
                }
                hVarA.d(Integer.valueOf(iJ7), "firstgid");
                com.badlogic.gdx.utils.a<XmlReader.a> aVarH = aVar6.h("tile");
                u.h hVarA2 = fVar.a();
                if (aVarA != null) {
                    TextureRegion textureRegionA = aVar3.a(aVarA.path());
                    hVarA2.d(str4, "imagesource");
                    hVarA2.d(Integer.valueOf(i3), "imagewidth");
                    hVarA2.d(Integer.valueOf(i4), "imageheight");
                    hVarA2.d(Integer.valueOf(iJ11), "tilewidth");
                    hVarA2.d(Integer.valueOf(iJ12), "tileheight");
                    hVarA2.d(Integer.valueOf(iJ14), "margin");
                    hVarA2.d(Integer.valueOf(iJ13), "spacing");
                    int regionWidth = textureRegionA.getRegionWidth() - iJ11;
                    int regionHeight = textureRegionA.getRegionHeight() - iJ12;
                    int i6 = iJ7;
                    int i7 = iJ14;
                    while (i7 <= regionHeight) {
                        int i8 = iJ14;
                        while (i8 <= regionWidth) {
                            int i9 = regionWidth;
                            z.b bVar3 = new z.b(new TextureRegion(textureRegionA, i8, i7, iJ11, iJ12));
                            bVar3.d(i6);
                            fVar.c(i6, bVar3);
                            i8 += iJ11 + iJ13;
                            i6++;
                            regionWidth = i9;
                        }
                        i7 += iJ12 + iJ13;
                        regionWidth = regionWidth;
                    }
                } else {
                    a.b<XmlReader.a> it2 = aVarH.iterator();
                    while (it2.hasNext()) {
                        XmlReader.a next2 = it2.next();
                        XmlReader.a aVarF6 = next2.f("image");
                        if (aVarF6 != null) {
                            String strC3 = aVarF6.c("source");
                            if (str5 != null) {
                                str6 = str5;
                                aVarA2 = a(a(aVar, str6), strC3);
                            } else {
                                str6 = str5;
                                aVarA2 = a(aVar, strC3);
                            }
                            aVarA = aVarA2;
                        } else {
                            str6 = str5;
                        }
                        TextureRegion textureRegionA2 = aVar3.a(aVarA.path());
                        int i10 = Integer.parseInt(next2.c("id")) + iJ7;
                        z.b bVar4 = new z.b(textureRegionA2);
                        bVar4.d(i10);
                        fVar.c(i10, bVar4);
                        str5 = str6;
                    }
                }
                com.badlogic.gdx.utils.a aVar10 = new com.badlogic.gdx.utils.a();
                a.b<XmlReader.a> it3 = aVarH.iterator();
                while (it3.hasNext()) {
                    XmlReader.a next3 = it3.next();
                    d dVarB = fVar.b(next3.j(0, "id") + iJ7);
                    if (dVarB != null) {
                        XmlReader.a aVarF7 = next3.f("animation");
                        if (aVarF7 != null) {
                            com.badlogic.gdx.utils.a aVar11 = new com.badlogic.gdx.utils.a();
                            IntArray oVar = new IntArray ();
                            a.b<XmlReader.a> it4 = aVarF7.h("frame").iterator();
                            while (it4.hasNext()) {
                                XmlReader.a next4 = it4.next();
                                aVar11.a((z.b) fVar.b(Integer.parseInt(next4.c("tileid")) + iJ7));
                                oVar.a(Integer.parseInt(next4.c("duration")));
                                it3 = it3;
                            }
                            bVar2 = it3;
                            aVar7 = new z.a(oVar, aVar11);
                            aVar7.d(dVarB.getId());
                        } else {
                            bVar2 = it3;
                            aVar7 = null;
                        }
                        if (aVar7 != null) {
                            aVar10.a(aVar7);
                            dVarB = aVar7;
                        }
                        String strD6 = next3.d("terrain", null);
                        if (strD6 != null) {
                            dVarB.c().d(strD6, "terrain");
                        }
                        String strD7 = next3.d("probability", null);
                        if (strD7 != null) {
                            dVarB.c().d(strD7, "probability");
                        }
                        XmlReader.a aVarF8 = next3.f("properties");
                        if (aVarF8 != null) {
                            e(dVarB.c(), aVarF8);
                        }
                        XmlReader.a aVarF9 = next3.f("objectgroup");
                        if (aVarF9 != null) {
                            a.b<XmlReader.a> it5 = aVarF9.h("object").iterator();
                            while (it5.hasNext()) {
                                d(this.f4072e, dVarB.a(), it5.next(), dVarB.b().getRegionHeight());
                            }
                        }
                    } else {
                        bVar2 = it3;
                    }
                    it3 = bVar2;
                }
                aVar5 = this;
                a.b it6 = aVar10.iterator();
                while (it6.hasNext()) {
                    z.a aVar12 = (z.a) it6.next();
                    fVar.c(aVar12.getId(), aVar12);
                }
                aVar5.f4072e.c().a(fVar);
            } else {
                bVar = it;
                str = str10;
                aVar4 = next;
                str2 = str8;
                aVar5 = aVar9;
                str3 = str9;
            }
            aVar5.f4069b.n(aVar4);
            aVar9 = aVar5;
            it = bVar;
            str10 = str;
            str8 = str2;
            str9 = str3;
        }
        a<P> aVar13 = aVar9;
        int iG = aVar13.f4069b.g();
        for (int i11 = 0; i11 < iG; i11++) {
            XmlReader.a aVarE = aVar13.f4069b.e(i11);
            b bVar5 = aVar13.f4072e;
            c(bVar5, bVar5.a(), aVarE, aVar, aVar3);
        }
        return aVar13.f4072e;
    }

    @Override // com.badlogic.gdx.assets.loaders.a
    public final com.badlogic.gdx.utils.a getDependencies(String str, com.badlogic.gdx.files.a aVar, r.b bVar) {
        C0061a c0061a = (C0061a) bVar;
        this.f4069b = this.f4068a.a(aVar);
        p.b bVar2 = new p.b();
        if (c0061a != null) {
            bVar2.f1594b = false;
            bVar2.f1597e = c0061a.f4073a;
            bVar2.f1598f = c0061a.f4074b;
        }
        com.badlogic.gdx.utils.a aVar2 = new com.badlogic.gdx.utils.a();
        a.b<com.badlogic.gdx.files.a> it = ((h) this).g(aVar).iterator();
        while (it.hasNext()) {
            aVar2.a(new r.a(it.next(), bVar2));
        }
        return aVar2;
    }
}
