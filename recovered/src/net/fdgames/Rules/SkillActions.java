package net.fdgames.Rules;

import a.a;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import java.util.Iterator;
import k0.a;
import m0.b;
import n0.w;
import net.fdgames.GameEntities.AI.Pathfinding.AStarPathFinder;
import net.fdgames.GameEntities.Character;
import net.fdgames.GameEntities.CharacterSheet.CharacterEffects;
import net.fdgames.GameEntities.CharacterSheet.CharacterResistances;
import net.fdgames.GameEntities.CharacterSheet.CharacterSheet;
import net.fdgames.GameEntities.Final.NPC;
import net.fdgames.GameEntities.Final.SecretDoor;
import net.fdgames.GameEntities.Final.Trap;
import net.fdgames.GameEntities.Helpers.DamageEffect;
import net.fdgames.GameLevel.GameLevel;
import net.fdgames.GameLevel.GameLevelData;
import net.fdgames.GameWorld.Follower;
import net.fdgames.GameWorld.GameData;
import net.fdgames.GameWorld.GameLog;
import net.fdgames.GameWorld.GameWorld;
import net.fdgames.Helpers.FDUtils;
import net.fdgames.Helpers.GameString;
import net.fdgames.TiledMap.Objects.Transition;
import net.fdgames.assets.AnimationLoader;
import net.fdgames.assets.GameAssets;
import net.fdgames.ek.Settings;

/* JADX INFO: loaded from: /tmp/claude-0/-home-user-Exiled-kingdoms/9d29ecaf-a4c0-5173-a278-bc8785ca37a9/scratchpad/jadxwork/../extracted_dex/classes.dex */
public class SkillActions {
    public static float a(CharacterSheet characterSheet) {
        int iG = characterSheet.skillSet.g("gossip");
        if (iG == 1) {
            return 0.97f;
        }
        if (iG == 2) {
            return 0.94f;
        }
        return iG == 3 ? 0.91f : 1.0f;
    }

    public static void b(Character character, boolean z2) {
        SecretDoor.SecretDoorState secretDoorState;
        int i2 = z2 ? 95 : 75;
        int i3 = z2 ? 640 : 320;
        Iterator<SecretDoor> it = GameLevelData.o().secretDoors.iterator();
        while (true) {
            boolean zHasNext = it.hasNext();
            secretDoorState = SecretDoor.SecretDoorState.f3034a;
            if (!zHasNext) {
                break;
            }
            SecretDoor next = it.next();
            if (next.I() == secretDoorState && b.s(next.B(), character.B()) < i3 && FDUtils.b(1, 100) < i2) {
                next.G(i2, character.getName());
            }
        }
        Iterator<SecretDoor> it2 = GameLevelData.o().secretDoors.iterator();
        int i4 = 0;
        while (it2.hasNext()) {
            if (it2.next().I() == secretDoorState) {
                i4++;
            }
        }
        GameLevelData.o();
        for (Trap trap : GameLevelData.z()) {
            if (!trap.discovered && b.s(trap.B(), character.B()) < i3 && FDUtils.b(1, 100) < i2) {
                trap.O(i2, character.getName());
            }
        }
        if (i4 > 0) {
            if (!z2) {
                GameLog gameLog = GameData.v().log;
                StringBuilder sb = new StringBuilder();
                a.w("DOORS_REMAINING_1", false, sb, " [BLUE]some[] ");
                sb.append(GameString.b("DOORS_REMAINING_2", false));
                gameLog.a(sb.toString());
                return;
            }
            GameData.v().log.a(GameString.b("DOORS_REMAINING_1", false) + " [BLUE]" + i4 + "[] " + GameString.b("DOORS_REMAINING_2", false));
        }
    }

