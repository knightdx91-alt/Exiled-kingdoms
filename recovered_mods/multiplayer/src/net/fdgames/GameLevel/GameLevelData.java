package net.fdgames.GameLevel;

import android.support.v4.app.mFy.fApIihhYHIP;
import h0.LN.EXicjtag;
import java.util.ArrayList;
import java.util.Iterator;
import net.fdgames.GameEntities.AI.Pathfinding.AStarPathFinder;
import net.fdgames.GameEntities.CharacterSheet.CharacterEffects;
import net.fdgames.GameEntities.CharacterSheet.CharacterSheet;
import net.fdgames.GameEntities.Final.Door;
import net.fdgames.GameEntities.Final.Loot;
import net.fdgames.GameEntities.Final.MapContainer;
import net.fdgames.GameEntities.Final.MapEffectEntity;
import net.fdgames.GameEntities.Final.MapItem;
import net.fdgames.GameEntities.Final.MonsterSpawn;
import net.fdgames.GameEntities.Final.NPC;
import net.fdgames.GameEntities.Final.PlantSpawn;
import net.fdgames.GameEntities.Final.Projectile;
import net.fdgames.GameEntities.Final.SecretDoor;
import net.fdgames.GameEntities.Final.StaticNPC;
import net.fdgames.GameEntities.Final.Trap;
import net.fdgames.GameEntities.Helpers.Shop;
import net.fdgames.GameEntities.MapActor;
import net.fdgames.GameEntities.MapSprite;
import net.fdgames.GameWorld.Follower;
import net.fdgames.GameWorld.GameData;
import net.fdgames.Helpers.Serializer;
import net.fdgames.Rules.Rules;
import net.fdgames.Rules.Spawn;
import net.fdgames.TiledMap.Objects.Trigger;
import y0.b;

/* JADX INFO: loaded from: /tmp/tmp.CKdUvKN3M2/classes.dex */
public class GameLevelData {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static GameLevelData f3317a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static ArrayList<MapSprite> f3318b = new ArrayList<>();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static boolean f3319c = false;
    public ArrayList<NPC> npcs = new ArrayList<>();
    public ArrayList<Loot> loots = new ArrayList<>();
    public ArrayList<MapContainer> containers = new ArrayList<>();
    public ArrayList<Shop> shops = new ArrayList<>();
    public ArrayList<MonsterSpawn> spawns = new ArrayList<>();
    public ArrayList<PlantSpawn> plants = new ArrayList<>();
    public ArrayList<StaticNPC> staticNPCs = new ArrayList<>();
    public ArrayList<Door> doors = new ArrayList<>();
    public ArrayList<MapItem> mapItems = new ArrayList<>();
    public ArrayList<MapEffectEntity> mapEffects = new ArrayList<>();
    public ArrayList<Trap> traps = new ArrayList<>();
    public ArrayList<Projectile> projectiles = new ArrayList<>();
    public ArrayList<Trigger> triggers = new ArrayList<>();
    public ArrayList<SecretDoor> secretDoors = new ArrayList<>();
    public int lastID = 0;

    private GameLevelData() {
    }

    public static ArrayList<Trigger> A() {
        GameLevelData gameLevelData = f3317a;
        if (gameLevelData.triggers == null) {
            gameLevelData.triggers = new ArrayList<>();
        }
        return f3317a.triggers;
    }

    public static void B() {
        f3317a.lastID = 1;
    }

