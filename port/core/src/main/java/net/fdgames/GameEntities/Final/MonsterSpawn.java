package net.fdgames.GameEntities.Final;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.l;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import java.util.Iterator;
import java.util.Locale;
import m0.b;
import m0.e;
import net.fdgames.GameEntities.Character;
import net.fdgames.GameEntities.Helpers.BestiaryData;
import net.fdgames.GameEntities.Helpers.Factions;
import net.fdgames.GameEntities.Helpers.Shop;
import net.fdgames.GameEntities.MapActor;
import net.fdgames.GameEntities.MapObject;
import net.fdgames.GameLevel.GameLevel;
import net.fdgames.GameLevel.GameLevelData;
import net.fdgames.GameLogic.ConditionsSet;
import net.fdgames.GameWorld.DynamicEvent;
import net.fdgames.GameWorld.DynamicQuest;
import net.fdgames.GameWorld.GameData;
import net.fdgames.GameWorld.GameWorld;
import net.fdgames.GameWorld.Party;
import net.fdgames.GameWorld.QuestVariation;
import net.fdgames.GameWorld.Quests;
import net.fdgames.GameWorld.WorldEvents;
import net.fdgames.GameWorld.WorldRandomNames;
import net.fdgames.Helpers.FDUtils;
import net.fdgames.Helpers.GameString;
import net.fdgames.Helpers.Serializer;
import net.fdgames.Rules.Rules;
import net.fdgames.Rules.Spawn;
import net.fdgames.TiledMap.Objects.Trigger;
import net.fdgames.assets.AnimationLoader;
import net.fdgames.ek.Settings;