    public static void c(Character character, Character character2) {
        int i2;
        int i3;
        int iG = character.sheet.skillSet.g("heal_wounds");
        CharacterSheet characterSheet = character.sheet;
        int i4 = characterSheet.stats.missingHP;
        int i5 = iG == 1 ? 30 : 0;
        if (iG == 2) {
            i5 = 75;
        }
        if (iG == 3) {
            i5 = 125;
        }
        if (iG == 4) {
            i5 = 180;
        }
        characterSheet.skillSet.s("heal_wounds");
        if (i4 > 0) {
            character.V0(i5);
            i5 -= i4;
        }
        if (i5 <= 0) {
            return;
        }
        int i6 = character2.sheet.stats.missingHP;
        if (i6 > 0) {
            character2.V0(i5);
            i5 -= i6;
        }
        if (i5 <= 0) {
            return;
        }
        if (character.k0() || character.q() == 1) {
            AStarPathFinder aStarPathFinder = GameLevel.f3094a;
            int i7 = GameData.v().player.sheet.stats.missingHP;
            if (i7 > 0) {
                GameData.v().player.V0(i5);
                i5 -= i7;
            }
            if (i5 <= 0) {
                return;
            }
            if (GameData.v().party.j() && (i3 = GameData.v().party.f().sheet.stats.missingHP) > 0) {
                GameData.v().party.f().V0(i5);
                i5 -= i3;
            }
            if (GameData.v().party == null || GameData.v().party.followers == null) {
                return;
            }
            for (Follower follower : GameData.v().party.followers) {
                if (i5 <= 0 || follower == null || follower.a() == null || follower.a().tag == null) {
                    return;
                }
                NPC npcJ = GameLevel.j(follower.a().tag);
                if (npcJ != null && (i2 = npcJ.sheet.stats.missingHP) > 0) {
                    npcJ.V0(i5);
                    i5 -= i2;
                }
            }
        }
    }

    public static void d(Character character) {
        if (character.sheet.d() || character.sheet.skillSet.g("fury") <= 0 || character.sheet.r() <= 0.0f) {
            character.sheet.effects.fury = Boolean.FALSE;
            return;
        }
        boolean zBooleanValue = character.sheet.effects.fury.booleanValue();
        int iG = character.sheet.skillSet.g("fury");
        float fR = character.sheet.r();
        CharacterEffects characterEffects = character.sheet.effects;
        characterEffects.fury = Boolean.TRUE;
        if (iG == 1 && fR >= 0.7f) {
            characterEffects.furyMultiplier = 1.3f;
        } else if (iG == 2 && fR >= 0.65f) {
            characterEffects.furyMultiplier = 1.5f;
        } else if (iG != 3 || fR < 0.65f) {
            characterEffects.fury = Boolean.FALSE;
            characterEffects.furyBonus = 0;
            characterEffects.furyMultiplier = 1.0f;
        } else {
            characterEffects.furyMultiplier = 1.65f;
        }
        if (!characterEffects.fury.booleanValue() || zBooleanValue) {
            return;
        }
        k0.a.l().a(new w(character.q(), "FURY!", 1.0f, Color.BLUE, 1.2f, 0.7f));
    }

    public static void e(Character character) {
        int iG = character.sheet.skillSet.g("flurry");
        if (iG == 1 || iG == 2 || iG == 3) {
            character.c1();
        }
        character.sheet.skillSet.s("flurry");
        k0.a.l().a(new w(character.q(), "Flurry!", 0.7f, Color.GREEN, 1.0f, 0.7f));
    }