    public static void E(GameLevelData gameLevelData) {
        f3319c = false;
        ArrayList<MonsterSpawn> arrayList = gameLevelData.spawns;
        if ((arrayList == null || arrayList.size() == 0) && b.P().X()) {
            f3319c = true;
            return;
        }
        float fU = GameData.v().u();
        float f2 = 0.0f;
        for (int i2 = 0; i2 < gameLevelData.npcs.size(); i2++) {
            if (gameLevelData.npcs.get(i2).lastTick > f2) {
                f2 = gameLevelData.npcs.get(i2).lastTick;
            }
            if (gameLevelData.npcs.get(i2).lastTick > 0.0f && gameLevelData.npcs.get(i2).lastTick < fU) {
                fU = gameLevelData.npcs.get(i2).lastTick;
            }
        }
        if (!Serializer.f3449e && (f2 > GameData.v().u() || fU + 1080.0f < GameData.v().u())) {
            f3319c = true;
            return;
        }
        f3317a = gameLevelData;
        if (!Serializer.f3449e) {
            Iterator<NPC> it = f3317a.npcs.iterator();
            while (it.hasNext()) {
                NPC next = it.next();
                if (next.lanPeerVisual) {
                    it.remove();
                } else {
                    next.sheet.S();
                    CharacterSheet characterSheet = next.sheet;
                    characterSheet.getClass();
                    characterSheet.effects = new CharacterEffects();
                    if (GameData.v().party.p(next.spawn_id, next.tag) || next.M1()) {
                        it.remove();
                    }
                }
            }
            return;
        }
        if (GameData.v().party.j()) {
            Iterator<NPC> it2 = f3317a.npcs.iterator();
            while (it2.hasNext()) {
                if (it2.next().spawn_id.equals(GameData.v().party.f().spawn_id)) {
                    it2.remove();
                }
            }
            GameData.v().party.f().U1(0);
            GameData.v().party.f().w();
            GameData.v().party.f().C1();
            GameData.v().party.f().Q1();
            GameLevel.a(GameData.v().party.f());
            if (GameData.v().party.f().i0()) {
                GameData.v().party.f().o("RECOVER", GameData.v().party.f().q(), null, 0.1f, null);
            }
            Iterator<NPC> it3 = f3317a.npcs.iterator();
            while (it3.hasNext()) {
                if (it3.next().spawn_id.equals(GameData.v().party.g())) {
                    GameData.v().party.f();
                }
            }
        }
    }

    public static void F() {
        ArrayList<NPC> arrayList = f3317a.npcs;
        if (arrayList != null) {
            Iterator<NPC> it = arrayList.iterator();
            while (it.hasNext()) {
                if (it.next().d0() == MapActor.ActorState.f3289e) {
                    it.remove();
                }
            }
        }
    }

    public static void G() {
        for (NPC npc : f3317a.npcs) {
            if (!npc.k0()) {
                npc.Q1();
            }
        }
    }

    public static void I() {
        if (!GameData.v().party.o()) {
            if (GameData.v().party.p("IM_breno", "IM_breno") && !GameData.v().CurrentLevel.equals(EXicjtag.fPQGga) && !GameData.v().CurrentLevel.equals("G13") && !GameData.v().CurrentLevel.equals("IM_sewer") && !GameData.v().CurrentLevel.equals("IM_planeoffire")) {
                GameData.v().party.d("IM_breno");
                GameData.v().gameVariables.e(100, "sewer_horrors");
                GameData.v().gameVariables.c(12, "REP_thuram");
                GameData.v().player.O0(20000);
            }
            for (Follower follower : GameData.v().party.followers) {
                ArrayList<NPC> arrayList = f3317a.npcs;
                if (arrayList != null) {
                    Iterator<NPC> it = arrayList.iterator();
                    while (it.hasNext()) {
                        NPC next = it.next();
                        if (next.spawn_id.equals(follower.spawn_id) && next.tag.equals(follower.a().tag)) {
                            it.remove();
                        }
                    }
                }
                Spawn spawn = new Spawn(Rules.i(follower.spawn_id));
                spawn.conversation_ID = follower.a().conversation_id;
                spawn.wander = follower.a().wander_range;
                if (follower.a().name != null && !follower.a().name.equals("")) {
                    spawn.b(follower.a().name);
                }
                spawn.portrait = follower.a().portrait;
                NPC npc = new NPC(spawn);
                npc.tag = follower.a().tag;
                npc.f3307x = GameData.v().player.f3307x;
                npc.f3308y = GameData.v().player.f3308y;
                npc.U1(0);
                npc.x(100);
                GameLevel.a(npc);
                npc.C1();
                int i2 = follower.missingHP;
                if (i2 > 0) {
                    CharacterSheet characterSheet = npc.sheet;
                    characterSheet.stats.missingHP = i2;
                    if (i2 > characterSheet.z()) {
                        characterSheet.stats.missingHP = (int) (characterSheet.z() * 0.9f);
                    }
                    follower.missingHP = 0;
                }
                int i3 = follower.dismissTime;
                if (i3 > 0) {
                    npc.dismissTime = i3;
                    npc.respawned = true;
                    npc.summoned = true;
                    npc.sheet.attributes.outsider = true;
                }
                int i4 = follower.lastLevel;
                if (i4 > 0) {
                    npc.sheet.i0(i4);
                }
            }
        }
        if (!GameData.v().party.j() || f3317a.npcs == null) {
            return;
        }
        GameData.v().party.f().f3307x = GameData.v().player.f3307x;
        GameData.v().party.f().f3308y = GameData.v().player.f3308y;
        GameData.v().party.f().U1(0);
        GameData.v().party.f().w();
        GameData.v().party.f().C1();
        GameData.v().party.f().Q1();
        GameLevel.a(GameData.v().party.f());
        if (GameData.v().party.f().i0()) {
            GameData.v().party.f().m(0.1f, GameData.v().party.f().q(), fApIihhYHIP.HDt);
        }
    }

