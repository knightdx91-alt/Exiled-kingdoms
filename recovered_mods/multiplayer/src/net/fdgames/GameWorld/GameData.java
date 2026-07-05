package net.fdgames.GameWorld;

import androidx.appcompat.widget.LW.RCsZWh;
import com.google.android.gms.games.GamesStatusCodes;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import net.fdgames.GameEntities.AI.Pathfinding.AStarPathFinder;
import net.fdgames.GameEntities.CharacterSheet.CharacterSheet;
import net.fdgames.GameEntities.Final.MonsterSpawn;
import net.fdgames.GameEntities.Final.NPC;
import net.fdgames.GameEntities.Final.Player;
import net.fdgames.GameEntities.Helpers.Items;
import net.fdgames.GameEntities.Helpers.SkillSet;
import net.fdgames.GameLevel.GameLevel;
import net.fdgames.GameLevel.GameLevelData;
import net.fdgames.Helpers.FDUtils;
import net.fdgames.Helpers.GameConsole;
import net.fdgames.Helpers.GameString;
import net.fdgames.Helpers.Serializer;
import net.fdgames.Rules.PlayerCreation;
import net.fdgames.TiledMap.Objects.Transition;
import net.fdgames.assets.GameAssets;
import net.fdgames.ek.ExiledKingdoms;
import net.fdgames.ek.android.lan.LanSessionManager;
import o.Oeoo.vIBRkbZbNjpf;
import w0.a;
import y0.b;
import z0.ow.DkgvDLHsdXPkn;
import z0.z;

/* JADX INFO: loaded from: /tmp/tmp.CKdUvKN3M2/classes.dex */
public final class GameData {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static GameData f3403a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static boolean f3404b;
    public String CurrentLevel;
    public Transition NewArea;
    public Items backpack;
    public ArrayList<CastleData> castleData;
    public String currentMapName;
    private int difficulty;
    private ArrayList<DynamicEvent> dynamicEvents;
    public ArrayList<DynamicQuest> dynamicQuests;
    private double gameTime;
    public boolean hasVault;
    public boolean hasVault2;
    public boolean hasVault3;
    public boolean hasVault4;
    public ArrayList<InnData> innData;
    public boolean introMessageShown;
    public int lastDynamicQuest;
    private int lastRoll;
    public GameLog log;
    private ArrayList<String> lootedContainers;
    public Items lostBackpack;
    public Items lostEquipment;
    public int lostGold;
    public boolean night;
    public Party party;
    public Player player;
    public double realTime;
    public boolean shardsCompleted;
    public boolean shardsMessageShown;
    public int slot;
    private GameStats stats;
    private int times_reset;
    private int times_reset_companion;
    public boolean tolCurse;
    public boolean tolCurseMessageShown;
    private ArrayList<WorldContainer> worldContainers;
    private double last_hour_check = 0.0d;
    private double last_day_check = 0.0d;
    public float lastQuickSave = 0.0f;
    private int daysToLetterTremadan = -1;
    public boolean msg_letter_tremadan = false;
    public GameVariables gameVariables = new GameVariables();
    public ArrayList<String> deadNPCs = new ArrayList<>();
    private ArrayList<String> secrets = new ArrayList<>();
    public boolean TIP_MOVEMENT = false;
    public boolean TIP_INTERACTION = false;
    public boolean TIP_DUNGEON = false;
    public boolean TIP_ITEMS = false;
    public boolean TIP_MOVEATTACK = false;
    public boolean TIP_CHARACTER = false;
    public boolean TIP_RECOVERY = false;
    public boolean TIP_SLEEP = false;
    public boolean TIP_SAVE = false;
    public boolean TIP_RELOAD = false;
    public boolean TIP_ITEM_ATTRIBUTE = false;
    public boolean WARNING_SNEAK = false;
    public boolean WARNING_WHIRLWIND = false;
    public boolean WARNING_CLEAVE = false;
    public boolean WARNING_EVASION = false;
    public boolean WARNING_CRUSADER = false;
    public boolean WARNING_TRAITS = false;
    private boolean bagHolding = false;
    private boolean blood = false;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    public static final class GameStatus {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final GameStatus f3405b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public static final GameStatus f3406c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private static final /* synthetic */ GameStatus[] f3407d;

        static {
            GameStatus gameStatus = new GameStatus("RUNNING", 0);
            f3405b = gameStatus;
            GameStatus gameStatus2 = new GameStatus("STOPPED", 1);
            f3406c = gameStatus2;
            f3407d = new GameStatus[]{gameStatus, gameStatus2};
        }

        private GameStatus() {
            throw null;
        }

        public static GameStatus valueOf(String str) {
            return (GameStatus) Enum.valueOf(GameStatus.class, str);
        }

        public static GameStatus[] values() {
            return (GameStatus[]) f3407d.clone();
        }
    }

