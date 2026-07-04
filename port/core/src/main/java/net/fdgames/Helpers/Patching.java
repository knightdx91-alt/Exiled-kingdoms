package net.fdgames.Helpers;

import com.badlogic.gdx.Gdx;
import java.util.Iterator;
import n0.z;
import net.fdgames.GameEntities.AI.Pathfinding.AStarPathFinder;
import net.fdgames.GameEntities.CharacterSheet.CharacterSheet;
import net.fdgames.GameEntities.Final.NPC;
import net.fdgames.GameLevel.GameLevel;
import net.fdgames.GameLevel.GameLevelData;
import net.fdgames.GameWorld.GameData;
import net.fdgames.GameWorld.GameWorld;
import net.fdgames.ek.ControllerConfig;
import net.fdgames.ek.ExiledKingdoms;
import net.fdgames.ek.Settings;

/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
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
            ControllerConfig controllerConfig = Settings.f3418a;
            ExiledKingdoms.f3371a = true;
            GameConsole.d();
        }
        if (i2 < 887 && GameData.v().player.sheet.skillSet.g("whirlwind") > 0) {
            GameData.v().player.sheet.skillSet.q("whirlwind");
            AStarPathFinder aStarPathFinder = GameLevel.f3094a;
            GameData.v().player.b2();
            GameData.v().WARNING_WHIRLWIND = true;
            z.v().Y();
        }
        if (i2 < 889 && GameData.v().player.sheet.skillSet.g("cleave") > 0) {
            GameData.v().player.sheet.skillSet.q("cleave");
            AStarPathFinder aStarPathFinder2 = GameLevel.f3094a;
            GameData.v().player.b2();
            GameData.v().WARNING_CLEAVE = true;
            z.v().Y();
        }
        if (i2 < 896 && GameData.v().player.sheet.skillSet.g("evasion") > 0) {
            GameData.v().player.sheet.skillSet.q("evasion");
            AStarPathFinder aStarPathFinder3 = GameLevel.f3094a;
            GameData.v().player.b2();
            GameData.v().WARNING_EVASION = true;
            z.v().Y();
        }
        if (i2 < 902 && GameData.v().player.sheet.skillSet.g("crusader") == 3) {
            GameData.v().player.sheet.skillSet.q("crusader");
            GameData.v().WARNING_CRUSADER = true;
        }
        if (i2 < 911) {
            if (GameData.v().player.sheet.skillSet.g("sneak_attack") > 0) {
                GameData.v().player.sheet.skillSet.q("sneak_attack");
                GameData.v().WARNING_SNEAK = true;
            }
            if (GameData.v().gameVariables.b("where_is_giles") == -100) {
                GameData.v().gameVariables.e(200, "where_is_giles");
            }
        }
        if (i2 < 913) {
            GameData.v().S();
            if (GameData.v().gameVariables.b("guild_seventh") == 1 && GameData.v().gameVariables.b("smugglers_night") < 100) {
                GameData.v().gameVariables.e(100, "smugglers_night");
            }
            GameConsole.d();
        }
        if (i2 > 953 && i2 < 961) {
            GameData.v().player.sheet.k0();
            GameData.v().player.sheet.d0();
            GameData.v().WARNING_TRAITS = true;
        }
        if (i2 < 964 && GameData.v().gameVariables.b("restless_dead") == 100) {
            GameWorld.f3189c.a(5, 999, "warriorsguild");
            GameData.v().player.B1(500);
            GameData.v().log.c(500);
        }
        if (i2 < 977) {
            GameData.v().gameVariables.e(0, "ank_ni_bishop");
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
                AStarPathFinder aStarPathFinder4 = GameLevel.f3094a;
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
