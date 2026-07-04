package m0;

import a0.i;
import a0.m;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.GdxNativesLoader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import n0.w;
import net.fdgames.GameEntities.AI.Pathfinding.AStarPathFinder;
import net.fdgames.GameEntities.Character;
import net.fdgames.GameEntities.Final.Door;
import net.fdgames.GameEntities.Final.Loot;
import net.fdgames.GameEntities.Final.MapContainer;
import net.fdgames.GameEntities.Final.MapEffectEntity;
import net.fdgames.GameEntities.Final.MapItem;
import net.fdgames.GameEntities.Final.NPC;
import net.fdgames.GameEntities.Final.PlantSpawn;
import net.fdgames.GameEntities.Final.Player;
import net.fdgames.GameEntities.Final.SecretDoor;
import net.fdgames.GameEntities.Helpers.Shop;
import net.fdgames.GameEntities.MapActor;
import net.fdgames.GameLevel.GameLevel;
import net.fdgames.GameLevel.GameLevelData;
import net.fdgames.GameLogic.ConditionsSet;
import net.fdgames.GameWorld.GameData;
import net.fdgames.GameWorld.GameWorld;
import net.fdgames.GameWorld.WorldFactions;
import net.fdgames.Helpers.FDUtils;
import net.fdgames.Helpers.GameString;
import net.fdgames.TiledMap.Objects.Bed;
import net.fdgames.TiledMap.Objects.Coords;
import net.fdgames.TiledMap.Objects.ItemConversation;
import net.fdgames.TiledMap.Objects.MapCastle;
import net.fdgames.TiledMap.Objects.MapConversation;
import net.fdgames.TiledMap.Objects.MapEntry;
import net.fdgames.TiledMap.Objects.MapLight;
import net.fdgames.TiledMap.Objects.RestPoint;
import net.fdgames.TiledMap.Objects.Transition;
import net.fdgames.TiledMap.Objects.Trigger;
import net.fdgames.TiledMap.Objects.Waypoint;
import net.fdgames.ek.ExiledKingdoms;
import u.f;
import com.badlogic.gdx.maps.MapProperties;
import w.h;