    private GameData() {
        if (this.party == null) {
            this.party = new Party();
        }
    }

    public static boolean I() {
        return ExiledKingdoms.f3610l == GameStatus.f3405b;
    }

    public static void K(GameData gameData) {
        f3403a = gameData;
    }

    public static void X() {
        AStarPathFinder aStarPathFinder = GameLevel.f3309a;
        if (v().player.sheet.o() > 0) {
            Serializer.D();
        }
        ExiledKingdoms.f3610l = GameStatus.f3406c;
    }

    public static void k() {
        if (f3403a != null) {
            f3403a = null;
        }
    }

    public static GameData v() {
        if (f3403a == null) {
            f3403a = new GameData();
        }
        return f3403a;
    }

    public final boolean A() {
        Items items = this.lostBackpack;
        if ((items == null || items.p().booleanValue()) && !this.CurrentLevel.equals("FT_arena")) {
            return this.bagHolding;
        }
        return false;
    }

    public final boolean B(String str) {
        if (this.innData == null) {
            this.innData = new ArrayList<>();
        }
        for (InnData innData : this.innData) {
            if (innData.id.equals(str)) {
                if (v().o() >= 20) {
                    return false;
                }
                innData.nextGossipTime = v().u() + 1080.0f;
                return true;
            }
        }
        return false;
    }

    public final boolean C(String str) {
        if (this.innData == null) {
            this.innData = new ArrayList<>();
        }
        for (InnData innData : this.innData) {
            if (innData.id.equals(str)) {
                return innData.nextGossipTime < v().u();
            }
        }
        InnData innData2 = new InnData();
        innData2.id = str;
        innData2.nextGossipTime = 0.0f;
        this.innData.add(innData2);
        return true;
    }

    public final boolean D() {
        return this.difficulty == 3;
    }

    public final boolean E() {
        int i2 = this.difficulty;
        return i2 == 1 || i2 == 2;
    }

    public final boolean F() {
        return this.difficulty == 4;
    }

    public final boolean G() {
        return this.difficulty == 2;
    }

    public final boolean H(String str) {
        ArrayList<String> arrayList = this.deadNPCs;
        if (arrayList != null) {
            return arrayList.contains(str);
        }
        this.deadNPCs = new ArrayList<>();
        return false;
    }

    public final boolean J(String str) {
        ArrayList<String> arrayList = this.secrets;
        if (arrayList != null) {
            return arrayList.contains(str);
        }
        this.secrets = new ArrayList<>();
        return false;
    }

    public final void L(String str) {
        if (this.lootedContainers == null) {
            this.lootedContainers = new ArrayList<>();
        }
        Iterator<String> it = this.lootedContainers.iterator();
        while (it.hasNext()) {
            if (it.next().equals(str)) {
                return;
            }
        }
        this.lootedContainers.add(str);
    }

    public final void M(int i2) {
        this.gameTime += (double) (i2 * 45);
    }

    public final void N() {
        for (WorldContainer worldContainer : this.worldContainers) {
            while (worldContainer.items.l(2600) > 0) {
                worldContainer.c(2600);
            }
            while (worldContainer.items.l(2601) > 0) {
                worldContainer.c(2601);
            }
        }
        while (this.backpack.l(2600) > 0) {
            this.backpack.q(2600);
        }
        while (this.backpack.l(2601) > 0) {
            this.backpack.q(2601);
        }
        while (this.lostBackpack.l(2600) > 0) {
            this.lostBackpack.q(2600);
        }
        while (this.lostBackpack.l(2601) > 0) {
            this.lostBackpack.q(2601);
        }
        this.shardsCompleted = true;
    }

