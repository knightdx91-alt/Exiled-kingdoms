package net.fdgames.Helpers;

import a.f;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.utils.WHW.gNaSiQJmMEn;
import com.google.android.gms.internal.p000authapi.KJ.ZeMa;
import java.util.Iterator;
import l0.KUbz.MbzYTTrg;
import net.fdgames.GameEntities.AI.Pathfinding.AStarPathFinder;
import net.fdgames.GameEntities.CharacterSheet.CharacterSheet;
import net.fdgames.GameEntities.Final.NPC;
import net.fdgames.GameEntities.Helpers.SkillSet;
import net.fdgames.GameLevel.GameLevel;
import net.fdgames.GameLevel.GameLevelData;
import net.fdgames.GameWorld.GameData;
import net.fdgames.GameWorld.GameVariables;
import net.fdgames.GameWorld.GameWorld;
import net.fdgames.Rules.zrcH.pgtXvpMCFu;
import net.fdgames.TiledMap.Objects.Transition;
import net.fdgames.ek.Settings;
import z0.ow.DkgvDLHsdXPkn;
import z0.z;

/* JADX INFO: loaded from: /tmp/tmp.CKdUvKN3M2/classes.dex */
public class Patching {
    public static void a(int i2) {
        NPC next;
        int iB;
        if (i2 < 822) {
            GameData.v().player.sheet.skillSet.b();
            Iterator<NPC> it = GameLevelData.o().npcs.iterator();
            while (it.hasNext()) {
                it.next().sheet.skillSet.b();
            }
            Iterator<NPC> it2 = GameData.v().party.companions.iterator();
            while (it2.hasNext()) {
                it2.next().sheet.skillSet.b();
            }
        }
        if (i2 < 839) {
            if (GameData.v().gameVariables.b("ant_hunt") < 100) {
                GameData.v().gameVariables.e(0, "ant_hunt");
            }
            if (!Settings.t().booleanValue() && !f.a(GameData.v().CurrentLevel)) {
                GameData.v().NewArea = new Transition("H10", 999);
            }
            GameConsole.d();
        }
        if (i2 < 887 && GameData.v().player.sheet.skillSet.g("whirlwind") > 0) {
            GameData.v().player.sheet.skillSet.q("whirlwind");
            AStarPathFinder aStarPathFinder = GameLevel.f3309a;
            GameData.v().player.c2();
            GameData.v().WARNING_WHIRLWIND = true;
            z.w().a0();
        }
        if (i2 < 889 && GameData.v().player.sheet.skillSet.g("cleave") > 0) {
            GameData.v().player.sheet.skillSet.q("cleave");
            AStarPathFinder aStarPathFinder2 = GameLevel.f3309a;
            GameData.v().player.c2();
            GameData.v().WARNING_CLEAVE = true;
            z.w().a0();
        }
        if (i2 < 896 && GameData.v().player.sheet.skillSet.g("evasion") > 0) {
            GameData.v().player.sheet.skillSet.q("evasion");
            AStarPathFinder aStarPathFinder3 = GameLevel.f3309a;
            GameData.v().player.c2();
            GameData.v().WARNING_EVASION = true;
            z.w().a0();
        }
        if (i2 < 902 && GameData.v().player.sheet.skillSet.g("crusader") == 3) {
            GameData.v().player.sheet.skillSet.q("crusader");
            GameData.v().WARNING_CRUSADER = true;
        }
        if (i2 < 911) {
            SkillSet skillSet = GameData.v().player.sheet.skillSet;
            String str = MbzYTTrg.mSnOZVrNL;
            if (skillSet.g(str) > 0) {
                GameData.v().player.sheet.skillSet.q(str);
                GameData.v().WARNING_SNEAK = true;
            }
            GameVariables gameVariables = GameData.v().gameVariables;
            String str2 = gNaSiQJmMEn.DWqSolHjEWTwhAr;
            if (gameVariables.b(str2) == -100) {
                GameData.v().gameVariables.e(200, str2);
            }
        }
        if (i2 < 913) {
            GameData.v().S();
            if (GameData.v().gameVariables.b(pgtXvpMCFu.CujfjBcZd) == 1 && GameData.v().gameVariables.b("smugglers_night") < 100) {
                GameData.v().gameVariables.e(100, "smugglers_night");
            }
            GameConsole.d();
        }
        if (i2 > 953 && i2 < 961) {
            GameData.v().player.sheet.d0();
            GameData.v().WARNING_TRAITS = true;
        }
        if (i2 < 964 && GameData.v().gameVariables.b(ZeMa.zSnGMF) == 100) {
            GameWorld.f3410c.a(5, 999, "warriorsguild");
            GameData.v().player.B1(500);
            GameData.v().log.c(500);
        }
        if (i2 < 977) {
            GameData.v().gameVariables.e(0, DkgvDLHsdXPkn.UnCnoKqWyn);
            if (GameData.v().gameVariables.b("shards_fate") > 100) {
                GameData.v().gameVariables.e(100, "shards_fate");
            }
            if (GameData.v().gameVariables.b("shards_fate") > 22 && GameData.v().gameVariables.b("shards_fate") < 100) {
                GameData.v().gameVariables.e(22, "shards_fate");
            }
        }
        if (i2 < 978 && GameData.v().gameVariables.b("shards_fate") > 21) {
            GameData.v().shardsCompleted = true;
        }
        if (i2 < 987 && GameData.v().gameVariables.b("snake_gang") > 99 && GameData.v().party.k().booleanValue()) {
            GameData.v().party.d("H6_snake");
        }
        if (i2 < 1003 && GameData.v().gameVariables.b("ark_lothasan") == 10) {
            GameData.v().W();
        }
        if (i2 < 1017 && GameData.v().gameVariables.b("know_lifeextension") == 1) {
            GameData.v().gameVariables.e(0, "know_lifeextension");
            GameData.v().player.sheet.getClass();
            CharacterSheet.b(2621);
        }
        b();
        if (i2 < 1024 && GameData.v().gameVariables.b("ark_lothasan") == 100) {
            while (true) {
                AStarPathFinder aStarPathFinder4 = GameLevel.f3309a;
                if (GameData.v().player.sheet.Q(2622) <= 0) {
                    break;
                } else {
                    GameData.v().player.sheet.b0(2622);
                }
            }
        }
        if (i2 < 1054 && (iB = GameData.v().gameVariables.b("REP_whitetower")) > 0) {
            GameData.v().gameVariables.e(iB, "REP_town_whitetower");
            GameData.v().gameVariables.e(0, "REP_whitetower");
            GameConsole.d();
        }
        if (i2 < 1057) {
            int iB2 = GameData.v().gameVariables.b("REP_town_whitetower");
            int iB3 = GameData.v().gameVariables.b("pig_wife");
            if (iB2 <= 0 && (iB3 == 100 || iB3 == 120)) {
                GameData.v().gameVariables.e(3, "REP_town_whitetower");
                GameConsole.d();
            }
        }
        if (i2 < 1103) {
            if (GameData.v().gameVariables.b("ancient_seal") == 100) {
                Iterator<NPC> it3 = GameData.v().party.companions.iterator();
                while (true) {
                    if (!it3.hasNext()) {
                        next = null;
                        break;
                    } else {
                        next = it3.next();
                        if (next.spawn_id.equals("hirge")) {
                            break;
                        }
                    }
                }
                if (next != null) {
                    next.sheet.f();
                    next.sheet.f();
                    next.N0();
                    next.N0();
                    next.N0();
                }
            }
            GameConsole.d();
        }
        b();
    }

    private static void b() {
        if (GameData.v().gameVariables.b("web_terror") != 50 || GameData.v().CurrentLevel.equals("E9_cave")) {
            return;
        }
        Gdx.files.local(Serializer.e(GameData.v().slot)).deleteDirectory();
        GameData.v().gameVariables.e(30, "web_terror");
    }
}
