package net.fdgames.GameEntities.Final;

import a0.p;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import k0.a;
import m0.b;
import n0.l1;
import n0.w;
import n0.z;
import net.fdgames.GameEntities.AI.Pathfinding.AStarPathFinder;
import net.fdgames.GameEntities.Character;
import net.fdgames.GameEntities.CharacterSheet.CharacterEffects;
import net.fdgames.GameEntities.CharacterSheet.CharacterInventory;
import net.fdgames.GameEntities.CharacterSheet.CharacterResistances;
import net.fdgames.GameEntities.CharacterSheet.CharacterSheet;
import net.fdgames.GameEntities.CharacterSheet.CharacterStats;
import net.fdgames.GameEntities.Helpers.Activable;
import net.fdgames.GameEntities.Helpers.AreasVisited;
import net.fdgames.GameEntities.Helpers.Damage;
import net.fdgames.GameEntities.Helpers.Items;
import net.fdgames.GameEntities.Helpers.Shop;
import net.fdgames.GameEntities.MapActor;
import net.fdgames.GameEntities.MapSprite;
import net.fdgames.GameLevel.GameLevel;
import net.fdgames.GameLevel.GameLevelData;
import net.fdgames.GameWorld.DynamicQuest;
import net.fdgames.GameWorld.GameData;
import net.fdgames.GameWorld.GameWorld;
import net.fdgames.GameWorld.Quests;
import net.fdgames.Helpers.FDUtils;
import net.fdgames.Helpers.GameConsole;
import net.fdgames.Helpers.GameString;
import net.fdgames.Helpers.Serializer;
import net.fdgames.Rules.PlayerCreation;
import net.fdgames.Rules.Rules;
import net.fdgames.Rules.SkillActions;
import net.fdgames.TiledMap.Objects.Bed;
import net.fdgames.TiledMap.Objects.Coords;
import net.fdgames.TiledMap.Objects.MapCastle;
import net.fdgames.TiledMap.Objects.MapConversation;
import net.fdgames.TiledMap.Objects.RestPoint;
import net.fdgames.TiledMap.Objects.Transition;
import net.fdgames.assets.GameAssets;
import net.fdgames.ek.ControllerConfig;
import net.fdgames.ek.ExiledKingdoms;
import net.fdgames.ek.GPGSUpdate;
import net.fdgames.ek.Settings;