/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public class MonsterSpawn extends MapObject {
    public boolean alwaysReward;
    public Trigger conversationTrigger;
    public String conversation_ID;
    public Daycycle cycle;
    public String eventLocation;
    public int eventSpawn;
    public boolean markRespawn;
    public String name;
    public int portrait;
    public MapActor.Facing preferredFacing;
    public String questLocation;
    public int questSpawn;
    public int respawn;
    public Shop shop;
    public ConditionsSet spawnConditions;
    public Factions.Faction spawnFaction;
    public int spawnLevel;
    public String spawn_id;
    public String spawn_id_rare;
    public String spawn_id_uncommon;
    public String unique_tag;
    public int wander;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    public enum Daycycle {
        NORMAL, DAY, NIGHT;
    }

    public MonsterSpawn() {
        this.spawnLevel = 0;
    }

    public void H() {
        int iB = FDUtils.b(1, 100);
        if (!this.spawn_id_rare.equals("") && iB > 92) {
            this.spawn_id = this.spawn_id_rare;
        } else {
            if (this.spawn_id_uncommon.equals("") || iB <= 69) {
                return;
            }
            this.spawn_id = this.spawn_id_uncommon;
        }
    }

    @Override // net.fdgames.GameEntities.MapObject
    public final TextureRegion E() {
        return null;
    }

    public final void F(Trigger trigger) {
        this.conversationTrigger = trigger;
    }

    public final void G(String str, String str2) {
        if (DynamicEvent.c(str) == null) {
            return;
        }
        if (str2.equals("")) {
            str2 = "1";
        }
        this.eventLocation = str.toLowerCase(Locale.ENGLISH);
        this.eventSpawn = Integer.parseInt(str2);
    }

    public final boolean I() {
        return this.spawnConditions.a().booleanValue();
    }

    public final Daycycle J() {
        if (this.cycle == null) {
            this.cycle = Daycycle.NORMAL;
        }
        return this.cycle;
    }

    public final void K() {
        this.alwaysReward = true;
    }

    public final void L(String str) {
        this.spawnConditions = new ConditionsSet(str);
    }

    public final void M(String str) {
        String lowerCase = str.toLowerCase(Locale.ENGLISH);
        this.cycle = lowerCase.equals("night") ? Daycycle.NIGHT : lowerCase.equals("day") ? Daycycle.DAY : Daycycle.NORMAL;
    }

    public final void N(String str) {
        Locale locale = Locale.ENGLISH;
        if (str.toLowerCase(locale).equals("ru")) {
            this.preferredFacing = MapActor.Facing.RU;
        }
        if (str.toLowerCase(locale).equals("rd")) {
            this.preferredFacing = MapActor.Facing.RD;
        }
        if (str.toLowerCase(locale).equals("lu")) {
            this.preferredFacing = MapActor.Facing.LU;
        }
        if (str.toLowerCase(locale).equals("ld")) {
            this.preferredFacing = MapActor.Facing.LD;
        }
        if (str.toLowerCase(locale).equals("l")) {
            this.preferredFacing = MapActor.Facing.L;
        }
        if (str.toLowerCase(locale).equals("d")) {
            this.preferredFacing = MapActor.Facing.D;
        }
    }

    public final void O(String str) {
        this.unique_tag = str;
    }

    public final void P(int i2) {
        this.wander = i2;
    }

    /* JADX WARN: Removed duplicated region for block: B:42:0x0108  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0157  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void Q() {
        String strH;
        NPC next;
        int iJ = DynamicQuest.j(this.questLocation);
        if (this.cycle == null) {
            this.cycle = Daycycle.NORMAL;
        }
        if (GameData.v().night && this.cycle == Daycycle.DAY) {
            return;
        }
        if (!GameData.v().night && this.cycle == Daycycle.NIGHT) {
            return;
        }
        if (iJ > 0 && !this.markRespawn) {
            String str = DynamicQuest.e(iJ).quest_ID;
            String str2 = DynamicQuest.e(iJ).variation_ID;
            GameWorld.f3187a.getClass();
            QuestVariation questVariationD = Quests.d(str, str2);
            GameWorld.f3187a.getClass();
            if (!Quests.a(str).h()) {
                GameWorld.f3187a.getClass();
                if (Quests.a(str).g()) {
                    int i2 = this.questSpawn;
                    if (i2 == 2) {
                        this.spawn_id = questVariationD.spawn_id2;
                        this.spawnLevel = Math.max(DynamicQuest.e(iJ).difficulty_level - 2, 1);
                        this.worldfactions[0] = 101;
                    } else if (i2 == 1) {
                        this.spawn_id = questVariationD.spawn_id;
                        this.name = questVariationD.a();
                        this.worldfactions[0] = 101;
                        this.spawnLevel = DynamicQuest.e(iJ).difficulty_level;
                        GameWorld.f3187a.getClass();
                        strH = Quests.a(str).g() ? a.a.h(iJ, "DQ_") : "";
                    }
                }
                if (!GameData.v().party.m(this.spawn_id)) {
                    Party party = GameData.v().party;
                    String str3 = this.spawn_id;
                    Iterator<NPC> it = party.companions.iterator();
                    while (true) {
                        if (it.hasNext()) {
                            next = it.next();
                            if (next.spawn_id.equals(str3)) {
                                break;
                            }
                        } else {
                            next = null;
                            break;
                        }
                    }
                    next.w();
                    next.f3092x = this.f3092x;
                    next.f3093y = this.f3093y;
                    next.visibleToPlayer = Boolean.TRUE;
                    next.spriteIndex = null;
                    if (next.getName().equals("Grisenda")) {
                        next.r1("Grissenda");
                    }
                    GameLevel.a(next);
                    next.V1();
                    return;
                }
                if (Rules.i(this.spawn_id) == null) {
                    System.out.println("WARNING: null spawn for id:" + this.spawn_id);
                    Settings.A(1, "GL_spawnerror_" + this.spawn_id);
                    return;
                }
                Spawn spawn = new Spawn(Rules.i(this.spawn_id));
                spawn.conversation_ID = this.conversation_ID;
                spawn.wander = this.wander;
                String str4 = this.name;
                if (str4 == null || !str4.contains("@")) {
                    String str5 = this.name;
                    if (str5 != null && !str5.equals("") && !this.name.equals("RANDOM")) {
                        spawn.b(this.name);
                    }
                } else {
                    spawn.b(GameString.b(this.name.replace("@", ""), true));
                }
                if (spawn.a().equals("RANDOM")) {
                    spawn.b(WorldRandomNames.c(Rules.i(this.spawn_id).gender));
                }
                int i3 = this.portrait;
                if (i3 > 0) {
                    spawn.portrait = i3;
                }
                if (i3 == -1) {
                    Character.Gender gender = Rules.i(this.spawn_id).gender;
                    spawn.portrait = FDUtils.b(1, 7);
                }
                AnimationLoader.a(this.spawn_id);
                NPC npc = new NPC(spawn);
                if (this.tag.equals("")) {
                    this.tag = Integer.toHexString(FDUtils.b(1, CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT));
                }
                npc.tag = this.tag;
                npc.U1(q());
                npc.f3092x = this.f3092x;
                npc.f3093y = this.f3093y;
                GameLevel.a(npc);
                npc.B1();
                MapActor.Facing facing = this.preferredFacing;
                if (facing != null) {
                    npc.facing = facing;
                }
                npc.visibleToPlayer = Boolean.TRUE;
                int i4 = this.spawnLevel;
                if (i4 > 0) {
                    npc.sheet.i0(i4);
                }
                int i5 = this.respawn;
                if (i5 > 0) {
                    npc.S1(i5);
                }
                if (!strH.equals("")) {
                    npc.tag = strH;
                }
                int[] iArr = this.worldfactions;
                if (iArr[0] != 0) {
                    npc.y(iArr);
                }
                String str6 = this.eventLocation;
                if (str6 != null && !str6.equals("") && this.eventSpawn == 1) {
                    String str7 = this.eventLocation;
                    npc.eventSpawnLocation = str7;
                    DynamicEvent dynamicEventC = DynamicEvent.c(str7);
                    if (dynamicEventC != null) {
                        WorldEvents worldEvents = GameWorld.f3193g;
                        String str8 = dynamicEventC.event_id;
                        worldEvents.getClass();
                        String[] strArrSplit = WorldEvents.a(str8).spawn_id2.trim().split(";");
                        if (strArrSplit != null) {
                            for (String str9 : strArrSplit) {
                                if (!str9.trim().equals("")) {
                                    NPC npc2 = new NPC(new Spawn(Rules.i(str9)));
                                    npc2.f3092x = FDUtils.b(-50, 50) + npc.f3092x;
                                    npc2.f3093y = FDUtils.b(-50, 50) + npc.f3093y;
                                    GameLevel.a(npc2);
                                    npc2.y(npc.r());
                                    npc2.B1();
                                }
                            }
                        }
                    }
                }
                Shop shop = this.shop;
                if (shop != null) {
                    shop.u(npc.q());
                    GameLevelData.o().shops.add(this.shop);
                }
                String str10 = this.unique_tag;
                if (str10 != null && !str10.equals("")) {
                    npc.unique_tag = this.unique_tag;
                }
                if (this.alwaysReward) {
                    npc.alwaysReward = true;
                }
                Trigger trigger = this.conversationTrigger;
                if (trigger != null) {
                    trigger.owner = npc.q();
                    GameLevelData.j(this.conversationTrigger);
                }
                if (!spawn.skills.trim().equals("")) {
                    npc.T1(spawn.skills.trim());
                }
                if (this.markRespawn) {
                    npc.respawned = true;
                }
                npc.R1(J());
                if (Serializer.f3226c && npc.s()) {
                    BestiaryData.a(npc);
                    return;
                }
                return;
            }
            H();
            this.spawnLevel = Math.max(DynamicQuest.e(iJ).difficulty_level - 2, 1);
            this.name = GameString.b("GUARDIAN", true);
        } else if (this.eventSpawn <= 0 || DynamicEvent.c(this.eventLocation) == null) {
            H();
        } else {
            DynamicEvent dynamicEventC2 = DynamicEvent.c(this.eventLocation);
            if (dynamicEventC2 != null) {
                WorldEvents worldEvents2 = GameWorld.f3193g;
                String str11 = dynamicEventC2.event_id;
                worldEvents2.getClass();
                this.spawn_id = WorldEvents.a(str11).spawn_id;
                this.name = dynamicEventC2.name;
            }
        }
        if (!GameData.v().party.m(this.spawn_id)) {
        }
    }

    public final void R() {
        int[] iArr = this.worldfactions;
        if (iArr == null || iArr[0] == 0) {
            if (this.faction.equals(Factions.Faction.ENEMY)) {
                x(com.google.android.gms.games.quest.Quests.SELECT_COMPLETED_UNCLAIMED);
            }
            if (this.faction.equals(Factions.Faction.PLAYER)) {
                x(100);
            }
        }
    }

    @Override // net.fdgames.GameEntities.MapObject, java.lang.Comparable
    public final /* bridge */ /* synthetic */ int compareTo(Object obj) {
        return 0;
    }

    @Override // net.fdgames.GameEntities.MapObject
    public final String getName() {
        return this.spawn_id + " " + this.f3092x + " " + this.f3093y;
    }

    @Override // net.fdgames.GameEntities.GameObject
    public final void u(int i2, String str, String str2) {
        if (str.equals("SPAWN")) {
            if (GameData.v().party.p(this.spawn_id, this.tag) || !this.spawnConditions.a().booleanValue()) {
                return;
            }
            Q();
            return;
        }
        if (str.equals("RESPAWN")) {
            if (GameData.v().party.p(this.spawn_id, this.tag) || !this.spawnConditions.a().booleanValue()) {
                return;
            }
            this.markRespawn = true;
            Q();
            return;
        }
        l.d("CAUTION : NON-CONSUMED MESSAGE BY MONSTERSPAWN: " + q() + "/" + getName() + " message: " + str + "/" + i2 + "/" + str2);
    }

    @Override // net.fdgames.GameEntities.MapObject
    /* JADX INFO: renamed from: z */
    public final int compareTo(MapObject mapObject) {
        return 0;
    }

    public MonsterSpawn(int i2, int i3, String str, String str2, String str3, int i4, e eVar) {
        Shop shop;
        this.spawnLevel = 0;
        this.spawn_id = str;
        this.spawn_id_uncommon = str2;
        this.spawn_id_rare = str3;
        this.respawn = i4;
        this.alwaysReward = false;
        this.f3092x = b.u(i2, i3).f3287x;
        this.f3093y = b.u(i2, i3).f3288y;
        this.wander = eVar.f2446b;
        this.name = eVar.f2447c;
        this.worldfactions = new int[2];
        this.unique_tag = "";
        this.portrait = eVar.f2448d;
        this.tag = eVar.f2450f;
        this.questLocation = "";
        this.questSpawn = 0;
        this.eventSpawn = 0;
        this.conversation_ID = eVar.f2445a;
        String str4 = eVar.f2451g;
        if (str4 == null || str4.equals("")) {
            shop = null;
        } else {
            String str5 = eVar.f2451g;
            String str6 = eVar.f2452h;
            shop = new Shop();
            Iterator<Integer> it = Shop.t(str5).iterator();
            while (it.hasNext()) {
                shop.i(it.next().intValue());
            }
            if (!str6.equals("")) {
                shop.modifier = Float.parseFloat(str6);
            } else {
                shop.modifier = 1.0f;
            }
        }
        this.shop = shop;
        ConditionsSet conditionsSet = new ConditionsSet(eVar.f2449e);
        this.spawnConditions = conditionsSet;
        this.markRespawn = false;
        this.preferredFacing = MapActor.Facing.LD;
        if (conditionsSet.a().booleanValue()) {
            m(0.1f, q(), "SPAWN");
        }
    }
}