    public static void J() {
        f3317a = null;
    }

    public static void K() {
        if (f3317a.npcs != null) {
            for (Follower follower : GameData.v().party.followers) {
                follower.missingHP = 0;
                follower.dismissTime = 0;
                for (NPC npc : f3317a.npcs) {
                    if (npc.spawn_id.equals(follower.spawn_id) && npc.tag.equals(follower.a().tag)) {
                        follower.missingHP = npc.sheet.z() - npc.sheet.o();
                        follower.dismissTime = npc.dismissTime;
                        follower.lastLevel = npc.sheet.stats.f();
                    }
                }
            }
        }
    }

    public static void a(SecretDoor secretDoor) {
        f3317a.secretDoors.add(secretDoor);
    }

    public static void b(NPC npc) {
        f3317a.npcs.add(npc);
    }

    public static void c(MapContainer mapContainer) {
        f3317a.containers.add(mapContainer);
    }

    public static void d(Loot loot) {
        f3317a.loots.add(loot);
    }

    public static void e(MapEffectEntity mapEffectEntity) {
        f3317a.mapEffects.add(mapEffectEntity);
    }

    public static void f(MapItem mapItem) {
        f3317a.mapItems.add(mapItem);
    }

    public static void g(PlantSpawn plantSpawn) {
        f3317a.plants.add(plantSpawn);
    }

    public static void h(Projectile projectile) {
        GameLevelData gameLevelData = f3317a;
        if (gameLevelData.projectiles == null) {
            gameLevelData.projectiles = new ArrayList<>();
        }
        f3317a.projectiles.add(projectile);
    }

    public static void i(Trap trap) {
        f3317a.traps.add(trap);
    }

    public static void j(Trigger trigger) {
        f3317a.triggers.add(trigger);
    }

    public static MapContainer l(int i2) {
        for (int i3 = 0; i3 < f3317a.containers.size(); i3++) {
            if (f3317a.containers.get(i3).q() == i2) {
                return f3317a.containers.get(i3);
            }
        }
        return null;
    }

    public static ArrayList<MapContainer> m() {
        return f3317a.containers;
    }

    public static GameLevelData o() {
        if (f3317a == null) {
            f3317a = new GameLevelData();
        }
        return f3317a;
    }

    public static ArrayList<Loot> p() {
        return f3317a.loots;
    }

    public static ArrayList<MapEffectEntity> q() {
        return f3317a.mapEffects;
    }

    public static ArrayList<MapItem> r() {
        return f3317a.mapItems;
    }

    public static ArrayList<NPC> t() {
        return f3317a.npcs;
    }

    public static ArrayList<SecretDoor> v() {
        return f3317a.secretDoors;
    }