    public final void O(String str) {
        if (this.dynamicEvents == null) {
            this.dynamicEvents = new ArrayList<>();
        }
        Iterator<DynamicEvent> it = v().t().iterator();
        while (it.hasNext()) {
            String str2 = it.next().location_ID;
            Locale locale = Locale.ENGLISH;
            if (str2.toLowerCase(locale).equals(str.toLowerCase(locale))) {
                it.remove();
            }
        }
    }

    public final void P() {
        if (this.party.j()) {
            this.party.f().sheet.k0();
            SkillSet skillSet = this.party.f().sheet.skillSet;
            CharacterSheet characterSheet = this.party.f().sheet;
            skillSet.r();
            this.times_reset_companion++;
            a.l().b(v().party.f().B(), a.EnumC0056a.f3885d, 0.0f);
            GameAssets.o("levelup");
        }
    }

    public final void Q() {
        this.player.sheet.skillSet.r();
        this.times_reset++;
        a.l().b(this.player.B(), a.EnumC0056a.f3885d, 0.0f);
        GameAssets.o("levelup");
    }

    public final void R() {
        this.player.sheet.k0();
        this.times_reset++;
        a.l().b(this.player.B(), a.EnumC0056a.f3885d, 0.0f);
        GameAssets.o("levelup");
    }

    public final void S() {
        this.times_reset_companion = 0;
        this.times_reset = 0;
    }

    public final int T() {
        ArrayList<String> arrayList = this.secrets;
        if (arrayList != null) {
            return arrayList.size();
        }
        this.secrets = new ArrayList<>();
        return 0;
    }

    public final void U(String str) {
        DynamicEvent dynamicEventC = DynamicEvent.c(str);
        if (dynamicEventC != null) {
            this.lastRoll = dynamicEventC.d();
        }
    }

    public final void V(boolean z2) {
        if (z2) {
            this.gameTime += 540.0d;
        } else {
            this.gameTime += 270.0d;
        }
        this.gameVariables.d();
        Y();
    }

    public final void W() {
        this.daysToLetterTremadan = 1;
    }

    public final void Y() {
        if (FDUtils.j() >= 20 || FDUtils.j() <= 8) {
            this.night = true;
        } else {
            this.night = false;
        }
    }