    public static DamageEffect f(int i2) {
        if (i2 <= 0) {
            return null;
        }
        DamageEffect.EffectType effectType = DamageEffect.EffectType.f3054a;
        if (i2 == 1) {
            return new DamageEffect(effectType, 1, 2);
        }
        if (i2 == 2) {
            return new DamageEffect(effectType, 1, 4);
        }
        if (i2 == 3) {
            return new DamageEffect(effectType, 1, 5);
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0037  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static Transition g() {
        String str = GameData.v().CurrentLevel;
        Transition transition = new Transition();
        transition.entry_id = 999;
        transition.area_id = "";
        if (!str.contains("E9_cave")) {
            if (str.equals("G7_tomb")) {
                AStarPathFinder aStarPathFinder = GameLevel.f3094a;
                if (GameData.v().player.sheet.Q(2618) <= 0) {
                    if (!b.P().f2427l) {
                        if (str.contains("H10") || str.contains("H11") || str.contains("I11") || str.contains("I10") || str.contains("I9") || str.contains("G10")) {
                            transition.area_id = "H10";
                        } else if (str.contains("F9")) {
                            transition.area_id = "G9";
                        } else if (str.contains("F8") || str.contains("E8")) {
                            transition.area_id = "F8";
                        } else if (str.contains("G9") || str.contains("H9")) {
                            transition.area_id = "G9";
                        } else if (str.contains("G11") || str.contains("F11")) {
                            transition.area_id = "NI";
                        } else if (str.contains("F10")) {
                            transition.area_id = "F10";
                        } else if (str.contains("E10") || str.contains("E9")) {
                            transition.area_id = "E10";
                        } else if (str.contains("G8") || str.contains("G7") || str.contains("H8") || str.contains("I8")) {
                            transition.area_id = "NG";
                        } else if (str.contains("D9") || str.contains("D8") || str.contains("D10") || str.contains("C10")) {
                            transition.area_id = "FT";
                        } else if (str.contains("J8")) {
                            transition.area_id = "J7";
                        } else if (str.contains("J9") || str.contains("J10") || str.contains("J11")) {
                            transition.area_id = "J10";
                        } else if (str.contains("K11") || str.contains("J12")) {
                            transition.area_id = "K11";
                        } else if (str.contains("D11") || str.contains("E11") || str.contains("E12") || str.contains("D12") || str.contains("C11") || str.contains("C12")) {
                            transition.area_id = "D11";
                        } else if (str.contains("H7") || str.contains("H6") || str.contains("G6") || str.contains("F6") || str.contains("H5") || str.contains("H4") || str.contains("H3") || str.contains("I3")) {
                            transition.area_id = "H6";
                        } else if (str.contains("I13")) {
                            transition.area_id = "I13";
                        } else if (str.contains("H13") || str.contains("G13") || str.contains("I12") || str.contains("H12")) {
                            transition.area_id = "H12";
                        } else if (str.contains("D13") || str.contains("C13") || str.contains("C12")) {
                            transition.area_id = "C13";
                        } else if (str.contains("J7") || str.contains("K7")) {
                            transition.area_id = "J7";
                        }
                        return transition;
                    }
                }
            }
        }
        return transition;
    }

    public static void h(Character character) {
        int i2;
        int i3;
        if (character.Z0(character.sheet.skillSet.f("holy_shield"), 0.5f)) {
            int iG = character.sheet.skillSet.g("holy_shield");
            if (iG == 1) {
                i2 = 25;
                i3 = 4;
            } else {
                i2 = 0;
                i3 = 0;
            }
            if (iG == 2) {
                i2 = 40;
                i3 = 6;
            }
            if (iG == 3) {
                i2 = 60;
                i3 = 8;
            }
            if (iG == 4) {
                i2 = 70;
                i3 = 10;
            }
            character.W0(i3);
            character.q1(CharacterResistances.ResistanceType.f2998d, i2, 12.0f);
            character.sheet.skillSet.s("holy_shield");
        }
    }

    public static void i(Character character) {
        int iG = character.sheet.skillSet.g("rapid_fire");
        if (iG == 1 || iG == 2 || iG == 3) {
            character.e1();
        }
        character.sheet.skillSet.s("rapid_fire");
    }

    public static void j(Character character) {
        int iG = character.sheet.skillSet.g("resilience");
        if (iG == 1) {
            character.t1(5, 6.0f);
        }
        if (iG == 2) {
            character.t1(9, 8.0f);
        }
        if (iG == 3) {
            character.t1(14, 10.0f);
        }
        character.sheet.skillSet.s("resilience");
    }

    public static void k(Character character) {
        int iG = character.sheet.skillSet.g("stab");
        int iF = character.sheet.stats.f();
        if (iG == 1) {
            character.w1(iF + 6);
        } else if (iG == 2) {
            character.w1(iF + 10);
        } else if (iG == 3) {
            character.w1(Math.round(iF * 1.4f) + 10);
        } else if (iG == 4) {
            character.w1(Math.round(iF * 1.8f) + 10);
        }
        character.sheet.skillSet.s("stab");
    }

    public static void l(Character character, String str, int i2, int i3) {
        if (Rules.i(str) == null) {
            System.out.println("WARNING: null spawn for id:" + str);
            Settings.A(1, "GL_spawnerror_" + str);
            return;
        }
        int iMin = Math.min(i2, character.sheet.stats.f());
        Spawn spawn = new Spawn(Rules.i(str));
        AnimationLoader.a(str);
        NPC npc = new NPC(spawn);
        npc.tag = Integer.toHexString(FDUtils.b(1, CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT));
        npc.f3092x = character.f3092x;
        npc.f3093y = character.f3093y;
        GameLevel.a(npc);
        npc.B1();
        npc.visibleToPlayer = Boolean.TRUE;
        int iG = character.sheet.skillSet.g("summoner") > 0 ? character.sheet.skillSet.g("summoner") : 0;
        if (iMin > 0) {
            npc.sheet.i0(iMin + iG);
        }
        if (character.q() == 1 || character.k0()) {
            GameData.v().party.e();
            npc.tag = "player_summon";
            npc.C1();
        } else {
            npc.y(character.r());
        }
        npc.respawned = true;
        npc.summoned = true;
        npc.dismissTime = (int) (GameData.v().u() + i3);
        npc.sheet.attributes.outsider = true;
        k0.a.l().a(new w(npc.q(), GameString.b("SUMMONED", false), 1.2f, Color.BLUE, 0.8f, 0.8f));
        k0.a aVarL = k0.a.l();
        a.EnumC0031a enumC0031a = a.EnumC0031a.f2303o;
        aVarL.getClass();
        aVarL.b(npc.B(), enumC0031a, 1.0f).owner = npc;
        GameAssets.o("spell1");
    }

    public static void m(Character character) {
        GameAssets.o("spell1");
        for (NPC npc : GameLevelData.t()) {
            if (!npc.destroy && npc.sheet.P("undead") && b.s(npc.B(), character.B()) <= 128 && GameWorld.f3189c.g(npc.r(), character.r())) {
                npc.z1(3);
            }
        }
        k0.a.l().d(1.0f, character.f3092x, character.f3093y, VertexAttributes.Usage.Tangent, "flash_red");
    }

    public static void n(Character character) {
        int iG;
        if (character.SkillUseData_consecutive_hits <= 1 || (iG = character.sheet.skillSet.g("duel")) == 0) {
            return;
        }
        if (iG == 1) {
            CharacterEffects characterEffects = character.sheet.effects;
            int i2 = character.SkillUseData_consecutive_hits - 1;
            characterEffects.duelbonus = i2;
            if (i2 < 1) {
                characterEffects.duelbonus = 1;
            }
            if (characterEffects.duelbonus > 4) {
                characterEffects.duelbonus = 4;
            }
        }
        if (iG == 2) {
            CharacterEffects characterEffects2 = character.sheet.effects;
            int i3 = (character.SkillUseData_consecutive_hits - 1) * 2;
            characterEffects2.duelbonus = i3;
            if (i3 < 2) {
                characterEffects2.duelbonus = 2;
            }
            if (characterEffects2.duelbonus > 6) {
                characterEffects2.duelbonus = 6;
            }
        }
        if (iG == 3) {
            CharacterEffects characterEffects3 = character.sheet.effects;
            int i4 = (character.SkillUseData_consecutive_hits - 1) * 2;
            characterEffects3.duelbonus = i4;
            if (i4 < 2) {
                characterEffects3.duelbonus = 2;
            }
            if (characterEffects3.duelbonus > 10) {
                characterEffects3.duelbonus = 10;
            }
        }
        CharacterEffects characterEffects4 = character.sheet.effects;
        if (characterEffects4.duelbonus > 0) {
            characterEffects4.duel = Boolean.TRUE;
        } else {
            characterEffects4.duel = Boolean.FALSE;
        }
        k0.a aVarL = k0.a.l();
        int iQ = character.q();
        StringBuilder sb = new StringBuilder();
        a.a.v("duel", " +", sb);
        sb.append(character.sheet.effects.duelbonus);
        aVarL.a(new w(iQ, sb.toString(), 1.0f, Color.YELLOW, 1.5f, 0.7f));
    }
}