/* JADX INFO: compiled from: GameMap.java */
/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public final class b {

    /* JADX INFO: renamed from: b0, reason: collision with root package name */
    private static int f2405b0;

    /* JADX INFO: renamed from: c0, reason: collision with root package name */
    private static Rectangle f2406c0 = new Rectangle (0.0f, 0.0f, 1.0f, 1.0f);

    /* JADX INFO: renamed from: d0, reason: collision with root package name */
    private static ArrayList<Loot> f2407d0 = new ArrayList<>();

    /* JADX INFO: renamed from: e0, reason: collision with root package name */
    private static m f2408e0 = new m();

    /* JADX INFO: renamed from: f0, reason: collision with root package name */
    private static float[] f2409f0 = new float[8];

    /* JADX INFO: renamed from: g0, reason: collision with root package name */
    private static Vector2 f2410g0 = new Vector2 (0.0f, 0.0f);

    /* JADX INFO: renamed from: h0, reason: collision with root package name */
    private static Vector2 f2411h0 = new Vector2 (0.0f, 0.0f);

    /* JADX INFO: renamed from: i0, reason: collision with root package name */
    private static b f2412i0;

    /* JADX INFO: renamed from: j0, reason: collision with root package name */
    private static Vector2 f2413j0;

    /* JADX INFO: renamed from: k0, reason: collision with root package name */
    public static final /* synthetic */ int f2414k0 = 0;
    private ArrayList<MapCastle> A;
    private ArrayList<ItemConversation> B;
    private ArrayList<Waypoint> C;
    public ArrayList<MapLight> D;
    public String E;
    public String F;
    public String G;
    private w.b H;
    public w.e I;
    public w.e J;
    public w.e K;
    public u.d L;
    public MapProperties M;
    public Transition N;
    public Transition O;
    public Transition P;
    public Transition Q;
    private ArrayList<MapConversation> R;
    private ArrayList<MapContainer> S;
    private ArrayList<Shop> T;
    private ArrayList<PlantSpawn> U;
    private ArrayList<MapItem> V;
    private ArrayList<RestPoint> W;
    private ArrayList<MapCastle> X;
    private ArrayList<Bed> Y;
    private MapActor Z;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f2415a;

    /* JADX INFO: renamed from: a0, reason: collision with root package name */
    private MapActor f2416a0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f2417b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f2418c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public byte[][] f2419d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f2420e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f2421f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public String f2422g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f2423h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f2424i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f2425j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public boolean f2426k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f2427l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public c f2428m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int[][] f2429n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private int[][] f2430o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int[][] f2431p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private boolean f2432q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private int[][] f2433r;
    private int[][] s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int[][] f2434t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public int[][] f2435u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    private com.badlogic.gdx.utils.Array<Coords> f2436v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    private ArrayList<Transition> f2437w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    private ArrayList<MapEntry> f2438x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    private ArrayList<RestPoint> f2439y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    private ArrayList<Bed> f2440z;

    public static Coords A(int i2, Coords coords) {
        int i3 = coords.f3287x;
        int i4 = coords.f3288y;
        return new Coords((i3 + i4) - (i2 / 2), (i4 - i3) / 2);
    }

    public static Vector2 B(int i2, int i3) {
        if (f2413j0 == null) {
            f2413j0 = new Vector2 ();
        }
        int i4 = i2 / 2;
        float f2 = i4 - i3;
        float f3 = i3 + i4;
        Vector2 qVar = f2413j0;
        qVar.f91a = ((int) f2) + 32;
        qVar.f92b = ((int) f3) - 32;
        return qVar;
    }

    public static void E(int i2, int i3) {
        Rectangle pVar = f2406c0;
        pVar.set(i2 - 8, i3 - 8, 32.0f, 32.0f);
        for (Trigger trigger : GameLevelData.A()) {
            Rectangle pVarA = trigger.a();
            int i4 = i.f68h;
            if (pVar.overlaps(pVarA) && trigger.c()) {
                return;
            }
        }
    }

    public static double F(int i2, Coords coords) {
        MapActor mapActorG = GameLevel.g(i2);
        if (mapActorG != null) {
            return p(mapActorG.B(), coords);
        }
        return 999.0d;
    }

    public static void H() {
        f2412i0 = null;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static Coords L(Coords coords, int i2, MapActor.Facing facing) {
        Coords coords2 = new Coords(0, 0);
        switch (facing.ordinal()) {
            case 0:
                float f2 = i2 * 0.71f;
                coords2.f3288y = (int) (coords.f3288y + f2);
                coords2.f3287x = (int) (coords.f3287x - f2);
                return coords2;
            case 1:
                coords2.f3288y = coords.f3288y + i2;
                coords2.f3287x = coords.f3287x;
                return coords2;
            case 2:
                float f3 = i2 * 0.71f;
                coords2.f3288y = (int) (coords.f3288y + f3);
                coords2.f3287x = (int) (coords.f3287x + f3);
                return coords2;
            case 3:
                coords2.f3288y = coords.f3288y;
                coords2.f3287x = coords.f3287x + i2;
                return coords2;
            case 4:
                float f4 = i2 * 0.71f;
                coords2.f3288y = (int) (coords.f3288y - f4);
                coords2.f3287x = (int) (coords.f3287x + f4);
                return coords2;
            case 5:
                coords2.f3288y = coords.f3288y - i2;
                coords2.f3287x = coords.f3287x;
                return coords2;
            case 6:
                float f5 = i2 * 0.71f;
                coords2.f3288y = (int) (coords.f3288y - f5);
                coords2.f3287x = (int) (coords.f3287x - f5);
                return coords2;
            case 7:
                coords2.f3288y = coords.f3288y;
                coords2.f3287x = coords.f3287x - i2;
                return coords2;
            default:
                return coords2;
        }
    }

    public static int N() {
        return f2412i0.f2421f;
    }

    public static int O() {
        return f2412i0.f2420e;
    }

    public static b P() {
        if (f2412i0 == null) {
            b bVar = new b();
            bVar.f2432q = false;
            bVar.f2436v = new com.badlogic.gdx.utils.a<>();
            bVar.N = new Transition();
            bVar.O = new Transition();
            bVar.P = new Transition();
            bVar.Q = new Transition();
            bVar.R = new ArrayList<>();
            bVar.S = new ArrayList<>();
            bVar.T = new ArrayList<>();
            bVar.U = new ArrayList<>();
            bVar.V = new ArrayList<>();
            bVar.W = new ArrayList<>();
            bVar.X = new ArrayList<>();
            bVar.Y = new ArrayList<>();
            f2412i0 = bVar;
        }
        return f2412i0;
    }

    public static int R() {
        return f2412i0.f2418c;
    }

    public static int S() {
        return f2412i0.f2417b;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static Rectangle U() {
        AStarPathFinder aStarPathFinder = GameLevel.f3094a;
        int i2 = GameData.v().player.f3092x;
        int i3 = GameData.v().player.f3093y;
        int i4 = i3 - 224;
        int i5 = i2 - 224;
        int i6 = i2 + 224;
        int i7 = i3 + 224;
        switch (GameData.v().player.facing.ordinal()) {
            case 0:
                i6 = i2;
                i2 = i5;
                break;
            case 1:
                i3 += 64;
                int i8 = i2 + 64;
                i2 -= 64;
                i6 = i8;
                break;
            case 2:
                break;
            case 3:
                int i9 = i3 + 64;
                i3 -= 64;
                i2 += 64;
                i7 = i9;
                break;
            case 4:
                i7 = i3;
                i3 = i4;
                break;
            case 5:
                int i10 = i2 + 64;
                i2 -= 64;
                i7 = i3 - 64;
                i3 = i4;
                i6 = i10;
                break;
            case 6:
                i6 = i2;
                i7 = i3;
                i3 = i4;
                i2 = i5;
                break;
            case 7:
                int i11 = i3 + 64;
                i3 -= 64;
                i6 = i2 - 64;
                i7 = i11;
                i2 = i5;
                break;
            default:
                i3 = i4;
                i2 = i5;
                break;
        }
        return new Rectangle (i2, i3, i6 - i2, i7 - i3);
    }

    public static Coords V(int i2, int i3, int i4) {
        int iMin;
        int iMin2;
        if (i4 == 0) {
            return new Coords(i2, i3);
        }
        int i5 = 100;
        do {
            int iB = FDUtils.b(i2 - i4, i2 + i4);
            int iB2 = FDUtils.b(i3 - i4, i3 + i4);
            i5--;
            iMin = Math.min(Math.max(iB, 0), f2412i0.f2420e);
            iMin2 = Math.min(Math.max(iB2, 0), f2412i0.f2421f);
            if (i5 <= 0) {
                break;
            }
        } while (P().i(iMin, iMin2, 16));
        return new Coords(iMin, iMin2);
    }

    public static ArrayList Y(Character character) {
        ArrayList arrayList = new ArrayList();
        float fA = character.sheet.a();
        for (MapActor mapActor : GameLevel.f3096c) {
            if (!mapActor.i0() && mapActor.q() != character.q() && Math.abs(mapActor.f3092x - character.f3092x) < fA && Math.abs(mapActor.f3093y - character.f3093y) < fA && GameWorld.f3189c.g(mapActor.r(), character.r())) {
                arrayList.add(Integer.valueOf(mapActor.q()));
            }
        }
        return arrayList;
    }

    public static boolean Z(Character character, Character character2) {
        if (!character.i0() && !character.destroy && !character2.i0() && !character2.destroy) {
            Coords coordsB = character2.B();
            MapActor.Facing facing = character2.facing;
            Coords coords = new Coords(0, 0);
            switch (facing.ordinal()) {
                case 0:
                    coords.f3288y = (int) (coordsB.f3288y - 22.72f);
                    coords.f3287x = (int) (coordsB.f3287x + 22.72f);
                    break;
                case 1:
                    coords.f3288y = coordsB.f3288y - 32;
                    coords.f3287x = coordsB.f3287x;
                    break;
                case 2:
                    coords.f3288y = (int) (coordsB.f3288y - 22.72f);
                    coords.f3287x = (int) (coordsB.f3287x - 22.72f);
                    break;
                case 3:
                    coords.f3288y = coordsB.f3288y;
                    coords.f3287x = coordsB.f3287x - 32;
                    break;
                case 4:
                    coords.f3288y = (int) (coordsB.f3288y + 22.72f);
                    coords.f3287x = (int) (coordsB.f3287x - 22.72f);
                    break;
                case 5:
                    coords.f3288y = coordsB.f3288y + 32;
                    coords.f3287x = coordsB.f3287x;
                    break;
                case 6:
                    coords.f3288y = (int) (coordsB.f3288y + 22.72f);
                    coords.f3287x = (int) (coordsB.f3287x + 22.72f);
                    break;
                case 7:
                    coords.f3288y = coordsB.f3288y;
                    coords.f3287x = coordsB.f3287x + 32;
                    break;
            }
            Rectangle pVar = new Rectangle (coords.f3287x - 16, coords.f3288y - 16, 32.0f, 32.0f);
            Rectangle pVarS = character.S(false);
            int i2 = i.f68h;
            if (pVar.overlaps(pVarS)) {
                return true;
            }
        }
        return false;
    }

    public static Coords e(double d2, boolean z2) {
        AStarPathFinder aStarPathFinder = GameLevel.f3094a;
        Coords coordsB = GameData.v().player.B();
        int size = GameLevel.f3096c.size();
        Coords coordsB2 = null;
        for (int i2 = 0; i2 < size; i2++) {
            WorldFactions worldFactions = GameWorld.f3189c;
            int[] iArrR = GameData.v().player.r();
            ArrayList<MapActor> arrayList = GameLevel.f3096c;
            if (worldFactions.g(iArrR, arrayList.get(i2).r()) && !arrayList.get(i2).i0()) {
                double dF = F(arrayList.get(i2).q(), coordsB);
                if (dF < d2 && (!z2 || arrayList.get(i2).h0())) {
                    coordsB2 = arrayList.get(i2).B();
                    d2 = dF;
                }
            }
        }
        return coordsB2;
    }

    public static ArrayList h(int i2, int i3) {
        ArrayList<Loot> arrayList = f2407d0;
        arrayList.clear();
        AStarPathFinder aStarPathFinder = GameLevel.f3094a;
        int size = GameLevelData.p().size();
        for (int i4 = 0; i4 < size; i4++) {
            if (GameLevelData.p().get(i4) != null && Math.abs(i2 - (GameLevelData.p().get(i4).f3092x - 32)) < 35.7f && Math.abs(i3 - (GameLevelData.p().get(i4).f3093y - 32)) < 35.7f) {
                if (GameLevelData.p().get(i4).j().p().booleanValue()) {
                    Player player = GameData.v().player;
                    int iH = GameLevelData.p().get(i4).h();
                    player.getClass();
                    k0.a.l().a(new w(player.q(), "+" + Integer.toString(iH) + " " + GameString.b("GOLD", false), 1.0f, Color.YELLOW, 0.6f, 0.6f));
                    GameLevelData.p().get(i4).e();
                } else {
                    arrayList.add(GameLevelData.p().get(i4));
                }
            }
        }
        return arrayList;
    }

    public static ArrayList j(Rectangle pVar) {
        ArrayList arrayList = new ArrayList();
        for (MapActor mapActor : GameLevel.f3096c) {
            if (mapActor.S(false) != null) {
                Rectangle pVarS = mapActor.S(false);
                int i2 = i.f68h;
                if (pVar.overlaps(pVarS)) {
                    arrayList.add(Integer.valueOf(mapActor.q()));
                }
            }
        }
        return arrayList;
    }

    public static ArrayList k(int[] iArr, Coords coords, Coords coords2) {
        int i2;
        ArrayList arrayList;
        int i3 = coords.f3287x;
        float f2 = i3 + 48;
        int i4 = coords.f3288y;
        float f3 = i4 + 48;
        Vector2 qVar = new Vector2 (f2, f3);
        int i5 = coords2.f3287x;
        float f4 = i5 + 48;
        int i6 = coords2.f3288y;
        float f5 = i6 + 48;
        Vector2 qVar2 = new Vector2 (f4, f5);
        float f6 = i3 - 48;
        Vector2 qVar3 = new Vector2 (f6, f3);
        float f7 = i5 - 48;
        Vector2 qVar4 = new Vector2 (f7, f5);
        float f8 = i4 - 48;
        Vector2 qVar5 = new Vector2 (f6, f8);
        float f9 = i6 - 48;
        Vector2 qVar6 = new Vector2 (f7, f9);
        Vector2 qVar7 = new Vector2 (f2, f8);
        Vector2 qVar8 = new Vector2 (f4, f9);
        Vector2 qVar9 = f2410g0;
        qVar9.f91a = i3;
        qVar9.f92b = i4;
        Vector2 qVar10 = f2411h0;
        qVar10.f91a = i5;
        qVar10.f92b = i6;
        ArrayList arrayList2 = new ArrayList();
        int i7 = 0;
        while (true) {
            ArrayList<MapActor> arrayList3 = GameLevel.f3096c;
            if (i7 >= arrayList3.size()) {
                return arrayList2;
            }
            if (arrayList3.get(i7).visibleToPlayer.booleanValue()) {
                int iZ = arrayList3.get(i7).Z();
                ArrayList arrayList4 = arrayList2;
                if (!GameWorld.f3189c.g(arrayList3.get(i7).r(), iArr) || arrayList3.get(i7).i0() || ((arrayList3.get(i7).f3092x + iZ < qVar9.f91a && arrayList3.get(i7).f3092x + iZ < qVar10.f91a) || ((arrayList3.get(i7).f3093y + iZ < qVar9.f92b && arrayList3.get(i7).f3093y + iZ < qVar10.f92b) || ((arrayList3.get(i7).f3092x - iZ > qVar9.f91a && arrayList3.get(i7).f3092x - iZ > qVar10.f91a) || (arrayList3.get(i7).f3093y - iZ > qVar9.f92b && arrayList3.get(i7).f3093y - iZ > qVar10.f92b))))) {
                    i2 = i7;
                    arrayList = arrayList4;
                } else {
                    int i8 = iZ / 2;
                    int i9 = arrayList3.get(i7).f3092x - i8;
                    int i10 = arrayList3.get(i7).f3093y - i8;
                    float f10 = i9 - 48;
                    int i11 = i7;
                    float[] fArr = f2409f0;
                    fArr[0] = f10;
                    float f11 = i10 - 48;
                    fArr[1] = f11;
                    float f12 = i9 + i8 + 48;
                    fArr[2] = f12;
                    fArr[3] = f11;
                    fArr[4] = f12;
                    float f13 = i10 + i8 + 48;
                    fArr[5] = f13;
                    fArr[6] = f10;
                    fArr[7] = f13;
                    m mVar = f2408e0;
                    mVar.e(fArr);
                    if (i.c(qVar9, qVar10, mVar) || i.c(qVar, qVar2, mVar) || i.c(qVar3, qVar4, mVar) || i.c(qVar5, qVar6, mVar) || i.c(qVar7, qVar8, mVar)) {
                        i2 = i11;
                        arrayList = arrayList4;
                        arrayList.add(Integer.valueOf(arrayList3.get(i2).q()));
                    } else {
                        arrayList = arrayList4;
                        i2 = i11;
                    }
                }
            } else {
                i2 = i7;
                arrayList = arrayList2;
            }
            ArrayList arrayList5 = arrayList;
            i7 = i2 + 1;
            arrayList2 = arrayList5;
        }
    }

    public static ArrayList l(int[] iArr, Rectangle pVar) {
        ArrayList arrayList = new ArrayList();
        int size = GameLevel.f3096c.size();
        for (int i2 = 0; i2 < size; i2++) {
            ArrayList<MapActor> arrayList2 = GameLevel.f3096c;
            if (arrayList2.get(i2).S(false) != null) {
                Rectangle pVarS = arrayList2.get(i2).S(false);
                int i3 = i.f68h;
                if (pVar.overlaps(pVarS) && GameWorld.f3189c.g(iArr, arrayList2.get(i2).r()) && !arrayList2.get(i2).i0()) {
                    arrayList.add(Integer.valueOf(arrayList2.get(i2).q()));
                }
            }
        }
        return arrayList;
    }

    public static int n(int[] iArr, Rectangle pVar) {
        int size = GameLevel.f3096c.size();
        for (int i2 = 0; i2 < size; i2++) {
            ArrayList<MapActor> arrayList = GameLevel.f3096c;
            if (arrayList.get(i2).S(false) != null) {
                Rectangle pVarS = arrayList.get(i2).S(false);
                int i3 = i.f68h;
                if (pVar.overlaps(pVarS) && GameWorld.f3189c.g(iArr, arrayList.get(i2).r()) && !arrayList.get(i2).i0()) {
                    return arrayList.get(i2).q();
                }
            }
        }
        return 0;
    }

    public static double p(Coords coords, Coords coords2) {
        int i2 = coords.f3287x;
        int i3 = coords.f3288y;
        int i4 = i2 - coords2.f3287x;
        int i5 = i3 - coords2.f3288y;
        return Math.sqrt((i5 * i5) + (i4 * i4));
    }

    public static w.b q() {
        return f2412i0.H;
    }

    public static int r(int i2, int i3, int i4, int i5) {
        return Math.abs(i3 - i5) + Math.abs(i2 - i4);
    }

    public static int s(Coords coords, Coords coords2) {
        return r(coords.f3287x, coords.f3288y, coords2.f3287x, coords2.f3288y);
    }

    public static Coords u(int i2, int i3) {
        return new Coords((i2 * 32) + 16, (i3 * 32) + 16);
    }

    public final boolean C(int i2, int i3) {
        if (!this.f2432q) {
            v();
        }
        if (i2 < 0 || i3 < 0 || i2 > this.f2417b - 1 || i3 > this.f2418c - 1) {
            return true;
        }
        Door doorN = GameLevelData.o().n(i2, i3);
        if (doorN != null && !doorN.G()) {
            return true;
        }
        SecretDoor secretDoorU = GameLevelData.o().u(i2, i3);
        return secretDoorU != null ? !secretDoorU.J() : this.f2431p[i2][i3] == 1;
    }

    public final boolean D(int i2, int i3, int i4, int i5, int i6, int i7) {
        Vector2 qVar = f2410g0;
        qVar.f91a = i2;
        qVar.f92b = i3;
        Vector2 qVar2 = f2411h0;
        qVar2.f91a = i4;
        qVar2.f92b = i5;
        char c2 = 0;
        int iMax = Math.max(Math.min(i2, i4) / 32, 0);
        int iMin = Math.min(Math.max(i2, i4) / 32, this.f2417b);
        int iMax2 = Math.max(Math.min(i3, i5) / 32, 0);
        int iMin2 = Math.min(Math.max(i3, i5) / 32, this.f2418c);
        while (true) {
            m mVar = f2408e0;
            float[] fArr = f2409f0;
            if (iMax > iMin) {
                if (i6 != 0) {
                    int i8 = 0;
                    while (true) {
                        ArrayList<MapActor> arrayList = GameLevel.f3096c;
                        if (i8 >= arrayList.size()) {
                            break;
                        }
                        int iZ = arrayList.get(i8).Z();
                        if (arrayList.get(i8).q() != i6 && arrayList.get(i8).q() != i7 && !arrayList.get(i8).i0() && ((arrayList.get(i8).f3092x + iZ >= qVar.f91a || arrayList.get(i8).f3092x + iZ >= qVar2.f91a) && ((arrayList.get(i8).f3093y + iZ >= qVar.f92b || arrayList.get(i8).f3093y + iZ >= qVar2.f92b) && ((arrayList.get(i8).f3092x - iZ <= qVar.f91a || arrayList.get(i8).f3092x - iZ <= qVar2.f91a) && (arrayList.get(i8).f3093y - iZ <= qVar.f92b || arrayList.get(i8).f3093y - iZ <= qVar2.f92b))))) {
                            int i9 = iZ / 2;
                            int i10 = arrayList.get(i8).f3092x - i9;
                            int i11 = arrayList.get(i8).f3093y - i9;
                            float f2 = i10 - 10;
                            fArr[0] = f2;
                            float f3 = i11 - 10;
                            fArr[1] = f3;
                            float f4 = i10 + iZ + 10;
                            fArr[2] = f4;
                            fArr[3] = f3;
                            fArr[4] = f4;
                            float f5 = i11 + iZ + 10;
                            fArr[5] = f5;
                            fArr[6] = f2;
                            fArr[7] = f5;
                            mVar.e(fArr);
                            if (i.c(qVar, qVar2, mVar)) {
                                return false;
                            }
                        }
                        i8++;
                    }
                }
                return true;
            }
            int i12 = iMax2;
            while (i12 <= iMin2) {
                if (y(iMax, i12) || K(iMax, i12)) {
                    int i13 = iMax * 32;
                    int i14 = i12 * 32;
                    float f6 = i13;
                    fArr[c2] = f6;
                    float f7 = i14;
                    fArr[1] = f7;
                    float f8 = i13 + 32;
                    fArr[2] = f8;
                    fArr[3] = f7;
                    fArr[4] = f8;
                    float f9 = i14 + 32;
                    fArr[5] = f9;
                    fArr[6] = f6;
                    fArr[7] = f9;
                    mVar.e(fArr);
                    if (i.c(qVar, qVar2, mVar)) {
                        return false;
                    }
                }
                i12++;
                c2 = 0;
            }
            iMax++;
            c2 = 0;
        }
    }

    public final ArrayList<Bed> G(Rectangle pVar) {
        ArrayList<Bed> arrayList = this.Y;
        arrayList.clear();
        int size = this.f2440z.size();
        for (int i2 = 0; i2 < size; i2++) {
            Rectangle pVar2 = f2406c0;
            pVar2.set(this.f2440z.get(i2).position.f3287x - 64, this.f2440z.get(i2).position.f3288y - 64, 100.0f, 100.0f);
            if (pVar2 != null) {
                int i3 = i.f68h;
                if (pVar2.overlaps(pVar)) {
                    arrayList.add(this.f2440z.get(i2));
                }
            }
        }
        return arrayList;
    }

    public final ArrayList<MapContainer> I(Rectangle pVar) {
        ArrayList<MapContainer> arrayList = this.S;
        arrayList.clear();
        int size = GameLevelData.m().size();
        for (int i2 = 0; i2 < size; i2++) {
            Rectangle pVar2 = f2406c0;
            pVar2.set(GameLevelData.m().get(i2).f3092x - 50, GameLevelData.m().get(i2).f3093y - 25, 80.0f, 80.0f);
            if (pVar2 != null) {
                int i3 = i.f68h;
                if (pVar2.overlaps(pVar)) {
                    arrayList.add(GameLevelData.m().get(i2));
                }
            }
        }
        return arrayList;
    }

    public final ArrayList<MapConversation> J(int i2, int i3) {
        ArrayList<MapConversation> arrayList = this.R;
        arrayList.clear();
        int iQ = (GameData.v().party == null || !GameData.v().party.j()) ? 0 : GameData.v().party.f().q();
        int size = GameLevel.f3097d.size();
        int i4 = 0;
        while (true) {
            if (i4 >= size) {
                break;
            }
            if ((!(GameLevel.f3097d.get(i4) instanceof NPC) || !((NPC) GameLevel.f3097d.get(i4)).i0()) && ((!(GameLevel.f3097d.get(i4) instanceof NPC) || !((NPC) GameLevel.f3097d.get(i4)).h0()) && GameLevel.f3097d.get(i4).A() != null && !GameLevel.f3097d.get(i4).A().equals("") && !GameWorld.f3189c.f(GameLevel.f3097d.get(i4).r()) && iQ != GameLevel.f3097d.get(i4).q())) {
                float f2 = ((GameLevel.f3097d.get(i4) instanceof NPC) && ((NPC) GameLevel.f3097d.get(i4)).k0()) ? 22.550001f : 41.0f;
                if (Math.abs(i2 - GameLevel.f3097d.get(i4).f3092x) < f2 && Math.abs(i3 - GameLevel.f3097d.get(i4).f3093y) < f2) {
                    arrayList.add(new d(GameLevel.f3097d.get(i4).q(), GameLevel.f3097d.get(i4).A()));
                }
            }
            i4++;
        }
        int size2 = this.B.size();
        for (int i5 = 0; i5 < size2; i5++) {
            if (Math.abs(i2 - this.B.get(i5).g()) < 51 && Math.abs(i3 - this.B.get(i5).h()) < 51 && this.B.get(i5).f()) {
                arrayList.add(this.B.get(i5));
            }
        }
        if (iQ > 0) {
            NPC npcF = GameData.v().party.f();
            if (!npcF.i0() && !npcF.h0() && npcF.A() != null && !npcF.A().equals("") && Math.abs(i2 - npcF.f3092x) < 22.550001f && Math.abs(i3 - npcF.f3093y) < 22.550001f) {
                arrayList.add(new d(iQ, npcF.A()));
            }
        }
        return arrayList;
    }

    public final boolean K(int i2, int i3) {
        int[][] iArr;
        int[][] iArr2 = this.f2434t;
        if (iArr2 != null && (iArr = this.f2435u) != null) {
            try {
                if (iArr2[i2][i3] != 1) {
                    if (iArr[i2][i3] != 1) {
                        return false;
                    }
                }
                return true;
            } catch (Exception unused) {
            }
        }
        return false;
    }

    public final Coords M(int i2) {
        for (MapEntry mapEntry : this.f2438x) {
            if (mapEntry.entry_id == i2) {
                return mapEntry.coords;
            }
        }
        l.d("Caution, MapEntry " + i2 + " not found in GameMap.getEntryCoords");
        return new Coords(0, 0);
    }

    public final ItemConversation Q(String str) {
        for (ItemConversation itemConversation : this.B) {
            String str2 = itemConversation.tag;
            if (str2 != null && str2.equals(str)) {
                return itemConversation;
            }
        }
        return null;
    }

    public final com.badlogic.gdx.utils.Array<Coords> T(int i2, int i3) {
        com.badlogic.gdx.utils.a<Coords> aVar = this.f2436v;
        aVar.clear();
        int i4 = i2 - 1;
        int i5 = i3 + 1;
        if (!y(i4, i5)) {
            aVar.a(new Coords(i4, i5));
        }
        int i6 = i2 + 1;
        if (!y(i6, i5)) {
            aVar.a(new Coords(i6, i5));
        }
        int i7 = i3 - 1;
        if (!y(i4, i7)) {
            aVar.a(new Coords(i4, i7));
        }
        if (!y(i6, i7)) {
            aVar.a(new Coords(i6, i7));
        }
        if (!y(i2, i5)) {
            aVar.a(new Coords(i2, i5));
        }
        if (!y(i4, i3)) {
            aVar.a(new Coords(i4, i3));
        }
        if (!y(i6, i3)) {
            aVar.a(new Coords(i6, i3));
        }
        if (!y(i2, i7)) {
            aVar.a(new Coords(i2, i7));
        }
        return aVar;
    }

    public final Coords W(String str) {
        for (Waypoint waypoint : this.C) {
            if (waypoint.id.trim().equals(str.trim())) {
                return new Coords(waypoint.f3298x, waypoint.f3299y);
            }
        }
        System.out.println("waypoint not found");
        return new Coords(-1, -1);
    }

    public final boolean X() {
        ArrayList<RestPoint> arrayList = this.f2439y;
        return (arrayList == null || arrayList.size() == 0) ? false : true;
    }

    public final int a(Coords coords, int[] iArr) {
        double d2 = 320;
        int size = GameLevel.f3096c.size();
        int iQ = 0;
        for (int i2 = 0; i2 < size; i2++) {
            WorldFactions worldFactions = GameWorld.f3189c;
            ArrayList<MapActor> arrayList = GameLevel.f3096c;
            if (worldFactions.g(iArr, arrayList.get(i2).r()) && arrayList.get(i2).m0()) {
                double dS = s(coords, arrayList.get(i2).B());
                if (dS < d2 && e0(coords, arrayList.get(i2).B())) {
                    iQ = arrayList.get(i2).q();
                    d2 = dS;
                }
            }
        }
        return iQ;
    }

    public final boolean a0(int i2, int i3) {
        return this.f2429n[i2 / 32][i3 / 32] > 0;
    }

    public final int b(int i2, ArrayList<Integer> arrayList) {
        MapActor mapActorG = GameLevel.g(i2);
        this.Z = mapActorG;
        if (mapActorG == null || arrayList.size() == 0) {
            return 0;
        }
        Coords coordsB = this.Z.B();
        double d2 = 9999.0d;
        int iIntValue = 0;
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            if (arrayList.get(i3).intValue() != i2) {
                MapActor mapActorG2 = GameLevel.g(arrayList.get(i3).intValue());
                this.f2416a0 = mapActorG2;
                if (mapActorG2 != null && GameWorld.f3189c.g(this.Z.r(), this.f2416a0.r())) {
                    double dF = F(arrayList.get(i3).intValue(), coordsB);
                    if (dF < d2) {
                        iIntValue = arrayList.get(i3).intValue();
                        d2 = dF;
                    }
                }
            }
        }
        return iIntValue;
    }

    public final boolean b0(int i2, int i3) {
        int[][] iArr = this.f2433r;
        if (i2 >= iArr.length || i3 >= iArr[0].length) {
            this.f2433r = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, this.f2417b + 1, this.f2418c + 1);
        }
        if (this.f2423h) {
            return true;
        }
        return this.f2433r[i2][i3] > 0;
    }

    public final int c(int i2, ArrayList<Integer> arrayList) {
        MapActor mapActorG = GameLevel.g(i2);
        this.Z = mapActorG;
        if (mapActorG == null || arrayList.size() == 0) {
            return 0;
        }
        Coords coordsB = this.Z.B();
        double d2 = 9999.0d;
        int iIntValue = 0;
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            if (arrayList.get(i3).intValue() != i2) {
                MapActor mapActorG2 = GameLevel.g(arrayList.get(i3).intValue());
                this.f2416a0 = mapActorG2;
                if (mapActorG2 != null && GameWorld.f3189c.g(this.Z.r(), this.f2416a0.r())) {
                    double dF = F(arrayList.get(i3).intValue(), coordsB);
                    if (dF < d2 && e0(this.Z.B(), this.f2416a0.B())) {
                        iIntValue = arrayList.get(i3).intValue();
                        d2 = dF;
                    }
                }
            }
        }
        return iIntValue;
    }

    public final boolean c0(int i2, int i3, int i4, int i5) {
        if (this.f2419d[i4][i5] > 0 || i4 > this.f2417b - 2 || i5 > this.f2418c - 2 || i4 < 2 || i5 < 2 || !d0(i4, i5)) {
            return false;
        }
        if (i3 > i5) {
            int[][] iArr = this.f2430o;
            if (iArr[i4 - 1][i5] > 0 && iArr[i4 + 1][i5] > 0) {
                return true;
            }
        }
        if (i2 < i4) {
            int[] iArr2 = this.f2430o[i4];
            if (iArr2[i5 - 1] > 0 && iArr2[i5 + 1] > 0) {
                return true;
            }
        }
        return false;
    }

    public final int d(Coords coords, int[] iArr, int i2) {
        double d2 = i2 * 32;
        int size = GameLevel.f3096c.size();
        int iQ = 0;
        for (int i3 = 0; i3 < size; i3++) {
            ArrayList<MapActor> arrayList = GameLevel.f3096c;
            int[] iArrR = arrayList.get(i3).r();
            if (!iArr.equals(iArrR) && GameWorld.f3189c.g(iArr, iArrR) && arrayList.get(i3).m0()) {
                double dS = s(coords, arrayList.get(i3).B());
                if (dS < d2 && z(coords, arrayList.get(i3).B())) {
                    iQ = arrayList.get(i3).q();
                    d2 = dS;
                }
            }
        }
        return iQ;
    }

    public final boolean d0(int i2, int i3) {
        return this.f2430o[i2][i3] == 1;
    }

    public final boolean e0(Coords coords, Coords coords2) {
        int i2 = coords.f3287x;
        float f2 = coords.f3288y;
        Vector2 qVar = f2410g0;
        qVar.f91a = i2;
        qVar.f92b = f2;
        int i3 = coords2.f3287x;
        float f3 = coords2.f3288y;
        Vector2 qVar2 = f2411h0;
        qVar2.f91a = i3;
        qVar2.f92b = f3;
        int iMin = Math.min(Math.max(coords.f3287x, coords2.f3287x) / 32, this.f2417b);
        int iMax = Math.max(Math.min(coords.f3288y, coords2.f3288y) / 32, 0);
        int iMin2 = Math.min(Math.max(coords.f3288y, coords2.f3288y) / 32, this.f2418c);
        for (int iMax2 = Math.max(Math.min(i2, i3) / 32, 0); iMax2 <= iMin; iMax2++) {
            for (int i4 = iMax; i4 <= iMin2; i4++) {
                if (C(iMax2, i4)) {
                    int i5 = iMax2 * 32;
                    int i6 = i4 * 32;
                    float f4 = i5 - 10;
                    float[] fArr = f2409f0;
                    fArr[0] = f4;
                    float f5 = i6 - 10;
                    fArr[1] = f5;
                    float f6 = i5 + 42;
                    fArr[2] = f6;
                    fArr[3] = f5;
                    fArr[4] = f6;
                    float f7 = i6 + 42;
                    fArr[5] = f7;
                    fArr[6] = f4;
                    fArr[7] = f7;
                    m mVar = f2408e0;
                    mVar.e(fArr);
                    if (i.c(qVar, qVar2, mVar)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public final boolean f(int i2, int i3, int i4, boolean z2) {
        if (i(i2, i3, GameLevel.g(i4).b0())) {
            return true;
        }
        if (z2) {
            int size = GameLevel.f3096c.size();
            int i5 = 0;
            while (true) {
                if (i5 < size) {
                    ArrayList<MapActor> arrayList = GameLevel.f3096c;
                    if (arrayList.get(i5).q() != i4 && !arrayList.get(i5).i0() && ((i4 != 1 || !arrayList.get(i5).k0()) && Math.abs(i2 - arrayList.get(i5).f3092x) <= arrayList.get(i5).Z() + 2 && Math.abs(i3 - arrayList.get(i5).f3093y) <= arrayList.get(i5).Z() + 2)) {
                        break;
                    }
                    i5++;
                } else {
                    int size2 = GameLevelData.y().size();
                    for (int i6 = 0; i6 < size2; i6++) {
                        if (GameLevelData.y().get(i6).q() != i4) {
                            int iAbs = Math.abs(i2 - GameLevelData.y().get(i6).f3092x);
                            GameLevelData.y().get(i6).getClass();
                            if (iAbs <= 20) {
                                int iAbs2 = Math.abs(i3 - GameLevelData.y().get(i6).f3093y);
                                GameLevelData.y().get(i6).getClass();
                                if (iAbs2 <= 20) {
                                }
                            } else {
                                continue;
                            }
                        }
                    }
                }
            }
            return true;
        }
        return false;
    }

    public final ArrayList<MapCastle> f0(Rectangle pVar) {
        ArrayList<MapCastle> arrayList = this.X;
        arrayList.clear();
        int size = this.A.size();
        for (int i2 = 0; i2 < size; i2++) {
            Rectangle pVar2 = f2406c0;
            pVar2.set(this.A.get(i2).position.f3287x - 64, this.A.get(i2).position.f3288y - 64, 100.0f, 100.0f);
            if (pVar2 != null) {
                int i3 = i.f68h;
                if (pVar2.overlaps(pVar)) {
                    arrayList.add(this.A.get(i2));
                }
            }
        }
        return arrayList;
    }

    public final boolean g(int i2, int i3, int i4) {
        if (i2 >= 8 && i3 >= 8) {
            int i5 = i2 + i4 + 6;
            int i6 = (i2 - i4) + 6;
            int i7 = (i3 + i4) - 6;
            int i8 = (i3 - i4) - 6;
            if (!a0(i6, i8) && !a0(i6, i7) && !a0(i5, i7) && !a0(i5, i8)) {
                return false;
            }
        }
        return true;
    }

    public final ArrayList<PlantSpawn> g0(int i2, int i3) {
        ArrayList<PlantSpawn> arrayList = this.U;
        arrayList.clear();
        AStarPathFinder aStarPathFinder = GameLevel.f3094a;
        for (PlantSpawn plantSpawn : GameLevelData.o().plants) {
            if (!plantSpawn.destroy && Math.abs(i2 - plantSpawn.f3092x) < 51 && Math.abs((i3 - plantSpawn.f3093y) - 8) < 51) {
                arrayList.add(plantSpawn);
            }
        }
        return arrayList;
    }

    public final ArrayList<RestPoint> h0(Rectangle pVar) {
        ArrayList<RestPoint> arrayList = this.W;
        arrayList.clear();
        int size = this.f2439y.size();
        for (int i2 = 0; i2 < size; i2++) {
            Rectangle pVar2 = f2406c0;
            pVar2.set(this.f2439y.get(i2).position.f3287x - 64, this.f2439y.get(i2).position.f3288y - 64, 100.0f, 100.0f);
            if (pVar2 != null) {
                int i3 = i.f68h;
                if (pVar2.overlaps(pVar)) {
                    arrayList.add(this.f2439y.get(i2));
                }
            }
        }
        return arrayList;
    }

    public final boolean i(int i2, int i3, int i4) {
        if (i2 >= 8 && i3 >= 8) {
            int i5 = i2 + i4 + 6;
            int i6 = (i3 + i4) - 6;
            int i7 = ((i2 - i4) + 6) / 32;
            int i8 = ((i3 - i4) - 6) / 32;
            if (!y(i7, i8)) {
                int i9 = i6 / 32;
                if (!y(i7, i9)) {
                    int i10 = i5 / 32;
                    if (!y(i10, i9) && !y(i10, i8)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public final ArrayList<Shop> i0(int i2, int i3) {
        ArrayList<Shop> arrayList = this.T;
        arrayList.clear();
        AStarPathFinder aStarPathFinder = GameLevel.f3094a;
        int size = GameLevelData.o().shops.size();
        for (int i4 = 0; i4 < size; i4++) {
            if (!GameLevelData.o().shops.get(i4).s() && Math.abs(i2 - GameLevelData.o().shops.get(i4).p()) < 51 && Math.abs(i3 - GameLevelData.o().shops.get(i4).q()) < 51) {
                arrayList.add(GameLevelData.o().shops.get(i4));
            }
        }
        return arrayList;
    }

    public final ArrayList<MapItem> j0(int i2, int i3) {
        ArrayList<MapItem> arrayList = this.V;
        arrayList.clear();
        for (MapItem mapItem : GameLevelData.r()) {
            if (!mapItem.destroy && mapItem.Q() && Math.abs(i2 - mapItem.f3092x) < 30.6f && Math.abs((i3 - mapItem.f3093y) - 8) < 30.6f) {
                arrayList.add(mapItem);
            }
        }
        return arrayList;
    }

    /* JADX WARN: Removed duplicated region for block: B:82:0x0177  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void k0() {
        int i2;
        int i3;
        int i4;
        byte b2 = 1;
        if (this.f2423h || this.f2419d == null) {
            return;
        }
        AStarPathFinder aStarPathFinder = GameLevel.f3094a;
        int i5 = GameData.v().player.f3092x / 32;
        int i6 = GameData.v().player.f3093y / 32;
        int i7 = 0;
        int iMax = Math.max(i5 - f2405b0, 0);
        int iMin = Math.min(i5 + f2405b0, this.f2417b);
        int iMax2 = Math.max(i6 - f2405b0, 0);
        int iMin2 = Math.min(i6 + f2405b0, this.f2418c);
        for (int[] iArr : this.f2433r) {
            Arrays.fill(iArr, 0);
        }
        int[][] iArr2 = this.f2433r;
        int length = iArr2.length;
        int i8 = this.f2417b + 1;
        if (length != i8 || iArr2[1].length != this.f2418c + 1) {
            this.f2433r = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, i8, this.f2418c + 1);
        }
        int i9 = 0;
        while (i9 < 360) {
            double d2 = i9 * 0.01745f;
            float fCos = (float) Math.cos(d2);
            float fSin = (float) Math.sin(d2);
            if (this.f2423h) {
                i3 = i7;
            } else {
                AStarPathFinder aStarPathFinder2 = GameLevel.f3094a;
                int i10 = GameData.v().player.f3092x / 32;
                int i11 = GameData.v().player.f3093y / 32;
                float f2 = i10 + 0.5f;
                float f3 = i11 + 0.5f;
                int i12 = i7;
                while (i12 < f2405b0 && (i2 = (int) f2) >= 0) {
                    if (i2 < this.f2417b && (i4 = (int) f3) >= 0) {
                        if (i4 >= this.f2418c) {
                            b2 = 1;
                        } else {
                            this.f2433r[i2][i4] = 1;
                            this.f2419d[i2][i4] = 1;
                            if (C(i2, i4)) {
                                if (i10 < i2 || i11 > i4) {
                                    int[] iArr3 = this.f2433r[i2];
                                    i3 = 0;
                                    iArr3[i4] = 0;
                                } else {
                                    i3 = 0;
                                }
                                b2 = 1;
                            } else {
                                f2 += fCos;
                                f3 += fSin;
                                b2 = 1;
                                i12++;
                                i7 = 0;
                            }
                        }
                    }
                    i3 = 0;
                }
                i3 = i7;
            }
            i9 += b2;
            i7 = i3;
        }
        for (int i13 = iMax + 1; i13 < iMin; i13 += b2) {
            for (int i14 = iMax2 + 1; i14 < iMin2; i14 += b2) {
                int[][] iArr4 = this.f2433r;
                if (iArr4[i13][i14] == 0) {
                    int i15 = i13 + 1;
                    if (iArr4[i15][i14] != b2 || C(i15, i14)) {
                        int i16 = i13 - 1;
                        if (this.f2433r[i16][i14] != b2 || C(i16, i14)) {
                            int i17 = i14 + 1;
                            if (this.f2433r[i13][i17] != b2 || C(i13, i17)) {
                                int i18 = i14 - 1;
                                if ((this.f2433r[i13][i18] == b2 && !C(i13, i18)) || ((this.f2433r[i16][i18] == b2 && !C(i16, i18)) || ((this.f2433r[i16][i17] == b2 && !C(i16, i17)) || ((this.f2433r[i15][i18] == b2 && !C(i15, i18)) || (this.f2433r[i15][i17] == b2 && !C(i15, i17)))))) {
                                    this.f2433r[i13][i14] = -1;
                                    this.f2419d[i13][i14] = b2;
                                }
                            }
                        }
                    }
                }
            }
        }
        while (iMax < iMin) {
            for (int i19 = iMax2; i19 < iMin2; i19 += b2) {
                int[] iArr5 = this.f2433r[iMax];
                if (iArr5[i19] == -1) {
                    iArr5[i19] = b2;
                }
            }
            iMax += b2;
        }
    }

    public final Transition m(int i2, int i3) {
        Rectangle pVar = f2406c0;
        pVar.set(i2, i3, 30.0f, 30.0f);
        for (Transition transition : this.f2437w) {
            Rectangle pVarA = transition.a();
            int i4 = i.f68h;
            if (pVar.overlaps(pVarA)) {
                return transition;
            }
        }
        if (pVar.f90y < 32.0f) {
            Transition transition2 = this.N;
            if (!transition2.area_id.equals("")) {
                Coords coords = transition2.coords;
                AStarPathFinder aStarPathFinder = GameLevel.f3094a;
                coords.f3287x = GameData.v().player.f3092x;
                transition2.coords.f3288y = -1;
                return transition2;
            }
        }
        if (pVar.f90y > this.f2421f - 32) {
            Transition transition3 = this.O;
            if (!transition3.area_id.equals("")) {
                Coords coords2 = transition3.coords;
                AStarPathFinder aStarPathFinder2 = GameLevel.f3094a;
                coords2.f3287x = GameData.v().player.f3092x;
                transition3.coords.f3288y = -2;
                return transition3;
            }
        }
        if (pVar.f89x < 32.0f) {
            Transition transition4 = this.Q;
            if (!transition4.area_id.equals("")) {
                Coords coords3 = transition4.coords;
                AStarPathFinder aStarPathFinder3 = GameLevel.f3094a;
                coords3.f3288y = GameData.v().player.f3093y;
                transition4.coords.f3287x = -1;
                return transition4;
            }
        }
        if (pVar.f89x <= this.f2420e - 32) {
            return null;
        }
        Transition transition5 = this.P;
        if (transition5.area_id.equals("")) {
            return null;
        }
        Coords coords4 = transition5.coords;
        AStarPathFinder aStarPathFinder4 = GameLevel.f3094a;
        coords4.f3288y = GameData.v().player.f3093y;
        transition5.coords.f3287x = -2;
        return transition5;
    }

    public final int[][] o() {
        if (this.s == null) {
            this.s = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, this.f2417b, this.f2418c);
        }
        int[][] iArr = this.s;
        int length = iArr.length;
        int i2 = this.f2417b;
        if (length != i2 || iArr[1].length != this.f2418c) {
            this.s = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, i2, this.f2418c);
        }
        for (int[] iArr2 : this.s) {
            Arrays.fill(iArr2, 0);
        }
        int size = GameLevel.f3096c.size();
        for (int i3 = 0; i3 < size; i3++) {
            ArrayList<MapActor> arrayList = GameLevel.f3096c;
            int i4 = arrayList.get(i3).f3092x / 32;
            int i5 = arrayList.get(i3).f3093y / 32;
            for (int i6 = i4 - 1; i6 <= i4 + 1; i6++) {
                for (int i7 = i5 - 1; i7 <= i5 + 1; i7++) {
                    if (i6 >= 0 && i6 < this.f2417b && i7 >= 0 && i7 < this.f2418c) {
                        Rectangle pVar = f2406c0;
                        pVar.set(i6 * 32, i7 * 32, 32.0f, 32.0f);
                        ArrayList<MapActor> arrayList2 = GameLevel.f3096c;
                        if (arrayList2.get(i3).S(true) != null) {
                            Rectangle pVarS = arrayList2.get(i3).S(true);
                            int i8 = i.f68h;
                            if (pVar.overlaps(pVarS)) {
                                this.s[i6][i7] = 1;
                            }
                        }
                    }
                }
            }
        }
        return this.s;
    }

    public final Coords t(Transition transition) {
        int i2;
        if (transition.entry_id != 0) {
            for (MapEntry mapEntry : this.f2438x) {
                if (mapEntry.entry_id == transition.entry_id) {
                    return mapEntry.coords;
                }
            }
        }
        if (transition.entry_id == 0) {
            Coords coords = transition.coords;
            if (coords.f3287x > 0 && coords.f3288y > 0) {
                return coords;
            }
        }
        Coords coords2 = transition.coords;
        int i3 = coords2.f3287x;
        if (i3 > 0 && (i2 = coords2.f3288y) < 0) {
            if (i2 == -1) {
                return new Coords(i3, this.f2421f - 80);
            }
            if (i2 == -2) {
                return new Coords(i3, 80);
            }
        }
        int i4 = coords2.f3288y;
        if (i4 > 0 && i3 < 0) {
            if (i3 == -1) {
                return new Coords(this.f2420e - 80, i4);
            }
            if (i3 == -2) {
                return new Coords(80, i4);
            }
        }
        return new Coords(64, 64);
    }

    public final void v() {
        for (int i2 = 0; i2 < this.f2417b; i2++) {
            for (int i3 = 0; i3 < this.f2418c; i3++) {
                this.f2430o[i2][i3] = 0;
                if (this.K.j(i2, i3) == null) {
                    this.f2429n[i2][i3] = 1;
                } else {
                    this.f2429n[i2][i3] = 0;
                }
                if (this.I.j(i2, i3) != null) {
                    if (this.I.j(i2, i3).a().c().a("blocked")) {
                        this.f2431p[i2][i3] = 1;
                        this.f2429n[i2][i3] = 1;
                        if (this.I.j(i2, i3).a().c().a("wall")) {
                            this.f2430o[i2][i3] = 1;
                        }
                    }
                    if (this.I.j(i2, i3).a().c().a("obstacle")) {
                        this.f2431p[i2][i3] = 0;
                        this.f2429n[i2][i3] = 1;
                    }
                }
                w.e eVar = this.J;
                if (eVar != null && eVar.j(i2, i3) != null) {
                    this.f2429n[i2][i3] = 1;
                }
            }
        }
        this.f2432q = true;
    }

    public final void w(String str) {
        int i2;
        Iterator<f> it;
        String str2;
        if (ExiledKingdoms.f3378h) {
            f2405b0 = 20;
        } else {
            f2405b0 = 11;
        }
        w.b bVar = this.H;
        if (bVar != null) {
            bVar.dispose();
        }
        h.Array aVar = new h.a();
        w.b bVarH = new w.h(new GdxNativesLoader ()).h("data/tmx/" + str + ".tmx", aVar);
        this.H = bVarH;
        u.MapProperties hVarB = bVarH.b();
        this.M = hVarB;
        this.f2417b = ((Integer) hVarB.c("width")).intValue();
        int iIntValue = ((Integer) this.M.get("height")).intValue();
        this.f2418c = iIntValue;
        int[] iArr = {this.f2417b + 1, iIntValue + 1};
        Class cls = Integer.TYPE;
        this.f2433r = (int[][]) Array.newInstance((Class<?>) cls, iArr);
        if (this.M.containsKey("outdoor")) {
            this.f2423h = true;
        } else {
            this.f2423h = false;
        }
        if (this.M.containsKey("maxlight")) {
            this.f2424i = Integer.parseInt(this.M.get("maxlight").toString());
        } else {
            this.f2424i = 0;
        }
        if (this.M.containsKey("music")) {
            this.f2415a = this.M.get("music").toString();
        } else {
            this.f2415a = "";
        }
        if (this.M.containsKey("nominimap")) {
            this.f2425j = true;
        } else {
            this.f2425j = false;
        }
        if (this.M.containsKey("house")) {
            this.f2426k = true;
        } else {
            this.f2426k = false;
        }
        if (this.M.containsKey("norecall")) {
            this.f2427l = true;
        } else {
            this.f2427l = false;
        }
        if (this.M.containsKey("faction_civilian")) {
            this.E = this.M.get("faction_civilian").toString();
        } else {
            this.E = "";
        }
        if (this.M.containsKey("faction_guard")) {
            this.F = this.M.get("faction_guard").toString();
        } else {
            this.F = "";
        }
        if (this.M.containsKey("faction_trap")) {
            this.G = this.M.get("faction_trap").toString();
        } else {
            this.G = "";
        }
        boolean zA = this.M.containsKey("exit_s");
        Transition transition = this.N;
        if (zA) {
            transition.area_id = this.M.get("exit_s").toString();
        } else {
            transition.area_id = "";
        }
        boolean zA2 = this.M.containsKey("exit_n");
        Transition transition2 = this.O;
        if (zA2) {
            transition2.area_id = this.M.get("exit_n").toString();
        } else {
            transition2.area_id = "";
        }
        boolean zA3 = this.M.containsKey("exit_e");
        Transition transition3 = this.P;
        if (zA3) {
            transition3.area_id = this.M.get("exit_e").toString();
        } else {
            transition3.area_id = "";
        }
        boolean zA4 = this.M.containsKey("exit_w");
        Transition transition4 = this.Q;
        if (zA4) {
            transition4.area_id = this.M.get("exit_w").toString();
        } else {
            transition4.area_id = "";
        }
        this.I = (w.e) this.H.a().c("objects");
        this.K = (w.e) this.H.a().c("ground");
        w.e eVar = (w.e) this.H.a().c("nonwalk");
        this.J = eVar;
        if (eVar != null) {
            eVar.i(false);
        }
        u.d dVarC = this.H.a().c("data");
        this.L = dVarC;
        dVarC.i(false);
        this.f2429n = (int[][]) Array.newInstance((Class<?>) cls, this.f2417b, this.f2418c);
        this.f2430o = (int[][]) Array.newInstance((Class<?>) cls, this.f2417b, this.f2418c);
        this.f2434t = (int[][]) Array.newInstance((Class<?>) cls, this.f2417b, this.f2418c);
        this.f2435u = (int[][]) Array.newInstance((Class<?>) cls, this.f2417b, this.f2418c);
        this.f2431p = (int[][]) Array.newInstance((Class<?>) cls, this.f2417b, this.f2418c);
        this.f2419d = (byte[][]) Array.newInstance((Class<?>) Byte.TYPE, this.f2417b, this.f2418c);
        this.f2437w = new ArrayList<>();
        this.f2439y = new ArrayList<>();
        this.f2440z = new ArrayList<>();
        this.A = new ArrayList<>();
        this.f2438x = new ArrayList<>();
        this.C = new ArrayList<>();
        this.D = new ArrayList<>();
        this.B = new ArrayList<>();
        this.f2420e = this.f2417b * 32;
        this.f2421f = this.f2418c * 32;
        v();
        this.f2428m = new c();
        int i3 = 0;
        while (true) {
            i2 = this.f2417b;
            if (i3 >= i2) {
                break;
            }
            for (int i4 = 0; i4 < this.f2418c; i4++) {
                if (this.K.j(i3, i4) != null && this.K.j(i3, i4).a().c().a("effect")) {
                    this.f2428m.a(i3, i4, this.K.j(i3, i4).a().c().b("effect").toString());
                }
            }
            i3++;
        }
        this.f2433r = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, i2, this.f2418c);
        this.f2437w.clear();
        this.f2438x.clear();
        Iterator<f> it2 = this.L.b().iterator();
        while (true) {
            a.b bVar2 = (a.b) it2;
            if (!bVar2.hasNext()) {
                this.f2422g = GameWorld.f3192f.g(str);
                GameData.v().currentMapName = this.f2422g;
                return;
            }
            f fVar = (f) bVar2.next();
            if (fVar.a().a("type")) {
                try {
                    String strTrim = fVar.a().b("type").toString().trim();
                    if (strTrim.equals("transition")) {
                        Transition transition5 = new Transition();
                        transition5.area_id = fVar.a().b("area_id").toString();
                        transition5.entry_id = Integer.parseInt(fVar.a().b("entry_id").toString());
                        transition5.b(((v.b) fVar).b());
                        this.f2437w.add(transition5);
                    }
                    if (strTrim.equals("waypoint")) {
                        ArrayList<Waypoint> arrayList = this.C;
                        try {
                            it = it2;
                            try {
                                int i5 = (int) Float.parseFloat(fVar.a().b("x").toString());
                                int i6 = (int) Float.parseFloat(fVar.a().b("y").toString());
                                str2 = "entry_id";
                                String string = fVar.a().b("id").toString();
                                Waypoint waypoint = new Waypoint();
                                waypoint.f3298x = i5;
                                waypoint.f3299y = i6;
                                waypoint.id = string;
                                arrayList.add(waypoint);
                            } catch (Exception unused) {
                                r0.a.a("ERROR 6 ;" + GameData.v().CurrentLevel + "; general map entity initialization error");
                                it2 = it;
                            }
                        } catch (Exception unused2) {
                            it = it2;
                            r0.a.a("ERROR 6 ;" + GameData.v().CurrentLevel + "; general map entity initialization error");
                            it2 = it;
                        }
                    } else {
                        it = it2;
                        str2 = "entry_id";
                    }
                    if (strTrim.equals("restpoint")) {
                        RestPoint restPoint = new RestPoint();
                        restPoint.position.f3287x = (int) Float.parseFloat(fVar.a().b("x").toString());
                        restPoint.position.f3288y = (int) Float.parseFloat(fVar.a().b("y").toString());
                        restPoint.name = fVar.a().b("name").toString();
                        restPoint.id = fVar.a().b("id").toString();
                        this.f2439y.add(restPoint);
                    }
                    if (strTrim.equals("bed")) {
                        Bed bed = new Bed();
                        bed.position.f3287x = (int) Float.parseFloat(fVar.a().b("x").toString());
                        bed.position.f3288y = (int) Float.parseFloat(fVar.a().b("y").toString());
                        this.f2440z.add(bed);
                    }
                    if (strTrim.equals("castle")) {
                        MapCastle mapCastle = new MapCastle();
                        mapCastle.position.f3287x = (int) Float.parseFloat(fVar.a().b("x").toString());
                        mapCastle.position.f3288y = (int) Float.parseFloat(fVar.a().b("y").toString());
                        mapCastle.id = fVar.a().b("castle_id").toString();
                        this.A.add(mapCastle);
                    }
                    if (strTrim.equals("conversation")) {
                        ItemConversation itemConversation = new ItemConversation(fVar.a().b("icon").toString(), fVar.a().b("name").toString(), fVar.a().b("conversation").toString(), (int) Float.parseFloat(fVar.a().b("x").toString()), (int) Float.parseFloat(fVar.a().b("y").toString()), fVar.a().a("conditions") ? fVar.a().b("conditions").toString() : "");
                        if (itemConversation.getName().contains("@")) {
                            try {
                                itemConversation.i(GameString.b(itemConversation.getName().replace("@", ""), true));
                            } catch (Exception unused3) {
                                r0.a.a("ERROR 6 ;" + GameData.v().CurrentLevel + "; general map entity initialization error");
                            }
                        }
                        if (fVar.a().a("tag")) {
                            itemConversation.tag = fVar.a().b("tag").toString();
                        }
                        this.B.add(itemConversation);
                    }
                    if (strTrim.equals("entry")) {
                        MapEntry mapEntry = new MapEntry();
                        mapEntry.entry_id = Integer.parseInt(fVar.a().b(str2).toString());
                        mapEntry.coords.f3287x = (int) Float.parseFloat(fVar.a().b("x").toString());
                        mapEntry.coords.f3288y = (int) Float.parseFloat(fVar.a().b("y").toString());
                        this.f2438x.add(mapEntry);
                    }
                } catch (Exception unused4) {
                    it = it2;
                }
            } else {
                it = it2;
            }
            it2 = it;
        }
    }

    public final void x() {
        Iterator<f> it = this.L.b().iterator();
        while (true) {
            a.b bVar = (a.b) it;
            if (!bVar.hasNext()) {
                return;
            }
            f fVar = (f) bVar.next();
            if (fVar.a().a("type") && fVar.a().b("type").toString().trim().equals("mapitem")) {
                try {
                    String string = fVar.a().a("conditions") ? fVar.a().b("conditions").toString() : "";
                    if (string.equals("") || new ConditionsSet(string).a().booleanValue()) {
                        MapItem mapItem = new MapItem(fVar.a());
                        mapItem.T();
                        AStarPathFinder aStarPathFinder = GameLevel.f3094a;
                        GameLevelData.f(mapItem);
                    }
                } catch (Exception unused) {
                    r0.a.a("ERROR 6 ;" + GameData.v().CurrentLevel + "; mapItem initialization error");
                }
            }
            if (fVar.a().a("type") && fVar.a().b("type").toString().trim().equals("mapeffect")) {
                try {
                    String string2 = fVar.a().a("conditions") ? fVar.a().b("conditions").toString() : "";
                    if (string2.equals("") || new ConditionsSet(string2).a().booleanValue()) {
                        MapEffectEntity mapEffectEntity = new MapEffectEntity(fVar.a());
                        AStarPathFinder aStarPathFinder2 = GameLevel.f3094a;
                        GameLevelData.e(mapEffectEntity);
                    }
                } catch (Exception unused2) {
                    r0.a.a("ERROR 6 ;" + GameData.v().CurrentLevel + "; mapEffect initialization error");
                }
            }
        }
    }

    public final boolean y(int i2, int i3) {
        boolean zJ;
        if (!this.f2432q) {
            v();
        }
        if (i2 < 0 || i3 < 0 || i2 > this.f2417b - 1 || i3 > this.f2418c - 1) {
            return true;
        }
        GameLevelData gameLevelDataO = GameLevelData.o();
        if (gameLevelDataO.secretDoors == null) {
            gameLevelDataO.secretDoors = new ArrayList<>();
        }
        int size = gameLevelDataO.secretDoors.size();
        int i4 = 0;
        while (true) {
            if (i4 >= size) {
                zJ = false;
                break;
            }
            if (gameLevelDataO.secretDoors.get(i4).f3092x / 32 == i2 && gameLevelDataO.secretDoors.get(i4).f3093y / 32 == i3) {
                zJ = gameLevelDataO.secretDoors.get(i4).J();
                break;
            }
            i4++;
        }
        if (zJ) {
            return false;
        }
        if (this.f2429n[i2][i3] > 0) {
            return true;
        }
        Door doorN = GameLevelData.o().n(i2, i3);
        return (doorN == null || doorN.G()) ? false : true;
    }

    public final boolean z(Coords coords, Coords coords2) {
        b bVar = this;
        int i2 = coords.f3287x;
        int i3 = coords.f3288y;
        int i4 = coords2.f3287x;
        int i5 = coords2.f3288y;
        Vector2 qVar = f2410g0;
        qVar.f91a = i2;
        qVar.f92b = i3;
        Vector2 qVar2 = f2411h0;
        qVar2.f91a = i4;
        qVar2.f92b = i5;
        char c2 = 0;
        int iMax = Math.max(Math.min(i2, i4) / 32, 0);
        int iMin = Math.min(Math.max(i2, i4) / 32, bVar.f2417b);
        int iMax2 = Math.max(Math.min(i3, i5) / 32, 0);
        int iMin2 = Math.min(Math.max(i3, i5) / 32, bVar.f2418c);
        while (iMax <= iMin) {
            int i6 = iMax2;
            while (i6 <= iMin2) {
                if (bVar.C(iMax, i6) && ((iMax != i2 || i6 != i3) && (iMax != i4 || i6 != i5))) {
                    int i7 = iMax * 32;
                    int i8 = i6 * 32;
                    float f2 = i7;
                    float[] fArr = f2409f0;
                    fArr[c2] = f2;
                    float f3 = i8;
                    fArr[1] = f3;
                    float f4 = i7 + 32;
                    fArr[2] = f4;
                    fArr[3] = f3;
                    fArr[4] = f4;
                    float f5 = i8 + 32;
                    fArr[5] = f5;
                    fArr[6] = f2;
                    fArr[7] = f5;
                    m mVar = f2408e0;
                    mVar.e(fArr);
                    if (i.c(qVar, qVar2, mVar)) {
                        return false;
                    }
                }
                i6++;
                bVar = this;
                c2 = 0;
            }
            iMax++;
            bVar = this;
            c2 = 0;
        }
        return true;
    }
}