    public static ArrayList<MapSprite> x() {
        ArrayList<Loot> arrayList = f3317a.loots;
        if (arrayList != null) {
            Iterator<Loot> it = arrayList.iterator();
            while (it.hasNext()) {
                Loot next = it.next();
                if (next.destroy) {
                    GameLevelData gameLevelData = f3317a;
                    int iQ = next.q();
                    Iterator<Trigger> it2 = gameLevelData.triggers.iterator();
                    while (it2.hasNext()) {
                        if (it2.next().owner == iQ) {
                            it2.remove();
                        }
                    }
                    it.remove();
                }
            }
        }
        ArrayList<NPC> arrayList2 = f3317a.npcs;
        if (arrayList2 != null) {
            Iterator<NPC> it3 = arrayList2.iterator();
            while (it3.hasNext()) {
                NPC next2 = it3.next();
                if (next2.destroy) {
                    next2.destroy = false;
                    it3.remove();
                }
            }
        }
        ArrayList<PlantSpawn> arrayList3 = f3317a.plants;
        if (arrayList3 != null) {
            Iterator<PlantSpawn> it4 = arrayList3.iterator();
            while (it4.hasNext()) {
                if (it4.next().destroy) {
                    it4.remove();
                }
            }
        }
        ArrayList<MapItem> arrayList4 = f3317a.mapItems;
        if (arrayList4 != null) {
            Iterator<MapItem> it5 = arrayList4.iterator();
            while (it5.hasNext()) {
                if (it5.next().destroy) {
                    it5.remove();
                }
            }
        }
        ArrayList<MapEffectEntity> arrayList5 = f3317a.mapEffects;
        if (arrayList5 != null) {
            Iterator<MapEffectEntity> it6 = arrayList5.iterator();
            while (it6.hasNext()) {
                if (it6.next().destroy) {
                    it6.remove();
                }
            }
        }
        ArrayList<Trap> arrayList6 = f3317a.traps;
        if (arrayList6 != null) {
            Iterator<Trap> it7 = arrayList6.iterator();
            while (it7.hasNext()) {
                if (it7.next().destroy) {
                    it7.remove();
                }
            }
        }
        ArrayList<StaticNPC> arrayList7 = f3317a.staticNPCs;
        if (arrayList7 != null) {
            Iterator<StaticNPC> it8 = arrayList7.iterator();
            while (it8.hasNext()) {
                if (it8.next().destroy) {
                    it8.remove();
                }
            }
        }
        ArrayList<Projectile> arrayList8 = f3317a.projectiles;
        if (arrayList8 != null) {
            Iterator<Projectile> it9 = arrayList8.iterator();
            while (it9.hasNext()) {
                if (it9.next().destroy) {
                    it9.remove();
                }
            }
        }
        ArrayList<MapSprite> arrayList9 = f3318b;
        arrayList9.clear();
        if (f3317a.mapItems != null) {
            for (int i2 = 0; i2 < f3317a.mapItems.size(); i2++) {
                arrayList9.add(f3317a.mapItems.get(i2));
            }
        }
        if (f3317a.mapEffects != null) {
            for (int i3 = 0; i3 < f3317a.mapEffects.size(); i3++) {
                arrayList9.add(f3317a.mapEffects.get(i3));
            }
        }
        if (f3317a.npcs != null) {
            for (int i4 = 0; i4 < f3317a.npcs.size(); i4++) {
                arrayList9.add(f3317a.npcs.get(i4));
            }
        }
        if (f3317a.loots != null) {
            for (int i5 = 0; i5 < f3317a.loots.size(); i5++) {
                arrayList9.add(f3317a.loots.get(i5));
            }
        }
        if (f3317a.plants != null) {
            for (int i6 = 0; i6 < f3317a.plants.size(); i6++) {
                arrayList9.add(f3317a.plants.get(i6));
            }
        }
        if (f3317a.traps != null) {
            for (int i7 = 0; i7 < f3317a.traps.size(); i7++) {
                arrayList9.add(f3317a.traps.get(i7));
            }
        }
        if (f3317a.staticNPCs != null) {
            for (int i8 = 0; i8 < f3317a.staticNPCs.size(); i8++) {
                arrayList9.add(f3317a.staticNPCs.get(i8));
            }
        }
        if (f3317a.projectiles != null) {
            for (int i9 = 0; i9 < f3317a.projectiles.size(); i9++) {
                arrayList9.add(f3317a.projectiles.get(i9));
            }
        }
        arrayList9.add(GameData.v().player);
        return arrayList9;
    }

    public static ArrayList<StaticNPC> y() {
        return f3317a.staticNPCs;
    }

    public static ArrayList<Trap> z() {
        return f3317a.traps;
    }

