package net.fdgames.GameLevel;

import com.badlogic.gdx.utils.l;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import m0.b;
import n0.z;
import net.fdgames.GameEntities.AI.Pathfinding.AStarPathFinder;
import net.fdgames.GameEntities.Character;
import net.fdgames.GameEntities.Final.Door;
import net.fdgames.GameEntities.Final.MonsterSpawn;
import net.fdgames.GameEntities.Final.NPC;
import net.fdgames.GameEntities.Final.Player;
import net.fdgames.GameEntities.Final.Projectile;
import net.fdgames.GameEntities.Helpers.DamageData;
import net.fdgames.GameEntities.MapActor;
import net.fdgames.GameEntities.MapObject;
import net.fdgames.GameEntities.MapSprite;
import net.fdgames.GameWorld.GameData;
import net.fdgames.GameWorld.MessageRouter;
import net.fdgames.Helpers.GameConsole;
import net.fdgames.Rules.WeaponStats;
import net.fdgames.TiledMap.Objects.Coords;
import net.fdgames.TiledMap.Objects.Transition;
import net.fdgames.ek.ExiledKingdoms;
import net.fdgames.ek.GPGSUpdate;

/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public class GameLevel {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static AStarPathFinder f3094a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static boolean f3095b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static ArrayList<MapActor> f3096c = new ArrayList<>();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static ArrayList<MapSprite> f3097d = new ArrayList<>();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static double f3098e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static int f3099f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static boolean f3100g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static int f3101h;
    public boolean checkedDuplicatedIDs;
    public float initializationTime;

    public GameLevel(Transition transition) {
        this.initializationTime = 0.0f;
        l.d("GameLevel.InitializeLevel()");
        GameData.v().NewArea = null;
        f3094a = new AStarPathFinder();
        GameLevelData.B();
        Coords coordsT = b.P().t(transition);
        GameData.v().player.f3092x = coordsT.f3287x;
        GameData.v().player.f3093y = coordsT.f3288y;
        GameData.v().player.sheet.d0();
        if (GameData.v().party.j()) {
            GameData.v().party.f().sheet.d0();
        }
        GameConsole.e().initialized = Boolean.TRUE;
        this.checkedDuplicatedIDs = false;
        this.initializationTime = GameData.v().u();
        l.d("GameLevel.InitializeLevel() - done");
    }

    public static void a(NPC npc) {
        GameLevelData.b(npc);
        npc.Q1();
    }

    public static float b() {
        return GameData.v().u();
    }

    public static Projectile c(int i2, int i3, int[] iArr, WeaponStats weaponStats, DamageData damageData, Projectile.ProjectileType projectileType) {
        MapActor mapActorG = g(i3);
        if (mapActorG == null) {
            return null;
        }
        Projectile projectile = new Projectile(i2, mapActorG.f3092x, mapActorG.f3093y, iArr, weaponStats, damageData, projectileType);
        GameLevelData.h(projectile);
        return projectile;
    }

    public static Boolean d(String str) {
        int size = GameLevelData.t().size();
        for (int i2 = 0; i2 < size; i2++) {
            if (GameLevelData.t().get(i2).tag == null) {
                System.out.print(GameLevelData.t().get(i2).spawn_id);
            }
            if (GameLevelData.t().get(i2).tag != null && GameLevelData.t().get(i2).tag.equals(str) && !GameLevelData.t().get(i2).i0() && !GameLevelData.t().get(i2).destroy) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    public static ArrayList<MapActor> e() {
        return f3096c;
    }

    public static Character f(int i2) {
        if (i2 == 1) {
            return GameData.v().player;
        }
        for (int i3 = 0; i3 < GameLevelData.t().size(); i3++) {
            if (GameLevelData.t().get(i3).q() == i2) {
                return GameLevelData.t().get(i3);
            }
        }
        return null;
    }

    public static MapActor g(int i2) {
        if (i2 == 0) {
            return null;
        }
        if (i2 == 1) {
            return GameData.v().player;
        }
        for (int i3 = 0; i3 < GameLevelData.t().size(); i3++) {
            if (GameLevelData.t().get(i3).q() == i2) {
                return GameLevelData.t().get(i3);
            }
        }
        return null;
    }

    public static MapObject h(int i2) {
        if (i2 == 1) {
            return GameData.v().player;
        }
        MapObject monsterSpawnI = i(i2);
        Door door = null;
        int i3 = 0;
        if (monsterSpawnI == null) {
            int i4 = 0;
            while (true) {
                if (i4 >= GameLevelData.o().spawns.size()) {
                    monsterSpawnI = null;
                    break;
                }
                if (GameLevelData.o().spawns.get(i4).q() == i2) {
                    monsterSpawnI = GameLevelData.o().spawns.get(i4);
                    break;
                }
                i4++;
            }
        }
        if (monsterSpawnI == null) {
            while (true) {
                if (i3 >= GameLevelData.o().doors.size()) {
                    break;
                }
                if (GameLevelData.o().doors.get(i3).q() == i2) {
                    door = GameLevelData.o().doors.get(i3);
                    break;
                }
                i3++;
            }
            monsterSpawnI = door;
        }
        return monsterSpawnI == null ? GameLevelData.l(i2) : monsterSpawnI;
    }

    public static MapSprite i(int i2) {
        ArrayList<MapSprite> arrayList = f3097d;
        if (arrayList == null || arrayList.size() == 0) {
            f3097d = GameLevelData.x();
        }
        for (int i3 = 0; i3 < f3097d.size(); i3++) {
            if (f3097d.get(i3).q() == i2) {
                return f3097d.get(i3);
            }
        }
        return null;
    }

    public static NPC j(String str) {
        if (str != null && !str.equals("")) {
            for (int i2 = 0; i2 < GameLevelData.t().size(); i2++) {
                if (GameLevelData.t().get(i2) != null && GameLevelData.t().get(i2).tag != null && GameLevelData.t().get(i2).tag.equals(str)) {
                    return GameLevelData.t().get(i2);
                }
            }
        }
        return null;
    }

    public static Player k() {
        return GameData.v().player;
    }

    public static boolean l() {
        return f3095b;
    }

    public static boolean m() {
        return GameData.v().realTime > f3098e + 2.0d;
    }

    public static void n(boolean z2) {
        f3095b = z2;
        f3098e = GameData.v().realTime;
        if (f3095b) {
            return;
        }
        l0.b.i();
        if (ExiledKingdoms.f3378h) {
            GPGSUpdate.c(false);
        }
    }

    public final void o(float f2) {
        if (z.z().booleanValue() || z.y().booleanValue()) {
            n(true);
        }
        if (!f3095b) {
            ArrayList<MapActor> arrayList = f3096c;
            arrayList.clear();
            f3097d.clear();
            for (int i2 = 0; i2 < GameLevelData.o().npcs.size(); i2++) {
                arrayList.add(GameLevelData.o().npcs.get(i2));
            }
            arrayList.add(GameData.v().player);
            f3097d = GameLevelData.x();
            GameConsole.f(f2);
            GameData.v().Z(f2);
            MessageRouter.e();
            int size = f3097d.size();
            for (int i3 = 0; i3 < size; i3++) {
                f3097d.get(i3).visibleToPlayer = Boolean.FALSE;
                f3097d.get(i3).M(f2);
            }
            if (!this.checkedDuplicatedIDs && GameData.v().u() > this.initializationTime + 1.0f) {
                this.checkedDuplicatedIDs = true;
                try {
                    for (NPC npc : GameLevelData.t()) {
                        if (!npc.k0()) {
                            for (MapSprite mapSprite : GameLevelData.x()) {
                                if (npc.q() == mapSprite.q() && !npc.equals(mapSprite)) {
                                    npc.w();
                                    npc.B1();
                                }
                            }
                        }
                    }
                } catch (ConcurrentModificationException unused) {
                }
            }
        }
        GameData.v().realTime += (double) f2;
    }
}
