package l0;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.l;
import com.google.android.gms.games.quest.Quests;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import n0.c0;
import n0.d0;
import n0.g1;
import n0.p1;
import n0.q1;
import n0.z;
import n0.z0;
import net.fdgames.GameEntities.AI.Pathfinding.AStarPathFinder;
import net.fdgames.GameEntities.CharacterSheet.CharacterEffects;
import net.fdgames.GameEntities.CharacterSheet.CharacterSheet;
import net.fdgames.GameEntities.Final.Door;
import net.fdgames.GameEntities.Final.MapContainer;
import net.fdgames.GameEntities.Final.MapEffectEntity;
import net.fdgames.GameEntities.Final.MapItem;
import net.fdgames.GameEntities.Final.MonsterSpawn;
import net.fdgames.GameEntities.Final.NPC;
import net.fdgames.GameEntities.Final.PlantSpawn;
import net.fdgames.GameEntities.Final.Player;
import net.fdgames.GameEntities.Final.SecretDoor;
import net.fdgames.GameEntities.Final.StaticNPC;
import net.fdgames.GameEntities.Final.Trap;
import net.fdgames.GameEntities.Helpers.Shop;
import net.fdgames.GameEntities.MapActor;
import net.fdgames.GameLevel.GameLevel;
import net.fdgames.GameLevel.GameLevelData;
import net.fdgames.GameLogic.ConditionsSet;
import net.fdgames.GameWorld.GameData;
import net.fdgames.GameWorld.GameWorld;
import net.fdgames.GameWorld.MessageRouter;
import net.fdgames.GameWorld.SpawnTableEntry;
import net.fdgames.GameWorld.WorldFactions;
import net.fdgames.Helpers.FDUtils;
import net.fdgames.Helpers.GameConsole;
import net.fdgames.Helpers.GameString;
import net.fdgames.Helpers.Serializer;
import net.fdgames.Rules.Plant;
import net.fdgames.Rules.Plants;
import net.fdgames.Rules.Rules;
import net.fdgames.Rules.SkillActions;
import net.fdgames.Rules.Spawn;
import net.fdgames.TiledMap.Objects.Coords;
import net.fdgames.TiledMap.Objects.Transition;
import net.fdgames.TiledMap.Objects.Trigger;
import net.fdgames.assets.AnimationLoader;
import net.fdgames.assets.AnimationSet;
import net.fdgames.assets.Assets;
import net.fdgames.assets.GameAssets;
import net.fdgames.ek.ExiledKingdoms;
import net.fdgames.ek.GPGSUpdate;
import net.fdgames.ek.IPlatformResolver;
import net.fdgames.ek.Settings;
import o0.x;
import u.f;

