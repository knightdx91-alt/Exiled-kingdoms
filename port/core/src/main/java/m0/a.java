package m0;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.Array;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import n0.z;
import net.fdgames.GameEntities.AI.Pathfinding.AStarPathFinder;
import net.fdgames.GameEntities.Final.Door;
import net.fdgames.GameEntities.Final.SecretDoor;
import net.fdgames.GameEntities.MapSprite;
import net.fdgames.GameLevel.GameLevel;
import net.fdgames.GameLevel.GameLevelData;
import net.fdgames.GameWorld.GameData;
import net.fdgames.Helpers.Serializer;
import net.fdgames.TiledMap.Objects.Coords;
import net.fdgames.assets.GameAssets;
import net.fdgames.ek.ExiledKingdoms;
import w.e;

/* JADX INFO: compiled from: ADTIsometricTiledMapRenderer.java */
/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public final class a extends y.a {
    private static float D;
    private static Color E = new Color();
    boolean A;
    e.a B;
    Door C;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private Matrix4 f2386g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private Matrix4 f2387h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private com.badlogic.gdx.math.a f2388i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private Vector2 f2389j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private Vector2 f2390k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private Vector2 f2391l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private Vector2 f2392m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public ArrayList<MapSprite> f2393n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private ArrayList<MapSprite>[][] f2394o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private float f2395p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    float f2396q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    float f2397r;
    float s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    float f2398t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    float f2399u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    float f2400v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    float f2401w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    float f2402x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    float f2403y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    float f2404z;

    public a(w.b bVar) {
        super(bVar);
        this.f2388i = new com.badlogic.gdx.math.a();
        this.f2389j = new Vector2 ();
        this.f2390k = new Vector2 ();
        this.f2391l = new Vector2 ();
        this.f2392m = new Vector2 ();
        this.f2395p = 0.0f;
        Matrix4 matrix4 = new Matrix4();
        this.f2386g = matrix4;
        matrix4.d();
        this.f2386g.l((float) (Math.sqrt(2.0d) / 2.0d), (float) (Math.sqrt(2.0d) / 4.0d), 1.0f);
        this.f2386g.i(0.0f, 0.0f, 1.0f, -45.0f);
        Matrix4 matrix42 = new Matrix4(this.f2386g);
        this.f2387h = matrix42;
        matrix42.e();
    }

    private void e(int i2, int i3) {
        if (b.P().f2428m.c(i2, i3)) {
            b.P().f2428m.b(i2, i3, (SpriteBatch) c());
        }
        ArrayList<MapSprite>[][] arrayListArr = this.f2394o;
        if (arrayListArr == null) {
            return;
        }
        int size = arrayListArr[i2][i3].size();
        for (int i4 = 0; i4 < size; i4++) {
            if (this.f2394o[i2][i3].get(i4) != null) {
                this.f2394o[i2][i3].get(i4).visibleToPlayer = Boolean.TRUE;
                this.f2394o[i2][i3].get(i4).F((SpriteBatch) c());
            }
        }
    }

    public static Color g() {
        return E;
    }

    private com.badlogic.gdx.math.a j(Vector2 qVar) {
        float f2 = qVar.f91a;
        float f3 = qVar.f92b;
        com.badlogic.gdx.math.a aVar = this.f2388i;
        aVar.t(f2, f3, 0.0f);
        aVar.m(this.f2387h);
        return aVar;
    }

    public final void f() {
        this.f2395p = 0.0f;
    }

    /* JADX WARN: Removed duplicated region for block: B:124:0x0407  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x040f  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x0497  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x04e6  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x05e0  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x02fe  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void h() {
        int i2;
        char c2;
        Iterator<u.d> it;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        w.e eVar;
        String str;
        int i9;
        float f2;
        int i10;
        int i11;
        TextureRegion textureRegionB;
        Color color;
        w.e eVar2;
        int i12;
        Boolean bool;
        int i13 = 1;
        int i14 = 3;
        char c3 = 4;
        this.A = b.P().f2423h;
        a();
        Iterator<u.d> it2 = this.f4089a.a().iterator();
        while (true) {
            a.b bVar = (a.b) it2;
            if (!bVar.hasNext()) {
                b();
                return;
            }
            u.d dVar = (u.d) bVar.next();
            if (dVar.f() && (dVar instanceof w.e)) {
                w.e eVar3 = (w.e) dVar;
                AStarPathFinder aStarPathFinder = GameLevel.f3094a;
                if (GameData.v().player != null) {
                    int i15 = z.v().w() == i13 ? i13 : 0;
                    Color color2 = E;
                    color2.f1680r = c().getColor().f1680r;
                    color2.f1679g = c().getColor().f1679g;
                    color2.f1678b = c().getColor().f1678b;
                    color2.f1677a = c().getColor().f1677a;
                    Color color3 = c().getColor();
                    boolean zContains = eVar3.a().contains("objects");
                    boolean zContains2 = eVar3.a().contains("scenery");
                    boolean zContains3 = eVar3.a().contains("bridge");
                    boolean zContains4 = eVar3.a().contains("roofs");
                    boolean zContains5 = eVar3.a().contains("ground");
                    int i16 = GameData.v().player.f3092x / 32;
                    int i17 = GameData.v().player.f3093y / 32;
                    if (zContains4) {
                        i4 = i14;
                        i6 = 6;
                        i5 = 2;
                    } else {
                        i4 = 0;
                        i5 = 0;
                        i6 = 0;
                    }
                    if (zContains2) {
                        i4 = i14;
                        i6 = i4;
                        i5 = 2;
                    }
                    if (zContains3) {
                        i5 = i14;
                        i4 = 6;
                        i6 = 8;
                    }
                    if (zContains) {
                        i6 = i14;
                        i4 = 2;
                        i5 = 2;
                    }
                    if (ExiledKingdoms.f3378h) {
                        i4 += 3;
                        i5 += 3;
                        i6 += 4;
                    }
                    float fL = eVar3.l();
                    float f3 = this.f4090b;
                    float f4 = fL * f3;
                    float f5 = f4 * 0.5f;
                    float fK = eVar3.k() * f3 * 0.5f;
                    Rectangle pVar = this.f4092d;
                    float f6 = pVar.f89x;
                    float f7 = pVar.width + f6;
                    it = it2;
                    float f8 = pVar.f90y;
                    Vector2 qVar = this.f2389j;
                    qVar.f91a = f7;
                    qVar.f92b = f8;
                    float f9 = pVar.height + f8;
                    Vector2 qVar2 = this.f2390k;
                    qVar2.f91a = f6;
                    qVar2.f92b = f9;
                    Vector2 qVar3 = this.f2391l;
                    qVar3.f91a = f6;
                    qVar3.f92b = f8;
                    Vector2 qVar4 = this.f2392m;
                    qVar4.f91a = f7;
                    qVar4.f92b = f9;
                    int i18 = ((int) (j(qVar3).f1730b / f4)) - 2;
                    int i19 = ((int) (j(qVar4).f1730b / f4)) + 2;
                    int i20 = (((int) (j(qVar2).f1729a / f4)) - 2) - i5;
                    int i21 = ((int) (j(qVar).f1729a / f4)) + 2 + i4;
                    int i22 = i18 - i6;
                    if (i20 < 0) {
                        i20 = 0;
                    }
                    if (i21 > b.P().f2417b - 1) {
                        i21 = b.P().f2417b - 1;
                    }
                    if (i22 < 0) {
                        i22 = 0;
                    }
                    if (i19 > b.P().f2418c - 1) {
                        i19 = b.P().f2418c - 1;
                    }
                    b bVarP = b.P();
                    byte[][] bArr = bVarP.f2419d;
                    if (bArr != null && bVarP.f2417b == bArr.length && bVarP.f2418c == bArr[0].length) {
                        i7 = i19;
                    } else {
                        Serializer.t(GameData.v().CurrentLevel);
                        byte[][] bArr2 = bVarP.f2419d;
                        if (bArr2 == null || bVarP.f2417b != bArr2.length) {
                            i7 = i19;
                        } else {
                            i7 = i19;
                            if (bVarP.f2418c != bArr2[0].length) {
                            }
                            i9 = i7;
                            while (i9 >= i22) {
                                int i23 = i8;
                                while (i23 <= i21) {
                                    if (i15 == 0 && zContains5 && (k0.a.l().f2278e.zoom != 1.0f || Math.abs(i9 - i17) + Math.abs(i23 - i16) < 12)) {
                                        b bVarP2 = b.P();
                                        if (bVarP2.f2423h && i23 < bVarP2.f2417b && i9 < bVarP2.f2418c) {
                                            bVarP2.f2419d[i23][i9] = 1;
                                        }
                                    }
                                    float f10 = i23;
                                    float f11 = i9;
                                    this.f2402x = (f11 * f5) + (f10 * f5);
                                    this.f2403y = (f11 * fK) - (f10 * fK);
                                    this.f2404z = eVar.c() * color3.f1677a;
                                    w.e eVar4 = eVar;
                                    this.B = eVar4.j(i23, i9);
                                    boolean zB0 = (zContains || !this.A) ? b.P().b0(i23, i9) : true;
                                    if (!this.A && zContains5 && b.P().c0(i16, i17, i23, i9)) {
                                        f2 = fK;
                                    } else {
                                        if (zContains && zB0 && i15 == 0) {
                                            e(i23, i9);
                                            if (!this.A && zContains) {
                                                float f12 = this.f2404z;
                                                if (this.B != null && b.P().d0(i23, i9) && i16 <= i23 && i16 >= i23 - 4 && i17 >= i9 && i17 <= i9 + 4) {
                                                    f12 = 0.42f;
                                                }
                                                this.f2404z = f12;
                                            }
                                        }
                                        if (!zContains4 || i15 == 0) {
                                            f2 = fK;
                                            if (this.B != null) {
                                                boolean z2 = b.P().f2419d[i23][i9] > 0 || (this.A && i15 == 0);
                                                w.d dVarA = this.B.a();
                                                if (dVarA != null || !z2) {
                                                    i10 = i22;
                                                    i11 = i21;
                                                    D = Color.toFloatBits(color3.f1680r, color3.f1679g, color3.f1678b, this.f2404z);
                                                    Boolean boolValueOf = Boolean.FALSE;
                                                    if (z2) {
                                                        if (!zB0 && i15 == 0 && !zContains4) {
                                                            D = Color.toFloatBits(color3.f1680r / 3.0f, color3.f1679g / 3.0f, color3.f1678b / 3.0f, 1.0f);
                                                        }
                                                        if (!zContains || this.A) {
                                                            bool = boolValueOf;
                                                            boolValueOf = bool;
                                                            textureRegionB = null;
                                                            if (textureRegionB == null) {
                                                                textureRegionB = dVarA.b();
                                                            }
                                                        } else {
                                                            Door doorN = GameLevelData.o().n(i23, i9);
                                                            this.C = doorN;
                                                            if (doorN != null) {
                                                                textureRegionB = doorN.F();
                                                                boolValueOf = Boolean.valueOf(this.C.G());
                                                                if (textureRegionB == null) {
                                                                }
                                                            } else {
                                                                SecretDoor secretDoorU = GameLevelData.o().u(i23, i9);
                                                                if (secretDoorU != null) {
                                                                    int iOrdinal = secretDoorU.I().ordinal();
                                                                    if (iOrdinal == 1) {
                                                                        bool = boolValueOf;
                                                                        D = Color.toFloatBits(color3.f1680r * 0.6f, color3.f1679g, color3.f1678b * 0.6f, Math.min(GameLevel.b() - ((int) GameLevel.b()), 0.35f));
                                                                    } else if (iOrdinal == 2) {
                                                                        bool = boolValueOf;
                                                                        D = Color.toFloatBits(color3.f1680r, color3.f1679g, color3.f1678b, Math.min(GameLevel.b() - ((int) GameLevel.b()), 0.35f));
                                                                    }
                                                                    boolValueOf = bool;
                                                                    textureRegionB = null;
                                                                    if (textureRegionB == null) {
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    } else {
                                                        textureRegionB = null;
                                                    }
                                                    if (textureRegionB != null) {
                                                        float f13 = this.f2402x;
                                                        this.f2396q = f13;
                                                        this.f2397r = this.f2403y;
                                                        this.s = (textureRegionB.getRegionWidth() * f3) + f13;
                                                        this.f2398t = (textureRegionB.getRegionHeight() * f3) + this.f2397r;
                                                        this.f2399u = textureRegionB.getU();
                                                        this.f2400v = textureRegionB.getV2();
                                                        this.f2401w = textureRegionB.getU2();
                                                        float v2 = textureRegionB.getV();
                                                        float f14 = this.f2396q;
                                                        float[] fArr = this.f4094f;
                                                        fArr[0] = f14;
                                                        float f15 = this.f2397r;
                                                        fArr[1] = f15;
                                                        float f16 = D;
                                                        fArr[2] = f16;
                                                        color = color3;
                                                        float f17 = this.f2399u;
                                                        fArr[3] = f17;
                                                        eVar2 = eVar4;
                                                        float f18 = this.f2400v;
                                                        fArr[4] = f18;
                                                        fArr[5] = f14;
                                                        float f19 = this.f2398t;
                                                        fArr[6] = f19;
                                                        fArr[7] = f16;
                                                        fArr[8] = f17;
                                                        fArr[9] = v2;
                                                        float f20 = this.s;
                                                        fArr[10] = f20;
                                                        fArr[11] = f19;
                                                        fArr[12] = f16;
                                                        float f21 = this.f2401w;
                                                        fArr[13] = f21;
                                                        fArr[14] = v2;
                                                        fArr[15] = f20;
                                                        fArr[16] = f15;
                                                        fArr[17] = f16;
                                                        fArr[18] = f21;
                                                        fArr[19] = f18;
                                                        c().draw(textureRegionB.getTexture(), fArr, 0, 20);
                                                    } else {
                                                        color = color3;
                                                        eVar2 = eVar4;
                                                    }
                                                    if (boolValueOf.booleanValue() && zB0 && i15 == 0) {
                                                        e(i23, i9);
                                                    }
                                                    i12 = 1;
                                                }
                                            }
                                        } else {
                                            AStarPathFinder aStarPathFinder2 = GameLevel.f3094a;
                                            if (GameData.v().player.f3092x / 32 == i23 && GameData.v().player.f3093y / 32 == i9) {
                                                Coords coordsA = b.A(140, GameData.v().player.B());
                                                f2 = fK;
                                                c().draw(GameAssets.v0, coordsA.f3287x + 32, coordsA.f3288y);
                                            }
                                            if (this.B != null) {
                                            }
                                        }
                                        i23 += i12;
                                        fK = f2;
                                        i22 = i10;
                                        i21 = i11;
                                        color3 = color;
                                        eVar = eVar2;
                                    }
                                    i10 = i22;
                                    color = color3;
                                    i11 = i21;
                                    eVar2 = eVar4;
                                    i12 = 1;
                                    i23 += i12;
                                    fK = f2;
                                    i22 = i10;
                                    i21 = i11;
                                    color3 = color;
                                    eVar = eVar2;
                                }
                                i9--;
                                fK = fK;
                                color3 = color3;
                            }
                            i3 = 1;
                            i2 = 3;
                            c2 = 4;
                        }
                        i8 = i20;
                        if (bArr2 == null) {
                            eVar = eVar3;
                            r0.a.a("ERROR 1.1 ;" + GameData.v().CurrentLevel + ";" + GameData.v().currentMapName + ";name:" + bVarP.f2422g + ";size:" + bVarP.f2417b + "," + bVarP.f2418c);
                        } else {
                            eVar = eVar3;
                            StringBuilder sb = new StringBuilder("ERROR 1 ;");
                            sb.append(GameData.v().CurrentLevel);
                            sb.append(";");
                            sb.append(GameData.v().currentMapName);
                            sb.append(";name:");
                            sb.append(bVarP.f2422g);
                            sb.append(";size:");
                            sb.append(bVarP.f2417b);
                            sb.append(",");
                            sb.append(bVarP.f2418c);
                            sb.append(";tiles:");
                            sb.append(bVarP.f2419d.length);
                            sb.append(",");
                            sb.append(bVarP.f2419d[0].length);
                            sb.append(";Player: ");
                            sb.append(GameData.v().player.B().toString());
                            sb.append("NewArea: ");
                            GameData gameDataV = GameData.v();
                            if (gameDataV.NewArea == null) {
                                str = "null";
                            } else {
                                str = gameDataV.NewArea.area_id + "," + gameDataV.NewArea.entry_id;
                            }
                            sb.append(str);
                            r0.a.a(sb.toString());
                        }
                        int i24 = GameData.v().slot;
                        String str2 = GameData.v().CurrentLevel;
                        Gdx.files.local("data/saves/" + i24 + "/levels/" + str2 + "_tiles.txt").delete();
                        bVarP.f2419d = (byte[][]) Array.newInstance((Class<?>) Byte.TYPE, bVarP.f2417b, bVarP.f2418c);
                        i9 = i7;
                        while (i9 >= i22) {
                        }
                        i3 = 1;
                        i2 = 3;
                        c2 = 4;
                    }
                    i8 = i20;
                    eVar = eVar3;
                    i9 = i7;
                    while (i9 >= i22) {
                    }
                    i3 = 1;
                    i2 = 3;
                    c2 = 4;
                }
            } else {
                i2 = i14;
                c2 = c3;
                it = it2;
                i3 = i13;
            }
            i13 = i3;
            i14 = i2;
            c3 = c2;
            it2 = it;
        }
    }

    public final void i(ArrayList<MapSprite> arrayList) {
        if (this.f2393n == null) {
            this.f2393n = new ArrayList<>();
        }
        if (GameLevel.b() <= this.f2395p + 0.2f || arrayList == null) {
            return;
        }
        int i2 = GameData.v().player.f3092x;
        int i3 = GameData.v().player.f3093y;
        int i4 = ExiledKingdoms.f3378h ? 448 : 288;
        this.f2395p = GameLevel.b();
        if (this.f2394o == null) {
            this.f2394o = (ArrayList[][]) Array.newInstance((Class<?>) ArrayList.class, 96, 96);
            for (int i5 = 0; i5 < 96; i5++) {
                for (int i6 = 0; i6 < 96; i6++) {
                    this.f2394o[i5][i6] = new ArrayList<>();
                }
            }
        } else {
            for (int i7 = 0; i7 < 96; i7++) {
                for (int i8 = 0; i8 < 96; i8++) {
                    this.f2394o[i7][i8].clear();
                }
            }
        }
        this.f2393n.clear();
        int size = arrayList.size();
        for (int i9 = 0; i9 < size; i9++) {
            int iAbs = Math.abs(arrayList.get(i9).f3092x - i2);
            int iAbs2 = Math.abs(arrayList.get(i9).f3093y - i3);
            if (iAbs < i4 && iAbs2 < i4) {
                this.f2393n.add(arrayList.get(i9));
                int i10 = (arrayList.get(i9).f3092x + 8) / 32;
                int i11 = (arrayList.get(i9).f3093y - 24) / 32;
                if (i10 >= 96) {
                    i10 = 95;
                }
                if (i11 >= 96) {
                    i11 = 95;
                }
                if (i10 < 0) {
                    i10 = 0;
                }
                if (i11 < 0) {
                    i11 = 0;
                }
                this.f2394o[i10][i11].add(arrayList.get(i9));
                arrayList.get(i9).visibleToPlayer = Boolean.FALSE;
            }
        }
    }
}