/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public class Player extends Character {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static float f3024b = 0.35f;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static Set<Integer> f3025c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static boolean f3026d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static boolean f3027e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static boolean f3028f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static float f3029g;
    private float TransitionTime;
    public Activable[] activables;
    private String activeSkill_1;
    private String activeSkill_2;
    private String activeSkill_3;
    private String activeSkill_4;
    private String activeSkill_5;
    private String activeSkill_6;
    private String activeSkill_7;
    private String activeSkill_8;
    private ArrayList<String> activeSkills;
    public AreasVisited areasVisited;
    private ArrayList<Bed> beds;
    private ArrayList<MapContainer> containers;
    public ArrayList<MapConversation> conversations;
    private int gold;
    private float lastPlayerCollisionCheck;
    private float lastPlayerFOVCheck;
    private float last_casual_regen;
    private float last_mana_regen;
    private float lastplayerattack;
    private ArrayList<Loot> loots;
    private ArrayList<MapCastle> mapCastles;
    public int numActivables;
    private ArrayList<PlantSpawn> plants;
    private int quickSlot1;
    private int quickSlot2;
    private int quickSlot3;
    private int quickSlot4;
    private int quickSlot5;
    private int quickSlotOrigin1;
    private int quickSlotOrigin2;
    private int quickSlotOrigin3;
    private int quickSlotOrigin4;
    private int quickSlotOrigin5;
    private ArrayList<RestPoint> restpoints;
    private ArrayList<Shop> shops;
    public Transition tempTransition;
    private ArrayList<MapItem> toggles;
    private int tolCurseCounter;
    private int usedEndurance;
    public int usedRecover;

    public Player() {
        this.tolCurseCounter = 0;
        this.gold = 0;
        this.tempTransition = null;
        this.activeSkills = new ArrayList<>();
        this.activables = new Activable[4];
        this.numActivables = 0;
        this.lastPlayerCollisionCheck = 0.0f;
        this.lastPlayerFOVCheck = 0.0f;
        this.usedEndurance = 0;
        this.areasVisited = new AreasVisited();
        this.usedRecover = 0;
        this.activeSkill_1 = "";
        this.activeSkill_2 = "";
        this.activeSkill_3 = "";
        this.activeSkill_4 = "";
        this.activeSkill_5 = "";
        this.activeSkill_6 = "";
        this.activeSkill_7 = "";
        this.activeSkill_8 = "";
        this.quickSlot1 = -1;
        this.quickSlot2 = -1;
        this.quickSlot3 = -1;
        this.quickSlot4 = -1;
        this.quickSlot5 = -1;
        this.quickSlotOrigin1 = 0;
        this.quickSlotOrigin2 = 0;
        this.quickSlotOrigin3 = 0;
        this.quickSlotOrigin4 = 0;
        this.quickSlotOrigin5 = 0;
    }

    private void E1() {
        if (this.activables == null) {
            this.activables = new Activable[]{null, null, null, null};
            this.numActivables = 0;
        }
    }

    public static boolean L1(int[] iArr) {
        if (f3025c == null) {
            f3025c = GameWorld.f3189c.c("player").hostileFactions;
        }
        if (f3025c.contains(Integer.valueOf(iArr[0]))) {
            return true;
        }
        return iArr.length > 1 && f3025c.contains(Integer.valueOf(iArr[1]));
    }

    @Override // net.fdgames.GameEntities.Character
    public final boolean A0(int i2, boolean z2) {
        int iA = (int) this.sheet.a();
        if (z2) {
            iA = 44;
        }
        MapSprite mapSpriteI = GameLevel.i(i2);
        if (mapSpriteI == null) {
            return false;
        }
        if (z2) {
            return Math.abs(this.f3092x - mapSpriteI.f3092x) < iA && Math.abs(this.f3093y - mapSpriteI.f3093y) < iA;
        }
        if (!z2) {
            int i3 = this.f3092x;
            int i4 = this.f3093y;
            int i5 = mapSpriteI.f3092x;
            int i6 = mapSpriteI.f3093y;
            int i7 = b.f2414k0;
            int i8 = i3 - i5;
            int i9 = i4 - i6;
            if (Math.sqrt((i9 * i9) + (i8 * i8)) <= iA - 21) {
                return true;
            }
        }
        return false;
    }

    @Override // net.fdgames.GameEntities.Character
    public final boolean A1(int i2) {
        if (Rules.f(i2) == null) {
            return false;
        }
        this.sheet.getClass();
        boolean zB = CharacterSheet.b(i2);
        if (zB && Rules.f(i2).OnTakeconditions.a().booleanValue()) {
            Rules.f(i2).OnTake.a();
            for (DynamicQuest dynamicQuest : GameData.v().dynamicQuests) {
                int iB = GameData.v().gameVariables.b("DQ_" + dynamicQuest.DQ_id);
                if (iB > 0 && iB < 50) {
                    Quests quests = GameWorld.f3187a;
                    String str = dynamicQuest.quest_ID;
                    String str2 = dynamicQuest.variation_ID;
                    quests.getClass();
                    if (Quests.d(str, str2).item_id == i2) {
                        GameData.v().gameVariables.e(50, "DQ_" + dynamicQuest.DQ_id);
                    }
                }
            }
            GameAssets.o("item2");
        }
        return zB;
    }

    public final void B1(int i2) {
        if (i2 != 0) {
            GameAssets.o("coin");
        }
        this.gold += i2;
    }

    @Override // net.fdgames.GameEntities.MapObject
    public final String C() {
        return a.a.o("YOU", false, new StringBuilder("[GREEN]"), "[]");
    }

    public final void C1() {
        this.sheet.inventory = new CharacterInventory();
        this.sheet.getClass();
        GameData.v().backpack.e();
        this.gold = 18;
        this.sheet.getClass();
        CharacterSheet.b(501);
        this.sheet.inventory.c(0);
    }

    @Override // net.fdgames.GameEntities.MapObject
    public final String D() {
        return a.a.o("YOU_TARGET", false, new StringBuilder("[GREEN]"), "[]");
    }

    public final void D1(String str) {
        if (M1(str) >= 0) {
            return;
        }
        b2();
        if (this.activeSkill_1.equals("")) {
            this.activeSkill_1 = str;
            return;
        }
        if (this.activeSkill_2.equals("")) {
            this.activeSkill_2 = str;
            return;
        }
        if (this.activeSkill_3.equals("")) {
            this.activeSkill_3 = str;
            return;
        }
        if (this.activeSkill_4.equals("")) {
            this.activeSkill_4 = str;
            return;
        }
        if (this.activeSkill_5.equals("")) {
            this.activeSkill_5 = str;
            return;
        }
        if (this.activeSkill_6.equals("")) {
            this.activeSkill_6 = str;
        } else if (this.activeSkill_7.equals("")) {
            this.activeSkill_7 = str;
        } else if (this.activeSkill_8.equals("")) {
            this.activeSkill_8 = str;
        }
    }

    @Override // net.fdgames.GameEntities.Character
    public final void E0(int i2) {
        int iB;
        this.last_casual_regen = GameData.v().u() + 3.0f;
        if (!ExiledKingdoms.f3378h && !i0() && !j0()) {
            z.f2926j0 = true;
        }
        if (!l0() || i0() || j0()) {
            return;
        }
        if (!g0()) {
            if (this.sheet.N().c()) {
                GameAssets.o("spell_attack");
            } else {
                GameAssets.o("swing");
            }
        }
        if (!this.sheet.N().ranged) {
            if (i2 > 0) {
                iB = i2;
            } else {
                Coords coordsL = b.L(B(), 24, this.facing);
                float f2 = 22;
                p pVar = new p(coordsL.f3287x - 11, coordsL.f3288y - 11, f2, f2);
                b bVarP = b.P();
                int iQ = q();
                b.P().getClass();
                iB = bVarP.b(iQ, b.j(pVar));
                if (iB == 0) {
                    b bVarP2 = b.P();
                    int iQ2 = q();
                    b bVarP3 = b.P();
                    p pVarY0 = y0();
                    bVarP3.getClass();
                    iB = bVarP2.b(iQ2, b.j(pVarY0));
                }
            }
            if (iB > 0) {
                R(GameLevel.g(iB).B());
            }
        } else if (ExiledKingdoms.f3378h) {
            b bVarP4 = b.P();
            int iQ3 = q();
            b bVarP5 = b.P();
            p pVarY02 = y0();
            bVarP5.getClass();
            int iB2 = bVarP4.b(iQ3, b.j(pVarY02));
            if (iB2 > 0) {
                R(GameLevel.g(iB2).B());
            }
        }
        z.f2926j0 = true;
        super.E0(i2);
    }

    public final String F1(int i2) {
        b2();
        return i2 == 0 ? this.activeSkill_1 : i2 == 1 ? this.activeSkill_2 : i2 == 2 ? this.activeSkill_3 : i2 == 3 ? this.activeSkill_4 : i2 == 4 ? this.activeSkill_5 : i2 == 5 ? this.activeSkill_6 : i2 == 6 ? this.activeSkill_7 : i2 == 7 ? this.activeSkill_8 : "";
    }

    public final int G1() {
        return (this.sheet.skillSet.g("extra_recovery") + 2) - this.usedRecover;
    }

    public final int H1(int i2) {
        if (i2 == 0) {
            return this.quickSlot1;
        }
        if (i2 == 1) {
            return this.quickSlot2;
        }
        if (i2 == 2) {
            return this.quickSlot3;
        }
        if (i2 == 3) {
            return this.quickSlot4;
        }
        if (i2 == 4) {
            return this.quickSlot5;
        }
        return 0;
    }

    public final int I1(int i2) {
        int iH1 = H1(i2);
        int iJ1 = J1(i2);
        if (iJ1 > 0) {
            return iJ1 == 2 ? this.sheet.inventory.i(iH1) : iJ1 == 1 ? GameData.v().backpack.h(iH1) : 0;
        }
        return 0;
    }

    public final int J1(int i2) {
        if (i2 == 0) {
            return this.quickSlotOrigin1;
        }
        if (i2 == 1) {
            return this.quickSlotOrigin2;
        }
        if (i2 == 2) {
            return this.quickSlotOrigin3;
        }
        if (i2 == 3) {
            return this.quickSlotOrigin4;
        }
        if (i2 == 4) {
            return this.quickSlotOrigin5;
        }
        return 0;
    }

    public final int K1(int i2) {
        int iH1 = H1(i2);
        int iJ1 = J1(i2);
        return (iJ1 != 1 || GameData.v().backpack.h(iH1) <= 0) ? (iJ1 != 2 || this.sheet.inventory.i(iH1) <= 0) ? 0 : 1 : GameData.v().backpack.j(iH1).units;
    }

    @Override // net.fdgames.GameEntities.Character
    public final boolean L0(Character character) {
        return (character.sheet == null || character.d0() == MapActor.ActorState.f3078h || character.d0() == MapActor.ActorState.f3077g || character.sheet.stats.f() <= 2 || !b.Z(character, this)) ? false : true;
    }

    @Override // net.fdgames.GameEntities.Character, net.fdgames.GameEntities.MapActor, net.fdgames.GameEntities.MapSprite
    public final void M(float f2) {
        int i2;
        boolean z2 = this.destroy;
        MapActor.ActorState actorState = MapActor.ActorState.f3074d;
        if (z2 && d0() != actorState && this.sheet.o() > 0) {
            this.destroy = false;
        }
        boolean z3 = ExiledKingdoms.f3378h;
        if (z3) {
            f3024b = 0.15f;
        }
        if (z3 && (i2 = GameLevel.f3101h) != 0) {
            MapActor mapActorG = GameLevel.g(i2);
            if (mapActorG == null || mapActorG.i0()) {
                V1(-1, 1);
                GameLevel.f3101h = 0;
                GameLevel.f3100g = false;
            } else if (A0(GameLevel.f3101h, !this.sheet.N().ranged)) {
                E0(GameLevel.f3101h);
                V1(-1, 1);
            } else {
                V1(mapActorG.f3092x, mapActorG.f3093y);
            }
        }
        if (z.x() && l0()) {
            if (ExiledKingdoms.f3378h) {
                AStarPathFinder aStarPathFinder = GameLevel.f3094a;
                GameData.v().player.getClass();
                z.f2926j0 = false;
            }
            z.v().Z();
            z.v().H();
        }
        this.lastPlayerFOVCheck += f2;
        if (d0() != actorState && this.lastPlayerFOVCheck > f3024b) {
            b.P().k0();
            this.lastPlayerFOVCheck = 0.0f;
            b.P().getClass();
            Coords coordsE = b.e(100.0d, false);
            f3026d = ((coordsE == null || b.p(coordsE, GameData.v().player.B()) >= 288.0d) ? Boolean.FALSE : Boolean.TRUE).booleanValue();
            b.P().getClass();
            f3027e = b.e(176.0d, true) != null;
            b.P().getClass();
            f3028f = b.e(576.0d, true) != null;
            f3025c = GameWorld.f3189c.c("player").hostileFactions;
        }
        this.lastPlayerCollisionCheck += f2;
        if (d0() != actorState && this.lastPlayerCollisionCheck > 0.15f) {
            this.lastPlayerCollisionCheck = 0.0f;
            b bVarP = b.P();
            int i3 = this.f3092x;
            int i4 = this.f3093y;
            bVarP.getClass();
            b.E(i3, i4);
            b bVarP2 = b.P();
            int i5 = this.f3092x;
            int i6 = this.f3093y;
            bVarP2.getClass();
            int i7 = i6 - 16;
            int i8 = i5 - 16;
            int size = GameLevelData.o().doors.size();
            AStarPathFinder aStarPathFinder2 = GameLevel.f3094a;
            boolean z4 = GameData.v().player.sheet.skillSet.g("stealth") == 3;
            for (int i9 = 0; i9 < size; i9++) {
                int i10 = GameLevelData.o().doors.get(i9).f3092x;
                int i11 = GameLevelData.o().doors.get(i9).f3093y;
                if (!GameLevelData.o().doors.get(i9).G() && Math.abs(i8 - i10) < 36 && Math.abs(i7 - i11) < 36) {
                    GameLevelData.o().doors.get(i9).H();
                    if (!z4) {
                        GameData.v().player.sheet.effects.stealth = Boolean.FALSE;
                    }
                    if (z4 && FDUtils.b(1, 2) == 1) {
                        GameData.v().player.sheet.effects.stealth = Boolean.FALSE;
                    }
                    int i12 = 0;
                    while (true) {
                        ArrayList<MapActor> arrayList = GameLevel.f3096c;
                        if (i12 < arrayList.size()) {
                            if (GameWorld.f3189c.f(arrayList.get(i12).r()) && b.r(arrayList.get(i12).f3092x, arrayList.get(i12).f3093y, i10, i11) < 128) {
                                arrayList.get(i12).U(1);
                            }
                            i12++;
                        }
                    }
                }
            }
            E1();
            Activable[] activableArr = this.activables;
            activableArr[0] = null;
            activableArr[1] = null;
            activableArr[2] = null;
            activableArr[3] = null;
            this.numActivables = 0;
            b bVarP3 = b.P();
            int i13 = this.f3092x;
            int i14 = this.f3093y;
            bVarP3.getClass();
            ArrayList<Loot> arrayListH = b.h(i13, i14);
            this.loots = arrayListH;
            if (arrayListH.size() > 0 && this.numActivables < 4) {
                E1();
                this.activables[0] = new Activable(0);
                this.numActivables++;
            }
            ArrayList<RestPoint> arrayListH0 = b.P().h0(S(true));
            this.restpoints = arrayListH0;
            if (arrayListH0.size() > 0 && this.numActivables < 4) {
                for (RestPoint restPoint : this.restpoints) {
                    E1();
                    Activable[] activableArr2 = this.activables;
                    int i15 = this.numActivables;
                    activableArr2[i15] = new Activable(restPoint);
                    this.numActivables = i15 + 1;
                }
            }
            ArrayList<Bed> arrayListG = b.P().G(S(true));
            this.beds = arrayListG;
            if (arrayListG.size() > 0 && this.numActivables < 4) {
                for (Bed bed : this.beds) {
                    E1();
                    Activable[] activableArr3 = this.activables;
                    int i16 = this.numActivables;
                    activableArr3[i16] = new Activable((Object) null);
                    this.numActivables = i16 + 1;
                }
            }
            ArrayList<MapCastle> arrayListF0 = b.P().f0(S(true));
            this.mapCastles = arrayListF0;
            if (arrayListF0.size() > 0 && this.numActivables < 4) {
                for (MapCastle mapCastle : this.mapCastles) {
                    E1();
                    this.activables[this.numActivables] = new Activable(mapCastle);
                    this.numActivables++;
                }
            }
            ArrayList<PlantSpawn> arrayListG0 = b.P().g0(this.f3092x, this.f3093y);
            this.plants = arrayListG0;
            if (arrayListG0.size() > 0 && this.numActivables < 4) {
                for (PlantSpawn plantSpawn : this.plants) {
                    if (this.numActivables < 4) {
                        E1();
                        Activable[] activableArr4 = this.activables;
                        int i17 = this.numActivables;
                        activableArr4[i17] = new Activable(plantSpawn);
                        this.numActivables = i17 + 1;
                    }
                }
            }
            ArrayList<MapItem> arrayListJ0 = b.P().j0(this.f3092x, this.f3093y);
            this.toggles = arrayListJ0;
            if (arrayListJ0.size() > 0 && this.numActivables < 4) {
                for (MapItem mapItem : this.toggles) {
                    if (this.numActivables < 4) {
                        E1();
                        Activable[] activableArr5 = this.activables;
                        int i18 = this.numActivables;
                        activableArr5[i18] = new Activable(mapItem);
                        this.numActivables = i18 + 1;
                    }
                }
            }
            ArrayList<MapContainer> arrayListI = b.P().I(S(true));
            this.containers = arrayListI;
            if (arrayListI.size() > 0 && this.numActivables < 4) {
                for (MapContainer mapContainer : this.containers) {
                    if (this.numActivables < 4) {
                        E1();
                        this.activables[this.numActivables] = new Activable(mapContainer.iconName, mapContainer.q());
                        this.numActivables++;
                    }
                }
            }
            ArrayList<Shop> arrayListI0 = b.P().i0(this.f3092x, this.f3093y);
            this.shops = arrayListI0;
            if (arrayListI0.size() > 0 && this.numActivables < 4) {
                for (Shop shop : this.shops) {
                    if (this.numActivables < 4) {
                        E1();
                        Activable[] activableArr6 = this.activables;
                        int i19 = this.numActivables;
                        activableArr6[i19] = new Activable(shop);
                        this.numActivables = i19 + 1;
                    }
                }
            }
            int size2 = GameLevelData.z().size();
            for (int i20 = 0; i20 < size2; i20++) {
                if (GameLevelData.z().get(i20).P()) {
                    GameLevelData.z().get(i20).X(this);
                    if (GameData.v().party.j()) {
                        GameLevelData.z().get(i20).X(GameData.v().party.f());
                    }
                } else if (GameLevelData.z().get(i20).U() && this.numActivables < 4) {
                    E1();
                    this.activables[this.numActivables] = new Activable(GameLevelData.z().get(i20));
                    this.numActivables++;
                }
            }
            int size3 = GameLevelData.v().size();
            for (int i21 = 0; i21 < size3; i21++) {
                if (GameLevelData.v().get(i21).H()) {
                    GameLevelData.v().get(i21).L(this);
                    if (GameData.v().party.j()) {
                        GameLevelData.v().get(i21).L(GameData.v().party.f());
                    }
                } else if (GameLevelData.v().get(i21).F(this.f3092x, this.f3093y) && this.numActivables < 4) {
                    E1();
                    this.activables[this.numActivables] = new Activable(GameLevelData.v().get(i21));
                    this.numActivables++;
                }
            }
            ArrayList<MapConversation> arrayListJ = b.P().J(this.f3092x, this.f3093y);
            this.conversations = arrayListJ;
            if (arrayListJ.size() > 0 && this.numActivables < 4) {
                for (MapConversation mapConversation : this.conversations) {
                    if (this.numActivables < 4) {
                        E1();
                        Activable[] activableArr7 = this.activables;
                        int i22 = this.numActivables;
                        activableArr7[i22] = new Activable(mapConversation);
                        this.numActivables = i22 + 1;
                    }
                }
            }
            z.v().S();
            if (GameData.v().NewArea == null && this.tempTransition == null) {
                Transition transitionM = b.P().m(this.f3092x, this.f3093y);
                this.tempTransition = transitionM;
                if (transitionM != null) {
                    ControllerConfig controllerConfig = Settings.f3418a;
                    ExiledKingdoms.f3371a = true;
                    if (transitionM.area_id.equals(GameData.v().CurrentLevel)) {
                        Coords coordsT = b.P().t(this.tempTransition);
                        this.f3092x = coordsT.f3287x;
                        this.f3093y = coordsT.f3288y;
                        k0.a.l().b(B(), a.EnumC0031a.f2291c, 0.0f);
                        if (GameData.v().party.j()) {
                            GameData.v().party.f().f3092x = coordsT.f3287x;
                            GameData.v().party.f().f3093y = coordsT.f3288y;
                        }
                        this.tempTransition = null;
                    } else {
                        if (Gdx.files.internal("data/tmx/" + this.tempTransition.area_id + ".tmx").exists()) {
                            z.v().Q();
                            this.TransitionTime = GameLevel.b() + 0.05f;
                        } else {
                            this.tempTransition = null;
                            if (GameLevel.b() > f3029g) {
                                new a(GameString.b("AREA_UNAVAILABLE_TITLE", false), GameString.b("AREA_UNAVAILABLE", false), Boolean.FALSE).show(z.v().a());
                                GameLevel.n(true);
                                f3029g = GameLevel.b() + 5.0f;
                            }
                        }
                    }
                }
            } else if (this.tempTransition != null && GameLevel.b() > this.TransitionTime) {
                GameData.v().NewArea = this.tempTransition;
                this.tempTransition = null;
            }
            if (GameData.v().tolCurse) {
                int i23 = this.tolCurseCounter;
                if (i23 < 20) {
                    this.tolCurseCounter = i23 + 1;
                } else {
                    this.tolCurseCounter = 0;
                    R0(new Damage(Damage.DamageType.f3050e, 12, false), 0, false, 0);
                    if (this.sheet.F(CharacterResistances.ResistanceType.f2998d) <= 178) {
                        GameData.v().log.a("[RED]" + GameString.b("TOL_CURSE_LOG", false) + "[]");
                    }
                }
            } else {
                boolean zD = GameData.v().D();
                MapActor.ActorState actorState2 = MapActor.ActorState.f3072b;
                MapActor.ActorState actorState3 = MapActor.ActorState.f3071a;
                if (!zD || f3027e || (!(d0() == actorState3 || d0() == actorState2) || this.sheet.r() < 0.5f || this.sheet.o() >= 80)) {
                    if (GameData.v().F() && !f3027e && (d0() == actorState3 || d0() == actorState2)) {
                        if (this.sheet.stats.missingHP > 0 && GameData.v().u() > this.last_casual_regen + 0.7f) {
                            this.last_casual_regen = GameData.v().u();
                            this.sheet.R(1);
                        }
                        if (this.sheet.V()) {
                            CharacterSheet characterSheet = this.sheet;
                            if (characterSheet.stats.missingMana > characterSheet.A() / 2 && GameData.v().u() > this.last_mana_regen + 2.5f) {
                                CharacterStats characterStats = this.sheet.stats;
                                int i24 = characterStats.missingMana - 1;
                                characterStats.missingMana = i24;
                                if (i24 < 0) {
                                    characterStats.missingMana = 0;
                                }
                                this.last_mana_regen = GameData.v().u();
                            }
                        }
                    }
                } else if (GameData.v().u() > this.last_casual_regen + 0.7f) {
                    this.sheet.R(1);
                    this.last_casual_regen = GameData.v().u();
                }
            }
        }
        super.M(f2);
    }

    public final int M1(String str) {
        if (this.activeSkill_1.equals(str)) {
            return 0;
        }
        if (this.activeSkill_2.equals(str)) {
            return 1;
        }
        if (this.activeSkill_3.equals(str)) {
            return 2;
        }
        if (this.activeSkill_4.equals(str)) {
            return 3;
        }
        if (this.activeSkill_5.equals(str)) {
            return 4;
        }
        if (this.activeSkill_6.equals(str)) {
            return 5;
        }
        if (this.activeSkill_7.equals(str)) {
            return 6;
        }
        return this.activeSkill_8.equals(str) ? 7 : -1;
    }

    public final void N1() {
        GameData.v().lostBackpack = new Items();
        for (int i2 = 0; i2 < 20; i2++) {
            int i3 = GameData.v().backpack.j(i2).itemID;
            int i4 = GameData.v().backpack.j(i2).units;
            if (i3 != 0) {
                GameData.v().lostBackpack.a(i3, i4);
            }
        }
        GameData.v().backpack = new Items();
        GameData.v().lostEquipment = new Items();
        GameData.v().lostEquipment.a(this.sheet.inventory.slot_body, 1);
        GameData.v().lostEquipment.a(this.sheet.inventory.slot_cloak, 1);
        GameData.v().lostEquipment.a(this.sheet.inventory.slot_feet, 1);
        GameData.v().lostEquipment.a(this.sheet.inventory.slot_hands, 1);
        GameData.v().lostEquipment.a(this.sheet.inventory.slot_head, 1);
        GameData.v().lostEquipment.a(this.sheet.inventory.slot_legs, 1);
        GameData.v().lostEquipment.a(this.sheet.inventory.slot_mainhand, 1);
        GameData.v().lostEquipment.a(this.sheet.inventory.slot_necklace, 1);
        GameData.v().lostEquipment.a(this.sheet.inventory.slot_offhand, 1);
        GameData.v().lostEquipment.a(this.sheet.inventory.slot_ring, 1);
        GameData.v().lostEquipment.a(this.sheet.inventory.slot_ring2, 1);
        GameData.v().lostEquipment.a(this.sheet.inventory.slot_belt, 1);
        this.sheet.inventory = new CharacterInventory();
        GameData.v().lostGold = this.gold;
        this.gold = 0;
    }

    @Override // net.fdgames.GameEntities.Character
    public final void O0(int i2) {
        int iO;
        if (this.sheet.stats.i() >= 30000 && !ExiledKingdoms.f3371a) {
            if (Settings.o()) {
                GameData.v().log.a(GameString.b("MAX_XP", false));
            }
            z.v().q();
            return;
        }
        boolean zJ = GameData.v().party.j();
        a.EnumC0031a enumC0031a = a.EnumC0031a.f2291c;
        if (zJ) {
            NPC npcF = GameData.v().party.f();
            int i3 = (int) (i2 * 0.8f);
            int i4 = (int) (i3 * 0.7f);
            iO = this.sheet.O(i3);
            int iO2 = npcF.sheet.O(i4);
            int iF = GameData.v().party.f().sheet.stats.f();
            npcF.sheet.stats.a(iO2);
            k0.a.l().a(new w(GameData.v().party.f().q(), "+" + Integer.toString(iO2) + "xp", 1.3f, Color.YELLOW, 0.7f, 0.7f));
            if (npcF.sheet.stats.f() > iF) {
                k0.a.l().a(new w(npcF.q(), GameString.b("LEVEL_UP", false), 1.0f, Color.BLUE, 1.0f, 0.7f));
                k0.a.l().b(npcF.B(), enumC0031a, 0.0f);
                GameAssets.o("levelup");
            }
            if (Settings.o()) {
                GameData.v().log.b(iO, iO2);
            }
        } else {
            iO = this.sheet.O(i2);
            if (Settings.o()) {
                GameData.v().log.b(iO, 0);
            }
        }
        int iF2 = this.sheet.stats.f();
        this.sheet.stats.a(iO);
        k0.a.l().a(new w(q(), "+" + Integer.toString(iO) + "xp", 1.3f, Color.YELLOW, 1.0f, 0.7f));
        if (this.sheet.stats.f() > iF2) {
            k0.a.l().a(new w(q(), GameString.b("LEVEL_UP", false), 1.0f, Color.BLUE, 1.0f, 0.7f));
            k0.a.l().b(B(), enumC0031a, 0.0f);
            int i5 = this.sheet.stats.f() > 15 ? 3 : 2;
            GameData.v().log.a(GameString.b("GAINED_LEVEL", false).replace("{a}", "" + i5));
            GameAssets.o("levelup");
            l0.b.h();
            GPGSUpdate.c(false);
            l0.b.f(this.sheet.stats.f());
        }
    }

    public final void O1() {
        if (j0()) {
            k0.a.l().a(new w(q(), GameString.b("INTERRUPTED", false), 0.6f, Color.GREEN, 1.0f, 0.7f));
            return;
        }
        Transition transitionG = SkillActions.g();
        if (transitionG.area_id.equals("")) {
            GameConsole.a("The spell failed!");
            return;
        }
        this.tempTransition = transitionG;
        z.v().Q();
        this.TransitionTime = GameLevel.b() + 0.05f;
    }

    @Override // net.fdgames.GameEntities.Character
    public final float P0() {
        return 1.0f;
    }

    public final void P1() {
        V0(this.sheet.stats.missingHP);
        this.sheet.stats.missingMana = 0;
        for (NPC npc : GameLevelData.t()) {
            if (npc.k0()) {
                npc.V0(npc.sheet.stats.missingHP);
                npc.sheet.stats.missingMana = 0;
            }
        }
        this.usedRecover++;
    }

    @Override // net.fdgames.GameEntities.MapActor
    public final com.badlogic.gdx.utils.a<TextureRegion> Q() {
        com.badlogic.gdx.utils.a<Integer> aVar = this.spriteIndex;
        if (aVar == null || aVar.f1750b == 0) {
            v0();
        }
        GameAssets.f3315a.clear();
        int i2 = 0;
        while (true) {
            com.badlogic.gdx.utils.a<Integer> aVar2 = this.spriteIndex;
            if (i2 >= aVar2.f1750b) {
                break;
            }
            GameAssets.f3315a.a((TextureRegion) GameAssets.i(aVar2.get(i2).intValue()).a(d0(), this.facing).getKeyFrame(V()));
            i2++;
        }
        if (this.sheet.inventory.slot_mainhand > 0 && ((d0().equals(MapActor.ActorState.f3073c) || d0().equals(MapActor.ActorState.f3078h)) && !this.sheet.N().c())) {
            GameAssets.f3315a.a((TextureRegion) GameAssets.f3354v.b(this.facing).getKeyFrame(V()));
        }
        D0();
        return GameAssets.f3315a;
    }

    @Override // net.fdgames.GameEntities.Character
    protected final float Q0() {
        return 0.5f;
    }

    public final void Q1() {
        for (int i2 = 0; i2 < 20; i2++) {
            int i3 = GameData.v().lostBackpack.j(i2).itemID;
            int i4 = GameData.v().lostBackpack.j(i2).units;
            if (i3 != 0 && !GameData.v().backpack.a(i3, i4)) {
                while (i4 > 0) {
                    int i5 = this.f3092x;
                    int i6 = this.f3093y;
                    AStarPathFinder aStarPathFinder = GameLevel.f3094a;
                    GameLevelData.d(new Loot(i5, i6, i3));
                    i4--;
                }
            }
        }
        for (int i7 = 0; i7 < 20; i7++) {
            int i8 = GameData.v().lostEquipment.j(i7).itemID;
            int i9 = GameData.v().lostEquipment.j(i7).units;
            if (i8 != 0 && !GameData.v().backpack.a(i8, i9)) {
                while (i9 > 0) {
                    int i10 = this.f3092x;
                    int i11 = this.f3093y;
                    AStarPathFinder aStarPathFinder2 = GameLevel.f3094a;
                    GameLevelData.d(new Loot(i10, i11, i8));
                    i9--;
                }
            }
        }
        this.gold += GameData.v().lostGold;
        GameData.v().lostGold = 0;
        GameData.v().lostBackpack = new Items();
        GameData.v().lostEquipment = new Items();
    }

    @Override // net.fdgames.GameEntities.Character
    public final void R0(Damage damage, int i2, boolean z2, int i3) {
        if (z2 || damage.hp >= this.sheet.z() / 12) {
            if (this.gender == Character.Gender.f2992a) {
                GameAssets.p("male_grunt_1;male_grunt_2;male_grunt_3;male_grunt_4");
            } else {
                GameAssets.p("female_grunt_1;female_grunt_2;female_grunt_3");
            }
        }
        super.R0(damage, i2, z2, i3);
    }

    public final void R1(int i2) {
        int i3 = this.gold;
        if (i3 >= i2) {
            this.gold = i3 - i2;
        }
    }

    public final void S1() {
        this.usedRecover = 0;
    }

    public final void T1() {
        this.activeSkill_1 = "";
        this.activeSkill_2 = "";
        this.activeSkill_3 = "";
        this.activeSkill_4 = "";
        this.activeSkill_5 = "";
        this.activeSkill_6 = "";
        this.activeSkill_7 = "";
        this.activeSkill_8 = "";
    }

    public final void U1(boolean z2) {
        MapActor.ActorState actorState;
        this.sheet.skillSet.p();
        if (z2) {
            this.usedRecover = 0;
            this.sheet.S();
            if (GameData.v().G()) {
                GameConsole.a(GameString.b("RECOVER_ALL_HEALTH_NOSAVE", false));
            } else {
                GameConsole.a(GameString.b("RECOVER_ALL_HEALTH", false));
            }
        } else {
            CharacterSheet characterSheet = this.sheet;
            characterSheet.R(characterSheet.z() / 2);
            CharacterSheet characterSheet2 = this.sheet;
            int iA = characterSheet2.A() / 2;
            CharacterStats characterStats = characterSheet2.stats;
            int i2 = characterStats.missingMana - iA;
            characterStats.missingMana = i2;
            if (i2 < 0) {
                characterStats.missingMana = 0;
            }
            int iG = this.usedRecover - ((this.sheet.skillSet.g("extra_recovery") + 2) / 2);
            this.usedRecover = iG;
            if (iG < 0) {
                this.usedRecover = 0;
            }
            if (GameData.v().G()) {
                GameConsole.a(GameString.b("RECOVER_SOME_HEALTH_NOSAVE", false));
            } else {
                GameConsole.a(GameString.b("RECOVER_SOME_HEALTH", false));
            }
        }
        Iterator<NPC> it = GameLevelData.t().iterator();
        while (true) {
            boolean zHasNext = it.hasNext();
            actorState = MapActor.ActorState.f3071a;
            if (!zHasNext) {
                break;
            }
            NPC next = it.next();
            if (next.k0()) {
                next.sheet.S();
                SkillActions.d(next);
                CharacterSheet characterSheet3 = next.sheet;
                characterSheet3.getClass();
                characterSheet3.effects = new CharacterEffects();
                next.q0(actorState);
                next.t0();
            }
        }
        if (GameData.v().party != null && GameData.v().party.companions != null) {
            for (NPC npc : GameData.v().party.companions) {
                npc.sheet.S();
                SkillActions.d(npc);
                CharacterSheet characterSheet4 = npc.sheet;
                characterSheet4.getClass();
                characterSheet4.effects = new CharacterEffects();
                npc.q0(actorState);
            }
        }
        SkillActions.d(this);
        l0.b.g("Rest: " + GameData.v().CurrentLevel);
        GameData.v().party.e();
        Serializer.z(GameData.v().slot, 1);
    }

    public final void V1(int i2, int i3) {
        Coords coords = this.destination;
        coords.f3287x = i2;
        coords.f3288y = i3;
        AStarPathFinder aStarPathFinder = GameLevel.f3094a;
        GameData.v().player.getClass();
        if (z.f2926j0) {
            GameData.v().player.getClass();
            z.f2926j0 = false;
        }
    }

    public final void W1(int i2, int i3, int i4) {
        if (i4 == 0) {
            this.quickSlot1 = i3;
            this.quickSlotOrigin1 = i2;
        }
        if (i4 == 1) {
            this.quickSlot2 = i3;
            this.quickSlotOrigin2 = i2;
        }
        if (i4 == 2) {
            this.quickSlot3 = i3;
            this.quickSlotOrigin3 = i2;
        }
        if (i4 == 3) {
            this.quickSlot4 = i3;
            this.quickSlotOrigin4 = i2;
        }
        if (i4 == 4) {
            this.quickSlot5 = i3;
            this.quickSlotOrigin5 = i2;
        }
        z.v().W();
    }

    @Override // net.fdgames.GameEntities.MapActor
    protected final void X() {
        l0.b.e();
        GPGSUpdate.c(false);
        if (this.gender == Character.Gender.f2992a) {
            GameAssets.o("male_death");
        } else {
            GameAssets.o("female_death");
        }
        GameConsole.a(GameString.b("GAME_OVER", false));
        Settings.s("GL_died");
        GameData.v().getClass();
        GameData.X();
        super.X();
        k0.a.l().j();
        if (GameData.v().G()) {
            Serializer.f(GameData.v().slot);
            return;
        }
        int i2 = GameData.v().slot;
        if (i2 != -1) {
            int i3 = 1;
            long jLastModified = Gdx.files.local(Serializer.C(i2, 1)).lastModified();
            if (Gdx.files.local(Serializer.C(i2, 2)).exists() && Gdx.files.local(Serializer.C(i2, 2)).lastModified() > jLastModified) {
                jLastModified = Gdx.files.local(Serializer.C(i2, 2)).lastModified();
                i3 = 2;
            }
            if (Gdx.files.local(Serializer.C(i2, 3)).exists() && Gdx.files.local(Serializer.C(i2, 3)).lastModified() > jLastModified) {
                jLastModified = Gdx.files.local(Serializer.C(i2, 3)).lastModified();
                i3 = 3;
            }
            if (Gdx.files.local(Serializer.C(i2, 4)).exists() && Gdx.files.local(Serializer.C(i2, 4)).lastModified() > jLastModified) {
                jLastModified = Gdx.files.local(Serializer.C(i2, 4)).lastModified();
                i3 = 4;
            }
            if (Gdx.files.local(Serializer.C(i2, 5)).exists() && Gdx.files.local(Serializer.C(i2, 5)).lastModified() > jLastModified) {
                jLastModified = Gdx.files.local(Serializer.C(i2, 5)).lastModified();
                i3 = 5;
            }
            if (Gdx.files.local(Serializer.C(i2, 6)).exists() && Gdx.files.local(Serializer.C(i2, 6)).lastModified() > jLastModified) {
                jLastModified = Gdx.files.local(Serializer.C(i2, 6)).lastModified();
                i3 = 6;
            }
            if (Gdx.files.local(Serializer.C(i2, 7)).exists() && Gdx.files.local(Serializer.C(i2, 7)).lastModified() > jLastModified) {
                Gdx.files.local(Serializer.C(i2, 7)).lastModified();
                i3 = 7;
            }
            if (Gdx.files.local(Serializer.C(i2, i3)).exists()) {
                Gdx.files.local(Serializer.C(i2, i3)).copyTo(Gdx.files.local(Serializer.C(i2, 0)));
            }
        } else {
            com.badlogic.gdx.files.a aVar = Serializer.f3225b;
        }
        this.areasVisited.a();
    }

    @Override // net.fdgames.GameEntities.Character
    public final boolean X0() {
        return false;
    }

    public final void X1(int i2, String str) {
        b2();
        if (str.equals("")) {
            if (i2 == 0) {
                this.activeSkill_1 = "";
            }
            if (i2 == 1) {
                this.activeSkill_2 = "";
            }
            if (i2 == 2) {
                this.activeSkill_3 = "";
            }
            if (i2 == 3) {
                this.activeSkill_4 = "";
            }
            if (i2 == 4) {
                this.activeSkill_5 = "";
            }
            if (i2 == 5) {
                this.activeSkill_6 = "";
            }
            if (i2 == 6) {
                this.activeSkill_7 = "";
            }
            if (i2 == 7) {
                this.activeSkill_8 = "";
                return;
            }
            return;
        }
        if (this.activeSkill_1.equals(str)) {
            this.activeSkill_1 = "";
        }
        if (this.activeSkill_2.equals(str)) {
            this.activeSkill_2 = "";
        }
        if (this.activeSkill_3.equals(str)) {
            this.activeSkill_3 = "";
        }
        if (this.activeSkill_4.equals(str)) {
            this.activeSkill_4 = "";
        }
        if (this.activeSkill_5.equals(str)) {
            this.activeSkill_5 = "";
        }
        if (this.activeSkill_6.equals(str)) {
            this.activeSkill_6 = "";
        }
        if (this.activeSkill_7.equals(str)) {
            this.activeSkill_7 = "";
        }
        if (this.activeSkill_8.equals(str)) {
            this.activeSkill_8 = "";
        }
        if (i2 == 0) {
            this.activeSkill_1 = str;
        }
        if (i2 == 1) {
            this.activeSkill_2 = str;
        }
        if (i2 == 2) {
            this.activeSkill_3 = str;
        }
        if (i2 == 3) {
            this.activeSkill_4 = str;
        }
        if (i2 == 4) {
            this.activeSkill_5 = str;
        }
        if (i2 == 5) {
            this.activeSkill_6 = str;
        }
        if (i2 == 6) {
            this.activeSkill_7 = str;
        }
        if (i2 == 7) {
            this.activeSkill_8 = str;
        }
    }

    public final void Y1(Transition transition) {
        z.v().getClass();
        z.u().r();
        this.tempTransition = transition;
        z.v().Q();
        this.TransitionTime = GameLevel.b() + 0.05f;
    }

    public final void Z1(String str) {
        if (SkillActions.g().area_id.equals("")) {
            GameConsole.a("The spell failed!");
            return;
        }
        Transition transition = new Transition();
        this.tempTransition = transition;
        transition.area_id = str;
        transition.entry_id = 999;
        z.v().Q();
        this.TransitionTime = 0.0f;
    }

    public final void a2() {
        this.sheet.skillSet.p();
        this.usedRecover = 0;
        this.sheet.S();
        SkillActions.d(this);
    }

    public final void b2() {
        if (this.activeSkill_1 == null) {
            this.activeSkill_1 = "";
        }
        if (this.activeSkill_2 == null) {
            this.activeSkill_2 = "";
        }
        if (this.activeSkill_3 == null) {
            this.activeSkill_3 = "";
        }
        if (this.activeSkill_4 == null) {
            this.activeSkill_4 = "";
        }
        if (this.activeSkill_5 == null) {
            this.activeSkill_5 = "";
        }
        if (this.activeSkill_6 == null) {
            this.activeSkill_6 = "";
        }
        if (this.activeSkill_7 == null) {
            this.activeSkill_7 = "";
        }
        if (this.activeSkill_8 == null) {
            this.activeSkill_8 = "";
        }
        String str = this.activeSkill_1;
        if (str != "" && this.sheet.skillSet.g(str) == 0) {
            this.activeSkill_1 = "";
        }
        String str2 = this.activeSkill_2;
        if (str2 != "" && this.sheet.skillSet.g(str2) == 0) {
            this.activeSkill_2 = "";
        }
        String str3 = this.activeSkill_3;
        if (str3 != "" && this.sheet.skillSet.g(str3) == 0) {
            this.activeSkill_3 = "";
        }
        String str4 = this.activeSkill_4;
        if (str4 != "" && this.sheet.skillSet.g(str4) == 0) {
            this.activeSkill_4 = "";
        }
        String str5 = this.activeSkill_5;
        if (str5 != "" && this.sheet.skillSet.g(str5) == 0) {
            this.activeSkill_5 = "";
        }
        String str6 = this.activeSkill_6;
        if (str6 != "" && this.sheet.skillSet.g(str6) == 0) {
            this.activeSkill_6 = "";
        }
        String str7 = this.activeSkill_7;
        if (str7 != "" && this.sheet.skillSet.g(str7) == 0) {
            this.activeSkill_7 = "";
        }
        String str8 = this.activeSkill_8;
        if (str8 != "" && this.sheet.skillSet.g(str8) == 0) {
            this.activeSkill_8 = "";
        }
        l1.f2768r = this.sheet.Y();
    }

    @Override // net.fdgames.GameEntities.Character
    public final void d1() {
        if (i0()) {
            return;
        }
        b bVarP = b.P();
        int[] iArrR = r();
        p pVarY0 = y0();
        bVarP.getClass();
        if (b.l(iArrR, pVarY0).size() <= 0) {
            b.P().getClass();
            Coords coordsE = b.e(100.0d, false);
            if (coordsE != null) {
                R(new Coords(coordsE.f3287x, coordsE.f3288y));
            }
        }
        super.d1();
    }

    @Override // net.fdgames.GameEntities.Character
    public final void g1() {
        if (i0()) {
            return;
        }
        super.g1();
    }

    public final int h() {
        return this.gold;
    }

    @Override // net.fdgames.GameEntities.Character
    public final boolean m1() {
        return z.f2926j0;
    }

    @Override // net.fdgames.GameEntities.Character, net.fdgames.GameEntities.GameObject
    public final void u(int i2, String str, String str2) {
        if (!i0()) {
            if (str.equals("RECALL")) {
                O1();
                return;
            } else if (str.equals("TELEPORT")) {
                if (j0()) {
                    k0.a.l().a(new w(q(), GameString.b("INTERRUPTED", false), 0.6f, Color.GREEN, 1.0f, 0.7f));
                    return;
                } else {
                    z.v().P();
                    return;
                }
            }
        }
        super.u(i2, str, str2);
    }

    /* JADX WARN: Removed duplicated region for block: B:63:0x0157  */
    @Override // net.fdgames.GameEntities.MapActor
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void v0() {
        String str;
        String string;
        boolean z2;
        if (this.spriteIndex == null) {
            this.spriteIndex = new com.badlogic.gdx.utils.a<>();
        }
        this.spriteIndex.clear();
        String str2 = this.gender == Character.Gender.f2992a ? "male" : "female";
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
            int iH = GameAssets.h("composite/male_clothes");
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
        MapActor.ActorState actorState = MapActor.ActorState.f3075e;
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

    public Player(PlayerCreation playerCreation) {
        super(Rules.CharacterRace.f3266a, playerCreation.charClass, playerCreation.name, "adt_human_warrior", 1.0f, 1.0f, new int[]{100, 0}, playerCreation.gender, playerCreation.portraitIndex, "", false);
        this.tolCurseCounter = 0;
        this.gold = 0;
        this.tempTransition = null;
        this.activeSkills = new ArrayList<>();
        this.activables = new Activable[4];
        this.numActivables = 0;
        this.lastPlayerCollisionCheck = 0.0f;
        this.lastPlayerFOVCheck = 0.0f;
        this.usedEndurance = 0;
        this.areasVisited = new AreasVisited();
        this.usedRecover = 0;
        this.activeSkill_1 = "";
        this.activeSkill_2 = "";
        this.activeSkill_3 = "";
        this.activeSkill_4 = "";
        this.activeSkill_5 = "";
        this.activeSkill_6 = "";
        this.activeSkill_7 = "";
        this.activeSkill_8 = "";
        this.quickSlot1 = -1;
        this.quickSlot2 = -1;
        this.quickSlot3 = -1;
        this.quickSlot4 = -1;
        this.quickSlot5 = -1;
        this.quickSlotOrigin1 = 0;
        this.quickSlotOrigin2 = 0;
        this.quickSlotOrigin3 = 0;
        this.quickSlotOrigin4 = 0;
        this.quickSlotOrigin5 = 0;
        p();
        this.gold = 200;
        int iOrdinal = this.sheet.stats.c().ordinal();
        if (iOrdinal == 0) {
            this.sheet.getClass();
            CharacterSheet.b(100);
            this.sheet.inventory.c(0);
            this.sheet.getClass();
            CharacterSheet.b(com.google.android.gms.games.quest.Quests.SELECT_COMPLETED_UNCLAIMED);
            this.sheet.inventory.c(0);
            this.sheet.getClass();
            CharacterSheet.b(503);
            this.sheet.inventory.c(0);
        } else if (iOrdinal == 1) {
            this.sheet.getClass();
            CharacterSheet.b(100);
            this.sheet.inventory.c(0);
            this.sheet.getClass();
            CharacterSheet.b(com.google.android.gms.games.quest.Quests.SELECT_COMPLETED_UNCLAIMED);
            this.sheet.inventory.c(0);
            this.sheet.getClass();
            CharacterSheet.b(502);
            this.sheet.inventory.c(0);
        } else if (iOrdinal == 2) {
            this.sheet.getClass();
            CharacterSheet.b(100);
            this.sheet.inventory.c(0);
            this.sheet.getClass();
            CharacterSheet.b(com.google.android.gms.games.quest.Quests.SELECT_COMPLETED_UNCLAIMED);
            this.sheet.inventory.c(0);
            this.sheet.getClass();
            CharacterSheet.b(505);
            this.sheet.inventory.c(0);
        } else if (iOrdinal == 3) {
            this.sheet.getClass();
            CharacterSheet.b(300);
            this.sheet.inventory.c(0);
            this.sheet.getClass();
            CharacterSheet.b(301);
            this.sheet.inventory.c(0);
            this.sheet.getClass();
            CharacterSheet.b(370);
            this.sheet.inventory.c(0);
        }
        this.sheet.getClass();
        CharacterSheet.b(2515);
        this.facing = MapActor.Facing.f3090h;
        if (ExiledKingdoms.f3378h) {
            f3024b = 0.15f;
        }
    }
}