    public final void Z(float f2) {
        MonsterSpawn.Daycycle daycycle;
        MonsterSpawn.Daycycle daycycle2;
        double d2 = this.gameTime + ((double) f2);
        this.gameTime = d2;
        if (d2 - this.last_hour_check >= 45.0d) {
            this.last_hour_check = d2;
            for (DynamicQuest dynamicQuest : v().dynamicQuests) {
                if (v().gameVariables.b("DQ_" + dynamicQuest.DQ_id) > 0) {
                    if (v().gameVariables.b("DQ_" + dynamicQuest.DQ_id) < 100 && dynamicQuest.expirationDate.floatValue() > 0.0f && ((float) v().gameTime) > dynamicQuest.expirationDate.floatValue()) {
                        v().gameVariables.e(-100, "DQ_" + dynamicQuest.DQ_id);
                        GameConsole.a(GameString.b("QUEST_FAILED", false) + " " + dynamicQuest.f());
                        dynamicQuest.i(false);
                    }
                }
            }
            Iterator<DynamicQuest> it = v().dynamicQuests.iterator();
            while (it.hasNext()) {
                DynamicQuest next = it.next();
                if (v().gameVariables.b("DQ_" + next.DQ_id) == -5 && ((float) v().gameTime) > next.generationDate.floatValue() + 4320.0f) {
                    it.remove();
                }
            }
            z.w().W();
            if (this.dynamicEvents == null) {
                this.dynamicEvents = new ArrayList<>();
            }
            Iterator<DynamicEvent> it2 = v().t().iterator();
            while (it2.hasNext()) {
                if (((float) v().gameTime) >= it2.next().expirationDate.floatValue()) {
                    it2.remove();
                }
            }
            int iB = FDUtils.b(1, 100);
            if (this.player.sheet.stats.f() >= 11 && t().size() < 2 && iB <= 3) {
                AStarPathFinder aStarPathFinder = GameLevel.f3309a;
                DynamicEvent dynamicEvent = null;
                if (v().player.sheet.stats.f() >= 11) {
                    try {
                        DynamicEvent dynamicEvent2 = new DynamicEvent(0);
                        for (int i2 = 1; DynamicEvent.a(dynamicEvent2) && i2 < 100; i2++) {
                            dynamicEvent2 = new DynamicEvent(0);
                            System.out.println(vIBRkbZbNjpf.xnXJIByS + i2);
                        }
                        if (!DynamicEvent.b(dynamicEvent2.location_ID)) {
                            dynamicEvent = dynamicEvent2;
                        }
                    } catch (NullPointerException unused) {
                        System.out.println("WARNING: null dynamic event");
                    }
                }
                if (dynamicEvent != null) {
                    t().add(dynamicEvent);
                    GameLog gameLog = this.log;
                    if (gameLog != null) {
                        String strE = dynamicEvent.e();
                        gameLog.a(strE);
                        try {
                            String str = ">>> [World Event] " + strE.replace("[BLUE]", "").replace("[BLACK]", "").replace("[]", "");
                            LanSessionManager instanceIfReady = LanSessionManager.getInstanceIfReady();
                            if (instanceIfReady != null && instanceIfReady.isSessionRunning()) {
                                instanceIfReady.sendChatAsync(str);
                            }
                        } catch (Exception e2) {
                        }
                    }
                }
            }
            boolean z2 = this.night;
            Y();
            if (b.P().f4016h && z2 != this.night) {
                GameLevelData gameLevelDataO = GameLevelData.o();
                gameLevelDataO.getClass();
                boolean z3 = v().night;
                Iterator<NPC> it3 = gameLevelDataO.npcs.iterator();
                while (true) {
                    boolean zHasNext = it3.hasNext();
                    daycycle = MonsterSpawn.Daycycle.f3236d;
                    daycycle2 = MonsterSpawn.Daycycle.f3235c;
                    if (!zHasNext) {
                        break;
                    }
                    NPC next2 = it3.next();
                    if (z3 && next2.K1() == daycycle2) {
                        next2.L();
                    }
                    if (!z3 && next2.K1() == daycycle) {
                        next2.L();
                    }
                }
                for (MonsterSpawn monsterSpawn : gameLevelDataO.spawns) {
                    boolean z4 = z3 && monsterSpawn.J() == daycycle && monsterSpawn.I();
                    if (!z3 && monsterSpawn.J() == daycycle2 && monsterSpawn.I()) {
                        z4 = true;
                    }
                    if (z4) {
                        Iterator<NPC> it4 = gameLevelDataO.npcs.iterator();
                        while (it4.hasNext()) {
                            if (it4.next().L1() == monsterSpawn.q()) {
                                z4 = false;
                            }
                        }
                    }
                    if (z4) {
                        monsterSpawn.Q();
                    }
                }
            }
        }
        double d3 = this.gameTime;
        if (d3 - this.last_day_check >= 1080.0d) {
            this.last_day_check = d3;
            if (this.daysToLetterTremadan == -1) {
                if (this.gameVariables.b("ark_lothasan") == 10) {
                    this.daysToLetterTremadan = 3;
                    return;
                }
                return;
            }
            if (this.gameVariables.b("ark_lothasan") != 10) {
                this.daysToLetterTremadan = -1;
                return;
            }
            if (this.gameVariables.b("ark_lothasan") == 10 && this.daysToLetterTremadan == -1) {
                this.daysToLetterTremadan = 7;
                return;
            }
            int i3 = this.daysToLetterTremadan;
            if (i3 > 0) {
                this.daysToLetterTremadan = i3 - 1;
            } else if (i3 == 0 && this.gameVariables.b("ark_lothasan") == 10) {
                this.daysToLetterTremadan = -1;
                this.msg_letter_tremadan = true;
            }
        }
    }

