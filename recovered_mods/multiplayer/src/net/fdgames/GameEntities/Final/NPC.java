package net.fdgames.GameEntities.Final;

import android.app.cYK.lRWXeT;
import android.support.v4.app.mFy.fApIihhYHIP;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.l;
import com.google.android.datatransport.backend.cct.CSLH.VEZT;
import com.google.android.gms.auth.api.proxy.AuthApiStatusCodes;
import com.google.android.gms.games.GamesStatusCodes;
import com.google.android.gms.games.quest.Quests;
import com.google.android.gms.games.snapshot.hbR.cxRMW;
import com.google.android.gms.location.LocationRequest;
import h0.LN.EXicjtag;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import m0.p;
import n0.WOA.jzidqMPaLNVH;
import net.fdgames.GameEntities.AI.AI;
import net.fdgames.GameEntities.AI.CivilianAI;
import net.fdgames.GameEntities.AI.CompanionAI;
import net.fdgames.GameEntities.AI.IdleAI;
import net.fdgames.GameEntities.AI.Pathfinding.AStarPathFinder;
import net.fdgames.GameEntities.AI.Pathfinding.Path;
import net.fdgames.GameEntities.AI.PatrollerAI;
import net.fdgames.GameEntities.Character;
import net.fdgames.GameEntities.CharacterSheet.AttributesSet;
import net.fdgames.GameEntities.CharacterSheet.CharacterInventory;
import net.fdgames.GameEntities.CharacterSheet.CharacterSheet;
import net.fdgames.GameEntities.CharacterSheet.CharacterStats;
import net.fdgames.GameEntities.Final.MonsterSpawn;
import net.fdgames.GameEntities.Helpers.Damage;
import net.fdgames.GameEntities.Helpers.Factions;
import net.fdgames.GameEntities.Helpers.SkillSet;
import net.fdgames.GameEntities.MapActor;
import net.fdgames.GameEntities.MapSprite;
import net.fdgames.GameLevel.GameLevel;
import net.fdgames.GameLevel.GameLevelData;
import net.fdgames.GameLogic.ActionsSet;
import net.fdgames.GameLogic.ConditionsSet;
import net.fdgames.GameWorld.Follower;
import net.fdgames.GameWorld.GameData;
import net.fdgames.GameWorld.GameLog;
import net.fdgames.GameWorld.GameStats;
import net.fdgames.GameWorld.GameVariables;
import net.fdgames.GameWorld.GameWorld;
import net.fdgames.GameWorld.Party;
import net.fdgames.GameWorld.WorldFactions;
import net.fdgames.Helpers.FDUtils;
import net.fdgames.Helpers.GameString;
import net.fdgames.Rules.Item;
import net.fdgames.Rules.Rules;
import net.fdgames.Rules.SkillActions;
import net.fdgames.Rules.Spawn;
import net.fdgames.Rules.WeaponStats;
import net.fdgames.TiledMap.Objects.Coords;
import net.fdgames.assets.GameAssets;
import net.fdgames.ek.GPGSUpdate;
import net.fdgames.ek.android.lan.LanGameBridge;
import t.Lzz.yorS;
import w0.a;
import y0.b;
import y0.e;
import z0.w;

/* JADX INFO: loaded from: /tmp/tmp.CKdUvKN3M2/classes.dex */
public class NPC extends Character {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final /* synthetic */ int f3238c = 0;
    private AI ai;
    public boolean ai_disabled;
    public String ai_type;
    public boolean alwaysReward;
    public int attackStrategy;
    private boolean companionSpawn;
    private MonsterSpawn.Daycycle cycle;
    public int detectedEnemyID;
    public int dismissTime;
    private boolean droppedloot;
    public String eventSpawnLocation;
    private float growthTime;
    private boolean hasSkills;
    private boolean knowsPlayer;
    public boolean lanPeerVisual;
    private float lastNPCFacingChange;
    public float lastPerceptioncheck;
    private float lastRegenTime;
    public float lastSpotCheck;
    public float lastTick;
    private String lootTable;
    private Coords newTargetCoords;
    private int respawnTime;
    public boolean respawned;
    private Coords spawnPos;
    public String spawn_id;
    private int spawnerID;
    public boolean summoned;
    public String unique_tag;
    public boolean wait;
    public int wanderDistanceMapPoints;

