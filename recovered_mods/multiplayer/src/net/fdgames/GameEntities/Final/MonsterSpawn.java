package net.fdgames.GameEntities.Final;

import androidx.drawerlayout.widget.Qm.ReTXwDyZpZSd;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.loader.rr.fIsXh;
import com.badlogic.gdx.scenes.scene2d.utils.WHW.gNaSiQJmMEn;
import com.badlogic.gdx.utils.l;
import com.google.android.gms.common.logging.Nqk.fNqLzGyLIBqug;
import h0.LN.EXicjtag;
import java.util.Iterator;
import java.util.Locale;
import l0.KUbz.MbzYTTrg;
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
import net.fdgames.ek.android.lan.LanSessionManager;
import y0.b;
import y0.e;

/* JADX INFO: loaded from: /tmp/tmp.CKdUvKN3M2/classes.dex */
public class MonsterSpawn extends MapObject {
    private boolean alwaysReward;
    private Trigger conversationTrigger;
    private String conversation_ID;
    private Daycycle cycle;
    public String eventLocation;
    public int eventSpawn;
    private boolean markRespawn;
    private String name;
    private int portrait;
    public MapActor.Facing preferredFacing;
    public String questLocation;
    public int questSpawn;
    private int respawn;
    private Shop shop;
    private ConditionsSet spawnConditions;
    private Factions.Faction spawnFaction;
    public int spawnLevel;
    private String spawn_id;
    private String spawn_id_rare;
    private String spawn_id_uncommon;
    private String unique_tag;
    private int wander;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    public static final class Daycycle {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final Daycycle f3234b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public static final Daycycle f3235c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public static final Daycycle f3236d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private static final /* synthetic */ Daycycle[] f3237e;

        static {
            Daycycle daycycle = new Daycycle("NORMAL", 0);
            f3234b = daycycle;
            Daycycle daycycle2 = new Daycycle(EXicjtag.bLmYBYfKoqnR, 1);
            f3235c = daycycle2;
            Daycycle daycycle3 = new Daycycle("NIGHT", 2);
            f3236d = daycycle3;
            f3237e = new Daycycle[]{daycycle, daycycle2, daycycle3};
        }

        private Daycycle() {
            throw null;
        }

        public static Daycycle valueOf(String str) {
            return (Daycycle) Enum.valueOf(Daycycle.class, str);
        }

        public static Daycycle[] values() {
            return (Daycycle[]) f3237e.clone();
        }
    }

    public MonsterSpawn() {
        this.spawnLevel = 0;
    }

    public MonsterSpawn(int i2, int i3, String str, String str2, String str3, int i4, e eVar) {
        Shop shop;
        this.spawnLevel = 0;
        this.spawn_id = str;
        this.spawn_id_uncommon = str2;
        this.spawn_id_rare = str3;
        this.respawn = i4;
        this.alwaysReward = false;
        this.f3307x = b.u(i2, i3).f3508x;
        this.f3308y = b.u(i2, i3).f3509y;
        this.wander = eVar.f4040b;
        this.name = eVar.f4041c;
        this.worldfactions = new int[2];
        String str4 = ReTXwDyZpZSd.eCfM;
        this.unique_tag = str4;
        this.portrait = eVar.f4042d;
        this.tag = eVar.f4044f;
        this.questLocation = str4;
        this.questSpawn = 0;
        this.eventSpawn = 0;
        this.conversation_ID = eVar.f4039a;
        String str5 = eVar.f4045g;
        if (str5 == null || str5.equals(str4)) {
            shop = null;
        } else {
            String str6 = eVar.f4045g;
            String str7 = eVar.f4046h;
            shop = new Shop();
            Iterator<Integer> it = Shop.t(str6).iterator();
            while (it.hasNext()) {
                shop.i(it.next().intValue());
            }
            if (str7.equals(str4)) {
                shop.modifier = 1.0f;
            } else {
                shop.modifier = Float.parseFloat(str7);
            }
        }
        this.shop = shop;
        ConditionsSet conditionsSet = new ConditionsSet(eVar.f4043e);
        this.spawnConditions = conditionsSet;
        this.markRespawn = false;
        this.preferredFacing = MapActor.Facing.f3303g;
        if (conditionsSet.a().booleanValue()) {
            m(0.1f, q(), "SPAWN");
        }
    }