    public final void a() {
        this.player.sheet.skillSet.e();
        this.times_reset++;
        a.l().b(this.player.B(), a.EnumC0056a.f3885d, 0.0f);
        GameAssets.o("levelup");
    }

    public final void b(PlayerCreation playerCreation, int i2) {
        this.introMessageShown = true;
        f3404b = true;
        ExiledKingdoms.f3610l = GameStatus.f3406c;
        this.gameVariables = new GameVariables();
        this.lastDynamicQuest = 0;
        this.dynamicQuests = new ArrayList<>();
        this.party = new Party();
        this.backpack = new Items();
        this.castleData = new ArrayList<>();
        this.player = null;
        this.player = new Player(playerCreation);
        this.gameTime = 810.0d;
        this.realTime = 0.0d;
        this.log = new GameLog();
        this.currentMapName = "";
        b.H();
        GameLevelData.o().H();
        this.slot = i2;
        Serializer.f(i2);
        GameConsole.d();
        this.lootedContainers = new ArrayList<>();
        this.difficulty = playerCreation.difficulty;
        this.TIP_MOVEMENT = true;
        this.TIP_INTERACTION = true;
        this.TIP_MOVEATTACK = true;
        this.TIP_CHARACTER = true;
        this.TIP_RECOVERY = true;
        this.TIP_SLEEP = true;
        this.TIP_SAVE = true;
        this.TIP_ITEMS = true;
        this.TIP_DUNGEON = true;
    }

    public final void c() {
        int i2 = this.difficulty;
        if (i2 == 0) {
            this.difficulty = 3;
            return;
        }
        if (i2 == 1) {
            this.difficulty = 0;
        } else if (i2 == 2) {
            this.difficulty = 1;
        } else {
            if (i2 != 3) {
                return;
            }
            this.difficulty = 4;
        }
    }

    public final boolean d(String str) {
        GameVariables gameVariables = this.gameVariables;
        StringBuilder sb = new StringBuilder("EXP_");
        sb.append(str);
        return gameVariables.b(sb.toString()) > 0;
    }

    public final boolean e() {
        if (!this.blood && (this.gameVariables.b("blood1") != 1 || this.gameVariables.b(RCsZWh.OgLGbqHUlrFA) != 1 || this.gameVariables.b("blood3") != 1)) {
            return false;
        }
        this.blood = true;
        return true;
    }

    public final void f() {
        if (this.dynamicEvents == null) {
            this.dynamicEvents = new ArrayList<>();
        }
        Iterator<DynamicEvent> it = v().t().iterator();
        while (it.hasNext()) {
            if (((float) v().gameTime) >= it.next().expirationDate.floatValue()) {
                it.remove();
            }
        }
    }

    public final boolean g(String str) {
        if (this.lootedContainers == null) {
            this.lootedContainers = new ArrayList<>();
            return false;
        }
        if (str.equals("")) {
            return false;
        }
        Iterator<String> it = this.lootedContainers.iterator();
        while (it.hasNext()) {
            if (it.next().equals(str)) {
                return true;
            }
        }
        return false;
    }

    public final int getDifficulty() {
        return this.difficulty;
    }

    public final int h() {
        int i2 = 0;
        for (DynamicQuest dynamicQuest : this.dynamicQuests) {
            if (this.gameVariables.b("DQ_" + dynamicQuest.DQ_id) > 99) {
                i2++;
            }
        }
        return i2;
    }