    public final void C() {
        int size = this.npcs.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.npcs.get(i2).spriteIndex = null;
        }
        AStarPathFinder aStarPathFinder = GameLevel.f3309a;
        if (GameData.v().player != null) {
            GameData.v().player.spriteIndex = null;
        }
        for (int i3 = 0; i3 < GameData.v().party.companions.size(); i3++) {
            GameData.v().party.companions.get(i3).spriteIndex = null;
        }
    }

    public final boolean D() {
        ArrayList<NPC> arrayList = this.npcs;
        if (arrayList != null && arrayList.size() > 0) {
            return false;
        }
        ArrayList<Loot> arrayList2 = this.loots;
        if (arrayList2 != null && arrayList2.size() > 0) {
            return false;
        }
        ArrayList<MapContainer> arrayList3 = this.containers;
        if (arrayList3 != null && arrayList3.size() > 0) {
            return false;
        }
        ArrayList<Shop> arrayList4 = this.shops;
        if (arrayList4 != null && arrayList4.size() > 0) {
            return false;
        }
        ArrayList<MonsterSpawn> arrayList5 = this.spawns;
        if (arrayList5 != null && arrayList5.size() > 0) {
            return false;
        }
        ArrayList<PlantSpawn> arrayList6 = this.plants;
        if (arrayList6 != null && arrayList6.size() > 0) {
            return false;
        }
        ArrayList<Trap> arrayList7 = this.traps;
        if (arrayList7 != null && arrayList7.size() > 0) {
            return false;
        }
        ArrayList<MapItem> arrayList8 = this.mapItems;
        if (arrayList8 != null && arrayList8.size() > 0) {
            return false;
        }
        ArrayList<MapEffectEntity> arrayList9 = this.mapEffects;
        if (arrayList9 != null && arrayList9.size() > 0) {
            return false;
        }
        ArrayList<StaticNPC> arrayList10 = this.staticNPCs;
        if (arrayList10 != null && arrayList10.size() > 0) {
            return false;
        }
        ArrayList<Projectile> arrayList11 = this.projectiles;
        if (arrayList11 != null && arrayList11.size() > 0) {
            return false;
        }
        ArrayList<Trigger> arrayList12 = this.triggers;
        if (arrayList12 != null && arrayList12.size() > 0) {
            return false;
        }
        ArrayList<SecretDoor> arrayList13 = this.secretDoors;
        if (arrayList13 != null && arrayList13.size() > 0) {
            return false;
        }
        ArrayList<Door> arrayList14 = this.doors;
        return arrayList14 == null || arrayList14.size() <= 0;
    }

    public final void H() {
        this.npcs.clear();
        this.loots.clear();
        this.containers.clear();
        this.shops.clear();
        this.spawns.clear();
        this.doors.clear();
        this.plants.clear();
        this.traps.clear();
        this.mapItems.clear();
        this.mapEffects.clear();
        this.staticNPCs.clear();
        this.projectiles.clear();
        this.triggers.clear();
        this.secretDoors.clear();
    }

    public final void k() {
        ArrayList arrayList = new ArrayList();
        for (NPC npc : this.npcs) {
            while (arrayList.contains(Integer.valueOf(npc.q()))) {
                System.out.println("WARNING: duplicated npc id=" + npc.q() + ", id has been reset.");
                npc.w();
            }
            arrayList.add(Integer.valueOf(npc.q()));
        }
    }

    public final Door n(int i2, int i3) {
        int size = this.doors.size();
        for (int i4 = 0; i4 < size; i4++) {
            if (this.doors.get(i4).f3307x / 32 == i2 && this.doors.get(i4).f3308y / 32 == i3) {
                return this.doors.get(i4);
            }
        }
        return null;
    }

    public final int s(String str) {
        for (NPC npc : this.npcs) {
            String str2 = npc.tag;
            if (str2 != null && str2.equals(str)) {
                return npc.q();
            }
        }
        for (NPC npc2 : this.npcs) {
            if (npc2.spawn_id.equals(str)) {
                return npc2.q();
            }
        }
        for (StaticNPC staticNPC : this.staticNPCs) {
            String str3 = staticNPC.tag;
            if (str3 != null && str3.equals(str)) {
                return staticNPC.q();
            }
        }
        return 0;
    }

    public final SecretDoor u(int i2, int i3) {
        if (this.secretDoors == null) {
            this.secretDoors = new ArrayList<>();
        }
        int size = this.secretDoors.size();
        for (int i4 = 0; i4 < size; i4++) {
            if (this.secretDoors.get(i4).f3307x / 32 == i2 && this.secretDoors.get(i4).f3308y / 32 == i3) {
                return this.secretDoors.get(i4);
            }
        }
        return null;
    }

    public final Shop w(int i2) {
        for (Shop shop : this.shops) {
            if (shop.o() == i2) {
                return shop;
            }
        }
        return null;
    }
}