    private void H() {
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
            str2 = MbzYTTrg.DYGhMxSI;
        }
        this.eventLocation = str.toLowerCase(Locale.ENGLISH);
        this.eventSpawn = Integer.parseInt(str2);
    }

    public final boolean I() {
        return this.spawnConditions.a().booleanValue();
    }

    public final Daycycle J() {
        if (this.cycle == null) {
            this.cycle = Daycycle.f3234b;
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
        this.cycle = lowerCase.equals("night") ? Daycycle.f3236d : lowerCase.equals("day") ? Daycycle.f3235c : Daycycle.f3234b;
    }

    public final void N(String str) {
        Locale locale = Locale.ENGLISH;
        if (str.toLowerCase(locale).equals("ru")) {
            this.preferredFacing = MapActor.Facing.f3299c;
        }
        if (str.toLowerCase(locale).equals("rd")) {
            this.preferredFacing = MapActor.Facing.f3301e;
        }
        if (str.toLowerCase(locale).equals("lu")) {
            this.preferredFacing = MapActor.Facing.f3305i;
        }
        if (str.toLowerCase(locale).equals("ld")) {
            this.preferredFacing = MapActor.Facing.f3303g;
        }
        if (str.toLowerCase(locale).equals("l")) {
            this.preferredFacing = MapActor.Facing.f3304h;
        }
        if (str.toLowerCase(locale).equals(fNqLzGyLIBqug.deNCkwLtyH)) {
            this.preferredFacing = MapActor.Facing.f3302f;
        }
    }

    public final void O(String str) {
        this.unique_tag = str;
    }

    public final void P(int i2) {
        this.wander = i2;
    }

    /* JADX WARN: Removed duplicated region for block: B:42:0x0108  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0158  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void Q() {
        String strG;
        int playerCount;
        NPC next;
        int iJ = DynamicQuest.j(this.questLocation);
        if (this.cycle == null) {
            this.cycle = Daycycle.f3234b;
        }
        if (GameData.v().night && this.cycle == Daycycle.f3235c) {
            return;
        }
        if (!GameData.v().night && this.cycle == Daycycle.f3236d) {
            return;
        }
        if (iJ > 0 && !this.markRespawn) {
            String str = DynamicQuest.e(iJ).quest_ID;
            String str2 = DynamicQuest.e(iJ).variation_ID;
            GameWorld.f3408a.getClass();
            QuestVariation questVariationD = Quests.d(str, str2);
            GameWorld.f3408a.getClass();
            if (!Quests.a(str).h()) {
                GameWorld.f3408a.getClass();
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
                        GameWorld.f3408a.getClass();
                        strG = Quests.a(str).g() ? a.a.g(iJ, "DQ_") : "";
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
                    next.f3307x = this.f3307x;
                    next.f3308y = this.f3308y;
                    next.visibleToPlayer = Boolean.TRUE;
                    next.spriteIndex = null;
                    if (next.getName().equals(gNaSiQJmMEn.AMJcQKbW)) {
                        next.r1("Grissenda");
                    }
                    GameLevel.a(next);
                    next.V1();
                    return;
                }
                if (Rules.i(this.spawn_id) == null) {
                    System.out.println("WARNING: null spawn for id:" + this.spawn_id);
                    Settings.C(1, "GL_spawnerror_" + this.spawn_id);
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
                    this.tag = Integer.toHexString(FDUtils.b(1, 1000));
                }
                npc.tag = this.tag;
                npc.U1(q());
                npc.f3307x = this.f3307x;
                npc.f3308y = this.f3308y;
                GameLevel.a(npc);
                npc.B1();
                MapActor.Facing facing = this.preferredFacing;
                if (facing != null) {
                    npc.facing = facing;
                }
                npc.visibleToPlayer = Boolean.TRUE;
                int i4 = this.spawnLevel;
                if (i4 == 0) {
                    i4 = spawn.minlevel;
                }
                try {
                    LanSessionManager instanceIfReady = LanSessionManager.getInstanceIfReady();
                    if (instanceIfReady != null && instanceIfReady.isInSession() && (playerCount = instanceIfReady.getPlayerCount()) > 0) {
                        i4 += (playerCount - 1) * 2;
                        this.spawnLevel = i4;
                    }
                } catch (Exception e2) {
                }
                if (i4 > 0) {
                    npc.sheet.i0(i4);
                }
                int i5 = this.respawn;
                if (i5 > 0) {
                    npc.S1(i5);
                }
                if (!strG.equals("")) {
                    npc.tag = strG;
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
                        WorldEvents worldEvents = GameWorld.f3414g;
                        String str8 = dynamicEventC.event_id;
                        worldEvents.getClass();
                        String[] strArrSplit = WorldEvents.a(str8).spawn_id2.trim().split(";");
                        if (strArrSplit != null) {
                            for (String str9 : strArrSplit) {
                                if (!str9.trim().equals("")) {
                                    NPC npc2 = new NPC(new Spawn(Rules.i(str9)));
                                    npc2.f3307x = FDUtils.b(-50, 50) + npc.f3307x;
                                    npc2.f3308y = FDUtils.b(-50, 50) + npc.f3308y;
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
                if (Serializer.f3447c && npc.s()) {
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
                WorldEvents worldEvents2 = GameWorld.f3414g;
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
            if (this.faction.equals(Factions.Faction.f3275b)) {
                x(com.google.android.gms.games.quest.Quests.SELECT_COMPLETED_UNCLAIMED);
            }
            if (this.faction.equals(Factions.Faction.f3277d)) {
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
        StringBuilder sb = new StringBuilder();
        sb.append(this.spawn_id);
        String str = fIsXh.JZfGSFQciE;
        sb.append(str);
        sb.append(this.f3307x);
        sb.append(str);
        sb.append(this.f3308y);
        return sb.toString();
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
        l.e("CAUTION : NON-CONSUMED MESSAGE BY MONSTERSPAWN: " + q() + "/" + getName() + " message: " + str + "/" + i2 + "/" + str2);
    }

    @Override // net.fdgames.GameEntities.MapObject
    /* JADX INFO: renamed from: z */
    public final int compareTo(MapObject mapObject) {
        return 0;
    }
}