/* JADX INFO: compiled from: GameScreen.java */
/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public final class b implements n, j {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static GameLevel f2308i = null;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static boolean f2309j = false;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static boolean f2310k = false;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static boolean f2311l = false;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static boolean f2312m = true;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static boolean f2313n = true;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static float f2314o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static float f2315p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static com.badlogic.gdx.Game f2316q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static boolean f2317r;
    public static boolean s;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public float f2318a = 0.0f;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public float f2319b = 0.0f;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public float f2320c = 0.0f;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public float f2321d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Vector2 f2322e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Vector2 f2323f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public Vector2 f2324g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public float f2325h;

    public b(com.badlogic.gdx.Game eVar, Transition transition) {
        this.f2325h = 0.5f;
        f2316q = eVar;
        l.d("GameScreen constructor called t.area=" + transition.area_id);
        i iVar = new i();
        if (ExiledKingdoms.f3378h) {
            iVar.a(new com.badlogic.gdx.input.GestureDetector(z.v().t()));
        }
        iVar.a(z.v().a());
        iVar.a(this);
        Gdx.input.setInputProcessor(iVar);
        Gdx.input.setCatchBackKey(true);
        d(transition);
        if (ExiledKingdoms.f3378h) {
            this.f2325h = 0.1f;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:95:0x0312  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static void d(Transition transition) {
        Assets assets = Assets.f3309a;
        assets.x();
        f2309j = true;
        String str = transition.area_id;
        String str2 = GameData.v().CurrentLevel;
        l.d("GameScreen.InitializeScreen() > transitioning to " + transition.area_id + "-" + transition.entry_id);
        if (str2 != null && !str2.equals("") && !Serializer.f3228e) {
            l.d("GameScreen.InitializeScreen() >  saving status of level " + GameData.v().CurrentLevel);
            GameLevelData.o().getClass();
            GameLevelData.F();
            Serializer.B();
            AStarPathFinder aStarPathFinder = GameLevel.f3094a;
            GameData.v().player.areasVisited.c(GameData.v().CurrentLevel);
            GameLevelData.o().getClass();
            GameLevelData.K();
        }
        assets.u();
        com.badlogic.gdx.utils.Array<AnimationSet> aVar = AnimationLoader.f3300a;
        if (aVar != null) {
            a.b<AnimationSet> it = aVar.iterator();
            while (it.hasNext()) {
                Assets.y(it.next().name);
                it.remove();
            }
            AnimationLoader.f3300a.clear();
            AnimationLoader.f3300a = null;
        }
        GameAssets.g();
        System.gc();
        GameLevelData.o().H();
        MessageRouter.c();
        GameData.v().CurrentLevel = str;
        m0.b.P().w(str);
        m0.b bVarP = m0.b.P();
        bVarP.getClass();
        ArrayList<String> arrayList = new ArrayList();
        new ArrayList();
        Iterator<f> it2 = bVarP.L.b().iterator();
        while (true) {
            a.b bVar = (a.b) it2;
            if (!bVar.hasNext()) {
                break;
            }
            f fVar = (f) bVar.next();
            if (fVar.a().a("type") && fVar.a().b("type").toString().equals("spawn") && fVar.a().a("spawn")) {
                if (!arrayList.contains(fVar.a().b("spawn").toString())) {
                    arrayList.add(fVar.a().b("spawn").toString());
                }
                if (fVar.a().a("spawn_u") && !arrayList.contains(fVar.a().b("spawn_u").toString())) {
                    arrayList.add(fVar.a().b("spawn_u").toString());
                }
                if (fVar.a().a("spawn_r") && !arrayList.contains(fVar.a().b("spawn_r").toString())) {
                    arrayList.add(fVar.a().b("spawn_r").toString());
                }
            }
            if (fVar.a().a("type") && fVar.a().b("type").toString().equals("spawn")) {
                fVar.a().a("spawntable");
            }
        }
        for (String str3 : arrayList) {
            AnimationLoader.a(str3);
            Spawn spawnI = Rules.i(str3);
            if (spawnI != null && spawnI.onAggroSound.trim() != "") {
                GameAssets.n(spawnI.onAggroSound);
            }
        }
        GameAssets.l();
        f2308i = new GameLevel(transition);
        l.d("GameScreen.InitializeScreen() > Map loaded and level initialized, checking respawn or reload of map " + GameData.v().currentMapName);
        boolean z2 = GameData.f3183b;
        GameData.GameStatus gameStatus = GameData.GameStatus.RUNNING;
        if (z2) {
            l.d("GameScreen.InitializeScreen() > initializing area, new game");
            n();
            GameData.v().gameVariables.e(5, "want_letter_back");
            f2310k = false;
            GameData gameDataV = GameData.v();
            gameDataV.getClass();
            ExiledKingdoms.f3382l = gameStatus;
            gameDataV.Y();
            Serializer.z(GameData.v().slot, 1);
        } else if (!Serializer.f3228e || GameLevelData.f3104c) {
            Player player = GameData.v().player;
            player.getClass();
            if (str.contains("FT_arena")) {
                l.d("GameScreen.InitializeScreen() > Player does not remember area " + str + ", respawning");
                n();
                Serializer.t(GameData.v().CurrentLevel);
                GameConsole.a(m0.b.P().f2422g);
            } else {
                int iB = GameData.v().gameVariables.b("like_father");
                if (!str.toLowerCase().equals("h10") || iB <= 40 || iB >= 100) {
                    player.areasVisited.b();
                    if (player.areasVisited.e(str)) {
                        int i2 = GameData.v().slot;
                        if (Gdx.files.local(Serializer.e(i2) + str + ".sav").exists()) {
                            l.d("GameScreen.InitializeScreen() > Player remembers area ".concat(str));
                            Serializer.u(str);
                            MessageRouter.c();
                            GameLevelData gameLevelDataO = GameLevelData.o();
                            for (NPC npc : gameLevelDataO.npcs) {
                                for (MonsterSpawn monsterSpawn : gameLevelDataO.spawns) {
                                    if (npc.L1() == monsterSpawn.q() && !monsterSpawn.I()) {
                                        npc.destroy = true;
                                    }
                                }
                            }
                            for (StaticNPC staticNPC : gameLevelDataO.staticNPCs) {
                                if (!staticNPC.spawnConditions.a().booleanValue()) {
                                    staticNPC.destroy = true;
                                }
                            }
                            GameLevelData.o().getClass();
                            GameLevelData.I();
                            Iterator<MapItem> it3 = GameLevelData.o().mapItems.iterator();
                            while (it3.hasNext()) {
                                it3.next().T();
                            }
                        }
                        Serializer.t(GameData.v().CurrentLevel);
                        GameConsole.a(m0.b.P().f2422g);
                    }
                }
            }
        } else {
            l.d("GameScreen.InitializeScreen() > no loading nor respawning, because we are loading a saved game");
        }
        z.v().Y();
        z.v().W();
        z zVarV = z.v();
        zVarV.getClass();
        l.f1832b = 0.0f;
        zVarV.J(0);
        zVarV.U();
        zVarV.W();
        zVarV.f2951k = ExiledKingdoms.g();
        z.v().r();
        z.v().J(0);
        z.v().H();
        k0.a.l().m();
        GameLevelData gameLevelDataO2 = GameLevelData.o();
        ArrayList<Trap> arrayList2 = gameLevelDataO2.traps;
        if (arrayList2 != null) {
            for (Trap trap : arrayList2) {
                trap.Z();
                if (trap.R() == 1) {
                    trap.destroy = true;
                }
            }
        }
        ArrayList<MapEffectEntity> arrayList3 = gameLevelDataO2.mapEffects;
        if (arrayList3 != null) {
            Iterator<MapEffectEntity> it4 = arrayList3.iterator();
            while (it4.hasNext()) {
                it4.next().V();
            }
        }
        GameData.v().player.activables = null;
        GameData.v().player.numActivables = 0;
        WorldFactions.j();
        f2310k = false;
        GameData gameDataV2 = GameData.v();
        gameDataV2.getClass();
        ExiledKingdoms.f3382l = gameStatus;
        gameDataV2.Y();
        p1.b().setVisible(false);
        GameLevel.n(false);
        l.d("GameScreen.InitializeScreen() >  done");
        h();
        GameData.v().getClass();
        if (GameData.v().party != null && GameData.v().party.j()) {
            NPC npcF = GameData.v().party.f();
            GameData.v().party.s();
            CharacterSheet characterSheet = npcF.sheet;
            characterSheet.getClass();
            characterSheet.effects = new CharacterEffects();
            if (Player.f3027e && npcF.sheet.o() == 0) {
                npcF.q0(MapActor.ActorState.DEAD);
                npcF.o("RECOVER", npcF.q(), null, 1.0f, null);
            } else {
                npcF.q0(MapActor.ActorState.IDLE);
            }
            npcF.lastTargetHit_id = 0;
            npcF.Q1();
            npcF.t0();
            SkillActions.d(GameData.v().party.f());
            GameData.v().party.r();
            npcF.wait = false;
        }
        GameData.v().player.destroy = false;
        CharacterSheet characterSheet2 = GameData.v().player.sheet;
        characterSheet2.getClass();
        characterSheet2.effects = new CharacterEffects();
        SkillActions.d(GameData.v().player);
        GameData.v().player.p0(new Coords(-1, -1));
        GameLevelData.o().C();
        for (MapEffectEntity mapEffectEntity : GameLevelData.o().mapEffects) {
            mapEffectEntity.Q();
            mapEffectEntity.R();
        }
        GameData.v().gameVariables.e(1, "EXP_" + str);
        if (str.equals("FT") && GameData.v().lostGold > GameData.v().player.h() && GameData.v().lostGold > 5000) {
            GameData.v().player.B1(GameData.v().lostGold);
            GameData.v().lostGold = 0;
        }
        System.gc();
        l.d("GameScreen.InitializeScreen() >  done all subtasks");
        f2309j = false;
        IPlatformResolver iPlatformResolverF = ExiledKingdoms.f();
        "area ".concat(str);
        iPlatformResolverF.getClass();
        if (str2 != null && !str2.equals("") && !Serializer.f3228e && !GameData.v().G()) {
            Serializer.z(GameData.v().slot, 7);
        }
        Serializer.v("******   " + GameData.v().player.getName() + ", LEVEL " + GameData.v().player.sheet.stats.f() + " entering area " + GameData.v().currentMapName);
        if (m0.b.P().f2415a.equals("")) {
            return;
        }
        Assets.f3309a.t(m0.b.P().f2415a);
    }

    public static void e() {
        com.badlogic.gdx.Game eVar = f2316q;
        if (eVar != null) {
            ExiledKingdoms.f().getClass();
        }
    }

    public static void f(int i2) {
        com.badlogic.gdx.Game eVar = f2316q;
        if (eVar != null) {
            ExiledKingdoms.f().getClass();
        }
    }

    public static void g(String str) {
        com.badlogic.gdx.Game eVar = f2316q;
        if (eVar != null) {
            ExiledKingdoms.f().getClass();
        }
    }

    public static void h() {
        if (GameData.v().player.sheet.L() <= 0 || GameData.v().player.sheet.stats.f() >= 9) {
            return;
        }
        z.v().L(GameString.b("AVAILABLE_SKILL_POINTS", false) + "!");
    }

    public static void i() {
        f2314o = (float) (GameData.v().realTime + 0.25d);
    }

    public void j() {
        f2312m = false;
        this.f2318a = 0.0f;
        if (f2309j) {
            return;
        }
        int iW = z.v().w();
        if (iW != 0) {
            if (iW == 1) {
                z.v().J(0);
                GameLevel.n(false);
                z.v().Y();
                return;
            } else {
                if (iW != 2) {
                    return;
                }
                z.v().J(0);
                GameLevel.n(false);
                z.v().Y();
                return;
            }
        }
        if (z.y().booleanValue()) {
            if (z.v().f2939b0.isVisible()) {
                z.v().f2939b0.c();
                return;
            }
            if (z.v().Y.isVisible()) {
                z.v().Y.a();
                return;
            }
            if (z.v().X.isVisible()) {
                z.v().X.m();
                return;
            }
            if (o0.l.e().isVisible()) {
                o0.l.e().setVisible(false);
                return;
            }
            if (o0.n.b().isVisible()) {
                o0.n.b().setVisible(false);
                z.v().Y();
                return;
            } else if (x.b().isVisible()) {
                x.b().setVisible(false);
                return;
            } else if (z.v().W.isVisible()) {
                z.v().W.c();
                return;
            } else {
                z.v().V.E();
                GameLevel.n(false);
                return;
            }
        }
        if (g1.f().isVisible()) {
            g1.f().c();
            GameLevel.n(false);
            z.v().Y();
            z.v().W();
            return;
        }
        if (z.v().S.isVisible()) {
            z.v().S.c();
            return;
        }
        if (n0.d.f().isVisible()) {
            n0.d.f().d();
            GameLevel.n(false);
            z.v().Y();
            z.v().W();
            return;
        }
        if (n0.d.f().isVisible()) {
            n0.d.f().d();
            GameLevel.n(false);
            z.v().Y();
            z.v().W();
            return;
        }
        if (z.v().C()) {
            z.v().e();
            return;
        }
        if (d0.b().isVisible()) {
            d0.b().setVisible(false);
            GameLevel.n(false);
        } else {
            if (GameLevel.l()) {
                return;
            }
            GameLevel.n(true);
            d0.b().c();
        }
    }

    public void k(float f2, float f3) {
        if (this.f2324g == null) {
            this.f2324g = new Vector2 (0.0f, 0.0f);
        }
        if (this.f2323f == null) {
            this.f2323f = new Vector2 (0.0f, 0.0f);
        }
        if (this.f2322e == null) {
            this.f2322e = new Vector2 (0.0f, 0.0f);
        }
        this.f2323f.b(m0.b.B((int) k0.a.l().f2278e.position.f1729a, (int) k0.a.l().f2278e.position.f1730b));
        this.f2322e.b(m0.b.B((int) (((f2 * k0.a.f2270q) / Gdx.graphics.getWidth()) - (k0.a.f2270q / 2)), (int) ((((f3 * k0.a.f2271r) / Gdx.graphics.getHeight()) - (k0.a.f2271r / 2)) + 12.0f)));
        Vector2 qVar = this.f2324g;
        Vector2 qVar2 = this.f2323f;
        float f4 = qVar2.f91a;
        Vector2 qVar3 = this.f2322e;
        qVar.f91a = f4 + qVar3.f92b;
        qVar.f92b = qVar2.f92b + qVar3.f91a;
    }

    public static void l() {
        if (Settings.b()) {
            m0.b bVarP = m0.b.P();
            AStarPathFinder aStarPathFinder = GameLevel.f3094a;
            if (bVarP.a(GameData.v().player.B(), GameData.v().player.r()) <= 0) {
                z.v().getClass();
                if (GameData.v().player.numActivables > 0 && GameData.v().u() > f2315p) {
                    z.v().E(0);
                    return;
                }
            }
        }
        AStarPathFinder aStarPathFinder2 = GameLevel.f3094a;
        GameData.v().player.E0(-1);
        f2315p = GameData.v().u() + 0.8f;
    }

    public void m() {
        f2313n = false;
        this.f2320c = (float) GameData.v().realTime;
    }

    /* JADX WARN: Removed duplicated region for block: B:161:0x04af A[PHI: r0
      0x04af: PHI (r0v118 net.fdgames.GameLogic.ConditionsSet) = (r0v117 net.fdgames.GameLogic.ConditionsSet), (r0v124 net.fdgames.GameLogic.ConditionsSet) binds: [B:157:0x049a, B:159:0x04a9] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static void n() {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        Plant next;
        String str7;
        String str8;
        String strQ;
        String str9;
        String strQ2;
        String strQ3;
        String str10;
        String str11;
        String strQ4;
        String str12;
        String strQ5;
        String strQ6;
        String strQ7;
        Shop shop;
        ConditionsSet conditionsSet;
        boolean z2;
        Iterator<f> it;
        String[] strArr;
        int i2;
        String str13 = "type";
        String str14 = "ERROR 6 ;";
        l.d("respawning map");
        GameLevelData.o().H();
        MessageRouter.c();
        GameLevelData.B();
        m0.b bVarP = m0.b.P();
        bVarP.getClass();
        GameLevelData.o().doors = new ArrayList<>();
        for (int i3 = 0; i3 < bVarP.f2417b; i3++) {
            for (int i4 = 0; i4 < bVarP.f2418c; i4++) {
                if (bVarP.I.j(i3, i4) != null && bVarP.I.j(i3, i4).a().c().a("door")) {
                    Door door = new Door(i3, i4, bVarP.I.j(i3, i4).a().c().b("door").toString());
                    if (bVarP.I.j(i3, i4).a().c().a("steel")) {
                        door.steel = true;
                    }
                    GameLevelData.o().doors.add(door);
                }
            }
        }
        m0.b bVarP2 = m0.b.P();
        bVarP2.getClass();
        boolean z3 = GameData.v().gameVariables.b("shards_fate") > 21;
        Iterator<f> it2 = bVarP2.L.b().iterator();
        while (true) {
            a.b bVar = (a.b) it2;
            str = "name";
            str2 = ",";
            str3 = "quest_location";
            str4 = "unique_tag";
            str5 = "@";
            if (!bVar.hasNext()) {
                break;
            }
            f fVar = (f) bVar.next();
            if (fVar.a().a("type")) {
                it = it2;
                if (fVar.a().b("type").toString().equals("container")) {
                    try {
                        String string = fVar.a().a("conditions") ? fVar.a().b("conditions").toString() : "";
                        String string2 = fVar.a().a("unique_tag") ? fVar.a().b("unique_tag").toString() : "";
                        if (string.equals("") || new ConditionsSet(string).a().booleanValue()) {
                            MapContainer mapContainer = new MapContainer();
                            String string3 = fVar.a().b("name").toString();
                            mapContainer.name = string3;
                            if (string3.contains("@")) {
                                mapContainer.name = GameString.b(mapContainer.name.replace("@", ""), true);
                            }
                            mapContainer.iconName = fVar.a().b("icon").toString();
                            mapContainer.f3092x = (int) Float.parseFloat(fVar.a().b("x").toString());
                            mapContainer.f3093y = (int) Float.parseFloat(fVar.a().b("y").toString());
                            mapContainer.H(string2);
                            if ((!fVar.a().a("items") || fVar.a().b("items").toString().trim() == "") && !fVar.a().a("gold")) {
                                mapContainer.loot_table = fVar.a().b("loot_table").toString().toLowerCase(Locale.ENGLISH);
                                mapContainer.G();
                            } else if (!GameData.v().g(string2)) {
                                if (fVar.a().a("gold")) {
                                    mapContainer.gold = Integer.parseInt(fVar.a().b("gold").toString());
                                }
                                if (fVar.a().a("items") && fVar.a().b("items").toString().trim() != "") {
                                    String[] strArrSplit = fVar.a().b("items").toString().split(",");
                                    int i5 = 0;
                                    while (i5 < strArrSplit.length && i5 < 20) {
                                        int i6 = Integer.parseInt(strArrSplit[i5]);
                                        if (z3 && (i6 == 2600 || i6 == 2601)) {
                                            strArr = strArrSplit;
                                            i2 = 1;
                                        } else {
                                            strArr = strArrSplit;
                                            i2 = 1;
                                            mapContainer.items.a(i6, 1);
                                        }
                                        i5 += i2;
                                        strArrSplit = strArr;
                                    }
                                }
                            }
                            if (fVar.a().a("quest_location")) {
                                mapContainer.quest_location = fVar.a().b("quest_location").toString();
                            }
                            GameLevelData.c(mapContainer);
                            mapContainer.F();
                        }
                    } catch (Exception e2) {
                        r0.a.a("ERROR 6 ;" + GameData.v().CurrentLevel + "; location:" + (((int) Float.parseFloat(fVar.a().b("x").toString())) / 32) + "," + (((int) Float.parseFloat(fVar.a().b("y").toString())) / 32) + " ;container initialization error; " + e2.getMessage());
                    }
                }
            } else {
                it = it2;
            }
            it2 = it;
        }
        Iterator<f> it3 = m0.b.P().L.b().iterator();
        while (true) {
            a.b bVar2 = (a.b) it3;
            if (!bVar2.hasNext()) {
                break;
            }
            f fVar2 = (f) bVar2.next();
            String strQ8 = fVar2.a().a("faction") ? a.a.q(fVar2, "faction") : "";
            String strQ9 = fVar2.a().a("cycle") ? a.a.q(fVar2, "cycle") : "";
            Iterator<f> it4 = it3;
            Boolean bool = fVar2.a().a("alwaysReward") ? Boolean.TRUE : Boolean.FALSE;
            String str15 = str14;
            String strQ10 = fVar2.a().a("facing") ? a.a.q(fVar2, "facing") : "";
            String str16 = strQ9;
            String strQ11 = fVar2.a().a(str4) ? a.a.q(fVar2, str4) : "";
            String str17 = str4;
            Boolean bool2 = bool;
            String str18 = str2;
            String str19 = str3;
            String str20 = strQ10;
            String str21 = strQ8;
            if (fVar2.a().a(str13)) {
                str7 = str13;
                if (fVar2.a().b(str13).toString().equals("staticNPC")) {
                    String strQ12 = fVar2.a().a("conditions") ? a.a.q(fVar2, "conditions") : "";
                    String str22 = (fVar2.a().a("facing") && a.a.q(fVar2, "facing").toLowerCase(Locale.ENGLISH).contains("r")) ? "r" : str20;
                    String str23 = (fVar2.a().a("gender") && a.a.q(fVar2, "gender").toLowerCase(Locale.ENGLISH).contains("f")) ? "f" : "";
                    String strQ13 = fVar2.a().a("conversation") ? a.a.q(fVar2, "conversation") : "";
                    int i7 = fVar2.a().a("portrait") ? Integer.parseInt(fVar2.a().b("portrait").toString()) : 0;
                    String strQ14 = fVar2.a().a("tag") ? a.a.q(fVar2, "tag") : "";
                    if (!fVar2.a().a("shop_items") || fVar2.a().b("shop_items").toString().trim() == "") {
                        shop = null;
                    } else {
                        Shop shop2 = new Shop();
                        Iterator<Integer> it5 = Shop.t(fVar2.a().b("shop_items").toString()).iterator();
                        while (it5.hasNext()) {
                            shop2.i(it5.next().intValue());
                        }
                        if (fVar2.a().a("shop_modifier")) {
                            shop2.modifier = Float.parseFloat(fVar2.a().b("shop_modifier").toString());
                        } else {
                            shop2.modifier = 1.0f;
                        }
                        shop = shop2;
                    }
                    ConditionsSet conditionsSet2 = new ConditionsSet();
                    if (!strQ12.equals("")) {
                        conditionsSet2 = new ConditionsSet(strQ12);
                        if (conditionsSet2.a().booleanValue()) {
                            conditionsSet = conditionsSet2;
                            z2 = true;
                        } else {
                            conditionsSet = conditionsSet2;
                            z2 = false;
                        }
                        if (!strQ11.equals("") && GameData.v().H(strQ11)) {
                            z2 = false;
                        }
                        if (z2) {
                            StaticNPC staticNPC = new StaticNPC((int) Float.parseFloat(fVar2.a().b("x").toString()), (int) Float.parseFloat(fVar2.a().b("y").toString()), str22, a.a.q(fVar2, "sprite"), strQ13, i7, str23, a.a.q(fVar2, str), shop, strQ14, conditionsSet);
                            if (staticNPC.getName().contains(str5)) {
                                staticNPC.N(GameString.b(staticNPC.getName().replace(str5, ""), true));
                            }
                            if (!str21.equals("")) {
                                staticNPC.y(WorldFactions.i(str21));
                            }
                            GameLevelData.o().staticNPCs.add(staticNPC);
                        }
                        str8 = str5;
                    }
                    str = str10;
                    it3 = it4;
                    str14 = str15;
                    str4 = str17;
                    str2 = str18;
                    str3 = str19;
                    str5 = str8;
                    str13 = str7;
                }
                str10 = str;
                str = str10;
                it3 = it4;
                str14 = str15;
                str4 = str17;
                str2 = str18;
                str3 = str19;
                str5 = str8;
                str13 = str7;
            } else {
                str7 = str13;
            }
            String str24 = str7;
            if (fVar2.a().a(str24)) {
                str8 = str5;
                if (fVar2.a().b(str24).toString().equals("spawn")) {
                    str7 = str24;
                    strQ = "1";
                    String str25 = str;
                    if (!fVar2.a().a("spawntable") || fVar2.a().b("spawntable").toString().equals("")) {
                        str9 = str19;
                        if (strQ11.equals("") || !GameData.v().H(strQ11)) {
                            if (fVar2.a().a(str9) && fVar2.a().a("quest_spawn")) {
                                strQ3 = a.a.q(fVar2, str9);
                                strQ2 = a.a.q(fVar2, "quest_spawn");
                            } else {
                                strQ2 = "";
                                strQ3 = strQ2;
                            }
                            str19 = str9;
                            String str26 = strQ11;
                            String strQ15 = fVar2.a().a("spawn_u") ? a.a.q(fVar2, "spawn_u") : "";
                            String strQ16 = fVar2.a().a("spawn_r") ? a.a.q(fVar2, "spawn_r") : "";
                            String strQ17 = fVar2.a().a("conversation") ? a.a.q(fVar2, "conversation") : "";
                            String strQ18 = fVar2.a().a("conditions") ? a.a.q(fVar2, "conditions") : "";
                            boolean zBooleanValue = fVar2.a().a("hostile") ? new ConditionsSet(a.a.q(fVar2, "hostile")).a().booleanValue() : false;
                            str10 = str25;
                            String strQ19 = fVar2.a().a(str10) ? a.a.q(fVar2, str10) : "";
                            int i8 = fVar2.a().a("wander") ? Integer.parseInt(fVar2.a().b("wander").toString()) : -1;
                            int i9 = fVar2.a().a("respawn") ? Integer.parseInt(fVar2.a().b("respawn").toString()) : 0;
                            int i10 = fVar2.a().a("portrait") ? Integer.parseInt(fVar2.a().b("portrait").toString()) : 0;
                            String strQ20 = fVar2.a().a("tag") ? a.a.q(fVar2, "tag") : "";
                            if (!fVar2.a().a("shop_items") || fVar2.a().b("shop_items").toString().trim() == "") {
                                str11 = "";
                                strQ4 = str11;
                            } else {
                                String strQ21 = a.a.q(fVar2, "shop_items");
                                if (!fVar2.a().a("shop_modifier") || fVar2.a().b("shop_modifier").toString().trim() == "") {
                                    strQ4 = "";
                                    str11 = strQ21;
                                } else {
                                    str11 = strQ21;
                                    strQ4 = a.a.q(fVar2, "shop_modifier");
                                }
                            }
                            MonsterSpawn monsterSpawn = new MonsterSpawn(((int) Float.parseFloat(fVar2.a().b("x").toString())) / 32, ((int) Float.parseFloat(fVar2.a().b("y").toString())) / 32, a.a.q(fVar2, "spawn"), strQ15, strQ16, i9, new m0.e(strQ17, i8, strQ19, i10, strQ18, strQ20, str11, strQ4));
                            GameLevelData.o().spawns.add(monsterSpawn);
                            if (bool2.booleanValue()) {
                                monsterSpawn.K();
                            }
                            if (!strQ3.equals("")) {
                                monsterSpawn.questLocation = strQ3.toLowerCase(Locale.ENGLISH);
                                monsterSpawn.questSpawn = Integer.parseInt(strQ2);
                            }
                            String strQ22 = fVar2.a().a("event_location") ? a.a.q(fVar2, "event_location") : "";
                            String strQ23 = fVar2.a().a("event_spawn") ? a.a.q(fVar2, "event_spawn") : "1";
                            if (!strQ22.equals("")) {
                                monsterSpawn.G(strQ22, strQ23);
                            }
                            if (!str21.equals("")) {
                                monsterSpawn.y(WorldFactions.i(str21));
                            }
                            if (zBooleanValue) {
                                monsterSpawn.x(Quests.SELECT_COMPLETED_UNCLAIMED);
                            }
                            if (!str26.equals("")) {
                                monsterSpawn.O(str26);
                            }
                            if (!str20.equals("")) {
                                monsterSpawn.N(str20);
                            }
                            monsterSpawn.M(str16);
                        }
                        str = str10;
                        it3 = it4;
                        str14 = str15;
                        str4 = str17;
                        str2 = str18;
                        str3 = str19;
                        str5 = str8;
                        str13 = str7;
                    } else {
                        boolean z4 = !fVar2.a().a("spawnChance") || FDUtils.b(1, 100) <= Integer.parseInt(fVar2.a().b("spawnChance").toString());
                        if (!strQ11.equals("") && GameData.v().H(strQ11)) {
                            z4 = false;
                        }
                        SpawnTableEntry spawnTableEntryA = GameWorld.f3191e.a(fVar2.a().b("spawntable").toString());
                        if (spawnTableEntryA == null || !z4) {
                            str12 = str18;
                            str9 = str19;
                        } else {
                            MonsterSpawn monsterSpawn2 = new MonsterSpawn(((int) Float.parseFloat(fVar2.a().b("x").toString())) / 32, ((int) Float.parseFloat(fVar2.a().b("y").toString())) / 32, spawnTableEntryA.spawn_id, "", "", fVar2.a().a("respawn") ? Integer.parseInt(fVar2.a().b("respawn").toString()) : 0, spawnTableEntryA.spawnData);
                            if (fVar2.a().a("wander")) {
                                monsterSpawn2.P(Integer.parseInt(fVar2.a().b("wander").toString()));
                            }
                            if (!spawnTableEntryA.faction.equals("")) {
                                monsterSpawn2.y(WorldFactions.i(spawnTableEntryA.faction));
                            }
                            if (!str21.equals("")) {
                                monsterSpawn2.y(WorldFactions.i(str21));
                            }
                            if (!str20.equals("")) {
                                monsterSpawn2.N(str20);
                            }
                            if (fVar2.a().a("tag")) {
                                monsterSpawn2.tag = a.a.q(fVar2, "tag");
                            }
                            if (fVar2.a().a("conditions")) {
                                monsterSpawn2.L(fVar2.a().b("conditions").toString());
                            }
                            str9 = str19;
                            if (fVar2.a().a(str9) && fVar2.a().a("quest_spawn")) {
                                strQ5 = a.a.q(fVar2, str9);
                                strQ6 = a.a.q(fVar2, "quest_spawn");
                            } else {
                                strQ5 = "";
                                strQ6 = strQ5;
                            }
                            if (!strQ5.equals("")) {
                                monsterSpawn2.questLocation = strQ5.toLowerCase(Locale.ENGLISH);
                                monsterSpawn2.questSpawn = Integer.parseInt(strQ6);
                            }
                            if (fVar2.a().a("event_location")) {
                                strQ7 = a.a.q(fVar2, "event_location");
                                if (fVar2.a().a("event_spawn")) {
                                    strQ = a.a.q(fVar2, "event_spawn");
                                }
                            } else {
                                strQ7 = "";
                            }
                            String str27 = strQ;
                            if (!strQ7.equals("")) {
                                monsterSpawn2.G(strQ7, str27);
                            }
                            if (!strQ11.equals("")) {
                                monsterSpawn2.O(strQ11);
                            }
                            if (spawnTableEntryA.conversationRange > 0) {
                                StringBuilder sb = new StringBuilder("StartConversation#");
                                sb.append(spawnTableEntryA.spawnData.f2450f);
                                str12 = str18;
                                sb.append(str12);
                                sb.append(spawnTableEntryA.spawnData.f2445a);
                                Trigger trigger = new Trigger(spawnTableEntryA.conversationConditions, sb.toString());
                                trigger.size = spawnTableEntryA.conversationRange * 32;
                                monsterSpawn2.F(trigger);
                            } else {
                                str12 = str18;
                            }
                            if (bool2.booleanValue()) {
                                monsterSpawn2.K();
                            }
                            GameLevelData.o().spawns.add(monsterSpawn2);
                            for (String str28 : spawnTableEntryA.secondarySpawns) {
                                Coords coordsV = m0.b.V(monsterSpawn2.f3092x, monsterSpawn2.f3093y, spawnTableEntryA.secondaryDistance * 32);
                                MonsterSpawn monsterSpawn3 = new MonsterSpawn(coordsV.f3287x / 32, coordsV.f3288y / 32, str28, "", "", 0, new m0.e("", 0, "", 0, "", spawnTableEntryA.secondaryTag, "", ""));
                                String str29 = spawnTableEntryA.faction;
                                if (str29 != "") {
                                    monsterSpawn3.y(WorldFactions.i(str29));
                                }
                                GameLevelData.o().spawns.add(monsterSpawn3);
                            }
                            for (String str30 : spawnTableEntryA.tertiarySpawns) {
                                Coords coordsV2 = m0.b.V(monsterSpawn2.f3092x, monsterSpawn2.f3093y, spawnTableEntryA.tertiaryDistance * 32);
                                MonsterSpawn monsterSpawn4 = new MonsterSpawn(coordsV2.f3287x / 32, coordsV2.f3288y / 32, str30, "", "", 0, new m0.e("", 0, "", 0, "", spawnTableEntryA.tertiaryTag, "", ""));
                                String str31 = spawnTableEntryA.faction;
                                if (str31 != "") {
                                    monsterSpawn4.y(WorldFactions.i(str31));
                                }
                                GameLevelData.o().spawns.add(monsterSpawn4);
                            }
                        }
                        str18 = str12;
                    }
                    str19 = str9;
                    str10 = str25;
                    str = str10;
                    it3 = it4;
                    str14 = str15;
                    str4 = str17;
                    str2 = str18;
                    str3 = str19;
                    str5 = str8;
                    str13 = str7;
                }
            } else {
                str8 = str5;
            }
            str7 = str24;
            str10 = str;
            str = str10;
            it3 = it4;
            str14 = str15;
            str4 = str17;
            str2 = str18;
            str3 = str19;
            str5 = str8;
            str13 = str7;
        }
        String str32 = str13;
        String str33 = str14;
        Iterator<f> it6 = m0.b.P().L.b().iterator();
        while (true) {
            a.b bVar3 = (a.b) it6;
            if (!bVar3.hasNext()) {
                break;
            }
            f fVar3 = (f) bVar3.next();
            String str34 = str32;
            if (fVar3.a().a(str34) && fVar3.a().b(str34).toString().equals("plant") && fVar3.a().a("id")) {
                String strQ24 = a.a.q(fVar3, "id");
                Iterator<Plant> it7 = Plants.f3246a.iterator();
                while (true) {
                    if (it7.hasNext()) {
                        next = it7.next();
                        if (next.ID.equals(strQ24)) {
                            break;
                        }
                    } else {
                        next = null;
                        break;
                    }
                }
                next.getClass();
                if (FDUtils.b(1, 100) < next.chance) {
                    GameLevelData.g(new PlantSpawn(a.a.q(fVar3, "id"), (int) Float.parseFloat(fVar3.a().b("x").toString()), (int) Float.parseFloat(fVar3.a().b("y").toString())));
                }
            }
            str32 = str34;
        }
        String str35 = str32;
        Iterator<f> it8 = m0.b.P().L.b().iterator();
        while (true) {
            a.b bVar4 = (a.b) it8;
            if (!bVar4.hasNext()) {
                break;
            }
            f fVar4 = (f) bVar4.next();
            if (fVar4.a().a(str35) && fVar4.a().b(str35).toString().equals("trigger")) {
                try {
                    Trigger trigger2 = new Trigger(fVar4.a().a("conditions") ? fVar4.a().b("conditions").toString() : "", fVar4.a().a("actions") ? fVar4.a().b("actions").toString() : "");
                    trigger2.b(((v.b) fVar4).b());
                    GameLevelData.j(trigger2);
                    str6 = str33;
                } catch (Exception unused) {
                    str6 = str33;
                    r0.a.a(str6 + GameData.v().CurrentLevel + "; trigger initialization error");
                }
            } else {
                str6 = str33;
            }
            str33 = str6;
        }
        String str36 = str33;
        Iterator<f> it9 = m0.b.P().L.b().iterator();
        while (true) {
            a.b bVar5 = (a.b) it9;
            if (!bVar5.hasNext()) {
                break;
            }
            f fVar5 = (f) bVar5.next();
            if (fVar5.a().a(str35) && fVar5.a().b(str35).toString().trim().equals("trap")) {
                if (new ConditionsSet(fVar5.a().a("conditions") ? fVar5.a().b("conditions").toString().trim() : "").a().booleanValue()) {
                    try {
                        if (FDUtils.b(1, 100) <= (fVar5.a().a("spawn_chance") ? Integer.parseInt(fVar5.a().b("spawn_chance").toString()) : 100)) {
                            Trap trap = new Trap(fVar5.a());
                            AStarPathFinder aStarPathFinder = GameLevel.f3094a;
                            GameLevelData.i(trap);
                        }
                    } catch (Exception unused2) {
                        r0.a.a(str36 + GameData.v().CurrentLevel + "; trap initialization error");
                    }
                }
            }
        }
        Iterator<f> it10 = m0.b.P().L.b().iterator();
        while (true) {
            a.b bVar6 = (a.b) it10;
            if (!bVar6.hasNext()) {
                m0.b.P().x();
                GameLevelData.o().getClass();
                GameLevelData.I();
                GameData.f3183b = false;
                return;
            }
            f fVar6 = (f) bVar6.next();
            if (fVar6.a().a(str35) && fVar6.a().b(str35).toString().trim().equals("secretdoor")) {
                if (new ConditionsSet(fVar6.a().a("conditions") ? fVar6.a().b("conditions").toString().trim() : "").a().booleanValue()) {
                    SecretDoor secretDoor = new SecretDoor(fVar6.a());
                    AStarPathFinder aStarPathFinder2 = GameLevel.f3094a;
                    GameLevelData.a(secretDoor);
                }
            }
        }
    }

    public static boolean o() {
        return !GameLevel.l() && GameData.v().realTime >= ((double) f2314o);
    }

    @Override // com.badlogic.gdx.Screen
    public final void a() {
    }

    @Override // com.badlogic.gdx.Screen
    public final void b(int i2, int i3) {
    }

    /* JADX WARN: Removed duplicated region for block: B:282:0x0701  */
    /* JADX WARN: Removed duplicated region for block: B:344:0x085b  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0153  */
    @Override // com.badlogic.gdx.Screen
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void c(float f2) {
        float f3;
        int iQ;
        if (f2310k || 1.0f / f2 < 4.0f) {
            return;
        }
        f2308i.o(f2);
        z.v().R();
        k0.a.l().q(f2);
        m0.b bVarP = m0.b.P();
        bVarP.getClass();
        if (!GameLevel.l()) {
            bVarP.f2428m.d(f2);
        }
        if (GameData.v().NewArea != null) {
            Player player = GameData.v().player;
            player.speedY = 0.0f;
            player.speedX = 0.0f;
            f2310k = true;
            d(GameData.v().NewArea);
        }
        if (GameData.v().player != null) {
            if (f2312m && (Gdx.input.isKeyJustPressed(Settings.f3419b.escape.id) || Gdx.input.isKeyJustPressed(4) || (f2313n && ExiledKingdoms.j(Settings.f3418a.cancel)))) {
                j();
                if (ExiledKingdoms.g()) {
                    m();
                }
            }
            if (!Gdx.input.isKeyJustPressed(Settings.f3419b.quicksave.id)) {
                if (ExiledKingdoms.g() && !Gdx.input.isKeyPressed(Settings.f3418a.action.id) && !Gdx.input.isKeyPressed(Settings.f3419b.attack.id)) {
                    GameData.v().player.getClass();
                    if (z.f2926j0) {
                        GameData.v().player.getClass();
                        z.f2926j0 = false;
                    }
                }
                if (!f2313n && GameData.v().realTime > this.f2320c + 0.25f) {
                    f2313n = true;
                }
                if (!f2312m) {
                    float f4 = this.f2318a;
                    if (f4 > this.f2325h) {
                        f2312m = true;
                    } else {
                        this.f2318a = f4 + f2;
                    }
                }
                if (z.z().booleanValue()) {
                    if (Gdx.input.isKeyJustPressed(8)) {
                        z.v().getClass();
                        z.u().p(1);
                    }
                    if (Gdx.input.isKeyJustPressed(9)) {
                        z.v().getClass();
                        z.u().p(2);
                    }
                    if (Gdx.input.isKeyJustPressed(10)) {
                        z.v().getClass();
                        z.u().p(3);
                    }
                    if (Gdx.input.isKeyJustPressed(11)) {
                        z.v().getClass();
                        z.u().p(4);
                    }
                    if (f2313n) {
                        if (ExiledKingdoms.e(Settings.f3418a.up) > 0.03f) {
                            z.v().getClass();
                            z.u().b();
                            m();
                        }
                        if (ExiledKingdoms.e(Settings.f3418a.down) > 0.03f) {
                            z.v().getClass();
                            z.u().a();
                            m();
                        }
                        if (ExiledKingdoms.e(Settings.f3418a.action) > 0.0f) {
                            z.v().getClass();
                            z.u().q();
                            m();
                        }
                    }
                }
                if (!GameLevel.l()) {
                    float fE = ExiledKingdoms.e(Settings.f3418a.right) - ExiledKingdoms.e(Settings.f3418a.left);
                    float fE2 = ExiledKingdoms.e(Settings.f3418a.up) - ExiledKingdoms.e(Settings.f3418a.down);
                    if (ExiledKingdoms.j(Settings.f3418a.nextItem)) {
                        z.v().f();
                    }
                    if (ExiledKingdoms.j(Settings.f3418a.prevItem)) {
                        z.v().j();
                    }
                    if (ExiledKingdoms.j(Settings.f3418a.useItem)) {
                        z.v().a0();
                    }
                    if (o()) {
                        if (ExiledKingdoms.j(Settings.f3418a.skill1)) {
                            z.v().k(0);
                        }
                        if (ExiledKingdoms.j(Settings.f3418a.skill2)) {
                            z.v().k(1);
                        }
                        if (ExiledKingdoms.j(Settings.f3418a.skill3)) {
                            z.v().k(2);
                        }
                        if (ExiledKingdoms.j(Settings.f3418a.skill4)) {
                            z.v().k(3);
                        }
                        if (ExiledKingdoms.j(Settings.f3418a.skill5)) {
                            z.v().k(4);
                        }
                        if (ExiledKingdoms.j(Settings.f3418a.skill6)) {
                            z.v().k(5);
                        }
                        if (ExiledKingdoms.j(Settings.f3418a.skill7)) {
                            z.v().k(6);
                        }
                        if (ExiledKingdoms.j(Settings.f3418a.skill8)) {
                            z.v().k(7);
                        }
                    }
                    if (f2313n && ExiledKingdoms.j(Settings.f3418a.recovery)) {
                        z.v().G();
                        m();
                    }
                    if (z.v().A() && GameData.v().player.l0()) {
                        l();
                    }
                    if (f2313n) {
                        if (ExiledKingdoms.j(Settings.f3418a.action)) {
                            m();
                            l();
                        } else if (!Gdx.input.isKeyPressed(Settings.f3418a.action.id) && GameData.v().player != null) {
                            GameData.v().player.getClass();
                            if (z.f2926j0) {
                                GameData.v().player.getClass();
                                z.f2926j0 = false;
                            }
                        }
                    }
                    if (ExiledKingdoms.f3378h) {
                        fE += ExiledKingdoms.e(Settings.f3419b.right) - ExiledKingdoms.e(Settings.f3419b.left);
                        fE2 += ExiledKingdoms.e(Settings.f3419b.up) - ExiledKingdoms.e(Settings.f3419b.down);
                        if (Gdx.input.isKeyPressed(Settings.f3419b.attack.id)) {
                            l();
                        } else if (!Player.f3027e && !Gdx.input.isKeyPressed(Settings.f3419b.attack.id) && !Gdx.input.isKeyPressed(Settings.f3418a.action.id)) {
                            GameData.v().player.getClass();
                            if (z.f2926j0) {
                                GameData.v().player.getClass();
                                z.f2926j0 = false;
                            }
                        }
                        if (ExiledKingdoms.j(Settings.f3419b.interact)) {
                            z.v().getClass();
                            if (GameData.v().player.numActivables > 0) {
                                z.v().E(0);
                            }
                        }
                        if (!Gdx.input.isButtonPressed(0) && !Gdx.input.isKeyPressed(Settings.f3419b.attack.id) && !Gdx.input.isKeyPressed(Settings.f3418a.action.id)) {
                            GameData.v().player.getClass();
                            if (z.f2926j0) {
                                GameData.v().player.getClass();
                                z.f2926j0 = false;
                            }
                            GameLevel.f3100g = false;
                        }
                        if (ExiledKingdoms.j(Settings.f3419b.quickItem1)) {
                            z.v().b0(0);
                        }
                        if (ExiledKingdoms.j(Settings.f3419b.quickItem2)) {
                            z.v().b0(1);
                        }
                        if (ExiledKingdoms.j(Settings.f3419b.quickItem3)) {
                            z.v().b0(2);
                        }
                        if (ExiledKingdoms.j(Settings.f3419b.quickItem4)) {
                            z.v().b0(3);
                        }
                        if (ExiledKingdoms.j(Settings.f3419b.quickItem5)) {
                            z.v().b0(4);
                        }
                        if (o()) {
                            if (ExiledKingdoms.j(Settings.f3419b.skill1)) {
                                z.v().k(0);
                            }
                            if (ExiledKingdoms.j(Settings.f3419b.skill2)) {
                                z.v().k(1);
                            }
                            if (ExiledKingdoms.j(Settings.f3419b.skill3)) {
                                z.v().k(2);
                            }
                            if (ExiledKingdoms.j(Settings.f3419b.skill4)) {
                                z.v().k(3);
                            }
                            if (ExiledKingdoms.j(Settings.f3419b.skill5)) {
                                z.v().k(4);
                            }
                            if (ExiledKingdoms.j(Settings.f3419b.skill6)) {
                                z.v().k(5);
                            }
                            if (ExiledKingdoms.j(Settings.f3419b.skill7)) {
                                z.v().k(6);
                            }
                            if (ExiledKingdoms.j(Settings.f3419b.skill8)) {
                                z.v().k(7);
                            }
                        }
                        if (f2313n && ExiledKingdoms.j(Settings.f3419b.recovery)) {
                            z.v().G();
                            m();
                        }
                        if (ExiledKingdoms.j(Settings.f3419b.map)) {
                            z.v().h();
                        }
                        if (ExiledKingdoms.j(Settings.f3419b.character)) {
                            z.v().i();
                        }
                        if (ExiledKingdoms.j(Settings.f3419b.companion)) {
                            z.v().g();
                        }
                        if (ExiledKingdoms.j(Settings.f3419b.log)) {
                            c0.b().c();
                        }
                        if (z.v().w() == 0) {
                            k(Gdx.input.getX(), Gdx.input.getY());
                            m0.b bVarP2 = m0.b.P();
                            Vector2 qVar = this.f2324g;
                            bVarP2.getClass();
                            int size = GameLevel.f3096c.size();
                            int i2 = 0;
                            while (true) {
                                if (i2 >= size) {
                                    iQ = 0;
                                    break;
                                }
                                WorldFactions worldFactions = GameWorld.f3189c;
                                int[] iArrR = GameData.v().player.r();
                                ArrayList<MapActor> arrayList = GameLevel.f3096c;
                                if (worldFactions.g(iArrR, arrayList.get(i2).r()) && !arrayList.get(i2).i0() && Math.abs(arrayList.get(i2).f3092x - qVar.f91a) < 18.0f && Math.abs(arrayList.get(i2).f3093y - qVar.f92b) < 18.0f) {
                                    iQ = arrayList.get(i2).q();
                                    break;
                                }
                                i2++;
                            }
                            GameLevel.f3099f = iQ;
                        }
                        if (o() && Gdx.input.isButtonPressed(0) && this.f2321d + 0.2f < GameData.v().realTime) {
                            GameData.v().player.getClass();
                            if (!z.f2926j0 && !GameLevel.f3100g) {
                                float x2 = Gdx.input.getX();
                                float width = (((x2 * k0.a.f2270q) / Gdx.graphics.getWidth()) - (k0.a.f2270q / 2)) / 120.0f;
                                float f5 = (-((((Gdx.input.getY() * k0.a.f2271r) / Gdx.graphics.getHeight()) - (k0.a.f2271r / 2)) - 12.0f)) / 120.0f;
                                if (width <= -0.25f || width >= 0.25f || f5 <= -0.25f || f5 >= 0.25f) {
                                    fE = width;
                                    fE2 = f5;
                                } else {
                                    fE = 0.0f;
                                    fE2 = 0.0f;
                                }
                            }
                            f3 = 0.0f;
                        } else {
                            if (o() && Gdx.input.isButtonPressed(0)) {
                                int i3 = ((this.f2321d + 0.2f) > GameData.v().realTime ? 1 : ((this.f2321d + 0.2f) == GameData.v().realTime ? 0 : -1));
                            }
                            f3 = 0.0f;
                        }
                    } else {
                        f3 = 0.0f;
                    }
                    if (fE != f3 || (fE2 != f3 && !ExiledKingdoms.j(Settings.f3419b.attack))) {
                        GameData.v().player.V1(-1, -1);
                        GameLevel.f3101h = 0;
                        GameLevel.f3100g = false;
                        GameData.v().player.getClass();
                        if (z.f2926j0) {
                            GameData.v().player.getClass();
                            z.f2926j0 = false;
                        }
                        z.D(fE, fE2);
                    }
                } else if (!z.y().booleanValue() || z0.f2967e) {
                    if (ExiledKingdoms.f3378h) {
                        if (!f2310k && z.v().w() == 2) {
                            int i4 = Gdx.input.isKeyPressed(21) ? 10 : 0;
                            if (Gdx.input.isKeyPressed(22)) {
                                i4 = -10;
                            }
                            int i5 = Gdx.input.isKeyPressed(20) ? -10 : Gdx.input.isKeyPressed(19) ? 10 : 0;
                            if (Gdx.input.getX() > Gdx.graphics.getWidth() - 40) {
                                i4 = -8;
                            }
                            if (Gdx.input.getX() < 40) {
                                i4 = 8;
                            }
                            if (Gdx.input.getY() < 40) {
                                i5 = 8;
                            }
                            if (Gdx.input.getY() > Gdx.graphics.getHeight() - 40) {
                                i5 = -8;
                            }
                            q1 q1Var = z.v().f2949i;
                            q1Var.setX(q1Var.getX() + i4);
                            q1Var.setY(q1Var.getY() - i5);
                        }
                        if (!f2310k && z.v().w() == 1) {
                            int i6 = Gdx.input.isKeyPressed(21) ? -20 : 0;
                            if (Gdx.input.isKeyPressed(22)) {
                                i6 = 20;
                            }
                            int i7 = Gdx.input.isKeyPressed(20) ? -20 : Gdx.input.isKeyPressed(19) ? 20 : 0;
                            if (Gdx.input.getX() > Gdx.graphics.getWidth() - 30) {
                                i6 = 8;
                            }
                            if (Gdx.input.getX() < 30) {
                                i6 = -8;
                            }
                            k0.a.l().g(i6, Gdx.input.getY() <= Gdx.graphics.getHeight() - 30 ? Gdx.input.getY() >= 70 ? i7 : 8 : -8);
                        }
                        if (ExiledKingdoms.j(Settings.f3419b.map)) {
                            z.v().h();
                        }
                        if (ExiledKingdoms.j(Settings.f3419b.character) && z.y().booleanValue()) {
                            j();
                        }
                        if (ExiledKingdoms.j(Settings.f3419b.companion) && z.y().booleanValue()) {
                            j();
                        }
                    }
                    if (f2313n && ExiledKingdoms.j(Settings.f3418a.action) && z.v().V.C()) {
                        m();
                        z.v().V.W();
                    }
                    if (f2313n && ((ExiledKingdoms.j(Settings.f3419b.interact) || ExiledKingdoms.j(Settings.f3419b.attack)) && z.v().V.C())) {
                        m();
                        z.v().V.W();
                    }
                    if (ExiledKingdoms.j(Settings.f3419b.log)) {
                        c0.b().setVisible(false);
                        GameLevel.n(false);
                    }
                } else if ((f2313n && ExiledKingdoms.e(Settings.f3418a.up) > 0.1f) || ExiledKingdoms.j(Settings.f3419b.up)) {
                    z.v().V.K();
                    m();
                } else if ((f2313n && ExiledKingdoms.e(Settings.f3418a.down) > 0.1f) || ExiledKingdoms.j(Settings.f3419b.down)) {
                    z.v().V.H();
                    m();
                } else if ((f2313n && ExiledKingdoms.e(Settings.f3418a.left) > 0.1f) || ExiledKingdoms.j(Settings.f3419b.left)) {
                    z.v().V.I();
                    m();
                } else if ((f2313n && ExiledKingdoms.e(Settings.f3418a.right) > 0.1f) || ExiledKingdoms.j(Settings.f3419b.right)) {
                    z.v().V.J();
                    m();
                } else if ((f2313n && ExiledKingdoms.e(Settings.f3418a.invTake) > 0.1f) || ExiledKingdoms.j(Settings.f3419b.invTake)) {
                    z.v().V.F();
                    m();
                } else if ((f2313n && ExiledKingdoms.e(Settings.f3418a.invDrop) > 0.1f) || ExiledKingdoms.j(Settings.f3419b.invDrop)) {
                    z.v().V.G();
                    m();
                }
            } else if (!GameData.v().G() && ((m0.b.P().f2423h || GameData.v().F() || GameData.v().D() || m0.b.P().f2426k) && GameData.v().u() > GameData.v().lastQuickSave + 1.0f && !GameLevel.l())) {
                GameLevel.n(true);
                GameData.v().lastQuickSave = GameData.v().u();
                GameConsole.a(GameString.b("GAME_QUICKSAVED", false));
                Serializer.z(GameData.v().slot, 2);
                GameLevel.n(false);
            } else if (!GameData.v().D() && !GameData.v().F() && !m0.b.P().f2423h && !m0.b.P().f2426k) {
                GameLevel.n(true);
                new a(GameString.b("CANT_SAVE_INDOOR", false), 0).show(z.v().a());
            }
        }
        if (ExiledKingdoms.f3378h) {
            if (GameLevel.f3099f <= 0 || GameData.v().player.i0()) {
                if (ExiledKingdoms.f3378h) {
                    Gdx.graphics.setCursor(Assets.f3312d);
                } else {
                    Assets assets = Assets.f3309a;
                }
            } else if (ExiledKingdoms.f3378h) {
                Gdx.graphics.setCursor(Assets.f3313e);
            } else {
                Assets assets2 = Assets.f3309a;
            }
        }
        if (!GameLevel.l()) {
            if (ExiledKingdoms.f3378h && ExiledKingdoms.s) {
                ExiledKingdoms.f().getClass();
            }
            if (f2311l) {
                f2311l = false;
                GameAssets.o("journal");
                return;
            }
            return;
        }
        if (f2317r) {
            Settings.v();
            f2317r = false;
        }
        if (s && GameLevel.m()) {
            s = false;
            GPGSUpdate.c(false);
        }
    }

    @Override // com.badlogic.gdx.InputProcessor
    public final boolean keyDown(int i2) {
        return false;
    }

    @Override // com.badlogic.gdx.InputProcessor
    public final boolean keyTyped(char c2) {
        return false;
    }

    @Override // com.badlogic.gdx.InputProcessor
    public final boolean keyUp(int i2) {
        if (i2 == Settings.f3419b.attack.id) {
            AStarPathFinder aStarPathFinder = GameLevel.f3094a;
            GameData.v().player.getClass();
            z.f2926j0 = false;
        }
        return false;
    }

    @Override // com.badlogic.gdx.InputProcessor
    public final boolean mouseMoved(int i2, int i3) {
        return false;
    }

    @Override // com.badlogic.gdx.Screen
    public final void resume() {
    }

    @Override // com.badlogic.gdx.InputProcessor
    public final boolean scrolled(float f2, float f3) {
        return false;
    }

    @Override // com.badlogic.gdx.InputProcessor
    public final boolean touchDown(int i2, int i3, int i4, int i5) {
        if (ExiledKingdoms.f3378h && !f2310k && z.v().w() == 0 && o()) {
            this.f2321d = (float) GameData.v().realTime;
            k(i2, i3);
            Player player = GameData.v().player;
            Vector2 qVar = this.f2324g;
            int i6 = (int) qVar.f91a;
            int i7 = (int) qVar.f92b;
            player.getClass();
            int i8 = GameLevel.f3099f;
            if (i8 <= 1) {
                player.V1(i6, i7);
                GameLevel.f3101h = 0;
                GameLevel.f3100g = false;
            } else {
                GameLevel.f3101h = i8;
                GameLevel.f3100g = true;
                int i9 = player.lastTargetHit_id;
                int i10 = GameLevel.f3101h;
                if (i9 != i10) {
                    player.SkillUseData_consecutive_hits = 0;
                }
                player.lastTargetHit_id = i10;
                if (player.A0(GameLevel.f3099f, false)) {
                    player.E0(GameLevel.f3099f);
                } else {
                    player.V1(i6, i7);
                }
            }
        } else {
            this.f2319b = (float) GameData.v().realTime;
            if (!f2310k && z.v().w() == 1) {
                k0.a.l().f2277d.t(i2, i3, 0.0f);
            }
            if (!f2310k && z.v().w() == 2) {
                z.v().K(i2, i3);
            }
        }
        return false;
    }

    @Override // com.badlogic.gdx.InputProcessor
    public final boolean touchDragged(int i2, int i3, int i4) {
        if (ExiledKingdoms.f3378h) {
            return false;
        }
        if (!f2310k && z.v().w() == 1) {
            k0.a.l().o(i2, i3);
        }
        if (f2310k || z.v().w() != 2) {
            return false;
        }
        z.v().o(i2, i3);
        return false;
    }

    @Override // com.badlogic.gdx.InputProcessor
    public final boolean touchUp(int i2, int i3, int i4, int i5) {
        if (GameData.v().realTime <= this.f2319b + 0.5f) {
            z.v().f2949i.b(i2, i3);
        }
        if (!ExiledKingdoms.f3378h && !z.v().A()) {
            AStarPathFinder aStarPathFinder = GameLevel.f3094a;
            GameData.v().player.getClass();
            if (z.f2926j0) {
                GameData.v().player.getClass();
                z.f2926j0 = false;
            }
        }
        if (ExiledKingdoms.f3378h && !Gdx.input.isKeyPressed(Settings.f3419b.attack.id)) {
            AStarPathFinder aStarPathFinder2 = GameLevel.f3094a;
            GameData.v().player.getClass();
            if (z.f2926j0) {
                GameData.v().player.getClass();
                z.f2926j0 = false;
            }
            GameLevel.f3100g = false;
        }
        return false;
    }
}
