package net.fdgames.GameEntities.AI;

import com.google.android.datatransport.fJ.lxYOBQSyWPCj;
import net.fdgames.GameEntities.AI.Pathfinding.AStarPathFinder;
import net.fdgames.GameEntities.Character;
import net.fdgames.GameEntities.Final.NPC;
import net.fdgames.GameEntities.Helpers.SkillSet;
import net.fdgames.GameEntities.MapActor;
import net.fdgames.GameLevel.GameLevel;
import net.fdgames.GameLevel.GameLevelData;
import net.fdgames.GameWorld.GameData;
import net.fdgames.GameWorld.GameWorld;
import net.fdgames.Helpers.FDUtils;
import net.fdgames.Rules.SkillActions;
import y0.b;

/* JADX INFO: loaded from: /tmp/tmp.CKdUvKN3M2/classes.dex */
public class AISkillUsage {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static Character f3195a;

    public static void a(NPC npc) {
        if (!npc.U0() && npc.sheet.skillSet.k("flurry") && FDUtils.b(1, 100) < 20) {
            SkillActions.e(npc);
        }
        if (npc.U0() && npc.sheet.skillSet.k("rapid_fire") && FDUtils.b(1, 100) < 20) {
            SkillActions.i(npc);
        }
        int iOrdinal = npc.sheet.stats.c().ordinal();
        if (iOrdinal == 1) {
            if (npc.U0() || !npc.sheet.skillSet.k("stab") || FDUtils.b(1, 100) >= 10) {
                return;
            }
            SkillActions.k(npc);
            return;
        }
        if (iOrdinal != 2) {
            return;
        }
        SkillSet skillSet = npc.sheet.skillSet;
        String str = lxYOBQSyWPCj.pkxKPsjkAMhs;
        if (skillSet.k(str) && FDUtils.b(1, 100) <= 45) {
            Character characterD = d(npc);
            f3195a = characterD;
            if (characterD != null) {
                npc.x1(str);
                npc.s1(f3195a.q());
                return;
            }
        }
        if (npc.sheet.r() > 0.15f && npc.sheet.skillSet.k("holy_shield") && FDUtils.b(1, 100) < 50) {
            SkillActions.h(npc);
        } else {
            if (npc.U0() || !npc.sheet.skillSet.k("arbenos_might") || FDUtils.b(1, 100) >= 20) {
                return;
            }
            npc.x1("arbenos_might");
        }
    }

    public static void b(NPC npc) {
        MapActor mapActorG;
        int iOrdinal = npc.sheet.stats.c().ordinal();
        if (iOrdinal == 0) {
            if (!npc.U0() && npc.sheet.skillSet.k("whirlwind") && FDUtils.b(1, 100) < 20) {
                npc.g1();
                return;
            }
            if (!npc.U0() && npc.sheet.skillSet.k("bash") && FDUtils.b(1, 100) < 20) {
                npc.Y0();
                return;
            } else {
                if (npc.sheet.r() <= 0.15f || !npc.sheet.skillSet.k("resilience") || FDUtils.b(1, 100) >= 50) {
                    return;
                }
                SkillActions.j(npc);
                return;
            }
        }
        if (iOrdinal != 1) {
            if (iOrdinal == 2 && npc.sheet.r() > 0.15f && npc.sheet.skillSet.k("holy_shield") && FDUtils.b(1, 100) < 50) {
                SkillActions.h(npc);
                return;
            }
            return;
        }
        int i2 = npc.detectedEnemyID;
        if (i2 != 0 && (mapActorG = GameLevel.g(i2)) != null && b.s(npc.B(), mapActorG.B()) < 70 && npc.sheet.skillSet.k("kick") && FDUtils.b(1, 100) < 35) {
            npc.d1();
        }
    }

    public static void c(NPC npc) {
        if (npc.sheet.stats.c().ordinal() == 2 && npc.sheet.skillSet.k("heal_wounds") && FDUtils.b(1, 100) <= 35 && npc.sheet.p() >= npc.sheet.skillSet.f("heal_wounds")) {
            Character characterD = d(npc);
            f3195a = characterD;
            if (characterD != null) {
                npc.x1("heal_wounds");
                npc.s1(f3195a.q());
            }
        }
    }

    private static Character d(NPC npc) {
        if (npc.sheet.r() > 0.5f) {
            return npc;
        }
        if (npc.k0()) {
            AStarPathFinder aStarPathFinder = GameLevel.f3309a;
            if (GameData.v().player.sheet.r() > 0.5f) {
                return GameData.v().player;
            }
        }
        for (NPC npc2 : GameLevelData.t()) {
            if (!npc2.i0() && npc2.f3307x - npc.f3307x < 224 && npc2.f3308y - npc.f3308y < 224 && !GameWorld.f3410c.g(npc.r(), npc2.r()) && npc2.sheet.r() > 0.5f) {
                return npc2;
            }
        }
        return null;
    }
}