    public NPC() {
        this.attackStrategy = 0;
        this.wait = false;
        this.lastTick = GameLevel.b() - 1.0f;
        this.ai = null;
        this.spawnPos = new Coords();
        this.wanderDistanceMapPoints = -1;
        this.companionSpawn = false;
        this.lanPeerVisual = false;
        this.ai_disabled = false;
        this.lastRegenTime = 0.0f;
        this.newTargetCoords = new Coords();
        this.hasSkills = false;
        this.respawned = false;
        this.alwaysReward = false;
        this.summoned = false;
        this.dismissTime = 0;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00cd  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public NPC(Spawn spawn) {
        int i2;
        AttributesSet attributesSet;
        super(spawn.race, spawn.characterclass, spawn.a(), spawn.spriteName, spawn.size.floatValue(), spawn.speedModifier, WorldFactions.i(spawn.faction), spawn.gender, spawn.portrait, spawn.conversation_ID, spawn.extraSize);
        int iMax = 1;
        this.attackStrategy = 0;
        this.wait = false;
        this.lastTick = GameLevel.b() - 1.0f;
        this.ai = null;
        this.spawnPos = new Coords();
        this.wanderDistanceMapPoints = -1;
        this.companionSpawn = false;
        this.lanPeerVisual = false;
        this.ai_disabled = false;
        this.lastRegenTime = 0.0f;
        this.newTargetCoords = new Coords();
        this.hasSkills = false;
        this.respawned = false;
        this.alwaysReward = false;
        this.summoned = false;
        this.dismissTime = 0;
        this.ai_type = spawn.AI_type;
        this.lootTable = spawn.lootTable;
        this.lastNPCFacingChange = GameLevel.b();
        this.lastSpotCheck = 0.0f;
        int i3 = spawn.wander;
        this.wanderDistanceMapPoints = i3 * 32;
        if (i3 == -1) {
            this.wanderDistanceMapPoints = 400;
        }
        String str = spawn.spawn_ID;
        this.spawn_id = str;
        com.badlogic.gdx.utils.a<TextureRegion> aVar = GameAssets.f3536a;
        Spawn spawnI = Rules.i(str);
        if (spawnI != null && spawnI.onAggroSound.trim() != "") {
            GameAssets.n(spawnI.onAggroSound);
        }
        String str2 = this.spawn_id;
        Locale locale = Locale.ENGLISH;
        String lowerCase = str2.toLowerCase(locale);
        String str3 = jzidqMPaLNVH.BQTTfaFIjZtv;
        if (!lowerCase.equals(str3) && !this.spawn_id.toLowerCase(locale).equals("adaon") && !this.spawn_id.toLowerCase(locale).equals("hirge")) {
            CharacterSheet characterSheet = this.sheet;
            WeaponStats weaponStats = spawn.weaponStats;
            int i4 = spawn.baseArmor;
            int i5 = spawn.minlevel;
            int i6 = spawn.maxlevel;
            if (i5 != i6) {
                int iF = GameData.v().player.sheet.stats.f();
                if (iF > i6) {
                    i2 = i6;
                } else if (iF < i5) {
                    i2 = i5;
                } else if (iF == 1) {
                    i2 = iMax;
                } else if (FDUtils.b(1, 10) <= 5) {
                    i2 = iF;
                } else {
                    iMax = Math.max(Math.min(FDUtils.b(i5, i6), iF + 1), iF - 1);
                    i2 = iMax;
                }
            }
            characterSheet.h0(weaponStats, i4, i2, spawn.resistances, spawn.attributes);
            CharacterSheet characterSheet2 = this.sheet;
            if (characterSheet2 == null || (attributesSet = characterSheet2.attributes) == null || !attributesSet.growth) {
                return;
            }
            this.growthTime = GameData.v().u() + 5.0f;
            return;
        }
        if (this.spawn_id.toLowerCase(locale).equals(str3) && !GameData.v().party.m(str3)) {
            CharacterSheet characterSheet3 = this.sheet;
            CharacterInventory characterInventory = characterSheet3.inventory;
            characterInventory.slot_ring = 2502;
            characterInventory.slot_body = 100;
            characterInventory.slot_offhand = 120;
            characterInventory.slot_mainhand = 522;
            characterSheet3.g();
            this.sheet.g();
            this.sheet.g();
            this.sheet.U(1);
            this.sheet.U(1);
            this.sheet.U(0);
            this.sheet.U(5);
            this.sheet.U(2);
            this.sheet.inventory.s();
            this.sheet.i0(3);
            this.sheet.skillSet.j("shield_expert");
            this.sheet.skillSet.j("shield_expert");
        }
        if (this.spawn_id.toLowerCase(locale).equals("hirge") && !GameData.v().party.m("hirge")) {
            CharacterSheet characterSheet4 = this.sheet;
            CharacterInventory characterInventory2 = characterSheet4.inventory;
            characterInventory2.slot_ring = AuthApiStatusCodes.AUTH_URL_RESOLUTION;
            characterInventory2.slot_body = 110;
            characterInventory2.slot_feet = 113;
            characterInventory2.slot_mainhand = 595;
            characterInventory2.slot_necklace = GamesStatusCodes.STATUS_SNAPSHOT_CONFLICT_MISSING;
            characterSheet4.g();
            this.sheet.g();
            this.sheet.g();
            this.sheet.g();
            this.sheet.g();
            this.sheet.U(1);
            this.sheet.U(0);
            this.sheet.U(0);
            this.sheet.U(5);
            this.sheet.U(5);
            this.sheet.U(5);
            this.sheet.U(2);
            this.sheet.U(3);
            this.sheet.inventory.s();
            this.sheet.i0(6);
            SkillSet skillSet = this.sheet.skillSet;
            String str4 = EXicjtag.TAKLI;
            skillSet.j(str4);
            this.sheet.skillSet.j(str4);
            this.sheet.skillSet.j("crusader");
            this.sheet.skillSet.j("nivarias_barrier");
        }
        if (this.spawn_id.toLowerCase(locale).equals("adaon") && !GameData.v().party.m("adaon")) {
            CharacterSheet characterSheet5 = this.sheet;
            characterSheet5.inventory.slot_mainhand = 205;
            characterSheet5.g();
            this.sheet.g();
            this.sheet.g();
            this.sheet.U(4);
            this.sheet.U(2);
            this.sheet.U(2);
            this.sheet.U(5);
            this.sheet.U(1);
            this.sheet.inventory.s();
            this.sheet.i0(3);
            this.sheet.skillSet.j("stab");
            this.sheet.skillSet.j("stab");
            this.sheet.skillSet.j("gossip");
        }
        this.companionSpawn = true;
    }

    /* JADX WARN: Removed duplicated region for block: B:63:0x0169  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void v0LanPeer() {
        String str;
        String string;
        boolean z2;
        if (this.spriteIndex == null) {
            this.spriteIndex = new com.badlogic.gdx.utils.a<>();
        }
        this.spriteIndex.clear();
        String str2 = this.gender == Character.Gender.f3207b ? "male" : "female";
        CharacterSheet characterSheet = this.sheet;
        CharacterInventory characterInventory = characterSheet.inventory;
        int i2 = characterInventory.slot_body;
        if (((i2 <= 0 || Rules.f(i2).sprite.equals("")) ? "" : Rules.f(characterInventory.slot_body).sprite).equals("")) {
            str = "clothes";
        } else {
            CharacterInventory characterInventory2 = characterSheet.inventory;
            int i3 = characterInventory2.slot_body;
            str = (i3 <= 0 || Rules.f(i3).sprite.equals("")) ? "" : Rules.f(characterInventory2.slot_body).sprite;
        }
        String strX = this.sheet.x();
        String strD = this.sheet.D();
        CharacterSheet characterSheet2 = this.sheet;
        CharacterInventory characterInventory3 = characterSheet2.inventory;
        int i4 = characterInventory3.slot_head;
        if (((i4 <= 0 || Rules.f(i4).sprite.equals("")) ? "" : Rules.f(characterInventory3.slot_head).sprite).equals("")) {
            string = "head";
        } else {
            StringBuilder sb = new StringBuilder("head_");
            CharacterInventory characterInventory4 = characterSheet2.inventory;
            int i5 = characterInventory4.slot_head;
            sb.append((i5 <= 0 || Rules.f(i5).sprite.equals("")) ? "" : Rules.f(characterInventory4.slot_head).sprite);
            string = sb.toString();
        }
        boolean z3 = true;
        if (str.equals("")) {
            int iH = GameAssets.h("composite/" + str2 + "_clothes");
            if (iH != -1) {
                this.spriteIndex.a(Integer.valueOf(iH));
                z2 = false;
            }
            z2 = true;
        } else {
            int iH2 = GameAssets.h("composite/" + str2 + "_" + str);
            if (iH2 != -1) {
                this.spriteIndex.a(Integer.valueOf(iH2));
                z2 = false;
            }
            z2 = true;
        }
        MapActor.ActorState actorStateD0 = d0();
        MapActor.ActorState actorState = MapActor.ActorState.f3290f;
        if (actorStateD0 != actorState && !strD.equals("")) {
            int iH3 = GameAssets.h("composite/" + str2 + "_" + strD);
            if (iH3 != -1) {
                this.spriteIndex.a(Integer.valueOf(iH3));
            } else {
                z2 = true;
            }
        }
        if (string.equals("")) {
            int iH4 = GameAssets.h("composite/" + str2 + "_head");
            if (iH4 != -1) {
                this.spriteIndex.a(Integer.valueOf(iH4));
            } else {
                z2 = true;
            }
        } else {
            int iH5 = GameAssets.h("composite/" + str2 + "_" + string);
            if (iH5 != -1) {
                this.spriteIndex.a(Integer.valueOf(iH5));
            }
        }
        if (d0() == actorState || strX.equals("")) {
            z3 = z2;
        } else {
            int iH6 = GameAssets.h("composite/" + str2 + "_" + strX);
            if (iH6 != -1) {
                this.spriteIndex.a(Integer.valueOf(iH6));
                z3 = z2;
            }
        }
        if (z3) {
            this.spriteIndex.clear();
        }
    }

    @Override // net.fdgames.GameEntities.Character
    public final boolean B0() {
        return this.f3307x == a0().f3508x && this.f3308y == a0().f3509y;
    }

    public final void B1() {
        this.ai = null;
        if (this.ai_type.equals("patroller")) {
            this.ai = new PatrollerAI(q());
        }
        boolean zEquals = this.ai_type.equals("civilian");
        AI.NPCState nPCState = AI.NPCState.f3189b;
        if (zEquals) {
            CivilianAI civilianAI = new CivilianAI(q());
            civilianAI.a(nPCState);
            this.ai = civilianAI;
        }
        if (this.ai_type.equals("idle")) {
            this.ai = new IdleAI(q());
        }
        if (this.ai_type.equals("companion")) {
            CompanionAI companionAI = new CompanionAI(q());
            companionAI.a(nPCState);
            this.ai = companionAI;
        }
    }

    @Override // net.fdgames.GameEntities.MapObject
    public final String C() {
        StringBuilder sb = GameWorld.f3410c.f(r()) ? new StringBuilder("[RED]") : new StringBuilder("[GREEN]");
        sb.append(getName());
        sb.append("[]");
        return sb.toString();
    }

    public final void C1() {
        this.ai_type = "companion";
        x(100);
        B1();
        this.lastTick = GameLevel.b() - 1.0f;
        this.lastSpotCheck = GameLevel.b() - 0.6f;
        if (this.companionSpawn) {
            GameData.v().party.a(this);
            return;
        }
        if (k0()) {
            return;
        }
        e eVar = new e(this.conversationTag, -1, getName(), this.portraitIndex, "", this.tag, "", cxRMW.EJiuaeR);
        Party party = GameData.v().party;
        String str = this.spawn_id;
        if (party.followers == null) {
            party.followers = new ArrayList<>();
        }
        for (Follower follower : party.followers) {
            if (follower.spawn_id.equals(str) && follower.a().tag != null && follower.a().tag.equals(eVar.f4044f) && follower.a().name.equals(eVar.f4041c)) {
                return;
            }
        }
        party.followers.add(new Follower(str, eVar));
    }

    public final void D1(int i2) {
        MapSprite mapSpriteI = GameLevel.i(i2);
        if (mapSpriteI != null) {
            F1(i2, mapSpriteI.B());
        }
    }

    public final void E1() {
        int i2 = this.wanderDistanceMapPoints;
        if (i2 == 0) {
            if (Math.abs(this.f3307x - this.spawnPos.f3508x) <= 5 || Math.abs(this.f3308y - this.spawnPos.f3509y) <= 5) {
                return;
            }
            F1(0, this.spawnPos);
            return;
        }
        Coords coords = this.spawnPos;
        int i3 = coords.f3508x;
        int i4 = coords.f3509y;
        int i5 = 0;
        while (true) {
            int i6 = this.spawnPos.f3508x;
            int iB = FDUtils.b(i6 - i2, i6 + i2);
            int i7 = this.spawnPos.f3509y;
            int iB2 = FDUtils.b(i7 - i2, i7 + i2);
            i5++;
            if (i5 >= 50 || (iB >= 0 && iB <= b.O() && iB2 >= 0 && iB2 <= b.N() && !b.P().i(iB, iB2, 8) && !b.P().g(iB, iB2, 8))) {
                AStarPathFinder aStarPathFinder = GameLevel.f3309a;
                if (b.r(iB, iB2, GameData.v().player.f3307x, GameData.v().player.f3308y) >= 60) {
                    Coords coords2 = this.newTargetCoords;
                    coords2.f3508x = iB;
                    coords2.f3509y = iB2;
                    F1(0, coords2);
                    return;
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x0090  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void F1(int i2, Coords coords) {
        if ((((!k0() && h0()) || b.r(this.f3307x, this.f3308y, coords.f3508x, coords.f3509y) < 64.0f || (k0() && !h0())) && b.P().D(this.f3307x, this.f3308y, coords.f3508x, coords.f3509y, q(), i2)) || (k0() && h0() && b.r(this.f3307x, this.f3308y, coords.f3508x, coords.f3509y) < 54.4f)) {
            if (this.path == null) {
                this.path = new Path();
            }
            this.path.c();
            this.path.b(coords.f3508x, coords.f3509y);
        } else if (!this.stuck) {
            int i3 = coords.f3508x;
            int i4 = coords.f3509y;
            Path path = this.path;
            if (path == null || path.e() == 0 || b.r(this.path.f(0).f3204x, this.path.f(0).f3205y, i3, i4) > 32) {
                this.path = null;
                Path pathA = GameLevel.f3309a.a(this.f3307x, this.f3308y, coords.f3508x, coords.f3509y);
                this.path = pathA;
                this.path = H0(pathA);
            }
        }
        Path path2 = this.path;
        if (path2 == null || path2.e() == 0) {
            C0();
            q0(MapActor.ActorState.f3286b);
            this.ai.state = AI.NPCState.f3189b;
            this.detectedEnemyID = 0;
        }
    }

    public final void G1() {
        if (d0() == MapActor.ActorState.f3289e) {
            return;
        }
        this.ai_type = yorS.yGtwU;
        x(LocationRequest.PRIORITY_LOW_POWER);
        this.ai = new PatrollerAI(q());
        this.lastTick = GameLevel.b() - 1.0f;
        this.lastSpotCheck = GameLevel.b() - 0.6f;
    }

    /* JADX WARN: Removed duplicated region for block: B:52:0x01e4  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void H1() {
        if (GameData.v().u() < this.lastPerceptioncheck + 1.0f) {
            return;
        }
        this.lastPerceptioncheck = GameData.v().u();
        AI ai = this.ai;
        if (ai == null || ai.state != AI.NPCState.f3189b) {
            return;
        }
        GameLevel.k();
        int i2 = 0;
        if (!Player.L1(r())) {
            int i3 = this.attackStrategy;
            if (i3 != 3 && i3 != 2) {
                i2 = i3 == 1 ? 3 : this.sheet.Y() ? 8 : 6;
            }
            this.detectedEnemyID = b.P().d(B(), r(), i2);
            return;
        }
        MapActor mapActorResolveHostileTargetActor = LanGameBridge.resolveHostileTargetActor(this);
        Character character = null;
        int iQ = 0;
        if (mapActorResolveHostileTargetActor != null) {
            character = (Character) mapActorResolveHostileTargetActor;
            iQ = mapActorResolveHostileTargetActor.q();
        }
        int iD = b.P().d(B(), r(), 8);
        if (iD != 1) {
            this.detectedEnemyID = iD;
        } else if (mapActorResolveHostileTargetActor == null || mapActorResolveHostileTargetActor.d0() == MapActor.ActorState.f3289e || ((!this.knowsPlayer && b.s(B(), mapActorResolveHostileTargetActor.B()) > 208.0f) || b.s(B(), mapActorResolveHostileTargetActor.B()) > 272.0f)) {
            this.detectedEnemyID = 0;
        } else if ((!character.sheet.effects.stealth.booleanValue() || this.sheet.P("detector")) && b.P().z(B(), mapActorResolveHostileTargetActor.B())) {
            character.sheet.effects.stealth = Boolean.FALSE;
            w0.a.l().a(new w(q(), "!!!", 1.0f, Color.RED, 1.0f, 1.0f));
            this.detectedEnemyID = iQ;
        } else if (!character.sheet.effects.stealth.booleanValue() || this.lastSpotCheck + 0.6f >= GameData.v().u() || b.p(B(), mapActorResolveHostileTargetActor.B()) > 100.0d || !b.P().z(B(), mapActorResolveHostileTargetActor.B())) {
            this.detectedEnemyID = 0;
        } else {
            this.lastSpotCheck = GameData.v().u();
            Character character2 = character;
            int iMax = ((Math.max(0, this.sheet.stats.f() - character2.sheet.stats.f()) * 5) + (65 - (((int) b.p(B(), character2.B())) / 2))) / Math.max(character.sheet.skillSet.g("stealth") + 1, 2);
            boolean z2 = FDUtils.b(1, 100) <= iMax;
            GameLog gameLog = GameData.v().log;
            String name = getName();
            gameLog.getClass();
            gameLog.a(name + " " + GameString.b("SPOT_CHECK", false) + "(" + iMax + "%)" + (z2 ? a.a.o("SPOTTED", false, new StringBuilder(". [RED]"), "[]") : ""));
            if (z2) {
                character.sheet.effects.stealth = Boolean.FALSE;
                w0.a.l().a(new w(q(), "!!!", 1.0f, Color.RED, 1.0f, 1.0f));
                this.detectedEnemyID = iQ;
            }
        }
        int i4 = this.detectedEnemyID;
        if (iQ <= 0 || i4 != iQ) {
            return;
        }
        character.sheet.effects.stealth = Boolean.FALSE;
    }

    public final void I1() {
        if (this.ai == null || Math.abs(this.f3307x - this.waypointDestination.f3508x) >= 32 || Math.abs(this.f3308y - this.waypointDestination.f3509y) >= 32) {
            return;
        }
        this.waypointDestination = null;
    }

    public final void J1() {
        w0.a.l().a(new w(q(), GameString.b("DISMISSED", false), 1.2f, Color.BLUE, 0.8f, 0.8f));
        GameData.v().party.e();
        this.destroy = true;
        w0.a aVarL = w0.a.l();
        a.EnumC0056a enumC0056a = a.EnumC0056a.f3897p;
        aVarL.getClass();
        aVarL.b(B(), enumC0056a, 1.4f).owner = this;
    }

    public final MonsterSpawn.Daycycle K1() {
        if (this.cycle == null) {
            this.cycle = MonsterSpawn.Daycycle.f3234b;
        }
        return this.cycle;
    }

    @Override // net.fdgames.GameEntities.Character
    public final boolean L0(Character character) {
        return (character.d0() == MapActor.ActorState.f3293i || character.d0() == MapActor.ActorState.f3292h || (this.ai.state != AI.NPCState.f3189b && !b.Z(character, this))) ? false : true;
    }

    public final int L1() {
        return this.spawnerID;
    }

    @Override // net.fdgames.GameEntities.Character, net.fdgames.GameEntities.MapActor, net.fdgames.GameEntities.MapSprite
    public final void M(float f2) {
        if (this.ai == null) {
            B1();
        }
        if (d0() == MapActor.ActorState.f3286b && this.sheet.o() == 0 && k0()) {
            q0(MapActor.ActorState.f3289e);
            o(lRWXeT.rRwCbIWNk, q(), null, 1.0f, null);
        }
        if (i0()) {
            this.ai.a(AI.NPCState.f3192e);
            super.M(f2);
            return;
        }
        if (this.dismissTime > 0 && GameLevel.b() > this.dismissTime) {
            J1();
            return;
        }
        if (this.growthTime > 0.0f && GameLevel.b() > this.growthTime) {
            if (this.sheet.stats.f() >= 99) {
                this.growthTime = 0.0f;
                return;
            }
            CharacterSheet characterSheet = this.sheet;
            characterSheet.i0(characterSheet.stats.f() + 1);
            T0();
            this.growthTime = GameData.v().u() + 4.0f;
            return;
        }
        AI.NPCState nPCState = this.ai.state;
        AI.NPCState nPCState2 = AI.NPCState.f3189b;
        if (nPCState == nPCState2 && this.stuck) {
            C0();
        }
        Coords coordsB = B();
        AStarPathFinder aStarPathFinder = GameLevel.f3309a;
        if (b.s(coordsB, GameData.v().player.B()) < 640) {
            if (this.lastTick > GameLevel.b()) {
                this.lastTick = GameLevel.b();
                l.e("warning, AI lasttick was later thn current game time");
            }
            if (!j0() && d0() != MapActor.ActorState.f3290f && GameLevel.b() - this.lastTick >= 0.3f) {
                if (this.stuck && d0() == MapActor.ActorState.f3287c && !k0()) {
                    int i2 = this.timesStuck;
                    if (i2 > 5) {
                        CharacterSheet characterSheet2 = this.sheet;
                        CharacterStats characterStats = characterSheet2.stats;
                        int iZ = characterStats.missingHP - (characterSheet2.z() / 10);
                        characterStats.missingHP = iZ;
                        if (iZ < 0) {
                            characterStats.missingHP = 0;
                        }
                        this.lastRegenTime = GameData.v().u();
                    } else {
                        this.timesStuck = i2 + 1;
                    }
                }
                if (this.ai.state == nPCState2 && !k0() && this.sheet.stats.missingHP > 0 && GameData.v().u() > this.lastRegenTime + 0.8f && !Player.f3242f) {
                    CharacterSheet characterSheet3 = this.sheet;
                    CharacterStats characterStats2 = characterSheet3.stats;
                    int iZ2 = characterStats2.missingHP - (characterSheet3.z() / 10);
                    characterStats2.missingHP = iZ2;
                    if (iZ2 < 0) {
                        characterStats2.missingHP = 0;
                    }
                    this.lastRegenTime = GameData.v().u();
                }
                t0();
                if (this.ai_disabled && !M1()) {
                    if (this.lanPeerVisual) {
                        super.M(f2);
                        return;
                    }
                    return;
                }
                this.ai.c(this.uniqueID);
                this.lastTick = GameLevel.b();
            }
            super.M(f2);
        }
    }

    public final boolean M1() {
        AI ai = this.ai;
        return ai != null && (ai instanceof CompanionAI);
    }

    public final boolean N1() {
        if (this.companionSpawn) {
            return true;
        }
        return this.hasSkills;
    }

    @Override // net.fdgames.GameEntities.Character
    public final void O0(int i2) {
        CharacterStats characterStats = this.sheet.stats;
        int i3 = characterStats.i();
        characterStats.a(i2);
        int i4 = characterStats.i() - i3;
        if (GameData.v().party.f().q() == q()) {
            w0.a.l().a(new w(q(), "" + Integer.toString(i4) + "xp", 1.3f, Color.YELLOW, 0.7f, 0.7f));
        }
    }

    public final void O1() {
        Coords coords = this.spawnPos;
        coords.f3508x = this.f3307x;
        coords.f3509y = this.f3308y;
        this.wanderDistanceMapPoints = VertexAttributes.Usage.Tangent;
        E1();
        this.ai_disabled = true;
        m(4.0f, q(), "ENABLE_AI");
    }

    @Override // net.fdgames.GameEntities.Character
    public final float P0() {
        AI ai = this.ai;
        if (ai == null || ai.state != AI.NPCState.f3189b) {
            return 1.0f;
        }
        ai.getClass();
        return !(ai instanceof CompanionAI) ? 0.5f : 1.0f;
    }

    public final void P1() {
        AI ai = this.ai;
        if (ai != null && (ai instanceof PatrollerAI) && ai.b()) {
            this.ai.a(AI.NPCState.f3189b);
            this.detectedEnemyID = 0;
            this.knowsPlayer = false;
        }
    }

    @Override // net.fdgames.GameEntities.MapActor
    public final com.badlogic.gdx.utils.a<TextureRegion> Q() {
        com.badlogic.gdx.utils.a<Integer> aVar = this.spriteIndex;
        if (aVar == null || aVar.f2517c == 0) {
            v0();
        }
        GameAssets.f3536a.clear();
        int i2 = 0;
        while (true) {
            com.badlogic.gdx.utils.a<Integer> aVar2 = this.spriteIndex;
            if (i2 >= aVar2.f2517c) {
                break;
            }
            GameAssets.f3536a.a((TextureRegion) GameAssets.i(aVar2.get(i2).intValue()).a(d0(), this.facing).getKeyFrame(V()));
            i2++;
        }
        if (this.lanPeerVisual) {
            MapActor.ActorState actorStateD0 = d0();
            if (actorStateD0.equals(MapActor.ActorState.f3288d) || actorStateD0.equals(MapActor.ActorState.f3293i)) {
                GameAssets.f3536a.a((TextureRegion) GameAssets.f3577v.b(this.facing).getKeyFrame(V()));
            }
        }
        D0();
        return GameAssets.f3536a;
    }

    @Override // net.fdgames.GameEntities.Character
    protected final float Q0() {
        return this.sheet.Y() ? 0.375f : 0.05f;
    }

    public final void Q1() {
        this.ai = null;
        this.lastTick = 0.0f;
        this.lastTargetHit_id = 0;
        this.detectedEnemyID = 0;
    }

    @Override // net.fdgames.GameEntities.Character
    public final void R0(Damage damage, int i2, boolean z2, int i3) {
        super.R0(damage, i2, z2, i3);
        if (!i0() && !j0() && i2 > 0 && i2 != q() && GameLevel.g(i2) != null && this.attackStrategy != 3) {
            this.detectedEnemyID = i2;
        }
        if (i2 == 1) {
            this.knowsPlayer = true;
        }
        this.sheet.d();
    }

    public final void R1(MonsterSpawn.Daycycle daycycle) {
        this.cycle = daycycle;
    }

    @Override // net.fdgames.GameEntities.MapActor
    public final p S(boolean z2) {
        if (i0()) {
            return null;
        }
        return T(this.f3307x, this.f3308y, z2);
    }

    public final void S1(int i2) {
        this.respawnTime = i2;
    }

    public final void T1(String str) {
        int i2;
        int i3;
        SkillSet skillSet = this.sheet.skillSet;
        skillSet.getClass();
        Iterator it = FDUtils.s(str).iterator();
        while (it.hasNext()) {
            String[] strArrSplit = ((String) it.next()).split("#", -1);
            String str2 = strArrSplit[0];
            String str3 = strArrSplit[1];
            String str4 = fApIihhYHIP.oTgNGvAY;
            if (str3.contains(str4)) {
                String[] strArrSplit2 = strArrSplit[1].split(str4, -1);
                int i4 = Integer.parseInt(strArrSplit2[0]);
                i2 = Integer.parseInt(strArrSplit2[1]);
                i3 = i4;
            } else {
                i3 = Integer.parseInt(strArrSplit[1]);
                i2 = i3;
            }
            int iB = FDUtils.b(i3, i2);
            while (skillSet.g(str2) < iB && skillSet.g(str2) < 3) {
                skillSet.j(str2);
            }
        }
        skillSet.n();
        this.hasSkills = true;
    }

    @Override // net.fdgames.GameEntities.MapActor
    public final void U(int i2) {
        MapActor mapActorG;
        if (this.ai_disabled || d0() == MapActor.ActorState.f3289e || i2 == 0 || i2 == q() || this.attackStrategy == 3) {
            return;
        }
        if (this.ai == null) {
            B1();
        }
        if (h0() || this.ai_type.equals("civilian")) {
            return;
        }
        int i3 = this.lastTargetHit_id;
        if (i3 > 0 && i3 != i2 && ((mapActorG = GameLevel.g(i3)) == null || mapActorG.i0() || mapActorG.j0())) {
            this.lastTargetHit_id = 0;
        }
        GameAssets.p(Rules.i(this.spawn_id).onAggroSound);
        if (this.detectedEnemyID == 0) {
            this.detectedEnemyID = i2;
        }
        this.ai.a(AI.NPCState.f3190c);
        int size = GameLevel.f3311c.size();
        for (int i4 = 0; i4 < size; i4++) {
            ArrayList<MapActor> arrayList = GameLevel.f3311c;
            if (b.s(arrayList.get(i4).B(), B()) < 96 && t(arrayList.get(i4).r()) && arrayList.get(i4).q() != q()) {
                arrayList.get(i4).U(i2);
            }
        }
    }

    public final void U1(int i2) {
        this.spawnerID = i2;
        if (i2 > 0) {
            this.spawnPos = GameLevel.h(i2).B();
        } else {
            this.spawnPos = B();
        }
    }

    public final void V1() {
        this.ai_type = Rules.i(this.spawn_id).AI_type;
        y(WorldFactions.i(Rules.i(this.spawn_id).faction));
        B1();
        this.lastTick = GameLevel.b() - 1.0f;
        this.lastSpotCheck = GameLevel.b() - 0.6f;
        GameData.v().party.b(this.spawn_id, this.tag, getName());
        Coords coords = this.spawnPos;
        coords.f3508x = this.f3307x;
        coords.f3509y = this.f3308y;
    }

    public final void W1() {
        int[] iArr = this.worldfactions;
        if (iArr == null || iArr[0] == 0) {
            y(WorldFactions.i(Rules.i(this.spawn_id).faction));
        }
        int[] iArr2 = this.worldfactions;
        if (iArr2 == null || iArr2[0] == 0) {
            if (this.faction.equals(Factions.Faction.f3275b)) {
                x(Quests.SELECT_COMPLETED_UNCLAIMED);
            }
            if (this.faction.equals(Factions.Faction.f3276c)) {
                x(LocationRequest.PRIORITY_NO_POWER);
            }
            if (this.faction.equals(Factions.Faction.f3277d)) {
                x(100);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:216:0x037b  */
    /* JADX WARN: Removed duplicated region for block: B:227:0x03b7  */
    /* JADX WARN: Removed duplicated region for block: B:237:0x03ee  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x016a A[PHI: r2
      0x016a: PHI (r2v66 int) = (r2v50 int), (r2v65 int) binds: [B:73:0x0168, B:128:0x01f0] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // net.fdgames.GameEntities.MapActor
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void X() {
        int i2;
        int i3;
        int iG;
        int i4;
        int i5;
        int i6;
        int i7;
        GameData gameDataV;
        String strAsString;
        GameVariables gameVariables;
        boolean zEquals = this.ai_type.equals("civilian");
        if (this.ai == null) {
            B1();
        }
        boolean z2 = this.sheet.P("regeneration") && !this.receivedDamage.b();
        boolean zP = this.sheet.P("immortal");
        if (z2) {
            GameData.v().log.a(C() + VEZT.VePYFFtN + GameString.b("REGENERATES", false));
        }
        boolean z3 = this.companionSpawn;
        AI.NPCState nPCState = AI.NPCState.f3192e;
        if (z3 || z2 || zP) {
            q0(MapActor.ActorState.f3294j);
            this.ai.a(nPCState);
            m(8.0f, q(), "RECOVER");
            return;
        }
        if (k0()) {
            GameData.v().party.b(this.spawn_id, this.tag, getName());
        }
        this.ai.a(nPCState);
        this.conversationTag = "";
        if (!this.killedByNPC || this.alwaysReward) {
            int i8 = 32;
            if (!this.respawned && !this.droppedloot && !this.lootTable.equals("")) {
                this.droppedloot = true;
                String[] strArrSplit = this.lootTable.split(";");
                String str = this.eventSpawnLocation;
                if (str != null && !str.equals("")) {
                    GameData.v().U(this.eventSpawnLocation);
                }
                int length = strArrSplit.length;
                int i9 = 0;
                while (i9 < length) {
                    String str2 = strArrSplit[i9];
                    ArrayList<Integer> arrayListE = Rules.e(str2);
                    int iD = Rules.d(str2);
                    if ((arrayListE.size() > 0) | (iD > 0)) {
                        int i10 = this.f3307x + i8;
                        int i11 = this.f3308y + i8;
                        AStarPathFinder aStarPathFinder = GameLevel.f3309a;
                        GameLevelData.d(new Loot(i10, i11, arrayListE, iD));
                    }
                    i9++;
                    i8 = 32;
                }
            }
            if (GameWorld.f3410c.f(r())) {
                if (this.sheet.stats.f() > 10) {
                    int iF = this.sheet.stats.f() - 10;
                    i6 = this.sheet.stats.characterRace.equals(Rules.CharacterRace.f3494i) ? iF * 50 : 0;
                    if (this.sheet.stats.characterRace.equals(Rules.CharacterRace.f3495j)) {
                        i6 += iF * 100;
                    }
                } else {
                    i6 = 0;
                }
                int iF2 = this.sheet.stats.f();
                Item[] itemArr = Rules.f3468a;
                if (iF2 == 1) {
                    i7 = 7;
                } else if (iF2 == 13) {
                    i7 = 160;
                } else if (iF2 == 2) {
                    i7 = 10;
                } else {
                    int i12 = 14;
                    if (iF2 == 14) {
                        i7 = 200;
                    } else if (iF2 != 3) {
                        if (iF2 == 15) {
                            i7 = 250;
                        } else if (iF2 == 4) {
                            i7 = 19;
                        } else if (iF2 == 16) {
                            i7 = 310;
                        } else if (iF2 == 5) {
                            i7 = 25;
                        } else if (iF2 == 17) {
                            i7 = 380;
                        } else if (iF2 == 6) {
                            i7 = 32;
                        } else if (iF2 == 18) {
                            i7 = 460;
                        } else if (iF2 == 7) {
                            i7 = 40;
                        } else if (iF2 == 19) {
                            i7 = 550;
                        } else if (iF2 == 8) {
                            i7 = 52;
                        } else if (iF2 == 20) {
                            i7 = 650;
                        } else if (iF2 == 9) {
                            i7 = 67;
                        } else if (iF2 == 21) {
                            i7 = 800;
                        } else if (iF2 == 10) {
                            i7 = 85;
                        } else if (iF2 == 22) {
                            i7 = 1000;
                        } else if (iF2 == 11) {
                            i7 = 107;
                        } else if (iF2 == 23) {
                            i7 = 1250;
                        } else if (iF2 == 12) {
                            i7 = 131;
                        } else if (iF2 == 24) {
                            i7 = 1500;
                        } else {
                            i12 = 1750;
                            i7 = iF2 == 25 ? i12 : iF2 > 25 ? ((iF2 - 25) * 250) + 1750 : 5;
                        }
                    }
                }
                int i13 = i7 + i6;
                if (this.respawned) {
                    i13 /= 10;
                }
                AStarPathFinder aStarPathFinder2 = GameLevel.f3309a;
                GameData.v().player.O0(i13);
                GameStats gameStatsY = GameData.v().y();
                AttributesSet attributesSet = this.sheet.attributes;
                gameStatsY.killed++;
                if (attributesSet != null) {
                    if (attributesSet.beast) {
                        gameStatsY.killed_beasts++;
                    }
                    if (attributesSet.goblin) {
                        gameStatsY.killed_goblins++;
                    }
                    if (attributesSet.orc) {
                        gameStatsY.killed_orcs++;
                    }
                    if (attributesSet.undead) {
                        gameStatsY.killed_undead++;
                    }
                    if (attributesSet.outsider) {
                        gameStatsY.killed_outsiders++;
                    }
                    if (attributesSet.aberration) {
                        gameStatsY.killed_aberrations++;
                    }
                }
                if (GameData.v().y().killed % 100 == 0 || GameData.v().y().killed == 20) {
                    GPGSUpdate.c(false);
                }
            }
            if (!this.respawned) {
                AttributesSet attributesSet2 = this.sheet.attributes;
                if ((attributesSet2.undead || attributesSet2.outsider) && GameWorld.f3410c.f(r())) {
                    AStarPathFinder aStarPathFinder3 = GameLevel.f3309a;
                    if (GameData.v().player.sheet.stats.missingMana >= 0) {
                        int iG2 = GameData.v().player.sheet.skillSet.g("retribution");
                        int i14 = iG2 == 1 ? 1 : 0;
                        if (iG2 == 2) {
                            i5 = 3;
                            i14 = 2;
                        } else {
                            i5 = 3;
                        }
                        if (iG2 == i5) {
                            i14 = 3;
                        }
                        if (i14 > 0) {
                            GameData.v().player.o1(i14);
                        }
                    }
                    if (GameData.v().party.j() && GameData.v().party.f() != null && (iG = GameData.v().party.f().sheet.skillSet.g("retribution")) != 0) {
                        int i15 = iG == 1 ? 1 : 0;
                        if (iG == 2) {
                            i4 = 3;
                            i15 = 2;
                        } else {
                            i4 = 3;
                        }
                        if (iG == i4) {
                            i15 = 3;
                        }
                        if (i15 > 0) {
                            GameData.v().party.f().o1(i15);
                        }
                    }
                }
            }
            if (this.respawned || !GameWorld.f3410c.f(r())) {
                i2 = 2;
                for (int i16 = 0; i16 < r().length; i16++) {
                    if (r()[i16] > 0 && r()[i16] < 100) {
                        int iE = GameWorld.f3410c.b(r()[i16]).e();
                        String str3 = GameWorld.f3410c.b(r()[i16]).id;
                        if (iE > -100) {
                            int i17 = zEquals ? 25 : i2;
                            if (iE - i17 < -100) {
                                i17 = -((-100) - iE);
                            }
                            GameWorld.f3410c.a(-i17, 999, str3);
                        }
                    }
                }
            } else {
                CharacterSheet characterSheet = this.sheet;
                AStarPathFinder aStarPathFinder4 = GameLevel.f3309a;
                if (GameData.v().player.sheet.stats.c() == Rules.CharacterClass.f3482e && GameData.v().player.sheet.N() != null && !GameData.v().player.sheet.N().ranged && GameData.v().player.sheet.N().has_secondary_damage && GameData.v().player.sheet.N().secondary_damageType == Damage.DamageType.f3265f) {
                    int iG3 = GameData.v().player.sheet.skillSet.g("vampiric_blade");
                    if (iG3 == 1) {
                        GameData.v().player.sheet.R(5);
                    }
                    i2 = 2;
                    if (iG3 == 2) {
                        GameData.v().player.sheet.R(5);
                        if (characterSheet.V() || characterSheet.l().outsider) {
                            i3 = 3;
                            GameData.v().player.o1(3);
                        } else {
                            i3 = 3;
                        }
                        if (iG3 == i3) {
                            GameData.v().player.sheet.R(10);
                            if (characterSheet.V() || characterSheet.l().outsider) {
                                GameData.v().player.o1(5);
                            }
                        }
                    }
                }
                while (i16 < r().length) {
                }
            }
        }
        int i18 = this.respawnTime;
        if (i18 > 0) {
            m(i18, this.spawnerID, "RESPAWN");
        }
        String str4 = this.spawn_id;
        if (str4 != null && !str4.equals("") && new ConditionsSet(Rules.i(this.spawn_id).onDieConditions).a().booleanValue()) {
            new ActionsSet(Rules.i(this.spawn_id).onDieActions).a();
        }
        String str5 = this.tag;
        if (str5 != null && str5.contains("DQ")) {
            GameData.v().gameVariables.e(50, this.tag);
        }
        String str6 = this.eventSpawnLocation;
        if (str6 != null && !str6.equals("")) {
            GameData.v().O(this.eventSpawnLocation);
            AStarPathFinder aStarPathFinder5 = GameLevel.f3309a;
            GameData.v().player.O0(this.sheet.stats.f() * 100);
        }
        String str7 = this.unique_tag;
        if (str7 != null && !str7.equals("")) {
            GameData gameDataV2 = GameData.v();
            String str8 = this.unique_tag;
            if (gameDataV2.deadNPCs == null) {
                gameDataV2.deadNPCs = new ArrayList<>();
            }
            gameDataV2.deadNPCs.add(str8);
        }
        super.X();
        if (!this.lanPeerVisual || (gameDataV = GameData.v()) == null || (strAsString = LanGameBridge.asString(LanGameBridge.getFieldValue(gameDataV, "currentMapName"))) == null || !strAsString.equals("H10_pvp_arena")) {
            return;
        }
        this.destroy = true;
        ArrayList<MapActor> arrayListE2 = GameLevel.e();
        if (arrayListE2 != null) {
            int i19 = 0;
            for (MapActor mapActor : arrayListE2) {
                if (mapActor != this && (mapActor instanceof NPC)) {
                    NPC npc = (NPC) mapActor;
                    if (npc.lanPeerVisual && npc.d0() != MapActor.ActorState.f3294j && !npc.destroy) {
                        i19++;
                    }
                }
            }
            if (i19 != 0 || (gameVariables = gameDataV.gameVariables) == null) {
                return;
            }
            gameVariables.e(1, "pvp_arena_won");
        }
    }

    @Override // net.fdgames.GameEntities.Character
    public final boolean X0() {
        return (GameWorld.f3410c.f(r()) || k0()) ? false : true;
    }

    @Override // net.fdgames.GameEntities.MapActor
    public final int Z() {
        return 14;
    }

    @Override // net.fdgames.GameEntities.MapActor
    public final int b0() {
        return 8;
    }

    @Override // net.fdgames.GameEntities.MapActor
    public final boolean e0() {
        return this.sheet.P("undead");
    }

    @Override // net.fdgames.GameEntities.MapActor
    public final boolean h0() {
        AI ai = this.ai;
        return ai != null && ai.state == AI.NPCState.f3190c;
    }

    @Override // net.fdgames.GameEntities.Character
    public final boolean i1() {
        return this.sheet.l().shadow;
    }

    @Override // net.fdgames.GameEntities.MapActor
    public final boolean k0() {
        AI ai = this.ai;
        if (ai == null) {
            return false;
        }
        ai.getClass();
        return (ai instanceof CompanionAI) && GameData.v().party.p(this.spawn_id, this.tag);
    }

    @Override // net.fdgames.GameEntities.MapActor
    public final boolean m0() {
        if (this.ai_type.equals("civilian")) {
            return false;
        }
        return super.m0();
    }

    @Override // net.fdgames.GameEntities.MapActor
    public final void o0(Coords coords) {
        this.spawnPos = coords;
    }

    @Override // net.fdgames.GameEntities.MapActor
    public final void r0(Coords coords) {
        this.waypointDestination = coords;
    }

    @Override // net.fdgames.GameEntities.Character, net.fdgames.GameEntities.GameObject
    public final void u(int i2, String str, String str2) {
        if (this.ai == null) {
            B1();
        }
        boolean zI0 = i0();
        AI.NPCState nPCState = AI.NPCState.f3189b;
        if (zI0) {
            boolean zEquals = str.equals("RECOVER");
            MapActor.ActorState actorState = MapActor.ActorState.f3286b;
            if (zEquals) {
                AI ai = this.ai;
                ai.getClass();
                if (ai instanceof CompanionAI) {
                    b bVarP = b.P();
                    Coords coordsB = B();
                    MapActor mapActorG = GameLevel.g(bVarP.d(coordsB, r(), 8));
                    if (!((mapActorG == null || b.p(mapActorG.B(), coordsB) >= 192.0d) ? Boolean.FALSE : Boolean.TRUE).booleanValue()) {
                        b bVarP2 = b.P();
                        Coords coordsB2 = GameData.v().player.B();
                        MapActor mapActorG2 = GameLevel.g(bVarP2.d(coordsB2, r(), 8));
                        if (!((mapActorG2 == null || b.p(mapActorG2.B(), coordsB2) >= 192.0d) ? Boolean.FALSE : Boolean.TRUE).booleanValue()) {
                            q0(actorState);
                            this.ai.a(nPCState);
                            t0();
                            CharacterSheet characterSheet = this.sheet;
                            if (characterSheet.stats.missingHP > 0) {
                                int iZ = characterSheet.z();
                                CharacterSheet characterSheet2 = this.sheet;
                                int i3 = iZ / 2;
                                characterSheet2.stats.missingHP = i3;
                                if (i3 > characterSheet2.z()) {
                                    characterSheet2.stats.missingHP = (int) (characterSheet2.z() * 0.9f);
                                }
                            }
                            SkillActions.d(this);
                            return;
                        }
                    }
                    m(5.0f, q(), "RECOVER");
                    return;
                }
            }
            if (str.equals("RECOVER")) {
                q0(actorState);
                this.ai.a(nPCState);
                t0();
                CharacterSheet characterSheet3 = this.sheet;
                characterSheet3.R(characterSheet3.z());
                SkillActions.d(this);
                w0.a.l().a(new w(q(), GameString.b("REGENERATION", false), 1.2f, Color.RED, 0.8f, 0.8f));
            }
        } else if (str.equals("ENABLE_AI")) {
            this.ai_disabled = false;
            return;
        } else if (str.equals("RANDOM_MOVE")) {
            if (k0() || this.ai.state != nPCState) {
                return;
            }
            E1();
            m(FDUtils.b(3, 10), q(), "RANDOM_MOVE");
            return;
        }
        super.u(i2, str, str2);
    }

    @Override // net.fdgames.GameEntities.MapActor
    public final void u0(float f2, float f3) {
        float fB = GameLevel.b();
        float f4 = this.lastNPCFacingChange;
        if (fB - f4 > 0.2f || f4 > GameLevel.b()) {
            MapActor.Facing facing = this.facing;
            super.u0(f2, f3);
            if (this.facing != facing) {
                this.lastNPCFacingChange = GameLevel.b();
            }
        }
    }

    @Override // net.fdgames.GameEntities.MapActor
    public final void v0() {
        String str;
        boolean z2;
        if (!this.companionSpawn) {
            boolean z3 = this.lanPeerVisual;
            if (z3) {
                v0LanPeer();
                return;
            }
            if (!z3) {
                if (this.spriteIndex == null) {
                    this.spriteIndex = new com.badlogic.gdx.utils.a<>();
                }
                this.spriteIndex.clear();
                Iterator<String> it = this.animationSetName.iterator();
                while (it.hasNext()) {
                    int iH = GameAssets.h(it.next());
                    if (iH != -1) {
                        this.spriteIndex.a(Integer.valueOf(iH));
                    }
                }
                return;
            }
        }
        if (this.spriteIndex == null) {
            this.spriteIndex = new com.badlogic.gdx.utils.a<>();
        }
        this.spriteIndex.clear();
        String str2 = this.gender == Character.Gender.f3207b ? "male" : "female";
        CharacterSheet characterSheet = this.sheet;
        CharacterInventory characterInventory = characterSheet.inventory;
        int i2 = characterInventory.slot_body;
        if (((i2 <= 0 || Rules.f(i2).sprite.equals("")) ? "" : Rules.f(characterInventory.slot_body).sprite).equals("")) {
            str = "clothes";
        } else {
            CharacterInventory characterInventory2 = characterSheet.inventory;
            int i3 = characterInventory2.slot_body;
            str = (i3 <= 0 || Rules.f(i3).sprite.equals("")) ? "" : Rules.f(characterInventory2.slot_body).sprite;
        }
        String strX = this.sheet.x();
        String strD = this.sheet.D();
        boolean zEquals = str.equals("");
        boolean z4 = true;
        String str3 = lRWXeT.AiGPnSUnqMVvaz;
        if (zEquals) {
            int iH2 = GameAssets.h(str3 + str2 + "_clothes");
            if (iH2 != -1) {
                this.spriteIndex.a(Integer.valueOf(iH2));
                z2 = false;
            }
            z2 = true;
        } else {
            int iH3 = GameAssets.h(str3 + str2 + "_" + str);
            if (iH3 != -1) {
                this.spriteIndex.a(Integer.valueOf(iH3));
                z2 = false;
            }
            z2 = true;
        }
        MapActor.ActorState actorStateD0 = d0();
        MapActor.ActorState actorState = MapActor.ActorState.f3290f;
        if (actorStateD0 != actorState && !strD.equals("")) {
            int iH4 = GameAssets.h(str3 + str2 + "_" + strD);
            if (iH4 != -1) {
                this.spriteIndex.a(Integer.valueOf(iH4));
            } else {
                z2 = true;
            }
        }
        String strL = (!this.lanPeerVisual && this.spawn_id.equals("grissenda")) ? a.a.l(str3, str2, "_head3") : "";
        if (this.spawn_id.equals("hirge")) {
            strL = a.a.l(str3, str2, "_head2");
        }
        if (this.spawn_id.equals("adaon")) {
            strL = a.a.l(str3, str2, "_head_leather");
        }
        int iH5 = GameAssets.h(strL);
        if (iH5 != -1) {
            this.spriteIndex.a(Integer.valueOf(iH5));
        } else {
            z2 = true;
        }
        if (d0() == actorState || strX.equals("")) {
            z4 = z2;
        } else {
            int iH6 = GameAssets.h(str3 + str2 + "_" + strX);
            if (iH6 != -1) {
                this.spriteIndex.a(Integer.valueOf(iH6));
                z4 = z2;
            }
        }
        if (z4) {
            this.spriteIndex.clear();
        }
    }
}