    public final int i() {
        Iterator<Quest> it = Quests.f3417a.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            if (this.gameVariables.b(it.next().ID) > 99) {
                i2++;
            }
        }
        return i2;
    }

    public final void j(String str) {
        if (this.secrets == null) {
            this.secrets = new ArrayList<>();
        }
        if (J(str)) {
            return;
        }
        this.secrets.add(str);
        this.log.a(GameString.b("DISCOVERED_SECRET", false));
    }

    public final void l() {
        this.gameTime += 90.0d;
        Y();
    }

    public final void m() {
        this.bagHolding = true;
    }

    public final String n() {
        return this.difficulty == 0 ? GameString.b("MESSAGE_ITEM_COOLDOWN_NORMAL", false) : GameString.b("MESSAGE_ITEM_COOLDOWN", false);
    }

    public final int o() {
        int i2 = this.lastRoll;
        if (i2 >= 999) {
            this.lastRoll = 0;
        } else {
            this.lastRoll = i2 + 1;
        }
        return FDUtils.i(this.lastRoll);
    }

    public final String p() {
        String strO = a.a.o("DIFFICULTY_LEVEL", false, new StringBuilder(), ": ");
        if (this.difficulty == 0) {
            strO = a.a.n("DL_NORMAL", false, a.a.r(strO));
        }
        if (this.difficulty == 1) {
            strO = a.a.n("DL_HARD", false, a.a.r(strO));
        }
        if (this.difficulty == 2) {
            strO = a.a.n("DL_IRONMAN", false, a.a.r(strO));
        }
        if (this.difficulty == 3) {
            strO = a.a.n("DL_CASUAL", false, a.a.r(strO));
        }
        return this.difficulty == 4 ? a.a.n("DL_STORY", false, a.a.r(strO)) : strO;
    }

    public final String q() {
        if (E()) {
            return p() + "(+20%)";
        }
        int i2 = this.difficulty;
        if (i2 == 3) {
            return p() + "(-50%)";
        }
        if (i2 == 4) {
            return p() + "(-80%)";
        }
        return p() + "(+0%)";
    }

    public final String r() {
        String strB = this.difficulty == 0 ? GameString.b("DL_NORMAL", false) : "";
        if (this.difficulty == 1) {
            strB = GameString.b("DL_HARD", false);
        }
        if (this.difficulty == 2) {
            strB = GameString.b("DL_IRONMAN", false);
        }
        if (this.difficulty == 3) {
            strB = GameString.b("DL_CASUAL", false);
        }
        return this.difficulty == 4 ? GameString.b("DL_STORY", false) : strB;
    }

    public final void raiseDifficulty() {
        int i2 = this.difficulty;
        if (i2 == 4) {
            this.difficulty = 3;
            return;
        }
        if (i2 == 3) {
            this.difficulty = 0;
        } else if (i2 == 0) {
            this.difficulty = 1;
        } else if (i2 == 1) {
            this.difficulty = 2;
        }
    }

    public final double s() {
        return this.gameTime;
    }

    public final void setDifficulty(int i2) {
        this.difficulty = i2;
    }

    public final ArrayList<DynamicEvent> t() {
        if (this.dynamicEvents == null) {
            this.dynamicEvents = new ArrayList<>();
        }
        return this.dynamicEvents;
    }

    public final float u() {
        return (float) this.gameTime;
    }

    public final int w() {
        return (this.times_reset_companion * 2000) + 5000;
    }

    public final int x() {
        return (this.times_reset * GamesStatusCodes.STATUS_SNAPSHOT_NOT_FOUND) + 2000;
    }

    public final GameStats y() {
        if (this.stats == null) {
            this.stats = new GameStats();
        }
        return this.stats;
    }

    public final WorldContainer z(String str) {
        if (this.worldContainers == null) {
            this.worldContainers = new ArrayList<>();
        }
        for (WorldContainer worldContainer : this.worldContainers) {
            if (worldContainer.id.equals(str)) {
                return worldContainer;
            }
        }
        WorldContainer worldContainer2 = new WorldContainer();
        worldContainer2.items = new Items();
        worldContainer2.gold = 0;
        worldContainer2.id = str;
        this.worldContainers.add(worldContainer2);
        if (str.equals(DkgvDLHsdXPkn.kJlZvfJvyxlFd)) {
            worldContainer2.i(3204);
            worldContainer2.i(5020);
            worldContainer2.i(5020);
            worldContainer2.i(5021);
        }
        return worldContainer2;
    }
}
